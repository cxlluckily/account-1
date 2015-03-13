/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.pojo;
import java.io.Serializable;
import java.util.Date;
/**
 * 个人化数据信息
 * @author zhanwx
 * @version 0.1 (2014年8月22日 下午12:25:34)
 * @since 0.1
 * @see
 */
public class PersonDataInfo implements Serializable{

	/**
	 * 数据元标签
	 */
	private String tag;
	/**
	 * 长度
	 */
	private Integer length;
	/**
	 * 数据元取值
	 */
	private String value;
	/**
	 * 数据来源
	 */
	private String sourceId;
	/**
	 * 所属组
	 */
	private String groupId;
	/**
	 * 默认值
	 */
	private String defaultValue;
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
	private int reserve3;
	/**
	 * 预留字段4
	 */
	private int reserve4;
	/**
	 * 更新时间
	 */
	private Date updateDatetime ;

	/**
	 * 获取
	 * @return tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * 设置
	 * @param tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * 获取
	 * @return length
	 */
	public Integer getLength() {
		return length;
	}

	/**
	 * 设置
	 * @param length
	 */
	public void setLength(Integer length) {
		this.length = length;
	}

	/**
	 * 获取
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 设置
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 获取
	 * @return sourceId
	 */
	public String getSourceId() {
		return sourceId;
	}

	/**
	 * 设置
	 * @param sourceId
	 */
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * 获取
	 * @return groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * 设置
	 * @param groupId
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * 获取
	 * @return defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * 设置
	 * @param defaultValue
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
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
	public int getReserve3() {
		return reserve3;
	}

	/**
	 * 设置
	 * @param reserve3
	 */
	public void setReserve3(int reserve3) {
		this.reserve3 = reserve3;
	}

	/**
	 * 获取
	 * @return reserve4
	 */
	public int getReserve4() {
		return reserve4;
	}

	/**
	 * 设置
	 * @param reserve4
	 */
	public void setReserve4(int reserve4) {
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
