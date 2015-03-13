package com.cssweb.payment.account.dao;

import java.util.List;

import com.cssweb.payment.account.pojo.FinancialSummaryDay;
import com.cssweb.payment.account.pojo.PersonalUser;
import com.cssweb.payment.account.vo.FinancialSummaryDayQuery;
import com.cssweb.payment.account.vo.PersonalUserQuery;
import com.cssweb.payment.common.util.Page;


public interface IFinancialSummaryDayDao {
	
	public  int selectCount(FinancialSummaryDayQuery param);
	
	public Page<FinancialSummaryDay> select(FinancialSummaryDayQuery param);

	public List<FinancialSummaryDay> selectList(FinancialSummaryDayQuery param);
}
