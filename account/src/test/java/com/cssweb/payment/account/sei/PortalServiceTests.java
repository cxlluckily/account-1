package com.cssweb.payment.account.sei;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.cssweb.payment.account.constant.DictionaryKey;
import com.cssweb.payment.account.pojo.EnterpriseUser;
import com.cssweb.payment.account.pojo.PersonalUser;
import com.cssweb.payment.account.pojo.UserBandBankInfo;
import com.cssweb.payment.account.pojo.UserQuestionInfo;
import com.cssweb.payment.account.util.DictionaryUtils;
import com.cssweb.payment.account.vo.GetEnterpriseUserBasicInfoResponse;
import com.cssweb.payment.account.vo.LoginResponse;
import com.cssweb.payment.account.vo.UserBasicInfoResponse;
import com.cssweb.payment.common.response.AttachmentResponse;

public class PortalServiceTests extends TestCase {
	
	
	/**
	 * 测试安全问题模块
	 * @author zhanwx
	 * @date 2014年7月30日 下午3:48:44 
	 * @since 0.1
	 * @see
	 */
/*	@Test
	public void testQuestion(){
		//用户登录
		AttachmentResponse<LoginResponse> login = portalService.login("187011234", "521314", "125.123.2.1", "D3G4G6J5G4H3G4", "PC");
		logger.debug("状态码:" + login.getReturnCode().toString());
		logger.debug("状态信息:" + login.getReturnMsg() + "(用户登录)");
		assertTrue(login.isSuccess());
		Long userId = login.getAttachment().getPersonalUser().getUserId();
		//查询是否有安全问题
		AttachmentResponse<Integer> isExist = portalService.safeQuestionIsExist(userId);
		if(isExist.getReturnCode() == 0){
			//存在安全问题
			logger.debug("状态码:" + isExist.getReturnCode().toString());
			logger.debug("状态信息:" + isExist.getReturnMsg() + "(存在安全问题)");
			assertTrue(isExist.isSuccess());
			//返回安全问题列表
			AttachmentResponse<List<UserQuestionInfo>> qlist = portalService.getQuestion(userId);
			logger.debug("状态码:" + qlist.getReturnCode().toString());
			logger.debug("状态信息:" + qlist.getReturnMsg() + "(返回安全问题列表)");
			assertTrue(qlist.isSuccess());
			//检查安全问题是否匹配
			int length = qlist.getAttachment().size();
			AttachmentResponse<String> question = null;
			String[] qid = {"1","2","3"};
			String[] ans = {"我","好","吗"};
			Boolean flag = true;
			for(int i = 0; i < length;i++ ){
				question = portalService.checkSafeQuestionIsOk(userId, qid[i],ans[i]);
				if(question.getReturnCode() == 1406 || question.getReturnCode() == 1408){
					logger.debug("状态码:" + question.getReturnCode().toString());
					logger.debug("状态信息:" + question.getReturnMsg() + "，题号为" + qid[i]);
					assertFalse(question.isSuccess());
					flag = false;
					break;
				}
			}
			if(flag == true){
				logger.debug("状态码:" + question.getReturnCode().toString());
				logger.debug("状态信息:" + question.getReturnMsg()+ "(安全问题匹配)");
				assertTrue(question.isSuccess());
			}
		}
		else{
			AttachmentResponse<String> setQt = portalService.setSafeQuestion(userId, "1","我","2","很","3","好");
			logger.debug("状态码:" + setQt.getReturnCode().toString());
			logger.debug("状态消息:" + setQt.getReturnMsg()+ "(添加安全问题)");
			assertTrue(setQt.isSuccess()); 
		}
	}
	
	*//**
	 * 3.43
	 *//*
	@Test
	public void testSetSafeQuestion(){
		AttachmentResponse<String> setQt = portalService.setSafeQuestion(67L, "1234","我234","2234","很234","3234","好234");
		logger.debug("状态码:" + setQt.getReturnCode().toString());
		logger.debug("状态消息:" + setQt.getReturnMsg()+ "(添加安全问题)");
		assertTrue(setQt.isSuccess()); 
	}
	*//**
	 * 测试 企业用户注册、登录、信息查询、修改、登出
	 * @author zhanwx
	 * @date 2014年7月31日 下午1:05:43 
	 * @since 0.1
	 * @see
	 *//*
	@Test
	public void testEURegister(){
		//检测用户名是否存在
		AttachmentResponse<Boolean> isExist = portalService.checkUserNameIsExits("1008655@chinaMobile.com");
		if(isExist.getAttachment()){
			logger.debug("状态码:" + isExist.getReturnCode().toString());
			logger.debug("状态信息:" + isExist.getReturnMsg());
			assertTrue(isExist.isSuccess());
		}
		else{
			String source = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);
			String close   = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_NO);
			//用户注册
			AttachmentResponse<Long> register = portalService.enterpriseRegistration("1008655@chinaMobile.com", "root","root",1,"北京",source);
			logger.debug("状态码:" + register.getReturnCode().toString());
			logger.debug("状态信息:" + register.getReturnMsg() + "（注册成功）");
			assertTrue(register.isSuccess());
			//用户登录
			AttachmentResponse<LoginResponse> login = portalService.login("1008655@chinaMobile.com", "root", "125.123.2.1", "D3G4G6J5G4H3G4", source);
			logger.debug("状态码:" + login.getReturnCode().toString());
			logger.debug("状态信息:" + login.getReturnMsg() + "（登录成功）");
			assertTrue(login.isSuccess());
			//用户信息查询
			EnterpriseUser eu = new EnterpriseUser();
			eu = login.getAttachment().getEnterpriseUser();
			AttachmentResponse<GetEnterpriseUserBasicInfoResponse> getUser = portalService.getEnterpriseUserBasicInfo(eu.getUserId());
			logger.debug("状态码:" + login.getReturnCode().toString());
			logger.debug("状态信息:" + login.getReturnMsg() + "（信息查询成功）");
			assertTrue(getUser.isSuccess());
			//用户信息修改
			eu.setBusinessName("ChinaMobile");
			AttachmentResponse<String> updateUser = portalService.updateEnterprise(eu,"12");
			logger.debug("状态码:" + updateUser.getReturnCode().toString());
			logger.debug("状态信息:" + updateUser.getReturnMsg() + "（信息修改成功）");
			assertTrue(updateUser.isSuccess());
			//关闭短信提醒功能
			AttachmentResponse<String> closeSMS = portalService.smsAlertsOpenOrClose(eu.getUserId(), close, source);
			logger.debug("状态码:" + closeSMS.getReturnCode().toString());
			logger.debug("状态消息:" + closeSMS.getReturnMsg() + "(关闭短信提醒功能)");
			assertTrue(closeSMS.isSuccess());
			//关闭余额支付功能
			AttachmentResponse<String> closePay = portalService.balancePayOpenOrClose(eu.getUserId(), close, source);
			logger.debug("状态码:" + closePay.getReturnCode().toString());
			logger.debug("状态消息" + closePay.getReturnMsg() + "(关闭余额支付功能)");
			assertTrue(closePay.isSuccess());
			//用户登出
			AttachmentResponse<String> logout = portalService.logout(eu.getUserId());
			logger.debug("状态码:" + logout.getReturnCode().toString());
			logger.debug("状态消息:" + logout.getReturnMsg() + "(登出成功)");
			assertTrue(logout.isSuccess());
		}
	}	
	
	
	
	*//**
	 * 测试 个人用户注册、登录、信息查询、修改、登出
	 * @author zhanwx
	 * @date 2014年7月30日 下午1:06:09 
	 * @since 0.1
	 * @see
	 *//*
	@Test
	public void testRegister(){
		String country  = DictionaryUtils.getStringValue(DictionaryKey.COUNTRY_CODE_CN);
		String regType  = DictionaryUtils.getStringValue(DictionaryKey.REG_TYPE_PHONE);
		String source   = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_MOBILE);
		String cardType = DictionaryUtils.getStringValue(DictionaryKey.BANK_CARD_TYPE_CREDIT);
		//检测用户名是否存在
		AttachmentResponse<Boolean> isExist = portalService.checkUserNameIsExits("285354770@qq.com");
		if(isExist.getAttachment()){
			logger.debug("状态码:" + isExist.getReturnCode().toString());
			logger.debug("状态信息:" + isExist.getReturnMsg());
			assertTrue(isExist.isSuccess());
		}
		else{
			//用户注册
			AttachmentResponse<Long> register = portalService.personalRegistration(country, "15625114720","占伟雄",
					 "285354770@qq.com", regType, source, cardType, "20102100161", "root", "root", 1, "广东湛江");
			logger.debug("状态码:" + register.getReturnCode().toString());
			logger.debug("状态信息:" + register.getReturnMsg());
			assertTrue(register.isSuccess());
			//用户登录
			AttachmentResponse<LoginResponse> login = portalService.login("15625114720", "root", "125.123.2.1", "D3G4G6J5G4H3G4", source);
			logger.debug("状态码:" + login.getReturnCode().toString());
			logger.debug("状态信息:" + login.getReturnMsg());
			assertTrue(login.isSuccess());
			//用户信息查询
			PersonalUser pu = new PersonalUser();
			pu = login.getAttachment().getPersonalUser();
			AttachmentResponse<UserBasicInfoResponse> getUser = portalService.getUserBasicInfo(pu.getUserId());
			logger.debug("状态码:" + login.getReturnCode().toString());
			logger.debug("状态信息:" + login.getReturnMsg());
			assertTrue(getUser.isSuccess());
			//用户信息修改
			//pu = getUser.getAttachment().getPersonalUser();
			pu.setUserName("符晓雷");
			AttachmentResponse<String> updateUser = portalService.updatePersonal(pu,"12");
			logger.debug("状态码:" + updateUser.getReturnCode().toString());
			logger.debug("状态信息:" + updateUser.getReturnMsg());
			assertTrue(updateUser.isSuccess());
			//密码修改
			String pwdType = DictionaryUtils.getStringValue(DictionaryKey.PWD_TYPE_LOGIN);
			Long userId = pu.getUserId();
			AttachmentResponse<String> updatePwd = portalService.updatePwd(userId,"root2", pwdType, source);
			logger.debug("状态码:" + updatePwd.getReturnCode().toString());
			logger.debug("状态消息:" + updatePwd.getReturnMsg());
			assertTrue(updatePwd.isSuccess());
			//预留信息修改
			AttachmentResponse<String> updateRes = portalService.reserveMessageUpdate(userId, "我是预留信息啊", source);
			logger.debug("状态码:" + updateRes.getReturnCode().toString());
			logger.debug("状态消息" + updateRes.getReturnMsg());
			assertTrue(updateRes.isSuccess());
			//用户登出
			AttachmentResponse<String> logout = portalService.logout(userId);
			logger.debug("状态码:" + logout.getReturnCode().toString());
			logger.debug("状态消息:" + logout.getReturnMsg());
			assertTrue(logout.isSuccess());
		}
	}
	
	@Test
	public void testGetUserBasicInfo(){
		AttachmentResponse<UserBasicInfoResponse> response = portalService.getUserBasicInfo(66L);
		logger.debug("状态码:" + response.getReturnCode().toString());
		logger.debug("状态信息:" + response.getReturnMsg());
		assertTrue(response.isSuccess());
	}
	@Test
	public void testGetEnterpriseUserBasicInfo(){
		AttachmentResponse<GetEnterpriseUserBasicInfoResponse> response = portalService.getEnterpriseUserBasicInfo(71L);
		logger.debug("状态码:" + response.getReturnCode().toString());
		logger.debug("状态信息:" + response.getReturnMsg());
		assertTrue(response.isSuccess());
	}
	@Test
	public void testSafeQuestionIsExist(){
		AttachmentResponse<Integer> response = portalService.safeQuestionIsExist(67L);
		logger.debug("状态码:" + response.getReturnCode().toString());
		logger.debug("状态信息:" + response.getReturnMsg());
		assertTrue(response.isSuccess());
	}
	
	@Test
	public void testAddBankCard(){
		UserBandBankInfo ubb = new UserBandBankInfo();
		ubb.setBankProvince("北京市");
		ubb.setBankCity("北京市");
		ubb.setUserId(68L);
		ubb.setBankAccountNo("1111234567");
		AttachmentResponse<String> response = portalService.addBankCard(ubb);
		logger.debug("状态码:" + response.getReturnCode().toString());
		logger.debug("状态信息:" + response.getReturnMsg());
		assertTrue(response.isSuccess());
	}
	
	*//**
	 * 3.25个人用户基本信息修改接口
	 *//*
	@Test
	public void testUpdatePersonal(){
		PersonalUser user = new PersonalUser();
		user.setUserId(80L);
		user.setPhoneNumber("1234567");
		user.setEmailAddress("1234567@qq.com");
		AttachmentResponse<String> updateUser = portalService.updatePersonal(user,null);
		logger.debug("状态码:" + updateUser.getReturnCode().toString());
		logger.debug("状态信息:" + updateUser.getReturnMsg());
		assertTrue(updateUser.isSuccess());
	}
	*//**
	 *  3.31企业客户基本信息修改接口
	 *//*
	@Test
	public void testUpdateEnterprise(){
		EnterpriseUser user = new EnterpriseUser();
		user.setUserId(72L);
		user.setManagerMobile("1234567");
		user.setEmail("1234567@qq.com");
		AttachmentResponse<String> updateUser = portalService.updateEnterprise(user,null);
		logger.debug("状态码:" + updateUser.getReturnCode().toString());
		logger.debug("状态信息:" + updateUser.getReturnMsg());
		assertTrue(updateUser.isSuccess());
	}
	*//**
	 * 手机或邮箱同步到登录表中
	 *//*
	@Test
	public void testLoginNameSynchronous(){
		AttachmentResponse<String> response = portalService.loginNameSynchronous(213L, "01");
		logger.debug("状态码:" + response.getReturnCode().toString());
		logger.debug("状态信息:" + response.getReturnMsg());
		assertTrue(response.isSuccess());
	}
	*//**
	 * 3.18	账户支付密码验证接口
	 * 1.入参检查
	 * 2.判断入参是否纯数字，以便通过手机号或邮箱进行查询
	 * 3.查询用户登录表判断用户是否存在，不存在即返回提示
	 * 4.判断密码是否匹配，返回结果
	 * 5.异常处理
	 *//*
	@Test
	public void testVerifyPayPwd(){
		AttachmentResponse<Boolean> response = portalService.verifyPayPwd("wangyf@cssweb.sh.cn", "123456");
		logger.debug("状态信息:" + response.getReturnMsg());
		assertTrue(response.isSuccess());
	}
	*/
	@Test
	public void testGetPersonalAuDate(){
		AttachmentResponse<Date> res = portalService.getPersonalAuDate(212L);
		assertTrue(res.isSuccess());
	}
	
	
}
