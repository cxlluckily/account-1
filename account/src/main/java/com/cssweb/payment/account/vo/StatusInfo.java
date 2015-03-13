/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.vo;

import java.io.Serializable;

/**
 * 用户状态、实名认证状态、账户状态封装
 * @author zhanwx
 * @version 0.1 (2014年9月19日 下午1:23:45)
 * @since 0.1
 * @see
 */
public class StatusInfo implements Serializable{
	

	private static final long serialVersionUID = -6042791388955285972L;

	/**
	 * 用户id
	 */
	private Long userId;
	
	/**
	 * 用户状态
	 */
	private String userStatus;
	/**
	 * 用户状态显示名
	 */
	private String userStatusName;
	/**
	 * 实名认证状态
	 */
	private String authStatus;
	/**
	 * 实名认证状态显示名
	 */
	private String authStatusName;
	/**
	 * 主账户状态
	 */
	private String mainAccountStatus;
	/**
	 * 主账户状态显示名
	 */
	private String mainAccountStatusName;
	
	/**
	 * 获取
	 * @return userStatus
	 */
	public String getUserStatus() {
		return userStatus;
	}
	/**
	 * 设置
	 * @param userStatus
	 */
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	/**
	 * 获取
	 * @return authStatus
	 */
	public String getAuthStatus() {
		return authStatus;
	}
	/**
	 * 设置
	 * @param authStatus
	 */
	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}
	/**
	 * 获取
	 * @return mainAccountStatus
	 */
	public String getMainAccountStatus() {
		return mainAccountStatus;
	}
	/**
	 * 设置
	 * @param mainAccountStatus
	 */
	public void setMainAccountStatus(String mainAccountStatus) {
		this.mainAccountStatus = mainAccountStatus;
	}

	/**
	 * 获取
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取
	 * @return userStatusName
	 */
	public String getUserStatusName() {
		return userStatusName;
	}
	/**
	 * 设置
	 * @param userStatusName
	 */
	public void setUserStatusName(String userStatusName) {
		this.userStatusName = userStatusName;
	}
	/**
	 * 获取
	 * @return authStatusName
	 */
	public String getAuthStatusName() {
		return authStatusName;
	}
	/**
	 * 设置
	 * @param authStatusName
	 */
	public void setAuthStatusName(String authStatusName) {
		this.authStatusName = authStatusName;
	}
	/**
	 * 获取
	 * @return mainAccountStatusName
	 */
	public String getMainAccountStatusName() {
		return mainAccountStatusName;
	}
	/**
	 * 设置
	 * @param mainAccountStatusName
	 */
	public void setMainAccountStatusName(String mainAccountStatusName) {
		this.mainAccountStatusName = mainAccountStatusName;
	}
	
}
