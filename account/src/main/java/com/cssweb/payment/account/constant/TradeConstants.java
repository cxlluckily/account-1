package com.cssweb.payment.account.constant;
/**
 * 交易接口常量
 * 
 * @author duxiaohua
 * @version 1.0 (2014年7月20日 下午1:26:09)
 * @since 1.0
 */
public abstract class TradeConstants {
	/**
	 * 账号冻结扣款失败
	 */
	public static final Integer ACCOUNT_SEQUESTRATE_FAILURE = 1200;
	
	/**
	 * 账号冻结提现失败
	 */
	public static final Integer ACCOUNT_SEQUESTRATE_WITHDRAWALS_FAILURE = 1201;
	
	/**
	 * 余额不足
	 */
	public static final Integer ACCOUNT_BALANCE_LACK = 1202;
	
	/**
	 * 用户不存在
	 */
	public static final Integer USER_NOT_EXIT = 1203;
	
	/**
	 * 认证打款金额必须在0-1之间
	 */
	public static final Integer AUTHENTICATION_PAYMENT_RANGE_WORNG= 1204;
	
	/**
	 * 退款出入金账户与原出入金账户不匹配
	 */
	public static final Integer REFUND_ACCOUNT_MISMATCHING = 1205;
	
	/**
	 * 流水号不存在
	 */
	public static final Integer ACCOUNT_SN_NOT_EXIT = 1206;
	/**
	 * 重复请求
	 */
	public static final Integer REPEATED_REQUEST = 1207;
	/**
	 * 账户不存在
	 */
	public static final Integer ACCOUNT_NOT_EXIT = 1208;
	/**
	 * 收款方和付款方不能相同
	 */
	public static final Integer FROM_TO_MUST_NOT_EQUALS = 1209;
	/**
	 * 平台账号不存在
	 */
	public static final Integer PAYMENT_ACCOUNT_NOT_EXIT = 1210;
	/**
	 * 冻结金额不足,账户异常
	 */
	public static final Integer FROZENAMOUNT_NOT_ENOUGH = 1211;
}
