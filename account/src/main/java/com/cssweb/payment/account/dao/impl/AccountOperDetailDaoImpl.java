/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.cssweb.payment.account.dao.IAccountOperDetailDao;
import com.cssweb.payment.account.pojo.AccountOperDetail;
import com.cssweb.payment.common.ibatis.BaseDao;
/**
 * 账户资金变动流水实现
 * @author ZhaoXingwen
 * @version 0.1 (2014年7月19日 下午2:29:34)
 * @since 0.1
 * @see
 */
@Repository
public class AccountOperDetailDaoImpl  extends BaseDao<AccountOperDetail> implements
IAccountOperDetailDao {
	public static final String CLASS_NAME = AccountOperDetail.class.getName();
	/**
	 * 插入记录
	 * @author zhaoxingwen
	 * @date 2014年7月19日 下午2:29:34
	 * @param accountOperDetail
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public void insert(AccountOperDetail accountOperDetail) {
		getSqlMapClientTemplate().insert(CLASS_NAME + ".insert", accountOperDetail);
	}
	/**
	 * 更新记录
	 * @author zhaoxingwen
	 * @date 2014年7月19日 下午2:29:34
	 * @param accountOperDetail
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public void update(AccountOperDetail accountOperDetail) {
		getSqlMapClientTemplate().insert(CLASS_NAME + ".update", accountOperDetail);
	}
	/**
	 * 单条记录查询
	 * @author zhaoxingwen
	 * @date 2014年7月19日 下午2:29:34
	 * @param accountOperDetail
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public AccountOperDetail selectOne(AccountOperDetail accountOperDetail) {
		return (AccountOperDetail) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".select", accountOperDetail);
	}
	/**
	 * 多记录查询
	 * @author zhaoxingwen
	 * @date 2014年7月19日 下午2:29:34
	 * @param accountOperDetail
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public List<AccountOperDetail> selectList(AccountOperDetail accountOperDetail) {
		return getSqlMapClientTemplate().queryForList(CLASS_NAME + ".select", accountOperDetail);
	}
	/**
	 * 记录总数查询
	 * @author zhaoxingwen
	 * @date 2014年7月19日 下午2:29:34
	 * @param accountOperDetail
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public int selectCount(AccountOperDetail accountOperDetail) {
		return (int) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".selectCount", accountOperDetail);
	}
}
