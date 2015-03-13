package com.cssweb.payment.account.dao;

import java.util.List;

import com.cssweb.payment.account.pojo.EnterpriseUser;
import com.cssweb.payment.account.vo.EnterpriseUserQuery;
import com.cssweb.payment.account.vo.GetEnterpriseUserListResponse;
import com.cssweb.payment.account.vo.PersonalUserQuery;
import com.cssweb.payment.account.vo.UserInfo;
import com.cssweb.payment.common.util.Page;



/**
 * Title: IEnterpriseUserDao
 * Description: 
 * Company: China Software & Service Company
 * Project:payment-account
 * @author liLei
 * @version 1.0
 * @date 2014年7月23日 下午4:41:20
 * @see 
 */
public interface IEnterpriseUserDao {
	
	
	/**
	 * 插入记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:41:20
	 * @param param 
	 * @since 0.1
	 * @see
	 */
	public void insert(EnterpriseUser param);
	/**
	 * 更新记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:41:20
	 * @param param 
	 * @since 0.1
	 * @see
	 */
	public void update(EnterpriseUser param);
	/**
	 * 查询单条记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:41:20
	 * @param param 
	 * @since 0.1
	 * @see
	 */
	public EnterpriseUser selectOne(EnterpriseUserQuery param);
	/**
	 * 查询多条记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:41:20
	 * @param param 
	 * @since 0.1
	 * @see
	 */
	public List<EnterpriseUser> selectList(EnterpriseUserQuery param);
	/**
	 * 查询记录数
	 * @author liLei
	 * @date 2014年7月23日 下午4:41:20
	 * @param param 
	 * @since 0.1
	 * @see
	 */
	public int selectCount(EnterpriseUser param);
	/**
	 * 分页查询企业用户
	 * @author liLei
	 * @date 2014年7月23日 下午4:41:20
	 * @param param 
	 * @since 0.1
	 * @see
	 */
	public Page<EnterpriseUser> selectByPage(EnterpriseUserQuery param);
	/**
	 * 分页查询企业用户列表
	 * @author liLei
	 * @date 2014年7月23日 下午4:41:20
	 * @param param 
	 * @since 0.1
	 * @see
	 */
	public Page<GetEnterpriseUserListResponse> selectEnterpriseUserListByPage(EnterpriseUserQuery param);
	/**
	 * 更新未绑定的手机注册用户信息
	 * @author liLei
	 * @date 2014年7月23日 下午4:41:20
	 * @param userId 
	 * @since 0.1
	 * @see
	 */
	public void updateUnbindingMobile(Long userId);
	/**
	 * 更新未绑定的邮箱注册用户信息
	 * @author liLei
	 * @date 2014年7月23日 下午4:41:20
	 * @param param 
	 * @since 0.1
	 * @see
	 */
	public void updateUnbindingEmail(Long userId);
	/**
	 * 撤销实名认证，更新认证状态
	 * @author zhanwx
	 * @date 2014年8月20日 下午2:28:18
	 * @param userId 
	 * @since 0.1
	 * @see
	 */
	public void updateAuth(EnterpriseUser param);
	/**
	 * 查询企业用户信息列表
	 * @author wangpeng
	 * @date 2014年9月29日 上午 9：34：20
	 * @since 0.1
	 */
	public List<UserInfo> selectEnterpriseInfoList(EnterpriseUserQuery param);
	
}

