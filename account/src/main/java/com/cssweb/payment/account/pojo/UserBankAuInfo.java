package com.cssweb.payment.account.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.sf.json.JSONObject;

/**
 * Title: UserBankAuInfo Description:
 * 用户银行打款认证信息表对应字段，表名称ACM_USER_BANK_AU_INFO，V1.6 Company: China Software &
 * Service Company Project:payment-account
 * 
 * @author liLei
 * @version 1.0
 * @date 2014年7月23日 下午3:43:35
 * @see CEC支付平台_数据库设计说明书_账户管理模块_V1.6.xlsx
 */
public class UserBankAuInfo implements Serializable {
	/**
	 * id
	 */
	private Long id;
	/**
	 * 账户ID
	 */
	private Long userId;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 银行卡号
	 */
	private String bankNo;
	/**
	 * 打款金额
	 */
	private BigDecimal bandAmount;
	/**
	 * 打款结果,银行返回的打款结果。00:成功,01失败
	 */
	private String bankRestatus;
	/**
	 * 认证次数
	 */
	private Integer authNum;
	/**
	 * 创建时间
	 */
	private Date userAuDateTime;
	/**
	 * 银行打款时间
	 */
	private Date bankTime;
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

	// -------2014.8.1 sl:add ： bank_province、
	// bank_city、bank_cnaps、bank__internal 字段--------
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
	private String note;
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
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

	public String getBankCnaps() {
		return bankCnaps;
	}

	public void setBankCnaps(String bankCnaps) {
		this.bankCnaps = bankCnaps;
	}

	public String getBankInternal() {
		return bankInternal;
	}

	public void setBankInternal(String bankInternal) {
		this.bankInternal = bankInternal;
	}

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
	 * @return the userId
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
	 * @return the bankName
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
	 * @return the bankNo
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
	 * @return the bandAmount
	 */
	public BigDecimal getBandAmount() {
		return bandAmount;
	}

	/**
	 * @param bandAmount
	 */
	public void setBandAmount(BigDecimal bandAmount) {
		this.bandAmount = bandAmount;
	}

	/**
	 * @return the bankRestatus
	 */
	public String getBankRestatus() {
		return bankRestatus;
	}

	/**
	 * @param bankRestatus
	 */
	public void setBankRestatus(String bankRestatus) {
		this.bankRestatus = bankRestatus;
	}

	/**
	 * @return the authNum
	 */
	public Integer getAuthNum() {
		return authNum;
	}

	/**
	 * @param authNum
	 */
	public void setAuthNum(Integer authNum) {
		this.authNum = authNum;
	}

	/**
	 * @return the userAuDateTime
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
	 * @return the bankTime
	 */
	public Date getBankTime() {
		return bankTime;
	}

	/**
	 * @param bankTime
	 */
	public void setBankTime(Date bankTime) {
		this.bankTime = bankTime;
	}

	/**
	 * @return the reserve1
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
	 * @return the reserve2
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
	 * @return the reserve3
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
	 * @return the reserve4
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
	 * @return the updateDatetime
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

}