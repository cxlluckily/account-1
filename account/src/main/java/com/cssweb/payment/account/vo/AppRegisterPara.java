/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.vo;

import java.io.Serializable;

import net.sf.json.JSONObject;

/**App 注册参数
 * @author zhaoxingwen
 * @version 0.1 (2014年7月26日 下午4:50:10)
 * @since 0.1
 * @see
 */
public class AppRegisterPara  implements Serializable {
	/**
	 * 国籍
	 */
	private String countryCode;
	/**
	 * 用户姓名
	 */
	private String userName;
	/**
	 * 证件类型
	 */
	private String idType; 
	/**
	 * 证件号码
	 */
	private String idNumber;
	/**
	 * 手机号码
	 */
	private String phoneNumber; 
	/**
	 * 邮箱
	 */
	private String emailAddress; 
	/**
	 * 登陆密码
	 */
	private String loginPasswd;
	/**
	 * 支付密码
	 */
	private String payPasswd;
	
	public String getCountryCode(){
		return this.countryCode;
	}
	public void setCountryCode(String countryCode){
		this.countryCode = countryCode ;
	}
	
	public String getUserName(){
		return this.userName;
	}
	public void setUserName(String userName){
		this.userName = userName ;
	}
	
	public String getIdType(){
		return this.idType;
	}
	public void setIdType(String idType){
		this.idType = idType ;
	}
	public String getIdNumber(){
		return this.idNumber;
	}
	public void setIdNumber(String idNumber){
		this.idNumber = idNumber ;
	}
	
	public String getPhoneNumber(){
		return this.phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber ;
	}
	public String getEmailAddress(){
		return this.emailAddress;
	}
	public void setEmailAddress(String emailAddress){
		this.emailAddress = emailAddress ;
	}
	
	public String getLoginPasswd(){
		return this.loginPasswd;
	}
	public void setLoginPasswd(String loginPasswd){
		this.loginPasswd = loginPasswd ;
	}
	
	public String getPayPasswd(){
		return this.payPasswd;
	}
	public void setPayPasswd(String payPasswd){
		this.payPasswd = payPasswd ;
	}
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

}
