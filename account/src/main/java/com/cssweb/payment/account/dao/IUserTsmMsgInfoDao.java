package com.cssweb.payment.account.dao;

import java.util.List;

import com.cssweb.payment.account.pojo.UserLoginInfo;
import com.cssweb.payment.account.pojo.UserTsmMsgInfo;


/**
 * Title: IUserTsmMsgInfoDao
 * Description: 
 * Company: China Software & Service Company
 * Project:payment-account
 * @author liLei
 * @version 1.0
 * @date 2014年7月23日 下午4:48:04
 * @see 
 */
public interface IUserTsmMsgInfoDao {
	
	/**
	 * 插入记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:48:04
	 * @param userMsgInfo 
	 * @since 0.1
	 * @see
	 */
	public void insert(UserTsmMsgInfo userMsgInfo);
	
	/**
	 * 更新记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:48:04
	 * @param userMsgInfo 
	 * @since 0.1
	 * @see
	 */
	public void update(UserTsmMsgInfo userMsgInfo);
	
	/**
	 * 查询单条记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:48:04
	 * @param userMsgInfo 
	 * @since 0.1
	 * @see
	 */
	public UserTsmMsgInfo selectOne(UserTsmMsgInfo userMsgInfo);
	
	/**
	 * 查询多条记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:48:04
	 * @param userMsgInfo 
	 * @since 0.1
	 * @see
	 */
	public List<UserTsmMsgInfo> selectList(UserTsmMsgInfo userMsgInfo);
	
	/**
	 * 查询记录数
	 * @author liLei
	 * @date 2014年7月23日 下午4:48:04
	 * @param userMsgInfo 
	 * @since 0.1
	 * @see
	 */
	public int selectCount(UserTsmMsgInfo user);
	
	
}
