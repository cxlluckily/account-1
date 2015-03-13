package com.cssweb.payment.account.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.cssweb.payment.account.pojo.AccountInfo;
import com.cssweb.payment.account.sei.IAccountService;
import com.cssweb.payment.account.test.TestCase;
import com.cssweb.payment.account.vo.UserInfo;
import com.cssweb.payment.common.response.AttachmentResponse;

public class AccountServiceTests extends TestCase {
	
	@Resource
	private IAccountService accountService;
	
	/**
	 * 测试 查询账户状态 方法
	 * @author zhanwx/ganlin
	 * @date 2014年7月21日 下午2:51:49 
	 * @since 0.1
	 * @see
	 */
	@Test
	public void testAccountStatusQuery() {
		AttachmentResponse<UserInfo> response = accountService.accountStatusQuery(99999999999999L);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		AttachmentResponse<UserInfo> response1 = accountService.accountStatusQuery(null);
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		assertFalse(response1.isSuccess());
		AttachmentResponse<UserInfo> response2 = accountService.accountStatusQuery(266L);
		logger.debug("状态码：" + response2.getReturnCode().toString());
		logger.debug("状态信息：" + response2.getReturnMsg());
		logger.debug("-------------------------打印信息-------------------");
		logger.debug("账户状态码："+response2.getAttachment().getAccountStatus());
		logger.debug("账户状态：   "+response2.getAttachment().getAccountStatusName());
		logger.debug("用户类型码："+response2.getAttachment().getUsertype());
		logger.debug("用户类型：   "+response2.getAttachment().getUsertypeName());
		logger.debug("账户id：      "+response2.getAttachment().getAccountId().toString());
		logger.debug("用户id：      "+response2.getAttachment().getUserId().toString());
		logger.debug("用户名称：   "+response2.getAttachment().getName());
		logger.debug("用户邮箱：   "+response2.getAttachment().getEmail());
		logger.debug("用户电话：   "+response2.getAttachment().getTel());
		
		assertTrue(response2.isSuccess());
	}
	
	/**
	 * 测试 查询账户余额 方法
	 * @author zhanwx/ganlin
	 * @date 2014年7月21日 下午2:51:49 
	 * @since 0.1
	 * @see
	 */
	@Test
	public void testAccountBalanceQuery() {
		AttachmentResponse<AccountInfo> response = accountService.accountBalanceQuery(99999999999999L);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		AttachmentResponse<AccountInfo> response1 = accountService.accountBalanceQuery(null);
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		assertFalse(response1.isSuccess());
		AttachmentResponse<AccountInfo> response2 = accountService.accountBalanceQuery(51L);
		logger.debug("状态码：" + response2.getReturnCode().toString());
		logger.debug("状态信息：" + response2.getReturnMsg());
		logger.debug("----------打印信息------------------------");
		logger.debug("可提现金额   ："+response2.getAttachment().getAvailableBalance());
		logger.debug("冻结金额      ："+response2.getAttachment().getFrozenAmount());
		logger.debug("不可提现金额："+response2.getAttachment().getNomentionAmount());
		assertTrue(response2.isSuccess());
	}
	
	/**
	 * 测试 查询用户所拥有的账户列表 方法
	 * @author zhanwx/ganlin
	 * @date 2014年7月21日 下午2:51:49 
	 * @since 0.1
	 * @see
	 */
	@Test
	public void testgetAccountList() {
		AttachmentResponse<List<AccountInfo>> response = accountService.getAccountList(99999999999999L);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		AttachmentResponse<List<AccountInfo>> response1 = accountService.getAccountList(null);
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		assertFalse(response1.isSuccess());
		AttachmentResponse<List<AccountInfo>> response2 = accountService.getAccountList(501L);
		logger.debug("状态码：" + response2.getReturnCode().toString());
		logger.debug("状态信息：" + response2.getReturnMsg());
		logger.debug("------------------------打印信息-----------------");
		logger.debug("用户ID:"+response2.getAttachment().get(0).getUserId());
		logger.debug("子账户个数:"+response2.getAttachment().size());
		for(int i = 0; i < response2.getAttachment().size();i++){
			logger.debug("子账户"+(i+1)+"ID:"+response2.getAttachment().get(i).getAccountId().toString());
		}
		assertTrue(response2.isSuccess());
	}
	
	/**
	 * 测试 查询费率 方法
	 * @author zhanwx/ganlin
	 * @date 2014年7月21日 下午2:51:49 
	 * @since 0.1
	 * @see
	 */
	@Test
	public void testgetRate() {
		AttachmentResponse<Float> response = accountService.getRate(2L, "002");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		AttachmentResponse<Float> response1 = accountService.getRate(null,null);
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		assertFalse(response1.isSuccess());
		AttachmentResponse<Float> response2 = accountService.getRate(1L, "1");
		logger.debug("状态码：" + response2.getReturnCode().toString());
		logger.debug("状态信息：" + response2.getReturnMsg());
		logger.debug("------------------------打印信息-----------------");
		logger.debug("费率:"+response2.getAttachment().toString());
		assertTrue(response2.isSuccess());
	}
	
	/**
	 * 测试通过用户名（手机或邮箱）查询主账户可提现余额
	 * @author Ganlin
	 * @date 2014年7月25日 上午11:12:06 
	 * @since 0.1
	 * @see
	 */
	@Test
	public void testGetBalanceByUsername() {
		AttachmentResponse<AccountInfo> response;
		//用户名不存在
		/*response = accountService.getBalanceByUsername("15210830453123111", "payPwd");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());*/
		//支付密码错误
		response = accountService.getBalanceByUsername("119", "123456");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		//成功
		/*response = accountService.getBalanceByUsername("ryhrr@ghjj.com", "Qwerty");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("------------------------打印信息-----------------");
		logger.debug("主账户ID:"+response.getAttachment().getAccountId());
		logger.debug("主账户可提现余额:"+response.getAttachment().getAvailableBalance());
		assertTrue(response.isSuccess());*/
	}
	
	@Test
	public void testQueryUserBasicInfoByAccountId(){
		AttachmentResponse<UserInfo> response;
		//参数为空
		response = accountService.queryUserBasicInfoByAccountId(null);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		//账户不存在
		response = accountService.queryUserBasicInfoByAccountId(999999L);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		//成功
		response = accountService.queryUserBasicInfoByAccountId(61L);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("------------------------打印信息-----------------");
		logger.debug("用户ID："+response.getAttachment().getUserId());
	}
}
