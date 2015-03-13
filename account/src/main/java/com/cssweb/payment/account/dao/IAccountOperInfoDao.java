/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.dao;

import java.math.BigDecimal;
import java.util.List;

import com.cssweb.payment.account.pojo.AccountOperInfo;

/**
 * @author ZhaoXingwen
 * @version 0.1 (2014年7月19日 下午2:28:30)
 * @since 0.1
 * @see
 */
public interface IAccountOperInfoDao {

	/**
	 * 插入记录
	 * @author zhaoxingwen
	 * @date 2014年7月19日 下午2:28:30
	 * @param operInfo 
	 * @since 0.1
	 * @see
	 */
	public void insert(AccountOperInfo operInfo);
	/**
	 * 更新记录
	 * @author zhaoxingwen
	 * @date 2014年7月19日 下午2:28:30
	 * @param operInfo 
	 * @since 0.1
	 * @see
	 */
	public void update(AccountOperInfo operInfo);
	/**
	 * 查询单条记录
	 * @author zhaoxingwen
	 * @date 2014年7月19日 下午2:28:30
	 * @param operInfo 
	 * @since 0.1
	 * @see
	 */
	public AccountOperInfo selectOne(AccountOperInfo operInfo);
	/**
	 * 查询多条记录
	 * @author zhaoxingwen
	 * @date 2014年7月19日 下午2:28:30
	 * @param operInfo 
	 * @since 0.1
	 * @see
	 */
	public List<AccountOperInfo> selectList(AccountOperInfo operInfo);
	/**
	 * 查询记录数
	 * @author zhaoxingwen
	 * @date 2014年7月19日 下午2:28:30
	 * @param operInfo 
	 * @since 0.1
	 * @see
	 */
	public int selectCount(AccountOperInfo operInfo);
	/**
	 * 查询当天交易类型的总记录数
	 * @author wangpeng
	 * @date 2014年9月5日 下午9:21:30
	 * @param operInfo 
	 * @since 0.1
	 * @see
	 */
	public int selectTimesDayCount(AccountOperInfo operInfo);
	/**
	 * 查询当天交易类型的总金额数
	 * @author wangpeng
	 * @date 2014年9月19日 下午4:21:30
	 * @param operInfo 
	 * @since 0.1
	 * @see
	 */
	public BigDecimal selectAmountDayCount(AccountOperInfo operInfo);
}
