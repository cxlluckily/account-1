package com.cssweb.payment.account.vo;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class RiskAuthStatus implements Serializable {

	/**
	 * 认证状态
	 */
	private String status;
	/**
	 * 用户类型
	 */
	private String userType;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

}
