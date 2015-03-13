package com.cssweb.payment.account.pojo;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONObject;

/**
 * Title: BusinessInfo
 * Description: 合作商户信息表对应字段，表名称为ACM_BUSINESS_INFO,V1.6
 * Company: China Software & Service Company
 * Project:payment-account
 * @author liLei
 * @version 1.0
 * @date 2014年7月23日 下午2:59:47
 * @see CEC支付平台_数据库设计说明书_账户管理模块_V1.6.xlsx
 */
public class BusinessInfo implements Serializable{
	
	/**
	 * ID,不能为空
	 */
    private Long  id;
    /**
     * 用户ID，不能为空
     */
    private Long  userId;
    /**
     * 密钥
     */
    private String  merchKey;
    /**
     * 合作有效期
     */
    private Date  expDate;
    /**
     * 网关类型,01：支付网关,02：账户网管 
     */
    private String  gateType;
    /**
     * 接口类型,一般交易、担保交易、协议交易
     */
    private String  channelType;
    
    private String channelTypeName;
    /**
     * 签约状态,默认01签约；02解约
     */
    private String  status;
    /**
     * 商户编号
     */
    private Long  ctNumber;
    /**
     * 商户网站地址
     */
    private String  ctWebUrl;

    /**
     * 接口英文名称
     */
    private String  interEn;
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
	 * @return id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return merchKey
	 */
	public String getMerchKey() {
		return merchKey;
	}
	/**
	 * @param merchKey
	 */
	public void setMerchKey(String merchKey) {
		this.merchKey = merchKey;
	}
	/**
	 * @return expDate
	 */
	public Date getExpDate() {
		return expDate;
	}
	/**
	 * @param expDate
	 */
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	/**
	 * @return gateType
	 */
	public String getGateType() {
		return gateType;
	}
	/**
	 * @param gateType
	 */
	public void setGateType(String gateType) {
		this.gateType = gateType;
	}
	/**
	 * @return channelType
	 */
	public String getChannelType() {
		return channelType;
	}
	/**
	 * @param channelType
	 */
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	/**
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return ctNumber
	 */
	public Long getCtNumber() {
		return ctNumber;
	}
	/**
	 * @param ctNumber
	 */
	public void setCtNumber(Long ctNumber) {
		this.ctNumber = ctNumber;
	}
	/**
	 * @return ctWebUrl
	 */
	public String getCtWebUrl() {
		return ctWebUrl;
	}
	/**
	 * @param ctWebUrl
	 */
	public void setCtWebUrl(String ctWebUrl) {
		this.ctWebUrl = ctWebUrl;
	}
	/**
	 * @return interEn
	 */
	public String getInterEn() {
		return interEn;
	}
	/**
	 * @param interEn
	 */
	public void setInterEn(String interEn) {
		this.interEn = interEn;
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
	
	
	/**
	 * @return channelTypeName
	 */
	public String getChannelTypeName() {
		return channelTypeName;
	}
	/**
	 * @param channelTypeName
	 */
	public void setChannelTypeName(String channelTypeName) {
		this.channelTypeName = channelTypeName;
	}
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

}

