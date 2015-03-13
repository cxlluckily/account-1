package com.cssweb.payment.account.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.cssweb.payment.account.pojo.UserMobileAccountInfo;
import com.cssweb.payment.account.test.TestCase;
import com.cssweb.payment.common.provider.sei.IIdGeneratorService;

public class UserMobileAccountInfoDaoTest extends TestCase {
	
	@Resource 
	private IUserMobileAccountInfoDao nativeImpl;
	@Resource
	private IIdGeneratorService idGeneratorService;
	
	private static Long idNum = 9001L;
	private static Long userId = 10087L;
	private static Long accountId = 98765432L;
	
	@Test
	@Rollback(false)
	public void testInsert() {
//		AttachmentResponse<Long> response = idGeneratorService.generate(UserMobileAccountInfo.class.getName());
		UserMobileAccountInfo param = new UserMobileAccountInfo();
		Date date = new Date();
//		param.setUserId(response.getAttachment());
		param.setId(12L);
		param.setUserId(userId);
		param.setAccountId(accountId);
		param.setCreateDatetime(date);
		param.setUpdateDatetime(date);
		nativeImpl.insert(param);
	}
	
	@Test
	@Rollback(false)
	public void testUpdate() {
		UserMobileAccountInfo param = new UserMobileAccountInfo();
		param.setUserId(4L);
		Date date = new Date();
		param.setUpdateDatetime(date);
		nativeImpl.update(param);
	}
	
	@Test
	@Rollback(false)
	public void testSelectOne()  {
		UserMobileAccountInfo param = new UserMobileAccountInfo();
		param.setUserId(4L);
		param = nativeImpl.selectOne(param);
		assertNotNull(param);
	}
	
	@Test
	@Rollback(false)
	public void testSelectList() {
		UserMobileAccountInfo param = new UserMobileAccountInfo();
		param.setUserId(4L);
		List<UserMobileAccountInfo> userList = nativeImpl.selectList(param);
		assertTrue(!userList.isEmpty());
	}
	
	@Test
	@Rollback(false)
	public void testSelectCount() {
		UserMobileAccountInfo param = new UserMobileAccountInfo();
		param.setUserId(4L);
		int count = nativeImpl.selectCount(param);
		assertTrue(count == 0);
	}
}
