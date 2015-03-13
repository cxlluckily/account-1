/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.sei;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 功能测试父类
 * 
 * @author DuXiaohua
 * @version 1.0 (2014年7月29日 上午11:07:08)
 * @since 1.0
 * @see
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public abstract class TestCase extends Assert {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	protected IAccountService accountService;
	@Resource
	protected IPortalService portalService;
	@Resource
	protected IManageService manageService;
	@Resource
	protected IRiskService riskService;
	@Resource
	protected ITradeService tradeService;
	@Resource
	protected IMobileService mobileService;
}
