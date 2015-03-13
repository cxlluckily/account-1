/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.service.support;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.com.caucho.hessian.io.IteratorSerializer;
import com.cssweb.payment.account.constant.DictionaryKey;
import com.cssweb.payment.account.constant.GlobalConstants;
import com.cssweb.payment.account.constant.PortalConstants;
import com.cssweb.payment.account.dao.IAccountInfoDao;
import com.cssweb.payment.account.dao.IEnterpriseUserDao;
import com.cssweb.payment.account.dao.IPersonalUserDao;
import com.cssweb.payment.account.dao.ISecurityClassInfoDao;
import com.cssweb.payment.account.dao.IUserBankAuInfoDao;
import com.cssweb.payment.account.dao.IUserFunctionInfoDao;
import com.cssweb.payment.account.dao.IUserLoginDao;
import com.cssweb.payment.account.dao.IUserLoginLogDao;
import com.cssweb.payment.account.dao.IUserQuestionDao;
import com.cssweb.payment.account.dao.IUserRelAuInfoDao;
import com.cssweb.payment.account.pojo.AccountInfo;
import com.cssweb.payment.account.pojo.AccountOperInfo;
import com.cssweb.payment.account.pojo.EnterpriseUser;
import com.cssweb.payment.account.pojo.PersonalUser;
import com.cssweb.payment.account.pojo.SecurityClassInfo;
import com.cssweb.payment.account.pojo.UserBankAuInfo;
import com.cssweb.payment.account.pojo.UserFunctionInfo;
import com.cssweb.payment.account.pojo.UserLoginInfo;
import com.cssweb.payment.account.pojo.UserLoginLog;
import com.cssweb.payment.account.pojo.UserQuestionInfo;
import com.cssweb.payment.account.pojo.UserRelAuInfo;
import com.cssweb.payment.account.util.DictionaryUtils;
import com.cssweb.payment.account.vo.AccountInfoQuery;
import com.cssweb.payment.account.vo.EnterpriseUserQuery;
import com.cssweb.payment.account.vo.PersonalAuProcessResponse;
import com.cssweb.payment.account.vo.PersonalUserQuery;
import com.cssweb.payment.account.vo.UpdateUserResponse;
import com.cssweb.payment.common.provider.sei.IIdGeneratorService;
import com.cssweb.payment.common.response.AttachmentResponse;
import com.cssweb.payment.common.util.ResponseUtils;

/**
 * 门户网站接口服务支持类，提供事务处理及通用方法
 * 
 * @author DuXiaohua
 * @version 1.0 (2014年7月23日 下午2:24:14)
 * @since 1.0
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PortalServiceSupport {
	
	
	@Resource
	private IUserLoginDao userLoginDao;
	
	@Resource
	private IUserQuestionDao userQuestionDao;
	
	@Resource
	private IPersonalUserDao personalUserDao;
	
	@Resource
	private IEnterpriseUserDao enterpriseUserDao;
	
	@Resource
	private IAccountInfoDao accountInfoDao;
	
	@Resource
	private IUserFunctionInfoDao userFunctionInfoDao;
	
	@Resource
	private IUserBankAuInfoDao userBankAuInfoDao;
	
	@Resource
	private IIdGeneratorService idGeneratorService;
	
	@Resource
	private ISecurityClassInfoDao sciDao;
	
	@Resource
	private IUserLoginLogDao userLoginLogDao;
	
	@Resource
	private ISecurityClassInfoDao scDao;
	
	@Resource
	private IUserRelAuInfoDao userRelAuInfoDao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 个人用户注册，事务处理方法。
	 * 保存用户对象、用户登陆对象、用户安全问题对象、账户对象列表、开通功能列表
	 * 
	 * @author DuXiaohua
	 * @date 2014年7月23日 下午2:31:35
	 * @param user 用户对象
	 * @param loginInfo 用户登陆对象
	 * @param questionInfo 用户安全问题对象
	 * @param accountInfoList 账户对象列表
	 * @param functionInfoList 开通功能列表
	 * @since 1.0
	 */
	public void personalRegistration(PersonalUser user, UserLoginInfo loginInfo, UserQuestionInfo questionInfo,
			List<AccountInfo> accountInfoList, List<UserFunctionInfo> functionInfoList,SecurityClassInfo sci) {
		personalUserDao.insert(user);
		userLoginDao.insert(loginInfo);
		if(questionInfo!=null){
		   userQuestionDao.insert(questionInfo);
		}
		for (AccountInfo accountInfo : accountInfoList) {
			accountInfoDao.insert(accountInfo);
		}
		for (UserFunctionInfo functionInfo : functionInfoList) {
			userFunctionInfoDao.insert(functionInfo);
		}
		sciDao.insert(sci);
	}
	
	public void enterpriseRegistration(EnterpriseUser user, UserLoginInfo loginInfo, UserQuestionInfo questionInfo,
			List<AccountInfo> accountInfoList, List<UserFunctionInfo> functionInfoList,SecurityClassInfo sci) {
		enterpriseUserDao.insert(user);
		userLoginDao.insert(loginInfo);
		userQuestionDao.insert(questionInfo);
		for (AccountInfo accountInfo : accountInfoList) {
			accountInfoDao.insert(accountInfo);
		}
		for (UserFunctionInfo functionInfo : functionInfoList) {
			userFunctionInfoDao.insert(functionInfo);
		}
		sciDao.insert(sci);
	}
	
	/**
	 * 个人实名认证关联事务处理
	 * @author wangpeng
	 * @date 2014-09-24
	 * @param  relationPersonaluser  被关联账户
	 * @param  mainPersonaluser 本账户
	 * @param  userRelAuInfo 关联认证表
	 * @param  security 安全等级表
	 * @since 1.2
	 */
	public void personalAuthRelation(PersonalUser relationPersonaluser,PersonalUser mainPersonaluser,
						UserRelAuInfo userRelAuInfo,SecurityClassInfo security,
						UserBankAuInfo mainBankAuInfo,UserBankAuInfo relationBankAuInfo){
		if(relationBankAuInfo==null){
			AttachmentResponse<Long> response = idGeneratorService.generate(UserBankAuInfo.class.getName());
			UserBankAuInfo relation = new UserBankAuInfo();
			relation.setId(response.getAttachment());
			relation.setUserId(relationPersonaluser.getUserId());
			relation.setUserAuDateTime(new Date());
			PortalServiceSupport.copyProperties(mainBankAuInfo, relation);
			userBankAuInfoDao.insert(relation);
		}else{
			PortalServiceSupport.copyProperties(mainBankAuInfo, relationBankAuInfo);
			userBankAuInfoDao.update(relationBankAuInfo);
		}
		PortalServiceSupport.copyProperties(relationPersonaluser, mainPersonaluser);
		personalUserDao.update(relationPersonaluser);
		userRelAuInfoDao.insert(userRelAuInfo);
		scDao.update(security);
	}
	
	/**
	 * 企业实名认证关联事务处理
	 * @author wangpeng
	 * @date 2014-09-24
	 * @param  relationEnterpriseuser  被关联账户
	 * @param  mainEnterpriseuser 本账户
	 * @param  userRelAuInfo 关联认证表
	 * @param  security 安全等级表
	 * @since 1.2
	 */
	public void enterpriseAuthRelation(EnterpriseUser relationEnterpriseuser,
			EnterpriseUser mainEnterpriseuser,
			UserRelAuInfo userRelAuInfo,SecurityClassInfo security,
			UserBankAuInfo mainBankAuInfo,UserBankAuInfo relationBankAuInfo){
		if(relationBankAuInfo==null){
			AttachmentResponse<Long> response = idGeneratorService.generate(UserBankAuInfo.class.getName());
			UserBankAuInfo relation = new UserBankAuInfo();
			relation.setId(response.getAttachment());
			relation.setUserId(relationEnterpriseuser.getUserId());
			relation.setUserAuDateTime(new Date());
			PortalServiceSupport.copyProperties(mainBankAuInfo, relation);
			userBankAuInfoDao.insert(relation);
		}else{
			PortalServiceSupport.copyProperties(mainBankAuInfo, relationBankAuInfo);
			userBankAuInfoDao.update(relationBankAuInfo);
		}
		PortalServiceSupport.copyProperties(relationEnterpriseuser, mainEnterpriseuser);
		enterpriseUserDao.update(relationEnterpriseuser);
		userRelAuInfoDao.insert(userRelAuInfo);
		scDao.update(security);
	}
	
	/**
	 * 判断时间差
	 * @author wangpeng
	 * @date 2014-09-11 10:36
	 * @param
	 * @since
	 */
	public  AttachmentResponse<Long> dateUtil(Date authDate){
		try{
			if (authDate == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[] {"authDate"});
			}
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			sf.format(date);
			long distance = date.getTime() - authDate.getTime();
			distance = distance / 1000 / 60 / 60 / 24;
			return ResponseUtils.buildSuccessResponse(distance);
		} catch (Exception e) {
			AttachmentResponse<Long> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}
	
	
	/**
	 * 复制对象的属性
	 * @author wangpeng
	 * @param UserBankAuInfo mainBankAuInfo,UserBankAuInfo relationBankAuInfo
	 */
	public static void copyProperties(UserBankAuInfo mainBankAuInfo,UserBankAuInfo relationBankAuInfo){
		if(relationBankAuInfo!=null){
			relationBankAuInfo.setBankName(mainBankAuInfo.getBankName());
			relationBankAuInfo.setBankRestatus(mainBankAuInfo.getBankRestatus());
			relationBankAuInfo.setBankNo(mainBankAuInfo.getBankNo());
			relationBankAuInfo.setBankProvince(mainBankAuInfo.getBankProvince());
			relationBankAuInfo.setBankCity(mainBankAuInfo.getBankCity());
			relationBankAuInfo.setBranchId(mainBankAuInfo.getBranchId());
			relationBankAuInfo.setBranchName(mainBankAuInfo.getBranchName());
			relationBankAuInfo.setBandAmount(mainBankAuInfo.getBandAmount());
			relationBankAuInfo.setBankId(mainBankAuInfo.getBankId());
			relationBankAuInfo.setUpdateDatetime(new Date());
		}
	}
	
	/**
	 * 复制对象的属性
	 * @author wangpeng
	 * @param EnterpriseUser relationEnterpriseuser,EnterpriseUser mainEnterpriseuser
	 */
	public static void copyProperties(PersonalUser relationPersonaluser,PersonalUser mainPersonaluser){
		relationPersonaluser.setPersonalAddress(mainPersonaluser.getPersonalAddress());
		relationPersonaluser.setAuthStep(mainPersonaluser.getAuthStep());
		relationPersonaluser.setAuthStatus(mainPersonaluser.getAuthStatus());
		relationPersonaluser.setAuthType(mainPersonaluser.getAuthType());
		relationPersonaluser.setAuthCertType(mainPersonaluser.getAuthCertType());
		relationPersonaluser.setAuthCertId(mainPersonaluser.getAuthCertId());
		relationPersonaluser.setAuthCertExpirDate(mainPersonaluser.getAuthCertExpirDate());
		relationPersonaluser.setPictureFront(mainPersonaluser.getPictureFront());
		relationPersonaluser.setPictureBack(mainPersonaluser.getPictureBack());
		relationPersonaluser.setPreString(mainPersonaluser.getPreString());
		relationPersonaluser.setUpdateDatetime(new Date());
	}
	
	/**
	 * 复制对象的属性
	 * @author wangpeng
	 * @param EnterpriseUser relationEnterpriseuser,EnterpriseUser mainEnterpriseuser
	 */
	public static void copyProperties(EnterpriseUser relationEnterpriseuser,EnterpriseUser mainEnterpriseuser){
		relationEnterpriseuser.setBusinessName(mainEnterpriseuser.getBusinessName());
		relationEnterpriseuser.setBusinessLicence(mainEnterpriseuser.getBusinessLicence());
		relationEnterpriseuser.setBusinessLocation(mainEnterpriseuser.getBusinessLocation());
		relationEnterpriseuser.setBusinessExpdate(mainEnterpriseuser.getBusinessExpdate());
		relationEnterpriseuser.setBusinessAddress(mainEnterpriseuser.getBusinessAddress());
		relationEnterpriseuser.setManagerTel(mainEnterpriseuser.getManagerTel());
		relationEnterpriseuser.setOrganizationCode(mainEnterpriseuser.getOrganizationCode());
		relationEnterpriseuser.setBusinessSphere(mainEnterpriseuser.getBusinessSphere());
		relationEnterpriseuser.setBusinessRegamount(mainEnterpriseuser.getBusinessRegamount());
		relationEnterpriseuser.setFax(mainEnterpriseuser.getFax());
		relationEnterpriseuser.setBusinessPic1(mainEnterpriseuser.getBusinessPic1());
		relationEnterpriseuser.setBusinessPic2(mainEnterpriseuser.getBusinessPic2());
		
		relationEnterpriseuser.setBusinessBank(mainEnterpriseuser.getBusinessBank());
		relationEnterpriseuser.setBusinessBankno(mainEnterpriseuser.getBusinessBankno());
		relationEnterpriseuser.setBusinessBankcity(mainEnterpriseuser.getBusinessBankcity());
		relationEnterpriseuser.setBusinessBankbranch(mainEnterpriseuser.getBusinessBankbranch());
		
		relationEnterpriseuser.setLegalPersonname(mainEnterpriseuser.getLegalPersonname());
		relationEnterpriseuser.setLegalPersonphone(mainEnterpriseuser.getLegalPersonphone());
		relationEnterpriseuser.setLegalPersoncity(mainEnterpriseuser.getLegalPersoncity());
		relationEnterpriseuser.setIdType(mainEnterpriseuser.getIdType());
		relationEnterpriseuser.setLegalPersonidno(mainEnterpriseuser.getLegalPersonidno());
		relationEnterpriseuser.setIdPic1(mainEnterpriseuser.getIdPic1());
		relationEnterpriseuser.setIdPic2(mainEnterpriseuser.getIdPic2());
		
		relationEnterpriseuser.setAuthStep(mainEnterpriseuser.getAuthStep());
		relationEnterpriseuser.setAuthStatus(mainEnterpriseuser.getAuthStatus());
		relationEnterpriseuser.setAuthType(mainEnterpriseuser.getAuthType());
		relationEnterpriseuser.setSource(mainEnterpriseuser.getSource());
		relationEnterpriseuser.setManagerTel(mainEnterpriseuser.getManagerTel());
		relationEnterpriseuser.setCountryCode(mainEnterpriseuser.getCountryCode());
		relationEnterpriseuser.setPreString(mainEnterpriseuser.getPreString());
		relationEnterpriseuser.setUpdateDatetime(new Date());
	}
	
	/**
	 * 设置（修改）安全保护问题
	 * @author 
	 * @date 2014年8月7日 下午1:11:42
	 * @param 
	 * @since 1.0
	 */
	public void setSafeQuestion(Long userId,String question1,String answer1,
			String question2,String answer2,String question3,String answer3,SecurityClassInfo sci) {
		UserQuestionInfo newInfo1 = new UserQuestionInfo();
		AttachmentResponse<Long> idResponse1 = idGeneratorService.generate(UserQuestionInfo.class.getName());
		newInfo1.setId(idResponse1.getAttachment());
		newInfo1.setUserId(userId);
		newInfo1.setQuestionId(Integer.valueOf(question1));
		newInfo1.setQuestionContent(answer1);
		newInfo1.setCreateDatetime(new Date());
		newInfo1.setUpdateDatetime(new Date());
		userQuestionDao.insert(newInfo1);
		
		UserQuestionInfo newInfo2 = new UserQuestionInfo();
		AttachmentResponse<Long> idResponse2 = idGeneratorService.generate(UserQuestionInfo.class.getName());
		newInfo2.setId(idResponse2.getAttachment());
		newInfo2.setUserId(userId);
		newInfo2.setQuestionId(Integer.valueOf(question2));
		newInfo2.setQuestionContent(answer2);
		newInfo2.setCreateDatetime(new Date());
		newInfo2.setUpdateDatetime(new Date());
		userQuestionDao.insert(newInfo2);
		
		UserQuestionInfo newInfo3 = new UserQuestionInfo();
		AttachmentResponse<Long> idResponse3 = idGeneratorService.generate(UserQuestionInfo.class.getName());
		newInfo3.setId(idResponse3.getAttachment());
		newInfo3.setUserId(userId);
		newInfo3.setQuestionId(Integer.valueOf(question3));
		newInfo3.setQuestionContent(answer3);
		newInfo3.setCreateDatetime(new Date());
		newInfo3.setUpdateDatetime(new Date());
		userQuestionDao.insert(newInfo3);
		
		sciDao.update(sci);
	}

	/**
	 * 根据userId查询PersonalUser
	 * @author Ganlin
	 * @date 2014年7月23日 下午6:12:31
	 * @param userId
	 * @return AttachmentResponse<PersonalUser>
	 * @since 0.1
	 * @see
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public AttachmentResponse<PersonalUser> getPersonalUser(Long userId) {
		PersonalUserQuery query = new PersonalUserQuery();
		query.setUserId(userId);
		PersonalUser user = personalUserDao.selectOne(query);
		if (user == null) {
			return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
		}
		return ResponseUtils.buildSuccessResponse(user);
	}
	
	/**
	 * 根据userId查询EnterpriseUser
	 * @author Ganlin
	 * @date 2014年7月23日 下午6:18:30
	 * @param userId
	 * @return 
	 * @since 0.1
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public AttachmentResponse<EnterpriseUser> getEnterpriseUser(Long userId) {
		EnterpriseUserQuery query = new EnterpriseUserQuery();
		query.setUserId(userId);
		EnterpriseUser user = enterpriseUserDao.selectOne(query);
		if (user == null) {
			return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
		}
		return ResponseUtils.buildSuccessResponse(user);
	}
	
	/**
	 * 根据userId查询UserLoginInfo
	 * @author Ganlin
	 * @date 2014年7月23日 下午6:18:30
	 * @param userId
	 * @return 
	 * @since 0.1
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public AttachmentResponse<UserLoginInfo> getUserLoginInfo(Long userId) {
		UserLoginInfo query = new UserLoginInfo();
		query.setUserId(userId);
		UserLoginInfo user = userLoginDao.selectOne(query);
		if (user == null) {
			return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
		}
		return ResponseUtils.buildSuccessResponse(user);
	}
	
	/**
	 * 个人用户谁，事务处理方法。
	 * </br>保存个人用户信息、银行打款信息表。
	 * 
	 * @author shilei@cssweb.com.cn
	 * @date 2014年8月1日 下午3:47:11
	 * @param user　个人用户信息
	 * @param bankAuInfo　银行打款信息
	 * @since 0.1
	 * @see
	 */
	public void  personalAu(PersonalUser user,UserBankAuInfo bankAuInfo){
		personalUserDao.update(user);
		if (bankAuInfo != null) {
			userBankAuInfoDao.insert(bankAuInfo);
		}
	}
	
	public void  enterpriseAu(EnterpriseUser user,UserBankAuInfo bankAuInfo){
		enterpriseUserDao.update(user);
		if (bankAuInfo != null) {
			userBankAuInfoDao.insert(bankAuInfo);
		}
	}

	public void updatePersonal(PersonalUser user, UserLoginInfo loginInfo,List<AccountInfo> acList,UserFunctionInfo uf,SecurityClassInfo sc) {
		personalUserDao.update(user);
		if (loginInfo != null) {
			userLoginDao.update(loginInfo);
		}
		if(acList != null){
			for(int i = 0; i < acList.size();i++){
				accountInfoDao.update(acList.get(i));
			}
		}
		if(uf != null){
			userFunctionInfoDao.update(uf);
		}
		if(sc != null){
			scDao.update(sc);
		}
	}
	
	public void updateEnterprise(EnterpriseUser user, UserLoginInfo loginInfo,List<AccountInfo> acList,UserFunctionInfo uf,SecurityClassInfo sc) {
		enterpriseUserDao.update(user);
		if (loginInfo != null) {
			userLoginDao.update(loginInfo);
		}
		if(acList != null){
			for(int i = 0; i < acList.size();i++){
				accountInfoDao.update(acList.get(i));
			}
		}
		if(uf != null){
			userFunctionInfoDao.update(uf);
		}
		if(sc != null){
			scDao.update(sc);
		}
	}
	
	public AttachmentResponse unbindingMobilePhone(Long userId) {
		UserLoginInfo query = new UserLoginInfo();
		query.setUserId(userId);
		UserLoginInfo userLogin = userLoginDao.selectOne(query);
		if(userLogin == null){
			return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
		}
		if (StringUtils.isBlank(userLogin.getUserEmail())) {
			return ResponseUtils.buildFailureResponse(PortalConstants.UNBINDING_MOBILE_MUST_BINDING_EMAIL);
		}
		// 判断个人用户或企业用业，返回
		String userTypePerson = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL);
		String userTypeEn = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_ENTERPRISE);
		if (userTypeEn.equals(userLogin.getUserType())) {
			//企业用户
			EnterpriseUserQuery euq = new EnterpriseUserQuery();
			euq.setUserId(userId);
			EnterpriseUser enterprise = enterpriseUserDao.selectOne(euq);
			String mobile = enterprise.getManagerMobile();
			if (StringUtils.isBlank(mobile)) {
				return ResponseUtils.buildFailureResponse(PortalConstants.NOT_BINDING_MOBILE_PHONE);
			}
			enterpriseUserDao.updateUnbindingMobile(userId);
		} else if (userTypePerson.equals(userLogin.getUserType())) {
			//个人用户
			PersonalUserQuery param = new PersonalUserQuery();
			param.setUserId(userId);
			PersonalUser pUser = personalUserDao.selectOne(param);
			String mobile = pUser.getPhoneNumber();
			if (StringUtils.isBlank(mobile)) {
				return ResponseUtils.buildFailureResponse(PortalConstants.NOT_BINDING_MOBILE_PHONE);
			}
			personalUserDao.updateUnbindingMobile(userId);
		}
		if (StringUtils.isNotBlank(userLogin.getUserMobile())) {
			userLoginDao.updateUnbindingMobile(userId);
		}
		String closeFunction = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_NO);
		SecurityClassInfo sc = new SecurityClassInfo();
		sc.setUserId(userId);
		sc.setPhoneSet(closeFunction);
		sc.setUpdateDatetime(new Date());
		sciDao.update(sc);
		UserFunctionInfo uf = new UserFunctionInfo();
		String functionId = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_SMS_REMIND);
		uf.setUserId(userId);
		uf.setFunctionId(functionId);
		uf.setOpenStatus(closeFunction);
		uf.setUpdateDatetime(new Date());
		userFunctionInfoDao.update(uf);
		return ResponseUtils.buildSuccessResponse();
	}
	
	public AttachmentResponse unbindingEmail(Long userId) {	
		UserLoginInfo query = new UserLoginInfo();
		query.setUserId(userId);
		UserLoginInfo userLogin = userLoginDao.selectOne(query);
		if(userLogin == null){
			return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
		}
		String userTypeEn = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_ENTERPRISE);
		if (userTypeEn.equals(userLogin.getUserType())) {
			//企业用户不允许解绑邮箱
			return ResponseUtils.buildFailureResponse(PortalConstants.ENTERPRISE_NOT_UNBIND);
		} 
		if (StringUtils.isBlank(userLogin.getUserMobile())) {
			return ResponseUtils.buildFailureResponse(PortalConstants.UNBINDING_EMAIL_MUST_BINDING_MOBILE);
		}
		String userTypePerson = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL);
		if(userTypePerson.equals(userLogin.getUserType())) {
			//个人用户
			PersonalUserQuery param = new PersonalUserQuery();
			param.setUserId(userId);
			PersonalUser pUser = personalUserDao.selectOne(param);
			String email = pUser.getEmailAddress();
			if (StringUtils.isBlank(email)) {
				return ResponseUtils.buildFailureResponse(PortalConstants.NOT_BINDING_EMAIL);
			}
			personalUserDao.updateUnbindingEmail(userId);
		}
		else{
			//针对除个人、企业用户外尚未扩展的用户类型
			return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
		}
		if (StringUtils.isNotBlank(userLogin.getUserEmail())) {
			userLoginDao.updateUnbindingEmail(userId);
		}
		String closeFunction = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_NO);
		String functionId = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_EMAIL_REMIND);
		UserFunctionInfo uf = new UserFunctionInfo();
		uf.setUserId(userId);
		uf.setFunctionId(functionId);
		uf.setOpenStatus(closeFunction);
		uf.setUpdateDatetime(new Date());
		userFunctionInfoDao.update(uf);
		return ResponseUtils.buildSuccessResponse();
	}
	
	/**
	 * 为个人用户修改预留信息
	 * @author zhanwx
	 * @date 2014年8月13日 下午3:54:42
	 * @param pu
	 * @param sci 
	 * @since 0.1
	 * @see
	 */
	public void reservationUpdate(PersonalUser pu,SecurityClassInfo sci){
		personalUserDao.update(pu);	
		sciDao.update(sci);
	}
	
	/**
	 * 为企业用户修改预留信息
	 * @author zhanwx
	 * @date 2014年8月13日 下午3:55:55
	 * @param eu
	 * @param sci 
	 * @since 0.1
	 * @see
	 */
	public void reservationUpdate(EnterpriseUser eu , SecurityClassInfo sci){
		enterpriseUserDao.update(eu);
		sciDao.update(sci);
	}
	
	/**
	 * 设置余额支付功能
	 * @author zhanwx
	 * @date 2014年8月13日 下午4:05:01
	 * @param uf
	 * @param sci 
	 * @since 0.1
	 * @see
	 */
	public void balancepayUpdate(UserFunctionInfo uf,SecurityClassInfo sci){
		userFunctionInfoDao.update(uf);
		sciDao.update(sci);
	}
	
	/**
	 * 设置短信提醒功能
	 * @author zhanwx
	 * @date 2014年8月13日 下午4:05:21
	 * @param uf
	 * @param sci 
	 * @since 0.1
	 * @see
	 */
	public void smsUpdate(UserFunctionInfo uf , SecurityClassInfo sci){
		userFunctionInfoDao.update(uf);
		sciDao.update(sci);
	}
	/**
	 * @return userLoginDao
	 */
	public IUserLoginDao getUserLoginDao() {
		return userLoginDao;
	}

	/**
	 * @param userLoginDao
	 */
	public void setUserLoginDao(IUserLoginDao userLoginDao) {
		this.userLoginDao = userLoginDao;
	}

	/**
	 * @return userQuestionDao
	 */
	public IUserQuestionDao getUserQuestionDao() {
		return userQuestionDao;
	}

	/**
	 * @param userQuestionDao
	 */
	public void setUserQuestionDao(IUserQuestionDao userQuestionDao) {
		this.userQuestionDao = userQuestionDao;
	}

	/**
	 * @return personalUserDao
	 */
	public IPersonalUserDao getPersonalUserDao() {
		return personalUserDao;
	}

	/**
	 * @param personalUserDao
	 */
	public void setPersonalUserDao(IPersonalUserDao personalUserDao) {
		this.personalUserDao = personalUserDao;
	}



	/**
	 * @return enterpriseUserDao
	 */
	public IEnterpriseUserDao getEnterpriseUserDao() {
		return enterpriseUserDao;
	}

	/**
	 * @param enterpriseUserDao
	 */
	public void setEnterpriseUserDao(IEnterpriseUserDao enterpriseUserDao) {
		this.enterpriseUserDao = enterpriseUserDao;
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

	/**
	 * @return userFunctionInfoDao
	 */
	public IUserFunctionInfoDao getUserFunctionInfoDao() {
		return userFunctionInfoDao;
	}

	/**
	 * @param userFunctionInfoDao
	 */
	public void setUserFunctionInfoDao(IUserFunctionInfoDao userFunctionInfoDao) {
		this.userFunctionInfoDao = userFunctionInfoDao;
	}
	
	
	/**
	 * 查询打款认证输入金额次数
	 * @author zhanwx
	 * @date 2014年9月5日 下午5:01:41
	 * @param userId
	 * @param authDate
	 * @param status
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public AttachmentResponse<UserBankAuInfo> bankAuSelectSupport(Long userId){
		UserBankAuInfo au = new UserBankAuInfo();
		au.setUserId(userId);
		List<UserBankAuInfo> auq = userBankAuInfoDao.selectList(au);
		if(auq.size()==0){
			return ResponseUtils.buildFailureResponse(PortalConstants.USER_NAME_NOT_EXIST);
		}
		//2、取最新一条记录
		UserBankAuInfo lastAuth = auq.get(0);
		for (UserBankAuInfo auth : auq) {
			if (auth.getUpdateDatetime().after(lastAuth.getUpdateDatetime())) {
				lastAuth = auth;
			}
		}
		String success = DictionaryUtils.getStringValue(DictionaryKey.AUTH_RESTATUS_SUCCESS);
		Integer num = 0;
		if(success.equals(lastAuth.getBankRestatus())){
			if(lastAuth.getAuthNum() != null){
				num = lastAuth.getAuthNum();
			}
			au.setAuthNum(num);
		}
		else{
			au.setAuthNum(num);
		}
		au.setBankTime(lastAuth.getBankTime());
		au.setNote(lastAuth.getNote());
		return ResponseUtils.buildSuccessResponse(au);
	}
	
	/**
	 * 银行打款认证support
	 * @author zhanwx
	 * @date 2014年8月19日 下午3:24:29
	 * @param userId
	 * @param money
	 * @param auInfo
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public AttachmentResponse<String> bankAuSupport(Long userId,String bankId,String bankCardNo,BigDecimal money){
		//1、查询指定用户所有打款成功的认证记录
		UserBankAuInfo auInfo = new UserBankAuInfo();
		String authRestatus = DictionaryUtils.getStringValue(DictionaryKey.AUTH_RESTATUS_SUCCESS);
		auInfo.setUserId(userId);
		auInfo.setBankId(bankId);
		auInfo.setBankNo(bankCardNo);
		auInfo.setBankRestatus(authRestatus);
		List<UserBankAuInfo> auInfoList = userBankAuInfoDao.selectList(auInfo);
		if (auInfoList.isEmpty()) {				
			return ResponseUtils.buildFailureResponse(PortalConstants.AUTH_NOT_EXIST);
		}
		//2、取最新一条记录
		UserBankAuInfo lastAuth = auInfoList.get(0);
		for (UserBankAuInfo auth : auInfoList) {
			if (auth.getBankTime().after(lastAuth.getBankTime())) {
				lastAuth = auth;
			}
		}
		//3、检查认证次数
		if (lastAuth.getAuthNum() != null && lastAuth.getAuthNum() >= 2) {
			return ResponseUtils.buildFailureResponse(PortalConstants.AUTH_TIMES_LIMIT);
		}
		//4、核对打款金额，如果错误，更新错误次数
		if ( lastAuth.getBandAmount().compareTo(money) != 0) {
			UserBankAuInfo update = new UserBankAuInfo();
			update.setId(lastAuth.getId());
			update.setAuthNum(lastAuth.getAuthNum() == null? 1: lastAuth.getAuthNum() + 1);
			update.setUpdateDatetime(new Date());
			userBankAuInfoDao.update(update);
			//还能认证次数
			String auth = String.valueOf((2-update.getAuthNum()));
			return ResponseUtils.buildFailureResponse(PortalConstants.AUTH_FAILD,auth);
		}
		//5、在用户登录表查询用户类型，以确定在个人表或企业表更新用户认证状态
		UserLoginInfo ul = new UserLoginInfo();
		String person = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL);
		String enterprise = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_ENTERPRISE);
		String authStatus = DictionaryUtils.getStringValue(DictionaryKey.AUTH_STATUS_AUTH);
		ul.setUserId(userId);
		ul = userLoginDao.selectOne(ul);
		if(null == ul){
			return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
		}
		//6、更新用户认证状态
		if(person.equals(ul.getUserType())){
			PersonalUser puser = new PersonalUser();
			puser.setUserId(userId);
			puser.setAuthStatus(authStatus);
			puser.setUpdateDatetime(new Date());
			personalUserDao.update(puser);
		}
		else if(enterprise.equals(ul.getUserType())){
			EnterpriseUser euser = new EnterpriseUser();
			euser.setUserId(userId);
			euser.setAuthStatus(authStatus);
			euser.setUpdateDatetime(new Date());
			enterpriseUserDao.update(euser);
		}
		SecurityClassInfo sci = new SecurityClassInfo();
		sci.setUserId(userId);
		sci.setAuthSet(DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_YES));
		sciDao.update(sci);
		//7、返回结果
		return ResponseUtils.buildSuccessResponse();
	}
	
	public void userLogin(UserLoginInfo ufo,UserLoginLog ulog){
		userLoginDao.update(ufo);
		if(null != ulog){
			userLoginLogDao.insert(ulog);
		}
	}
	
	
	/**
	 * 个人、企业信息修改接口调用此方法更改账户状态
	 * @author zhanwx
	 * @date 2014年9月12日 下午1:17:07
	 * @param userId
	 * @param userStatusNow
	 * @param userStatus
	 * @return AccountInfo List
	 * @since 0.1
	 * @see
	 */
	public AttachmentResponse<List<AccountInfo>> changeStatusSupport(Long userId,String userStatusNow,String userStatus){
		try{
			String normal = DictionaryUtils.getStringValue(DictionaryKey.USER_STATUS_NORMAL);
			String frozen = DictionaryUtils.getStringValue(DictionaryKey.USER_STATUS_FROZEN);
			String cancel = DictionaryUtils.getStringValue(DictionaryKey.USER_STATUS_CANCEL);
			if(!userStatus.equals(normal) && !userStatus.equals(frozen) && !userStatus.equals(cancel)){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENT_INCORRECT,new String[]{"userStatus"});
			}
			//1.用户已注销，不能进行任何操作
			if(userStatusNow.equals(cancel)){
				if(userStatus.equals(normal)){
					return ResponseUtils.buildFailureResponse(PortalConstants.UPDATE_INVALID,new String[]{"注销","解冻"});
				}
				if(userStatus.equals(frozen)){
					return ResponseUtils.buildFailureResponse(PortalConstants.UPDATE_INVALID,new String[]{"注销","冻结"});
				}
				if(userStatus.equals(cancel)){
					return ResponseUtils.buildFailureResponse(PortalConstants.UPDATE_INVALID,new String[]{"注销","注销"});
				}
			}
			//2、用户已冻结，不能进行冻结
			if(userStatusNow.equals(frozen)&&userStatus.equals(frozen)){
				return ResponseUtils.buildFailureResponse(PortalConstants.UPDATE_INVALID,new String[]{"冻结","冻结"});
			}
			//3、用户状态正常，不能解冻
			if(userStatusNow.equals(normal)&&userStatus.equals(normal)){
				return ResponseUtils.buildFailureResponse(PortalConstants.UPDATE_INVALID,new String[]{"正常","解冻"});
			}
			//4、正常或冻结的用户进行更新 
			AccountInfoQuery acq = new AccountInfoQuery();
			acq.setUserId(userId);
			List<AccountInfo> acList = accountInfoDao.selectList(acq);
			BigDecimal zero = BigDecimal.ZERO;
			//4.1正常或冻结用户进行注销，检查账户余额
			if(userStatus.equals(cancel)){
				//如果任一账户余额不为零，不能销户
				for(int i = 0; i < acList.size();i++){
					if(acList.get(i).getAvailableBalance().compareTo(zero)==1 ||
					   acList.get(i).getFrozenAmount().compareTo(zero)==1 ||
					   acList.get(i).getNomentionAmount().compareTo(zero)==1){
						String actype = DictionaryUtils.getDisplayName(DictionaryKey.ACCOUNT_TYPE, acList.get(i).getAccountType());
						return ResponseUtils.buildFailureResponse(PortalConstants.ACCOUNT_BALANCE_NOT_ZERO, new String[]{actype});
					}
				}
			}
			//4.2正常用户冻结或冻结用户解冻
			//修改账户状态
			for(int j = 0; j < acList.size();j++){
				acList.get(j).setAccountStatus(userStatus);
				acList.get(j).setUpdateDatetime(new Date());
			}
			return ResponseUtils.buildSuccessResponse(acList);
		}
		catch(Exception e){
			AttachmentResponse<List<AccountInfo>> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}
	
	
	public AttachmentResponse<UpdateUserResponse> checkIfUpdateLoginInfo(Long userId,String userPhone,String loginPhone,String userEmail,String loginEmail,Date date){
		try{
			UpdateUserResponse response = new UpdateUserResponse();
			UserFunctionInfo uf = new UserFunctionInfo();
			SecurityClassInfo sc = new SecurityClassInfo();
			UserLoginInfo ul = new UserLoginInfo();
			String sms = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_SMS_REMIND);
			String open = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_YES);
			String close = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_NO);
			boolean flagPhone = true;
			boolean flagEmail = true;
			//（1）检查手机
			if(userPhone!=null){
				//开通短信提醒
				uf.setUserId(userId);
				uf.setFunctionId(sms);
				uf.setOpenStatus(close);
				uf.setUpdateDatetime(date);
				//设置安全等级记录，绑定手机，开通短信提醒
				sc.setUserId(userId);
				sc.setPhoneSet(open);
				sc.setUpdateDatetime(date);
				//用户未设置该手机为登录名，再检查其他用户
				if(!userPhone.equals(loginPhone)){
					UserLoginInfo ulpu = new UserLoginInfo();
					ulpu.setUserMobile(userPhone);
					ulpu = userLoginDao.selectOne(ulpu);
					if(ulpu != null){
						flagPhone = false;
					}
				}
			}
			else{
				uf = null;
				sc = null;
			}
			//（2）检查邮箱
			if(userEmail!=null){
				//用户未设置该邮箱为登录名，再检查其他用户
				if(!userEmail.equals(loginEmail)){
					UserLoginInfo uleu = new UserLoginInfo();
					uleu.setUserEmail(userEmail);
					uleu = userLoginDao.selectOne(uleu);
					if(uleu != null){
						flagEmail = false;
					}
				}
			}//更新用户信息表里手机或邮箱，检查是否被其他用户设为登录名----End			
			//更新登录表里手机或邮箱-----Begin
			ul.setUserId(userId);
			ul.setUpdateDatetime(date);
			//检查入参中手机是否为空
			if (StringUtils.isNotBlank(userPhone)) {
				//检查登录表中手机是否为空
				if(StringUtils.isNotBlank(loginPhone)){
					//该手机唯一，改变登录表中手机信息
					if(flagPhone){
						ul.setUserMobile(userPhone);
					}
				}
			}
			//检查入参中邮箱是否为空
			if(StringUtils.isNotBlank(userEmail)){
				//检查登录表中邮箱是否为空
				if(StringUtils.isNotBlank(loginEmail)){
					//该邮箱唯一，改变登录表中邮箱信息
					if(flagEmail){
						ul.setUserEmail(userEmail);
					}
				}
			}//更新登录表里手机或邮箱-----End
			response.setSc(sc);
			response.setUf(uf);
			response.setUl(ul);
			return ResponseUtils.buildSuccessResponse(response);
		}
		catch(Exception e){
			AttachmentResponse<UpdateUserResponse> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}
}
