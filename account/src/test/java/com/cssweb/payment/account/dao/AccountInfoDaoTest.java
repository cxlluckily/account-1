package com.cssweb.payment.account.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.cssweb.payment.account.pojo.AccountInfo;
import com.cssweb.payment.account.test.TestCase;
import com.cssweb.payment.account.vo.AccountInfoQuery;
import com.cssweb.payment.common.provider.sei.IIdGeneratorService;

public class AccountInfoDaoTest extends TestCase {
	
	@Resource 
	private IAccountInfoDao nativeImpl;
	@Resource
	private IIdGeneratorService idGeneratorService;
	
	// private static Long idNum = 9001L;
	private static Long userId = 10088L;
	private static Long accountId = 100089L;
	private static String accountStatus = "01";
	private String currencyType = "01";
	private BigDecimal accountBalance = new BigDecimal("12345");
	private BigDecimal frozenAmount = new BigDecimal("32345");
	private BigDecimal nomentionAmount = new BigDecimal("42345");
	private String mac = "what's mac?";
	
	
	@Test
	@Rollback(false)
	public void testInsert() {
//		AttachmentResponse<Long> response = idGeneratorService.generate(AccountInfo.class.getName());
		AccountInfo param = new AccountInfo();
		Date date = new Date();
//		param.setUserId(response.getAttachment());
		// param.setId(idNum);
		param.setUserId(userId);
		param.setAccountId(accountId);
		param.setAccountStatus(accountStatus);
		param.setCurrencyType(currencyType);
		param.setAvailableBalance(accountBalance);
		param.setFrozenAmount(frozenAmount);
		param.setNomentionAmount(nomentionAmount);
		param.setMac(mac);
		param.setCreateDatetime(date);
		param.setUpdateDatetime(date);
		nativeImpl.insert(param);
	}
	
	@Test
	@Rollback(false)
	public void testUpdate() {
		AccountInfo param = new AccountInfo();
		param.setUserId(userId);
		param.setAccountId(accountId);
		param.setReserve1("helloworld");
		Date date = new Date();
		param.setUpdateDatetime(date);
		nativeImpl.update(param);
	}
	
	@Test
	@Rollback(false)
	public void testSelectOne()  {
		AccountInfoQuery param = new AccountInfoQuery();
		param.setAccountId(accountId);
		AccountInfo accountInfo = nativeImpl.selectOne(param);
		assertNotNull(param);
	}
	
	@Test
	@Rollback(false)
	public void testSelectList() {
		AccountInfoQuery param = new AccountInfoQuery();
		param.setUserId(32L);
		List<AccountInfo> userList = nativeImpl.selectList(param);
		assertTrue(!userList.isEmpty());
	}
	
	@Test
	@Rollback(false)
	public void testSelectCount() {
		AccountInfoQuery param = new AccountInfoQuery();
		param.setUserId(32L);
		int count = nativeImpl.selectCount(param);
		assertTrue(count == 1);
	}
	
}
