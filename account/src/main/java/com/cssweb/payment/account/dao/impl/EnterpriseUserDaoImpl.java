/**
 * 
 */
package com.cssweb.payment.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cssweb.payment.account.constant.DictionaryKey;
import com.cssweb.payment.account.dao.IEnterpriseUserDao;
import com.cssweb.payment.account.pojo.EnterpriseUser;
import com.cssweb.payment.account.util.DictionaryUtils;
import com.cssweb.payment.account.vo.EnterpriseUserQuery;
import com.cssweb.payment.account.vo.GetEnterpriseUserListResponse;
import com.cssweb.payment.account.vo.UserInfo;
import com.cssweb.payment.common.ibatis.BaseDao;
import com.cssweb.payment.common.util.Page;

/**
 * Title: EnterpriseUserDaoImpl
 * Description: EnterpriseUser类对应数据表DAO
 * Company: China Software & Service Company
 * Project:payment-account
 * @author liLei
 * @version 1.0
 * @date 2014年7月23日 下午4:36:31
 * @see EnterpriseUser
 */
@Repository
public class EnterpriseUserDaoImpl extends BaseDao<EnterpriseUser> implements
        IEnterpriseUserDao {
    
    public static final String CLASS_NAME = EnterpriseUser.class.getName();
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
    public void insert(EnterpriseUser param) {
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
    public void update(EnterpriseUser param) {
        getSqlMapClientTemplate().update(CLASS_NAME + ".update",param);
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
    public EnterpriseUser selectOne(EnterpriseUserQuery param) {
        return (EnterpriseUser) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".select",param);
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
    public List<EnterpriseUser> selectList(EnterpriseUserQuery param) {
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
    public int selectCount(EnterpriseUser param) {
        return (int) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".selectCount",param);
    }

	/**
	 * 分页查询企业用户
	 * @author Ganlin
	 * @date 2014年7月24日 下午2:02:36
	 * @return 
	 * @since 0.1
	 */
	@Override
	public Page<EnterpriseUser> selectByPage(EnterpriseUserQuery param) {
		Integer pageNo = param.getPageNo();
		Integer pageSize = param.getPageSize();
		if (pageNo == null || pageSize == null) {
			throw new IllegalArgumentException("pageNo or pageSize is null");
		}
		Integer firstResult = Page.getFirstResult(pageNo, pageSize);
		param.setFirstResult(firstResult);
		Page<EnterpriseUser> page = new Page<EnterpriseUser>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		Integer count = selectCount(param);
		page.setTotalCount(count);
		if (count == 0) {
			return page;
		}
		List<EnterpriseUser> userList = selectList(param);
		page.setData(userList);
		return page;
	}
	
	/**
	 * 分页查询企业用户列表
	 * @author Ganlin
	 * @date 2014年7月24日 下午2:02:36
	 * @return 
	 * @since 0.1
	 */
	@Override
	public Page<GetEnterpriseUserListResponse> selectEnterpriseUserListByPage(EnterpriseUserQuery param){
		Integer pageNo = param.getPageNo();
		Integer pageSize = param.getPageSize();
		if (pageNo == null || pageSize == null) {
			throw new IllegalArgumentException("pageNo or pageSize is null");
		}
		Integer firstResult = Page.getFirstResult(pageNo, pageSize);
		param.setFirstResult(firstResult);
		Page<GetEnterpriseUserListResponse> page = new Page<GetEnterpriseUserListResponse>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("com.cssweb.payment.account.vo.GetEnterpriseUserList.selectCount",param);
		page.setTotalCount(count);
		if (count == 0) {
			return page;
		}
		List<GetEnterpriseUserListResponse> list = getSqlMapClientTemplate().queryForList("com.cssweb.payment.account.vo.GetEnterpriseUserList.select",param);
		for(int i=0;i<list.size();i++){
			// 企业认证状态对应中文
			String authStatus = list.get(i).getAuthStatus();
			if(authStatus!=null){
				String authStatusName = DictionaryUtils.getDisplayName(DictionaryKey.AUTH_STATUS, authStatus);
				list.get(i).setAuthStatusName(authStatusName);
			}
			//用户来源对应中文
			String source = list.get(i).getSource();
			if(source!=null){
				String sourceName = DictionaryUtils.getDisplayName(DictionaryKey.SOURCE, source);
				list.get(i).setSourceName(sourceName);
			}
			//用户状态对应中文
			String status = list.get(i).getUserStatus();
			if(status!=null){
				String statusName = DictionaryUtils.getDisplayName(DictionaryKey.USER_STATUS, status);
				list.get(i).setUserStatusName(statusName);
			}
			//用户类型对应中文
			String userType = list.get(i).getUserType();
			if(userType!=null){
				String userTypeName = DictionaryUtils.getDisplayName(DictionaryKey.USER_TYPE, userType);
				list.get(i).setUserTypeName(userTypeName);
			}
			//证件类型对应中文
			String idType = list.get(i).getIdType();
			if(idType!=null){
				String idTypeName = DictionaryUtils.getDisplayName(DictionaryKey.ID_TYPE, idType);
				list.get(i).setIdTypeName(idTypeName);
			}
			//是否签约商户
			String isMerchant = list.get(i).getIsMerchant();
			if(isMerchant != null){
				String isMerchantName = DictionaryUtils.getDisplayName(DictionaryKey.BUSINESS_STATUS, isMerchant);
				list.get(i).setIsMerchantName(isMerchantName);
			}
			//国家对应中文
			String countryCode = list.get(i).getCountryCode();
			if(countryCode !=null){
				String countryName = DictionaryUtils.getDisplayName(DictionaryKey.COUNTRY_CODE, countryCode);
				list.get(i).setCountryName(countryName);
			}
		}
		page.setData(list);
		return page;
	}
	/**
	 * 更新手机注册用户信息
	 * @author liLei
	 * @date 2014年7月23日 下午4:18:02
	 * @param param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public void updateUnbindingMobile(Long userId) {
		getSqlMapClientTemplate().update(CLASS_NAME + ".updateUnbindingMobile", userId);
	}
	/**
	 * 更新邮箱注册用户信息
	 * @author liLei
	 * @date 2014年7月23日 下午4:18:02
	 * @param param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public void updateUnbindingEmail(Long userId) {
		getSqlMapClientTemplate().update(CLASS_NAME + ".updateUnbindingEmail", userId);
		
	}
	/**
	 * 撤销实名认证，更新认证状态
	 * @author zhanwx
	 * @date 2014年8月20日 下午2:28:18
	 * @param userId 
	 * @since 0.1
	 * @see
	 */
	@Override
	public void updateAuth(EnterpriseUser param){
		getSqlMapClientTemplate().update(CLASS_NAME + ".updateAuth",param);
	}
	/**
	 * 查询企业用户信息列表
	 * @author wangpeng
	 * @date 2014年9月29日 上午 9：34：20
	 * @since 0.1
	 */
	@Override
	public List<UserInfo> selectEnterpriseInfoList(EnterpriseUserQuery param){
		List<UserInfo> list =  getSqlMapClientTemplate().queryForList(CLASS_NAME + ".selectEnterpriseInfoList",param);
		if(list.size() == 0){
			return null;
		}
		for(int i=0;i<list.size();i++){
			//用户类型对应中文
			String userType = list.get(i).getUsertype();
			if(userType!=null){
				String userTypeName = DictionaryUtils.getDisplayName(DictionaryKey.USER_TYPE, userType);
				list.get(i).setUsertypeName(userTypeName);
			}
			//用户状态对应中文
			String userStatus = list.get(i).getUserStatus();
			if(userStatus!=null){
				String userStatusName = DictionaryUtils.getDisplayName(DictionaryKey.USER_STATUS, userStatus);
				list.get(i).setUserStatusName(userStatusName);
			}
			//账户状态对应中文
			String status = list.get(i).getAccountStatus();
			if(status!=null){
				String statusName = DictionaryUtils.getDisplayName(DictionaryKey.ACCOUNT_STATUS, status);
				list.get(i).setAccountStatus(statusName);
			}
		}
		return list;
	}
	
}
