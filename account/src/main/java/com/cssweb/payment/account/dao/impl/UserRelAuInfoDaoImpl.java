/**
 * 
 */
package com.cssweb.payment.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cssweb.payment.account.dao.IUserRelAuInfoDao;
import com.cssweb.payment.account.pojo.UserRelAuInfo;
import com.cssweb.payment.account.vo.EnterpriseUserQuery;
import com.cssweb.payment.account.vo.GetUserRelAuInfoResponse;
import com.cssweb.payment.account.vo.PersonalUserQuery;
import com.cssweb.payment.common.ibatis.BaseDao;


/**
 * Title: UserRelAuInfoDaoImpl
 * Description: UserRelAuInfo表对应数据表DAO
 * Company: China Software & Service Company
 * Project:payment-account
 * @author wangpeng
 * @version 1.0
 * @date 2014年9月4日 下午8:14:01
 * @see 
 */
@Repository
public class UserRelAuInfoDaoImpl extends BaseDao<UserRelAuInfo> implements
		IUserRelAuInfoDao {
	
	public static final String CLASS_NAME = UserRelAuInfo.class.getName();
	/**
	 * 插入记录
	 * @param param
	 * @return
	 * @author wangpeng
	 * @date 2014年9月4日 下午8:14:01
	 * @since 0.1
	 */
	@Override
	public void insert(UserRelAuInfo param) {
		getSqlMapClientTemplate().insert(CLASS_NAME + ".insert",param);
	}

	/**
	 * 更新记录
	 * @param param
	 * @return
	 * @author wangpeng
	 * @date 2014年9月4日 下午8:14:01
	 * @since 0.1
	 */
	@Override
	public void update(UserRelAuInfo param) {
		getSqlMapClientTemplate().insert(CLASS_NAME + ".update",param);
		
	}

	/**
	 * 单记录查询
	 * @param param
	 * @return
	 * @author wangpeng
	 * @date 2014年9月4日 下午8:14:01
	 * @since 0.1
	 */
	@Override
	public UserRelAuInfo selectOne(UserRelAuInfo param) {
		return (UserRelAuInfo) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".select",param);
	}

	/**
	 * 多记录查询
	 * @param param
	 * @return
	 * @author wangpeng
	 * @date 2014年9月4日 下午8:14:01
	 * @since 0.1
	 */
	@Override
	public List<UserRelAuInfo> selectList(UserRelAuInfo param) {
		return getSqlMapClientTemplate().queryForList(CLASS_NAME + ".select",param);
	}

	/**
	 * 记录总数查询
	  * @param param
	 * @return
	 * @author wangpeng
	 * @date 2014年9月4日 下午8:14:01
	 * @since 0.1
	 */
	@Override
	public int selectCount(UserRelAuInfo param) {
		return (int) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".selectCount",param);
	}
	
	/**
	 * 查询个人的所有关联实名认证信息
	 * @author wangpeng
	 * @param PersonalUserQuery
	 * @return List<GetUserRelAuInfoResponse>
	 * @date 2014年09月24日  下午2：30：00
	 * @since 0.1
	 */
	@Override
	public List<GetUserRelAuInfoResponse> selectPersonalAuRel(PersonalUserQuery personal){
		List<GetUserRelAuInfoResponse> list = getSqlMapClientTemplate().queryForList(CLASS_NAME + ".selectPersonalRelAu",personal);
		return list;
	}
	
	/**
	 * 查询企业的所有关联实名认证信息
	 * @author wangpeng
	 * @param personal
	 * @return list
	 * @date 2014年09月24日  下午2：30：00
	 * @since 0.1
	 */
	@Override
	public List<GetUserRelAuInfoResponse> selectEnterpriseAuRel(EnterpriseUserQuery enterprise){
		List<GetUserRelAuInfoResponse> list = getSqlMapClientTemplate().queryForList(CLASS_NAME + ".selectEnterpriseuserRelAu",enterprise);
		return list;
	}
}
