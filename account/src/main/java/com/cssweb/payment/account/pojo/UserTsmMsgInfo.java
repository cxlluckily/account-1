package com.cssweb.payment.account.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Resource;

import net.sf.json.JSONObject;


/**
 * Title: UserTsmMsgInfo
 * Description: 发送给TSM的信息表对应字段，表名称为	ACM_USER_TSM_MSG_INFO，V1.6		
 * Company: China Software & Service Company
 * Project:payment-account
 * @author liLei
 * @version 1.0
 * @date 2014年7月23日 下午4:00:37
 * @see CEC支付平台_数据库设计说明书_账户管理模块_V1.6.xlsx
 */
public class UserTsmMsgInfo implements  Serializable{
	
	/**
	 * ID
	 */
	private Long id;

	/**
	 * 发送信息时产生的id，tsm返回来的
	 */
	private String messageId;
	
	/**
	 * sender id
	 */
	private String senderId;
	
//	/** 
//	 * changed to serviceId,serviceVer by Lilei20140818
//	 * SERVICE
//	 */
//	private String service;
	/**
	 * 服务号
	 */
	private String serviceId;
	
	/**
	 * 服务版本号
	 */
	private String serviceVersion;
	
//	/** changed to iccid,by Lilei 20140818
//	 * sim卡唯一标识
//	 */
//	private String secureElement;
	
	/**
	 * 卡的识别码
	 */
	private String iccid;
	
	/**
	 * added 20140818 by Lilei
	 * seId and seType
	 */
	
	/**
	 * 卡识别号
	 */
	private String seId;
	
	/**
	 * 卡识别号类型
	 */
	private String seType;
	
	/**
	 * ACTION (需要数字字典表维护)
	 */
	private String action;
	
//	/** changed to beforeValue and afterValue by Lilei 20140818
//	 * VALUE
//	 */
//	private String value;
	
	/**
	 * 充值前金额
	 */
	private String beforeValue;
	
	/**
	 * 充值后金额
	 */
	private String afterValue;
	
	/**
	 * TSM返回来的msgid
	 */
	private String reMessageId;
	
	/**
	 * FunctionExecutionStatus
	 */
	private String functionExecutionStatus;
	/**
	 * 失败原因
	 */
	private String failReason;
	/**
	 * 主记录序号
	 */
	private Long accountSn;
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
	 * 预留字段4
	 */
	private Integer reserve4;
	
	/**
	 * 更新时间
	 */
	private Date updateDatetime;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the messageId
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/**
	 * @return the senderId
	 */
	public String getSenderId() {
		return senderId;
	}

	/**
	 * @param senderId the senderId to set
	 */
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	/**
	 * @return the serviceId
	 */
	public String getServiceId() {
		return serviceId;
	}

	/**
	 * @param serviceId the serviceId to set
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	/**
	 * @return the serviceVer
	 */
	public String getServiceVersion() {
		return serviceVersion;
	}

	/**
	 * @param serviceVer the serviceVer to set
	 */
	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
	}

	/**
	 * @return the iccid
	 */
	public String getIccid() {
		return iccid;
	}

	/**
	 * @param iccid the iccid to set
	 */
	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	/**
	 * @return the seId
	 */
	public String getSeId() {
		return seId;
	}

	/**
	 * @param seId the seId to set
	 */
	public void setSeId(String seId) {
		this.seId = seId;
	}

	/**
	 * @return the seType
	 */
	public String getSeType() {
		return seType;
	}

	/**
	 * @param seType the seType to set
	 */
	public void setSeType(String seType) {
		this.seType = seType;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the beforeValue
	 */
	public String getBeforeValue() {
		return beforeValue;
	}

	/**
	 * @param beforeValue the beforeValue to set
	 */
	public void setBeforeValue(String beforeValue) {
		this.beforeValue = beforeValue;
	}

	/**
	 * @return the afterValue
	 */
	public String getAfterValue() {
		return afterValue;
	}

	/**
	 * @param afterValue the afterValue to set
	 */
	public void setAfterValue(String afterValue) {
		this.afterValue = afterValue;
	}

	/**
	 * @return the reMessageId
	 */
	public String getReMessageId() {
		return reMessageId;
	}

	/**
	 * @param reMessageId the reMessageId to set
	 */
	public void setReMessageId(String reMessageId) {
		this.reMessageId = reMessageId;
	}

	/**
	 * @return the functionExecutionStatus
	 */
	public String getFunctionExecutionStatus() {
		return functionExecutionStatus;
	}

	/**
	 * @param functionExecutionStatus the functionExecutionStatus to set
	 */
	public void setFunctionExecutionStatus(String functionExecutionStatus) {
		this.functionExecutionStatus = functionExecutionStatus;
	}

	/**
	 * @return the accountSn
	 */
	public Long getAccountSn() {
		return accountSn;
	}

	/**
	 * @param accountSn the accountSn to set
	 */
	public void setAccountSn(Long accountSn) {
		this.accountSn = accountSn;
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

	/**
	 * 获取
	 * @return failReason
	 */
	public String getFailReason() {
		return failReason;
	}

	/**
	 * 设置
	 * @param failReason
	 */
	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	
}
