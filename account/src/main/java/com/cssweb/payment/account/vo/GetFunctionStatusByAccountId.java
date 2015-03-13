/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.vo;

import java.io.Serializable;

/**
 * @author zhanwx
 * @version 0.1 (2014年9月3日 上午10:17:02)
 * @since 0.1
 * @see
 */
public class GetFunctionStatusByAccountId implements Serializable{

	
	private static final long serialVersionUID = 1041153715665023384L;

	/**
	 * 手机号
	 */
	private String phoneNum;
	/**
	 * 短信提醒开通状态
	 */
	private String smsStatus;
	/**
	 * 余额支付开通状态
	 */
	private String payStatus;
	/**
	 * 获取
	 * @return phoneNum
	 */
	public String getPhoneNum() {
		return phoneNum;
	}
	/**
	 * 设置
	 * @param phoneNum
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	/**
	 * 获取
	 * @return smsStatus
	 */
	public String getSmsStatus() {
		return smsStatus;
	}
	/**
	 * 设置
	 * @param smsStatus
	 */
	public void setSmsStatus(String smsStatus) {
		this.smsStatus = smsStatus;
	}
	/**
	 * 获取
	 * @return payStatus
	 */
	public String getPayStatus() {
		return payStatus;
	}
	/**
	 * 设置
	 * @param payStatus
	 */
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
}
