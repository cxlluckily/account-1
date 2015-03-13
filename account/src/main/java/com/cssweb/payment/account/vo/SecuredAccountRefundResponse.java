package com.cssweb.payment.account.vo;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class SecuredAccountRefundResponse implements Serializable{
	
	/**
	 * 支付帐户
	 */
	private long fromaccountid;
	/**
	 * 收入帐户
	 */
	private long toaccountid;
	/**
	 * 账户退款流水号
	 */
	private long accountSn;
	
	
	public long getFromaccountid() {
		return fromaccountid;
	}
	public void setFromaccountid(long fromaccountid) {
		this.fromaccountid = fromaccountid;
	}
	public long getToaccountid() {
		return toaccountid;
	}
	public void setToaccountid(long toaccountid) {
		this.toaccountid = toaccountid;
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
