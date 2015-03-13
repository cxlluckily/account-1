package com.cssweb.payment.account.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class RiskAccountInfo implements Serializable {

	private static final long serialVersionUID = 494305705187808105L;
	
	/**
	 * 当天交易金额
	 */
	private BigDecimal amountDay;
	/**
	 * 当天交易次数
	 */
	private Integer timesDay;
	/**
	 * 当月交易金额
	 */
	private BigDecimal amountMonth;
	/**
	 * 当月交易次数
	 */
	private Integer timesMonth;
	/**
	 * 当前账户余额
	 */
	private BigDecimal balance;
	/**
	 * 当天接受退款的次数
	 */
	private Integer timesRefundReciveDay;
	/**
	 * 当天接受退款的金额
	 */
	private BigDecimal amountRefundReciveDay;
	
	/**
	 * @return amountDay
	 */
	public BigDecimal getAmountDay() {
		return amountDay;
	}
	/**
	 * @param amountDay
	 */
	public void setAmountDay(BigDecimal amountDay) {
		this.amountDay = amountDay;
	}
	/**
	 * @return timesDay
	 */
	public Integer getTimesDay() {
		return timesDay;
	}
	/**
	 * @param timesDay
	 */
	public void setTimesDay(Integer timesDay) {
		this.timesDay = timesDay;
	}
	/**
	 * @return amountMonth
	 */
	public BigDecimal getAmountMonth() {
		return amountMonth;
	}
	/**
	 * @param amountMonth
	 */
	public void setAmountMonth(BigDecimal amountMonth) {
		this.amountMonth = amountMonth;
	}
	/**
	 * @return timesMonth
	 */
	public Integer getTimesMonth() {
		return timesMonth;
	}
	/**
	 * @param timesMonth
	 */
	public void setTimesMonth(Integer timesMonth) {
		this.timesMonth = timesMonth;
	}
	/**
	 * @return balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}
	/**
	 * @param balance
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	/**
	 * @return timesRefundReciveDay
	 */
	public Integer getTimesRefundReciveDay() {
		return timesRefundReciveDay;
	}
	/**
	 * @param timesRefundReciveDay
	 */
	public void setTimesRefundReciveDay(Integer timesRefundReciveDay) {
		this.timesRefundReciveDay = timesRefundReciveDay;
	}
	/**
	 * @return amountRefundReciveDay
	 */
	public BigDecimal getAmountRefundReciveDay() {
		return amountRefundReciveDay;
	}
	/**
	 * @param amountRefundReciveDay
	 */
	public void setAmountRefundReciveDay(BigDecimal amountRefundReciveDay) {
		this.amountRefundReciveDay = amountRefundReciveDay;
	}
	@Override
	public String toString() {
		return String.format("RiskAccountInfo [amountDay=%s, timesDay=%s, amountMonth=%s, timesMonth=%s, balance=%s]",
				amountDay, timesDay, amountMonth, timesMonth, balance);
	}

}
