/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.util;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cssweb.payment.account.constant.DictionaryKey;
import com.cssweb.payment.common.response.AttachmentResponse;
import com.cssweb.payment.common.response.BasicResponse;
import com.cssweb.payment.omp.pojo.Dictionary;
import com.cssweb.payment.omp.sei.IDictionaryService;

/**
 * 数据字典工具类
 * 
 * @author DuXiaohua
 * @version 1.0 (2014年7月27日 下午2:43:46)
 * @since 1.0
 * @see DictionaryKey IDictionaryService
 */
@Component
public class DictionaryUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(DictionaryUtils.class);
	private static IDictionaryService dictionaryService;
	
	/**
	 * 获取字典项值，转换为Integer类型
	 * 
	 * @author DuXiaohua
	 * @date 2014年7月27日 下午3:23:13
	 * @param key 字典项键
	 * @return 字典项值
	 * @since 1.0
	 */
	public static Integer getIntegerValue(String key) {
		Dictionary dictionary = getDictionary(key);
		String value = dictionary.getValue();
		if (StringUtils.isBlank(value)) {
			return null;
		}
		return Integer.valueOf(value);
	}
	
	/**
	 * 获取字典项值
	 * 
	 * @author DuXiaohua
	 * @date 2014年7月27日 下午3:23:13
	 * @param key 字典项键
	 * @return 字典项值
	 * @since 1.0
	 */
	public static String getStringValue(String key) {
		Dictionary dictionary = getDictionary(key);
		String value = dictionary.getValue();
		return value;
	}
	
	/**
	 * 获取字典项值
	 * 
	 * @author DuXiaohua
	 * @date 2014年7月27日 下午3:23:13
	 * @param key 字典项键
	 * @return 字典项值
	 * @since 1.0
	 */
	public static Long getLongValue(String key) {
		Dictionary dictionary = getDictionary(key);
		String value = dictionary.getValue();
		if (StringUtils.isBlank(value)) {
			return null;
		}
		return Long.valueOf(value);
	}
	
	/**
	 * 获取字典项
	 * 
	 * @author DuXiaohua
	 * @date 2014年7月27日 下午3:17:27
	 * @param key 字典项键
	 * @return 字典项对象
	 * @since 1.0
	 * @see
	 */
	public static Dictionary getDictionary(String key) {
		AttachmentResponse<Dictionary> response = dictionaryService.getDictionaryByKeyCode(key);
		if (!BasicResponse.SUCCESSFUL_RESPONSE_CODE.equals(response.getReturnCode())) {
			logger.error(response.getReturnCode() + ":" + response.getReturnMsg());
			throw new RuntimeException(response.getReturnCode() + ":" + response.getReturnMsg());
		}
		Dictionary dictionary = response.getAttachment();
		if (dictionary == null) {
			throw new RuntimeException("无此字典项");
		}
		return dictionary;
	}
	
	/**
	 * 根据父键，获取所有子字典项
	 * 
	 * @author DuXiaohua
	 * @date 2014年7月27日 下午3:15:45
	 * @param parentCode 父键
	 * @return 子字典项对象列表
	 * @since 1.0
	 */
	public static List<Dictionary> getDictionaryByParent(String parentCode) {
		AttachmentResponse<List<Dictionary>> response = dictionaryService.getDictionaryByParenKeyCode(parentCode);
		if (!BasicResponse.SUCCESSFUL_RESPONSE_CODE.equals(response.getReturnCode())) {
			logger.error(response.getReturnCode() + ":" + response.getReturnMsg());
			throw new RuntimeException(response.getReturnCode() + ":" + response.getReturnMsg());
		}
		List<Dictionary> dictionaryList = response.getAttachment();
		if (dictionaryList == null || dictionaryList.isEmpty()) {
			throw new RuntimeException("无此字典项");
		}
		return dictionaryList;
	}
	
	/**
	 * 根据父键和子值，取子项的显示名称
	 * @author DuXiaohua
	 * @date 2014年8月7日 下午4:49:42
	 * @param parentCode
	 * @param value
	 * @since 1.0
	 */
	public static String getDisplayName(String parentCode, String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		}
		AttachmentResponse<Dictionary> response = dictionaryService.getDictionaryByParenKeyCodeAndValue(parentCode, value);
		if (!BasicResponse.SUCCESSFUL_RESPONSE_CODE.equals(response.getReturnCode())) {
			logger.error(response.getReturnCode() + ":" + response.getReturnMsg());
			throw new RuntimeException(response.getReturnCode() + ":" + response.getReturnMsg());
		}
		Dictionary dictionary = response.getAttachment();
		if (dictionary == null) {
			throw new RuntimeException("无此字典项");
		}
		return dictionary.getKeyName();
	}
	
	/**
	 * 根据父键和子值，取子项的keycode
	 * @author DuXiaohua
	 * @date 2014年8月7日 下午4:49:42
	 * @param parentCode
	 * @param value
	 * @since 1.0
	 */
	public static String getKeyCode(String parentCode, String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		}
		AttachmentResponse<Dictionary> response = dictionaryService.getDictionaryByParenKeyCodeAndValue(parentCode, value);
		if (!BasicResponse.SUCCESSFUL_RESPONSE_CODE.equals(response.getReturnCode())) {
			logger.error(response.getReturnCode() + ":" + response.getReturnMsg());
			throw new RuntimeException(response.getReturnCode() + ":" + response.getReturnMsg());
		}
		Dictionary dictionary = response.getAttachment();
		if (dictionary == null) {
			throw new RuntimeException("无此字典项");
		}
		return dictionary.getKeyCode();
	}
	
	/**
	 * @param dictionaryService
	 */
	@Resource
	public void setDictionaryService(IDictionaryService service) {
		dictionaryService = service;
	}
	
}
