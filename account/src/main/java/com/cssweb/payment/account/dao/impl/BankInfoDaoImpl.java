package com.cssweb.payment.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cssweb.payment.account.dao.IBankInfoDao;
import com.cssweb.payment.account.pojo.*;
import com.cssweb.payment.common.ibatis.BaseDao;

/**
 * Title: BankInfoDaoImpl
 * Description: 
 * Company: China Software & Service Company
 * Project:payment-account
 * @author liLei
 * @version 1.0
 * @date 2014年7月23日 下午4:16:34
 * @see 
 */
@Repository
public class BankInfoDaoImpl extends BaseDao<BankInfo> implements IBankInfoDao {

	public static final String CLASS_NAME = BankInfo.class.getName();
	
	/**
	 * 插入记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:16:34
	 * @param bankInfo
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public void insert(BankInfo bankInfo) {
		this.getSqlMapClientTemplate().insert(CLASS_NAME + ".insert", bankInfo);
		
	}

	/**
	 * 更新记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:16:34
	 * @param bankInfo
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public void update(BankInfo bankInfo) {
		this.getSqlMapClientTemplate().insert(CLASS_NAME + ".update", bankInfo);
		
	}

	/**
	 * 单记录查询
	 * @author liLei
	 * @date 2014年7月23日 下午4:16:34
	 * @param bankInfo
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public BankInfo selectOne(BankInfo bankInfo) {
		return (BankInfo) this.getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".select", bankInfo);
	}

	/**
	 * 多记录查询
	 * @author liLei
	 * @date 2014年7月23日 下午4:16:34
	 * @param bankInfo
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public List<BankInfo> selectList(BankInfo bankInfo) {
		return getSqlMapClientTemplate().queryForList(CLASS_NAME + ".select", bankInfo);
	}

	/**
	 * 记录总数查询
	 * @author liLei
	 * @date 2014年7月23日 下午4:16:34
	 * @param bankInfo
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public int selectCount(BankInfo bankInfo) {
		return (int) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".selectCount", bankInfo);
	}
	
	/**
	 * 根据平台与银行对应的账户ID列表进行多记录查询
	 * @author liLei
	 * @date 2014年7月23日 下午4:16:34
	 * @param bankInfo
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public List<BankInfo> selectByPaymentIdList(List<Long> paymentIdList) {
		return getSqlMapClientTemplate().queryForList(CLASS_NAME + ".selectByPaymentIdList", paymentIdList);
	}
	
	/**
	 * 按更新日期降序并查询第一条记录
	 * @author liLei
	 * @date 2014年7月23日 下午4:16:34
	 * @param bankInfo
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public BankInfo selectLimitOne(BankInfo bankInfo){
		return (BankInfo) this.getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".selectLimitOne", bankInfo);
	}

}
