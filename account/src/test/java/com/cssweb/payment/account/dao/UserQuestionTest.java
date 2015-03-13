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

import com.cssweb.payment.account.pojo.RateInfo;
import com.cssweb.payment.account.pojo.UserQuestionInfo;
import com.cssweb.payment.account.test.TestCase;
import com.cssweb.payment.common.provider.sei.IIdGeneratorService;
import com.cssweb.payment.common.response.AttachmentResponse;

/**
 * 
 * @author zhanwx
 * @version 0.1 (2014年7月19日 下午2:26:55)
 * @since 0.1
 * @see
 */
public class UserQuestionTest extends TestCase {

	@Resource
	private IUserQuestionDao userQuestionDao;
	/*@Resource
	private IIdGeneratorService idGeneratorService;*/
	
	@Test
	public void testSelect() {
		
		UserQuestionInfo uq = new UserQuestionInfo();
		
		uq.setId(1L);;

		userQuestionDao.selectOne(uq);
	}
	
	@Test
	public void testInsert() {
		UserQuestionInfo uq = new UserQuestionInfo();
		uq.setId(2l);
		uq.setUserId(2l);
		Date date = new Date();
		uq.setUpdateDatetime(date);
		userQuestionDao.insert(uq);
	}
	
	@Test
	public void testUpdate()  {
		UserQuestionInfo uq = new UserQuestionInfo();
		uq.setId(2l);
		uq.setQuestionContent("详情请咨询人工客服！");
		userQuestionDao.update(uq);
		
	}
	
	@Test
	public void testSelectList() {
		UserQuestionInfo uq = new UserQuestionInfo();
		userQuestionDao.selectList(uq);
	}
	
	@Test
	public void testSelectCount() {
		UserQuestionInfo uq = new UserQuestionInfo();
		userQuestionDao.selectCount(uq);
	}
	
}
