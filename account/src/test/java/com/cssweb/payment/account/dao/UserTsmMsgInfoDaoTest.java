package com.cssweb.payment.account.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.cssweb.payment.account.pojo.UserTsmMsgInfo;
import com.cssweb.payment.account.test.TestCase;
import com.cssweb.payment.common.provider.sei.IIdGeneratorService;

public class UserTsmMsgInfoDaoTest extends TestCase {
	
	@Resource 
	private IUserTsmMsgInfoDao nativeImpl;
	@Resource
	private IIdGeneratorService idGeneratorService;
	
	private static Long idNum = 3L;	
	
	@Test
	@Rollback(false)
	public void testInsert() {
//		AttachmentResponse<Long> response = idGeneratorService.generate(UserTsmMsgInfo.class.getName());
		UserTsmMsgInfo param = new UserTsmMsgInfo();
		Date date = new Date();
//		param.setUserId(response.getAttachment());
		param.setId(38L);
		param.setServiceId("123");
		param.setIccid("ad");
		param.setCreateDatetime(date);
		param.setUpdateDatetime(date);
		nativeImpl.insert(param);
	}
	
	@Test
	@Rollback(false)
	public void testUpdate() {
		UserTsmMsgInfo param = new UserTsmMsgInfo();
		//param.setId(idNum);
		param.setMessageId("new");
		param.setFunctionExecutionStatus("555");
//		Date date = new Date();
//		param.setUpdateDatetime(date);
		nativeImpl.update(param);
	}
	
	@Test
	@Rollback(false)
	public void testSelectOne()  {
		UserTsmMsgInfo param = new UserTsmMsgInfo();
		param.setId(idNum);
		param = nativeImpl.selectOne(param);
		assertNotNull(param);
	}
	
	@Test
	@Rollback(false)
	public void testSelectList() {
		UserTsmMsgInfo param = new UserTsmMsgInfo();
		param.setId(idNum);
		List<UserTsmMsgInfo> userList = nativeImpl.selectList(param);
		assertTrue(!userList.isEmpty());
	}
	
	@Test
	@Rollback(false)
	public void testSelectCount() {
		UserTsmMsgInfo param = new UserTsmMsgInfo();
		param.setId(idNum);
		int count = nativeImpl.selectCount(param);
		assertTrue(count == 1);
	}
}
