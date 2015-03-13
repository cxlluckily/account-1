/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.cssweb.payment.account.pojo.UserLoginInfo;
import com.cssweb.payment.account.test.TestCase;
import com.cssweb.payment.common.provider.sei.IIdGeneratorService;
import com.cssweb.payment.common.response.AttachmentResponse;

/**
 * @author Ganlin
 * @version 0.1 (2014年7月18日 下午3:20:37)
 * @since 0.1
 * @see
 */
public class UserLoginDaoTests extends TestCase {

	@Resource
	private IUserLoginDao userLoginDao;
	@Resource
	private IIdGeneratorService idGeneratorService;
	
	@Test
	public void testInsert() {
		AttachmentResponse<Long> respinse = idGeneratorService.generate(UserLoginInfo.class.getName());
		UserLoginInfo user = new UserLoginInfo();
		user.setUserId(respinse.getAttachment());
		user.setUserStatus("1");
		Date date = new Date();
		user.setCreateDatetime(date);
		user.setUpdateDatetime(date);
		userLoginDao.insert(user);
	}
	
	@Test
	public void testUpdate() {
		UserLoginInfo user = new UserLoginInfo();
		user.setUserId(1L);
		user.setUserStatus("2");
		Date date = new Date();
		user.setCreateDatetime(date);
		user.setUpdateDatetime(date);
		userLoginDao.update(user);
	}
	
	@Test
	public void testSelectOne()  {
		UserLoginInfo user = new UserLoginInfo();
		user.setUserId(1L);
		user = userLoginDao.selectOne(user);
		assertNotNull(user);
		assertNotNull(user.getUserStatus().equals("2"));
	}
	
	@Test
	public void testSelectList() {
		UserLoginInfo user = new UserLoginInfo();
		user.setUserId(1L);
		List<UserLoginInfo> userList = userLoginDao.selectList(user);
		assertTrue(!userList.isEmpty());
		assertNotNull(userList.get(0).getUserStatus().equals("2"));
	}
	
	@Test
	public void testSelectCount() {
		UserLoginInfo user = new UserLoginInfo();
		user.setUserId(1L);
		int count = userLoginDao.selectCount(user);
		assertTrue(count == 1);
	}
	
}
