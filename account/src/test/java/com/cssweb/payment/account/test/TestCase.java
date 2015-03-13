package com.cssweb.payment.account.test;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
@Transactional(propagation = Propagation.REQUIRED)
@TransactionConfiguration(defaultRollback = false)
public abstract class TestCase extends Assert {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
}
