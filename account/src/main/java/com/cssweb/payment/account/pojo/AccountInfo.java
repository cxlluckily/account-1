 package com.cssweb.payment.account.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

import net.sf.json.JSONObject;


/**
 * Title: AccountInfo
 * Description: 账户信息表对应的字段，表的名称为ACM_MAIN_ACCOUNT_INFO,V1.6
 * Company: China Software & Service Company
 * Project:payment-account
 * @author liLei
 * @version 1.0
 * @date 2014年7月23日 下午2:38:14
 * @see CEC支付平台_数据库设计说明书_账户管理模块_V1.6.xlsx
 */
public class AccountInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5478243392071107839L;
	/**
	 * 用户ID，主键值，不能为空
	 */
	private Long userId;
	/**
	 * 账户标志
	 */
	private Long accountId;
	/**
	 * 用户类型
	 */
	private String userType;
	/**
	 * 账户类型
	 */
	private String accountType;
	/**
	 * 账户状态
	 */
	private String accountStatus;
	/**
	 * 币种代码
	 */
	private String currencyType;
	/**
	 * 可用余额
	 */
	private BigDecimal availableBalance;
	/**
	 * 冻结金额
	 */
	private BigDecimal frozenAmount;
	/**
	 * 不可提现金额
	 */
	private BigDecimal nomentionAmount;
	/**
	 * 创建时间
	 */
	private Date createDatetime;
	/**
	 * 校验串，账户信息校验串.账户操作前，要检查MAC值
	 */
	private String mac;
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
	private Date updateDatetime;

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the accountId
	 */
	public Long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * @return the accountType
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	/**
	 * @return the accountStatus
	 */
	public String getAccountStatus() {
		return accountStatus;
	}

	/**
	 * @param accountStatus the accountStatus to set
	 */
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	/**
	 * @return the currencyType
	 */
	public String getCurrencyType() {
		return currencyType;
	}

	/**
	 * @param currencyType the currencyType to set
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}


	/**
	 * @return availableBalance
	 */
	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	/**
	 * @param availableBalance
	 */
	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	/**
	 * @return the frozenAmount
	 */
	public BigDecimal getFrozenAmount() {
		return frozenAmount;
	}

	/**
	 * @param frozenAmount the frozenAmount to set
	 */
	public void setFrozenAmount(BigDecimal frozenAmount) {
		this.frozenAmount = frozenAmount;
	}

	/**
	 * @return the nomentionAmount
	 */
	public BigDecimal getNomentionAmount() {
		return nomentionAmount;
	}

	/**
	 * @param nomentionAmount the nomentionAmount to set
	 */
	public void setNomentionAmount(BigDecimal nomentionAmount) {
		this.nomentionAmount = nomentionAmount;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime the createDatetime to set
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the mac
	 */
	public String getMac() {
		return mac;
	}

	/**
	 * @param mac the mac to set
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}

	/**
	 * @return the reserve1
	 */
	public String getReserve1() {
		return reserve1;
	}

	/**
	 * @param reserve1 the reserve1 to set
	 */
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	/**
	 * @return the reserve2
	 */
	public String getReserve2() {
		return reserve2;
	}

	/**
	 * @param reserve2 the reserve2 to set
	 */
	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	/**
	 * @return the reserve3
	 */
	public Integer getReserve3() {
		return reserve3;
	}

	/**
	 * @param reserve3 the reserve3 to set
	 */
	public void setReserve3(Integer reserve3) {
		this.reserve3 = reserve3;
	}

	/**
	 * @return the reserve4
	 */
	public Integer getReserve4() {
		return reserve4;
	}

	/**
	 * @param reserve4 the reserve4 to set
	 */
	public void setReserve4(Integer reserve4) {
		this.reserve4 = reserve4;
	}

	/**
	 * @return the updateDatetime
	 */
	public Date getUpdateDatetime() {
		return updateDatetime;
	}

	/**
	 * @param updateDatetime the updateDatetime to set
	 */
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

	
	
	
	
	
	
}