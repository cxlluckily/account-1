package com.cssweb.payment.account.vo;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONObject;

public class PersonalAuProcessResponse implements Serializable {
	/**
     * 认证步骤
     */
    private String  authStep;
    /**
     * 实名认证状态,实名认证状态,00未认证；01:已认证；02：已审核；03等待确认打款金额；04审核不通过；05等待审核
     */
    private String  authStatus;
    /**
     * 实名认证状态对应中文
     */
    private String  authStatusName;
    /**
     * 认证申请时间
     */
    private Date  authDate;
    
    /**
     * 银行打款时间
     */
    private Date bankTime;
    /**
     * 实名审核描述
     */
    private String authDescribe;
    /**
     * 打款失败描述
     */
    private String paymentDescribe;
    
    /**
     * 打款金额输入错误次数
     */
    private String errorTimes;
    
    
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
	public String getAuthStep() {
		return authStep;
	}
	public void setAuthStep(String authStep) {
		this.authStep = authStep;
	}
	public String getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}
	public Date getAuthDate() {
		return authDate;
	}
	public void setAuthDate(Date authDate) {
		this.authDate = authDate;
	}
	public String getAuthStatusName() {
		return authStatusName;
	}
	public void setAuthStatusName(String authStatusName) {
		this.authStatusName = authStatusName;
	}
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
	/**
	 * 获取
	 * @return errorTimes
	 */
	public String getErrorTimes() {
		return errorTimes;
	}
	/**
	 * 设置
	 * @param errorTimes
	 */
	public void setErrorTimes(String errorTimes) {
		this.errorTimes = errorTimes;
	}
	/**
	 * 获取
	 * @return bankTime
	 */
	public Date getBankTime() {
		return bankTime;
	}
	/**
	 * 设置
	 * @param bankTime
	 */
	public void setBankTime(Date bankTime) {
		this.bankTime = bankTime;
	}
	public String getPaymentDescribe() {
		return paymentDescribe;
	}
	public void setPaymentDescribe(String paymentDescribe) {
		this.paymentDescribe = paymentDescribe;
	}
	
}
