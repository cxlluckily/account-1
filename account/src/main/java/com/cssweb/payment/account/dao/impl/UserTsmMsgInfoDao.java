package com.cssweb.payment.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cssweb.payment.account.dao.IUserTsmMsgInfoDao;
import com.cssweb.payment.account.pojo.UserLoginInfo;
import com.cssweb.payment.account.pojo.UserTsmMsgInfo;
import com.cssweb.payment.common.ibatis.BaseDao;


/**
 * Title: UserTsmMsgInfoDao
 * Description: UserTsmMsgInfo对应数据表DAO
 * Company: China Software & Service Company
 * Project:payment-account
 * @author liLei
 * @version 1.0
 * @date 2014年7月23日 下午4:46:15
 * @see UserTsmMsgInfo
 */
@Repository
public class UserTsmMsgInfoDao extends BaseDao<UserLoginInfo> implements IUserTsmMsgInfoDao {

	
	public static final String CLASS_NAME = UserTsmMsgInfo.class.getName();
	/**
	 * 插入记录
	 * @param userMsgInfo
	 * @author liLei
	 * @date 2014年7月23日 下午4:46:15
	 * @since 0.1
	 */
	@Override
	public void insert(UserTsmMsgInfo userMsgInfo) {
		this.getSqlMapClientTemplate().insert(CLASS_NAME + ".insert", userMsgInfo);

	}
	/**
	 * 更新记录
	 * @param userMsgInfo
	 * @author liLei
	 * @date 2014年7月23日 下午4:46:15
	 * @since 0.1
	 */
	@Override
	public void update(UserTsmMsgInfo userMsgInfo) {
		this.getSqlMapClientTemplate().update(CLASS_NAME + ".update", userMsgInfo);

	}

	/**
	 * 单记录查询
	 * @param userMsgInfo
	 * @author liLei
	 * @date 2014年7月23日 下午4:46:15
	 * @since 0.1
	 */
	@Override
	public UserTsmMsgInfo selectOne(UserTsmMsgInfo userMsgInfo) {
		 return (UserTsmMsgInfo) this.getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".select", userMsgInfo);
	}
	/**
	 * 多记录查询
	 * @param userMsgInfo
	 * @author liLei
	 * @date 2014年7月23日 下午4:46:15
	 * @since 0.1
	 */
	@Override
	public List<UserTsmMsgInfo> selectList(UserTsmMsgInfo userMsgInfo) {
		return getSqlMapClientTemplate().queryForList(CLASS_NAME + ".select", userMsgInfo);
	}
	/**
	 * 记录总数查询
	 * @param user
	 * @author liLei
	 * @date 2014年7月23日 下午4:46:15
	 * @since 0.1
	 */
	@Override
	public int selectCount(UserTsmMsgInfo user) {
		return (int) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".selectCount", user);
	}

}
