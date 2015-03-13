package com.cssweb.payment.account.vo;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONObject;

public class GetPersonalUserListResponse implements Serializable {
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 用户姓名
	 */
	private String userName;

	/**
	 * 绑定电话
	 */
	private String phoneNumber;
	/**
	 * 用户类型
	 */
	private String userType;
	/**
	 * 用户类型对应中文
	 */
	private String userTypeName;
	/**
	 * 绑定邮箱
	 */
	private String emailAddress;
	/**
	 * 证件类型
	 */
	private String authCertType;
	/**
	 * 证件类型对应中文
	 */
	private String authCertTypeName;
	/**
	 * 证件号码
	 */
	private String authCertId;
	/**
	 * 身份证正面照
	 */
	private String pictureFront;
	/**
	 * 身份证反面照
	 */
	private String pictureBack;
	/**
	 * 证件有效截止日期
	 */
	private Date authCertExpirDate;
	/**
	 * 联系地址
	 */
	private String personalAddress;
	/**
	 * 注册日期
	 */
	private Date registerDate;
	/**
	 * 用户状态
	 */
	private String userStatus;
	/**
	 * 用户状态对应中文
	 */
	private String userStatusName;
	/**
	 * 职业
	 */
	private String occupation;
	/**
	 * 用户非正常状态理由
	 */
	private String reason;
	/**
	 * 实名认证状态, 实名认证状态,00:未认证;01:已认证；02：已审核；03等待确认打款金额；04审核失败；05等待审核
	 */
	private String authStatus;
	/**
	 * 实名认证状态对应中文
	 */
	private String authStatusName;
	/**
	 * 用户认证失败理由
	 */
	private String authenticationReason;
	/**
	 * 用户来源
	 */
	private String source;
	/**
	 * 用户来源对应中文
	 */
	private String sourceName;
	/**
	 * 账户id
	 */
	private Long accountId;

	private String status;
	/**
	 * 上次登录时间
	 */
	private Date lastLoginTime;
	/**
	 * 认证申请时间
	 */
	private Date authDate;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 银行卡号
	 */
	private String bankNo;
	/**
	 * 创建时间
	 */
	private Date userAuDateTime;

	/** 开户行所在省 */
	private String bankProvince;
	/** 开户行所在市 */
	private String bankCity;
	/** 跨行转账CNAPS联行号 */
	private String bankCnaps;
	/** 银行内部转账行号 */
	private String bankInternal;
	/** 支行ID */
	private String branchId;
	/** 支行名称 */
	private String branchName;
	/** 银行序号 */
	private String bankId;
	
	/**
	 * 审核描述
	 */
	private String authDescribe;
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getAuthCertType() {
		return authCertType;
	}

	public void setAuthCertType(String authCertType) {
		this.authCertType = authCertType;
	}

	public String getAuthCertTypeName() {
		return authCertTypeName;
	}

	public void setAuthCertTypeName(String authCertTypeName) {
		this.authCertTypeName = authCertTypeName;
	}

	public String getAuthCertId() {
		return authCertId;
	}

	public void setAuthCertId(String authCertId) {
		this.authCertId = authCertId;
	}

	public String getPictureFront() {
		return pictureFront;
	}

	public void setPictureFront(String pictureFront) {
		this.pictureFront = pictureFront;
	}

	public String getPictureBack() {
		return pictureBack;
	}

	public void setPictureBack(String pictureBack) {
		this.pictureBack = pictureBack;
	}

	public Date getAuthCertExpirDate() {
		return authCertExpirDate;
	}

	public void setAuthCertExpirDate(Date authCertExpirDate) {
		this.authCertExpirDate = authCertExpirDate;
	}

	public String getPersonalAddress() {
		return personalAddress;
	}

	public void setPersonalAddress(String personalAddress) {
		this.personalAddress = personalAddress;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserStatusName() {
		return userStatusName;
	}

	public void setUserStatusName(String userStatusName) {
		this.userStatusName = userStatusName;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public String getAuthStatusName() {
		return authStatusName;
	}

	public void setAuthStatusName(String authStatusName) {
		this.authStatusName = authStatusName;
	}

	public String getAuthenticationReason() {
		return authenticationReason;
	}

	public void setAuthenticationReason(String authenticationReason) {
		this.authenticationReason = authenticationReason;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getAuthDate() {
		return authDate;
	}

	public void setAuthDate(Date authDate) {
		this.authDate = authDate;
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
	
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

	/**
	 * 获取
	 * @return authDescribe
	 */
	public String getAuthDescribe() {
		return authDescribe;
	}

	/**
	 * 设置
	 * @param authDescribe
	 */
	public void setAuthDescribe(String authDescribe) {
		this.authDescribe = authDescribe;
	}

}
