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
 * @version 0.1 (2014年8月6日 下午1:12:01)
 * @since 0.1
 * @see
 */
public class GetEnterpriseUserBasicInfoResponse implements Serializable {

	private static final long serialVersionUID = 3673387937698960582L;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 企业名称|银行开户名
	 */
	private String businessName;
	/**
	 * 营业执照注册号
	 */
	private String businessLicence;
	/**
	 * 营业执照所在地
	 */
	private String businessLocation;
	/**
	 * 营业期限
	 */
	private Date businessExpdate;
	/**
	 * 常用地址
	 */
	private String businessAddress;
	/**
	 * 联系电话
	 */
	private String managerTel;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 手机
	 */
	private String managerMobile;
	/**
	 * 是否开通短信校验服务
	 */
	private boolean messageStatus;
	/**
	 * 营业执照副本扫描件
	 */
	private String businessPic1;
	/**
	 * 加盖公章的副本
	 */
	private String businessPic2;
	/**
	 * 组织机构代码
	 */
	private String organizationCode;
	/**
	 * 营业范围
	 */
	private String businessSphere;
	/**
	 * 注册资金
	 */
	private BigDecimal businessRegamount;
	/**
	 * 传真
	 */
	private String fax;
	/**
	 * 开户银行
	 */
	private String businessBank;
	/**
	 * 开户银行所在城市
	 */
	private String businessBankcity;
	/**
	 * 开户银行支行名称
	 */
	private String businessBankbranch;
	/**
	 * 公司对公账户
	 */
	private String businessBankno;
	/**
	 * 法定代表人的手机号码
	 */
	private String legalPersonPhone;
	/**
	 * 法定代表人归属地
	 */
	private String legalPersoncity;
	/**
	 * 法定代表人真实姓名
	 */
	private String legalPersonname;
	/**
	 * 身份证号码
	 */
	private String legalPersonidno;
	/**
	 * 身份证类型
	 */
	private String idType;
	/**
	 * 身份证类型对应中文
	 */
	private String idTypeName;
	/**
	 * 身份证图片正面
	 */
	private String idPic1;
	/**
	 * 身份证图片反面
	 */
	private String idPic2;
	/**
	 * 代理人姓名
	 */
	private String dlrName;
	/**
	 * 代理人身份证号码
	 */
	private String dlrSfzhm;
	/**
	 * 代理人身份证类型
	 */
	private String dlrSfzlx;
	/**
	 * 代理人身份证类型对应中文
	 */
	private String dlrSfzlxName;
	/**
	 * 代理人身份证正面
	 */
	private String dlrSfzzm;
	/**
	 * 代理人身份证反面
	 */
	private String dlrSfzfm;
	/**
	 * 企业委托书扫描件
	 */
	private String qywtssmj;
	/**
	 * 代理人手机号码
	 */
	private String dlrSjhm;
	/**
	 * 是否代理人实名认证
	 */
	private String dlrAuth;
	/**
	 * 用户状态
	 */
	private String userStatus;
	/**
	 * 用户状态对应中文
	 */
	private String userStatusName;

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
	private Date regTime;
	/**
	 * 上次登录时间
	 */
	private Date lastLoginTime;
	/**
	 * 是否开通余额支付
	 */
	private boolean balanceStatus;
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
	 * 是否签约商户
	 */
	private boolean isMerchant;
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
	 * 登陆手机号
	 */
	private String loginMobile;
	/**
	 * 登陆邮箱
	 */
	private String loginEmail;
	/**
	 * 头像
	 */
	private String headPic;
	/**
	 * 失败原因
	 */
	private String authenticationReason;
	
	/**
	 * 审核描述
	 */
	private String authDescribe;
	
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
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @return isMerchant
	 */
	public boolean isMerchant() {
		return isMerchant;
	}

	public String getDlrAuth() {
		return dlrAuth;
	}

	public void setDlrAuth(String dlrAuth) {
		this.dlrAuth = dlrAuth;
	}

	/**
	 * @param isMerchant
	 */
	public void setMerchant(boolean isMerchant) {
		this.isMerchant = isMerchant;
	}

	public boolean isMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(boolean messageStatus) {
		this.messageStatus = messageStatus;
	}

	/**
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return businessName
	 */
	public String getBusinessName() {
		return businessName;
	}

	/**
	 * @param businessName
	 */
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	/**
	 * @return businessLicence
	 */
	public String getBusinessLicence() {
		return businessLicence;
	}

	/**
	 * @param businessLicence
	 */
	public void setBusinessLicence(String businessLicence) {
		this.businessLicence = businessLicence;
	}

	/**
	 * @return businessLocation
	 */
	public String getBusinessLocation() {
		return businessLocation;
	}

	/**
	 * @param businessLocation
	 */
	public void setBusinessLocation(String businessLocation) {
		this.businessLocation = businessLocation;
	}

	/**
	 * @return businessExpdate
	 */
	public Date getBusinessExpdate() {
		return businessExpdate;
	}

	/**
	 * @param businessExpdate
	 */
	public void setBusinessExpdate(Date businessExpdate) {
		this.businessExpdate = businessExpdate;
	}

	/**
	 * @return businessAddress
	 */
	public String getBusinessAddress() {
		return businessAddress;
	}

	/**
	 * @param businessAddress
	 */
	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	/**
	 * @return managerTel
	 */
	public String getManagerTel() {
		return managerTel;
	}

	/**
	 * @param managerTel
	 */
	public void setManagerTel(String managerTel) {
		this.managerTel = managerTel;
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
	 * @return managerMobile
	 */
	public String getManagerMobile() {
		return managerMobile;
	}

	/**
	 * @param managerMobile
	 */
	public void setManagerMobile(String managerMobile) {
		this.managerMobile = managerMobile;
	}

	/**
	 * @return businessPic1
	 */
	public String getBusinessPic1() {
		return businessPic1;
	}

	/**
	 * @param businessPic1
	 */
	public void setBusinessPic1(String businessPic1) {
		this.businessPic1 = businessPic1;
	}

	/**
	 * @return businessPic2
	 */
	public String getBusinessPic2() {
		return businessPic2;
	}

	/**
	 * @param businessPic2
	 */
	public void setBusinessPic2(String businessPic2) {
		this.businessPic2 = businessPic2;
	}

	/**
	 * @return organizationCode
	 */
	public String getOrganizationCode() {
		return organizationCode;
	}

	/**
	 * @param organizationCode
	 */
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	/**
	 * @return businessSphere
	 */
	public String getBusinessSphere() {
		return businessSphere;
	}

	/**
	 * @param businessSphere
	 */
	public void setBusinessSphere(String businessSphere) {
		this.businessSphere = businessSphere;
	}

	/**
	 * @return businessRegamount
	 */
	public BigDecimal getBusinessRegamount() {
		return businessRegamount;
	}

	/**
	 * @param businessRegamount
	 */
	public void setBusinessRegamount(BigDecimal businessRegamount) {
		this.businessRegamount = businessRegamount;
	}

	/**
	 * @return fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax
	 */
	public void setFax(String fax) {
		this.fax = fax;
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

	/**
	 * @return businessBankcity
	 */
	public String getBusinessBankcity() {
		return businessBankcity;
	}

	/**
	 * @param businessBankcity
	 */
	public void setBusinessBankcity(String businessBankcity) {
		this.businessBankcity = businessBankcity;
	}

	/**
	 * @return businessBankbranch
	 */
	public String getBusinessBankbranch() {
		return businessBankbranch;
	}

	/**
	 * @param businessBankbranch
	 */
	public void setBusinessBankbranch(String businessBankbranch) {
		this.businessBankbranch = businessBankbranch;
	}

	/**
	 * @return businessBankno
	 */
	public String getBusinessBankno() {
		return businessBankno;
	}

	/**
	 * @param businessBankno
	 */
	public void setBusinessBankno(String businessBankno) {
		this.businessBankno = businessBankno;
	}

	/**
	 * @return legalPersoncity
	 */
	public String getLegalPersoncity() {
		return legalPersoncity;
	}

	/**
	 * @param legalPersoncity
	 */
	public void setLegalPersoncity(String legalPersoncity) {
		this.legalPersoncity = legalPersoncity;
	}

	/**
	 * @return legalPersonname
	 */
	public String getLegalPersonname() {
		return legalPersonname;
	}

	/**
	 * @param legalPersonname
	 */
	public void setLegalPersonname(String legalPersonname) {
		this.legalPersonname = legalPersonname;
	}

	/**
	 * @return legalPersonidno
	 */
	public String getLegalPersonidno() {
		return legalPersonidno;
	}

	/**
	 * @param legalPersonidno
	 */
	public void setLegalPersonidno(String legalPersonidno) {
		this.legalPersonidno = legalPersonidno;
	}

	/**
	 * @return idType
	 */
	public String getIdType() {
		return idType;
	}

	/**
	 * @param idType
	 */
	public void setIdType(String idType) {
		this.idType = idType;
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
	 * @return idPic1
	 */
	public String getIdPic1() {
		return idPic1;
	}

	/**
	 * @param idPic1
	 */
	public void setIdPic1(String idPic1) {
		this.idPic1 = idPic1;
	}

	/**
	 * @return idPic2
	 */
	public String getIdPic2() {
		return idPic2;
	}

	/**
	 * @param idPic2
	 */
	public void setIdPic2(String idPic2) {
		this.idPic2 = idPic2;
	}

	/**
	 * @return dlrName
	 */
	public String getDlrName() {
		return dlrName;
	}

	/**
	 * @param dlrName
	 */
	public void setDlrName(String dlrName) {
		this.dlrName = dlrName;
	}

	/**
	 * @return dlrSfzhm
	 */
	public String getDlrSfzhm() {
		return dlrSfzhm;
	}

	/**
	 * @param dlrSfzhm
	 */
	public void setDlrSfzhm(String dlrSfzhm) {
		this.dlrSfzhm = dlrSfzhm;
	}

	/**
	 * @return dlrSfzlx
	 */
	public String getDlrSfzlx() {
		return dlrSfzlx;
	}

	/**
	 * @param dlrSfzlx
	 */
	public void setDlrSfzlx(String dlrSfzlx) {
		this.dlrSfzlx = dlrSfzlx;
	}

	/**
	 * @return dlrSfzlxName
	 */
	public String getDlrSfzlxName() {
		return dlrSfzlxName;
	}

	/**
	 * @param dlrSfzlxName
	 */
	public void setDlrSfzlxName(String dlrSfzlxName) {
		this.dlrSfzlxName = dlrSfzlxName;
	}

	/**
	 * @return dlrSfzzm
	 */
	public String getDlrSfzzm() {
		return dlrSfzzm;
	}

	/**
	 * @param dlrSfzzm
	 */
	public void setDlrSfzzm(String dlrSfzzm) {
		this.dlrSfzzm = dlrSfzzm;
	}

	/**
	 * @return dlrSfzfm
	 */
	public String getDlrSfzfm() {
		return dlrSfzfm;
	}

	/**
	 * @param dlrSfzfm
	 */
	public void setDlrSfzfm(String dlrSfzfm) {
		this.dlrSfzfm = dlrSfzfm;
	}

	/**
	 * @return qywtssmj
	 */
	public String getQywtssmj() {
		return qywtssmj;
	}

	/**
	 * @param qywtssmj
	 */
	public void setQywtssmj(String qywtssmj) {
		this.qywtssmj = qywtssmj;
	}

	/**
	 * @return dlrSjhm
	 */
	public String getDlrSjhm() {
		return dlrSjhm;
	}

	/**
	 * @param dlrSjhm
	 */
	public void setDlrSjhm(String dlrSjhm) {
		this.dlrSjhm = dlrSjhm;
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

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
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
	 * @return headPic
	 */
	public String getHeadPic() {
		return headPic;
	}

	/**
	 * 设置
	 * @param headPic
	 */
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
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

	public String getLegalPersonPhone() {
		return legalPersonPhone;
	}

	public void setLegalPersonPhone(String legalPersonPhone) {
		this.legalPersonPhone = legalPersonPhone;
	}
	

}
