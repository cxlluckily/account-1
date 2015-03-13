/**
 * 
 */
package com.cssweb.payment.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cssweb.payment.account.dao.IBusinessInfoDao;
import com.cssweb.payment.account.pojo.BusinessInfo;
import com.cssweb.payment.account.pojo.UserLoginInfo;
import com.cssweb.payment.common.ibatis.BaseDao;


/**
 * Title: BusinessInfoDaoImpl
 * Description: 
 * Company: China Software & Service Company
 * Project:payment-account
 * @author liLei
 * @version 1.0
 * @date 2014年7月23日 下午4:18:02
 * @see 
 */
@Repository
public class BusinessInfoDaoImpl extends BaseDao<BusinessInfo> implements
		IBusinessInfoDao {
	
	public static final String CLASS_NAME = BusinessInfo.class.getName();
	
	/**
	 * 插入记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:18:02
	 * @param param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public void insert(BusinessInfo param) {
		getSqlMapClientTemplate().insert(CLASS_NAME + ".insert",param);
	}

	/**
	 * 更新记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:18:02
	 * @param param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public void update(BusinessInfo param) {
		getSqlMapClientTemplate().insert(CLASS_NAME + ".update",param);
	}

	/**
	 * 单记录查询
	 * @author liLei
	 * @date 2014年7月23日 下午4:18:02
	 * @param param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public BusinessInfo selectOne(BusinessInfo param) {
		return (BusinessInfo) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".select",param);
	}

	/**
	 * 多记录查询
	 * @author liLei
	 * @date 2014年7月23日 下午4:18:02
	 * @param param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public List<BusinessInfo> selectList(BusinessInfo param) {
		return getSqlMapClientTemplate().queryForList(CLASS_NAME + ".select",param);
	}
	/**
	 * 记录总数查询
	 * @author liLei
	 * @date 2014年7月23日 下午4:18:02
	 * @param param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public int selectCount(BusinessInfo param) {
		return (int) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".selectCount",param);
	}
	
	/**
	 * 删除单条记录
	 * @author wangpeng
	 * @date 2014年9月3号 下午12:37:40
	 * @param id
	 * @since 0.1
	 */
	@Override
	public int delete(BusinessInfo param){
		return getSqlMapClientTemplate().delete(CLASS_NAME + ".delete",param);
	}

}
