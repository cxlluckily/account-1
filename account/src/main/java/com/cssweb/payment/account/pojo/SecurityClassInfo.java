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
 * @author zhanwx
 * @version 0.1 (2014年8月13日 下午2:19:16)
 * @since 0.1
 * @see
 */
public class SecurityClassInfo implements Serializable {
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 实名认证情况
	 */
	private String authSet;
	/**
	 * 余额支付功能开通情况
	 */
	private String balancepaySet;
	/**
	 * 手机支付验证设置情况
	 */
	private String verifySet;
	/**
	 * 预留信息修改情况
	 */
	private String reservationSet;
	/**
	 * 安全问题设置情况
	 */
	private String questionSet;
	/**
	 * 绑定手机开通情况
	 */
	private String phoneSet;
	/**
	 * 数字证书设置情况
	 */
	private String certSet;
	
	private String reserve1;
	
	private String reserve2;
	
	private Integer reserve3;
	
	private Integer reserve4;
	
	private Date updateDatetime;
	/**
	 * 获取 用户id
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 获取 实名认证情况
	 * @return authSet
	 */
	public String getAuthSet() {
		return authSet;
	}
	/**
	 * 获取 余额支付功能开通情况
	 * @return balancepaySet
	 */
	public String getBalancepaySet() {
		return balancepaySet;
	}
	/**
	 * 获取 手机支付验证情况
	 * @return verifySet
	 */
	public String getVerifySet() {
		return verifySet;
	}
	/**
	 * 获取 预留信息修改情况
	 * @return reservationSet
	 */
	public String getReservationSet() {
		return reservationSet;
	}
	/**
	 * 获取 安全问题设置情况
	 * @return questionSet
	 */
	public String getQuestionSet() {
		return questionSet;
	}
	/**
	 * 获取 数字证书设置情况
	 * @return certSet
	 */
	public String getCertSet() {
		return certSet;
	}
	/**
	 * 设置 用户id
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 设置 实名认证情况
	 * @param authSet
	 */
	public void setAuthSet(String authSet) {
		this.authSet = authSet;
	}
	/**
	 * 设置 余额支付功能开通情况
	 * @param balancepaySet
	 */
	public void setBalancepaySet(String balancepaySet) {
		this.balancepaySet = balancepaySet;
	}
	/**
	 * 设置 手机支付验证情况
	 * @param verifySet
	 */
	public void setVerifySet(String verifySet) {
		this.verifySet = verifySet;
	}
	/**
	 * 设置 预留信息修改情况
	 * @param reservationSet
	 */
	public void setReservationSet(String reservationSet) {
		this.reservationSet = reservationSet;
	}
	/**
	 * 设置 安全问题设置情况
	 * @param questionSet
	 */
	public void setQuestionSet(String questionSet) {
		this.questionSet = questionSet;
	}
	
	public String getPhoneSet() {
		return phoneSet;
	}
	public void setPhoneSet(String phoneSet) {
		this.phoneSet = phoneSet;
	}
	/**
	 * 设置 数字证书设置情况
	 * @param certSet
	 */
	public void setCertSet(String certSet) {
		this.certSet = certSet;
	}
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
	/**
	 * 获取
	 * @return reserve1
	 */
	public String getReserve1() {
		return reserve1;
	}
	/**
	 * 设置
	 * @param reserve1
	 */
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}
	/**
	 * 获取
	 * @return reserve2
	 */
	public String getReserve2() {
		return reserve2;
	}
	/**
	 * 设置
	 * @param reserve2
	 */
	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}
	/**
	 * 获取
	 * @return reserve3
	 */
	public Integer getReserve3() {
		return reserve3;
	}
	/**
	 * 设置
	 * @param reserve3
	 */
	public void setReserve3(Integer reserve3) {
		this.reserve3 = reserve3;
	}
	/**
	 * 获取
	 * @return reserve4
	 */
	public Integer getReserve4() {
		return reserve4;
	}
	/**
	 * 设置
	 * @param reserve4
	 */
	public void setReserve4(Integer reserve4) {
		this.reserve4 = reserve4;
	}
	/**
	 * 获取
	 * @return updateDatetime
	 */
	public Date getUpdateDatetime() {
		return updateDatetime;
	}
	/**
	 * 设置
	 * @param updateDatetime
	 */
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

}
