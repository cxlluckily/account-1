/**
 * 
 */
package com.cssweb.payment.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cssweb.payment.account.constant.DictionaryKey;
import com.cssweb.payment.account.dao.IPersonalUserDao;
import com.cssweb.payment.account.pojo.PersonalUser;
import com.cssweb.payment.account.util.DictionaryUtils;
import com.cssweb.payment.account.vo.GetPersonalUserListResponse;
import com.cssweb.payment.account.vo.PersonalUserQuery;
import com.cssweb.payment.account.vo.UserInfo;
import com.cssweb.payment.common.ibatis.BaseDao;
import com.cssweb.payment.common.util.Page;


/**
 * Title: PersonalUserDaoImpl
 * Description: PersonalUser类对应数据表DAO
 * Company: China Software & Service Company
 * Project:payment-account
 * @author liLei
 * @version 1.0
 * @date 2014年7月23日 下午4:44:25
 * @see PersonalUser
 */
@Repository
public class PersonalUserDaoImpl extends BaseDao<PersonalUser> implements
        IPersonalUserDao {
    
    public static final String CLASS_NAME = PersonalUser.class.getName();
    
    /**
     * 插入记录
     * @param param
     * @author liLei
     * @date 2014年7月23日 下午4:44:25
     * @since 0.1
     */
    @Override
    public void insert(PersonalUser param) {
        getSqlMapClientTemplate().insert(CLASS_NAME + ".insert",param);
    }

    /**
     * 更新记录
     * @param param
     * @author liLei
     * @date 2014年7月23日 下午4:44:25
     * @since 0.1
     */
    @Override
    public void update(PersonalUser param) {
        getSqlMapClientTemplate().insert(CLASS_NAME + ".update",param);
    }

    /**
     * 单记录查询
     * @param param
     * @author liLei
     * @date 2014年7月23日 下午4:44:25
     * @since 0.1
     */
    @Override
    public PersonalUser selectOne(PersonalUserQuery param) {
        return (PersonalUser) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".select",param);
    }

    /**
     * 多记录查询
     * @param param
     * @author liLei
     * @date 2014年7月23日 下午4:44:25
     * @since 0.1
     */
    @Override
    public List<PersonalUser> selectList(PersonalUserQuery param) {
        return getSqlMapClientTemplate().queryForList(CLASS_NAME + ".select",param);
    }

    /**
     * 记录总数查询
     * @param param
     * @author liLei
     * @date 2014年7月23日 下午4:44:25
     * @since 0.1
     */
    @Override
    public int selectCount(PersonalUserQuery param) {
        return (int) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".selectCount",param);
    }

	/** 
	 * 分页查询
	 * @author Ganlin
	 * @date 2014年7月22日 下午1:26:53
	 * @param param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public Page<PersonalUser> selectByPage(PersonalUserQuery param) {
		Integer pageNo = param.getPageNo();
		Integer pageSize = param.getPageSize();
		if (pageNo == null || pageSize == null) {
			throw new IllegalArgumentException("pageNo or pageSize is null");
		}
		Integer firstResult = Page.getFirstResult(pageNo, pageSize);
		param.setFirstResult(firstResult);
		Page<PersonalUser> page = new Page<PersonalUser>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		Integer count = selectCount(param);
		page.setTotalCount(count);
		if (count == 0) {
			return page;
		}
		List<PersonalUser> userList = selectList(param);
		page.setData(userList);
		return page;
	}
	
	/**
	 * 分页查询个人客户信息列表
	 * @author wangpeng
	 * @date 2014年8月7日 下午1:26:53
	 * @param param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public Page<GetPersonalUserListResponse> selectPersonalUserListByPage(PersonalUserQuery param){
		Integer pageNo = param.getPageNo();
		Integer pageSize = param.getPageSize();
		if (pageNo == null || pageSize == null) {
			throw new IllegalArgumentException("pageNo or pageSize is null");
		}
		Integer firstResult = Page.getFirstResult(pageNo, pageSize);
		param.setFirstResult(firstResult);
		Page<GetPersonalUserListResponse> page = new Page<GetPersonalUserListResponse>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("com.cssweb.payment.account.vo.GetPersonalUserList.selectCount",param);
		page.setTotalCount(count);
		if (count == 0) {
			return page;
		}
		List<GetPersonalUserListResponse> userList = getSqlMapClientTemplate().queryForList("com.cssweb.payment.account.vo.GetPersonalUserList.select",param);
		for(int i=0;i<userList.size();i++){
			//用户类型对应中文
			String userType = userList.get(i).getUserType();
			if(userType!=null){
				String userTypeName = DictionaryUtils.getDisplayName(DictionaryKey.USER_TYPE, userType);
				userList.get(i).setUserTypeName(userTypeName);
			}
			//证件类型对应中文
			String authCertType = userList.get(i).getAuthCertType();
			if(authCertType!=null){
				String authCertTypeName = DictionaryUtils.getDisplayName(DictionaryKey.ID_TYPE, authCertType);
				userList.get(i).setAuthCertTypeName(authCertTypeName);
			}
			//用户状态对应中文
			String userStatus = userList.get(i).getUserStatus();
			if(userStatus!=null){
				String userStatusName = DictionaryUtils.getDisplayName(DictionaryKey.USER_STATUS, userStatus);
				userList.get(i).setUserStatusName(userStatusName);
			}
			//实名认证状态对应中文
			String authStatus = userList.get(i).getAuthStatus();
			if(authStatus!=null){
				String authStatusName = DictionaryUtils.getDisplayName(DictionaryKey.AUTH_STATUS, authStatus);
				userList.get(i).setAuthStatusName(authStatusName);
			}
			//用户来源对应中文
			String source = userList.get(i).getSource();
			if(source!=null){
				String sourceName = DictionaryUtils.getDisplayName(DictionaryKey.SOURCE, source);
				userList.get(i).setSourceName(sourceName);
			}
		}
		page.setData(userList);
		return page;
	}

	/** 
	 * 通过邮箱查询用户
	 * @author ZhaoXingwen
	 * @date 2014年7月23日 下午1:26:53
	 * @param param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public PersonalUser selectByEmailPhone(PersonalUser parm){
		return (PersonalUser)getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".selectByEmailPhone",parm);
	}
	
	/** 
	 * 待定
	 * @author liLei
	 * @date 2014年7月23日 下午1:26:53
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
	 * 待定
	 * @author liLei
	 * @date 2014年7月23日 下午1:26:53
	 * @param param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public void updateUnbindingEmail(Long userId) {
		getSqlMapClientTemplate().update(CLASS_NAME + ".updateUnbindingEmail", userId);
		
	}
	@Override
	public void updateAuth(PersonalUser param){
		getSqlMapClientTemplate().update(CLASS_NAME + ".updateAuth",param);
	}
	@Override
	public void updateAuthDate(PersonalUser param){
		getSqlMapClientTemplate().update(CLASS_NAME + ".updateAuthDate",param);
	}
	
	/**
	 * 查询个人用户信息列表
	 * @author wangpeng
	 * @date 2014年9月29日 上午 9：34：20
	 * @since 0.1
	 */
	@Override
	public List<UserInfo> selectPersonalInfoList(PersonalUserQuery param){
		List<UserInfo> list = getSqlMapClientTemplate().queryForList(CLASS_NAME + ".selectPersonalInfoList",param);
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
