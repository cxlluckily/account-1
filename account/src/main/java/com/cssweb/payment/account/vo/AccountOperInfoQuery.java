
/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.vo;

import java.io.Serializable;
import java.util.Date;

import com.cssweb.payment.account.pojo.AccountOperInfo;

/**扩展账户操作流水表查询
 * @author wangpeng
 * @date 2014年9月5日 下午9:13:48
 * @version 0.1 (2014年9月5日 下午9:13:48)
 * @since 0.1
 * @see
 */
public class AccountOperInfoQuery extends AccountOperInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 367733356419327281L;
	
	/**
	 *  查询日期开始
	 */
	private Date beginDate;
	/**
	 * 查询日期止
	 */
	private Date endDate;
	private Integer pageNo;
	private Integer pageSize;
	private Integer firstResult;
	
	/**	
	 * 记录的起始数
	 */
	private Integer startIndex;
	
	/**
	 * 总记录条数
	 */
	private Integer total;
	
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
	
	/**
	 * @return the startIndex
	 */
	public Integer getStartIndex() {
		return startIndex;
	}
	/**
	 * @param startIndex the startIndex to set
	 */
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	/**
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

}
