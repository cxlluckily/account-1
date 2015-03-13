package com.cssweb.payment.account.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.cssweb.payment.account.pojo.PersonalUser;
import com.cssweb.payment.account.test.TestCase;
import com.cssweb.payment.account.vo.PersonalUserQuery;
import com.cssweb.payment.common.provider.sei.IIdGeneratorService;
import com.cssweb.payment.common.util.Page;

public class PersonalUserDaoTest extends TestCase {
	
	@Resource 
	private IPersonalUserDao nativeImpl;
	@Resource
	private IIdGeneratorService idGeneratorService;
	
	// private static Long idNum = 9001L;
	private static Long userId = 10086L;
	
	@Test
	@Rollback(false)
	public void testInsert() {
//		AttachmentResponse<Long> response = idGeneratorService.generate(PersonalUser.class.getName());
		PersonalUser param = new PersonalUser();
		Date date = new Date();
//		param.setUserId(response.getAttachment());
		// param.setId(idNum);
		param.setUserId(userId);
		param.setCreateDatetime(date);
		param.setUpdateDatetime(date);
		nativeImpl.insert(param);
	}
	
	@Test
	@Rollback(false)
	public void testUpdate() {
		PersonalUser param = new PersonalUser();
		param.setUserId(userId);
		Date date = new Date();
		param.setUpdateDatetime(date);
		nativeImpl.update(param);
	}
	
	@Test
	@Rollback(false)
	public void testSelectOne()  {
		PersonalUserQuery param = new PersonalUserQuery();
		param.setUserId(userId);
		PersonalUser user = nativeImpl.selectOne(param);
		assertNotNull(user);
	}
	
	@Test
	@Rollback(false)
	public void testSelectList() {
		PersonalUserQuery param = new PersonalUserQuery();
		param.setUserId(userId);
		List<PersonalUser> userList = nativeImpl.selectList(param);
		assertTrue(!userList.isEmpty());
		
		//按注册时间区间查询用户列表测试
		Calendar cal = Calendar.getInstance();
		PersonalUserQuery query = new PersonalUserQuery();
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, 6);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		query.setCreateDatetimeStart(cal.getTime());
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, 6);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		query.setCreateDatetimeEnd(cal.getTime());
		List<PersonalUser> list = nativeImpl.selectList(query);

	}
	
	@Test
	@Rollback(false)
	public void testSelectCount() {
		PersonalUserQuery param = new PersonalUserQuery();
		param.setUserId(userId);
		int count = nativeImpl.selectCount(param);
		assertTrue(count == 1);
	}
	
	@Test
	public void testSelectByPage() {
		PersonalUserQuery query = new PersonalUserQuery();
		query.setPageNo(2);
		query.setPageSize(2);
		Page<PersonalUser> userPage= nativeImpl.selectByPage(query);
	}
}
