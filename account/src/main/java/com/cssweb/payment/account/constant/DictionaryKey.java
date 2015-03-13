/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.constant;

/**
 * 字典表键值常量
 * 
 * @author DuXiaohua
 * @version 1.0 (2014年7月27日 上午10:01:44)
 * @since 1.0
 * @see
 */
public abstract class DictionaryKey {
	/**
	 * 用户类型
	 */
	public static final String USER_TYPE = "ACM_USER_TYPE";
	/**
	 * 个人
	 */
	public static final String USER_TYPE_PERSONAL = "ACM_USER_TYPE_PERSONAL";
	/**
	 * 企业账户
	 */
	public static final String USER_TYPE_ENTERPRISE = "ACM_USER_TYPE_ENTERPRISE";
	/**
	 * 平台自有账户
	 */
	public static final String USER_TYPE_PLATFORM = "ACM_USER_TYPE_PLATFORM";
	/**
	 * 账户类型
	 */
	public static final String ACCOUNT_TYPE = "ACM_ACCOUNT_TYPE";
	/**
	 * 主账户
	 */
	public static final String ACCOUNT_TYPE_MAIN = "ACM_ACCOUNT_TYPE_MAIN";
	/**
	 * 电子现金账户
	 */
	public static final String ACCOUNT_TYPE_CASH = "ACM_ACCOUNT_TYPE_CASH";
	/**
	 * 预付卡
	 */
	public static final String ACCOUNT_TYPE_PREPAID = "ACM_ACCOUNT_TYPE_PREPAID";
	/**
	 * 账户状态
	 */
	public static final String ACCOUNT_STATUS = "ACM_ACCOUNT_STATUS";
	
	/**
	 * 正常
	 */
	public static final String ACCOUNT_STATUS_NORMAL = "ACM_ACCOUNT_STATUS_NORMAL";
	/**
	 * 冻结
	 */
	public static final String ACCOUNT_STATUS_FROZEN = "ACM_ACCOUNT_STATUS_FROZEN";
	/**
	 * 注销
	 */
	public static final String ACCOUNT_STATUS_CANCEL = "ACM_ACCOUNT_STATUS_CANCEL";
	/**
	 * 币种
	 */
	public static final String CURRENCY_TYPE = "ACM_CURRENCY_TYPE";
	/**
	 * 人民币
	 */
	public static final String CURRENCY_TYPE_RMB = "ACM_CURRENCY_TYPE_RMB";
	/**
	 * 港币
	 */
	public static final String CURRENCY_TYPE_HKD = "ACM_CURRENCY_TYPE_HKD";
	/**
	 * 美元
	 */
	public static final String CURRENCY_TYPE_USD = "ACM_CURRENCY_TYPE_USD";
	/**
	 * 注册类型
	 */
	public static final String REG_TYPE = "ACM_REG_TYPE";
	/**
	 * 手机注册
	 */
	public static final String REG_TYPE_PHONE = "ACM_REG_TYPE_PHONE";
	/**
	 * 邮箱注册
	 */
	public static final String REG_TYPE_EMAIL = "ACM_REG_TYPE_EMAIL";
	/**
	 * 来源
	 */
	public static final String SOURCE = "ACM_SOURCE";
	/**
	 * PC
	 */
	public static final String SOURCE_PC = "ACM_SOURCE_PC";
	/**
	 * MOBILE
	 */
	public static final String SOURCE_MOBILE = "ACM_SOURCE_MOBILE";
	/**
	 * 实名认证类型
	 */
	public static final String AUTH_TYPE = "ACM_AUTH_TYPE";
	/**
	 * 银行卡认证
	 */
	public static final String AUTH_TYPE_BANK = "ACM_AUTH_TYPE_BANK";
	/**
	 * 运营商认证
	 */
	public static final String AUTH_TYPE_MNO = "ACM_AUTH_TYPE_MNO";
	/**
	 * 公安部认证
	 */
	public static final String AUTH_TYPE_MPS = "ACM_AUTH_TYPE_MPS";
	/**
	 * 实名认证状态
	 */
	public static final String AUTH_STATUS = "ACM_AUTH_STATUS";
	/**
	 * 未认证
	 */
	public static final String AUTH_STATUS_NONE = "ACM_AUTH_STATUS_NONE";
	/**
	 * 已认证
	 */
	public static final String AUTH_STATUS_AUTH = "ACM_AUTH_STATUS_AUTH";
	/**
	 * 已审核
	 */
	public static final String AUTH_STATUS_AUDITED = "ACM_AUTH_STATUS_AUDITED";
	/**
	 * 等待确认打款金额
	 */
	public static final String AUTH_STATUS_WAIT_CONFIRM = "ACM_AUTH_STATUS_WAIT_CONFIRM";
	/**
	 * 审核失败
	 */
	public static final String AUTH_STATUS_AUDIT_FAILURE = "ACM_AUTH_STATUS_AUDIT_FAILURE";
	/**
	 * 等待审核
	 */
	public static final String AUTH_STATUS_WAIT_AUDIT = "ACM_AUTH_STATUS_WAIT_AUDIT";
	/**
	 * 证件类型
	 */
	public static final String ID_TYPE = "ACM_ID_TYPE";
	/**
	 * 身份证
	 */
	public static final String ID_TYPE_IDCARD = "ACM_ID_TYPE_IDCARD";
	/**
	 * 军官证
	 */
	public static final String ID_TYPE_MILITARY = "ACM_ID_TYPE_MILITARY";
	/**
	 * 护照
	 */
	public static final String ID_TYPE_PASSPORT = "ACM_ID_TYPE_PASSPORT";
	/**
	 * 网关类型
	 */
	public static final String GATE_TYPE = "ACM_GATE_TYPE";
	/**
	 * 支付网关
	 */
	public static final String GATE_TYPE_PAY = "ACM_GATE_TYPE_PAY";
	/**
	 * 账户网关
	 */
	public static final String GATE_TYPE_ACCOUNT = "ACM_GATE_TYPE_ACCOUNT";
	/**
	 * 接口类型
	 */
	public static final String CHANNEL_TYPE = "ACM_CHANNEL_TYPE";
	/**
	 * 一般交易
	 */
	public static final String CHANNEL_TYPE_NORMAL = "ACM_CHANNEL_TYPE_NORMAL";
	/**
	 * 担保交易
	 */
	public static final String CHANNEL_TYPE_SECURED = "ACM_CHANNEL_TYPE_SECURED";
	/**
	 * 协议交易
	 */
	public static final String CHANNEL_TYPE_PROTOCOL = "ACM_CHANNEL_TYPE_PROTOCOL";
	/**
	 * 银行卡绑定状态
	 */
	public static final String BAND_STATUS = "ACM_BAND_STATUS";
	/**
	 * 已绑定
	 */
	public static final String BAND_STATUS_BINDING = "ACM_BAND_STATUS_BINDING";
	/**
	 * 已解绑
	 */
	public static final String BAND_STATUS_UNBINDING = "ACM_BAND_STATUS_UNBINDING";
	/**
	 * 银行卡类型
	 */
	public static final String BANK_CARD_TYPE = "ACM_BANK_CARD_TYPE";
	/**
	 * 借记卡
	 */
	public static final String BANK_CARD_TYPE_DEBIT = "ACM_BANK_CARD_TYPE_DEBIT";
	/**
	 * 贷记卡
	 */
	public static final String BANK_CARD_TYPE_CREDIT = "ACM_BANK_CARD_TYPE_CREDIT";
	/**
	 * 预付卡
	 */
	public static final String BANK_CARD_TYPE_PREPAID = "ACM_BANK_CARD_TYPE_PREPAID";
	/**
	 * 账号位置
	 */
	public static final String ACCOUNT_POSITION = "ACM_ACCOUNT_POSITION";
	/**
	 * 可提现
	 */
	public static final String ACCOUNT_POSITION_AVALIBALE = "ACM_ACCOUNT_POSITION_AVALIBALE";
	/**
	 * 冻结
	 */
	public static final String ACCOUNT_POSITION_FROZEN = "ACM_ACCOUNT_POSITION_FROZEN";
	/**
	 * 不可提现
	 */
	public static final String ACCOUNT_POSITION_NOMENTION = "ACM_ACCOUNT_POSITION_NOMENTION";
	/**
	 * 交易类型
	 */
	public static final String TXN_TYPE = "ACM_TXN_TYPE";
	/**
	 * 支付
	 */
	public static final String TXN_TYPE_PAY = "ACM_TXN_TYPE_PAY";
	/**
	 * 退款
	 */
	public static final String TXN_TYPE_REFUND = "ACM_TXN_TYPE_REFUND";
	/**
	 * 退款冻结
	 */
	public static final String TXN_TYPE_REFUND_UNFROZEN = "ACM_TXN_TYPE_REFUND_UNFROZEN";
	/**
	 * 充值
	 */
	public static final String TXN_TYPE_TOPUP = "ACM_TXN_TYPE_TOPUP";
	/**
	 * 支付充值
	 */
	public static final String TXN_TYPE_PAYTOPUP = "ACM_TXN_TYPE_PAYTOPUP";
	/**
	 * 转账
	 */
	public static final String TXN_TYPE_TRANSFER = "ACM_TXN_TYPE_TRANSFER";
	/**
	 * 提现
	 */
	public static final String TXN_TYPE_WITHDRAW = "ACM_TXN_TYPE_WITHDRAW";
	/**
	 * 结算
	 */
	public static final String TXN_TYPE_SETTLEMENT = "ACM_TXN_TYPE_SETTLEMENT";
	/**
	 * 手续费
	 */
	public static final String TXN_TYPE_FEE = "ACM_TXN_TYPE_FEE";
	/**
	 * 充值退回
	 */
	public static final String TXN_TYPE_TOPUP_REFUND = "ACM_TXN_TYPE_TOPUP_REFUND";
	/**
	 * 调账
	 */
	public static final String TXN_TYPE_ADJUSTMENT = "ACM_TXN_TYPE_ADJUSTMENT";
	/**
	 * 线下充值
	 */
	public static final String TXN_TYPE_OFFLINE_TOPUP = "ACM_TXN_TYPE_OFFLINE_TOPUP";
	/**
	 * 无账户支付
	 */
	public static final String TXN_TYPE_NOACCOUNT_PAY = "ACM_TXN_TYPE_NOACCOUNT_PAY";
	/**
	 * 冻结
	 */
	public static final String TXN_TYPE_FROZEN = "ACM_TXN_TYPE_FROZEN"; 
	
	/**
	 * 解冻
	 */
	public static final String TXN_TYPE_UNFROZEN = "ACM_TXN_TYPE_UNFROZEN"; 
	/**
	 * 支付取消
	 */
	public static final String TXN_TYPE_CANCEL = "ACM_TXN_TYPE_CANCEL";
	/**
	 * 打款
	 */
	public static final String TXN_TYPE_AUTH_PAY = "ACM_TXN_TYPE_AUTH_PAY";
	/**
	 * 出站扣款
	 */
	public static final String TXN_TYPE_EXIT_PAY = "ACM_TXN_TYPE_EXIT_PAY";
	/**
	 * 补票扣款
	 */
	public static final String TXN_TYPE_REISSUE_PAY = "ACM_TXN_TYPE_REISSUE_PAY";
	/**
	 * 联机充值
	 */
	public static final String TXN_TYPE_ONLINE_TOPUP = "ACM_TXN_TYPE_ONLINE_TOPUP";
	/**
	 * 进出账标志
	 */
	public static final String IO_FLAG = "ACM_IO_FLAG";
	/**
	 * 支付
	 */
	public static final String IO_FLAG_O = "ACM_IO_FLAG_O";
	/**
	 * 收入
	 */
	public static final String IO_FLAG_I = "ACM_IO_FLAG_I";
	/**
	 * 功能列表
	 */
	public static final String FUNCTION = "ACM_FUNCTION";
	/**
	 * 余额支付
	 */
	public static final String FUNCTION_BALANCE_PAY = "ACM_FUNCTION_BALANCE_PAY";
	/**
	 * 短信提醒
	 */
	public static final String FUNCTION_SMS_REMIND = "ACM_FUNCTION_SMS_REMIND";
	/**
	 * 邮箱提醒
	 */
	public static final String FUNCTION_EMAIL_REMIND = "ACM_FUNCTION_EMAIL_REMIND";
	/**
	 * 功能开通标志
	 */
	public static final String FUNCTION_OPEN = "ACM_FUNCTION_OPEN";
	/**
	 * 开通
	 */
	public static final String FUNCTION_OPEN_YES = "ACM_FUNCTION_OPEN_YES";
	/**
	 * 不开通
	 */
	public static final String FUNCTION_OPEN_NO = "ACM_FUNCTION_OPEN_NO";
	/**
	 * 打款结果
	 */
	public static final String AUTH_RESTATUS = "ACM_AUTH_RESTATUS";
	/**
	 * 成功
	 */
	public static final String AUTH_RESTATUS_SUCCESS = "ACM_AUTH_RESTATUS_SUCCESS";
	/**
	 * 失败
	 */
	public static final String AUTH_RESTATUS_FAILURE = "ACM_AUTH_RESTATUS_FAILURE";
	/**
	 * 银行合作类型
	 */
	public static final String BANK_COOP = "ACM_BANK_COOP";
	/**
	 * 备付金存管行
	 */
	public static final String BANK_COOP_DEPOSITORY = "ACM_BANK_COOP_DEPOSITORY";
	/**
	 * 备付金合作行
	 */
	public static final String BANK_COOP_COOP = "ACM_BANK_COOP_COOP";
	/**
	 * 汇缴行
	 */
	public static final String BANK_COOP_PAYMENT = "ACM_BANK_COOP_PAYMENT";
	/**
	 * 一般接入
	 */
	public static final String BANK_COOP_JOIN = "ACM_BANK_COOP_JOIN";
	/**
	 * 手续费存管行
	 */
	public static final String BANK_COOP_FEE = "ACM_BANK_COOP_FEE";
	/**
	 * 自有资金存管行
	 */
	public static final String BANK_COOP_OWN = "ACM_BANK_COOP_OWN";
	/**
	 * 银行级别
	 */
	public static final String BANK_LEVEL = "ACM_BANK_LEVEL";
	/**
	 * 总行
	 */
	public static final String BANK_LEVEL_HEAD = "ACM_BANK_LEVEL_HEAD";
	/**
	 * 分行
	 */
	public static final String BANK_LEVEL_BRANCH = "ACM_BANK_LEVEL_BRANCH";
	/**
	 * 支行
	 */
	public static final String BANK_LEVEL_SUBBRANCH = "ACM_BANK_LEVEL_SUBBRANCH";
	/**
	 * 运营商
	 */
	public static final String MNO = "ACM_MNO";
	/**
	 * 移动
	 */
	public static final String MNO_MOBILE = "ACM_MNO_MOBILE";
	/**
	 * 联通
	 */
	public static final String MNO_UNICOM = "ACM_MNO_UNICOM";
	/**
	 * 电信
	 */
	public static final String MNO_TELECOM = "ACM_MNO_TELECOM";
	/**
	 * 国籍
	 */
	public static final String COUNTRY_CODE = "ACM_COUNTRY_CODE";
	/**
	 * 中国
	 */
	public static final String COUNTRY_CODE_CN = "COUNTRY_CODE_CN";
	/**
	 * 美国
	 */
	public static final String COUNTRY_CODE_US = "COUNTRY_CODE_US";
	/**
	 * 香港
	 */
	public static final String COUNTRY_CODE_HK = "COUNTRY_CODE_HK";
	/**
	 * 安全问题
	 */
	public static final String OMP_SECURITY_ISSUES = "OMP_SECURITY_ISSUES";
	/**
	 * 安全问题一：你的小学校长叫什么？
	 */
	public static final String SECURITY_ISSUES_ONE = "SECURITY_ISSUES_ONE";
	/**
	 * 安全问题二：你的小学学校名称叫什么？
	 */
	public static final String SECURITY_ISSUES_TWO = "SECURITY_ISSUES_TWO";
	/**
	 * 用户状态
	 */
	public static final String USER_STATUS = "ACM_USER_STATUS";
	/**
	 * 正常
	 */
	public static final String USER_STATUS_NORMAL = "ACM_USER_STATUS_NORMAL";
	/**
	 * 冻结
	 */
	public static final String USER_STATUS_FROZEN = "ACM_USER_STATUS_FROZEN";
	/**
	 * 注销
	 */
	public static final String USER_STATUS_CANCEL = "ACM_USER_STATUS_CANCEL";
	/**
	 * 密码类型
	 */
	public static final String PWD_TYPE = "ACM_PWD_TYPE";
	/**
	 * 支付密码
	 */
	public static final String PWD_TYPE_PAY = "ACM_PWD_TYPE_PAY";
	/**
	 * 登录密码
	 */
	public static final String PWD_TYPE_LOGIN = "ACM_PWD_TYPE_LOGIN";
	
	/**
	 * 交易密码
	 */
	public static final String PWD_TYPE_TRADE="ACM_PWD_TYPE_TRADE";
	
	/**
	 * 冲正标志
	 */
	public static final String REVERSE_FLAG = "ACM_REVERSE_FLAG";
	/**
	 * 已冲正
	 */
	public static final String REVERSE_FLAG_YES = "ACM_REVERSE_FLAG_YES";
	/**
	 * 未冲正   
	 */
	public static final String REVERSE_FLAG_NO = "ACM_REVERSE_FLAG_NO";
	
	/**
	 * 账户操作状态
	 */
	public static final String  OPERATE_STATUS = "ACM_OPERATE_STATUS";
	/**
	 * 正常    
	 */
	public static final String OPERATE_STATUS_NORMAL = "ACM_OPERATE_STATUS_NORMAL";
	/**
	 * 已处理
	 */
	public static final String OPERATE_STATUS_DONE = "ACM_OPERATE_STATUS_DONE";
	/**
	 * 未处理
	 */
	public static final String OPERATE_STATUS_UNDONE = "ACM_OPERATE_STATUS_UNDONE";
	/**
	 * 商户签约状态
	 */
	public static final String BUSINESS_STATUS = "ACM_BUSINESS_STATUS";
	/**
	 * 已签约
	 */
	public static final String BUSINESS_STATUS_SIGN = "ACM_BUSINESS_STATUS_SIGN";
	/**
	 * 已解约
	*/
	public static final String BUSINESS_STATUS_UNSIGN = "ACM_BUSINESS_STATUS_UNSIGN";
	/**
	 * 未签约
	 */
	public static final String BUSINESS_STATUS_NONE = "ACM_BUSINESS_STATUS_NONE";
	/**
	 * 平台账户
	 */
	public static final String ACCOUNT_PLATFORM = "ACM_ACCOUNT_PLATFORM";
	/**
	 * 平台账户-手续费账户
	 */
	public static final String ACCOUNT_PLATFORM_FEE = "ACM_ACCOUNT_PLATFORM_FEE";
	/**
	 * 平台账户-自有资金账户
	 */
	public static final String ACCOUNT_PLATFORM_OWN = "ACM_ACCOUNT_PLATFORM_OWN";
	/**
	 * 银行卡用途
	 */
	public static final String BANK_USETYPE = "ACM_BANK_USETYPE";
	/**
	 * 快捷支付
	 */
	public static final String BANK_USETYPE_PAY = "ACM_BANK_USETYPE_PAY";
	/**
	 * 提现
	 */
	public static final String BANK_USETYPE_WITHDRAW = "ACM_BANK_USETYPE_WITHDRAW";
	/**
	 * 打款失败
	 */
	public static final String AUTH_STATUS_WAIT_FAILURE = "ACM_AUTH_STATUS_WAIT_FAILURE";
	/**
	 * 个人化数据状态
	 */
	public static final String PERSONAL_DATA_STATUS = "ACM_PERSONAL_DATA_STATUS";
	/**
	 * 未获取个人化数据
	 */
	public static final String PERSONAL_DATA_STATUS_UNGET = "ACM_PERSONAL_DATA_STATUS_UNGET";
	/**
	 * 已获取个人化数据
	 */
	public static final String PERSONAL_DATA_STATUS_GOT = "ACM_PERSONAL_DATA_STATUS_GOT";
	/**
	 * 关联认证
	 */
	public static final String AUTH_TYPE_RELATION = "ACM_AUTH_TYPE_RELATION";
	
	/**
	 * 发行者标识码
	 */
	public static final String CARD_IIN = "ACM_CARD_IIN";
	
	/**
	 * 卡号公司代码
	 */
	public static final String CARD_COMPANY_CODE = "ACM_CARD_COMPANY_CODE";
}
