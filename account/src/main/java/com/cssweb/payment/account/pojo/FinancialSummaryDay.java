package com.cssweb.payment.account.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FinancialSummaryDay implements Serializable {

	/**
	 * 商户日结算表
	 */
	private static final long serialVersionUID = 7646172323488483722L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 日期(日)
	 */
	private Date day;

	/**
	 * 收入（元）
	 */
	private BigDecimal income;
	/**
	 * 收入总笔数
	 */
	private Long incomeTotal;
	/**
	 * 卖出（元）
	 */
	private BigDecimal sellMoneyIncome;
	/**
	 * 卖出笔数
	 */
	private Long sellNumIncome;
	/**
	 * 其它（元）
	 */
	private BigDecimal otherMoneyIncome;
	/**
	 * 其它笔数
	 */
	private Long otherNumIncome;
	/**
	 * 支出（元）
	 */
	private BigDecimal spending;
	/**
	 * 支出总笔数
	 */
	private Long spendingTotal;
	/**
	 * 提现（元）
	 */
	private BigDecimal withdrawalSpending;
	/**
	 * 提现笔数
	 */
	private Long withdrawalNumSpending;
	/**
	 * 退款（元）
	 */
	private BigDecimal refundSpending;
	/**
	 * 退款笔数
	 */
	private Long refundNumSpending;
	/**
	 * 期初余额（元）
	 */
	private BigDecimal beginBalance;
	/**
	 * 期末余额（元）
	 */
	private BigDecimal endBalance;
	/**
	 * 商户ID
	 */
	private Long enterId;

	/**
	 * 
	 */
	private Long ext1;
	/**
	 * 
	 */
	private Long ext2;
	/**
	 * 
	 */
	private String ext3;
	/**
	 * 
	 */
	private String ext4;

	/**
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return day
	 */
	public Date getDay() {
		return day;
	}

	/**
	 * @param day
	 */
	public void setDay(Date day) {
		this.day = day;
	}

	/**
	 * @return income
	 */
	public BigDecimal getIncome() {
		return income;
	}

	/**
	 * @param income
	 */
	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	/**
	 * @return incomeTotal
	 */
	public Long getIncomeTotal() {
		return incomeTotal;
	}

	/**
	 * @param incomeTotal
	 */
	public void setIncomeTotal(Long incomeTotal) {
		this.incomeTotal = incomeTotal;
	}

	/**
	 * @return sellMoneyIncome
	 */
	public BigDecimal getSellMoneyIncome() {
		return sellMoneyIncome;
	}

	/**
	 * @param sellMoneyIncome
	 */
	public void setSellMoneyIncome(BigDecimal sellMoneyIncome) {
		this.sellMoneyIncome = sellMoneyIncome;
	}

	/**
	 * @return sellNumIncome
	 */
	public Long getSellNumIncome() {
		return sellNumIncome;
	}

	/**
	 * @param sellNumIncome
	 */
	public void setSellNumIncome(Long sellNumIncome) {
		this.sellNumIncome = sellNumIncome;
	}

	/**
	 * @return otherMoneyIncome
	 */
	public BigDecimal getOtherMoneyIncome() {
		return otherMoneyIncome;
	}

	/**
	 * @param otherMoneyIncome
	 */
	public void setOtherMoneyIncome(BigDecimal otherMoneyIncome) {
		this.otherMoneyIncome = otherMoneyIncome;
	}

	/**
	 * @return otherNumIncome
	 */
	public Long getOtherNumIncome() {
		return otherNumIncome;
	}

	/**
	 * @param otherNumIncome
	 */
	public void setOtherNumIncome(Long otherNumIncome) {
		this.otherNumIncome = otherNumIncome;
	}

	/**
	 * @return spending
	 */
	public BigDecimal getSpending() {
		return spending;
	}

	/**
	 * @param spending
	 */
	public void setSpending(BigDecimal spending) {
		this.spending = spending;
	}

	/**
	 * @return spendingTotal
	 */
	public Long getSpendingTotal() {
		return spendingTotal;
	}

	/**
	 * @param spendingTotal
	 */
	public void setSpendingTotal(Long spendingTotal) {
		this.spendingTotal = spendingTotal;
	}

	/**
	 * @return withdrawalSpending
	 */
	public BigDecimal getWithdrawalSpending() {
		return withdrawalSpending;
	}

	/**
	 * @param withdrawalSpending
	 */
	public void setWithdrawalSpending(BigDecimal withdrawalSpending) {
		this.withdrawalSpending = withdrawalSpending;
	}

	/**
	 * @return withdrawalNumSpending
	 */
	public Long getWithdrawalNumSpending() {
		return withdrawalNumSpending;
	}

	/**
	 * @param withdrawalNumSpending
	 */
	public void setWithdrawalNumSpending(Long withdrawalNumSpending) {
		this.withdrawalNumSpending = withdrawalNumSpending;
	}

	/**
	 * @return refundSpending
	 */
	public BigDecimal getRefundSpending() {
		return refundSpending;
	}

	/**
	 * @param refundSpending
	 */
	public void setRefundSpending(BigDecimal refundSpending) {
		this.refundSpending = refundSpending;
	}

	/**
	 * @return refundNumSpending
	 */
	public Long getRefundNumSpending() {
		return refundNumSpending;
	}

	/**
	 * @param refundNumSpending
	 */
	public void setRefundNumSpending(Long refundNumSpending) {
		this.refundNumSpending = refundNumSpending;
	}

	/**
	 * @return beginBalance
	 */
	public BigDecimal getBeginBalance() {
		return beginBalance;
	}

	/**
	 * @param beginBalance
	 */
	public void setBeginBalance(BigDecimal beginBalance) {
		this.beginBalance = beginBalance;
	}

	/**
	 * @return endBalance
	 */
	public BigDecimal getEndBalance() {
		return endBalance;
	}

	/**
	 * @param endBalance
	 */
	public void setEndBalance(BigDecimal endBalance) {
		this.endBalance = endBalance;
	}

	/**
	 * @return enterId
	 */
	public Long getEnterId() {
		return enterId;
	}

	/**
	 * @param enterId
	 */
	public void setEnterId(Long enterId) {
		this.enterId = enterId;
	}

	/**
	 * @return ext1
	 */
	public Long getExt1() {
		return ext1;
	}

	/**
	 * @param ext1
	 */
	public void setExt1(Long ext1) {
		this.ext1 = ext1;
	}

	/**
	 * @return ext2
	 */
	public Long getExt2() {
		return ext2;
	}

	/**
	 * @param ext2
	 */
	public void setExt2(Long ext2) {
		this.ext2 = ext2;
	}

	/**
	 * @return ext3
	 */
	public String getExt3() {
		return ext3;
	}

	/**
	 * @param ext3
	 */
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	/**
	 * @return ext4
	 */
	public String getExt4() {
		return ext4;
	}

	/**
	 * @param ext4
	 */
	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}

}
