package com.cssweb.payment.account.dao;

import java.util.List;

import com.cssweb.payment.account.pojo.UserRelAuInfo;
import com.cssweb.payment.account.vo.EnterpriseUserQuery;
import com.cssweb.payment.account.vo.GetUserRelAuInfoResponse;
import com.cssweb.payment.account.vo.PersonalUserQuery;

/**
 * Title: IUserRelAuInfoDao
 * Description: 
 * Company: China Software & Service Company
 * Project:payment-account
 * @author wangpeng
 * @version 1.0
 * @date 2014年9月4日 下午8:05:42
 * @see 
 */
public interface IUserRelAuInfoDao {
	
	/**
	 * 增加
	 * @author wangpeng
	 * @date 2014年9月4日 下午8:05:42
	 * @param param 
	 * @since 0.1
	 * @see
	 */
	public void insert(UserRelAuInfo param);
	/**
	 * 更新
	 * @author wangpeng
	 * @date 2014年9月4日 下午8:05:42
	 * @param param 
	 * @since 0.1
	 * @see
	 */
	public void update(UserRelAuInfo param);
	
	/**
	 * Title: selectOne
	 * @author wangpeng
	 * @date 2014年9月4日 下午8:05:42
	 * @param param 
	 * @since 0.1
	 * @see
	 */
	public UserRelAuInfo selectOne(UserRelAuInfo param);
	
	/**
	 * Title: selectList
	 * @author wangpeng
	 * @date 2014年9月4日 下午8:05:42
	 * @param param 
	 * @since 0.1
	 * @see
	 */
	public List<UserRelAuInfo> selectList(UserRelAuInfo param);
	
	/**
	 * Title: selectCount
	 * @author wangpeng
	 * @date 2014年9月4日 下午8:05:42
	 * @param param 
	 * @since 0.1
	 * @see
	 */
	public int selectCount(UserRelAuInfo param);
	
	/**
	 * 查询个人的所有关联实名认证信息
	 * @author wangpeng
	 * @param personal
	 * @return list
	 * @date 2014年09月24日  下午2：30：00
	 * @since 0.1
	 */
	public List<GetUserRelAuInfoResponse> selectPersonalAuRel(PersonalUserQuery personal);
	
	/**
	 * 查询企业的所有关联实名认证信息
	 * @author wangpeng
	 * @param personal
	 * @return list
	 * @date 2014年09月24日  下午2：30：00
	 * @since 0.1
	 */
	public List<GetUserRelAuInfoResponse> selectEnterpriseAuRel(EnterpriseUserQuery enterprise);
}
