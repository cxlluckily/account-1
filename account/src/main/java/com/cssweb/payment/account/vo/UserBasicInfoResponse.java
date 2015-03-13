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
 * @author wangpeng
 * @version 0.1 (2014年8月5日 下午1:12:01)
 * @since 0.1
 * @see
 */
public class UserBasicInfoResponse implements Serializable {

	private static final long serialVersionUID = -7911736902593966178L;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 用户姓名
	 */
	private String realName;
	/**
	 * 头像
	 */
	private String headPic;
	/**
	 * 绑定电话
	 */
	private String mobile;
	/**
	 * 绑定邮箱
	 */
	private String email;
	/**
	 * 联系地址
	 */
	private String address;
	/**
	 * 联系电话
	 */
	private String telephone;
	/**
	 * 是否通过实名认证
	 */
	private boolean authStatus;
	/**
	 * 预留信息
	 */
	private String preString;

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

	/**
	 * 账户注册时间
	 */
	private String regTime;
	/**
	 * 上次登录时间
	 */
	private Date lastLoginTime;
	/**
	 * 是否开通余额支付
	 */
	private boolean balanceStatus;
	/**
	 * 是否开通短信校验服务
	 */
	private boolean messageStatus;
	/**
	 * 用户状态
	 */
	private String userStatus;
	/**
	 * 用户状态对应中文
	 */
	private String userStatusName;
	/**
	 * 账户id
	 */
	private Long accountId;

	/**
	 * 认证状态
	 */
	private String userAuthStatus;

	/**
	 * 认证状态对应中文
	 */
	private String userAuthStatusName;

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
	/**
	 * 认证证件照片正面
	 */
	private String pictureFront;
	/**
	 * 认证证件照片反面
	 */
	private String pictureBack;
	/**
	 * 认证证件类别
	 */
	private String authCertType;
	/**
	 * 认证证件类别对应中文
	 */
	private String authCertTypeName;
	/**
	 * 认证证件号
	 */
	private String authCertId;

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
	 * 登陆手机号
	 */
	private String loginMobile;
	/**
	 * 登陆邮箱
	 */
	private String loginEmail;
	/**
     * 证件有效截止日期
     */
    private Date authCertExpirDate;  
    /**
	 * 失败原因
	 */
	private String authenticationReason;
	/**
	 * 审核描述
	 */
	private String authDescribe;
	public String getAuthenticationReason() {
		return authenticationReason;
	}

	public void setAuthenticationReason(String authenticationReason) {
		this.authenticationReason = authenticationReason;
	}

	public Date getAuthCertExpirDate() {
		return authCertExpirDate;
	}

	public void setAuthCertExpirDate(Date authCertExpirDate) {
		this.authCertExpirDate = authCertExpirDate;
	}

	/**
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
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

	/**
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @param realName
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * @return headPic
	 */
	public String getHeadPic() {
		return headPic;
	}

	/**
	 * @param headPic
	 */
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	/**
	 * @return mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return authStatus
	 */
	public boolean isAuthStatus() {
		return authStatus;
	}

	/**
	 * @param authStatus
	 */
	public void setAuthStatus(boolean authStatus) {
		this.authStatus = authStatus;
	}

	/**
	 * @return preString
	 */
	public String getPreString() {
		return preString;
	}

	/**
	 * @param preString
	 */
	public void setPreString(String preString) {
		this.preString = preString;
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

	public boolean isMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(boolean messageStatus) {
		this.messageStatus = messageStatus;
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
	 * @return regTime
	 */
	public String getRegTime() {
		return regTime;
	}

	/**
	 * @param regTime
	 */
	public void setRegTime(String regTime) {
		this.regTime = regTime;
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
	 * @return balanceStatus
	 */
	public boolean isBalanceStatus() {
		return balanceStatus;
	}

	/**
	 * @param balanceStatus
	 */
	public void setBalanceStatus(boolean balanceStatus) {
		this.balanceStatus = balanceStatus;
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
	 * @return userAuthStatus
	 */
	public String getUserAuthStatus() {
		return userAuthStatus;
	}

	/**
	 * @param userAuthStatus
	 */
	public void setUserAuthStatus(String userAuthStatus) {
		this.userAuthStatus = userAuthStatus;
	}

	/**
	 * @return userAuthStatusName
	 */
	public String getUserAuthStatusName() {
		return userAuthStatusName;
	}

	/**
	 * @param userAuthStatusName
	 */
	public void setUserAuthStatusName(String userAuthStatusName) {
		this.userAuthStatusName = userAuthStatusName;
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
	 * @return loginMobile
	 */
	public String getLoginMobile() {
		return loginMobile;
	}

	/**
	 * @param loginMobile
	 */
	public void setLoginMobile(String loginMobile) {
		this.loginMobile = loginMobile;
	}

	/**
	 * @return loginEmail
	 */
	public String getLoginEmail() {
		return loginEmail;
	}

	/**
	 * @param loginEmail
	 */
	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
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
