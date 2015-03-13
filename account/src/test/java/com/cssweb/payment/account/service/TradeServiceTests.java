/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cssweb.payment.account.pojo.AccountInfo;
import com.cssweb.payment.account.pojo.AccountOperInfo;
import com.cssweb.payment.account.pojo.BankInfo;
import com.cssweb.payment.account.sei.ITradeService;
import com.cssweb.payment.account.test.TestCase;
import com.cssweb.payment.account.vo.ApplyMoneyResponse;
import com.cssweb.payment.account.vo.FrozenResponse;
import com.cssweb.payment.account.vo.FrozenTxResponse;
import com.cssweb.payment.account.vo.SecuredAccountRefundResponse;
import com.cssweb.payment.common.response.AttachmentResponse;
import com.cssweb.payment.omp.sei.IDictionaryService;

/**
 * 交易服务接口测试类
 * 
 * @author wangpeng
 * @version 1.0 (2014年7月28日  下午5:02:37)
 * @since 1.0
 */
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class TradeServiceTests extends TestCase{
	
	@Resource
	private ITradeService tradeService;
	@Resource
	private IDictionaryService dictionaryService;
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
	* @throws
	 */
	@Test
	public void testAccountRecharge() {
		
		//AttachmentResponse<Long> response = tradeService.accountRecharge(48L,"312432543984325435",BigDecimal.ONE,
				//"01040000","62178501000000234230063112312","充值","098174632143");
		AttachmentResponse<Long> response = tradeService.accountRecharge(296L,"02201503061720492802850613313073",BigDecimal.valueOf(2l),"TP000001","9876543210","充值","411503066245046164");

		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}
	
/*	*//**
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
	 *//*
	@Test
	public void testFeeAcountRecharge() {
		
		AttachmentResponse<Long> response = tradeService.feeAcountRecharge("90123789234",BigDecimal.ONE,
				"8908123098432123","01040000","6217850100000000631","手续费账户充值");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}

	*//**
	 * 1005	账户资金冻结接口
	* @param  accountId 账户号
	* @param  tradeSn 交易流水号
	* @param  cashAmount 冻结金额
	* @param  bankId  银行id
	* @param  bankAccount 
	* @param  note
	* @return AttachmentResponse<FrozenResponse>    返回类型
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-24 下午4:26:28 
	* @throws
	 *//*
	@Test
	public void testaccountFrozen() {
		
		AttachmentResponse<FrozenResponse> response = tradeService.accountFrozen(47L,"0920140811162127617311836290977",BigDecimal.ONE,"01040000","6217850100000000631","ceshi");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}
	
	*//**
	 * 1006 账户冻结扣款接口
	* @param  accountSn  账户资金冻结流水号
	* @param  note 备注
	* @return AttachmentResponse<FrozenResponse>  返回类型
	* @author wangpeng@cssweb.sh.cn
	* @date 2014-7-24 下午8:48:22 
	* @throws
	* @ return 1200 
	 *//*
	@Test
	public void testAccountFrozenCharge() {
		
		AttachmentResponse<FrozenResponse> response = tradeService.accountFrozenCharge(60L,"冻结扣款");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}
	
	*//**
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
	 *//*
	@Test
	public void testAccountFrozenTx() {
		
		AttachmentResponse<FrozenTxResponse> response = tradeService.accountFrozenTx(642L,"01040000","591902896710201","提现扣款");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}
	
	*//**
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
	 *//*
	@Test
	public void testAccountUnfrozen() {
		AttachmentResponse<FrozenResponse> response = tradeService.accountUnfrozen(18280L,"冻结解冻");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}
	
	*//**
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
	 *//*
	@Test
	public void testAccountPay() {
		
		AttachmentResponse<Long> response = tradeService.accountPay(241L,242L,"1012321",BigDecimal.valueOf(100.0),"余额支付接口");
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
		
		AttachmentResponse<Long> response = tradeService.accountTransfer(266L,267L,UUID.randomUUID().toString(),BigDecimal.ONE,"转账");
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
	 *//*
	@Test
	public void testAccountRefund() {
		
		AttachmentResponse<Long> response = tradeService.accountRefund(266L,267L,"0620140820112754298155338984886252",
				19341L,BigDecimal.valueOf(1),false,"余额支付退款余额");
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
	 *//*
	@Test
	public void testApplyMoney() {
		
		AttachmentResponse<ApplyMoneyResponse> response = tradeService.applyMoney(
				223L,BigDecimal.valueOf(0.11),"中国银行","6222021001116245702","0","01040000","6217850100000000748","891204326512946","认证打款申请");
		logger.debug("流水号："+response.getAttachment().getAccountSn());
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("打款结果：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug("自有资金平台账户Id:"+response.getAttachment().getAccountId());
	}
	
	*//**
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
	 *//*
	@Test
	public void testAccountReChargeReturn() {
		
		AttachmentResponse<Long> response = tradeService.accountReChargeReturn(1072L,"01040000","6217850100000000748","账户充值退回");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}
	
	*//**
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
	 *//*
	@Test
	public void testAccountRechargePay() {
		
		AttachmentResponse<Long> response = tradeService.accountRechargePay(266L,267L,"22123412320",BigDecimal.ONE,"01040000","6217850100000000748","充值支付转账");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}
	
	*//**
	 * 3.98	查询交易流水号是否存在
	 * @author wangpeng
	 * @date 2014年7月30日 下午5:28:42
	 * @param tradeSn 交易流水号
	 * @param Long txnType
	 * @return AttachmentResponse<Long> 附加对象为账户操作流水对象
	 * @since 1.0
	 * @see
	 *//*
	@Test
	public void testGetAccountOperInfoByTradeSn(){
		AttachmentResponse<AccountOperInfo> response = tradeService.getAccountOperInfoByTradeSn("0420114378242569075", "1");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}
	
	*//**
	 * 根据bankAccount查询平台账户，返回账户对象
	 * @author wangpeng
	 * @date 2014年8月15日  下午2:04:34
	 * @param bankAccount
	 * @return AttachmentResponse<AccountInfo>
	 * @since 1.0
	 *//*
	@Test
	public void testGetPaymentAccountByBankAccount(){
		AttachmentResponse<AccountInfo> response = tradeService.getPaymentAccountByBankAccount("891723");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}
	
	*//**
	 * 查询手续费账户接口，返回账户对象
	 * @author wangpeng
	 * @date 2014年8月18日  下午2:54:34
	 * @return AttachmentResponse<AccountInfo>
	 * @since 1.0
	 *//*
	@Test
	public void testGetFreeAccount(){
		AttachmentResponse<AccountInfo> response = tradeService.getFeePaymentAccount();
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}
	
	*//**
	 * 1100   账户资金提现冻结（含手续费）
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
	 * @since 1.0
	 *//*
	@Test
	public void testFreezeForWithdraw(){
		AttachmentResponse<Long> response = tradeService.freezeForWithdraw(268L,"98852123904135",BigDecimal.valueOf(100),BigDecimal.valueOf(2),
				"01040000","6217850100000000631","提现冻结");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}
	
	*//**
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
	 *//*
	@Test
	public void testRefundFailureUnfrozen(){
		AttachmentResponse<FrozenResponse> response = tradeService.refundFailureUnfrozen(642L,"退回失败 解冻");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}
	
	*//**
	 * 根据平台账号获取银行信息
	 * 
	 * @author WangPeng
	 * @date 2014年8月20日 下午4:50
	 * @param paymentAccountId
	 * @since 1.0
	 *//*
	@Test
	public void testGetBankInfoByPaymentAccountId(){
		AttachmentResponse<BankInfo> response = tradeService.getBankInfoByPaymentAccountId(10L);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		logger.debug(response.getAttachment().toString());
	}
	
	@Test
	public void testTrade() throws IOException{
		ExecutorService service = Executors.newFixedThreadPool(40);
		for(int i=0;i<30;i++){
			service.submit(new MyThread());
		}
		System.in.read();
	}
	class MyThread implements Runnable {
		public void run() {
			try {
				for(int i=0;i<40;i++){
					Math.random();
					//转账
					//tradeService.accountTransfer(267L,266L,UUID.randomUUID().toString(),BigDecimal.ONE,"转账1200_02");
					//支付
					tradeService.accountPay(267L,266L,UUID.randomUUID().toString(),BigDecimal.valueOf(1.0),"余额支付001");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}*/
	
	
}
/*class MyThread extends Thread {
	private ITradeService tradeService;
	public MyThread(ITradeService tradeService){
		this.tradeService = tradeService;
	}
	public void run() {
		try {
			tradeService.accountTransfer(266L,267L,"1012321333",BigDecimal.ONE,"转账");
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}*/