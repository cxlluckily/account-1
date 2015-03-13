package com.cssweb.payment.account.dao;

import java.util.List;

import com.cssweb.payment.account.pojo.BankInfo;


/**
 * Title: IBankInfoDao
 * Description: BankInfo类对应数据表的DAO
 * Company: China Software & Service Company
 * Project:payment-account
 * @author liLei
 * @version 1.0
 * @date 2014年7月23日 下午4:15:22
 * @see BankInfo
 */
public interface IBankInfoDao {
	
	/**
	 * 插入记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:15:22
	 * @param bankInfo 
	 * @since 0.1
	 * @see
	 */
	public void insert(BankInfo bankInfo);
	/**
	 * 更新记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:15:22
	 * @param bankInfo 
	 * @since 0.1
	 * @see
	 */
	public void update(BankInfo bankInfo);
	/**
	 * 查询单条记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:15:22
	 * @param bankInfo 
	 * @since 0.1
	 * @see
	 */
	public BankInfo selectOne(BankInfo bankInfo);
	/**
	 * 查询指定记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:15:22
	 * @param bankInfo 
	 * @since 0.1
	 * @see
	 */
	public BankInfo selectLimitOne(BankInfo bankInfo);
	/**
	 * 查询多条记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:15:22
	 * @param bankInfo 
	 * @since 0.1
	 * @see
	 */
	public List<BankInfo> selectList(BankInfo bankInfo);
	/**
	 * 查询记录数
	 * @author liLei
	 * @date 2014年7月23日 下午4:15:22
	 * @param bankInfo 
	 * @since 0.1
	 * @see
	 */
	public int selectCount(BankInfo bankInfo);
	/**
	 * 通过paymentId查询多条记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:15:22
	 * @param paymentIdList
	 * @since 0.1
	 * @see
	 */
	public List<BankInfo> selectByPaymentIdList(List<Long> paymentIdList);
	
}
