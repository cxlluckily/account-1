/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.vo;

import java.io.Serializable;
import java.util.Date;

import com.cssweb.payment.account.pojo.EnterpriseUser;

/**
 * @author Ganlin
 * @version 0.1 (2014年7月22日 下午1:46:20)
 * @since 0.1
 * @see
 */
public class EnterpriseUserQuery extends EnterpriseUser implements Serializable {

	private static final long serialVersionUID = -8634942274041748279L;

	private Date createDatetimeStart;
	private Date createDatetimeEnd;
	private Integer pageNo;
	private Integer pageSize;
	private Integer firstResult;
	private String userStatus;

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

	/**
	 * @return userStatus
	 */
	public String getUserStatus() {
		return userStatus;
	}

	/**
	 * @param userStatus
	 */
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

}
