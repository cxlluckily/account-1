package com.cssweb.payment.account.dao;

import java.util.List;

import com.cssweb.payment.account.pojo.BusinessInfo;



/**
 * Title: IBusinessInfoDao
 * Description: BusinessInfo类对应的数据表DAO
 * Company: China Software & Service Company
 * Project:payment-account
 * @author liLei
 * @version 1.0
 * @date 2014年7月23日 下午4:17:14
 * @see BusinessInfo
 */
public interface IBusinessInfoDao {
	
	/**
	 * 插入记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:17:22
	 * @param param
	 * @since 0.1
	 * @see
	 */
	public void insert(BusinessInfo param);
	/**
	 * 更新记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:17:22
	 * @param param
	 * @since 0.1
	 * @see
	 */
	public void update(BusinessInfo param);
	/**
	 * 查询单条记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:17:22
	 * @param param
	 * @since 0.1
	 * @see
	 */
	public BusinessInfo selectOne(BusinessInfo param);
	/**
	 * 查询多条记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:17:22
	 * @param param
	 * @since 0.1
	 * @see
	 */
	public List<BusinessInfo> selectList(BusinessInfo param);
	/**
	 * 查询记录数
	 * @author liLei
	 * @date 2014年7月23日 下午4:17:22
	 * @param param
	 * @since 0.1
	 * @see
	 */
	public int selectCount(BusinessInfo param);
	
	/**
	 * 删除单条记录
	 * @author wangpeng
	 * @date 2014年9月3号 下午12:37:40
	 * @param id
	 * @since 0.1
	 */
	public int delete(BusinessInfo param);
}

