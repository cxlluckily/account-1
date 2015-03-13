/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.cssweb.payment.account.pojo.AccountOperInfo;
import com.cssweb.payment.account.test.TestCase;
import com.cssweb.payment.common.provider.sei.IIdGeneratorService;
import com.cssweb.payment.common.response.AttachmentResponse;

/**
 * @author ZhaoXingwen
 * @version 0.1 (2014年7月18日 下午3:20:37)
 * @since 0.1
 * @see
 */
public class AccountOperInfoDaoTests extends TestCase {

	@Resource
	private IAccountOperInfoDao accountUserWaterDao;
	@Resource
	private IIdGeneratorService idGeneratorService;
	
	@Test
	public void testInsert() {
		AttachmentResponse<Long> respinse = idGeneratorService.generate(AccountOperInfo.class.getName());
		AccountOperInfo userWater = new AccountOperInfo();
		//userWater.setAccountSn(respinse.getAttachment());
		userWater.setAccountSn(3L);
		userWater.setAmount(BigDecimal.valueOf(13L));
		userWater.setMemo("test");
		userWater.setReserver1("Hello");
		Date date = new Date();
		userWater.setUpdateDateTime(date);
		accountUserWaterDao.insert(userWater);
	}
	
	@Test
	public void testUpdate() {
		AccountOperInfo userWater = new AccountOperInfo();
		userWater.setAccountSn(1L);
		userWater.setInterfaceName("interfaceName");
		userWater.setMemo("my upd test2");
		Date date = new Date();
		userWater.setUpdateDateTime(date);
		accountUserWaterDao.update(userWater);
	}
	
	@Test
	public void testSelectOne()  {
		AccountOperInfo user = new AccountOperInfo();
		user.setAccountSn(9L);
		user = accountUserWaterDao.selectOne(user);
		assertNotNull(user);
		assertNotNull(user.getInterfaceName().equals("interfaceName"));
	}
	
	@Test
	public void testSelectList() {
		AccountOperInfo user = new AccountOperInfo();
		user.setAccountSn(9L);
		List<AccountOperInfo> userList = accountUserWaterDao.selectList(user);
		assertTrue(!userList.isEmpty());
		assertNotNull(userList.get(0).getInterfaceName().equals("interfaceName"));
	}
	
	@Test
	public void testSelectCount() {
		AccountOperInfo user = new AccountOperInfo();
		user.setAccountSn(9L);
		int count = accountUserWaterDao.selectCount(user);
		assertTrue(count == 1);
	}

}
