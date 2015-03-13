/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.dao.impl;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cssweb.payment.account.dao.IAccountMoneyChgDetailDao;
import com.cssweb.payment.account.pojo.AccountMoneyChgDetail;
import com.cssweb.payment.account.vo.AccountMoneyChgDetailQuery;
import com.cssweb.payment.common.ibatis.BaseDao;
import com.cssweb.payment.common.util.Page;

/**
 * 账户资金变动流水实现
 * @author ZhaoXingwen
 * @version 0.1 (2014年7月19日 下午2:29:34)
 * @since 0.1
 * @see
 */
@Repository
public class AccountMoneyChgDetailDaoImpl   extends BaseDao<AccountMoneyChgDetail> implements
IAccountMoneyChgDetailDao {
	public static final String CLASS_NAME = AccountMoneyChgDetail.class.getName();
	
	/**
	 * 插入记录
	 * @author zhaoxingwen
	 * @date 2014年7月19日 下午2:29:34
	 * @param accountMoneyChgLog
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public void insert(AccountMoneyChgDetail accountMoneyChgLog) {
		getSqlMapClientTemplate().insert(CLASS_NAME + ".insert", accountMoneyChgLog);
	}
	/**
	 * 更新记录
	 * @author zhaoxingwen
	 * @date 2014年7月19日 下午2:29:34
	 * @param accountMoneyChgLog
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public void update(AccountMoneyChgDetail accountMoneyChgLog) {
		getSqlMapClientTemplate().insert(CLASS_NAME + ".update", accountMoneyChgLog);
	}
	/**
	 * 单记录查询
	 * @author zhaoxingwen
	 * @date 2014年7月19日 下午2:29:34
	 * @param accountMoneyChgLog
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public AccountMoneyChgDetail selectOne(AccountMoneyChgDetailQuery accountMoneyChgLog) {
		return (AccountMoneyChgDetail) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".select", accountMoneyChgLog);
	}
	/**
	 * 多记录查询
	 * @author zhaoxingwen
	 * @date 2014年7月19日 下午2:29:34
	 * @param accountMoneyChgLog
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public List<AccountMoneyChgDetail> selectList(AccountMoneyChgDetailQuery accountMoneyChgLog) {
		return getSqlMapClientTemplate().queryForList(CLASS_NAME + ".select", accountMoneyChgLog);
	}

	/**
	 * 记录总数查询
	 * @author zhaoxingwen
	 * @date 2014年7月19日 下午2:29:34
	 * @param accountMoneyChgLog
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public int selectCount(AccountMoneyChgDetailQuery accountMoneyChgLog) {
		return (int) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".selectCount", accountMoneyChgLog);
	}

	/**
	 * 分页查询
	 * @author zhaoxingwen
	 * @date 2014年7月19日 下午2:29:34
	 * @param accountMoneyChgLog
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public Page<AccountMoneyChgDetail> selectByPage(AccountMoneyChgDetailQuery param) {
		Integer pageNo = param.getPageNo();
		Integer pageSize = param.getPageSize();
		if (pageNo == null || pageSize == null) {
			throw new IllegalArgumentException("pageNo or pageSize is null");
		}
		Integer firstResult = Page.getFirstResult(pageNo, pageSize);
		param.setFirstResult(firstResult);
		Page<AccountMoneyChgDetail> page = new Page<AccountMoneyChgDetail>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		Integer count = selectCount(param);
		page.setTotalCount(count);
		if (count == 0) {
			return page;
		}
		List<AccountMoneyChgDetail> userList = selectList(param);
		page.setData(userList);
		return page;
	}

	/**
	 * 账户资金变动总额查询
	 * @author zhaoxingwen
	 * @date 2014年7月19日 下午2:29:34
	 * @param accountMoneyChgLog
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public BigDecimal selectTradeAmount(AccountMoneyChgDetailQuery param) {
		return (BigDecimal) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".selectTradeAmount", param);
	}
	
}
