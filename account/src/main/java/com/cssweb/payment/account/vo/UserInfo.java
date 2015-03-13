/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.vo;

import java.io.Serializable;

import net.sf.json.JSONObject;

/** 用于接口3.103	根据用户ID查询用户信息接口 的返回值封装
 * 
 * @author shilei@cssweb.com.cn
 * @version 0.1 (2014年8月1日 下午4:20:40)
 * @since 0.1
 * @see
 */
public class UserInfo implements Serializable {

	private static final long serialVersionUID = -6250777978965715602L;

	/** 
	 * 用户类别 
	 */
	private String usertype;
	/** 
	 * 用户类别对应中文
	 */
	private String usertypeName;
	/** 
	 * 用户姓名
	 */
	private String name;
	/** 
	 * 邮箱 
	 */
	private String email;
	/** 
	 * 企业电话
	 */
	private String tel;
	/** 
	 * 个人手机号码
	 */
	private String managerMobile;

	private Long userId;
	/** 
	 * 主账户
	 */
	private Long accountId;
	/** 
	 * 账户状态
	 */
	private String accountStatus;
	/** 
	 * 账户状态对应中文
	 */
	private String accountStatusName;
	/**
	 * 用户状态
	 */
	private String userStatus;
	/**
	 * 用户状态对应中文
	 */
	private String userStatusName;
	

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserStatusName() {
		return userStatusName;
	}

	public void setUserStatusName(String userStatusName) {
		this.userStatusName = userStatusName;
	}

	public String getUsertypeName() {
		return usertypeName;
	}

	public void setUsertypeName(String usertypeName) {
		this.usertypeName = usertypeName;
	}

	/**
	 * @return accountStatus
	 */
	public String getAccountStatus() {
		return accountStatus;
	}

	/**
	 * @param accountStatus
	 */
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	/**
	 * @return accountStatusName
	 */
	public String getAccountStatusName() {
		return accountStatusName;
	}

	/**
	 * @param accountStatusName
	 */
	public void setAccountStatusName(String accountStatusName) {
		this.accountStatusName = accountStatusName;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * 获取
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
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
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 设置
	 * @param accountId
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

	/**
	 * @return managerMobile
	 */
	public String getManagerMobile() {
		return managerMobile;
	}

	/**
	 * @param managerMobile
	 */
	public void setManagerMobile(String managerMobile) {
		this.managerMobile = managerMobile;
	}


}
