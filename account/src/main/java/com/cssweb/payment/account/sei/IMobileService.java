/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */
package com.cssweb.payment.account.sei;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cssweb.payment.account.pojo.AccountInfo;
import com.cssweb.payment.account.pojo.AccountMoneyChgDetail;
import com.cssweb.payment.account.pojo.BankInfo;
import com.cssweb.payment.account.pojo.PersonData;
import com.cssweb.payment.account.pojo.PersonalUser;
import com.cssweb.payment.account.pojo.UserMobileAccountInfo;
import com.cssweb.payment.account.pojo.UserTsmMsgInfo;
import com.cssweb.payment.account.vo.AppBindBankPara;
import com.cssweb.payment.account.vo.AppGetUserBindingBankListPara;
import com.cssweb.payment.account.vo.AppRegisterPara;
import com.cssweb.payment.account.vo.CreateMobileAccountResponse;
import com.cssweb.payment.account.vo.GetMobileAccountResponse;
import com.cssweb.payment.account.vo.TopupToMobileAccountByDefaultResponse;
import com.cssweb.payment.common.response.AttachmentResponse;

/**
 * 手机APP相关服务接口
 * 
 * @author DuXiaohua
 * @version 1.0 (2014年7月22日 下午11:55:04)
 * @since 1.0
 */
public interface IMobileService {

	/**
	 * 3.66 用户注册
	 * 
	 * @author DuXiaohua
	 * @date 2014年7月23日 上午12:09:16
	 * @param AppRegisterPara 注册对象
	 * @return AttachmentResponse<Long>, 附加对象为用户的id
	 * @since 1.0
	 */
	public AttachmentResponse<Long> registe(AppRegisterPara registerPara);
	

	/**
	 * 3.67 获取用户绑定的银行卡列表
	 * 
	 * @author DuXiaohua
	 * @date 2014年7月23日 上午12:22:43
	 * @param userId 用户的id
	 * @return AttachmentResponse<List<UserBandBankInfo>> 附加对象为用户绑定的银行卡列表
	 * @since 1.0
	 */
	//public AttachmentResponse<List<UserBandBankInfo>> getUserBindingBankList(Long userId);
	public AttachmentResponse<List<AppGetUserBindingBankListPara>> getUserBindingBankList(Long userId);
	/**
	 * 3.68 获取合作银行列表
	 * 
	 * @author DuXiaohua
	 * @date 2014年7月23日 上午12:37:17
	 * @return AttachmentResponse<List<BankInfo>> 附加对象为合作银行列表
	 * @since 1.0
	 */
	public AttachmentResponse<List<BankInfo>> getCooperativeBankList();

	/**
	 * 3.69 绑定银行卡
	 * 
	 * @author DuXiaohua
	 * @date 2014年7月23日 上午12:59:32
	 * @param AppBindBankPara 绑定信息对象
	 * @return AttachmentResponse 无附加对象
	 * @since 1.0
	 */
	public AttachmentResponse bindBank(AppBindBankPara  AppBindBankPara);
	
	/**
	 * 3.70 解绑银行卡
	 * 
	 * @author DuXiaohua
	 * @date 2014年7月23日 上午1:10:48
	 * @param userId 用户id
	 * @param bankAccountNo 银行卡号
	 * @return AttachmentResponse 无附加对象
	 * @since 1.0
	 */
	public AttachmentResponse unbindBank(Long userId, String bankAccountNo);
	
	/**
	 * 3.71 获取账户信息
	 * 
	 * @author DuXiaohua
	 * @date 2014年7月23日 上午1:13:00
	 * @param userId 用户id
	 * @return AttachmentResponse<AccountInfo> 附加对象为账户信息对象
	 * @since 1.0
	 */
	public AttachmentResponse<AccountInfo> getAccountInfo(Long userId);
	
	/**
	 * 3.72 获取账户历史交易信息 - 闪通宝
	 * 
	 * @author DuXiaohua
	 * @date 2014年7月23日 上午1:18:22
	 * @param userId 用户id
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return AttachmentResponse<List<AccountMoneyChgDetail>> 附加对象为账户交易信息列表
	 * @since 1.0
	 */
	public AttachmentResponse<List<AccountMoneyChgDetail>> getAccountTradingHistory(Long userId, Date startDate, Date endDate);
	
	/**
	 * 3.74 修改密码
	 * 
	 * @author DuXiaohua
	 * @date 2014年7月23日 上午1:24:09
	 * @param userId 用户ID
	 * @param passwdType 密码类型。登陆密码或交易密码
	 * @param passwd 密码
	 * @return AttachmentResponse 无附加对象
	 * @since 1.0
	 * -----------------------------------------------------------------------------------------------------------------
	 * 修改记录
	 * 增加newPasswd  zhaoxingwen 201407241853
	 */
	public AttachmentResponse changePassword(Long userId, Integer passwdType, String passwd,String newPasswd);
	
	/**
	 * 3.76 用户登陆
	 * 
	 * @author DuXiaohua
	 * @date 2014年7月23日 上午1:33:04
	 * @param loginName 登陆名，手机或者邮箱
	 * @param loginPasswd 登陆密码
	 * @return AttachmentResponse 附加对象为用户id
	 * @since 1.0
	 */
	public AttachmentResponse<Long> login(String loginName, String loginPasswd);
	
	/**
	 * 3.77 创建移动支付帐户 - 闪客蜂现金账户
	 * 
	 * @author DuXiaohua
	 * @date 2014年7月23日 上午1:38:55
	 * @param userId 用户id
	 * @param secureElement sim卡唯一标识
	 * @param service 服务版本号
	 * @return AttachmentResponse<Long> 移动支付帐户 - 闪客蜂现金账户id
	 * @since 1.0
	 * ----------------------------------------------------------------------------------------------------------
	 * 修改：
	 * 增加入参zxw20140815
	 */
	//public AttachmentResponse<Long> createMobileAccount(Long userId, String secureElement, String service);
	public AttachmentResponse<CreateMobileAccountResponse>   createMobileAccount(Long userId,String seIdName, String iccid,String cardUniqueData,
			String seIdGenericType,String seIdGenericId, String serviceId,String serviceVer);
	
	/**
	 * 3.78 获取移动支付帐户 - 闪客蜂现金账户
	 * 
	 * @author DuXiaohua
	 * @date 2014年7月23日 上午1:38:55
	 * @param secureElement sim卡唯一标识
	 * @param service 服务版本号
	 * @return AttachmentResponse<Long> 移动支付帐户 - 闪客蜂现金账户id
	 * @since 1.0
	 * ------------------------------------------------------------------------------------------------------------------
	 * 修改记录
	 * 接口名改为 getMobileAccount()   zhaoxingwen 201407241853
	 * 增加入参  zxw20140815
	 */
	public AttachmentResponse<GetMobileAccountResponse> getMobileAccount(String iccid, String serviceId,String serviceVersion);
	
	/**
	 * 3.79 圈存-默认闪通宝到闪客蜂现金账户
	 * 
	 * @author DuXiaohua
	 * @date 2014年7月23日 上午2:14:10
	 * @param mobileAccountId 闪客蜂现金账户id
	 * @param topupAmount 圈存金额
	 * @return AttachmentResponse 无附加对象
	 * @since 1.0
	 * --------------
	 * 修改记录
	 * 增加两个入参 zxw20140813
	 */
	//public AttachmentResponse topupToMobileAccountByDefault(Long mobileAccountId, BigDecimal topupAmount,String payPasswd);
	public AttachmentResponse<TopupToMobileAccountByDefaultResponse> topupToMobileAccountByDefault(Long prepaidAccountId, BigDecimal beforeAmount,BigDecimal topupAmount,String payPasswd,String messageId, String senderId);
	
	
	/**
	 * 验证支付密码
	 * 
	 * @author DuXiaohua
	 * @date 2014年7月23日 上午2:19:36
	 * @param userId 用户id
	 * @param payPasswd 支付密码
	 * @return AttachmentResponse<Boolean> 附加对象为支付密码是否正确
	 * @since 1.0
	 * @see
	 */
	public AttachmentResponse<Boolean> verifyPayPasswd(Long userId, String payPasswd);
	
	/**
	 * 3.80 获取移动支付账户交易流水 - 闪客蜂现金账户
	 * @author DuXiaohua
	 * @date 2014年7月23日 上午2:26:32
	 * @param mobileAccountId 闪客蜂现金账户id
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @since 1.0
	 * --------------
	 * 增加入参 zxw20140822
	 */
	public AttachmentResponse<List<AccountMoneyChgDetail>> getMobileAccountTradingHistory(Long mobileAccountId, String tranType, Date startDate, Date endDate);
	
	/**
	 * 3.81 服务状态变更通知
	 * 
	 * @author DuXiaohua
	 * @date 2014年7月23日 上午2:30:29
	 * @param secureElement sim卡唯一标识
	 * @param newService 新服务版本号
	 * @param newStatus 新状态
	 * @return AttachmentResponse 无附加对象
	 * @since 1.0
	 */
	public AttachmentResponse serviceStateChangeNotification(String secureElement, String newService, String newStatus);
	
	/**
	 * 3.82 检查登陆用户名是否存在
	 * 
	 * @author DuXiaohua
	 * @date 2014年7月23日 上午2:39:07
	 * @param loginName 手机号码或者邮箱 
	 * @return AttachmentResponse<Boolean> 附加对象为是否存在
	 * @since 1.0
	 */
	public AttachmentResponse<Boolean> isLoginNameExist(String loginName);
	
	/**
	 * 3.83 获取用户信息
	 * 
	 * @author DuXiaohua
	 * @date 2014年7月23日 上午2:48:25
	 * @param userId 用户id
	 * @return AttachmentResponse<PersonalUser> 附加对象为用户信息
	 * @since 1.0
	 */
	public AttachmentResponse<PersonalUser> getUserInfo(Long userId);
	
	/**
	 * 3.84 更新用户信息
	 * @author DuXiaohua
	 * @date 2014年7月23日 上午2:51:29
	 * @param user 用户信息对象
	 * @return AttachmentResponse 无附加对象
	 * @since 1.0
	 */
	public AttachmentResponse modifyUserInfo(PersonalUser user);
	
	/**
	 * 3.85 服务更新通知反馈
	 * @author DuXiaohua
	 * @date 2014年7月23日 上午2:55:10
	 * @param msgInfo 与TSM交互信息
	 * @since 1.0
	 */
	public AttachmentResponse<Long> modifyTsmMsgInfo(UserTsmMsgInfo msgInfo);
	
	/**
	 * 是否需要密码
	 * @author zhaoxingwen
	 * @date 2014年8月12日 下午2:55:10
	 * @param 账号
	 * @since 1.0
	 */
	public AttachmentResponse<Boolean> isBankCardPasswordRequired(String accountId);
	

	/**
	 * 收到callBack后续处理
	 * @author zhaoxingwen
	 * @date 2014年8月14日 下午2:55:10
	 * @param 
	 * @since 1.0
	 */
	public AttachmentResponse<String> tsmRequestDataUpdateCallBack(UserTsmMsgInfo userTsmMsgInfo);
	
	/**
	 * 按messageId从ACM_USER_TSM_MSG_INFO表查询信息
	 * @author zhaoxingwen
	 * @date 2014年8月14日 下午2:55:10
	 * @param 
	 * @since 1.0
	 */
	public AttachmentResponse<UserTsmMsgInfo> getTsmMessage(String messageId);
	
	
	/**
	 * 修改手机-移动支付账户对应关系表 
	 * @author zhaoxingwen
	 * @date 2014年9月3日 下午2:55:10
	 * @param 
	 * @since 1.0
	 */
	public AttachmentResponse updateUserMobileAccountInfo(UserMobileAccountInfo userMobileAccountInfo);
	

//del by zxw20140826
//	/**
//	 * Title: getMobileAccountByAccountId
//	 * Description:为了IPayment中topupMobilePaymentCard接口新增
//	 * @date : 2014年8月15日
//	 * @author Lilei
//	 * @param accountId
//	 * @return
//	 */
//	public AttachmentResponse<UserMobileAccountInfo> getMobileAccountByAccountId(String accountId);
	/**
	 * Title: getInputPersonData
	 * Description:
	 * @date : 2014年9月12日
	 * @author Lilei
	 * @return
	 */
	public List<PersonData>  getInputPersonData(Long seq,String accountId,String name,String IdType,String IdNum);

	
}
