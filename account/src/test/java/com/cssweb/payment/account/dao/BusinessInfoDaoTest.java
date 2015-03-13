package com.cssweb.payment.account.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.cssweb.payment.account.pojo.BusinessInfo;
import com.cssweb.payment.account.test.TestCase;
import com.cssweb.payment.common.provider.sei.IIdGeneratorService;

public class BusinessInfoDaoTest extends TestCase {
	
	@Resource 
	private IBusinessInfoDao nativeImpl;
	@Resource
	private IIdGeneratorService idGeneratorService;
	
	private static Long idNum = 9001L;
	private static Long userId = 10086L;
	
	@Test
	@Rollback(false)
	public void testInsert() {
//		AttachmentResponse<Long> response = idGeneratorService.generate(BusinessInfo.class.getName());
		BusinessInfo param = new BusinessInfo();
		Date date = new Date();
//		param.setUserId(response.getAttachment());
		param.setId(idNum);
		param.setUserId(userId);
		param.setUpdateDatetime(date);
		nativeImpl.insert(param);
	}
	
	@Test
	@Rollback(false)
	public void testUpdate() {
		BusinessInfo param = new BusinessInfo();
		param.setUserId(userId);
		Date date = new Date();
		param.setUpdateDatetime(date);
		nativeImpl.update(param);
	}
	
	@Test
	@Rollback(false)
	public void testSelectOne()  {
		BusinessInfo param = new BusinessInfo();
		param.setUserId(userId);
		param = nativeImpl.selectOne(param);
		assertNotNull(param);
	}
	
	@Test
	@Rollback(false)
	public void testSelectList() {
		BusinessInfo param = new BusinessInfo();
		param.setUserId(userId);
		List<BusinessInfo> userList = nativeImpl.selectList(param);
		assertTrue(!userList.isEmpty());
	}
	
	@Test
	@Rollback(false)
	public void testSelectCount() {
		BusinessInfo param = new BusinessInfo();
		param.setUserId(userId);
		int count = nativeImpl.selectCount(param);
		assertTrue(count == 1);
	}
}
