/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.service.support;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cssweb.payment.account.constant.DictionaryKey;
import com.cssweb.payment.account.constant.GlobalConstants;
import com.cssweb.payment.account.constant.TradeConstants;
import com.cssweb.payment.account.dao.IAccountInfoDao;
import com.cssweb.payment.account.dao.IAccountMoneyChgDetailDao;
import com.cssweb.payment.account.dao.IAccountOperDetailDao;
import com.cssweb.payment.account.dao.IAccountOperInfoDao;
import com.cssweb.payment.account.dao.IBankInfoDao;
import com.cssweb.payment.account.dao.IEnterpriseUserDao;
import com.cssweb.payment.account.dao.IPersonalUserDao;
import com.cssweb.payment.account.dao.IUserBankAuInfoDao;
import com.cssweb.payment.account.pojo.AccountInfo;
import com.cssweb.payment.account.pojo.AccountMoneyChgDetail;
import com.cssweb.payment.account.pojo.AccountOperDetail;
import com.cssweb.payment.account.pojo.AccountOperInfo;
import com.cssweb.payment.account.pojo.BankInfo;
import com.cssweb.payment.account.pojo.EnterpriseUser;
import com.cssweb.payment.account.pojo.PersonalUser;
import com.cssweb.payment.account.pojo.UserBankAuInfo;
import com.cssweb.payment.account.util.DictionaryUtils;
import com.cssweb.payment.account.vo.AccountInfoQuery;
import com.cssweb.payment.account.vo.FrozenResponse;
import com.cssweb.payment.account.vo.FrozenTxResponse;
import com.cssweb.payment.common.provider.sei.IIdGeneratorService;
import com.cssweb.payment.common.response.AttachmentResponse;
import com.cssweb.payment.common.util.ResponseUtils;

/**
 * 交易服务接口支持类
 * 
 * @author wangpeng
 * @version 1.0 (2014年7月24日 下午2:36:18)
 * @since 1.0
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TradeServiceSupport {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	public IAccountInfoDao accountInfoDao;
	
	@Resource
	public IAccountOperInfoDao accountOperInfoDao;
	
	@Resource
	public IAccountOperDetailDao accountOperDetailDao;
	
	@Resource
	public IAccountMoneyChgDetailDao accountMoneyChgDetailDao;
	
	@Resource
	public IUserBankAuInfoDao userBankAuInfoDao;
	
	@Resource
	public IPersonalUserDao personalUserDao;
	
	@Resource
	public IEnterpriseUserDao enterpriseUserDao;
	
	@Resource
	public IBankInfoDao bankInfoDao;
	
	@Resource
	private AccountServiceSupport accountServiceSupport;
	
	@Resource
	private IIdGeneratorService idGeneratorService;
	
	/**
	 * 处理账户操作
	 * @author DuXiaohua
	 * @date 2014年7月26日 上午1:11:42
	 * @param accountOperateBuilder 账户操作构建器
	 * @since 1.0
	 */
	public void accountOperate(AccountOperateBuilder accountOperateBuilder) {
		for (AccountInfo accountInfo : accountOperateBuilder.accountInfoList) {
			accountInfoDao.update(accountInfo);
		}
		for (AccountOperInfo accountOperInfo : accountOperateBuilder.accountOperInfoList) {
			accountOperInfoDao.insert(accountOperInfo);
		}
		for (AccountOperDetail accountOperDetail : accountOperateBuilder.accountOperDetailList) {
			accountOperDetailDao.insert(accountOperDetail);
		}
		for (AccountMoneyChgDetail accountMoneyChgDetail : accountOperateBuilder.accountMoneyChgDetailList) {
			accountMoneyChgDetailDao.insert(accountMoneyChgDetail);
		}
		for (UserBankAuInfo userBankAuInfo : accountOperateBuilder.userBankAuInfoList) {
			userBankAuInfoDao.insert(userBankAuInfo);
		}
	}
	
	/**
	 * 打款认证操作
	 * @author 
	 * @date 2014年7月26日 上午1:11:42
	 * @param accountOperateBuilder 账户操作构建器
	 * @since 1.0
	 */
	public void applyMoneyPersonalUser(AccountOperateBuilder accountOperateBuilder, PersonalUser personalUser, UserBankAuInfo userBankAuInfo) {
		if (personalUser != null) {
			personalUserDao.update(personalUser);
		}
		userBankAuInfoDao.update(userBankAuInfo);
		if (accountOperateBuilder != null) {
			accountOperate(accountOperateBuilder);
		}
	}
	
	/**
	 * 打款认证操作
	 * @author 
	 * @date 2014年7月26日 上午1:11:42
	 * @param accountOperateBuilder 账户操作构建器
	 * @since 1.0
	 */
	public void applyMoneyEnterpriseUser(AccountOperateBuilder accountOperateBuilder,EnterpriseUser enterpriseUser, UserBankAuInfo userBankAuInfo) {
		if (enterpriseUser != null) {
			enterpriseUserDao.update(enterpriseUser);
		}
		userBankAuInfoDao.update(userBankAuInfo);
		if (accountOperateBuilder != null) {
			accountOperate(accountOperateBuilder);
		}
	}
	
	public AccountOperateBuilder newAccountOperateBuilder() {
		return new AccountOperateBuilder();
	}
	
	/**
	 * 账户操作构建器，构建需要操作的账户流水表信息
	 * 
	 * @author DuXiaohua
	 * @version 1.0 (2014年7月26日 上午1:08:23)
	 * @since 1.0
	 */
	public class AccountOperateBuilder {
		private List<AccountInfo> accountInfoList = new ArrayList<AccountInfo>();
		private List<AccountOperInfo> accountOperInfoList = new ArrayList<AccountOperInfo>();
		private List<AccountOperDetail> accountOperDetailList = new ArrayList<AccountOperDetail>();
		private List<AccountMoneyChgDetail> accountMoneyChgDetailList = new ArrayList<AccountMoneyChgDetail>();
		private List<UserBankAuInfo> userBankAuInfoList = new ArrayList<UserBankAuInfo>();
		
		/**
		 * 添加账户表信息
		 * 
		 * @author DuXiaohua
		 * @date 2014年7月26日 上午1:09:38
		 * @param accountInfo
		 * @since 1.0
		 */
		public AccountOperateBuilder addAccountInfo(AccountInfo accountInfo) {
			accountInfoList.add(accountInfo);
			return this;
		}
		
		/**
		 * 添加账户操作表信息
		 * 
		 * @author DuXiaohua
		 * @date 2014年7月26日 上午1:10:20
		 * @param accountOperInfo
		 * @since 1.0
		 */
		public AccountOperateBuilder addAccountOperInfo(AccountOperInfo accountOperInfo) {
			accountOperInfoList.add(accountOperInfo);
			return this;
		}
		
		/**
		 * 添加账户操作明细表信息
		 * 
		 * @author DuXiaohua
		 * @date 2014年7月26日 上午1:10:20
		 * @param accountOperInfo
		 * @since 1.0
		 */		
		public AccountOperateBuilder addAccountOperDetail(AccountOperDetail accountOperDetail) {
			accountOperDetailList.add(accountOperDetail);
			return this;
		}
		
		/**
		 * 添加账户资金变化明细表信息
		 * 
		 * @author DuXiaohua
		 * @date 2014年7月26日 上午1:10:20
		 * @param accountOperInfo
		 * @since 1.0
		 */			
		public AccountOperateBuilder addAccountMoneyChgDetail(AccountMoneyChgDetail accountMoneyChgDetail) {
			accountMoneyChgDetailList.add(accountMoneyChgDetail);
			return this;
		}
		
		
		
	}
	/**
	 * 根据银行账户id获取平台信息
	 * 
	 * @author WangPeng
	 * @date 2014年8月13日 下午3:24
	 * @param bankAccount
	 * @since 1.0
	 */
	public AccountInfo getPaymentByBankAccount(String bankAccount){
		BankInfo bankInfo = new BankInfo();
		bankInfo.setBankAccount(bankAccount);
		bankInfo = bankInfoDao.selectOne(bankInfo);
		
		AccountInfoQuery accountInfoQuery = new AccountInfoQuery();
		if(bankInfo != null){
			accountInfoQuery.setUserId(bankInfo.getPaymentId());
		}
		AccountInfo paymentAccountInfo = accountInfoDao.selectOne(accountInfoQuery);
		return paymentAccountInfo;
	}
	
	/**
	 * 根据平台账号获取银行信息
	 * 
	 * @author WangPeng
	 * @date 2014年8月20日 下午4:50
	 * @param paymentAccountId
	 * @since 1.0
	 */
	public AttachmentResponse<BankInfo> getBankInfoByPaymentAccountId(Long paymentAccountId){
		try{
			if(paymentAccountId==null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"paymentAccountId"});
			}
			AccountInfoQuery payment = new AccountInfoQuery();
			payment.setAccountId(paymentAccountId);
			AccountInfo paymentAccount = accountInfoDao.selectOne(payment);
			if(paymentAccount==null){
				return ResponseUtils.buildFailureResponse(TradeConstants.ACCOUNT_NOT_EXIT);
			}
			BankInfo bank = new BankInfo();
			bank.setPaymentId(paymentAccount.getUserId());
			bank = bankInfoDao.selectOne(bank);
			return ResponseUtils.buildSuccessResponse(bank);
		} catch (Exception e) {
			AttachmentResponse<BankInfo> response = ResponseUtils
					.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		 }
	}
	
	/**
	 * 1003.账户充值接口
	* @param  accountId 账户id
	* @param  tradeSn 交易流水号
	* @param  cashAmount 充值金额
	* @param  fundChannelSn 具体资金渠道生成流水号(银行通道流水号/刷预付卡流水号)
	* @param  bankId 银行id,流水表中用这个参数
	* @param  bankAccount 银行账户  ,根据bankAccount查询平台账号信息
	* @param  note
	* @param  externalOrderNo 外部系统订单号
	* @return AttachmentResponse<String>    返回类型
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-23 下午5:07:16 
	* @throws  1301    参数为空   
	* @throws  1300   账户不存在
	* @throws  1207   请求重复
	 */
	public AttachmentResponse<Long> accountRecharge(Long accountId,
			String tradeSn, BigDecimal cashAmount, String bankId,String bankAccount,
		    String note,String externalOrderNo) {
		AccountOperateBuilder accountOperateBuilder = newAccountOperateBuilder();
		//1、查询账户信息   
		AttachmentResponse<AccountInfo> toAccountResponse = accountServiceSupport.getAccountInfoWidthMACVerify(accountId);			
		//2、账户不存在,返回错误信息
		if (!toAccountResponse.isSuccess()) {
			return ResponseUtils.buildFailureResponse(toAccountResponse.getReturnCode());
		}
		AccountInfo toAccountInfo = toAccountResponse.getAttachment();
		//记录总金额 供资金明细表中使用      总金额 = 可提现金额 + 不可提现金额 + 冻结金额
		BigDecimal toAcountTotal = toAccountInfo.getAvailableBalance().add(toAccountInfo.getNomentionAmount())
				.add(toAccountInfo.getFrozenAmount());
		
		//3、根据银行id查询平台账号信息
		AttachmentResponse<AccountInfo> peymentResponse = getPaymentAccountByBankAccount(bankAccount);
		if(!peymentResponse.isSuccess()){
			return ResponseUtils.buildFailureResponse(peymentResponse.getReturnCode());
		}
		AccountInfo paymentAccountInfo = peymentResponse.getAttachment();
		//记录总金额 供资金明细表中使用      总金额 = 可提现金额 + 不可提现金额 + 冻结金额
		BigDecimal paymentAcountTotal = paymentAccountInfo.getAvailableBalance().add(paymentAccountInfo.getNomentionAmount())
				.add(paymentAccountInfo.getFrozenAmount());
		
		//5、获取流水号
		AttachmentResponse<Long> idGeneratorResponse = idGeneratorService
				.generate(AccountOperInfo.class.getName());
		Long newAccountSn = idGeneratorResponse.getAttachment();
		
		//6、更新账户信息表（个人） 
		//个人账号可用余额      可用  + 充值
		toAccountInfo.setAvailableBalance(cashAmount.add(toAccountInfo.getAvailableBalance()));
		toAccountInfo.setUpdateDatetime(new Date());
		accountOperateBuilder.addAccountInfo(toAccountInfo);
		
		//7、更新账户信息（平台）  平台自有账户
		//平台账号可用余额      可用  + 充值
		paymentAccountInfo.setAvailableBalance(cashAmount.add(paymentAccountInfo.getAvailableBalance()));
		paymentAccountInfo.setUpdateDatetime(new Date());
		accountOperateBuilder.addAccountInfo(paymentAccountInfo);
		
		//8、账户操作流水表
		AccountOperInfo accountOperInfo = new AccountOperInfo();
		accountOperInfo.setAccountSn(newAccountSn);
		accountOperInfo.setInterfaceName("accountRecharge");
		//来源     电脑端来源
		String sourceType = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);
		accountOperInfo.setSource(sourceType); 
		accountOperInfo.setToAccountId(accountId);
		accountOperInfo.setBankId(bankId);
		accountOperInfo.setAmount(cashAmount);
		//手续费设为0
		accountOperInfo.setFee(BigDecimal.ZERO);
		accountOperInfo.setMemo(note);
		accountOperInfo.setTxnSeq(tradeSn);
		//交易类型    充值
		String txnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_TOPUP);
		accountOperInfo.setTxnType(txnType);
		accountOperInfo.setBankAccount(bankAccount);
		accountOperInfo.setExternalOrderNo(externalOrderNo);
		//状态     正常
		String operInfoStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
		accountOperInfo.setStatus(operInfoStatus);
		//冲正标志     未冲正
		String reverseFlag = DictionaryUtils.getStringValue(DictionaryKey.REVERSE_FLAG_NO);
		accountOperInfo.setReverseFlag(reverseFlag);
		accountOperInfo.setUpdateDateTime(new Date());
		accountOperateBuilder.addAccountOperInfo(accountOperInfo);
		
		//9、账户操作明细表(insert个人)
		AccountOperDetail toAccountOperDetail = new AccountOperDetail();
		//id自增
		AttachmentResponse<Long> toAccountOperDetailRespinse = idGeneratorService
				.generate(AccountOperDetail.class.getName());
		toAccountOperDetail.setId(toAccountOperDetailRespinse.getAttachment());
		toAccountOperDetail.setAccountSn(newAccountSn);
		//出入金标志     进账
		Integer userIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
		toAccountOperDetail.setIoFlag(userIoFlag);
		toAccountOperDetail.setAccountId(accountId);
		//账户位置     可提现金额
		String userAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
		toAccountOperDetail.setAccountPosition(userAccountPosition);
		toAccountOperDetail.setAmount(cashAmount);
		//操作状态        正常
		String detalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
		toAccountOperDetail.setStatus(detalStatus);
		toAccountOperDetail.setUpdateDatetime(new Date());
		accountOperateBuilder.addAccountOperDetail(toAccountOperDetail);
		
		//9、账户操作明细表(insert平台)
		AccountOperDetail paymentAccountOperDetail = new AccountOperDetail();
		//id自增
		AttachmentResponse<Long> paymentAccountOperDetailRespinse = idGeneratorService
				.generate(AccountOperDetail.class.getName());
		paymentAccountOperDetail.setId(paymentAccountOperDetailRespinse.getAttachment());
		paymentAccountOperDetail.setAccountSn(newAccountSn);
		//出入金标志     进账
		Integer paymentIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
		paymentAccountOperDetail.setIoFlag(paymentIoFlag);
		paymentAccountOperDetail.setAccountId(paymentAccountInfo.getAccountId());
		//账户位置     可提现金额
		String paymentAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
		paymentAccountOperDetail.setAccountPosition(paymentAccountPosition);
		paymentAccountOperDetail.setAmount(cashAmount);
		//操作状态        正常
		String paymentDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
		paymentAccountOperDetail.setStatus(paymentDetalStatus);
		paymentAccountOperDetail.setUpdateDatetime(new Date());
		accountOperateBuilder.addAccountOperDetail(paymentAccountOperDetail);
		
		//10、资金变动明细表（个人）
		AccountMoneyChgDetail toAccountMoneyChgDetail = new AccountMoneyChgDetail();
		//自动生成id
		AttachmentResponse<Long> toAccountMoneyChgDetailRespinse = idGeneratorService
				.generate(AccountMoneyChgDetail.class.getName());
		//主键
		toAccountMoneyChgDetail.setId(toAccountMoneyChgDetailRespinse.getAttachment());
		//关联流水号
		toAccountMoneyChgDetail.setAccountSn(newAccountSn);
		toAccountMoneyChgDetail.setUserId(toAccountInfo.getUserId());
		toAccountMoneyChgDetail.setAccountId(accountId);
		toAccountMoneyChgDetail.setAccountType(toAccountInfo.getAccountType());
		//变化金额
		toAccountMoneyChgDetail.setChgAccount(cashAmount);
		//变化前金额
		toAccountMoneyChgDetail.setOldBalance(toAcountTotal);
		//变化后余额
		toAccountMoneyChgDetail.setBalance(toAccountInfo.getAvailableBalance().add(toAccountInfo.getNomentionAmount())
				.add(toAccountInfo.getFrozenAmount()));
		//出入金标志     进账
		Integer userMoneyIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
		toAccountMoneyChgDetail.setIoFlag(userMoneyIoFlag);
		toAccountMoneyChgDetail.setChangeDateTime(new Date());
		//交易类型    充值
		String toMoneyTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_TOPUP);
		toAccountMoneyChgDetail.setTxnType(toMoneyTxnType);
		//操作状态        正常
		String userMoneyStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
		toAccountMoneyChgDetail.setStatus(userMoneyStatus);
		toAccountMoneyChgDetail.setUpdateDateTime(new Date());
		accountOperateBuilder.addAccountMoneyChgDetail(toAccountMoneyChgDetail);
		
		//11、资金变动明细表（平台）
		AccountMoneyChgDetail paymentAccountMoneyChgDetail = new AccountMoneyChgDetail();
		//自动生成id
		AttachmentResponse<Long> paymentAccountMoneyChgDetailRespinse = idGeneratorService
				.generate(AccountMoneyChgDetail.class.getName());
		//主键id
		paymentAccountMoneyChgDetail.setId(paymentAccountMoneyChgDetailRespinse.getAttachment());
		//流水号
		paymentAccountMoneyChgDetail.setAccountSn(newAccountSn);
		paymentAccountMoneyChgDetail.setUserId(paymentAccountInfo.getUserId());
		paymentAccountMoneyChgDetail.setAccountId(paymentAccountInfo.getAccountId());
		paymentAccountMoneyChgDetail.setAccountType(paymentAccountInfo.getAccountType());
		//变化金额
		paymentAccountMoneyChgDetail.setChgAccount(cashAmount);
		//变化前金额
		paymentAccountMoneyChgDetail.setOldBalance(paymentAcountTotal);
		//变化后余额
		paymentAccountMoneyChgDetail.setBalance(paymentAccountInfo.getAvailableBalance().add(paymentAccountInfo.getNomentionAmount())
				.add(paymentAccountInfo.getFrozenAmount()));
		//出入金标志     进账
		Integer paymentMoneyIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
		paymentAccountMoneyChgDetail.setIoFlag(paymentMoneyIoFlag);
		paymentAccountMoneyChgDetail.setChangeDateTime(new Date());
		//交易类型    充值
		String paymentMoneyTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_TOPUP);
		paymentAccountMoneyChgDetail.setTxnType(paymentMoneyTxnType);
		//操作状态        正常
		String paymentMoneyStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
		paymentAccountMoneyChgDetail.setStatus(paymentMoneyStatus);
		paymentAccountMoneyChgDetail.setUpdateDateTime(new Date());
		accountOperateBuilder.addAccountMoneyChgDetail(paymentAccountMoneyChgDetail);
		
		//事务操作
		accountOperate(accountOperateBuilder);
		//返回账号操作流水号
		return ResponseUtils.buildSuccessResponse(newAccountSn);
	}
	
	/**
	 * 1010 账户转账接口
	* @param  fromAccountId 支出账户ID
	* @param  toAccountId 收入账户ID
	* @param  tradeSn 交易流水号
	* @param  cashAmount 转账金额
	* @param  note 备注
	* @return AttachmentResponse<Long>    返回类型
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-2 下午 4:48:28 
	* @return 1209 收款方和付款方不能相同
	* @return 1207 重复请求
	* @return 1301 参数为null
	* @return 1300 账户不存在
	* @return 1202 余额不足
	* @throws
	 */
	public AttachmentResponse<Long> accountTransfer(Long fromAccountId,
			Long toAccountId, String tradeSn, BigDecimal cashAmount,
			String note){
		AccountOperateBuilder accountOperateBuilder = newAccountOperateBuilder();
		//1、查询出金账户fromId，含校验码  ，返回对象
		AttachmentResponse<AccountInfo> fromAccountInfoResponse = accountServiceSupport.getAccountInfoWidthMACVerify(fromAccountId);			
		if (!fromAccountInfoResponse.isSuccess()) {
			return ResponseUtils.buildFailureResponse(fromAccountInfoResponse.getReturnCode());
		}
	    //根据账号id查询账号信息   fromAccountInfo为出金账号信息
		AccountInfo fromAccountInfo = fromAccountInfoResponse.getAttachment();
		//记录总金额 供资金明细表中使用      总金额 = 可提现金额 + 不可提现金额 + 冻结金额
		BigDecimal fromAcountTotal = fromAccountInfo.getAvailableBalance().add(fromAccountInfo.getNomentionAmount())
				.add(fromAccountInfo.getFrozenAmount());
		
		//2、查询入金账户toId，含校验码 ，返回对象
		AttachmentResponse<AccountInfo> toAccountInfoResponse = accountServiceSupport.getAccountInfoWidthMACVerify(toAccountId);			
		if (!toAccountInfoResponse.isSuccess()) {
			return ResponseUtils.buildFailureResponse(toAccountInfoResponse.getReturnCode());
		}
	    //根据账号id查询账号信息   toAccountInfo为入金账号信息
		AccountInfo toAccountInfo = toAccountInfoResponse.getAttachment();
		//记录总金额 供资金明细表中使用      总金额 = 可提现金额 + 不可提现金额 + 冻结金额
		BigDecimal toAcountTotal = toAccountInfo.getAvailableBalance().add(toAccountInfo.getNomentionAmount())
				.add(toAccountInfo.getFrozenAmount());
		
		//3、校验出金账号金额是否足够
		//通过BigDecimal的compareTo方法来进行比较。返回的结果是int类型，-1表示小于，0是等于，1是大于。
		if((fromAccountInfo.getAvailableBalance()).compareTo(cashAmount) == -1){
			return ResponseUtils.buildFailureResponse(TradeConstants.ACCOUNT_BALANCE_LACK);
		}
		
		//4、获取账号流水号   
		AttachmentResponse<Long> idGeneratorResponse = idGeneratorService
				.generate(AccountOperInfo.class.getName());
		Long newAccountSn = idGeneratorResponse.getAttachment();
		
		//5、更新入金账户信息  toid 可用余额，校验串，更新时间
		toAccountInfo.setAvailableBalance(toAccountInfo.getAvailableBalance().add(cashAmount));
		//TODO校验串
		toAccountInfo.setMac("mac");
		toAccountInfo.setUpdateDatetime(new Date());
		//更新操作
		accountOperateBuilder.addAccountInfo(toAccountInfo);
		
		//6、更新出金账户 信息  fromid 可用余额，校验串，更新时间
		fromAccountInfo.setAvailableBalance(fromAccountInfo.getAvailableBalance().subtract(cashAmount));
		//TODO校验串
		fromAccountInfo.setMac("mac");
		fromAccountInfo.setUpdateDatetime(new Date());
		//更新操作
		accountOperateBuilder.addAccountInfo(fromAccountInfo);
		
		//7、增加账号操作流水表
		AccountOperInfo accountOperInfo = new AccountOperInfo();
		//记录序号，和产生的流水号一致
		accountOperInfo.setAccountSn(newAccountSn);
		accountOperInfo.setInterfaceName("accountTransfer");
		//来源      电脑端来源
		String sourceType = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);
		accountOperInfo.setSource(sourceType);
		accountOperInfo.setFromAccountId(fromAccountInfo.getAccountId());
		accountOperInfo.setToAccountId(toAccountInfo.getAccountId());
		//变化金额
		accountOperInfo.setAmount(cashAmount);
		//手续费设为0
		accountOperInfo.setFee(BigDecimal.ZERO);
		accountOperInfo.setMemo(note);
		accountOperInfo.setTxnSeq(tradeSn);
		//交易类型   转账  
		String txnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_TRANSFER);
		accountOperInfo.setTxnType(txnType);
		//银行id  入参没有就不插入数据
		//accountOperInfo.setBankId(bankId);
		//状态     正常
		String operInfoStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
		accountOperInfo.setStatus(operInfoStatus);
		//冲正标志    未冲正
		String reverseFlag = DictionaryUtils.getStringValue(DictionaryKey.REVERSE_FLAG_NO);
		accountOperInfo.setReverseFlag(reverseFlag);
		accountOperInfo.setUpdateDateTime(new Date());
		//增加操作
		accountOperateBuilder.addAccountOperInfo(accountOperInfo);
		
		//8、插入账号操作明细表  出金账户fromid
		AccountOperDetail fromAccountOperDetail = new AccountOperDetail();
		AttachmentResponse<Long> fromAccountOperDetailResponse = idGeneratorService.generate(AccountOperDetail.class.getName());
		if (!fromAccountOperDetailResponse.isSuccess()) {
			return ResponseUtils.buildResponse(fromAccountOperDetailResponse);
		}
		fromAccountOperDetail.setId(fromAccountOperDetailResponse.getAttachment());
		//主记录序号,主记录序号，与账户操作记录关联
		fromAccountOperDetail.setAccountSn(newAccountSn);
		//出入金标志    出金
		Integer fromIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
		fromAccountOperDetail.setIoFlag(fromIoFlag);
		fromAccountOperDetail.setAccountId(fromAccountInfo.getAccountId());
		//出入金位置      可提现金额
		String fromAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
		fromAccountOperDetail.setAccountPosition(fromAccountPosition);
		//变化金额 
		fromAccountOperDetail.setAmount(cashAmount);
		//状态    正常
		String detalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
		fromAccountOperDetail.setStatus(detalStatus);
		fromAccountOperDetail.setUpdateDatetime(new Date());
		//插入数据
		accountOperateBuilder.addAccountOperDetail(fromAccountOperDetail);
		
		//9、插入账户操作明细表   入今账户  toid
		AccountOperDetail toAccountOperDetail = new AccountOperDetail();
		AttachmentResponse<Long> toAccountOperDetailResponse = idGeneratorService.generate(AccountOperDetail.class.getName());
		if (!toAccountOperDetailResponse.isSuccess()) {
			return ResponseUtils.buildResponse(toAccountOperDetailResponse);
		}
		toAccountOperDetail.setId(toAccountOperDetailResponse.getAttachment());
		//主记录序号,主记录序号，与账户操作记录关联
		toAccountOperDetail.setAccountSn(newAccountSn);
		//出入金标志     入金
		Integer toIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
		toAccountOperDetail.setIoFlag(toIoFlag);
		toAccountOperDetail.setAccountId(accountOperInfo.getToAccountId());
		//出入金位置    可提现金额
		String toAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
		toAccountOperDetail.setAccountPosition(toAccountPosition);
		//变化金额 
		toAccountOperDetail.setAmount(cashAmount);
		//状态       正常
		String toDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
		toAccountOperDetail.setStatus(toDetalStatus);
		toAccountOperDetail.setUpdateDatetime(new Date());
		accountOperateBuilder.addAccountOperDetail(toAccountOperDetail);
		
		//10、增加出金账户  fromid 资金明细表
		AccountMoneyChgDetail  fromAccountMoneyChgDetail = new AccountMoneyChgDetail();
		AttachmentResponse<Long> toChgDetailrespinse = idGeneratorService
				.generate(AccountMoneyChgDetail.class.getName());
		fromAccountMoneyChgDetail.setId(toChgDetailrespinse.getAttachment());
		//主记录序号，与账户操作关联
		fromAccountMoneyChgDetail.setAccountSn(newAccountSn);
		fromAccountMoneyChgDetail.setUserId(fromAccountInfo.getUserId());
		fromAccountMoneyChgDetail.setAccountId(fromAccountInfo.getAccountId());
		fromAccountMoneyChgDetail.setAccountType(fromAccountInfo.getAccountType());
		//变化金额
		fromAccountMoneyChgDetail.setChgAccount(cashAmount);
		//变化前金额     用之前定义的总共金额
		fromAccountMoneyChgDetail.setOldBalance(fromAcountTotal);
		//变化后余额    用改变之后的账户中的   可提现+不可提现+冻结金额+增量
		fromAccountMoneyChgDetail.setBalance(fromAccountInfo.getAvailableBalance().add(fromAccountInfo.getNomentionAmount())
				.add(fromAccountInfo.getFrozenAmount()));
		//进出帐标志   出金 
		Integer fromMoneyIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
		fromAccountMoneyChgDetail.setIoFlag(fromMoneyIoFlag);
		fromAccountMoneyChgDetail.setChangeDateTime(new Date());
		//交易类型   转账  
		String fromMoneyTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_TRANSFER);
		fromAccountMoneyChgDetail.setTxnType(fromMoneyTxnType);
		//状态      正常
		String fromMoneydetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
		fromAccountMoneyChgDetail.setStatus(fromMoneydetalStatus);
		fromAccountMoneyChgDetail.setUpdateDateTime(new Date());
		//插入数据
		accountOperateBuilder.addAccountMoneyChgDetail(fromAccountMoneyChgDetail);
		
		//11、增加入金账户 toid 资金明细表
		AccountMoneyChgDetail toAccountMoneyChgDetail = new AccountMoneyChgDetail();
		AttachmentResponse<Long> fromChgDetailrespinse = idGeneratorService
				.generate(AccountMoneyChgDetail.class.getName());
		toAccountMoneyChgDetail.setId(fromChgDetailrespinse.getAttachment());
		//主记录序号，与账户操作关联
		toAccountMoneyChgDetail.setAccountSn(newAccountSn);
		toAccountMoneyChgDetail.setUserId(toAccountInfo.getUserId());
		toAccountMoneyChgDetail.setAccountId(toAccountInfo.getAccountId());
		toAccountMoneyChgDetail.setAccountType(toAccountInfo.getAccountType());
		//变化金额
		toAccountMoneyChgDetail.setChgAccount(cashAmount);
		//变化前金额     用之前定义的总共金额
		toAccountMoneyChgDetail.setOldBalance(toAcountTotal);
		//变化后余额    用改变之后的账户中的   可提现+不可提现+冻结金额
		toAccountMoneyChgDetail.setBalance(toAccountInfo.getAvailableBalance().add(toAccountInfo.getNomentionAmount())
				.add(toAccountInfo.getFrozenAmount()));
		//进出帐标志     进账
		Integer toMoneyIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
		toAccountMoneyChgDetail.setIoFlag(toMoneyIoFlag);
		toAccountMoneyChgDetail.setChangeDateTime(new Date());
		//交易类型   转账  
		String toMoneyTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_TRANSFER);
		toAccountMoneyChgDetail.setTxnType(toMoneyTxnType);
		//状态      正常
		String toMoneyDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
		toAccountMoneyChgDetail.setStatus(toMoneyDetalStatus);
		toAccountMoneyChgDetail.setUpdateDateTime(new Date());
		//插入数据
		accountOperateBuilder.addAccountMoneyChgDetail(toAccountMoneyChgDetail);
		
		//事务操作
		accountOperate(accountOperateBuilder);
		//12、返回账号操作流水号
		return ResponseUtils.buildSuccessResponse(newAccountSn);
	}
	
	/**
	 * 1009	账户支付接口
	* @param  fromAccountId 支出账户ID
	* @param  toAccountId 收入账户ID
	* @param  tradeSn 交易流水号
	* @param  cashAmount 转账金额
	* @param  note 备注
	* @return AttachmentResponse<Long>    返回类型
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-9-26 下午2:43:38 
	* @return 1209  收款方和付款方不能相同
	* @return 1207  重复请求
	* @return 1301 参数为null
	* @return 1300 账户不存在
	* @return 1202 余额不足
	* @throws
	 */
	public AttachmentResponse<Long> accountPay(Long fromAccountId,
			Long toAccountId, String tradeSn, BigDecimal cashAmount, String note){
		AccountOperateBuilder accountOperateBuilder = newAccountOperateBuilder();
		//1、查询出金账户fromId，含校验码  ，返回对象
		AttachmentResponse<AccountInfo> fromAccountInforResponse = accountServiceSupport.getAccountInfoWidthMACVerify(fromAccountId);			
		if (!fromAccountInforResponse.isSuccess()) {
			return ResponseUtils.buildFailureResponse(fromAccountInforResponse.getReturnCode());
		}
	    //根据账号id查询账号信息   
		AccountInfo fromAccountInfo = fromAccountInforResponse.getAttachment();
		//记录总金额 供资金明细表中使用      总金额 = 可提现金额 + 不可提现金额 + 冻结金额
		BigDecimal fromAcountTotal = fromAccountInfo.getAvailableBalance().add(fromAccountInfo.getNomentionAmount())
				.add(fromAccountInfo.getFrozenAmount());
		//记录不可提现余额
		BigDecimal oldFromNomention = fromAccountInfo.getNomentionAmount();
		
		//2、查询入金账户toId，含校验码 ，返回对象
		AttachmentResponse<AccountInfo> toAccountInfoResponse = accountServiceSupport.getAccountInfoWidthMACVerify(toAccountId);			
		if (!toAccountInfoResponse.isSuccess()) {
			return ResponseUtils.buildFailureResponse(toAccountInfoResponse.getReturnCode());
		}
	    //根据账号id查询账号信息   toAccountInfo为入金账号信息
		AccountInfo toAccountInfo = toAccountInfoResponse.getAttachment();
		//记录总金额 供资金明细表中使用      总金额 = 可提现金额 + 不可提现金额 + 冻结金额
		BigDecimal toAcountTotal = toAccountInfo.getAvailableBalance().add(toAccountInfo.getNomentionAmount())
				.add(toAccountInfo.getFrozenAmount());
		
		//3、校验出金账号金额是否足够
		//验证可以余额和不可用余额是否充足，如果不足，返回错误信息     
		//通过BigDecimal的compareTo方法来进行比较。返回的结果是int类型，-1表示小于，0是等于，1是大于。
		if((fromAccountInfo.getAvailableBalance().add(fromAccountInfo.getNomentionAmount())).compareTo(cashAmount)==-1){
			return ResponseUtils.buildFailureResponse(TradeConstants.ACCOUNT_BALANCE_LACK);
		}
		
		//4、获取账号流水号   
		AttachmentResponse<Long> idGeneratorResponse = idGeneratorService
				.generate(AccountOperInfo.class.getName());
		Long newAccountSn = idGeneratorResponse.getAttachment();
		
		//5、更新出金账户，可提现金额或不可提现金额    ，生成校验码，更新时间，支付的时候不可提现金额优先支付
		//判断不可提现金额是否足够支付，优先支付不可提现金额    
		//通过BigDecimal的compareTo方法来进行比较。返回的结果是int类型，-1表示小于，0是等于，1是大于。
		//情况一：如果不可提现金额为0 ,则扣减可提现金额
		if(fromAccountInfo.getNomentionAmount().compareTo(BigDecimal.ZERO)==0){
			fromAccountInfo.setAvailableBalance(fromAccountInfo.getAvailableBalance().subtract(cashAmount));
		}
		//情况二：不可提现金额足够支付，则不可提现金额 扣除相应消费金额
		else if(!(fromAccountInfo.getNomentionAmount().compareTo(cashAmount)==-1)){
			fromAccountInfo.setNomentionAmount(fromAccountInfo.getNomentionAmount().subtract(cashAmount));
		}else{
		//情况三：不可提现金额不足够支付，则先扣光不可提现金额，然后从可提现金额中扣除剩余的金额
			fromAccountInfo.setNomentionAmount(BigDecimal.ZERO);
		//   可用余额 - （消费金额-不可用余额）
			fromAccountInfo.setAvailableBalance(fromAccountInfo.getAvailableBalance()
					.subtract((cashAmount.subtract(fromAccountInfo.getNomentionAmount()))));
		}
		//TODO生成校验码
		fromAccountInfo.setMac("TODO");
		fromAccountInfo.setUpdateDatetime(new Date());
		//更新操作
		accountOperateBuilder.addAccountInfo(fromAccountInfo);
		
		//6、更新入金账户，可提现金额，生成校验码，更新时间
		toAccountInfo.setAvailableBalance(toAccountInfo.getAvailableBalance().add(cashAmount));
		//TODO 生成校验码
		toAccountInfo.setMac("TODO");
		toAccountInfo.setUpdateDatetime(new Date());
		//更新操作
		accountOperateBuilder.addAccountInfo(toAccountInfo);
		
		//7、增加账号操作流水表
		AccountOperInfo accountOperInfo = new AccountOperInfo();
		//记录序号，和产生的流水号一致
		accountOperInfo.setAccountSn(newAccountSn);
		accountOperInfo.setInterfaceName("accountPay");
		//来源    电脑端来源
		String sourceType = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);
		accountOperInfo.setSource(sourceType);
		accountOperInfo.setFromAccountId(fromAccountInfo.getAccountId());
		accountOperInfo.setToAccountId(toAccountInfo.getAccountId());
		//变化金额
		accountOperInfo.setAmount(cashAmount);
		//手续费设为0
		accountOperInfo.setFee(BigDecimal.ZERO);
		//备注（入参）
		accountOperInfo.setMemo(note);
		accountOperInfo.setTxnSeq(tradeSn);
		//交易类型     “支付”  
		String txnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_PAY);
		accountOperInfo.setTxnType(txnType);
		//银行id  入参没有就不插入数据
		//accountOperInfo.setBankId(bankId);
		//状态   正常
		String operInfoStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
		accountOperInfo.setStatus(operInfoStatus);
		//冲正标志     未冲正
		String reverseFlag = DictionaryUtils.getStringValue(DictionaryKey.REVERSE_FLAG_NO);
		accountOperInfo.setReverseFlag(reverseFlag);
		accountOperInfo.setUpdateDateTime(new Date());
		//插入操作
		accountOperateBuilder.addAccountOperInfo(accountOperInfo);
		
		//8、增加出金账户 fromid 操作明细，可能有多条，根据账号的不可提现金额是否足够支付，进行判断，足够支付就一条信息， 不足够支付，就增加两条信息
		//判断不可提现金额是否足够支付，优先支付不可提现金额    
		//通过BigDecimal的compareTo方法来进行比较。返回的结果是int类型，-1表示小于，0是等于，1是大于。
		//情况一：不可提现金额为0的情况下，则改变的位置是可提现金额的记录
		if(fromAccountInfo.getNomentionAmount().compareTo(BigDecimal.ZERO)==0){
			AccountOperDetail fromAccountOperDetail =  new AccountOperDetail();
			//id随机产生
			AttachmentResponse<Long> fromAccountOperDetailRespinse = idGeneratorService
					.generate(AccountOperDetail.class.getName());
			fromAccountOperDetail.setId(fromAccountOperDetailRespinse.getAttachment());
			//主记录序号,主记录序号，与账户操作记录关联
			fromAccountOperDetail.setAccountSn(newAccountSn);
			//出入金标志    出金
			Integer fromIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
			fromAccountOperDetail.setIoFlag(fromIoFlag);
			fromAccountOperDetail.setAccountId(fromAccountInfo.getAccountId());
			//出入金位置    可提现金额
			String fromAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
			fromAccountOperDetail.setAccountPosition(fromAccountPosition);
			//变化金额 
			fromAccountOperDetail.setAmount(cashAmount);
			//状态   正常
			String detalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			fromAccountOperDetail.setStatus(detalStatus);
			fromAccountOperDetail.setUpdateDatetime(new Date());
			//插入操作
			accountOperateBuilder.addAccountOperDetail(fromAccountOperDetail);
		}
		//情况二：不可提现金额足够支付，则有一条明细，都是不可提现金额的记录
		else if(!(fromAccountInfo.getNomentionAmount().compareTo(cashAmount)==1)){ 
			AccountOperDetail fromAccountOperDetail =  new AccountOperDetail();
			//id随机产生
			AttachmentResponse<Long> fromAccountOperDetailRespinse = idGeneratorService
					.generate(AccountOperDetail.class.getName());
			fromAccountOperDetail.setId(fromAccountOperDetailRespinse.getAttachment());
			//主记录序号,与账户操作记录关联
			fromAccountOperDetail.setAccountSn(newAccountSn);
			//出入金标志    出金
			Integer fromIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
			fromAccountOperDetail.setIoFlag(fromIoFlag);
			fromAccountOperDetail.setAccountId(fromAccountInfo.getAccountId());
			//出入金位置     不可提现金额
			String fromAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_NOMENTION);
			fromAccountOperDetail.setAccountPosition(fromAccountPosition);
			//变化金额 
			fromAccountOperDetail.setAmount(cashAmount);
			//状态     正常
			String detalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			fromAccountOperDetail.setStatus(detalStatus);
			fromAccountOperDetail.setUpdateDatetime(new Date());
			//插入操作
			accountOperateBuilder.addAccountOperDetail(fromAccountOperDetail);
		}else{
			//情况三：不可提现金额不足够支付，则会在两个位置上产生数据，不可提现金额位置和可提现金额位置
			//位置一：不可提现金额位置
			AccountOperDetail NomentionfromAccountOperDetail =  new AccountOperDetail();
			//id随机产生
			AttachmentResponse<Long> fromNonementRespinse = idGeneratorService
					.generate(AccountOperDetail.class.getName());
			NomentionfromAccountOperDetail.setId(fromNonementRespinse.getAttachment());
			//主记录序号,主记录序号，与账户操作记录关联
			NomentionfromAccountOperDetail.setAccountSn(newAccountSn);
			//出入金标志  出金
			Integer nonFromIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
			NomentionfromAccountOperDetail.setIoFlag(nonFromIoFlag);
			NomentionfromAccountOperDetail.setAccountId(fromAccountInfo.getAccountId());
			//出入金位置    不可提现金额
			String fromAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_NOMENTION);
			NomentionfromAccountOperDetail.setAccountPosition(fromAccountPosition);
			//变化金额    变化的金额是 所有的不可提现金额的数量
			NomentionfromAccountOperDetail.setAmount(oldFromNomention);
			//状态  正常
			String nonDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
			NomentionfromAccountOperDetail.setStatus(nonDetalStatus);
			NomentionfromAccountOperDetail.setUpdateDatetime(new Date());
			//插入操作
			accountOperateBuilder.addAccountOperDetail(NomentionfromAccountOperDetail);
			
			//位置二：可提现金额位置
			AccountOperDetail availableFromAccountOperDetail =  new AccountOperDetail();
			//id随机产生
			AttachmentResponse<Long> availableFromAccountOperDetailRespinse = idGeneratorService
					.generate(AccountOperDetail.class.getName());
			availableFromAccountOperDetail.setId(availableFromAccountOperDetailRespinse.getAttachment());
			//主记录序号,与账户操作记录关联
			availableFromAccountOperDetail.setAccountSn(newAccountSn);
			//出入金标志  出金
			Integer avaFromIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
			availableFromAccountOperDetail.setIoFlag(avaFromIoFlag);//进出帐标志，-1：出账；1：进账;
			availableFromAccountOperDetail.setAccountId(fromAccountInfo.getAccountId());
			//出入金位置      可提现
			String avaFromAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
			availableFromAccountOperDetail.setAccountPosition(avaFromAccountPosition);
			//变化金额    变化的金额是     消费的金额 - 不可提现的金额   之差
			availableFromAccountOperDetail.setAmount(cashAmount.subtract(oldFromNomention));
			//状态       正常
			String detalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			availableFromAccountOperDetail.setStatus(detalStatus);
			availableFromAccountOperDetail.setUpdateDatetime(new Date());
			//插入操作
			accountOperateBuilder.addAccountOperDetail(availableFromAccountOperDetail);
		}
		
		//9、增加入金账户 toid 操作明细
		AccountOperDetail toAccountOperDetail =  new AccountOperDetail();
		//id随机产生
		AttachmentResponse<Long> toAccountOperDetailRespinse = idGeneratorService
				.generate(AccountOperDetail.class.getName());
		toAccountOperDetail.setId(toAccountOperDetailRespinse.getAttachment());
		//主记录序号,与账户操作记录关联
		toAccountOperDetail.setAccountSn(newAccountSn);
		//出入金标志   
		Integer toIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
		toAccountOperDetail.setIoFlag(toIoFlag);//进出帐标志，-1：出账；1：进账;
		toAccountOperDetail.setAccountId(toAccountInfo.getAccountId());
		//出入金位置     可提现
		String avaFromAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
		toAccountOperDetail.setAccountPosition(avaFromAccountPosition);
		//变化金额 
		toAccountOperDetail.setAmount(cashAmount);
		//状态   正常
		String detalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
		toAccountOperDetail.setStatus(detalStatus);
		toAccountOperDetail.setUpdateDatetime(new Date());
		//插入操作
		accountOperateBuilder.addAccountOperDetail(toAccountOperDetail);
		
		//10、增加出金账户  fromid 资金明细表
		AccountMoneyChgDetail  fromAccountMoneyChgDetail = new AccountMoneyChgDetail();
		//自动生成id
		AttachmentResponse<Long> fromAccountMoneyChgDetailRespinse = idGeneratorService
				.generate(AccountMoneyChgDetail.class.getName());
		//主键id
		fromAccountMoneyChgDetail.setId(fromAccountMoneyChgDetailRespinse.getAttachment());
		//主记录序号，与账户操作关联
		fromAccountMoneyChgDetail.setAccountSn(newAccountSn);
		fromAccountMoneyChgDetail.setUserId(fromAccountInfo.getUserId());
		fromAccountMoneyChgDetail.setAccountId(fromAccountInfo.getAccountId());
		fromAccountMoneyChgDetail.setAccountType(fromAccountInfo.getAccountType());
		//变化金额
		fromAccountMoneyChgDetail.setChgAccount(cashAmount);
		//变化前金额     用之前定义的总共金额
		fromAccountMoneyChgDetail.setOldBalance(fromAcountTotal);
		//变化后余额    用改变之后的账户中的   可提现+不可提现+冻结金额
		fromAccountMoneyChgDetail.setBalance(fromAccountInfo.getAvailableBalance().add(fromAccountInfo.getNomentionAmount())
				.add(fromAccountInfo.getFrozenAmount()));
		//进出帐标志   出金
		Integer fromIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
		fromAccountMoneyChgDetail.setIoFlag(fromIoFlag);
		fromAccountMoneyChgDetail.setChangeDateTime(new Date());
		//交易类型     “支付”  
		String fromMoneytxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_PAY);
		fromAccountMoneyChgDetail.setTxnType(fromMoneytxnType);
		//状态     正常
		String fromDdetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
		fromAccountMoneyChgDetail.setStatus(fromDdetalStatus);
		fromAccountMoneyChgDetail.setUpdateDateTime(new Date());
		//插入数据
		accountOperateBuilder.addAccountMoneyChgDetail(fromAccountMoneyChgDetail);
		
		
		//11、增加入金账户 toid 资金明细表
		AccountMoneyChgDetail toAccountMoneyChgDetail = new AccountMoneyChgDetail();
		//自动生成id
		AttachmentResponse<Long> toAccountMoneyChgDetailRespinse = idGeneratorService
				.generate(AccountMoneyChgDetail.class.getName());
		//主键id
		toAccountMoneyChgDetail.setId(toAccountMoneyChgDetailRespinse.getAttachment());
		//主记录序号，与账户操作关联
		toAccountMoneyChgDetail.setAccountSn(newAccountSn);
		toAccountMoneyChgDetail.setUserId(toAccountInfo.getUserId());
		toAccountMoneyChgDetail.setAccountId(toAccountInfo.getAccountId());
		toAccountMoneyChgDetail.setAccountType(toAccountInfo.getAccountType());
		//变化金额
		toAccountMoneyChgDetail.setChgAccount(cashAmount);
		//变化前金额     用之前定义的总共金额
		toAccountMoneyChgDetail.setOldBalance(toAcountTotal);
		//变化后余额    用改变之后的账户中的   可提现+不可提现+冻结金额
		toAccountMoneyChgDetail.setBalance(toAccountInfo.getAvailableBalance().add(toAccountInfo.getNomentionAmount())
				.add(toAccountInfo.getFrozenAmount()));
		//进出帐标志     进账
		Integer toMoneyIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
		toAccountMoneyChgDetail.setIoFlag(toMoneyIoFlag);
		toAccountMoneyChgDetail.setChangeDateTime(new Date());
		//交易类型     “支付”  
		String toMoneyTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_PAY);
		toAccountMoneyChgDetail.setTxnType(toMoneyTxnType);
		//状态      正常
		String toDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
		toAccountMoneyChgDetail.setStatus(toDetalStatus);
		toAccountMoneyChgDetail.setUpdateDateTime(new Date());
		//插入数据
		accountOperateBuilder.addAccountMoneyChgDetail(toAccountMoneyChgDetail);
		
		//事务操作
		accountOperate(accountOperateBuilder);
		//12、返回账号操作流水号
		return ResponseUtils.buildSuccessResponse(newAccountSn);
	}
	
	/**
	 * 1013	 账户退款接口
	* @param  fromAccountId 支出账户ID
	* @param  toAccountId 收入账户ID
	* @param  tradeSn 交易流水号
	* @param  accountSn 原交易账户转账流水号
	* @param  cashAmount  退款金额
	* @param  isfrozen 收入账户入账是否冻结(1 冻结 0 不冻结)
	* @param  note 备注
	* @return AttachmentResponse<Long>    返回类型
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-26 下午8:14:15 
	* @return 1301  参数为null
	* @return 1209 收款方和付款方不能相同
	* @return 1207 重复请求
	* @return 1300 账户不存在
	* @return 1202 余额不足
	* @return 1205 退款出入金账户与原出入金账户不匹配
	* @throws
	 */
	public AttachmentResponse<Long> accountRefund(Long fromAccountId,
			Long toAccountId, String tradeSn, Long accountSn,
			BigDecimal cashAmount, boolean isfrozen, String note){
		AccountOperateBuilder accountOperateBuilder = newAccountOperateBuilder();
		//1、查询出金账户fromId，含校验码  ，返回对象
		AttachmentResponse<AccountInfo> fromAccountInfoResponse = accountServiceSupport.getAccountInfoWidthMACVerify(fromAccountId);			
		//账户不存在,返回错误信息
		if (!fromAccountInfoResponse.isSuccess()) {
			return ResponseUtils.buildFailureResponse(fromAccountInfoResponse.getReturnCode());
		}
	    //根据账号id查询账号信息   fromAccountInfo为出金账号信息
		AccountInfo fromAccountInfo = fromAccountInfoResponse.getAttachment();
		//记录总金额 供资金明细表中使用      总金额 = 可提现金额 + 不可提现金额 + 冻结金额
		BigDecimal fromAcountTotal = fromAccountInfo.getAvailableBalance().add(fromAccountInfo.getNomentionAmount())
				.add(fromAccountInfo.getFrozenAmount());
		
		//2、查询入金账户toId，含校验码 ，返回对象
		AttachmentResponse<AccountInfo> toAccountInfoResponse = accountServiceSupport.getAccountInfoWidthMACVerify(toAccountId);			
		//账户不存在,返回错误信息
		if (!toAccountInfoResponse.isSuccess()) {
			return ResponseUtils.buildFailureResponse(toAccountInfoResponse.getReturnCode());
		}
	    //根据账号id查询账号信息   toAccountInfo为入金账号信息
		AccountInfo toAccountInfo = toAccountInfoResponse.getAttachment();
		//记录总金额 供资金明细表中使用      总金额 = 可提现金额 + 不可提现金额 + 冻结金额
		BigDecimal toAcountTotal = toAccountInfo.getAvailableBalance().add(toAccountInfo.getNomentionAmount())
				.add(toAccountInfo.getFrozenAmount());
		
		//3、校验出金账号金额是否足够
		//通过BigDecimal的compareTo方法来进行比较。返回的结果是int类型，-1表示小于，0是等于，1是大于。
		if((fromAccountInfo.getAvailableBalance()).compareTo(cashAmount)==-1){
			return ResponseUtils.buildFailureResponse(TradeConstants.ACCOUNT_BALANCE_LACK);
		}
		
		//4、根据交易流水号，查找操作流水对象
		AccountOperInfo accountOperInfo = new AccountOperInfo();
		accountOperInfo.setAccountSn(accountSn);;
		accountOperInfo = accountOperInfoDao.selectOne(accountOperInfo);
		
		//5、验证支出账号，收入账号与流水表中的  收入账户， 支出账户一致
		if(!(fromAccountId.equals(accountOperInfo.getToAccountId())
				&& toAccountId.equals(accountOperInfo.getFromAccountId()))){
			logger.error("出入金账户不一致：原from=" + accountOperInfo.getFromAccountId() + ",to=" + accountOperInfo.getToAccountId() + 
					     ";现from=" + fromAccountId + ",to=" + toAccountId);
			return ResponseUtils.buildFailureResponse(TradeConstants.REFUND_ACCOUNT_MISMATCHING);
		}
		
		//6、验证退款金额是否小于等于变化金额
		//通过BigDecimal的compareTo方法来进行比较。返回的结果是int类型，-1表示小于，0是等于，1是大于。
		if(cashAmount.compareTo(accountOperInfo.getAmount())==1){
			return ResponseUtils.buildFailureResponse(TradeConstants.ACCOUNT_BALANCE_LACK);
		}
		
		//7、根据流水号，返回账号操作明细集合
		AccountOperDetail accountOperDetail = new AccountOperDetail();
		accountOperDetail.setAccountSn(accountSn);
		List<AccountOperDetail> list = accountOperDetailDao.selectList(accountOperDetail);
		
		//8、获取账号流水号  newAccountSn为账号流水号
		AttachmentResponse<Long> idGeneratorResponse = idGeneratorService
				.generate(AccountOperInfo.class.getName());
		Long newAccountSn = idGeneratorResponse.getAttachment();
		
		//9、分收入账号冻结不冻结两种情况
		//情况一：如果出金账号入账不冻结  
		if(!isfrozen){
			//9.1更新出金账号信息表    可提现金额，校验串，更新时间
			fromAccountInfo.setAvailableBalance(fromAccountInfo.getAvailableBalance().subtract(cashAmount));
			//TODO
			fromAccountInfo.setMac("mac"); 
			fromAccountInfo.setUpdateDatetime(new Date());
			//更新操作
			accountOperateBuilder.addAccountInfo(fromAccountInfo);
			
			//9.2更新入金账号信息 ，可提现金额，不可提现金额，（根据资金位置来确定），冻结金额   遍历操作明细表的数据，如果变化的位置为可用，则可以增加变化金额，
			//如果变化的位置为不可提现，则不可提现增加金额，冻结金额扣除相应的数  ?第三种情况，怎么可提现和不可提现都同时增加，
			//第三种情况就是如果查出来的是三条明细数据的话，两个if语句都会执行
			for(int i=0;i<list.size();i++){
				//出金账户位置，01：可提现金额，02：冻结金额，03：不可提现金额
				//如果查询的位置是可提现，就增加可提现的金额，扣减冻结金额
				String avaAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
				//修改地方：此处应该加上账户相等的判断
				/*if(list.get(i).getAccountPosition().equals(avaAccountPosition)){*/
				if(list.get(i).getAccountPosition().equals(avaAccountPosition)&&list.get(i).getAccountId().equals(toAccountId)){
				   toAccountInfo.setAvailableBalance(toAccountInfo.getAvailableBalance().add(list.get(i).getAmount()));
				}
				//如果查询的位置是不可提现，就增加不可提现的金额，扣减冻结金额
				String nonAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_NOMENTION);
				//修改地方：此处应该加上账户相等的判断
				/*if(list.get(i).getAccountPosition().equals(nonAccountPosition)){*/
				if(list.get(i).getAccountPosition().equals(nonAccountPosition)&&list.get(i).getAccountId().equals(toAccountId)){
				   toAccountInfo.setNomentionAmount(toAccountInfo.getNomentionAmount().add(list.get(i).getAmount()));
				}
			}
			toAccountInfo.setUpdateDatetime(new Date());
			//TODO
			toAccountInfo.setMac("mac");
			//更新操作
			accountOperateBuilder.addAccountInfo(toAccountInfo);
			
			//9.3、增加账号操作流水表
			AccountOperInfo nofrozenAccountOperInfo = new AccountOperInfo();
			//记录序号，和产生的流水号一致
			nofrozenAccountOperInfo.setAccountSn(newAccountSn);
			nofrozenAccountOperInfo.setInterfaceName("accountRefund");
			//来源      电脑端来源
			String sourceType = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);
			nofrozenAccountOperInfo.setSource(sourceType);
			nofrozenAccountOperInfo.setFromAccountId(fromAccountInfo.getAccountId());
			nofrozenAccountOperInfo.setToAccountId(toAccountInfo.getAccountId());
			//变化金额
			nofrozenAccountOperInfo.setAmount(cashAmount);
			//手续费设为0
			nofrozenAccountOperInfo.setFee(BigDecimal.ZERO);
			nofrozenAccountOperInfo.setMemo(note);
			//交易流水号  （入参，没有就不改变）   此时入参的tradeSn是上一笔的交易流水号，和现在这笔交易没有关系
			//nofrozenAccountOperInfo.setTxnSeq(tradeSn);
			//交易类型 变化原因        退款
			String txnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_REFUND);
			nofrozenAccountOperInfo.setTxnType(txnType);
			//银行id  入参没有就不插入数据
			//accountOperInfo.setBankId(bankId);
			//状态   正常
			String operInfoStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
			nofrozenAccountOperInfo.setStatus(operInfoStatus);
			//冲正标志      未冲正
			String reverseFlag = DictionaryUtils.getStringValue(DictionaryKey.REVERSE_FLAG_NO);
			nofrozenAccountOperInfo.setReverseFlag(reverseFlag);
			nofrozenAccountOperInfo.setUpdateDateTime(new Date());
			nofrozenAccountOperInfo.setTxnSeq(tradeSn);
			accountOperateBuilder.addAccountOperInfo(nofrozenAccountOperInfo);
			
			//9.4、增加入金账户  账户操作明细表  可能有多条明细信息，根据查询出来的操作明细来确定，插入操作的数据都一样，就是出入金标志相反
			for(int i=0;i<list.size();i++){
				AccountOperDetail newtoAccountOperDetail =  new AccountOperDetail();
				//id随机产生
				AttachmentResponse<Long> toOperDatailRespinse = idGeneratorService
						.generate(AccountOperDetail.class.getName());
				newtoAccountOperDetail.setId(toOperDatailRespinse.getAttachment());
				//主记录序号,与账户操作记录关联
				newtoAccountOperDetail.setAccountSn(newAccountSn);
				//如果原操作明细里是出金标志，现在就插入入金标志，反之；
				Integer fromIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
				Integer toIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
				if(list.get(i).getIoFlag().equals(fromIoFlag)){
					//出入金标志
					newtoAccountOperDetail.setIoFlag(toIoFlag);
				}else{
					//出入金标志
					newtoAccountOperDetail.setIoFlag(fromIoFlag);
				}
				newtoAccountOperDetail.setAccountId(list.get(i).getAccountId());
				//出入金位置   //根据读取的流水表里的出入金位置，位置不变
				newtoAccountOperDetail.setAccountPosition(list.get(i).getAccountPosition());
				//变化金额 
				newtoAccountOperDetail.setAmount(list.get(i).getAmount());
				//状态    正常
				String detalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
				newtoAccountOperDetail.setStatus(detalStatus);
				newtoAccountOperDetail.setUpdateDatetime(new Date());
				//插入操作
				accountOperateBuilder.addAccountOperDetail(newtoAccountOperDetail);
			}
		}else{//如果入金账户资金冻结
			
			//10、更新出金账户信息     可提现金额，校验串，更新时间
			fromAccountInfo.setAvailableBalance(fromAccountInfo.getAvailableBalance().subtract(cashAmount));
			fromAccountInfo.setMac("mac");//TODO
			fromAccountInfo.setUpdateDatetime(new Date());
			//更新操作
			accountOperateBuilder.addAccountInfo(fromAccountInfo);
			
			//10.1 更新入金账户信息  冻结金额，校验串，更新时间
			//冻结金额 + 退款金额
			toAccountInfo.setFrozenAmount(toAccountInfo.getFrozenAmount().add(cashAmount));
			toAccountInfo.setMac("mac");//TODO
			toAccountInfo.setUpdateDatetime(new Date());
			//更新操作
			accountOperateBuilder.addAccountInfo(toAccountInfo);
			
			//10.2、增加账号操作流水表
			AccountOperInfo frozenAccountOperInfo = new AccountOperInfo();
			//记录序号，和产生的流水号一致
			frozenAccountOperInfo.setAccountSn(newAccountSn);
			frozenAccountOperInfo.setInterfaceName("accountRefund");
			//来源    电脑端来源
			String sourceType = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);
			frozenAccountOperInfo.setSource(sourceType);
			frozenAccountOperInfo.setFromAccountId(fromAccountInfo.getAccountId());
			frozenAccountOperInfo.setToAccountId(toAccountInfo.getAccountId());
			//变化金额
			frozenAccountOperInfo.setAmount(cashAmount);
			//手续费设为0
			frozenAccountOperInfo.setFee(BigDecimal.ZERO);
			frozenAccountOperInfo.setMemo(note);
			//交易流水号  入参
			frozenAccountOperInfo.setTxnSeq(tradeSn);
			//交易类型 变化原因： 退款冻结
			String txnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_REFUND_UNFROZEN);
			frozenAccountOperInfo.setTxnType(txnType);
			//银行id  入参没有就不插入数据
			//accountOperInfo.setBankId(bankId);
			//状态    正常
			String detalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			frozenAccountOperInfo.setStatus(detalStatus);
			//冲正标志    未冲正
			String reverseFlag = DictionaryUtils.getStringValue(DictionaryKey.REVERSE_FLAG_NO);
			frozenAccountOperInfo.setReverseFlag(reverseFlag);
			frozenAccountOperInfo.setUpdateDateTime(new Date());
			frozenAccountOperInfo.setTxnSeq(tradeSn);
			accountOperateBuilder.addAccountOperInfo(frozenAccountOperInfo);
			
			//10.3增加入金账户操作明细   冻结金额下就一条，资金位置就在冻结位置上
			AccountOperDetail newtoAccountOperDetail =  new AccountOperDetail();
			//id随机产生
			AttachmentResponse<Long> newtoAccountOperDetailRespinse = idGeneratorService
					.generate(AccountOperDetail.class.getName());
			newtoAccountOperDetail.setId(newtoAccountOperDetailRespinse.getAttachment());
			//主记录序号,与账户操作记录关联
			newtoAccountOperDetail.setAccountSn(newAccountSn);
			//出入金标志   
			Integer toIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
			newtoAccountOperDetail.setIoFlag(toIoFlag);
			newtoAccountOperDetail.setAccountId(toAccountInfo.getAccountId());
			//出入金位置      冻结金额
			String frozenAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_FROZEN);
			newtoAccountOperDetail.setAccountPosition(frozenAccountPosition);
			//变化金额 
			newtoAccountOperDetail.setAmount(cashAmount);
			//状态     正常
			String toDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			newtoAccountOperDetail.setStatus(toDetalStatus);
			newtoAccountOperDetail.setUpdateDatetime(new Date());
			//插入操作
			accountOperateBuilder.addAccountOperDetail(newtoAccountOperDetail);
			
			//修改记录三：此处增加出金账户明细
			//11、增加出金账户操作明细      出金账户不用考虑分情况，分不分情况，账户位置都是在可提现金额位置上
			AccountOperDetail newfromAccountOperDetail =  new AccountOperDetail();
			//id随机产生
			AttachmentResponse<Long> newfromAccountOperDetailRespinse = idGeneratorService
					.generate(AccountOperDetail.class.getName());
			newfromAccountOperDetail.setId(newfromAccountOperDetailRespinse.getAttachment());
			//主记录序号,与账户操作记录关联
			newfromAccountOperDetail.setAccountSn(newAccountSn);
			//出入金标志   
			Integer fromIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
			newfromAccountOperDetail.setIoFlag(fromIoFlag);
			newfromAccountOperDetail.setAccountId(fromAccountInfo.getAccountId());
			//出入金位置      可提现
			String avalibaleAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
			newfromAccountOperDetail.setAccountPosition(avalibaleAccountPosition);
			//变化金额 
			newfromAccountOperDetail.setAmount(cashAmount);
			//状态    正常
			String Status = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			newfromAccountOperDetail.setStatus(Status);
			newfromAccountOperDetail.setUpdateDatetime(new Date());
			//插入操作
			accountOperateBuilder.addAccountOperDetail(newfromAccountOperDetail);
		}
		
		//12、增加出金账户 fromid 资金明细表
		AccountMoneyChgDetail fromAccountMoneyChgDetail = new AccountMoneyChgDetail();
		//自动生成id
		AttachmentResponse<Long> fromAccountMoneyChgDetailRespinse = idGeneratorService
				.generate(AccountMoneyChgDetail.class.getName());
		//主键id
		fromAccountMoneyChgDetail.setId(fromAccountMoneyChgDetailRespinse.getAttachment());
		//主记录序号，与账户操作关联
		fromAccountMoneyChgDetail.setAccountSn(newAccountSn);
		fromAccountMoneyChgDetail.setUserId(fromAccountInfo.getUserId());
		fromAccountMoneyChgDetail.setAccountId(fromAccountInfo.getAccountId());
		fromAccountMoneyChgDetail.setAccountType(fromAccountInfo.getAccountType());
		//变化金额
		fromAccountMoneyChgDetail.setChgAccount(cashAmount);
		//变化前金额     用之前定义的总共金额
		fromAccountMoneyChgDetail.setOldBalance(fromAcountTotal);
		//变化后余额  
		fromAccountMoneyChgDetail.setBalance(fromAccountInfo.getAvailableBalance().add(fromAccountInfo.getNomentionAmount())
				.add(fromAccountInfo.getFrozenAmount()));
		//进出帐标志    出金
		Integer fromMoneyIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
		fromAccountMoneyChgDetail.setIoFlag(fromMoneyIoFlag);
		fromAccountMoneyChgDetail.setChangeDateTime(new Date());
		//交易类型 变化原因        账号退款
		String fromMoneyTxnType = null;
		if(!isfrozen){ //不冻结，对应交易类型：退款
			fromMoneyTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_REFUND);
		}else{//冻结，对应交易类型：退款冻结
			fromMoneyTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_REFUND_UNFROZEN);
		}
		fromAccountMoneyChgDetail.setTxnType(fromMoneyTxnType);
		//状态    正常
		String fromdetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
		fromAccountMoneyChgDetail.setStatus(fromdetalStatus);
		fromAccountMoneyChgDetail.setUpdateDateTime(new Date());
		//插入数据
		accountOperateBuilder.addAccountMoneyChgDetail(fromAccountMoneyChgDetail);
		
		//12、增加入金账户 toid 资金明细表
		AccountMoneyChgDetail toAccountMoneyChgDetail = new AccountMoneyChgDetail();
		//自动生成id
		AttachmentResponse<Long> toAccountMoneyChgDetailRespinse = idGeneratorService
				.generate(AccountMoneyChgDetail.class.getName());
		//主键id
		toAccountMoneyChgDetail.setId(toAccountMoneyChgDetailRespinse.getAttachment());
		//主记录序号，与账户操作关联
		toAccountMoneyChgDetail.setAccountSn(newAccountSn);
		toAccountMoneyChgDetail.setUserId(toAccountInfo.getUserId());
		toAccountMoneyChgDetail.setAccountId(toAccountInfo.getAccountId());
		toAccountMoneyChgDetail.setAccountType(toAccountInfo.getAccountType());
		//变化金额
		toAccountMoneyChgDetail.setChgAccount(cashAmount);
		//变化前金额     用之前定义的总共金额
		toAccountMoneyChgDetail.setOldBalance(toAcountTotal);
		//变化后余额    用改变之后的账户中的   可提现+不可提现+冻结金额
		toAccountMoneyChgDetail.setBalance(toAccountInfo.getAvailableBalance().add(toAccountInfo.getNomentionAmount())
				.add(toAccountInfo.getFrozenAmount()));
		//进出帐标志    入金
		Integer toIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
		toAccountMoneyChgDetail.setIoFlag(toIoFlag);
		toAccountMoneyChgDetail.setChangeDateTime(new Date());
		//交易类型 变化原因        账号退款
		String toMoneyTxnType = null;
		if(!isfrozen){ //不冻结，对应交易类型：退款
			toMoneyTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_REFUND);
		}else{//冻结，对应交易类型：退款冻结
			toMoneyTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_REFUND_UNFROZEN);
		}
		toAccountMoneyChgDetail.setTxnType(toMoneyTxnType);
		//状态       正常
		String todetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
		toAccountMoneyChgDetail.setStatus(todetalStatus);
		toAccountMoneyChgDetail.setUpdateDateTime(new Date());
		//插入数据
		accountOperateBuilder.addAccountMoneyChgDetail(toAccountMoneyChgDetail);
		
		//事务操作
		accountOperate(accountOperateBuilder);
		return ResponseUtils.buildSuccessResponse(newAccountSn);
	}
	
	/**
	 * 1100  账户资金提现冻结（含手续费）
	 * @author wangpeng
	 * @date 2014年7月30日 下午5:54:34
	 * @param accountId 账户Id
	 * @param tradeSn 交易流水号
	 * @param cashAmount 冻结金额(不含手续费)
	 * @param feeAmount 手续费
	 * @param bankId 银行Id
	 * @param bankAccount 银行的账号  
	 * @param note 备注
	 * @return AttachmentResponse<Long> 附加对象为账户操作流水号
	 * @throws  1301    参数为空   
	 * @throws  1300   账户不存在
	 * @throws  1207   请求重复
	 * @since 1.0
	 */
	public AttachmentResponse<Long> freezeForWithdraw(Long accountId,
			String tradeSn, BigDecimal cashAmount, BigDecimal feeAmount,
			String bankId, String bankAccount, String note) {
		AccountOperateBuilder accountOperateBuilder = newAccountOperateBuilder();
		//1、根据accountId 查找 账户信息 含校验码
		AttachmentResponse<AccountInfo> fromAccountInfoResponse = accountServiceSupport.getAccountInfoWidthMACVerify(accountId);			
		//账户不存在,返回错误信息
		if (!fromAccountInfoResponse.isSuccess()) {
			return ResponseUtils.buildFailureResponse(fromAccountInfoResponse.getReturnCode());
		}
		AccountInfo fromAccountInfo = fromAccountInfoResponse.getAttachment();
		
		//2、验证账号余额是否足够  和冻结金额进行比较   -1表示小于  
		if(fromAccountInfo.getAvailableBalance().compareTo(cashAmount.add(feeAmount))==-1){
			return ResponseUtils.buildFailureResponse(TradeConstants.ACCOUNT_BALANCE_LACK);
		}
		
		//3、获取流水号
		AttachmentResponse<Long> idGeneratorResponse = idGeneratorService
				.generate(AccountOperInfo.class.getName());
		Long newAccountSn = idGeneratorResponse.getAttachment();
		
		//4、更新账号信息   可提现金额  冻结金额    mac 更新时间
		//可提现 - 冻结金额  - 手续费
		fromAccountInfo.setAvailableBalance(fromAccountInfo.getAvailableBalance().subtract(cashAmount).subtract(feeAmount));
		//冻结 + 冻结金额  + 手续费
		fromAccountInfo.setFrozenAmount(fromAccountInfo.getFrozenAmount().add(cashAmount).add(feeAmount));
		//TODO
		fromAccountInfo.setMac("mac");
		fromAccountInfo.setUpdateDatetime(new Date());
		accountOperateBuilder.addAccountInfo(fromAccountInfo);
		
		//5、插入操作流水表
		AccountOperInfo accountOperInfo = new AccountOperInfo();
		//记录序号，和产生的流水号一致
		accountOperInfo.setAccountSn(newAccountSn);
		accountOperInfo.setInterfaceName("freezeForWithdraw");
		//来源 电脑端来源
		String transfersourceType = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);
		accountOperInfo.setSource(transfersourceType);
		accountOperInfo.setFromAccountId(fromAccountInfo.getAccountId());
		accountOperInfo.setToAccountId(fromAccountInfo.getAccountId());
		//变化金额
		accountOperInfo.setAmount(cashAmount);
		accountOperInfo.setBankId(bankId);
		accountOperInfo.setBankAccount(bankAccount);
		accountOperInfo.setMemo(note);
		//交易流水号  入参
		accountOperInfo.setTxnSeq(tradeSn);
		//交易类型 变化原因：  冻结
		String transferTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_FROZEN);
		accountOperInfo.setTxnType(transferTxnType);  
		accountOperInfo.setBankId(bankId);
		//手续费
		accountOperInfo.setFee(feeAmount);
		//状态      正常
		String transferInfoStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
		accountOperInfo.setStatus(transferInfoStatus);
		//冲正标志    未冲正
		String transferReverseFlag = DictionaryUtils.getStringValue(DictionaryKey.REVERSE_FLAG_NO);
		accountOperInfo.setReverseFlag(transferReverseFlag);
		accountOperInfo.setUpdateDateTime(new Date());
		//插入操作
		accountOperateBuilder.addAccountOperInfo(accountOperInfo);
		
		//6、插入账号操作明细表   可用金额位置
		AccountOperDetail fromAvaAccountOperDetail =  new AccountOperDetail();
		//id随机产生
		AttachmentResponse<Long> fromAvaAccountOperRespinse = idGeneratorService
				.generate(AccountOperDetail.class.getName());
		fromAvaAccountOperDetail.setId(fromAvaAccountOperRespinse.getAttachment());
		//主记录序号,主记录序号，与账户操作记录关联
		fromAvaAccountOperDetail.setAccountSn(newAccountSn);
		//出入金标志   出金
		Integer avaFromIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
		fromAvaAccountOperDetail.setIoFlag(avaFromIoFlag);
		fromAvaAccountOperDetail.setAccountId(fromAccountInfo.getAccountId());
		//出入金位置         可提现金额
		String avaAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
		fromAvaAccountOperDetail.setAccountPosition(avaAccountPosition);
		//变化金额 
		fromAvaAccountOperDetail.setAmount(cashAmount.add(feeAmount));
		//状态     正常
		String avaDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
		fromAvaAccountOperDetail.setStatus(avaDetalStatus);
		fromAvaAccountOperDetail.setUpdateDatetime(new Date());
		//插入操作
		accountOperateBuilder.addAccountOperDetail(fromAvaAccountOperDetail);
		
		//冻结金额位置:
		AccountOperDetail fromFrozenAccountOperDetail =  new AccountOperDetail();
		//id随机产生
		AttachmentResponse<Long> fromFrozenAccountOperRespinse = idGeneratorService
				.generate(AccountOperDetail.class.getName());
		fromFrozenAccountOperDetail.setId(fromFrozenAccountOperRespinse.getAttachment());
		//主记录序号,主记录序号，与账户操作记录关联
		fromFrozenAccountOperDetail.setAccountSn(newAccountSn);
		//出入金标志   入金
		Integer frozenFromIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
		fromFrozenAccountOperDetail.setIoFlag(frozenFromIoFlag);
		fromFrozenAccountOperDetail.setAccountId(fromAccountInfo.getAccountId());
		//出入金位置         冻结金额
		String frozenAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_FROZEN);
		fromFrozenAccountOperDetail.setAccountPosition(frozenAccountPosition);
		//变化金额 
		fromFrozenAccountOperDetail.setAmount(cashAmount.add(feeAmount));
		//状态     正常
		String frozenDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
		fromFrozenAccountOperDetail.setStatus(frozenDetalStatus);
		fromFrozenAccountOperDetail.setUpdateDatetime(new Date());
		//插入操作
		accountOperateBuilder.addAccountOperDetail(fromFrozenAccountOperDetail);
		
		//事务操作
		accountOperate(accountOperateBuilder);
		return ResponseUtils.buildSuccessResponse(newAccountSn);
	}
	
	/**
	 * 根据bankAccount查询平台账户接口，返回账户对象
	 * @author wangpeng
	 * @date 2014年8月15日  下午2:04:34
	 * @param bankAccount
	 * @return AttachmentResponse<AccountInfo>
	 * @since 1.0
	 * @return 1210  平台账号不存在
	 * @return 1301 参数为null
	 */
	public AttachmentResponse<AccountInfo> getPaymentAccountByBankAccount(String bankAccount){
		try{
			if (bankAccount == null ) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"bankAccount"});
			}
			BankInfo bankInfo = new BankInfo();
			bankInfo.setBankAccount(bankAccount);
			bankInfo = bankInfoDao.selectOne(bankInfo);
			if(bankInfo==null){
				return ResponseUtils.buildFailureResponse(TradeConstants.PAYMENT_ACCOUNT_NOT_EXIT);
			}
			
			AccountInfoQuery accountInfoQuery = new AccountInfoQuery();
			accountInfoQuery.setUserId(bankInfo.getPaymentId());
			AccountInfo accountInfo = accountInfoDao.selectOneForUpdate(accountInfoQuery);
			if(accountInfo==null){
				return ResponseUtils.buildFailureResponse(TradeConstants.PAYMENT_ACCOUNT_NOT_EXIT);
			}
			return ResponseUtils.buildSuccessResponse(accountInfo);
		} catch (Exception e) {
		AttachmentResponse<AccountInfo> response = ResponseUtils
				.buildExceptionResponse();
		logger.error(response.getReturnMsg(), e);
		return response;
	}
	}
	
	 /**
		* 1007	账户冻结提现接口
		* @param accountSn 账户资金冻结流水号
		* @param bankId 银行id
		* @param bankAccount 银行账户   查询平台id时根据bankAccount来查
		* @param note 备注
		* @return AttachmentResponse<FrozenTxResponse>    返回类型
		* @author wangpeng@cssweb.sh.cn
		* @date 2014-7-25 下午2:26:04 
		* @throws
		* @return 1206  流水号不存在
		* @return 1207  重复请求
		 */
		public AttachmentResponse<FrozenTxResponse> accountFrozenTx(Long accountSn, String bankId, String bankAccount, String note) {
			AccountOperateBuilder accountOperateBuilder = newAccountOperateBuilder();
			
			//查询账号操作流水对象
			AccountOperInfo oldAccountOperInfo = new AccountOperInfo();
			oldAccountOperInfo.setAccountSn(accountSn);
			oldAccountOperInfo = accountOperInfoDao.selectOne(oldAccountOperInfo);
			if(oldAccountOperInfo==null){
				return ResponseUtils.buildFailureResponse(TradeConstants.ACCOUNT_SN_NOT_EXIT);
			}
			//防重复提交
			AccountOperInfo accountInfo = new AccountOperInfo();
			accountInfo.setTxnSeq(oldAccountOperInfo.getTxnSeq());
			//提现
			String txn = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_WITHDRAW);
			accountInfo.setTxnType(txn);
			accountInfo = accountOperInfoDao.selectOne(accountInfo);
			if(accountInfo!=null){
				return ResponseUtils.buildFailureResponse(TradeConstants.REPEATED_REQUEST);
			}
			BigDecimal frozenAmount = oldAccountOperInfo.getAmount();
			//手续费
			BigDecimal freeAmount = oldAccountOperInfo.getFee();
			
			AttachmentResponse<AccountInfo> response = getPaymentAccountByBankAccount(bankAccount);
			AccountInfo paymentAccountInfo = response.getAttachment();
			//记录总金额     可提现   + 不可提现   + 冻结
			BigDecimal oldPaymentAccountTotal = paymentAccountInfo.getAvailableBalance().add(paymentAccountInfo.getNomentionAmount())
					.add(paymentAccountInfo.getFrozenAmount());
			
			// 获取出金账户信息
			AttachmentResponse<AccountInfo> toAccountResponse = accountServiceSupport.getAccountInfoWidthMACVerify(oldAccountOperInfo.getToAccountId());			
			if (!toAccountResponse.isSuccess()) {
				return ResponseUtils.buildFailureResponse(toAccountResponse.getReturnCode());
			}
			AccountInfo toAccountInfo = toAccountResponse.getAttachment();
			//记录总金额
			BigDecimal oldFromAccountTotal = toAccountInfo.getAvailableBalance().add(toAccountInfo.getNomentionAmount())
					.add(toAccountInfo.getFrozenAmount());
			
			//判断冻结金额是否足够
			if(toAccountInfo.getFrozenAmount().compareTo(oldAccountOperInfo.getAmount().add(oldAccountOperInfo.getFee()))==-1){
				return ResponseUtils.buildFailureResponse(TradeConstants.FROZENAMOUNT_NOT_ENOUGH);
			}
			
			//查询手续费平台账户
			Long account_platfrom_free = DictionaryUtils.getLongValue(DictionaryKey.ACCOUNT_PLATFORM_FEE);
			AttachmentResponse<AccountInfo> freeAccountResponse = accountServiceSupport.getAccountInfoWidthMACVerify(account_platfrom_free);
			AccountInfo freeAccount = freeAccountResponse.getAttachment();
			//记录总结额供资金明细变化表中使用
			BigDecimal oldFreeTotal = freeAccount.getAvailableBalance().add(freeAccount.getNomentionAmount())
					.add(freeAccount.getFrozenAmount());
			
			//获取账号流水号
			AttachmentResponse<Long> idGeneratorResponse = idGeneratorService
					.generate(AccountOperInfo.class.getName());
			Long newAccountSn = idGeneratorResponse.getAttachment();
			
			//判断手续费是否为空,计算总变动金额    冻结金额（不包含手续费）+ 手续费
			if(freeAmount==null){
				freeAmount = BigDecimal.ZERO;
			}
			BigDecimal changeAmount = frozenAmount.add(freeAmount);
			
			//更新出金账户信息表
			//冻结金额   扣除   
			toAccountInfo.setFrozenAmount(toAccountInfo.getFrozenAmount().subtract(changeAmount));
			toAccountInfo.setMac("mac");//TODO
			toAccountInfo.setUpdateDatetime(new Date());
			//更新方法
			accountOperateBuilder.addAccountInfo(toAccountInfo);
			
			//更新平台账号信息表   冻结金额 + 手续费
			//可用余额  减少
			paymentAccountInfo.setAvailableBalance(paymentAccountInfo.getAvailableBalance().subtract(changeAmount));
			paymentAccountInfo.setMac("mac");//TODO
			paymentAccountInfo.setUpdateDatetime(new Date());
			//更新方法
			accountOperateBuilder.addAccountInfo(paymentAccountInfo);
			
			//增加账号操作流水表
			AccountOperInfo newAccountOperInfo = new AccountOperInfo();
			//记录序号，和产生的流水号一致
			newAccountOperInfo.setAccountSn(newAccountSn);
			newAccountOperInfo.setInterfaceName("accountFrozenTx");
			//来源    PC：电脑端来源
			String sourceType = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);
			newAccountOperInfo.setSource(sourceType);
			//出金账户
			newAccountOperInfo.setFromAccountId(toAccountInfo.getAccountId());
			//入金账户   提现不涉及入金账户
			//newAccountOperInfo.setToAccountId(freeAccount.getAccountId());
			//变化金额
			newAccountOperInfo.setAmount(frozenAmount);
			newAccountOperInfo.setMemo(note);
			if(freeAmount!=null){
				//手续费
				newAccountOperInfo.setFee(freeAmount);
			}
			//交易流水号  （查询得来）
			newAccountOperInfo.setTxnSeq(oldAccountOperInfo.getTxnSeq());
			//交易类型            “冻结提现” 对应的类型是    提现
			String txnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_WITHDRAW);
			newAccountOperInfo.setTxnType(txnType);
			newAccountOperInfo.setBankId(bankId);
			newAccountOperInfo.setBankAccount(bankAccount);
			//状态   正常
			String operInfoStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
			newAccountOperInfo.setStatus(operInfoStatus);
			//冲正标志   未冲正
			String reverseFlag = DictionaryUtils.getStringValue(DictionaryKey.REVERSE_FLAG_NO);
			newAccountOperInfo.setReverseFlag(reverseFlag);
			newAccountOperInfo.setUpdateDateTime(new Date());
			accountOperateBuilder.addAccountOperInfo(newAccountOperInfo);
			
			//增加出金账户 操作明细表
			AccountOperDetail fromAccountOperDetail = new AccountOperDetail();
			//id随机产生
			AttachmentResponse<Long> fromAccountOperDetailRespinse = idGeneratorService
					.generate(AccountOperDetail.class.getName());
			fromAccountOperDetail.setId(fromAccountOperDetailRespinse.getAttachment());
			//主记录序号,与账户操作记录关联
			fromAccountOperDetail.setAccountSn(newAccountSn);
			//出入金标志   出账
			Integer fromIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
			fromAccountOperDetail.setIoFlag(fromIoFlag);
			fromAccountOperDetail.setAccountId(toAccountInfo.getAccountId());
			//出入金位置  冻结金额
			String fromAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_FROZEN);
			fromAccountOperDetail.setAccountPosition(fromAccountPosition); 
			//变化金额 
			fromAccountOperDetail.setAmount(frozenAmount);
			//状态    正常
			String fromDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			fromAccountOperDetail.setStatus(fromDetalStatus);
			fromAccountOperDetail.setUpdateDatetime(new Date());
			//插入操作
			accountOperateBuilder.addAccountOperDetail(fromAccountOperDetail);
			
			//出金账户的手续费  出金  账户操作明细表
			if((freeAmount!=null)&&(freeAmount.compareTo(BigDecimal.ZERO)!=0)){
				AccountOperDetail fromFreeAccountOperDetail = new AccountOperDetail();
				//id随机产生
				AttachmentResponse<Long> fromFreeAccountOperDetailRespinse = idGeneratorService
						.generate(AccountOperDetail.class.getName());
				fromFreeAccountOperDetail.setId(fromFreeAccountOperDetailRespinse.getAttachment());
				//主记录序号,与账户操作记录关联
				fromFreeAccountOperDetail.setAccountSn(newAccountSn);
				//出入金标志   出金
				Integer freeIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
				fromFreeAccountOperDetail.setIoFlag(freeIoFlag);
				fromFreeAccountOperDetail.setAccountId(toAccountInfo.getAccountId());
				//出入金位置   冻结金额
				String freeAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_FROZEN);
				fromFreeAccountOperDetail.setAccountPosition(freeAccountPosition);
				//变化金额 
				fromFreeAccountOperDetail.setAmount(freeAmount);
				//状态   正常
				String freeDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
				fromFreeAccountOperDetail.setStatus(freeDetalStatus);
				fromFreeAccountOperDetail.setUpdateDatetime(new Date());
				//插入操作
				accountOperateBuilder.addAccountOperDetail(fromFreeAccountOperDetail);
			}
			
			//增加平台账号  操作明细表
			AccountOperDetail paymentAccountOperDetail = new AccountOperDetail();
			//id随机产生
			AttachmentResponse<Long> paymentAccountOperDetailRespinse = idGeneratorService
					.generate(AccountOperDetail.class.getName());
			paymentAccountOperDetail.setId(paymentAccountOperDetailRespinse.getAttachment());
			//主记录序号,与账户操作记录关联
			paymentAccountOperDetail.setAccountSn(newAccountSn);
			//出入金标志   出金
			Integer paymentIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
			paymentAccountOperDetail.setIoFlag(paymentIoFlag);
			paymentAccountOperDetail.setAccountId(paymentAccountInfo.getAccountId());
			//出入金位置    可提现金额
			String paymentAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
			paymentAccountOperDetail.setAccountPosition(paymentAccountPosition);
			//变化金额 
			paymentAccountOperDetail.setAmount(changeAmount);
			//状态   正常
			String paymentDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			paymentAccountOperDetail.setStatus(paymentDetalStatus);
			paymentAccountOperDetail.setUpdateDatetime(new Date());
			//插入操作
			accountOperateBuilder.addAccountOperDetail(paymentAccountOperDetail);
			
			//增加出金账户     提现资金明细表(不包括手续费)
			AccountMoneyChgDetail fromAccountTXMoneyChgDetail = new AccountMoneyChgDetail();
			//自动生成id
			AttachmentResponse<Long> fromAccountTXMoneyChgDetailRespinse = idGeneratorService
					.generate(AccountMoneyChgDetail.class.getName());
			//主键id
			fromAccountTXMoneyChgDetail.setId(fromAccountTXMoneyChgDetailRespinse.getAttachment());
			//主记录序号，与账户操作关联
			fromAccountTXMoneyChgDetail.setAccountSn(newAccountSn);
			fromAccountTXMoneyChgDetail.setUserId(toAccountInfo.getUserId());
			fromAccountTXMoneyChgDetail.setAccountId(toAccountInfo.getAccountId());
			fromAccountTXMoneyChgDetail.setAccountType(toAccountInfo.getAccountType());
			//变化金额
			fromAccountTXMoneyChgDetail.setChgAccount(frozenAmount);
			//变化前金额  
			fromAccountTXMoneyChgDetail.setOldBalance(oldFromAccountTotal);
			//变化后余额  可提现+不可提现+冻结金额  + 手续费  ，这个时候还不记录手续费
			fromAccountTXMoneyChgDetail.setBalance(toAccountInfo.getAvailableBalance().add(toAccountInfo.getNomentionAmount())
					.add(toAccountInfo.getFrozenAmount()).add(freeAmount));
			//进出帐标志    出金
			Integer fromMoneyIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
			fromAccountTXMoneyChgDetail.setIoFlag(fromMoneyIoFlag);
			fromAccountTXMoneyChgDetail.setChangeDateTime(new Date());
			//交易类型            “冻结提现” 对应的类型是    提现
			String fromMoneyTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_WITHDRAW);
			fromAccountTXMoneyChgDetail.setTxnType(fromMoneyTxnType);
			//状态      正常
			String fromMoneyDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			fromAccountTXMoneyChgDetail.setStatus(fromMoneyDetalStatus);
			fromAccountTXMoneyChgDetail.setUpdateDateTime(new Date());
			//插入数据
			accountOperateBuilder.addAccountMoneyChgDetail(fromAccountTXMoneyChgDetail);
			
			//出金账户手续费       资金变动明细表
			if((freeAmount!=null)&&(freeAmount.compareTo(BigDecimal.ZERO)!=0)){
				AccountMoneyChgDetail fromAccountFeeMoneyChgDetail = new AccountMoneyChgDetail();
				//自动生成id
				AttachmentResponse<Long> fromAccountFeeMoneyChgDetailRespinse = idGeneratorService
						.generate(AccountMoneyChgDetail.class.getName());
				//主键id
				fromAccountFeeMoneyChgDetail.setId(fromAccountFeeMoneyChgDetailRespinse.getAttachment());
				//主记录序号，与账户操作关联
				fromAccountFeeMoneyChgDetail.setAccountSn(newAccountSn);
				fromAccountFeeMoneyChgDetail.setUserId(toAccountInfo.getUserId());
				fromAccountFeeMoneyChgDetail.setAccountId(toAccountInfo.getAccountId());
				fromAccountFeeMoneyChgDetail.setAccountType(toAccountInfo.getAccountType());
				//变化金额
				fromAccountFeeMoneyChgDetail.setChgAccount(freeAmount);
				//变化前金额   可提现+不可提现+冻结金额+手续费
				fromAccountFeeMoneyChgDetail.setOldBalance(toAccountInfo.getAvailableBalance().add(toAccountInfo.getNomentionAmount())
						.add(toAccountInfo.getFrozenAmount()).add(freeAmount));
				//变化后余额  可提现+不可提现+冻结金额
				fromAccountFeeMoneyChgDetail.setBalance(toAccountInfo.getAvailableBalance().add(toAccountInfo.getNomentionAmount())
						.add(toAccountInfo.getFrozenAmount()));
				//进出帐标志    出金
				Integer fromMoneyFeeIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
				fromAccountFeeMoneyChgDetail.setIoFlag(fromMoneyFeeIoFlag);
				fromAccountFeeMoneyChgDetail.setChangeDateTime(new Date());
				//交易类型           手续费
				String fromMoneyFeeTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_FEE);
				fromAccountFeeMoneyChgDetail.setTxnType(fromMoneyFeeTxnType);
				//状态      正常
				String fromMoneyFeeDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
				fromAccountFeeMoneyChgDetail.setStatus(fromMoneyFeeDetalStatus);
				fromAccountFeeMoneyChgDetail.setUpdateDateTime(new Date());
				//插入数据
				accountOperateBuilder.addAccountMoneyChgDetail(fromAccountFeeMoneyChgDetail);
		    }
			
			//增加平台账号   资金明细表
			AccountMoneyChgDetail paymentAccountMoneyChgDetail = new AccountMoneyChgDetail();
			//自动生成id
			AttachmentResponse<Long> paymentAccountMoneyChgDetailRespinse = idGeneratorService
					.generate(AccountMoneyChgDetail.class.getName());
			//主键id
			paymentAccountMoneyChgDetail.setId(paymentAccountMoneyChgDetailRespinse.getAttachment());
			//主记录序号，与账户操作关联
			paymentAccountMoneyChgDetail.setAccountSn(newAccountSn);
			paymentAccountMoneyChgDetail.setUserId(paymentAccountInfo.getUserId());
			paymentAccountMoneyChgDetail.setAccountId(paymentAccountInfo.getAccountId());
			paymentAccountMoneyChgDetail.setAccountType(paymentAccountInfo.getAccountType());
			//变化金额
			paymentAccountMoneyChgDetail.setChgAccount(changeAmount);
			//变化前金额   
			paymentAccountMoneyChgDetail.setOldBalance(oldPaymentAccountTotal);
			//变化后余额  可提现+不可提现+冻结金额
			paymentAccountMoneyChgDetail.setBalance(paymentAccountInfo.getAvailableBalance().add(paymentAccountInfo.getNomentionAmount())
					.add(paymentAccountInfo.getFrozenAmount()));
			//进出帐标志     出账
			Integer paymentMoneyIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
			paymentAccountMoneyChgDetail.setIoFlag(paymentMoneyIoFlag);
			paymentAccountMoneyChgDetail.setChangeDateTime(new Date());
			//交易类型            “冻结提现” 对应的类型是    提现
			String paymentMoneyTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_WITHDRAW);
			paymentAccountMoneyChgDetail.setTxnType(paymentMoneyTxnType);
			//状态    正常
			String paymentMoneyDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			paymentAccountMoneyChgDetail.setStatus(paymentMoneyDetalStatus);
			paymentAccountMoneyChgDetail.setUpdateDateTime(new Date());
			//插入数据
			accountOperateBuilder.addAccountMoneyChgDetail(paymentAccountMoneyChgDetail);
			
			//判断是否要修改手续费平台信息
			if((freeAmount!=null)&&(freeAmount.compareTo(BigDecimal.ZERO)!=0)){
				//更新手续费账户信息
				freeAccount.setAvailableBalance(freeAccount.getAvailableBalance().add(freeAmount));
				//TODO 校验串
				freeAccount.setMac("mac");
				freeAccount.setUpdateDatetime(new Date());
				//更新方法
				accountOperateBuilder.addAccountInfo(freeAccount);
				
				//插入账户操作明细表
				AccountOperDetail freeAccountOperDetail = new AccountOperDetail();
				//id随机产生
				AttachmentResponse<Long> freeAccountOperDetailRespinse = idGeneratorService
						.generate(AccountOperDetail.class.getName());
				freeAccountOperDetail.setId(freeAccountOperDetailRespinse.getAttachment());
				//主记录序号,主记录序号，与账户操作记录关联
				freeAccountOperDetail.setAccountSn(newAccountSn);
				//出入金标志   入金
				Integer freeIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
				freeAccountOperDetail.setIoFlag(freeIoFlag);
				freeAccountOperDetail.setAccountId(freeAccount.getAccountId());
				//出入金位置    可提现金额
				String freeAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
				freeAccountOperDetail.setAccountPosition(freeAccountPosition);
				//变化金额 
				freeAccountOperDetail.setAmount(freeAmount);
				//状态   正常
				String freeDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
				freeAccountOperDetail.setStatus(freeDetalStatus);
				freeAccountOperDetail.setUpdateDatetime(new Date());
				//插入操作
				accountOperateBuilder.addAccountOperDetail(freeAccountOperDetail);
				
				//增加手续费 资金变动明细表
				AccountMoneyChgDetail freeAccountMoneyChgDetail = new AccountMoneyChgDetail();
				//自动生成id
				AttachmentResponse<Long> freeAccountMoneyChgDetailRespinse = idGeneratorService
						.generate(AccountMoneyChgDetail.class.getName());
				//主键id
				freeAccountMoneyChgDetail.setId(freeAccountMoneyChgDetailRespinse.getAttachment());
				//主记录序号，与账户操作关联
				freeAccountMoneyChgDetail.setAccountSn(newAccountSn);
				freeAccountMoneyChgDetail.setUserId(freeAccount.getUserId());
				freeAccountMoneyChgDetail.setAccountId(freeAccount.getAccountId());
				freeAccountMoneyChgDetail.setAccountType(freeAccount.getAccountType());
				//变化金额
				freeAccountMoneyChgDetail.setChgAccount(freeAmount);
				//变化前金额   
				freeAccountMoneyChgDetail.setOldBalance(oldFreeTotal);
				//变化后余额  可提现+不可提现+冻结金额
				freeAccountMoneyChgDetail.setBalance(freeAccount.getAvailableBalance().add(freeAccount.getNomentionAmount())
						.add(freeAccount.getFrozenAmount()));
				//进出帐标志     入账
				Integer freeMoneyIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
				freeAccountMoneyChgDetail.setIoFlag(freeMoneyIoFlag);
				freeAccountMoneyChgDetail.setChangeDateTime(new Date());
				//交易类型         手续费
				String freeMoneyTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_FEE);
				freeAccountMoneyChgDetail.setTxnType(freeMoneyTxnType);
				//状态    正常
				String freeMoneyDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
				freeAccountMoneyChgDetail.setStatus(freeMoneyDetalStatus);
				freeAccountMoneyChgDetail.setUpdateDateTime(new Date());
				//插入数据
				accountOperateBuilder.addAccountMoneyChgDetail(freeAccountMoneyChgDetail);
			}
			accountOperate(accountOperateBuilder);
			FrozenTxResponse frozenResponse = new FrozenTxResponse();
			frozenResponse.setAccountSn(newAccountSn);
			frozenResponse.setCashAmount(frozenAmount);
			frozenResponse.setAccountId(toAccountInfo.getAccountId());
			frozenResponse.setFeeAccountId(account_platfrom_free);
			return ResponseUtils.buildSuccessResponse(frozenResponse);
	}
	
		/**
		 * 1008	账户资金冻结解冻接口
		* @param accountSn 账户资金冻结流水号
		* @param note 备注
		* @return AttachmentResponse<FrozenResponse> 返回类型
		* @author wangpeng@cssweb.sh.cn
		* @date 2014-7-26 上午10:06:38 
		* @throws
		* @return 1206  流水号不存在
		* @return 1207     重复请求
		* @return 1300  账户不存在
		* @return 1301 参数为null
		 */
		public AttachmentResponse<FrozenResponse> accountUnfrozen(Long accountSn, String note) {
			AccountOperateBuilder accountOperateBuilder = newAccountOperateBuilder();
			//1、根据操作流水表，验证流水号
				AccountOperInfo oldAccountOperInfo = new AccountOperInfo();
				oldAccountOperInfo.setAccountSn(accountSn);
			    //2、根据资金流水号，查询账号操作流水对象
				oldAccountOperInfo = accountOperInfoDao.selectOne(oldAccountOperInfo);
				if(oldAccountOperInfo==null){
					return ResponseUtils.buildFailureResponse(TradeConstants.ACCOUNT_SN_NOT_EXIT);
				}
				AccountOperInfo accountInfo = new AccountOperInfo();
				accountInfo.setTxnSeq(oldAccountOperInfo.getTxnSeq());
				String txn = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_UNFROZEN);
				accountInfo.setTxnType(txn);
				accountInfo = accountOperInfoDao.selectOne(accountInfo);
				if(accountInfo!=null){
					return ResponseUtils.buildFailureResponse(TradeConstants.REPEATED_REQUEST);
				}
				//记录冻结金额数
				BigDecimal oldFrozenAmount = oldAccountOperInfo.getAmount();
				
			//3、根据流水号，返回账号操作明细集合
				AccountOperDetail accountOperDetail = new AccountOperDetail();
				accountOperDetail.setAccountSn(accountSn);
				List<AccountOperDetail> list = accountOperDetailDao.selectList(accountOperDetail);
				
			//4、根据流水表中的fromcountid查询账号信息
				AttachmentResponse<AccountInfo> toAccountResponse =  accountServiceSupport.getAccountInfoWidthMACVerify(oldAccountOperInfo.getToAccountId());
				if (!toAccountResponse.isSuccess()) {
					return ResponseUtils.buildFailureResponse(toAccountResponse.getReturnCode());
				}
				AccountInfo toAccountInfo = toAccountResponse.getAttachment();
				
				//判断冻结金额是否足够，如果不够，提示账户异常
				if(toAccountInfo.getFrozenAmount().compareTo(oldFrozenAmount)==-1){
					return ResponseUtils.buildFailureResponse(TradeConstants.FROZENAMOUNT_NOT_ENOUGH);
				}
				
			//5、根据id自动生成器，返回账号流水号   
				AttachmentResponse<Long> idGeneratorResponse = idGeneratorService
						.generate(AccountOperInfo.class.getName());
				Long newAccountSn = idGeneratorResponse.getAttachment();
				String avalibalePosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
				String nomentionPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_NOMENTION);
			//6、更新账号信息 ，可提现金额，不可提现金额，（根据资金位置来确定），冻结金额   遍历操作明细表的数据，如果变化的位置为可用，则可以增加变化金额，
				//如果变化的位置为不可提现，则不可提现增加金额，冻结金额扣除相应的数    
				//第三种情况就是如果查出来的是三条明细数据的话，两个if语句都会执行
				for(int i=0;i<list.size();i++){
					//出金账户位置，01：可提现金额，02：冻结金额，03：不可提现金额
					//如果查询的位置是可提现，就增加可提现的金额，扣减冻结金额
					//账户位置     可提现金额
					if(list.get(i).getAccountPosition().equals(avalibalePosition)){
						toAccountInfo.setAvailableBalance(toAccountInfo.getAvailableBalance().add(list.get(i).getAmount()));
						toAccountInfo.setFrozenAmount(toAccountInfo.getFrozenAmount().subtract(list.get(i).getAmount()));
					}
					//如果查询的位置是不可提现，就增加不可提现的金额，扣减冻结金额
					if(list.get(i).getAccountPosition().equals(nomentionPosition)){
						toAccountInfo.setNomentionAmount(toAccountInfo.getNomentionAmount().add(list.get(i).getAmount()));
						toAccountInfo.setFrozenAmount(toAccountInfo.getFrozenAmount().subtract(list.get(i).getAmount()));
					}
				}
				//更新操作
				accountOperateBuilder.addAccountInfo(toAccountInfo);
				
			//9、增加操作流水表记录   入金账户
				AccountOperInfo newAccountOperInfo = new AccountOperInfo();
				//记录序号，和产生的流水号一致
				newAccountOperInfo.setAccountSn(newAccountSn);
				newAccountOperInfo.setInterfaceName("accountUnfrozen");
				//来源    电脑端来源
				String sourceType = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);
				newAccountOperInfo.setSource(sourceType);
				newAccountOperInfo.setFromAccountId(toAccountInfo.getAccountId());
				newAccountOperInfo.setToAccountId(toAccountInfo.getAccountId());
				//变化金额
				newAccountOperInfo.setAmount(oldFrozenAmount);
				//手续费设为0
				newAccountOperInfo.setFee(BigDecimal.ZERO);
				//备注（入参）
				newAccountOperInfo.setMemo(note);
				//交易流水号   查询得来
				newAccountOperInfo.setTxnSeq(oldAccountOperInfo.getTxnSeq());
				//交易类型  冻结解冻
			    String txnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_UNFROZEN);
				newAccountOperInfo.setTxnType(txnType);
				//银行id 没有的话就不插
				//accountOperInfo2.setBankId();
				//状态   正常
				String operInfoStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
				newAccountOperInfo.setStatus(operInfoStatus);
				//冲正标志    未冲正
				String reverseFlag = DictionaryUtils.getStringValue(DictionaryKey.REVERSE_FLAG_NO);
				newAccountOperInfo.setReverseFlag(reverseFlag);
				newAccountOperInfo.setUpdateDateTime(new Date());
				accountOperateBuilder.addAccountOperInfo(newAccountOperInfo);
				
			//10、增加操作明细记录，根据查询出来的明细来插入，在for循环里new对象，如果是2条就会new两个对象，3条就会new3个对象，根据账号位置确定进出金标志，其他的例如账号位置，变化金额都不变
				for(int i=0;i<list.size();i++){
					AccountOperDetail newAccountOperDetail =  new AccountOperDetail();
					//id随机产生
					AttachmentResponse<Long> newAccountOperDetailRespinse = idGeneratorService
							.generate(AccountOperDetail.class.getName());
					newAccountOperDetail.setId(newAccountOperDetailRespinse.getAttachment());
					//主记录序号,主记录序号，与账户操作记录关联
					newAccountOperDetail.setAccountSn(newAccountSn);
					//如果原操作明细里是出金标志，现在就插入入金标志，反之；
					  //入金
					Integer toIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
					  //出金
					Integer fromIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
					if(list.get(i).getIoFlag().equals(fromIoFlag)){
						//出入金标志
						newAccountOperDetail.setIoFlag(toIoFlag);
					}else{
						//出入金标志
						newAccountOperDetail.setIoFlag(fromIoFlag);
					}
					//账户ID
					newAccountOperDetail.setAccountId(list.get(i).getAccountId());
					//出入金位置   //根据读取的流水表里的出入金位置，位置不变
					newAccountOperDetail.setAccountPosition(list.get(i).getAccountPosition());
					//变化金额 
					newAccountOperDetail.setAmount(list.get(i).getAmount());
					//状态      正常
					String detalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
					newAccountOperDetail.setStatus(detalStatus);
					newAccountOperDetail.setUpdateDatetime(new Date());
					//插入操作
					accountOperateBuilder.addAccountOperDetail(newAccountOperDetail);
				}
				//事务操作
				accountOperate(accountOperateBuilder);
				FrozenResponse frozenResponse = new FrozenResponse();
				frozenResponse.setAccountSn(newAccountSn);
				frozenResponse.setCashAmount(oldFrozenAmount);
				frozenResponse.setAccountid(toAccountInfo.getAccountId());
				return ResponseUtils.buildSuccessResponse(frozenResponse);
		}
		
		/**
		* 1097 账户充值支付转账接口  
		* @param  fromAccountId  充值支出/买家账户ID
		* @param  toAccountId   收入/卖家账户ID
		* @param  tradeSn  交易流水号
		* @param  cashAmount  充值转账金额
		* @param  bankid 银行id
		* @param  note 备注
		* @param @return   
		* @return AttachmentResponse<Long>
		* @author wangpeng@cssweb.sh.cn
		* @date 2014-7-30 下午2:54:32 
		* @throws
		* @return 1209 收款方和付款方不能相同
		* @return 1301 参数为null
		* @return 1207 重复请求
		* @return 1300 账户不存在
		 */
		public AttachmentResponse<Long> accountRechargePay(Long fromAccountId, Long toAccountId, String tradeSn,
				BigDecimal cashAmount, String bankId, String bankAccount, String note){
			AccountOperateBuilder accountOperateBuilder = newAccountOperateBuilder();
			//1、根据fromId 查找 出金账户信息 含校验码
			AttachmentResponse<AccountInfo> fromAccountInfoResponse = accountServiceSupport.getAccountInfoWidthMACVerify(fromAccountId);			
			//账户不存在,返回错误信息
			if (!fromAccountInfoResponse.isSuccess()) {
				return ResponseUtils.buildFailureResponse(fromAccountInfoResponse.getReturnCode());
			}
		    //根据账号id查询账号信息   
			AccountInfo fromAccountInfo = fromAccountInfoResponse.getAttachment();
			//记录总金额 供资金明细表中使用      总金额 = 可提现金额 + 不可提现金额 + 冻结金额
			BigDecimal fromAcountTotal = fromAccountInfo.getAvailableBalance().add(fromAccountInfo.getNomentionAmount())
					.add(fromAccountInfo.getFrozenAmount());
			
			//2、根据toId 查找入金账户信息 含校验码
			AttachmentResponse<AccountInfo> toAccountInfoResponse = accountServiceSupport.getAccountInfoWidthMACVerify(toAccountId);			
			//账户不存在,返回错误信息
			if (!toAccountInfoResponse.isSuccess()) {
				return ResponseUtils.buildFailureResponse(toAccountInfoResponse.getReturnCode());
			}
		    //根据账号id查询账号信息   
			AccountInfo toAccountInfo = toAccountInfoResponse.getAttachment();
			//记录总金额 供资金明细表中使用      总金额 = 可提现金额 + 不可提现金额 + 冻结金额
			BigDecimal toAcountTotal = toAccountInfo.getAvailableBalance().add(toAccountInfo.getNomentionAmount())
					.add(toAccountInfo.getFrozenAmount());
			
			//3、根据银行bankAccount 查找平台账号信息
			AttachmentResponse<AccountInfo> peymentResponse = getPaymentAccountByBankAccount(bankAccount);
			if(!peymentResponse.isSuccess()){
				return ResponseUtils.buildFailureResponse(peymentResponse.getReturnCode());
			}
			AccountInfo paymentAccountInfo = peymentResponse.getAttachment();
			//记录总金额 供资金明细表中使用      总金额 = 可提现金额 + 不可提现金额 + 冻结金额
			BigDecimal paymentAcountTotal = paymentAccountInfo.getAvailableBalance().add(paymentAccountInfo.getNomentionAmount())
					.add(paymentAccountInfo.getFrozenAmount());
			
			//4、获取流水号
			AttachmentResponse<Long> rechargeIdGeneratorResponse = idGeneratorService
					.generate(AccountOperInfo.class.getName());
			Long newRechargeAccountSn = rechargeIdGeneratorResponse.getAttachment();
			
			//5、更新平台账号信息   可提现金额（加）   校验串  更新时间
			paymentAccountInfo.setAvailableBalance(paymentAccountInfo.getAvailableBalance().add(cashAmount));
			//TODO  
			paymentAccountInfo.setMac("mac");
			paymentAccountInfo.setUpdateDatetime(new Date());
			accountOperateBuilder.addAccountInfo(paymentAccountInfo);
			
			//6、更新fromId账号信息      可提现金额 （加，此时是充值操作，可提现金额增加） 校验串   更新时间
			fromAccountInfo.setAvailableBalance(fromAccountInfo.getAvailableBalance().add(cashAmount));
			fromAccountInfo.setMac("mac");//TODO
			fromAccountInfo.setUpdateDatetime(new Date());
			accountOperateBuilder.addAccountInfo(fromAccountInfo);
			//记录更新后的总金额，提供给转账的资金变化明细表使用
			BigDecimal rechargeFromAccountTotal = fromAccountInfo.getAvailableBalance().add(fromAccountInfo.getNomentionAmount())
					.add(fromAccountInfo.getFrozenAmount());
			
			//7、增加操作流水表
			AccountOperInfo rechargeAccountOperInfo = new AccountOperInfo();
			//记录序号，和产生的流水号一致
			rechargeAccountOperInfo.setAccountSn(newRechargeAccountSn);
			rechargeAccountOperInfo.setInterfaceName("accountRechargePay");
			//来源 电脑端来源
			String sourceType = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);
			rechargeAccountOperInfo.setSource(sourceType);
			//出金账户  无
			//accountOperInfo.setFromAccountId(fromAccountInfo.getAccountId());
			rechargeAccountOperInfo.setToAccountId(fromAccountInfo.getAccountId());
			//变化金额
			rechargeAccountOperInfo.setAmount(cashAmount);
			//手续费设为0
			rechargeAccountOperInfo.setFee(BigDecimal.ZERO);
			rechargeAccountOperInfo.setMemo(note);
			//交易流水号  
			rechargeAccountOperInfo.setTxnSeq(tradeSn);
			//交易类型 变化原因：    支付充值
			String txnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_PAYTOPUP);
			rechargeAccountOperInfo.setTxnType(txnType);  
			rechargeAccountOperInfo.setBankId(bankId);
			rechargeAccountOperInfo.setBankAccount(bankAccount);
			//状态      正常
			String operInfoStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
			rechargeAccountOperInfo.setStatus(operInfoStatus);
			//冲正标志    未冲正
			String reverseFlag = DictionaryUtils.getStringValue(DictionaryKey.REVERSE_FLAG_NO);
			rechargeAccountOperInfo.setReverseFlag(reverseFlag);
			rechargeAccountOperInfo.setUpdateDateTime(new Date());
			//插入操作
			accountOperateBuilder.addAccountOperInfo(rechargeAccountOperInfo);
			
			//8、增加fromId 操作明细表
			AccountOperDetail rechargeFromAccountOperDetail =  new AccountOperDetail();
			//id随机产生
			AttachmentResponse<Long> rechargeFromAccountOperDetailRespinse = idGeneratorService
					.generate(AccountOperDetail.class.getName());
			rechargeFromAccountOperDetail.setId(rechargeFromAccountOperDetailRespinse.getAttachment());
			//主记录序号,主记录序号，与账户操作记录关联
			rechargeFromAccountOperDetail.setAccountSn(newRechargeAccountSn);
			//出入金标志   入金
			Integer fromIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
			rechargeFromAccountOperDetail.setIoFlag(fromIoFlag);
			rechargeFromAccountOperDetail.setAccountId(fromAccountInfo.getAccountId());
			//出入金位置         可提现金额
			String avaAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
			rechargeFromAccountOperDetail.setAccountPosition(avaAccountPosition);
			//变化金额 
			rechargeFromAccountOperDetail.setAmount(cashAmount);
			//状态     正常
			String detalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			rechargeFromAccountOperDetail.setStatus(detalStatus);
			rechargeFromAccountOperDetail.setUpdateDatetime(new Date());
			//插入操作
			accountOperateBuilder.addAccountOperDetail(rechargeFromAccountOperDetail);
			
			//9、增加平台账号  操作明细表
			AccountOperDetail rechargePaymentAccountOperDetail =  new AccountOperDetail();
			//id随机产生
			AttachmentResponse<Long> rechargePaymentAccountOperDetailRespinse = idGeneratorService
					.generate(AccountOperDetail.class.getName());
			rechargePaymentAccountOperDetail.setId(rechargePaymentAccountOperDetailRespinse.getAttachment());
			//主记录序号,主记录序号，与账户操作记录关联
			rechargePaymentAccountOperDetail.setAccountSn(newRechargeAccountSn);
			//出入金标志   入金
			Integer paymentIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
			rechargePaymentAccountOperDetail.setIoFlag(paymentIoFlag);
			rechargePaymentAccountOperDetail.setAccountId(paymentAccountInfo.getAccountId());
			//出入金位置         可提现金额
			String paymentAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
			rechargePaymentAccountOperDetail.setAccountPosition(paymentAccountPosition);
			//变化金额 
			rechargePaymentAccountOperDetail.setAmount(cashAmount);
			//状态     正常
			String paymentDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			rechargePaymentAccountOperDetail.setStatus(paymentDetalStatus);
			rechargePaymentAccountOperDetail.setUpdateDatetime(new Date());
			//插入操作
			accountOperateBuilder.addAccountOperDetail(rechargePaymentAccountOperDetail);
			
			//10、增加fromId 资金变动明细表
			AccountMoneyChgDetail rechargefromAccountChgDetail = new AccountMoneyChgDetail();
			//自动生成id
			AttachmentResponse<Long> rechargefromAccountChgDetailRespinse = idGeneratorService
					.generate(AccountMoneyChgDetail.class.getName());
			//主键id
			rechargefromAccountChgDetail.setId(rechargefromAccountChgDetailRespinse.getAttachment());
			//主记录序号，与账户操作关联
			rechargefromAccountChgDetail.setAccountSn(newRechargeAccountSn);
			rechargefromAccountChgDetail.setUserId(fromAccountInfo.getUserId());
			rechargefromAccountChgDetail.setAccountId(fromAccountInfo.getAccountId());
			rechargefromAccountChgDetail.setAccountType(fromAccountInfo.getAccountType());
			//变化金额
			rechargefromAccountChgDetail.setChgAccount(cashAmount);
			//变化前金额    
			rechargefromAccountChgDetail.setOldBalance(fromAcountTotal);
			//变化后余额    
			rechargefromAccountChgDetail.setBalance(fromAccountInfo.getAvailableBalance().add(fromAccountInfo.getNomentionAmount())
					.add(fromAccountInfo.getFrozenAmount()));
			//进出帐标志    入金
			Integer fromMoneyIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
			rechargefromAccountChgDetail.setIoFlag(fromMoneyIoFlag);
			rechargefromAccountChgDetail.setChangeDateTime(new Date());
			//交易  支付充值
		    String rechargeFromTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_PAYTOPUP);
		    rechargefromAccountChgDetail.setTxnType(rechargeFromTxnType);
			//状态  正常
			String fromdetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			rechargefromAccountChgDetail.setStatus(fromdetalStatus);
			rechargefromAccountChgDetail.setUpdateDateTime(new Date());
			//插入数据
			accountOperateBuilder.addAccountMoneyChgDetail(rechargefromAccountChgDetail);
			
			//11、增加平台账号  资金变动明细表
			AccountMoneyChgDetail rechargePaymentChgDetail = new AccountMoneyChgDetail();
			//自动生成id
			AttachmentResponse<Long> rechargePaymentChgDetailRespinse = idGeneratorService
					.generate(AccountMoneyChgDetail.class.getName());
			//主键id
			rechargePaymentChgDetail.setId(rechargePaymentChgDetailRespinse.getAttachment());
			//主记录序号，与账户操作关联
			rechargePaymentChgDetail.setAccountSn(newRechargeAccountSn);
			rechargePaymentChgDetail.setUserId(paymentAccountInfo.getUserId());
			rechargePaymentChgDetail.setAccountId(paymentAccountInfo.getAccountId());
			rechargePaymentChgDetail.setAccountType(paymentAccountInfo.getAccountType());
			//变化金额
			rechargePaymentChgDetail.setChgAccount(cashAmount);
			//变化前金额    
			rechargePaymentChgDetail.setOldBalance(paymentAcountTotal);
			//变化后余额   
			rechargePaymentChgDetail.setBalance(paymentAccountInfo.getAvailableBalance().add(paymentAccountInfo.getNomentionAmount())
					.add(paymentAccountInfo.getFrozenAmount()));
			//进出帐标志    入金
			Integer paymentfromIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
			rechargePaymentChgDetail.setIoFlag(paymentfromIoFlag);
			rechargePaymentChgDetail.setChangeDateTime(new Date());
			// 支付充值
			String rechargePaymentTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_PAYTOPUP);
			rechargePaymentChgDetail.setTxnType(rechargePaymentTxnType);
			//状态  正常
			String paymentdetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			rechargePaymentChgDetail.setStatus(paymentdetalStatus);
			rechargePaymentChgDetail.setUpdateDateTime(new Date());
			//插入数据
			accountOperateBuilder.addAccountMoneyChgDetail(rechargePaymentChgDetail);
			
			//12、获取转账流水号
			AttachmentResponse<Long> transferIdGeneratorResponse = idGeneratorService
					.generate(AccountOperInfo.class.getName());
			Long newTransferAccountSn = transferIdGeneratorResponse.getAttachment();
			
			//13、更新fromId账号信息表 可提现（扣），校验串，更新时间 
			fromAccountInfo.setAvailableBalance(fromAccountInfo.getAvailableBalance().subtract(cashAmount));
			fromAccountInfo.setMac("mac");//TODO
			fromAccountInfo.setUpdateDatetime(new Date());
			accountOperateBuilder.addAccountInfo(fromAccountInfo);
			
			//14、更新toId账号信息表  可提交（加） 
			toAccountInfo.setAvailableBalance(toAccountInfo.getAvailableBalance().add(cashAmount));
			toAccountInfo.setMac("mac");//TODO
			toAccountInfo.setUpdateDatetime(new Date());
			accountOperateBuilder.addAccountInfo(toAccountInfo);
			
			//15、增加操作流水表
			AccountOperInfo transferAccountOperInfo = new AccountOperInfo();
			//记录序号，和产生的流水号一致
			transferAccountOperInfo.setAccountSn(newTransferAccountSn);
			transferAccountOperInfo.setInterfaceName("accountRechargePay");
			//来源 电脑端来源
			String transfersourceType = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);
			transferAccountOperInfo.setSource(transfersourceType);
			transferAccountOperInfo.setFromAccountId(fromAccountInfo.getAccountId());
			transferAccountOperInfo.setToAccountId(toAccountInfo.getAccountId());
			//变化金额
			transferAccountOperInfo.setAmount(cashAmount);
			//手续费设为0
			transferAccountOperInfo.setFee(BigDecimal.ZERO);
			transferAccountOperInfo.setBankId(bankId);
			transferAccountOperInfo.setBankAccount(bankAccount);
			//备注（入参）
			transferAccountOperInfo.setMemo(note);
			//交易流水号  
			transferAccountOperInfo.setTxnSeq(tradeSn);
			//交易类型 变化原因：    支付
			String transferTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_PAY);
			transferAccountOperInfo.setTxnType(transferTxnType);  
			//银行id  入参没有就不插入数据
			//transferAccountOperInfo.setBankId(bankId);
			//状态      正常
			String transferInfoStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
			transferAccountOperInfo.setStatus(transferInfoStatus);
			//冲正标志    未冲正
			String transferReverseFlag = DictionaryUtils.getStringValue(DictionaryKey.REVERSE_FLAG_NO);
			transferAccountOperInfo.setReverseFlag(transferReverseFlag);
			transferAccountOperInfo.setUpdateDateTime(new Date());
			//插入操作
			accountOperateBuilder.addAccountOperInfo(transferAccountOperInfo);
			
			//16、增加fromId 账户操作明细表
			AccountOperDetail transferFromAccountOperDetail =  new AccountOperDetail();
			//id随机产生
			AttachmentResponse<Long> transferFromAccountOperDetailRespinse = idGeneratorService
					.generate(AccountOperDetail.class.getName());
			transferFromAccountOperDetail.setId(transferFromAccountOperDetailRespinse.getAttachment());
			//主记录序号,主记录序号，与账户操作记录关联
			transferFromAccountOperDetail.setAccountSn(newTransferAccountSn);
			//出入金标志   出金
			Integer transferFromIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
			transferFromAccountOperDetail.setIoFlag(transferFromIoFlag);
			transferFromAccountOperDetail.setAccountId(fromAccountInfo.getAccountId());
			//出入金位置         可提现金额
			String transferAvaAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
			transferFromAccountOperDetail.setAccountPosition(transferAvaAccountPosition);
			//变化金额 
			transferFromAccountOperDetail.setAmount(cashAmount);
			//状态     正常
			String transferDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			transferFromAccountOperDetail.setStatus(transferDetalStatus);
			transferFromAccountOperDetail.setUpdateDatetime(new Date());
			//插入操作
			accountOperateBuilder.addAccountOperDetail(transferFromAccountOperDetail);
			
			//17、增加toId 账户操作明细表
			AccountOperDetail transferToAccountOperDetail =  new AccountOperDetail();
			//id随机产生
			AttachmentResponse<Long> transferToAccountOperDetailRespinse = idGeneratorService
					.generate(AccountOperDetail.class.getName());
			transferToAccountOperDetail.setId(transferToAccountOperDetailRespinse.getAttachment());
			//主记录序号,与账户操作记录关联
			transferToAccountOperDetail.setAccountSn(newTransferAccountSn);
			//出入金标志   出金
			Integer transferToIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
			transferToAccountOperDetail.setIoFlag(transferToIoFlag);
			transferToAccountOperDetail.setAccountId(toAccountInfo.getAccountId());
			//出入金位置         可提现金额
			String transferToAvaAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
			transferToAccountOperDetail.setAccountPosition(transferToAvaAccountPosition);
			//变化金额 
			transferToAccountOperDetail.setAmount(cashAmount);
			//状态     正常
			String transferToDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			transferToAccountOperDetail.setStatus(transferToDetalStatus);
			transferToAccountOperDetail.setUpdateDatetime(new Date());
			//插入操作
			accountOperateBuilder.addAccountOperDetail(transferToAccountOperDetail);
			
			//18、增加fromId  资金变动明细表
			AccountMoneyChgDetail transferFromAccountChgDetail = new AccountMoneyChgDetail();
			//自动生成id
			AttachmentResponse<Long> transferFromAccountChgDetailrespinse = idGeneratorService
					.generate(AccountMoneyChgDetail.class.getName());
			//主键id
			transferFromAccountChgDetail.setId(transferFromAccountChgDetailrespinse.getAttachment());
			//主记录序号，与账户操作关联
			transferFromAccountChgDetail.setAccountSn(newTransferAccountSn);
			transferFromAccountChgDetail.setUserId(fromAccountInfo.getUserId());
			transferFromAccountChgDetail.setAccountId(fromAccountInfo.getAccountId());
			transferFromAccountChgDetail.setAccountType(fromAccountInfo.getAccountType());
			//变化金额
			transferFromAccountChgDetail.setChgAccount(cashAmount);
			//变化前金额     
			transferFromAccountChgDetail.setOldBalance(rechargeFromAccountTotal);
			//变化后余额    
			transferFromAccountChgDetail.setBalance(fromAccountInfo.getAvailableBalance().add(fromAccountInfo.getNomentionAmount())
					.add(fromAccountInfo.getFrozenAmount()));
			//进出帐标志    入金
			Integer transferToMoneyIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
			transferFromAccountChgDetail.setIoFlag(transferToMoneyIoFlag);
			transferFromAccountChgDetail.setChangeDateTime(new Date());
			//交易类型 变化原因：   支付
			String transferFromTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_PAY);
			transferFromAccountChgDetail.setTxnType(transferFromTxnType);
			//状态   正常
			String transferMoneyDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
			transferFromAccountChgDetail.setStatus(transferMoneyDetalStatus);
			transferFromAccountChgDetail.setUpdateDateTime(new Date());
			//插入数据
			accountOperateBuilder.addAccountMoneyChgDetail(transferFromAccountChgDetail);
			
			//19、增加toId 资金变动明细表
			AccountMoneyChgDetail transferToAccountChgDetail = new AccountMoneyChgDetail();
			//自动生成id
			AttachmentResponse<Long> transferToAccountChgDetailrespinse = idGeneratorService
					.generate(AccountMoneyChgDetail.class.getName());
			//主键id
			transferToAccountChgDetail.setId(transferToAccountChgDetailrespinse.getAttachment());
			//主记录序号，与账户操作关联
			transferToAccountChgDetail.setAccountSn(newTransferAccountSn);
			transferToAccountChgDetail.setUserId(toAccountInfo.getUserId());
			transferToAccountChgDetail.setAccountId(toAccountInfo.getAccountId());
			transferToAccountChgDetail.setAccountType(toAccountInfo.getAccountType());
			//变化金额
			transferToAccountChgDetail.setChgAccount(cashAmount);
			//变化前金额     
			transferToAccountChgDetail.setOldBalance(toAcountTotal);
			//变化后余额    
			transferToAccountChgDetail.setBalance(toAccountInfo.getAvailableBalance().add(toAccountInfo.getNomentionAmount())
					.add(toAccountInfo.getFrozenAmount()));
			//进出帐标志    入金
			Integer ToIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
			transferToAccountChgDetail.setIoFlag(ToIoFlag);
			transferToAccountChgDetail.setChangeDateTime(new Date());
			//交易类型 变化原因：  支付
			String transferToAccountTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_PAY);
			transferToAccountChgDetail.setTxnType(transferToAccountTxnType);
			//状态   正常
			String transferToMoneyDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
			transferToAccountChgDetail.setStatus(transferToMoneyDetalStatus);
			transferToAccountChgDetail.setUpdateDateTime(new Date());
			//插入数据
			accountOperateBuilder.addAccountMoneyChgDetail(transferToAccountChgDetail);
			
			//事务操作
			accountOperate(accountOperateBuilder);
			return ResponseUtils.buildSuccessResponse(newTransferAccountSn);
		}
		
		/**
		  * 1016账户充值退回接口
		* @param  accountSn 账户退款流水号
		* @param  bankId 银行id
		* @param  note 备注
		* @return AttachmentResponse<Long>
		* @author wangpeng@cssweb.sh.cn
		* @date 2014-7-25 下午3:05:32 
		* @throws  1301    参数为空   
		* @throws  1300   账户不存在
		* @throws  1207   请求重复
		* @throws  1306   记录不存在
		* @return  1210  平台账号不存在
		 */
		public AttachmentResponse<Long> accountReChargeReturn(Long accountSn, String bankId, String bankAccount, String note) {
			AccountOperateBuilder accountOperateBuilder = newAccountOperateBuilder();
			//1、根据流水号查询操作流水对象
			AccountOperInfo oldAccountOperInfo = new AccountOperInfo();
			oldAccountOperInfo.setAccountSn(accountSn);
			oldAccountOperInfo = accountOperInfoDao.selectOne(oldAccountOperInfo);
			if(oldAccountOperInfo==null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.RECORD_NOT_EXIST);
			}
			//防止重复提交
			AccountOperInfo account = new AccountOperInfo();
			String txn = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_TOPUP_REFUND);
			account.setTxnSeq(oldAccountOperInfo.getTxnSeq());
			account.setTxnType(txn);
			account = accountOperInfoDao.selectOne(account);
			if(account!=null){
				return ResponseUtils.buildFailureResponse(TradeConstants.REPEATED_REQUEST);
			}
			//冻结的金额
			BigDecimal frozenAmount = oldAccountOperInfo.getAmount();
			
			//3、获取出金账户信息
			AttachmentResponse<AccountInfo> response = accountServiceSupport.getAccountInfoWidthMACVerify(oldAccountOperInfo.getToAccountId());			
			//账户不存在,返回错误信息
			if (!response.isSuccess()) {
				return ResponseUtils.buildFailureResponse(response.getReturnCode());
			}
		    //根据账号id查询账号信息   fromAccountInfo为出金账号信息
			AccountInfo fromAccountInfo = response.getAttachment();
			//记录总金额 供资金明细表中使用      总金额 = 可提现金额 + 不可提现金额 + 冻结金额
			BigDecimal fromAcountTotal = fromAccountInfo.getAvailableBalance().add(fromAccountInfo.getNomentionAmount())
					.add(fromAccountInfo.getFrozenAmount());
			
			//4、验证冻结金额是否足够
			if(fromAccountInfo.getFrozenAmount().compareTo(oldAccountOperInfo.getAmount()) == -1){
				return ResponseUtils.buildFailureResponse(TradeConstants.ACCOUNT_BALANCE_LACK);
			}
			
			//5、根据银行id获取平台账号对象
			AttachmentResponse<AccountInfo> paymentResponse = getPaymentAccountByBankAccount(bankAccount);
			if(!paymentResponse.isSuccess()){
				return ResponseUtils.buildFailureResponse(paymentResponse.getReturnCode());
			}
			AccountInfo paymentAccountInfo = paymentResponse.getAttachment();
			//记录总金额 供资金明细表中使用      总金额 = 可提现金额 + 不可提现金额 + 冻结金额
			BigDecimal paymentAcountTotal = paymentAccountInfo.getAvailableBalance().add(paymentAccountInfo.getNomentionAmount())
					.add(paymentAccountInfo.getFrozenAmount());
			
			//6、获取流水号
			AttachmentResponse<Long> idGeneratorResponse = idGeneratorService
					.generate(AccountOperInfo.class.getName());
			Long newAccountSn = idGeneratorResponse.getAttachment();
			
			//7、更新出金账户信息 fromid  冻结金额扣款，校验串，更新时间
			fromAccountInfo.setFrozenAmount(fromAccountInfo.getFrozenAmount().subtract(frozenAmount));
			//TODO
			fromAccountInfo.setMac("mac");
			fromAccountInfo.setUpdateDatetime(new Date());
			//更新操作
			accountOperateBuilder.addAccountInfo(fromAccountInfo);
			
			//8、更新平台账号信息    可用金额，校验串，更新时间
			paymentAccountInfo.setAvailableBalance(paymentAccountInfo.getAvailableBalance().subtract(frozenAmount));
			//TODO
			paymentAccountInfo.setMac("mac");
			paymentAccountInfo.setUpdateDatetime(new Date());
			//更新操作
			accountOperateBuilder.addAccountInfo(paymentAccountInfo);
			
			//9、增加出金账户操作流水
			AccountOperInfo accountOperInfo = new AccountOperInfo();
			//记录序号，和产生的流水号一致
			accountOperInfo.setAccountSn(newAccountSn);
			accountOperInfo.setInterfaceName("accountReChargeReturn");
			//来源 电脑端来源
			String sourceType = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);
			accountOperInfo.setSource(sourceType);
			//出金账户
			accountOperInfo.setFromAccountId(fromAccountInfo.getAccountId());
			//入金账户  //此时钱直接打到银行卡里，没有入金账号
			//AccountOperInfo.setToAccountId(toAccountInfo.getAccountId());
			//变化金额
			accountOperInfo.setAmount(frozenAmount);
			//手续费设为0
			accountOperInfo.setFee(BigDecimal.ZERO);
			accountOperInfo.setMemo(note);
			//交易流水号    查询得来
			accountOperInfo.setTxnSeq(oldAccountOperInfo.getTxnSeq());
			//交易类型 变化原因：    充值退回  
			String txnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_TOPUP_REFUND);
			accountOperInfo.setTxnType(txnType);  
			//银行id  入参没有就不插入数据
			accountOperInfo.setBankId(bankId);
			accountOperInfo.setBankAccount(bankAccount);
			//状态      正常
			String operInfoStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
			accountOperInfo.setStatus(operInfoStatus);
			//冲正标志    未冲正
			String reverseFlag = DictionaryUtils.getStringValue(DictionaryKey.REVERSE_FLAG_NO);
			accountOperInfo.setReverseFlag(reverseFlag);
			accountOperInfo.setUpdateDateTime(new Date());
			//插入操作
			accountOperateBuilder.addAccountOperInfo(accountOperInfo);
			
			//10、增加出金账户操作明细
			AccountOperDetail newfromaccountOperDetail =  new AccountOperDetail();
			//id随机产生
			AttachmentResponse<Long> newfromaccountOperDetailrespinse = idGeneratorService
					.generate(AccountOperDetail.class.getName());
			newfromaccountOperDetail.setId(newfromaccountOperDetailrespinse.getAttachment());
			//主记录序号,与账户操作记录关联
			newfromaccountOperDetail.setAccountSn(newAccountSn);
			//出入金标志    出金
			Integer toIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
			newfromaccountOperDetail.setIoFlag(toIoFlag);
			newfromaccountOperDetail.setAccountId(fromAccountInfo.getAccountId());
			//出入金位置         冻结金额
			String fromAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_FROZEN);
			newfromaccountOperDetail.setAccountPosition(fromAccountPosition);
			//变化金额 
			newfromaccountOperDetail.setAmount(frozenAmount);
			//状态   正常
			String detalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
			newfromaccountOperDetail.setStatus(detalStatus);
			newfromaccountOperDetail.setUpdateDatetime(new Date());
			//插入操作
			accountOperateBuilder.addAccountOperDetail(newfromaccountOperDetail);
			
			//11、增加平台账号操作明细
			AccountOperDetail newpaymentaccountOperDetail =  new AccountOperDetail();
			//id随机产生
			AttachmentResponse<Long> newpaymentaccountOperDetailrespinse = idGeneratorService
					.generate(AccountOperDetail.class.getName());
			newpaymentaccountOperDetail.setId(newpaymentaccountOperDetailrespinse.getAttachment());
			//主记录序号,与账户操作记录关联
			newpaymentaccountOperDetail.setAccountSn(newAccountSn);
			//出入金标志    出金
			Integer panmentIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
			newpaymentaccountOperDetail.setIoFlag(panmentIoFlag);
			newpaymentaccountOperDetail.setAccountId(paymentAccountInfo.getAccountId());
			//出入金位置       可提现金额
			String avaAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
			newpaymentaccountOperDetail.setAccountPosition(avaAccountPosition);
			//变化金额 
			newpaymentaccountOperDetail.setAmount(frozenAmount);
			//状态    正常
			String paymentdetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			newpaymentaccountOperDetail.setStatus(paymentdetalStatus);
			newpaymentaccountOperDetail.setUpdateDatetime(new Date());
			//插入操作
			accountOperateBuilder.addAccountOperDetail(newpaymentaccountOperDetail);
			
			//12、增加出金账户资金变动操作明细
			AccountMoneyChgDetail fromAccountChgDetail = new AccountMoneyChgDetail();
			//自动生成id
			AttachmentResponse<Long> fromAccountChgDetailrespinse = idGeneratorService
					.generate(AccountMoneyChgDetail.class.getName());
			//主键id
			fromAccountChgDetail.setId(fromAccountChgDetailrespinse.getAttachment());
			//主记录序号，与账户操作关联
			fromAccountChgDetail.setAccountSn(newAccountSn);
			fromAccountChgDetail.setUserId(fromAccountInfo.getUserId());
			fromAccountChgDetail.setAccountId(fromAccountInfo.getAccountId());
			fromAccountChgDetail.setAccountType(fromAccountInfo.getAccountType());
			//变化金额
			fromAccountChgDetail.setChgAccount(frozenAmount);
			//变化前金额     
			fromAccountChgDetail.setOldBalance(fromAcountTotal);
			//变化后余额    
			fromAccountChgDetail.setBalance(fromAccountInfo.getAvailableBalance().add(fromAccountInfo.getNomentionAmount())
					.add(fromAccountInfo.getFrozenAmount()));
			//进出帐标志    出金
			Integer toMoneyIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
			fromAccountChgDetail.setIoFlag(toMoneyIoFlag);
			fromAccountChgDetail.setChangeDateTime(new Date());
			//交易类型 变化原因：    充值退回 
			String fromMoneyTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_TOPUP_REFUND);
			fromAccountChgDetail.setTxnType(fromMoneyTxnType);
			//状态   正常
			String moneyDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
			fromAccountChgDetail.setStatus(moneyDetalStatus);
			fromAccountChgDetail.setUpdateDateTime(new Date());
			//插入数据
			accountOperateBuilder.addAccountMoneyChgDetail(fromAccountChgDetail);
			
			//13、增加平台账户资金变动明细表
			AccountMoneyChgDetail paymentccountChgDetail = new AccountMoneyChgDetail();
			//自动生成id
			AttachmentResponse<Long> paymentccountChgDetailRespinse = idGeneratorService
					.generate(AccountMoneyChgDetail.class.getName());
			//主键id
			paymentccountChgDetail.setId(paymentccountChgDetailRespinse.getAttachment());
			//主记录序号，与账户操作关联
			paymentccountChgDetail.setAccountSn(newAccountSn);
			paymentccountChgDetail.setUserId(paymentAccountInfo.getUserId());
			paymentccountChgDetail.setAccountId(paymentAccountInfo.getAccountId());
			paymentccountChgDetail.setAccountType(paymentAccountInfo.getAccountType());
			//变化金额
			paymentccountChgDetail.setChgAccount(frozenAmount);
			//变化前金额     
			paymentccountChgDetail.setOldBalance(paymentAcountTotal);
			//变化后余额    
			paymentccountChgDetail.setBalance(paymentAccountInfo.getAvailableBalance().add(paymentAccountInfo.getNomentionAmount())
					.add(paymentAccountInfo.getFrozenAmount()));
			//进出帐标志    出金
			Integer paymentMoneyIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
			paymentccountChgDetail.setIoFlag(paymentMoneyIoFlag);
			paymentccountChgDetail.setChangeDateTime(new Date());
			//交易类型 变化原因：    充值退回 
			String paymentMoneyTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_TOPUP_REFUND);
			paymentccountChgDetail.setTxnType(paymentMoneyTxnType);
			//状态   正常
			String paymentDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
			paymentccountChgDetail.setStatus(paymentDetalStatus);
			paymentccountChgDetail.setUpdateDateTime(new Date());
			//插入数据
			accountOperateBuilder.addAccountMoneyChgDetail(paymentccountChgDetail);
			
			//事务操作
			accountOperate(accountOperateBuilder);
			return ResponseUtils.buildSuccessResponse(newAccountSn);
		}
		
		/**
		 * 	1107 网银支付退款-银行处理失败-解冻接口
		* @param accountSn 账户资金冻结流水号
		* @param note 备注
		* @return AttachmentResponse<FrozenResponse>    返回类型
		* @author wangpeng@cssweb.sh.cn
		* @date 2014-7-25 下午2:26:04 
		* @throws
		* @return 1206 流水号不存在
		* @return 1207 重复请求
		* @return 1301 参数为null
		* @return 1300 账户不存在
		 */
		public AttachmentResponse<FrozenResponse> refundFailureUnfrozen(Long accountSn, String note) {
			AccountOperateBuilder accountOperateBuilder = newAccountOperateBuilder();
		 //1、根据操作流水表，验证流水号
			AccountOperInfo oldAccountOperInfo = new AccountOperInfo();
			oldAccountOperInfo.setAccountSn(accountSn);
		 //2、根据资金流水号，查询账号操作流水对象
			oldAccountOperInfo = accountOperInfoDao.selectOne(oldAccountOperInfo);
			if(oldAccountOperInfo==null){
				return ResponseUtils.buildFailureResponse(TradeConstants.ACCOUNT_SN_NOT_EXIT);
			}
			AccountOperInfo accountInfo = new AccountOperInfo();
			accountInfo.setTxnSeq(oldAccountOperInfo.getTxnSeq());
			String txn = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_UNFROZEN);
			accountInfo.setTxnType(txn);
			accountInfo = accountOperInfoDao.selectOne(accountInfo);
			if(accountInfo!=null){
				return ResponseUtils.buildFailureResponse(TradeConstants.REPEATED_REQUEST);
			}
			//记录冻结金额数
			BigDecimal oldFrozenAmount = oldAccountOperInfo.getAmount();
			
		//3、根据流水表中的to查询账号信息
			AttachmentResponse<AccountInfo> toAccountResponse =  accountServiceSupport.getAccountInfoWidthMACVerify(oldAccountOperInfo.getToAccountId());
			if (!toAccountResponse.isSuccess()) {
				return ResponseUtils.buildFailureResponse(toAccountResponse.getReturnCode());
			}
			AccountInfo toAccountInfo = toAccountResponse.getAttachment();
			
		//4、根据id自动生成器，返回账号流水号   
			AttachmentResponse<Long> idGeneratorResponse = idGeneratorService
					.generate(AccountOperInfo.class.getName());
			Long newAccountSn = idGeneratorResponse.getAttachment();
			String avalibalePosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
			String frozenPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_FROZEN);
			toAccountInfo.setUpdateDatetime(new Date());
			toAccountInfo.setAvailableBalance(toAccountInfo.getAvailableBalance().add(oldFrozenAmount));
			toAccountInfo.setFrozenAmount(toAccountInfo.getFrozenAmount().subtract(oldFrozenAmount));
			//更新操作
			accountOperateBuilder.addAccountInfo(toAccountInfo);
			
		//5、增加操作流水表记录   入金账户
			AccountOperInfo newAccountOperInfo = new AccountOperInfo();
			//记录序号，和产生的流水号一致
			newAccountOperInfo.setAccountSn(newAccountSn);
			newAccountOperInfo.setInterfaceName("refundFailureUnfrozen");
			//来源    电脑端来源
			String sourceType = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);
			newAccountOperInfo.setSource(sourceType);
			newAccountOperInfo.setFromAccountId(toAccountInfo.getAccountId());
			newAccountOperInfo.setToAccountId(toAccountInfo.getAccountId());
			//变化金额
			newAccountOperInfo.setAmount(oldFrozenAmount);
			//手续费设为0
			newAccountOperInfo.setFee(BigDecimal.ZERO);
			newAccountOperInfo.setMemo(note);
			//交易流水号  （查询得来）
			newAccountOperInfo.setTxnSeq(oldAccountOperInfo.getTxnSeq());
			//交易类型  冻结解冻
		    String txnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_UNFROZEN);
			newAccountOperInfo.setTxnType(txnType);
			//银行id 没有的话就不插
			//newAccountOperInfo.setBankId();
			//状态   正常
			String operInfoStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
			newAccountOperInfo.setStatus(operInfoStatus);
			//冲正标志    未冲正
			String reverseFlag = DictionaryUtils.getStringValue(DictionaryKey.REVERSE_FLAG_NO);
			newAccountOperInfo.setReverseFlag(reverseFlag);
			newAccountOperInfo.setUpdateDateTime(new Date());
			accountOperateBuilder.addAccountOperInfo(newAccountOperInfo);
			
			//入金
			Integer toIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
			//出金
			Integer fromIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
			AccountOperDetail fromAccountOperDetail =  new AccountOperDetail();
			//id随机产生
			AttachmentResponse<Long> fromAccountOperDetailRespinse = idGeneratorService
					.generate(AccountOperDetail.class.getName());
			fromAccountOperDetail.setId(fromAccountOperDetailRespinse.getAttachment());
			//主记录序号,与账户操作记录关联
			fromAccountOperDetail.setAccountSn(newAccountSn);
			
			fromAccountOperDetail.setIoFlag(fromIoFlag);
			fromAccountOperDetail.setAccountId(toAccountInfo.getAccountId());
			//出入金位置   //根据读取的流水表里的出入金位置，位置不变
			fromAccountOperDetail.setAccountPosition(frozenPosition);
			//变化金额 
			fromAccountOperDetail.setAmount(oldFrozenAmount);
			//状态      正常
			String detalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			fromAccountOperDetail.setStatus(detalStatus);
			fromAccountOperDetail.setUpdateDatetime(new Date());
			//插入操作
			accountOperateBuilder.addAccountOperDetail(fromAccountOperDetail);
			
			AccountOperDetail toAccountOperDetail =  new AccountOperDetail();
			//id随机产生
			AttachmentResponse<Long> toAccountOperDetailRespinse = idGeneratorService
					.generate(AccountOperDetail.class.getName());
			toAccountOperDetail.setId(toAccountOperDetailRespinse.getAttachment());
			//主记录序号,与账户操作记录关联
			toAccountOperDetail.setAccountSn(newAccountSn);
			
			toAccountOperDetail.setIoFlag(toIoFlag);
			toAccountOperDetail.setAccountId(toAccountInfo.getAccountId());
			//出入金位置   //根据读取的流水表里的出入金位置，位置不变
			toAccountOperDetail.setAccountPosition(avalibalePosition);
			//变化金额 
			toAccountOperDetail.setAmount(oldFrozenAmount);
			//状态      正常
			toAccountOperDetail.setStatus(detalStatus);
			toAccountOperDetail.setUpdateDatetime(new Date());
			//插入操作
			accountOperateBuilder.addAccountOperDetail(toAccountOperDetail);			
			
		  //事务操作
			accountOperate(accountOperateBuilder);
			FrozenResponse frozenResponse = new FrozenResponse();
			frozenResponse.setAccountSn(newAccountSn);
			frozenResponse.setCashAmount(oldFrozenAmount);
			frozenResponse.setAccountid(toAccountInfo.getAccountId());
			return ResponseUtils.buildSuccessResponse(frozenResponse);
		}
}
