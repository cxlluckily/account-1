/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.constant;

/**
 * 门户接口常量
 * 
 * @author zhanwx
 * @version 1.0 (2014年7月23日 上午10:55:01)
 * @since 1.0
 */
public class PortalConstants {
	
	/**
	 * 用户  名不存在
	 */
	public static final Integer USER_NAME_NOT_EXIST = 1400;
	
	/**
	 * portal_用户不存在
	 */
	public static final Integer PORTAL_USER_NOT_EXIST = 1401;
	
	/**
	 * portal_参数为null
	 */
	public static final Integer PORTAL_ARGUMENTS_IS_NULL = 1402;
	
	/**
	 * 修改密码时提供密码类型不正确
	 */
	public static final Integer PORTAL_PWD_TYPE_INCORRECT = 1403;
	
	/**
	 * 用户绑定银行卡信息已存在
	 */
	public static final Integer PORTAL_BAND_IS_EXIST = 1404;
	/**
	 * 更新或插入用户安全问题失败
	 */
	public static final Integer PORTAL_FAIL_UPDATE_OR_INSERT_USERQUESTION = 1405;
	/**
	 * 用户不存在或未设置该安全问题
	 */
	public static final Integer PORTAL_NOUSER_OR_NOQUESTION = 1406;
	/**
	 * 安全问题答案输入正确
	 */
	public static final Integer PORTAL_QUESTIOM_CORRECT = 1407;
	/**
	 * 安全问题答案输入不正确
	 */
	public static final Integer PORTAL_QUESTIOM_INCORRECT = 1408;
	/**
	 * 用户不存在或未绑定银行
	 */
	public static final Integer PORTAL_USER_NOT_BAND = 1409;
	/**
	 * 用户不存在或未设置功能
	 */
	public static final Integer PORTAL_NOUSER_OR_NOFUNCTION = 1410;
	/**
	 * 未设置认证状态
	 */
	public static final Integer PORTAL_NOTSET_AUTHSTATUS = 1411;
	/**
	 * 该状态下无法撤销实名认证请求
	 */
	public static final Integer PORTAL_NOT_CANCEL_AUTHSTATUS = 1412;
	/**
	 * 无提现银行卡
	 */
	public static final Integer PORTAL_NO_CARD_FOR_WITHDRAW = 1413;
	
	/**
	 * 该银行卡实名认证次数超过限制
	 */
	public static final Integer BANKCARD_IS_LIMITED = 1414;
	
	/**
	 * 该手机号已被注册
	 */
	public static final Integer PHONE_NUM_EXIST = 1450;
	/**
	 * 该邮箱已被注册
	 */
	public static final Integer  EMAIL_EXIST= 1451;
	
	/**
	 * 用户ID生成失败
	 */
	public static final Integer  USER_ID_GEN_FAIL= 1452;
	
	/**
	 * 账户ID生成失败
	 */
	public static final Integer  ACCOUNT_ID_GEN_FAIL= 1453;
	
	/**
	 * ID生成失败
	 */
	public static final Integer  ID_GEN_FAIL= 1454;
	
	/**
	 * 密码错误
	 */
	public static final Integer  WRONG_PASS_WORD= 1455;
	
	/**
	 * 认证记录不存在
	 */
	public static final Integer  AUTH_NOT_EXIST= 1456;
	
	/**
	 * 金额输入错误
	 */
	public static final Integer  AUTH_FAILD= 1457;
	
	/**
	 * 认证错误次数超出限制
	 */
	public static final Integer  AUTH_TIMES_LIMIT= 1458;
	
	/**
	 * 撤消认证失败
	 */
	public static final Integer  AUTH_CANCEL_FAIL = 1459;
	/**
	 * 身份证号码不匹配
	 */
	public static final Integer  IDCARD_MISMATCHING = 1460;
	
	/**
	 * 登陆密码不能与支付密码相同
	 */
	public static final Integer  LOGINPWD_PAYPWD_MUST_DIFFERENT = 1461;
	
	/**
	 * 未绑定手机
	 */
	public static final Integer  NOT_BINDING_MOBILE_PHONE = 1462;
	
	/**
	 * 解绑手机需要先绑定邮箱，并设为登录名
	 */
	public static final Integer  UNBINDING_MOBILE_MUST_BINDING_EMAIL = 1463;
	
	/**
	 * 未绑定邮箱
	 */
	public static final Integer  NOT_BINDING_EMAIL = 1464;
	
	/**
	 * 解绑邮箱需要先绑定手机，并设为登录名
	 */
	public static final Integer  UNBINDING_EMAIL_MUST_BINDING_MOBILE = 1465;
	/**
	 * 取消手机需要将邮箱设为登录名
	 */
	public static final Integer  CANCEL_MOBILE_MUST_SET_EMAIL = 1466;
	/**
	 * 取消邮箱需要将手机设为登录名
	 */
	public static final Integer  CANCEL_EMAIL_MUST_SET_MOBILE = 1467;
	/**
	 * 手机或邮箱已存在
	 */
	public static final Integer  PHONE_NUM_OR_EMAIL_EXIST = 1468;
	
	/**
	 * 余额不为零，无法销户
	 */
	public static final Integer ACCOUNT_BALANCE_NOT_ZERO = 1469;
	
	/**
	 * 冻结或注销
	 */
	public static final Integer USER_STATUS_ABNORMAL = 1470;
	/**
	 * 用户当前状态为，无效
	 */
	public static final Integer UPDATE_INVALID = 1471;
	/**
	 * 用户当前认证状态非等待审核
	 */
	public static final Integer NOT_WAITAUDIT = 1472;
	/**
	 * 手机已被绑定
	 */
	public static final Integer PHONE_NUM_BINDING = 1473;
	/**
	 * 邮箱已被绑定
	 */
	public static final Integer EMAIL_BINDING = 1474;
	/**
	 * 开通状态一致操作失败
	 */
	public static final Integer OPERATE_FAILED = 1475;
	/**
	 * 新密码与原密码不能相同
	 */
	public static final Integer NEWPWD_OLDPWD_MUST_DIFFERENT = 1476;
	/**
	 * 被关联账户用户名或密码错误
	 */
	public static final Integer RELATION_USERNAME_OR_PWD_WRONG = 1477;
	/**
	 * 用户已被注销
	 */
	public static final Integer USER_CANCEL = 1478;
	/**
	 * 关联账户密码错误
	 */
	public static final Integer MAIN_ACCOUNT_PWD_WRONG = 1479;
	/**
	 * 身份证号码错误
	 */
	public static final Integer AUTH_CERT_ID_WRONG = 1480;
	/**
	 * 营业执照注册号错误
	 */
	public static final Integer BUSINESS_LICENCE_WRONG = 1481;
	/**
	 * 该账户名已经实名认证
	 */
	public static final Integer RELATION_ACCOUNT_AUTH_ALREADY = 1482;
	/**
	 * 关联认证失败，关联账户未通过实名认证
	 */
	public static final Integer MAIN_ACCOUNT_NOT_AUTH = 1483;
	/**
	 * 该用户未进行实名认证申请
	 */
	public static final Integer ACCOUNT_NOT_AUTH = 1484;
	
	/**
	 * 企业用户不允许解绑邮箱
	 */
	public static final Integer ENTERPRISE_NOT_UNBIND = 1485;
	
	/**
	 * 企业用户不允许把手机设为登录名
	 */
	public static final Integer ENTERPRISE_PHONE_NOT_LOGINNAME = 1486;
	
	/**
	 * 企业用户不允许取消登录名
	 */
	public static final Integer ENTERPRISE_NOT_CANCEL_LOGINNAME = 1487;
	
	/**
	 * 手机未设为登录名
	 */
	public static final Integer PHONE_ISNT_LOGINNAME = 1488;

	/**
	 * 邮箱未设为登录名
	 */
	public static final Integer EMAIL_ISNT_LOGINNAME = 1489;
	/**
	 * 手机已设为登录名
	 */
	public static final Integer PHONE_IS_LOGINNAME = 1490;
	
	/**
	 * 邮箱已设为登录名
	 */
	public static final Integer EMAIL_IS_LOGINNAME = 1491;
	
	/**
	 * 营业执照注册号不匹配
	 */
	public static final Integer LICENCE_ISNT_MATCH = 1492;
	
	/**
	 * 个人账号最多能关联认证5个账号
	 */
	public static final Integer PERSONAL_RELA_AUTH_LIMIT = 1493;
	
	/**
	 * 企业账号最多能关联认证10个账号
	 */
	public static final Integer ENTERPRISE_RELA_AUTH_LIMIT = 1494;
	
	/**
	 * 不能通过该账户进行实名认证关联
	 */
	public static final Integer FORBIDDEN_ACCOUNT_RELA = 1495;
	
	/**
	 * 支付密码错误次数超出上限
	 */
	public static final Integer PAY_PWD_ERRORTIME_BEYOND = 1496;
	
	/**
	 * 交易密码不能和登录密码相同
	 */
	public static final Integer  LOGINPWD_TRADEPWD_MUST_DIFFERENT = 1497;
	
	
	/**
	 * 交易密码错误次数超出上限
	 */
	public static final Integer TRADE_PWD_ERRORTIME_BEYOND = 1496;
}