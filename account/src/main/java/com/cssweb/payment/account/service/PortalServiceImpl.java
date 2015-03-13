
package com.cssweb.payment.account.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cssweb.payment.account.constant.DictionaryKey;
import com.cssweb.payment.account.constant.GlobalConstants;
import com.cssweb.payment.account.constant.PortalConstants;
import com.cssweb.payment.account.dao.IAccountInfoDao;
import com.cssweb.payment.account.dao.IAccountMoneyChgDetailDao;
import com.cssweb.payment.account.dao.IAccountOperInfoDao;
import com.cssweb.payment.account.dao.IEnterpriseUserDao;
import com.cssweb.payment.account.dao.IFinancialSummaryDayDao;
import com.cssweb.payment.account.dao.IFinancialSummaryMonthDao;
import com.cssweb.payment.account.dao.IPersonalUserDao;
import com.cssweb.payment.account.dao.ISecurityClassInfoDao;
import com.cssweb.payment.account.dao.IUserBandBankInfoDao;
import com.cssweb.payment.account.dao.IUserBankAuInfoDao;
import com.cssweb.payment.account.dao.IUserFunctionInfoDao;
import com.cssweb.payment.account.dao.IUserLoginDao;
import com.cssweb.payment.account.dao.IUserLoginLogDao;
import com.cssweb.payment.account.dao.IUserQuestionDao;
import com.cssweb.payment.account.dao.IUserRelAuInfoDao;
import com.cssweb.payment.account.pojo.AccountInfo;
import com.cssweb.payment.account.pojo.EnterpriseUser;
import com.cssweb.payment.account.pojo.FinancialSummaryDay;
import com.cssweb.payment.account.pojo.FinancialSummaryMonth;
import com.cssweb.payment.account.pojo.PersonalUser;
import com.cssweb.payment.account.pojo.SecurityClassInfo;
import com.cssweb.payment.account.pojo.UserBandBankInfo;
import com.cssweb.payment.account.pojo.UserBankAuInfo;
import com.cssweb.payment.account.pojo.UserFunctionInfo;
import com.cssweb.payment.account.pojo.UserLoginInfo;
import com.cssweb.payment.account.pojo.UserLoginLog;
import com.cssweb.payment.account.pojo.UserQuestionInfo;
import com.cssweb.payment.account.pojo.UserRelAuInfo;
import com.cssweb.payment.account.sei.IPortalService;
import com.cssweb.payment.account.service.support.AccountServiceSupport;
import com.cssweb.payment.account.service.support.ManageServiceSupport;
import com.cssweb.payment.account.service.support.PortalServiceSupport;
import com.cssweb.payment.account.util.DictionaryUtils;
import com.cssweb.payment.account.util.SecurityUtils;
import com.cssweb.payment.account.vo.AccountInfoQuery;
import com.cssweb.payment.account.vo.AccountMoneyChgDetailQuery;
import com.cssweb.payment.account.vo.EnterpriseAuProcessQueryResponse;
import com.cssweb.payment.account.vo.EnterpriseUserQuery;
import com.cssweb.payment.account.vo.FinancialSummaryDayQuery;
import com.cssweb.payment.account.vo.FinancialSummaryMonthQuery;
import com.cssweb.payment.account.vo.GetEnterpriseUserBasicInfoResponse;
import com.cssweb.payment.account.vo.GetPersonalUserListResponse;
import com.cssweb.payment.account.vo.GetUserRelAuInfoResponse;
import com.cssweb.payment.account.vo.LoginResponse;
import com.cssweb.payment.account.vo.PersonalAuProcessResponse;
import com.cssweb.payment.account.vo.PersonalUserQuery;
import com.cssweb.payment.account.vo.StatusInfo;
import com.cssweb.payment.account.vo.UpdateUserResponse;
import com.cssweb.payment.account.vo.UserBasicInfoResponse;
import com.cssweb.payment.common.provider.sei.IIdGeneratorService;
import com.cssweb.payment.common.response.AttachmentResponse;
import com.cssweb.payment.common.util.Page;
import com.cssweb.payment.common.util.ResponseUtils;
@Component
public class PortalServiceImpl implements IPortalService{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private AccountServiceSupport accountServiceSupport;
	@Resource
	private PortalServiceSupport portalServiceSupport;
	
	@Resource
	private ManageServiceSupport manageServiceSupport;	
	
	@Resource
	private IUserLoginDao userLoginDao;
	
	@Resource
	private IUserQuestionDao userQuestionDao;
	
	@Resource
	private IPersonalUserDao personalUserDao;
	
	@Resource
	private IEnterpriseUserDao enterpriseUserDao;
	
	@Resource
	private IUserFunctionInfoDao userFunctionDao;
	
	@Resource
	private IUserBandBankInfoDao userBandBankInfoDao;
	
	@Resource
	private IAccountInfoDao accountInfoDao;
	
	@Resource
	private IUserBankAuInfoDao userBankAuInfoDao;
	
	@Resource 
	private IIdGeneratorService idGeneratorService;
	
	@Resource
	private ISecurityClassInfoDao sciDao;
	
	@Resource
	private IAccountOperInfoDao accountOperInfoDao;
	
	@Resource
	private IAccountMoneyChgDetailDao moneyChgDao;
	
	@Resource
	private IUserLoginLogDao userLoginLogDao;
	
	@Resource
	private IUserRelAuInfoDao userRelAuInfoDao;
	
	@Resource
	private SecurityUtils securityUtils;
	
	@Resource
	private IFinancialSummaryDayDao financialSummaryDayDao;
	
	
	@Resource
	private IFinancialSummaryMonthDao financialSummaryMonthDao;
	/**
	 * 判断一个字符串是否为纯数字
	 * @author Ganlin
	 * @date 2014年7月21日 下午4:08:49
	 * @param str
	 * @return true为纯数字；false则不是
	 * @since 0.1
	 * @see
	 */
	public static boolean isNumeric(String str) {
		for (int i = str.length();--i>=0;) {   
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 判断是否是邮箱
	 * @param email
	 * @return
	 */
	 public static boolean isEmail(String email){     
	    String str="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);     
        Matcher m = p.matcher(email);     
        return m.matches();     
	 } 
	
	/**
	 * 1018	账户支付密码验证接口
	 * 1.入参检查
	 * 2.判断用户名是否纯数字
	 * 3.判断用户是否存在，不存在即返回提示
	 * 4.用户存在，判断密码是否匹配，返回结果
	 * 5.异常处理
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-2 下午6:42:11 
	 * @since 0.1
	 */
	 /* ----------------------------------------------------------------------------
	 * 修改记录:
	 * 修改内容：用户不存在，返回 GlobalConstants.USER_NOT_EXIST ;
	 * 修改者:zhanwx
	 * 时间:2014-7-29 上午9:13
	 */
	@Override
	public AttachmentResponse<Boolean> verifyPayPwd(String userName, String payPasswd) {
		try {
			//入参检查
			if (userName == null || payPasswd == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"userName or payPwd"});
			}
			UserLoginInfo user = new UserLoginInfo();
			//判断用户名是否纯数字
			if (isNumeric(userName)) {
				user.setUserMobile(userName);
			} else {
				user.setUserEmail(userName);
			}
			user = userLoginDao.selectOne(user);
			//判断用户是否存在
			if (user == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			} 
			else {
				String userTypePerson = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL);
				String userTypeEn = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_ENTERPRISE);
				if(userTypePerson.equals(user.getUserType())){
					if(user.getErrorPayTimes()==null){
						user.setErrorPayTimes(0);
					}
					if(user.getErrorPayTimes()>=3){
						return ResponseUtils.buildFailureResponse(PortalConstants.PAY_PWD_ERRORTIME_BEYOND, false);
					}
				}
				if(userTypeEn.equals(user.getUserType())){
					if(user.getErrorPayTimes()==null){
						user.setErrorPayTimes(0);
					}
					if(user.getErrorPayTimes()>=5){
						return ResponseUtils.buildFailureResponse(PortalConstants.PAY_PWD_ERRORTIME_BEYOND, false);
					}
				}
				//密码加密
				payPasswd = securityUtils.encryptMD5(payPasswd, user.getCreateDatetime());
				//判断支付密码是否匹配
				if (user.getPayPasswd().equals(payPasswd)) {
					//支付密码错误次数清零
					UserLoginInfo update = new UserLoginInfo();
					update.setUserId(user.getUserId());
					update.setErrorPayTimes(0);
					update.setUpdateDatetime(new Date());
					userLoginDao.update(update);
					return ResponseUtils.buildSuccessResponse(true);
				} 
				else {
					//增加支付密码错误次数
					UserLoginInfo update = new UserLoginInfo();
					update.setUserId(user.getUserId());
					update.setErrorPayTimes(user.getErrorPayTimes() == null? 1: user.getErrorPayTimes() + 1);
					update.setUpdateDatetime(new Date());
					userLoginDao.update(update);
					return ResponseUtils.buildFailureResponse(GlobalConstants.WRONG_PAY_PWD, false);
				}
			}
		} 
		catch (Exception e) {
			AttachmentResponse<Boolean> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1019	用户名是否存在接口
	 * 1.入参检查
	 * 2.判断用户名是否纯数字
	 * 3.查询用户登录表判断用户是否存在，不存在即返回提示
	 * 4.异常处理
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-2 下午6:45:05 
	 * @since 0.1
	 */
	@Override
	public AttachmentResponse<Boolean> checkUserNameIsExits(String userName) {
		try {
			//入参检查
			if (userName == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"userName"});
			}
			UserLoginInfo user = new UserLoginInfo();
			//判断用户名是否纯数字
			if (isNumeric(userName)) {
					user.setUserMobile(userName);
			} 
			else {
					user.setUserEmail(userName);
			}
			user = userLoginDao.selectOne(user);
			//判断用户是否存在
			if (user == null) {
				return ResponseUtils.buildSuccessResponse(false);
			} else {
				return ResponseUtils.buildSuccessResponse(true);
			}
		} 
		catch (Exception e) {
			AttachmentResponse<Boolean> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1020	用户（个人）注册接口
	 * 1.入参检查
	 * 2.检查手机是否被注册
	 * 3.检查邮箱是否被注册
	 * 4.个人用户信息表、登录表、用户安全问题表、账户表、功能表、安全等级表中插入记录
	 * 5.异常处理
	 * @author Ganlin
	 * @date 2014年7月22日 下午3:54:06
	 * @since 0.1
	 * @param nationality 地区
	 * @param mobile 手机号
	 * @param name 姓名
	 * @param email 邮箱
	 * @param regType 注册类型
	 * @param source 来源
	 * @param cardType 证件类型
	 * @param cardId 证件号码
	 * @param loginPwd 登录密码
	 * @param payPwd 支付密码
	 * @param questionId 安全问题
	 * @param answer 答案
	 */
	@Override
	public AttachmentResponse<Long> personalRegistration(String nationality, String mobile, 
			String name, String email, String regType, String source, String cardType, String cardId, 
			String loginPwd, String payPwd, int questionId, String answer) {
		try {
			//入参检查 
			if (mobile == null && email == null) {
				AttachmentResponse<Long> response = ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,  new String[] {"phoneNum and emailAddress"});
				return response;
			}
			//检查登录密码和支付密码是否相同
			if (loginPwd.equals(payPwd)) {
				return ResponseUtils.buildFailureResponse(PortalConstants.LOGINPWD_PAYPWD_MUST_DIFFERENT);
			}
			//检查手机号或邮箱是否已存在于登录表
			UserLoginInfo ul;
			//用手机注册
			if (mobile != null) {
				UserLoginInfo query = new UserLoginInfo();
				query.setUserMobile(mobile);
				ul = userLoginDao.selectOne(query);
				if(ul != null){
					return ResponseUtils.buildFailureResponse(PortalConstants.PHONE_NUM_EXIST);
				}
			}
			//用邮箱注册
			if (email != null) {
				UserLoginInfo query = new UserLoginInfo();
				query.setUserEmail(email);
				ul = userLoginDao.selectOne(query);
				if(ul != null){
					return ResponseUtils.buildFailureResponse(PortalConstants.EMAIL_EXIST);
				}
			}
			//统一用UserLoginInfo的类名作为关键字生成userId
			AttachmentResponse<Long> userIdRes = idGeneratorService.generate(UserLoginInfo.class.getName());
			if (userIdRes.getReturnCode() != 0) {
				return ResponseUtils.buildFailureResponse(PortalConstants.USER_ID_GEN_FAIL);
			}
			Long userId = userIdRes.getAttachment();
			PersonalUser user = new PersonalUser();
			user.setCountryCode(nationality);
			user.setUserId(userId);
			user.setPhoneNumber(mobile);
			user.setUserName(name);
			user.setEmailAddress(email);
			user.setRegType(regType);
			user.setSource(source);
			user.setAuthCertType(cardType);
			user.setAuthCertId(cardId);	
			String authStatus = DictionaryUtils.getStringValue(DictionaryKey.AUTH_STATUS_NONE);
			user.setAuthStatus(authStatus);
			user.setCreateDatetime(new Date());
			user.setUpdateDatetime(new Date());
		
			String userTypePerson = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL);
			String userStatusNormal = DictionaryUtils.getStringValue(DictionaryKey.USER_STATUS_NORMAL);
			
			
			// 登陆　、　支付密码加密
			Date currentDate = new Date();
			loginPwd = securityUtils.encryptMD5(loginPwd, currentDate);
			payPwd = securityUtils.encryptMD5(payPwd, currentDate);
			
			UserLoginInfo userLogin = new UserLoginInfo();
			userLogin.setUserId(userId);
			userLogin.setUserType(userTypePerson);
			userLogin.setUserMobile(mobile);
			userLogin.setUserEmail(email);
			userLogin.setLoginPasswd(loginPwd);
			userLogin.setPayPasswd(payPwd);
			userLogin.setErrorLoginTimes(0);
			userLogin.setErrorPayTimes(0);
			userLogin.setUserStatus(userStatusNormal);
			userLogin.setCreateDatetime(currentDate);
			userLogin.setUpdateDatetime(currentDate);
			
			AttachmentResponse<Long> userQuestionResponse = idGeneratorService.generate(UserQuestionInfo.class.getName());
			UserQuestionInfo userQuestion = new UserQuestionInfo();
			userQuestion.setId(userQuestionResponse.getAttachment());
			userQuestion.setUserId(userId);
			userQuestion.setQuestionId(questionId);
			userQuestion.setQuestionContent(answer);
			userQuestion.setCreateDatetime(new Date());
			userQuestion.setUpdateDatetime(new Date());
			
			List<AccountInfo> accountInfoList = new ArrayList<AccountInfo>();
			//初始化账户
			/*AttachmentResponse<Long> accountIdRes = idGeneratorService.generate(AccountInfo.class.getName());
			if (accountIdRes.getReturnCode() != 0) {
				return ResponseUtils.buildFailureResponse(PortalConstants.ACCOUNT_ID_GEN_FAIL);
			}
			Long accountId = accountIdRes.getAttachment();*/
			String accountTypeMain = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_TYPE_MAIN);
			String accountStatusNormal = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_STATUS_NORMAL);
			String currencyTypeRMB = DictionaryUtils.getStringValue(DictionaryKey.CURRENCY_TYPE_RMB);
			String functionPay = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_BALANCE_PAY);
			String functionSMS = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_SMS_REMIND);
			String open = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_YES);
			String clos = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_NO);
			AttachmentResponse<Long> accountResponse = accountServiceSupport.createCardNumber(accountTypeMain);
			if (accountResponse.getReturnCode() != 0){
				return ResponseUtils.buildFailureResponse(PortalConstants.ACCOUNT_ID_GEN_FAIL);
			}
			Long accountId = accountResponse.getAttachment();
			AccountInfo account = new AccountInfo();
			account.setUserId(userId);
			account.setUserType(userTypePerson);
			account.setAccountId(accountId);
			account.setAccountType(accountTypeMain);
			account.setAccountStatus(accountStatusNormal);
			account.setCurrencyType(currencyTypeRMB);
			account.setAvailableBalance(new BigDecimal(0));
			account.setFrozenAmount(new BigDecimal(0));
			account.setNomentionAmount(new BigDecimal(0));
			account.setMac("");
			account.setCreateDatetime(new Date());
			account.setUpdateDatetime(new Date());
			accountInfoList.add(account);
			//创建ID
			AttachmentResponse<Long[]> idRes = idGeneratorService.generate(UserFunctionInfo.class.getName(), 2);
			if (idRes.getReturnCode() != 0) {
				return ResponseUtils.buildFailureResponse(PortalConstants.ID_GEN_FAIL);
			}
			
			List<UserFunctionInfo> functionInfoList = new ArrayList<UserFunctionInfo>();
			//设置余额支付功能
			UserFunctionInfo userFunc1 = new UserFunctionInfo();
			userFunc1.setId(idRes.getAttachment()[0]);
			userFunc1.setUserId(userId);
			userFunc1.setFunctionId(functionPay);
			userFunc1.setOpenStatus(open);
			userFunc1.setCreateDatetime(new Date());
			userFunc1.setUpdateDatetime(new Date());
			//设置短信提醒功能
			UserFunctionInfo userFunc2 = new UserFunctionInfo();
			userFunc2.setId(idRes.getAttachment()[1]);
			userFunc2.setUserId(userId);
			userFunc2.setFunctionId(functionSMS);
			userFunc2.setCreateDatetime(new Date());
			userFunc2.setUpdateDatetime(new Date());
			
			SecurityClassInfo sci = new SecurityClassInfo();
			sci.setUserId(userId);
			sci.setBalancepaySet(open);
			sci.setQuestionSet(open);
			sci.setVerifySet(clos);
			sci.setCertSet(clos);
			sci.setAuthSet(clos);
			sci.setReservationSet(clos);
			sci.setUpdateDatetime(new Date());
			//根据是否手机注册设置开通状态
			if(mobile != null){
				userFunc2.setOpenStatus(clos);
				sci.setPhoneSet(open);
			}
			if(mobile == null){
				userFunc2.setOpenStatus(clos);
				sci.setPhoneSet(clos);
			}
			
			functionInfoList.add(userFunc1);
			functionInfoList.add(userFunc2);
			
			portalServiceSupport.personalRegistration(user, userLogin, userQuestion, accountInfoList, functionInfoList,sci);
			return ResponseUtils.buildSuccessResponse(userId);
		} catch (Exception e) {
			AttachmentResponse<Long> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}
	
	/**
	 * 理财用户注册的接口
	 * @author nyc
	 * @date 2014年12月19日 下午4:23:27
	 * @param nationality：国籍
	 * @param mobile：手机号码
	 * @param regType：注册方式
	 * @param source：用户来源
	 * @param loginPwd：登录密码
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public AttachmentResponse<Long> personalRegistration(String nationality,
			String mobile, String regType, String source, String loginPwd) {
		logger.debug(" loginPwd password is =="+loginPwd);
		try {
			//入参检查 
			if (mobile == null) {
				AttachmentResponse<Long> response = ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,  new String[] {"phoneNum"});
				return response;
			}
			
			//检查手机号或邮箱是否已存在于登录表
			UserLoginInfo ul;
			//用手机注册
			if (mobile != null) {
				UserLoginInfo query = new UserLoginInfo();
				query.setUserMobile(mobile);
				ul = userLoginDao.selectOne(query);
				if(ul != null){
					return ResponseUtils.buildFailureResponse(PortalConstants.PHONE_NUM_EXIST);
				}
			}
			
			//统一用UserLoginInfo的类名作为关键字生成userId
			AttachmentResponse<Long> userIdRes = idGeneratorService.generate(UserLoginInfo.class.getName());
			if (userIdRes.getReturnCode() != 0) {
				return ResponseUtils.buildFailureResponse(PortalConstants.USER_ID_GEN_FAIL);
			}
			Long userId = userIdRes.getAttachment();
			PersonalUser user = new PersonalUser();
			user.setCountryCode(nationality);
			user.setUserId(userId);
			user.setPhoneNumber(mobile);
			//user.setUserName(name);
			//user.setEmailAddress(email);
			user.setRegType(regType);
			user.setSource(source);
			//user.setAuthCertType(cardType);
			//user.setAuthCertId(cardId);	
			String authStatus = DictionaryUtils.getStringValue(DictionaryKey.AUTH_STATUS_NONE);
			user.setAuthStatus(authStatus);
			user.setCreateDatetime(new Date());
			user.setUpdateDatetime(new Date());
		
			String userTypePerson = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL);
			String userStatusNormal = DictionaryUtils.getStringValue(DictionaryKey.USER_STATUS_NORMAL);
			
			
			// 登陆　、　支付密码加密
			Date currentDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date=sdf.format(currentDate);
			try {
				currentDate=sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			logger.debug("11111111111111==="+loginPwd);
			loginPwd = securityUtils.encryptMD5(loginPwd, currentDate);
			logger.debug("222222222222==="+loginPwd);
			logger.debug("currentDate===>"+currentDate);
			//payPwd = securityUtils.encryptMD5(payPwd, currentDate);
			
			UserLoginInfo userLogin = new UserLoginInfo();
			userLogin.setUserId(userId);
			userLogin.setUserType(userTypePerson);
			userLogin.setUserMobile(mobile);
			//userLogin.setUserEmail(email);
			userLogin.setLoginPasswd(loginPwd);
			//userLogin.setPayPasswd(payPwd);
			userLogin.setErrorLoginTimes(0);
			userLogin.setErrorPayTimes(0);
			userLogin.setUserStatus(userStatusNormal);
			userLogin.setCreateDatetime(currentDate);
			userLogin.setUpdateDatetime(currentDate);
			
			/*AttachmentResponse<Long> userQuestionResponse = idGeneratorService.generate(UserQuestionInfo.class.getName());
			UserQuestionInfo userQuestion = new UserQuestionInfo();
			userQuestion.setId(userQuestionResponse.getAttachment());
			userQuestion.setUserId(userId);
			userQuestion.setQuestionId(questionId);
			userQuestion.setQuestionContent(answer);
			userQuestion.setCreateDatetime(new Date());
			userQuestion.setUpdateDatetime(new Date());*/
			
			List<AccountInfo> accountInfoList = new ArrayList<AccountInfo>();
			//初始化账户
			/*AttachmentResponse<Long> accountIdRes = idGeneratorService.generate(AccountInfo.class.getName());
			if (accountIdRes.getReturnCode() != 0) {
				return ResponseUtils.buildFailureResponse(PortalConstants.ACCOUNT_ID_GEN_FAIL);
			}
			Long accountId = accountIdRes.getAttachment();*/
			String accountTypeMain = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_TYPE_MAIN);
			String accountStatusNormal = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_STATUS_NORMAL);
			String currencyTypeRMB = DictionaryUtils.getStringValue(DictionaryKey.CURRENCY_TYPE_RMB);
			String functionPay = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_BALANCE_PAY);
			String functionSMS = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_SMS_REMIND);
			String open = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_YES);
			String clos = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_NO);
			AttachmentResponse<Long> accountResponse = accountServiceSupport.createCardNumber(accountTypeMain);
			if (accountResponse.getReturnCode() != 0){
				return ResponseUtils.buildFailureResponse(PortalConstants.ACCOUNT_ID_GEN_FAIL);
			}
			Long accountId = accountResponse.getAttachment();
			AccountInfo account = new AccountInfo();
			account.setUserId(userId);
			account.setUserType(userTypePerson);
			account.setAccountId(accountId);
			account.setAccountType(accountTypeMain);
			account.setAccountStatus(accountStatusNormal);
			account.setCurrencyType(currencyTypeRMB);
			account.setAvailableBalance(new BigDecimal(0));
			account.setFrozenAmount(new BigDecimal(0));
			account.setNomentionAmount(new BigDecimal(0));
			account.setMac("");
			account.setCreateDatetime(new Date());
			account.setUpdateDatetime(new Date());
			accountInfoList.add(account);
			//创建ID
			AttachmentResponse<Long[]> idRes = idGeneratorService.generate(UserFunctionInfo.class.getName(), 2);
			if (idRes.getReturnCode() != 0) {
				return ResponseUtils.buildFailureResponse(PortalConstants.ID_GEN_FAIL);
			}
			
			List<UserFunctionInfo> functionInfoList = new ArrayList<UserFunctionInfo>();
			//设置余额支付功能
			UserFunctionInfo userFunc1 = new UserFunctionInfo();
			userFunc1.setId(idRes.getAttachment()[0]);
			userFunc1.setUserId(userId);
			userFunc1.setFunctionId(functionPay);
			userFunc1.setOpenStatus(open);
			userFunc1.setCreateDatetime(new Date());
			userFunc1.setUpdateDatetime(new Date());
			//设置短信提醒功能
			UserFunctionInfo userFunc2 = new UserFunctionInfo();
			userFunc2.setId(idRes.getAttachment()[1]);
			userFunc2.setUserId(userId);
			userFunc2.setFunctionId(functionSMS);
			userFunc2.setCreateDatetime(new Date());
			userFunc2.setUpdateDatetime(new Date());
			
			SecurityClassInfo sci = new SecurityClassInfo();
			sci.setUserId(userId);
			sci.setBalancepaySet(open);
			sci.setQuestionSet(open);
			sci.setVerifySet(clos);
			sci.setCertSet(clos);
			sci.setAuthSet(clos);
			sci.setReservationSet(clos);
			sci.setUpdateDatetime(new Date());
			//根据是否手机注册设置开通状态
			if(mobile != null){
				userFunc2.setOpenStatus(clos);
				sci.setPhoneSet(open);
			}
			if(mobile == null){
				userFunc2.setOpenStatus(clos);
				sci.setPhoneSet(clos);
			}
			
			functionInfoList.add(userFunc1);
			functionInfoList.add(userFunc2);
			logger.debug("44444444444444==="+userLogin.getCreateDatetime());
			portalServiceSupport.personalRegistration(user, userLogin, null, accountInfoList, functionInfoList,sci);
			return ResponseUtils.buildSuccessResponse(userId);
		} catch (Exception e) {
			AttachmentResponse<Long> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1021	企业注册接口
	 * 1.入参检查
	 * 2.检查邮箱是否被注册
	 * 3.个人用户信息表、登录表、用户安全问题表、账户表、功能表、安全等级表中插入记录
	 * 4.异常处理
	 * @author Ganlin
	 * @date 2014年7月23日 下午3:53:22
	 * @since 0.1
	 */
	@Override
	public AttachmentResponse<Long>  enterpriseRegistration(String userName, String loginPwd, 
			String payPwd, int questionId, String answer, String source) {
		try {
			//入参检查 
			if (userName == null) {
				AttachmentResponse<Long> response = ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,  new String[] {"userName"});
				return response;
			}
			//检查登录密码和支付密码是否相同
			if (loginPwd.equals(payPwd)) {
				return ResponseUtils.buildFailureResponse(PortalConstants.LOGINPWD_PAYPWD_MUST_DIFFERENT);
			}
			//检查邮箱是否存在于登录表
			UserLoginInfo ul = new UserLoginInfo();
			ul.setUserEmail(userName);
			ul = userLoginDao.selectOne(ul);
			if (ul != null) {
				return ResponseUtils.buildFailureResponse(PortalConstants.EMAIL_EXIST);
			} 
			//统一用UserLoginInfo的类名作为关键字生成userId
			AttachmentResponse<Long> userIdRes = idGeneratorService.generate(UserLoginInfo.class.getName());
			if (userIdRes.getReturnCode() != 0) {
				return ResponseUtils.buildFailureResponse(PortalConstants.USER_ID_GEN_FAIL);
			}
			Long userId = userIdRes.getAttachment();
			EnterpriseUser user = new EnterpriseUser();
			String userTypeEn = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_ENTERPRISE);
			String userStatusNormal = DictionaryUtils.getStringValue(DictionaryKey.USER_STATUS_NORMAL);
			String authStatus = DictionaryUtils.getStringValue(DictionaryKey.AUTH_STATUS_NONE);
			user.setUserId(userId);
			user.setEmail(userName);
			user.setSource(source);
			user.setAuthStatus(authStatus);
			user.setCreateDatetime(new Date());
			user.setUpdateDatetime(new Date());
			String statusNone = DictionaryUtils.getStringValue(DictionaryKey.BUSINESS_STATUS_NONE);
			user.setIsMerchant(statusNone);
			// 登陆密码、支付密码加密
			Date currentDate = new Date();
			currentDate = DateUtils.setMilliseconds(currentDate, 0);
			loginPwd = securityUtils.encryptMD5(loginPwd, currentDate);
			payPwd = securityUtils.encryptMD5(payPwd, currentDate);
			
			UserLoginInfo userLogin = new UserLoginInfo();
			userLogin.setUserId(userId);
			userLogin.setUserType(userTypeEn);
			userLogin.setUserEmail(userName);
			userLogin.setLoginPasswd(loginPwd);
			userLogin.setPayPasswd(payPwd);
			userLogin.setErrorPayTimes(0);
			userLogin.setErrorLoginTimes(0);
			userLogin.setUserStatus(userStatusNormal);
			userLogin.setCreateDatetime(currentDate);
			userLogin.setUpdateDatetime(currentDate);
			
			AttachmentResponse<Long> userQuestionResponse = idGeneratorService.generate(UserQuestionInfo.class.getName());
			UserQuestionInfo userQuestion = new UserQuestionInfo();
			userQuestion.setId(userQuestionResponse.getAttachment());
			userQuestion.setUserId(userId);
			userQuestion.setQuestionId(questionId);
			userQuestion.setQuestionContent(answer);
			userQuestion.setCreateDatetime(new Date());
			userQuestion.setUpdateDatetime(new Date());
			
			//初始化账户
			/*AttachmentResponse<Long> accountIdRes = idGeneratorService.generate(AccountInfo.class.getName());
			if (accountIdRes.getReturnCode() != 0) {
				return ResponseUtils.buildFailureResponse(PortalConstants.ACCOUNT_ID_GEN_FAIL);
			}
			Long accountId = accountIdRes.getAttachment();*/
			List<AccountInfo> accountInfoList = new ArrayList<AccountInfo>();
			String accountTypeMain = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_TYPE_MAIN);
			String accountStatusNormal = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_STATUS_NORMAL);
			String currencyTypeRMB = DictionaryUtils.getStringValue(DictionaryKey.CURRENCY_TYPE_RMB);
			String functionPay = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_BALANCE_PAY);
			String functionSMS = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_SMS_REMIND);
			String open = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_YES);
			String clos = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_NO);
			AttachmentResponse<Long> accountResponse = accountServiceSupport.createCardNumber(accountTypeMain);
			if (accountResponse.getReturnCode() != 0){
				return ResponseUtils.buildFailureResponse(PortalConstants.ACCOUNT_ID_GEN_FAIL);
			}
			Long accountId = accountResponse.getAttachment();
			AccountInfo account = new AccountInfo();
			account.setUserId(userId);
			account.setUserType(userTypeEn);
			account.setAccountId(accountId);
			account.setAccountType(accountTypeMain);
			account.setAccountStatus(accountStatusNormal);
			account.setCurrencyType(currencyTypeRMB);
			account.setAvailableBalance(new BigDecimal(0));
			account.setFrozenAmount(new BigDecimal(0));
			account.setNomentionAmount(new BigDecimal(0));
			account.setMac(""); 
			account.setCreateDatetime(new Date());
			account.setUpdateDatetime(new Date());
			accountInfoList.add(account);
			//创建ID
			AttachmentResponse<Long[]> idRes = idGeneratorService.generate(UserFunctionInfo.class.getName(), 2);
			if (idRes.getReturnCode() != 0) {
				return ResponseUtils.buildFailureResponse(PortalConstants.ID_GEN_FAIL);
			}		
			List<UserFunctionInfo> functionInfoList = new ArrayList<UserFunctionInfo>();
			//设置余额支付
			UserFunctionInfo userFunc1 = new UserFunctionInfo();
			userFunc1.setId(idRes.getAttachment()[0]);
			userFunc1.setUserId(userId);
			userFunc1.setFunctionId(functionPay);
			userFunc1.setOpenStatus(open);
			userFunc1.setCreateDatetime(new Date());
			userFunc1.setUpdateDatetime(new Date());
			//设置短信提醒
			UserFunctionInfo userFunc2 = new UserFunctionInfo();
			userFunc2.setId(idRes.getAttachment()[1]);
			userFunc2.setUserId(userId);
			userFunc2.setFunctionId(functionSMS);
			userFunc2.setOpenStatus(clos);
			userFunc2.setCreateDatetime(new Date());
			userFunc2.setUpdateDatetime(new Date());
			functionInfoList.add(userFunc1);
			functionInfoList.add(userFunc2);
			
			SecurityClassInfo sci = new SecurityClassInfo();
			sci.setUserId(userId);
			sci.setBalancepaySet(open);
			sci.setPhoneSet(clos);
			sci.setQuestionSet(open);
			sci.setVerifySet(clos);
			sci.setCertSet(clos);
			sci.setAuthSet(clos);
			sci.setReservationSet(clos);
			sci.setUpdateDatetime(new Date());
			portalServiceSupport.enterpriseRegistration(user, userLogin, userQuestion, accountInfoList, functionInfoList ,sci);
			return ResponseUtils.buildSuccessResponse(userId);
		} 
		catch (Exception e) {
			AttachmentResponse<Long> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1022 用户登陆接口
	 * 1.入参检查
	 * 2.按手机号或邮箱查询用户登录表
	 * 3.检查用户状态，销户的用户不能登录
	 * 4.检查密码
	 * 5.判断是个人用户还是企业用户
	 * 6.更新用户登录信息
	 * 7.异常处理
	 * @author Ganlin
	 * @date 2014年7月23日 下午5:44:10 
	 * @since 0.1
	 */
	@Override
	public AttachmentResponse<LoginResponse> login(String userName, String passWord,
			String ip, String mac, String source) {
		logger.debug("deng lu password=="+passWord);
		try {
			//入参检查
			if (userName == null || passWord == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[] {"userName or passWord"});
			}
			//按手机号或者邮箱查询UserLoginInfo
			UserLoginInfo query = new UserLoginInfo();
			if (isNumeric(userName)) {
				query.setUserMobile(userName);
			} 
			else {
				query.setUserEmail(userName);
			}
			UserLoginInfo userLogin = userLoginDao.selectOne(query);
			if (userLogin == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			}
			//检查用户状态是否被注销，是则不能登录
			String cancel = DictionaryUtils.getStringValue(DictionaryKey.USER_STATUS_CANCEL);
			if(cancel.equals(userLogin.getUserStatus())){
				return ResponseUtils.buildFailureResponse(PortalConstants.USER_CANCEL);
			}
			//密码加密
			logger.debug("3333333333333>"+userLogin.getCreateDatetime());
			passWord = securityUtils.encryptMD5(passWord,userLogin.getCreateDatetime());
			logger.debug("===========>"+passWord);
			//检查密码，密码错误次数写入数据库
			LoginResponse loginRes = new LoginResponse();
			if (!userLogin.getLoginPasswd().equals(passWord)) {
				UserLoginInfo ul = new UserLoginInfo();
				ul.setUserId(userLogin.getUserId());
				Integer errorTimes = userLogin.getErrorLoginTimes();
				ul.setErrorLoginTimes(errorTimes == null? 1: errorTimes + 1);
				ul.setUpdateDatetime(new Date());
				portalServiceSupport.userLogin(ul, null);
				loginRes.setErrorTimes(ul.getErrorLoginTimes());
				return ResponseUtils.buildFailureResponse(PortalConstants.WRONG_PASS_WORD,loginRes);
			}
			//判断个人用户或企业用业，返回
			String userTypePerson = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL);
			String userTypeEn = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_ENTERPRISE);
			if (userLogin.getUserType().equals(userTypePerson)) {
				AttachmentResponse<PersonalUser> userRes = portalServiceSupport.getPersonalUser(userLogin.getUserId());
				if (userRes.getReturnCode() != 0) {
					return ResponseUtils.buildFailureResponse(userRes.getReturnCode());
				}
				loginRes.setUserType(userLogin.getUserType());
				loginRes.setPersonalUser(userRes.getAttachment());
			} 
			else if (userLogin.getUserType().equals(userTypeEn)) {
				AttachmentResponse<EnterpriseUser> userRes = portalServiceSupport.getEnterpriseUser(userLogin.getUserId());
				if (userRes.getReturnCode() != 0) {
					return ResponseUtils.buildFailureResponse(userRes.getReturnCode());
				}
				loginRes.setUserType(userLogin.getUserType());
				loginRes.setEnterpriseUser(userRes.getAttachment());
			} 
			else {
				return ResponseUtils.buildExceptionResponse();
			}
			AccountInfoQuery accountInfoQuery = new AccountInfoQuery();
			accountInfoQuery.setUserId(userLogin.getUserId());
			accountInfoQuery.setAccountType(DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_TYPE_MAIN));
			AccountInfo account = accountInfoDao.selectOne(accountInfoQuery);
			loginRes.setAccountInfo(account);
			//更新登录信息
			UserLoginInfo update = new UserLoginInfo();
			Date date = new Date();
			update.setUserId(userLogin.getUserId());
			update.setErrorLoginTimes(0);	//错误登录次数清零
			update.setLastLogininTime(date);
			update.setLastLoginIp(ip);
			update.setUpdateDatetime(date);
			
			//往登录日志表插入记录
			UserLoginLog log = new UserLoginLog();
			AttachmentResponse<Long> logId = idGeneratorService.generate(UserLoginLog.class.getName());
			log.setLoginName(userName);
			log.setId(logId.getAttachment());
			log.setUserId(userLogin.getUserId());
			log.setMac(mac);
			log.setIp(ip);
			log.setSource(source);
			log.setCreateTime(date);
			log.setLoginTime(date);
			log.setUpdateTime(date);
		    
			portalServiceSupport.userLogin(update, log);
			
			return ResponseUtils.buildSuccessResponse(loginRes);

		} catch (Exception e) {
			AttachmentResponse<LoginResponse> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}	
	}

	/**
	 * 1023	用户登出接口
	 * 1.入参检查
	 * 2.更新登录表
	 * 3.异常处理
	 * @author Ganlin
	 * @date 2014年7月24日 上午10:19:48 
	 * @since 0.1
	 */
	@Override
	public AttachmentResponse<String> logout(Long userId) {
		try {
			//入参检查
			if (userId == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[] {"userId"});
			}
			AttachmentResponse<UserLoginInfo> userRes = portalServiceSupport.getUserLoginInfo(userId);
			if (userRes.getReturnCode() != 0) {
				return ResponseUtils.buildFailureResponse(userRes.getReturnCode());
			}
			//更新登录表
			UserLoginInfo update = new UserLoginInfo();
			update.setUserId(userRes.getAttachment().getUserId());
			update.setLastLoginoutTime(new Date());
			update.setUpdateDatetime(new Date());
			userLoginDao.update(update);
			return ResponseUtils.buildSuccessResponse();
		} 
		catch (Exception e) {
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}	
	}

	/**
	 * 1024用户（个人和企业）银行打款认证接口
	 * 1.入参检查
	 * 2.查询用户类型
	 * 3.查询打款成功的记录是否存在
	 * 4.取最新一条打款成功的记录
	 * 5.检查认证次数
	 * 6.核对打款金额，若错误更新错误次数
	 * 7.认证通过，修改用户认证状态
	 * 8.异常处理
	 * @author Ganlin
	 * @date 2014年7月24日 上午10:52:55
	 * @param userId 用户id
	 * @param bankNo 银行编号
	 * @param bankCardNo 银行卡号
	 * @param money 打款金额
	 * @param source 用户来源
	 * @return AttachmentResponse<String>    返回类型
	 * @since 0.1
	 */
	public AttachmentResponse<String> bankAu(Long userId,String bankId,String bankCardNo,BigDecimal money,String source){
		try {
			//入参检查
			if (userId == null || bankId == null || bankCardNo == null || money == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, 
						new String[] {"userId, bankId, bankCardNo, money"});
			}
			return portalServiceSupport.bankAuSupport(userId, bankId, bankCardNo, money);
		} catch (Exception e) {
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}


	/**
	 * 1025 个人用户基本信息修改接口
	 * 1.入参检查
	 * 2.用户信息、登录信息检查
	 * 3.更新用户信息表里手机或邮箱，检查是否被其他用户设为登录名,并更新登录表
	 * 4.1状态参数不为空，涉及用户冻结、解冻、注销，进行相应处理
	 * 4.2状态参数为空，只进行用户信息修改
	 * 5.异常处理
	 * @author Ganlin
	 * @date 2014年7月24日 下午2:27:28
	 * @param user
	 * @return AttachmentResponse<String>    返回类型
	 * @since 0.1
	 */
	 /* -------------------------------------------------
	 * 修改记录
	 * 1.设置user实体的updateTime属性，再更新记录
	 * 修改者:zhanwx
	 * 修改时间:2014-07-29 上午9:46
	 * --------------------------------------------------
	 * 2.修改绑定手机、绑定邮箱、用户冻结、解冻、销户等功能
	 * 修改者:zhanwx
	 * 修改时间:2014-08
	 * --------------------------------------------------
	 * 3.代码优化
	 * 修改者:zhanwx
	 * 修改时间:2014-09-12 下午14:53
	 */
	@Override
	public AttachmentResponse updatePersonal(PersonalUser user, String userStatus) {
		try {
			//1、入参检查
			if (user == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[] {"user"});	
			}
			Long userId = user.getUserId();
			if(userId == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"user对象里userId"});
			}
			//2、用户信息、登录信息检查
			AttachmentResponse<PersonalUser> userRes = portalServiceSupport.getPersonalUser(userId);
			if (userRes.getReturnCode() != 0) {
				return ResponseUtils.buildFailureResponse(userRes.getReturnCode());
			}
			UserLoginInfo ulp = new UserLoginInfo();
			ulp.setUserId(userId);
			ulp = userLoginDao.selectOne(ulp);
			if(ulp == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.RECORD_NOT_EXIST);
			}
			//3、更新用户信息表里手机或邮箱，检查是否被其他用户设为登录名,并更新登录表
			Date date = new Date();
			AttachmentResponse<UpdateUserResponse> checkRes = portalServiceSupport.checkIfUpdateLoginInfo(userId,
					user.getPhoneNumber(), ulp.getUserMobile(), user.getEmailAddress(), ulp.getUserEmail(), date);
			UserFunctionInfo uf = checkRes.getAttachment().getUf();
			UserLoginInfo ul = checkRes.getAttachment().getUl();
			SecurityClassInfo sc = checkRes.getAttachment().getSc();
			//4.1、状态参数不为空，涉及用户冻结、解冻、注销，作如下处理
			user.setUpdateDatetime(date);
			String userStatusNow = ulp.getUserStatus();
			if(null != userStatus){
				List<AccountInfo> acList;
				AttachmentResponse<List<AccountInfo>> response = portalServiceSupport.changeStatusSupport(userId, userStatusNow, userStatus);
				if(response.getReturnCode()!=0){
					return response;
				}
				else{
					acList = response.getAttachment();
				}		
				//登录表设置用户状态
				ul.setUserStatus(userStatus);
				portalServiceSupport.updatePersonal(user, ul,acList,uf,sc);
				return ResponseUtils.buildSuccessResponse("update success!");
			}
			//4.2、状态参数为空，只做基本信息修改
			portalServiceSupport.updatePersonal(user, ul,null,uf,sc);
			return ResponseUtils.buildSuccessResponse("update success!");
		} 
		catch (Exception e) {
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}
	
	/**
	 * 1026	个人用户实名认证进度查询接口
	 * 1.入参检查
	 * 2.返回个人用户实名认证状态
	 * 3.异常处理
	 * @author Ganlin
	 * @date 2014年7月24日 下午2:59:00
	 * @param userId 用户id
	 * @return AttachmentResponse<String>    返回类型
	 * @since 0.1
	 */
	@Override
	public AttachmentResponse<PersonalAuProcessResponse> personalAuProcess(Long userId) {
		try {
			//入参检查
			if (userId == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[] {"userId"});
			}
			AttachmentResponse<PersonalUser> oldUserRes = portalServiceSupport.getPersonalUser(userId);
			if (oldUserRes.getReturnCode() != 0) {
				return ResponseUtils.buildFailureResponse(oldUserRes.getReturnCode());
			}
			PersonalAuProcessResponse response = new PersonalAuProcessResponse();
			response.setAuthStatus(oldUserRes.getAttachment().getAuthStatus());
			response.setAuthStep(oldUserRes.getAttachment().getAuthStep());
			response.setAuthDate(oldUserRes.getAttachment().getAuthDate());
			response.setAuthDescribe(oldUserRes.getAttachment().getAuthDescribe());
			if(oldUserRes.getAttachment().getAuthStatus()!=null){
				String statusName = DictionaryUtils.getDisplayName(DictionaryKey.AUTH_STATUS,
						oldUserRes.getAttachment().getAuthStatus());
				response.setAuthStatusName(statusName);
			}
			//获取打款认证已输入金额次数
			AttachmentResponse<UserBankAuInfo> timeResponse = portalServiceSupport.bankAuSelectSupport(userId);
			if(!timeResponse.isSuccess()){
				return ResponseUtils.buildSuccessResponse(response);
			}
			response.setPaymentDescribe(timeResponse.getAttachment().getNote());
			Integer times = timeResponse.getAttachment().getAuthNum();
			if(times!=null){
				response.setErrorTimes(times.toString());
			}
			response.setBankTime(timeResponse.getAttachment().getBankTime());
			return ResponseUtils.buildSuccessResponse(response);
		} catch (Exception e) {
			AttachmentResponse<PersonalAuProcessResponse> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}
	
	/**
	 * 1027	个人用户实名认证申请时间查询接口
	 * 1.入参检查
	 * 2.返回个人用户实名认证申请时间
	 * 3.异常处理
	 * @author Ganlin
	 * @date 2014年7月24日 下午3:26:16
	 * @param userId 用户id
	 * @return AttachmentResponse<String>    返回类型
	 * @since 0.1
	 * @see
	 */
	@Override
	public AttachmentResponse<Date> getPersonalAuDate(Long userId) {
		try {
			//入参检查
			if (userId == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[] {"userId"});
			}
			AttachmentResponse<PersonalUser> userRes = portalServiceSupport.getPersonalUser(userId);
			if (userRes.getReturnCode() != 0) {
				return ResponseUtils.buildFailureResponse(userRes.getReturnCode());
			}
			return ResponseUtils.buildSuccessResponse(userRes.getAttachment().getAuthDate());
		} catch (Exception e) {
			AttachmentResponse<Date> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1028	个人用户撤销实名认证（未审核）接口
	 * 1.入参检查
	 * 2.检查认证状态，认证未通过均可撤销
	 * 3.更新个人用户认证状态
	 * 4.异常处理
	 * @author Ganlin
	 * @date 2014年7月24日 下午3:58:51
	 * @param userId
	 * @return AttachmentResponse<String>    返回类型
	 * @since 0.1
	 * @see
	 */
	//-------------修改记录--------------------
	//修改者:zhanwx    修改时间: 2014-08-20
	//修改内容: 认证未通过均可撤销
	@Override
	public AttachmentResponse<String> personalAuCancel(Long userId) {
		try {
			//入参检查
			if (userId == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[] {"userId"});
			}
			AttachmentResponse<PersonalUser> userRes = portalServiceSupport.getPersonalUser(userId);
			String auth = DictionaryUtils.getStringValue(DictionaryKey.AUTH_STATUS_AUTH);
			String nonAuth = DictionaryUtils.getStringValue(DictionaryKey.AUTH_STATUS_NONE);
			if (userRes.getReturnCode() != 0) {
				return ResponseUtils.buildFailureResponse(userRes.getReturnCode());
			}
			//检查认证状态，若不是已认证，均可撤销
			if (userRes.getAttachment().getAuthStatus().equals(auth)) {
				return ResponseUtils.buildFailureResponse(PortalConstants.AUTH_CANCEL_FAIL);
			}
			//更新认证状态为未认证
			PersonalUser pu = new PersonalUser();
			pu.setUserId(userId);
			pu.setAuthStep(null);
			pu.setAuthDate(null);
			pu.setAuthStatus(DictionaryUtils.getStringValue(DictionaryKey.AUTH_STATUS_NONE));
			pu.setUpdateDatetime(new Date());
			personalUserDao.updateAuth(pu);
			return ResponseUtils.buildSuccessResponse();
		} catch (Exception e) {
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}
	
	/**
	 * 1029	企业实名认证（法人、代理人）接口
	 * @author zhanwx
	 * @date 2014年7月24日 下午4:29:26
	 * @param user
	 * @return AttachmentResponse<String>
	 * @since 0.1
	 * @see
	 */
	@Override
	public AttachmentResponse<String> enterpriselAu(EnterpriseUser user , UserBankAuInfo bankAuInfo) {
		try {
			if(null == user){ 
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"user"});
			}
			//查询用户是否存在
			EnterpriseUserQuery euq = new EnterpriseUserQuery();
			euq.setUserId(user.getUserId());
			EnterpriseUser eu = enterpriseUserDao.selectOne(euq);
			if (null == eu){
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			}
			//组织认证的用户信息
			user.setAuthDate(new Date());
			user.setUpdateDatetime(new Date());
			//检查银行打款认证信息对象是否为空
			if(bankAuInfo != null){
				UserBankAuInfo ub = new UserBankAuInfo();
				ub.setUserId(user.getUserId());
				ub.setBankNo(bankAuInfo.getBankNo());
				ub.setAuthNum(2);
				ub = userBankAuInfoDao.selectOne(ub);
				//检查该银行卡确认金额次数是否超过限制
				if(null == ub){
					AttachmentResponse<Long> userBankAuInforespinse = idGeneratorService.generate(UserBankAuInfo.class.getName());
					bankAuInfo.setId(userBankAuInforespinse.getAttachment());
					bankAuInfo.setUserId(user.getUserId());
					bankAuInfo.setUserAuDateTime(new Date());
					bankAuInfo.setUpdateDatetime(new Date());
				}
				else{
					return ResponseUtils.buildFailureResponse(PortalConstants.BANKCARD_IS_LIMITED);
				}
			}
			portalServiceSupport.enterpriseAu(user, bankAuInfo);		
			return ResponseUtils.buildSuccessResponse();
		} 
		catch (Exception e) {
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1030	企业客户基本信息查询接口
	 * 1.入参检查
	 * 2.查询企业用户信息表
	 * 3.若用户不存在报错
	 * 4.查询用户信息
	 * 5.异常处理
	 * @author zhanwx
	 * @date 2014年7月24日 下午4:30:03
	 * @param userId
	 * @return AttachmentResponse<EnterpriseUser>
	 * @since 0.1
	 * @see
	 * ------------------------------------------------------------------------------------------------------------
	 * 修改记录
	 * 增加对查询为空的处理 zxw20140727
	 */
	@Override
	public AttachmentResponse<GetEnterpriseUserBasicInfoResponse> getEnterpriseUserBasicInfo(Long userId) {
		//入参检查
		try{
			if(userId == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"userId"});
			}
			else{
				GetEnterpriseUserBasicInfoResponse response = new GetEnterpriseUserBasicInfoResponse();
				EnterpriseUserQuery enterpriseUser = new EnterpriseUserQuery();
				enterpriseUser.setUserId(userId);
				EnterpriseUser enterpriseuser = enterpriseUserDao.selectOne(enterpriseUser);
				if(enterpriseuser==null){
					return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
				}
				//返回是否商户
				String statusSign = DictionaryUtils.getStringValue(DictionaryKey.BUSINESS_STATUS_SIGN);
				if (statusSign.equals(enterpriseuser.getIsMerchant())) {
					response.setMerchant(true);
				} else {
					response.setMerchant(false);
				}
				//用户ID
				response.setUserId(enterpriseuser.getUserId());
				//企业名称|银行开户名
				response.setBusinessName(enterpriseuser.getBusinessName());
				//营业执照注册号
				response.setBusinessLicence(enterpriseuser.getBusinessLicence());
				//营业执照所在地
				response.setBusinessLocation(enterpriseuser.getBusinessLocation());
				//营业期限
				response.setBusinessExpdate(enterpriseuser.getBusinessExpdate());
				//常用地址
				response.setBusinessAddress(enterpriseuser.getBusinessAddress());
				//联系电话
				response.setManagerTel(enterpriseuser.getManagerTel());
				//营业执照副本扫描件
				response.setBusinessPic1(enterpriseuser.getBusinessPic1());
				//加盖公章的副本
				response.setBusinessPic2(enterpriseuser.getBusinessPic2());
				//组织机构代码
				response.setOrganizationCode(enterpriseuser.getOrganizationCode());
				//营业范围
				response.setBusinessSphere(enterpriseuser.getBusinessSphere());
				//注册资金
				response.setBusinessRegamount(enterpriseuser.getBusinessRegamount());
				//传真
				response.setFax(enterpriseuser.getFax());
				//开户银行
				response.setBusinessBank(enterpriseuser.getBusinessBank());
				//开户银行所在城市
				response.setBusinessBankcity(enterpriseuser.getBusinessBankcity());
				//开户银行支行名称
				response.setBusinessBankbranch(enterpriseuser.getBusinessBankbranch());
				//公司对公账户
				response.setBusinessBankno(enterpriseuser.getBusinessBankno());
				//法定代表人归属地
				response.setLegalPersoncity(enterpriseuser.getLegalPersoncity());
				//法定代表人真实姓名
				response.setLegalPersonname(enterpriseuser.getLegalPersonname());
				//法定代表人手机号码
				response.setLegalPersonPhone(enterpriseuser.getLegalPersonphone());
				//身份证号码
				response.setLegalPersonidno(enterpriseuser.getLegalPersonidno());
				//身份证类型
				response.setIdType(enterpriseuser.getIdType());
				String idTypeName = DictionaryUtils.getDisplayName(DictionaryKey.ID_TYPE, enterpriseuser.getIdType());
				response.setIdTypeName(idTypeName);
				//身份证图片正面
				response.setIdPic1(enterpriseuser.getIdPic1());
				//身份证图片反面
				response.setIdPic2(enterpriseuser.getIdPic2());
				//头像
				response.setHeadPic(enterpriseuser.getHeadPic());
				//手机号码
				response.setManagerMobile(enterpriseuser.getManagerMobile());
				response.setManagerTel(enterpriseuser.getManagerTel());
				response.setEmail(enterpriseuser.getEmail());
				//代理人真实姓名
				response.setDlrName(enterpriseuser.getDlrName());
				//代理人身份证号码
				response.setDlrSfzhm(enterpriseuser.getDlrSfzhm());
				//代理人身份证类型
				response.setDlrSfzlx(enterpriseuser.getDlrSfzlx());
				String dlrStatusName = DictionaryUtils.getDisplayName(DictionaryKey.ID_TYPE, enterpriseuser.getDlrSfzlx());
				response.setDlrSfzlxName(dlrStatusName);
				//代理人身份证正面
				response.setDlrSfzzm(enterpriseuser.getDlrSfzzm());
				//代理人身份证反面
				response.setDlrSfzfm(enterpriseuser.getDlrSfzfm());
				//是否代理人认证
				response.setDlrAuth(enterpriseuser.getDlrAuth());
				//企业委托书扫描件
				response.setQywtssmj(enterpriseuser.getQywtssmj());
				//代理人手机
				response.setDlrSjhm(enterpriseuser.getDlrSjhm());
				//实名认证时间
				response.setUserAuDateTime(enterpriseuser.getAuthDate());
				response.setPreString(enterpriseuser.getPreString());
				//审核描述
				response.setAuthDescribe(enterpriseuser.getAuthDescribe());
				//是否通过实名认证    auth_status实名认证
				String auth_status =  DictionaryUtils.getStringValue(DictionaryKey.AUTH_STATUS_AUTH);
				if(enterpriseuser.getAuthStatus().equals(auth_status)){
					response.setAuthStatus(true);
				}else{
					response.setAuthStatus(false);
				}
				String authStatus = enterpriseuser.getAuthStatus();
				if(authStatus!=null){
					response.setUserAuthStatus(authStatus);
					response.setUserAuthStatusName(DictionaryUtils.getDisplayName(DictionaryKey.AUTH_STATUS, authStatus));
				}
				AccountInfoQuery accountQuery = new AccountInfoQuery();
				accountQuery.setUserId(userId);
				accountQuery.setAccountType(DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_TYPE_MAIN));
				AccountInfo accountInfo = accountInfoDao.selectOne(accountQuery);
				if(accountInfo==null){
					return ResponseUtils.buildFailureResponse(GlobalConstants.ACCOUNT_NOT_EXIST);
				}
				//账户注册时间
				response.setRegTime(accountInfo.getCreateDatetime());
				//账户可用余额
				response.setAvailableBalance(accountInfo.getAvailableBalance());
				//账户不可用余额
				response.setFrozenAmount(accountInfo.getFrozenAmount());
				//不可提现金额
				response.setNomentionAmount(accountInfo.getNomentionAmount());
				response.setAccountId(accountInfo.getAccountId());
				//银行卡号
				UserBankAuInfo userBankAuInfo = manageServiceSupport.getLatestUserBankAuInfo(userId);
				if (userBankAuInfo != null) {
					response.setBankCity(userBankAuInfo.getBankCity());
					response.setBankCnaps(userBankAuInfo.getBankCnaps());
					response.setBankId(userBankAuInfo.getBankId());
					response.setBankInternal(userBankAuInfo.getBankInternal());
					response.setBankName(userBankAuInfo.getBankName());
					response.setBankNo(userBankAuInfo.getBankNo());
					response.setBranchId(userBankAuInfo.getBranchId());
					response.setBranchName(userBankAuInfo.getBranchName());
					response.setBankProvince(userBankAuInfo.getBankProvince());
					//打款失败原因
					response.setAuthenticationReason(userBankAuInfo.getNote());
				}
				//用户状态
				UserLoginInfo userLoginInfo = new UserLoginInfo();
				userLoginInfo.setUserId(userId);
				userLoginInfo = userLoginDao.selectOne(userLoginInfo);
				if(userLoginInfo!=null){
					response.setUserStatus(userLoginInfo.getUserStatus());
					String loginStatusName = DictionaryUtils.getDisplayName(DictionaryKey.USER_STATUS, userLoginInfo.getUserStatus());
					response.setUserStatusName(loginStatusName);
					response.setLoginMobile(userLoginInfo.getUserMobile());
					response.setLoginEmail(userLoginInfo.getUserEmail());
					response.setLastLoginTime(userLoginInfo.getLastLogininTime());
					response.setFreezeReason(userLoginInfo.getReserve1());
				}
				//查询用户功能表，返回其余额支付状态
				UserFunctionInfo query = new UserFunctionInfo();
				query.setUserId(userId);
				query.setFunctionId(DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_BALANCE_PAY));
				UserFunctionInfo result = userFunctionDao.selectOne(query);
				//是否开通余额支付
				if (result == null) {
					response.setBalanceStatus(false);
				} else {
					String functionOpenYes = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_YES);
					if (functionOpenYes.equals(result.getOpenStatus())) {
						response.setBalanceStatus(true);
					} else {
						response.setBalanceStatus(false);
					}
				}
				
				//查询用户功能表，返回是否开通短信服务
				UserFunctionInfo messageQuery = new UserFunctionInfo();
				messageQuery.setUserId(userId);
				messageQuery.setFunctionId(DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_SMS_REMIND));
				UserFunctionInfo messageResult = userFunctionDao.selectOne(messageQuery);
				//是否开通短信服务
				if (messageResult == null) {
					response.setBalanceStatus(false);
				} else {
					String functionOpenYes = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_YES);
					if (functionOpenYes.equals(messageResult.getOpenStatus())) {
						response.setMessageStatus(true);
					} else {
						response.setMessageStatus(false);
					}
				}
				return ResponseUtils.buildSuccessResponse(response);
			}
		}
		catch (Exception e) {
			AttachmentResponse<GetEnterpriseUserBasicInfoResponse> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1031	企业客户基本信息修改接口
	 * 1.入参检查
	 * 2.检查用户信息、登录信息是否存在
	 * 3.更新用户信息表里手机或邮箱，检查是否被其他用户设为登录名,并更新登录表
	 * 4.1状态参数不为空，涉及用户冻结、解冻、注销，进行相应处理
	 * 4.2状态参数为空，只做基本信息修改
	 * 5.异常处理
	 * @author zhanwx
	 * @date 2014年7月24日 下午4:30:37
	 * @param user
	 * @return AttachmentResponse<String>
	 * @since 0.1
	 * @see
	 */
	/* -------------------------------------------------
	 * 修改记录
	 * 1.设置user实体的updateTime属性，再更新记录
	 * 修改者:zhanwx
	 * 修改时间:2014-07-29 上午9:46
	 * --------------------------------------------------
	 * 2.修改绑定手机、绑定邮箱、用户冻结、解冻、销户等功能
	 * 修改者:zhanwx
	 * 修改时间:2014-08
	 * --------------------------------------------------
	 */
	@Override
	public AttachmentResponse updateEnterprise(EnterpriseUser user,String userStatus) {
		try{
			//1、入参检查
			if (user == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[] {"user"});
			}
			Long userId = user.getUserId();
			if(userId == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"user对象里userId"});
			}
			//2、检查用户信息、登录信息是否存在
			AttachmentResponse<EnterpriseUser> userRes = portalServiceSupport.getEnterpriseUser(user.getUserId());
			if (userRes.getReturnCode() != 0) {
				return ResponseUtils.buildFailureResponse(userRes.getReturnCode());
			}
			UserLoginInfo ule = new UserLoginInfo();
			ule.setUserId(userId);
			ule = userLoginDao.selectOne(ule);
			if(ule == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.RECORD_NOT_EXIST);
			}
			//3、更新用户信息表里手机或邮箱，检查是否被其他用户设为登录名,并更新登录表
			Date date = new Date();
			AttachmentResponse<UpdateUserResponse> checkRes = portalServiceSupport.checkIfUpdateLoginInfo(userId,
					user.getManagerMobile(), ule.getUserMobile(), user.getEmail(), ule.getUserEmail(), date);
			UserFunctionInfo uf = checkRes.getAttachment().getUf();
			UserLoginInfo ul = checkRes.getAttachment().getUl();
			SecurityClassInfo sc = checkRes.getAttachment().getSc();
			//4.1状态参数不为空，涉及用户冻结、解冻、注销，作如下处理
			user.setUpdateDatetime(date);
			String userStatusNow = ule.getUserStatus();
			if(null != userStatus){
				List<AccountInfo> acList;
				AttachmentResponse<List<AccountInfo>> response = portalServiceSupport.changeStatusSupport(userId, userStatusNow, userStatus);
				if(response.getReturnCode()!=0){
					return response;
				}
				else{
					acList = response.getAttachment();
				}	
				//登录表设置用户状态
				ul.setUserStatus(userStatus);
				portalServiceSupport.updateEnterprise(user, ul,acList,uf,sc);
				return ResponseUtils.buildSuccessResponse("update success!");
			}
			//4.2状态参数为空，只做基本信息修改
			portalServiceSupport.updateEnterprise(user, ul,null,uf,sc);
			return ResponseUtils.buildSuccessResponse("update success!");
		} 
		catch (Exception e) {
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1032	企业客户实名认证进度查询接口
	 * 1.入参检查
	 * 2.查询企业用户信息表判断企业用户是否存在
	 * 3.若用户不存在返回提示
	 * 4.查询并返回实名认证进度
	 * 5.异常处理
	 * @author zhanwx
	 * @date 2014年7月24日 下午4:30:54
	 * @param userId
	 * @return AttachmentResponse<String>
	 * @since 0.1
	 * @see
	 */
	@Override
	public AttachmentResponse<EnterpriseAuProcessQueryResponse> enterpriseAuProcessQuery(Long userId) {
		try {
			//入参检查
			if (userId == null ) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"userId"});
			}
			//查询企业用户信息表判断企业用户是否存在
			EnterpriseUserQuery euq = new EnterpriseUserQuery();
			euq.setUserId(userId);
			EnterpriseUser eu = enterpriseUserDao.selectOne(euq);
			//若企业用户不存在,返回提示
			if (eu == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			}
			else{
				EnterpriseAuProcessQueryResponse response = new EnterpriseAuProcessQueryResponse();
				if(eu.getAuthStatus()!=null){
					String statusName = DictionaryUtils.getDisplayName(DictionaryKey.AUTH_STATUS, eu.getAuthStatus());
					response.setAuthStatusName(statusName);
				}
				response.setAuthStatus(eu.getAuthStatus());
				response.setAuthStep(eu.getAuthStep());
				response.setAuthDate(eu.getAuthDate());
				response.setDlrAuth(eu.getDlrAuth());
				response.setAuthDescribe(eu.getAuthDescribe());
				//获取打款认证已输入金额次数
				AttachmentResponse<UserBankAuInfo> timeResponse = portalServiceSupport.bankAuSelectSupport(userId);
				if(!timeResponse.isSuccess()){
					return ResponseUtils.buildSuccessResponse(response);
				}
				response.setPaymentDescribe(timeResponse.getAttachment().getNote());
				if(timeResponse.getAttachment().getAuthNum()!=null){
					Integer times = timeResponse.getAttachment().getAuthNum();
					response.setErrorTimes(times.toString());
				}
				response.setBankTime(timeResponse.getAttachment().getBankTime());
				return ResponseUtils.buildSuccessResponse(response);
			}
		} 
		catch (Exception e) {
			AttachmentResponse<EnterpriseAuProcessQueryResponse> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1033	企业客户撤销实名认证接口
	 * 1.入参检查
	 * 2.查询企业用户信息表判断企业用户是否存在
	 * 3.查询该用户实名认证状态
	 * 4.若认证审核状态是"已认证",返回"撤销认证失败"提示
	 * 5.撤销实名认证，更新状态为未认证
	 * 6.异常处理
	 * @author zhanwx
	 * @date 2014年7月24日 下午4:31:18
	 * @param userId
	 * @return AttachmentResponse<String>
	 * @since 0.1
	 * @see
	 */
	@Override
	public AttachmentResponse<String> enterpriselAuCancel(Long userId) {
		try {
			String auth = DictionaryUtils.getStringValue(DictionaryKey.AUTH_STATUS_AUTH);
			String nonAuth = DictionaryUtils.getStringValue(DictionaryKey.AUTH_STATUS_NONE);
			//入参检查
			if (userId == null ) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"userId"});
			}
			//查询企业用户信息表判断企业用户是否存在
			EnterpriseUserQuery euq = new EnterpriseUserQuery();
			euq.setUserId(userId);
			EnterpriseUser eu = enterpriseUserDao.selectOne(euq);
			//若不存在返回提示
			if (eu == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			}
			//若存在，但认证审核状态是"已认证",返回"撤销认证失败"提示
			if(eu.getAuthStatus().equals(auth)){
				return ResponseUtils.buildFailureResponse(PortalConstants.AUTH_CANCEL_FAIL);
			} 
			//撤销实名认证
			EnterpriseUser euser = new EnterpriseUser();
			euser.setUserId(userId);
			euser.setAuthStep(null);
			euser.setAuthDate(null);
			euser.setAuthStatus(DictionaryUtils.getStringValue(DictionaryKey.AUTH_STATUS_NONE));
			euser.setUpdateDatetime(new Date());
			enterpriseUserDao.updateAuth(euser);
			return ResponseUtils.buildSuccessResponse();
		} 
		catch (Exception e) {
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1034	实名认证人工审核接口
	 * 1.入参检查
	 * 2.查询个人用户信息表判断用户是否存在
	 * 3.若个人用户存在，判断认证状态是否为待审核，是则更新
	 * 4.若个人用户不存在，查询企业用户信息表判断用户是否存在
	 * 5.若企业用户存在，判断认证状态是否为待审核，是则更新
	 * 6.若企业用户不存在，报错
	 * 7.异常处理
	 * @author zhanwx
	 * @date 2014年7月24日 下午4:31:35
	 * @param userId
	 * @param source
	 * @return AttachmentResponse<String>
	 * @since 0.1
	 * @see
	 * ------------------------------------------------------------------------------------------------------
	 * 修改记录
	 * 审核是更新个人用户信息 personalUserDao.update(pu);  zhaoxw20140727
	 */
	@Override
	public AttachmentResponse<String> enterpriselAuApply(Long userId,String source) {
		try {
			//入参检查
			if (userId == null || source == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"userId或source"});
			}
			String authStatus = DictionaryUtils.getStringValue(DictionaryKey.AUTH_STATUS_AUDITED);
			String waitAudit = DictionaryUtils.getStringValue(DictionaryKey.AUTH_STATUS_WAIT_AUDIT);
			//查询个人用户信息表判断个人用户是否存在，存在即更新审核状态
			PersonalUserQuery puq = new PersonalUserQuery();
			puq.setUserId(userId);
			PersonalUser pu = personalUserDao.selectOne(puq);
			if (pu != null) {
				//用户当前状态为待审核，更新认证状态为已审核
				if(pu.getAuthStatus().equals(waitAudit)){
					pu.setAuthStatus(authStatus);
					pu.setUpdateDatetime(new Date());
					personalUserDao.update(pu);
					return ResponseUtils.buildSuccessResponse("成功更新个人用户认证状态为已审核");
				}
				else{
					return ResponseUtils.buildFailureResponse(PortalConstants.NOT_WAITAUDIT);
				}
			}
			//否则查询企业用户信息表判断企业用户是否存在,存在即更新审核状态
			EnterpriseUserQuery euq = new EnterpriseUserQuery();
			euq.setUserId(userId);
			EnterpriseUser eu = enterpriseUserDao.selectOne(euq);
			if(eu != null){
				//用户当前状态为待审核，更新认证状态为已审核
				if(eu.getAuthStatus().equals(waitAudit)){
					eu.setAuthStatus(authStatus);
					eu.setUpdateDatetime(new Date());
					enterpriseUserDao.update(eu);
					return ResponseUtils.buildSuccessResponse("成功更新企业用户认证状态为已审核");
				}
				else{
					return ResponseUtils.buildFailureResponse(PortalConstants.NOT_WAITAUDIT);
				}
			}
			else{
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			}
		} 
		catch (Exception e) {
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1035	个人客户基本信息查询接口
	 * 1.入参检查
	 * 2.查询个人用户信息表
	 * 3.若不存在报错
	 * 4.查询账户信息表，获取该用户全部账户信息
	 * 5.返回用户及其账户信息
	 * 6.异常处理
	 * @author zhanwx
	 * @date 2014年7月24日 下午4:31:51
	 * @param userId
	 * @return AttachmentResponse<PersonalUser>
	 * @since 0.1
	 * @see
	 * ------------------------------------------------------------------------------------------------------------
	 * 修改记录
	 * 修改返回值 类型为PortalUserBasicInfoPara  zxw20140727
	 */
	@Override
	//public AttachmentResponse<PersonalUser> getUserBasicInfo(Long userId) {
	public AttachmentResponse<UserBasicInfoResponse> getUserBasicInfo(Long userId){
		try{
			//入参检查
			if(userId == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"userId"});
			}
			PersonalUserQuery puq = new PersonalUserQuery();
			puq.setUserId(userId);
			PersonalUser pu = personalUserDao.selectOne(puq);
			//查询个人用户信息表判断用户是否存在
			if(pu == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			}
			//1、返回个人用户相关信息
			UserBasicInfoResponse basicInfo = new UserBasicInfoResponse();
			//用户id
			basicInfo.setUserId(userId);
			//姓名
			basicInfo.setRealName(pu.getUserName());
			//头像图片地址
			basicInfo.setHeadPic(pu.getHeadPic());
			//手机号码
			basicInfo.setMobile(pu.getPhoneNumber());
			//电话号码
			basicInfo.setTelephone(pu.getSecondPhoneNumber());
			//电子邮箱
			basicInfo.setEmail(pu.getEmailAddress());
			//地址
			basicInfo.setAddress(pu.getPersonalAddress());
			//预留信息
			basicInfo.setPreString(pu.getPreString());
			//实名认证证件正面照信息
			basicInfo.setPictureFront(pu.getPictureFront());
			//实名认证证件反面照信息
			basicInfo.setPictureBack(pu.getPictureBack());
			//身份证到期时间
			basicInfo.setAuthCertExpirDate(pu.getAuthCertExpirDate());
			//认证证件类别
			basicInfo.setAuthCertType(pu.getAuthCertType());
			//认证证件类别对应中文
			String authCertType = pu.getAuthCertType();
			if(authCertType!=null){
				String authCertTypeName = DictionaryUtils.getDisplayName(DictionaryKey.ID_TYPE, authCertType);
				basicInfo.setAuthCertTypeName(authCertTypeName);
			}
			//认证证件号
			basicInfo.setAuthCertId(pu.getAuthCertId());
			//审核描述
			basicInfo.setAuthDescribe(pu.getAuthDescribe());
			//是否通过实名认证    auth_status实名认证
			String auth_status =  DictionaryUtils.getStringValue(DictionaryKey.AUTH_STATUS_AUTH);
			if(pu.getAuthStatus().equals(auth_status)){
				basicInfo.setAuthStatus(true);
			}else{
				basicInfo.setAuthStatus(false);
			}
			String authStatus = pu.getAuthStatus();
			if(authStatus!=null){
				basicInfo.setUserAuthStatus(authStatus);
				basicInfo.setUserAuthStatusName(DictionaryUtils.getDisplayName(DictionaryKey.AUTH_STATUS, authStatus));
			}
			//用户注册时间
			String reg_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(pu.getCreateDatetime());
			basicInfo.setRegTime(reg_time);
			//2、返回其主账户相关信息
			AccountInfoQuery accountQuery = new AccountInfoQuery();
			accountQuery.setUserId(userId);
			accountQuery.setAccountType(DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_TYPE_MAIN));
			AccountInfo accountInfo = accountInfoDao.selectOne(accountQuery);
			if(accountInfo != null){
				//账户可用余额
				basicInfo.setAvailableBalance(accountInfo.getAvailableBalance());
				//账户不可用余额
				basicInfo.setFrozenAmount(accountInfo.getFrozenAmount());
				//不可提现金额
				basicInfo.setNomentionAmount(accountInfo.getNomentionAmount());
				basicInfo.setAccountId(accountInfo.getAccountId());
			}
			//3、返回用户登录相关信息
			UserLoginInfo userLoginInfo = new UserLoginInfo();
			userLoginInfo.setUserId(userId);
			userLoginInfo = userLoginDao.selectOne(userLoginInfo);
			if(userLoginInfo != null){
				//userLoginInfo.getUserStatus() 为状态的值
				String statusName = DictionaryUtils.getDisplayName(DictionaryKey.USER_STATUS, userLoginInfo.getUserStatus());
				//状态值
				basicInfo.setUserStatus(userLoginInfo.getUserStatus());
				//状态对应的中文
				basicInfo.setUserStatusName(statusName);
				basicInfo.setLoginMobile(userLoginInfo.getUserMobile());
				basicInfo.setLoginEmail(userLoginInfo.getUserEmail());
				//上次登录时间
				basicInfo.setLastLoginTime(userLoginInfo.getLastLogininTime());
			}
			//4、返回银行卡信息
			UserBankAuInfo userBankAuInfo = manageServiceSupport.getLatestUserBankAuInfo(userId);
			if (userBankAuInfo != null) {
				basicInfo.setBankCity(userBankAuInfo.getBankCity());
				basicInfo.setBankCnaps(userBankAuInfo.getBankCnaps());
				basicInfo.setBankId(userBankAuInfo.getBankId());
				basicInfo.setBankInternal(userBankAuInfo.getBankInternal());
				basicInfo.setBankName(userBankAuInfo.getBankName());
				basicInfo.setBankNo(userBankAuInfo.getBankNo());
				basicInfo.setBranchId(userBankAuInfo.getBranchId());
				basicInfo.setBranchName(userBankAuInfo.getBranchName());
				basicInfo.setBankProvince(userBankAuInfo.getBankProvince());
				//打款失败原因
				basicInfo.setAuthenticationReason(userBankAuInfo.getNote());
			}
			//5、查询用户功能表，返回是否开通余额支付
			UserFunctionInfo query = new UserFunctionInfo();
			query.setUserId(userId);
			query.setFunctionId(DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_BALANCE_PAY));
			UserFunctionInfo result = userFunctionDao.selectOne(query);
			//是否开通余额支付
			if (result == null) {
				basicInfo.setBalanceStatus(false);
			} else {
				String functionOpenYes = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_YES);
				if (functionOpenYes.equals(result.getOpenStatus())) {
					basicInfo.setBalanceStatus(true);
				} else {
					basicInfo.setBalanceStatus(false);
				}
			}
			
			//6、查询用户功能表，返回是否开通短信提醒
			UserFunctionInfo messageQuery = new UserFunctionInfo();
			messageQuery.setUserId(userId);
			messageQuery.setFunctionId(DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_SMS_REMIND));
			UserFunctionInfo messageResult = userFunctionDao.selectOne(messageQuery);
			//是否开通短信提醒
			if (messageResult == null) {
				basicInfo.setMessageStatus(false);
			} else {
				String functionOpenYes = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_YES);
				if (functionOpenYes.equals(messageResult.getOpenStatus())) {
					basicInfo.setMessageStatus(true);
				} else {
					basicInfo.setMessageStatus(false);
				}
			}
			
			//7、返回对象
			return ResponseUtils.buildSuccessResponse(basicInfo);
		}
		catch(Exception e){
			AttachmentResponse<UserBasicInfoResponse> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1036	添加银行卡接口
	 * 1.入参检查
	 * 2.查询用户登陆表判断用户是否存在
	 * 3.查询用户绑定银行信息表判断绑定信息是否存在
	 * 4.根据绑定信息是否存在及绑定状态，分情况处理
	 * 5.异常处理
	 * @author zhanwx
	 * @date 2014年7月24日 下午4:32:08
	 * @param info
	 * @return AttachmentResponse<String>
	 * @since 0.1
	 * @see
	 */
	@Override
	public AttachmentResponse<String> addBankCard(UserBandBankInfo info) {
		try {
			String close = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_NO);
			String bind = DictionaryUtils.getStringValue(DictionaryKey.BAND_STATUS_BINDING);
			String unbind = DictionaryUtils.getStringValue(DictionaryKey.BAND_STATUS_UNBINDING);
			//入参检查
			if (info == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"info"});
			}
			Long userId = info.getUserId();
			if (userId == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"userId"});
			}
			//查询用户登录表判断用户是否存在
			UserLoginInfo ul = new UserLoginInfo();
			ul.setUserId(userId);
			ul = userLoginDao.selectOne(ul);
			if(ul == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			}
			UserBandBankInfo ubb = new UserBandBankInfo();
			AttachmentResponse<Long> respinse = idGeneratorService.generate(UserBandBankInfo.class.getName());
			ubb.setUserId(userId);
			ubb.setBankAccountNo(info.getBankAccountNo());
			ubb = userBandBankInfoDao.selectOne(ubb);
			//用户绑定银行卡信息已存在
			if (ubb != null) {
				//已绑定则返回提示
				if(ubb.getBandStatus().equals(bind)){
					return ResponseUtils.buildFailureResponse(PortalConstants.PORTAL_BAND_IS_EXIST);
				}
				//未绑定则把绑定状态设为"已绑定"
				ubb.setBandStatus(bind);
				userBandBankInfoDao.update(ubb);
				return ResponseUtils.buildSuccessResponse("绑定银行卡信息成功");
			} 
			//用户绑定银行卡信息不存在
			else {
				//银行卡快捷支付用途设置默认值不开通
				if(null == info.getUsetypeShortcut()){
					info.setUsetypeShortcut(close);
				}
				//银行卡提现用途设置默认值不开通
				if(null == info.getUsetypeWithdraw()){
					info.setUsetypeWithdraw(close);
				}
				info.setId(respinse.getAttachment());
				info.setUpdateDatetime(new Date());
				info.setBandStatus(bind);
				userBandBankInfoDao.insert(info);
				return ResponseUtils.buildSuccessResponse("绑定银行卡信息成功");
			}
		} 
		catch (Exception e) {
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1037	修改银行卡信息接口
	 * 1.入参检查
	 * 2.查询用户绑定银行信息表
	 * 3.修改银行卡绑定信息
	 * 4.异常处理
	 * @author zhanwx
	 * @date 2014年7月24日 下午4:32:22
	 * @param info
	 * @return AttachmentResponse<String>
	 * @since 0.1
	 * @see
	 */
	@Override
	public AttachmentResponse<String> updateBankCard(UserBandBankInfo info) {
		try {
			//入参检查
			if (info == null ) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"info"});
			}
			if (info.getId() == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"id"});
			}
			UserBandBankInfo ubb = new UserBandBankInfo();
			ubb.setId(info.getId());
			ubb = userBandBankInfoDao.selectOne(ubb);
			//用户不存在或未绑定银行
			if (ubb == null) {
				return ResponseUtils.buildFailureResponse(PortalConstants.PORTAL_USER_NOT_BAND);
			} 
			//修改银行卡信息
			else {
				//银行卡号不修改，修改其他信息
				if(ubb.getBankAccountNo().equals(info.getBankAccountNo())){
					info.setUpdateDatetime(new Date());
					userBandBankInfoDao.update(info);
					return ResponseUtils.buildSuccessResponse("修改银行卡信息成功");
				}
				//修改银行卡号和自己已绑定的重复
				String bind = DictionaryUtils.getStringValue(DictionaryKey.BAND_STATUS_BINDING);
				UserBandBankInfo ubbInfo = new UserBandBankInfo();
				ubbInfo.setUserId(info.getUserId());
				ubbInfo.setBankAccountNo(info.getBankAccountNo());
				ubbInfo = userBandBankInfoDao.selectOne(ubbInfo);
				//用户绑定银行卡信息已存在
				if (ubbInfo != null) {
					//已绑定则返回提示
					if(ubbInfo.getBandStatus().equals(bind)){
						return ResponseUtils.buildFailureResponse(PortalConstants.PORTAL_BAND_IS_EXIST);
					}
			    }
				//修改银行卡号，和其他信息
				info.setUpdateDatetime(new Date());
				userBandBankInfoDao.update(info);
				return ResponseUtils.buildSuccessResponse("修改银行卡信息成功");
		  }
		} catch (Exception e) {
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1038	删除银行卡接口
	 * 1.入参检查
	 * 2.查询用户绑定银行信息表
	 * 3.删除银行卡绑定信息
	 * 4.异常处理
	 * @author zhanwx
	 * @date 2014年7月24日 下午4:32:41
	 * @param userId
	 * @param id
	 * @param source
	 * @return AttachmentResponse<String>
	 * @since 0.1
	 * @see
	 */
	@Override
	public AttachmentResponse<String> deleteBankCard(Long userId, Long id,String source) {
		try {
			//入参检查
			if (userId == null || id == null ) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"userId或id"});
			}
			UserBandBankInfo ubb = new UserBandBankInfo();
			ubb.setId(id);
			ubb = userBandBankInfoDao.selectOne(ubb);
			//用户不存在或未绑定银行
			if (ubb == null) {
				return ResponseUtils.buildFailureResponse(PortalConstants.PORTAL_USER_NOT_BAND);
			} 
			//删除银行卡
			else{
				userBandBankInfoDao.delete(ubb);
				return ResponseUtils.buildSuccessResponse("删除指定银行卡");
			}
		} 
		catch (Exception e) {
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1039	设置提现银行卡接口
	 * 1.入参检查
	 * 2.查询用户绑定银行表
	 * 3.根据id查询绑定关系是否存在
	 * 4.若存在则设置银行卡用途，否则报错
	 * 5.异常处理
	 * @param1 Long userId
	 * @param2 Long id
	 * @param3 String source
	 * @return AttachmentResponse<String>
	 * @author zhanwx
	 * @date 2014年7月23日 下午3:45:09
	 * @since 0.1
	 */
	@Override
	public AttachmentResponse<String> setBankCardWithdrawals(Long userId, Long id, String source) {
		try {
			//入参检查
			if (userId == null || id == null ) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"userId或id"});
			}
			String bankUse = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_YES);
			UserBandBankInfo ubb = new UserBandBankInfo();
			ubb.setId(id);
			ubb = userBandBankInfoDao.selectOne(ubb);
			//用户未绑定银行卡
			if (ubb == null) {
				return ResponseUtils.buildFailureResponse(PortalConstants.PORTAL_USER_NOT_BAND);
			} 
			//更新银行卡用途
			else{
				ubb.setUsetypeWithdraw(bankUse);
				ubb.setUpdateDatetime(new Date());
				userBandBankInfoDao.update(ubb);
			}
			return ResponseUtils.buildSuccessResponse("更新银行卡用途为提现");
		} catch (Exception e) {
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1040	安全问题是否匹配接口
	 * 1.入参检查
	 * 2.查询用户安全问题表
	 * 3.根据用户ID、安全问题ID和答案查询用户输入答案是否匹配
	 * 4.返回匹配结果
	 * 5.异常处理
	 * @param1 Long userId 用户Id
	 * @param2 String questionId 问题Id
	 * @param3 String answer 答案
	 * @return AttachmentResponse<String>
	 * @author zhanwx
	 * @date 2014年7月23日 下午3:40:15
	 * @since 0.1
	 */
	@Override
	public AttachmentResponse<String> checkSafeQuestionIsOk(Long userId,String questionId, String answer) {		
		try {
			//入参检查
			if (userId == null || questionId == null || answer == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"userId或questionId或answer"});
			}
			UserQuestionInfo uq = new UserQuestionInfo();
			uq.setUserId(userId);
			uq.setQuestionId(Integer.parseInt(questionId));
			uq = userQuestionDao.selectOne(uq);
			//用户不存在或未设置该安全问题
			if (uq == null) {
				return ResponseUtils.buildFailureResponse(PortalConstants.PORTAL_NOUSER_OR_NOQUESTION);
			} 
			//安全问题答案匹配
			if(answer.equals(uq.getQuestionContent())){
				return ResponseUtils.buildSuccessResponse("000");
			}
			//安全问题答案不匹配
			else{
				return ResponseUtils.buildFailureResponse(PortalConstants.PORTAL_QUESTIOM_INCORRECT);
			}
		} 
		catch (Exception e) {
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1041	余额支付功能开通关闭接口
	 * 1.入参检查
	 * 2.查询用户功能开通记录
	 * 3.判断入参和用户功能当前状态是否一致
	 * 4.记录存在且状态不一致则更新状态
	 * 5.异常处理
	 * @author zhanwx
	 * @param userId
	 * @param flag
	 * @param source
	 * @return AttachmentResponse<String> 
	 * @date 2014年7月22日 下午5:14:51
	 * @since 0.1
	 * @see
	 */
	@Override
	public AttachmentResponse<String> balancePayOpenOrClose(Long userId, String flag, String source) {
		try {
			//入参检查
			if (userId == null || flag == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"userId或flag"});
			}
			String functionId = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_BALANCE_PAY);
			String open = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_YES);
			String close = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_NO);
			if (!flag.equals(open) && !flag.equals(close)){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENT_INCORRECT,new String[]{"flag"});
			}
			UserFunctionInfo uf = new UserFunctionInfo();
			uf.setUserId(userId);
			uf.setFunctionId(functionId);				
			uf = userFunctionDao.selectOne(uf);
			//记录为空，用户不存在或未设置功能
			if (uf == null) {
				return ResponseUtils.buildFailureResponse(PortalConstants.PORTAL_NOUSER_OR_NOFUNCTION);
			} 
			//若不为空，且flag与当前功能状态一致，返回操作失败
			if  (uf.getOpenStatus().equals(flag)){
				return ResponseUtils.buildFailureResponse(PortalConstants.OPERATE_FAILED);
			}
			//更新功能开通状态
			else {
				SecurityClassInfo sci = new SecurityClassInfo();
				sci.setUserId(userId);
				sci.setBalancepaySet(flag);
				uf.setOpenStatus(flag);
				uf.setUpdateDatetime(new Date());
				portalServiceSupport.balancepayUpdate(uf, sci);
			}
			return ResponseUtils.buildSuccessResponse("成功更新余额支付功能状态");
		} 
		catch (Exception e) {
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1042	短信提醒功能开通关闭接口
	 * 1.入参检查
	 * 2.查询用户功能开通记录
	 * 3.判断入参和用户功能当前状态是否一致
	 * 4.记录存在且状态不一致则更新状态
	 * 5.异常处理
	 * @author zhanwx
	 * @param userId
	 * @param flag
	 * @param source
	 * @return AttachmentResponse<String>
	 * @date 2014年7月22日 下午5:14:51
	 * @since 0.1
	 * @see
	 */
	@Override
	public AttachmentResponse<String> smsAlertsOpenOrClose(Long userId, String flag, String source) {
		try {
			//入参检查
			if (userId == null || flag == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"userId或flag"});
			}
			String functionId = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_SMS_REMIND);
			String open = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_YES);
			String close = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_NO);
			if (!flag.equals(open) && !flag.equals(close)){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENT_INCORRECT,new String[]{"flag"});
			}
			UserFunctionInfo uf = new UserFunctionInfo();
			uf.setUserId(userId);
			uf.setFunctionId(functionId);
			uf = userFunctionDao.selectOne(uf);
			//记录为空，用户不存在或未设置功能
			if (uf == null) {
				return ResponseUtils.buildFailureResponse(PortalConstants.PORTAL_NOUSER_OR_NOFUNCTION);
			} 
			//若不为空，且flag与当前功能状态一致，返回操作失败
			if  (uf.getOpenStatus().equals(flag)){
				return ResponseUtils.buildFailureResponse(PortalConstants.OPERATE_FAILED);
			}
			else {
				SecurityClassInfo sci = new SecurityClassInfo();
				sci.setUserId(userId);
				//短信校验服务   用VerifySet 字段来表示 
				sci.setVerifySet(flag);
				uf.setOpenStatus(flag);
				uf.setUpdateDatetime(new Date());
				portalServiceSupport.smsUpdate(uf, sci);
			}
			return ResponseUtils.buildSuccessResponse("成功更新短信提醒功能状态");
		} 
		catch (Exception e) {
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1043	设置（修改）安全保护问题接口，如果有多个安全保护问题，循环写入
	 * 1、入参检查
	 * 2、查询用户登录表,判断用户是否存在
	 * 3、若用户存在，查询用户安全问题表
	 * 4、判断安全问题是否存在,若存在则更新，否则插入
	 * 5、异常处理
	 * @param1  userId 用户id
	 * @param2  question1 问题1
	 * @param3  answer1 答案1
	 * @param4  question2 问题2
	 * @param5  answer2 答案2
	 * @param6  question3 问题3
	 * @param7  answer3 答案3
	 * @return AttachmentResponse<String>
	 * @author zhanwx
	 * @date 2014-7-22下午16:23:01 
	 * @throws
	 */
	@Override
	public AttachmentResponse<String> setSafeQuestion(Long userId,String question1,String answer1,
			String question2,String answer2,String question3,String answer3) {	
		try {
			//入参检查
			if(userId == null ){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"userId"});
			}
			//从登录表检查用户是否存在
			UserLoginInfo ul = new UserLoginInfo();
			ul.setUserId(userId);
			ul = userLoginDao.selectOne(ul);
			if(ul == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			}
			//查找安全问题
			UserQuestionInfo info = new UserQuestionInfo();
			info.setUserId(userId);
			List<UserQuestionInfo> list = userQuestionDao.selectList(info);
			if(!list.isEmpty()){
				//删除旧记录
				userQuestionDao.delete(info);
			}
			//插入新记录
			SecurityClassInfo sci = new SecurityClassInfo();
			String status = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_YES);
			sci.setUserId(userId);
			sci.setQuestionSet(status);
			portalServiceSupport.setSafeQuestion(userId, question1, answer1, question2, answer2, question3, answer3, sci);
			return ResponseUtils.buildSuccessResponse();
		}
		catch (Exception e) {
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1044	是否有安全保护问题查询接口
	 * 1.入参检查
	 * 2.查询用户安全问题表
	 * 3.判断用户ID是否存在
	 * 4.返回查询结果
	 * 5.异常处理
	 * @param1
	 * @param2
	 * @return
	 * @author zhanwx
	 * @date 2014年7月22日 下午2:49:51
	 * @since 0.1
	 */
	@Override
	public AttachmentResponse<Integer> safeQuestionIsExist(Long userId) {
		//入参检查
		try {
			if (userId == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"userId"});
			}
			//查询用户安全问题表
			List<UserQuestionInfo> uqList = new ArrayList<UserQuestionInfo>();
			UserQuestionInfo uq = new UserQuestionInfo();
			uq.setUserId(userId);
			uqList = userQuestionDao.selectList(uq);
			//若查询结果为空，用户不存在或未设置安全问题
			if (uqList.isEmpty()) {
				return ResponseUtils.buildFailureResponse(PortalConstants.PORTAL_NOUSER_OR_NOQUESTION);
			} 
			//否则，用户已设置
			else {
				return ResponseUtils.buildSuccessResponse(uqList.size());
			}
		}
		catch (Exception e) {
			AttachmentResponse<Integer> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}
	/**
	 * 1045	用户密码修改接口
	 * 1.入参检查
	 * 2.查询登录表
	 * 3.判断用户是否存在
	 * 4.根据入参密码类型进行相应更新
	 * 5.异常处理
	 * @param1 Long userId 用户Id
	 * @param2 String psswd 密码
	 * @param3 String pwd_type 密码类型
	 * @param4 source 来源
	 * @return String
	 * @author zhanwx
	 * @date 2014年7月22日 上午10:38:17
	 * @since 0.1
	 */
	@Override
	public AttachmentResponse<String> updatePwd(Long userId, String psswd, String pwd_type, String source) {
		try {
			//入参检查
			if (userId == null || psswd == null || pwd_type == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"userId或psswd或pwd_type"});
			}
			if (psswd.equals("")){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENT_INCORRECT,new String[]{"psswd"});
			}
			String pwdTypePay = DictionaryUtils.getStringValue(DictionaryKey.PWD_TYPE_PAY);
			String pwdTypeLog = DictionaryUtils.getStringValue(DictionaryKey.PWD_TYPE_LOGIN);
			String pwdTypeTrade = DictionaryUtils.getStringValue(DictionaryKey.PWD_TYPE_TRADE);
			if (!pwd_type.equals(pwdTypePay) && !pwd_type.equals(pwdTypeLog)&&!pwd_type.equals(pwdTypeTrade)){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENT_INCORRECT,new String[]{"pwd_type"});
			}
			//查询用户登录表，判断用户是否存在
			UserLoginInfo userLogin = new UserLoginInfo();
			userLogin.setUserId(userId);
			userLogin = userLoginDao.selectOne(userLogin);
			//若用户不存在返回提示
			if (userLogin == null) {
				return ResponseUtils.buildFailureResponse(PortalConstants.PORTAL_USER_NOT_EXIST);
			}
			String oldLoginPasswd = userLogin.getLoginPasswd();
			String oldPayPasswd = userLogin.getPayPasswd();
			String oldTradePasswd=userLogin.getReserve1();
			
			// 新密码加密
			psswd = securityUtils.encryptMD5(psswd, userLogin.getCreateDatetime());
			//支付密码修改
			if(pwd_type.equals(pwdTypePay)){
				if (psswd.equals(oldPayPasswd)){
					return ResponseUtils.buildFailureResponse(PortalConstants.NEWPWD_OLDPWD_MUST_DIFFERENT);
				}
				if (psswd.equals(oldLoginPasswd)) {
					return ResponseUtils.buildFailureResponse(PortalConstants.LOGINPWD_PAYPWD_MUST_DIFFERENT);
				}
				userLogin.setUserId(userId);
				userLogin.setPayPasswd(psswd);
				userLogin.setErrorPayTimes(0);		//支付密码错误次数清零
				userLogin.setUpdateDatetime(new Date());
				userLoginDao.update(userLogin);	
			}
			//登录密码修改
			if(pwd_type.equals(pwdTypeLog)){
				if (psswd.equals(oldLoginPasswd)){
					return ResponseUtils.buildFailureResponse(PortalConstants.NEWPWD_OLDPWD_MUST_DIFFERENT);
				}
				if (psswd.equals(oldPayPasswd)) {
					return ResponseUtils.buildFailureResponse(PortalConstants.LOGINPWD_PAYPWD_MUST_DIFFERENT);
				}
				
				if (psswd.equals(oldTradePasswd)) {
					return ResponseUtils.buildFailureResponse(PortalConstants.LOGINPWD_TRADEPWD_MUST_DIFFERENT);
				}
				
				userLogin.setUserId(userId);
				userLogin.setLoginPasswd(psswd);
				userLogin.setErrorLoginTimes(0); 		//登录错误次数清零
				userLogin.setUpdateDatetime(new Date());
				userLoginDao.update(userLogin);	
			}
			//交易密码修改
			if(pwd_type.equals(pwdTypeTrade)){
				if (psswd.equals(oldTradePasswd)){
					return ResponseUtils.buildFailureResponse(PortalConstants.NEWPWD_OLDPWD_MUST_DIFFERENT);
				}
				if (psswd.equals(oldLoginPasswd)) {
					return ResponseUtils.buildFailureResponse(PortalConstants.LOGINPWD_TRADEPWD_MUST_DIFFERENT);
				}
				userLogin.setUserId(userId);
				userLogin.setReserve1(psswd);
				userLogin.setReserve3(0); 		//交易密码错误次数清零
				userLogin.setUpdateDatetime(new Date());
				userLoginDao.update(userLogin);	
			}
			return ResponseUtils.buildSuccessResponse("密码修改成功");
		} 
		catch (Exception e) {
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}
	/**
	 * 1046	预留信息修改接口
	 * 1.入参检查
	 * 2.查询登陆表
	 * 3.判断用户是否存在
	 * 4.获取用户类型
	 * 5.根据用户类型，在对应的数据表中更新信息
	 * 6.异常处理
	 * @param1 Long userId 用户Id
	 * @param2 String message 预留信息
	 * @param3 String source 来源
	 * @return AttachmentResponse<String>
	 * @author zhanwx
	 * @date 2014年7月21日 下午5:30:19
	 * @since 0.1
	 */
	@Override
	public AttachmentResponse<String> reserveMessageUpdate(Long userId, String message, String source) {
		try {
			//入参检查
			if (userId == null || message == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"userId或message"});
			}
			String userTypePerson = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL);
			String open = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_YES);
			//查询用户登录表判断用户是否存在
			UserLoginInfo userLogin = new UserLoginInfo();
			userLogin.setUserId(userId);
			userLogin = userLoginDao.selectOne(userLogin);
			//若用户不存在返回提示
			if (userLogin == null) {
				return ResponseUtils.buildFailureResponse(PortalConstants.PORTAL_USER_NOT_EXIST);
			}
			//若用户存在，根据用户类型进行操作
			else {
				String userType = userLogin.getUserType();
				SecurityClassInfo sci = new SecurityClassInfo();
				sci.setUserId(userId);
				sci.setReservationSet(open);
				//若用户类型为个人，更新个人用户表中该用户预留信息
				if(userType.equals(userTypePerson)){
					PersonalUser pu = new PersonalUser();
					pu.setUserId(userId);
					pu.setPreString(message);
					pu.setUpdateDatetime(new Date());
					portalServiceSupport.reservationUpdate(pu,sci);
				}
				//若用户类型为企业，更新企业用户表中该企业预留信息
				else{
					EnterpriseUser eu = new EnterpriseUser();
					eu.setUserId(userId);
					eu.setPreString(message);
					eu.setUpdateDatetime(new Date());
					portalServiceSupport.reservationUpdate(eu, sci);	
				}
				return ResponseUtils.buildSuccessResponse("预留信息修改成功");
			}
		} 
		catch (Exception e) {
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	
	/**
	 * 1047	用户安全问题列表查询接口
	 * 1.入参检查
	 * 2.查询用户信息
	 * 3.判断用户是否存在
	 * 4.返回安全问题列表
	 * 5.异常处理
	 * @param Long userId 用户ID
	 * @return AttachmentResponse<List<UserQuestionInfo>>
	 * @author zhanwx
	 * @date 2014年7月21日 下午4:38:18
	 * @since 0.1
	 */
	@Override
	public AttachmentResponse<List<UserQuestionInfo>> getQuestion(Long userId) {
		try {
			//入参检查
			if (userId == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"userId"});
			}
			UserQuestionInfo userQuestion = new UserQuestionInfo();
			userQuestion.setUserId(userId);
			//查询安全问题表，返回指定用户所设置的安全问题列
			List<UserQuestionInfo> userQuestionList = userQuestionDao.selectList(userQuestion);
			//若list为空，根据用户注册同时添加安全问题逻辑，证明用户不存在，返回提示
			if (userQuestionList.isEmpty()) {
				return ResponseUtils.buildFailureResponse(PortalConstants.PORTAL_USER_NOT_EXIST);
			} 
			//若list不为空，则返回该list
			else {
				return ResponseUtils.buildSuccessResponse(userQuestionList);
			}
		} catch (Exception e) {
			AttachmentResponse<List<UserQuestionInfo>> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}
	
	/**
	 * 1098 获取提现银行卡列表
	 * @author zhanwx
	 * @date 2014年7月28日 下午5:14:48
	 * @param userId
	 * @return AttachmentResponse<List<UserBandBankInfo>>
	 * @since 0.1
	 * @see
	 */
	//-----------------------修改记录-------------------------
	//修改者：zhanwx  时间：2014-08-19
	public AttachmentResponse<List<UserBandBankInfo>> getBankCard(Long userId,String bankCardType,String bankUseType){
		try{
			//入参检查
			if(userId == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"userId"});
			}
			UserBandBankInfo ubb = new UserBandBankInfo();
			String pay = DictionaryUtils.getStringValue(DictionaryKey.BANK_USETYPE_PAY) ;
			String withdraw = DictionaryUtils.getStringValue(DictionaryKey.BANK_USETYPE_WITHDRAW); 
			String open = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_YES);
			String status = DictionaryUtils.getStringValue(DictionaryKey.BAND_STATUS_BINDING);
			if (StringUtils.isNotBlank(bankUseType)) {
				if(pay.equals(bankUseType)){
					ubb.setUsetypeShortcut(open);
				} else if (withdraw.equals(bankUseType)){
					ubb.setUsetypeWithdraw(open);
				}
			}
			if (StringUtils.isNotBlank(bankCardType)) {
				ubb.setBankCardType(bankCardType);
			}
			ubb.setUserId(userId);
			ubb.setBandStatus(status);
			List<UserBandBankInfo> ubbList = userBandBankInfoDao.selectList(ubb);
			return ResponseUtils.buildSuccessResponse(ubbList);
		}
		catch(Exception e){
			AttachmentResponse<List<UserBandBankInfo>> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}
	
	/**
	 * 1101 按Id查询用户绑定银行卡对象
	 * @author zhanwx
	 * @date 2014年7月28日 下午5:14:19
	 * @param id
	 * @return AttachmentResponse<UserBandBankInfo>
	 * @since 0.1
	 * @see
	 */
	public AttachmentResponse<UserBandBankInfo> getUserBandBankInfo(Long id){
		try{
			//入参检查
			if(id == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"id"});
			}
			List<UserBandBankInfo> ubbList = new ArrayList<UserBandBankInfo>();
			UserBandBankInfo ubb = new UserBandBankInfo();
			ubb.setId(id);
			ubb = userBandBankInfoDao.selectOne(ubb);
			//查询用户绑定银行信息表判断id是否存在，不存在则返回提示
			if(ubb == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.RECORD_NOT_EXIST);
			}
			else{
				return ResponseUtils.buildSuccessResponse(ubb);
			}
		}
		catch(Exception e){
			AttachmentResponse<UserBandBankInfo> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
		
	}
	
	/**
	 * 1102 个人实名认证申请接口
	 * @author shilei@cssweb.com.cn
	 * @date 2014年8月1日 下午3:10:12
	 * @param userId　用户id
	 * @param userName 真实姓名
	 * @param cardId　证件id
	 * @param cardType　证件类型
	 * @param cardUrlfront　证件正面URL
	 * @param cardUrlback　证件反面URL
	 * @param cardIdExpirDate 证件到期日期
	 * @param address 常用地址
	 * @param bankAuInfo 打款认证银行信息
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public  AttachmentResponse  personalAu(PersonalUser user, UserBankAuInfo bankAuInfo){
		try {
			if(user == null){ 
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"user"});
			}
			
			//查询用户是否存在
			PersonalUserQuery puq = new PersonalUserQuery();
			puq.setUserId(user.getUserId());
			PersonalUser pu = personalUserDao.selectOne(puq);
			if (null == pu){
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			}
			//组织认证的用户信息
			user.setAuthDate(new Date());
			user.setUpdateDatetime(new Date());
			//检查银行打款认证信息对象是否为空
			if(bankAuInfo != null){
				UserBankAuInfo ub = new UserBankAuInfo();
				ub.setUserId(user.getUserId());
				ub.setBankNo(bankAuInfo.getBankNo());
				ub.setAuthNum(2);
				ub = userBankAuInfoDao.selectOne(ub);
				//检查该银行卡确认金额次数是否超过限制
				if(null == ub){
					AttachmentResponse<Long> userBankAuInforespinse = idGeneratorService.generate(UserBankAuInfo.class.getName());
					bankAuInfo.setId(userBankAuInforespinse.getAttachment());
					bankAuInfo.setUserId(user.getUserId());
					bankAuInfo.setUserAuDateTime(new Date());
					bankAuInfo.setUpdateDatetime(new Date());
				}
				else{
					return ResponseUtils.buildFailureResponse(PortalConstants.BANKCARD_IS_LIMITED);
				}
			}
			portalServiceSupport.personalAu(user, bankAuInfo);		
			return ResponseUtils.buildSuccessResponse();
		} catch (Exception e) {
			AttachmentResponse response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1103 判断用户ID，身份证号码是否匹配接口
	 * @param1 userId
	 * @param2 cardId
	 * @return AttachmentResponse<Boolean>
	 * @author shilei
	 * @date 2014年8月15日 上午11:50:14
	 * @since 0.1
	 */
	@Override
	public AttachmentResponse<Boolean> checkCardIdIsValid(Long userId, String cardId) {
		try {
			if(userId == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"userId"});
			}
			if(cardId == null || "".equals(cardId)){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"cardId"});
			}
			UserLoginInfo query = new UserLoginInfo();
			query.setUserId(userId);
			UserLoginInfo userLogin = userLoginDao.selectOne(query);
			// 判断个人用户或企业用业，返回
			String userTypePerson = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL);
			String userTypeEn = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_ENTERPRISE);
			String dataCard = "";
			if (userTypeEn.equals(userLogin.getUserType())) {
				//企业用户
				EnterpriseUserQuery euq = new EnterpriseUserQuery();
				euq.setUserId(userId);
				EnterpriseUser enterprise = enterpriseUserDao.selectOne(euq);
				dataCard = enterprise.getLegalPersonidno();
			} else if (userTypePerson.equals(userLogin.getUserType())) {
				//个人用户
				PersonalUserQuery param = new PersonalUserQuery();
				param.setUserId(userId);
				PersonalUser pUser = personalUserDao.selectOne(param);
				dataCard = pUser.getAuthCertId();
			}
			else{
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			}
			if(!cardId.trim().equals(dataCard)){
				return ResponseUtils.buildFailureResponse(PortalConstants.IDCARD_MISMATCHING, false);
			}
			return ResponseUtils.buildSuccessResponse(true);
		} catch (Exception e) {
			AttachmentResponse<Boolean> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1110 手机解绑接口
	 * @param userId
	 * @return AttachmentResponse
	 * @author wangpeng
	 * @date 2014年8月15日 上午11:55:11
	 * @since 0.1
	 */
	@Override
	public AttachmentResponse unbindingMobilePhone(Long userId) {
		try {
			if(userId == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"userId"});
			}
			return  portalServiceSupport.unbindingMobilePhone(userId);
		} catch(Exception e){
			AttachmentResponse response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;	
		}
	}

	/**
	 * 1111 邮箱解绑接口
	 * @param userId
	 * @return AttachmentResponse
	 * @author wangpeng
	 * @date 2014年8月15日 上午11:55:11
	 * @since 0.1
	 */
	@Override
	public AttachmentResponse unbindingEmail(Long userId) {
		try {
			if(userId == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"userId"});
			}
			return  portalServiceSupport.unbindingEmail(userId);
		} catch(Exception e){
			AttachmentResponse response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;	
		}
	}
	
	/**
	 * 1112 手机、邮箱设为登录名
	 * @author wangpeng
	 * @date 2014-08-07
	 * @param userId 用户id
	 * @param type  用户类型
	 * @return  1302 用户不存在   1306 记录不存在    1462   未绑定手机 
	 */
	@Override
	public AttachmentResponse<String> loginNameSynchronous(Long userId,String type){
		try {
			if(userId == null || type == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"userId or type"});
			}
			UserLoginInfo userLogin = new UserLoginInfo();
			userLogin.setUserId(userId);
			userLogin = userLoginDao.selectOne(userLogin);
			if(userLogin == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			}
			String userTypePerson = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL);
			String userTypeEn = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_ENTERPRISE);
			//企业用户
			if (userTypeEn.equals(userLogin.getUserType())) {
				EnterpriseUserQuery euq = new EnterpriseUserQuery();
				euq.setUserId(userLogin.getUserId());
				EnterpriseUser enterprise = enterpriseUserDao.selectOne(euq);
				if(enterprise == null){
					return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
				}
				//同步手机
				if(type.equals("01")){
					return ResponseUtils.buildFailureResponse(PortalConstants.ENTERPRISE_PHONE_NOT_LOGINNAME);
				}
				//同步邮箱
				if(type.equals("02")){
					String euEmail = enterprise.getEmail();
					if (StringUtils.isBlank(euEmail)) {
						return ResponseUtils.buildFailureResponse(PortalConstants.NOT_BINDING_EMAIL);
					}
					//该用户未把绑定邮箱同步为登录名，继而检查是否被其他用户使用
					if(!enterprise.getEmail().equals(userLogin.getUserEmail()) ){
						UserLoginInfo ulpu = new UserLoginInfo();
						ulpu.setUserEmail(euEmail);
						ulpu = userLoginDao.selectOne(ulpu);
						//该邮箱不与其他用户登录名冲突，可同步
						if(ulpu == null){
							userLogin.setUserEmail(euEmail);
							userLogin.setUpdateDatetime(new Date());
						}
						else{
							return ResponseUtils.buildFailureResponse(PortalConstants.EMAIL_EXIST);
						}
					}	
					else{
						return ResponseUtils.buildFailureResponse(PortalConstants.EMAIL_IS_LOGINNAME);
					}
				}
			} 
			if (userTypePerson.equals(userLogin.getUserType())) {
				//个人用户
				PersonalUserQuery param = new PersonalUserQuery();
				param.setUserId(userLogin.getUserId());
				PersonalUser pUser = personalUserDao.selectOne(param);
				if(pUser == null){
					return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
				}
				//同步手机
				if(type.equals("01")){
					String puPhone = pUser.getPhoneNumber();
					if (StringUtils.isBlank(puPhone)) {
						return ResponseUtils.buildFailureResponse(PortalConstants.NOT_BINDING_MOBILE_PHONE);
					}
					//该用户未把绑定手机同步为登录名，继而检查是否被其他用户使用
					if(!pUser.getPhoneNumber().equals(userLogin.getUserMobile())){
						UserLoginInfo uleu = new UserLoginInfo();
						uleu.setUserMobile(puPhone);
						uleu = userLoginDao.selectOne(uleu);
						//该手机号不与其他用户登录名冲突，可同步
						if(uleu == null){
							userLogin.setUserMobile(puPhone);
							userLogin.setUpdateDatetime(new Date());
						}
						else{
							return ResponseUtils.buildFailureResponse(PortalConstants.PHONE_NUM_EXIST);
						}
					}
					else{
						return ResponseUtils.buildFailureResponse(PortalConstants.PHONE_IS_LOGINNAME);
					}
				}
				//同步邮箱
				if(type.equals("02")){
					String puEmail = pUser.getEmailAddress();
					if (StringUtils.isBlank(puEmail)) {
						return ResponseUtils.buildFailureResponse(PortalConstants.NOT_BINDING_EMAIL);
					}
					//该用户未把绑定邮箱同步为登录名，继而检查是否被其他用户使用
					if(!pUser.getEmailAddress().equals(userLogin.getUserEmail())){
						UserLoginInfo ulpu = new UserLoginInfo();
						ulpu.setUserEmail(puEmail);
						ulpu = userLoginDao.selectOne(ulpu);
						//该邮箱不与其他用户登录名冲突，可同步
						if(ulpu == null){
							userLogin.setUserEmail(puEmail);
							userLogin.setUpdateDatetime(new Date());
						}
						else{
							return ResponseUtils.buildFailureResponse(PortalConstants.EMAIL_EXIST);
						}
					}
					else{
						return ResponseUtils.buildFailureResponse(PortalConstants.EMAIL_IS_LOGINNAME);
					}
				}
			} 		
			userLoginDao.update(userLogin);
			return ResponseUtils.buildSuccessResponse();
		} catch (Exception e) {
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}
	
	/**
	 * 1113 手机、邮箱取消设为登录名
	 * @author wangpeng
	 * @date 2014-08-07
	 * @param userId 用户id
	 * @param type 用户类型
	 */
	@Override
	public AttachmentResponse<String> cancalLoginName(Long userId,String type){
		try {
			if(userId == null || type == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"userId or type"});
			}
			UserLoginInfo userLogin = new UserLoginInfo();
			userLogin.setUserId(userId);
			userLogin = userLoginDao.selectOne(userLogin);
			if(userLogin == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.RECORD_NOT_EXIST);
			}
			String enterprise = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_ENTERPRISE);
			if(userLogin.getUserType().equals(enterprise)){
				return ResponseUtils.buildFailureResponse(PortalConstants.ENTERPRISE_NOT_CANCEL_LOGINNAME);
			}
			if(type.equals("01")){
				String mobile = userLogin.getUserMobile();
				if (StringUtils.isBlank(mobile)) {
					return ResponseUtils.buildFailureResponse(PortalConstants.PHONE_ISNT_LOGINNAME);
				}
				if (StringUtils.isBlank(userLogin.getUserEmail())) {
					return ResponseUtils.buildFailureResponse(PortalConstants.CANCEL_MOBILE_MUST_SET_EMAIL);	
				}
				userLoginDao.updateUnbindingMobile(userId);
			}
			if(type.equals("02")){
				String email = userLogin.getUserEmail();
				if (StringUtils.isBlank(email)) {
					return ResponseUtils.buildFailureResponse(PortalConstants.EMAIL_ISNT_LOGINNAME);
				}
				if (StringUtils.isBlank(userLogin.getUserMobile())) {
					return ResponseUtils.buildFailureResponse(PortalConstants.CANCEL_EMAIL_MUST_SET_MOBILE);	
				}
				userLoginDao.updateUnbindingEmail(userId);
			}
			return ResponseUtils.buildSuccessResponse();
		} catch (Exception e) {
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}
	
	/**
	 * 1115 用户安全等级详细信息查询接口
	 * 1.入参检查
	 * 2.用户存在则根据userId查询安全等级表，返回对象
	 * 3.异常处理
	 * @param userId
	 * @return AttachmentResponse<SecurityClassInfo>
	 * @author zhanwx
	 * @date 2014年8月15日 上午10:48:33
	 * @since 0.1
	 */
	@Override
	public AttachmentResponse<SecurityClassInfo> checkUserSecurity(Long userId){
		try{
			if(null == userId){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"userId"});
			}
			SecurityClassInfo sci = new SecurityClassInfo();
			sci.setUserId(userId);
			sci = sciDao.selectOne(sci);
			if(null == sci){
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			}
			return ResponseUtils.buildSuccessResponse(sci);
		}
		catch(Exception e){
			AttachmentResponse response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;	
		}
	}
	/**
	 * 1116 设置银行卡用途
	 * @author zhanwx
	 * @date 2014年8月15日 下午12:01:14
	 * @param userId
	 * @param type
	 * @param state
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public AttachmentResponse<Long> setBankCardType(Long id,String type,String state){
		try{
			if(null == id || null == type || null == state){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"id或type或state"});
			}
			UserBandBankInfo ubb1 = new UserBandBankInfo();
			UserBandBankInfo ubb2 = new UserBandBankInfo();
			ubb1.setId(id);
			ubb1 = userBandBankInfoDao.selectOne(ubb1);
			if(null == ubb1){
				return ResponseUtils.buildFailureResponse(GlobalConstants.RECORD_NOT_EXIST);
			}
			ubb2.setId(id);
			String pay = DictionaryUtils.getStringValue(DictionaryKey.BANK_USETYPE_PAY);
			String withdraw = DictionaryUtils.getStringValue(DictionaryKey.BANK_USETYPE_WITHDRAW);
			if(type.equals(pay)){
				ubb2.setUsetypeShortcut(state);
			}
			else if(type.equals(withdraw)){
				ubb2.setUsetypeWithdraw(state);
			}
			ubb2.setUpdateDatetime(new Date());
			userBandBankInfoDao.update(ubb2);
			return ResponseUtils.buildSuccessResponse(id);
		}
		catch(Exception e){
			AttachmentResponse response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}
	
	
	/**
	 * 1117 查询指定账户指定时间范围内指定类型交易总金额
	 * 1.入参检查
	 * 2.非空则查询资金变动明细表
	 * 3.返回账户在指定时间范围内指定类型的交易总金额
	 * 4.异常处理
	 * @param Long accountId
	 * @return AttachmentResponse<BigDecimal>
	 * @author zhanwx
	 * @date 2014年8月18日 上午10:40:14
	 * @since 0.1
	 */
	public AttachmentResponse<BigDecimal> selectTotalBalance(Long accountId,String txnType,Date beginDate,Date endDate){
		try{
			if(null == accountId || null == txnType || null == beginDate || null == endDate){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"accountId or txnType or Date"});
			}
			AccountMoneyChgDetailQuery amcQuery = new AccountMoneyChgDetailQuery();
			amcQuery.setAccountId(accountId);
			amcQuery.setTxnType(txnType);
			amcQuery.setBeginDate(beginDate);
			amcQuery.setEndDate(endDate);
			//查询该账户某时间范围内指定交易类型的交易总金额		
			BigDecimal totalBalance = moneyChgDao.selectTradeAmount(amcQuery);
			if(null == totalBalance){
				return ResponseUtils.buildSuccessResponse(new BigDecimal(0));
			}
			else{
				return ResponseUtils.buildSuccessResponse(totalBalance);
			}
		}
		catch(Exception e){
			AttachmentResponse response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(),e);
			return response;
		}
	}
	
	/**
	 * 1118  个人实名认证账户关联
	 * @author wangpeng
	 * @date 2014年9月2日下午 5:32:09
	 * @param relationUserName 被关联账户
	 * @param relationPayPwd 被关联账户支付密码
	 * @param UserName 本账户用户名
	 * @param payPwd 本账户支付密码
	 * @param authCertId本账户身份证号码 
	 * @param mac 校验码	
	 * @return 
	 */  
	public AttachmentResponse<String> personalAuthRelation(String relationUserName, String relationPayPwd,String UserName, String payPwd, String authCertId,String mac){
		try{
			//入参检查
			if(relationUserName == null || relationPayPwd == null || payPwd == null || authCertId == null || mac == null ){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,
						new String[]{"relationUserName or relationPayPwd or payPwd or certNo or mac！"});
			}
			//按手机号或者邮箱验证关联账户用户名密码是否正确
			UserLoginInfo query = new UserLoginInfo();
			if (PortalServiceImpl.isNumeric(relationUserName)) {
				query.setUserMobile(relationUserName);
			} 
			else {
				query.setUserEmail(relationUserName);
			}
			
			//密码加密
			 /*   由于密码校验需要获取到创建日期 故此先按照用户名查询  再判断日期   修改人：石磊
			query.setPayPasswd(relationPayPwd);
			UserLoginInfo userLogin = userLoginDao.selectOne(query);
			if(userLogin == null){
				 return ResponseUtils.buildFailureResponse(PortalConstants.RELATION_USERNAME_OR_PWD_WRONG);
			}*/
			UserLoginInfo userLogin = userLoginDao.selectOne(query);
			if(userLogin == null){
				return ResponseUtils.buildFailureResponse(PortalConstants.RELATION_USERNAME_OR_PWD_WRONG);
			}
 
			relationPayPwd = securityUtils.encryptMD5(relationPayPwd, userLogin.getCreateDatetime());
			if(!relationPayPwd.trim().equals(userLogin.getPayPasswd())){
				return ResponseUtils.buildFailureResponse(PortalConstants.RELATION_USERNAME_OR_PWD_WRONG);
			}
 
			//获取被关联账户信息
			PersonalUserQuery personal = new PersonalUserQuery();
			personal.setUserId(userLogin.getUserId());
			PersonalUser relationPersonaluser = personalUserDao.selectOne(personal);
			if(relationPersonaluser==null){
				return ResponseUtils.buildFailureResponse(PortalConstants.USER_NAME_NOT_EXIST);
			}
			String auth = DictionaryUtils.getStringValue(DictionaryKey.AUTH_STATUS_AUTH);
			String authType = DictionaryUtils.getStringValue(DictionaryKey.AUTH_TYPE_RELATION);
			if(relationPersonaluser.getAuthStatus().equals(auth)){
				return ResponseUtils.buildFailureResponse(PortalConstants.RELATION_ACCOUNT_AUTH_ALREADY);
			}
			//更新被关联账户实名认证状态
			relationPersonaluser.setAuthStatus(auth);
			relationPersonaluser.setAuthType(authType);
			
			//验证关联账户信息
			UserLoginInfo mainQuery = new UserLoginInfo();
			if (PortalServiceImpl.isNumeric(UserName)) {
				mainQuery.setUserMobile(UserName);
			} 
			else {
				mainQuery.setUserEmail(UserName);
			}
			/* 验证密码需要加密  修改验证流程                   修改人 ：  石磊
			mainQuery.setPayPasswd(payPwd);
			UserLoginInfo mainUserLogin = userLoginDao.selectOne(mainQuery);
			if(mainUserLogin == null){
				 return ResponseUtils.buildFailureResponse(PortalConstants.MAIN_ACCOUNT_PWD_WRONG);
			}*/
			UserLoginInfo mainUserLogin = userLoginDao.selectOne(mainQuery);
			if(mainUserLogin == null){
				 return ResponseUtils.buildFailureResponse(PortalConstants.MAIN_ACCOUNT_PWD_WRONG);
			}
			payPwd = securityUtils.encryptMD5(payPwd, mainUserLogin.getCreateDatetime());
			if(! payPwd.trim().equals(mainUserLogin.getPayPasswd())){
				return ResponseUtils.buildFailureResponse(PortalConstants.MAIN_ACCOUNT_PWD_WRONG);
			}
			
			//获取关联账户信息
			PersonalUserQuery mainPersonal = new PersonalUserQuery();
			mainPersonal.setUserId(mainUserLogin.getUserId());
			mainPersonal.setAuthCertId(authCertId);
			PersonalUser mainPersonaluser = personalUserDao.selectOne(mainPersonal);
			if(mainPersonaluser==null){
				return ResponseUtils.buildFailureResponse(PortalConstants.AUTH_CERT_ID_WRONG);
			}
			if(!mainPersonaluser.getAuthStatus().equals(auth)){
				return ResponseUtils.buildFailureResponse(PortalConstants.MAIN_ACCOUNT_NOT_AUTH);
			}
			
			//验证关联账户是否通过关联认证的实名信息
			UserRelAuInfo userRel = new UserRelAuInfo();
			userRel.setRelationUserId(mainUserLogin.getUserId());
			int count = userRelAuInfoDao.selectCount(userRel);
			if(count > 0){
				return ResponseUtils.buildFailureResponse(PortalConstants.FORBIDDEN_ACCOUNT_RELA);
			}
			//验证关联账户，个人最多能关联5个账号
			UserRelAuInfo userRelAuInfoCheck = new UserRelAuInfo();
			userRelAuInfoCheck.setMainUserId(mainUserLogin.getUserId());
			List<UserRelAuInfo> list = userRelAuInfoDao.selectList(userRelAuInfoCheck);
			if(list.size() >= 5){
				return ResponseUtils.buildFailureResponse(PortalConstants.PERSONAL_RELA_AUTH_LIMIT);
			}
			
			//插入实名认证关联关系表
			AttachmentResponse<Long> respinse = idGeneratorService
					.generate(UserRelAuInfo.class.getName());
			UserRelAuInfo userRelAuInfo = new UserRelAuInfo();
			userRelAuInfo.setId(respinse.getAttachment());
			userRelAuInfo.setMainUserId(mainUserLogin.getUserId());
			userRelAuInfo.setRelationUserId(userLogin.getUserId());
			userRelAuInfo.setCreateDateTime(new Date());
			userRelAuInfo.setUpdateDateTime(new Date());
			
			//更新安全等级表
			SecurityClassInfo security = new SecurityClassInfo();
			security.setUserId(userLogin.getUserId());
			String open = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_YES);
			security.setAuthSet(open);
			security.setUpdateDatetime(new Date());
			
			//更新打款认证表
			UserBankAuInfo mainBankAuInfo = manageServiceSupport.getLatestUserBankAuInfo(mainPersonaluser.getUserId()); 
			UserBankAuInfo relationBankAuInfo = manageServiceSupport.getLatestUserBankAuInfo(relationPersonaluser.getUserId());
			
			portalServiceSupport.personalAuthRelation(relationPersonaluser,mainPersonaluser, userRelAuInfo,security,mainBankAuInfo,relationBankAuInfo);
			return ResponseUtils.buildSuccessResponse();
		}
		catch(Exception e){
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(),e);
			return response;
		}
	}
	
	/**
	 * 1119  企业实名认证账户关联
	 * @author wangpeng
	 * @date 2014年9月2日下午 5:32:09
	 * @param relationUserName 被关联账户
	 * @param relationPayPwd 被关联账户支付密码
	 * @param payPwd 本账户支付密码
	 * @param businessLicence本账户营业执照注册号 
	 * @param mac 校验码	
	 */  
	public AttachmentResponse<String> enterpriseAuthRelation(String relationUserName, String relationPayPwd, String UserName,String payPwd, String businessLicence,String mac){
		try{
			//入参检查
			if(relationUserName == null || relationPayPwd == null || payPwd == null || businessLicence == null || mac == null ){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,
						new String[]{"relationUserName or relationPayPwd or payPwd or bussinessLicence or mac！"});
			}
			//按手机号或者邮箱验证关联账户用户名密码是否正确
			UserLoginInfo query = new UserLoginInfo();
			if (PortalServiceImpl.isNumeric(relationUserName)) {
				query.setUserMobile(relationUserName);
			} 
			else {
				query.setUserEmail(relationUserName);
			}
			/*
			 * 修改流程： 验证密码时 密码需要加密     修改人：石磊
			 * query.setPayPasswd(relationPayPwd);
			UserLoginInfo userLogin = userLoginDao.selectOne(query);
			if(userLogin == null){
				 return ResponseUtils.buildFailureResponse(PortalConstants.RELATION_USERNAME_OR_PWD_WRONG);
			}*/
			UserLoginInfo userLogin = userLoginDao.selectOne(query);
			if(userLogin == null){
				 return ResponseUtils.buildFailureResponse(PortalConstants.RELATION_USERNAME_OR_PWD_WRONG);
			}
			relationPayPwd = securityUtils.encryptMD5(relationPayPwd, userLogin.getCreateDatetime());
			if(!relationPayPwd.trim().equals(userLogin.getPayPasswd())){
				 return ResponseUtils.buildFailureResponse(PortalConstants.RELATION_USERNAME_OR_PWD_WRONG);
			}
			//校验码TODO
			//获取被关联账户信息
			EnterpriseUserQuery enterprise = new EnterpriseUserQuery();
			enterprise.setUserId(userLogin.getUserId());
			EnterpriseUser relationEnterpriseuser = enterpriseUserDao.selectOne(enterprise);
			if(relationEnterpriseuser==null){
				return ResponseUtils.buildFailureResponse(PortalConstants.USER_NAME_NOT_EXIST);
			}
			//更新被关联账户实名认证状态
			String auth = DictionaryUtils.getStringValue(DictionaryKey.AUTH_STATUS_AUTH);
			String authType = DictionaryUtils.getStringValue(DictionaryKey.AUTH_TYPE_RELATION);
			if(relationEnterpriseuser.getAuthStatus().equals(auth)){
				return ResponseUtils.buildFailureResponse(PortalConstants.RELATION_ACCOUNT_AUTH_ALREADY);
			}
			relationEnterpriseuser.setAuthStatus(auth);
			relationEnterpriseuser.setAuthType(authType);
			
			//验证关联账户信息
			UserLoginInfo mainQuery = new UserLoginInfo();
			if (PortalServiceImpl.isNumeric(UserName)) {
				mainQuery.setUserMobile(UserName);
			} 
			else {
				mainQuery.setUserEmail(UserName);
			}
			/*
			 * 修改流程： 验证密码时 密码需要加密     修改人：石磊
			 * mainQuery.setPayPasswd(payPwd);
			UserLoginInfo mainUserLogin = userLoginDao.selectOne(mainQuery);
			if(mainUserLogin == null){
				 return ResponseUtils.buildFailureResponse(PortalConstants.MAIN_ACCOUNT_PWD_WRONG);
			}*/
			
			UserLoginInfo mainUserLogin = userLoginDao.selectOne(mainQuery);
			if(mainUserLogin == null){
				 return ResponseUtils.buildFailureResponse(PortalConstants.MAIN_ACCOUNT_PWD_WRONG);
			}
			payPwd = securityUtils.encryptMD5(payPwd, mainUserLogin.getCreateDatetime());
			if(!payPwd.trim().equals(mainUserLogin.getPayPasswd())){
				 return ResponseUtils.buildFailureResponse(PortalConstants.MAIN_ACCOUNT_PWD_WRONG);
			}
			//获取关联账户信息
			EnterpriseUserQuery mainenterprise = new EnterpriseUserQuery();
			mainenterprise.setUserId(mainUserLogin.getUserId());
			mainenterprise.setBusinessLicence(businessLicence);
			EnterpriseUser mainEnterpriseUser = enterpriseUserDao.selectOne(mainenterprise);
			if(mainEnterpriseUser==null){
				return ResponseUtils.buildFailureResponse(PortalConstants.BUSINESS_LICENCE_WRONG);
			}
			if(!mainEnterpriseUser.getAuthStatus().equals(auth)){
				return ResponseUtils.buildFailureResponse(PortalConstants.MAIN_ACCOUNT_NOT_AUTH);
			}
			
			//验证关联账户是否通过关联认证的实名信息
			UserRelAuInfo userRel = new UserRelAuInfo();
			userRel.setRelationUserId(mainUserLogin.getUserId());
			int count = userRelAuInfoDao.selectCount(userRel);
			if(count > 0){
				return ResponseUtils.buildFailureResponse(PortalConstants.FORBIDDEN_ACCOUNT_RELA);
			}
			
			//验证关联账户，企业最多能关联10个账号
			UserRelAuInfo userRelAuInfoCheck = new UserRelAuInfo();
			userRelAuInfoCheck.setMainUserId(mainUserLogin.getUserId());
			List<UserRelAuInfo> list = userRelAuInfoDao.selectList(userRelAuInfoCheck);
			if(list.size()>=10){
				return ResponseUtils.buildFailureResponse(PortalConstants.ENTERPRISE_RELA_AUTH_LIMIT);
			}
			
			//插入实名认证关联关系表
			AttachmentResponse<Long> respinse = idGeneratorService
					.generate(UserRelAuInfo.class.getName());
			UserRelAuInfo userRelAuInfo = new UserRelAuInfo();
			userRelAuInfo.setId(respinse.getAttachment());
			userRelAuInfo.setMainUserId(mainUserLogin.getUserId());
			userRelAuInfo.setRelationUserId(userLogin.getUserId());
			userRelAuInfo.setCreateDateTime(new Date());
			userRelAuInfo.setUpdateDateTime(new Date());
			
			//更新安全等级表
			SecurityClassInfo security = new SecurityClassInfo();
			security.setUserId(userLogin.getUserId());
			String open = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_YES);
			security.setAuthSet(open);
			security.setUpdateDatetime(new Date());
			
			//更新打款认证表
			UserBankAuInfo mainBankAuInfo = manageServiceSupport.getLatestUserBankAuInfo(mainEnterpriseUser.getUserId()); 
			UserBankAuInfo relationBankAuInfo = manageServiceSupport.getLatestUserBankAuInfo(relationEnterpriseuser.getUserId());
			
			portalServiceSupport.enterpriseAuthRelation(relationEnterpriseuser,mainEnterpriseUser, userRelAuInfo,security,mainBankAuInfo,relationBankAuInfo);
			return ResponseUtils.buildSuccessResponse();
		}
		catch(Exception e){
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(),e);
			return response;
		}
	}
	
	/**
	 * 1120 查询实名认证的账号列表
	 * @author wangpeng
	 * @date 2014年9月2日下午 5:32:09
	 * @param useId 用户id 
	 * @return 登陆用户名（手机或邮箱），头像，关联账户列表
	 * @throws 1301 参数为空
	 * @throws 1302 用户不存在
	 * @since 1.2
	 */
	public AttachmentResponse<List<GetUserRelAuInfoResponse>> getUserRelAuInfoList(Long userId){
		try{
			//入参检查
			if(userId == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,
						new String[]{"userId"});
			}
			//用户检查
			UserLoginInfo user = new UserLoginInfo();
			user.setUserId(userId);
			user = userLoginDao.selectOne(user);
			if(user == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			}
			
			List<GetUserRelAuInfoResponse> list = null;
			//返回手机和邮箱
			String userTypePerson = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL);
			String userTypeEn = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_ENTERPRISE);
			if(user.getUserType().equals(userTypePerson)){
				PersonalUserQuery person = new PersonalUserQuery();
				person.setUserId(userId);
				list = userRelAuInfoDao.selectPersonalAuRel(person);
			}
			if(user.getUserType().equals(userTypeEn)){
				EnterpriseUserQuery enterprise = new EnterpriseUserQuery();
				enterprise.setUserId(userId);
				list = userRelAuInfoDao.selectEnterpriseAuRel(enterprise);
			}
			return ResponseUtils.buildSuccessResponse(list);
		}
		catch(Exception e){
			AttachmentResponse<List<GetUserRelAuInfoResponse>> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(),e);
			return response;
		}
	}
	
	/**
	 * 1121 查询打款金额接口 
	 * @author wangpeng
	 * @date 2014年9月16日下午2：21：09
	 * @param userId 用户id
	 * @return AttachmentResponse<BigDecimal>
	 * @since 0.1
	 */
	public AttachmentResponse<BigDecimal> getBandAmount(Long userId){
		try{
			if(userId == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,
						new String[]{"userId"});
			}
			UserBankAuInfo au = manageServiceSupport.getLatestUserBankAuInfo(userId);
			if(au == null){
				return null;
			}
			BigDecimal response = au.getBandAmount();
			return ResponseUtils.buildSuccessResponse(response);
		}
		catch(Exception e){
			AttachmentResponse<BigDecimal> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(),e);
			return response;
		}
	}
	
	/**
	 * 1122 通过userID查询用户状态、实名认证状态及各账户状态
	 * @author zhanwx
	 * @date 2014年9月19日 下午1:31:34
	 * @param userId
	 * @return AttachmentResponse<StatusInfo>
	 * @since 0.1
	 */
	public AttachmentResponse<StatusInfo> getStatus(Long userId){
		try{
			if(userId == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"userId"});
			}
			UserLoginInfo ul = new UserLoginInfo();
			ul.setUserId(userId);
			ul = userLoginDao.selectOne(ul);
			if(ul == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			}
			StatusInfo sts = new StatusInfo();
			//获取用户状态
			sts.setUserId(userId);
			sts.setUserStatus(ul.getUserStatus());
			sts.setUserStatusName(DictionaryUtils.getDisplayName(DictionaryKey.USER_STATUS,ul.getUserStatus()));
			//获取主账户状态
			String mainAccount = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_TYPE_MAIN);
			AccountInfoQuery query = new AccountInfoQuery();
			query.setUserId(userId);
			query.setAccountType(mainAccount);
			AccountInfo acc = accountInfoDao.selectOne(query);
			if(acc != null){
				sts.setMainAccountStatus(acc.getAccountStatus());
				sts.setMainAccountStatusName(DictionaryUtils.getDisplayName(DictionaryKey.ACCOUNT_STATUS, acc.getAccountStatus()));
			}
			//获取实名认证状态
			String person = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL);
			String enterprise = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_ENTERPRISE);
			if(ul.getUserType().equals(person)){
				PersonalUserQuery puq = new PersonalUserQuery();
				puq.setUserId(userId);
				PersonalUser pu = personalUserDao.selectOne(puq);
				if(pu != null){
					sts.setAuthStatus(pu.getAuthStatus());
					sts.setAuthStatusName(DictionaryUtils.getDisplayName(DictionaryKey.AUTH_STATUS, pu.getAuthStatus()));
				}
			}
			else if(ul.getUserType().equals(enterprise)){
				EnterpriseUserQuery euq = new EnterpriseUserQuery();
				euq.setUserId(userId);
				EnterpriseUser eu = enterpriseUserDao.selectOne(euq);
				if(eu != null){
					sts.setAuthStatus(eu.getAuthStatus());
					sts.setAuthStatusName(DictionaryUtils.getDisplayName(DictionaryKey.AUTH_STATUS, eu.getAuthStatus()));
				}
			}
			return ResponseUtils.buildSuccessResponse(sts);
		}
		catch(Exception e){
			AttachmentResponse<StatusInfo> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(),e);
			return response;
		}	
	}

	
	@Override
	public AttachmentResponse<Page<FinancialSummaryDay>> getFinancialSummaryDayList(
			FinancialSummaryDayQuery query) {
		try{
			//入参检查
			if (query == null || query.getPageSize() == null || query.getPageNo() == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[] {"PageSiz,PageNo"});
			}
			
				Page<FinancialSummaryDay> dayPage =financialSummaryDayDao.select(query);
				return ResponseUtils.buildSuccessResponse(dayPage);
			
			
		}
		catch(Exception e){
			AttachmentResponse<Page<FinancialSummaryDay>> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	
	
	@Override
	public AttachmentResponse<Page<FinancialSummaryMonth>> getFinancialSummaryMonthList(
			FinancialSummaryMonthQuery query) {
		try{
			//入参检查
			if (query == null || query.getPageSize() == null || query.getPageNo() == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[] {"PageSiz,PageNo"});
			}
			
				Page<FinancialSummaryMonth> dayPage =financialSummaryMonthDao.select(query);
				return ResponseUtils.buildSuccessResponse(dayPage);
		}
		catch(Exception e){
			AttachmentResponse<Page<FinancialSummaryMonth>> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}
	/**
	 * 1123	账户交易密码验证接口
	 * 1.入参检查
	 * 2.判断用户名是否纯数字
	 * 3.判断用户是否存在，不存在即返回提示
	 * 4.用户存在，判断密码是否匹配，返回结果
	 * 5.异常处理
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-2 下午6:42:11 
	 * @since 0.1
	 */
	@Override
	public AttachmentResponse<Boolean> verifyTradePwd(String userName,
			String tradePasswd) {
		try {
			//入参检查
			if (userName == null || tradePasswd == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"userName or tradePwd"});
			}
			UserLoginInfo user = new UserLoginInfo();
			//判断用户名是否纯数字
			if (isNumeric(userName)) {
				user.setUserMobile(userName);
			} else {
				user.setUserEmail(userName);
			}
			user = userLoginDao.selectOne(user);
			//判断用户是否存在
			if (user == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			} 
			else {
				String userTypePerson = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL);
				if(userTypePerson.equals(user.getUserType())){
					if(user.getReserve3()==null){
						user.setReserve3(0);
					}
					if(user.getReserve3()>=3){
						return ResponseUtils.buildFailureResponse(PortalConstants.TRADE_PWD_ERRORTIME_BEYOND, false);
					}
				}
				//密码加密
				tradePasswd = securityUtils.encryptMD5(tradePasswd, user.getCreateDatetime());
				//判断支付密码是否匹配
				if (user.getReserve1().equals(tradePasswd)) {
					//支付密码错误次数清零
					UserLoginInfo update = new UserLoginInfo();
					update.setUserId(user.getUserId());
					update.setReserve3(0);
					update.setUpdateDatetime(new Date());
					userLoginDao.update(update);
					return ResponseUtils.buildSuccessResponse(true);
				} 
				else {
					//增加支付密码错误次数
					UserLoginInfo update = new UserLoginInfo();
					update.setUserId(user.getUserId());
					update.setReserve3(user.getReserve3() == null? 1: user.getReserve3() + 1);
					update.setUpdateDatetime(new Date());
					userLoginDao.update(update);
					return ResponseUtils.buildFailureResponse(GlobalConstants.WRONG_TRADE_PWD, false);
				}
			}
		} 
		catch (Exception e) {
			AttachmentResponse<Boolean> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}
    
	/**
	 * 查询用户是否已经设置交易密码
	 * @author nyc
	 * @date 2015年1月8日 下午5:06:27
	 * @param userName
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public AttachmentResponse<Boolean> checkTradePwdExists(String userName) {
		try {
			//入参检查
			if (userName == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"userName"});
			}
			UserLoginInfo user = new UserLoginInfo();
			//判断用户名是否纯数字
			if (isNumeric(userName)) {
				user.setUserMobile(userName);
			} else {
				user.setUserEmail(userName);
			}
			user = userLoginDao.selectOne(user);
			//判断用户是否存在
			if (user == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST,false);
			} 
			else {
				if(user.getReserve1()!=null){
					return ResponseUtils.buildSuccessResponse(true);
				}else{
					return ResponseUtils.buildSuccessResponse(false);
				}
			}
		} 
		catch (Exception e) {
			AttachmentResponse<Boolean> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 银行卡认证结果写入（个人）
	 * @author nyc
	 * @date 2015年1月27日 下午3:42:56
	 * @param user
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public AttachmentResponse<String> personalBankCardAu(PersonalUser user) {
		try {
			if(user == null){ 
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"user"});
			}
			
			//查询用户是否存在
			PersonalUserQuery puq = new PersonalUserQuery();
			puq.setUserId(user.getUserId());
			PersonalUser pu = personalUserDao.selectOne(puq);
			if (null == pu){
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			}
			
			user.setAuthDate(new Date());
			user.setUpdateDatetime(new Date());
			user.setReserve1("1");//银行卡实名认证结果
			portalServiceSupport.personalAu(user,null);		
			return ResponseUtils.buildSuccessResponse();
		} catch (Exception e) {
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 银行卡认证是否通过查询（个人）
	 * @author nyc
	 * @date 2015年1月27日 下午4:12:50
	 * @param user
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public AttachmentResponse<Boolean> getPersonalBankCardAuStatus(
			PersonalUser user) {
		try {
			//入参检查
			if(user == null){ 
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"user"});
			}
			//查询用户是否存在
			PersonalUserQuery puq = new PersonalUserQuery();
			puq.setUserId(user.getUserId());
			PersonalUser pu = personalUserDao.selectOne(puq);
			if (null == pu){
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			}else{
				if(pu.getReserve1()!=null){
					if("1".equals(pu.getReserve1())){
						return ResponseUtils.buildSuccessResponse(true);
					}else{
						return ResponseUtils.buildSuccessResponse(false);
					}
				}else{
					return ResponseUtils.buildSuccessResponse(false);
				}
				
			}
		} 
		catch (Exception e) {
			AttachmentResponse<Boolean> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}


	
}

