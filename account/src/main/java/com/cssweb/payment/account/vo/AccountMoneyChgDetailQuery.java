/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.vo;

import java.io.Serializable;
import java.util.Date;

import com.cssweb.payment.account.pojo.AccountMoneyChgDetail;

/**
 * @author zhaoxingwen
 * @version 0.1 (2014年7月22日 上午11:25:48)
 * @since 0.1
 * @see
 */
public class AccountMoneyChgDetailQuery extends AccountMoneyChgDetail implements Serializable {

	private static final long serialVersionUID = -1174491511057187987L;

	private String userName;
	private Date beginDate;
	private Date endDate;
	private Integer pageNo;
	private Integer pageSize;
	private Integer firstResult;

	/**
	 * getUserName
	 */
	public String getUserName(){
		return this.userName;
	}
	/**
	 * setUserName
	 */
	public void setUserName(String userName){
		this.userName = userName;
	}
	/**
	 * @return beginDate
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * @param 
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
}
