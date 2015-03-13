/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.dao;

import java.util.List;

import com.cssweb.payment.account.pojo.UserLoginInfo;

/**
 * @author Ganlin
 * @version 0.1 (2014年7月18日 下午2:28:30)
 * @since 0.1
 * @see
 */
public interface IUserLoginDao {

	/**
	 * 插入记录
	 * @author Ganlin
	 * @date 2014年7月18日 下午2:28:30
	 * @param user 
	 * @since 0.1
	 * @see
	 */
	public void insert(UserLoginInfo user);
	/**
	 * 更新记录
	 * @author Ganlin
	 * @date 2014年7月18日 下午2:28:30
	 * @param user 
	 * @since 0.1
	 * @see
	 */
	public void update(UserLoginInfo user);
	/**
	 * 查询单条记录
	 * @author Ganlin
	 * @date 2014年7月18日 下午2:28:30
	 * @param user 
	 * @since 0.1
	 * @see
	 */
	public UserLoginInfo selectOne(UserLoginInfo user);
	/**
	 * 查询多条记录
	 * @author Ganlin
	 * @date 2014年7月18日 下午2:28:30
	 * @param user 
	 * @since 0.1
	 * @see
	 */
	public List<UserLoginInfo> selectList(UserLoginInfo user);
	/**
	 * 查询记录数
	 * @author Ganlin
	 * @date 2014年7月18日 下午2:28:30
	 * @param user 
	 * @since 0.1
	 * @see
	 */
	public int selectCount(UserLoginInfo user);
	/**
	 * 更新未绑定的手机注册用户信息
	 * @author liLei
	 * @date 2014年7月18日 下午2:28:30
	 * @param user 
	 * @since 0.1
	 * @see
	 */
	public void updateUnbindingMobile(Long userId);
	/**
	 * 更新未绑定的邮箱注册用户信息
	 * @author liLei
	 * @date 2014年7月18日 下午2:28:30
	 * @param user 
	 * @since 0.1
	 * @see
	 */
	public void updateUnbindingEmail(Long userId);
	
}
