package com.cssweb.payment.account.dao;

import java.util.List;

import com.cssweb.payment.account.pojo.UserMobileAccountInfo;

/**
 * Title: IUserMobileAccountInfoDao
 * Description: 
 * Company: China Software & Service Company
 * Project:payment-account
 * @author liLei
 * @version 1.0
 * @date 2014年7月23日 下午4:47:35
 * @see 
 */
public interface IUserMobileAccountInfoDao {
	
	/**
	 * 插入记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:47:35
	 * @param userMobileInfo 
	 * @since 0.1
	 * @see
	 */
	public void insert(UserMobileAccountInfo userMobileInfo);
	
	/**
	 * 更新记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:47:35
	 * @param userMobileInfo 
	 * @since 0.1
	 * @see
	 */
	public void update(UserMobileAccountInfo userMobileInfo);
	
	/**
	 * 查询单条记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:47:35
	 * @param userMobileInfo 
	 * @since 0.1
	 * @see
	 */
	public UserMobileAccountInfo selectOne(UserMobileAccountInfo userMobileInfo);
	
	/**
	 * 查询多条记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:47:35
	 * @param userMobileInfo 
	 * @since 0.1
	 * @see
	 */
	public List<UserMobileAccountInfo> selectList(UserMobileAccountInfo userMobileInfo);
	
	/**
	 * 查询记录数
	 * @author liLei
	 * @date 2014年7月23日 下午4:47:35
	 * @param userMobileInfo 
	 * @since 0.1
	 * @see
	 */
	public int selectCount(UserMobileAccountInfo userMobileInfo);
	
	
}
