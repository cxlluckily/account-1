package com.cssweb.payment.account.pojo;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONObject;

/**
 * @author zhanwx
 * @version 0.1 (2014年8月4日 下午1:08:53)
 * @since 0.1
 * @see
 */
public class UserLoginLog implements Serializable{
	/**
	 * id
	 */
	private Long id;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 登录用户名
	 */
	private String loginName;
	/**
	 * MAC
	 */
	private String mac;
	/**
	 * IP
	 */
	private String ip;
	/**
	 * 登录时间
	 */
	private Date loginTime;
	/**
	 * 来源
	 */
	private String source;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 预留信息1
	 */
	private String reserve1;
	/**
	 * 预留信息2
	 */
	private String reserve2;
	/**
	 * 预留信息3
	 */
	private String reserve3;
	/**
	 * 预留信息4
	 */
	private String reserve4;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	
	/**
	 * 获取id
	 * @return id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置id
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取用户id
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置用户id
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取登录名
	 * @return loginName
	 */
	public String getLoginName() {
		return loginName;
	}
	/**
	 * 设置登录名
	 * @param loginName
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * 获取mac地址
	 * @return mac
	 */
	public String getMac() {
		return mac;
	}
	/**
	 * 设置mac地址
	 * @param mac
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}
	/**
	 * 获取IP地址
	 * @return ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * 设置IP地址
	 * @param ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * 获取登录时间
	 * @return loginTime
	 */
	public Date getLoginTime() {
		return loginTime;
	}
	/**
	 * 设置登录时间
	 * @param loginTime
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	/**
	 * 获取来源
	 * @return source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * 设置来源
	 * @param source
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * 获取创建时间
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置创建时间
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取预留字段1
	 * @return reserve1
	 */
	public String getReserve1() {
		return reserve1;
	}
	/**
	 * 设置预留字段1
	 * @param reserve1
	 */
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}
	/**
	 * 获取预留字段2
	 * @return reserve2
	 */
	public String getReserve2() {
		return reserve2;
	}
	/**
	 * 设置预留字段2
	 * @param reserve2
	 */
	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}
	/**
	 * 获取预留字段3
	 * @return reserve3
	 */
	public String getReserve3() {
		return reserve3;
	}
	/**
	 * 设置预留字段3
	 * @param reserve3
	 */
	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}
	/**
	 * 获取预留字段4
	 * @return reserve4
	 */
	public String getReserve4() {
		return reserve4;
	}
	/**
	 * 设置预留字段4
	 * @param reserve4
	 */
	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}
	/**
	 * 获取更新时间
	 * @return updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置更新时间
	 * @param updateTime
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

	
	
	
	
}
