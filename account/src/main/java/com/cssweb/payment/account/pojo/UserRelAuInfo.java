package com.cssweb.payment.account.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.sf.json.JSONObject;

/**
 * Title: UserRelAuInfo Description:
 * 实名认证关联关系表对应字段，表名称ACM_USER_REL_AU_INFO，V1.6 Company: China Software &
 * Service Company Project:payment-account
 * 
 * @author wangpeng
 * @version 1.0
 * @date 2014年9月2日 下午7:43:35
 * @see CEC支付平台_数据库设计说明书_账户管理模块_V3.2.xlsx
 */
public class UserRelAuInfo implements Serializable {
	/**
	 * id
	 */
	private Long id;
	/**
	 * 主账户ID
	 */
	private Long mainUserId;
	/**
	 * 关联账户id
	 */
	private Long relationUserId;
	/**
	 * 创建时间
	 */
	private Date createDateTime;
	/**
	 * 预留字段1
	 */
	private String reserve1;
	/**
	 * 预留字段2
	 */
	private String reserve2;
	/**
	 * 预留字段3
	 */
	private Integer reserve3;
	/**
	 * 预留字段4
	 */
	private Integer reserve4;
	/**
	 * 更新时间
	 */
	private Date updateDateTime;

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getMainUserId() {
		return mainUserId;
	}


	public void setMainUserId(Long mainUserId) {
		this.mainUserId = mainUserId;
	}


	public Long getRelationUserId() {
		return relationUserId;
	}


	public void setRelationUserId(Long relationUserId) {
		this.relationUserId = relationUserId;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}


	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}


	public String getReserve1() {
		return reserve1;
	}


	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}


	public String getReserve2() {
		return reserve2;
	}


	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}


	public Integer getReserve3() {
		return reserve3;
	}


	public void setReserve3(Integer reserve3) {
		this.reserve3 = reserve3;
	}


	public Integer getReserve4() {
		return reserve4;
	}


	public void setReserve4(Integer reserve4) {
		this.reserve4 = reserve4;
	}

	public Date getUpdateDateTime() {
		return updateDateTime;
	}


	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}


	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

}