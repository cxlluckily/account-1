package com.cssweb.payment.account.sei;

import java.math.BigDecimal;

import com.cssweb.payment.account.pojo.AccountInfo;
import com.cssweb.payment.account.pojo.AccountOperInfo;
import com.cssweb.payment.account.pojo.BankInfo;
import com.cssweb.payment.account.vo.ApplyMoneyResponse;
import com.cssweb.payment.account.vo.FrozenResponse;
import com.cssweb.payment.account.vo.FrozenTxResponse;
import com.cssweb.payment.account.vo.SecuredAccountRefundResponse;
import com.cssweb.payment.common.response.AttachmentResponse;


/**
 * 账户系统提供给交易系统的接口
 *
 * <!--
 * 历史记录：
 * ---------------------------------------------------------------------------------------------------------------------
 * 2014-07-01，聂亚春，新建
 * -->
 *
 * @author <a href="mailto:nieyc@cssweb.sh.cn">聂亚春</a>
 * @version 1.00
 * @since 1.00
 */

public interface ITradeService {

	
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
	public AttachmentResponse<Long> accountRecharge(Long accountId, String tradeSn, BigDecimal cashAmount, String bankId, String bankAccount,
			String note, String externalOrderNo);
	
		
	/**
	 * 3.4	平台手续费账户充值接口
	* @param  tradeSn 交易流水号
	* @param  cashAmount 手续费金额
	* @param  fundChannelSn  具体资金渠道生成流水号(银行通道流水号/刷预付卡流水号)
	* @param  bankId 银行id
	* @param  note 备注
	* @return AttachmentResponse<Long>    返回类型
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-24 上午10:08:26 
	* @throws
	 */
	public AttachmentResponse<Long> feeAcountRecharge(String tradeSn, BigDecimal cashAmount, String fundChannelSn,
			String bankId, String bankAccount, String note);
	
	/**
	 * 3.5	账户资金冻结接口
	* @param  accountId 账户号
	* @param  tradeSn 交易流水号
	* @param  cashAmount 冻结金额
	* @param  bankId  银行id
	* @param  note
	* @return AttachmentResponse<FrozenResponse>    返回类型
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-24 下午4:26:28 
	* @throws
	 */
	public AttachmentResponse<FrozenResponse> accountFrozen(Long accountId, String tradeSn, BigDecimal cashAmount, String bankId, String bankAccount,
			String note);
	
	
	
	/**
	 * 3.6账户冻结扣款接口
	* @param  accountSn  账户资金冻结流水号
	* @param  note 备注
	* @return AttachmentResponse<FrozenResponse>    返回类型
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-24 下午8:48:22 
	* @throws
	 */
	public AttachmentResponse<FrozenResponse> accountFrozenCharge(Long accountSn, String note) ;

	
	
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
	public AttachmentResponse<FrozenTxResponse>  accountFrozenTx(Long accountSn, String bankId, String bankAccount, String note);

	
	
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
	public AttachmentResponse<FrozenResponse> accountUnfrozen(Long accountSn, String note);
	
	
	
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
	public AttachmentResponse<Long> accountPay(Long fromAccountId, Long toAccountId, String tradeSn,
			BigDecimal cashAmount, String note);
	

	
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
	public AttachmentResponse<Long> accountTransfer(Long fromAccountId, Long toAccountId, String tradeSn,
			BigDecimal cashAmount, String note);
	
	
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
	public AttachmentResponse<Long> securedAccountTransfer(Long fromAccountId, Long toAccountId, String tradeSn,
			BigDecimal cashAmount, String note);
	
	
	/**
	 * 3.12	账户担保交易扣款接口
	* @param  tradeSn 担保交易账户转账流水号
	* @param  note 备注
	* @return AttachmentResponse<Long>    返回类型
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-27  上午11:08:47 
	* @throws
	 */
	public AttachmentResponse<Long> securedAccountCharge(long tradeSn,
			String note);
	
	
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
	public AttachmentResponse<Long> accountRefund(Long fromAccountId, Long toAccountId, String tradeSn, Long accountSn,
			BigDecimal cashAmount, boolean isfrozen, String note);
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
	public AttachmentResponse<FrozenResponse> refundFailureUnfrozen(Long accountSn, String note);
	/**
	 * 3.14	账户担保交易取消接口
	* @param accountSn 原交易账户转账流水号
	* @param isfrozen 收入账户入账是否冻结(1 冻结 0 不冻结)
	* @param note 备注
	* @return AttachmentResponse<AccountUserWater>    返回类型
	* @date 2014-7-2 下午5:00:35 
	* @throws
	 */
	public AttachmentResponse<SecuredAccountRefundResponse> securedAccountRefund(Long accountSn, boolean isfrozen, String note);
	

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

	public AttachmentResponse<ApplyMoneyResponse> applyMoney(Long userId, BigDecimal cashAmount, String Bankname,
			String bankCardNum, String bankRestatus, String bankId, String bankAccount,String tradeSn, String note);
	
	

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
	 */
	public AttachmentResponse<Long> accountReChargeReturn(Long accountSn, String bankId, String bankAccount, String note);
	
	
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
	public AttachmentResponse<Long> accountRechargePay(Long fromAccountId, Long toAccountId, String tradeSn, BigDecimal cashAmount, String bankId, String bankAccount, String note);
	
	/**
	 * 3.98	查询交易流水号是否存在
	 * @author wangpeng
	 * @date 2014年7月30日 下午5:28:42
	 * @param tradeSn 交易流水号
	 * @param Long txnType
	 * @return AttachmentResponse<Long> 附加对象为账户操作流水对象
	 * @since 1.0
	 * @see
	 */
	public AttachmentResponse<AccountOperInfo> getAccountOperInfoByTradeSn(String tradeSn,String txnType);
	
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
	public AttachmentResponse<Long> freezeForWithdraw(Long accountId, String tradeSn, BigDecimal cashAmount,
			BigDecimal feeAmount, String bankId, String bankAccount, String note);
	
	/**
	 * 根据bankAccount查询平台账户，返回账户对象
	 * @author wangpeng
	 * @date 2014年8月15日  下午2:04:34
	 * @param bankAccount
	 * @return AttachmentResponse<AccountInfo>
	 * @since 1.0
	 */
	public AttachmentResponse<AccountInfo> getPaymentAccountByBankAccount(String bankAccount);
	
	/**
	 * 查询手续费账户接口，返回账户对象
	 * @author wangpeng
	 * @date 2014年8月18日  下午2:54:34
	 * @return AttachmentResponse<AccountInfo>
	 * @since 1.0
	 */
	public AttachmentResponse<AccountInfo> getFeePaymentAccount();
	
	/**
	 * 根据平台账号获取银行信息
	 * 
	 * @author WangPeng
	 * @date 2014年8月20日 下午4:50
	 * @param paymentAccountId
	 * @since 1.0
	 */
	public AttachmentResponse<BankInfo> getBankInfoByPaymentAccountId(Long paymentAccountId);
}
