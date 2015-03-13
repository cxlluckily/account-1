/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.dao;

import java.util.List;

import com.cssweb.payment.account.pojo.UserBandBankInfo;
import com.cssweb.payment.account.vo.AppGetUserBindingBankListPara;

/**
 * @author zhanwx
 * @version 0.1 (2014年7月19日 下午5:21:21)
 * @since 0.1
 * @see
 */
public interface IUserBandBankInfoDao {

	/**
	 * 插入记录
	 * @author zhanwx
	 * @date 2014年7月19日 上午11:28:37
	 * @param ubb 
	 * @since 0.1
	 * @see
	 */
	public void insert(UserBandBankInfo ubb);
	
	/**
	 * 更新记录
	 * @author zhanwx
	 * @date 2014年7月19日 上午11:29:19
	 * @param ubb
	 * @since 0.1
	 * @see
	 */
	public void update(UserBandBankInfo ubb);
	
	/**
	 * 删除记录
	 * @author zhanwx
	 * @date 2014年7月23日 下午4:23:25
	 * @param ubb 
	 * @since 0.1
	 * @see
	 */
	public void delete(UserBandBankInfo ubb);
	
	/**
	 * 查询单条记录
	 * @author zhanwx
	 * @date 2014年7月19日 上午11:29:43
	 * @param ubb
	 * @since 0.1
	 * @see
	 */
	public UserBandBankInfo  selectOne(UserBandBankInfo ubb);
	
	/**
	 * 查询多条记录
	 * @author zhanwx
	 * @date 2014年7月19日 上午11:30:10
	 * @param ubb
	 * @since 0.1
	 * @see
	 */
	public List<UserBandBankInfo> selectList(UserBandBankInfo ubb);
	
	/**
	 * 查询记录条数
	 * @author zhanwx
	 * @date 2014年7月19日 上午11:30:45
	 * @param rateInfo
	 * @since 0.1
	 * @see
	 */
	public int selectCount(UserBandBankInfo ubb);
	
	/**
	 * 更新记录,按accountid userid更新
	 * @author zhaoxingwen
	 * @date 2014年7月24日 上午16:29:19
	 * @param ubb
	 * @since 0.1
	 * @see
	 */
	public void updateByUseridAccountid(UserBandBankInfo ubb);
	
	/**
	 * 返回userbandbankinfo信息并附带bankName
	 * @author zhaoxingwen
	 * @date 2014年7月30日
	 * @param ubb
	 * @since 0.1
	 * @see
	 */
	public List<AppGetUserBindingBankListPara> selectBandCardFullInfo(UserBandBankInfo ubb);

}
