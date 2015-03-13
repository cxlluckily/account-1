package com.cssweb.payment.account.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.bind.attachment.AttachmentUnmarshaller;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.cssweb.payment.account.constant.DictionaryKey;
import com.cssweb.payment.account.constant.GlobalConstants;
import com.cssweb.payment.account.constant.RiskConstants;
import com.cssweb.payment.account.dao.IAccountInfoDao;
import com.cssweb.payment.account.dao.IAccountMoneyChgDetailDao;
import com.cssweb.payment.account.dao.IAccountOperInfoDao;
import com.cssweb.payment.account.dao.IEnterpriseUserDao;
import com.cssweb.payment.account.dao.IPersonalUserDao;
import com.cssweb.payment.account.dao.IUserBankAuInfoDao;
import com.cssweb.payment.account.dao.IUserLoginDao;
import com.cssweb.payment.account.dao.IUserFunctionInfoDao;
import com.cssweb.payment.account.pojo.AccountInfo;
import com.cssweb.payment.account.pojo.AccountOperInfo;
import com.cssweb.payment.account.pojo.EnterpriseUser;
import com.cssweb.payment.account.pojo.PersonalUser;
import com.cssweb.payment.account.pojo.UserBankAuInfo;
import com.cssweb.payment.account.pojo.UserFunctionInfo;
import com.cssweb.payment.account.pojo.UserLoginInfo;
import com.cssweb.payment.account.sei.IRiskService;
import com.cssweb.payment.account.service.support.AccountServiceSupport;
import com.cssweb.payment.account.service.support.PortalServiceSupport;
import com.cssweb.payment.account.service.support.RiskServiceSupport;
import com.cssweb.payment.account.util.DictionaryUtils;
import com.cssweb.payment.account.vo.AccountInfoQuery;
import com.cssweb.payment.account.vo.AccountMoneyChgDetailQuery;
import com.cssweb.payment.account.vo.AccountOperInfoQuery;
import com.cssweb.payment.account.vo.EnterpriseUserQuery;
import com.cssweb.payment.account.vo.PersonalUserQuery;
import com.cssweb.payment.account.vo.RiskAccountInfo;
import com.cssweb.payment.account.vo.RiskAuthStatus;
import com.cssweb.payment.account.vo.GetFunctionStatusByAccountId;
import com.cssweb.payment.common.response.AttachmentResponse;
import com.cssweb.payment.common.util.ResponseUtils;
/**
 * 风控服务接口实现类
 * 
 * @author DuXiaohua
 * @version 1.0 (2014年7月24日 下午1:38:46)
 * @since 1.0
 */
@Component
public class RiskServiceImpl implements IRiskService{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private IUserLoginDao userLoginDao;
	@Resource
	private IAccountInfoDao accountInfoDao;
	@Resource
	private IUserBankAuInfoDao userBankAuInfoDao;
	@Resource
	private IAccountMoneyChgDetailDao accountMoneyChgDetailDao;
	@Resource
	private RiskServiceSupport riskServiceSupport;
	@Resource
	private AccountServiceSupport accountServiceSupport;
	@Resource
	private PortalServiceSupport portalServiceSupport;
	@Resource
	private IUserFunctionInfoDao userFunctionDao;
	@Resource 
	private IPersonalUserDao personDao;
	@Resource
	private IEnterpriseUserDao enterpriseDao;
	@Resource
	private IAccountOperInfoDao accountOperDao;
	
	
	@Override
	public AttachmentResponse<Integer> getLoginPwdWrong(Long userId) {
		try {
			AttachmentResponse<UserLoginInfo> userLoginResponse = portalServiceSupport.getUserLoginInfo(userId);
			if (!userLoginResponse.isSuccess()) {
				return ResponseUtils.buildFailureResponse(userLoginResponse.getReturnCode());
			}
			UserLoginInfo userLogin = userLoginResponse.getAttachment();
			Integer loginTimes = userLogin.getErrorLoginTimes();
			if(loginTimes == null){
				loginTimes = 0;
			}
			return ResponseUtils.buildSuccessResponse(loginTimes);
		} catch (Exception e) {
			AttachmentResponse<Integer> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	@Override
	public AttachmentResponse<Integer> getPayPwdWrong(Long userId) {
		try {
			AttachmentResponse<UserLoginInfo> userLoginResponse = portalServiceSupport.getUserLoginInfo(userId);
			if (!userLoginResponse.isSuccess()) {
				return ResponseUtils.buildFailureResponse(userLoginResponse.getReturnCode());
			}
			UserLoginInfo userLogin = userLoginResponse.getAttachment();
			Integer loginTimes = userLogin.getErrorPayTimes();
			if(loginTimes == null){
				loginTimes = 0;
			}
			return ResponseUtils.buildSuccessResponse(loginTimes);
		} catch (Exception e) {
			AttachmentResponse<Integer> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}
	
	@Override
	public AttachmentResponse setAccountStatus(Long accountId, String status) {
		try {
			if (StringUtils.isBlank(status)) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"status"});
			}
			AttachmentResponse<AccountInfo> response = accountServiceSupport.getAccountInfo(accountId);
			if (!response.isSuccess()) {
				return ResponseUtils.buildResponse(response);
			}
			AccountInfo accountInfo = new AccountInfo();
			accountInfo.setAccountId(accountId);
			accountInfo.setAccountStatus(status);
			accountInfoDao.update(accountInfo);
			return ResponseUtils.buildSuccessResponse();
		} catch (Exception e) {
			AttachmentResponse response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}
	
	@Override
	public AttachmentResponse<RiskAccountInfo> getAccountInfo(Long accountId, String tradeType,Integer ioFlag) {
		try {
			AttachmentResponse<AccountInfo> response = accountServiceSupport.getAccountInfo(accountId);
			if (!response.isSuccess()) {
				return ResponseUtils.buildResponse(response);
			}
			AccountInfo accountInfo = response.getAttachment();
			Date currentDate = new Date();
			// 计算当天、当月起始日期
			Date dayStart = DateUtils.truncate(currentDate, Calendar.DAY_OF_MONTH);
			Date dayEnd = DateUtils.addDays(dayStart, 1);
			Date monthStart = DateUtils.truncate(currentDate, Calendar.MONTH);
			Date monthEnd = DateUtils.addMonths(monthStart, 1);
			AccountMoneyChgDetailQuery query = new AccountMoneyChgDetailQuery();
			query.setAccountId(accountId);
			query.setTxnType(tradeType);
			query.setBeginDate(dayStart);
			query.setEndDate(dayEnd);
			AccountOperInfoQuery accountInfoQuery = new AccountOperInfoQuery();
			accountInfoQuery.setFromAccountId(accountId);
			accountInfoQuery.setToAccountId(accountId);
			accountInfoQuery.setTxnType(tradeType);
			accountInfoQuery.setBeginDate(dayStart);
			accountInfoQuery.setEndDate(dayEnd);
			RiskAccountInfo riskAccountInfo = new RiskAccountInfo();
			//当天交易次数
			Integer timesDay = null;
			//当天交易金额
			BigDecimal amountDay = null;
			//冻结
			String frozenTpye = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_FROZEN);
			//转账
			String transferTpye = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_TRANSFER);
			if(tradeType.equals(frozenTpye)){
				timesDay = accountOperDao.selectTimesDayCount(accountInfoQuery);
				amountDay = accountOperDao.selectAmountDayCount(accountInfoQuery);
			}else if(tradeType.equals(transferTpye)){
				query.setIoFlag(ioFlag);
				timesDay = accountMoneyChgDetailDao.selectCount(query);
				amountDay = accountMoneyChgDetailDao.selectTradeAmount(query);
			}else{
				timesDay = accountMoneyChgDetailDao.selectCount(query);
				amountDay = accountMoneyChgDetailDao.selectTradeAmount(query);
			}
			query.setBeginDate(monthStart);
			query.setEndDate(monthEnd);
			accountInfoQuery.setBeginDate(monthStart);
			accountInfoQuery.setEndDate(monthEnd);
			//当月交易次数
			Integer timesMonth = null;
			//当月交易金额
			BigDecimal amountMonth = null;
			if(tradeType.equals(frozenTpye)){
				timesMonth = accountOperDao.selectTimesDayCount(accountInfoQuery);
				amountMonth = accountOperDao.selectAmountDayCount(accountInfoQuery);
			}else if(tradeType.equals(transferTpye)){
				query.setIoFlag(ioFlag);
				timesMonth = accountMoneyChgDetailDao.selectCount(query);
				amountMonth = accountMoneyChgDetailDao.selectTradeAmount(query);
			}else{
				timesMonth = accountMoneyChgDetailDao.selectCount(query);
				amountMonth = accountMoneyChgDetailDao.selectTradeAmount(query);
			}
			
			//交易类型为退款
			String refund = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_REFUND);
			if(tradeType.equals(refund)){
				query.setIoFlag(-1);
				query.setBeginDate(dayStart);
				query.setEndDate(dayEnd);
				//当天接受退款的次数
				Integer timesRefundReciveDay = accountMoneyChgDetailDao.selectCount(query);
				//当天接受退款的金额
				BigDecimal amountRefundReciveDay = accountMoneyChgDetailDao.selectTradeAmount(query);
				riskAccountInfo.setTimesRefundReciveDay(timesRefundReciveDay);
				riskAccountInfo.setAmountRefundReciveDay(amountRefundReciveDay);
			}
			riskAccountInfo.setTimesDay(timesDay);
			riskAccountInfo.setAmountDay(amountDay);
			riskAccountInfo.setTimesMonth(timesMonth);
			riskAccountInfo.setAmountMonth(amountMonth);
			riskAccountInfo.setBalance(accountInfo.getAvailableBalance()
										.add(accountInfo.getFrozenAmount())
										.add(accountInfo.getNomentionAmount()));
			return ResponseUtils.buildSuccessResponse(riskAccountInfo);
		} catch (Exception e) {
			AttachmentResponse<RiskAccountInfo> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1.入参检查
	 * 2.查询用户登陆信息，得到用户类型
	 * 3.个人用户查询个人用户信息表，企业用户查询企业用户信息表
	 * 4.得到认证状态并返回
	 * 5.异常处理
	 * @author DuXiaohua
	 * @date 2014年7月24日 下午2:34:08
	 * @since 1.0
	 */
	@Override
	public AttachmentResponse<RiskAuthStatus> getAuthStatus(Long userId) {
		try {
			AttachmentResponse<UserLoginInfo> response = portalServiceSupport.getUserLoginInfo(userId);
			if (!response.isSuccess()) {
				return ResponseUtils.buildResponse(response);
			}
			UserLoginInfo loginInfo = response.getAttachment();
			String userType = loginInfo.getUserType();
			RiskAuthStatus riskAuthStatus = new RiskAuthStatus();
			riskAuthStatus.setUserType(userType);
			String personalUserType = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL);
			String enterpriseUserType = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_ENTERPRISE);
			if (userType.equals(personalUserType)) {//个人
				AttachmentResponse<PersonalUser> personalUserResponse = portalServiceSupport.getPersonalUser(userId);
				if (!response.isSuccess()) {
					return ResponseUtils.buildResponse(personalUserResponse);
				}
				PersonalUser personalUser = personalUserResponse.getAttachment();
				riskAuthStatus.setStatus(personalUser.getAuthStatus());
			} else if (userType.equals(enterpriseUserType)) {//企业
				AttachmentResponse<EnterpriseUser> enterpriseUserResponse = portalServiceSupport.getEnterpriseUser(userId);
				if (!response.isSuccess()) {
					return ResponseUtils.buildResponse(enterpriseUserResponse);
				}
				EnterpriseUser enterpriseUser = enterpriseUserResponse.getAttachment();
				riskAuthStatus.setStatus(enterpriseUser.getAuthStatus());
			}
			return ResponseUtils.buildSuccessResponse(riskAuthStatus);
		} catch (Exception e) {
			AttachmentResponse<RiskAuthStatus> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	@Override
	public AttachmentResponse freezeUser(Long userId,String reason) {
		try {
			AttachmentResponse<UserLoginInfo> response = portalServiceSupport.getUserLoginInfo(userId);
			if (!response.isSuccess()) {
				return ResponseUtils.buildResponse(response);
			}
			String statusFrozen = DictionaryUtils.getStringValue(DictionaryKey.USER_STATUS_FROZEN);
			UserLoginInfo loginInfo = new UserLoginInfo();
			loginInfo.setUserId(userId);
			loginInfo.setUserStatus(statusFrozen);
			loginInfo.setUpdateDatetime(new Date());
			loginInfo.setReserve1(reason);
			userLoginDao.update(loginInfo);
			return ResponseUtils.buildSuccessResponse();
		} catch (Exception e) {
			AttachmentResponse response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	@Override
	public AttachmentResponse unfreezeUser(Long userId,String reason) {
		try {
			AttachmentResponse<UserLoginInfo> response = portalServiceSupport.getUserLoginInfo(userId);
			if (!response.isSuccess()) {
				return ResponseUtils.buildResponse(response);
			}
			String statusNormal = DictionaryUtils.getStringValue(DictionaryKey.USER_STATUS_NORMAL);
			UserLoginInfo loginInfo = new UserLoginInfo();
			loginInfo.setUserId(userId);
			loginInfo.setUserStatus(statusNormal);
			loginInfo.setUpdateDatetime(new Date());
			loginInfo.setReserve1(reason);
			userLoginDao.update(loginInfo);
			return ResponseUtils.buildSuccessResponse();
		} catch (Exception e) {
			AttachmentResponse response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1.入参检查
	 * 2.查询打款次数
	 * 5.异常处理
	 * @author DuXiaohua
	 * @date 2014年7月24日 下午2:34:08
	 * @since 1.0
	 */
	@Override
	public AttachmentResponse<Integer> getBankTimes(Long userId) {
		try {
			UserBankAuInfo userBankAuInfo = new UserBankAuInfo();
			userBankAuInfo.setUserId(userId);
			List<UserBankAuInfo> list = userBankAuInfoDao.selectList(userBankAuInfo);
			int num = list.size();
			for(int i=0;i<list.size();i++){
				if(list.get(i).getBankTime()==null){
					num=num-1;
				}
			}
			return ResponseUtils.buildSuccessResponse(num);
		} catch (Exception e) {
			AttachmentResponse<Integer> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1.入参检查
	 * 2.查询打款成功的最新一条用户打款认证信息
	 * 4.返回打款成功后已过去的天数
	 * 5.异常处理
	 * @author DuXiaohua
	 * @date 2014年7月24日 下午2:34:08
	 * @since 1.0
	 */
	@Override
	public AttachmentResponse<Integer> getBankDays(Long userId) {
		try {
			AttachmentResponse<UserBankAuInfo> response = riskServiceSupport.getLatestUserBankAuInfo(userId);
			if(response.getReturnCode().equals(RiskConstants.AUTH_RECORD_NOT_EXIST)){
				return ResponseUtils.buildSuccessResponse(null);
			}else if (!response.isSuccess()) {
				return ResponseUtils.buildResponse(response);
			}
			UserBankAuInfo userBankAuInfo = response.getAttachment();
			Date bankTime = userBankAuInfo.getBankTime();
			long timeMillis = System.currentTimeMillis() - bankTime.getTime();
			Integer days = (int) (timeMillis / 1000 / 60 / 60 / 24);
			return ResponseUtils.buildSuccessResponse(days);
		} catch (Exception e) {
			AttachmentResponse<Integer> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
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

	/**
	 * @return userBankAuInfoDao
	 */
	public IUserBankAuInfoDao getUserBankAuInfoDao() {
		return userBankAuInfoDao;
	}

	/**
	 * @param userBankAuInfoDao
	 */
	public void setUserBankAuInfoDao(IUserBankAuInfoDao userBankAuInfoDao) {
		this.userBankAuInfoDao = userBankAuInfoDao;
	}

	/**
	 * @return riskServiceSupport
	 */
	public RiskServiceSupport getRiskServiceSupport() {
		return riskServiceSupport;
	}

	/**
	 * @param riskServiceSupport
	 */
	public void setRiskServiceSupport(RiskServiceSupport riskServiceSupport) {
		this.riskServiceSupport = riskServiceSupport;
	}

	/**
	 * @return accountServiceSupport
	 */
	public AccountServiceSupport getAccountServiceSupport() {
		return accountServiceSupport;
	}

	/**
	 * @param accountServiceSupport
	 */
	public void setAccountServiceSupport(AccountServiceSupport accountServiceSupport) {
		this.accountServiceSupport = accountServiceSupport;
	}

	/**
	 * @return portalServiceSupport
	 */
	public PortalServiceSupport getPortalServiceSupport() {
		return portalServiceSupport;
	}

	/**
	 * @param portalServiceSupport
	 */
	public void setPortalServiceSupport(PortalServiceSupport portalServiceSupport) {
		this.portalServiceSupport = portalServiceSupport;
	}
	
	/**
	 * 通过acccountId 获取用户短信提醒、余额支付功能开通状态和手机号
	 * 1.入参检查
	 * 2.
	 * 3.
	 * 4.
	 * 5.
	 * @param accountId
	 * @return
	 * @author zhanwx
	 * @date 2014年9月3日 上午11:15:54
	 * @since 0.1
	 */
	public AttachmentResponse<GetFunctionStatusByAccountId> getFunctionByAccountId(Long accountId){
		try{
			//入参检查
			if(accountId == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"accountId"});
			}
			//获取userId
			AccountInfoQuery acq = new AccountInfoQuery();
			acq.setAccountId(accountId);
			acq.setAccountType(DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_TYPE_MAIN));
			AccountInfo ac = accountInfoDao.selectOne(acq);
			if(ac == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ACCOUNT_NOT_EXIST);
			}
			Long userId = ac.getUserId();
			String userType = ac.getUserType();
			GetFunctionStatusByAccountId gf = new GetFunctionStatusByAccountId();
			String pay = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_BALANCE_PAY);
			String sms = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_SMS_REMIND);
			String open = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_YES);
			String clos = DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_NO);
			String person = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL);
			String enterprise = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_ENTERPRISE);
			//查用户功能表获取功能开通状态
			//查余额支付状态
			UserFunctionInfo ufp = new UserFunctionInfo();
			ufp.setUserId(userId);
			ufp.setFunctionId(pay);
			ufp = userFunctionDao.selectOne(ufp);
			if(ufp == null || ufp.getOpenStatus().equals(clos)){
				gf.setPayStatus(clos);
			}
			else{
				gf.setPayStatus(open);
			}
			//查短信提醒状态
			UserFunctionInfo ufs = new UserFunctionInfo();
			ufs.setUserId(userId);
			ufs.setFunctionId(sms);
			ufs = userFunctionDao.selectOne(ufs);
			if(ufs == null || ufs.getOpenStatus().equals(clos)){
				gf.setSmsStatus(clos);
			}
			else{
				gf.setSmsStatus(open);
			}
			if(userType.equals(person)){
				PersonalUserQuery puq = new PersonalUserQuery();
				puq.setUserId(userId);
				PersonalUser pu = personDao.selectOne(puq);
				if(pu == null){
					return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
				}
				else{
					gf.setPhoneNum(pu.getPhoneNumber());
				}
			}
			else if(userType.equals(enterprise)){
				EnterpriseUserQuery euq = new EnterpriseUserQuery();
				euq.setUserId(userId);
				EnterpriseUser eu = enterpriseDao.selectOne(euq);
				if(eu == null){
					return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
				}
				else{
					gf.setPhoneNum(eu.getManagerMobile());
				}
			}
			else{
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			}
			return ResponseUtils.buildSuccessResponse(gf);
		}
		catch(Exception e){
			AttachmentResponse<GetFunctionStatusByAccountId> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 获取交易密码失败次数
	 * @author nyc
	 * @date 2015年1月9日 下午3:38:12
	 * @param userId
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public AttachmentResponse<Integer> getTradePwdWrong(Long userId) {
		try {
			AttachmentResponse<UserLoginInfo> userLoginResponse = portalServiceSupport.getUserLoginInfo(userId);
			if (!userLoginResponse.isSuccess()) {
				return ResponseUtils.buildFailureResponse(userLoginResponse.getReturnCode());
			}
			UserLoginInfo userLogin = userLoginResponse.getAttachment();
			Integer tradeTimes = userLogin.getReserve3();
			if(tradeTimes == null){
				tradeTimes = 0;
			}
			return ResponseUtils.buildSuccessResponse(tradeTimes);
		} catch (Exception e) {
			AttachmentResponse<Integer> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

}
