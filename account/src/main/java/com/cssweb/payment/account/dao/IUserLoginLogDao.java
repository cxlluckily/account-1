package com.cssweb.payment.account.dao;

import java.util.List;

import com.cssweb.payment.account.pojo.UserLoginLog;


/**
 * @author zhanwx
 * @version 0.1 (2014年8月4日 下午1:55:05)
 * @since 0.1
 * @see
 */
public interface IUserLoginLogDao {
	/**
	 * 插入记录
	 * @author zhanwx
	 * @date 2014年8月4日 下午1:55:05
	 * @param log 
	 * @since 0.1
	 * @see
	 */
	public void insert(UserLoginLog log);
	/**
	 * 更新记录
	 * @author zhanwx
	 * @date 2014年8月4日 下午1:55:05
	 * @param log 
	 * @since 0.1
	 * @see
	 */
	public void update(UserLoginLog log);
	/**
	 * 查询单条记录
	 * @author zhanwx
	 * @date 2014年8月4日 下午1:55:05
	 * @param log 
	 * @since 0.1
	 * @see
	 */
	public UserLoginLog selectOne(UserLoginLog log);
	/**
	 * 查询多条记录
	 * @author zhanwx
	 * @date 2014年8月4日 下午1:55:05
	 * @param log 
	 * @since 0.1
	 * @see
	 */
	public List<UserLoginLog> selectList(UserLoginLog log);
	/**
	 * 查询记录数
	 * @author zhanwx
	 * @date 2014年8月4日 下午1:55:05
	 * @param log 
	 * @since 0.1
	 * @see
	 */
	public int selectCount(UserLoginLog log);
}
