package com.cssweb.payment.account.dao;

import java.util.List;

import com.cssweb.payment.account.pojo.RateInfo;


/**
 * 
 * @author zhanwx
 * @version 0.1 (2014年7月19日 上午11:25:51)
 * @since 0.1
 * @see
 */


public interface IRateInfoDao {
	
	/**
	 * 插入记录
	 * @author zhanwx
	 * @date 2014年7月19日 上午11:28:37
	 * @param rateInfo 
	 * @since 0.1
	 * @see
	 */
	public void insert(RateInfo rateInfo);
	
	/**
	 * 更新记录
	 * @author zhanwx
	 * @date 2014年7月19日 上午11:29:19
	 * @param rateInfo 
	 * @since 0.1
	 * @see
	 */
	public void update(RateInfo rateInfo);
	
	/**
	 * 查询单条记录
	 * @author zhanwx
	 * @date 2014年7月19日 上午11:29:43
	 * @param rateInfo
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public RateInfo selectOne(RateInfo rateInfo);
	
	/**
	 * 查询多条记录
	 * @author zhanwx
	 * @date 2014年7月19日 上午11:30:10
	 * @param rateInfo
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public List<RateInfo> selectList(RateInfo rateInfo);
	
	/**
	 * 查询记录条数
	 * @author zhanwx
	 * @date 2014年7月19日 上午11:30:45
	 * @param rateInfo
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public int selectCount(RateInfo rateInfo);
	

}
