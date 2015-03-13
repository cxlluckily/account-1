package com.cssweb.payment.account.dao;

import java.util.List;

import com.cssweb.payment.account.pojo.PersonalUser;
import com.cssweb.payment.account.vo.GetPersonalUserListResponse;
import com.cssweb.payment.account.vo.PersonalUserQuery;
import com.cssweb.payment.account.vo.UserInfo;
import com.cssweb.payment.common.util.Page;


/**
 * 
 * @author Lilei 
 * @version V1.0
 * @since 2014-07-19
 *
 */
public interface IPersonalUserDao {
	/**
	 * 插入记录
	 * @author Ganlin
	 * @date 2014年7月22日 下午1:53:32
	 * @param PersonalUser param
	 * @return  
	 * @since 0.1
	 */
	public void insert(PersonalUser param);
	/**
	 * 更新记录
	 * @author Ganlin
	 * @date 2014年7月22日 下午1:53:32
	 * @param PersonalUser param
	 * @return  
	 * @since 0.1
	 */
	public void update(PersonalUser param);
	/**
	 * 查询单条记录
	 * @author Ganlin
	 * @date 2014年7月22日 下午1:53:32
	 * @param PersonalUserQuery param
	 * @return  
	 * @since 0.1
	 */
	public PersonalUser selectOne(PersonalUserQuery param);
	/**
	 * 查询多条记录
	 * @author Ganlin
	 * @date 2014年7月22日 下午1:53:32
	 * @param PersonalUserQuery param
	 * @return  
	 * @since 0.1
	 */
	public List<PersonalUser> selectList(PersonalUserQuery param);
	/**
	 * 查询记录数
	 * @author Ganlin
	 * @date 2014年7月22日 下午1:53:32
	 * @param PersonalUserQuery param
	 * @return 
	 * @since 0.1
	 */
	public int selectCount(PersonalUserQuery param);
	
	/**
	 * 分页查询个人用户
	 * @author Ganlin
	 * @date 2014年7月22日 下午1:53:32
	 * @param PersonalUserQuery param
	 * @return  
	 * @since 0.1
	 */
	public Page<PersonalUser> selectByPage(PersonalUserQuery param);
	
	/**
	 * 分页查询个人用户
	 * @author Ganlin
	 * @date 2014年7月22日 下午1:53:32
	 * @param PersonalUserQuery param
	 * @return  分页器对象
	 * @since 0.1
	 */
	public Page<GetPersonalUserListResponse> selectPersonalUserListByPage(PersonalUserQuery param);

	/**
	 * 查询电话号码 和email是否存在
	 * @author ZhaoXingwen
	 * @date 2014年7月23日 下午1:53:32
	 * @param  param 个人用户查询对象
	 * @return 个人用户对象 或 null
	 * @since 0.1
	 */
	public PersonalUser selectByEmailPhone(PersonalUser parm);
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
	public void updateAuth(PersonalUser param);
	/**
	 * 认证申请时间超过15天作废
	 * @author wangpeng
	 * @date 2014年9月9日 下午5：42
	 * @since 0.1
	 * @see
	 */
	public void updateAuthDate(PersonalUser param);
	/**
	 * 查询个人用户信息列表
	 * @author wangpeng
	 * @date 2014年9月29日 上午 9：34：20
	 * @since 0.1
	 */
	public List<UserInfo> selectPersonalInfoList(PersonalUserQuery param);
}

