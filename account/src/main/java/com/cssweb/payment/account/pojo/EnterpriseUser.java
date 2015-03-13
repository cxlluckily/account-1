package com.cssweb.payment.account.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.sf.json.JSONObject;



/**
 * Title: EnterpriseUser
 * Description: 单位用户信息表对应字段，表名称为ACM_BUSINESS_USER_INFO	,v1.6	
 * Company: China Software & Service Company
 * Project:payment-account
 * @author liLei
 * @version 1.0
 * @date 2014年7月23日 下午3:06:04
 * @see CEC支付平台_数据库设计说明书_账户管理模块_V1.6.xlsx
 */
public class EnterpriseUser  implements Serializable {
	
	/**
	 * 用户ID
	 */
    private Long  userId;
    /**
     * 企业名称|银行开户名
     */
    private String  businessName;
    /**
     * 营业执照注册号
     */
    private String  businessLicence;
    /**
     * 营业执照所在地
     */
    private String  businessLocation;
    /**
     * 营业期限
     */
    private Date  businessExpdate;
    /**
     * 常用地址
     */
    private String  businessAddress;
    /**
     * 联系电话
     */
    private String  managerTel;
    /**
     * 营业执照副本扫描件
     */
    private String  businessPic1;
    /**
     * 加盖公章的副本
     */
    private String  businessPic2;
    /**
     * 组织机构代码
     */
    private String  organizationCode;
    /**
     * 营业范围
     */
    private String  businessSphere;
    /**
     * 注册资金
     */
    private BigDecimal  businessRegamount;
    /**
     * 传真
     */
    private String  fax;
    /**
     * 开户银行
     */
    private String  businessBank;
    /**
     * 开户银行所在城市
     */
    private String  businessBankcity;
    /**
     * 开户银行支行名称
     */
    private String  businessBankbranch;
    /**
     * 公司对公账户
     */
    private String  businessBankno;
    /**
     * 法定代表人归属地
     */
    private String  legalPersoncity;
    /**
     * 法定代表人真实姓名
     */
    private String  legalPersonname;
    /**
     * 法定代表人电话
     */
    private String legalPersonphone;
    /**
     * 身份证号码
     */
    private String  legalPersonidno;
    /**
     * 身份证类型
     */
    private String  idType;
    /**
     * 身份证图片正面
     */
    private String  idPic1;
    /**
     * 身份证图片反面
     */
    private String  idPic2;
    /**
     * 手机号码
     */
    private String  managerMobile;
    /**
     * 电子邮箱
     */
    private String  email;
    /**
     * 认证步骤
     */
    private String  authStep;
    /**
     * 实名认证状态,实名认证状态,00未认证；01:已认证；02：已审核；03等待确认打款金额；04审核失败；05等待审核
     */
    private String  authStatus;

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
     * 代理人身份证正面
     */
    private String dlrSfzzm;
    /**
     * 代理人身份证反面
     */
    private String dlrSfzfm;
    /**
     * 代理人手机号码
     */
    private String dlrSjhm;
    /**
     * 是否代理人实名认证
     */
    private String dlrAuth;
    /**
     * 企业委托书扫描件
     */
    private String qywtssmj;
    /**
     * 实名认证类别,银行卡认证；01：公安部认证；02：移动运营商；其他
     */
    private String  authType;
    /**
     * 认证时间
     */
    private Date  authDate;
    /**
     * 是否签约商户
     */
    private String  isMerchant;
    /**
     * 预留信息
     */
    private String  preString;
    /**
     * 用户来源
     */
    private String  source;
    /**
     * 实名审核描述
     */
    private String  authDescribe;
    /**
     * 所属国家
     */
    private String  countryCode;
    /**
     * 地址
     */
    private String  address;
    /**
     * 邮编
     */
    private String  zipCode;
    /**
     * 主营业务
     */
    private String  business;
    /**
     * 商户类型
     */
    private String  ctStatus;
    /**
     * 创建时间
     */
    private Date  createDatetime;
    /**
     * 预留字段1
     */
    private String  reserve1;
    /**
     * 预留字段2
     */
    private String  reserve2;
    /**
     * 预留字段3
     */
    private Integer  reserve3;
    /**
     * 预留字段4
     */
    private Integer  reserve4;
    /**
     * 更新时间
     */
    private Date  updateDatetime;
    /**
     * 头像链接地址
     */
    private String headPic;
    
    
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
	
	public String getDlrAuth() {
		return dlrAuth;
	}
	public void setDlrAuth(String dlrAuth) {
		this.dlrAuth = dlrAuth;
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
	public String getLegalPersonphone() {
		return legalPersonphone;
	}
	public void setLegalPersonphone(String legalPersonphone) {
		this.legalPersonphone = legalPersonphone;
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
	 * @return authStep
	 */
	public String getAuthStep() {
		return authStep;
	}
	/**
	 * @param authStep
	 */
	public void setAuthStep(String authStep) {
		this.authStep = authStep;
	}
	/**
	 * @return authStatus
	 */
	public String getAuthStatus() {
		return authStatus;
	}
	/**
	 * @param authStatus
	 */
	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
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
	 * @return authType
	 */
	public String getAuthType() {
		return authType;
	}
	/**
	 * @param authType
	 */
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	/**
	 * @return authDate
	 */
	public Date getAuthDate() {
		return authDate;
	}
	/**
	 * @param authDate
	 */
	public void setAuthDate(Date authDate) {
		this.authDate = authDate;
	}
	/**
	 * @return isMerchant
	 */
	public String getIsMerchant() {
		return isMerchant;
	}
	/**
	 * @param isMerchant
	 */
	public void setIsMerchant(String isMerchant) {
		this.isMerchant = isMerchant;
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
	 * @return source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return authDescribe
	 */
	public String getAuthDescribe() {
		return authDescribe;
	}
	/**
	 * @param authDescribe
	 */
	public void setAuthDescribe(String authDescribe) {
		this.authDescribe = authDescribe;
	}
	/**
	 * @return countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}
	/**
	 * @param countryCode
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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
	 * @return zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return business
	 */
	public String getBusiness() {
		return business;
	}
	/**
	 * @param business
	 */
	public void setBusiness(String business) {
		this.business = business;
	}
	/**
	 * @return ctStatus
	 */
	public String getCtStatus() {
		return ctStatus;
	}
	/**
	 * @param ctStatus
	 */
	public void setCtStatus(String ctStatus) {
		this.ctStatus = ctStatus;
	}
	/**
	 * @return createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}
	/**
	 * @param createDatetime
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	/**
	 * @return reserve1
	 */
	public String getReserve1() {
		return reserve1;
	}
	/**
	 * @param reserve1
	 */
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}
	/**
	 * @return reserve2
	 */
	public String getReserve2() {
		return reserve2;
	}
	/**
	 * @param reserve2
	 */
	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}
	/**
	 * @return reserve3
	 */
	public Integer getReserve3() {
		return reserve3;
	}
	/**
	 * @param reserve3
	 */
	public void setReserve3(Integer reserve3) {
		this.reserve3 = reserve3;
	}
	/**
	 * @return reserve4
	 */
	public Integer getReserve4() {
		return reserve4;
	}
	/**
	 * @param reserve4
	 */
	public void setReserve4(Integer reserve4) {
		this.reserve4 = reserve4;
	}
	/**
	 * @return updateDatetime
	 */
	public Date getUpdateDatetime() {
		return updateDatetime;
	}
	/**
	 * @param updateDatetime
	 */
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
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

}

