
/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.vo;

import java.io.Serializable;
import java.util.Date;

import com.cssweb.payment.account.pojo.AccountInfo;

/**扩展账户表查询
 * @author zhaoxingwen
 * @date 2014年7月23日 上午11:25:48
 * @version 0.1 (2014年7月23日 上午11:25:48)
 * @since 0.1
 * @see
 */
public class AccountInfoQuery extends AccountInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 367733356419327281L;
	/**
	 * 用户姓名
	 */
	private String userName;
	/**
	 * 用户手机号
	 */
	private String mobile;
	private String email;
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
	
	
	public String getUserName(){
		return this.userName;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getMobile(){
		return this.mobile;
	}
	public void setMobile(String mobile){
		this.mobile = mobile ;
	}
	
	public String getEmail(){
		return this.email ;
	}
	public void setEmail(String email){
		this.email = email ;
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
