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
public class RateInfoTest extends TestCase {

	@Resource
	private IRateInfoDao rateInfoDao;
	
	private IIdGeneratorService idGeneratorService;
	
	@Test
	public void testSelect() {
		//AttachmentResponse<Long> respinse = idGeneratorService.generate(RateInfo.class.getName());
		RateInfo rt = new RateInfo();
		
		
		rt.setMerchantId(4);
		rt.setRateId("003");
		rateInfoDao.selectOne(rt);
	}
	
	@Test
	public void testInsert() {
		RateInfo rt = new RateInfo();
		rt.setMerchantId(102);
		rt.setRateId("009");
		rt.setRate((float)0.520);
		Date date = new Date();
		rt.setCreateDatetime(date);
		rt.setUpdateDatetime(date);
		rateInfoDao.insert(rt);
	}
	
	@Test
	public void testUpdate()  {
		RateInfo rt = new RateInfo();
		rt.setMerchantId(100);
		rt.setRateId("009");
		rt.setRate((float)0.256);
		rateInfoDao.update(rt);
		
	}
	
	@Test
	public void testSeleteCount(){
		RateInfo rt = new RateInfo();
		rateInfoDao.selectCount(rt);
	}
	
	@Test
	public void seleteList(){
		RateInfo rt = new RateInfo();
		rateInfoDao.selectList(rt);
	}
	
}
