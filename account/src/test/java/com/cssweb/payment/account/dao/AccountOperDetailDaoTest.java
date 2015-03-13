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

import com.cssweb.payment.account.pojo.AccountOperDetail;
import com.cssweb.payment.account.pojo.AccountOperInfo;
import com.cssweb.payment.account.test.TestCase;
import com.cssweb.payment.common.provider.sei.IIdGeneratorService;
import com.cssweb.payment.common.response.AttachmentResponse;


/**
 * @author ZhaoXingwen
 * @version 0.1 (2014年7月22日 下午3:20:37)
 * @since 0.1
 * @see
 */
public class AccountOperDetailDaoTest extends TestCase{

	@Resource
	private IAccountOperDetailDao detailDao;
	@Resource
	private IIdGeneratorService idGeneratorService;
	
	@Test
	public void testInsert() {
		AttachmentResponse<Long> respinse = idGeneratorService.generate(AccountOperDetail.class.getName());
		AccountOperDetail userWater = new AccountOperDetail();
		userWater.setId(1L);
		userWater.setAccountSn(1L);
		userWater.setReserve1("test");
		Date date = new Date();
		userWater.setUpdateDatetime(date);
		detailDao.insert(userWater);
	}
	
	@Test
	public void testUpdate() {
		AccountOperDetail userWater = new AccountOperDetail();
		userWater.setId(1L);
		userWater.setReserve2("asdfwwxx");
		Date date = new Date();
		userWater.setUpdateDatetime(date);
		detailDao.update(userWater);
	}
	
	@Test
	public void testSelectOne()  {
		AccountOperDetail user = new AccountOperDetail();
		user.setId(1L);
		user = detailDao.selectOne(user);
		assertNotNull(user);
		assertNotNull(user.getReserve1().equals("interfaceName"));
	}
	
	@Test
	public void testSelectList() {
		AccountOperDetail user = new AccountOperDetail();
		user.setId(1L);
		List<AccountOperDetail> userList = detailDao.selectList(user);
		assertTrue(!userList.isEmpty());
		assertNotNull(userList.get(0).getReserve1().equals("test"));
	}
	
	@Test
	public void testSelectCount() {
		AccountOperDetail user = new AccountOperDetail();
		user.setId(1L);
		int count = detailDao.selectCount(user);
		assertTrue(count == 1);
	}
}
