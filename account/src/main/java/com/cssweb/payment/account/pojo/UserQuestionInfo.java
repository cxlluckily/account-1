package com.cssweb.payment.account.pojo;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONObject;
/**
 * 
 * @author zhanwx
 * @version 0.2 (2014年7月19日 下午5:31:05)
 * @since 0.1
 * @see
 */
public class UserQuestionInfo implements Serializable{
	/**
	 * 序号ID
	 */
	private Long id;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 问题ID
	 */
	private Integer questionId;
	/**
	 * 问题答案
	 */
	private String questionContent;
	/**
	 * 创建日期
	 */
	private Date createDatetime;
	/**
	 * 更新日期
	 */
	private Date updateDatetime;
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
	
	
	/* ************以下是get和set方法 *******************/
	
	/**
	 * 获取
	 * @return id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 获取
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 获取
	 * @return questionId
	 */
	public Integer getQuestionId() {
		return questionId;
	}
	/**
	 * 获取
	 * @return questionContent
	 */
	public String getQuestionContent() {
		return questionContent;
	}
	/**
	 * 获取
	 * @return createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}
	/**
	 * 获取
	 * @return updateDatetime
	 */
	public Date getUpdateDatetime() {
		return updateDatetime;
	}
	/**
	 * 获取
	 * @return reserve1
	 */
	public String getReserve1() {
		return reserve1;
	}
	/**
	 * 获取
	 * @return reserve2
	 */
	public String getReserve2() {
		return reserve2;
	}
	/**
	 * 获取
	 * @return reserve3
	 */
	public Integer getReserve3() {
		return reserve3;
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
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 设置
	 * @param questionId
	 */
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	/**
	 * 设置
	 * @param questionContent
	 */
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	/**
	 * 设置
	 * @param createDatetime
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	/**
	 * 设置
	 * @param updateDatetime
	 */
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	/**
	 * 设置
	 * @param reserve1
	 */
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}
	/**
	 * 设置
	 * @param reserve2
	 */
	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}
	/**
	 * 设置
	 * @param reserve3
	 */
	public void setReserve3(Integer reserve3) {
		this.reserve3 = reserve3;
	}
	/**
	 * 设置
	 * @param reserve4
	 */
	public void setReserve4(Integer reserve4) {
		this.reserve4 = reserve4;
	}
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

	

}
