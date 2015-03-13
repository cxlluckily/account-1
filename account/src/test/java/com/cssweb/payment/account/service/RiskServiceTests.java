/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cssweb.payment.account.sei.IRiskService;
import com.cssweb.payment.account.test.TestCase;
import com.cssweb.payment.account.vo.GetFunctionStatusByAccountId;
import com.cssweb.payment.account.vo.RiskAccountInfo;
import com.cssweb.payment.account.vo.RiskAuthStatus;
import com.cssweb.payment.common.response.AttachmentResponse;
import com.cssweb.payment.omp.pojo.Dictionary;
import com.cssweb.payment.omp.sei.IDictionaryService;

/**
 * 风控服务接口测试类
 * 
 * @author DuXiaohua
 * @version 1.0 (2014年7月25日 上午12:16:37)
 * @since 1.0
 */
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class RiskServiceTests extends TestCase{
	
	@Resource
	private IRiskService riskService;
	@Resource
	private IDictionaryService dictionaryService;
/*	@Test
	public void testGetLoginPwdWrong() {
		String userName = "189031234";
		AttachmentResponse<Integer> response = riskService.getLoginPwdWrong(69L);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
		AttachmentResponse<Integer> response1 = riskService.getLoginPwdWrong(1L);
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		assertFalse(response1.isSuccess());
	}

	@Test
	public void testGetPayPwdWrong() {
		AttachmentResponse<Integer> response = riskService.getPayPwdWrong(1L);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
		AttachmentResponse<Integer> response1 = riskService.getPayPwdWrong(70L);
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		assertFalse(response1.isSuccess());
	}

	@Test
	public void testSetAccountStatus() {
		AttachmentResponse response = riskService.setAccountStatus(1L, "03");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
	}

	@Test
	public void testGetAccountInfo() {
		AttachmentResponse<RiskAccountInfo> response = riskService.getAccountInfo(278L, "11",null);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("返回值：" + response.getAttachment());
		assertTrue(response.isSuccess());
	}

	@Test
	public void testGetAuthStatus() {
		AttachmentResponse<RiskAuthStatus> response = riskService.getAuthStatus(1L);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().getStatus());
		logger.debug(response.getAttachment().getUserType());
	}

	@Test
	public void testFreezeUser() {
		AttachmentResponse response = riskService.freezeUser(212L,"违规");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
	}*/

	
	@Test
	public void testUnfreezeUser() {
		AttachmentResponse response = riskService.unfreezeUser(212L,"11违规");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
	}

	/*
	@Test
	public void testGetBankTimes() {
		AttachmentResponse<Integer> response = riskService.getBankTimes(84L);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}

	@Test
	public void testGetBankDays() {//102
		AttachmentResponse<Integer> response = riskService.getBankDays(1128302L);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}

	
	@Test
	public void testGetFunction(){
		AttachmentResponse<GetFunctionStatusByAccountId> r = riskService.getFunctionByAccountId(266L);
		logger.debug("状态码：" + r.getReturnCode().toString());
		logger.debug("状态信息：" + r.getReturnMsg());
		logger.debug(r.getAttachment().getPayStatus());
		logger.debug(r.getAttachment().getSmsStatus());
		logger.debug(r.getAttachment().getPhoneNum());
		assertTrue(r.isSuccess());
	}*/
}
