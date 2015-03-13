package com.cssweb.payment.account.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import net.sf.json.JSONObject;

public class FrozenTxResponse implements Serializable{
	/**
	 * 流水号
	 */
	private long accountSn;
	/**
	 * 提现金额
	 */
	private BigDecimal cashAmount;
	/**
	 * 账户号
	 */
	private Long accountId;
	/**
	 * 手续费账户id
	 */
	private Long feeAccountId;
	/**
	 * @return accountSn
	 */
	public long getAccountSn() {
		return accountSn;
	}
	/**
	 * @param accountSn
	 */
	public void setAccountSn(long accountSn) {
		this.accountSn = accountSn;
	}
	/**
	 * @return cashAmount
	 */
	public BigDecimal getCashAmount() {
		return cashAmount;
	}
	/**
	 * @param cashAmount
	 */
	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}
	/**
	 * @return accountId
	 */
	public Long getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return feeAccountId
	 */
	public Long getFeeAccountId() {
		return feeAccountId;
	}
	/**
	 * @param feeAccountId
	 */
	public void setFeeAccountId(Long feeAccountId) {
		this.feeAccountId = feeAccountId;
	}
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

}
