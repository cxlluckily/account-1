package com.cssweb.payment.account.sei;

import org.junit.Test;

import com.cssweb.payment.account.pojo.EnterpriseUser;
import com.cssweb.payment.account.vo.AccountDetailed;
import com.cssweb.payment.account.vo.CommercialTenantAccount;
import com.cssweb.payment.account.vo.CommercialTenantAccountDetailed;
import com.cssweb.payment.account.vo.EnterpriseUserQuery;
import com.cssweb.payment.account.vo.GetBusinessInfoListResponse;
import com.cssweb.payment.account.vo.GetEnterpriseUserListResponse;
import com.cssweb.payment.account.vo.GetPersonalUserListResponse;
import com.cssweb.payment.account.vo.PaymentAccount;
import com.cssweb.payment.account.vo.PaymentAccountDetailed;
import com.cssweb.payment.account.vo.PersonalUserQuery;
import com.cssweb.payment.account.vo.UserAccount;
import com.cssweb.payment.common.response.AttachmentResponse;
import com.cssweb.payment.common.util.Page;

public class ManageServiceTests extends TestCase {
	
	
	
	/**
	 * 3.50
	 * @since 0.1
	 */
	@Test
	public void testGetPersonalUserList() {
		
		AttachmentResponse<Page<GetPersonalUserListResponse>> response;
		PersonalUserQuery query = new PersonalUserQuery();
		query.setPageSize(5);
		query.setPageNo(1);
		response = manageService.getPersonalUserList(query);
		logger.debug("状态码：" + response.getReturnCode());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
	}
	/**
	 * 3.51	企业客户信息列表查询接口
	 */
	@Test
	public void testGetEnterpriseUserList() {
		EnterpriseUserQuery para = new EnterpriseUserQuery();
		para.setPageSize(20);
		para.setPageNo(1);
		AttachmentResponse<Page<GetEnterpriseUserListResponse>> response = manageService.getEnterpriseUserList(para);
		logger.debug("状态码：" + response.getReturnCode());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
	}
	
	/**
	 * 3.54商户信息列表查询接口
	 */
	@Test
	public void testGetMerchant() {

		AttachmentResponse<Page<GetEnterpriseUserListResponse>> response;
		EnterpriseUserQuery query = new EnterpriseUserQuery();
		query.setBusinessName("我是商家名字");
		query.setPageSize(4);
		query.setPageNo(1);
		response = manageService.getMerchant(query);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
	}
	/**
	 * 3.56	账户列表（个人）查询接口
	 */
	@Test
	public void testGetAccountInfoList(){
		AttachmentResponse<Page<UserAccount>> response = manageService
				.getAccountInfoList("高鸿全", null,null, null, null, null, null, 8, 1);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
	}
	/**
	 * 3.62	账户（平台）列表查询接口
	 */
	@Test
	public void testQueryPaymentAccount(){
		AttachmentResponse<Page<PaymentAccount>> response = manageService.queryPaymentAccount("1", 5, 1);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
	}
	/**
	 *
	 * 3.59	账户列表（企业）查询接口
	 */
	@Test
	public void testQueryCommercialTenantAccount(){
		AttachmentResponse<Page<CommercialTenantAccount>> response = manageService
				.queryCommercialTenantAccount("李四", null, "1", null, null, null,null, 5, 1);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
	}
	/**
	 * 3.57	账户（个人 ）资金变化明细列表查询接口
	 */
	@Test
	public void testQueryUserAccountDetailed(){
		AttachmentResponse<Page<AccountDetailed>> response = manageService
				.queryUserAccountDetailed(null, null, null, null, null, null, 5, 1);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
	}
	/**
	 * 
	 */
	@Test
	public void testQueryCTAccountDetailed(){
		AttachmentResponse<Page<CommercialTenantAccountDetailed>> response = manageService
				.queryCTAccountDetailed(null, null, null, null, null, null, 5, 1);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
	}
	
	/**
	 * 3.63	账户（平台）资金变化明细查询接口
	 */
	@Test
	public void testQueryPaymentAccountDetailed(){
		AttachmentResponse<Page<PaymentAccountDetailed>> response = manageService
				.queryPaymentAccountDetailed(null, null, null, null, 5, 1);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
	}
	
	@Test
	public void testGetBusinessInfoList() {
		AttachmentResponse<GetBusinessInfoListResponse> response = manageService.getBusinessInfoList(100L);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
	}
	
}
