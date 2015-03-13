package com.cssweb.payment.account.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;

import org.junit.Test;

import com.cssweb.payment.account.constant.DictionaryKey;
import com.cssweb.payment.account.pojo.BankInfo;
import com.cssweb.payment.account.pojo.BusinessInfo;
import com.cssweb.payment.account.pojo.EnterpriseUser;
import com.cssweb.payment.account.sei.IManageService;
import com.cssweb.payment.account.test.TestCase;
import com.cssweb.payment.account.util.DictionaryUtils;
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
import com.cssweb.payment.account.vo.UserInfo;
import com.cssweb.payment.common.response.AttachmentResponse;
import com.cssweb.payment.common.util.Page;

public class ManagerServiceTests  extends TestCase {
	@Resource
	private IManageService manageService;
	
/*	@Test
	public void testSigningSearch() {
		AttachmentResponse<BusinessInfo> response1 = manageService.signingSearch(null,null);
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		assertFalse(response1.isSuccess());
		
		AttachmentResponse<BusinessInfo> response = manageService.signingSearch(100L, "99");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		
		AttachmentResponse<BusinessInfo> response2 = manageService.signingSearch(245L,"create_direct_pay_by_use");
		logger.debug("状态码：" + response2.getReturnCode().toString());
		logger.debug("状态信息：" + response2.getReturnMsg());
		assertTrue(response2.isSuccess());
	}

	@Test
	public void testMerchantSigning() {
		BusinessInfo bi = new BusinessInfo();
		bi.setUserId(91L);
		BusinessInfo bi2 = new BusinessInfo();
		bi2.setUserId(154L);
		List<BusinessInfo> list = new ArrayList<BusinessInfo>();
		list.add(bi);
		list.add(bi2);
		AttachmentResponse<String> response = manageService.merchantSigning(list);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
	}


	@Test
	public void testUpdateMerchant() {
		//TODO 修改入参为对象后测试
		BusinessInfo businessInfo = new BusinessInfo();
		businessInfo.setId(110L);
		businessInfo.setMerchKey("82194834");
		AttachmentResponse<String> response1 = manageService.updateMerchant(businessInfo);
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		//assertFalse(response1.isSuccess());
		assertTrue(response1.isSuccess());
	}	
	
	
 		3.55	商户解约接口
	 
	@Test
	public void testMerchantTermination() {
//		AttachmentResponse<String> response = manageService.merchantTermination(36L);
//		assertTrue(response.isSuccess());
	}
	*/


	/**
	 * 3.50
	 * @author Ganlin
	 * @date 2014年7月28日 下午2:44:09 
	 * @since 0.1
	 */
	
	@Test
	public void testGetPersonalUserList() {
		
		AttachmentResponse<Page<GetPersonalUserListResponse>> response;
		PersonalUserQuery query = new PersonalUserQuery();
		//参数null
		response = manageService.getPersonalUserList(query);
		logger.debug("状态码：" + response.getReturnCode());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		//分页查询
		query.setPageSize(5);
		query.setPageNo(1);
		//query.setPhoneNumber("15555555557");
		query.setReserve1("1");
		query.setAuthStatus("5");
		response = manageService.getPersonalUserList(query);
		logger.debug("状态码：" + response.getReturnCode());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
	}
	
	/*
	@Test
	public void testGetEnterpriseUserList() {
		EnterpriseUserQuery para = new EnterpriseUserQuery();
		para.setBusinessRegamount(new BigDecimal(0));
		para.setPageSize(2);
		para.setPageNo(1);
		para.setBusinessName("测试股份公司");
		AttachmentResponse<Page<GetEnterpriseUserListResponse>> response = manageService.getEnterpriseUserList(para);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("状态信息：" + response.getAttachment().getData());
		List list=response.getAttachment().getData();
		logger.debug("===="+list.size());
		for(int i=0;i<list.size();i++){
			GetEnterpriseUserListResponse res=(GetEnterpriseUserListResponse)list.get(i);
			logger.debug("reason====="+res.getFreezeReason());
		}
		assertTrue(response.isSuccess());
	}*/
	/*
	@Test
	public void testGetMerchant() {

		AttachmentResponse<Page<GetEnterpriseUserListResponse>> response;
		EnterpriseUserQuery query = new EnterpriseUserQuery();
		query.setUserId(236L);
		query.setPageSize(4);
		query.setPageNo(1);
		response = manageService.getMerchant(query);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
	}
	
	*//**
	 * 
	 * @author Ganlin
	 * @date 2014年7月27日 下午6:02:12 
	 * @since 0.1
	 *//*
	@Test
	public void testQueryPaymentAccount() {
		
		AttachmentResponse<Page<PaymentAccount>> response;
		//无参查询
		response = manageService.queryPaymentAccount(null, 2, 1);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		//有参查询
		String coType = DictionaryUtils.getStringValue(DictionaryKey.BANK_COOP_COOP);
		response = manageService.queryPaymentAccount(coType, 2, 1);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		
	}
	
	*//**
	 * 
	 * @author Ganlin
	 * @date 2014年7月27日 下午7:51:14
	 * @throws ParseException 
	 * @since 0.1
	 *//*
	@Test
	public void testGetAccountInfoList() {

		AttachmentResponse<Page<UserAccount>> response;
		//无参查询
		response = manageService.getAccountInfoList(null, null, null, null, null, null, null, 5, 1);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		//有参查询
		response = manageService.getAccountInfoList("Godlin", null, null, null, null, null, null, 5, 1);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
	}

	*//**
	 * 
	 * @author Ganlin
	 * @date 2014年7月25日 下午7:10:42 
	 * @since 0.1
	 *//*
	@Test
	public void testUserAccountAdjustment() {
		
		AttachmentResponse<Long> response;
		//参数null
		response = manageService.userAccountAdjustment("8724954398",null, 1, new BigDecimal("1000.00"), "Godlin", "01");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		//账户不存在
		response = manageService.userAccountAdjustment("8724954398",99999l, 1, new BigDecimal("1000.00"), "Godlin", "01");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		//加线成功
		response = manageService.userAccountAdjustment("8724954398",56L, 1, new BigDecimal("1000"), "Godlin", "1");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
//		//企业调账
//		response = manageService.ctAccountAdjustment(81l, -1, new BigDecimal("9999999"), "Godlin", "01");
//		logger.debug("状态码：" + response.getReturnCode().toString());
//		logger.debug("状态信息：" + response.getReturnMsg());
//		assertTrue(response.isSuccess());
	}
	
	*//**
	 * 
	 * @author Ganlin
	 * @date 2014年7月26日 下午6:37:11 
	 * @since 0.1
	 *//*
	@Test
	public void testQueryCommercialTenantAccount() {
		
		AttachmentResponse<Page<CommercialTenantAccount>> response;
		//不设置参数查询
		response = manageService.queryCommercialTenantAccount(null,null, null, null, null, null, null, 10, 1);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		//设置时间区间查询
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 7, 25, 0, 0);
		Date beginDate = cal.getTime();
		cal.set(2014, 12, 27, 23, 59);
		Date endDate = cal.getTime();
		response = manageService.queryCommercialTenantAccount(null, null, "58888886", null,null, beginDate, endDate, 10, 1);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		//设置法人姓名查询
		response = manageService.queryCommercialTenantAccount("法定代表人", null, null, null, null, null, 10, 1);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		//设置法人邮箱查询
		response = manageService.queryCommercialTenantAccount(null, "nieyachun@163.com", null, null, null, null, 10, 1);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
	}
	
	*//**
	 * 
	 * @author Ganlin
	 * @date 2014年7月26日 下午7:01:50 
	 * @since 0.1
	 *//*
	@Test
	public void testQueryCTAccountDetailed() {
		
		AttachmentResponse<Page<CommercialTenantAccountDetailed>> response;
		//不设置参数查询
		response = manageService.queryCTAccountDetailed(null, null, null, null, null, null, 2, 1);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		//设置时间区间查询
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 6, 26, 18, 0);
		Date beginDate = cal.getTime();
		cal.set(2014, 8, 26, 18, 30);
		Date endDate = cal.getTime();
		response = manageService.queryCTAccountDetailed(null, null, null, null, beginDate, endDate, -1, -1);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
	}
	
	*//**
	 * 测试3.63 queryPaymentAccountDetailed
	 * @author Ganlin
	 * @date 2014年7月27日 下午3:35:06 
	 * @since 0.1
	 *//*
	@Test
	public void testQueryPaymentAccountDetailed() {
		
		AttachmentResponse<Page<PaymentAccountDetailed>> response;
		//参数null
		response = manageService.queryPaymentAccountDetailed(null, null, null, null, null, null);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		//不设置参数查询
		response = manageService.queryPaymentAccountDetailed(null, null, null, null, 10, 1);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Page size：" + response.getAttachment().getTotalCount());
		assertTrue(response.isSuccess());
		//设置时间区间查询
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 6, 27, 15, 0);
		Date beginDate = cal.getTime();
		cal.set(2014, 6, 27, 15, 10);
		Date endDate = cal.getTime();
		response = manageService.queryPaymentAccountDetailed(null, null, beginDate, endDate, 10, 1);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Page size：" + response.getAttachment().getTotalCount());
		assertTrue(response.isSuccess());
	}
	

	*//**
	 * 
	 * @author Ganlin
	 * @date 2014年7月27日 下午4:32:02 
	 * @since 0.1
	 *//*
	@Test
	public void testModefyPaymentAccount() {
		
		String bankId = "111111";
		String bankName = "神马银行";
		//增加一个平台账户，即创建一条合作银行信息及其主账户
		BankInfo bankInfo = new BankInfo();
		bankInfo.setBankAccount(bankId);
		bankInfo.setBankName(bankName);
		AttachmentResponse<String> res = manageService.modefyPaymentAccount(bankInfo);
		assertTrue(res.isSuccess());
	}
	
	*//**
	 * 3.57
	 * @author Ganlin
	 * @date 2014年7月28日 上午10:33:06 
	 * @since 0.1
	 *//*
	@Test
	public void testQueryUserAccountDetailed() {
		
		AttachmentResponse<Page<AccountDetailed>> response;
		//参数null
		response = manageService.queryUserAccountDetailed(null, null, null, null, null, null, null, null);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		//不设置参数查询
		response = manageService.queryUserAccountDetailed(null, null, null, null, null, null, 4, 1);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Page size：" + response.getAttachment().getTotalCount());
		assertTrue(response.isSuccess());
		//设置时间区间查询
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 8, 11, 0, 0);
		Date beginDate = cal.getTime();
		cal.set(2014, 9, 11, 23, 59);
		Date endDate = cal.getTime();
		response = manageService.queryUserAccountDetailed(278L, 224L, null, null, beginDate, endDate, -1, -1);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Page size：" + response.getAttachment().getTotalCount());
		assertTrue(response.isSuccess());
	}
	
	@Test
	public void testGetBusinessInfoList(){
		AttachmentResponse<GetBusinessInfoListResponse> response = manageService.getBusinessInfoList(229L);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		List<BusinessInfo> list = response.getAttachment().getBusinessInfoList();
		logger.debug("listSize = " + list.size());
		for (BusinessInfo businessInfo : list) {
			logger.debug("channelTypeName = " + businessInfo.getChannelTypeName());
		}
	}
	
	@Test
	public void testGetUserInfoByTelEmail(){
		AttachmentResponse<UserInfo> response = manageService.getUserInfoByTelEmail("flashxx@live.cn");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
	}
	
	@Test
	public void testGetUserInfobyTelOrEmail(){
		AttachmentResponse<UserInfo> r1 = manageService.getUserInfoByTelEmail("021-54666781", "2");
		logger.debug("状态码：" + r1.getReturnCode().toString());
		logger.debug("状态信息：" + r1.getReturnMsg());
		logger.debug(r1.getAttachment().getTel());
		assertTrue(r1.isSuccess());

	}
	
	@Test
	public void testAddBusinessInfo(){
		BusinessInfo businessInfo = new BusinessInfo();
		businessInfo.setUserId(236L);
		businessInfo.setMerchKey("092173");
		businessInfo.setChannelType("2");
		businessInfo.setInterEn("english");
		businessInfo.setStatus("1");
		AttachmentResponse<String> r1 = manageService.addBusinessInfo(businessInfo);
		logger.debug("状态码：" + r1.getReturnCode().toString());
		logger.debug("状态信息：" + r1.getReturnMsg());
		assertTrue(r1.isSuccess());
	}
	
	@Test
	public void testDeleteBusinessInfoById(){
		AttachmentResponse<String> r1 = manageService.deleteBusinessInfoById(137L);
		logger.debug("状态码：" + r1.getReturnCode().toString());
		logger.debug("状态信息：" + r1.getReturnMsg());
		assertTrue(r1.isSuccess());
	}
	
	@Test
	public void testGetUserInfoListByTelEmail(){
		AttachmentResponse<List<UserInfo>> response = manageService.getUserInfoListByTelEmail("12345678", "2");
		logger.debug("状态信息：" + response.getReturnMsg());
	}
	
	public static void main(String[] args) {
		Timer timer = new Timer();  
		timer.scheduleAtFixedRate(getTime(), new Date(), 1000);
	}
	
	public static TimerTask getTime(){
		return new TimerTask() {
			@Override
			public void run() {
				System.out.println("1111111111111111111111");
			}
		};
	}*/
	
}
