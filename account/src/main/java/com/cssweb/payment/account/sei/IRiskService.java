package com.cssweb.payment.account.sei;

import com.cssweb.payment.account.vo.RiskAccountInfo;
import com.cssweb.payment.account.vo.RiskAuthStatus;
import com.cssweb.payment.account.vo.GetFunctionStatusByAccountId;
import com.cssweb.payment.common.response.AttachmentResponse;


/**
 * 账户系统提供给风控系统的接口
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

public interface IRiskService {
	
	/**
	 * 3.86 获取用户登录密码输错次数
	* @param  username 邮箱或手机号
	* @return AttachmentResponse<String>    返回类型
	* @author nieyc@cssweb.sh.cn
	* @date 2014-7-9 上午11:25:36 
	* @throws
	 */
	public AttachmentResponse<Integer> getLoginPwdWrong(Long userId);
	
	/**
	 * 3.87 获取用户支付密码输错次数
	* @param  username 邮箱或手机号
	* @return AttachmentResponse<String>    返回类型
	* @author nieyc@cssweb.sh.cn
	* @date 2014-7-9 上午11:25:40 
	* @throws
	 */
	public AttachmentResponse<Integer> getPayPwdWrong(Long userId);
	
	/**
	 * 3.88 设置账户状态
	* @param  accountId 账户id
	* @param  state 账户状态
	* @return AttachmentResponse<String>    返回类型
	* @author nieyc@cssweb.sh.cn
	* @date 2014-7-9 上午11:25:44 
	* @throws
	 */
	public AttachmentResponse<String> setAccountStatus(Long accountId, String status);
	
	/**
	 * 3.89 获取账户信息
	* @param  accountId 账户id
	* @return AttachmentResponse<AccountInfo>    返回类型
	* @author nieyc@cssweb.sh.cn
	* @date 2014-7-9 上午11:25:47 
	* @throws
	 */
	public AttachmentResponse<RiskAccountInfo> getAccountInfo(Long accountId, String tradeType, Integer iofalg);
	
	/**
	 * 3.90 获取实名认证状态
	* @param  userId 用户id
	* @return AttachmentResponse<String>    返回类型
	* @author nieyc@cssweb.sh.cn
	* @date 2014-7-9 上午11:24:50 
	* @throws
	 */
	public AttachmentResponse<RiskAuthStatus> getAuthStatus(Long userId);

	/**
	 * 3.91 冻结用户 
	 * @author DuXiaohua
	 * @date 2014年7月24日 下午9:01:17
	 * @param userId 用户id
	 * @return AttachmentResponse 无附加对象
	 * @since 1.0
	 */
	public AttachmentResponse freezeUser(Long userId,String reason);
	
	/**
	 * 3.92 解冻用户
	 * @author DuXiaohua
	 * @date 2014年7月24日 下午9:04:57
	 * @param userId
	 * @return AttachmentResponse 无附加对象
	 * @since 1.0
	 */
	public AttachmentResponse unfreezeUser(Long userId,String reason);
	
	/**
	 * 3.93 获取打款认证的次数
	* @param  userId 用户id
	* @return AttachmentResponse<String>    返回类型
	* @author nieyc@cssweb.sh.cn
	* @date 2014-7-9 上午11:24:08 
	* @throws
	 */
	public AttachmentResponse<Integer> getBankTimes(Long userId);
	
	/**
	 * 3.94 获取打款后已过去的天数
	* @param  userId 用户id
	* @return AttachmentResponse<String>    返回类型
	* @author nieyc@cssweb.sh.cn
	* @date 2014-7-9 上午11:23:09 
	* @throws
	 */
	public AttachmentResponse<Integer> getBankDays(Long userId);
	
	/**
	 * 通过accountId 获取用户功能开通状态，返回手机号和功能开通状态
	 * @author zhanwx
	 * @date 2014年9月3日 上午10:24:55
	 * @param accountId
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public AttachmentResponse<GetFunctionStatusByAccountId> getFunctionByAccountId(Long accountId);
	
	/**
	 * 获取用户交易密码失败次数
	 * @author nyc
	 * @date 2015年1月9日 下午3:36:43
	 * @param userId
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public AttachmentResponse<Integer> getTradePwdWrong(Long userId);
	
 }
