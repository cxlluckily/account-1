package com.cssweb.payment.account.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.cssweb.payment.account.pojo.EnterpriseUser;
import com.cssweb.payment.account.test.TestCase;
import com.cssweb.payment.account.vo.EnterpriseUserQuery;
import com.cssweb.payment.common.provider.sei.IIdGeneratorService;
import com.cssweb.payment.common.util.Page;

public class EnterpriseUserDaoTest extends TestCase {
	
	@Resource 
	private IEnterpriseUserDao nativeImpl;
	@Resource
	private IIdGeneratorService idGeneratorService;
	
	private static Long userId = 10086L;
	private static String authStatus = "01";
	
	@Test
	@Rollback(false)
	public void testInsert() {
//		AttachmentResponse<Long> response = idGeneratorService.generate(EnterpriseUser.class.getName());
		EnterpriseUser param = new EnterpriseUser();
		Date date = new Date();
//		param.setUserId(response.getAttachment());
		// param.setId(idNum);
		param.setUserId(userId);
		param.setAuthStatus(authStatus);
		param.setUpdateDatetime(date);
		nativeImpl.insert(param);
	}
	
	@Test
	@Rollback(false)
	public void testUpdate() {
		EnterpriseUser param = new EnterpriseUser();
		param.setUserId(userId);
		Date date = new Date();
		param.setUpdateDatetime(date);
		nativeImpl.update(param);
	}
	
	@Test
	@Rollback(false)
	public void testSelectOne()  {
		EnterpriseUserQuery param = new EnterpriseUserQuery();
		param.setUserId(userId);
		EnterpriseUser user = nativeImpl.selectOne(param);
		assertNotNull(user);
	}
	
	@Test
	@Rollback(false)
	public void testSelectList() {
		EnterpriseUserQuery param = new EnterpriseUserQuery();
		param.setUserId(userId);
		List<EnterpriseUser> userList = nativeImpl.selectList(param);
		assertTrue(!userList.isEmpty());
	}
	
	@Test
	@Rollback(false)
	public void testSelectCount() {
		EnterpriseUser param = new EnterpriseUser();
		param.setUserId(userId);
		int count = nativeImpl.selectCount(param);
		assertTrue(count == 1);
	}
	
	@Test
	public void testSelectByPage() {
		EnterpriseUserQuery query = new EnterpriseUserQuery();
		query.setPageNo(1);
		query.setPageSize(2);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, 6);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		query.setCreateDatetimeStart(cal.getTime());
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, 6);
		cal.set(Calendar.DAY_OF_MONTH, 20);
		query.setCreateDatetimeEnd(cal.getTime());
		Page<EnterpriseUser> userPage = nativeImpl.selectByPage(query);
	}
}
