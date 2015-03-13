package com.cssweb.payment.account.sei;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.junit.Test;

import com.cssweb.payment.account.constant.DictionaryKey;
import com.cssweb.payment.account.pojo.AccountInfo;
import com.cssweb.payment.account.pojo.EnterpriseUser;
import com.cssweb.payment.account.pojo.PersonalUser;
import com.cssweb.payment.account.util.DictionaryUtils;
import com.cssweb.payment.account.vo.LoginResponse;
import com.cssweb.payment.account.vo.UserInfo;
import com.cssweb.payment.common.response.AttachmentResponse;

public class AccountServiceTests extends TestCase {

	@Test
	public void testGetAccountInfo() {
		// accountService
		AttachmentResponse<LoginResponse> response = portalService.login(
				"189031234", "123", "127.0.0.1", null,
				DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC));
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		LoginResponse loginResponse = response.getAttachment();
		PersonalUser personalUser = loginResponse.getPersonalUser();
		AttachmentResponse<List<AccountInfo>> accInfoRes = accountService
				.getAccountList(personalUser.getUserId());
		logger.debug("账户查询状态  ：" + accInfoRes.getReturnCode().toString());
		logger.debug("账户查询信息码：" + accInfoRes.getReturnMsg());
		assertTrue(accInfoRes.isSuccess());
		List<AccountInfo> accInfos = accInfoRes.getAttachment();
		AccountInfo accInfo = null;
		if (accInfos != null || accInfos.size() > 0) {
			logger.debug("用户类型 |账户类型| 币 种  |账户状态|可用金额");
			for (int i = 0; i < accInfos.size(); i++) {
				accInfo = accInfos.get(i);
				logger.debug(accInfo.getUserType() + "|"
						+ accInfo.getAccountType() + "|"
						+ accInfo.getCurrencyType() + "|"
						+ accInfo.getAccountStatus() + "|"
						+ accInfo.getAvailableBalance());
			}
		} else {
			logger.debug("用户[" + personalUser.getUserName() + "]下面有账户");
		}
	}

	/**
	 * 登陆（失败） － 获取错误次数 － 正超过次数冻结
	 * 
	 * @author shilei@cssweb.com.cn
	 * @date 2014年7月30日 下午4:11:58 
	 * @since 0.1
	 * @see
	 */
	@Test
	public void verifyLoninPassWord() {
		String passWd = "loginPasswd"+12;
		AttachmentResponse<LoginResponse> response = portalService.login(
				"189031234", passWd, "127.0.0.1", null,
				DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC));
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		if (response.isSuccess()) {
			logger.debug("登陆成功");
			AttachmentResponse<Integer> intRespinse = riskService.getLoginPwdWrong(1L);
			logger.debug("获取密码错误 - 状态值 ： "+intRespinse.getReturnMsg());
			logger.debug("获取密码错误 - 状态信息 ： "+intRespinse.getReturnCode());
			assertTrue(intRespinse.isSuccess());
			logger.debug("你 的密码置为【"+intRespinse.getAttachment()+"】了");
		} else {
			/*
			 * 获取密码核验次数 密码输入错误的接口
			 * 
			 * 在登陆密码输入错误的时候，没有返回用户信息。（即：没有userid）无法调用密码错误次数。getLoginPwdWrong（long
			 * userid）----------修复
			 */
			AttachmentResponse<Integer> intRespinse = riskService.getLoginPwdWrong(1L);
			logger.debug("获取密码错误 - 状态值 ： "+intRespinse.getReturnMsg());
			logger.debug("获取密码错误 - 状态信息 ： "+intRespinse.getReturnCode());
			assertTrue(intRespinse.isSuccess());
			logger.debug("你 连续【"+intRespinse.getAttachment()+"】次输入的密码错误");
			
			
		}
	}

	/***
	 * 登陆 － 验证支付密码 － 获取支付密码错误次数
	 * 
	 * @author shilei@cssweb.com.cn
	 * @date 2014年7月30日 下午4:10:22 
	 * @since 0.1
	 * @see
	 */
	@Test
	public void verfyPayPwd() {

		/* 登陆并获取用户信息 */
		String userName = "189031234";
		String passWd = "loginPasswd";
		AttachmentResponse<LoginResponse> response = portalService.login(
				userName, passWd, "127.0.0.1", null,
				DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC));
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		if (!response.isSuccess()) {
			return;
		}
		PersonalUser personalUser = response.getAttachment().getPersonalUser();
		EnterpriseUser enterpriseUser = response.getAttachment()
				.getEnterpriseUser();
		/* 验证支付密码 */
		String payPwd = "payPasswd" + "111";
		// verifyPayPwd
		AttachmentResponse<Boolean> verRespinse = portalService.verifyPayPwd(
				userName, payPwd);
		logger.debug("状态码：" + verRespinse.getReturnCode().toString());
		logger.debug("状态信息：" + verRespinse.getReturnMsg());
		assertTrue(verRespinse.isSuccess());
		if (verRespinse.getAttachment()) {
			logger.debug("支付密码验证成功！");
		} 
		else {
			logger.debug("支付密码错误");
			Long userId = 0l;
			// 获取支付密码错误次数
			if (personalUser != null) {
				userId = personalUser.getUserId();
			} else if (enterpriseUser != null) {
				userId = enterpriseUser.getUserId();
			} else {
				logger.debug("用户信息错误！", new Exception("user_id获取异常"));
				return;
			}
			AttachmentResponse<Integer> payPwdResponse = riskService
					.getPayPwdWrong(1L);
			logger.debug("状态码：" + payPwdResponse.getReturnCode().toString());
			logger.debug("状态信息：" + payPwdResponse.getReturnMsg());
			assertTrue(payPwdResponse.isSuccess());
			logger.debug("支付密码错误次数：" + payPwdResponse.getAttachment());

			// 支付密码错误次没数有累加
		}

	}

	/**
	 * 登陆 － 冻结 - 冻结提示－ 解冻 -提示 -客户信心查询 - 登出
	 * 
	 * @author shilei@cssweb.com.cn
	 * @date 2014年7月30日 下午4:08:57 
	 * @since 0.1
	 * @see 发现问题： 如何判断用户已经冻结？ // 获取用户冻结状态的接口没有提供 无法判断用户是 冻结、正常、注销 状态；
	 *      目前 据说
	 *      账户状态 == 用户状态 但是冻结解冻只针对了 用户操作 没有对账户修改
	 */
	@Test
	public void freezeUserTest() {
		/* 登陆并获取用户信息 */
		String userName = "189031234";
		String passWd = "loginPasswd";
		AttachmentResponse freeResponse = null;
		String userStarus = "";
		Long userId = 0L;
		// 判断个人用户或企业用业，返回
		String userTypePerson = DictionaryUtils
				.getStringValue(DictionaryKey.USER_TYPE_PERSONAL);
		String userTypeEn = DictionaryUtils
				.getStringValue(DictionaryKey.USER_TYPE_ENTERPRISE);

		AttachmentResponse<LoginResponse> response = portalService.login(
				userName, passWd, "127.0.0.1", null,
				DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC));
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		LoginResponse loginBean = response.getAttachment();
		String statusFrozen = DictionaryUtils.getStringValue(
				DictionaryKey.USER_STATUS_FROZEN).trim();
		String statusCancel = DictionaryUtils.getStringValue(
				DictionaryKey.USER_STATUS_CANCEL).trim();
		logger.debug("statusCancel = " + statusCancel);

		if (userTypeEn.trim().equals(loginBean.getUserType())) {
			EnterpriseUser enUser = loginBean.getEnterpriseUser();
			userId = enUser.getUserId();
		} else if (userTypePerson.trim().equals(loginBean.getUserType())) {
			PersonalUser perUser = loginBean.getPersonalUser();
			userId = perUser.getUserId();
		}
		// 获取用户冻结状态的接口没有提供 无法判断用户是 冻结、正常、注销 状态；
		// 目前 据说 账户状态 == 用户状态 但是冻结解冻只针对了 用户操作 没有对账户修改
		userStarus = "2";
		if (statusCancel.equals(userStarus)) {
			logger.debug("账户已经注销");
			return;
		}
		if (!statusFrozen.equals(userStarus)) {
			logger.debug("用户未冻结－－－－");
			freeResponse = riskService.freezeUser(userId,"");
			logger.debug("用户冻结-状态码：" + freeResponse.getReturnCode().toString());
			logger.debug("用户冻结-状态信息：" + freeResponse.getReturnMsg());
			assertTrue(freeResponse.isSuccess());
			logger.debug("－－－－用户冻结－－－－");
			return;
		}
		logger.debug("账户冻结，需要解冻－－－－");
		// 实名认证
		freeResponse = riskService.unfreezeUser(userId,"");
		logger.debug("用户已冻结－－－－");
		logger.debug("用户解冻-状态码：" + freeResponse.getReturnCode().toString());
		logger.debug("用户解冻-状态信息：" + freeResponse.getReturnMsg());
		assertTrue(freeResponse.isSuccess());
		logger.debug("－－－－用户解冻－－－－");
		if (userTypeEn.trim().equals(loginBean.getUserType())) {
			EnterpriseUser enUser = loginBean.getEnterpriseUser();
			logger.debug(enUser.getAuthStatus());
			// logger.debug(enUser.getAuthDate().toString());
			logger.debug(enUser.getSource());
		} else if (userTypePerson.trim().equals(loginBean.getUserType())) {
			PersonalUser perUser = loginBean.getPersonalUser();
			logger.debug(perUser.getAuthStatus());
			// logger.debug(perUser.getAuthDate().toString());
			logger.debug(perUser.getSource());
		}
		AttachmentResponse<String> logoutResponse = portalService
				.logout(userId);
		logger.debug("用户登出-状态码：" + logoutResponse.getReturnCode().toString());
		logger.debug("用户登出-状态信息：" + logoutResponse.getReturnMsg());
		assertTrue(logoutResponse.isSuccess());
	}

	/***
	 * 登陆 - 获取账户列表 - 获取账户状态 - 获取可用余额
	 * 
	 * @author shilei@cssweb.com.cn
	 * @date 2014年7月31日 上午11:36:48 
	 * @since 0.1
	 * @see
	 */
	@Test
	public void accountTest() {
		// 判断个人用户或企业用业，返回
		String userTypePerson = DictionaryUtils
				.getStringValue(DictionaryKey.USER_TYPE_PERSONAL);
		String userTypeEn = DictionaryUtils
				.getStringValue(DictionaryKey.USER_TYPE_ENTERPRISE);
		LoginResponse loginBean = getUserId();
		String userType = loginBean.getUserType();
		Long userId = 0L;
		if (userTypeEn.equals(userType)) {
			logger.debug("企业用户");
			userId = loginBean.getEnterpriseUser().getUserId();
		} else if (userTypePerson.equals(userType)) {
			logger.debug("个人用户");
			userId = loginBean.getPersonalUser().getUserId();
		}
		AttachmentResponse<List<AccountInfo>> accountsPesp = accountService
				.getAccountList(userId);
		logger.debug("获取账户列表 - 状态值：" + accountsPesp.getReturnCode());
		logger.debug("获取账户列表 - 状态信息：" + accountsPesp.getReturnMsg());
		assertTrue(accountsPesp.isSuccess());

		AttachmentResponse<UserInfo> strResponse = null;
		AttachmentResponse<AccountInfo> accResp = null;
		logger.debug("----------------------账户列表------------------------------");
		for (AccountInfo accInfo : accountsPesp.getAttachment()) {
			logger.debug(" 账户标志 ：" + accInfo.getAccountId());
			logger.debug(" 可用余额 ：" + accInfo.getAvailableBalance());
			strResponse = accountService.accountStatusQuery(accInfo
					.getAccountId());
			logger.debug("获取【" + accInfo.getAccountId() + "】账户状态-状态值"
					+ strResponse.getReturnCode());
			logger.debug("获取【" + accInfo.getAccountId() + "】账户状态-状态信息"
					+ strResponse.getReturnMsg());
			assertTrue(strResponse.isSuccess());
			UserInfo userInfo = strResponse.getAttachment();
			logger.debug("用户姓名" + userInfo.getName());
			logger.debug("用户Email" + userInfo.getEmail());
			logger.debug("用户状态" + userInfo.getUserStatus());
			logger.debug("用户电话" + userInfo.getTel());
			logger.debug("用户类型" + userInfo.getUsertype());
			logger.debug("用户ID" + userInfo.getUserId());
			logger.debug("账户ID" + userInfo.getAccountId());
			logger.debug("-----------------------------------------------------");
			accResp = accountService
					.accountBalanceQuery(accInfo.getAccountId());
			logger.debug("获取【" + accInfo.getAccountId() + "】账户余额-状态值"
					+ accResp.getReturnCode());
			logger.debug("获取【" + accInfo.getAccountId() + "】账户余额-状态信息"
					+ accResp.getReturnMsg());
			assertTrue(accResp.isSuccess());
			logger.debug("账户余额" + accResp.getAttachment().getAvailableBalance());
			logger.debug("--------------------- -----------------------------");
		}
		String userName = "189031234";
		String payPwd = "payPasswd";
		logger.debug("--------  用户名 和 密 码 查询账户余额  ------------");
		String accountTypeMain = DictionaryUtils
				.getStringValue(DictionaryKey.ACCOUNT_TYPE_MAIN);
		logger.debug("主账户的账户类别：" + accountTypeMain); // 1 2 数据库存储的时候 01 02
														// 赋值的时候也希望是从数据字典中查询到人值，这样就不会出错
		accResp = accountService.getBalanceByUsername(userName, payPwd);
		logger.debug("获取[" + userName + "账户余额-状态值" + accResp.getReturnCode());
		logger.debug("获取[" + userName + "账户余额-状态信息" + accResp.getReturnMsg());
		assertTrue(accResp.isSuccess());
		logger.debug("账户余额" + accResp.getAttachment().getAvailableBalance());
		AttachmentResponse<String> logoutResponse = portalService
				.logout(userId);
		logger.debug("用户登出-状态码：" + logoutResponse.getReturnCode().toString());
		logger.debug("用户登出-状态信息：" + logoutResponse.getReturnMsg());
		assertTrue(logoutResponse.isSuccess());
	}

	@Test
	public void testAccountStatusQuery(){
		AttachmentResponse<UserInfo> response = accountService.accountStatusQuery(56L);
		logger.debug( "邮箱"+response.getAttachment().getEmail() );
		logger.debug( "用户姓名 "+response.getAttachment().getName());
		logger.debug( "用户类别 "+response.getAttachment().getUsertype());
		logger.debug( "手机号码"+response.getAttachment().getTel());
		logger.debug( "userId"+response.getAttachment().getUserId());
		logger.debug( "主账户"+response.getAttachment().getAccountId());
		logger.debug( "账户状态"+response.getAttachment().getAccountStatus());
		assertTrue(response.isSuccess());
	}
	
	/**
	 * 公用的登陆回去loginBean的方法
	 * 
	 * @author shilei@cssweb.com.cn
	 * @date 2014年7月31日 上午11:36:16
	 * @return 
	 * @since 0.1
	 * @see
	 */
	private LoginResponse getUserId() {
		/* 登陆并获取用户信息 */
		String userName = "189031234";
		String passWd = "123";
		AttachmentResponse<LoginResponse> response = portalService.login(
				userName, passWd, "127.0.0.1", null,
				DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC));
		logger.debug("登陆 - 状态码：" + response.getReturnCode().toString());
		logger.debug("登陆 - 状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		return response.getAttachment();
	}

}
