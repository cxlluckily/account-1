package com.cssweb.payment.account.dao;

import java.util.List;

import com.cssweb.payment.account.pojo.FinancialSummaryDay;
import com.cssweb.payment.account.pojo.FinancialSummaryMonth;
import com.cssweb.payment.account.vo.FinancialSummaryMonthQuery;
import com.cssweb.payment.common.util.Page;

public interface IFinancialSummaryMonthDao {
public  int selectCount(FinancialSummaryMonthQuery param);
	
	public Page<FinancialSummaryMonth> select(FinancialSummaryMonthQuery param);

	public List<FinancialSummaryMonth> selectList(FinancialSummaryMonthQuery param);

}
