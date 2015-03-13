/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cssweb.payment.account.constant.DictionaryKey;
import com.cssweb.payment.account.dao.IManageCommonDao;
import com.cssweb.payment.account.util.DictionaryUtils;
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
import com.cssweb.payment.common.ibatis.BaseDao;
import com.cssweb.payment.common.util.Page;

/**
 * 运营管理平台复杂查询的dao实现类
 * @author Ganlin
 * @version 0.1 (2014年7月26日 下午3:17:34)
 * @since 0.1
 * @see
 */
@Repository
public class ManageCommonDaoImpl extends BaseDao<CommercialTenantAccount> implements IManageCommonDao {

	/** 
	 * @author Ganlin
	 * @date 2014年7月26日 下午3:17:34
	 * @param param
	 * @return 
	 * @since 0.1
	 */
	@Override
	public List<CommercialTenantAccount> selectList(CommercialTenantAccountQuery param) {
		return getSqlMapClientTemplate().queryForList("com.cssweb.payment.account.vo.CommercialTenantAccount" + ".select",param);
	}


	/**
	 * 关联查询账户表、企业用户表、商户表、账户操作表、账户资金变化明细表
	 * @author Ganlin
	 * @date 2014年7月26日 下午5:55:16
	 * @param param
	 * @return 
	 * @since 0.1
	 */
	@Override
	public List<CommercialTenantAccountDetailed> selectList(CommercialTenantAccountDetailedQuery param) {
		return getSqlMapClientTemplate().queryForList("com.cssweb.payment.account.vo.CommercialTenantAccountDetailed" + ".select", param);
	}
	
	/**
	 * 分页关联查询账户表、企业用户表、商户表、账户操作表、账户资金变化明细表
	 * @author Ganlin
	 * @date 2014年7月26日 下午6:20:44
	 * @param param
	 * @return 
	 * @since 0.1
	 */
	public Page<CommercialTenantAccountDetailed> selectByPage(CommercialTenantAccountDetailedQuery param) {
		Integer pageNo = param.getPageNo();
		Integer pageSize = param.getPageSize();
		if (pageNo == null || pageSize == null) {
			throw new IllegalArgumentException("pageNo or pageSize is null");
		}
		Integer firstResult = Page.getFirstResult(pageNo, pageSize);
		param.setFirstResult(firstResult);
		Page<CommercialTenantAccountDetailed> page = new Page<CommercialTenantAccountDetailed>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		Integer count = selectCount(param);
		page.setTotalCount(count);
		if (count == 0) {
			return page;
		}
		List<CommercialTenantAccountDetailed> list = selectList(param);
		for(int i=0;i<list.size();i++){
			//交易类型对应中文
			String txnType = list.get(i).getTxnType();
			if(txnType!=null){
			          String txnTypeName = DictionaryUtils.getDisplayName(DictionaryKey.TXN_TYPE, txnType);
				list.get(i).setTxnTypeName(txnTypeName);
			}
			
			//货币类型对应中文
			String moneyType = list.get(i).getMoneyType();
			if(moneyType!=null){
				String moneyTypeName = DictionaryUtils.getDisplayName(DictionaryKey.CURRENCY_TYPE, moneyType);
				list.get(i).setMoneyTypeName(moneyTypeName);
			}
			
			//交易类型对应中文
			String transactionStatus = list.get(i).getTransactionStatus();
			if(transactionStatus!=null){
				String transactionStatusName = DictionaryUtils.getDisplayName(DictionaryKey.OPERATE_STATUS, transactionStatus);
				list.get(i).setTransactionStatusName(transactionStatusName);
			}
			
			//账户类型对应中文
			String accountType = list.get(i).getAccountType();
			if(accountType!=null){
				String accountTypeName = DictionaryUtils.getDisplayName(DictionaryKey.ACCOUNT_TYPE, accountType);
				list.get(i).setAccountTypeName(accountTypeName);
			}
		}
		page.setData(list);
		return page;
	}
	
	/**
	 * 分页关联查询账户表、企业用户表、商户表、账户操作表、账户资金变化明细表
	 * @author Ganlin
	 * @date 2014年7月26日 下午6:20:44
	 * @param param
	 * @return 
	 * @since 0.1
	 */
	public Page<CommercialTenantAccountDetailed> selectAll(CommercialTenantAccountDetailedQuery param) {
		Page<CommercialTenantAccountDetailed> page = new Page<CommercialTenantAccountDetailed>();
		page.setPageNo(-1);
		page.setPageSize(-1);
		List<CommercialTenantAccountDetailed> list = selectList(param);
		for(int i=0;i<list.size();i++){
			//交易类型对应中文
			String txnType = list.get(i).getTxnType();
			if(txnType!=null){
			          String txnTypeName = DictionaryUtils.getDisplayName(DictionaryKey.TXN_TYPE, txnType);
				list.get(i).setTxnTypeName(txnTypeName);
			}
			
			//货币类型对应中文
			String moneyType = list.get(i).getMoneyType();
			if(moneyType!=null){
				String moneyTypeName = DictionaryUtils.getDisplayName(DictionaryKey.CURRENCY_TYPE, moneyType);
				list.get(i).setMoneyTypeName(moneyTypeName);
			}
			
			//交易类型对应中文
			String transactionStatus = list.get(i).getTransactionStatus();
			if(transactionStatus!=null){
				String transactionStatusName = DictionaryUtils.getDisplayName(DictionaryKey.OPERATE_STATUS, transactionStatus);
				list.get(i).setTransactionStatusName(transactionStatusName);
			}
			
			//账户类型对应中文
			String accountType = list.get(i).getAccountType();
			if(accountType!=null){
				String accountTypeName = DictionaryUtils.getDisplayName(DictionaryKey.ACCOUNT_TYPE, accountType);
				list.get(i).setAccountTypeName(accountTypeName);
			}
		}
		page.setData(list);
		return page;
	}
	
	/**
	 * 关联查询账户表、合作银行信息表、账户操作表、账户资金变化明细表
	 * @author Ganlin
	 * @date 2014年7月27日 下午3:09:58
	 * @param param
	 * @return 
	 * @since 0.1
	 */
	public List<PaymentAccountDetailed> selectList(PaymentAccountDetailedQuery param) {
		return getSqlMapClientTemplate().queryForList("com.cssweb.payment.account.vo.PaymentAccountDetailed" + ".select", param); 
	}
	
	/**
	 * 分页关联查询账户表、合作银行信息表、账户操作表、账户资金变化明细表
	 * @author Ganlin
	 * @date 2014年7月27日 下午3:11:14
	 * @param param
	 * @return 
	 * @since 0.1
	 */
	public Page<PaymentAccountDetailed> selectByPage(PaymentAccountDetailedQuery param) {
		Integer pageNo = param.getPageNo();
		Integer pageSize = param.getPageSize();
		if (pageNo == null || pageSize == null) {
			throw new IllegalArgumentException("pageNo or pageSize is null");
		}
		Integer firstResult = Page.getFirstResult(pageNo, pageSize);
		param.setFirstResult(firstResult);
		Page<PaymentAccountDetailed> page = new Page<PaymentAccountDetailed>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		Integer count = selectCount(param);
		page.setTotalCount(count);
		if (count == 0) {
			return page;
		}
		List<PaymentAccountDetailed> list = selectList(param);
		for(int i=0;i<list.size();i++){
			//交易类型对应中文
			String txnType = list.get(i).getTxnType();
			if(txnType!=null){
			    String txnTypeName = DictionaryUtils.getDisplayName(DictionaryKey.TXN_TYPE, txnType);
				list.get(i).setTxnTypeName(txnTypeName);
			}
			
			//合作类型对应中文
			String cooperationType = list.get(i).getCooperationType();
			if(cooperationType!=null){
				String cooperationTypeName = DictionaryUtils.getDisplayName(DictionaryKey.BANK_COOP,cooperationType);
				list.get(i).setCooperationTypeName(cooperationTypeName);
			}
			
			//货币种类对应中文
			String moneyType = list.get(i).getMoneyType();
			if(moneyType!=null){
				String moneyTypeName = DictionaryUtils.getDisplayName(DictionaryKey.CURRENCY_TYPE,moneyType);
				list.get(i).setMoneyTypeName(moneyTypeName);
			}
			
			//账号的操作状态对应中文
			String transactionStatus = list.get(i).getTransactionStatus();
			if(transactionStatus!=null){
				String transactionStatusName = DictionaryUtils.getDisplayName(DictionaryKey.OPERATE_STATUS,transactionStatus);
				list.get(i).setTransactionStatusName(transactionStatusName);
			}
		}
		page.setData(list);
		return page;
	}

	/** 
	 * @author Ganlin
	 * @date 2014年7月27日 下午4:57:55
	 * @param param
	 * @return 
	 * @since 0.1
	 */
	@Override
	public int selectCount(CommercialTenantAccount param) {
		return (int) getSqlMapClientTemplate().queryForObject("com.cssweb.payment.account.vo.CommercialTenantAccount" + ".selectCount", param);
	}

	/** 
	 * @author Ganlin
	 * @date 2014年7月27日 下午4:57:55
	 * @param param
	 * @return 
	 * @since 0.1
	 */
	@Override
	public int selectCount(CommercialTenantAccountDetailedQuery param) {
		return (int) getSqlMapClientTemplate().queryForObject("com.cssweb.payment.account.vo.CommercialTenantAccountDetailed" + ".selectCount", param);
	}

	/** 
	 * @author Ganlin
	 * @date 2014年7月27日 下午4:57:55
	 * @param param
	 * @return 
	 * @since 0.1
	 */
	@Override
	public int selectCount(PaymentAccountDetailedQuery param) {
		return (int) getSqlMapClientTemplate().queryForObject("com.cssweb.payment.account.vo.PaymentAccountDetailed" + ".selectCount", param);
	}


	/** 
	 * @author Ganlin
	 * @date 2014年7月27日 下午5:49:57
	 * @param param
	 * @return 
	 * @since 0.1
	 */
	@Override
	public int selectCount(PaymentAccountQuery param) {
		return (int) getSqlMapClientTemplate().queryForObject("com.cssweb.payment.account.vo.PaymentAccount" + ".selectCount", param);
	}


	/** 
	 * @author Ganlin
	 * @date 2014年7月27日 下午5:49:57
	 * @param param
	 * @return 
	 * @since 0.1
	 */
	@Override
	public List<PaymentAccount> selectList(PaymentAccountQuery param) {
		return getSqlMapClientTemplate().queryForList("com.cssweb.payment.account.vo.PaymentAccount" + ".select", param);
	}


	/** 
	 * @author Ganlin
	 * @date 2014年7月27日 下午5:49:57
	 * @param param
	 * @return 
	 * @since 0.1
	 */
	@Override
	public Page<PaymentAccount> selectByPage(PaymentAccountQuery param) {
		Integer pageNo = param.getPageNo();
		Integer pageSize = param.getPageSize();
		if (pageNo == null || pageSize == null) {
			throw new IllegalArgumentException("pageNo or pageSize is null");
		}
		Integer firstResult = Page.getFirstResult(pageNo, pageSize);
		param.setFirstResult(firstResult);
		Page<PaymentAccount> page = new Page<PaymentAccount>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		Integer count = selectCount(param);
		page.setTotalCount(count);
		if (count == 0) {
			return page;
		}
		List<PaymentAccount> list = selectList(param);
		for(int i=0;i<list.size();i++){
			//账户状态对应中文
			String statusName = list.get(i).getAccountStatus();
			if(statusName!=null){
				String accountStatusName = DictionaryUtils.getDisplayName(DictionaryKey.ACCOUNT_STATUS, statusName);
				list.get(i).setAccountStatusName(accountStatusName);
			}
			//账户类型对应中文
			String typeName = list.get(i).getAccountType();
			if(typeName!=null){
				String accountTypeName = DictionaryUtils.getDisplayName(DictionaryKey.ACCOUNT_TYPE, typeName);
				list.get(i).setAccountTypeName(accountTypeName);
			}
			//银行合作类型对应中文
			String cooperationType = list.get(i).getCooperationType();
			if(cooperationType!=null){
				String cooperationTypeName = DictionaryUtils.getDisplayName(DictionaryKey.BANK_COOP, cooperationType);
				list.get(i).setCooperationTypeName(cooperationTypeName);
			}
			//币种对应中文
			String moneyType = list.get(i).getMoneyType();
			if(moneyType!=null){
				String moneyTypeName = DictionaryUtils.getDisplayName(DictionaryKey.CURRENCY_TYPE, moneyType);
				list.get(i).setMoneyTypeName(moneyTypeName);
			}
			// 用户类型对应中文
			String userType = list.get(i).getUserType();
			if(userType!=null){
				String userTypeName = DictionaryUtils.getDisplayName(DictionaryKey.USER_TYPE, userType);
				list.get(i).setUserTypeName(userTypeName);
			}
		}
		page.setData(list);
		return page;
	}


	/** 
	 * @author Ganlin
	 * @date 2014年7月27日 下午6:27:58
	 * @param param
	 * @return 
	 * @since 0.1
	 */
	@Override
	public Page<CommercialTenantAccount> selectByPage(
			CommercialTenantAccountQuery param) {
		Integer pageNo = param.getPageNo();
		Integer pageSize = param.getPageSize();
		if (pageNo == null || pageSize == null) {
			throw new IllegalArgumentException("pageNo or pageSize is null");
		}
		Integer firstResult = Page.getFirstResult(pageNo, pageSize);
		param.setFirstResult(firstResult);
		Page<CommercialTenantAccount> page = new Page<CommercialTenantAccount>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		Integer count = selectCount(param);
		page.setTotalCount(count);
		if (count == 0) {
			return page;
		}
		List<CommercialTenantAccount> list = selectList(param);
		for(int i=0;i<list.size();i++){
			//账户状态对应中文
			String accountStatus = list.get(i).getAccountStatus();
			if(accountStatus!=null){
				String accountStatusName = DictionaryUtils.getDisplayName(DictionaryKey.ACCOUNT_STATUS, accountStatus);
				list.get(i).setAccountStatusName(accountStatusName);
			}
			//账户类型对应中文
			String accountType = list.get(i).getAccountType();
			if(accountType!=null){
				String accountTypeName = DictionaryUtils.getDisplayName(DictionaryKey.ACCOUNT_TYPE, accountType);
				list.get(i).setAccountTypeName(accountTypeName);
			}
			//币种类型对应中文
			String moneyType = list.get(i).getMoneyType();
			if(moneyType!=null){
				String moneyTypeName = DictionaryUtils.getDisplayName(DictionaryKey.CURRENCY_TYPE, moneyType);
				list.get(i).setMoneyTypeName(moneyTypeName);
			}
			//用户类型
			String userType = list.get(i).getUserType();
			if(userType!=null){
				String userTypeName = DictionaryUtils.getDisplayName(DictionaryKey.USER_TYPE, userType);
				list.get(i).setUserTypeName(userTypeName);
			}
		}
		page.setData(list);
		return page;
	}


	/** 
	 * @author Ganlin
	 * @date 2014年7月27日 下午7:40:35
	 * @param param
	 * @return 
	 * @since 0.1
	 */
	@Override
	public int selectCount(UserAccountQuery param) {
		return (int) getSqlMapClientTemplate().queryForObject("com.cssweb.payment.account.vo.UserAccount" + ".selectCount", param);
	}


	/** 
	 * @author Ganlin
	 * @date 2014年7月27日 下午7:40:35
	 * @param param
	 * @return 
	 * @since 0.1
	 */
	@Override
	public List<UserAccount> selectList(UserAccountQuery param) {
		return getSqlMapClientTemplate().queryForList("com.cssweb.payment.account.vo.UserAccount" + ".select", param);

	}


	/** 
	 * @author Ganlin
	 * @date 2014年7月27日 下午7:40:35
	 * @param param
	 * @return 
	 * @since 0.1
	 */
	@Override
	public Page<UserAccount> selectByPage(UserAccountQuery param) {
		Integer pageNo = param.getPageNo();
		Integer pageSize = param.getPageSize();
		if (pageNo == null || pageSize == null) {
			throw new IllegalArgumentException("pageNo or pageSize is null");
		}
		Integer firstResult = Page.getFirstResult(pageNo, pageSize);
		param.setFirstResult(firstResult);
		Page<UserAccount> page = new Page<UserAccount>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		Integer count = selectCount(param);
		page.setTotalCount(count);
		if (count == 0) {
			return page;
		}
		List<UserAccount> list = selectList(param);
		for(int i=0;i<list.size();i++){
			//账户状态对应中文
			String accountStatus = list.get(i).getAccountStatus();
			if(accountStatus!=null){
				String accountStatusName = DictionaryUtils.getDisplayName(DictionaryKey.ACCOUNT_STATUS, accountStatus);
				list.get(i).setAccountStatusName(accountStatusName);
			}
			//账户类型对应中文
			String accountType = list.get(i).getAccountType();
			if(accountType!=null){
				String accountTypeName = DictionaryUtils.getDisplayName(DictionaryKey.ACCOUNT_TYPE, accountType);
				list.get(i).setAccountTypeName(accountTypeName);
			}
			//币种对应中文
			String moneyType = list.get(i).getMoneyType();
			if(moneyType!=null){
				String moneyTypeName = DictionaryUtils.getDisplayName(DictionaryKey.CURRENCY_TYPE, moneyType);
				list.get(i).setMoneyTypeName(moneyTypeName);
			}
			//用户类型对应中文
			String userType = list.get(i).getUserType();
			if(userType!=null){
				String userTypeName = DictionaryUtils.getDisplayName(DictionaryKey.USER_TYPE, userType);
				list.get(i).setUserTypeName(userTypeName);
			}
		}
		page.setData(list);
		return page;
	}


	/** 
	 * @author Ganlin
	 * @date 2014年7月28日 上午10:22:03
	 * @param param
	 * @return 
	 * @since 0.1
	 */
	@Override
	public int selectCount(AccountDetailedQuery param) {
		return (int) getSqlMapClientTemplate().queryForObject("com.cssweb.payment.account.vo.AccountDetailed" + ".selectCount", param);
	}


	/** 
	 * @author Ganlin
	 * @date 2014年7月28日 上午10:22:03
	 * @param param
	 * @return 
	 * @since 0.1
	 */
	@Override
	public List<AccountDetailed> selectList(AccountDetailedQuery param) {
		return getSqlMapClientTemplate().queryForList("com.cssweb.payment.account.vo.AccountDetailed" + ".select", param);
	}


	/** 
	 * @author Ganlin
	 * @date 2014年7月28日 上午10:22:03
	 * @param param
	 * @return 
	 * @since 0.1
	 */
	@Override
	public Page<AccountDetailed> selectByPage(AccountDetailedQuery param) {
		Integer pageNo = param.getPageNo();
		Integer pageSize = param.getPageSize();
		if (pageNo == null || pageSize == null) {
			throw new IllegalArgumentException("pageNo or pageSize is null");
		}
		Integer firstResult = Page.getFirstResult(pageNo, pageSize);
		param.setFirstResult(firstResult);
		Page<AccountDetailed> page = new Page<AccountDetailed>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		Integer count = selectCount(param);
		page.setTotalCount(count);
		if (count == 0) {
			return page;
		}
		List<AccountDetailed> list = selectList(param);
		for(int i=0;i<list.size();i++){
			//交易类型对应中文
			String txnType = list.get(i).getTxnType();
			if(txnType!=null){
				String txnTypeName = DictionaryUtils.getDisplayName(DictionaryKey.TXN_TYPE, txnType);
				list.get(i).setTxnTypeName(txnTypeName);
			}
			
			//账户状态对应中文
			String accountStatus = list.get(i).getAccountStatus();
			if(accountStatus!=null){
				String accountStatusName = DictionaryUtils.getDisplayName(DictionaryKey.ACCOUNT_STATUS, accountStatus);
				list.get(i).setAccountStatusName(accountStatusName);
			}
			
			//账户类型对应中文
			String accountType = list.get(i).getAccountType();
			if(accountType!=null){
				String accountTypeName = DictionaryUtils.getDisplayName(DictionaryKey.ACCOUNT_TYPE, accountType);
				list.get(i).setAccountTypeName(accountTypeName);
			}
			
			//币种类型对应中文
			String moneyType = list.get(i).getMoneyType();
			if(moneyType!=null){
				String moneyTypeName = DictionaryUtils.getDisplayName(DictionaryKey.CURRENCY_TYPE, moneyType);
				list.get(i).setMoneyTypeName(moneyTypeName);
			}
			
			//交易状态对应中文
			String transactionStatus = list.get(i).getTransactionStatus();
			if(transactionStatus!=null){
				String transactionStatusName = DictionaryUtils.getDisplayName(DictionaryKey.OPERATE_STATUS, transactionStatus);
				list.get(i).setTransactionStatusName(transactionStatusName);
			}
		}
		page.setData(list);
		return page;
	}
	
	/** 
	 * @author wangpeng
	 * @date 2014年8月29日 上午10:22:03
	 * @param param
	 * @return 
	 * @since 0.1
	 */
	@Override
	public Page<AccountDetailed> selectAll(AccountDetailedQuery param) {
		Page<AccountDetailed> page = new Page<AccountDetailed>();
		page.setPageNo(-1);
		page.setPageSize(-1);
		List<AccountDetailed> list = selectList(param);
		for(int i=0;i<list.size();i++){
			//交易类型对应中文
			String txnType = list.get(i).getTxnType();
			if(txnType!=null){
				String txnTypeName = DictionaryUtils.getDisplayName(DictionaryKey.TXN_TYPE, txnType);
				list.get(i).setTxnTypeName(txnTypeName);
			}
			
			//账户状态对应中文
			String accountStatus = list.get(i).getAccountStatus();
			if(accountStatus!=null){
				String accountStatusName = DictionaryUtils.getDisplayName(DictionaryKey.ACCOUNT_STATUS, accountStatus);
				list.get(i).setAccountStatusName(accountStatusName);
			}
			
			//账户类型对应中文
			String accountType = list.get(i).getAccountType();
			if(accountType!=null){
				String accountTypeName = DictionaryUtils.getDisplayName(DictionaryKey.ACCOUNT_TYPE, accountType);
				list.get(i).setAccountTypeName(accountTypeName);
			}
			
			//币种类型对应中文
			String moneyType = list.get(i).getMoneyType();
			if(moneyType!=null){
				String moneyTypeName = DictionaryUtils.getDisplayName(DictionaryKey.CURRENCY_TYPE, moneyType);
				list.get(i).setMoneyTypeName(moneyTypeName);
			}
			
			//交易状态对应中文
			String transactionStatus = list.get(i).getTransactionStatus();
			if(transactionStatus!=null){
				String transactionStatusName = DictionaryUtils.getDisplayName(DictionaryKey.OPERATE_STATUS, transactionStatus);
				list.get(i).setTransactionStatusName(transactionStatusName);
			}
		}
		page.setData(list);
		return page;
	}
	
	
}
