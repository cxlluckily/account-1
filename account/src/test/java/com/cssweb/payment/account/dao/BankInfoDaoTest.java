package com.cssweb.payment.account.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.cssweb.payment.account.pojo.BankInfo;
import com.cssweb.payment.account.test.TestCase;
import com.cssweb.payment.common.provider.sei.IIdGeneratorService;

public class BankInfoDaoTest extends TestCase {
	
	@Resource 
	private IBankInfoDao nativeImpl;
	@Resource
	private IIdGeneratorService idGeneratorService;
	
	private static Long idNum = 9001L;
	private static Long paymentId = 100087L;
	
	
	@Test
	@Rollback(false)
	public void testInsert() {
//		AttachmentResponse<Long> response = idGeneratorService.generate(BankInfo.class.getName());
		BankInfo param = new BankInfo();
		Date date = new Date();
//		param.setUserId(response.getAttachment());
		param.setId(idNum);
		param.setPaymentId(paymentId);
		param.setUpdateDatetime(date);
		nativeImpl.insert(param);
	}
	
	@Test
	@Rollback(false)
	public void testUpdate() {
		BankInfo param = new BankInfo();
		param.setPaymentId(paymentId);
		Date date = new Date();
		param.setUpdateDatetime(date);
		nativeImpl.update(param);
	}
	
	@Test
	@Rollback(false)
	public void testSelectOne()  {
		BankInfo param = new BankInfo();
		param.setPaymentId(paymentId);
		param = nativeImpl.selectOne(param);
		assertNotNull(param);
	}
	
	@Test
	@Rollback(false)
	public void testSelectList() {
		BankInfo param = new BankInfo();
		param.setPaymentId(paymentId);
		List<BankInfo> userList = nativeImpl.selectList(param);
		assertTrue(!userList.isEmpty());
	}
	
	@Test
	@Rollback(false)
	public void testSelectCount() {
		BankInfo param = new BankInfo();
		param.setPaymentId(paymentId);
		int count = nativeImpl.selectCount(param);
		assertTrue(count == 1);
	}
	
	@Test
	@Rollback(false)
	public void TestselectByPaymentIdList() {
		List<Long> paymentIdList = new ArrayList<Long>();
		paymentIdList.add(5L);
		paymentIdList.add(6L);
		paymentIdList.add(7L);
		paymentIdList.add(8L);
		nativeImpl.selectByPaymentIdList(paymentIdList);
//		assertTrue(count == 1);
	}
	
}
