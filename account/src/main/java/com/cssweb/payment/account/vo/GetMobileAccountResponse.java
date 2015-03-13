package com.cssweb.payment.account.vo;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class GetMobileAccountResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7638359782648969066L;

	/**
	 * 账号
	 */
	private Long account;
	
	/**
	 * 用户ID
	 */
	private Long userId;
	
	
	private String dpFilePath;
	

	public Long getAccount() {
		return account;
	}

	public void setAccount(Long account) {
		this.account = account;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

	public String getDpFilePath() {
		return dpFilePath;
	}

	public void setDpFilePath(String dpFilePath) {
		this.dpFilePath = dpFilePath;
	}
	
}
