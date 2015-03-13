package com.cssweb.payment.account.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.math.BigInteger;

import net.sf.json.JSONObject;

/**
 * 
 * @author zhaoxingwen
 * @version 0.2 (2014年7月22日 上午11:08:39)
 * @since 0.1
 * @see
 */
public class AccountOperDetail implements Serializable {
	/**
	 * id
	 */
	private	Long id;
	/**
	 * 主记录序号
	 */
	private	Long accountSn;
	/**
	 * 进出账标志
	 */
	private	Integer ioFlag;
	/**
	 * 账户Id
	 */
	private	Long accountId;
	/**
	 * 账户位置
	 */
	private	String accountPosition;
	/**
	 * 变化金额
	 */
	private	BigDecimal amount;
	/**
	 * 状态
	 */
	private	String status;
	/**
	 * 预留信息1
	 */
	private	String reserve1;
	/**
	 * 预留信息2
	 */
	private	String reserve2;
	/**
	 * 预留信息3
	 */
	private	Integer reserve3;
	/**
	 * 预留信息4
	 */
	private	Integer reserve4;
	/**
	 * 更新时间
	 */
	private	Date updateDatetime;
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
	 * @return accountPosition
	 */
	public String getAccountPosition() {
		return accountPosition;
	}
	/**
	 * 设置
	 * @param accountPosition
	 */
	public void setAccountPosition(String accountPosition) {
		this.accountPosition = accountPosition;
	}
	/**
	 * 获取
	 * @return amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * 设置
	 * @param amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
	 * @return updateDatetime
	 */
	public Date getUpdateDatetime() {
		return updateDatetime;
	}
	/**
	 * 设置
	 * @param updateDatetime
	 */
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

	
}
