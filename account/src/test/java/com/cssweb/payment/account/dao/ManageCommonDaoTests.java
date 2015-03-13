/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.cssweb.payment.account.test.TestCase;
import com.cssweb.payment.account.vo.CommercialTenantAccount;
import com.cssweb.payment.account.vo.CommercialTenantAccountDetailedQuery;
import com.cssweb.payment.account.vo.CommercialTenantAccountQuery;
import com.cssweb.payment.account.vo.PaymentAccountDetailedQuery;

/**
 * @author Ganlin
 * @version 0.1 (2014年7月26日 下午3:23:09)
 * @since 0.1
 * @see
 */
public class ManageCommonDaoTests extends TestCase {

	@Resource
	private IManageCommonDao manageCommonDao;
	
	/**
	 * 测试CommercialTenantAccount的selectList
	 * @author Ganlin
	 * @date 2014年7月26日 下午5:58:34 
	 * @since 0.1
	 */
	@Test
	public void testSelectList() {
		CommercialTenantAccountQuery query = new CommercialTenantAccountQuery();
		//无条件查询
		manageCommonDao.selectList(query);
		//按法人姓名查询
		query.setLegalPersonName("杜潜");
		manageCommonDao.selectList(query);
	}
	
	/**
	 * CommercialTenantAccountDetailed的selectList和selectByPage
	 * @author Ganlin
	 * @date 2014年7月26日 下午5:58:55 
	 * @since 0.1
	 */
	@Test
	public void testSelectList2() {
		CommercialTenantAccountDetailedQuery query = new CommercialTenantAccountDetailedQuery();
		//无条件查询
		manageCommonDao.selectList(query);
		//按accountId查
		query.setAccountNumber(81L);
		manageCommonDao.selectList(query);
		//分页查询
		query.setPageNo(1);
		query.setPageSize(2);
		manageCommonDao.selectByPage(query);
	}
	
	/**
	 * PaymentAccountDetailed的selectList和selectByPage
	 * @author Ganlin
	 * @date 2014年7月26日 下午5:58:55 
	 * @since 0.1
	 */
	@Test
	public void testSelectList3() {
		PaymentAccountDetailedQuery query = new PaymentAccountDetailedQuery();
		//无条件查询
		manageCommonDao.selectList(query);
		//按accountId查
		query.setAccountNumber(200L);
		manageCommonDao.selectList(query);
		//分页查询
		query.setPageNo(2);
		query.setPageSize(2);
		manageCommonDao.selectByPage(query);
	}
}
