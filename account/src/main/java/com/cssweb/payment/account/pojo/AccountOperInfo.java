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
public class AccountOperInfo implements Serializable {
	/**
	 * 记录序号
	 */
	private Long accountSn;
	/**
	 * 接口名称
	 */
	private String interfaceName;  
	/**
	 * 来源
	 */
	private String source;
	/**
	 * 出金账户
	 */
	private Long fromAccountId;
	/**
	 * 入金账户
	 */
	private Long toAccountId; 
	/**
	 * 变化金额
	 */
	private BigDecimal amount;
	/**
	 * 账户变动描述
	 */
	private String memo;
	/**
	 * 手续费
	 */
	private BigDecimal fee;
	/**
	 * 交易流水号
	 */
	private String txnSeq; 
	/**
	 * 消费/充值终端主流水
	 */
	private Long hostNo; 
	/**
	 * 操作员号
	 */
	private String operId; 
	/**
	 * 交易类型
	 */
	private String txnType; 
	/**
	 * 商户标志
	 */
	private String merchantId; 
	/**
	 * 银行ID
	 */
	private String bankId; 
	/**
	 * 银行账号
	 */
	private String bankAccount;
	/**
	 * 外部系统订单号
	 */
	private String externalOrderNo; 
	/**
	 * 退款单号
	 */
    private String refundOrderNo;  
    /**
	 * 状态
	 */
	private String status; 
	/**
	 * 冲正标志
	 */
	private String reverseFlag;  
	/**
	 * 预留信息1
	 */
	private String reserver1;  
	/**
	 * 预留信息2
	 */
	private String reserver2; 
	/**
	 * 预留信息3
	 */
	private Integer reserver3; 
	/**
	 * 预留信息4
	 */
	private Integer reserver4; 
	/**
	 * 更新时间
	 */
	private Date updateDateTime;

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
	 * @return interfaceName
	 */
	public String getInterfaceName() {
		return interfaceName;
	}

	/**
	 * 设置
	 * @param interfaceName
	 */
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	/**
	 * 获取
	 * @return source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * 设置
	 * @param source
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * 获取
	 * @return fromAccountId
	 */
	public Long getFromAccountId() {
		return fromAccountId;
	}

	/**
	 * 设置
	 * @param fromAccountId
	 */
	public void setFromAccountId(Long fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	/**
	 * 获取
	 * @return toAccountId
	 */
	public Long getToAccountId() {
		return toAccountId;
	}

	/**
	 * 设置
	 * @param toAccountId
	 */
	public void setToAccountId(Long toAccountId) {
		this.toAccountId = toAccountId;
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
	 * @return memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * 设置
	 * @param memo
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * 获取
	 * @return fee
	 */
	public BigDecimal getFee() {
		return fee;
	}

	/**
	 * 设置
	 * @param fee
	 */
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	/**
	 * 获取
	 * @return txnSeq
	 */
	public String getTxnSeq() {
		return txnSeq;
	}

	/**
	 * 设置
	 * @param txnSeq
	 */
	public void setTxnSeq(String txnSeq) {
		this.txnSeq = txnSeq;
	}

	/**
	 * 获取
	 * @return hostNo
	 */
	public Long getHostNo() {
		return hostNo;
	}

	/**
	 * 设置
	 * @param hostNo
	 */
	public void setHostNo(Long hostNo) {
		this.hostNo = hostNo;
	}

	/**
	 * 获取
	 * @return operId
	 */
	public String getOperId() {
		return operId;
	}

	/**
	 * 设置
	 * @param operId
	 */
	public void setOperId(String operId) {
		this.operId = operId;
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
	 * @return merchantId
	 */
	public String getMerchantId() {
		return merchantId;
	}

	/**
	 * 设置
	 * @param merchantId
	 */
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * 获取
	 * @return bankId
	 */
	public String getBankId() {
		return bankId;
	}

	/**
	 * 设置
	 * @param bankId
	 */
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	/**
	 * 获取
	 * @return bankAccount
	 */
	public String getBankAccount() {
		return bankAccount;
	}

	/**
	 * 设置
	 * @param bankAccount
	 */
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	/**
	 * 获取
	 * @return externalOrderNo
	 */
	public String getExternalOrderNo() {
		return externalOrderNo;
	}

	/**
	 * 设置
	 * @param externalOrderNo
	 */
	public void setExternalOrderNo(String externalOrderNo) {
		this.externalOrderNo = externalOrderNo;
	}

	/**
	 * 获取
	 * @return refundOrderNo
	 */
	public String getRefundOrderNo() {
		return refundOrderNo;
	}

	/**
	 * 设置
	 * @param refundOrderNo
	 */
	public void setRefundOrderNo(String refundOrderNo) {
		this.refundOrderNo = refundOrderNo;
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
	 * @return reverseFlag
	 */
	public String getReverseFlag() {
		return reverseFlag;
	}

	/**
	 * 设置
	 * @param reverseFlag
	 */
	public void setReverseFlag(String reverseFlag) {
		this.reverseFlag = reverseFlag;
	}

	/**
	 * 获取
	 * @return reserver1
	 */
	public String getReserver1() {
		return reserver1;
	}

	/**
	 * 设置
	 * @param reserver1
	 */
	public void setReserver1(String reserver1) {
		this.reserver1 = reserver1;
	}

	/**
	 * 获取
	 * @return reserver2
	 */
	public String getReserver2() {
		return reserver2;
	}

	/**
	 * 设置
	 * @param reserver2
	 */
	public void setReserver2(String reserver2) {
		this.reserver2 = reserver2;
	}

	/**
	 * 获取
	 * @return reserver3
	 */
	public Integer getReserver3() {
		return reserver3;
	}

	/**
	 * 设置
	 * @param reserver3
	 */
	public void setReserver3(Integer reserver3) {
		this.reserver3 = reserver3;
	}

	/**
	 * 获取
	 * @return reserver4
	 */
	public Integer getReserver4() {
		return reserver4;
	}

	/**
	 * 设置
	 * @param reserver4
	 */
	public void setReserver4(Integer reserver4) {
		this.reserver4 = reserver4;
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
