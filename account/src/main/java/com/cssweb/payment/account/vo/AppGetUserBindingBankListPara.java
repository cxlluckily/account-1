/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.vo;

import java.io.Serializable;

import com.cssweb.payment.account.pojo.UserBandBankInfo;

/**
 * @author zhaoxingwen
 * @version 0.1 (2014年7月30日 下午3:11:06)
 * @since 0.1
 * @see
 */
public class AppGetUserBindingBankListPara extends UserBandBankInfo implements Serializable {

	/**
	 *银行名称 
	 */
	private String bankName;
	
	public String getBankName(){
		return this.bankName;
	}
	public void setBankName(String bankName){
		this.bankName = bankName;
	}
}
