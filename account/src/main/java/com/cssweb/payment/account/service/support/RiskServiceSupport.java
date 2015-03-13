/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.service.support;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cssweb.payment.account.constant.DictionaryKey;
import com.cssweb.payment.account.constant.GlobalConstants;
import com.cssweb.payment.account.constant.RiskConstants;
import com.cssweb.payment.account.dao.IUserBankAuInfoDao;
import com.cssweb.payment.account.pojo.UserBankAuInfo;
import com.cssweb.payment.account.util.DictionaryUtils;
import com.cssweb.payment.common.response.AttachmentResponse;
import com.cssweb.payment.common.util.ResponseUtils;

/**
 * 风控服务接口相关支持类
 * 
 * @author DuXiaohua
 * @version 1.0 (2014年7月24日 下午1:48:09)
 * @since 1.0
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class RiskServiceSupport {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private IUserBankAuInfoDao userBankAuInfoDao;
	/**
	 * 获取打款成功的最新一条认证打款信息
	 * 
	 * @author DuXiaohua
	 * @date 2014年7月24日 下午7:54:14
	 * @param userId 用户id
	 * @return AttachmentResponse<UserBankAuInfo> 用户打款认证信息
	 * @since 1.0
	 */
	public AttachmentResponse<UserBankAuInfo> getLatestUserBankAuInfo(Long userId) {
		if (userId == null) {
			return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"userId"});
		}
		UserBankAuInfo userBankAuInfoQuery = new UserBankAuInfo();
		userBankAuInfoQuery.setUserId(userId);
		userBankAuInfoQuery.setBankRestatus(DictionaryUtils.getStringValue(DictionaryKey.AUTH_RESTATUS_SUCCESS));
		List<UserBankAuInfo> userBankAuInfoList = userBankAuInfoDao.selectList(userBankAuInfoQuery);
		if (userBankAuInfoList.isEmpty()) {
			return ResponseUtils.buildFailureResponse(RiskConstants.AUTH_RECORD_NOT_EXIST);
		}
		Collections.sort(userBankAuInfoList, new Comparator<UserBankAuInfo>() {
			@Override
			public int compare(UserBankAuInfo o1, UserBankAuInfo o2) {
				return o2.getBankTime().compareTo(o1.getBankTime());
			}
		});
		return ResponseUtils.buildSuccessResponse(userBankAuInfoList.get(0));
	}
}
