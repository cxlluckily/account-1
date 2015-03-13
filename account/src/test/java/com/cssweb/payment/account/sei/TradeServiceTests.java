package com.cssweb.payment.account.sei;

import java.math.BigDecimal;

import org.junit.Test;

import com.cssweb.payment.account.vo.ApplyMoneyResponse;
import com.cssweb.payment.account.vo.FrozenResponse;
import com.cssweb.payment.account.vo.FrozenTxResponse;
import com.cssweb.payment.account.vo.SecuredAccountRefundResponse;
import com.cssweb.payment.common.response.AttachmentResponse;

public class TradeServiceTests extends TestCase {
	

	//private String country = DictionaryUtils.getStringValue(DictionaryKey.COUNTRY_CODE_CN);
	
	/**
	 * 3.3账户充值接口
	* @param  accountId 账户id
	* @param  tradeSn 交易流水号
	* @param  cashAmount 充值金额
	* @param  fundChannelSn 具体资金渠道生成流水号(银行通道流水号/刷预付卡流水号)
	* @param  bankAccount 银行账户
	* @param  note
	* @param  externalOrderNo 外部系统订单号
	* @return AttachmentResponse<String>    返回类型
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-23 下午5:07:16 
	* @throws
	 */
	@Test
	public void testAccountRecharge() {
		
		//AttachmentResponse<Long> response = tradeService.accountRecharge(49L,"3",BigDecimal.ONE,"50","319456082671","测试","123123");
		AttachmentResponse<Long> response = tradeService.accountRecharge(296L,"02201503061720492802850613313073",BigDecimal.valueOf(2l),"TP000001","9876543210","充值","411503066245046164");

		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}
	
	/**
	 * 3.4	平台手续费账户充值接口
	* @param  tradeSn 交易流水号
	* @param  cashAmount 手续费金额
	* @param  fundChannelSn  具体资金渠道生成流水号(银行通道流水号/刷预付卡流水号)
	* @param  bankId 银行的id
	* @param  bankAccount 银行账户  根据bankAccount查找平台信息
	* @param  note 备注
	* @return AttachmentResponse<Long>    返回类型
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-24 上午10:08:26 
	* @throws
	 *//*
	@Test
	public void testFeeAcountRecharge() {
		
		AttachmentResponse<Long> response = tradeService.feeAcountRecharge("9089420",BigDecimal.ONE,"890381293","01040000","6217850100000000631","ceshi");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}

	*//**
	 * 3.5	账户资金冻结接口
	* @param  accountId 账户号
	* @param  tradeSn 交易流水号
	* @param  cashAmount 冻结金额
	* @param  bankAccount  银行账户
	* @param  note
	* @return AttachmentResponse<Long>    返回类型
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-24 下午4:26:28 
	* @throws
	 *//*
	@Test
	public void testaccountFrozen() {
		
		AttachmentResponse<FrozenResponse> response = tradeService.accountFrozen(55L,"893",BigDecimal.ONE,"50","57","ceshi");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}
	
	*//**
	 * 3.6账户冻结扣款接口
	* @param  accountSn  账户资金冻结流水号
	* @param  note 备注
	* @return AttachmentResponse<Long>    返回类型
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-24 下午8:48:22 
	* @throws
	 *//*
	@Test
	public void testAccountFrozenCharge() {
		
		AttachmentResponse<FrozenResponse> response = tradeService.accountFrozenCharge(258L,"ceshi");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}
	
	*//**
	 * 3.7	账户冻结提现接口
	* @param accountSn 账户资金冻结流水号
	* @param bankId 银行id
	* @param bankAccount 银行账户   查询平台id时根据bankAccount来查
	* @param note 备注
	* @param 设定文件
	* @return AttachmentResponse<Long>    返回类型
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-25 下午2:26:04 
	* @throws
	 *//*
	@Test
	public void testAccountFrozenTx() {
		
		AttachmentResponse<FrozenTxResponse> response = tradeService.accountFrozenTx(307L,"01050000","6217850100000000631","ceshi");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}
	
	*//**
	 * 3.8	账户资金冻结解冻接口
	* @param accountSn 账户资金冻结流水号
	* @param note 备注
	* @return AttachmentResponse<Long> 返回类型
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-26 上午10:06:38 
	* @throws
	 *//*
	@Test
	public void testAccountUnfrozen() {
		
		AttachmentResponse<FrozenResponse> response = tradeService.accountUnfrozen(220L,"ceshi");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}
	
	*//**
	 * 3.9	账户支付接口
	* @param  fromAccountId 支出账户ID
	* @param  toAccountId 收入账户ID
	* @param  tradeSn 交易流水号
	* @param  cashAmount 转账金额
	* @param  note 备注
	* @return AttachmentResponse<Long>    返回类型
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-26 下午2:43:38 
	* @throws
	 *//*
	@Test
	public void testAccountPay() {
		
		AttachmentResponse<Long> response = tradeService.accountPay(24L,200L,"1012321",BigDecimal.ONE,"ceshi");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}
	
	*//**
	 * 3.10	账户转账接口
	* @param  fromAccountId 支出账户ID
	* @param  toAccountId 收入账户ID
	* @param  tradeSn 交易流水号
	* @param  cashAmount 转账金额
	* @param  note 备注
	* @param @return   设定文件
	* @return AttachmentResponse<Long>    返回类型
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-2 下午 4:48:28 
	* @throws
	 *//*
	@Test
	public void testAccountTransfer() {
		
		AttachmentResponse<Long> response = tradeService.accountTransfer(24L,200L,"1012321",BigDecimal.ONE,"ceshi");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}
	
	
	*//**
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
	 *//*
	@Test
	public void testSecuredAccountTransfer() {
		
		AttachmentResponse<Long> response = tradeService.securedAccountTransfer(24L,200L,"1012321",BigDecimal.ONE,"ceshi");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}
	
	*//**
	 * 3.12	账户担保交易扣款接口
	* @param  tradeSn 担保交易账户转账流水号
	* @param  note 备注
	* @return AttachmentResponse<Long>    返回类型
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-27  上午11:08:47 
	* @throws
	 *//*
	@Test
	public void testSecuredAccountCharge() {
		
		AttachmentResponse<Long> response = tradeService.securedAccountCharge(220L,"ceshi");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}
	
	*//**
	 * 3.13	账户退款接口
	* @param  fromAccountId 支出账户ID
	* @param  toAccountId 收入账户ID
	* @param  tradeSn 交易流水号
	* @param  accountSn 原交易账户转账流水号
	* @param  cashAmount  退款金额
	* @param  isfrozen 收入账户入账是否冻结(1 冻结 0 不冻结)
	* @param  note 备注
	* @return AttachmentResponse<String>    返回类型
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-26 下午8:14:15 
	* @throws
	 *//*
	@Test
	public void testAccountRefund() {
		
		AttachmentResponse<Long> response = tradeService.accountRefund(200L,200L,"123",220L,BigDecimal.ONE,true,"ceshi");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}
	
	*//**
	* 3.14	账户担保交易取消接口
	* @param accountSn 原交易账户转账流水号
	* @param isfrozen 收入账户入账是否冻结(1 冻结 0 不冻结)
	* @param note 备注
	* @return AttachmentResponse<Long> 返回类型
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-27 下午3:02:35 
	* @throws
	 *//*
	@Test
	public void testSecuredAccountRefund() {
		
		//不冻结
		//AttachmentResponse<Long> response = tradeService.securedAccountRefund(220L,true,"ceshi");
		//冻结
		AttachmentResponse<SecuredAccountRefundResponse> response = tradeService.securedAccountRefund(220L,false,"ceshi");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}
	
	*//**
	 * 3.15认证打款申请接口
	* @Description: TODO()
	* @param  userId 用户ID
	* @param  cashAmount 打款金额
	* @param  Bankname 认证银行名称
	* @param  bankCardNum 银行卡号
	* @param  bankId 银行id
	* @param  note 备注
	* @return AttachmentResponse<String>
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-27 下午6:08:32 
	* @throws
	 *//*
	@Test
	public void testApplyMoney() {
		
		AttachmentResponse<ApplyMoneyResponse> response = tradeService.applyMoney(70L, BigDecimal.valueOf(0.1),"中国银行","123","0","20","1","8753226891","ceshi");
		logger.debug("流水号："+response.getAttachment().getAccountSn());
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("打款结果：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug("自有资金平台账户Id:"+response.getAttachment().getAccountId());
	}
	
	*//**
	  * 3.16账户充值退回接口
	* @param  accountSn 账户退款流水号
	* @param  bankId 银行id
	* @param  note 备注
	* @param @return   
	* @return AttachmentResponse<AccountMoneyChgLog>
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-25 下午3:05:32 
	* @throws
	 *//*
	@Test
	public void testAccountReChargeReturn() {
		
		AttachmentResponse<Long> response = tradeService.accountReChargeReturn(220L,"50","20","ceshi");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}
	
	*//**
	* 3.96 账户充值支付转账接口  
	* @param  fromAccountId  充值支出/买家账户ID
	* @param  toAccountId   收入/卖家账户ID
	* @param  tradeSn  交易流水号
	* @param  cashAmount  充值转账金额
	* @param  bankAccount 银行账户
	* @param  note 备注
	* @param @return   
	* @return AttachmentResponse<Long>
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-30 下午2:54:32 
	* @throws
	 *//*
	@Test
	public void testAccountRechargePay() {
		
		AttachmentResponse<Long> response = tradeService.accountRechargePay(30L,200L,"220",BigDecimal.ONE,"50","20","ceshi");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}
	
	@Test
	public void testRefundFailureUnfrozen() {
		tradeService.refundFailureUnfrozen(119L, "网银支付退款-解冻");
	}
	*//**
	 * 3.99 账户资金提现冻结（不含手续费）
	 * @author wangpeng
	 * @date 2014年7月30日 下午5:54:34
	 * @param accountId 账户Id
	 * @param tradeSn 交易流水号
	 * @param cashAmount 冻结金额
	 * @param feeAmount 手续费
	 * @param bankId 银行Id
	 * @param bankAccount 银行的账号 
	 * @param note 备注
	 * @return AttachmentResponse<Long> 附加对象为账户操作流水号
	 * @since 1.0
	 *//*
	@Test
	public void testFreezeForWithdraw(){
		AttachmentResponse<Long> response = tradeService.freezeForWithdraw(
				48L, "032014080816133345", BigDecimal.TEN, BigDecimal.ONE, "01050000", "6217850100000000631", "ceshi");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}*/
}
