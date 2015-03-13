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
 * @author Ganlin
 * @version 0.1 (2014年7月27日 下午1:57:31)
 * @since 0.1
 * @see
 */
public class PaymentAccountDetailed implements Serializable {

	private static final long serialVersionUID = 8131282623547651178L;
	/**
	 * 主键id
	 */
	private Long accountNumber;
	/**
	 * 平台账户id
	 */
	private Long paymentId;
	/**
	 * 合作类型
	 */
	private String cooperationType;
	/**
	 * 合作类型对应中文
	 */
	private String cooperationTypeName;
	/**
	 * 币种代码
	 */
	private String moneyType;
	/**
	 * 货币种类对应中文
	 */
	private String moneyTypeName;
	/**
	 * 账户流水号
	 */
	private Long accountSn;
	/**
	 * 变化前金额 
	 */
	private BigDecimal money;
	/**
	 * 变化后余额
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
	 * 账号的操作状态
	 */
	private String transactionStatus;
	/**
	 * 账号的操作状态对应中文
	 */
	private String transactionStatusName;
	
	/**
	 * 变化金额
	 */
	private String chgAccount;
	/**
	 * 手续费
	 */
	private BigDecimal fee;
	
	
	public String getTxnTypeName() {
		return txnTypeName;
	}
	public void setTxnTypeName(String txnTypeName) {
		this.txnTypeName = txnTypeName;
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
	public String getMoneyTypeName() {
		return moneyTypeName;
	}
	public void setMoneyTypeName(String moneyTypeName) {
		this.moneyTypeName = moneyTypeName;
	}
	public String getTransactionStatusName() {
		return transactionStatusName;
	}
	public void setTransactionStatusName(String transactionStatusName) {
		this.transactionStatusName = transactionStatusName;
	}
	/**
	 * @return accountNumber
	 */
	public Long getAccountNumber() {
		return accountNumber;
	}
	/**
	 * @param accountNumber
	 */
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public String getCooperationType() {
		return cooperationType;
	}
	public void setCooperationType(String cooperationType) {
		this.cooperationType = cooperationType;
	}
	public String getCooperationTypeName() {
		return cooperationTypeName;
	}
	public void setCooperationTypeName(String cooperationTypeName) {
		this.cooperationTypeName = cooperationTypeName;
	}
	/**
	 * @return moneyType
	 */
	public String getMoneyType() {
		return moneyType;
	}
	/**
	 * @param moneyType
	 */
	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}
	/**
	 * @return money
	 */
	public BigDecimal getMoney() {
		return money;
	}
	/**
	 * @param money
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	/**
	 * @return changeMoney
	 */
	public BigDecimal getChangeMoney() {
		return changeMoney;
	}
	/**
	 * @param changeMoney
	 */
	public void setChangeMoney(BigDecimal changeMoney) {
		this.changeMoney = changeMoney;
	}
	/**
	 * @return inOutMark
	 */
	public Integer getInOutMark() {
		return inOutMark;
	}
	/**
	 * @param inOutMark
	 */
	public void setInOutMark(Integer inOutMark) {
		this.inOutMark = inOutMark;
	}
	/**
	 * @return changeReason
	 */
	/**
	 * @return changeDate
	 */
	public Date getChangeDate() {
		return changeDate;
	}
	public String getTxnType() {
		return txnType;
	}
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	/**
	 * @param changeDate
	 */
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	/**
	 * @return accountChangeReason
	 */
	public String getAccountChangeReason() {
		return accountChangeReason;
	}
	/**
	 * @param accountChangeReason
	 */
	public void setAccountChangeReason(String accountChangeReason) {
		this.accountChangeReason = accountChangeReason;
	}
	/**
	 * @return transactionNumber
	 */
	public String getTransactionNumber() {
		return transactionNumber;
	}
	/**
	 * @param transactionNumber
	 */
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	/**
	 * @return externalSystemNumber
	 */
	public String getExternalSystemNumber() {
		return externalSystemNumber;
	}
	/**
	 * @param externalSystemNumber
	 */
	public void setExternalSystemNumber(String externalSystemNumber) {
		this.externalSystemNumber = externalSystemNumber;
	}
	/**
	 * @return refundNumber
	 */
	public String getRefundNumber() {
		return refundNumber;
	}
	/**
	 * @param refundNumber
	 */
	public void setRefundNumber(String refundNumber) {
		this.refundNumber = refundNumber;
	}
	/**
	 * @return transactionStatus
	 */
	public String getTransactionStatus() {
		return transactionStatus;
	}
	/**
	 * @param transactionStatus
	 */
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	/**
	 * @return paymentId
	 */
	public Long getPaymentId() {
		return paymentId;
	}
	/**
	 * @param paymentId
	 */
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	/**
	 * @return accountSn
	 */
	public Long getAccountSn() {
		return accountSn;
	}
	/**
	 * @param accountSn
	 */
	public void setAccountSn(Long accountSn) {
		this.accountSn = accountSn;
	}

	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

}
