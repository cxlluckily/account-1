/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.service;

import java.util.List;

import org.junit.Test;

import com.cssweb.payment.account.constant.DictionaryKey;
import com.cssweb.payment.account.test.TestCase;
import com.cssweb.payment.account.util.DictionaryUtils;
import com.cssweb.payment.omp.pojo.Dictionary;

/**
 * @author DuXiaohua
 * @version 1.0 (2014年7月27日 下午3:26:54)
 * @since 1.0
 * @see
 */
public class DictionaryUtilsTests extends TestCase {
	
	@Test
	public void testGetIntegerValue() {
		Integer value = DictionaryUtils.getIntegerValue(DictionaryKey.AUTH_STATUS_WAIT_AUDIT);
		assertNotNull(value);
		logger.debug(value.toString());
	}
	
	@Test
	public void testGetStringValue() {
		String value = DictionaryUtils.getStringValue(DictionaryKey.AUTH_STATUS_WAIT_AUDIT);
		assertNotNull(value);
		logger.debug(value);
	}
	
	@Test
	public void testGetDictionary() {
		Dictionary dictionary = DictionaryUtils.getDictionary(DictionaryKey.TXN_TYPE_EXIT_PAY);
		assertNotNull(dictionary);
		logger.debug(dictionary.getKeyName());
		logger.debug(dictionary.getValue());
		logger.debug(dictionary.getKeyCode());
	}
	
	@Test
	public void testGetDictionaryByParent() {
		List<Dictionary> dictionaryList = DictionaryUtils.getDictionaryByParent(DictionaryKey.USER_TYPE);
		assertFalse(dictionaryList.isEmpty());
		for (Dictionary dictionary : dictionaryList) {
			logger.debug(dictionary.getKeyName());
		}
	}
}
