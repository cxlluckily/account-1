/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.pojo;
import java.io.Serializable;
import java.util.Date;

/**
 * DP文件信息
 * @author zhanwx
 * @version 0.1 (2014年8月22日 下午12:04:13)
 * @since 0.1
 * @see
 */
public class DpFileInfo implements Serializable {

	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * 文件路径
	 */
	private String filePath;
	/**
	 * 创建时间
	 */
	private Date createDatetime;
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
	private int reserve3 ;
	/**
	 * 预留字段4
	 */
	private int reserve4 ;
	/**
	 * 更新时间
	 */
	private Date updateDatetime;
	
	/**
	 * 获取
	 * @return fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * 设置
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * 获取
	 * @return filePath
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * 设置
	 * @param filePath
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * 获取
	 * @return createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}
	/**
	 * 设置
	 * @param createDatetime
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
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
