/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cssweb.payment.account.dao.IUserLoginLogDao;
import com.cssweb.payment.account.pojo.UserLoginLog;
import com.cssweb.payment.common.ibatis.BaseDao;

/**
 * @author zhanwx
 * @version 0.1 (2014年8月4日 下午2:18:27)
 * @since 0.1
 * @see
 */
@Repository
public class UserLoginLogImpl extends BaseDao<UserLoginLog> implements IUserLoginLogDao{
	
public static final String CLASS_NAME = UserLoginLog.class.getName();
		
	/**
	 * 插入记录
	 * @param log
	 * @author zhanwx
	 * @date 2014年8月4日 下午2:18:27
	 * @since 0.1
	 */
	@Override
	public void insert(UserLoginLog log) {
		this.getSqlMapClientTemplate().insert(CLASS_NAME + ".insert", log);
	}
	/**
	 * 更新记录
	 * @param log
	 * @author zhanwx
	 * @date 2014年8月4日 下午2:18:27
	 * @since 0.1
	 */
	@Override
	public void update(UserLoginLog log) {
		getSqlMapClientTemplate().update(CLASS_NAME + ".update", log);
	}
	/**
	 * 单记录查询
	 * @param log
	 * @author zhanwx
	 * @date 2014年8月4日 下午2:18:27
	 * @since 0.1
	 */
	@Override
	public UserLoginLog selectOne(UserLoginLog log) {
		return (UserLoginLog) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".select", log);
	}
	/**
	 * 多记录查询
	 * @param log
	 * @author zhanwx
	 * @date 2014年8月4日 下午2:18:27
	 * @since 0.1
	 */
	@Override
	public List<UserLoginLog> selectList(UserLoginLog log) {
		return getSqlMapClientTemplate().queryForList(CLASS_NAME + ".select", log);
	}
	/**
	 * 记录总数查询
	 * @param log
	 * @author zhanwx
	 * @date 2014年8月4日 下午2:18:27
	 * @since 0.1
	 */
	@Override
	public int selectCount(UserLoginLog log) {
		return (int) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".selectCount", log);
	}
}
