/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.vo;

import java.io.Serializable;

import com.cssweb.payment.account.pojo.SecurityClassInfo;
import com.cssweb.payment.account.pojo.UserFunctionInfo;
import com.cssweb.payment.account.pojo.UserLoginInfo;

/**
 * 修改用户信息 support方法返回值的封装
 * @author zhanwx
 * @version 0.1 (2014年9月12日 下午1:58:35)
 * @since 0.1
 * @see
 */
public class UpdateUserResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8771202394817095030L;
	/**
	 * 用户功能表对象
	 */
	private UserFunctionInfo uf;
	/**
	 * 安全等级表对象
	 */
	private SecurityClassInfo sc;
	/**
	 * 用户登录表对象
	 */
	private UserLoginInfo ul ;
	
	/**
	 * 获取
	 * @return uf
	 */
	public UserFunctionInfo getUf() {
		return uf;
	}
	/**
	 * 设置
	 * @param uf
	 */
	public void setUf(UserFunctionInfo uf) {
		this.uf = uf;
	}
	/**
	 * 获取
	 * @return sc
	 */
	public SecurityClassInfo getSc() {
		return sc;
	}
	/**
	 * 设置
	 * @param sc
	 */
	public void setSc(SecurityClassInfo sc) {
		this.sc = sc;
	}
	/**
	 * 获取
	 * @return ul
	 */
	public UserLoginInfo getUl() {
		return ul;
	}
	/**
	 * 设置
	 * @param ul
	 */
	public void setUl(UserLoginInfo ul) {
		this.ul = ul;
	}
}
