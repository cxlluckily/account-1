package com.cssweb.payment.account.constant;

import com.cssweb.payment.account.pojo.AccountInfo;
import com.cssweb.payment.account.pojo.AccountOperInfo;
import com.cssweb.payment.account.pojo.UserLoginInfo;

/**
 * 公共接口常量
 * 
 * @author duxiaohua
 * @version 1.0 (2014年7月20日 下午1:26:09)
 * @since 1.0
 */
public abstract class GlobalConstants {
	
	/**
	 * 账户不存在
	 */
	public static final Integer ACCOUNT_NOT_EXIST = 1300;
	
	/**
	 * 参数为null
	 */
	public static final Integer ARGUMENTS_IS_NULL = 1301;
	/**
	 * 用户不存在
	 */
	public static final Integer USER_NOT_EXIST= 1302;
	/**
	 * 费率不存在
	 */
	public static final Integer RATE_NOT_EXIST= 1303;
	/**
	 * 支付密码不正确
	 */
	public static final Integer WRONG_PAY_PWD= 1304;
	/**
	 * id不存在
	 */
	public static final Integer ID_NOT_EXIST = 1305;
	/**
	 * 记录不存在
	 */
	public static final Integer RECORD_NOT_EXIST = 1306;	
	/**
	 * 参数格式不正确
	 */
	public static final Integer ARGUMENTS_FORMAT_INCORRECT = 1307;
	/**
	 * 用户类型不存在
	 */
	public static final Integer USERTYPE_NOT_EXIST = 1308;
	/**
	 * 参数不正确
	 */
	public static final Integer ARGUMENT_INCORRECT = 1309;
	
	
	/**
	 * 交易密码不正确
	 */
	public static final Integer WRONG_TRADE_PWD= 1310;
	
	/**
	 * 交易密码未设置
	 */
	public static final Integer NOTSET_TRADE_PWD= 1311;
	
	/**
	 * 生成user_id路径
	 */
	public static final String PATH_USERID = UserLoginInfo.class.getName();
	/**
	 * 生成account_id路径
	 */
	public static final String PATH_ACCOUNTID = AccountInfo.class.getName();

	/**
	 * 生成account_sn路径
	 */
	public static final String PATH_ACCOUNTSN = AccountOperInfo.class.getName();
	
	
}
