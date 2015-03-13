/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.pojo;
import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONObject;
/**
 * 
 * @author zhanwx
 * @version 0.2 (2014年7月19日 上午11:08:39)
 * @since 0.1
 * @see
 */
public class RateInfo implements Serializable {
	/**
	 * 商户ID或银行ID
	 */
	private Long merchantId;
	/**
	 * 费率科目ID
	 */
	private String rateId;
	/**
	 * 费率
	 */
	private Float rate;
	/**
	 * 创建日期
	 */
	private Date createDatetime;
	/**
	 * 预留字段1
	 */
	private String reserve1;
	/**
	 * 预留字段2
	 */
	private String reserve2;
	/**
	 * 预留字段3
	 */
	private Integer reserve3;
	/**
	 * 预留字段4
	 */
	private Integer reserve4;
	/**
	 * 更新时间
	 */
	private Date updateDatetime;

	/* ****************以下是get和set方法*********************/
	
	
	/**
	 * 获取 merchantId
	 * @return merchantId
	 */
	public Long getMerchantId() {
		return merchantId;
	}

	/**
	 * 设置 merchantId
	 * @param merchantId
	 */
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * 获取 rateId
	 * @return rateId
	 */
	public String getRateId() {
		return rateId;
	}

	/**
	 * 设置 rateId
	 * @param rateId
	 */
	public void setRateId(String rateId) {
		this.rateId = rateId;
	}

	/**
	 * 获取 rate
	 * @return rate
	 */
	public Float getRate() {
		return rate;
	}

	/**
	 * 设置 rate
	 * @param rate
	 */
	public void setRate(Float rate) {
		this.rate = rate;
	}

	/**
	 * 获取 createDatetime
	 * @return createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * 设置 createDatetime
	 * @param createDatetime
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * 获取 reserve1
	 * @return reserve1
	 */
	public String getReserve1() {
		return reserve1;
	}

	/**
	 * 设置 reserve1
	 * @param reserve1
	 */
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	/**
	 * 获取 reserve2
	 * @return reserve2
	 */
	public String getReserve2() {
		return reserve2;
	}

	/**
	 * 设置 reserve2
	 * @param reserve2
	 */
	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	/**
	 * 获取 reserve3
	 * @return reserve3
	 */
	public Integer getReserve3() {
		return reserve3;
	}

	/**
	 * 设置 reserve3
	 * @param reserve3
	 */
	public void setReserve3(Integer reserve3) {
		this.reserve3 = reserve3;
	}

	/**
	 * 获取 reserve4
	 * @return reserve4
	 */
	public Integer getReserve4() {
		return reserve4;
	}

	/**
	 * 设置 reserve4
	 * @param reserve4
	 */
	public void setReserve4(Integer reserve4) {
		this.reserve4 = reserve4;
	}

	/**
	 * 获取 updateDatetime
	 * @return updateDatetime
	 */
	public Date getUpdateDatetime() {
		return updateDatetime;
	}

	/**
	 * 设置 updateDatetime
	 * @param updateDatetime
	 */
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

	
	
	
}
