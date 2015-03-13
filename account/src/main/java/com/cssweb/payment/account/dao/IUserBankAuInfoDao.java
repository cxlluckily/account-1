package com.cssweb.payment.account.dao;

import java.util.List;

import com.cssweb.payment.account.pojo.UserBankAuInfo;

/**
 * Title: IUserBankAuInfoDao
 * Description: 
 * Company: China Software & Service Company
 * Project:payment-account
 * @author liLei
 * @version 1.0
 * @date 2014年7月23日 下午4:46:42
 * @see 
 */
public interface IUserBankAuInfoDao {
	
	/**
	 * 记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:46:42
	 * @param param 
	 * @since 0.1
	 * @see
	 */
	public void insert(UserBankAuInfo param);
	/**
	 * 记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:46:42
	 * @param param 
	 * @since 0.1
	 * @see
	 */
	public void update(UserBankAuInfo param);
	
	/**
	 * Title: selectOne
	 * Description:
	 * @param param
	 * @return
	 */
	public UserBankAuInfo selectOne(UserBankAuInfo param);
	
	/**
	 * Title: selectList
	 * Description:
	 * @param param
	 * @return
	 */
	public List<UserBankAuInfo> selectList(UserBankAuInfo param);
	
	/**
	 * Title: selectCount
	 * Description:
	 * @param param
	 * @return
	 */
	public int selectCount(UserBankAuInfo param);

}
