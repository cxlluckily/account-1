package com.cssweb.payment.account.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

/**
 * Title: PersonalUser
 * Description: 个人用户信息表对应字段，表名称为ACM_PERSONAL_USER_INFO,对应于V1.16版本	
 * Company: China Software & Service Company
 * Project:payment-account
 * @author liLei
 * @version 1.0
 * @date 2014年7月23日 下午3:14:17
 * @see CEC支付平台_数据库设计说明书_账户管理模块_V1.6.xlsx
 */
public class PersonalUser implements Serializable{
    
    /**
     * 用户ID
     */
    private Long userId;         
    /**
     * 用户姓名
     */
    private String userName;         
    /**
     * 证件截止日期
     */
    private Date certExpireDate;     
    /**
     * 头像
     */
    private String headPic;         
    /**
     * 注册方式
     */
    private String regType;         
    /**
     * 来源
     */
    private String source;         
    /**
     * 绑定电话
     */
    private String phoneNumber;         
    /**
     * 绑定邮箱
     */
    private String emailAddress;         
    /**
     * QQ号
     */
    private String qqNo;         
    /**
     * 微信号
     */
    private String wiChatNo;         
    /**
     * 联系地址
     */
    private String personalAddress;
    /**
     * 国籍
     */
    private String countryCode;
    /**
     * 所在城市
     */
    private String cityCode;         
    /**
     * 第二联系电话
     */
    private String secondPhoneNumber;         
    /**
     * 认证步骤
     */
    private String authStep;         
    /**
     * 实名认证状态, 实名认证状态,00:未认证;01:已认证；02：已审核；03等待确认打款金额；04审核失败；05等待审核
     */
    private String authStatus;         
    /**
     * 实名认证类别,银行卡认证；01：公安部认证；02：移动运营商；其他
     */
    private String authType;         
    /**
     * 认证申请时间
     */
    private Date authDate;         
    /**
     * 认证证件类别
     */
    private String authCertType;         
    /**
     * 认证证件号
     */
    private String authCertId;         
    /**
     * 证件有效截止日期
     */
    private Date authCertExpirDate;         
    /**
     * 认证证件照片正面
     */
    private String pictureFront;         
    /**
     * 认证证件照片反面
     */
    private String pictureBack;         
    /**
     * 实名审核描述
     */
    private String authDescribe;         
    /**
     * 预留信息
     */
    private String preString;         
    /**
     * 创建时间
     */
    private Date createDatetime;         
    /**
     * 预留字段1
     */
    private String reserve1;         
    /**
     * 预留字段2
     */
    private String reserve2;         
    /**
     * 预留字段3
     */
    private Integer reserve3;         
    /**
     * 预留字段2
     */
    private Integer reserve4;         
    /**
     * 更新时间
     */
    private Date updateDatetime;   
    
	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the certExpireDate
	 */
	public Date getCertExpireDate() {
		return certExpireDate;
	}
	/**
	 * @param certExpireDate the certExpireDate to set
	 */
	public void setCertExpireDate(Date certExpireDate) {
		this.certExpireDate = certExpireDate;
	}
	/**
	 * @return the headPic
	 */
	public String getHeadPic() {
		return headPic;
	}
	/**
	 * @param headPic the headPic to set
	 */
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	/**
	 * @return the regType
	 */
	public String getRegType() {
		return regType;
	}
	/**
	 * @param regType the regType to set
	 */
	public void setRegType(String regType) {
		this.regType = regType;
	}
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @return the qqNo
	 */
	public String getQqNo() {
		return qqNo;
	}
	/**
	 * @param qqNo the qqNo to set
	 */
	public void setQqNo(String qqNo) {
		this.qqNo = qqNo;
	}
	/**
	 * @return the wiChatNo
	 */
	public String getWiChatNo() {
		return wiChatNo;
	}
	/**
	 * @param wiChatNo the wiChatNo to set
	 */
	public void setWiChatNo(String wiChatNo) {
		this.wiChatNo = wiChatNo;
	}
	/**
	 * @return the personalAddress
	 */
	public String getPersonalAddress() {
		return personalAddress;
	}
	/**
	 * @param personalAddress the personalAddress to set
	 */
	public void setPersonalAddress(String personalAddress) {
		this.personalAddress = personalAddress;
	}
	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}
	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}
	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	/**
	 * @return the secondPhoneNumber
	 */
	public String getSecondPhoneNumber() {
		return secondPhoneNumber;
	}
	/**
	 * @param secondPhoneNumber the secondPhoneNumber to set
	 */
	public void setSecondPhoneNumber(String secondPhoneNumber) {
		this.secondPhoneNumber = secondPhoneNumber;
	}
	/**
	 * @return the authStep
	 */
	public String getAuthStep() {
		return authStep;
	}
	/**
	 * @param authStep the authStep to set
	 */
	public void setAuthStep(String authStep) {
		this.authStep = authStep;
	}
	/**
	 * @return the authStatus
	 */
	public String getAuthStatus() {
		return authStatus;
	}
	/**
	 * @param authStatus the authStatus to set
	 */
	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}
	/**
	 * @return the authType
	 */
	public String getAuthType() {
		return authType;
	}
	/**
	 * @param authType the authType to set
	 */
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	/**
	 * @return the authDate
	 */
	public Date getAuthDate() {
		return authDate;
	}
	/**
	 * @param authDate the authDate to set
	 */
	public void setAuthDate(Date authDate) {
		this.authDate = authDate;
	}
	/**
	 * @return the authCertType
	 */
	public String getAuthCertType() {
		return authCertType;
	}
	/**
	 * @param authCertType the authCertType to set
	 */
	public void setAuthCertType(String authCertType) {
		this.authCertType = authCertType;
	}
	/**
	 * @return the authCertId
	 */
	public String getAuthCertId() {
		return authCertId;
	}
	/**
	 * @param authCertId the authCertId to set
	 */
	public void setAuthCertId(String authCertId) {
		this.authCertId = authCertId;
	}
	/**
	 * @return the authCertExpirDate
	 */
	public Date getAuthCertExpirDate() {
		return authCertExpirDate;
	}
	/**
	 * @param authCertExpirDate the authCertExpirDate to set
	 */
	public void setAuthCertExpirDate(Date authCertExpirDate) {
		this.authCertExpirDate = authCertExpirDate;
	}
	/**
	 * @return the pictureFront
	 */
	public String getPictureFront() {
		return pictureFront;
	}
	/**
	 * @param pictureFront the pictureFront to set
	 */
	public void setPictureFront(String pictureFront) {
		this.pictureFront = pictureFront;
	}
	/**
	 * @return the pictureBack
	 */
	public String getPictureBack() {
		return pictureBack;
	}
	/**
	 * @param pictureBack the pictureBack to set
	 */
	public void setPictureBack(String pictureBack) {
		this.pictureBack = pictureBack;
	}
	/**
	 * @return the authDescribe
	 */
	public String getAuthDescribe() {
		return authDescribe;
	}
	/**
	 * @param authDescribe the authDescribe to set
	 */
	public void setAuthDescribe(String authDescribe) {
		this.authDescribe = authDescribe;
	}
	/**
	 * @return the preString
	 */
	public String getPreString() {
		return preString;
	}
	/**
	 * @param preString the preString to set
	 */
	public void setPreString(String preString) {
		this.preString = preString;
	}
	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}
	/**
	 * @param createDatetime the createDatetime to set
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	/**
	 * @return the reserve1
	 */
	public String getReserve1() {
		return reserve1;
	}
	/**
	 * @param reserve1 the reserve1 to set
	 */
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}
	/**
	 * @return the reserve2
	 */
	public String getReserve2() {
		return reserve2;
	}
	/**
	 * @param reserve2 the reserve2 to set
	 */
	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}
	/**
	 * @return the reserve3
	 */
	public Integer getReserve3() {
		return reserve3;
	}
	/**
	 * @param reserve3 the reserve3 to set
	 */
	public void setReserve3(Integer reserve3) {
		this.reserve3 = reserve3;
	}
	/**
	 * @return the reserve4
	 */
	public Integer getReserve4() {
		return reserve4;
	}
	/**
	 * @param reserve4 the reserve4 to set
	 */
	public void setReserve4(Integer reserve4) {
		this.reserve4 = reserve4;
	}
	/**
	 * @return the updateDatetime
	 */
	public Date getUpdateDatetime() {
		return updateDatetime;
	}
	/**
	 * @param updateDatetime the updateDatetime to set
	 */
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

}
