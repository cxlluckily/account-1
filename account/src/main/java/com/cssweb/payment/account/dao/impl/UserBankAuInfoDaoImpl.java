/**
 * 
 */
package com.cssweb.payment.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cssweb.payment.account.dao.IUserBankAuInfoDao;
import com.cssweb.payment.account.pojo.UserBankAuInfo;
import com.cssweb.payment.account.pojo.UserLoginInfo;
import com.cssweb.payment.common.ibatis.BaseDao;


/**
 * Title: UserBankAuInfoDaoImpl
 * Description: UserBankAuInfo表对应数据表DAO
 * Company: China Software & Service Company
 * Project:payment-account
 * @author liLei
 * @version 1.0
 * @date 2014年7月23日 下午4:45:01
 * @see UserBankAuInfo
 */
@Repository
public class UserBankAuInfoDaoImpl extends BaseDao<UserBankAuInfo> implements
		IUserBankAuInfoDao {
	
	public static final String CLASS_NAME = UserBankAuInfo.class.getName();
	/**
	 * 插入记录
	 * @param param
	 * @return
	 * @author liLei
	 * @date 2014年7月23日 下午4:45:01
	 * @since 0.1
	 */
	@Override
	public void insert(UserBankAuInfo param) {
		getSqlMapClientTemplate().insert(CLASS_NAME + ".insert",param);
	}

	/**
	 * 更新记录
	 * @param param
	 * @return
	 * @author liLei
	 * @date 2014年7月23日 下午4:45:01
	 * @since 0.1
	 */
	@Override
	public void update(UserBankAuInfo param) {
		getSqlMapClientTemplate().insert(CLASS_NAME + ".update",param);
		
	}

	/**
	 * 单记录查询
	 * @param param
	 * @return
	 * @author liLei
	 * @date 2014年7月23日 下午4:45:01
	 * @since 0.1
	 */
	@Override
	public UserBankAuInfo selectOne(UserBankAuInfo param) {
		return (UserBankAuInfo) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".select",param);
	}

	/**
	 * 多记录查询
	 * @param param
	 * @return
	 * @author liLei
	 * @date 2014年7月23日 下午4:45:01
	 * @since 0.1
	 */
	@Override
	public List<UserBankAuInfo> selectList(UserBankAuInfo param) {
		return getSqlMapClientTemplate().queryForList(CLASS_NAME + ".select",param);
	}

	/**
	 * 记录总数查询
	 * @param param
	 * @return
	 * @author liLei
	 * @date 2014年7月23日 下午4:45:01
	 * @since 0.1
	 */
	@Override
	public int selectCount(UserBankAuInfo param) {
		return (int) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".selectCount",param);
	}
	

}
