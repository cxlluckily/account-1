/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.vo;

import java.util.Collections;
import java.util.List;

import com.cssweb.payment.account.pojo.BusinessInfo;
import com.cssweb.payment.account.pojo.EnterpriseUser;

/**
 * @author DuXiaohua
 * @version 1.0 (2014年8月9日 上午11:45:09)
 * @since 1.0
 * @see
 */
public class GetBusinessInfoListResponse extends EnterpriseUser {
	/**
	 * 商户信息列表
	 */
	private List<BusinessInfo>  businessInfoList = Collections.EMPTY_LIST;
	/**
	 * idType对应中文
	 */
	private String idTypeName;
	/**
	 * 用户状态
	 */
	private String userStatus;
	/**
	 * 用户状态对应中文
	 */
	private String userStatusName;
	/**
	 * 国家对应中文
	 */
	private String countryName;
	
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

	/**
	 * @return userStatusName
	 */
	public String getUserStatusName() {
		return userStatusName;
	}

	/**
	 * @param userStatusName
	 */
	public void setUserStatusName(String userStatusName) {
		this.userStatusName = userStatusName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getIdTypeName() {
		return idTypeName;
	}

	public void setIdTypeName(String idTypeName) {
		this.idTypeName = idTypeName;
	}


	/**
	 * @return businessInfoList
	 */
	public List<BusinessInfo> getBusinessInfoList() {
		return businessInfoList;
	}

	/**
	 * @param businessInfoList
	 */
	public void setBusinessInfoList(List<BusinessInfo> businessInfoList) {
		this.businessInfoList = businessInfoList;
	}
	
}
