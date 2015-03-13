/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.vo;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONObject;

/**
 * @author Ganlin
 * @version 0.1 (2014年7月26日 下午5:41:23)
 * @since 0.1
 * @see
 */
public class AccountDetailedQuery extends AccountDetailed implements Serializable {
	
	private static final long serialVersionUID = 5552339659106670773L;
	private Date beginDate;
	private Date endDate;
	private Integer pageNo;
	private Integer pageSize;
	private Integer firstResult;
	/**
	 * @return beginDate
	 */
	public Date getBeginDate() {
		return beginDate;
	}
	/**
	 * @param beginDate
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	/**
	 * @return endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return pageNo
	 */
	public Integer getPageNo() {
		return pageNo;
	}
	/**
	 * @param pageNo
	 */
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	/**
	 * @return pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return firstResult
	 */
	public Integer getFirstResult() {
		return firstResult;
	}
	/**
	 * @param firstResult
	 */
	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}
	
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

}
