/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.vo;

import java.io.Serializable;
import java.util.Date;

import com.cssweb.payment.account.pojo.PersonalUser;

/**
 * @author Ganlin
 * @version 0.1 (2014年7月22日 上午11:25:48)
 * @since 0.1
 * @see
 */
public class PersonalUserQuery extends PersonalUser implements Serializable {

	private static final long serialVersionUID = -1174491511057187987L;

	private Date createDatetimeStart;
	private Date createDatetimeEnd;
	private Integer pageNo;
	private Integer pageSize;
	private Integer firstResult;
	private String userStatus;
	private String accountType;
	/**
	 * @return createDatetimeStart
	 */
	public Date getCreateDatetimeStart() {
		return createDatetimeStart;
	}

	/**
	 * @param createDatetimeStart
	 */
	public void setCreateDatetimeStart(Date createDatetimeStart) {
		this.createDatetimeStart = createDatetimeStart;
	}

	/**
	 * @return createDatetimeEnd
	 */
	public Date getCreateDatetimeEnd() {
		return createDatetimeEnd;
	}

	/**
	 * @param createDatetimeEnd
	 */
	public void setCreateDatetimeEnd(Date createDatetimeEnd) {
		this.createDatetimeEnd = createDatetimeEnd;
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

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	/**
	 * @return accountType
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
}
