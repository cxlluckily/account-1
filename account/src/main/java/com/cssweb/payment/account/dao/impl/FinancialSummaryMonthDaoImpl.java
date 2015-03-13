package com.cssweb.payment.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cssweb.payment.account.dao.IFinancialSummaryMonthDao;
import com.cssweb.payment.account.pojo.FinancialSummaryMonth;
import com.cssweb.payment.account.vo.FinancialSummaryMonthQuery;
import com.cssweb.payment.common.ibatis.BaseDao;
import com.cssweb.payment.common.util.Page;
@Repository
public class FinancialSummaryMonthDaoImpl extends BaseDao<FinancialSummaryMonth> implements IFinancialSummaryMonthDao  {

	public static final String CLASS_NAME = FinancialSummaryMonth.class.getName();
	@Override
	public int selectCount(FinancialSummaryMonthQuery param) {
		return (Integer)getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".selectCount",param);
	}

	@Override
	public Page<FinancialSummaryMonth> select(FinancialSummaryMonthQuery param) {
		Integer pageNo = param.getPageNo();
		Integer pageSize = param.getPageSize();
		if (pageNo == null || pageSize == null) {
			throw new IllegalArgumentException("pageNo or pageSize is null");
		}
		Integer firstResult = Page.getFirstResult(pageNo, pageSize);
		param.setFirstResult(firstResult);
		Page<FinancialSummaryMonth> page = new Page<FinancialSummaryMonth>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		Integer count = selectCount(param);
		page.setTotalCount(count);
		if (count == 0) {
			return page;
		}
		List<FinancialSummaryMonth> userList = selectList(param);
		page.setData(userList);
		return page;
	}

	@Override
	public List<FinancialSummaryMonth> selectList(FinancialSummaryMonthQuery param) {
		 return getSqlMapClientTemplate().queryForList(CLASS_NAME + ".select",param);
	}

}
