package com.cssweb.payment.account.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.cssweb.payment.account.constant.DictionaryKey;
import com.cssweb.payment.account.constant.GlobalConstants;
import com.cssweb.payment.account.constant.ManageConstants;
import com.cssweb.payment.account.dao.IAccountInfoDao;
import com.cssweb.payment.account.dao.IAccountMoneyChgDetailDao;
import com.cssweb.payment.account.dao.IAccountOperDetailDao;
import com.cssweb.payment.account.dao.IAccountOperInfoDao;
import com.cssweb.payment.account.dao.IBankInfoDao;
import com.cssweb.payment.account.dao.IBusinessInfoDao;
import com.cssweb.payment.account.dao.IEnterpriseUserDao;
import com.cssweb.payment.account.dao.IManageCommonDao;
import com.cssweb.payment.account.dao.IPersonalUserDao;
import com.cssweb.payment.account.dao.IUserLoginDao;
import com.cssweb.payment.account.pojo.AccountInfo;
import com.cssweb.payment.account.pojo.AccountMoneyChgDetail;
import com.cssweb.payment.account.pojo.AccountOperDetail;
import com.cssweb.payment.account.pojo.AccountOperInfo;
import com.cssweb.payment.account.pojo.BankInfo;
import com.cssweb.payment.account.pojo.BusinessInfo;
import com.cssweb.payment.account.pojo.EnterpriseUser;
import com.cssweb.payment.account.pojo.PersonalUser;
import com.cssweb.payment.account.pojo.UserBankAuInfo;
import com.cssweb.payment.account.pojo.UserLoginInfo;
import com.cssweb.payment.account.sei.IManageService;
import com.cssweb.payment.account.service.support.ManageServiceSupport;
import com.cssweb.payment.account.service.support.TradeServiceSupport;
import com.cssweb.payment.account.service.support.TradeServiceSupport.AccountOperateBuilder;
import com.cssweb.payment.account.util.DictionaryUtils;
import com.cssweb.payment.account.vo.AccountDetailed;
import com.cssweb.payment.account.vo.AccountDetailedQuery;
import com.cssweb.payment.account.vo.AccountInfoQuery;
import com.cssweb.payment.account.vo.CommercialTenantAccount;
import com.cssweb.payment.account.vo.CommercialTenantAccountDetailed;
import com.cssweb.payment.account.vo.CommercialTenantAccountDetailedQuery;
import com.cssweb.payment.account.vo.CommercialTenantAccountQuery;
import com.cssweb.payment.account.vo.EnterpriseUserQuery;
import com.cssweb.payment.account.vo.GetBusinessInfoListResponse;
import com.cssweb.payment.account.vo.GetEnterpriseUserListResponse;
import com.cssweb.payment.account.vo.GetPersonalUserListResponse;
import com.cssweb.payment.account.vo.PaymentAccount;
import com.cssweb.payment.account.vo.PaymentAccountDetailed;
import com.cssweb.payment.account.vo.PaymentAccountDetailedQuery;
import com.cssweb.payment.account.vo.PaymentAccountQuery;
import com.cssweb.payment.account.vo.PersonalUserQuery;
import com.cssweb.payment.account.vo.UserAccount;
import com.cssweb.payment.account.vo.UserAccountQuery;
import com.cssweb.payment.account.vo.UserInfo;
import com.cssweb.payment.common.provider.sei.IIdGeneratorService;
import com.cssweb.payment.common.response.AttachmentResponse;
import com.cssweb.payment.common.util.Page;
import com.cssweb.payment.common.util.ResponseUtils;
import com.cssweb.payment.omp.pojo.Dictionary;
import com.cssweb.payment.omp.sei.IDictionaryService;


@Component
public class ManageServiceImpl implements IManageService{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
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
	private IUserLoginDao userLoginDao;
	
	@Resource
	public IAccountMoneyChgDetailDao accountMoneyChgDetailDao;
	
	@Resource
	private IAccountOperInfoDao accountOperInfoDao;
	@Resource
	private IAccountOperDetailDao accountOperDetailDao; 
	@Resource
	private TradeServiceSupport tradeServiceSupport;
	@Resource
	private IManageCommonDao manageCommonDao;
	@Resource
	private IDictionaryService dictionaryService;
	
	/**
	 * 1017	商户签约查询接口
	 * 1.入参检查
	 * 2.查询账户信息
	 * 3.判断账户是否存在
	 * 4.返回账户状态
	 * 5.异常处理
	 * @author ZhaoXingwen
	 * @date 2014年7月21日 下午2:34:08
	 * @since 1.0
	 */
	@Override
	public AttachmentResponse<BusinessInfo>  signingSearch(Long userId, String service) {
		try{
			if (userId == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"user_id"});
			}
			if (service == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"service"});
			}
			BusinessInfo businessInfo = new BusinessInfo();
			businessInfo.setUserId(userId);
			businessInfo.setInterEn(service);
			businessInfo = businessInfoDao.selectOne(businessInfo);
			if (businessInfo == null) {
				return ResponseUtils.buildFailureResponse(ManageConstants.MERCHANT_NOT_EXIST);
			} 
			//已解约
			String statusSign = DictionaryUtils.getStringValue(DictionaryKey.BUSINESS_STATUS_UNSIGN);
			if(businessInfo.getStatus().equals(statusSign)){
				return ResponseUtils.buildFailureResponse(ManageConstants.MERCHANT_STATUS_UNSIGN);
			}
			//签约过期
			SimpleDateFormat FORMAT = new SimpleDateFormat("yy/MM/dd HH:mm");
			String expDate = FORMAT.format(businessInfo.getExpDate());
			String nowDate = FORMAT.format(new Date());
			if(expDate.compareTo(nowDate)<0){
				return ResponseUtils.buildFailureResponse(ManageConstants.MERCHANT_STATUS_EXPIRE);
			}
				
			return ResponseUtils.buildSuccessResponse(businessInfo);
			
		}
		catch(Exception e){
			AttachmentResponse<BusinessInfo> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}
	
	 /**
	  * 1050 个人客户信息列表查询接口
	  * @author Ganlin
	  * @date 2014年7月28日 下午2:39:03
	  * @param user PersonalUserQuery对象
	  * @return AttachmentResponse<Page<PersonalUser>>
	  * @since 0.1
	  */
	 public AttachmentResponse<Page<GetPersonalUserListResponse>> getPersonalUserList(PersonalUserQuery user) {
			try{
				//入参检查
				if (user == null || user.getPageSize() == null || user.getPageNo() == null) {
					return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[] {"user, user.PageSiz, user.PageNo"});
				}
				user.setAccountType(DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_TYPE_MAIN));
				Page<GetPersonalUserListResponse> personPage = personalUserDao.selectPersonalUserListByPage(user);
				List<GetPersonalUserListResponse> personalUserList = personPage.getData();
				for (GetPersonalUserListResponse personalUser : personalUserList) {
					UserBankAuInfo userBankAuInfo = manageServiceSupport.getLatestUserBankAuInfo(personalUser.getUserId());
					if (userBankAuInfo != null) {
						personalUser.setBankCity(userBankAuInfo.getBankCity());
						personalUser.setBankCnaps(userBankAuInfo.getBankCnaps());
						personalUser.setBankId(userBankAuInfo.getBankId());
						personalUser.setBankInternal(userBankAuInfo.getBankInternal());
						personalUser.setBankName(userBankAuInfo.getBankName());
						personalUser.setBankNo(userBankAuInfo.getBankNo());
						personalUser.setBranchId(userBankAuInfo.getBranchId());
						personalUser.setBranchName(userBankAuInfo.getBranchName());
						//认证失败原因
						personalUser.setAuthenticationReason(userBankAuInfo.getNote());
					}
				}
				return ResponseUtils.buildSuccessResponse(personPage);
			}
			catch(Exception e){
				AttachmentResponse<Page<GetPersonalUserListResponse>> response = ResponseUtils.buildExceptionResponse();
				logger.error(response.getReturnMsg(), e);
				return response;
			}
	 }

	/**
	 * 1051	企业客户信息列表查询接口
	* @param  enterpriseName 企业名称
	* @param  legalPersonUserName  企业法人姓名
	* @param  agentUserName 企业代理人姓名
	* @param  legalPersonUserType  企业法人证件类型 
	* @param  legalPersonUserNumber  企业法人证件号
	* @param  agentCertificatesType 企业代理人证件类型
	* @param  agentCertificatesNumber 企业代理人证件号
	* @param  beginDate 注册起始日期
	* @param  endDate 注册结束日期
	* @param  pageCount 每页数据条数
	* @param  pageNumber 当前页数
	* @return PagedAttachmentResponse<List<EnterpriseUser>>    返回类型
	* @author zhaoxingwen
	* @date 2014-7-3 下午1:22:59 
	* @throws
	 */
	@Override
	/*
	public AttachmentResponse<Page<EnterpriseUser>> getEnterpriseUserList(String enterpriseName,String legalPersonUserName,String agentUserName,
			String legalPersonUserType,String legalPersonUserNumber,String agentCertificatesType,String agentCertificatesNumber,String authStatus,String userStatus,
			String email,String mobile, Date beginDate,Date endDate,Integer pageCount,Integer pageNumber){*/
	public AttachmentResponse<Page<GetEnterpriseUserListResponse>> getEnterpriseUserList(EnterpriseUserQuery enterpriseUserQuery){
		try{
			if (enterpriseUserQuery == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"enterpriseUserQuery"});
			}
			Page<GetEnterpriseUserListResponse> enterpriseUserList = enterpriseUserDao.selectEnterpriseUserListByPage(enterpriseUserQuery);
			for (GetEnterpriseUserListResponse enterpriseUser : enterpriseUserList.getData()) {
			    UserBankAuInfo userBankAuInfo = manageServiceSupport.getLatestUserBankAuInfo(enterpriseUser.getUserId());
				if (userBankAuInfo != null) {
					enterpriseUser.setBankCity(userBankAuInfo.getBankCity());
					enterpriseUser.setBankCnaps(userBankAuInfo.getBankCnaps());
					enterpriseUser.setBankId(userBankAuInfo.getBankId());
					enterpriseUser.setBankInternal(userBankAuInfo.getBankInternal());
					enterpriseUser.setBankName(userBankAuInfo.getBankName());
					enterpriseUser.setBankNo(userBankAuInfo.getBankNo());
					enterpriseUser.setBranchId(userBankAuInfo.getBranchId());
					enterpriseUser.setBranchName(userBankAuInfo.getBranchName());
					//失败原因
					enterpriseUser.setAuthenticationReason(userBankAuInfo.getNote());
				}
			}
			return ResponseUtils.buildSuccessResponse(enterpriseUserList);
		}
		catch(Exception e){
			AttachmentResponse<Page<GetEnterpriseUserListResponse>> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}
	
	
	/**
	 * 1052	商户签约接口
	 * @author Ganlin
	 * @date 2014年7月29日 下午1:06:58
	 * @param businessInfo
	 * @return AttachmentResponse<String>
	 * @since 0.1
	 */
	@Deprecated
	public AttachmentResponse<String> merchantSigning(List<BusinessInfo> businessInfoList) {
		try {
			
			//入参检查
			if (businessInfoList == null || businessInfoList.isEmpty()) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"businessInfoList"});
			}
			Long userId = businessInfoList.get(0).getUserId();
			if (userId == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"businessInfo.userId"});
			}
			EnterpriseUserQuery query = new EnterpriseUserQuery();
			query.setUserId(userId);
			EnterpriseUser enterpriseUser = enterpriseUserDao.selectOne(query);
			if (null == enterpriseUser){
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			}
			BusinessInfo businessInfoQuery = new BusinessInfo();
			businessInfoQuery.setUserId(userId);
			List<BusinessInfo> businessInfoListOld = businessInfoDao.selectList(businessInfoQuery);
			String statusSign = DictionaryUtils.getStringValue(DictionaryKey.BUSINESS_STATUS_SIGN);
			String statusUnSign = DictionaryUtils.getStringValue(DictionaryKey.BUSINESS_STATUS_UNSIGN);
			String statusNoNe = DictionaryUtils.getStringValue(DictionaryKey.BUSINESS_STATUS_NONE);
			toNext : for (BusinessInfo businessInfo : businessInfoList) {
				businessInfo.setUpdateDatetime(new Date());
				businessInfo.setStatus(statusSign);
				for (BusinessInfo businessInfoOld : businessInfoListOld) {
					if (businessInfo.getChannelType().equals(businessInfoOld.getChannelType())) {
						businessInfo.setId(businessInfoOld.getId());
						continue toNext;
					}
				}
				businessInfo.setCreateDatetime(new Date());
			}
			toNext : for (BusinessInfo businessInfoOld : businessInfoListOld) {
				for (BusinessInfo businessInfo : businessInfoList) {
					if (businessInfoOld.getChannelType().equals(businessInfo.getChannelType())) {
						continue toNext;
					}
				}
				businessInfoOld.setUpdateDatetime(new Date());
				businessInfoOld.setStatus(statusUnSign);
				businessInfoList.add(businessInfoOld);
			}
			if (enterpriseUser.getIsMerchant().equals(statusUnSign)||enterpriseUser.getIsMerchant().equals(statusNoNe)) {
				enterpriseUser.setIsMerchant(statusSign);
				enterpriseUser.setUpdateDatetime(new Date());
				manageServiceSupport.saveEnterpriseUserAndBusinessInfo(enterpriseUser, businessInfoList);
				return ResponseUtils.buildSuccessResponse();
			}
			manageServiceSupport.saveEnterpriseUserAndBusinessInfo(null, businessInfoList);
			return ResponseUtils.buildSuccessResponse();
		} catch(Exception e){
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1053	商户信息修改接口
	 * @author Ganlin
	 * @date 2014年7月29日 下午1:16:48
	 * @param businessInfo
	 * @return AttachmentResponse<String>
	 * @since 0.1
	 */
	public AttachmentResponse<String> updateMerchant(BusinessInfo businessInfo) {
			try{
				if (businessInfo == null) {
					return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"businessInfo"});
				}
				BusinessInfo query = new BusinessInfo();
				query.setId(businessInfo.getId());
				BusinessInfo result = businessInfoDao.selectOne(query);
				if (null == result){
					return ResponseUtils.buildFailureResponse(ManageConstants.MERCHANT_NOT_EXIST);
				}
				Date updDate= new Date();
				businessInfo.setUpdateDatetime(updDate);
				businessInfoDao.update(businessInfo);
				return ResponseUtils.buildSuccessResponse();
			}catch(Exception e){
				AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
				logger.error(response.getReturnMsg(), e);
				return response; 
			}
	}

	
	/**
	 * 1054	商户信息列表查询接口
	 * @author Ganlin
	 * @date 2014年7月31日 上午11:37:02
	 * @param query
	 * @return 
	 * @since 0.1
	 */
	public AttachmentResponse<Page<GetEnterpriseUserListResponse>> getMerchant(EnterpriseUserQuery query) {
		try {
			//入参检查
			if (query == null || query.getPageSize() == null || query.getPageNo() == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[] {"query, query.pageSize, query.pageNO"});
			}
			Page<GetEnterpriseUserListResponse> enterpriseUserList = enterpriseUserDao.selectEnterpriseUserListByPage(query);
			return ResponseUtils.buildSuccessResponse(enterpriseUserList);
		} catch(Exception e){
			AttachmentResponse<Page<GetEnterpriseUserListResponse>> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response; 
		}
	}

	/**
	 * 1055	商户解约接口
	 * @author Ganlin
	 * @date 2014年8月1日 下午1:53:20
	 * @param userId
	 * @param channelType 签约类型
	 * @return 
	 * @since 0.1
	 */
	public AttachmentResponse<String>   merchantTermination(Long userId) {
		try{
			//入参检查
			if (userId == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"userId"});
			}
			//查询用户是否存在
			EnterpriseUserQuery  query = new EnterpriseUserQuery();
			query.setUserId(userId);
			EnterpriseUser enterpriseUser = enterpriseUserDao.selectOne(query);
			if (null == enterpriseUser){
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			}
			//查询此签约类型是否有记录
			BusinessInfo query2 = new BusinessInfo();
			query2.setUserId(userId);
			query2.setStatus(DictionaryUtils.getStringValue(DictionaryKey.BUSINESS_STATUS_SIGN));
			List<BusinessInfo> result = businessInfoDao.selectList(query2);
			if (result == null) {
				return ResponseUtils.buildFailureResponse(ManageConstants.MERCHANT_NOT_SIGN);
			}
			//更改签约状态为未签约
			for (BusinessInfo businessInfo : result) {
				businessInfo.setStatus(DictionaryUtils.getStringValue(DictionaryKey.BUSINESS_STATUS_UNSIGN));
				businessInfo.setUpdateDatetime(new Date());
			}
			enterpriseUser.setIsMerchant(DictionaryUtils.getStringValue(DictionaryKey.BUSINESS_STATUS_UNSIGN));
			manageServiceSupport.saveEnterpriseUserAndBusinessInfo(enterpriseUser, result);
			return ResponseUtils.buildSuccessResponse();
			
		} catch(Exception e) {
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response; 
		}
	}
	
	
	/**
	 * 1056	账户列表（个人）查询接口
	 * @author Ganlin
	 * @date 2014年7月27日 下午7:44:17
	 * @param userName 用户姓名
	 * @param mobileNo 用户手机号
	 * @param mailAddress 用户邮箱地址
	 * @param beginDate 账户创建时间起
	 * @param endDate 账户创建时间止
	 * @param accountType 账户类型
	 * @param accountStatus 账户状态
	 * @param pageSize 每页数据条数
	 * @param pageNo 当前页数
	 * @return  AttachmentResponse<Page<UserAccount>>
	 * @since 0.1
	 */
	public AttachmentResponse<Page<UserAccount>> getAccountInfoList(String userName,
			String mobileNo, String mailAddress, Date beginDate, Date endDate, String accountType, 
			String accountStatus, Integer pageSize,Integer pageNo) {
		try{
			//入参检查
			if (pageSize == null || pageNo == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[] {"pageSize", "pageNo"});
			}
			UserAccountQuery query = new UserAccountQuery();
			query.setUserName(userName);
			query.setMobileNo(mobileNo);
			query.setUserEmail(mailAddress);
			query.setAccountType(accountType);
			query.setAccountStatus(accountStatus);
			query.setBeginDate(beginDate);
			query.setEndDate(endDate);
			query.setPageSize(pageSize);
			query.setPageNo(pageNo);
			Page<UserAccount> page = manageCommonDao.selectByPage(query);
			return ResponseUtils.buildSuccessResponse(page);
		}
		catch(Exception e){
			AttachmentResponse<Page<UserAccount>> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1057	账户（个人 ）资金变化明细列表查询接口
	 * @author Ganlin
	 * @date 2014年7月28日 上午10:26:50
	 * @param  accountNumber 账户编号
	 * @param  userNumber 用户编号
	 * @param  userName 用户姓名
	 * @param  transactionType  交易类型
	 * @param  beginDate 开始日期
	 * @param  endDate 截止日期
	 * @param  pageSize 每页数据条数
	 * @param  pageNo  当前页数
	 * @return AttachmentResponse<Page<AccountDetailed>>
	 * @since 0.1
	 */
	public AttachmentResponse<Page<AccountDetailed>> queryUserAccountDetailed(
			Long accountNumber, Long userNumber, String userName,String transactionType, 
			Date beginDate, Date endDate, Integer pageSize, Integer pageNo) {
		try {
			//入参检查
			if (pageSize == null || pageNo == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[] {"pageSize", "pageNo"});
			}
			AccountDetailedQuery query = new AccountDetailedQuery();
			query.setAccountNumber(accountNumber);
			query.setUserId(userNumber);
			query.setUserName(userName);
			query.setTxnType(transactionType);
			query.setBeginDate(beginDate);
			query.setEndDate(endDate);
			Page<AccountDetailed> page = null;
			if(pageSize==-1&&pageNo==-1){
				 page = manageCommonDao.selectAll(query);
			}else{
				query.setPageSize(pageSize);
				query.setPageNo(pageNo);
				page = manageCommonDao.selectByPage(query);
			}
			return ResponseUtils.buildSuccessResponse(page);
		} catch(Exception e){
			AttachmentResponse<Page<AccountDetailed>> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;	
		}

	}

	/**
	 * 1058	账户（个人）金额调整接口
	 * TODO 是否还需要进行MAC校验?
	 * @author Ganlin
	 * @date 2014年7月25日 下午4:07:26
	 * @param  accountNumber 账户编号
	 * @param  inOutMark 进出账标志
	 * @param  money 金额
	 * @param  staffNumber 操作员工号
	 * @return AttachmentResponse<String>    返回类型
	 * @since 0.1
	 */
	public AttachmentResponse<Long> userAccountAdjustment(String tradeSn,Long accountId, int inOutMark, BigDecimal money, String staffNumber, String accountPosition) {
		try {
			//入参检查
			if (accountId == null || money == null || staffNumber == null || accountPosition == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[] {"accountId or money or staffNumber or accountPosition"});
			}
			AccountOperateBuilder accountOperateBuilder = tradeServiceSupport.newAccountOperateBuilder();
			//检查accountId是否存在
			AccountInfoQuery query = new AccountInfoQuery();
			query.setAccountId(accountId);
			AccountInfo account = accountInfoDao.selectOne(query);
			if (account == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ACCOUNT_NOT_EXIST);
			}
			//创建update对象，修改账户相应位置的金额
			AccountInfo update = new AccountInfo();
			update.setAccountId(accountId);
			if (accountPosition.equals(DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE))) {
				if (inOutMark ==DictionaryUtils.getIntegerValue(DictionaryKey.IO_FLAG_O) ) {
					update.setAvailableBalance(account.getAvailableBalance().subtract(money));
				} else {
					update.setAvailableBalance(account.getAvailableBalance().add(money));
				}
			} else if (accountPosition.equals(DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_FROZEN))) {
				if (inOutMark == DictionaryUtils.getIntegerValue(DictionaryKey.IO_FLAG_O)) {
					update.setFrozenAmount(account.getFrozenAmount().subtract(money));
				} else {
					update.setFrozenAmount(account.getFrozenAmount().add(money));
				}
			} else if (accountPosition.equals(DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_NOMENTION))) {
				if (inOutMark == DictionaryUtils.getIntegerValue(DictionaryKey.IO_FLAG_O)) {
					update.setNomentionAmount(account.getNomentionAmount().subtract(money));
				} else {
					update.setNomentionAmount(account.getNomentionAmount().add(money));
				}
			} 
			//创建operInfo，插入 账户操作表账户操作表 TODO 
			//生成accountSn
			AttachmentResponse<Long> accountSnRes = idGeneratorService.generate(AccountOperInfo.class.getName());
			if (accountSnRes.getReturnCode() != 0) {
				return ResponseUtils.buildFailureResponse(accountSnRes.getReturnCode());
			}
			AccountOperInfo operInfo = new AccountOperInfo();
			operInfo.setAccountSn(accountSnRes.getAttachment());
			operInfo.setSource(DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC));
			if (inOutMark == DictionaryUtils.getIntegerValue(DictionaryKey.IO_FLAG_O)) {
				operInfo.setFromAccountId(accountId);
			} else if (inOutMark == DictionaryUtils.getIntegerValue(DictionaryKey.IO_FLAG_I)) {
				operInfo.setToAccountId(accountId);
			} 
			operInfo.setAmount(money);
			operInfo.setTxnSeq(tradeSn);
			operInfo.setInterfaceName("userAccountAdjustment");
			operInfo.setMemo("运营管理平台修改");
			operInfo.setOperId(staffNumber);
			operInfo.setTxnType(DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_ADJUSTMENT));
			operInfo.setStatus(DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL));//记录状态.默认为00正常，01已处理。为运营管理平台的账目调整预留
			operInfo.setUpdateDateTime(new Date());
			operInfo.setReverseFlag(DictionaryUtils.getStringValue(DictionaryKey.REVERSE_FLAG_NO));
			//创建operDetail对象，插入账户操作流水明细表
			AttachmentResponse<Long> idRes = idGeneratorService.generate(AccountOperDetail.class.getName());
			if (idRes.getReturnCode() != 0) {
				return ResponseUtils.buildFailureResponse(idRes.getReturnCode());
			}
			AccountOperDetail operDetail = new AccountOperDetail();
			operDetail.setId(idRes.getAttachment());
			operDetail.setAccountSn(accountSnRes.getAttachment());
			operDetail.setIoFlag(inOutMark);
			operDetail.setAccountId(accountId);
			operDetail.setAccountPosition(accountPosition);
			operDetail.setAmount(money);
			operDetail.setStatus(DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL));
			//创建chg对象，插入账户资金变动明细表
			AttachmentResponse<Long> moneyIdRes = idGeneratorService.generate(AccountMoneyChgDetail.class.getName());
			if (moneyIdRes.getReturnCode() != 0) {
				return ResponseUtils.buildFailureResponse(moneyIdRes.getReturnCode());
			}
			AccountMoneyChgDetail chg = new AccountMoneyChgDetail();
			chg.setId(moneyIdRes.getAttachment());
			chg.setAccountSn(accountSnRes.getAttachment());
			chg.setUserId(account.getUserId());
			chg.setAccountId(accountId);
			chg.setAccountType(account.getAccountType());
			chg.setChgAccount(money);
			BigDecimal oldBalance = account.getAvailableBalance().add(account.getFrozenAmount()).add(account.getNomentionAmount());
			chg.setOldBalance(oldBalance);
			if (inOutMark == DictionaryUtils.getIntegerValue(DictionaryKey.IO_FLAG_O)) {
				chg.setBalance(oldBalance.subtract(money));
			} else if (inOutMark == DictionaryUtils.getIntegerValue(DictionaryKey.IO_FLAG_I)) {
				chg.setBalance(oldBalance.add(money));
			}
			chg.setIoFlag(inOutMark);
			chg.setTxnType(DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_ADJUSTMENT));
			//在一个事务中增改
			Date date = new Date();
			update.setUpdateDatetime(date);
			operInfo.setUpdateDateTime(date);
			operDetail.setUpdateDatetime(date);
			chg.setChangeDateTime(date);
			chg.setUpdateDateTime(date);
			//使用tradeServiceSupport和accountOperateBuilder统一进行账户变动操作
			accountOperateBuilder.addAccountInfo(update);
			accountOperateBuilder.addAccountOperInfo(operInfo);
			accountOperateBuilder.addAccountOperDetail(operDetail);
			accountOperateBuilder.addAccountMoneyChgDetail(chg);
			tradeServiceSupport.accountOperate(accountOperateBuilder);
			return ResponseUtils.buildSuccessResponse(accountSnRes.getAttachment());
		} catch(Exception e){
			AttachmentResponse<Long> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;	
		}
	}

	/**
	 * 1059	账户列表（企业）查询接口
	 * @author Ganlin
	 * @date 2014年7月26日 下午6:28:23
	 * @param legalPersonName 法人姓名
	 * @param legalPersonMail 法人邮箱
	 * @param commercialTenantType  商户类型
	 * @param accountStatus 账户状态
	 * @param beginDate 账户创建日期起
	 * @param endDate 账户创建日期止
	 * @param pageSize 每页数据条数
	 * @param pageNo 当前页数
	 * @return AttachmentResponse<Page<CommercialTenantAccount>>
	 * @since 0.1
	 */
	public AttachmentResponse<Page<CommercialTenantAccount>> queryCommercialTenantAccount(String legalPersonName, String legalPersonMail,
			String managerTel,String commercialTenantType, 
			String accountStatus, Date beginDate, Date endDate,Integer pageSize, Integer pageNo) {
		try {
			//入参检查
			if (pageSize == null || pageNo == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[] {"pageSize", "pageNo"});
			}
			CommercialTenantAccountQuery query = new CommercialTenantAccountQuery();
			query.setManagerTel(managerTel);
			//query.setLegalPersonMobile(managerMobile);
			query.setLegalPersonName(legalPersonName);
			query.setLegalPersonMail(legalPersonMail);
			query.setcTType(commercialTenantType);
			query.setAccountStatus(accountStatus);
			query.setBeginDate(beginDate);
			query.setEndDate(endDate);
			query.setPageSize(pageSize);
			query.setPageNo(pageNo);
			Page<CommercialTenantAccount> page = manageCommonDao.selectByPage(query);
			return ResponseUtils.buildSuccessResponse(page);
		} catch(Exception e){
			AttachmentResponse<Page<CommercialTenantAccount>> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;	
		}
	}

	/**
	 * 1060 账户（企业）资金变化明细查询接口
	 * @author Ganlin
	 * @date 2014年7月26日 下午6:47:20
	 * @param accountId 账户编号
	 * @param enterpriseName 企业名称
	 * @param CTNumber 商户编号
	 * @param transactionType 交易类型
	 * @param beginDate 交易日期起
	 * @param endDate 交易日期止
	 * @param pageSize 每页数据条数
	 * @param pageNo 当前页数
	 * @return AttachmentResponse<Page<CommercialTenantAccountDetailed>>
	 * @since 0.1
	 */
	@Override
	public AttachmentResponse<Page<CommercialTenantAccountDetailed>> queryCTAccountDetailed(Long accountId, String enterpriseName, 
			Long CTNumber, String transactionType, Date beginDate, Date endDate, Integer pageSize, Integer pageNo) {
		try {
			//入参检查
			if (pageSize == null || pageNo == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[] {"pageSize", "pageNo"});
			}
			CommercialTenantAccountDetailedQuery query = new CommercialTenantAccountDetailedQuery();
			query.setAccountNumber(accountId);
			query.setCompanyName(enterpriseName);
			query.setcTNumber(CTNumber);
			query.setTxnType(transactionType);
			query.setBeginDate(beginDate);
			query.setEndDate(endDate);
			Page<CommercialTenantAccountDetailed> page = null;
			if(pageSize==-1&&pageNo==-1){
				 page = manageCommonDao.selectAll(query);
			}else{
				query.setPageSize(pageSize);
				query.setPageNo(pageNo);
				page = manageCommonDao.selectByPage(query);
			}
			return ResponseUtils.buildSuccessResponse(page);
		} catch(Exception e){
			AttachmentResponse<Page<CommercialTenantAccountDetailed>> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;	
		}
	}
	
	
	/**
	 * 1061	账户（企业）金额调整接口
	 * 应该和3.58 相同，直接调用3.58
	 * @author Ganlin
	 * @date 2014年7月26日 下午1:40:26
	 * @param accountId
	 * @param inOutMark
	 * @param money
	 * @param staffNumber
	 * @param accountPosition
	 * @return 
	 * @since 0.1
	 */
	public AttachmentResponse<Long> ctAccountAdjustment(String tradeSn,Long accountId, int inOutMark, BigDecimal money, String staffNumber, String accountPosition)
	{
		return userAccountAdjustment( tradeSn,accountId, inOutMark, money, staffNumber, accountPosition);
	}
	
	/**
	 * 1062	账户（平台）列表查询接口
	 * @author Ganlin
	 * @date 2014年7月27日 下午5:57:17
	 * @param accountType 账户类型
	 * @param pageSize 每页数据条数
	 * @param pageNo 当前页数
	 * @return AttachmentResponse<Page<PaymentAccount>>
	 * @since 0.1
	 */
	public AttachmentResponse<Page<PaymentAccount>> queryPaymentAccount(String accountType, Integer pageSize, Integer pageNo) {
		try {
			//入参检查
			if (pageSize == null || pageNo == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[] {"pageSize", "pageNo"});
			}
			PaymentAccountQuery query = new PaymentAccountQuery();
			query.setAccountType(accountType);
			query.setPageSize(pageSize);
			query.setPageNo(pageNo);
			Page<PaymentAccount> page = manageCommonDao.selectByPage(query);
			return ResponseUtils.buildSuccessResponse(page);
		} catch(Exception e){
			AttachmentResponse<Page<PaymentAccount>> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;	
		}
	}
	
	/**
	 * 1063	账户（平台）资金变化明细查询接口
	 * @author Ganlin
	 * @date 2014年7月27日 下午3:28:35
	 * @param accountNumber 账户编号
	 * @param accountType 账户类型
	 * @param beginDate 交易日期起
	 * @param endDate 交易日期止
	 * @param pageSize 每页数据条数
	 * @param pageNo 当前页数
	 * @return AttachmentResponse<Page<PaymentAccountDetailed>>
	 * @since 0.1
	 */
	public AttachmentResponse<Page<PaymentAccountDetailed>> queryPaymentAccountDetailed(Long accountNumber, String cooperationType, 
			Date beginDate, Date endDate, Integer pageSize, Integer pageNo) {
		try {
			//入参检查
			if (pageSize == null || pageNo == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[] {"pageSize", "pageNo"});
			}
			PaymentAccountDetailedQuery query = new PaymentAccountDetailedQuery();
			query.setAccountNumber(accountNumber);
			query.setCooperationType(cooperationType);
			query.setBeginDate(beginDate);
			query.setEndDate(endDate);
			query.setPageSize(pageSize);
			query.setPageNo(pageNo);
			Page<PaymentAccountDetailed> page = manageCommonDao.selectByPage(query);
			return ResponseUtils.buildSuccessResponse(page);
		} catch(Exception e){
			AttachmentResponse<Page<PaymentAccountDetailed>> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;	
		}
	}
	
	/**
	 * 1064	平台账户增加和修改接口
	 * @author Ganlin
	 * @date 2014年7月27日 下午3:47:37
	 * @param bankInfo 平台账户对象
	 * @return AttachmentResponse<String>
	 * @since 0.1
	 */
	@Override
	public AttachmentResponse<String> modefyPaymentAccount(BankInfo bankInfo) {
		try {
			//入参检查
			if (bankInfo == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[] {"bankInfo"});
			}
			//查询是否存在
			BankInfo query = new BankInfo();
			query.setPaymentId(bankInfo.getPaymentId());
			query.setBankAccount(bankInfo.getBankAccount());
			BankInfo info = bankInfoDao.selectOne(query);
			if (info != null) {
				//存在，更新其信息
				bankInfo.setId(info.getId());
				bankInfo.setUpdateDatetime(new Date());
				bankInfoDao.update(bankInfo);
			} else {
				//不存在
				if (bankInfo.getPaymentId() != null) {
					return ResponseUtils.buildFailureResponse(ManageConstants.PAYMENT_ID_NOT_EXIST);
				}
				//插入记录，并创建账户
				//生成ID
				AttachmentResponse<Long> idRes = idGeneratorService.generate(BankInfo.class.getName());
				if (idRes.getReturnCode() != 0) {
					return ResponseUtils.buildFailureResponse(idRes.getReturnCode());
				}
				//生成paymentId
				AttachmentResponse<Long> paymentIdRes = idGeneratorService.generate(GlobalConstants.PATH_USERID);
				if (paymentIdRes.getReturnCode() != 0) {
					return ResponseUtils.buildFailureResponse(paymentIdRes.getReturnCode());
				}
				bankInfo.setId(idRes.getAttachment());
				bankInfo.setPaymentId(paymentIdRes.getAttachment());
				//创建一个主账户
				AttachmentResponse<Long> accountIdRes = idGeneratorService.generate(AccountInfo.class.getName());
				if (accountIdRes.getReturnCode() != 0) {
					return ResponseUtils.buildFailureResponse(accountIdRes.getReturnCode());
				}
				AccountInfo account = new AccountInfo();
				account.setAccountId(accountIdRes.getAttachment());
				account.setUserId(bankInfo.getPaymentId());
				account.setUserType(DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PLATFORM));
				account.setAccountType(DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_TYPE_MAIN));
				account.setAccountStatus(DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_STATUS_NORMAL));
				account.setCurrencyType(DictionaryUtils.getStringValue(DictionaryKey.CURRENCY_TYPE_RMB));
				account.setAvailableBalance(new BigDecimal("0"));
				account.setFrozenAmount(new BigDecimal("0"));
				account.setNomentionAmount(new BigDecimal("0"));
				account.setMac("");	//TODO 如何计算mac值

				//在一个事务中插入
				Date date = new Date();
				bankInfo.setUpdateDatetime(date);
				account.setCreateDatetime(date);
				account.setUpdateDatetime(date);
				manageServiceSupport.modefyPaymentAccount(bankInfo, account);
			}
			return ResponseUtils.buildSuccessResponse();
		} catch(Exception e){
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;	
		}
	}
	
	@Override
	public AttachmentResponse<UserInfo> getUserInfoByUserID(Long userId) {
		try {
			if(userId == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"userId"});
			}
			UserLoginInfo query = new UserLoginInfo();
			query.setUserId(userId);
			UserLoginInfo userLogin = userLoginDao.selectOne(query);
			if(userLogin == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			}
			// 判断个人用户或企业用业，返回
			String userTypePerson = DictionaryUtils
					.getStringValue(DictionaryKey.USER_TYPE_PERSONAL);
			String userTypeEn = DictionaryUtils
					.getStringValue(DictionaryKey.USER_TYPE_ENTERPRISE);
			UserInfo userInfo = new UserInfo();
			userInfo.setUserId(userId);
			if (userTypeEn.equals(userLogin.getUserType())) {
				//企业用户
				EnterpriseUserQuery euq = new EnterpriseUserQuery();
				euq.setUserId(userId);
				EnterpriseUser enterprise = enterpriseUserDao.selectOne(euq);
				userInfo.setEmail(enterprise.getEmail());
				userInfo.setName(enterprise.getBusinessName());//企业名称|银行开户名
				userInfo.setTel(enterprise.getManagerTel());
				userInfo.setManagerMobile(enterprise.getManagerMobile());
			} else if (userTypePerson.equals(userLogin.getUserType())) {
				//个人用户
				PersonalUserQuery param = new PersonalUserQuery();
				param.setUserId(userId);
				PersonalUser pUser = personalUserDao.selectOne(param);
				userInfo.setEmail(pUser.getEmailAddress());
				userInfo.setName(pUser.getUserName());//用户姓名
				userInfo.setManagerMobile(pUser.getPhoneNumber());//手机号码
			}
			else{
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			}
			userInfo.setUserStatus(userLogin.getUserStatus());
			if(userLogin.getUserStatus() != null){
				String userStatusName = DictionaryUtils.getDisplayName(DictionaryKey.USER_STATUS, userLogin.getUserStatus());
				userInfo.setUserStatusName(userStatusName);
			}
			userInfo.setUsertype(userLogin.getUserType());
			AccountInfoQuery accountInfoQuery = new AccountInfoQuery();
			accountInfoQuery.setUserId(userLogin.getUserId());
			accountInfoQuery.setAccountType(DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_TYPE_MAIN));
			AccountInfo account = accountInfoDao.selectOne(accountInfoQuery);
			userInfo.setAccountId(account.getAccountId());
			userInfo.setAccountStatus(account.getAccountStatus());	
			return  ResponseUtils.buildSuccessResponse(userInfo);
		} catch(Exception e){
			AttachmentResponse<UserInfo> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;	
		}
	}
	
	@Override
	public AttachmentResponse<UserInfo> getUserInfoByTelEmail(String userName) {
		 try {
			if(userName== null || "".equals(userName)){
				 return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"userName"});
			 }
			//按手机号或者邮箱查询UserLoginInfo
			UserLoginInfo query = new UserLoginInfo();
			if (PortalServiceImpl.isNumeric(userName)) {
				query.setUserMobile(userName);
			} 
			else {
				query.setUserEmail(userName);
			}
			 UserLoginInfo userLogin = userLoginDao.selectOne(query);
			 if(userLogin == null){
				 return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			 }
			 AttachmentResponse<UserInfo> response =  this.getUserInfoByUserID(userLogin.getUserId());
			 if(response.getReturnCode() == GlobalConstants.ARGUMENTS_IS_NULL){
				  return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			 }
			 return response;
		} catch (Exception e) {
			AttachmentResponse<UserInfo> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;	
		}
	}
	
	@Override
	@Deprecated
	public AttachmentResponse<UserInfo> getUserInfoByTelEmail(String userName , String userType){
		try{
			//入参检查
			if(null == userName || userName.equals("")){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"userName"});
			}
			if(null == userType || userType.equals("")){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"userType"});
			}
			//判断userType
			Long userId = null ;
			String personType = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL);
			String enterpriseType = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_ENTERPRISE);
			if(userType.equals(personType)){
				PersonalUserQuery puq = new PersonalUserQuery();
				PersonalUser pu = new PersonalUser();
				if(PortalServiceImpl.isNumeric(userName)){
					puq.setPhoneNumber(userName);
				}
				else{
					puq.setEmailAddress(userName);
				}
				pu = personalUserDao.selectOne(puq);
				if(null == pu){
					return ResponseUtils.buildSuccessResponse(null);
				}
				else{
					userId = pu.getUserId();
				}
			}
			else if(userType.equals(enterpriseType)){
				EnterpriseUserQuery euq = new EnterpriseUserQuery();
				EnterpriseUser eu = new EnterpriseUser();
				if(PortalServiceImpl.isNumeric(userName)){
					euq.setManagerTel(userName);
				}
				else{
					euq.setEmail(userName);
				}
				eu = enterpriseUserDao.selectOne(euq);
				if(null == eu){
					return ResponseUtils.buildSuccessResponse(null);
				}
				else{
					userId = eu.getUserId();
				}
			}
			//通过userId从用户登录表中查询用户信息
			AttachmentResponse<UserInfo> response =  this.getUserInfoByUserID(userId);
			return response;
		}
		catch(Exception e){
			AttachmentResponse<UserInfo> response = ResponseUtils.buildExceptionResponse();
			logger.debug(response.getReturnMsg(),e);
			return response;
		}
	}
	
	/**
	 * 根据绑定电话或邮箱查询用户列表(企业用固定电话来查)
	 * 
	 * @author wangpeng
	 * @date 2014年9月28日 下午16:49:32
	 * @param userName 绑定的电话或邮箱
	 * @param userType 用户类型
	 * @return list 用户集合
	 */
	@Override
	public AttachmentResponse<List<UserInfo>> getUserInfoListByTelEmail(String userName , String userType){
		try{
			//入参检查
			if(userName == null || userType == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"userName Or userType"});
			}
			List<UserInfo> list = null;
			String personType = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL);
			String enterpriseType = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_ENTERPRISE);
			if(userType.equals(personType)){
				PersonalUserQuery puq = new PersonalUserQuery();
				if(PortalServiceImpl.isEmail(userName)){
					puq.setEmailAddress(userName);
				}
				else{
					puq.setPhoneNumber(userName);
				}
				list = personalUserDao.selectPersonalInfoList(puq);
				return ResponseUtils.buildSuccessResponse(list);
			}
			if(userType.equals(enterpriseType)){
				EnterpriseUserQuery euq = new EnterpriseUserQuery();
				if(PortalServiceImpl.isEmail(userName)){
					euq.setEmail(userName);
				}
				else{
					euq.setManagerTel(userName);
				}
				list = enterpriseUserDao.selectEnterpriseInfoList(euq);
				return ResponseUtils.buildSuccessResponse(list);
			}
			return ResponseUtils.buildSuccessResponse(null);
		}
		catch(Exception e){
			AttachmentResponse<List<UserInfo>> response = ResponseUtils.buildExceptionResponse();
			logger.debug(response.getReturnMsg(),e);
			return response;
		}
	}
	
	@Override
	public AttachmentResponse<GetBusinessInfoListResponse> getBusinessInfoList(Long userId) {
		try {
			if(userId == null){
				 return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"userId"});
			}
			EnterpriseUserQuery userQuery = new EnterpriseUserQuery();
			userQuery.setUserId(userId);
			EnterpriseUser user = enterpriseUserDao.selectOne(userQuery);
			if (user == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			}
			BusinessInfo businessInfoQuery = new BusinessInfo();
			businessInfoQuery.setUserId(userId);
			String statusSign = DictionaryUtils.getStringValue(DictionaryKey.BUSINESS_STATUS_SIGN);
			businessInfoQuery.setStatus(statusSign);
			List<BusinessInfo> businessInfoList = businessInfoDao.selectList(businessInfoQuery);
			GetBusinessInfoListResponse businessInfoListResponse = new GetBusinessInfoListResponse();
			if(user.getIdType()!=null){
				String idTypeName = DictionaryUtils.getDisplayName(DictionaryKey.ID_TYPE, user.getIdType());
				businessInfoListResponse.setIdTypeName(idTypeName);
			}
			UserLoginInfo login = new UserLoginInfo();
			login.setUserId(userId);
			login = userLoginDao.selectOne(login);
			if(login.getUserStatus()!=null){
				businessInfoListResponse.setUserStatus(login.getUserStatus());
				String statusName = DictionaryUtils.getDisplayName(DictionaryKey.USER_STATUS, login.getUserStatus());
				businessInfoListResponse.setUserStatusName(statusName);
			}
			BeanUtils.copyProperties(user, businessInfoListResponse);
			if(businessInfoListResponse.getCountryCode()!=null){
				String countryName = DictionaryUtils.getDisplayName(DictionaryKey.COUNTRY_CODE, businessInfoListResponse.getCountryCode());
				businessInfoListResponse.setCountryName(countryName);
			}
			//循环为每个businessInfo填入channelTypeName
			for (BusinessInfo businessInfo : businessInfoList) {
				if (businessInfo.getChannelType() != null) {
					businessInfo.setChannelTypeName(DictionaryUtils.getDisplayName(DictionaryKey.CHANNEL_TYPE, businessInfo.getChannelType()));
				}
			}
			businessInfoListResponse.setBusinessInfoList(businessInfoList);
			return  ResponseUtils.buildSuccessResponse(businessInfoListResponse);
		}  catch (Exception e) {
			AttachmentResponse<GetBusinessInfoListResponse> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;	
		}
	}
	
	/**
	 * 1124 增加服务
	 * 
	 * @author wangpeng
	 * @date 2014年9月3日上午11:10:50
	 * @param business 服务对象
	 * @return 
	 */
	public AttachmentResponse<String> addBusinessInfo(BusinessInfo businessInfo){
		try{
			if (businessInfo == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"businessInfo"});
			}
			//英文接口是唯一的，必须入参
			if(businessInfo.getInterEn()==null){
				return ResponseUtils.buildFailureResponse(ManageConstants.MERCHANT_INTER_EN_NULL);
			}
			
			EnterpriseUserQuery enterprise = new EnterpriseUserQuery();
			enterprise.setUserId(businessInfo.getUserId());
			EnterpriseUser enterpriserUser = enterpriseUserDao.selectOne(enterprise);
			if(enterpriserUser==null){
				return ResponseUtils.buildFailureResponse(ManageConstants.MERCHANT_NOT_EXIST);
			}
			
			//查询商户信息
			BusinessInfo business =  new BusinessInfo();
			business.setUserId(enterpriserUser.getUserId());
			business.setInterEn(businessInfo.getInterEn());
			business = businessInfoDao.selectOne(business);
			if(business != null){
				if(DictionaryUtils.getStringValue(DictionaryKey.BUSINESS_STATUS_SIGN).equals(business.getStatus())){
					return ResponseUtils.buildFailureResponse(ManageConstants.REPEATED_REQUEST);
				}
				if(DictionaryUtils.getStringValue(DictionaryKey.BUSINESS_STATUS_UNSIGN).equals(business.getStatus())){
					businessInfo.setStatus(DictionaryUtils.getStringValue(DictionaryKey.BUSINESS_STATUS_SIGN));
					businessInfoDao.update(businessInfo);
					return ResponseUtils.buildSuccessResponse();
				}
			}else{
				AttachmentResponse<Long> idGeneratorResponse = idGeneratorService
						.generate(BusinessInfo.class.getName());
				businessInfo.setId(idGeneratorResponse.getAttachment());
				businessInfo.setCreateDatetime(new Date());
				businessInfo.setUpdateDatetime(new Date());
			}
			enterpriserUser.setIsMerchant(businessInfo.getStatus());
			enterpriserUser.setUpdateDatetime(new Date());
			
			manageServiceSupport.addBusinessInfo(businessInfo, enterpriserUser);
			return ResponseUtils.buildSuccessResponse();
		}  catch (Exception e) {
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;	
		}
	}
	
	/**
	 * 删除服务
	 * 
	 * @author wangpeng
	 * @date 2014年9月3日上午11:10:50
	 * @param business 服务对象
	 * @return 
	 */
	public AttachmentResponse<String> deleteBusinessInfoById(Long id){
		try{
			if (id == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"id"});
			}
			BusinessInfo businessInfo = new BusinessInfo();
			businessInfo.setId(id);
			businessInfo = businessInfoDao.selectOne(businessInfo);
			if(businessInfo==null){
				return ResponseUtils.buildFailureResponse(ManageConstants.MERCHANT_NOT_EXIST);
			}
			//查询用户是否存在
			EnterpriseUserQuery query = new EnterpriseUserQuery();
			query.setUserId(businessInfo.getUserId());
			EnterpriseUser enterpriseUser = enterpriseUserDao.selectOne(query);
			if (null == enterpriseUser){
				return ResponseUtils.buildFailureResponse(GlobalConstants.USER_NOT_EXIST);
			}
			//查询此服务是否是最后一个
			BusinessInfo query2 = new BusinessInfo();
			query2.setUserId(businessInfo.getUserId());
			query2.setStatus(DictionaryUtils.getStringValue(DictionaryKey.BUSINESS_STATUS_SIGN));
			List<BusinessInfo> result = businessInfoDao.selectList(query2);
			if (result.size() == 1) {
				enterpriseUser.setIsMerchant(DictionaryUtils.getStringValue(DictionaryKey.BUSINESS_STATUS_UNSIGN));
				manageServiceSupport.updateEnterpriseUserAndBusinessInfo(businessInfo,enterpriseUser);
			}else{
				businessInfoDao.delete(businessInfo);
			}
			return ResponseUtils.buildSuccessResponse();
		}  catch (Exception e) {
			AttachmentResponse<String> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;	
		}
	}
	
}
