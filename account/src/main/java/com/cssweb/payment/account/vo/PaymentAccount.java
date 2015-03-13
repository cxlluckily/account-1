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
public class PaymentAccount implements Serializable {

	private static final long serialVersionUID = 7725398132853680551L;
	
	private Long accountNumber;
	private Long paymentId;
	private String accountType;
	/**
	 * 账户类型对应中文
	 */
	private String accountTypeName;
	
	private String accountStatus;
	/**
	 * 账户状态对应中文
	 */
	private String accountStatusName;
	
	private String moneyType;
	/**
	 * 币种对应中文
	 */
	private String moneyTypeName;
	private BigDecimal availableMoney;
	private BigDecimal frozenMoney;
	private BigDecimal nomentionMoney;
	private Date createDate;
	private Date modifyDate;
	private String userType;
	/**
	 * 用户类型对应中文
	 */
	private String userTypeName;
	private String cooperationType;
	/**
	 * 银行合作类型对应中文
	 */
	private String cooperationTypeName;
	
	public String getAccountTypeName() {
		return accountTypeName;
	}
	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}
	public String getAccountStatusName() {
		return accountStatusName;
	}
	public void setAccountStatusName(String accountStatusName) {
		this.accountStatusName = accountStatusName;
	}
	public String getMoneyTypeName() {
		return moneyTypeName;
	}
	public void setMoneyTypeName(String moneyTypeName) {
		this.moneyTypeName = moneyTypeName;
	}
	public String getUserTypeName() {
		return userTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	public String getCooperationTypeName() {
		return cooperationTypeName;
	}
	public void setCooperationTypeName(String cooperationTypeName) {
		this.cooperationTypeName = cooperationTypeName;
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
	 * @return accountType
	 */
	public String getAccountType() {
		return accountType;
	}
	/**
	 * @param accountType
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	/**
	 * @return accountStatus
	 */
	public String getAccountStatus() {
		return accountStatus;
	}
	/**
	 * @param accountStatus
	 */
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
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
	 * @return availableMoney
	 */
	public BigDecimal getAvailableMoney() {
		return availableMoney;
	}
	/**
	 * @param availableMoney
	 */
	public void setAvailableMoney(BigDecimal availableMoney) {
		this.availableMoney = availableMoney;
	}
	/**
	 * @return frozenMoney
	 */
	public BigDecimal getFrozenMoney() {
		return frozenMoney;
	}
	/**
	 * @param frozenMoney
	 */
	public void setFrozenMoney(BigDecimal frozenMoney) {
		this.frozenMoney = frozenMoney;
	}
	/**
	 * @return nomentionMoney
	 */
	public BigDecimal getNomentionMoney() {
		return nomentionMoney;
	}
	/**
	 * @param nomentionMoney
	 */
	public void setNomentionMoney(BigDecimal nomentionMoney) {
		this.nomentionMoney = nomentionMoney;
	}
	/**
	 * @return createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}
	/**
	 * @param modifyDate
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	/**
	 * @return userType
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * @param userType
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * @return cooperationType
	 */
	public String getCooperationType() {
		return cooperationType;
	}
	/**
	 * @param cooperationType
	 */
	public void setCooperationType(String cooperationType) {
		this.cooperationType = cooperationType;
	}
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

}
