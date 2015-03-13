package com.cssweb.payment.account.vo;

import java.io.Serializable;

public class TopupToMobileAccountByDefaultResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5212204866983525905L;
	/**
	 * SE id
	 */
	private String SEIdentifierId ;
	
	/**
	 * SE type
	 */
	private String SEIdentifierType;
	
	/**
	 * service_id
	 */
	private String serviceId;
	
	/**
	 * service version
	 */
	private String serviceVersion;
	/**	
	 * add lilei 20140820
	 */
	private String seIdName;
	/**	
	 * add lilei 20140820
	 */
	private String iccid;

	public String getSEIdentifierId() {
		return SEIdentifierId;
	}

	public void setSEIdentifierId(String sEIdentifierId) {
		this.SEIdentifierId = sEIdentifierId;
	}

	public String getSEIdentifierType() {
		return SEIdentifierType;
	}

	public void setSEIdentifierType(String sEIdentifierType) {
		this.SEIdentifierType = sEIdentifierType;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceVersion() {
		return serviceVersion;
	}

	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
	}

	/**
	 * @return the seIdName
	 */
	public String getSeIdName() {
		return seIdName;
	}

	/**
	 * @param seIdName the seIdName to set
	 */
	public void setSeIdName(String seIdName) {
		this.seIdName = seIdName;
	}

	/**
	 * @return the iccid
	 */
	public String getIccid() {
		return iccid;
	}

	/**
	 * @param iccid the iccid to set
	 */
	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

}
