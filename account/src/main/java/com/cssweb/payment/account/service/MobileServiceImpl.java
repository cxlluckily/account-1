/**
 * <p>Title: MobileServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: China Software & Service Company.</p>
 * @author 移动支付开发团队
 * @date 2014年7月23日
 * @version 1.0
 */   
package com.cssweb.payment.account.service;   

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cssweb.payment.account.constant.DictionaryKey;
import com.cssweb.payment.account.constant.GlobalConstants;
import com.cssweb.payment.account.constant.MobileConstants;
import com.cssweb.payment.account.dao.IAccountInfoDao;
import com.cssweb.payment.account.dao.IAccountMoneyChgDetailDao;
import com.cssweb.payment.account.dao.IAccountOperInfoDao;
import com.cssweb.payment.account.dao.IBankInfoDao;
import com.cssweb.payment.account.dao.IBusinessInfoDao;
import com.cssweb.payment.account.dao.IEnterpriseUserDao;
import com.cssweb.payment.account.dao.IPersonDataDao;
import com.cssweb.payment.account.dao.IPersonalUserDao;
import com.cssweb.payment.account.dao.IUserBandBankInfoDao;
import com.cssweb.payment.account.dao.IUserFunctionInfoDao;
import com.cssweb.payment.account.dao.IUserLoginDao;
import com.cssweb.payment.account.dao.IUserLoginLogDao;
import com.cssweb.payment.account.dao.IUserMobileAccountInfoDao;
import com.cssweb.payment.account.dao.IUserTsmMsgInfoDao;
import com.cssweb.payment.account.pojo.AccountInfo;
import com.cssweb.payment.account.pojo.AccountMoneyChgDetail;
import com.cssweb.payment.account.pojo.AccountOperDetail;
import com.cssweb.payment.account.pojo.AccountOperInfo;
import com.cssweb.payment.account.pojo.BankInfo;
import com.cssweb.payment.account.pojo.PersonData;
import com.cssweb.payment.account.pojo.PersonalUser;
import com.cssweb.payment.account.pojo.UserBandBankInfo;
import com.cssweb.payment.account.pojo.UserFunctionInfo;
import com.cssweb.payment.account.pojo.UserLoginInfo;
import com.cssweb.payment.account.pojo.UserLoginLog;
import com.cssweb.payment.account.pojo.UserMobileAccountInfo;
import com.cssweb.payment.account.pojo.UserTsmMsgInfo;
import com.cssweb.payment.account.sei.IMobileService;
import com.cssweb.payment.account.service.support.AccountServiceSupport;
import com.cssweb.payment.account.service.support.ManageServiceSupport;
import com.cssweb.payment.account.service.support.MobileServiceSupport;
import com.cssweb.payment.account.service.support.TradeServiceSupport;
import com.cssweb.payment.account.util.DictionaryUtils;
import com.cssweb.payment.account.util.MobileResponseUtils;
import com.cssweb.payment.account.util.SecurityUtils;
import com.cssweb.payment.account.vo.AccountInfoQuery;
import com.cssweb.payment.account.vo.AccountMoneyChgDetailQuery;
import com.cssweb.payment.account.vo.AppBindBankPara;
import com.cssweb.payment.account.vo.AppGetUserBindingBankListPara;
import com.cssweb.payment.account.vo.AppRegisterPara;
import com.cssweb.payment.account.vo.CreateMobileAccountResponse;
import com.cssweb.payment.account.vo.GetMobileAccountResponse;
import com.cssweb.payment.account.vo.PersonalUserQuery;
import com.cssweb.payment.account.vo.TopupToMobileAccountByDefaultResponse;
import com.cssweb.payment.common.provider.sei.IIdGeneratorService;
import com.cssweb.payment.common.response.AttachmentResponse;
import com.cssweb.payment.omp.sei.IDictionaryService;

/**
 * Title: MobileServiceImpl
 * Description: 
 * Company: China Software & Service Company
 * Project:payment-account
 * @author liLei
 * @version 1.0
 * @date 2014年8月15日 下午2:33:16
 * @see 
 */
@Component
public class MobileServiceImpl implements IMobileService {
	
	@Resource
	private AccountServiceSupport accountServiceSupport;
	
	@Resource
	public IPersonDataDao personDataDao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 检查手机码格式
	 * @author zhanwx
	 * @date 2014年8月12日 下午3:43:59
	 * @param tel
	 * @return boolean
	 * @since 0.1
	 * @see
	 */
	public boolean checkTel(String tel){
		boolean flag = Pattern.matches("^[1][3-8][0-9]{9}$",tel);
		if(flag)
			return true;
		else
			return false;
	}
	
	/**
	 * 检查邮箱格式
	 * @author zhanwx
	 * @date 2014年8月12日 下午4:06:25
	 * @param email
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public boolean checkMail(String email){
		String zz = "^[a-zA-Z0-9_\\-]+(\\.[_a-zA-Z0-9\\-]+)*@([_a-zA-Z0-9\\-]+\\.)+([a-z]{2}|aero|arpa|biz|com|coop|edu|gov|info|jobs|mil|museum|name|nato|net|org|pro|travel)$";
		boolean flag = Pattern.matches(zz,email);
		if(flag)
			return true;
		else
			return false;
	}
	
	@Resource
	private ManageServiceSupport manageServiceSupport;
	
	@Resource
	private IIdGeneratorService idGeneratorService;
	
	@Resource
	public IAccountInfoDao accountInfoDao;
	
	@Resource
	public IBusinessInfoDao  businessInfoDao;
	
	@Resource
	public IPersonalUserDao  personalUserDao;
	
	@Resource
	public IEnterpriseUserDao enterpriseUserDao; 
	
	@Resource
	public IBankInfoDao bankInfoDao;
	
	@Resource
	public IAccountMoneyChgDetailDao accountMoneyChgDetailDao;
	
	@Resource 
	public IUserLoginDao userLoginDao;
	
	@Resource
	public MobileServiceSupport mobileServiceSupport;
	
	@Resource 
	public IUserTsmMsgInfoDao userTsmMsgInfoDao;

	@Resource 
	public IUserBandBankInfoDao userBandBankInfoDao;
	
	@Resource
	public IUserMobileAccountInfoDao mobileAccountDao;
	
	@Resource
	public TradeServiceSupport tradeServiceSupport;
	
	@Resource
	private IDictionaryService dictionaryService;
	
	@Resource
	private IUserFunctionInfoDao userFunctionInfoDao;
	
	@Resource
	private IAccountOperInfoDao accountOperInfoDao;
	
	@Resource
	private IUserLoginLogDao userLoginLogDao;
	
	@Resource
	private SecurityUtils securityUtils;


	/* (non-Javadoc)
	 * 3.66 用户注册
	 * @author ZhaoXingwen
	 * @date 2014年7月23日 下午19:09:16
	 * @since 1.0
	 * @see com.cssweb.payment.account.sei.IMobileService#registe(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * Description:
	 * 1.在个人用户表中检查是否已存在
	 * 2.插入个人用户表
	 * 3.插入登录信息表
	 * 4.增加主账号
	 */
	/* (non-Javadoc)
	 * @see com.cssweb.payment.account.sei.IMobileService#registe(com.cssweb.payment.account.vo.AppRegisterPara)
	 * Description:
	 */
	@Override
	public AttachmentResponse<Long> registe(AppRegisterPara para) {
		try{
			if (para == null) {
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID,new String[]{"输入参数"});
			}
			if (null == para.getCountryCode()){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID,new String[]{"国籍"});
			}
			if (null == para.getUserName()){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID,new String[]{"用户名"});
			}
			if (null == para.getIdType()){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID,new String[]{"用户类型"});
			}
			if (null == para.getEmailAddress()||null == para.getPhoneNumber()){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID,new String[]{"电话和邮箱"});
			}
			if(!checkTel(para.getPhoneNumber()) || !checkMail(para.getEmailAddress())){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_TYPE_ERR,new String[]{"电话和邮箱"});
			}
				
			if (null == para.getLoginPasswd()||null == para.getPayPasswd()){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID,new String[]{"登录密码和支付密码"});
			}
			
			if (para.getLoginPasswd().equals(para.getPayPasswd())){
				logger.error("支付密码和登录密码不能相同");
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID,new String[]{"登录密码和支付密码"});
			}
			
			if (para.getPayPasswd().length() != 6){
				logger.error("支付密码为6位长度");
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID,new String[]{"支付密码"});
			}
			
			//获取当前时间
			Date currentDate =  new Date();
			currentDate = DateUtils.setMilliseconds(currentDate, 0);
			logger.debug("系统当前时间为"+String.format("%1$TY-%1$Tm-%1$Td %1$tH:%1$tM:%1$tS  :%1$tL", currentDate));
			//获取支付密码、登陆密码的密文
			String cipherPay = securityUtils.encryptMD5(para.getPayPasswd(), currentDate);
			String cipherLogin = securityUtils.encryptMD5(para.getLoginPasswd(), currentDate);
			para.setPayPasswd(cipherPay);
			para.setLoginPasswd(cipherLogin);
			
			PersonalUser personal = new PersonalUser();
			personal.setPhoneNumber(para.getPhoneNumber());
			personal.setEmailAddress(para.getEmailAddress());
			personal = personalUserDao.selectByEmailPhone(personal);
			if (null != personal){
				logger.error("此用户已注册,手机号或邮箱已存在");
				return MobileResponseUtils.buildFailureResponse(MobileConstants.ACCOUNT_ALREADY_EXIST);
			}
			PersonalUser per =  new PersonalUser();
			per.setPhoneNumber(para.getPhoneNumber());
			per.setEmailAddress(para.getEmailAddress());
			per.setCountryCode(para.getCountryCode());
			per.setRegType(DictionaryUtils.getStringValue(DictionaryKey.REG_TYPE_PHONE));
			per.setSource(DictionaryUtils.getStringValue(DictionaryKey.SOURCE_MOBILE));
			per.setAuthStatus(DictionaryUtils.getStringValue(DictionaryKey.AUTH_STATUS_NONE));
			per.setUserName(para.getUserName());
			per.setAuthCertType(para.getIdType());
			per.setAuthCertId(para.getIdNumber());
			AttachmentResponse<Long> respinse = idGeneratorService.generate(GlobalConstants.PATH_USERID);
			Long userId = respinse.getAttachment() ;
			per.setUserId(userId);
			per.setCreateDatetime(currentDate);
			per.setUpdateDatetime(currentDate);
			
			//向登录信息表中增加记录
			UserLoginInfo logInf = new UserLoginInfo();
			logInf.setUserId(userId);
			logInf.setUserType(DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL));
			logInf.setLoginPasswd(para.getLoginPasswd());
			logInf.setPayPasswd(para.getPayPasswd());
			logInf.setUserEmail(para.getEmailAddress());//登录表里存email phone 有何用？
			logInf.setUserMobile(para.getPhoneNumber());
			logInf.setUserStatus(DictionaryUtils.getStringValue(DictionaryKey.USER_STATUS_NORMAL));
			logInf.setLastLogininTime(currentDate);
			logInf.setErrorLoginTimes(0); // added by lll 20141022
			logInf.setErrorPayTimes(0);
			logInf.setCreateDatetime(currentDate);
			logInf.setUpdateDatetime(currentDate);
			
			
			//主账号
			AccountInfo accountInfo = new AccountInfo();
			String main = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_TYPE_MAIN);
			Long mainAccountId = accountServiceSupport.createCardNumber(main).getAttachment();
			accountInfo.setUserId(userId);
			accountInfo.setAccountId(mainAccountId);
			accountInfo.setAccountType(DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_TYPE_MAIN));
			accountInfo.setUserType(DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL));//01个人、02企业账户、03平台自有账户
			accountInfo.setAccountStatus(DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_STATUS_NORMAL));//账户状态：01：正常;02:冻结;03：销户
			accountInfo.setCurrencyType(DictionaryUtils.getStringValue(DictionaryKey.CURRENCY_TYPE_RMB));
			accountInfo.setFrozenAmount(BigDecimal.valueOf(0));
			accountInfo.setAvailableBalance(BigDecimal.valueOf(0)); 
			accountInfo.setNomentionAmount(BigDecimal.valueOf(0));
			accountInfo.setCreateDatetime(currentDate);
			accountInfo.setMac("MMKK22主账号");
			accountInfo.setUpdateDatetime(currentDate);
		
			mobileServiceSupport.AppRegister(per,logInf,accountInfo);
			AttachmentResponse<Long> response = MobileResponseUtils.buildSuccessResponse(userId);
			return response;
		}catch (Exception e) {
			AttachmentResponse<Long> r = MobileResponseUtils.buildExceptionResponse(null);
			logger.error(r.getReturnMsg(), e);
			return r;
		}
		
	}

	/* 3.67 获取绑定的银行卡列表
	 * @author ZhaoXingwen
	 * @date 2014年7月24日 下午13:09:16
	 * @param userId 
	 * @see com.cssweb.payment.account.sei.IMobileService#getUserBindingBankList(java.lang.Long)
	 * Description:
	 * 1.查询绑定银行表
	 * 2.返回结果
	 */
	@Override
	public AttachmentResponse<List<AppGetUserBindingBankListPara>> getUserBindingBankList(Long userId) {
		try{
			if (userId == null) {
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID,new String[]{"用户id"});
			}
			UserBandBankInfo userBandBankInfo = new UserBandBankInfo();
			userBandBankInfo.setUserId(userId);
			userBandBankInfo.setBandStatus(DictionaryUtils.getStringValue(DictionaryKey.BAND_STATUS_BINDING));
			List<AppGetUserBindingBankListPara>  ubb=  userBandBankInfoDao.selectBandCardFullInfo(userBandBankInfo);
//没找到记录也返回成功zxw20140825			
//			if (ubb==null || 0==ubb.size()){
//				logger.error("没有找到符合条件记录");
//				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_NOT_FOUND_RECORD);
//			}
			logger.debug("长度："+String.valueOf(ubb.size()));
			return MobileResponseUtils.buildSuccessResponse(ubb);			
		}catch (Exception e) {
				AttachmentResponse<List<AppGetUserBindingBankListPara>> r = MobileResponseUtils.buildExceptionResponse(null);
				logger.error(r.getReturnMsg(), e);
				return r;
		}
	}

	/* (non-Javadoc)
	 * 3.68  获取合作银行列表
	 * @author ZhaoXingwen
	 * @date 2014年7月24日 下午13:40:16
	 * @param 无 
	 * @see com.cssweb.payment.account.sei.IMobileService#getCooperativeBankList()
	 * Description:
	 * 1.查询合作银行表
	 * 2.返回查询结果
	 */
	@Override
	public AttachmentResponse<List<BankInfo>> getCooperativeBankList() {
		try{
			BankInfo bankInfo = new BankInfo();
			List<BankInfo>  BankInfoList = bankInfoDao.selectList(bankInfo);
			if (null == BankInfoList || BankInfoList.size() ==0){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_BANK_NAME_ERROR);
			}
			return MobileResponseUtils.buildSuccessResponse(BankInfoList);
		}catch (Exception e) {
			AttachmentResponse<List<BankInfo>> r = MobileResponseUtils.buildExceptionResponse();
			logger.error(r.getReturnMsg(), e);
			return r;
		}
	}

	/* (non-Javadoc)
	 * 3.69手机绑定银行卡接口
	 * @author ZhaoXingwen
	 * @date 2014年7月24日 下午14:40:16
	 * @param AppBindBankPara  para 绑定信息
	 * userId 用户ID
	 * @see com.cssweb.payment.account.sei.IMobileService#bindBank(java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * Description:
	 * 1.根据userid和accountno查找绑定信息表
	 * 2.如果已存在，且为解绑状态，则进行重新绑定；
	 * 3.如果不存在则根据行名找行号，插入新记录
	 */
	@Override
	public AttachmentResponse bindBank(AppBindBankPara  para){
		try{
			if (para == null) {
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID);
			}
			if (para.getUserId() == null || null == para.getBankAccountNo()){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID);
			}
			
			UserBandBankInfo ubb = new UserBandBankInfo();
			ubb.setUserId(para.getUserId());
			ubb.setBankAccountNo(para.getBankAccountNo());
			ubb = userBandBankInfoDao.selectOne(ubb);
			if (null != ubb){
				if (ubb.getBandStatus().equals(DictionaryUtils.getStringValue(DictionaryKey.BAND_STATUS_BINDING))){
					return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_BANK_CARD_ALREADY_BINDED);	
				}
				ubb.setBandStatus(DictionaryUtils.getStringValue(DictionaryKey.BAND_STATUS_BINDING));
				ubb.setUpdateDatetime(new Date());
				ubb.setId(ubb.getId());
				userBandBankInfoDao.update(ubb);
				return MobileResponseUtils.buildSuccessResponse();
			}
			//根据行名查行号
			BankInfo bankInfo = new BankInfo();
			bankInfo.setBankName(para.getBankName());
			bankInfo = bankInfoDao.selectLimitOne(bankInfo);
			if (null == bankInfo){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_BANK_NAME_ERROR);	
			}
			logger.debug(bankInfo.getBankName() + bankInfo.getBankAccount());
			//插入新记录
			AttachmentResponse<Long> respinse = idGeneratorService.generate(UserBandBankInfo.class.getName());
			UserBandBankInfo record = new UserBandBankInfo();
			record.setId(respinse.getAttachment());
			record.setUserId(para.getUserId());
			record.setBankAccountNo(para.getBankAccountNo());
			record.setBankId(bankInfo.getBankId());
			record.setBankCardType(para.getBankCardType());
			record.setHolderName(para.getHolderName());
			record.setExpirDate(para.getExpirDate());
			record.setCvcCode(para.getCvcCode());
			record.setBandStatus(DictionaryUtils.getStringValue(DictionaryKey.BAND_STATUS_BINDING));//绑定状态
			record.setUsetypeWithdraw(para.getUsetypeWithdraw());//增加绑定银行卡用途 
			record.setUsetypeShortcut(para.getUsetypeShortcut());//增加绑定银行卡用途 
			record.setBandCertType(para.getIdType());
			record.setAuthCertId(para.getIdNumber());// 加密保存
			record.setCreateDatetime(new Date());
			record.setUpdateDatetime(new Date());
			mobileServiceSupport.UserBandBankInfoIns(record);
			return MobileResponseUtils.buildSuccessResponse();
		}catch (Exception e) {
			AttachmentResponse  r = MobileResponseUtils.buildExceptionResponse();
			logger.error(r.getReturnMsg(), e);
			return r;
		}
	}

	/* (non-Javadoc)
	 * 3.70 解绑银行卡
	 * 
	 * @author ZhaoXingwen
	 * @date 2014年7月24日 下午15:10:48
	 * @param userId 用户id
	 * @param bankAccountNo 银行卡号
	 * @return AttachmentResponse 无附加对象
	 * @see com.cssweb.payment.account.sei.IMobileService#unbindBank(java.lang.Long, java.lang.String)
	 * Description:
	 */
	@Override
	public AttachmentResponse unbindBank(Long userId, String bankAccountNo) {
		try{
			if (userId == null) {
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"用户ID"});
			}
			if (bankAccountNo == null) {
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"银行卡号"});
			}
			UserBandBankInfo ubb = new UserBandBankInfo();
			ubb.setUserId(userId);
			ubb.setBankAccountNo(bankAccountNo);
			ubb.setBandStatus(DictionaryUtils.getStringValue(DictionaryKey.BAND_STATUS_BINDING));//银行卡绑定状态,01：已绑定;02：已解绑    从字典中取
			ubb=  userBandBankInfoDao.selectOne(ubb);
			if (null == ubb){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_CARD_NOT_BINDED);	//用户未绑定该银行卡
			}
			UserBandBankInfo newUbb = new UserBandBankInfo();
			newUbb.setBandStatus(DictionaryUtils.getStringValue(DictionaryKey.BAND_STATUS_UNBINDING));
			newUbb.setUserId(userId);
			newUbb.setBankAccountNo(bankAccountNo);
			newUbb.setUpdateDatetime(new Date());
			mobileServiceSupport.UserBandBankInfoUpd(newUbb);
			return MobileResponseUtils.buildSuccessResponse();
		}catch (Exception e) {
			AttachmentResponse  r = MobileResponseUtils.buildExceptionResponse();
			logger.error(r.getReturnMsg(), e);
			return r;
		}
	}

	/* (non-Javadoc)
	/**
	 * 3.71 获取账户信息
	 * 
	 * @author zhaoxingwen
	 * @date 2014年7月24日 下午16:19:00
	 * @param userId 用户id
	 * @return AttachmentResponse<AccountInfo> 附加对象为账户信息对象
	 * @since 1.0
	 * @see com.cssweb.payment.account.sei.IMobileService#getAccountInfo(java.lang.Long)
	 * Description:
	 */
	@Override
	public AttachmentResponse<AccountInfo> getAccountInfo(Long userId) {
		try{
			if (userId == null) {
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"用户ID"});
			}
			AccountInfoQuery accountQuery = new AccountInfoQuery();
			accountQuery.setUserId(userId);
			accountQuery.setAccountType(DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_TYPE_MAIN));
			AccountInfo accRes=  accountInfoDao.selectOne(accountQuery);
			if (null == accRes){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_ACCOUNT_NOT_EXIST);
			}
			return MobileResponseUtils.buildSuccessResponse(accRes);
		}catch (Exception e) {
			AttachmentResponse<AccountInfo>  r = MobileResponseUtils.buildExceptionResponse();
			logger.error(r.getReturnMsg(), e);
			return r;
		}
	}

	/* (non-Javadoc)
	 * 3.72 获取账户历史交易信息 - 闪客蜂
	 * 
	 * @author zhaoxingwen
	 * @date 2014年7月24日 下午16:36:22
	 * @param userId 用户id
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return AttachmentResponse<List<AccountMoneyChgDetail>> 附加对象为账户交易信息列表
	 * @since 1.0
	 * @see com.cssweb.payment.account.sei.IMobileService#getAccountTradingHistory(java.lang.Long, java.util.Date, java.util.Date)
	 * Description:
	 */
	@Override
	public AttachmentResponse<List<AccountMoneyChgDetail>> getAccountTradingHistory(
			Long userId, Date startDate, Date endDate) {
		try{
			if (userId == null) {
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"用户ID"});
			}
			if (startDate == null) {
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"开始日期"});
			}
			if (endDate == null) {
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"结束日期"});
			}
			AccountInfoQuery accountQuery = new AccountInfoQuery();
			accountQuery.setUserId(userId);
			accountQuery.setAccountType(DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_TYPE_MAIN));
			AccountInfo accRes=  accountInfoDao.selectOne(accountQuery);
			if (null == accRes){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_ACCOUNT_NOT_EXIST);
				
			}
			
			AccountMoneyChgDetailQuery query = new AccountMoneyChgDetailQuery();
			query.setAccountId(accRes.getAccountId());
			query.setBeginDate(startDate);
			query.setEndDate(endDate);
			List<AccountMoneyChgDetail>  Tradehistory= accountMoneyChgDetailDao.selectList(query);


			return MobileResponseUtils.buildSuccessResponse(Tradehistory);
		}catch (Exception e) {
			AttachmentResponse<List<AccountMoneyChgDetail>> r=MobileResponseUtils.buildFailureResponse( null);;
			logger.error(r.getReturnMsg(), e);
			return r;
		}
	}

	/* (non-Javadoc)
	 * 3.74 修改密码
	 * 
	 * @author ZhaoXingwen
	 * @date 2014年7月23日 上午1:24:09
	 * @param userId 用户ID
	 * @param passwdType 密码类型。登陆密码或交易密码
	 * @param passwd 密码
	 * @return AttachmentResponse 无附加对象
	 * @since 1.0
	 * @see com.cssweb.payment.account.sei.IMobileService#changePassword(java.lang.Long, java.lang.Integer, java.lang.String)
	 * Description:
	 */
	@Override
	public AttachmentResponse changePassword(Long userId, Integer passwdType,
			String passwd,String newPasswd) {
		try{
			if (userId == null) {
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"用户ID"});
			}
			if (passwdType == null) {
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"密码类型"});
			}
			if (passwd == null) {
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"原密码"});
			}
			if (newPasswd == null) {
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"新密码"});
			}
			
			UserLoginInfo logIn = new UserLoginInfo();
			logIn.setUserId(userId);
			logIn = userLoginDao.selectOne(logIn);			
			if (null == logIn){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_USER_NOT_EXIST);
			}
			
			String lginPwdType = DictionaryUtils.getStringValue(DictionaryKey.PWD_TYPE_LOGIN);
			String payPwdType  = DictionaryUtils.getStringValue(DictionaryKey.PWD_TYPE_PAY);
			logger.debug(lginPwdType);
			logger.debug(payPwdType);
			logger.debug(passwdType.toString());
			//密码加密
			passwd = securityUtils.encryptMD5(passwd, logIn.getCreateDatetime());
			newPasswd = securityUtils.encryptMD5(newPasswd, logIn.getCreateDatetime());
			
			if (lginPwdType.equals(passwdType.toString())){
				if (passwd.equals(logIn.getLoginPasswd())){
					if (newPasswd.equals(passwd)){
						logger.error("原登录密码和新登录密码不能相同");
						return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"新登录密码"});
					}
					if (newPasswd.equals(logIn.getPayPasswd())){
						logger.error("登录密码和支付密码不能相同");
						return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"新登录密码"});
					}
					logIn.setLoginPasswd(newPasswd);
					mobileServiceSupport.UserLoginInfoUpd(logIn,null);
					return MobileResponseUtils.buildSuccessResponse();					
				}
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PASSWORD_ERROR);
			}			
			else if (payPwdType.equals(passwdType.toString())){
				if (passwd.equals(logIn.getPayPasswd())){
					if (newPasswd.equals(passwd)){
						logger.error("原支付密码和新支付密码不能相同");
						return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"新支付密码"});						
					}
					if (newPasswd.equals(logIn.getLoginPasswd())){
						logger.error("支付密码和登录密码不能相同");
						return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"新支付密码"});
					}
					// added lll 20141027
					if (newPasswd.length() != 6){
						logger.error("支付密码为6位长度");
						return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID,new String[]{"支付密码"});
					}
					
					logIn.setPayPasswd(newPasswd);
					mobileServiceSupport.UserLoginInfoUpd(logIn,null);
					return MobileResponseUtils.buildSuccessResponse();
				}
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PASSWORD_ERROR);
			}
			else
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"密码类型"});

		}catch (Exception e) {
			AttachmentResponse r = MobileResponseUtils.buildExceptionResponse();
			logger.error(r.getReturnMsg(), e);
			return r;
		}
	}

	/* (non-Javadoc)
	 * 3.76 用户登陆
	 * 
	 * @author ZhaoXingwen
	 * @date 2014年7月25日 上午9:15:04
	 * @param loginName 登陆名，手机或者邮箱
	 * @param loginPasswd 登陆密码
	 * @return AttachmentResponse 附加对象为用户id
	 * @since 1.0
	 * @see com.cssweb.payment.account.sei.IMobileService#login(java.lang.String, java.lang.String)
	 * Description:
	 */
	@Override
	public AttachmentResponse<Long> login(String loginName, String loginPasswd) {
		try{
			if (loginName == null) {
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"登录名"});
			}
			if (loginPasswd == null) {
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"密码"});
			}
			UserLoginInfo logIn = new UserLoginInfo();
			if (mobileServiceSupport.isEmail(loginName)){
				logIn.setUserEmail(loginName);
			}
			else{
				logIn.setUserMobile(loginName);	
			}
			logIn = userLoginDao.selectOne(logIn);
			if (null == logIn){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_ACCOUNT_NOT_EXIST);
			}
			//密码加密
			loginPasswd = securityUtils.encryptMD5(loginPasswd, logIn.getCreateDatetime());
			
			if (loginPasswd.equals(logIn.getLoginPasswd())){
				logIn.setLastLogininTime(new Date());
				logIn.setErrorLoginTimes(0); //次数清零
				logIn.setUpdateDatetime(new Date());
				//往登录日志表插入记录
				UserLoginLog log = new UserLoginLog();
				AttachmentResponse<Long> logId = idGeneratorService.generate(UserLoginLog.class.getName());
				Date date = new Date();
				log.setId(logId.getAttachment());
				log.setLoginName(loginName);
				log.setUserId(logIn.getUserId());
				log.setSource(DictionaryUtils.getStringValue(DictionaryKey.SOURCE_MOBILE));
				log.setCreateTime(date);
				log.setLoginTime(date);
				log.setUpdateTime(date);
				mobileServiceSupport.UserLoginInfoUpd(logIn,log);				
			}
			else{
				Integer times = logIn.getErrorLoginTimes();
				times++;
				logIn.setErrorLoginTimes(times);
				logIn.setUpdateDatetime(new Date());
				mobileServiceSupport.UserLoginInfoUpd(logIn,null);
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PASSWORD_ERROR);
			}
			return MobileResponseUtils.buildSuccessResponse(logIn.getUserId());
		}
		catch (Exception e) {
			AttachmentResponse<Long> r = MobileResponseUtils.buildExceptionResponse(null);
			logger.error(r.getReturnMsg(), e);
			return r;
		}
	}

	/* (non-Javadoc)
	 * 3.77 创建移动支付帐户 - 闪客蜂现金账户
	 * 
	 * @author ZhaoXingwen
	 * @date 2014年7月25日 上午10:07:55
	 * @param userId 用户id
	 * @param secureElement sim卡唯一标识
	 * @param service 服务版本号
	 * @return AttachmentResponse<Long> 移动支付帐户 - 闪客蜂现金账户id
	 * @since 1.0
	 * @see com.cssweb.payment.account.sei.IMobileService#createMobileAccount(java.lang.Long, java.lang.String, java.lang.String)
	 * Description:
	 */
	@Override
	public AttachmentResponse<CreateMobileAccountResponse>   createMobileAccount(Long userId, String seIdName,String iccid,String cardUniqueData,
			String seIdGenericType,String seIdGenericId, String serviceId,String serviceVer){
		try{
			if (userId == null) {
				logger.error("登录名不能为空");
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"登录名"});
			}
			if (iccid == null) {
				logger.error("iccid不能为空");
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"SIM卡唯一标识"});
			}

			UserMobileAccountInfo mobileAccount = new UserMobileAccountInfo();
			mobileAccount.setIccid(iccid);
			mobileAccount =mobileAccountDao.selectOne(mobileAccount) ;
			if (null != mobileAccount){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.MOBILE_ALREADY_REGISTERED);
			}
			//生成预付卡账户
			String prepaid = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_TYPE_PREPAID);
			Long preAccountId = accountServiceSupport.createCardNumber(prepaid).getAttachment();
			AccountInfo accountInfoPre = new AccountInfo();
			accountInfoPre.setUserId(userId);
			accountInfoPre.setAccountId(preAccountId);
			accountInfoPre.setAccountType(DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_TYPE_PREPAID));
			accountInfoPre.setUserType(DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL));
			accountInfoPre.setAccountStatus(DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_STATUS_NORMAL));
			accountInfoPre.setCurrencyType(DictionaryUtils.getStringValue(DictionaryKey.CURRENCY_TYPE_RMB));
			accountInfoPre.setFrozenAmount(BigDecimal.valueOf(0));
			accountInfoPre.setAvailableBalance(BigDecimal.valueOf(0));
			accountInfoPre.setNomentionAmount(BigDecimal.valueOf(0));
			accountInfoPre.setCreateDatetime(new Date());
			accountInfoPre.setMac("MMKK22预付卡");// 
			accountInfoPre.setUpdateDatetime(new Date());
			
			//生产电子现金账户
			String cash = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_TYPE_CASH);
			Long accountIdCash = accountServiceSupport.createCardNumber(cash).getAttachment();
			AccountInfo accountInfoCash = new AccountInfo();
			accountInfoCash.setUserId(userId);
			accountInfoCash.setAccountId(accountIdCash);
			accountInfoCash.setAccountType(DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_TYPE_CASH));
			accountInfoCash.setUserType(DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL));
			accountInfoCash.setAccountStatus(DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_STATUS_NORMAL));
			accountInfoCash.setCurrencyType(DictionaryUtils.getStringValue(DictionaryKey.CURRENCY_TYPE_RMB));
			accountInfoCash.setFrozenAmount(BigDecimal.valueOf(0));
			accountInfoCash.setAvailableBalance(BigDecimal.valueOf(0));
			accountInfoCash.setNomentionAmount(BigDecimal.valueOf(0));
			accountInfoCash.setCreateDatetime(new Date());
			accountInfoCash.setMac("MMKK22电子现金");// 
			accountInfoCash.setUpdateDatetime(new Date());
			
			//向手机-移动支付账户对应关系表增加记录
			UserMobileAccountInfo mobileAccountInfo = new UserMobileAccountInfo();
			AttachmentResponse<Long> respinse = null;
			respinse = idGeneratorService.generate(UserMobileAccountInfo.class.getName());
			Long id = respinse.getAttachment();
			mobileAccountInfo.setId(id);
			mobileAccountInfo.setUserId(userId);
			mobileAccountInfo.setAccountId(accountIdCash);
			mobileAccountInfo.setPrepaidAccountId(preAccountId);
			mobileAccountInfo.setSeIdName(seIdName);
			mobileAccountInfo.setIccid(iccid);
			mobileAccountInfo.setCardUniqueData(cardUniqueData);
			mobileAccountInfo.setSeType(seIdGenericType);
			mobileAccountInfo.setSeId(seIdGenericId);
			mobileAccountInfo.setServiceId(serviceId);
			mobileAccountInfo.setServiceVersion(serviceVer);
			mobileAccountInfo.setStatus(DictionaryUtils.getStringValue(DictionaryKey.PERSONAL_DATA_STATUS_UNGET)); 
			mobileAccountInfo.setCreateDatetime(new Date());
			mobileAccountInfo.setUpdateDatetime(new Date());
			
			//给用户开通余额支付功能
			UserFunctionInfo uf1 = new UserFunctionInfo();
			respinse = idGeneratorService.generate(UserFunctionInfo.class.getName());
			uf1.setId(respinse.getAttachment());
			uf1.setUserId(userId);
			uf1.setFunctionId(DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_BALANCE_PAY));
			uf1.setOpenStatus(DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_YES));
			uf1.setCreateDatetime(new Date());
			uf1.setUpdateDatetime(new Date());
			
			UserFunctionInfo uf2 = new UserFunctionInfo();
			respinse = idGeneratorService.generate(UserFunctionInfo.class.getName());
			uf2.setId(respinse.getAttachment());
			uf2.setUserId(userId);
			uf2.setFunctionId(DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_SMS_REMIND));
			uf2.setOpenStatus(DictionaryUtils.getStringValue(DictionaryKey.FUNCTION_OPEN_YES));
			uf2.setCreateDatetime(new Date());
			uf2.setUpdateDatetime(new Date());
			
			mobileServiceSupport.AppCreateMobileAccount(accountInfoPre,accountInfoCash, mobileAccountInfo,uf1,uf2);
			CreateMobileAccountResponse attach = new CreateMobileAccountResponse();
			attach.setId(id);
			attach.setMobileAccountId(accountIdCash);
			attach.setPrepaidAccountId(preAccountId);
			
			AttachmentResponse<Long> sequenceAttach = idGeneratorService.generate(MobileConstants.PATH_METRO_CARDSEQ);
			if (null == sequenceAttach){
				logger.error("序号生成错误");
				return MobileResponseUtils.buildExceptionResponse();		
			}
			attach.setSequence(sequenceAttach.getAttachment());
			logger.debug("创建移动支付卡成功");
			return MobileResponseUtils.buildSuccessResponse(attach);
		}catch (Exception e) {
			//可能在未得到accountId时就异常了
			AttachmentResponse<CreateMobileAccountResponse> exceptRes = MobileResponseUtils.buildExceptionResponse();
			logger.error(exceptRes.getReturnMsg(), e);
			return exceptRes;
		}
	}

	/* (non-Javadoc)
	 * 3.78 获取移动支付帐户 - 闪客蜂现金账户
	 * 
	 * @author ZhaoXingwen
	 * @date 2014年7月23日 上午1:38:55
	 * @param secureElement sim卡唯一标识
	 * @param service 服务版本号
	 * @return AttachmentResponse<Long> 移动支付帐户 - 闪客蜂现金账户id
	 * @since 1.0
	 * @see com.cssweb.payment.account.sei.IMobileService#createMobileAccount(java.lang.String, java.lang.String)
	 * Description:
	 */
	@Override
	public AttachmentResponse<GetMobileAccountResponse> getMobileAccount(String iccid,
			String serviceId,String serviceVersion) {
		try{
			if (iccid == null) {
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"SIM卡唯一标识"});
			}
//			if (service == null) {
//				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"服务"});
//			}
			UserMobileAccountInfo mobileAccount = new UserMobileAccountInfo();
			mobileAccount.setIccid(iccid);
//			mobileAccount.setServiceId(serviceId);
//			mobileAccount.setServiceVersion(serviceVersion);
			mobileAccount =mobileAccountDao.selectOne(mobileAccount) ;
			if (null == mobileAccount){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_ACCOUNT_NOT_EXIST);
			}
			GetMobileAccountResponse res = new GetMobileAccountResponse() ;
			res.setAccount(mobileAccount.getAccountId());
			res.setUserId(mobileAccount.getUserId());
			res.setDpFilePath(mobileAccount.getPersonalFile());
			logger.debug("zzzzzzzzzres.getFilePath():"+res.getDpFilePath());
			return MobileResponseUtils.buildSuccessResponse(res);
		}catch (Exception e) {
			AttachmentResponse<GetMobileAccountResponse> r = MobileResponseUtils.buildExceptionResponse(null);
			logger.error(r.getReturnMsg(), e);
		return r;
	}
	}

	/* 
	 * 3.79 圈存-默认闪通宝到闪客蜂现金账户
	 * 根据闪通宝account_id查出
	 * @author ZhaoXingwen
	 * @date 2014年8月13日 下午2:14:10
	 * 入参增加sendid和messageid
	 */
	@Override
	public AttachmentResponse<TopupToMobileAccountByDefaultResponse> topupToMobileAccountByDefault(
			Long prepaidAccountId, BigDecimal beforeAmount,BigDecimal topupAmount,String payPasswd,String messageId, String senderId) {
		try{
			if (prepaidAccountId == null) {
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"移动账户ID"});
			}
			if (topupAmount == null) {
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"充值金额"});
			}
			if (payPasswd == null) {
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"支付密码"});
			}
			
//			AccountInfoQuery accountQuery = new AccountInfoQuery();
//	        String Acc = prepaidAccountId.toString();
//	        Acc=  Acc.substring(10, 18);
//	        accountQuery.setAccountId(Long.parseLong(Acc));//特殊处理
//			accountQuery.setAccountType(DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_TYPE_CASH));
//			AccountInfo accountInfo = accountInfoDao.selectAccByAcc(accountQuery);
//			if (null == accountInfo){
//				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_ACCOUNT_NOT_EXIST);
//			}
			UserMobileAccountInfo userMobileAccountInfo = new UserMobileAccountInfo();
			userMobileAccountInfo.setPrepaidAccountId(prepaidAccountId);
			userMobileAccountInfo = mobileAccountDao.selectOne(userMobileAccountInfo);
			if (null == userMobileAccountInfo){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_ACCOUNT_NOT_EXIST);
			}
			
			Long mobileAccountId = userMobileAccountInfo.getAccountId();
			logger.debug("new mobileAccountId:"+mobileAccountId);

			AttachmentResponse<Boolean> Res = verifyPayPasswd(userMobileAccountInfo.getUserId(),payPasswd);
			if (!Res.isSuccess()){
				logger.error("支付密码校验失败");
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_CODE_EXCEPTION);
			}
			else
			{
				if (false == Res.getAttachment()){
					logger.error("支付密码校验错误");
					return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PASSWORD_ERROR);					
				}
			}
			
			AccountInfoQuery shankPhoneAccInfoQuery = new AccountInfoQuery();
			shankPhoneAccInfoQuery.setUserId(userMobileAccountInfo.getUserId());
			shankPhoneAccInfoQuery.setAccountType(DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_TYPE_MAIN));
			AccountInfo shankPhoneAccInfo = accountInfoDao.selectOne(shankPhoneAccInfoQuery);
			if (null == shankPhoneAccInfo){
				logger.equals("账号不存在");
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_ACCOUNT_NOT_EXIST);
			}
			
			if (shankPhoneAccInfo.getAvailableBalance().compareTo(topupAmount)<0){
				logger.equals("余额不足");
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_NOT_SUFFICIENT_FUND);
			}
			
			//向账户操作流水表ACM_ACCOUNT_OPER_INFO 里插入记录
			AttachmentResponse<Long> respinse = idGeneratorService.generate(GlobalConstants.PATH_ACCOUNTSN);
			Long accountSN= respinse.getAttachment();
			AccountOperInfo operInfo =  new AccountOperInfo();
			operInfo.setAccountSn(accountSN);
			operInfo.setInterfaceName("topupToMobileAccountByDefault");
			
			operInfo.setSource(DictionaryUtils.getStringValue(DictionaryKey.SOURCE_MOBILE));//PC：01；Mobile：02
			operInfo.setFromAccountId(shankPhoneAccInfo.getAccountId());
			operInfo.setToAccountId(mobileAccountId);
			operInfo.setAmount(topupAmount);
			operInfo.setFee(BigDecimal.valueOf(0));
			operInfo.setMemo("圈存");
			//operInfo.setExternalOrderNo(messageId);
			
			operInfo.setTxnType(DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_TOPUP));
			operInfo.setStatus(DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL));
			operInfo.setReverseFlag(DictionaryUtils.getStringValue(DictionaryKey.REVERSE_FLAG_NO));
			operInfo.setUpdateDateTime(new Date());
			
//			//向TSM消息表中插入数据
			UserMobileAccountInfo mobileAccount = new UserMobileAccountInfo();
			mobileAccount.setAccountId(mobileAccountId);
			mobileAccount =mobileAccountDao.selectOne(mobileAccount) ;
			if (null == mobileAccount){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_SE_INFO_NULL);
			}
			UserTsmMsgInfo userTsmMsg = new UserTsmMsgInfo();
			AttachmentResponse<Long> resId = idGeneratorService.generate(UserTsmMsgInfo.class.getName());
			userTsmMsg.setId(resId.getAttachment());
			userTsmMsg.setMessageId(messageId);
			userTsmMsg.setSenderId(senderId);
			userTsmMsg.setServiceId(mobileAccount.getServiceId());
			userTsmMsg.setServiceVersion(mobileAccount.getServiceVersion());
			userTsmMsg.setIccid(mobileAccount.getIccid());
			java.text.DecimalFormat format = new java.text.DecimalFormat("000000000000");
            String bfv = format.format(beforeAmount.multiply(BigDecimal.valueOf(100)));
            String afv = format.format(beforeAmount.add(topupAmount).multiply(BigDecimal.valueOf(100))); 
            logger.debug("bffffffffv:"+bfv);
            logger.debug("affffffffv:"+afv);
			userTsmMsg.setBeforeValue(bfv); 
			userTsmMsg.setAfterValue(afv);			
			userTsmMsg.setSeId(mobileAccount.getSeId());
			userTsmMsg.setSeType(mobileAccount.getSeType());
			userTsmMsg.setAction(MobileConstants.TSM_ACTION_RECHARGE);
			userTsmMsg.setAccountSn(accountSN);
			userTsmMsg.setCreateDatetime(new Date());
			userTsmMsg.setUpdateDatetime(new Date());
			mobileServiceSupport.ApptopupToMobileAccountByDefault(
					operInfo,
					userTsmMsg
					);
			
			TopupToMobileAccountByDefaultResponse topupRes = new TopupToMobileAccountByDefaultResponse();   
			topupRes.setSEIdentifierId(mobileAccount.getSeId());
			topupRes.setSEIdentifierType(mobileAccount.getSeType());
			topupRes.setServiceId(mobileAccount.getServiceId());
			topupRes.setServiceVersion(mobileAccount.getServiceVersion());
			topupRes.setSeIdName(mobileAccount.getSeIdName());
			topupRes.setIccid(mobileAccount.getIccid());
			return MobileResponseUtils.buildSuccessResponse(topupRes);
		}
		catch(Exception e){
			AttachmentResponse r = MobileResponseUtils.buildExceptionResponse();
			logger.error(r.getReturnMsg(), e);
			return r ;
		}
	}
	
	/* (non-Javadoc)
	 * 验证支付密码
	 * 
	 * @author ZhaoXingwen
	 * @date 2014年7月25日 下午19:19:36
	 * @param userId 用户id
	 * @param payPasswd 支付密码
	 * @return AttachmentResponse<Boolean> 附加对象为支付密码是否正确
	 * @since 1.0
	 * @see com.cssweb.payment.account.sei.IMobileService#verifyPayPasswd(java.lang.Long, java.lang.String)
	 * Description:
	 * 1.入参非空检验
	 * 2.查表、比较
	 */
	@Override
	public AttachmentResponse<Boolean> verifyPayPasswd(Long userId,
			String payPasswd) {
		try{
			if (userId == null) {
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"移动账户ID"});
			}
			if (payPasswd == null) {
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"支付密码"});
			}
			UserLoginInfo logIn = new UserLoginInfo();
			logIn.setUserId(userId);
			logIn = userLoginDao.selectOne(logIn);			
			if (null == logIn){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_ACCOUNT_NOT_EXIST,false);
			}
			payPasswd = securityUtils.encryptMD5(payPasswd, logIn.getCreateDatetime());
			
			if (logIn.getPayPasswd().equals(payPasswd)){
				return MobileResponseUtils.buildSuccessResponse(true);
			}
			return MobileResponseUtils.buildSuccessResponse(false);
		}catch(Exception e){
			AttachmentResponse<Boolean> r = MobileResponseUtils.buildExceptionResponse();
			logger.error(r.getReturnMsg(), e);
			return r ;
		}
	}

	/* 获取移动支付帐户交易流水接口
	 * (non-Javadoc)
	 * @see com.cssweb.payment.account.sei.IMobileService#getMobileAccountTradingHistory(java.lang.Long, java.util.Date, java.util.Date)
	 * Description:
	 * 1、入参非空判断
	 * 2、查AccountMoneyChgDetail数据表，返回结果
	 * 3、异常处理
	 */
	@Override
	public AttachmentResponse<List<AccountMoneyChgDetail>> getMobileAccountTradingHistory(
			Long mobileAccountId,String tranType, Date startDate, Date endDate) {
		try {
			if(mobileAccountId == null){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID,new String[]{"移动账户ID"});
			}
			if (startDate == null){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID,new String[]{"起始日期"});
			}
			if (endDate == null){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID,new String[]{"截止日期"});
			}
				
			AccountMoneyChgDetailQuery param = new AccountMoneyChgDetailQuery();
			param.setAccountId(mobileAccountId);
			param.setBeginDate(startDate);
			param.setEndDate(endDate);
			param.setTxnType(tranType); 
			List<AccountMoneyChgDetail> resultList = new ArrayList<AccountMoneyChgDetail>();
			//查表操作
			resultList = accountMoneyChgDetailDao.selectList(param);
			if(resultList == null ||resultList.size() ==0 ){
				//没有交易记录，返回空表，但是查询成功
//				return MobileResponseUtils.buildSuccessResponse((List<AccountMoneyChgDetail>) new ArrayList<AccountMoneyChgDetail>());
//				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_NOT_FOUND_RECORD);
				return MobileResponseUtils.buildSuccessResponse(null);
			}
			return MobileResponseUtils.buildSuccessResponse(resultList);
			
		} catch (Exception e) {
			AttachmentResponse<List<AccountMoneyChgDetail>> response = MobileResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/* (non-Javadoc)
	 * 3.81 服务状态变更通知
	 * @see com.cssweb.payment.account.sei.IMobileService#serviceStateChangeNotification(java.lang.String, java.lang.String, java.lang.String)
	 * Description:
	 * 1、入参非空判断
	 * 2、检查secureElement对应的账户是否存在
	 * 3、更新参数
	 * 4、异常处理
	 * 
	 */
	@Override
	public AttachmentResponse serviceStateChangeNotification(
			String secureElement, String newService, String newStatus) {
		try {
			if(secureElement == null){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID,new String[]{"SIM卡唯一标识"});
			}
			if (newService == null ){
				// 不再判断
			}
			if (newStatus == null){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID,new String[]{"新状态"});
			}
			
			UserMobileAccountInfo userMobileAccountInfo = new UserMobileAccountInfo();
			userMobileAccountInfo.setIccid(secureElement);
//			查找账户是否存在
			userMobileAccountInfo = mobileAccountDao.selectOne(userMobileAccountInfo);
			if(userMobileAccountInfo == null){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_ACCOUNT_NOT_EXIST);
			}
			
			//userMobileAccountInfo.setService(newService);zzz
			userMobileAccountInfo.setStatus(newStatus);
			userMobileAccountInfo.setUpdateDatetime(new Date());
//			更新表
			mobileServiceSupport.serviceStateChangeNotification(userMobileAccountInfo);
			return MobileResponseUtils.buildSuccessResponse();
		} catch (Exception e) {
			AttachmentResponse response = MobileResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/* (non-Javadoc)
	 * 3.82 检查登陆用户名是否存在
	 * @see com.cssweb.payment.account.sei.IMobileService#isLoginNameExist(java.lang.String)
	 * Description:
	 * 1、入参非空判断
	 * 2、判断是否为手机号或者邮箱
	 * 3、查表
	 * 4、异常处理
	 * -------------
	 * 修改 20140726
	 */
	@Override
	public AttachmentResponse<Boolean> isLoginNameExist(String loginName) {
		try {
			if(loginName == null){
				return MobileResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"登陆用户名"});
			}
			UserLoginInfo userLoginInfo = null;
			userLoginInfo = new UserLoginInfo();
//			先检查用户名是否为手机号
			if (MobileServiceSupport.isNumeric(loginName)){
				userLoginInfo.setUserMobile(loginName);
				
			}else{
				userLoginInfo.setUserEmail(loginName);
			}
			userLoginInfo = userLoginDao.selectOne(userLoginInfo);
		
			if(userLoginInfo != null){
				return MobileResponseUtils.buildSuccessResponse(true);
			}else{
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_USER_NOT_EXIST,false);
			}
		} catch (Exception e) {
			AttachmentResponse<Boolean> response =MobileResponseUtils.buildExceptionResponse(null);
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/* (non-Javadoc)
	 * 3.83 获取用户信息
	 * @see com.cssweb.payment.account.sei.IMobileService#getUserInfo(java.lang.Long)
	 * Description:
	 * 1、入参非空检查
	 * 2、检查personalUser表中是否存在，有则返回结果，无则返回无用户错误
	 * 3、异常处理
	 * ---------------------------
	 */
	@Override
	public AttachmentResponse<PersonalUser> getUserInfo(Long userId) {
		try {
			if(userId == null){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID ,new String[]{"用户ID"});
			}
			PersonalUserQuery personalUser = new PersonalUserQuery();
			personalUser.setUserId(userId);
//			查表 personalUserDao
			PersonalUser result = personalUserDao.selectOne(personalUser);
//没找到返回成功			
//			if (result == null){
//				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_ACCOUNT_NOT_EXIST);
//			}
			return MobileResponseUtils.buildSuccessResponse(result);
		} catch (Exception e) {
			AttachmentResponse<PersonalUser> response = MobileResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/* (non-Javadoc)
	 * 3.84 更新用户信息
	 * @see com.cssweb.payment.account.sei.IMobileService#modifyUserInfo(com.cssweb.payment.account.pojo.PersonalUser)
	 * Description:
	 * 1、入参非空检查
	 * 2、设置参数
	 * 3、更新UserLoginInfo表
	 * 4、异常处理
	 */
	@Override
	public AttachmentResponse modifyUserInfo(PersonalUser user) {
		try{
			if(user == null){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_USER_NOT_EXIST);
			}
			if (null == user.getUserId()){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_USER_NOT_EXIST);
			}
			PersonalUserQuery query = new PersonalUserQuery();
			query.setUserId(user.getUserId());
			PersonalUser pu = personalUserDao.selectOne(query);
			if (null == pu){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_USER_NOT_EXIST);
			}
			user.setUpdateDatetime(new Date());
			
			UserLoginInfo loginInfo = new UserLoginInfo();
			loginInfo.setUpdateDatetime(user.getUpdateDatetime());
			loginInfo.setUserId(user.getUserId());
			loginInfo.setUserEmail(user.getEmailAddress());
			loginInfo.setUserMobile(user.getPhoneNumber());
//			更新操作
			mobileServiceSupport.modifyUserInfo(user,loginInfo);
			return MobileResponseUtils.buildSuccessResponse();
		}catch(Exception e){
			AttachmentResponse response = MobileResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/* (non-Javadoc)
	 *  3.85 服务更新通知反馈
	 * @see com.cssweb.payment.account.sei.IMobileService#modifyTsmMsgInfo(com.cssweb.payment.account.pojo.UserTsmMsgInfo)
	 * Description:
	 * 1、入参非空检查
	 * 2、更新信息
	 * 3、异常处理
	 * */
	@Override
	public AttachmentResponse modifyTsmMsgInfo(UserTsmMsgInfo msgInfo) {
		try {
			if(msgInfo == null ){
				return MobileResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"消息信息"});
			}
//			修改信息
			mobileServiceSupport.modifyTsmMsgInfo(msgInfo);
			return MobileResponseUtils.buildSuccessResponse();
		} catch (Exception e) {
			AttachmentResponse response = MobileResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/* (non-Javadoc)
	 * 是否需要密码
	 * 
	 * @author ZhaoXingwen
	 * @date 2014年8月12日 下午19:19:36
	 * @param account
	 * @return AttachmentResponse<Boolean> 附加对象为支付密码是否正确
	 * @since 1.0
	 */
	@Override
	public AttachmentResponse<Boolean> isBankCardPasswordRequired(String accountId){
		try{
			if (null == accountId){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID, new String[]{"账号"});
			}
			UserBandBankInfo userBandBankInfo = new UserBandBankInfo();
			userBandBankInfo.setBankAccountNo(accountId);
			userBandBankInfo = userBandBankInfoDao.selectOne(userBandBankInfo);
			if (null == userBandBankInfo){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_ACCOUNT_NOT_EXIST);
			}
//			if ("0".equals(userBandBankInfo.getPasswordRequire())){
//				return MobileResponseUtils.buildSuccessResponse(true);	
//			}
//			else
			return MobileResponseUtils.buildSuccessResponse(false);//此接口总返回false
		} catch (Exception e) {
			AttachmentResponse<Boolean> response = MobileResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}
	
	/* (non-Javadoc)
	 * 收到requestDataUpdate应答后，后台继续处理圈存交易。
	 * 
	 * @author ZhaoXingwen
	 * @date 2014年8月25日 下午19:19:36
	 * @param account
	 * @return AttachmentResponse<String>
	 * @since 1.0
	 */
	@Override
	public AttachmentResponse<String> tsmRequestDataUpdateCallBack(UserTsmMsgInfo userTsmMsgInfo){
		try{
			if (userTsmMsgInfo == null){
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_PARAM_INVALID,new String[]{"userTsmMsgInfo"});
			}
			
			String messageId = userTsmMsgInfo.getMessageId();
			userTsmMsgInfo.setUpdateDatetime(new Date());

			//更新acm_account_oper_info表状态
			UserTsmMsgInfo tsmMsgInfo = new UserTsmMsgInfo(); 
			tsmMsgInfo.setMessageId(messageId);
			tsmMsgInfo = userTsmMsgInfoDao.selectOne(tsmMsgInfo);
			Long accountSn = null;
			if (null == tsmMsgInfo){
				logger.error("根据accountSn未能找到原流水");
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_NOT_FOUND_RECORD);
			}
			
			//TSM返回失败的反馈
			if (false == userTsmMsgInfo.getFunctionExecutionStatus().equals(MobileConstants.TSM_RETURN_SUCCESS)){
				logger.debug("处理失败的反馈");
				userTsmMsgInfoDao.update(userTsmMsgInfo);
				return MobileResponseUtils.buildSuccessResponse(messageId);
			}
			
			logger.error("accountSn:"+accountSn);
			accountSn = tsmMsgInfo.getAccountSn();
			
			//更改流水表
			AccountOperInfo accountOperInfo = new AccountOperInfo();
			accountOperInfo.setAccountSn(accountSn);
			accountOperInfo.setStatus(DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_DONE));
			accountOperInfo.setUpdateDateTime(new Date()); 
			
			//查找原流水
			AccountOperInfo  accountOperInfoSelect =new AccountOperInfo();
			accountOperInfoSelect.setAccountSn(accountSn);
			accountOperInfoSelect =  accountOperInfoDao.selectOne(accountOperInfoSelect);
			if (null == accountOperInfoSelect){
				logger.error("没有找到accountSN对应的交易记录，严重错误");
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_NOT_FOUND_RECORD);
			}
			//更改main金额
			AccountInfoQuery accountQuery = new AccountInfoQuery();
			accountQuery.setAccountId(accountOperInfoSelect.getFromAccountId());
			AccountInfo mainAccountInfo = new  AccountInfo();
			mainAccountInfo = accountInfoDao.selectOne(accountQuery);
			if (null == mainAccountInfo){
				logger.error("没有找到main accountId对应的交易记录，严重错误");
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_ACCOUNT_NOT_EXIST);					
			}
			mainAccountInfo.setAvailableBalance(mainAccountInfo.getAvailableBalance().subtract(accountOperInfoSelect.getAmount()));
			mainAccountInfo.setUpdateDatetime(new Date());
			
			//更改cash金额
			AccountInfoQuery accountCashQuery = new AccountInfoQuery();
			accountCashQuery.setAccountId(accountOperInfoSelect.getToAccountId());
			AccountInfo cashAccountInfo = new  AccountInfo();
			cashAccountInfo = accountInfoDao.selectOne(accountCashQuery);
			if (null == cashAccountInfo){
				logger.error("没有找到cash accountId对应的交易记录，严重错误");
				return MobileResponseUtils.buildFailureResponse(MobileConstants.RESPONSE_ACCOUNT_NOT_EXIST);					
			}
			BigDecimal oldBalance = cashAccountInfo.getAvailableBalance().add(cashAccountInfo.getNomentionAmount().add(cashAccountInfo.getFrozenAmount()));
			cashAccountInfo.setAvailableBalance(cashAccountInfo.getAvailableBalance().add(accountOperInfoSelect.getAmount()));
			cashAccountInfo.setUpdateDatetime(new Date());

			//插入金明细
			AccountOperDetail operDetailIn = new AccountOperDetail();
			AttachmentResponse<Long> idGenIn = idGeneratorService.generate(AccountOperDetail.class.getName());
			operDetailIn.setId(idGenIn.getAttachment());
			operDetailIn.setAccountSn(accountSn);
			operDetailIn.setIoFlag(DictionaryUtils.getIntegerValue(DictionaryKey.IO_FLAG_I));
			operDetailIn.setAccountId(accountOperInfoSelect.getToAccountId());
			operDetailIn.setAccountPosition(DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE));
			operDetailIn.setAmount(accountOperInfoSelect.getAmount());
			operDetailIn.setStatus(DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL));//记录状态.默认为00正常，01已处理。为运营管理平台的账目调整预留
			operDetailIn.setUpdateDatetime(new Date());	
			
			//插入出金明细
			AccountOperDetail operDetailOut = new AccountOperDetail();
			AttachmentResponse<Long> idGen = idGeneratorService.generate(AccountOperDetail.class.getName());
			operDetailOut.setId(idGen.getAttachment());
			operDetailOut.setAccountSn(accountSn);
			operDetailOut.setIoFlag(DictionaryUtils.getIntegerValue(DictionaryKey.IO_FLAG_O)); 
			operDetailOut.setAccountId(accountOperInfoSelect.getFromAccountId());
			operDetailOut.setAccountPosition(DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE));
			operDetailOut.setAmount(accountOperInfoSelect.getAmount());
			operDetailOut.setStatus(DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL));
			operDetailOut.setUpdateDatetime(new Date());

			//向账户资金变动明细表 ACM_ACCOUNT_MONEY_CHG_DETAIL插入数据
			AccountMoneyChgDetail moneyChgDetail = new AccountMoneyChgDetail() ;
			AttachmentResponse<Long> chgDetailId = idGeneratorService.generate(AccountMoneyChgDetail.class.getName());
			moneyChgDetail.setId(chgDetailId.getAttachment());
			moneyChgDetail.setAccountSn(accountSn);
			moneyChgDetail.setUserId(cashAccountInfo.getUserId());
			moneyChgDetail.setAccountId(cashAccountInfo.getAccountId());
			moneyChgDetail.setAccountType(cashAccountInfo.getAccountType());
			moneyChgDetail.setChgAccount(accountOperInfoSelect.getAmount());
			moneyChgDetail.setOldBalance(oldBalance);
			moneyChgDetail.setBalance(cashAccountInfo.getAvailableBalance().add(cashAccountInfo.getNomentionAmount()).add(cashAccountInfo.getFrozenAmount()));
			moneyChgDetail.setIoFlag(DictionaryUtils.getIntegerValue(DictionaryKey.IO_FLAG_I));
			moneyChgDetail.setChangeDateTime(new Date());
			moneyChgDetail.setStatus(DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL));
			moneyChgDetail.setUpdateDateTime(new Date());
			
			mobileServiceSupport.callBackUpdateStatus(userTsmMsgInfo,accountOperInfo,mainAccountInfo,
										cashAccountInfo,operDetailIn,operDetailOut,moneyChgDetail);
			return MobileResponseUtils.buildSuccessResponse(messageId);
		}
		catch(Exception e){
			AttachmentResponse<String> response = MobileResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}
	
	/**
	 * 查询TSM表状态
	 * 在查询圈存状态时调用本接口
	 * Description:
	 * @date : 2014年8月
	 * @author zhanwx
	 * @param 
	 * @return
	 */
	@Override
	public AttachmentResponse<UserTsmMsgInfo> getTsmMessage(String messageId){
		try{
			if (null == messageId ){
				return MobileResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"MessageId"});
			}
			UserTsmMsgInfo utm = new UserTsmMsgInfo();
			utm.setMessageId(messageId);
			utm = userTsmMsgInfoDao.selectOne(utm);
			return MobileResponseUtils.buildSuccessResponse(utm);
		}
		catch(Exception e){
			AttachmentResponse<UserTsmMsgInfo> response = MobileResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}
	
	/**
	 * 修改手机注册表
	 * Description:
	 * @date : 2014年9月3日
	 * @author zhaoxingwen
	 * @param 
	 * @return
	 */
	@Override
	public AttachmentResponse updateUserMobileAccountInfo(UserMobileAccountInfo userMobileAccountInfo){
		try{
			userMobileAccountInfo.setUpdateDatetime(new Date());
			mobileServiceSupport.updateUserMobileAccountInfo(userMobileAccountInfo);
			return MobileResponseUtils.buildSuccessResponse();
		} catch (Exception e) {
			AttachmentResponse  response = MobileResponseUtils.buildExceptionResponse();
			logger.error("修改acm_user_mobile_account_info表异常");
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}
	
	/**
	 * Title: getInputPersonData
	 * Description:
	 * @date : 2014年9月15日
	 * @author Lilei
	 * @param seq
	 * @param accountId
	 * @param name
	 * @param IdType
	 * @param IdNum
	 * @return
	 */
	public List<PersonData>  getInputPersonData(Long seq,String accountId,String name,String IdType,String IdNum){
		try {
			/**	
			 * getInputPersonData方法需要使用的常量
			 */
			Integer FLAG_NEED_PASS_IN = 1;
			Integer FLAG_ALL = 3;
			String TAG_PAN = "5A";
			String TAG_CARDHOLDER_NAME = "5F20";
			String TAG_ID_NUM = "9F61";
			String TAG_ID_TYPE = "9F62";
			String TAG_SUBWAY_DT004 = "DT004"; 
			
			PersonData param = new PersonData();
			param.setFlag(FLAG_NEED_PASS_IN);
			List<PersonData> personDataList = personDataDao.selectList(param);
			if(null == personDataList || personDataList.size() == 0) return null;
			for(PersonData personData : personDataList){
				if (personData.getTag().equals(TAG_PAN)){
					personData.setValue(accountId);
				}
				if (personData.getTag().equals(TAG_CARDHOLDER_NAME)){
					personData.setValue(name);
				}
				if (personData.getTag().equals(TAG_ID_NUM)){
					personData.setValue(IdNum);
				}
				if (personData.getTag().equals(TAG_ID_TYPE)){
					personData.setValue(IdType);
				}
				if (personData.getTag().equals(TAG_SUBWAY_DT004)){
					//8byte		逻辑卡号
					StringBuffer strbuf = new StringBuffer();
					strbuf.append("002");//车票代码
					strbuf.append("04");//车票所属主类型
					strbuf.append("01");//子类型  01：现金类；	02：计次类
			        java.text.DecimalFormat format = new java.text.DecimalFormat("00000000");
			        logger.debug("seq="+seq);
			        String sequence = format.format(seq);
					strbuf.append(sequence);
					String strDt004 = strbuf.toString();
					byte [] bts = strDt004.getBytes();
					Integer ret = 0;
					for(int j=0;j<bts.length-1;j++){
						ret = (bts[j] ^ bts[j+1]);
					}
					ret %=10 ;
					strDt004=strDt004+ ret.toString();
					logger.debug("dt004==================:"+strDt004);
					personData.setValue(strDt004);
				}		
			}
			return personDataList;
		} catch (Exception e) {
			return null;
		}
	}

	
}
	