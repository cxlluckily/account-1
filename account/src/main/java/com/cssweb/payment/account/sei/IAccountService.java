package com.cssweb.payment.account.sei;


import java.util.List;

import com.cssweb.payment.account.pojo.AccountInfo;
import com.cssweb.payment.account.vo.UserInfo;
import com.cssweb.payment.common.response.AttachmentResponse;


/**
 * 账户系统提供给其他系统公用的接口
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

public interface IAccountService {

 
	/**
	 * 1001	账户状态查询接口
	 * @param accountId 账户id
	 * @return UserInfo
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-3 下午4:29:57
	 * @since 0.1 
	 */
	public AttachmentResponse<UserInfo> accountStatusQuery(Long accountId);
	
	/**
	 * 1002 账户余额查询接口
	 * @param accountId 账户id
	 * @return AccountInfo
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-3 下午4:30:50 
	 * @since 0.1
	 */
	public AttachmentResponse<AccountInfo> accountBalanceQuery(Long accountId);
	
	
	/**
	 * 1048	用户获取账户列表查询接口
	 * @param userId 用户id
	 * @return List AccountInfo列表 
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-3 上午11:02:42 
	 * @since 0.1
	 */
	public AttachmentResponse<List<AccountInfo>> getAccountList(Long userId);
	
	/**
	 * 1049	手续费查询接口
	 * @param  merchantId 银行id
	 * @param  rateId 费率id
	 * @return Float
	 * @author nieyc@cssweb.sh.cn
	 * @date 2014-7-3 上午11:20:34 
	 * @since 0.1
	 */
	public   AttachmentResponse<Float> getRate(Long merchantId,String rateId);
	
	/**
	 * 1096	根据用户名获取账户余额接口
	 * @param userName 用户名，手机号码或邮箱
	 * @param payPwd 支付密码
	 * @return AccountInfo 
	 * @author Ganlin
	 * @date 2014年7月25日 上午10:28:33
	 * @since 0.1
	 */
	public AttachmentResponse<AccountInfo> getBalanceByUsername(String userName, String payPwd);
	
	/**
	 * 1106 根据账户ID获取用户信息
	 * @param accountId 账户id
	 * @return UserInfo
	 * @author zhanwx
	 * @date 2014年8月1日 上午10:10:03
	 * @since 0.1
	 */
	public AttachmentResponse<UserInfo> queryUserBasicInfoByAccountId(Long accountId);

	
}
