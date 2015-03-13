
package com.cssweb.payment.account.sei;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cssweb.payment.account.pojo.EnterpriseUser;
import com.cssweb.payment.account.pojo.FinancialSummaryDay;
import com.cssweb.payment.account.pojo.FinancialSummaryMonth;
import com.cssweb.payment.account.pojo.PersonalUser;
import com.cssweb.payment.account.pojo.SecurityClassInfo;
import com.cssweb.payment.account.pojo.UserBandBankInfo;
import com.cssweb.payment.account.pojo.UserBankAuInfo;
import com.cssweb.payment.account.pojo.UserQuestionInfo;
import com.cssweb.payment.account.vo.EnterpriseAuProcessQueryResponse;
import com.cssweb.payment.account.vo.FinancialSummaryDayQuery;
import com.cssweb.payment.account.vo.FinancialSummaryMonthQuery;
import com.cssweb.payment.account.vo.GetEnterpriseUserBasicInfoResponse;
import com.cssweb.payment.account.vo.GetUserRelAuInfoResponse;
import com.cssweb.payment.account.vo.LoginResponse;
import com.cssweb.payment.account.vo.PersonalAuProcessResponse;
import com.cssweb.payment.account.vo.StatusInfo;
import com.cssweb.payment.account.vo.UserBasicInfoResponse;
import com.cssweb.payment.common.response.AttachmentResponse;
import com.cssweb.payment.common.util.Page;


/**
 * 账户系统提供给门户的接口
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

public interface IPortalService {
	

	
	/**
	 * 1018	账户支付密码验证接口
	 * @param userName 邮箱或手机号
	 * @param payPasswd 支付密码
	 * @return Boolean
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-2 下午6:42:11 
	 * @since 0.1
	 */
	public AttachmentResponse<Boolean> verifyPayPwd(String userName, String payPasswd);
	

	
	/**
	 * 1019	用户名是否存在接口
	 * @param userName 用户名
	 * @return Boolean
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-2 下午6:45:05 
	 * @since 0.1
	 */
	public AttachmentResponse<Boolean> checkUserNameIsExits(String userName);
	
	/**
	 * 1020	用户（个人）注册接口
	 * @param nationality 地区
	 * @param mobile 手机号码
	 * @param name 姓名
	 * @param email 电子邮箱
	 * @param regType 注册方式
	 * @param source 用户来源
	 * @param cardType 证件类型
	 * @param cardId 证件号码
	 * @param loginPwd 登录密码
	 * @param payPwd 支付密码
	 * @param questionId 安全问题id
	 * @param answer 安全保护答案
	 * @return Long 用户id
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014年7月22日 下午3:54:06
	 * @since 0.1
	 */
	public AttachmentResponse<Long> personalRegistration(String nationality, String mobile, 
			String name, String email, String regType, String source, String cardType, String cardId, 
			String loginPwd, String payPwd, int questionId, String answer);
	
	
	/**
	 * 
	 * @author nyc
	 * @date 2014年12月19日 下午4:16:39
	 * @param nationality:国籍
	 * @param mobile：手机号码
	 * @param regType：注册方式
	 * @param source：来源
	 * @param loginPwd：登录密码
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public AttachmentResponse<Long> personalRegistration(String nationality, String mobile, 
			  String regType, String source, String loginPwd);
	
	/**
	 * 1021	企业注册接口
	 * @param userName 用户名(只能是邮箱)
	 * @param loginPwd 登录密码
	 * @param payPwd 支付密码
	 * @param questionId 安全问题id
	 * @param answer 安全问题答案
	 * @param source 用户来源
	 * @return Long 用户id
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014年7月23日 下午3:53:22
	 * @since 0.1
	 */
	public AttachmentResponse<Long> enterpriseRegistration(String userName, String loginPwd, 
			String payPwd, int questionId, String answer, String source);
		
	/**
	 * 1022 用户登陆接口
	 * @param userName 用户名
	 * @param passWord 密码
	 * @param ip ip地址
	 * @param mac mac地址(通过控件取)
	 * @param source 用户来源
	 * @return LoginResponse 登录信息对象
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014年7月23日 下午5:44:10
	 * @since 0.1
	 */
	public AttachmentResponse<LoginResponse> login(String userName,String passWord,String ip,String mac,String source);

	/**
	 * 1023	用户登出接口
	 * @param userId 用户id
	 * @return String 出错时返回消息
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014年7月24日 上午10:19:48
	 * @since 0.1
	 */
	public AttachmentResponse<String> logout(Long userId);
	
	
	/**
	 * 1024 用户(个人、企业)银行打款认证接口
	 * @param userId 用户id
	 * @param bankId 银行编号
	 * @param bankCardNo 银行卡号
	 * @param money 打款金额
	 * @param source 用户来源
	 * @return String 出错时返回消息
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014年7月24日 上午10:52:55
	 * @since 0.1
	 */
	public AttachmentResponse<String> bankAu(Long userId,String bankId,String bankCardNo,BigDecimal money,String source);
	

	/**
	 * 1025 个人用户基本信息修改接口
	 * @param user 用户信息对象
	 * @param userStatus 用户状态
	 * @return AttachmentResponse 出错时返回消息
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014年7月24日 下午2:27:28
	 * @since 0.1
	 */
	public AttachmentResponse updatePersonal(PersonalUser user,String userStatus);
	
	/**
	 * 1026	个人用户实名认证进度查询接口
	 * @param userId 用户id
	 * @return PersonalAuProcessResponse 返回信息对象
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014年7月24日 下午2:59:00
	 * @since 0.1
	 */
	public AttachmentResponse<PersonalAuProcessResponse> personalAuProcess(Long userId);
	
	/**
	 * 1027	个人用户实名认证申请时间查询接口
	 * @param userId 用户id
	 * @return Date 申请时间
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014年7月24日 下午3:26:16
	 * @since 0.1
	 */
	public AttachmentResponse<Date> getPersonalAuDate(Long userId);
	
	/**
	 * 1028	个人用户撤销实名认证接口
	 * @param userId 用户id
	 * @return String 出错时返回消息
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014年7月24日 下午3:58:51
	 * @since 0.1
	 * @see
	 */
	public AttachmentResponse<String> personalAuCancel(Long userId);
	
	/**
	 * 1029	企业实名认证（法人、代理人）接口
	 * @param user 企业用户信息对象
	 * @param bankAuInfo 打款认证信息对象
	 * @return String 出错时返回消息
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-2 下午9:54:21 
	 * @since 0.1
	 */
	public AttachmentResponse<String> enterpriselAu(EnterpriseUser user, UserBankAuInfo bankAuInfo);
	
	/**
	 * 1030	企业客户基本信息查询接口
	 * @param userId 用户id
	 * @return GetEnterpriseUserBasicInfoResponse 企业用户基本信息对象
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-2 下午9:59:04 
	 * @since 0.1
	 */
	public AttachmentResponse<GetEnterpriseUserBasicInfoResponse> getEnterpriseUserBasicInfo(Long userId);
	
	/**
	 * 1031	企业客户基本信息修改接口
	 * @param user 企业用户对象
	 * @param userStatus 用户状态
	 * @return AttachmentResponse 出错时返回信息
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-2 下午10:07:30 
	 * @since 0.1
	 */
	public AttachmentResponse updateEnterprise(EnterpriseUser user,String UserStatus);
	
	/**
	 * 1032	企业客户实名认证进度查询接口
	 * @param userId 用户id
	 * @return EnterpriseAuProcessQueryResponse 企业用户实名认证信息对象
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-2 下午10:12:11 
	 * @since 0.1
	 */
	public AttachmentResponse<EnterpriseAuProcessQueryResponse> enterpriseAuProcessQuery(Long userId);
	
	/**
	 * 1033	企业客户撤销实名认证接口
	 * @param userId 用户id
	 * @return String 出错时返回信息
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-2 下午10:13:56 
	 * @since 0.1
	 */
	public AttachmentResponse<String> enterpriselAuCancel(Long userId);
	
	/**
	 * 1034	实名认证人工审核接口
	 * @param userId 用户id
	 * @param source  来源
	 * @return String 出错时返回信息
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-2 下午10:17:58 
	 */
	public AttachmentResponse<String> enterpriselAuApply(Long userId,String source);
	
	/**
	 * 1035	个人客户基本信息查询接口
	 * @param userId 用户id
	 * @return UserBasicInfoResponse 用户信息对象
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-2 下午10:24:35 
	 * @since 0.1
	 */
	/* 
	 * 修改记录
	 * 修改返回类型 zhaoxw20140727
	 */
	 public AttachmentResponse<UserBasicInfoResponse> getUserBasicInfo(Long userId);
	
	/**
	 * 1036	添加银行卡接口
	 * @param userBandBankInfo 绑定银行信息对象
	 * @return String 出错时返回信息
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-2 下午10:48:26 
	 * @since 0.1
	 */
	public AttachmentResponse<String> addBankCard (UserBandBankInfo userBandBankInfo);
	
	
	/**
	 * 1037	修改银行卡信息接口
	 * @param userBandBankInfo 银行对象
	 * @return String 出错时返回信息
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-2 下午10:50:31 
	 * @since 0.1
	 */
	public AttachmentResponse<String> updateBankCard (UserBandBankInfo userBandBankInfo);
	
	/**
	 * 1038	删除银行卡接口
	 * @param userId 用户id
	 * @param id 表ID(序列号)
	 * @param source 来源
	 * @return String 出错时返回信息
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-2 下午10:53:09 
	 * @since 0.1
	 */
	public AttachmentResponse<String> deleteBankCard (Long userId,Long id,String source);
	
	/**
	 * 1039	设置提现银行卡接口
	 * @param userId 用户id
	 * @param id 表ID(序列号)
	 * @param source 来源
	 * @return String 出错时返回信息
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-2 下午10:56:53 
	 * @since 0.1
	 */
	public AttachmentResponse<String> setBankCardWithdrawals (Long userId,Long id,String source);
	
	/**
	 * 1040	安全问题是否匹配接口
	 * @param userId 用户id
	 * @param questionid 安全问题ID
	 * @param answer 答案
	 * @return String 匹配结果
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-3 上午10:05:11 
	 * @since 0.1
	 */
	public AttachmentResponse<String>  checkSafeQuestionIsOk(Long userId,String questionid,String answer);
	
	/**
	 * 1041	余额支付功能开通关闭接口
	 * @param userId 用户id
	 * @param flag 开通标志：0--开通；1--关闭
	 * @param source 来源
	 * @return String 出错时返回信息
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-3 上午10:08:54 
	 * @since 0.1
	 */
	public AttachmentResponse<String>  balancePayOpenOrClose(Long userId,String flag,String source);
	
	/**
	 * 1042	短信提醒功能开通关闭接口
	 * @param userId 用户id
	 * @param flag 开通标志：0--开通；1--关闭
	 * @param source 来源
	 * @return String 出错时返回信息
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-3 上午10:08:54 
	 * @since 0.1
	 */
	public AttachmentResponse<String>  smsAlertsOpenOrClose(Long userId,String flag,String source);
	
	/**
	 * 1043	设置（修改）安全保护问题接口
	 * @param userId 用户id
	 * @param question1 问题1id
	 * @param answer1 问题1答案
	 * @param question2 问题2id
	 * @param answer2 问题2答案
	 * @param question3 问题3id
	 * @param answer3 问题3答案
	 * @return String 出错时返回信息
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014年7月22日 下午4:39:39
	 * @since 0.1
	 */
	public AttachmentResponse<String> setSafeQuestion(Long userId,String question1,String answer1,
			String question2,String answer2,String question3,String answer3);
	
	/**
	 * 1044	是否有安全保护问题查询接口
	 * @param userId 用户id
	 * @return Integer 安全问题数目
	 * @author zhanwx
	 * @date 2014年7月22日 上午11:18:56
	 * @since 0.1
	 */
	public AttachmentResponse<Integer> safeQuestionIsExist(Long userId);
	
	/**
	 * 1045	用户密码修改接口
	 * @param userId 用户id
	 * @param psswd 新密码
	 * @param pwd_type 密码类型 0：登录密码，1：支付密码
	 * @param source 用户来源
	 * @return String 修改是否成功信息
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-3 上午10:29:59 
	 * @since 0.1
	 */
	public AttachmentResponse<String> updatePwd(Long userId,String psswd,String pwd_type,String source);
	
	/**
	 * 1046	预留信息修改接口
	 * @param userId 用户id
	 * @param message 预留信息
	 * @param source 来源
	 * @return String 修改是否成功信息
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-3 上午10:36:43 
	 * @since 0.1
	 */
	public AttachmentResponse<String> reserveMessageUpdate(Long userId,String message,String source);
	
	/**
	 * 1047	用户安全问题列表查询接口
	 * @param  userId 用户id
	 * @return List UserQuestionInfo列表
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-3 上午10:46:08 
	 * @since 0.1
	 */
	public AttachmentResponse<List<UserQuestionInfo>> getQuestion(Long userId);

	
	/**
	 * 1098 获取提现银行卡列表
	 * @param userId 用户Id
	 * @param bankCardType 银行卡类型
	 * @param bankUseType 银行卡用途类型
	 * @return List UserBandBankInfo列表
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014年7月22日 下午4:25:30
	 * @since 0.1
	 */
	/*--------------修改为可根据银行卡用途获取列表-------------
	 *修改者：zhanwx   
	 *时间：2014-08-19
	 */
	public AttachmentResponse<List<UserBandBankInfo>> getBankCard(Long userId,String bankCardType,String bankUseType);
	
	/**
	 * 1101 按Id查询用户绑定银行卡对象
	 * @param id 绑定银行卡表id(序列号)
	 * @return UserBandBankInfo 用户绑定银行卡对象
	 * @author DuXiaohua
	 * @date 2014年7月27日 下午1:42:19
	 * @since 1.0
	 */
	public AttachmentResponse<UserBandBankInfo> getUserBandBankInfo(Long id);
	
	/**
	 * 1102 个人实名认证申请接口
	 * @param user 用户信息对象
	 * @param bankAuInfo 银行打款认证信息对象
	 * @return AttachmentResponse 附加对象
	 * @author shilei@cssweb.com.cn
	 * @date 2014年8月1日 下午3:10:12
	 * @since 0.1
	 */
	public AttachmentResponse personalAu(PersonalUser user, UserBankAuInfo bankAuInfo);
	
	/**
	 * 1103 判断用户ID、身份证号码是否匹配接口
	 * @param userId 用户ID
	 * @param cardId 身份证号码
	 * @return Boolean 匹配结果
	 * @author shilei@cssweb.com.cn
	 * @date 2014年8月1日 下午4:06:45 
	 * @since 0.1
	 */
	public AttachmentResponse<Boolean> checkCardIdIsValid(Long userId,String cardId);
	
	/**
	 * 1110 手机解绑接口
	 * @param userId 用户id
	 * @return AttachmentResponse 
	 * @author DuXiaohua
	 * @date 2014年8月9日 下午7:26:39
	 * @since 1.0 
	 */
	public AttachmentResponse unbindingMobilePhone(Long userId);
	/**
	 * 1111 邮箱解绑接口
	 * @param userId 用户id
	 * @return AttachmentResponse 
	 * @author DuXiaohua
	 * @date 2014年8月9日 下午7:26:50
	 * @since 1.0
	 */
	public AttachmentResponse unbindingEmail(Long userId);
	/**
	 * 1112 手机、邮箱设为登录名
	 * @param userId 用户id
	 * @param type 01为手机，02为邮箱
	 * @return String 出错时返回信息
	 * @author wangpeng
	 * @date 2014年8月15日 上午10:35:43
	 * @since 0.1
	 */
	public AttachmentResponse<String> loginNameSynchronous(Long userId,String type);
	
	/**
	 * 1113 手机、邮箱取消设为登录名
	 * @param userId 用户id
	 * @param type 01为手机，02为邮箱
	 * @return String 出错时返回信息
	 * @author wangpeng
	 * @date 2014年8月15日 上午10:35:43
	 * @since 0.1
	 */
	public AttachmentResponse<String> cancalLoginName(Long userId,String type);
	
	/**
	 * 1115 用户安全等级详细信息查询接口
	 * @param userId 用户id
	 * @return SecurityClassInfo 安全等级信息对象
	 * @author zhanwx
	 * @date 2014年8月15日 上午10:35:43
	 * @since 0.1
	 */
	public AttachmentResponse<SecurityClassInfo> checkUserSecurity(Long userId);
	
	/**
	 * 1116 设置银行卡用途接口
	 * @param id 绑定银行卡表id(序列号)
	 * @param type 用途类型
	 * @param state 用途开通状态
	 * @return Long 绑定银行卡表id(序列号)
	 * @author zhanwx
	 * @date 2014年8月18日 上午9:21:04
	 * @since 0.1
	 */
	public AttachmentResponse<Long> setBankCardType(Long id,String type,String state);
	
	/**
	 * 1117 查询指定账户指定时间范围内指定类型交易总金额
	 * @param accountId 账号id
	 * @param txnType 交易类型
	 * @param beginDate 开始日期
	 * @param lastDate 结束日期
	 * @return BigDecimal 总金额
	 * @author zhanwx
	 * @date 2014年8月18日 上午9:40:55
	 * @since 0.1
	 */
	public AttachmentResponse<BigDecimal> selectTotalBalance(Long accountId,String txnType ,Date beginDate,Date lastDate);
	
	/**
	 * 1118 个人实名认证账户关联接口
	 * @param relationUserName 被关联账户
	 * @param relationPayPwd 被关联账户支付密码
	 * @param payPwd 本账户支付密码
	 * @param certNo 本账户身份证号码 
	 * @param mac 校验码	
	 * @return String 出错时返回信息
	 * @author wangpeng
	 * @date 2014年9月2日下午 5:32:09
	 * @since 0.1
	 */  
	public AttachmentResponse<String> personalAuthRelation(String relationUserName, String relationPayPwd,String UserName, String payPwd, String certNo,String mac);
	
	/**
	 * 1119 企业实名认证账户关联接口
	 * @param relationUserName 被关联账户
	 * @param relationPayPwd 被关联账户支付密码
	 * @param payPwd 本账户支付密码
	 * @param bussinessLicence 本账户营业执照注册号 
	 * @param mac 校验码	
	 * @return String 出错时返回信息
	 * @author wangpeng
	 * @date 2014年9月2日下午 5:32:09
	 * @since 0.1
	 */  
	public AttachmentResponse<String> enterpriseAuthRelation(String relationUserName, String relationPayPwd, String UserName,String payPwd, String bussinessLicence,String mac);
	
	/**
	 * 1120 查询实名认证的账号列表接口
	 * @param userId 用户id
	 * @return List UserRelAuInfo列表
	 * @author wangpeng
	 * @date 2014年9月2日下午 5:32:09
	 * @since 0.1
	 */
	public AttachmentResponse<List<GetUserRelAuInfoResponse>> getUserRelAuInfoList(Long userId);
	
	/**
	 * 1121 查询打款金额接口
	 * 
	 * @author wangpeng
	 * @date 2014年9月16日下午2：21：09
	 * @param
	 * @return
	 */
	public AttachmentResponse<BigDecimal> getBandAmount(Long userId);
	
	/**
	 * 1122 通过userId查询用户状态、实名认证状态和各账户状态
	 * @author zhanwx
	 * @date 2014年9月19日 下午1:30:17
	 * @param userId
	 * @return AttachmentResponse<StatusInfo>
	 * @since 0.1
	 * @see
	 */
	public AttachmentResponse<StatusInfo> getStatus(Long userId);
	
	
	
	/**
	 * 1123	账户交易密码验证接口
	 * @param userName 邮箱或手机号
	 * @param payPasswd 支付密码
	 * @return Boolean
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-2 下午6:42:11 
	 * @since 0.1
	 */
	public AttachmentResponse<Boolean> verifyTradePwd(String userName, String tradePasswd);
	
	
	/**
	 * 1124	查询用户是否已经设置交易密码
	 * @param userName 邮箱或手机号
	 * @param payPasswd 支付密码
	 * @return Boolean
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-2 下午6:42:11 
	 * @since 0.1
	 */
	public AttachmentResponse<Boolean> checkTradePwdExists(String userName);
	
	/**
	 * 商户结算查询(按天计算)
	 * @author nyc
	 * @date 2014年12月11日 下午4:24:46
	 * @param enterId
	 * @param beginDate
	 * @param endDate
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public AttachmentResponse<Page<FinancialSummaryDay>> getFinancialSummaryDayList(FinancialSummaryDayQuery query);
	
	
	/**
	 * 商户结算查询(按月计算)
	 * @author nyc
	 * @date 2014年12月11日 下午4:24:46
	 * @param enterId
	 * @param beginDate
	 * @param endDate
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public AttachmentResponse<Page<FinancialSummaryMonth>> getFinancialSummaryMonthList(FinancialSummaryMonthQuery query);
	
	/**
	 * 银行卡认证结果写入（个人）
	 * @author nyc
	 * @date 2015年1月27日 下午3:29:31
	 * @param user
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public AttachmentResponse<String> personalBankCardAu(PersonalUser user);
	
	
	/**
	 * 银行卡认证是否通过查询（个人）
	 * @author nyc
	 * @date 2015年1月27日 下午4:05:38
	 * @param user
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public AttachmentResponse<Boolean> getPersonalBankCardAuStatus(PersonalUser user);
	
}
