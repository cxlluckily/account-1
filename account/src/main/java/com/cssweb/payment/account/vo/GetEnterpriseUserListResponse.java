package com.cssweb.payment.account.vo;

import java.io.Serializable;
import java.util.Date;

import com.cssweb.payment.account.pojo.EnterpriseUser;

public class GetEnterpriseUserListResponse extends EnterpriseUser implements Serializable {
	/**
	 * 用户类型
	 */
	private String userType;
	/**
	 * 用户类型对应中文
	 */
	private String userTypeName;
	/**
	 * 开户银行
	 */
	private String businessBank;
	/**
	 * 账户id
	 */
	private Long accountId;
	/**
	 * 证件类型对应中文
	 */
	private String idTypeName;
	/**
	 * 企业认证状态对应中文
	 */
	private String authStatusName;
	/**
	 * 用户来源对应中文
	 */
	private String sourceName;
	/**
	 * 用户状态
	 */
	private String userStatus;
	/**
	 * 用户状态对应中文
	 */
	private String userStatusName;
    /**
     * 是否签约商户中文
     */
    private String  isMerchantName;
	/**
	 * 上次登录时间
	 */
	private Date lastLoginTime;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 银行卡号
	 */
	private String bankNo;
	/**
	 * 实名认证时间
	 */
	private Date userAuDateTime;
	/**
	 * 国家对应中文
	 */
	private String countryName;
	
	/** 开户行所在省 */
	private String bankProvince;
	/** 开户行所在市 */
	private String bankCity;
	/** 跨行转账CNAPS联行号 */
	private String bankCnaps;
	/** 银行内部转账行号 */
	private String bankInternal;
	/** 支行ID  */
	private String branchId;
	/** 支行名称  */
	private String branchName;
	/**  银行序号 */
	private String bankId;
	/**
	 * 失败原因
	 */
	private String authenticationReason;
	
	/**
	 * 冻结描述
	 */
	private String freezeReason;
	
	/**
	 * @return freezeReason
	 */
	public String getFreezeReason() {
		return freezeReason;
	}
	/**
	 * @param freezeReason
	 */
	public void setFreezeReason(String freezeReason) {
		this.freezeReason = freezeReason;
	}
	public String getAuthenticationReason() {
		return authenticationReason;
	}
	public void setAuthenticationReason(String authenticationReason) {
		this.authenticationReason = authenticationReason;
	}
	/**
	 * @return userTypeName
	 */
	public String getUserTypeName() {
		return userTypeName;
	}
	/**
	 * @param userTypeName
	 */
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	/**
	 * @return businessBank
	 */
	public String getBusinessBank() {
		return businessBank;
	}
	/**
	 * @param businessBank
	 */
	public void setBusinessBank(String businessBank) {
		this.businessBank = businessBank;
	}
	
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	/**
	 * @return accountId
	 */
	public Long getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return idTypeName
	 */
	public String getIdTypeName() {
		return idTypeName;
	}
	/**
	 * @param idTypeName
	 */
	public void setIdTypeName(String idTypeName) {
		this.idTypeName = idTypeName;
	}
	/**
	 * @return authStatusName
	 */
	public String getAuthStatusName() {
		return authStatusName;
	}
	/**
	 * @param authStatusName
	 */
	public void setAuthStatusName(String authStatusName) {
		this.authStatusName = authStatusName;
	}
	/**
	 * @return sourceName
	 */
	public String getSourceName() {
		return sourceName;
	}
	/**
	 * @param sourceName
	 */
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	
	/**
	 * @return userStatus
	 */
	public String getUserStatus() {
		return userStatus;
	}
	/**
	 * @param userStatus
	 */
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	/**
	 * @return userStatusName
	 */
	public String getUserStatusName() {
		return userStatusName;
	}
	/**
	 * @param userStatusName
	 */
	public void setUserStatusName(String userStatusName) {
		this.userStatusName = userStatusName;
	}
	/**
	 * @return isMerchantName
	 */
	public String getIsMerchantName() {
		return isMerchantName;
	}
	/**
	 * @param isMerchantName
	 */
	public void setIsMerchantName(String isMerchantName) {
		this.isMerchantName = isMerchantName;
	}
	/**
	 * @return lastLoginTime
	 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	/**
	 * @param lastLoginTime
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	/**
	 * @return bankName
	 */
	public String getBankName() {
		return bankName;
	}
	/**
	 * @param bankName
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**
	 * @return bankNo
	 */
	public String getBankNo() {
		return bankNo;
	}
	/**
	 * @param bankNo
	 */
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	/**
	 * @return userAuDateTime
	 */
	public Date getUserAuDateTime() {
		return userAuDateTime;
	}
	/**
	 * @param userAuDateTime
	 */
	public void setUserAuDateTime(Date userAuDateTime) {
		this.userAuDateTime = userAuDateTime;
	}
	/**
	 * @return bankProvince
	 */
	public String getBankProvince() {
		return bankProvince;
	}
	/**
	 * @param bankProvince
	 */
	public void setBankProvince(String bankProvince) {
		this.bankProvince = bankProvince;
	}
	/**
	 * @return bankCity
	 */
	public String getBankCity() {
		return bankCity;
	}
	/**
	 * @param bankCity
	 */
	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}
	/**
	 * @return bankCnaps
	 */
	public String getBankCnaps() {
		return bankCnaps;
	}
	/**
	 * @param bankCnaps
	 */
	public void setBankCnaps(String bankCnaps) {
		this.bankCnaps = bankCnaps;
	}
	/**
	 * @return bankInternal
	 */
	public String getBankInternal() {
		return bankInternal;
	}
	/**
	 * @param bankInternal
	 */
	public void setBankInternal(String bankInternal) {
		this.bankInternal = bankInternal;
	}
	/**
	 * @return branchId
	 */
	public String getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId
	 */
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	/**
	 * @return branchName
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * @param branchName
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	/**
	 * @return bankId
	 */
	public String getBankId() {
		return bankId;
	}
	/**
	 * @param bankId
	 */
	public void setBankId(String bankId) {
		this.bankId = bankId;
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

}
