package com.cssweb.payment.account.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.sf.json.JSONObject;
/**
 * 
 * @author zhaoxingwen
 * @version 0.2 (2014年7月19日 上午11:08:39)
 * @since 0.1
 * @see
 */
public class AccountMoneyChgDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3476023472334865181L;
	private Long id;
	/**
	 * 账户流水号
	 */
	private Long accountSn;
	/**
	 * 客户号
	 */
	private Long userId;
	/**
	 * 账号
	 */
	private Long accountId;
	/**
	 * 账户类型
	 */
	private String accountType;
	/**
	 * 变化金额
	 */
	private BigDecimal chgAccount;
	/**
	 * 原金额
	 */
	private BigDecimal oldBalance;
	/**
	 * 变化后余额
	 */
	private BigDecimal balance; 
	/**
	 * 出入金标识
	 */
	private Integer ioFlag;
	/**
	 * 手续费
	 */
	private BigDecimal accountFee;
	/**
	 * 变化时间
	 */
	private Date changeDateTime;
	/**
	 * 状态 记录状态.默认00正常，01已处理。
	 */
	private String status;
	/**
	 * 交易类型
	 */
	private String txnType;
	/**
	 * 预留信息1
	 */
	private String reserve1;
	/**
	 * 预留信息2
	 */
	private String reserve2;
	/**
	 * 预留信息3
	 */
	private Integer reserve3;
	/**
	 * 预留信息4
	 */
	private Integer reserve4;
	/**
	 * 更新时间
	 */
	private Date updateDateTime;
	/**
	 * 获取
	 * @return id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取
	 * @return accountSn
	 */
	public Long getAccountSn() {
		return accountSn;
	}
	/**
	 * 设置
	 * @param accountSn
	 */
	public void setAccountSn(Long accountSn) {
		this.accountSn = accountSn;
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
	 * @return accountId
	 */
	public Long getAccountId() {
		return accountId;
	}
	/**
	 * 设置
	 * @param accountId
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	/**
	 * 获取
	 * @return accountType
	 */
	public String getAccountType() {
		return accountType;
	}
	/**
	 * 设置
	 * @param accountType
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	/**
	 * 获取
	 * @return chgAccount
	 */
	public BigDecimal getChgAccount() {
		return chgAccount;
	}
	/**
	 * 设置
	 * @param chgAccount
	 */
	public void setChgAccount(BigDecimal chgAccount) {
		this.chgAccount = chgAccount;
	}
	/**
	 * 获取
	 * @return oldBalance
	 */
	public BigDecimal getOldBalance() {
		return oldBalance;
	}
	/**
	 * 设置
	 * @param oldBalance
	 */
	public void setOldBalance(BigDecimal oldBalance) {
		this.oldBalance = oldBalance;
	}
	/**
	 * 获取
	 * @return balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}
	/**
	 * 设置
	 * @param balance
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	/**
	 * 获取
	 * @return ioFlag
	 */
	public Integer getIoFlag() {
		return ioFlag;
	}
	/**
	 * 设置
	 * @param ioFlag
	 */
	public void setIoFlag(Integer ioFlag) {
		this.ioFlag = ioFlag;
	}
	/**
	 * 获取
	 * @return accountFee
	 */
	public BigDecimal getAccountFee() {
		return accountFee;
	}
	/**
	 * 设置
	 * @param accountFee
	 */
	public void setAccountFee(BigDecimal accountFee) {
		this.accountFee = accountFee;
	}
	/**
	 * 获取
	 * @return changeDateTime
	 */
	public Date getChangeDateTime() {
		return changeDateTime;
	}
	/**
	 * 设置
	 * @param changeDateTime
	 */
	public void setChangeDateTime(Date changeDateTime) {
		this.changeDateTime = changeDateTime;
	}
	/**
	 * 获取
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取
	 * @return txnType
	 */
	public String getTxnType() {
		return txnType;
	}
	/**
	 * 设置
	 * @param txnType
	 */
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	/**
	 * 获取
	 * @return reserve1
	 */
	public String getReserve1() {
		return reserve1;
	}
	/**
	 * 设置
	 * @param reserve1
	 */
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}
	/**
	 * 获取
	 * @return reserve2
	 */
	public String getReserve2() {
		return reserve2;
	}
	/**
	 * 设置
	 * @param reserve2
	 */
	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}
	/**
	 * 获取
	 * @return reserve3
	 */
	public Integer getReserve3() {
		return reserve3;
	}
	/**
	 * 设置
	 * @param reserve3
	 */
	public void setReserve3(Integer reserve3) {
		this.reserve3 = reserve3;
	}
	/**
	 * 获取
	 * @return reserve4
	 */
	public Integer getReserve4() {
		return reserve4;
	}
	/**
	 * 设置
	 * @param reserve4
	 */
	public void setReserve4(Integer reserve4) {
		this.reserve4 = reserve4;
	}
	/**
	 * 获取
	 * @return updateDateTime
	 */
	public Date getUpdateDateTime() {
		return updateDateTime;
	}
	/**
	 * 设置
	 * @param updateDateTime
	 */
	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	 
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
	
	

	

}
