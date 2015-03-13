/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.pojo;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONObject;

/**
 * 
 * @author zhanwx
 * @version 0.2 (2014年7月19日 下午5:03:41)
 * @since 0.1
 * @see
 */
public class UserBandBankInfo implements Serializable{

	/**
	 * 序号ID
	 */
	private Long id;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 银行卡帐号
	 */
	private String bankAccountNo;
	/**
	 * 开户行ID
	 */
	private String bankId;
	/**
	 * 银行卡类型
	 */
	private String bankCardType;
	/**
	 * 银行卡绑定状态
	 */
	private String bandStatus;
	/**
	 * 银行卡有效期
	 */
	private String expirDate;
	/**
	 * 银行卡协议代码
	 */
	private String cvcCode;
	/**
	 * 银行卡开户人姓名
	 */
	private String holderName;
	/**
	 * 银行卡用途 提现
	 */
	private String usetypeWithdraw;
	/**
	 * 银行卡用途 快捷
	 */
	private String usetypeShortcut;
	/**
	 * 银行卡开户网点全称
	 */
	private String branchName;
	/**
	 * 绑定联系电话
	 */
	private String bandPhoneNo;
	/**
	 * 绑定证件类型
	 */
	private String bandCertType;
	/**
	 * 绑定证件号码
	 */
	private String authCertId;
	/**
	 * 开户行所在省
	 */
	private String bankProvince;
	/**
	 * 开户行所在市
	 */
	private String bankCity;
	/**
	 * 创建日期
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
	
	
	/* ***********  以下是get和set方法   ************/
	
	
	
	/**
	 * 获取 id
	 * @return id
	 */
	public Long getId() {
		return id;
	}
	public String getBankProvince() {
		return bankProvince;
	}
	public void setBankProvince(String bankProvince) {
		this.bankProvince = bankProvince;
	}
	
	public String getBankCity() {
		return bankCity;
	}
	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}
	public String getBandPhoneNo() {
		return bandPhoneNo;
	}
	/**
	 * 获取 userId
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 获取 bankAccountNo
	 * @return bankAccountNo
	 */
	public String getBankAccountNo() {
		return bankAccountNo;
	}
	/**
	 * 获取
	 * @return bankId
	 */
	public String getBankId() {
		return bankId;
	}
	/**
	 * 获取
	 * @return bankCardType
	 */
	public String getBankCardType() {
		return bankCardType;
	}
	/**
	 * 获取
	 * @return bankStatus
	 */
	public String getBandStatus() {
		return bandStatus;
	}
	/**
	 * 获取
	 * @return expirDate
	 */
	public String getExpirDate() {
		return expirDate;
	}
	/**
	 * 获取
	 * @return cvcCode
	 */
	public String getCvcCode() {
		return cvcCode;
	}
	/**
	 * 获取
	 * @return holderName
	 */
	public String getHolderName() {
		return holderName;
	}
	/**
	 * 获取
	 * @return branchName
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * 获取
	 * @return bandPhoneNo
	 */
	public String mobileService() {
		return bandPhoneNo;
	}
	/**
	 * 获取
	 * @return bandCertType
	 */
	public String getBandCertType() {
		return bandCertType;
	}
	/**
	 * 获取
	 * @return authCertId
	 */
	public String getAuthCertId() {
		return authCertId;
	}
	/**
	 * 获取
	 * @return createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}
	/**
	 * 获取
	 * @return reserve1
	 */
	public String getReserve1() {
		return reserve1;
	}
	/**
	 * 获取
	 * @return reserve2
	 */
	public String getReserve2() {
		return reserve2;
	}
	/**
	 * 获取
	 * @return reserve3
	 */
	public Integer getReserve3() {
		return reserve3;
	}
	/**
	 * 获取
	 * @return reserve4
	 */
	public Integer getReserve4() {
		return reserve4;
	}
	/**
	 * 获取
	 * @return updateDatetime
	 */
	public Date getUpdateDatetime() {
		return updateDatetime;
	}
	/**
	 * 获取 银行卡用途_提现 开通情况
	 * @return usetypeWithdraw
	 */
	public String getUsetypeWithdraw() {
		return usetypeWithdraw;
	}
	/**
	 * 获取 银行卡用途_快捷 开通情况
	 * @return usetypeShortcut
	 */
	public String getUsetypeShortcut() {
		return usetypeShortcut;
	}
	/**
	 * 设置 银行卡用途_提现 开通情况
	 * @param usetypeWithdraw
	 */
	public void setUsetypeWithdraw(String usetypeWithdraw) {
		this.usetypeWithdraw = usetypeWithdraw;
	}
	/**
	 * 设置 银行卡用途_快捷 开通情况
	 * @param usetypeShortcut
	 */
	public void setUsetypeShortcut(String usetypeShortcut) {
		this.usetypeShortcut = usetypeShortcut;
	}
	/**
	 * 设置
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 设置
	 * @param bankAccountNo
	 */
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	/**
	 * 设置
	 * @param bankId
	 */
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	/**
	 * 设置
	 * @param bankCardType
	 */
	public void setBankCardType(String bankCardType) {
		this.bankCardType = bankCardType;
	}
	/**
	 * 设置
	 * @param bankStatus
	 */
	public void setBandStatus(String bandStatus) {
		this.bandStatus = bandStatus;
	}
	/**
	 * 设置
	 * @param expirDate
	 */
	public void setExpirDate(String expirDate) {
		this.expirDate = expirDate;
	}
	/**
	 * 设置
	 * @param cvcCode
	 */
	public void setCvcCode(String cvcCode) {
		this.cvcCode = cvcCode;
	}
	/**
	 * 设置
	 * @param holderName
	 */
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	
	/**
	 * 设置
	 * @param branchName
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	/**
	 * 设置
	 * @param bandPhoneNo
	 */
	public void setBandPhoneNo(String bandPhoneNo) {
		this.bandPhoneNo = bandPhoneNo;
	}
	/**
	 * 设置
	 * @param bandCertType
	 */
	public void setBandCertType(String bandCertType) {
		this.bandCertType = bandCertType;
	}
	/**
	 * 设置
	 * @param authCertId
	 */
	public void setAuthCertId(String authCertId) {
		this.authCertId = authCertId;
	}
	/**
	 * 设置
	 * @param createDatetime
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	/**
	 * 设置
	 * @param reserve1
	 */
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}
	/**
	 * 设置
	 * @param reserve2
	 */
	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}
	/**
	 * 设置
	 * @param reserve3
	 */
	public void setReserve3(Integer reserve3) {
		this.reserve3 = reserve3;
	}
	/**
	 * 设置
	 * @param reserve4
	 */
	public void setReserve4(Integer reserve4) {
		this.reserve4 = reserve4;
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