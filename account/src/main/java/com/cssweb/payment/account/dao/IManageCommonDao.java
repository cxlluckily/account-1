/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.dao;

import java.util.List;

import com.cssweb.payment.account.vo.AccountDetailed;
import com.cssweb.payment.account.vo.AccountDetailedQuery;
import com.cssweb.payment.account.vo.CommercialTenantAccount;
import com.cssweb.payment.account.vo.CommercialTenantAccountDetailed;
import com.cssweb.payment.account.vo.CommercialTenantAccountDetailedQuery;
import com.cssweb.payment.account.vo.CommercialTenantAccountQuery;
import com.cssweb.payment.account.vo.PaymentAccount;
import com.cssweb.payment.account.vo.PaymentAccountDetailed;
import com.cssweb.payment.account.vo.PaymentAccountDetailedQuery;
import com.cssweb.payment.account.vo.PaymentAccountQuery;
import com.cssweb.payment.account.vo.UserAccount;
import com.cssweb.payment.account.vo.UserAccountQuery;
import com.cssweb.payment.common.util.Page;


/**
 * @author Ganlin
 * @version 0.1 (2014年7月26日 下午3:14:15)
 * @since 0.1
 * @see
 */
public interface IManageCommonDao {
	
	/**
	 * 查询记录数
	 * @author Ganlin
	 * @date 2014年7月26日 下午3:14:15
	 * @param UserAccountQuery param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public int selectCount(UserAccountQuery param);
	/**
	 * 查询记录数
	 * @author Ganlin
	 * @date 2014年7月26日 下午3:14:15
	 * @param AccountDetailedQuery param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public int selectCount(AccountDetailedQuery param);
	/**
	 * 查询记录数
	 * @author Ganlin
	 * @date 2014年7月26日 下午3:14:15
	 * @param CommercialTenantAccount param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public int selectCount(CommercialTenantAccount param);
	/**
	 * 查询记录数
	 * @author Ganlin
	 * @date 2014年7月26日 下午3:14:15
	 * @param CommercialTenantAccountDetailedQuery param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public int selectCount(CommercialTenantAccountDetailedQuery param);
	/**
	 * 查询记录数
	 * @author Ganlin
	 * @date 2014年7月26日 下午3:14:15
	 * @param PaymentAccountDetailedQuery param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public int selectCount(PaymentAccountDetailedQuery param);
	/**
	 * 查询记录数
	 * @author Ganlin
	 * @date 2014年7月26日 下午3:14:15
	 * @param PaymentAccountQuery param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public int selectCount(PaymentAccountQuery param);

	
	/**
	 * 查询多条记录
	 * @author Ganlin
	 * @date 2014年7月26日 下午3:14:15
	 * @param UserAccountQuery param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public List<UserAccount> selectList(UserAccountQuery param);
	/**
	 * 查询多条记录
	 * @author Ganlin
	 * @date 2014年7月26日 下午3:14:15
	 * @param AccountDetailedQuery param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public List<AccountDetailed> selectList(AccountDetailedQuery param);
	/**
	 * 查询多条记录
	 * @author Ganlin
	 * @date 2014年7月26日 下午3:14:15
	 * @param CommercialTenantAccountQuery param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public List<CommercialTenantAccount> selectList(CommercialTenantAccountQuery param);
	/**
	 * 查询多条记录
	 * @author Ganlin
	 * @date 2014年7月26日 下午3:14:15
	 * @param CommercialTenantAccountDetailedQuery param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public List<CommercialTenantAccountDetailed> selectList(CommercialTenantAccountDetailedQuery param);
	/**
	 * 查询多条记录
	 * @author Ganlin
	 * @date 2014年7月26日 下午3:14:15
	 * @param PaymentAccountDetailedQuery param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public List<PaymentAccountDetailed> selectList(PaymentAccountDetailedQuery param);
	/**
	 * 查询多条记录
	 * @author Ganlin
	 * @date 2014年7月26日 下午3:14:15
	 * @param PaymentAccountQuery param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public List<PaymentAccount> selectList(PaymentAccountQuery param);

	
	/**
	 * 分页查询
	 * @author Ganlin
	 * @date 2014年7月26日 下午3:14:15
	 * @param UserAccountQuery param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public Page<UserAccount> selectByPage(UserAccountQuery param);
	/**
	 * 分页查询
	 * @author Ganlin
	 * @date 2014年7月26日 下午3:14:15
	 * @param AccountDetailedQuery param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public Page<AccountDetailed> selectByPage(AccountDetailedQuery param);
	/**
	 * 分页查询
	 * @author Ganlin
	 * @date 2014年7月26日 下午3:14:15
	 * @param CommercialTenantAccountQuery param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public Page<CommercialTenantAccount> selectByPage(CommercialTenantAccountQuery param);
	/**
	 * 分页查询
	 * @author Ganlin
	 * @date 2014年7月26日 下午3:14:15
	 * @param CommercialTenantAccountDetailedQuery param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public Page<CommercialTenantAccountDetailed> selectByPage(CommercialTenantAccountDetailedQuery param);
	/**
	 * 分页查询
	 * @author Ganlin
	 * @date 2014年7月26日 下午3:14:15
	 * @param PaymentAccountDetailedQuery param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public Page<PaymentAccountDetailed> selectByPage(PaymentAccountDetailedQuery param);
	/**
	 * 分页查询
	 * @author Ganlin
	 * @date 2014年7月26日 下午3:14:15
	 * @param PaymentAccountQuery param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public Page<PaymentAccount> selectByPage(PaymentAccountQuery param);
	
	public Page<AccountDetailed> selectAll(AccountDetailedQuery param);
	
	public Page<CommercialTenantAccountDetailed> selectAll(CommercialTenantAccountDetailedQuery param);

}
