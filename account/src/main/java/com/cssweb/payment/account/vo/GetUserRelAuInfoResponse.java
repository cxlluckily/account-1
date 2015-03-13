package com.cssweb.payment.account.vo;

import java.io.Serializable;
import java.util.Date;

public class GetUserRelAuInfoResponse implements Serializable{
	/**
	 * 登陆手机号
	 */
	private String userMobile;
	
	/**
	 * 登陆邮箱
	 */
	private String userEmail;
	
	/**
	 * 用户头像
	 */
	private String headPic;
	
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
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getHeadPic() {
		return headPic;
	}
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
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
}
