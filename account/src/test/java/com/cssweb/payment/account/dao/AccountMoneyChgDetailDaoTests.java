package com.cssweb.payment.account.dao;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.cssweb.payment.account.pojo.AccountOperInfo;
import com.cssweb.payment.account.pojo.AccountMoneyChgDetail;
import com.cssweb.payment.account.test.TestCase;
import com.cssweb.payment.account.vo.AccountMoneyChgDetailQuery;
import com.cssweb.payment.common.provider.sei.IIdGeneratorService;
import com.cssweb.payment.common.response.AttachmentResponse;

/**
 * @author ZhaoXingwen
 * @version 0.1 (2014年7月18日 下午3:20:37)
 * @since 0.1
 * @see
 */
public class AccountMoneyChgDetailDaoTests extends TestCase {

	@Resource
	private IAccountOperInfoDao accountUserWaterDao;
	
	@Resource
	private IAccountMoneyChgDetailDao moneyLog;
	
	@Resource
	private IIdGeneratorService idGeneratorService;
	
	@Test
	public void testInsert() {
		AttachmentResponse<Long> respinse = idGeneratorService.generate(AccountMoneyChgDetail.class.getName());
		AccountMoneyChgDetail userWater = new AccountMoneyChgDetail();
		userWater.setId(511L);
		userWater.setAccountSn(1L);
		userWater.setUserId(2L);
		userWater.setAccountId(4L);
		userWater.setReserve1("Hello");
		
		Date date = new Date();
		userWater.setUpdateDateTime(date);
		moneyLog.insert(userWater);
	}
	
	@Test
	public void testUpdate() {
		AccountMoneyChgDetail userWater = new AccountMoneyChgDetail();
		userWater.setAccountSn(9L);
		userWater.setId(511L);
		Date date = new Date();
		userWater.setUpdateDateTime(date);
		moneyLog.update(userWater);
	}
	
	@Test
	public void testSelectOne()  {
		AccountMoneyChgDetailQuery DetailQuery = new AccountMoneyChgDetailQuery();
		DetailQuery.setId(88L);
		AccountMoneyChgDetail detail = moneyLog.selectOne(DetailQuery);
		assertNotNull(detail);
		assertTrue(detail.getReserve1().equals("Hello2"));
	}
	
	@Test
	public void testSelectList() {
		AccountMoneyChgDetailQuery user = new AccountMoneyChgDetailQuery();
		user.setId(88L);
		List<AccountMoneyChgDetail> userList = moneyLog.selectList(user);
		assertTrue(!userList.isEmpty());
		assertTrue(userList.get(0).getReserve1().equals("Hello2"));
	}

	@Test
	public void testSelectCount() {
		AccountMoneyChgDetailQuery user = new AccountMoneyChgDetailQuery();
		user.setId(1L);
		int count = moneyLog.selectCount(user);
		assertTrue(count == 1);
	}

}
