/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.sei;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.cssweb.payment.account.constant.DictionaryKey;
import com.cssweb.payment.account.pojo.BankInfo;
import com.cssweb.payment.account.pojo.EnterpriseUser;
import com.cssweb.payment.account.pojo.PersonalUser;
import com.cssweb.payment.account.pojo.UserBandBankInfo;
import com.cssweb.payment.account.pojo.UserBankAuInfo;
import com.cssweb.payment.account.util.DictionaryUtils;
import com.cssweb.payment.account.vo.AppGetUserBindingBankListPara;
import com.cssweb.payment.account.vo.ApplyMoneyResponse;
import com.cssweb.payment.account.vo.LoginResponse;
import com.cssweb.payment.account.vo.UserInfo;
import com.cssweb.payment.common.response.AttachmentResponse;

/**
 * @author Ganlin
 * @version 0.1 (2014年7月30日 下午4:29:34)
 * @since 0.1
 * @see
 */
public class PortalServiceTestsGanlin extends TestCase {
	
	//注册信息
	String name = "吴此人";
	String email = "nobody@cssweb.com.cn";
	String enterpriseEmail = "enterprise@cssweb.com.cn";
	String cardId = "1111111111111111111111";
	String loginPwd = "loginPwd";
	String payPwd = "payPwd";
	String answer = "魏知树";
	
	//登录信息
	String ip = "192.168.5.123";
	String mac = "AB:CD:EF:GH";
	
	//打款信息
	String bankId = "111111";
	String bankName = "神马银行";
	String bankCardNo = "6222000000000111111111";
	
	String bankId2 = "222222";
	String bankCardNo2 = "6222000000000222222222";


/*	@Test
	public void testRegistration() {
		String nationality = DictionaryUtils.getStringValue(DictionaryKey.COUNTRY_CODE_CN);
		String regType = DictionaryUtils.getStringValue(DictionaryKey.REG_TYPE_EMAIL);
		String source = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);
		String cardType = DictionaryUtils.getStringValue(DictionaryKey.ID_TYPE_IDCARD);
		int questionId = DictionaryUtils.getIntegerValue(DictionaryKey.SECURITY_ISSUES_ONE);
		
		AttachmentResponse<String> res = portalService.personalRegistration(nationality, null, name, email, regType, source, cardType, cardId, loginPwd, payPwd, questionId, answer);
		assertTrue(res.isSuccess());
	}
		
	@Test 
	public void testModefyPaymentAccount() {
		String bankId = "222222";
		String bankName = "神牛银行";
		//增加一个平台账户，即创建一条合作银行信息及其主账户
		BankInfo bankInfo = new BankInfo();
		bankInfo.setBankId(bankId);
		bankInfo.setBankName(bankName);
		AttachmentResponse<String> res = manageService.modefyPaymentAccount(bankInfo);
		assertTrue(res.isSuccess());
	}
	*/
	
//	@Test	//企业用户注册
//	public void testEnterpriseRegistration() {
//		String nationality = DictionaryUtils.getStringValue(DictionaryKey.COUNTRY_CODE_CN);
//		String regType = DictionaryUtils.getStringValue(DictionaryKey.REG_TYPE_EMAIL);
//		String source = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);
//		String cardType = DictionaryUtils.getStringValue(DictionaryKey.ID_TYPE_IDCARD);
//		int questionId = DictionaryUtils.getIntegerValue(DictionaryKey.SECURITY_ISSUES_ONE);
//		
//		AttachmentResponse<String> res = portalService.enterpriseRegistration(enterpriseEmail, loginPwd, payPwd, questionId, answer, source);
//		assertTrue(res.isSuccess());
//	}
	
	@Test	//测试用户打款认证流程
	public void testUserAuth() {
		String source = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);

		//用户登录
		logger.debug("用户登录");
		AttachmentResponse<LoginResponse> loginRes = portalService.login(enterpriseEmail, loginPwd, ip, mac, source);
		logger.debug("状态码：" + loginRes.getReturnCode());
		logger.debug("状态信息：" + loginRes.getReturnMsg());
		assertTrue(loginRes.isSuccess());
		Long userId;
		if (loginRes.getAttachment().getUserType().equals(DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL))) {
			userId = loginRes.getAttachment().getPersonalUser().getUserId();
		} else {
			assertTrue(loginRes.getAttachment().getUserType().equals(DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_ENTERPRISE)));
			userId = loginRes.getAttachment().getEnterpriseUser().getUserId();
			EnterpriseUser enterpriseUser = loginRes.getAttachment().getEnterpriseUser();
			//实名认证申请，现在只有企业用户有这个接口
			logger.debug("企业用户实名认证申请");
			AttachmentResponse<String> response = portalService.enterpriselAu(enterpriseUser,null);
			logger.debug("状态码：" + response.getReturnCode());
			logger.debug("状态信息：" + response.getReturnMsg());
			assertTrue(response.isSuccess());
			//人工审核
			logger.debug("人工审核");
			response = portalService.enterpriselAuApply(userId, source);
			logger.debug("状态码：" + response.getReturnCode());
			logger.debug("状态信息：" + response.getReturnMsg());
			assertTrue(response.isSuccess());
			//打款申请
			logger.debug("打款申请");
			AttachmentResponse<ApplyMoneyResponse> res2 = tradeService.applyMoney(userId, new BigDecimal("0.02"),"50", bankName, bankCardNo, bankId,"123", "952779733212","我在测试...");
			logger.debug("状态码：" + res2.getReturnCode());
			logger.debug("状态信息：" + res2.getReturnMsg());
			assertTrue(res2.isSuccess());
			//用户撤消认证
			logger.debug("用户撤消认证");
			response = portalService.enterpriselAuCancel(userId);
			logger.debug("状态码：" + response.getReturnCode());
			logger.debug("状态信息：" + response.getReturnMsg());
			assertFalse(response.isSuccess());
			//确认打款金额
			logger.debug("确认打款金额");
			response = portalService.bankAu(userId, bankId, bankCardNo, new BigDecimal("0.02"), source);
			logger.debug("状态码：" + response.getReturnCode());
			logger.debug("状态信息：" + response.getReturnMsg());
			assertTrue(response.isSuccess());
		}

	}
	
	@Test
	public void testAddBankCard() {
		String nationality = DictionaryUtils.getStringValue(DictionaryKey.COUNTRY_CODE_CN);
		String regType = DictionaryUtils.getStringValue(DictionaryKey.REG_TYPE_EMAIL);
		String source = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);
		String cardType = DictionaryUtils.getStringValue(DictionaryKey.ID_TYPE_IDCARD);
		int questionId = DictionaryUtils.getIntegerValue(DictionaryKey.SECURITY_ISSUES_ONE);

		
		
		//用户登录
		logger.debug("用户登录");
		AttachmentResponse<LoginResponse> loginRes = portalService.login(email, loginPwd, "192.168.5.123", "AB:CD:EF:GH", source);
		logger.debug("状态码：" + loginRes.getReturnCode());
		logger.debug("状态信息：" + loginRes.getReturnMsg());
		assertTrue(loginRes.isSuccess());
		Long userId;
		if (loginRes.getAttachment().getUserType().equals(DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL))) {
			userId = loginRes.getAttachment().getPersonalUser().getUserId();
		} else {
			assertTrue(loginRes.getAttachment().getUserType().equals(DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_ENTERPRISE)));
			userId = loginRes.getAttachment().getEnterpriseUser().getUserId();
		}
		AttachmentResponse<List<AppGetUserBindingBankListPara>> listRes;
		AttachmentResponse<String> response;
		
		//获取绑定银行卡列表
		logger.debug("获取绑定银行卡列表");
		listRes = mobileService.getUserBindingBankList(userId);
		logger.debug("状态码：" + listRes.getReturnCode());
		logger.debug("状态信息：" + listRes.getReturnMsg());
		assertTrue(listRes.isSuccess());
		assertTrue(listRes.getAttachment().isEmpty());
		//添加银行卡
		logger.debug("添加银行卡");
		UserBandBankInfo bankCard = new UserBandBankInfo();
		bankCard.setUserId(userId);
		bankCard.setBankId(bankId);
		bankCard.setBankAccountNo("6222000000000111111111");
		response = portalService.addBankCard(bankCard);
		logger.debug("状态码：" + response.getReturnCode());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		//添加银行卡
		logger.debug("添加银行卡");
		bankCard.setUserId(userId);
		bankCard.setBankId(bankId2);
		bankCard.setBankAccountNo("6222000000000222222222");
		response = portalService.addBankCard(bankCard);
		logger.debug("状态码：" + response.getReturnCode());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		//获取绑定银行卡列表
		logger.debug("获取绑定银行卡列表");
		listRes = mobileService.getUserBindingBankList(userId);
		logger.debug("状态码：" + listRes.getReturnCode());
		logger.debug("状态信息：" + listRes.getReturnMsg());
		assertTrue(listRes.isSuccess());
		assertTrue(listRes.getAttachment().size() == 2);
		//修改银行卡
		logger.debug("修改银行卡");
		UserBandBankInfo update = new UserBandBankInfo();
		AppGetUserBindingBankListPara appBankCard = listRes.getAttachment().get(0);
		update.setId(appBankCard.getId());
		update.setReserve1("远程修改银行卡成功");
		response = portalService.updateBankCard(update);
		logger.debug("状态码：" + response.getReturnCode());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		//删除银行卡
		logger.debug("修改银行卡");
		appBankCard = listRes.getAttachment().get(1);
		response = portalService.deleteBankCard(userId, appBankCard.getId(), source);
		logger.debug("状态码：" + response.getReturnCode());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(listRes.isSuccess());
		//获取绑定银行卡列表
		logger.debug("获取绑定银行卡列表");
		listRes = mobileService.getUserBindingBankList(userId);
		logger.debug("状态码：" + listRes.getReturnCode());
		logger.debug("状态信息：" + listRes.getReturnMsg());
		assertTrue(listRes.isSuccess());
		assertTrue(listRes.getAttachment().size() == 1);
	}
	
	@Test
	public void personalAuTest(){
		PersonalUser user = new PersonalUser();
		user.setUserId(48L);
		user.setUserName("test");
		user.setAuthCertId("130192181740928171");
		user.setAuthCertType("1");
		user.setPictureFront("/a/dadaf/a/a");
		user.setPictureBack("/b/dadaf/b/b");
		user.setCertExpireDate(new Date());
		user.setPersonalAddress("北京市昌平");
		UserBankAuInfo bankAuInfo =  new UserBankAuInfo();
		String bankNo = "1212";
		String bankProvince = "1";
		String bankCity = "01";
		String bankCnaps = "31231";
		String bankInternal = "12312"; 
		String branchId = "33";
		String branchName = "33";
		
		bankAuInfo.setBankNo(bankNo);
		bankAuInfo.setBankProvince(bankProvince);
		bankAuInfo.setBankCity(bankCity);
		bankAuInfo.setBankCnaps(bankCnaps);
		bankAuInfo.setBankInternal(bankInternal);
		bankAuInfo.setBranchId(branchId);
		bankAuInfo.setBranchName(branchName);
		bankAuInfo.setBankId("121");
		
		AttachmentResponse  response = portalService.personalAu(user, bankAuInfo);
		logger.debug("状态值 ："+response.getReturnCode());
		logger.debug("状态信息 ："+response.getReturnMsg());
		assertTrue(response.isSuccess());
	}
	
	@Test
	public void checkCardIdIsValidTest(){
		Long userId = 48L;
		String cardId = "130192181740928171";
		AttachmentResponse<Boolean> response =  portalService.checkCardIdIsValid(userId, cardId);
		logger.debug("状态值 ："+response.getReturnCode());
		logger.debug("状态信息 ："+response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment()+"");
		cardId ="123131";
		response =  portalService.checkCardIdIsValid(userId, cardId);
		logger.debug("状态值 ："+response.getReturnCode());
		logger.debug("状态信息 ："+response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
		cardId =null;
		response =  portalService.checkCardIdIsValid(userId, cardId);
		logger.debug("状态值 ："+response.getReturnCode());
		logger.debug("状态信息 ："+response.getReturnMsg());
		assertFalse(response.isSuccess());
		 
	}
	
	@Test
	public void getUserInfoByUserIDTest(){
		Long userId = 48L;
		
		AttachmentResponse<UserInfo> response = manageService.getUserInfoByUserID(userId);
		logger.debug("状态值 ："+response.getReturnCode());
		logger.debug("状态信息 ："+response.getReturnMsg());
		assertTrue(response.isSuccess());
		UserInfo userInfo = response.getAttachment();
		logger.debug("EMail :"+userInfo.getEmail());
		logger.debug("name :"+userInfo.getName());
		logger.debug("Tel :"+userInfo.getTel());
		logger.debug("userTYpe :"+userInfo.getUsertype());
		logger.debug("-----------------------------------------------" );
		userId = null;
		response = manageService.getUserInfoByUserID(userId);
		logger.debug("状态值 ："+response.getReturnCode());
		logger.debug("状态信息 ："+response.getReturnMsg());
		assertFalse(response.isSuccess());
		logger.debug("-----------------------------------------------" );
		userId = 566l;
		response = manageService.getUserInfoByUserID(userId);
		logger.debug("状态值 ："+response.getReturnCode());
		logger.debug("状态信息 ："+response.getReturnMsg());
		assertFalse(response.isSuccess());
		
		
	}
	
	@Test
	public void getUserInfoByTelEmailTest(){
		String userName = "189031234";
		AttachmentResponse<UserInfo> response = manageService.getUserInfoByTelEmail(userName);
		logger.debug("状态值 ："+response.getReturnCode());
		logger.debug("状态信息 ："+response.getReturnMsg());
		assertTrue(response.isSuccess());
		UserInfo userInfo = response.getAttachment();
		logger.debug("EMail :"+userInfo.getEmail());
		logger.debug("name :"+userInfo.getName());
		logger.debug("Tel :"+userInfo.getTel());
		logger.debug("userTYpe :"+userInfo.getUsertype());
		logger.debug("-----------------------------------------------" );
		userName = null;
		response = manageService.getUserInfoByTelEmail(userName);
		logger.debug("状态值 ："+response.getReturnCode());
		logger.debug("状态信息 ："+response.getReturnMsg());
		assertFalse(response.isSuccess());
		logger.debug("-----------------------------------------------" );
		
		userName = "123131kl;3k1;l2k3;1l";
		response = manageService.getUserInfoByTelEmail(userName);
		logger.debug("状态值 ："+response.getReturnCode());
		logger.debug("状态信息 ："+response.getReturnMsg());
		assertFalse(response.isSuccess());
		 
	}
	 
}
