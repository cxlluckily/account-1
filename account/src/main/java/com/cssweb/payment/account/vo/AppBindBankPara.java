/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.vo;

import java.io.Serializable;

import net.sf.json.JSONObject;

/**
 * @author zhaoxingwen
 * @version 0.1 (2014年7月26日 下午5:31:08)
 * @since 0.1
 * @see
 * 
 * ------------------------------------------------------------------------------------------------------------------
 * 修改记录
 * 增加绑定银行卡用途 20140728
 */
public class AppBindBankPara  implements Serializable {
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 银行卡类型
	 */
	private String bankCardType;
	/**
	 * 卡号
	 */
	private String bankAccountNo;
	/**
	 * 银行卡开户人姓名
	 */
	private String holderName;
	/**
	 * 绑定用途
	 */
	private String purpose;
	/**
	 * 银行卡有效期
	 */
	private String expirDate;
	/**
	 * 银行卡协议代码
	 */
	private String cvcCode;
	/**
	 * 证件类型
	 */
	private String idType;
	/**
	 * 证件号码
	 */
	private String idNumber;
	/**
	 * 银行卡交易密码
	 */
	private String bankCardPassword;
	/**
	 * 银行卡用途 提现
	 */
	private String usetypeWithdraw;
	/**
	 * 银行卡用途 快捷支付
	 */
	private String usetypeShortcut;
	
	public Long  getUserId(){
		return this.userId;
	}
	public void setUserId(Long userId){
		this.userId = userId;
	}

	public String  getBankName(){
		return this.bankName;
	}
	public void setBankName(String bankName){
		this.bankName = bankName;
	}

	public String  getBankCardType(){
		return this.bankCardType;
	}
	public void setBankCardType(String bankCardType){
		this.bankCardType = bankCardType;
	}

	public String  getBankAccountNo(){
		return this.bankAccountNo;
	}
	public void setBankAccountNo(String bankAccountNo){
		this.bankAccountNo = bankAccountNo;
	}

	public String  getHolderName(){
		return this.holderName;
	}
	public void setHolderName(String holderName){
		this.holderName = holderName;
	}
	
	public String  getExpirDate(){
		return this.expirDate;
	}
	public void setExpirDate(String expirDate){
		this.expirDate = expirDate;
	}
	
	public String  getCvcCode(){
		return this.cvcCode;
	}
	public void setCvcCode(String cvcCode){
		this.cvcCode = cvcCode;
	}

	public String  getIdType(){
		return this.idType;
	}
	public void setIdType(String idType){
		this.idType = idType;
	}
	
	public String  getIdNumber(){
		return this.idNumber;
	}
	public void setIdNumber(String idNumber){
		this.idNumber = idNumber;
	}
	
	public String  getBankCardPassword(){
		return this.bankCardPassword;
	}
	public void setBankCardPassword(String bankCardPassword){
		this.bankCardPassword = bankCardPassword;
	}
	
	public String getPurpose(){
		return this.purpose;
	}
	public void setPurpose(String purpose){
		this.purpose = purpose;
	}

	public String toString() {
		return JSONObject.fromObject(this).toString();
	}	/**
	 * 获取
	 * @return usetypeWithdraw
	 */
	public String getUsetypeWithdraw() {
		return usetypeWithdraw;
	}
	/**
	 * 获取
	 * @return usetypeShortcut
	 */
	public String getUsetypeShortcut() {
		return usetypeShortcut;
	}
	/**
	 * 设置
	 * @param usetypeWithdraw
	 */
	public void setUsetypeWithdraw(String usetypeWithdraw) {
		this.usetypeWithdraw = usetypeWithdraw;
	}
	/**
	 * 设置
	 * @param usetypeShortcut
	 */
	public void setUsetypeShortcut(String usetypeShortcut) {
		this.usetypeShortcut = usetypeShortcut;
	}
}
