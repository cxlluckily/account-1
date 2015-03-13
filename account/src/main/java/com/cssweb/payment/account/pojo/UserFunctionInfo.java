package com.cssweb.payment.account.pojo;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONObject;

/**
 * Title: UserFunctionInfo
 * Description: 用户功能列表开通信息表对应字段，表名称为ACM_USER_FUNCTION_INFO		
 * Company: China Software & Service Company
 * Project:payment-account
 * @author liLei
 * @version 1.0
 * @date 2014年7月23日 下午3:54:59
 * @see 
 */
public class UserFunctionInfo implements Serializable {
	/**
	 * id
	 */
    private Long id;
    /**
	 * 用户ID
	 */
    private Long userId;
    /**
	 * 功能ID
	 */
    private String functionId;
    /**
	 * 开通状态
	 */
    private String openStatus;
    /**
	 * 创建时间
	 */
    private Date createDatetime;
    /**
	 * 预留信息1
	 */
    private String Reserve1;
    /**
	 * 预留信息2
	 */
    private String Reserve2;
    /**
	 * 预留信息3
	 */
    private Integer Reserve3;
    /**
	 * 预留信息4
	 */
    private Integer Reserve4;
    /**
	 * 更新时间
	 */
    private Date updateDatetime;
	
    /**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * @return the functionId
	 */
	public String getFunctionId() {
		return functionId;
	}
	/**
	 * @param functionId the functionId to set
	 */
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
	/**
	 * @return the openStatus
	 */
	public String getOpenStatus() {
		return openStatus;
	}
	/**
	 * @param openStatus the openStatus to set
	 */
	public void setOpenStatus(String openStatus) {
		this.openStatus = openStatus;
	}
	/**
	 * @return the createDateTime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}
	/**
	 * @param createDateTime the createDateTime to set
	 */
	public void setCreateDatetime(Date createDateTime) {
		this.createDatetime = createDateTime;
	}
	/**
	 * @return the reserve1
	 */
	public String getReserve1() {
		return Reserve1;
	}
	/**
	 * @param reserve1 the reserve1 to set
	 */
	public void setReserve1(String reserve1) {
		Reserve1 = reserve1;
	}
	/**
	 * @return the reserve2
	 */
	public String getReserve2() {
		return Reserve2;
	}
	/**
	 * @param reserve2 the reserve2 to set
	 */
	public void setReserve2(String reserve2) {
		Reserve2 = reserve2;
	}
	/**
	 * @return the reserve3
	 */
	public Integer getReserve3() {
		return Reserve3;
	}
	/**
	 * @param reserve3 the reserve3 to set
	 */
	public void setReserve3(Integer reserve3) {
		Reserve3 = reserve3;
	}
	/**
	 * @return the reserve4
	 */
	public Integer getReserve4() {
		return Reserve4;
	}
	/**
	 * @param reserve4 the reserve4 to set
	 */
	public void setReserve4(Integer reserve4) {
		Reserve4 = reserve4;
	}
	/**
	 * @return the updateDatetime
	 */
	public Date getUpdateDatetime() {
		return updateDatetime;
	}
	/**
	 * @param updateDatetime the updateDatetime to set
	 */
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

}
