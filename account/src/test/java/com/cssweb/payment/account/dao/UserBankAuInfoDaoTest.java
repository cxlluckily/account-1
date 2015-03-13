package com.cssweb.payment.account.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.cssweb.payment.account.pojo.UserBankAuInfo;
import com.cssweb.payment.account.test.TestCase;
import com.cssweb.payment.common.provider.sei.IIdGeneratorService;
import com.cssweb.payment.common.response.AttachmentResponse;

public class UserBankAuInfoDaoTest extends TestCase {
	
	@Resource 
	private IUserBankAuInfoDao nativeImpl;
	@Resource
	private IIdGeneratorService idGeneratorService;
	
	private static Long idNum = 9001L;
	private static Long userId = 10086L;
	@Test
	@Rollback(false)
	public void testInsert() {
//		AttachmentResponse<Long> response = idGeneratorService.generate(UserBankAuInfo.class.getName());
		UserBankAuInfo param = new UserBankAuInfo();
		Date date = new Date();
//		param.setUserId(response.getAttachment());
		param.setId(idNum);
		param.setUserId(userId);
		param.setBankName("中国银行");
		param.setBankNo("3456789");
		param.setUserAuDateTime(date);
		param.setUpdateDatetime(date);
		nativeImpl.insert(param);
	}
	
	@Test
	@Rollback(false)
	public void testUpdate() {
		UserBankAuInfo param = new UserBankAuInfo();
		param.setUserId(userId);
		Date date = new Date();
		param.setUpdateDatetime(date);
		nativeImpl.update(param);
	}
	
	@Test
	@Rollback(false)
	public void testSelectOne()  {
		UserBankAuInfo param = new UserBankAuInfo();
		param.setUserId(userId);
		param = nativeImpl.selectOne(param);
		assertNotNull(param);
	}
	
	@Test
	@Rollback(false)
	public void testSelectList() {
		UserBankAuInfo param = new UserBankAuInfo();
		param.setUserId(userId);
		List<UserBankAuInfo> userList = nativeImpl.selectList(param);
		assertTrue(!userList.isEmpty());
	}
	
	@Test
	@Rollback(false)
	public void testSelectCount() {
		UserBankAuInfo param = new UserBankAuInfo();
		param.setUserId(userId);
		int count = nativeImpl.selectCount(param);
		assertTrue(count == 1);
	}
}
