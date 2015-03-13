package com.cssweb.payment.account.pojo;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONObject;

public class BankInfo  implements Serializable {
	
	private static final long serialVersionUID = -1629183853512634788L;

	private Long id;
	
	/**
	 * 平台账户id
	 */
	private Long  paymentId;
	
	/**
	 * 银行ID
	 */
	private String  bankId;
	/**
	 * 银行账户
	 */
	private String  bankAccount;
	/**
	 * 银行名称
	 */
	private String  bankName;
	/**
	 * 合作类型
	 */
	private String  cooperationType;
	/**
	 * 落地支行名称
	 */
	private String  branchBankName;  
	/**
	 * 支行级别
	 */
	private String  branchBankLevel;
	/**
	 * 银行联系人姓名
	 */
	private String  contactName; 
	/**
	 * 联系人电话
	 */
	private String  contactTel; 
	/**
	 * 银行联系地址
	 */
	private String  bankAddress; 
	/**
	 * 银行联系电话
	 */
	private String  bankTel;
	/**
	 * 商户号
	 */
	private String  merchantNo;
	/**
	 * 密钥
	 */
	private String  merchantKey; 
	/**
	 * URL
	 */
	private String  bankUrl; 
	/**
	 * 邮编
	 */
	private String  postalCode;
	/**
	 * 传真
	 */
	private String  fax;
	/**
	 * 更新时间
	 */
	private Date  updateDatetime;
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
	 * @return the paymentId
	 */
	public Long getPaymentId() {
		return paymentId;
	}

	/**
	 * @param paymentId the paymentId to set
	 */
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	/**
	 * @return the bankId
	 */
	public String getBankId() {
		return bankId;
	}

	/**
	 * @param bankId the bankId to set
	 */
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the cooperationType
	 */
	public String getCooperationType() {
		return cooperationType;
	}

	/**
	 * @param cooperationType the cooperationType to set
	 */
	public void setCooperationType(String cooperationType) {
		this.cooperationType = cooperationType;
	}

	/**
	 * @return the branchBankName
	 */
	public String getBranchBankName() {
		return branchBankName;
	}

	/**
	 * @param branchBankName the branchBankName to set
	 */
	public void setBranchBankName(String branchBankName) {
		this.branchBankName = branchBankName;
	}

	/**
	 * @return the branchBankLevel
	 */
	public String getBranchBankLevel() {
		return branchBankLevel;
	}

	/**
	 * @param branchBankLevel the branchBankLevel to set
	 */
	public void setBranchBankLevel(String branchBankLevel) {
		this.branchBankLevel = branchBankLevel;
	}

	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * @param contactName the contactName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * @return the contactTel
	 */
	public String getContactTel() {
		return contactTel;
	}

	/**
	 * @param contactTel the contactTel to set
	 */
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	/**
	 * @return the bankAddress
	 */
	public String getBankAddress() {
		return bankAddress;
	}

	/**
	 * @param bankAddress the bankAddress to set
	 */
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	/**
	 * @return the bankTel
	 */
	public String getBankTel() {
		return bankTel;
	}

	/**
	 * @param bankTel the bankTel to set
	 */
	public void setBankTel(String bankTel) {
		this.bankTel = bankTel;
	}

	/**
	 * @return the merchantNo
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * @param merchantNo the merchantNo to set
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * @return the merchantKey
	 */
	public String getMerchantKey() {
		return merchantKey;
	}

	/**
	 * @param merchantKey the merchantKey to set
	 */
	public void setMerchantKey(String merchantKey) {
		this.merchantKey = merchantKey;
	}

	/**
	 * @return the bankUrl
	 */
	public String getBankUrl() {
		return bankUrl;
	}

	/**
	 * @param bankUrl the bankUrl to set
	 */
	public void setBankUrl(String bankUrl) {
		this.bankUrl = bankUrl;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the updateDateTime
	 */
	public Date getUpdateDatetime() {
		return updateDatetime;
	}

	/**
	 * @param updateDateTime the updateDateTime to set
	 */
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
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

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}


}
