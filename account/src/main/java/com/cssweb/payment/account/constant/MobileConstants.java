package com.cssweb.payment.account.constant;

/**
 * 手机接口常量
 * 
 * @author liLei
 * @version 1.0(2014年7月23日 下午7:53:09)
 * @since 1.0
 */
public abstract class MobileConstants {
	/**
	 * senderId
	 */
	public static final String SENDERID = "3" ; 
	
	/**
	 * TSM action
	 */
	public static final String TSM_ACTION_RECHARGE = "RECHARGE";
	public static final String TSM_RETURN_SUCCESS = "EXECUTED_SUCCESS";
	public static final String PATH_METRO_CARDSEQ = "PATH_METRO_CARDSEQ_VALUE";
	/**
	 * 创建移动卡的流水号 地铁扩展 DT004标签使用
	 */
    /**
    * 执行成功
    */
    public static final Integer RESPONSE_CODE_SUCCESS = 0;
    /**
     * 执行中
     */
    public static final Integer RESPONSE_PROCESS_RUNNING = 1;
    /**
     * 执行失败
     */
    public static final Integer RESPONSE_CODE_EXCEPTION = 2;
    /**
     * 无效的访问请求
     */
    public static final Integer RESPONSE_INVALID_REQUEST = 100;
    /**
     * 无访问权限
     */
    public static final Integer RESPONSE_NOT_PERMISSION = 101;
    /**
     * 请求参数无效
     */
    public static final Integer RESPONSE_PARAM_INVALID = 102;

    /**
     * 服务器处理错误
     */
    public static final Integer SERVER_PROCESS_ERROR = 106;
    /**
     * 操作数据库错误
     */
    public static final Integer RESPONSE_DB_ERROR = 65001001;
    /**
     * 参数类型错误 意为参数格式错错
     */
   public static final Integer RESPONSE_PARAM_TYPE_ERR = 65001002;
    /**
     * 用户编号超过255个字符
     */
    public static final Integer RESPONSE_TOO_LONG_CHARACTER = 65002001;
    /**
     * 账户密码为空
     */
    public static final Integer RESPONSE_PASSWORD_NULL = 65002002;
    /**
     * 账户已存在
     */
    public static final Integer ACCOUNT_ALREADY_EXIST = 65002003;
    /**
     * 账户号为空  用65002017代替
     */
    //public static final Integer RESPONSE_ACCOUNT_NULL = 65002004;
    /**
     * 银行卡为空
     */
    public static final Integer RESPONSE_BANK_CARD_NULL = 65002005;
    /**
     * 短信验证码错误
     */
    public static final Integer RESPONSE_SM_VERIRY_ERROR = 65002006;
    /**
     * 卡类型不正确
     */
    public static final Integer RESPONSE_CARD_TYPE_ERROR = 65002007;
    /**
     * 持卡人姓名错误
     */
    public static final Integer RESPONSE_HOLDER_NAME_ERROR = 65002008;
    /**
     * 银行卡号错误
     */
    public static final Integer RESPONSE_CARD_NUM_ERROR = 65002009;
    /**
     * 银行名称错误
     */
    public static final Integer RESPONSE_BANK_NAME_ERROR = 65002010;
    /**
     * 银行卡已绑定
     */
    public static final Integer RESPONSE_BANK_CARD_ALREADY_BINDED = 65002011;
    /**
     * 银卡号已失交效
     */
    public static final Integer RESPONSE_BANK_CARD_NUM_INVALID = 65002012;
    /**
     * 身份证件类型错误
     */
    public static final Integer RESPONSE_ID_TYPE_ERROR = 65002013;
    /**
     * 身份证件号码错误
     */
    public static final Integer RESPONSE_ID_NUM_ERROR = 65002014;
    /**
     * 银行卡cvcCode验证错误
     */
    public static final Integer RESPONSE_CVCCODE_ERROR = 65002015;
    /**
     * 用户未绑定该银行卡
     */
    public static final Integer RESPONSE_CARD_NOT_BINDED = 65002016;
    /**
     * 账户不存在
     */
    public static final Integer RESPONSE_ACCOUNT_NOT_EXIST = 65002017;
    /**
     * 查询记录开始日期为空
     */
    public static final Integer RESPONSE_START_DATE_NULL = 65002018;
    /**
     * 查询记录结束日期为空
     */
    public static final Integer RESPONSE_END_DATE_NULL = 65002019;
    /**
     * 查询记录结束日期小于开始日期
     */
    public static final Integer RESPONSE_END_BEFORE_START_ERROR = 65002020;
    /**
     * eMail地址格式不正确
     */
    public static final Integer RESPONSE_EMAIL_FORMAT_ERROR = 65002021;
    /**
     * 设置新密码为空
     */
    public static final Integer RESPONSE_NEW_PASSWORD_NULL = 65002022;
    /**
     * 账户密码错误
     */
    public static final Integer RESPONSE_PASSWORD_ERROR = 65002023;
    /**
     * 转账金额小于等于0
     */
    public static final Integer RESPONSE_AMOUNT_ZEOR_ERROR = 65002024;
    /**
     * 支付方式为空
     */
    public static final Integer RESPONSE_PAYMENT_METHOD_NULL = 65002025;
    /**
     * 是否保存该银行卡为空
     */
    public static final Integer RESPONSE_IS_KEEP_BANKCARD_EMPTY = 65002026;
    /**
     * 移动支付卡号为空
     */
    public static final Integer RESPONSE_MOBILEPAY_NUM_NULL = 65002027;
    /**
     * 移动支付卡交易类型为空
     */
    public static final Integer RESPONSE_MOBILEPAY_TRANTYPE_NULL = 65002028;
    /**
     * 是否自动充值参数为空
     */
    public static final Integer RESPONSE_AUTO_TOPUP_PARAM_NULL = 65002029;
    /**
     * 客户的SE信息为空
     */
    public static final Integer RESPONSE_SE_INFO_NULL = 65002030;
    /**
     * 服务信息为空
     */
    public static final Integer RESPONSE_SERVICE_INFO_NULL = 65002031;
    /**
     * 账户余额不足
     */
    public static final Integer RESPONSE_NOT_SUFFICIENT_FUND  = 65002032;
    /**
     * 银行卡信息为空
     */
    public static final Integer RESPONSE_BANKCARD_INFO_NULL = 65002033;
    /**
     * 用户不存在
     */
    public static final Integer RESPONSE_USER_NOT_EXIST = 65002034;
    /**
     * 手机已注册
     */
    public static final Integer MOBILE_ALREADY_REGISTERED = 65002035;
    /**
     * 没有找到符合条件记录  add20140821
     */
    public static final Integer RESPONSE_NOT_FOUND_RECORD = 65002036;    
    /**
     * 获取个人化数据失败add 20140903
     */
    public static final Integer RESPONSE_FAILED_GET_PERSONAL_DATA= 65002037;
    /**
     * SPTSM返回失败 add20140904
     */
    public static final Integer RESPONSE_SPTSM_FAILED= 65002038;

}
