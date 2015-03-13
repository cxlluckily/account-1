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
 * 商户账户信息
 * @author Ganlin
 * @version 0.1 (2014年7月26日 下午2:22:32)
 * @since 0.1
 * @see
 */
public class CommercialTenantAccount implements Serializable {

	private static final long serialVersionUID = -4208941885216337880L;
	private Long userId;
	private Long accountNumber;
	private String cTType;
	private String companyName;
	private String legalPersonName;
	private String legalPersonMail;
	private String legalPersonMobile;
	private String managerTel;  //企业用户手机号码
	private String accountStatus;
	/**
	 * 账户状态对应中文
	 */
	private String accountStatusName;
	private String moneyType;
	/**
	 * 币种类型对应中文
	 */
	private String moneyTypeName;
	/**
	 * 可用余额
	 */
	private BigDecimal availableBalance;

	/**
	 * 冻结金额
	 */
	private BigDecimal frozenAmount;

	/**
	 * 不可提现金额
	 */
	private BigDecimal nomentionAmount;
	private Date createDate;
	private Date modifyDate;
	private String userType;
	/**
	 * 用户类型
	 */
	private String userTypeName;
	private String accountType;
	/**
	 * 账户类型对应中文
	 */
	private String accountTypeName;
	
	
	public String getManagerTel() {
		return managerTel;
	}
	public void setManagerTel(String managerTel) {
		this.managerTel = managerTel;
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
	 * @return cTType
	 */
	public String getcTType() {
		return cTType;
	}
	/**
	 * @param cTType
	 */
	public void setcTType(String cTType) {
		this.cTType = cTType;
	}
	/**
	 * @return companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return legalPersonName
	 */
	public String getLegalPersonName() {
		return legalPersonName;
	}
	/**
	 * @param legalPersonName
	 */
	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName = legalPersonName;
	}
	/**
	 * @return legalPersonMail
	 */
	public String getLegalPersonMail() {
		return legalPersonMail;
	}
	/**
	 * @param legalPersonMail
	 */
	public void setLegalPersonMail(String legalPersonMail) {
		this.legalPersonMail = legalPersonMail;
	}
	/**
	 * @return legalPersonMobile
	 */
	public String getLegalPersonMobile() {
		return legalPersonMobile;
	}
	/**
	 * @param legalPersonMobile
	 */
	public void setLegalPersonMobile(String legalPersonMobile) {
		this.legalPersonMobile = legalPersonMobile;
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
	 * @return availableBalance
	 */
	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}
	/**
	 * @param availableBalance
	 */
	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}
	/**
	 * @return frozenAmount
	 */
	public BigDecimal getFrozenAmount() {
		return frozenAmount;
	}
	/**
	 * @param frozenAmount
	 */
	public void setFrozenAmount(BigDecimal frozenAmount) {
		this.frozenAmount = frozenAmount;
	}
	/**
	 * @return nomentionAmount
	 */
	public BigDecimal getNomentionAmount() {
		return nomentionAmount;
	}
	/**
	 * @param nomentionAmount
	 */
	public void setNomentionAmount(BigDecimal nomentionAmount) {
		this.nomentionAmount = nomentionAmount;
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
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

}
