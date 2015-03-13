package com.cssweb.payment.account.vo;

import java.io.Serializable;

public class CreateMobileAccountResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5834313935605836377L;
	/**
	 * id
	 * */
	private Long id ;
	/**
	 * cash account
	 */
	private Long mobileAccountId;
	
	/**
	 * prepaid account
	 */
	private Long prepaidAccountId;
	
	private Long sequence;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getMobileAccountId() {
		return mobileAccountId;
	}

	public void setMobileAccountId(Long mobileAccountId) {
		this.mobileAccountId = mobileAccountId;
	}

	public Long getPrepaidAccountId() {
		return prepaidAccountId;
	}

	public void setPrepaidAccountId(Long prepaidAccountId) {
		this.prepaidAccountId = prepaidAccountId;
	}

	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}
	

}
