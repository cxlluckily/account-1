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
public class UserAccount implements Serializable {

	private static final long serialVersionUID = -3007417670196962657L;
	private Long accountNumber;
	private Long userId;
	private String userName;
	private String userEmail;
	private String mobileNo;
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
	private String accountType;
	/**
	 * 账户类型对应中文
	 */
	private String accountTypeName;
	
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
	public String getAccountTypeName() {
		return accountTypeName;
	}
	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
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
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}
	/**
	 * @param userEmail
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	/**
	 * @return mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}
	/**
	 * @param mobileNo
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
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
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

}
