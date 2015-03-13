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
import com.cssweb.payment.account.pojo.UserBandBankInfo;
import com.cssweb.payment.account.test.TestCase;
import com.cssweb.payment.common.provider.sei.IIdGeneratorService;
import com.cssweb.payment.common.response.AttachmentResponse;
/**
 * @author zhanwx
 * @version 0.1 (2014年7月20日 下午1:20:09)
 * @since 0.1
 * @see
 */
public class UserBandBankInfoDaoTest extends TestCase{
	
	@Resource
	private IUserBandBankInfoDao ubbdao;
	
	private IIdGeneratorService idGeneratorService;
	
	

	@Test
	public void testSelect() {
		//AttachmentResponse<Long> respinse = idGeneratorService.generate(RateInfo.class.getName());
		UserBandBankInfo ubbf = new UserBandBankInfo();
		
		ubbf.setId(1L);
		ubbdao.selectOne(ubbf);
	}
	
	@Test
	public void testSelestList(){
		
		UserBandBankInfo ubbf = new UserBandBankInfo();
		ubbdao.selectList(ubbf);
	}
	
	@Test
	public void testInsert() {
		UserBandBankInfo ubb = new UserBandBankInfo();
		ubb.setId(2L);
		ubb.setUserId(2L);
		ubb.setBankAccountNo("002");
		Date date = new Date();
		
		ubb.setUpdateDatetime(date);
		ubbdao.insert(ubb);
	}
	
	@Test
	public void testUpdate()  {
		UserBandBankInfo ubb = new UserBandBankInfo();
		ubb.setId(2L);
		ubb.setBandPhoneNo("15625114726");
		ubbdao.update(ubb);
		
	}
	
	@Test
	public void testSeleteCount(){
		UserBandBankInfo ubb = new UserBandBankInfo();
		ubbdao.selectCount(ubb);
	}

}
