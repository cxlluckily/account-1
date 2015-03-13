/**
 * <p>Title: PersonData.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: China Software & Service Company.</p>
 * @author 移动支付开发团队
 * @date 2014年9月10日
 * @version 1.0
 */
package com.cssweb.payment.account.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Title: PersonData
 * Description:
 * Company: China Software & Service Company
 * Project:payment-account
 * @author liLei
 * @version 1.0
 * @date 2014年9月10日 下午4:02:59
 * @see
 */
public class PersonData implements Serializable {
    /**
     * 
     */
    String tag;
    
    /**
     * 
     */
    Integer length;
    
    /**
     * 
     */
    String value;
    
    /**
     * 
     */
    String dataFormat;
    
    /**
     * 
     */
    String sourceId;
    
    /**
     * 
     */
    String groupId;
    
    /**
     * 
     */
    String defaultValue;
    
    /**
     * 
     */
    Integer flag;
    
    /**
     * 
     */
    String reserve1;
    
    /**
     * 
     */
    String reserve2;
    
    /**
     * 
     */
    Integer reserve3;
    
    /**
     * 
     */
    Integer reserve4;
    
    /**
     * 
     */
    Date updateDatetime;

	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * @return the length
	 */
	public Integer getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(Integer length) {
		this.length = length;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the dataFormat
	 */
	public String getDataFormat() {
		return dataFormat;
	}

	/**
	 * @param dataFormat the dataFormat to set
	 */
	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}

	/**
	 * @return the sourceId
	 */
	public String getSourceId() {
		return sourceId;
	}

	/**
	 * @param sourceId the sourceId to set
	 */
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * @return the flag
	 */
	public Integer getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	/**
	 * @return the reserve1
	 */
	public String getReserve1() {
		return reserve1;
	}

	/**
	 * @param reserve1 the reserve1 to set
	 */
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	/**
	 * @return the reserve2
	 */
	public String getReserve2() {
		return reserve2;
	}

	/**
	 * @param reserve2 the reserve2 to set
	 */
	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	/**
	 * @return the reserve3
	 */
	public Integer getReserve3() {
		return reserve3;
	}

	/**
	 * @param reserve3 the reserve3 to set
	 */
	public void setReserve3(Integer reserve3) {
		this.reserve3 = reserve3;
	}

	/**
	 * @return the reserve4
	 */
	public Integer getReserve4() {
		return reserve4;
	}

	/**
	 * @param reserve4 the reserve4 to set
	 */
	public void setReserve4(Integer reserve4) {
		this.reserve4 = reserve4;
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
    

}

