/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.service.support;

import java.util.Date;
import java.util.TimerTask;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cssweb.payment.account.constant.DictionaryKey;
import com.cssweb.payment.account.constant.GlobalConstants;
import com.cssweb.payment.account.dao.IAccountInfoDao;
import com.cssweb.payment.account.dao.IBankInfoDao;
import com.cssweb.payment.account.pojo.AccountInfo;
import com.cssweb.payment.account.pojo.BankInfo;
import com.cssweb.payment.account.util.CreateCardNoUtils;
import com.cssweb.payment.account.util.DictionaryUtils;
import com.cssweb.payment.account.vo.AccountInfoQuery;
import com.cssweb.payment.common.provider.sei.IIdGeneratorService;
import com.cssweb.payment.common.response.AttachmentResponse;
import com.cssweb.payment.common.util.ResponseUtils;

/**
 * 账户管理公用的支持类
 * 
 * @author DuXiaohua
 * @version 1.0 (2014年7月23日 下午3:56:38)
 * @since 1.0
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AccountServiceSupport {
	@Resource
	public IAccountInfoDao accountInfoDao;
	
	@Resource
	public IBankInfoDao bankInfoDao;
	
	@Resource
	public IIdGeneratorService idGeneratorService;
	
	/**
	 * 获取账户信息，并且进行MAC校验
	 * 
	 * @author DuXiaohua
	 * @date 2014年7月23日 下午4:26:31
	 * @param accountId 账户id
	 * @return AttachmentResponse<AccountInfo> 账户信息
	 * @since 1.0
	 */
	public AttachmentResponse<AccountInfo> getAccountInfoWidthMACVerify(Long accountId) {
		if (accountId == null) {
			return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"accountId"});
		}
		AccountInfoQuery account = new AccountInfoQuery();
		account.setAccountId(accountId);
		AccountInfo accountInfo = accountInfoDao.selectOneForUpdate(account);
		if (accountInfo == null) {
			return ResponseUtils.buildFailureResponse(GlobalConstants.ACCOUNT_NOT_EXIST);
		} else {
			return ResponseUtils.buildSuccessResponse(accountInfo);
		}
	}
	
	/**
	 *   根据银行id  获取平台账号信息
	 * 
	 * @author wangpeng
	 * @date 2014年7月28日 下午6:04:31
	 * @param userId userid
	 * @return AttachmentResponse<AccountInfo> 账户信息
	 * @since 1.0
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public AccountInfo getAccountInfoByBankId(String bankAccount) {
		BankInfo bankInfo = new BankInfo();
		bankInfo.setBankAccount(bankAccount);
		bankInfo = bankInfoDao.selectOne(bankInfo);
		AccountInfoQuery accountInfoQuery = new AccountInfoQuery();
		accountInfoQuery.setUserId(bankInfo.getPaymentId());
		AccountInfo paymentAccountInfo = accountInfoDao.selectOne(accountInfoQuery);
		// TODO MAC校验
		return paymentAccountInfo;
	}
	
	/**
	 * 获取账户信息，不进行MAC校验
	 * 
	 * @author DuXiaohua
	 * @date 2014年7月23日 下午4:29:41
	 * @param accountId
	 * @return AttachmentResponse<AccountInfo> 账户信息
	 * @since 1.0
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public AttachmentResponse<AccountInfo> getAccountInfo(Long accountId) {
		if (accountId == null) {
			return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"accountId"});
		}
		AccountInfoQuery account = new AccountInfoQuery();
		account.setAccountId(accountId);
		AccountInfo accountInfo = accountInfoDao.selectOne(account);
		if (accountInfo == null) {
			return ResponseUtils.buildFailureResponse(GlobalConstants.ACCOUNT_NOT_EXIST);
		} else {
			return ResponseUtils.buildSuccessResponse(accountInfo);
		}
	}
	
	/**
	 * @return accountInfoDao
	 */
	public IAccountInfoDao getAccountInfoDao() {
		return accountInfoDao;
	}

	/**
	 * @param accountInfoDao
	 */
	public void setAccountInfoDao(IAccountInfoDao accountInfoDao) {
		this.accountInfoDao = accountInfoDao;
	}
	
	
	
	public AttachmentResponse<Long> createCardNumber (String accountType){
		//19位卡号对象
		AttachmentResponse<Long> cardNo = new AttachmentResponse<Long>();
		//8位序列号对象
		AttachmentResponse<Long> cardId = new AttachmentResponse<Long>();
		
		//生成前十位
		String IIN = DictionaryUtils.getStringValue(DictionaryKey.CARD_IIN);
		String COMPANY = DictionaryUtils.getStringValue(DictionaryKey.CARD_COMPANY_CODE);
		String TYPE = String.format("%02d",Integer.parseInt(accountType));
		String preStr = IIN+COMPANY+TYPE;
		
		//生成八位序列号
		//根据账户类型（主账户、电子现金、预付卡(待定)）生成序列号，并补零至八位
		String key = DictionaryUtils.getKeyCode(DictionaryKey.ACCOUNT_TYPE,accountType);
		cardId = idGeneratorService.generate(key);
		//检查是否成功生成序列号
		if(cardId.getReturnCode()!=0){
			return ResponseUtils.buildExceptionResponse();
		}
		String id = String.format("%08d",cardId.getAttachment());
		
		//numForLuhn是前18位卡号，作为入参生成第19位校验码
		String numForLuhn = preStr + id;
		cardNo.setAttachment(Long.parseLong(numForLuhn + CreateCardNoUtils.createCheck(numForLuhn)));
		
		//返回结果
		return ResponseUtils.buildSuccessResponse(cardNo.getAttachment());
	}
	
	/**
	 * 错误密码清零
	 * @author wangpeng
	 * @date 2014年11月3日 11:31:10
	 */
	public void cleanWrongPassWord() {
		accountInfoDao.cleanErrorLoginTimes();
	}
	
}
