/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.dao.impl;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cssweb.payment.common.ibatis.BaseDao;
import com.cssweb.payment.account.dao.IAccountOperInfoDao;
import com.cssweb.payment.account.pojo.AccountOperInfo;

/**
 * @author ZhaoXingwen
 * @version 0.1 (2014年7月19日 下午2:29:34)
 * @since 0.1
 * @see
 */
@Repository
public class AccountOperInfoDaoImpl  extends BaseDao<AccountOperInfo> implements
IAccountOperInfoDao {

	public static final String CLASS_NAME = AccountOperInfo.class.getName();
	/**
	 * 插入记录
	 * @author zhaoxingwen
	 * @date 2014年7月19日 下午2:29:34
	 * @param user
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public void insert(AccountOperInfo user) {
		getSqlMapClientTemplate().insert(CLASS_NAME + ".insert", user);
	}
	/**
	 * 更新记录
	 * @author zhaoxingwen
	 * @date 2014年7月19日 下午2:29:34
	 * @param user
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public void update(AccountOperInfo user) {
		getSqlMapClientTemplate().insert(CLASS_NAME + ".update", user);
	}
	/**
	 * 单记录查询
	 * @author zhaoxingwen
	 * @date 2014年7月19日 下午2:29:34
	 * @param user
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public AccountOperInfo selectOne(AccountOperInfo user) {
		return (AccountOperInfo) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".select", user);
	}
	/**
	 * 多记录查询
	 * @author zhaoxingwen
	 * @date 2014年7月19日 下午2:29:34
	 * @param user
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public List<AccountOperInfo> selectList(AccountOperInfo user) {
		return getSqlMapClientTemplate().queryForList(CLASS_NAME + ".select", user);
	}
	/**
	 * 记录总数查询
	 * @author zhaoxingwen
	 * @date 2014年7月19日 下午2:29:34
	 * @param user
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public int selectCount(AccountOperInfo user) {
		return (int) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".selectCount", user);
	}
	/**
	 * 查询当天交易类型的总记录数
	 * @author wangpeng
	 * @date 2014年9月5日 下午9:21:30
	 * @param operInfo 
	 * @since 0.1
	 * @see
	 */
	public int selectTimesDayCount(AccountOperInfo operInfo){
		return (int) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".selectTimesDayCount", operInfo);
	}
	/**
	 * 查询当天交易类型的总金额数
	 * @author wangpeng
	 * @date 2014年9月19日 下午4:21:30
	 * @param operInfo 
	 * @since 0.1
	 * @see
	 */
	public BigDecimal selectAmountDayCount(AccountOperInfo operInfo){
		return (BigDecimal) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".selectAmountDayCount", operInfo);
	}
}
