/**
 * 
 */
package com.cssweb.payment.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cssweb.payment.account.dao.IUserFunctionInfoDao;
import com.cssweb.payment.account.pojo.UserFunctionInfo;
import com.cssweb.payment.account.pojo.UserLoginInfo;
import com.cssweb.payment.common.ibatis.BaseDao;


/**
 * Title: UserFunctionInfoDaoImpl
 * Description: UserFunctionInfo对应数据表DAO
 * Company: China Software & Service Company
 * Project:payment-account
 * @author liLei
 * @version 1.0
 * @date 2014年7月23日 下午4:45:24
 * @see UserFunctionInfo
 */
@Repository
public class UserFunctionInfoDaoImpl extends BaseDao<UserFunctionInfo> implements
		IUserFunctionInfoDao {
	
	public static final String CLASS_NAME = UserFunctionInfo.class.getName();
	/**
	 * 插入记录
	 * @param param
	 * @return
	 * @author liLei
	 * @date 2014年7月23日 下午4:45:01
	 * @since 0.1
	 */
	@Override
	public void insert(UserFunctionInfo param) {
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
	public void update(UserFunctionInfo param) {
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
	public UserFunctionInfo selectOne(UserFunctionInfo param) {
		return (UserFunctionInfo) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".select",param);
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
	public List<UserFunctionInfo> selectList(UserFunctionInfo param) {
		return getSqlMapClientTemplate().queryForList(CLASS_NAME + ".select",param);
	}

	/**
	 * 查询记录总数
	 * @param param
	 * @return
	 * @author liLei
	 * @date 2014年7月23日 下午4:45:01
	 * @since 0.1
	 */
	@Override
	public int selectCount(UserFunctionInfo param) {
		return (int) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".selectCount",param);
	}
	

}
