package com.cssweb.payment.account.pojo;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONObject;



/**
 * Title: UserMobileAccountInfo
 * Description: 手机-移动支付账户对应关系表对应字段，表名称为ACM_USER_MOBILE_ACCOUNT_INFO,V1.6		
 * Company: China Software & Service Company
 * Project:payment-account
 * @author liLei
 * @version 1.0
 * @date 2014年7月23日 下午3:57:53
 * @see CEC支付平台_数据库设计说明书_账户管理模块_V1.6.xlsx
 */
public class UserMobileAccountInfo implements  Serializable{
	
	/**
	 * ID
	 */
	private  Long id;
	
	/**
	 * 用户ID
	 */
	private Long userId;
	
	/**
	 * 闪客蜂现金账户ID
	 */
	private Long accountId;
	/**
	 * 预付卡账户ID
	 */
	private Long prepaidAccountId;
	/**
	 * 逻辑卡号
	 */
	private String tkLogicalId;
	/**
	 * 卡唯一码
	 */
	private String cardUniqueData;
	/**	
	 * 卡唯一码名称，即类型,add lilei20140820
	 */
	private String seIdName;
	/**
	 * iccid
	 */
	private String iccid;
	/**
	 * se_type
	 */
	private String seType;
	/**
	 * se_id
	 */
	private String seId;
	/**
	 * 服务ID
	 */
	private String serviceId;
	/**
	 * 服务版本
	 */
	private String serviceVersion;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 个人化文件名
	 */
	private String personalFile;
	
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
	private String reserve3;
	
	/**
	 * 预留字段4
	 */
	private String reserve4;
	
	/**
	 * 更新时间
	 */
	private Date updateDatetime;

	/**
	 * 获取
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取
	 * @return accountId
	 */
	public Long getAccountId() {
		return accountId;
	}

	/**
	 * 设置
	 * @param accountId
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}


	/**
	 * 获取
	 * @return prepaidAccountId
	 */
	public Long getPrepaidAccountId() {
		return prepaidAccountId;
	}

	/**
	 * 设置
	 * @param prepaidAccountId
	 */
	public void setPrepaidAccountId(Long prepaidAccountId) {
		this.prepaidAccountId = prepaidAccountId;
	}
	
	/**
	 * 获取
	 * @return tkLogicalId
	 */
	public String getTkLogicalId() {
		return tkLogicalId;
	}

	/**
	 * 设置
	 * @param tkLogicalId
	 */
	public void setTkLogicalId(String tkLogicalId) {
		this.tkLogicalId = tkLogicalId;
	}

	/**
	 * 获取
	 * @return cardUniqueDate
	 */
	public String getCardUniqueData() {
		return cardUniqueData;
	}

	/**
	 * 设置
	 * @param cardUniqueDate
	 */
	public void setCardUniqueData(String cardUniqueData) {
		this.cardUniqueData = cardUniqueData;
	}

	/**
	 * @return the seIdName
	 */
	public String getSeIdName() {
		return seIdName;
	}

	/**
	 * @param seIdName the seIdName to set
	 */
	public void setSeIdName(String seIdName) {
		this.seIdName = seIdName;
	}

	/**
	 * 获取
	 * @return iccid
	 */
	public String getIccid() {
		return iccid;
	}

	/**
	 * 设置
	 * @param iccid
	 */
	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	/**
	 * 获取
	 * @return seType
	 */
	public String getSeType() {
		return seType;
	}

	/**
	 * 设置
	 * @param seType
	 */
	public void setSeType(String seType) {
		this.seType = seType;
	}

	/**
	 * 获取
	 * @return seId
	 */
	public String getSeId() {
		return seId;
	}

	/**
	 * 设置
	 * @param seId
	 */
	public void setSeId(String seId) {
		this.seId = seId;
	}

	/**
	 * 获取
	 * @return serviceId
	 */
	public String getServiceId() {
		return serviceId;
	}

	/**
	 * 设置
	 * @param serviceId
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	/**
	 * 获取
	 * @return serviceVersion
	 */
	public String getServiceVersion() {
		return serviceVersion;
	}

	/**
	 * 设置
	 * @param serviceVersion
	 */
	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
	}

	/**
	 * 获取
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 设置
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public String getPersonalFile() {
		return personalFile;
	}

	public void setPersonalFile(String personalFile) {
		this.personalFile = personalFile;
	}

	/**
	 * 获取
	 * @return createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * 设置
	 * @param createDatetime
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * 获取
	 * @return reserve1
	 */
	public String getReserve1() {
		return reserve1;
	}

	/**
	 * 设置
	 * @param reserve1
	 */
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	/**
	 * 获取
	 * @return reserve2
	 */
	public String getReserve2() {
		return reserve2;
	}

	/**
	 * 设置
	 * @param reserve2
	 */
	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	/**
	 * 获取
	 * @return reserve3
	 */
	public String getReserve3() {
		return reserve3;
	}

	/**
	 * 设置
	 * @param reserve3
	 */
	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}

	/**
	 * 获取
	 * @return reserve4
	 */
	public String getReserve4() {
		return reserve4;
	}

	/**
	 * 设置
	 * @param reserve4
	 */
	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}

	/**
	 * 获取
	 * @return updateDatetime
	 */
	public Date getUpdateDatetime() {
		return updateDatetime;
	}

	/**
	 * 设置
	 * @param updateDatetime
	 */
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

	
}
