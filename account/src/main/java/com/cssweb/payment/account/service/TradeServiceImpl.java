package com.cssweb.payment.account.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cssweb.payment.account.constant.DictionaryKey;
import com.cssweb.payment.account.constant.GlobalConstants;
import com.cssweb.payment.account.constant.RiskConstants;
import com.cssweb.payment.account.constant.TradeConstants;
import com.cssweb.payment.account.dao.IAccountInfoDao;
import com.cssweb.payment.account.dao.IAccountMoneyChgDetailDao;
import com.cssweb.payment.account.dao.IAccountOperDetailDao;
import com.cssweb.payment.account.dao.IAccountOperInfoDao;
import com.cssweb.payment.account.dao.IBankInfoDao;
import com.cssweb.payment.account.dao.IRateInfoDao;
import com.cssweb.payment.account.dao.IUserBankAuInfoDao;
import com.cssweb.payment.account.pojo.AccountInfo;
import com.cssweb.payment.account.pojo.AccountMoneyChgDetail;
import com.cssweb.payment.account.pojo.AccountOperDetail;
import com.cssweb.payment.account.pojo.AccountOperInfo;
import com.cssweb.payment.account.pojo.BankInfo;
import com.cssweb.payment.account.pojo.EnterpriseUser;
import com.cssweb.payment.account.pojo.PersonalUser;
import com.cssweb.payment.account.pojo.UserBankAuInfo;
import com.cssweb.payment.account.sei.ITradeService;
import com.cssweb.payment.account.service.support.AccountServiceSupport;
import com.cssweb.payment.account.service.support.TradeServiceSupport;
import com.cssweb.payment.account.service.support.TradeServiceSupport.AccountOperateBuilder;
import com.cssweb.payment.account.util.DictionaryUtils;
import com.cssweb.payment.account.vo.AccountInfoQuery;
import com.cssweb.payment.account.vo.ApplyMoneyResponse;
import com.cssweb.payment.account.vo.FrozenResponse;
import com.cssweb.payment.account.vo.FrozenTxResponse;
import com.cssweb.payment.account.vo.SecuredAccountRefundResponse;
import com.cssweb.payment.common.provider.sei.IIdGeneratorService;
import com.cssweb.payment.common.response.AttachmentResponse;
import com.cssweb.payment.common.util.ResponseUtils;
@Component
public class TradeServiceImpl implements ITradeService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	public IAccountInfoDao accountInfoDao;
	
	@Resource
	public IRateInfoDao rateInfoDao;
	
	@Resource
	public IBankInfoDao bankInfoDao;
	@Resource
	public IUserBankAuInfoDao userBankAuInfoDao;
	@Resource
	private IIdGeneratorService idGeneratorService;
	
	@Resource
	private IAccountOperInfoDao accountOperInfoDao;
	
	@Resource
	private IAccountOperDetailDao accountOperDetailDao;
	
	@Resource
	private IAccountMoneyChgDetailDao accountMoneyChgDetailDao;
	
	@Resource
	private AccountServiceSupport accountServiceSupport;
	
	@Resource
	public TradeServiceSupport tradeServiceSupport;
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
	@Override
	public AttachmentResponse<Long> accountRecharge(Long accountId,
			String tradeSn, BigDecimal cashAmount, String bankId,String bankAccount,
		    String note,String externalOrderNo) {
		try {
			if(accountId == null || tradeSn == null || cashAmount == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"accountId Or tradeSn Or cashAmount"});
			}
			AccountOperInfo accountInfo = new AccountOperInfo();
			accountInfo.setTxnSeq(tradeSn);
			String txn = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_TOPUP);
			accountInfo.setTxnType(txn);
			accountInfo = accountOperInfoDao.selectOne(accountInfo);
			if(accountInfo!=null){
				return ResponseUtils.buildFailureResponse(TradeConstants.REPEATED_REQUEST);
			}
			AttachmentResponse<Long> newAccountSn = tradeServiceSupport.accountRecharge(accountId, tradeSn, cashAmount, bankId, bankAccount, note, externalOrderNo);
			//返回流水号 
			return newAccountSn;
		} catch (Exception e) {
			AttachmentResponse<Long> response = ResponseUtils
					.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1004	平台手续费账户充值接口
	* @param  tradeSn 交易流水号
	* @param  cashAmount 手续费金额
	* @param  fundChannelSn  具体资金渠道生成流水号(银行通道流水号/刷预付卡流水号)
	* @param  bankId 银行id
	* @param  bankAccount  根据bankAccount查找平台信息
	* @param  note 备注
	* @return AttachmentResponse<Long>    返回类型
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-24 上午10:08:26 
	* @throws
	 */
	@Override
	public AttachmentResponse<Long> feeAcountRecharge(String tradeSn,
			BigDecimal cashAmount, String fundChannelSn, String bankId,String bankAccount,
			String note) {
		try {
			AccountOperInfo accountInfo = new AccountOperInfo();
			accountInfo.setTxnSeq(tradeSn);
			String txn = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_TOPUP);
			accountInfo.setTxnType(txn);
			accountInfo = accountOperInfoDao.selectOne(accountInfo);
			if(accountInfo!=null){
				return ResponseUtils.buildFailureResponse(TradeConstants.REPEATED_REQUEST);
			}
			AccountOperateBuilder accountOperateBuilder = tradeServiceSupport.newAccountOperateBuilder();
		//1、根据银行id查询平台账号
			AttachmentResponse<AccountInfo> response = getPaymentAccountByBankAccount(bankAccount);
			AccountInfo paymentAccountInfo = response.getAttachment();
			//记录总金额 供资金明细表中使用      总金额 = 可提现金额 + 不可提现金额 + 冻结金额
			BigDecimal paymentAcountTotal = paymentAccountInfo.getAvailableBalance().add(paymentAccountInfo.getNomentionAmount())
					.add(paymentAccountInfo.getFrozenAmount());
		
		//3、根据id生成器获取流水号
			AttachmentResponse<Long> idGeneratorResponse = idGeneratorService
					.generate(AccountOperInfo.class.getName());
			Long newAccountSn = idGeneratorResponse.getAttachment();
			
		//4、更新账号信息表，增加可提现金额
			paymentAccountInfo.setAvailableBalance(cashAmount.add(paymentAccountInfo.getAvailableBalance()));
			paymentAccountInfo.setUpdateDatetime(new Date());
			paymentAccountInfo.setMac("max");//7、TODO生成校验串
			accountOperateBuilder.addAccountInfo(paymentAccountInfo);
			
		//5、新增账号操作流水表
			AccountOperInfo accountOperInfo = new AccountOperInfo();
			accountOperInfo.setAccountSn(newAccountSn);
			accountOperInfo.setInterfaceName("feeAcountRecharge");
			//来源
			String sourceType = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);
			accountOperInfo.setSource(sourceType);
			//手续费
			accountOperInfo.setFee(cashAmount);
			accountOperInfo.setToAccountId(paymentAccountInfo.getAccountId());
			accountOperInfo.setAmount(cashAmount);
			accountOperInfo.setMemo(note);
			accountOperInfo.setTxnSeq(tradeSn);
			//交易类型    充值
			String txnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_TOPUP);
			accountOperInfo.setTxnType(txnType);
			accountOperInfo.setBankAccount(bankAccount);
			accountOperInfo.setBankId(bankId);
			//状态     正常
			String operInfoStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
			accountOperInfo.setStatus(operInfoStatus);
			//冲正标志     未冲正
			String reverseFlag = DictionaryUtils.getStringValue(DictionaryKey.REVERSE_FLAG_NO);
			accountOperInfo.setReverseFlag(reverseFlag);
			accountOperInfo.setUpdateDateTime(new Date());
			accountOperateBuilder.addAccountOperInfo(accountOperInfo);
			
		//6、新增账号操作明细表
			AccountOperDetail accountOperDetail = new AccountOperDetail();
			//id自增
			AttachmentResponse<Long> accountOperDetailRespinse = idGeneratorService
					.generate(AccountOperDetail.class.getName());
			accountOperDetail.setId(accountOperDetailRespinse.getAttachment());
			accountOperDetail.setAccountSn(newAccountSn);
			//出入金标志     进账
			Integer IoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
			accountOperDetail.setIoFlag(IoFlag);
			accountOperDetail.setAccountId(paymentAccountInfo.getAccountId());
			//账户位置     可提现金额
			String AccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
			accountOperDetail.setAccountPosition(AccountPosition);
			accountOperDetail.setAmount(cashAmount);
			//操作状态        正常
			String detalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			accountOperDetail.setStatus(detalStatus);
			accountOperDetail.setUpdateDatetime(new Date());
			accountOperateBuilder.addAccountOperDetail(accountOperDetail);
			
		//7、新增资金变动明细表
			AccountMoneyChgDetail accountMoneyChgDetail = new AccountMoneyChgDetail();
			//自动生成id
			AttachmentResponse<Long> accountMoneyChgDetailRespinse = idGeneratorService
					.generate(AccountMoneyChgDetail.class.getName());
			accountMoneyChgDetail.setId(accountMoneyChgDetailRespinse.getAttachment());
			accountMoneyChgDetail.setAccountSn(newAccountSn);
			accountMoneyChgDetail.setUserId(paymentAccountInfo.getUserId());
			accountMoneyChgDetail.setAccountId(paymentAccountInfo.getAccountId());
			accountMoneyChgDetail.setAccountType(paymentAccountInfo.getAccountType());
			accountMoneyChgDetail.setChgAccount(cashAmount);
			//变化前金额
			accountMoneyChgDetail.setOldBalance(paymentAcountTotal);
			//变化后余额
			accountMoneyChgDetail.setBalance(paymentAccountInfo.getAvailableBalance().add(paymentAccountInfo.getNomentionAmount())
					.add(paymentAccountInfo.getFrozenAmount()));
			Integer moneyIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
			accountMoneyChgDetail.setIoFlag(moneyIoFlag);
			//手续费
			accountMoneyChgDetail.setAccountFee(cashAmount);
			accountMoneyChgDetail.setChangeDateTime(new Date());
			//交易类型    充值
			String accountMoneyTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_TOPUP);
			accountMoneyChgDetail.setTxnType(accountMoneyTxnType);
			//操作状态      
			String moneyDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
			accountMoneyChgDetail.setStatus(moneyDetalStatus);
			accountMoneyChgDetail.setUpdateDateTime(new Date());
			//插入操作
			accountOperateBuilder.addAccountMoneyChgDetail(accountMoneyChgDetail);
			//开始事务
			tradeServiceSupport.accountOperate(accountOperateBuilder);
			//返回流水号
			return ResponseUtils.buildSuccessResponse(newAccountSn);
		} catch (Exception e) {
			AttachmentResponse<Long> response = ResponseUtils
					.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1005	账户资金冻结接口
	* @param  accountId 账户号
	* @param  tradeSn 交易流水号
	* @param  cashAmount 冻结金额
	* @param  bankId  银行id
	* @param  bankAccount 
	* @param  note
	* @return AttachmentResponse<FrozenResponse>    返回类型
	* @return 1202 余额不足
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-24 下午4:26:28 
	* @throws
	 */
	@Override
	public AttachmentResponse<FrozenResponse> accountFrozen(Long accountId, String tradeSn,
			BigDecimal cashAmount, String bankId, String bankAccount, String note) {
		try {
			AccountOperInfo account = new AccountOperInfo();
			account.setTxnSeq(tradeSn);
			String txn = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_FROZEN);
			account.setTxnType(txn);
			account = accountOperInfoDao.selectOne(account);
			if(account!=null){
				return ResponseUtils.buildFailureResponse(TradeConstants.REPEATED_REQUEST);
			}
			AccountOperateBuilder accountOperateBuilder = tradeServiceSupport.newAccountOperateBuilder();
		//1、检查账号id和验证余额    accountInfo为查到的个人账号信息
			AttachmentResponse<AccountInfo> response = accountServiceSupport.getAccountInfoWidthMACVerify(accountId);			
			if (!response.isSuccess()) {
				return ResponseUtils.buildFailureResponse(response.getReturnCode());
			}
		//2、根据账号id查询账号信息   accountInfo为账号信息
			AccountInfo accountInfo = response.getAttachment();
			//记录不可提现金额  
			BigDecimal oldNomentionAmount = accountInfo.getNomentionAmount();
			
			//验证可以余额和不可用余额是否充足，如果不足，返回错误信息     
			//  通过BigDecimal的compareTo方法来进行比较。返回的结果是int类型，-1表示小于，0是等于，1是大于。
			if((accountInfo.getAvailableBalance().add(accountInfo.getNomentionAmount())).compareTo(cashAmount)==-1){
				return ResponseUtils.buildFailureResponse(TradeConstants.ACCOUNT_BALANCE_LACK);
			}
			
		//3、获取账号流水号   respinse.getAttachment()即为账号流水号
			AttachmentResponse<Long> idGeneratorResponse = idGeneratorService
					.generate(AccountOperInfo.class.getName());
			Long newAccountSn = idGeneratorResponse.getAttachment();
		
		//4、更新账号信息表   ,更新不可提现金额，可提现金额，冻结金额，校验串，更新时间
			//优先冻结不可用金额       compareTo方法来进行比较。返回的结果是int类型，-1表示小于，0是等于，1是大于。
			//如果不可提现等于0，就扣可提现金额 ，增加冻结金额
			if(accountInfo.getNomentionAmount().compareTo(BigDecimal.ZERO)==0){
				//可提现金额减少 
				accountInfo.setAvailableBalance(accountInfo.getAvailableBalance().subtract(cashAmount));
				//冻结金额增加
				accountInfo.setFrozenAmount(accountInfo.getFrozenAmount().add(cashAmount));
			}
			else if((accountInfo.getNomentionAmount().compareTo(cashAmount)==1)
					||(accountInfo.getNomentionAmount().compareTo(cashAmount)==0)){
				//不可提现金额减
				accountInfo.setNomentionAmount(accountInfo.getNomentionAmount().subtract(cashAmount));
				//冻结金额 增加
				accountInfo.setFrozenAmount(accountInfo.getFrozenAmount().add(cashAmount));
			}else{
				//不可提现金额全减
				accountInfo.setNomentionAmount(BigDecimal.ZERO);
				//可提现金额继续减
				accountInfo.setAvailableBalance(accountInfo.getAvailableBalance()
						.subtract((cashAmount.subtract(oldNomentionAmount))));
				//冻结金额增加
				accountInfo.setFrozenAmount(accountInfo.getFrozenAmount().add(cashAmount));
			}
			accountInfo.setUpdateDatetime(new Date());
			accountInfo.setMac("TODO");//TODO生成校验串
			accountOperateBuilder.addAccountInfo(accountInfo);
			
		//5、增加账号操作流水表信息
			AccountOperInfo accountOperInfo = new AccountOperInfo();
			accountOperInfo.setAccountSn(newAccountSn);
			accountOperInfo.setInterfaceName("accountFrozen");
			//来源     电脑端来源
			String sourceType = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);
			accountOperInfo.setSource(sourceType);
			accountOperInfo.setFromAccountId(accountId);
			accountOperInfo.setToAccountId(accountId);
			//变化金额（入参）
			accountOperInfo.setAmount(cashAmount);
			//手续费设为0
			accountOperInfo.setFee(BigDecimal.ZERO);
			accountOperInfo.setMemo(note);
			accountOperInfo.setTxnSeq(tradeSn);
			//交易类型  冻结
			String txnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_FROZEN);
			accountOperInfo.setTxnType(txnType);
			accountOperInfo.setBankId(bankId);
			accountOperInfo.setBankAccount(bankAccount);
			//状态     正常
			String operInfoStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
			accountOperInfo.setStatus(operInfoStatus);
			//冲正标志     未冲正
			String reverseFlag = DictionaryUtils.getStringValue(DictionaryKey.REVERSE_FLAG_NO);
			accountOperInfo.setReverseFlag(reverseFlag);
			//TODO  MAC校验码
			accountOperInfo.setUpdateDateTime(new Date());
			accountOperateBuilder.addAccountOperInfo(accountOperInfo);
			
		//6、增加账号操作明细表信息
			//BigDecimal.ZERO为BigDecimal类型的0，  如果==0，说明等于0
			//插入两条数据，账号位置，可提现金额，和冻结金额
			if(accountInfo.getNomentionAmount().compareTo(BigDecimal.ZERO)==0){
				//6.1.1插入可提现金额位置
				AccountOperDetail avalibaleAccountOperDetail = new AccountOperDetail();
				//id随机产生
				AttachmentResponse<Long> avalibaleAccountOperDetailRespinse = idGeneratorService
						.generate(AccountOperDetail.class.getName());
				avalibaleAccountOperDetail.setId(avalibaleAccountOperDetailRespinse.getAttachment());
				//主记录序号,主记录序号，与账户操作记录关联
				avalibaleAccountOperDetail.setAccountSn(newAccountSn);
				//出入金标志     出账
				Integer IoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
				avalibaleAccountOperDetail.setIoFlag(IoFlag);
				avalibaleAccountOperDetail.setAccountId(accountId);
				//账户位置     可提现金额
				String accountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
				avalibaleAccountOperDetail.setAccountPosition(accountPosition);
				//变化金额 
				avalibaleAccountOperDetail.setAmount(cashAmount);
				//操作状态        正常
				String detalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
				avalibaleAccountOperDetail.setStatus(detalStatus);
				avalibaleAccountOperDetail.setUpdateDatetime(new Date());
				accountOperateBuilder.addAccountOperDetail(avalibaleAccountOperDetail);
				
				//6.1.2插入冻结金额位置
				AccountOperDetail frozenAccountOperDetail = new AccountOperDetail();
				//id随机产生
				AttachmentResponse<Long> frozenAccountOperDetailRespinse = idGeneratorService
						.generate(AccountOperDetail.class.getName());
				frozenAccountOperDetail.setId(frozenAccountOperDetailRespinse.getAttachment());
				//主记录序号,与账户操作记录关联
				frozenAccountOperDetail.setAccountSn(newAccountSn);
				//出入金标志     进账
				Integer frozenIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
				frozenAccountOperDetail.setIoFlag(frozenIoFlag);
				frozenAccountOperDetail.setAccountId(accountId);
				//账户位置     冻结金额
				String frozenAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_FROZEN);
				frozenAccountOperDetail.setAccountPosition(frozenAccountPosition);
				//变化金额 
				frozenAccountOperDetail.setAmount(cashAmount);
				//操作状态        正常
				String frozenDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
				frozenAccountOperDetail.setStatus(frozenDetalStatus);
				frozenAccountOperDetail.setUpdateDatetime(new Date());
				accountOperateBuilder.addAccountOperDetail(frozenAccountOperDetail);
			}
			//当不可用余额大于冻结金额时，插入两条位置数据，不可提现金额位置和冻结金额位置     
			//返回的结果是int类型，-1表示小于，0是等于，1是大于。
			else if(accountInfo.getNomentionAmount().compareTo(cashAmount)==1){
				//6.2.1 插入不可提现金额位置
				AccountOperDetail noavalibaleAccountOperDetail = new AccountOperDetail();
				//id随机产生
				AttachmentResponse<Long> noavalibaleAccountOperDetailRespinse = idGeneratorService
						.generate(AccountOperDetail.class.getName());
				noavalibaleAccountOperDetail.setId(noavalibaleAccountOperDetailRespinse.getAttachment());
				//主记录序号,与账户操作记录关联
				noavalibaleAccountOperDetail.setAccountSn(newAccountSn);
				//出入金标志     出账
				Integer noavalibaleIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
				noavalibaleAccountOperDetail.setIoFlag(noavalibaleIoFlag);
				noavalibaleAccountOperDetail.setAccountId(accountId);
				//账户位置     不可提现金额
				String noavalibaleAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_NOMENTION);
				noavalibaleAccountOperDetail.setAccountPosition(noavalibaleAccountPosition);
				//变化金额 
				noavalibaleAccountOperDetail.setAmount(cashAmount);
				//状态        正常
				String noavalibaleDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
				noavalibaleAccountOperDetail.setStatus(noavalibaleDetalStatus);
				noavalibaleAccountOperDetail.setUpdateDatetime(new Date());
				accountOperateBuilder.addAccountOperDetail(noavalibaleAccountOperDetail);
				
				//6.2.2插入冻结金额位置
				AccountOperDetail frozenAccountOperDetail = new AccountOperDetail();
				//id随机产生
				AttachmentResponse<Long> frozenAccountOperDetailRespinse = idGeneratorService
						.generate(AccountOperDetail.class.getName());
				frozenAccountOperDetail.setId(frozenAccountOperDetailRespinse.getAttachment());
				//主记录序号,与账户操作记录关联
				frozenAccountOperDetail.setAccountSn(newAccountSn);
				//出入金标志     进账
				Integer frozenAccountIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
				frozenAccountOperDetail.setIoFlag(frozenAccountIoFlag);
				frozenAccountOperDetail.setAccountId(accountId);
				//账户位置     冻结金额
				String frozenAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_FROZEN);
				frozenAccountOperDetail.setAccountPosition(frozenAccountPosition);
				//变化金额 
				frozenAccountOperDetail.setAmount(cashAmount);
				//操作状态        正常
				String frozenAccountDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
				frozenAccountOperDetail.setStatus(frozenAccountDetalStatus);
				frozenAccountOperDetail.setUpdateDatetime(new Date());
				accountOperateBuilder.addAccountOperDetail(frozenAccountOperDetail);
			}
			//当不可用余额小于冻结金额时，插入三条位置数据，不可提现金额位置和冻结金额位置和可提现金额位置
			else{
				//6.3.1 插入不可提现金额位置
				AccountOperDetail noavalibaleAccountOperDetail = new AccountOperDetail();
				//id随机产生
				AttachmentResponse<Long> noavalibaleAccountOperDetailRespinse = idGeneratorService
						.generate(AccountOperDetail.class.getName());
				noavalibaleAccountOperDetail.setId(noavalibaleAccountOperDetailRespinse.getAttachment());
				//主记录序号,与账户操作记录关联
				noavalibaleAccountOperDetail.setAccountSn(newAccountSn);
				//出入金标志  出账
				Integer noavalibaleIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
				noavalibaleAccountOperDetail.setIoFlag(noavalibaleIoFlag);//进出帐标志，-1：出账；1：进账;
				noavalibaleAccountOperDetail.setAccountId(accountId);
				//出入金位置
				String noavalibaleAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_NOMENTION);
				noavalibaleAccountOperDetail.setAccountPosition(noavalibaleAccountPosition);
				//变化金额 
				noavalibaleAccountOperDetail.setAmount(accountInfo.getNomentionAmount());
				//状态 正常
				String noavalibaleAccountDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
				noavalibaleAccountOperDetail.setStatus(noavalibaleAccountDetalStatus);
				noavalibaleAccountOperDetail.setUpdateDatetime(new Date());
				accountOperateBuilder.addAccountOperDetail(noavalibaleAccountOperDetail);
				
				//6.3.2插入冻结金额位置
				AccountOperDetail frozenAccountOperDetail = new AccountOperDetail();
				//id随机产生
				AttachmentResponse<Long> frozenAccountOperDetailRespinse = idGeneratorService
						.generate(AccountOperDetail.class.getName());
				frozenAccountOperDetail.setId(frozenAccountOperDetailRespinse.getAttachment());
				//主记录序号,与账户操作记录关联
				frozenAccountOperDetail.setAccountSn(newAccountSn);
				//出入金标志   进账
				Integer frozenAccountIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
				frozenAccountOperDetail.setIoFlag(frozenAccountIoFlag);
				frozenAccountOperDetail.setAccountId(accountId);
				//出入金位置  冻结金额
				String frozenAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_FROZEN);
				frozenAccountOperDetail.setAccountPosition(frozenAccountPosition);
				//变化金额 
				frozenAccountOperDetail.setAmount(cashAmount);
				//状态     正常
				String frozenAccountDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
				frozenAccountOperDetail.setStatus(frozenAccountDetalStatus);
				frozenAccountOperDetail.setUpdateDatetime(new Date());
				accountOperateBuilder.addAccountOperDetail(frozenAccountOperDetail);
				
				//6.3.3插入可用金额位置
				AccountOperDetail avalibaleAccountOperDetail = new AccountOperDetail();
				//id随机产生
				AttachmentResponse<Long> avalibaleAccountOperDetailRespinse = idGeneratorService
						.generate(AccountOperDetail.class.getName());
				avalibaleAccountOperDetail.setId(avalibaleAccountOperDetailRespinse.getAttachment());
				//主记录序号,与账户操作记录关联
				avalibaleAccountOperDetail.setAccountSn(newAccountSn);
				//出入金标志  出账
				Integer avalibaleAccountIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
				avalibaleAccountOperDetail.setIoFlag(avalibaleAccountIoFlag);
				avalibaleAccountOperDetail.setAccountId(accountId);
				//出入金位置  可提现金额
				String avalibaleAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
				avalibaleAccountOperDetail.setAccountPosition(avalibaleAccountPosition);
				//变化金额 
				avalibaleAccountOperDetail.setAmount(cashAmount.subtract(oldNomentionAmount));
				//状态  正常
				String avalibaleAccountDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
				avalibaleAccountOperDetail.setStatus(avalibaleAccountDetalStatus);
				avalibaleAccountOperDetail.setUpdateDatetime(new Date());
				accountOperateBuilder.addAccountOperDetail(avalibaleAccountOperDetail);
			}
			tradeServiceSupport.accountOperate(accountOperateBuilder);
			FrozenResponse frozenResponse = new FrozenResponse();
			frozenResponse.setAccountSn(newAccountSn);
			frozenResponse.setCashAmount(cashAmount);
		//7、返还账号流水号
			return ResponseUtils.buildSuccessResponse(frozenResponse);
		} catch (Exception e) {
			AttachmentResponse<FrozenResponse> response = ResponseUtils
					.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1006 账户冻结扣款接口
	* @param  accountSn  账户资金冻结流水号
	* @param  note 备注
	* @return AttachmentResponse<FrozenResponse>  返回类型
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-24 下午8:48:22 
	* @throws
	* @ return  1206    流水号不存在
	 */
	@Override
	public AttachmentResponse<FrozenResponse> accountFrozenCharge(Long accountSn, String note) {
		try {
			AccountOperateBuilder accountOperateBuilder = tradeServiceSupport.newAccountOperateBuilder();
			//1、验证流水号 ,无流水号或状态为已处理，账号冻结扣款失败
			AccountOperInfo oldAccountOperInfo = new AccountOperInfo();
			oldAccountOperInfo.setAccountSn(accountSn);
			//2、根据资金流水号，查询账号操作流水对象
			oldAccountOperInfo = accountOperInfoDao.selectOne(oldAccountOperInfo);
			if(oldAccountOperInfo==null){
				return ResponseUtils.buildFailureResponse(TradeConstants.ACCOUNT_SN_NOT_EXIT);
			}
			AccountOperInfo accountInfo = new AccountOperInfo();
			accountInfo.setTxnSeq(oldAccountOperInfo.getTxnSeq());
			String txn = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_PAY);
			accountInfo.setTxnType(txn);
			accountInfo = accountOperInfoDao.selectOne(accountInfo);
			if(accountInfo!=null){
				return ResponseUtils.buildFailureResponse(TradeConstants.REPEATED_REQUEST);
			}
			//3、验证from账号id,返回验证码   accountOperInfo.getFromAccountId()为出金账户
			AttachmentResponse<AccountInfo> fromResponse = accountServiceSupport.getAccountInfoWidthMACVerify(oldAccountOperInfo.getFromAccountId());			
			//账户不存在,返回错误信息
			if (!fromResponse.isSuccess()) {
				return ResponseUtils.buildResponse(fromResponse);
			}
			//4、验证to账号id,返回验证码
			AttachmentResponse<AccountInfo> toResponse = accountServiceSupport.getAccountInfoWidthMACVerify(oldAccountOperInfo.getToAccountId());			
			if (!toResponse.isSuccess()) {
				return ResponseUtils.buildResponse(toResponse);
			}
			//获取出金账户信息
			AccountInfo fromAccountInfo = fromResponse.getAttachment();
			//记录出金账户的总金额，供资金变动明细表中使用
			BigDecimal oldFromAccountTotal = fromAccountInfo.getAvailableBalance().add(fromAccountInfo.getNomentionAmount())
					.add(fromAccountInfo.getFrozenAmount());
			BigDecimal fromFrozenAmount = fromAccountInfo.getFrozenAmount();
			//获取入金账户信息
			AccountInfo toAccountInfo = toResponse.getAttachment();
			//记录入金账户的总金额，供资金变动明细表中使用
			BigDecimal oldToAccountTotal = toAccountInfo.getAvailableBalance().add(toAccountInfo.getNomentionAmount())
					.add(toAccountInfo.getFrozenAmount());
			BigDecimal toAvailableBalance = toAccountInfo.getAvailableBalance();
			
			//5、更新出金账户信息表 fromid,冻结金额，校验串，更新时间  
			//定义一个常量存冻结金额数
			BigDecimal frozenAmount = oldAccountOperInfo.getAmount();
			fromAccountInfo.setFrozenAmount(fromFrozenAmount.subtract(frozenAmount));
			fromAccountInfo.setMac("mac");//TODO
			fromAccountInfo.setUpdateDatetime(new Date());
			//更新方法
			accountOperateBuilder.addAccountInfo(fromAccountInfo);
			
			//6、更新入金账号信息表toid,可提现金额，校验串，更新时间
			//可提现金额,增加
			toAccountInfo.setAvailableBalance(toAvailableBalance.add(frozenAmount));
			toAccountInfo.setMac("mac");//TODO
			toAccountInfo.setUpdateDatetime(new Date());
			//更新方法
			accountOperateBuilder.addAccountInfo(toAccountInfo);
			
			//7、获取账户流水号
			AttachmentResponse<Long> idGeneratorResponse = idGeneratorService.generate(AccountOperInfo.class.getName());
			if (!idGeneratorResponse.isSuccess()) {
				return ResponseUtils.buildResponse(idGeneratorResponse);
			}
			Long newAccountSn = idGeneratorResponse.getAttachment();
			//8、插入账户操作流水表
			AccountOperInfo newAccountOperInfo = new AccountOperInfo();
			//记录序号，和产生的流水号一致
			newAccountOperInfo.setAccountSn(newAccountSn);
			newAccountOperInfo.setInterfaceName("accountFrozenCharge");
			//来源     电脑端来源
			 String sourceType = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);
			newAccountOperInfo.setSource(sourceType);
			newAccountOperInfo.setFromAccountId(oldAccountOperInfo.getFromAccountId());
			//变化金额
			newAccountOperInfo.setAmount(frozenAmount);
			//手续费设为0
			newAccountOperInfo.setFee(BigDecimal.ZERO);
			newAccountOperInfo.setMemo(note);
			//交易流水号  （根据查询得来）
			newAccountOperInfo.setTxnSeq(oldAccountOperInfo.getTxnSeq());
			//交易类型      冻结扣款对应的类型为   支付
			String txnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_PAY);
			newAccountOperInfo.setTxnType(txnType);
			newAccountOperInfo.setBankId(oldAccountOperInfo.getBankId());
			//状态     正常
			String operInfoStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
			newAccountOperInfo.setStatus(operInfoStatus);
			//冲正标志   未冲正
			String reverseFlag = DictionaryUtils.getStringValue(DictionaryKey.REVERSE_FLAG_NO);
			newAccountOperInfo.setReverseFlag(reverseFlag);
			newAccountOperInfo.setUpdateDateTime(new Date());
			accountOperateBuilder.addAccountOperInfo(newAccountOperInfo);
			
			//9、插入账号操作明细表  出金账户fromid
			AccountOperDetail fromAccountOperDetail = new AccountOperDetail();
			//id随机产生
			AttachmentResponse<Long> fromAccountOperDetailResponse = idGeneratorService.generate(AccountOperDetail.class.getName());
			if (!fromAccountOperDetailResponse.isSuccess()) {
				return ResponseUtils.buildResponse(fromAccountOperDetailResponse);
			}
			fromAccountOperDetail.setId(fromAccountOperDetailResponse.getAttachment());
			//主记录序号,与账户操作记录关联
			fromAccountOperDetail.setAccountSn(newAccountSn);
			//出入金标志   出金
			Integer fromIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
			fromAccountOperDetail.setIoFlag(fromIoFlag);
			fromAccountOperDetail.setAccountId(fromAccountInfo.getAccountId());
			//出入金位置     冻结金额位置
			String fromAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_FROZEN);
			fromAccountOperDetail.setAccountPosition(fromAccountPosition);
			//变化金额 
			fromAccountOperDetail.setAmount(frozenAmount);
			//状态    正常
			String fromDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
			fromAccountOperDetail.setStatus(fromDetalStatus);
			fromAccountOperDetail.setUpdateDatetime(new Date());
			//插入数据
			accountOperateBuilder.addAccountOperDetail(fromAccountOperDetail);
			
			//10、插入账户操作明细表   入今账户  toid
			AccountOperDetail toAccountOperDetail = new AccountOperDetail();
			//id随机产生
			AttachmentResponse<Long> toAccountOperDetailResponse = idGeneratorService.generate(AccountOperDetail.class.getName());
			if (!toAccountOperDetailResponse.isSuccess()) {
				return ResponseUtils.buildResponse(toAccountOperDetailResponse);
			}
			toAccountOperDetail.setId(toAccountOperDetailResponse.getAttachment());
			//主记录序号,与账户操作记录关联
			toAccountOperDetail.setAccountSn(newAccountSn);
			//出入金标志   进账
			Integer toIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
			toAccountOperDetail.setIoFlag(toIoFlag);
			toAccountOperDetail.setAccountId(toAccountInfo.getAccountId());
			//出入金位置    可提现金额
			String toAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
			toAccountOperDetail.setAccountPosition(toAccountPosition);
			//变化金额 
			toAccountOperDetail.setAmount(frozenAmount);
			//状态    正常
			String toDdetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			toAccountOperDetail.setStatus(toDdetalStatus);
			toAccountOperDetail.setUpdateDatetime(new Date());
			accountOperateBuilder.addAccountOperDetail(toAccountOperDetail);
			
			//11、插入资金变动明细表  出金账户fromid
			AccountMoneyChgDetail fromAccountMoneyChgDetail = new AccountMoneyChgDetail();
			//自动生成id
			AttachmentResponse<Long> fromAccountMoneyChgDetailResponse = idGeneratorService.generate(AccountMoneyChgDetail.class.getName());
			if (!fromAccountMoneyChgDetailResponse.isSuccess()) {
				return ResponseUtils.buildResponse(fromAccountMoneyChgDetailResponse);
			}
			//主键id
			fromAccountMoneyChgDetail.setId(fromAccountMoneyChgDetailResponse.getAttachment());
			//主记录序号，与账户操作关联
			fromAccountMoneyChgDetail.setAccountSn(newAccountSn);
			fromAccountMoneyChgDetail.setUserId(fromAccountInfo.getUserId());
			fromAccountMoneyChgDetail.setAccountId(fromAccountInfo.getAccountId());
			fromAccountMoneyChgDetail.setAccountType(fromAccountInfo.getAccountType());
			//变化金额
			fromAccountMoneyChgDetail.setChgAccount(frozenAmount);
			//变化前金额   
			fromAccountMoneyChgDetail.setOldBalance(oldFromAccountTotal);
			//变化后余额  
			fromAccountMoneyChgDetail.setBalance(fromAccountInfo.getAvailableBalance().add(toAccountInfo.getNomentionAmount())
					.add(toAccountInfo.getFrozenAmount()));
			//进出帐标志    出金
			Integer fromMoneyIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
			fromAccountMoneyChgDetail.setIoFlag(fromMoneyIoFlag);
			//交易类型   冻结扣款对应的类型为   支付
			String fromMoneyTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_PAY);
			fromAccountMoneyChgDetail.setTxnType(fromMoneyTxnType);
			fromAccountMoneyChgDetail.setChangeDateTime(new Date());
			//状态      正常
			String fromMoneyDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			fromAccountMoneyChgDetail.setStatus(fromMoneyDetalStatus);
			fromAccountMoneyChgDetail.setUpdateDateTime(new Date());
			//插入数据
			accountOperateBuilder.addAccountMoneyChgDetail(fromAccountMoneyChgDetail);
			
			//12、插入资金变动明细表  入今账户  toid
			AccountMoneyChgDetail toAccountMoneyChgDetail = new AccountMoneyChgDetail();
			//自动生成id
			AttachmentResponse<Long> toAccountMoneyChgDetailResponse = idGeneratorService.generate(AccountMoneyChgDetail.class.getName());
			if (!toAccountMoneyChgDetailResponse.isSuccess()) {
				return ResponseUtils.buildResponse(toAccountMoneyChgDetailResponse);
			}
			//主键id
			toAccountMoneyChgDetail.setId(toAccountMoneyChgDetailResponse.getAttachment());
			//主记录序号，与账户操作关联
			toAccountMoneyChgDetail.setAccountSn(newAccountSn);
			toAccountMoneyChgDetail.setUserId(toAccountInfo.getUserId());
			toAccountMoneyChgDetail.setAccountId(toAccountInfo.getAccountId());
			toAccountMoneyChgDetail.setAccountType(toAccountInfo.getAccountType());
			//变化金额
			toAccountMoneyChgDetail.setChgAccount(frozenAmount);
			//变化前金额   
			toAccountMoneyChgDetail.setOldBalance(oldToAccountTotal);
			//变化后余额  
			toAccountMoneyChgDetail.setBalance(toAccountInfo.getAvailableBalance().add(toAccountInfo.getNomentionAmount())
					.add(toAccountInfo.getFrozenAmount()));
			//进出帐标志     进账
			Integer toMoneyIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
			toAccountMoneyChgDetail.setIoFlag(toMoneyIoFlag);
			//交易类型   冻结扣款对应的类型为   支付
			String toMoneyTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_PAY);
			fromAccountMoneyChgDetail.setTxnType(toMoneyTxnType);
			toAccountMoneyChgDetail.setChangeDateTime(new Date());
			//状态    正常
			String toMoneyDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			toAccountMoneyChgDetail.setStatus(toMoneyDetalStatus);
			toAccountMoneyChgDetail.setUpdateDateTime(new Date());
			//插入数据
			accountOperateBuilder.addAccountMoneyChgDetail(toAccountMoneyChgDetail);
			tradeServiceSupport.accountOperate(accountOperateBuilder);
			//返回流水号
			FrozenResponse frozenResponse = new FrozenResponse();
			frozenResponse.setAccountSn(newAccountSn);
			frozenResponse.setCashAmount(frozenAmount);
			frozenResponse.setAccountid(fromAccountInfo.getAccountId());
			return ResponseUtils.buildSuccessResponse(frozenResponse);
		} catch (Exception e) {
			AttachmentResponse<FrozenResponse> response = ResponseUtils.buildExceptionResponse();
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
	@Override
	public AttachmentResponse<FrozenTxResponse> accountFrozenTx(Long accountSn, String bankId, String bankAccount, String note) {
		try {
			//检查入参
			if(accountSn == null ){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"accountSn"});
			}
			AttachmentResponse<FrozenTxResponse> frozenResponse = tradeServiceSupport.accountFrozenTx(accountSn, bankId, bankAccount, note);
			//返回流水号
			return frozenResponse;
		} catch (Exception e) {
			AttachmentResponse<FrozenTxResponse> response = ResponseUtils
					.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
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
	@Override
	public AttachmentResponse<FrozenResponse> refundFailureUnfrozen(Long accountSn, String note) {
		try {
			//检查入参
			if(accountSn == null ){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"accountSn"});
			}
			//返回流水号
			AttachmentResponse<FrozenResponse> frozenResponse = tradeServiceSupport.refundFailureUnfrozen(accountSn, note);
			return frozenResponse;
		} catch (Exception e) {
			AttachmentResponse<FrozenResponse> response = ResponseUtils
					.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
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
	@Override
	public AttachmentResponse<FrozenResponse> accountUnfrozen(Long accountSn, String note) {
		try {
			//检查入参
			if(accountSn == null ){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"accountSn"});
			}
			AttachmentResponse<FrozenResponse> frozenResponse = tradeServiceSupport.accountUnfrozen(accountSn, note);
			//返回流水号
			return frozenResponse;
		} catch (Exception e) {
			AttachmentResponse<FrozenResponse> response = ResponseUtils
					.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
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
	* @date 2014-7-26 下午2:43:38 
	* @return 1209  收款方和付款方不能相同
	* @return 1207  重复请求
	* @return 1301 参数为null
	* @return 1300 账户不存在
	* @return 1202 余额不足
	* @throws
	 */
	@Override
	public AttachmentResponse<Long> accountPay(Long fromAccountId,
			Long toAccountId, String tradeSn, BigDecimal cashAmount, String note) {
		try {
			//入参检查
			if (fromAccountId == null || toAccountId == null || tradeSn == null || cashAmount == null ) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"toAccountId Or toAccountId"
						+ "Or tradeSn Or cashAmount"});
			}
			if (fromAccountId.equals(toAccountId)) {
				return ResponseUtils.buildFailureResponse(TradeConstants.FROM_TO_MUST_NOT_EQUALS);
			}
			AccountOperInfo accountInfo = new AccountOperInfo();
			accountInfo.setTxnSeq(tradeSn);
			String txn = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_PAY);
			accountInfo.setTxnType(txn);
			accountInfo = accountOperInfoDao.selectOne(accountInfo);
			if(accountInfo!=null){
				return ResponseUtils.buildFailureResponse(TradeConstants.REPEATED_REQUEST);
			}
			AttachmentResponse<Long> newAccountSn = tradeServiceSupport.accountPay(fromAccountId, toAccountId, tradeSn, cashAmount, note);
			//12、返回账号操作流水号
			return newAccountSn;
		} catch (Exception e) {
			AttachmentResponse<Long> response = ResponseUtils
					.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
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
	@Override
	public AttachmentResponse<Long> accountTransfer(Long fromAccountId,
			Long toAccountId, String tradeSn, BigDecimal cashAmount,
			String note) {
		try {
			if (fromAccountId.equals(toAccountId)) {
				return ResponseUtils.buildFailureResponse(TradeConstants.FROM_TO_MUST_NOT_EQUALS);
			}
			AccountOperInfo accountOperInfoQuery = new AccountOperInfo();
			accountOperInfoQuery.setTxnSeq(tradeSn);
			String txn = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_TRANSFER);
			accountOperInfoQuery.setTxnType(txn);
			AccountOperInfo accountOperInfoExist = accountOperInfoDao.selectOne(accountOperInfoQuery);
			if(accountOperInfoExist != null){
				return ResponseUtils.buildFailureResponse(TradeConstants.REPEATED_REQUEST);
			}
			AttachmentResponse<Long> response = tradeServiceSupport.accountTransfer(fromAccountId, toAccountId, tradeSn, cashAmount, note);
			return response;
		} catch (Exception e) {
			AttachmentResponse<Long> response = ResponseUtils
					.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 3.11	账户担保交易转账接口
	* @param  fromAccountId 支出账户ID
	* @param  toAccountId  收入账户ID
	* @param  tradeSn 交易流水号
	* @param  cashAmount 转账金额
	* @param  note 备注
	* @return AttachmentResponse<String>    返回类型
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-26 下午6:17:56 
	* @throws
	 */
	@Override
	public AttachmentResponse<Long> securedAccountTransfer(Long fromAccountId,
			Long toAccountId, String tradeSn, BigDecimal cashAmount,
			String note) {
		try {
			//入参检查
			if (fromAccountId == null || toAccountId == null || tradeSn == null || cashAmount == null ) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"toAccountId Or toAccountId"
						+ "Or tradeSn Or cashAmount"});
			}
			if (fromAccountId.equals(toAccountId)) {
				return ResponseUtils.buildFailureResponse(TradeConstants.FROM_TO_MUST_NOT_EQUALS);
			}
			AccountOperateBuilder accountOperateBuilder = tradeServiceSupport.newAccountOperateBuilder();
			//1、查询出金账户fromId，含校验码  ，返回对象
			AttachmentResponse<AccountInfo> fromAccountInfoResponse = accountServiceSupport.getAccountInfoWidthMACVerify(fromAccountId);			
			if (!fromAccountInfoResponse.isSuccess()) {
				return ResponseUtils.buildFailureResponse(fromAccountInfoResponse.getReturnCode());
			}
		    //根据账号id查询账号信息   fromAccountInfo为出金账号信息
			AccountInfo fromAccountInfo = fromAccountInfoResponse.getAttachment();
			//记录不可用金额    供操作明细表中使用
			BigDecimal fromAcountNomentionAmount = fromAccountInfo.getNomentionAmount();
			//记录出金账户的总金额，供资金变动明细表中使用
			BigDecimal fromAcountTotal = fromAccountInfo.getAvailableBalance().add(fromAccountInfo.getNomentionAmount())
					.add(fromAccountInfo.getFrozenAmount());
			
			//2、查询入金账户toId，含校验码 ，返回对象
			AttachmentResponse<AccountInfo> toAccountInfoResponse = accountServiceSupport.getAccountInfoWidthMACVerify(toAccountId);			
			if (!toAccountInfoResponse.isSuccess()) {
				return ResponseUtils.buildFailureResponse(toAccountInfoResponse.getReturnCode());
			}
		    //根据账号id查询账号信息   
			AccountInfo toAccountInfo = toAccountInfoResponse.getAttachment();
			//记录总金额 供资金明细表中使用      总金额 = 可提现金额 + 不可提现金额 + 冻结金额
			BigDecimal toAcountTotal = toAccountInfo.getAvailableBalance().add(toAccountInfo.getNomentionAmount())
					.add(toAccountInfo.getFrozenAmount());
			
			//3、校验出金账号金额是否足够
			//验证可提现余额+不可提现金额是否充足，如果不足，返回错误信息     
			//通过BigDecimal的compareTo方法来进行比较。返回的结果是int类型，-1表示小于，0是等于，1是大于。
			if((fromAccountInfo.getAvailableBalance().add(fromAccountInfo.getNomentionAmount())).compareTo(cashAmount)==-1){
				return ResponseUtils.buildFailureResponse(TradeConstants.ACCOUNT_BALANCE_LACK);
			}
			
			//4、获取账号流水号   respinse.getAttachment()为账号流水号
			AttachmentResponse<Long> idGeneratorResponse = idGeneratorService
					.generate(AccountOperInfo.class.getName());
			Long newAccountSn = idGeneratorResponse.getAttachment();
			
			//5、更新出金账户，可提现金额或不可提现金额    ，生成校验码，更新时间，支付的时候不可提现金额优先支付
			//判断不可提现金额是否足够支付，优先支付不可提现金额    
			//通过BigDecimal的compareTo方法来进行比较。返回的结果是int类型，-1表示小于，0是等于，1是大于。
			//情况一：不可提现金额为0      扣除可提现金额  
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
			
			//6、更新入金账户， 冻结金额增加，生成校验码，更新时间
			toAccountInfo.setFrozenAmount(toAccountInfo.getFrozenAmount().add(cashAmount));
			//TODO 生成校验码
			toAccountInfo.setMac("TODO");
			toAccountInfo.setUpdateDatetime(new Date());
			//更新操作
			accountOperateBuilder.addAccountInfo(toAccountInfo);
			
			//7、增加账号操作流水表
			AccountOperInfo accountOperInfo = new AccountOperInfo();
			//记录序号，和产生的流水号一致
			accountOperInfo.setAccountSn(newAccountSn);
			//接口名称
			accountOperInfo.setInterfaceName("securedAccountTransfer");
			//来源   电脑端来源
			String sourceType = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);
			accountOperInfo.setSource(sourceType);
			accountOperInfo.setFromAccountId(fromAccountInfo.getAccountId());
			accountOperInfo.setToAccountId(toAccountInfo.getAccountId());
			//变化金额
			accountOperInfo.setAmount(cashAmount);
			//手续费设为0
			accountOperInfo.setFee(BigDecimal.ZERO);
			accountOperInfo.setMemo(note);
			//交易流水号    入参
			accountOperInfo.setTxnSeq(tradeSn);
			//交易类型   担保交易转账  对应  冻结
			String txnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_FROZEN);
			accountOperInfo.setTxnType(txnType);
			//银行id  入参没有就不插入数据
			//accountOperInfo.setBankId(bankId);
			//状态     正常
			String operInfoStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
			accountOperInfo.setStatus(operInfoStatus);
			//冲正标志       未冲正
			String reverseFlag = DictionaryUtils.getStringValue(DictionaryKey.REVERSE_FLAG_NO);
			accountOperInfo.setReverseFlag(reverseFlag);
			accountOperInfo.setUpdateDateTime(new Date());
			accountOperateBuilder.addAccountOperInfo(accountOperInfo);
			
			//8、增加出金账户 fromid 操作明细，可能有多条，根据账号的不可提现金额是否足够支付，进行判断，足够支付就一条信息， 不足够支付，就增加两条信息
			//判断不可提现金额是否足够支付，优先支付不可提现金额    
			//通过BigDecimal的compareTo方法来进行比较。返回的结果是int类型，-1表示小于，0是等于，1是大于。
			//情况一：不可提现金额为0 ，则操作明细表中记录的位置是 可提现金额，
			if(fromAccountInfo.getNomentionAmount().compareTo(BigDecimal.ZERO)==0){
				//位置：可提现金额
				AccountOperDetail fromAvaAccountOperDetail =  new AccountOperDetail();
				//id随机产生
				AttachmentResponse<Long> fromAccountOperDetailRespinse = idGeneratorService
						.generate(AccountOperDetail.class.getName());
				fromAvaAccountOperDetail.setId(fromAccountOperDetailRespinse.getAttachment());
				//主记录序号,主记录序号，与账户操作记录关联
				fromAvaAccountOperDetail.setAccountSn(newAccountSn);
				//出入金标志    出金
				Integer IoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
				fromAvaAccountOperDetail.setIoFlag(IoFlag); 
				fromAvaAccountOperDetail.setAccountId(fromAccountInfo.getAccountId());
				//出入金位置    可提现金额
				String fromAvaAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
				fromAvaAccountOperDetail.setAccountPosition(fromAvaAccountPosition);
				//变化金额 
				fromAvaAccountOperDetail.setAmount(cashAmount);
				//状态    正常
				String fromAvadetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
				fromAvaAccountOperDetail.setStatus(fromAvadetalStatus);
				fromAvaAccountOperDetail.setUpdateDatetime(new Date());
				//插入操作
				accountOperateBuilder.addAccountOperDetail(fromAvaAccountOperDetail);
			}
			//情况二：不可提现金额足够支付，则记录的是不可提现金额 的位置
			else if(!(fromAccountInfo.getNomentionAmount().compareTo(cashAmount)==-1)){
				//位置：不可提现金额
				AccountOperDetail fromNoAccountOperDetail =  new AccountOperDetail();
				//id随机产生
				AttachmentResponse<Long> fromAccountOperDetailRespinse = idGeneratorService
						.generate(AccountOperDetail.class.getName());
				fromNoAccountOperDetail.setId(fromAccountOperDetailRespinse.getAttachment());
				//主记录序号,主记录序号，与账户操作记录关联
				fromNoAccountOperDetail.setAccountSn(newAccountSn);
				//出入金标志    出金
				Integer IoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
				fromNoAccountOperDetail.setIoFlag(IoFlag); 
				fromNoAccountOperDetail.setAccountId(fromAccountInfo.getAccountId());
				//出入金位置    不可提现金额
				String fromNoAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_NOMENTION);
				fromNoAccountOperDetail.setAccountPosition(fromNoAccountPosition);
				//变化金额 
				fromNoAccountOperDetail.setAmount(cashAmount);
				//状态    正常
				String fromNodetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
				fromNoAccountOperDetail.setStatus(fromNodetalStatus);
				fromNoAccountOperDetail.setUpdateDatetime(new Date());
				//插入操作
				accountOperateBuilder.addAccountOperDetail(fromNoAccountOperDetail);
			}else{
				//情况三：不可提现金额不足够支付，则会在两个位置上产生数据，不可提现金额位置和可提现金额位置
				//位置一：不可提现金额位置
				AccountOperDetail fromNoAccountOperDetail =  new AccountOperDetail();
				//id随机产生
				AttachmentResponse<Long> fromAccountOperRespinse = idGeneratorService
						.generate(AccountOperDetail.class.getName());
				fromNoAccountOperDetail.setId(fromAccountOperRespinse.getAttachment());
				//主记录序号,主记录序号，与账户操作记录关联
				fromNoAccountOperDetail.setAccountSn(newAccountSn);
				//出入金标志   出金
				Integer fromNoIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
				fromNoAccountOperDetail.setIoFlag(fromNoIoFlag);
				fromNoAccountOperDetail.setAccountId(fromAccountInfo.getAccountId());
				//出入金位置       不可提现金额
				String fromNoAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_NOMENTION);
				fromNoAccountOperDetail.setAccountPosition(fromNoAccountPosition);
				//变化金额    变化的金额是 所有的不可提现金额的数量
				fromNoAccountOperDetail.setAmount(fromAcountNomentionAmount);
				//状态   正常
				String detalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
				fromNoAccountOperDetail.setStatus(detalStatus);
				fromNoAccountOperDetail.setUpdateDatetime(new Date());
				//插入操作
				accountOperateBuilder.addAccountOperDetail(fromNoAccountOperDetail);
				
				//位置二：可提现金额位置
				AccountOperDetail fromAvailableAccountOperDetail =  new AccountOperDetail();
				//id随机产生
				AttachmentResponse<Long> fromAvailableAccountRespinse = idGeneratorService
						.generate(AccountOperDetail.class.getName());
				fromAvailableAccountOperDetail.setId(fromAvailableAccountRespinse.getAttachment());
				//主记录序号,主记录序号，与账户操作记录关联
				fromAvailableAccountOperDetail.setAccountSn(newAccountSn);
				//出入金标志   出金
				Integer avaIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
				fromAvailableAccountOperDetail.setIoFlag(avaIoFlag);
				fromAvailableAccountOperDetail.setAccountId(fromAccountInfo.getAccountId());
				//出入金位置    可提现金额
				String avaAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
				fromAvailableAccountOperDetail.setAccountPosition(avaAccountPosition);
				//变化金额    变化的金额是     消费的金额 - 不可提现的金额   之差
				fromAvailableAccountOperDetail.setAmount(cashAmount.subtract(fromAcountNomentionAmount));
				//状态    正常
				String avaDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
				fromAvailableAccountOperDetail.setStatus(avaDetalStatus);
				fromAvailableAccountOperDetail.setUpdateDatetime(new Date());
				//插入操作
				accountOperateBuilder.addAccountOperDetail(fromAvailableAccountOperDetail);
				
			}
			
			//9、增加入金账户 toid 操作明细  位置：冻结金额
			AccountOperDetail toAccountOperDetail =  new AccountOperDetail();
			//id随机产生
			AttachmentResponse<Long> toAccountOperDetailRespinse = idGeneratorService
					.generate(AccountOperDetail.class.getName());
			toAccountOperDetail.setId(toAccountOperDetailRespinse.getAttachment());
			//主记录序号,主记录序号，与账户操作记录关联
			toAccountOperDetail.setAccountSn(newAccountSn);
			//出入金标志    
			Integer toIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
			toAccountOperDetail.setIoFlag(toIoFlag);
			toAccountOperDetail.setAccountId(fromAccountInfo.getAccountId());
			//出入金位置     冻结金额，
			String frozenAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_FROZEN);
			toAccountOperDetail.setAccountPosition(frozenAccountPosition);
			//变化金额 
			toAccountOperDetail.setAmount(cashAmount);
			//状态     正常
			String detalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			toAccountOperDetail.setStatus(detalStatus);
			toAccountOperDetail.setUpdateDatetime(new Date());
			//插入操作
			accountOperateBuilder.addAccountOperDetail(toAccountOperDetail);
			
			//10、增加入金账户 toid 资金明细表
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
			//变化后余额    用改变之后的账户中的   可提现+不可提现+冻结金额+增量
			toAccountMoneyChgDetail.setBalance(toAccountInfo.getAvailableBalance().add(toAccountInfo.getNomentionAmount())
					.add(toAccountInfo.getFrozenAmount()));
			//进出帐标志    进账
			Integer toAccountIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
			toAccountMoneyChgDetail.setIoFlag(toAccountIoFlag);
			toAccountMoneyChgDetail.setChangeDateTime(new Date());
			//交易类型   冻结
			String toMoneyTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_FROZEN);
			toAccountMoneyChgDetail.setTxnType(toMoneyTxnType);
			//状态    正常
			String toDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			toAccountMoneyChgDetail.setStatus(toDetalStatus);
			toAccountMoneyChgDetail.setUpdateDateTime(new Date());
			//插入数据
			accountOperateBuilder.addAccountMoneyChgDetail(toAccountMoneyChgDetail);
			
			//10、增加出金账户 fromid 资金明细表  
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
			//变化后余额    用改变之后的账户中的   可提现+不可提现+冻结金额
			fromAccountMoneyChgDetail.setBalance(fromAccountInfo.getAvailableBalance().add(fromAccountInfo.getNomentionAmount())
					.add(fromAccountInfo.getFrozenAmount()));
			//进出帐标志    进账
			Integer fromAccountIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
			fromAccountMoneyChgDetail.setIoFlag(fromAccountIoFlag);
			fromAccountMoneyChgDetail.setChangeDateTime(new Date());
			//交易类型 付款方为  支付  
			String fromMoneyTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_PAY);
			fromAccountMoneyChgDetail.setTxnType(fromMoneyTxnType);
			//状态    正常
			String fromDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			fromAccountMoneyChgDetail.setStatus(fromDetalStatus);
			fromAccountMoneyChgDetail.setUpdateDateTime(new Date());
			//插入数据
			accountOperateBuilder.addAccountMoneyChgDetail(fromAccountMoneyChgDetail);
			
			//事务操作
			tradeServiceSupport.accountOperate(accountOperateBuilder);
			//12、返回账号操作流水号
			return ResponseUtils.buildSuccessResponse(newAccountSn);
		} catch (Exception e) {
			AttachmentResponse<Long> response = ResponseUtils
					.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 3.12	账户担保交易扣款接口
	* @param  tradeSn 担保交易账户转账流水号
	* @param  note 备注
	* @return AttachmentResponse<Long>    返回类型
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-27  上午11:08:47 
	* @throws
	 */
	@Override
	public AttachmentResponse<Long> securedAccountCharge(long tradeSn,String note) {
		try {
			AccountOperateBuilder accountOperateBuilder = tradeServiceSupport.newAccountOperateBuilder();
			//1、根据资金流水号，返回账户操作流水对象
			AccountOperInfo oldAccountOperInfo = new AccountOperInfo();
			oldAccountOperInfo.setAccountSn(tradeSn);
			oldAccountOperInfo = accountOperInfoDao.selectOne(oldAccountOperInfo);
			
			//2、查询入金账户toId，含校验码 ，返回对象
			AttachmentResponse<AccountInfo> toAccountInfoResponse = accountServiceSupport.getAccountInfoWidthMACVerify(oldAccountOperInfo.getToAccountId());			
			//账户不存在,返回错误信息
			if (!toAccountInfoResponse.isSuccess()) {
				return ResponseUtils.buildFailureResponse(toAccountInfoResponse.getReturnCode());
			}
		    //根据账号id查询账号信息   toAccountInfo为入金账号信息
			AccountInfo toAccountInfo = toAccountInfoResponse.getAttachment();
			
			//3、获取账号流水号   respinse.getAttachment()为账号流水号
			AttachmentResponse<Long> idGeneratorResponse = idGeneratorService
					.generate(AccountOperInfo.class.getName());
			Long newAccountSn = idGeneratorResponse.getAttachment();
			
			//4、更新入金账户， 冻结金额扣减，可提现金额增加，生成校验码，更新时间
			// 总冻结金额 - 上次交易的冻结金额
			toAccountInfo.setFrozenAmount(toAccountInfo.getFrozenAmount().subtract(oldAccountOperInfo.getAmount()));
			// 可提现金额 + 上次交易的冻结金额
			toAccountInfo.setAvailableBalance(toAccountInfo.getAvailableBalance().add(oldAccountOperInfo.getAmount()));
			//TODO 生成校验码
			toAccountInfo.setMac("TODO");
			toAccountInfo.setUpdateDatetime(new Date());
			//更新操作
			accountOperateBuilder.addAccountInfo(toAccountInfo);
			
			//5、增加账号操作流水表
			AccountOperInfo newAccountOperInfo = new AccountOperInfo();
			//记录序号，和产生的流水号一致
			newAccountOperInfo.setAccountSn(newAccountSn);
			//接口名称
			newAccountOperInfo.setInterfaceName("securedAccountTransfer");
			//来源    电脑端来源
			String sourceType = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);
			newAccountOperInfo.setSource(sourceType);
			//出金账户    出金账户和入金账户一致，  因为金额位置发生变化
			newAccountOperInfo.setFromAccountId(toAccountInfo.getAccountId());
			//入金账户
			newAccountOperInfo.setToAccountId(toAccountInfo.getAccountId());
			//变化金额
			newAccountOperInfo.setAmount(oldAccountOperInfo.getAmount());
			//手续费设为0
			newAccountOperInfo.setFee(BigDecimal.ZERO);
			//备注（入参）
			newAccountOperInfo.setMemo(note);
			//交易流水号   查询得来
			newAccountOperInfo.setTxnSeq(oldAccountOperInfo.getTxnSeq());
			//交易类型     担保交易扣款    对应于  支付
			String txnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_PAY);
			newAccountOperInfo.setTxnType(txnType);
			//银行id  入参没有就不插入数据
			//accountOperInfo.setBankId(bankId);
			//状态   正常
			String operInfoStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
			newAccountOperInfo.setStatus(operInfoStatus);
			//冲正标志    未冲正
			String reverseFlag = DictionaryUtils.getStringValue(DictionaryKey.REVERSE_FLAG_NO);
			newAccountOperInfo.setReverseFlag(reverseFlag);
			newAccountOperInfo.setUpdateDateTime(new Date());
			accountOperateBuilder.addAccountOperInfo(newAccountOperInfo);
			
			//7、增加入金账户 toid 操作明细  两个位置   冻结金额位置  和  可提现金额位置
			//一、冻结金额位置
			AccountOperDetail toAccountFrozenOperDetail =  new AccountOperDetail();
			//id随机产生
			AttachmentResponse<Long> toAccountFrozenOperDetailrespinse = idGeneratorService
					.generate(AccountOperDetail.class.getName());
			toAccountFrozenOperDetail.setId(toAccountFrozenOperDetailrespinse.getAttachment());
			//主记录序号,主记录序号，与账户操作记录关联
			toAccountFrozenOperDetail.setAccountSn(newAccountSn);
			//出入金标志   冻结金额扣除，属于出账   
			Integer toIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
			toAccountFrozenOperDetail.setIoFlag(toIoFlag);
			//账户ID
			toAccountFrozenOperDetail.setAccountId(toAccountInfo.getAccountId());
			//出入金位置   冻结金额，
			String toAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_FROZEN);
			toAccountFrozenOperDetail.setAccountPosition(toAccountPosition);
			//变化金额   
			toAccountFrozenOperDetail.setAmount(oldAccountOperInfo.getAmount());
			//状态   正常
			String toDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			toAccountFrozenOperDetail.setStatus(toDetalStatus);
			toAccountFrozenOperDetail.setUpdateDatetime(new Date());
			//插入操作
			accountOperateBuilder.addAccountOperDetail(toAccountFrozenOperDetail);
			
			//二、可提现金额位置
			AccountOperDetail toAccountAvailableOperDetail =  new AccountOperDetail();
			//id随机产生
			AttachmentResponse<Long> toAccountAvailableOperDetailrespinse = idGeneratorService
					.generate(AccountOperDetail.class.getName());
			toAccountAvailableOperDetail.setId(toAccountAvailableOperDetailrespinse.getAttachment());
			//主记录序号,主记录序号，与账户操作记录关联
			toAccountAvailableOperDetail.setAccountSn(newAccountSn);
			//出入金标志    入金
			Integer avaIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
			toAccountAvailableOperDetail.setIoFlag(avaIoFlag);
			//账户ID
			toAccountAvailableOperDetail.setAccountId(toAccountInfo.getAccountId());
			//出入金位置    可提现金额
			String toAvaAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
			toAccountAvailableOperDetail.setAccountPosition(toAvaAccountPosition);
			//变化金额 
			toAccountAvailableOperDetail.setAmount(oldAccountOperInfo.getAmount());
			//状态     正常
			String toAvaDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			toAccountAvailableOperDetail.setStatus(toAvaDetalStatus);
			toAccountAvailableOperDetail.setUpdateDatetime(new Date());
			//插入操作
			accountOperateBuilder.addAccountOperDetail(toAccountAvailableOperDetail);
			
			//事务操作
			tradeServiceSupport.accountOperate(accountOperateBuilder);
			//12、返回账号操作流水号
			return ResponseUtils.buildSuccessResponse(newAccountSn);
		} catch (Exception e) {
			AttachmentResponse<Long> response = ResponseUtils
					.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
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
	@Override
	public AttachmentResponse<Long> accountRefund(Long fromAccountId,
			Long toAccountId, String tradeSn, Long accountSn,
			BigDecimal cashAmount, boolean isfrozen, String note) {
		try {
			//入参检查
			if (fromAccountId == null || toAccountId == null || tradeSn == null || cashAmount == null ) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"toAccountId Or toAccountId"
						+ "Or tradeSn Or cashAmount"});
			}
			if (fromAccountId.equals(toAccountId)) {
				return ResponseUtils.buildFailureResponse(TradeConstants.FROM_TO_MUST_NOT_EQUALS);
			}
			AccountOperInfo accountInfo = new AccountOperInfo();
			accountInfo.setTxnSeq(tradeSn);
			//退款
			String txn = null;
			if(isfrozen){
				txn = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_REFUND_UNFROZEN);
			}else{
				txn = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_REFUND);
			}
			accountInfo.setTxnType(txn);
			accountInfo = accountOperInfoDao.selectOne(accountInfo);
			if(accountInfo!=null){
				return ResponseUtils.buildFailureResponse(TradeConstants.REPEATED_REQUEST);
			}
			
			AttachmentResponse<Long> newAccountSn = tradeServiceSupport.accountRefund(fromAccountId, toAccountId, tradeSn, accountSn, cashAmount, isfrozen, note);
			//12、返回账号操作流水号
			return newAccountSn;
		} catch (Exception e) {
			AttachmentResponse<Long> response = ResponseUtils
					.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 3.14	账户担保交易取消接口
	* @param accountSn 原交易账户转账流水号
	* @param isfrozen 收入账户入账是否冻结(1 冻结 0 不冻结)
	* @param note 备注
	* @return AttachmentResponse<Long>    返回类型
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-27 下午3:02:35 
	* @throws
	 */
	@Override
	public AttachmentResponse<SecuredAccountRefundResponse> securedAccountRefund(Long accountSn,
			boolean isfrozen, String note) {
		try {
			AccountOperateBuilder accountOperateBuilder = tradeServiceSupport.newAccountOperateBuilder();
			//1、根据交易流水号，查找操作流水对象
			AccountOperInfo oldAccountOperInfo = new AccountOperInfo();
			oldAccountOperInfo.setAccountSn(accountSn);
			oldAccountOperInfo = accountOperInfoDao.selectOne(oldAccountOperInfo);
			//记录担保交易过程中的金额
			BigDecimal oldAccount = oldAccountOperInfo.getAmount();
			
			//2、查询new出金账户fromId，含校验码  ，返回对象   ,原来流水表里的old入金账户
			AttachmentResponse<AccountInfo> fromAccountInfoResponse = accountServiceSupport.getAccountInfoWidthMACVerify(oldAccountOperInfo.getToAccountId());			
			//账户不存在,返回错误信息
			if (!fromAccountInfoResponse.isSuccess()) {
				return ResponseUtils.buildFailureResponse(fromAccountInfoResponse.getReturnCode());
			}
		    //根据账号id查询账号信息   fromAccountInfo为出金账号信息
			AccountInfo fromAccountInfo = fromAccountInfoResponse.getAttachment();
			//记录总金额，供资金明细表中使用
			BigDecimal fromAccountTotal = fromAccountInfo.getAvailableBalance().add(fromAccountInfo.getNomentionAmount())
					.add(fromAccountInfo.getFrozenAmount());
			
			//3、查询new入金账户toId，含校验码 ，返回对象    ,原来流水表里的old出金账户
			AttachmentResponse<AccountInfo> toAccountInfoResponse = accountServiceSupport.getAccountInfoWidthMACVerify(oldAccountOperInfo.getFromAccountId());			
			//账户不存在,返回错误信息
			if (!toAccountInfoResponse.isSuccess()) {
				return ResponseUtils.buildFailureResponse(toAccountInfoResponse.getReturnCode());
			}
		    //根据账号id查询账号信息   toAccountInfo为入金账号信息
			AccountInfo toAccountInfo = toAccountInfoResponse.getAttachment();
			//记录总金额 供资金明细表中使用      总金额 = 可提现金额 + 不可提现金额 + 冻结金额
			BigDecimal toAcountTotal = toAccountInfo.getAvailableBalance().add(toAccountInfo.getNomentionAmount())
					.add(toAccountInfo.getFrozenAmount());
			
			//4、根据流水号查询  原交易明细
			AccountOperDetail accountOperDetail = new AccountOperDetail();
			accountOperDetail.setAccountSn(accountSn);
			List<AccountOperDetail> list = accountOperDetailDao.selectList(accountOperDetail);
			
			//4、获取账号流水号  newAccountSn为账号流水号
			AttachmentResponse<Long> idGeneratorResponse = idGeneratorService
					.generate(AccountOperInfo.class.getName());
			Long newAccountSn = idGeneratorResponse.getAttachment();
			
			//5、更新入金账户信息，   分冻结和不冻结情况两种情况 ，  
			//情况一：银行卡直接支付的时候，产生的操作是冻结，冻结金额增加；  
			if(isfrozen==true){
				fromAccountInfo.setFrozenAmount(fromAccountInfo.getFrozenAmount().add(oldAccount));
			}else{
			//情况二：不冻结的情况，原路返回
			//可提现金额，不可提现金额，（根据资金位置来确定），冻结金额   遍历操作明细表的数据，如果变化的位置为可用，则可以增加变化金额，
			//如果变化的位置为不可提现，则不可提现增加金额，冻结金额扣除相应的数  ?第三种情况，怎么可提现和不可提现都同时增加，
			//第三种情况就是如果查出来的是三条明细数据的话，两个if语句都会执行
			for(int i=0;i<list.size();i++){
				//出金账户位置，可提现金额
				//如果查询的位置是可提现，且是原来的出金账户信息，就增加原来出金账户的可提现的金额
				String toAvaAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
				String toNonAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_NOMENTION);
				if(list.get(i).getAccountPosition().equals(toAvaAccountPosition)&&(list.get(i).getAccountId().equals(toAccountInfo.getAccountId()))){
					toAccountInfo.setAvailableBalance(toAccountInfo.getAvailableBalance().add(list.get(i).getAmount()));
				}
				//如果查询的位置是不可提现，且是原来的出金账户信息，就增加原来出金账户的不可提现的金额
				if(list.get(i).getAccountPosition().equals(toNonAccountPosition)&&(list.get(i).getAccountId().equals(toAccountInfo.getAccountId()))){
					toAccountInfo.setNomentionAmount(toAccountInfo.getNomentionAmount().add(list.get(i).getAmount()));
				}
			  }
			}
			toAccountInfo.setUpdateDatetime(new Date());
			//TODO
			toAccountInfo.setMac("mac");
			//更新操作
			accountOperateBuilder.addAccountInfo(toAccountInfo);
			
			//6、更新出金账户信息   更新冻结金额，校验串，更新时间
			//  冻结金额 - 上次流水表中的变化量
			fromAccountInfo.setFrozenAmount(fromAccountInfo.getFrozenAmount().subtract(oldAccount));
			//TODO
			fromAccountInfo.setMac("mac");
			fromAccountInfo.setUpdateDatetime(new Date());
			//更新操作
			accountOperateBuilder.addAccountInfo(fromAccountInfo);
			
			//7、插入账户操作流水
			AccountOperInfo newAccountOperInfo = new AccountOperInfo();
			//记录序号，和产生的流水号一致
			newAccountOperInfo.setAccountSn(newAccountSn);
			//接口名称
			newAccountOperInfo.setInterfaceName("securedAccountRefund");
			//来源 PC   电脑端来源
			String sourceType = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);
			newAccountOperInfo.setSource(sourceType);
			//出金账户
			newAccountOperInfo.setFromAccountId(fromAccountInfo.getAccountId());
			//入金账户
			newAccountOperInfo.setToAccountId(toAccountInfo.getAccountId());
			//变化金额
			newAccountOperInfo.setAmount(oldAccountOperInfo.getAmount());
			//手续费设为0
			newAccountOperInfo.setFee(BigDecimal.ZERO);
			//备注（入参）
			newAccountOperInfo.setMemo(note);
			//交易流水号  （入参，没有就不改变）   
			newAccountOperInfo.setTxnSeq(oldAccountOperInfo.getTxnSeq());
			//交易类型 变化原因：  担保交易取消     对应支付取消
			String txnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_CANCEL);
			newAccountOperInfo.setTxnType(txnType);  
			//银行id  入参没有就不插入数据
			//accountOperInfo.setBankId(bankId);
			//状态     正常
			String detalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			newAccountOperInfo.setStatus(detalStatus);
			//冲正标志     未冲正
			String reverseFlag = DictionaryUtils.getStringValue(DictionaryKey.REVERSE_FLAG_NO);
			newAccountOperInfo.setReverseFlag(reverseFlag);
			newAccountOperInfo.setUpdateDateTime(new Date());
			//插入操作
			accountOperateBuilder.addAccountOperInfo(newAccountOperInfo);
			
			//8、插入入金账户操作明细     分冻结 和 不冻结   两种情况
			//情况一： 冻结   入金账户冻结金额增加
			if(isfrozen==true){
				AccountOperDetail newAccountOperDetail =  new AccountOperDetail();
				//id随机产生
				AttachmentResponse<Long> newAccountOperrespinse = idGeneratorService
						.generate(AccountOperDetail.class.getName());
				newAccountOperDetail.setId(newAccountOperrespinse.getAttachment());
				//主记录序号,主记录序号，与账户操作记录关联
				newAccountOperDetail.setAccountSn(newAccountSn);
				//如果原操作明细里是出金标志，现在就插入入金标志，反之；
				Integer toIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
				//出入金标志
				newAccountOperDetail.setIoFlag(toIoFlag);
				//账户ID
				newAccountOperDetail.setAccountId(toAccountInfo.getAccountId());
				//出入金位置     冻结金额位置
				String newAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_FROZEN);
				newAccountOperDetail.setAccountPosition(newAccountPosition);
				//变化金额 
				newAccountOperDetail.setAmount(oldAccount);
				//状态   正常
				String todetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
				newAccountOperDetail.setStatus(todetalStatus);
				newAccountOperDetail.setUpdateDatetime(new Date());
				//插入操作
				accountOperateBuilder.addAccountOperDetail(newAccountOperDetail);
			}else{
				//9、增加入金账户  账户操作明细表  
				//入金账户明细   ，根据读取得原来的出金账户的位置来进行相反操作
				for(int i=0;i<list.size();i++){
					//先判断账号是原先的出金账户
					if(list.get(i).getAccountId().equals(toAccountInfo.getAccountId())){
					AccountOperDetail newAccountOperDetail =  new AccountOperDetail();
					//id随机产生
					AttachmentResponse<Long> newAccountOperrespinse = idGeneratorService
							.generate(AccountOperDetail.class.getName());
					newAccountOperDetail.setId(newAccountOperrespinse.getAttachment());
					//主记录序号,主记录序号，与账户操作记录关联
					newAccountOperDetail.setAccountSn(newAccountSn);
					//如果原操作明细里是出金标志，现在就插入入金标志，反之；
					Integer fromIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
					Integer toIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
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
					//状态   正常
					String todetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
					newAccountOperDetail.setStatus(todetalStatus);
					newAccountOperDetail.setUpdateDatetime(new Date());
					//插入操作
					accountOperateBuilder.addAccountOperDetail(newAccountOperDetail);
					}
				}
			
			}
			
			//增加出金账户的账号操作明细表
			AccountOperDetail newFromAccountOperDetail =  new AccountOperDetail();
			//id随机产生
			AttachmentResponse<Long> newFromAccountOperRespinse = idGeneratorService
					.generate(AccountOperDetail.class.getName());
			newFromAccountOperDetail.setId(newFromAccountOperRespinse.getAttachment());
			//主记录序号,主记录序号，与账户操作记录关联
			newFromAccountOperDetail.setAccountSn(newAccountSn);
			Integer fromIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
			//出入金标志   出金
			newFromAccountOperDetail.setIoFlag(fromIoFlag);
			//账户ID
			newFromAccountOperDetail.setAccountId(fromAccountInfo.getAccountId());
			//出入金位置    冻结金额位置
			String fromAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_FROZEN);
			newFromAccountOperDetail.setAccountPosition(fromAccountPosition);
			//变化金额 
			newFromAccountOperDetail.setAmount(oldAccount);
			//状态   正常
			String todetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
			newFromAccountOperDetail.setStatus(todetalStatus);
			newFromAccountOperDetail.setUpdateDatetime(new Date());
			//插入操作
			accountOperateBuilder.addAccountOperDetail(newFromAccountOperDetail);
			
			
			//11、增加出金账户 fromid 资金明细表
			AccountMoneyChgDetail fromAccountMoneyChgDetail = new AccountMoneyChgDetail();
			//自动生成id
			AttachmentResponse<Long> fromAccountMoneyChgDetailrespinse = idGeneratorService
					.generate(AccountMoneyChgDetail.class.getName());
			//主键id
			fromAccountMoneyChgDetail.setId(fromAccountMoneyChgDetailrespinse.getAttachment());
			//主记录序号，与账户操作关联
			fromAccountMoneyChgDetail.setAccountSn(newAccountSn);
			fromAccountMoneyChgDetail.setUserId(fromAccountInfo.getUserId());
			fromAccountMoneyChgDetail.setAccountId(fromAccountInfo.getAccountId());
			fromAccountMoneyChgDetail.setAccountType(fromAccountInfo.getAccountType());
			//变化金额
			fromAccountMoneyChgDetail.setChgAccount(oldAccount);
			//变化前金额     用之前定义的总共金额
			fromAccountMoneyChgDetail.setOldBalance(fromAccountTotal);
			//变化后余额    用改变之后的账户中的   可提现+不可提现+冻结金额
			fromAccountMoneyChgDetail.setBalance(fromAccountInfo.getAvailableBalance().add(fromAccountInfo.getNomentionAmount())
					.add(fromAccountInfo.getFrozenAmount()));
			//进出帐标志    出金
			Integer fromMoneyIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
			fromAccountMoneyChgDetail.setIoFlag(fromMoneyIoFlag);
			fromAccountMoneyChgDetail.setChangeDateTime(new Date());
			//交易类型 变化原因：  担保交易取消     对应支付取消
			String fromMoneyTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_CANCEL);
			fromAccountMoneyChgDetail.setTxnType(fromMoneyTxnType);
			//状态     正常
			String fromdetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			fromAccountMoneyChgDetail.setStatus(fromdetalStatus);
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
			toAccountMoneyChgDetail.setUserId(fromAccountInfo.getUserId());
			toAccountMoneyChgDetail.setAccountId(fromAccountInfo.getAccountId());
			toAccountMoneyChgDetail.setAccountType(fromAccountInfo.getAccountType());
			//变化金额
			toAccountMoneyChgDetail.setChgAccount(oldAccount);
			//变化前金额     用之前定义的总共金额
			toAccountMoneyChgDetail.setOldBalance(toAcountTotal);
			//变化后余额    用改变之后的账户中的   可提现+不可提现+冻结金额
			toAccountMoneyChgDetail.setBalance(fromAccountInfo.getAvailableBalance().add(fromAccountInfo.getNomentionAmount())
					.add(fromAccountInfo.getFrozenAmount()));
			//进出帐标志    入金
			Integer toIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_I));
			toAccountMoneyChgDetail.setIoFlag(toIoFlag);
			toAccountMoneyChgDetail.setChangeDateTime(new Date());
			//交易类型 变化原因：  担保交易取消     对应支付取消
			String toMoneyTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_CANCEL);
			toAccountMoneyChgDetail.setTxnType(toMoneyTxnType);
			//状态     正常
			String fromMoneyDetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			toAccountMoneyChgDetail.setStatus(fromMoneyDetalStatus);
			toAccountMoneyChgDetail.setUpdateDateTime(new Date());
			//插入数据
			accountOperateBuilder.addAccountMoneyChgDetail(toAccountMoneyChgDetail);
			
			//事务操作
			tradeServiceSupport.accountOperate(accountOperateBuilder);
			//12、返回账号操作流水号
			SecuredAccountRefundResponse response = new SecuredAccountRefundResponse();
			response.setAccountSn(newAccountSn);
			response.setFromaccountid(fromAccountInfo.getAccountId());
			response.setToaccountid(toAccountInfo.getAccountId());
			return ResponseUtils.buildSuccessResponse(response);
		} catch (Exception e) {
			AttachmentResponse<SecuredAccountRefundResponse> response = ResponseUtils
					.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 1015 认证打款申请接口
	* @param  userId 用户ID
	* @param  cashAmount 打款金额
	* @param  Bankname 认证银行名称
	* @param  bankCardNum 银行卡号
	* @param  bankRestatus 打款结果
	* @param  bankId 银行id
	* @param  bankAccount 公司在银行开户的19位帐号
	* @param  tradeSn 交易流水号
	* @param  note 备注
	* @return AttachmentResponse<ApplyMoneyResponse>
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-27 下午6:08:32 
	* @return 1301 参数为null
	* @return 1207 重复请求
	* @return 1203 用户不存在
	* @return 1204 认证打款金额必须在0-1之间
	* @return 1600 银行打款认证信息不存在
	* @throws
	 */
	@Override
	public AttachmentResponse<ApplyMoneyResponse> applyMoney(Long userId, BigDecimal cashAmount, String Bankname,
			String bankCardNum, String bankRestatus, String bankId, String bankAccount,String tradeSn, String note) {
		try {
			//入参检查
			if (userId == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"userId"});
			}
			
			//防重校验
			AccountOperInfo accountInfo = new AccountOperInfo();
			accountInfo.setTxnSeq(tradeSn);
			String txn = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_AUTH_PAY);
			accountInfo.setTxnType(txn);
			accountInfo = accountOperInfoDao.selectOne(accountInfo);
			if(accountInfo!=null){
				return ResponseUtils.buildFailureResponse(TradeConstants.REPEATED_REQUEST);
			}
			
			//1、检查userid是否存在
			AccountInfoQuery accountInfoQuery = new AccountInfoQuery();
			accountInfoQuery.setUserId(userId);
			String accountType = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_TYPE_MAIN);
			accountInfoQuery.setAccountType(accountType);
			AccountInfo userAccountInfo = accountInfoDao.selectOne(accountInfoQuery);
			if(userAccountInfo==null){
				return ResponseUtils.buildFailureResponse(TradeConstants.USER_NOT_EXIT);
			}
			//2、检查打款金额是否在0-1之间
			//通过BigDecimal的compareTo方法来进行比较。返回的结果是int类型，-1表示小于，0是等于，1是大于
			if(!((cashAmount.compareTo(BigDecimal.ONE)==-1)&&(cashAmount.compareTo(BigDecimal.ZERO)==1))){
			    return ResponseUtils.buildFailureResponse(TradeConstants.AUTHENTICATION_PAYMENT_RANGE_WORNG);
			}
			//11、插入用户银行认证打款信息表
			UserBankAuInfo userBankAuInfo = new UserBankAuInfo();
			//账号id userid
			userBankAuInfo.setUserId(userId);
			//银行卡号
			userBankAuInfo.setBankNo(bankCardNum);
			List<UserBankAuInfo> list= userBankAuInfoDao.selectList(userBankAuInfo);
			if (list.isEmpty()) {
				return ResponseUtils.buildFailureResponse(RiskConstants.AUTH_RECORD_NOT_EXIST);
			}
			Collections.sort(list, new Comparator<UserBankAuInfo>() {
				@Override
				public int compare(UserBankAuInfo o1, UserBankAuInfo o2) {
					return o2.getUpdateDatetime().compareTo(o1.getUpdateDatetime());
				}
			});
			userBankAuInfo = list.get(0);
			//银行名称
			userBankAuInfo.setBankName(Bankname);
			//打款金额
			userBankAuInfo.setBandAmount(cashAmount);
			//更新时间
			userBankAuInfo.setUpdateDatetime(new Date());
			userBankAuInfo.setBankRestatus(bankRestatus);
			userBankAuInfo.setBankTime(new Date());
			//打款失败原因
			userBankAuInfo.setNote(note);
			String restatusFailure = DictionaryUtils.getStringValue(DictionaryKey.AUTH_RESTATUS_FAILURE);
			String failure = DictionaryUtils.getStringValue(DictionaryKey.AUTH_STATUS_WAIT_FAILURE);
			String userType = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL);
			String enterprise = DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_ENTERPRISE);
			//----------修改：打款失败时更新打款认证表和用户表的认证状态--------
			//----------修改者：zhanwx    时间：2014-08-28 14:37
			if (bankRestatus.equals(restatusFailure)) {
				if(userType.equals(userAccountInfo.getUserType())){
					PersonalUser pus = new PersonalUser();
					pus.setUserId(userId);
					pus.setAuthStatus(failure);
					pus.setUpdateDatetime(new Date());
					tradeServiceSupport.applyMoneyPersonalUser(null, pus, userBankAuInfo);
				}
				else if(enterprise.equals(userAccountInfo.getUserType())){
					EnterpriseUser eus = new EnterpriseUser();
					eus.setUserId(userId);
					eus.setAuthStatus(failure);
					eus.setUpdateDatetime(new Date());
					tradeServiceSupport.applyMoneyEnterpriseUser(null, eus, userBankAuInfo);
				}
				return ResponseUtils.buildSuccessResponse();
			}
			
			AccountOperateBuilder accountOperateBuilder = tradeServiceSupport.newAccountOperateBuilder();
			
			//4、获取账号流水号
			AttachmentResponse<Long> idGeneratorResponse = idGeneratorService
					.generate(AccountOperInfo.class.getName());
			Long newAccountSn = idGeneratorResponse.getAttachment();
			
			//平台自有资金账号
			Long paymentAccountId = DictionaryUtils.getLongValue(DictionaryKey.ACCOUNT_PLATFORM_OWN);
			AccountInfoQuery paymentAccountInfoQuery = new AccountInfoQuery();
			paymentAccountInfoQuery.setAccountId(paymentAccountId);
			AccountInfo paymentAccountInfo = accountInfoDao.selectOne(paymentAccountInfoQuery);
			//记录总金额 供资金明细表中使用      总金额 = 可提现金额 + 不可提现金额 + 冻结金额
			BigDecimal paymentAcountTotal = paymentAccountInfo.getAvailableBalance().add(paymentAccountInfo.getNomentionAmount())
					.add(paymentAccountInfo.getFrozenAmount());
			
			//7、更新平台账号信息  ，扣减可提现金额，校验串，更新时间
			//可用金额 - 认证金额
			paymentAccountInfo.setAvailableBalance(paymentAccountInfo.getAvailableBalance().subtract(cashAmount));
			//TODO
			paymentAccountInfo.setMac("mac");
			paymentAccountInfo.setUpdateDatetime(new Date());
			//插入数据操作
			accountOperateBuilder.addAccountInfo(paymentAccountInfo);
			
			//8、插入操作流水表
			AccountOperInfo accountOperInfo = new AccountOperInfo();
			//记录序号，和产生的流水号一致
			accountOperInfo.setAccountSn(newAccountSn);
			//接口名称
			accountOperInfo.setInterfaceName("applyMoney");
			//来源 PC：  电脑端来源
			String sourceType = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);
			accountOperInfo.setSource(sourceType);
			//出金账户   出入金的账号都空着
			//accountOperInfo.setFromAccountId(paymentAccountInfo.getAccountId());
			//入金账户  //此时钱直接打到银行卡里，没有如今账号
			//AccountOperInfo.setToAccountId(toAccountInfo.getAccountId());
			//变化金额
			accountOperInfo.setAmount(cashAmount);
			AccountInfoQuery payment = new AccountInfoQuery();
			payment.setAccountId(paymentAccountId);
			AccountInfo paymentAccount = accountInfoDao.selectOne(payment);
			BankInfo bank = new BankInfo();
			bank.setPaymentId(paymentAccount.getUserId());
			bank = bankInfoDao.selectOne(bank);
			accountOperInfo.setBankId(bank.getBankId());
			accountOperInfo.setBankAccount(bank.getBankAccount());
			//手续费设为0
			accountOperInfo.setFee(BigDecimal.ZERO);
			accountOperInfo.setMemo(note);
			//交易流水号 
			accountOperInfo.setTxnSeq(tradeSn);
			//交易类型 变化原因：  打款认证    
			String txnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_AUTH_PAY);
			accountOperInfo.setTxnType(txnType);  
			
			//状态    正常
			String operInfoStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
			accountOperInfo.setStatus(operInfoStatus);
			//冲正标志     未冲正
			String reverseFlag = DictionaryUtils.getStringValue(DictionaryKey.REVERSE_FLAG_NO);
			accountOperInfo.setReverseFlag(reverseFlag);
			accountOperInfo.setUpdateDateTime(new Date());
			//插入操作
			accountOperateBuilder.addAccountOperInfo(accountOperInfo);
			
			//9、插入平台账号操作明细表
			AccountOperDetail accountOperDetail =  new AccountOperDetail();
			//id随机产生
			AttachmentResponse<Long> accountOperDetailrespinse = idGeneratorService
					.generate(AccountOperDetail.class.getName());
			accountOperDetail.setId(accountOperDetailrespinse.getAttachment());
			//主记录序号,与账户操作记录关联
			accountOperDetail.setAccountSn(newAccountSn);
			//出入金标志   出金
			Integer fromIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
			accountOperDetail.setIoFlag(fromIoFlag);
			accountOperDetail.setAccountId(paymentAccountInfo.getAccountId());
			//出入金位置         可提现金额
			String avaAccountPosition = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_POSITION_AVALIBALE);
			accountOperDetail.setAccountPosition(avaAccountPosition);
			//变化金额 
			accountOperDetail.setAmount(cashAmount);
			//状态     正常
			String detalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);	
			accountOperDetail.setStatus(detalStatus);
			accountOperDetail.setUpdateDatetime(new Date());
			//插入操作
			accountOperateBuilder.addAccountOperDetail(accountOperDetail);
			
			//10、插入平台  资金变动明细表
			AccountMoneyChgDetail paymentChgDetail = new AccountMoneyChgDetail();
			//自动生成id
			AttachmentResponse<Long> paymentChgDetailrespinse = idGeneratorService
					.generate(AccountMoneyChgDetail.class.getName());
			//主键id
			paymentChgDetail.setId(paymentChgDetailrespinse.getAttachment());
			//主记录序号，与账户操作关联
			paymentChgDetail.setAccountSn(newAccountSn);
			paymentChgDetail.setUserId(paymentAccountInfo.getUserId());
			paymentChgDetail.setAccountId(paymentAccountInfo.getAccountId());
			paymentChgDetail.setAccountType(paymentAccountInfo.getAccountType());
			//变化金额
			paymentChgDetail.setChgAccount(cashAmount);
			//变化前金额    
			paymentChgDetail.setOldBalance(paymentAcountTotal);
			//变化后余额    
			paymentChgDetail.setBalance(paymentAccountInfo.getAvailableBalance().add(paymentAccountInfo.getNomentionAmount())
					.add(paymentAccountInfo.getFrozenAmount()));
			//进出帐标志    出金
			Integer paymentfromIoFlag = Integer.valueOf(DictionaryUtils.getStringValue(DictionaryKey.IO_FLAG_O));
			paymentChgDetail.setIoFlag(paymentfromIoFlag);
			paymentChgDetail.setChangeDateTime(new Date());
			//状态  正常
			String paymentdetalStatus = DictionaryUtils.getStringValue(DictionaryKey.OPERATE_STATUS_NORMAL);
			paymentChgDetail.setStatus(paymentdetalStatus);
			//交易类型 变化原因：   认证打款
			String paymentAccountTxnType = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_AUTH_PAY);
			paymentChgDetail.setTxnType(paymentAccountTxnType);
			paymentChgDetail.setUpdateDateTime(new Date());
			//插入数据
			accountOperateBuilder.addAccountMoneyChgDetail(paymentChgDetail);
			
			//12、根据用户信息表   ，判断是个人用户还是企业用户，更新用户或企业信息
			//个人    USER_TYPE_PERSONAL
			//认证状态     等待确认打款金额
			String authStatus = DictionaryUtils.getStringValue(DictionaryKey.AUTH_STATUS_WAIT_CONFIRM);
			PersonalUser personalUser = null ;
			EnterpriseUser enterpriseUser = null;
			if(userAccountInfo.getUserType().equals(userType)){
				personalUser = new PersonalUser();
				//用户identerpriseUser
				personalUser.setUserId(userId);
				personalUser.setAuthStatus(authStatus);
				personalUser.setCreateDatetime(new Date());
				personalUser.setUpdateDatetime(new Date());
				//事务操作
				tradeServiceSupport.applyMoneyPersonalUser(accountOperateBuilder,personalUser,userBankAuInfo);
			}
			if(userAccountInfo.getUserType().equals(enterprise)){
				enterpriseUser = new EnterpriseUser();
				//用户id
				enterpriseUser.setUserId(userId);
				//认证状态
				enterpriseUser.setAuthStatus(authStatus);
				enterpriseUser.setCreateDatetime(new Date());
				enterpriseUser.setUpdateDatetime(new Date());
				//事务操作
				tradeServiceSupport.applyMoneyEnterpriseUser(accountOperateBuilder,enterpriseUser,userBankAuInfo);
			}
			ApplyMoneyResponse message = new ApplyMoneyResponse();
			String accountId = DictionaryUtils.getStringValue(DictionaryKey.ACCOUNT_PLATFORM_OWN);
			message.setAccountSn(newAccountSn);
			message.setAccountId(accountId);
			//返回账号操作流水号
			return ResponseUtils.buildSuccessResponse(message);
		} catch (Exception e) {
			AttachmentResponse<ApplyMoneyResponse> response = ResponseUtils
					.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
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
	@Override
	public AttachmentResponse<Long> accountReChargeReturn(Long accountSn, String bankId, String bankAccount, String note) {
		try {
			//入参判断
			if(accountSn == null || bankAccount == null){
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"accountSn Or bankAccount"});
			}
			
			AttachmentResponse<Long> newAccountSn = tradeServiceSupport.accountReChargeReturn(accountSn, bankId, bankAccount, note);
			//返回账号操作流水号
			return newAccountSn;
		} catch (Exception e) {
			AttachmentResponse<Long> response = ResponseUtils
					.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
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
	@Override
	public AttachmentResponse<Long> accountRechargePay(Long fromAccountId, Long toAccountId, String tradeSn,
			BigDecimal cashAmount, String bankId, String bankAccount, String note) {
		try {
			//入参检查
			if (fromAccountId == null || toAccountId == null || tradeSn == null || cashAmount == null || bankAccount == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[]{"toAccountId Or toAccountId"
						+ "Or tradeSn Or cashAmount Or bankAccount"});
			}
			if (fromAccountId.equals(toAccountId)) {
				return ResponseUtils.buildFailureResponse(TradeConstants.FROM_TO_MUST_NOT_EQUALS);
			}
			AccountOperInfo accountInfo = new AccountOperInfo();
			accountInfo.setTxnSeq(tradeSn);
			String txn = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_PAY);
			accountInfo.setTxnType(txn);
			accountInfo = accountOperInfoDao.selectOne(accountInfo);
			if(accountInfo!=null){
				return ResponseUtils.buildFailureResponse(TradeConstants.REPEATED_REQUEST);
			}
			
			AttachmentResponse<Long> newTransferAccountSn = tradeServiceSupport.accountRechargePay(fromAccountId, toAccountId, tradeSn, cashAmount, bankId, bankAccount, note);
			//返回账号操作流水号
			return newTransferAccountSn;
		} catch (Exception e) {
			AttachmentResponse<Long> response = ResponseUtils
					.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
	}

	/**
	 * 3.98	查询交易流水号是否存在
	 * @author wangpeng
	 * @date 2014年7月30日 下午5:28:42
	 * @param tradeSn 交易流水号
	 * @param Long txnType
	 * @return AttachmentResponse<AccountOperInfo> 附加对象为账户操作流水对象
	 * @since 1.0
	 * @see
	 */
	@Override
	public AttachmentResponse<AccountOperInfo> getAccountOperInfoByTradeSn(String tradeSn,String txnType) {
		try {
			if (tradeSn == null|| txnType ==null ) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL, new String[]{"tradeSn"});
			}
			AccountOperInfo accountOperInfo = new AccountOperInfo();
			accountOperInfo.setTxnSeq(tradeSn);
			accountOperInfo.setTxnType(txnType);
			accountOperInfo = accountOperInfoDao.selectOne(accountOperInfo);
			return ResponseUtils.buildSuccessResponse(accountOperInfo);
		} catch (Exception e) {
			AttachmentResponse<AccountOperInfo> response = ResponseUtils.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		}
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
	@Override
	public AttachmentResponse<Long> freezeForWithdraw(Long accountId,
			String tradeSn, BigDecimal cashAmount, BigDecimal feeAmount,
			String bankId, String bankAccount, String note) {
		try {
			// 入参检查
			if (accountId == null || tradeSn == null || cashAmount == null) {
				return ResponseUtils.buildFailureResponse(GlobalConstants.ARGUMENTS_IS_NULL,new String[] { "accountId Or tradeSn Or cashAmount" });
			}
			AccountOperInfo accountInfo = new AccountOperInfo();
			accountInfo.setTxnSeq(tradeSn);
			String txn = DictionaryUtils.getStringValue(DictionaryKey.TXN_TYPE_FROZEN);
			accountInfo.setTxnType(txn);
			accountInfo = accountOperInfoDao.selectOne(accountInfo);
			if(accountInfo!=null){
				return ResponseUtils.buildFailureResponse(TradeConstants.REPEATED_REQUEST);
			}
			AttachmentResponse<Long> newAccountSn = tradeServiceSupport.freezeForWithdraw(accountId, tradeSn, cashAmount, feeAmount, bankId, bankAccount, note);
			//返回账号操作流水号
			return newAccountSn;
			} catch (Exception e) {
				AttachmentResponse<Long> response = ResponseUtils
						.buildExceptionResponse();
				logger.error(response.getReturnMsg(), e);
				return response;
			}
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
		 * 查询手续费账户接口，返回账户对象
		 * @author wangpeng
		 * @date 2014年8月18日  下午2:54:34
		 * @return AttachmentResponse<AccountInfo>
		 * @since 1.0
		 */
		public AttachmentResponse<AccountInfo> getFeePaymentAccount(){
			try{
				Long account_platfrom_free = DictionaryUtils.getLongValue(DictionaryKey.ACCOUNT_PLATFORM_FEE);
				AttachmentResponse<AccountInfo> freeAccountResponse = accountServiceSupport.getAccountInfo(account_platfrom_free);
				AccountInfo freeAccount = freeAccountResponse.getAttachment();
				return ResponseUtils.buildSuccessResponse(freeAccount);
			} catch (Exception e) {
			AttachmentResponse<AccountInfo> response = ResponseUtils
					.buildExceptionResponse();
			logger.error(response.getReturnMsg(), e);
			return response;
		 }
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
}
