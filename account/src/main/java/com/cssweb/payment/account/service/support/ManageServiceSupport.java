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

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cssweb.payment.account.dao.IAccountInfoDao;
import com.cssweb.payment.account.dao.IAccountMoneyChgDetailDao;
import com.cssweb.payment.account.dao.IAccountOperDetailDao;
import com.cssweb.payment.account.dao.IAccountOperInfoDao;
import com.cssweb.payment.account.dao.IBankInfoDao;
import com.cssweb.payment.account.dao.IBusinessInfoDao;
import com.cssweb.payment.account.dao.IEnterpriseUserDao;
import com.cssweb.payment.account.dao.IUserBankAuInfoDao;
import com.cssweb.payment.account.pojo.AccountInfo;
import com.cssweb.payment.account.pojo.AccountMoneyChgDetail;
import com.cssweb.payment.account.pojo.AccountOperDetail;
import com.cssweb.payment.account.pojo.AccountOperInfo;
import com.cssweb.payment.account.pojo.BankInfo;
import com.cssweb.payment.account.pojo.BusinessInfo;
import com.cssweb.payment.account.pojo.EnterpriseUser;
import com.cssweb.payment.account.pojo.UserBankAuInfo;
import com.cssweb.payment.common.provider.sei.IIdGeneratorService;
import com.cssweb.payment.common.response.AttachmentResponse;

/**
 * @author zhaoxingwen
 * @version 0.1 (2014年7月23日 下午6:00:05)
 * @since 0.1
 * @see
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ManageServiceSupport {
	@Resource
	public IAccountInfoDao accountInfoDao;
	@Resource
	private IAccountOperInfoDao accountOperInfoDao;
	@Resource
	private IAccountOperDetailDao accountOperDetailDao;
	@Resource
	private IAccountMoneyChgDetailDao accountMoneyChgDetailDao;
	@Resource IBankInfoDao bankInfoDao;
	
	@Resource
	public IBusinessInfoDao  businessInfoDao;
	
	@Resource
	public IEnterpriseUserDao enterpriseUserDao;
	@Resource
	private IUserBankAuInfoDao userBankAuInfoDao;
	@Resource
	private IIdGeneratorService idGeneratorService;
	public void 	businessIns(BusinessInfo businessInfo){
		businessInfoDao.insert(businessInfo);
	}
	
	public void enterpriseUserUpd(EnterpriseUser enterpriseUser)
	{
		enterpriseUserDao.update(enterpriseUser);
	}
	
	/**
	 * 
	 * @author Ganlin
	 * @date 2014年7月27日 下午4:27:07
	 * @param accountUpdate
	 * @param operInfo
	 * @param operDetail
	 * @param chgDetail 
	 * @since 0.1
	 */
	public void userAccountAdjustment(AccountInfo accountUpdate, AccountOperInfo operInfo, 
			AccountOperDetail operDetail, AccountMoneyChgDetail chgDetail) {
		accountInfoDao.update(accountUpdate);
		accountOperInfoDao.insert(operInfo);
		accountOperDetailDao.insert(operDetail);
		accountMoneyChgDetailDao.insert(chgDetail);
	}
	/**
	 * 商户签约
	 * @author zhaoxingwen
	 * @date 2014年7月27日 下午4:11:53
	 * @param bussinessInfo
	 * @param enterpriseUser 
	 * @since 0.1
	 * @see
	 */
	public void merchantSigning(BusinessInfo bussinessInfo,EnterpriseUser enterpriseUser){
		//this.businessIns(bussinessInfo);
		businessInfoDao.insert(bussinessInfo);
		//this.enterpriseUserUpd(enterpriseUser);
		enterpriseUserDao.update(enterpriseUser);
	}
	/**
	 * 商户解约
	 * @author zhaoxingwen
	 * @date 2014年7月27日 下午6:24:50
	 * @param bussinessInfo
	 * @param enterpriseUser 
	 * @since 0.1
	 * @see
	 */
	public void merchantTermination(BusinessInfo bussinessInfo,EnterpriseUser enterpriseUser){
		//this.enterpriseUserUpd(enterpriseUser);
		enterpriseUserDao.update(enterpriseUser);
		businessInfoDao.update(bussinessInfo);
		
	}
	
	/**
	 * 
	 * @author Ganlin
	 * @date 2014年7月27日 下午4:27:02
	 * @param bank
	 * @param account 
	 * @since 0.1
	 */
	public void modefyPaymentAccount(BankInfo bank, AccountInfo account) {
		bankInfoDao.insert(bank);
		accountInfoDao.insert(account);
	}
	
	public void merchantTermination(List<BusinessInfo> businessInfoList) {
		for (BusinessInfo businessInfo : businessInfoList) {
			businessInfoDao.update(businessInfo);
		}
	}
	
	/**
	 * 获取最新一条认证打款信息
	 * 
	 * @author wangpeng
	 * @date 2014年8月5日 下午7:54:14
	 * @param userId 用户id
	 * @return AttachmentResponse<UserBankAuInfo> 用户打款认证信息
	 * @since 1.0
	 */
	public UserBankAuInfo getLatestUserBankAuInfo(Long userId) {
		UserBankAuInfo userBankAuInfoQuery = new UserBankAuInfo();
		userBankAuInfoQuery.setUserId(userId);
		List<UserBankAuInfo> userBankAuInfoList = userBankAuInfoDao.selectList(userBankAuInfoQuery);
		if (userBankAuInfoList.isEmpty()) {
			return null;
		}
		Collections.sort(userBankAuInfoList, new Comparator<UserBankAuInfo>() {
			@Override
			public int compare(UserBankAuInfo o1, UserBankAuInfo o2) {
				return o2.getUpdateDatetime().compareTo(o1.getUpdateDatetime());
			}
		});
		return userBankAuInfoList.get(0);
	}
	
	public void saveEnterpriseUserAndBusinessInfo(EnterpriseUser enterpriseUser, List<BusinessInfo> businessInfoList) {
		if (enterpriseUser != null) {
			enterpriseUserDao.update(enterpriseUser);
		}
		for (BusinessInfo businessInfo : businessInfoList) {
			if (businessInfo.getId() == null) {
				AttachmentResponse<Long> response = idGeneratorService.generate(BusinessInfo.class.getName());
				businessInfo.setId(response.getAttachment());
				businessInfoDao.insert(businessInfo);
			} else {
				businessInfoDao.update(businessInfo);
			}
		}
	}
	/**
	 * 删除服务事务处理
	 * @author wangpeng
	 * @date 2014-10-29
	 * @param businessInfo 服务信息
	 * @param enterpriserUser 企业用户信息
	 */
	public void updateEnterpriseUserAndBusinessInfo(BusinessInfo businessInfo,EnterpriseUser enterpriserUser){
		businessInfoDao.delete(businessInfo);
		if(enterpriserUser!=null){
			enterpriseUserDao.update(enterpriserUser);
		}
	}
	/**
	 * 签约事务处理
	 * @author wangpeng
	 * @date 2014-09-19
	 * @param businessInfo 服务信息
	 * @param enterpriserUser 企业用户信息
	 */
	public void addBusinessInfo(BusinessInfo businessInfo,EnterpriseUser enterpriserUser){
		if(businessInfo != null){
			businessInfoDao.insert(businessInfo);
		}
		if(enterpriserUser!=null){
			enterpriseUserDao.update(enterpriserUser);
		}
	}
		
}
