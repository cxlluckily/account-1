package com.cssweb.payment.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cssweb.payment.account.dao.IUserMobileAccountInfoDao;
import com.cssweb.payment.account.pojo.UserLoginInfo;
import com.cssweb.payment.account.pojo.UserMobileAccountInfo;
import com.cssweb.payment.account.pojo.UserTsmMsgInfo;
import com.cssweb.payment.common.ibatis.BaseDao;


/**
 * Title: UserMobileAccountInfoDao
 * Description: UserMobileAccountInfo对应数据表DAO
 * Company: China Software & Service Company
 * Project:payment-account
 * @author liLei
 * @version 1.0
 * @date 2014年7月23日 下午4:45:51
 * @see UserMobileAccountInfo
 */
@Repository
public class UserMobileAccountInfoDao  extends BaseDao<UserLoginInfo>  implements IUserMobileAccountInfoDao {

	public static final String CLASS_NAME = UserMobileAccountInfo.class.getName();
	
	
	/**
	 * 插入记录
	 * @param userMobileInfo
	 * @author liLei
	 * @date 2014年7月23日 下午4:45:51
	 * @since 0.1
	 */
	@Override
	public void insert(UserMobileAccountInfo userMobileInfo) {
		this.getSqlMapClientTemplate().insert(CLASS_NAME + ".insert", userMobileInfo);

	}
	/**
	 * 更新记录
	 * @param userMobileInfo
	 * @author liLei
	 * @date 2014年7月23日 下午4:45:51
	 * @since 0.1
	 */
	@Override
	public void update(UserMobileAccountInfo userMobileInfo) {
		this.getSqlMapClientTemplate().insert(CLASS_NAME + ".update", userMobileInfo);
	}
	/**
	 * 单记录查询
	 * @param userMobileInfo
	 * @author liLei
	 * @date 2014年7月23日 下午4:45:51
	 * @since 0.1
	 */
	@Override
	public UserMobileAccountInfo selectOne(UserMobileAccountInfo userMobileInfo) {
		return (UserMobileAccountInfo) this.getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".select", userMobileInfo);
	}
	/**
	 * 多记录查询
	 * @param userMobileInfo
	 * @author liLei
	 * @date 2014年7月23日 下午4:45:51
	 * @since 0.1
	 */
	@Override
	public List<UserMobileAccountInfo> selectList(
			UserMobileAccountInfo userMobileInfo) {
		return getSqlMapClientTemplate().queryForList(CLASS_NAME + ".select", userMobileInfo);
	}
	/**
	 * 记录总数查询
	 * @param userMobileInfo
	 * @author liLei
	 * @date 2014年7月23日 下午4:45:51
	 * @since 0.1
	 */
	@Override
	public int selectCount(UserMobileAccountInfo userMobileInfo) {
		return (int) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".selectCount", userMobileInfo);
	}

}
