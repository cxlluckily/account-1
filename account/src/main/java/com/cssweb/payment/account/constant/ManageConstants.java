package com.cssweb.payment.account.constant;
/**
 * 运营接口常量
 * 
 * @author zhaoxingwen
 * @version 1.0 (2014年7月23日 下午1:26:09)
 * @since 1.0
 */
public abstract class ManageConstants {
	/**	
	 * 参数不合法
	 */
	public static final Integer PARAMETER_ILLEGAL = 1501;

	/**
	 * 商户已签约
	 */
	public static final Integer MERCHANT_STATUS_ERR = 1502;
	
	/**
	 * 商户信息不存在
	 */
	public static final Integer MERCHANT_NOT_EXIST= 1503;
	
	/**
	 * 查询结果为空
	 */
	public static final Integer MERCHANT_SELECT_NULL= 1504;
	
	/**
	 * 平台id不存在
	 */
	public static final Integer PAYMENT_ID_NOT_EXIST= 1505;
	
	/**
	 * 商户未签约
	 */
	public static final Integer MERCHANT_NOT_SIGN = 1506;
	
	/**
	 * 商户已解约
	 */
	public static final Integer MERCHANT_STATUS_UNSIGN = 1507;
	
	/**
	 * 商户签约过期
	 */
	public static final Integer MERCHANT_STATUS_EXPIRE = 1508;
	
	/**
	 * 商户接口英文名称不能为空
	 */
	public static final Integer MERCHANT_INTER_EN_NULL = 1509;
	
	/**
	 * 重复请求
	 */
	public static final Integer REPEATED_REQUEST = 1510;
}
