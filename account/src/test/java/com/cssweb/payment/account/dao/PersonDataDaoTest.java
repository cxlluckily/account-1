package com.cssweb.payment.account.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.cssweb.payment.account.pojo.PersonData;
import com.cssweb.payment.account.test.TestCase;
import com.cssweb.payment.common.provider.sei.IIdGeneratorService;

public class PersonDataDaoTest extends TestCase {
	
	@Resource 
	private IPersonDataDao nativeImpl;
	@Resource
	private IIdGeneratorService idGeneratorService;
		
	@Test
	@Rollback(false)
	public void testInsert() {
		PersonData param = new PersonData();
		Date date = new Date();
		param.setTag("TEST01");
		param.setGroupId("1");
		param.setSourceId("测试用例");
		param.setUpdateDatetime(date);
		nativeImpl.insert(param);
	}
	
	@Test
	@Rollback(false)
	public void testUpdate() {
		PersonData param = new PersonData();
		Date date = new Date();
		param.setUpdateDatetime(date);
		param.setTag("5A");
		nativeImpl.update(param);
	}
	
	@Test
	@Rollback(false)
	public void testSelectOne() {
		PersonData param = new PersonData();
		param.setTag("5A");
		param = nativeImpl.selectOne(param);
		assertNotNull(param);
	}
	
	@Test
	@Rollback(false)
	public void testSelectList() {
		PersonData param = new PersonData();
		param.setFlag(1);
		List<PersonData> userList = nativeImpl.selectList(param);
		assertTrue(!userList.isEmpty());
	}
	
	@Test
	@Rollback(false)
	public void testSelectCount() {
		PersonData param = new PersonData();
		param.setFlag(1);
		int count = nativeImpl.selectCount(param);
	}
}
