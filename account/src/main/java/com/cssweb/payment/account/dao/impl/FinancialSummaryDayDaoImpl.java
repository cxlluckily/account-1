package com.cssweb.payment.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cssweb.payment.account.dao.IFinancialSummaryDayDao;
import com.cssweb.payment.account.pojo.FinancialSummaryDay;
import com.cssweb.payment.account.pojo.PersonalUser;
import com.cssweb.payment.account.vo.FinancialSummaryDayQuery;
import com.cssweb.payment.account.vo.PersonalUserQuery;
import com.cssweb.payment.common.ibatis.BaseDao;
import com.cssweb.payment.common.util.Page;

@Repository
public class FinancialSummaryDayDaoImpl extends BaseDao<FinancialSummaryDay> implements IFinancialSummaryDayDao  {
	
	public static final String CLASS_NAME = FinancialSummaryDay.class.getName();
	
	@Override
	public int selectCount(FinancialSummaryDayQuery param) {
		return (Integer)getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".selectCount",param);
	}

	@Override
	public Page<FinancialSummaryDay> select(FinancialSummaryDayQuery param) {
		Integer pageNo = param.getPageNo();
		Integer pageSize = param.getPageSize();
		if (pageNo == null || pageSize == null) {
			throw new IllegalArgumentException("pageNo or pageSize is null");
		}
		Integer firstResult = Page.getFirstResult(pageNo, pageSize);
		param.setFirstResult(firstResult);
		Page<FinancialSummaryDay> page = new Page<FinancialSummaryDay>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		Integer count = selectCount(param);
		page.setTotalCount(count);
		if (count == 0) {
			return page;
		}
		List<FinancialSummaryDay> userList = selectList(param);
		page.setData(userList);
		return page;
	}
	
	
	   @Override
	    public List<FinancialSummaryDay> selectList(FinancialSummaryDayQuery param) {
	        return getSqlMapClientTemplate().queryForList(CLASS_NAME + ".select",param);
	    }

}
