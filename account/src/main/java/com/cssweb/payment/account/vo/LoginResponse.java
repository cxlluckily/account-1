/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.vo;

import java.io.Serializable;

import net.sf.json.JSONObject;

import com.cssweb.payment.account.pojo.AccountInfo;
import com.cssweb.payment.account.pojo.EnterpriseUser;
import com.cssweb.payment.account.pojo.PersonalUser;

/**
 * 3.22 用户登录接口返回的对象
 * @author Ganlin
 * @version 0.1 (2014年7月23日 下午8:08:38)
 * @since 0.1
 * @see
 */
public class LoginResponse implements Serializable{

	private static final long serialVersionUID = 7387923742502127603L;
	/**
	 * 用户类型
	 */
	private String userType;
	/**
	 * 个人用户信息-仅个人用户登陆时有值
	 */
	private PersonalUser personalUser;
	/**
	 * 企业用户信息-仅企业用户登陆时有值
	 */	
	private EnterpriseUser enterpriseUser;
	/**
	 * 主账户信息
	 */
	private AccountInfo accountInfo;
	/**
	 * 密码输入错误次数
	 */
	private Integer errorTimes;
	
	public Integer getErrorTimes() {
		return errorTimes;
	}
	public void setErrorTimes(Integer errorTimes) {
		this.errorTimes = errorTimes;
	}
	/**
	 * @return userType
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * @param userType
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * @return personalUser
	 */
	public PersonalUser getPersonalUser() {
		return personalUser;
	}
	/**
	 * @param personalUser
	 */
	public void setPersonalUser(PersonalUser personalUser) {
		this.personalUser = personalUser;
	}
	/**
	 * @return enterpriseUser
	 */
	public EnterpriseUser getEnterpriseUser() {
		return enterpriseUser;
	}
	/**
	 * @param enterpriseUser
	 */
	public void setEnterpriseUser(EnterpriseUser enterpriseUser) {
		this.enterpriseUser = enterpriseUser;
	}
	/**
	 * @return accountInfo
	 */
	public AccountInfo getAccountInfo() {
		return accountInfo;
	}
	/**
	 * @param accountInfo
	 */
	public void setAccountInfo(AccountInfo accountInfo) {
		this.accountInfo = accountInfo;
	}
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

}
