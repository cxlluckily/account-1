package com.cssweb.payment.account.pojo;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONObject;
/**
 * 
 * @author zhaoxingwen
 * @date 2014年7月19日 上午11:08:39
 * @version 0.2 
 * @since 0.1
 * @see
 */
public class UserLoginInfo  implements Serializable{
	/**
	* 用户ID
	*/
   private Long userId;
   /**
    * 用户类型
    */
   private String userType;
   /**
    * 注册用户登录密码
    */
   private String loginPasswd; 
   /**
    * 支付密码
    */
   private String payPasswd;
   /**
    * 最后登录时间
    */
   private Date lastLogininTime; 
   /**
    * 最后登出时间
    */
   private Date lastLoginoutTime; 
   /**
    * 最后登录IP
    */
   private String lastLoginIp; 
   /**
    * 错误登陆次数
    */
   private Integer errorLoginTimes;
   /**
    * 错误支付次数
    */
   private Integer errorPayTimes;
   /**
    * 手机号
    */
   private String userMobile; 
   /**
    * 邮箱
    */
   private String userEmail; 
   /**
    * 用户状态
    */
   private String userStatus; 
   /**
    * 创建时间
    */
   private Date createDatetime; 
   /**
    * 预留字段1
    */
   private String  reserve1;   
   /**
    * 预留字段2
    */
   private String  reserve2;
   /**
    * 预留字段3
    */
   private Integer  reserve3;
   /**
    * 预留字段4
    */
   private Integer  reserve4;
   /**
    * 更新时间
    */
   private Date updateDatetime;
   
   
   /* *****************以下是get和set方法*********************/
	
   
   /**
    *获取 userId 
	* @return userId
	*/
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置 userId
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取 userType
	 * @return userType
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * 设置 userType
	 * @param userType
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * 获取 loginPasswd
	 * @return loginPasswd
	 */
	public String getLoginPasswd() {
		return loginPasswd;
	}
	
	public Integer getErrorLoginTimes() {
		return errorLoginTimes;
	}
	public void setErrorLoginTimes(Integer errorLoginTimes) {
		this.errorLoginTimes = errorLoginTimes;
	}
	/**
	 * 设置 loginPasswd
	 * @param loginPasswd
	 */
	public void setLoginPasswd(String loginPasswd) {
		this.loginPasswd = loginPasswd;
	}
	/**
	 * 获取 payPasswd
	 * @return payPasswd
	 */
	public String getPayPasswd() {
		return payPasswd;
	}
	/**
	 * 设置 payPasswd
	 * @param payPasswd
	 */
	public void setPayPasswd(String payPasswd) {
		this.payPasswd = payPasswd;
	}
	/**
	 * 获取 lastLogininTime
	 * @return lastLogininTime
	 */
	public Date getLastLogininTime() {
		return lastLogininTime;
	}
	/**
	 * 设置 lastLogininTime
	 * @param lastLogininTime
	 */
	public void setLastLogininTime(Date lastLogininTime) {
		this.lastLogininTime = lastLogininTime;
	}
	/**
	 * 获取 lastLoginoutTime
	 * @return lastLoginoutTime
	 */
	public Date getLastLoginoutTime() {
		return lastLoginoutTime;
	}
	/**
	 * 设置 lastLoginoutTime
	 * @param lastLoginoutTime
	 */
	public void setLastLoginoutTime(Date lastLoginoutTime) {
		this.lastLoginoutTime = lastLoginoutTime;
	}
	/**
	 * 获取 lastLoginIp
	 * @return lastLoginIp
	 */
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	/**
	 * 设置 lastLoginIp
	 * @param lastLoginIp
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	/**
	 * 获取 errorPayTimes
	 * @return errorPayTimes
	 */
	public Integer getErrorPayTimes() {
		return errorPayTimes;
	}
	/**
	 * 设置 errorPayTimes
	 * @param errorPayTimes
	 */
	public void setErrorPayTimes(Integer errorPayTimes) {
		this.errorPayTimes = errorPayTimes;
	}
	/**
	 * 获取 userMobile
	 * @return userMobile
	 */
	public String getUserMobile() {
		return userMobile;
	}
	/**
	 * 设置 userMobile
	 * @param userMobile
	 */
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	/**
	 * 获取 userEmail
	 * @return userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}
	/**
	 * 设置 userEmail
	 * @param userEmail
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	/**
	 * 获取 userStatus
	 * @return userStatus
	 */
	public String getUserStatus() {
		return userStatus;
	}
	/**
	 * 设置 userStatus
	 * @param userStatus
	 */
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	/**
	 * 获取 createDatetime
	 * @return createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}
	/**
	 * 设置 createDatetime
	 * @param createDatetime
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	/**
	 * 获取 updateDatetime
	 * @return updateDatetime
	 */
	public Date getUpdateDatetime() {
		return updateDatetime;
	}
	/**
	 * 设置 updateDatetime
	 * @param updateDatetime
	 */
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	/**
	 * 获取 reserve1
	 * @return reserve1
	 */
	public String getReserve1() {
		return reserve1;
	}
	/**
	 * 设置 reserve1
	 * @param reserve1
	 */
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}
	/**
	 * 获取 reserve2
	 * @return reserve2
	 */
	public String getReserve2() {
		return reserve2;
	}
	/**
	 * 设置 reserve2
	 * @param reserve2
	 */
	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}
	/**
	 * 获取 reserve3
	 * @return reserve3
	 */
	public Integer getReserve3() {
		return reserve3;
	}
	/**
	 * 设置 reserve3
	 * @param reserve3
	 */
	public void setReserve3(Integer reserve3) {
		this.reserve3 = reserve3;
	}
	/**
	 * 获取 reserve4
	 * @return reserve4
	 */
	public Integer getReserve4() {
		return reserve4;
	}
	/**
	 * 设置 reserve4
	 * @param reserve4
	 */
	public void setReserve4(Integer reserve4) {
		this.reserve4 = reserve4;
	}
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
   
	   
	   
}
