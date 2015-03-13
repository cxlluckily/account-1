/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ganlin
 * @version 0.1 (2014年7月26日 下午5:41:23)
 * @since 0.1
 * @see
 */
public class UserAccountQuery extends UserAccount implements Serializable {

	private static final long serialVersionUID = -7201749541651747042L;
	
	private Date beginDate;
	private Date endDate;
	private Integer pageNo;
	private Integer pageSize;
	private Integer firstResult;
	
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
	
	
}
