package com.cssweb.payment.account.dao;

import java.util.List;

import com.cssweb.payment.account.pojo.AccountInfo;
import com.cssweb.payment.account.vo.AccountInfoQuery;
import com.cssweb.payment.account.vo.PersonalUserQuery;
import com.cssweb.payment.common.util.Page;


/**
 * 账户信息表接口
 * @author Lilei 
 * @version V1.0
 * @since 2014-07-19
 *
 */
public interface IAccountInfoDao {
	
	/**
	 * 插入记录
	 * @author Lilei
	 * @date 2014年8月4日 下午4:17:00
	 * @param param 
	 * @since 0.1
	 * @see
	 */
	public void insert(AccountInfo param);
	
	/**
	 * 更新记录
	 * @author Lilei
	 * @date 2014年8月4日 下午4:18:16
	 * @param param 
	 * @since 0.1
	 * @see
	 */
	public void update(AccountInfo param);
	
	/**
	 * 通过页数查询记录
	 * @author zhaoxingwen
	 * @date 2014年8月4日 下午4:18:57
	 * @param param
	 * @since 0.1
	 * @see
	 */
	public Page<AccountInfo> selectByPage(AccountInfoQuery param);
	/**
	 * 查询记录数
	 * @author zhaoxingwen
	 * @date 2014年8月4日 下午4:18:57
	 * @param param
	 * @since 0.1
	 * @see
	 */
	public int selectCount(AccountInfoQuery param);
	/**
	 * 单条记录查询更新
	 * @author wangpeng
	 * @date 2014年9月15日 下午16:43:50
	 * @param param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public AccountInfo selectOneForUpdate(AccountInfoQuery param);
	/**
	 * 查询多条记录
	 * @author zhaoxingwen
	 * @date 2014年8月4日 下午4:18:57
	 * @param param
	 * @since 0.1
	 * @see
	 */
	public List<AccountInfo> selectList(AccountInfoQuery param);
	/**
	 * 查询单条记录
	 * @author zhaoxingwen
	 * @date 2014年8月4日 下午4:18:57
	 * @param param
	 * @since 0.1
	 * @see
	 */
	public AccountInfo selectOne(AccountInfoQuery param);
	/**
	 * 通过时间范围查询记录
	 * @author zhaoxingwen
	 * @date 2014年8月4日 下午4:18:57
	 * @param param
	 * @since 0.1
	 * @see
	 */
	public Page<AccountInfo> selectBetweenDate(AccountInfoQuery param);
	
	/**
	 * 查询单条记录
	 * @author zhaoxingwen
	 * @date 2014年8月21
	 * @param param
	 * @since 0.1
	 * @see
	 */
	public AccountInfo selectAccByAcc(AccountInfoQuery param);
	
	/**
	 * 清除密码错误登陆次数
	 * @author wangpeng
	 * @date 2014年11月3日 12:31:31
	 * 
	 */
	public void cleanErrorLoginTimes();
}

