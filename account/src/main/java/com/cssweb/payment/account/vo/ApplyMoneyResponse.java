package com.cssweb.payment.account.vo;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class ApplyMoneyResponse implements Serializable{
	
	/**
	 * 自有资金平台账户Id
	 */
	private String accountId;
	/**
	 * 流水号
	 */
	private long accountSn;
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public long getAccountSn() {
		return accountSn;
	}
	public void setAccountSn(long accountSn) {
		this.accountSn = accountSn;
	}
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

}
