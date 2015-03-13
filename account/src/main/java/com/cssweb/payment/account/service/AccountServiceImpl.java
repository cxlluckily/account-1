package com.cssweb.payment.account.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cssweb.payment.account.constant.DictionaryKey;
import com.cssweb.payment.account.constant.GlobalConstants;
import com.cssweb.payment.account.dao.IAccountInfoDao;
import com.cssweb.payment.account.dao.IEnterpriseUserDao;
import com.cssweb.payment.account.dao.IPersonalUserDao;
import com.cssweb.payment.account.dao.IRateInfoDao;
import com.cssweb.payment.account.dao.IUserLoginDao;
import com.cssweb.payment.account.pojo.AccountInfo;
import com.cssweb.payment.account.pojo.EnterpriseUser;
import com.cssweb.payment.account.pojo.PersonalUser;
import com.cssweb.payment.account.pojo.RateInfo;
import com.cssweb.payment.account.pojo.UserLoginInfo;
import com.cssweb.payment.account.sei.IAccountService;
import com.cssweb.payment.account.util.CreateCardNoUtils;
import com.cssweb.payment.account.util.DictionaryUtils;
import com.cssweb.payment.account.util.SecurityUtils;
import com.cssweb.payment.account.vo.AccountInfoQuery;
import com.cssweb.payment.account.vo.EnterpriseUserQuery;
import com.cssweb.payment.account.vo.PersonalUserQuery;
import com.cssweb.payment.account.vo.UserInfo;
import com.cssweb.payment.common.provider.sei.IIdGeneratorService;
import com.cssweb.payment.common.response.AttachmentResponse;
import com.cssweb.payment.common.util.ResponseUtils;
/**
 * @author Ganlin
 * @version 1.0 (2014年7月16日 下午4:25:51)
 * @since 1.0
 * @see
 */
@Component
public class AccountServiceImpl  implements IAccountService{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	public IAccountInfoDao accountInfoDao;
	
	@Resource
	public IRateInfoDao rateInfoDao;
	
	@Resource
	private IUserLoginDao userLoginDao;
	
	@Resource
	private IPersonalUserDao personalUserDao;
	
	@Resource
	private IEnterpriseUserDao enterpriseUserDao;
	
	@Resource
	private SecurityUtils securityUtils;
	
	@Resource IIdGeneratorService idGeneratorService;
	
	/**
	 * 1001账户状态查询接口
	 * 1.入参检查
	 * 2.查询账户信息
	 * 3.判断账户是否存在
	 * 4.返回账户状态
	 * 5.异常处理
	 * @param @param accountId 账户id
	 * @return AttachmentResponse<String>    返回类型
	 * @author DuXiaohua
	 * @date 2014年7月20日 下午2:34:08
	 * @since 1.0
	 */
	 /* ----------------------------------------------------
	 * 修改记录
	 * 修改者：zhanwx
	 * 修改原因：参照文档0.9版本，设计返回值为UserInfo对象
	 * 修改时间：2014年8月5日 上午11:17:20
	 */
	@Override
	public AttachmentResponse<UserInfo> accountStatusQuery(Long accountId) {
		try {
			if (null == accountId) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"accountId"});
			}
			AccountInfoQuery accountQuery = new AccountInfoQuery(); 
			UserInfo ui = new UserInfo();
			accountQuery.setAccountId(accountId);
			AccountInfo accountInfo = accountInfoDao.selectOne(accountQuery);
			if(null == accountInfo){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ACCOUNT_NOT_EXIST);
			}
			String userType = accountInfo.getUserType();
			//判断用户类型，查询个人表或企业表
			if(userType.equals(DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL))){
				PersonalUserQuery puq = new PersonalUserQuery();
				puq.setUserId(accountInfo.getUserId());
				PersonalUser pu = personalUserDao.selectOne(puq);
				if(null != pu){
					ui.setName(pu.getUserName());
					ui.setEmail(pu.getEmailAddress());
					ui.setTel(pu.getPhoneNumber());
				}
				else{
					return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
				}
			}
			else if(userType.equals(DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_ENTERPRISE))){
				EnterpriseUserQuery euq = new EnterpriseUserQuery();
				euq.setUserId(accountInfo.getUserId());
				EnterpriseUser eu = enterpriseUserDao.selectOne(euq);
				if(null != eu){
					ui.setName(eu.getBusinessName());
					ui.setEmail(eu.getEmail());
					ui.setTel(eu.getManagerTel());
				}
				else{
					return  ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
				}
			}
			else{
				return ResponseUtils.buildFailureResponse(GlobalConstants.USERTYPE_NOT_EXIST);
			}
			String statusName = DictionaryUtils.getDisplayName(DictionaryKey.ACCOUNT_STATUS, accountInfo.getAccountStatus());
			String usertypeName = DictionaryUtils.getDisplayName(DictionaryKey.USER_TYPE, accountInfo.getUserType());
			ui.setAccountStatus(accountInfo.getAccountStatus());
			ui.setAccountStatusName(statusName);
			ui.setUsertype(accountInfo.getUserType());
			ui.setUsertypeName(usertypeName);
			ui.setAccountId(accountInfo.getAccountId());
			ui.setUserId(accountInfo.getUserId());
			return ResponseUtils.buildSuccessResponse(ui);
		}
		catch (Exception e) {
			AttachmentResponse<UserInfo> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}
	
	/**
	 * 1002获取账户余额接口
	 * 1.入参检查
	 * 2.查询账户信息
	 * 3.判断账户是否存在
	 * 4.返回账户余额
	 * 5.异常处理
	 * @param @param accountId 账户id
	 * @return AttachmentResponse<AccountInfo>    返回类型
	 * @author zhanwx/ganlin
	 * @date 2014-7-21 下午4:30:50 
	 * @throws
	 */
	@Override
	public AttachmentResponse<AccountInfo> accountBalanceQuery(Long accountId) {
		try {
			if (null == accountId) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"accountId"});
			}
			AccountInfoQuery accountQuery = new AccountInfoQuery();
			accountQuery.setAccountId(accountId);
			AccountInfo account = accountInfoDao.selectOne(accountQuery);
			if (account == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ACCOUNT_NOT_EXIST);
			}
			return ResponseUtils.buildSuccessResponse(account);
		} 
		catch (Exception e) {
			AttachmentResponse<AccountInfo> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1048	用户获取账户列表查询接口
	 * 1.入参检查
	 * 2.查询用户信息
	 * 3.判断用户是否存在
	 * 4.返回用户所拥有的账户列表
	 * 5.异常处理
	 * @param  userId 用户id
	 * @return AttachmentResponse<List<AccountInfo>>    返回类型
	 * @author zhanwx/ganlin
	 * @date 2014-7-21 下午14:02:42 
	 * @throws
	 */
	@Override
	public AttachmentResponse<List<AccountInfo>> getAccountList(Long userId) {
		try {
			if (null == userId) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"userId"});
			}
			AccountInfoQuery accountQuery = new AccountInfoQuery();
			accountQuery.setUserId(userId);
			List<AccountInfo> accountList = accountInfoDao.selectList(accountQuery);
			if (accountList.isEmpty()) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			}
			return ResponseUtils.buildSuccessResponse(accountList);
		} 
		catch (Exception e) {
			AttachmentResponse<List<AccountInfo>> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1049	手续费查询接口
	 * 1.入参检查
	 * 2.查询手续费信息
	 * 3.判断所查询的手续费记录是否存在
	 * 4.返回费率
	 * 5.异常处理
	 * @param  merchantId
	 * @param  rateId
	 * @return AttachmentResponse<Float>    返回类型
	 * @author zhanwx/ganlin
	 * @date 2014-7-21 下午14:20:34 
	 * @throws
	 */
	@Override
	public AttachmentResponse<Float> getRate(Long merchantId, String rateId) {
		try {
			if (null == merchantId || null == rateId) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"merchantId 或 rateId"});
			}
			RateInfo rateInfo = new RateInfo();
			rateInfo.setMerchantId(merchantId);
			rateInfo.setRateId(rateId);
			rateInfo = rateInfoDao.selectOne(rateInfo);
			if (null == rateInfo) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.RATE_NOT_EXIST);
			}
			return ResponseUtils.buildSuccessResponse(rateInfo.getRate());
		} 
		catch (Exception e) {
			AttachmentResponse<Float> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1096	根据用户名获取账户余额接口
	 * @author Ganlin
	 * @date 2014年7月25日 上午10:28:33
	 * @param userName 用户名，手机号码或邮箱
	 * @param payPwd 支付密码
	 * @return AttachmentResponse<AccountInfo> 附加对象为账户信息对象，包含accountId【主账户accountId】，userType【个人或企业】
	 * @since 0.1
	 */
	public AttachmentResponse<AccountInfo> getBalanceByUsername(String userName, String payPwd) {
		try {
			//入参检查
			if (null == userName || null == payPwd) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[] {"userName or payPwd"});
			}
			//根据用户名查询用户Id
			UserLoginInfo user = new UserLoginInfo();
			//判断用户名是手机号码还是邮箱
			if (PortalServiceImpl.isNumeric(userName)) {
				user.setUserMobile(userName);
			} 
			else {
				user.setUserEmail(userName);
			}
			user = userLoginDao.selectOne(user);
			if (null == user) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			}
			//支付密码加密
			String ciphertext =  securityUtils.encryptMD5(payPwd, user.getCreateDatetime());
			
			//判断支付密码是否匹配
			if (user.getPayPasswd().equals(ciphertext)) {
				//支付错误次数清零
				UserLoginInfo update = new UserLoginInfo();
				update.setUserId(user.getUserId());
				update.setErrorPayTimes(0);
				update.setUpdateDatetime(new Date());
				userLoginDao.update(update);
			} 
			else {
				//增加支付密码错误次数
				UserLoginInfo update = new UserLoginInfo();
				update.setUserId(user.getUserId());
				update.setErrorPayTimes(user.getErrorPayTimes() == null? 1: user.getErrorPayTimes() + 1);
				update.setUpdateDatetime(new Date());
				userLoginDao.update(update);
				return ResponseUtils.buildFailureResponse(GlobalConstants.WRONG_PAY_PWD);
			}
			//根据userId和accountType查询主账户
			String accountTypeMain = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_TYPE_MAIN);
			AccountInfoQuery accountQuery = new AccountInfoQuery();
			accountQuery.setUserId(user.getUserId());
			accountQuery.setAccountType(accountTypeMain);
			AccountInfo account = accountInfoDao.selectOne(accountQuery);
			if (null == account) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ACCOUNT_NOT_EXIST);
			}
			return ResponseUtils.buildSuccessResponse(account);
		} 
		catch (Exception e) {
			AttachmentResponse<AccountInfo> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}
	
	/**
	 * 1106 根据账户ID获取用户信息
	 * @author zhanwx
	 * @date 2014年8月1日 下午5:47:02
	 * @param accountId
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public AttachmentResponse<UserInfo> queryUserBasicInfoByAccountId(Long accountId){
		try {
			//入参检查
			if (null == accountId) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[] {"accountId"});
			}
			String userTypePerson = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL);
			String userTypeEnterprise = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_ENTERPRISE);
			AccountInfoQuery query = new AccountInfoQuery();
			query.setAccountId(accountId);
			AccountInfo account = accountInfoDao.selectOne(query);
			if(null == account){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ACCOUNT_NOT_EXIST);
			}
			//判断用户类型
			String userType = account.getUserType();
			UserInfo userInfo = new UserInfo();
			if(userType.equals(userTypePerson)) {
				PersonalUserQuery query2 = new PersonalUserQuery();
				query2.setUserId(account.getUserId());
				PersonalUser user = personalUserDao.selectOne(query2);
				userInfo.setName(user.getUserName());
				userInfo.setEmail(user.getEmailAddress());
				userInfo.setTel(user.getPhoneNumber());
			} 
			if(userType.equals(userTypeEnterprise)){
				EnterpriseUserQuery query3 = new EnterpriseUserQuery();
				query3.setUserId(account.getUserId());
				EnterpriseUser enterpriseUser = enterpriseUserDao.selectOne(query3);
				userInfo.setName(enterpriseUser.getBusinessName());
				userInfo.setEmail(enterpriseUser.getEmail());
				userInfo.setTel(enterpriseUser.getManagerTel());
			}
			userInfo.setUserId(account.getUserId());
			userInfo.setAccountId(accountId);
			userInfo.setUsertype(account.getUserType());
			userInfo.setAccountStatus(account.getAccountStatus());
			return ResponseUtils.buildSuccessResponse(userInfo);
		} 
		catch(Exception e) {
			AttachmentResponse<UserInfo> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}


}
