package com.cssweb.payment.account.sei;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cssweb.payment.account.pojo.BankInfo;
import com.cssweb.payment.account.pojo.BusinessInfo;
import com.cssweb.payment.account.vo.AccountDetailed;
import com.cssweb.payment.account.vo.CommercialTenantAccount;
import com.cssweb.payment.account.vo.CommercialTenantAccountDetailed;
import com.cssweb.payment.account.vo.EnterpriseUserQuery;
import com.cssweb.payment.account.vo.GetBusinessInfoListResponse;
import com.cssweb.payment.account.vo.GetEnterpriseUserListResponse;
import com.cssweb.payment.account.vo.GetPersonalUserListResponse;
import com.cssweb.payment.account.vo.PaymentAccount;
import com.cssweb.payment.account.vo.PaymentAccountDetailed;
import com.cssweb.payment.account.vo.PersonalUserQuery;
import com.cssweb.payment.account.vo.UserAccount;
import com.cssweb.payment.account.vo.UserInfo;
import com.cssweb.payment.common.response.AttachmentResponse;
import com.cssweb.payment.common.util.Page;



/**
 * 账户系统提供给运营管理平台的接口
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

public interface IManageService {

	
	/**
	 * 1017	商户签约查询接口
	* @param  user_id 合作者身份 ID
	* @param  service 接口名称
	* @param @return   设定文件
	* @return AttachmentResponse<BusinessInfo>    返回类型
	* @author nieyc@cssweb.sh.cn
	* @date 2014-7-3 下午4:31:59 
	* @throws
	 */
	public AttachmentResponse<BusinessInfo> signingSearch(Long user_id, String service);
	
	 /**
	  * 1050	个人客户信息列表查询接口
	  * @author Ganlin
	  * @date 2014年7月28日 下午2:39:03
	  * @param user PersonalUserQuery对象
	  * @return AttachmentResponse<Page<PersonalUser>>
	  * @since 0.1
	  */
	public AttachmentResponse<Page<GetPersonalUserListResponse>> getPersonalUserList(PersonalUserQuery user);
	 
	
	/**
	 * 1051	企业客户信息列表查询接口
	* @param  enterpriseName 企业名称
	* @param  legalPersonUserName  企业法人姓名
	* @param  agentUserName 企业代理人姓名
	* @param  legalPersonUserType  企业法人证件类型 
	* @param  legalPersonUserNumber  企业法人证件号
	* @param  agentCertificatesType 企业代理人证件类型
	* @param  agentCertificatesNumber 企业代理人证件号
	* @param  beginDate 注册起始日期
	* @param  endDate 注册结束日期
	* @param  pageCount 每页数据条数
	* @param  pageNumber 当前页数
	* @param  authStatus 实名认证状态
	* @param  userStatus 用户状态
	* @param  email 邮箱
	* @param  mobile 手机号码
	* @return PagedAttachmentResponse<List<EnterpriseUser>>    返回类型
	* @author nieyc@cssweb.sh.cn
	* @date 2014-7-3 下午1:22:59 
	* @throws
	*  修改记录：
	* ---------------------------------------------------------------------------------------------------------------------
	* 2014-07-15，聂亚春，修改
	* 修改说明：入参缺少authStatus【实名认证状态】、userStatus【用户状态】
	* 2014-07-17，聂亚春，修改
	* 修改说明：入参 缺少email【邮箱】、mobile【手机号码】
	* 2014-07-23，zhaoxingwen 修改
	* 修改说明：接口返回类型使用AttachmentResponse<Page<EnterpriseUser>> 
	 */
	 /*
	public AttachmentResponse<Page<EnterpriseUser>> getEnterpriseUserList(String enterpriseName,String legalPersonUserName,String agentUserName,
			String legalPersonUserType,String legalPersonUserNumber,String agentCertificatesType,String agentCertificatesNumber,String authStatus,String userStatus,
			String email,String mobile, Date beginDate,Date endDate,Integer pageCount,Integer pageNumber);
*/
	public AttachmentResponse<Page<GetEnterpriseUserListResponse>> getEnterpriseUserList(EnterpriseUserQuery enterpriseUserQuery);
	
	
	/**
	 * 1052	商户签约接口
	 * @author Ganlin
	 * @date 2014年7月29日 下午1:06:58
	 * @param businessInfo
	 * @return AttachmentResponse<String>
	 * @since 0.1
	 */
	public AttachmentResponse<String> merchantSigning(List<BusinessInfo> businessInfoList);
	
	/**
	 * 1053	商户信息修改接口
	 * @author Ganlin
	 * @date 2014年7月29日 下午1:16:48
	 * @param businessInfo
	 * @return AttachmentResponse<String>
	 * @since 0.1
	 */
	public AttachmentResponse<String> updateMerchant(BusinessInfo businessInfo);

	
	/**
	 * 1054	商户信息列表查询接口
	 * @author Ganlin
	 * @date 2014年7月31日 上午11:37:02
	 * @param query
	 * @return 
	 * @since 0.1
	 */
	public AttachmentResponse<Page<GetEnterpriseUserListResponse>> getMerchant(EnterpriseUserQuery query);
	
	
	/**
	 * 1055	商户解约接口
	 * @author Ganlin
	 * @date 2014年8月1日 下午1:53:20
	 * @param userId
	 * @param channelType 签约类型
	 * @return 
	 * @since 0.1
	 */
	public AttachmentResponse<String>   merchantTermination(Long userId);
	
	
	/**
	 * 1056	账户列表（个人）查询接口
	 * @author Ganlin
	 * @date 2014年7月27日 下午7:44:17
	 * @param userName 用户姓名
	 * @param mobileNo 用户手机号
	 * @param mailAddress 用户邮箱地址
	 * @param beginDate 账户创建时间起
	 * @param endDate 账户创建时间止
	 * @param accountType 账户类型
	 * @param accountStatus 账户状态
	 * @param pageSize 每页数据条数
	 * @param pageNo 当前页数
	 * @return  AttachmentResponse<Page<UserAccount>>
	 * @since 0.1
	 */
	public AttachmentResponse<Page<UserAccount>> getAccountInfoList(String userName,
			String mobileNo, String mailAddress, Date beginDate, Date endDate, String accountType, 
			String accountStatus, Integer pageSize,Integer pageNo);
	
	/**
	 * 1057	账户（个人 ）资金变化明细列表查询接口
	 * @author Ganlin
	 * @date 2014年7月28日 上午10:26:50
	 * @param  accountNumber 账户编号
	 * @param  userNumber 用户编号
	 * @param  userName 用户姓名
	 * @param  transactionType  交易类型
	 * @param  beginDate 开始日期
	 * @param  endDate 截止日期
	 * @param  pageSize 每页数据条数
	 * @param  pageNo  当前页数
	 * @return AttachmentResponse<Page<AccountDetailed>>
	 * @since 0.1
	 */
	public AttachmentResponse<Page<AccountDetailed>> queryUserAccountDetailed(
			Long accountNumber, Long userNumber, String userName,String transactionType, 
			Date beginDate, Date endDate, Integer pageSize, Integer pageNo);
	
	/**
	 * 1058	账户（个人）金额调整接口
	 * @author Ganlin
	 * @date 2014年7月25日 下午4:07:26
	 * @param  accountNumber 账户编号
	 * @param  inOutMark 进出账标志
	 * @param  money 金额
	 * @param  staffNumber 操作员工号
	 * @param accountPosition 金额位置
	 * @return AttachmentResponse<String>    返回类型
	 * @since 0.1
	 */
	public AttachmentResponse<Long> userAccountAdjustment(String tradeSn,Long accountId, int inOutMark, BigDecimal money, String staffNumber, String accountPosition);
	
	
	
//	/**
//	 * 1059	账户列表（企业）查询接口
//	* @param  legalPersonName 法人姓名
//	* @param  commercialTenantType 商户类型
//	* @param  accountStatus 账户状态
//	* @param  pageCount 每页数据条数
//	* @param  pageNumber  当前页数
//	* @param @return   设定文件
//	* @return PagedAttachmentResponse<List<EnterpriseUser>>    返回类型
//	* @author nieyc@cssweb.sh.cn
//	* @date 2014-7-3 下午3:08:47 
//	* @throws
//	 */
//	public PagedAttachmentResponse<List<AccountInfo>> queryCommercialTenantAccount(String legalPersonName,
//			int commercialTenantType, int accountStatus, int pageCount,
//			int pageNumber);
	
	/**
	 * 1059	账户列表（企业）查询接口
	 * @author Ganlin
	 * @date 2014年7月26日 下午6:28:23
	 * @param legalPersonName 法人姓名
	 * @param legalPersonMail 法人邮箱
	 * @param commercialTenantType  商户类型
	 * @param accountStatus 账户状态
	 * @param beginDate 账户创建日期起
	 * @param endDate 账户创建日期止
	 * @param pageSize 每页数据条数
	 * @param pageNo 当前页数
	 * @return AttachmentResponse<Page<CommercialTenantAccount>>
	 * @since 0.1
	 */
	public AttachmentResponse<Page<CommercialTenantAccount>> queryCommercialTenantAccount(String legalPersonName, String legalPersonMail, String managerTel,String commercialTenantType, 
			String accountStatus, Date beginDate, Date endDate,Integer pageSize, Integer pageNo);
	
	/**
	 * 
	 * @author Ganlin
	 * @date 2014年7月26日 下午6:47:20
	 * @param accountId 账户编号
	 * @param enterpriseName 企业名称
	 * @param CTNumber 商户编号
	 * @param transactionType 交易类型
	 * @param beginDate 交易日期起
	 * @param endDate 交易日期止
	 * @param pageSize 每页数据条数
	 * @param pageNo 当前页数
	 * @return AttachmentResponse<Page<CommercialTenantAccountDetailed>>
	 * @since 0.1
	 */
	public AttachmentResponse<Page<CommercialTenantAccountDetailed>> queryCTAccountDetailed(Long accountId, String enterpriseName, 
			Long CTNumber, String transactionType, Date beginDate, Date endDate, Integer pageSize, Integer pageNo);
	
	/**
	 * 1061	账户（企业）金额调整接口
	 * 应该和1058 相同，直接调用1058
	 * @author Ganlin
	 * @date 2014年7月26日 下午1:40:26
	 * @param accountId
	 * @param inOutMark
	 * @param money
	 * @param staffNumber
	 * @param accountPosition
	 * @return 
	 * @since 0.1
	 */
	public AttachmentResponse<Long> ctAccountAdjustment(String tradeSn,Long accountId, int inOutMark, BigDecimal money, String staffNumber, String accountPosition);
	 
	/**
	 * 1062	账户（平台）列表查询接口
	 * @author Ganlin
	 * @date 2014年7月27日 下午5:57:17
	 * @param accountType 账户类型
	 * @param pageSize 每页数据条数
	 * @param pageNo 当前页数
	 * @return AttachmentResponse<Page<PaymentAccount>>
	 * @since 0.1
	 */
	public AttachmentResponse<Page<PaymentAccount>> queryPaymentAccount(String accountType, Integer pageSize, Integer pageNo);
	
	
	/**
	 * 1063	账户（平台）资金变化明细查询接口
	 * @author Ganlin
	 * @date 2014年7月27日 下午3:28:35
	 * @param accountNumber 账户编号
	 * @param accountType 账户类型
	 * @param beginDate 交易日期起
	 * @param endDate 交易日期止
	 * @param pageSize 每页数据条数
	 * @param pageNo 当前页数
	 * @return AttachmentResponse<Page<PaymentAccountDetailed>>
	 * @since 0.1
	 */
	public AttachmentResponse<Page<PaymentAccountDetailed>> queryPaymentAccountDetailed(Long accountNumber, String accountType, 
			Date beginDate, Date endDate, Integer pageSize, Integer pageNo);
	
	/**
	 * 1064	平台账户增加和修改接口
	 * @author Ganlin
	 * @date 2014年7月27日 下午3:47:37
	 * @param bankInfo 平台账户对象
	 * @return AttachmentResponse<String>
	 * @since 0.1
	 */
	public AttachmentResponse<String> modefyPaymentAccount(BankInfo bankInfo);
	
	
	//1065	账户（平台）金额调整接口  应该和1058 相同。

	/**
	 * 1104	根据用户ID查询用户信息接口
	 * 根据入参在用户登陆表里获取邮箱、手机号、用户类别，在用户信息表里获取姓名并返回结果
	 * @author shilei@cssweb.com.cn
	 * @date 2014年8月1日 下午4:09:47
	 * @param userId - 用户ID
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public AttachmentResponse<UserInfo> getUserInfoByUserID(Long userId);
	
	/**
	 * 1105	根据用户邮箱、手机号码查询用户信息接口：
	 * 根据入参在用户登陆表里检查获取userid，根据userid在用户信息表里获取姓名并返回结果
	 * <br> 邮箱和手机号码中至少得有一个不能为空
	 * @author shilei@cssweb.com.cn
	 * @date 2014年8月1日 下午4:24:19
	 * @param userName 邮箱 或者 手机
	 * @return  
	 * @since 0.1
	 * @see
	 */
	public AttachmentResponse<UserInfo> getUserInfoByTelEmail(String userName);
	/**
	 *  根据用户邮箱、手机号码和用户类型查询用户信息接口：
	 * 根据入参在个人用户信息表或企业用户信息表里查询用户信息并返回对象
	 * <br> 邮箱和手机号码中至少得有一个不能为空
	 * @author zhanwx
	 * @date 2014年8月18日 下午1:01:38
	 * @param userName
	 * @param userType
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public AttachmentResponse<UserInfo> getUserInfoByTelEmail(String userName,String userType);
	/**
	 * 1114 查询商户签约服务列表
	 * @author DuXiaohua
	 * @date 2014年8月9日 下午1:39:52
	 * @param userId 企业用户id
	 * @return AttachmentResponse<GetBusinessInfoListResponse> 包含企业信息和商户信息列表
	 * @since 1.0
	 */
	public AttachmentResponse<GetBusinessInfoListResponse> getBusinessInfoList(Long userId);
	
	/**
	 * 增加服务
	 * 
	 * @author wangpeng
	 * @date 2014年9月3日上午11:10:50
	 * @param business 服务对象
	 * @return 
	 */
	public AttachmentResponse<String> addBusinessInfo(BusinessInfo businessInfo);
	
	/**
	 * 删除服务
	 * 
	 * @author wangpeng
	 * @date 2014年9月3日上午11:10:50
	 * @param business 服务对象
	 * @return 
	 */
	public AttachmentResponse<String> deleteBusinessInfoById(Long id);
	
	/**
	 * 根据绑定电话或邮箱查询用户列表
	 * 
	 * @author wangpeng
	 * @date 2014年9月28日 下午16:49:32
	 * @param userName 绑定的电话或邮箱
	 * @param userType 用户类型
	 * @return list 用户集合
	 */
	public AttachmentResponse<List<UserInfo>> getUserInfoListByTelEmail(String userName , String userType);
	
}
