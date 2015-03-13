package com.cssweb.payment.account.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.cssweb.payment.account.test.TestCase;
import com.cssweb.payment.account.vo.FinancialSummaryDayQuery;

public class FinancialSummaryDayTest extends TestCase {
	
	@Resource
	private IFinancialSummaryDayDao financialSummaryDayDao;
	
	
	@Test
	public void testSelect() {
		FinancialSummaryDayQuery param =new FinancialSummaryDayQuery();
		param.setEnterId(1l);
		financialSummaryDayDao.selectCount(param);
	}

}
