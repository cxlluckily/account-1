/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.sf.json.JSONObject;

/**
 * 商户账户详细信息
 * @author Ganlin
 * @version 0.1 (2014年7月26日 下午4:16:06)
 * @since 0.1
 * @see
 */
public class CommercialTenantAccountDetailed implements Serializable{

	private static final long serialVersionUID = 1912907994767765038L;
	/**
	 * 主键id
	 */
	private Long accountNumber;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 商户类型
	 */
	private String cTType;
	/**
	 * 商户编号
	 */
	private Long cTNumber;
	/**
	 * 企业名称|银行开户名
	 */
	private String companyName;
	/**
	 * 币种代码
	 */
	private String moneyType;
	/**
	 * 货币类型对应中文
	 */
	private String moneyTypeName;
	/**
	 * 变化前金额 
	 */
	private BigDecimal money;
	/**
	 * 变动金额
	 */
	private BigDecimal changeMoney;
	/**
	 * 进出帐标志
	 */
	private Integer inOutMark;
	/**
	 * 交易类型
	 */
	private String txnType;
	/**
	 * 交易类型对应中文
	 */
	private String txnTypeName;
	/**
	 * 变化时间
	 */
	private Date changeDate;
	/**
	 * 账户变动描述
	 */
	private String accountChangeReason;
	/**
	 * 交易流水号
	 */
	private String transactionNumber;
	/**
	 * 外部系统订单号
	 */
	private String externalSystemNumber;
	/**
	 * 退款单号
	 */
	private String refundNumber;
	/**
	 * 交易状态
	 */
	private String transactionStatus;
	/**
	 * 交易状态对应中文
	 */
	private String transactionStatusName;
	
	/**
	 * 变动流水号
	 */
	private String accountSn;
	/**
	 * 账户类型
	 */
	private String accountType;
	/**
	 * 账户类型对应中文
	 */
	private String accountTypeName;
	/**
	 * 变动金额
	 */
	private String chgAccount;
	/**
	 * 手续费
	 */
	private BigDecimal fee;
	/**
	 * 账户变动描述
	 */
	private String memo;
	
	public String getTxnTypeName() {
		return txnTypeName;
	}
	public void setTxnTypeName(String txnTypeName) {
		this.txnTypeName = txnTypeName;
	}
	public String getAccountTypeName() {
		return accountTypeName;
	}
	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}
	public String getAccountSn() {
		return accountSn;
	}
	public void setAccountSn(String accountSn) {
		this.accountSn = accountSn;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getChgAccount() {
		return chgAccount;
	}
	public void setChgAccount(String chgAccount) {
		this.chgAccount = chgAccount;
	}
	
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getMoneyTypeName() {
		return moneyTypeName;
	}
	public void setMoneyTypeName(String moneyTypeName) {
		this.moneyTypeName = moneyTypeName;
	}
	public String getAccountChangeReason() {
		return accountChangeReason;
	}
	public void setAccountChangeReason(String accountChangeReason) {
		this.accountChangeReason = accountChangeReason;
	}
	public String getTransactionStatusName() {
		return transactionStatusName;
	}
	public void setTransactionStatusName(String transactionStatusName) {
		this.transactionStatusName = transactionStatusName;
	}
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getcTType() {
		return cTType;
	}
	
	public void setcTType(String cTType) {
		this.cTType = cTType;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getMoneyType() {
		return moneyType;
	}
	
	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}
	
	public BigDecimal getMoney() {
		return money;
	}
	
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	public BigDecimal getChangeMoney() {
		return changeMoney;
	}
	
	public void setChangeMoney(BigDecimal changeMoney) {
		this.changeMoney = changeMoney;
	}
	
	public Integer getInOutMark() {
		return inOutMark;
	}
	
	public void setInOutMark(Integer inOutMark) {
		this.inOutMark = inOutMark;
	}
	
	public String getTxnType() {
		return txnType;
	}
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	public Date getChangeDate() {
		return changeDate;
	}
	
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	
	public String getAccountChangeReasoan() {
		return accountChangeReason;
	}
	
	public void setAccountChangeReasoan(String accountChangeReasoan) {
		this.accountChangeReason = accountChangeReasoan;
	}
	
	public String getTransactionNumber() {
		return transactionNumber;
	}
	
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	
	public String getExternalSystemNumber() {
		return externalSystemNumber;
	}
	
	public void setExternalSystemNumber(String externalSystemNumber) {
		this.externalSystemNumber = externalSystemNumber;
	}
	
	public String getRefundNumber() {
		return refundNumber;
	}
	
	public void setRefundNumber(String refundNumber) {
		this.refundNumber = refundNumber;
	}
	
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getcTNumber() {
		return cTNumber;
	}
	public void setcTNumber(Long cTNumber) {
		this.cTNumber = cTNumber;
	}
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
	
	
}
