/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.cssweb.payment.account.dao.IUserLoginDao;
import com.cssweb.payment.account.pojo.UserLoginInfo;
import com.cssweb.payment.common.ibatis.BaseDao;

/**
 * @author Ganlin
 * @version 0.1 (2014年7月18日 下午2:29:34)
 * @since 0.1
 * @see
 */
@Repository
public class UserLoginDaoImpl extends BaseDao<UserLoginInfo> implements
		IUserLoginDao {

	public static final String CLASS_NAME = UserLoginInfo.class.getName();
	/**
	 * 查询记录
	 * @param user
	 * @author Ganlin
	 * @date 2014年7月18日 下午2:29:34
	 * @since 0.1
	 */
	@Override
	public void insert(UserLoginInfo user) {
		this.getSqlMapClientTemplate().insert(CLASS_NAME + ".insert", user);
	}
	/**
	 * 更新记录
	 * @param user
	 * @author Ganlin
	 * @date 2014年7月18日 下午2:29:34
	 * @since 0.1
	 */
	@Override
	public void update(UserLoginInfo user) {
		getSqlMapClientTemplate().update(CLASS_NAME + ".update", user);
	}
	/**
	 * 单记录查询
	 * @param user
	 * @author Ganlin
	 * @date 2014年7月18日 下午2:29:34
	 * @since 0.1
	 */
	@Override
	public UserLoginInfo selectOne(UserLoginInfo user) {
		return (UserLoginInfo) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".select", user);
	}
	/**
	 * 多记录查询
	 * @param user
	 * @author Ganlin
	 * @date 2014年7月18日 下午2:29:34
	 * @since 0.1
	 */
	@Override
	public List<UserLoginInfo> selectList(UserLoginInfo user) {
		return getSqlMapClientTemplate().queryForList(CLASS_NAME + ".select", user);
	}
	/**
	 * 查询记录总数
	 * @param user
	 * @author Ganlin
	 * @date 2014年7月18日 下午2:29:34
	 * @since 0.1
	 */
	@Override
	public int selectCount(UserLoginInfo user) {
		return (int) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".selectCount", user);
	}
	/**
	 * 待定
	 * @param user
	 * @author liLei
	 * @date 2014年7月18日 下午2:29:34
	 * @since 0.1
	 */
	@Override
	public void updateUnbindingMobile(Long userId) {
		getSqlMapClientTemplate().update(CLASS_NAME + ".updateUnbindingMobile", userId);
	}
	/**
	 * 待定
	 * @param user
	 * @author liLei
	 * @date 2014年7月18日 下午2:29:34
	 * @since 0.1
	 */
	@Override
	public void updateUnbindingEmail(Long userId) {
		getSqlMapClientTemplate().update(CLASS_NAME + ".updateUnbindingEmail", userId);
		
	}

}
