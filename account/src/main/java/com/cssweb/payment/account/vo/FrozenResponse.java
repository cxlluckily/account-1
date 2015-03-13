package com.cssweb.payment.account.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import net.sf.json.JSONObject;

public class FrozenResponse implements Serializable{
	/**
	 * 流水号
	 */
	private long accountSn;
	/**
	 * 冻结金额
	 */
	private BigDecimal cashAmount;
	/**
	 * 帐户号
	 */
	private long accountid;
	
	public BigDecimal getCashAmount() {
		return cashAmount;
	}
	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}
	public long getAccountSn() {
		return accountSn;
	}
	public void setAccountSn(long accountSn) {
		this.accountSn = accountSn;
	}
	public long getAccountid() {
		return accountid;
	}
	public void setAccountid(long accountid) {
		this.accountid = accountid;
	}
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

	
}
