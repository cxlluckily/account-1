package com.cssweb.payment.account.dao;

import java.util.List;

import com.cssweb.payment.account.pojo.UserFunctionInfo;



/**
 * Title: IUserFunctionInfoDao
 * Description: 
 * Company: China Software & Service Company
 * Project:payment-account
 * @author liLei
 * @version 1.0
 * @date 2014年7月23日 下午4:47:12
 * @see 
 */
public interface IUserFunctionInfoDao {
	
	/**
	 * 插入记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:47:12
	 * @param user 
	 * @since 0.1
	 * @see
	 */
	public void insert(UserFunctionInfo user);
	/**
	 * 更新记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:47:12
	 * @param user 
	 * @since 0.1
	 * @see
	 */
	public void update(UserFunctionInfo user);
	/**
	 * 查询单条记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:47:12
	 * @param user 
	 * @since 0.1
	 * @see
	 */
	public UserFunctionInfo selectOne(UserFunctionInfo user);
	/**
	 * 查询多条记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:47:12
	 * @param user 
	 * @since 0.1
	 * @see
	 */
	public List<UserFunctionInfo> selectList(UserFunctionInfo user);
	/**
	 * 查询记录数
	 * @author liLei
	 * @date 2014年7月23日 下午4:47:12
	 * @param user 
	 * @since 0.1
	 * @see
	 */
	public int selectCount(UserFunctionInfo user);
}
