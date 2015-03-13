/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.service.support;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cssweb.payment.account.dao.IAccountInfoDao;
import com.cssweb.payment.account.dao.IAccountMoneyChgDetailDao;
import com.cssweb.payment.account.dao.IAccountOperDetailDao;
import com.cssweb.payment.account.dao.IAccountOperInfoDao;
import com.cssweb.payment.account.dao.IBankInfoDao;
import com.cssweb.payment.account.dao.IBusinessInfoDao;
import com.cssweb.payment.account.dao.IEnterpriseUserDao;
import com.cssweb.payment.account.dao.IPersonalUserDao;
import com.cssweb.payment.account.dao.IUserBandBankInfoDao;
import com.cssweb.payment.account.dao.IUserFunctionInfoDao;
import com.cssweb.payment.account.dao.IUserLoginDao;
import com.cssweb.payment.account.dao.IUserLoginLogDao;
import com.cssweb.payment.account.dao.IUserMobileAccountInfoDao;
import com.cssweb.payment.account.dao.IUserTsmMsgInfoDao;
import com.cssweb.payment.account.pojo.AccountInfo;
import com.cssweb.payment.account.pojo.AccountMoneyChgDetail;
import com.cssweb.payment.account.pojo.AccountOperDetail;
import com.cssweb.payment.account.pojo.AccountOperInfo;
import com.cssweb.payment.account.pojo.PersonalUser;
import com.cssweb.payment.account.pojo.UserBandBankInfo;
import com.cssweb.payment.account.pojo.UserFunctionInfo;
import com.cssweb.payment.account.pojo.UserLoginInfo;
import com.cssweb.payment.account.pojo.UserLoginLog;
import com.cssweb.payment.account.pojo.UserMobileAccountInfo;
import com.cssweb.payment.account.pojo.UserTsmMsgInfo;
import com.cssweb.payment.common.provider.sei.IIdGeneratorService;

/**
 * 账户管理公用的支持类
 * 
 * @author DuXiaohua
 * @version 1.0 (2014年7月23日 下午3:56:38)
 * @since 1.0
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MobileServiceSupport {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private ManageServiceSupport manageServiceSupport;
	
	@Resource
	private IIdGeneratorService idGeneratorService;
	
	@Resource
	public IAccountInfoDao accountInfoDao;
	
	@Resource
	public IBusinessInfoDao  businessInfoDao;
	
	@Resource
	public IPersonalUserDao  personalUserDao;
	
	@Resource
	public IEnterpriseUserDao enterpriseUserDao; 
	
	@Resource
	public IBankInfoDao bankInfoDao;
	
	@Resource
	public IAccountMoneyChgDetailDao accountMoneyChgDetailDao;
	
	@Resource 
	public IUserLoginDao userLoginDao;
	
	@Resource
	public MobileServiceSupport mobileServiceSupport;
	
	@Resource 
	public IUserTsmMsgInfoDao userTsmMsgInfoDao;
	
	@Resource 
	public IUserBandBankInfoDao userBandBankInfoDao;
	
	@Resource 
	public IUserMobileAccountInfoDao mobileAccountInfoDao;
	
	@Resource
	public IAccountOperInfoDao accountOperInfoDao;
	
	@Resource
	public IAccountOperDetailDao accountOperDetailDao;
	
	@Resource
	private IUserFunctionInfoDao userFunctionInfoDao;
	
	@Resource
	private IUserLoginLogDao userLoginLogDao;
	
	public void modifyUserInfo(PersonalUser user,UserLoginInfo loginInfo){
//		更新用户信息表
		personalUserDao.update(user);		
//		更新登陆信息表
		userLoginDao.update(loginInfo);
	}
	
	public void modifyTsmMsgInfo(UserTsmMsgInfo userTsmMsgInfo){
		userTsmMsgInfoDao.update(userTsmMsgInfo);
	}
	
	/**
	 * 手机注册
	 * @author zhaoxingwen
	 * @date 2014年7月26日 下午4:12:27
	 * @param accountInfo 
	 * @since 0.1
	 * @see
	 */
	public void AppRegister(PersonalUser personal,UserLoginInfo loginf,
			AccountInfo accountInfo){
		personalUserDao.insert(personal);
		userLoginDao.insert(loginf);
		accountInfoDao.insert(accountInfo);
	}
	
	/**
	 * 处理圈存请求
	 * @author zhaoxingwen
	 * @date 2014年7月26日 下午4:12:27
	 * @param accountInfo 
	 * @since 0.1
	 * @see
	 * --------------------------------------------------------------------------------
	 * 修改处理流程 zxw20140825
	 */
	public void ApptopupToMobileAccountByDefault(AccountOperInfo operInfo,UserTsmMsgInfo userTsmMsg ){
		accountOperInfoDao.insert(operInfo);
		userTsmMsgInfoDao.insert(userTsmMsg);
	}
	/**
	 * 3.77 创建移动支付帐户 - 闪客蜂现金账户
	 * @author zhaoxingwen
	 * @date 2014年7月26日 下午4:27:41
	 * @param accountInfo 
	 * @since 0.1
	 * @see
	 */
	public void AppCreateMobileAccount(AccountInfo preAccountInfo,AccountInfo accountInfo,UserMobileAccountInfo mobileAccountInfo,
			UserFunctionInfo uf1,UserFunctionInfo uf2){
		accountInfoDao.insert(preAccountInfo);
		accountInfoDao.insert(accountInfo);
		mobileAccountInfoDao.insert(mobileAccountInfo);	
		userFunctionInfoDao.insert(uf1);
		userFunctionInfoDao.insert(uf2);
	}
	
	public void AccountInfoUpd(AccountInfo accountInfo){
		accountInfoDao.update(accountInfo);
	}
	
	/**
	 * 用户银行绑定信息表插入数据
	 */
	public void UserBandBankInfoIns(UserBandBankInfo para){
		userBandBankInfoDao.insert(para);
	}
	/**
	 * 用户银行卡解绑
	 */
	public void UserBandBankInfoUpd(UserBandBankInfo para){
		userBandBankInfoDao.updateByUseridAccountid(para);
	}
	/**
	 * 修改密码
	 */
	public void UserLoginInfoUpd(UserLoginInfo para ,UserLoginLog ul){
		userLoginDao.update(para);
		if(null != ul){
			userLoginLogDao.insert(ul);
		}
	}

	/**
	 * 插入账户操作流水表
	 */
	public void AccountOperInfoIns(AccountOperInfo para){
		accountOperInfoDao.insert(para);
	}
	
	public void AccountOperDetailIns(AccountOperDetail para){
		accountOperDetailDao.insert(para);
	}
	
	public void AccountMoneyChgDetailIns(AccountMoneyChgDetail para){
		accountMoneyChgDetailDao.insert(para);
	}
	/**
	 * 手机-账户 关系表插入记录
	 * @author zhaoxingwen
	 * @date 2014年7月25日 上午10:43:01
	 * @param mobileAccount 
	 * @since 0.1
	 * @see
	 */
	public void UserMobileAccountIns(UserMobileAccountInfo mobileAccount){
		mobileAccountInfoDao.insert(mobileAccount);	
	}
	/**
	 * 是否是邮箱
	 * @author zhaoxingwen
	 * @date 2014年7月25日 上午9:43:18
	 * @param str
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public boolean isEmail(String str) {
		for(int i=0;i<str.length();i++){   
			if (str.charAt(i) == '@')
				return true;
		}
		return false;
	}
	/**
	 * 修改手机注册信息表
	 * @author zhaoxw
	 * @date 20140903
	 * @param userMobileAccountInfo
	 * 
	 */
	public void updateUserMobileAccountInfo(UserMobileAccountInfo userMobileAccountInfo){
		mobileAccountInfoDao.update(userMobileAccountInfo);
	}
	//lilei
	public void serviceStateChangeNotification(UserMobileAccountInfo userMobileAccountInfo){
//		更新移动用户账户表
		mobileAccountInfoDao.update(userMobileAccountInfo);
	}
	
	public static boolean isNumeric(String str) {
		for (int i = str.length();--i>=0;) {   
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 接收到requestDataUpdateCallback时更新状态,修改账户金额，记流水明细
	 * @author zhaoxw
	 * @date 20140822
	 * 
	 */
	public void callBackUpdateStatus(UserTsmMsgInfo userTsmMsgInfo,AccountOperInfo accountOperInfo,
			AccountInfo mainAccountInfo,AccountInfo cashAccountInfo,
			AccountOperDetail operDetailIn,AccountOperDetail operDetailOut,AccountMoneyChgDetail moneyChgDetail){
		userTsmMsgInfoDao.update(userTsmMsgInfo);
		accountOperInfoDao.update(accountOperInfo);
		accountInfoDao.update(mainAccountInfo);
		accountInfoDao.update(cashAccountInfo);
		accountOperDetailDao.insert(operDetailIn);
		accountOperDetailDao.insert(operDetailOut);
		accountMoneyChgDetailDao.insert(moneyChgDetail);
	}
	
	
}
