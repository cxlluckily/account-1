package com.cssweb.payment.account.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cssweb.payment.account.dao.IUserLoginDao;
import com.cssweb.payment.account.pojo.AccountInfo;
import com.cssweb.payment.account.pojo.AccountMoneyChgDetail;
import com.cssweb.payment.account.pojo.BankInfo;
import com.cssweb.payment.account.pojo.PersonData;
import com.cssweb.payment.account.pojo.PersonalUser;
import com.cssweb.payment.account.pojo.UserLoginInfo;
import com.cssweb.payment.account.pojo.UserTsmMsgInfo;
import com.cssweb.payment.account.sei.IManageService;
import com.cssweb.payment.account.sei.IMobileService;
import com.cssweb.payment.account.test.TestCase;
import com.cssweb.payment.account.util.SecurityUtils;
import com.cssweb.payment.account.vo.AppBindBankPara;
import com.cssweb.payment.account.vo.AppGetUserBindingBankListPara;
import com.cssweb.payment.account.vo.AppRegisterPara;
import com.cssweb.payment.account.vo.CreateMobileAccountResponse;
import com.cssweb.payment.account.vo.GetMobileAccountResponse;
import com.cssweb.payment.common.response.AttachmentResponse;

/**
 * @author zhaoxingwen
 * @version 0.1 (2014年7月24日 上午8:47:26)
 * @since 0.1
 * @see
 */
public class MobileServiceTests   extends TestCase {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private IManageService managerService;
	@Resource
	private IMobileService mobileService;
	
	@Resource
	private SecurityUtils securityUtils;
	
	@Resource 
	public IUserLoginDao userLoginDao;
	
	@Test
	public void testRegister() {
/**
//	public AttachmentResponse<Long> registe(String countryCode,
//			String userName, String idType, String idNumber,
//			String phoneNumber, String emailAddress, String loginPasswd,
//			String payPasswd) {
 */
		AppRegisterPara para = new AppRegisterPara();
		para.setCountryCode("16");
		para.setUserName("测试用户7799");
		para.setIdType("1");
		para.setIdNumber("145627777");
		para.setPhoneNumber("13888888524");
		para.setEmailAddress("test8524@cssweb.com.cn");
		para.setLoginPasswd("loginPasswd");
		para.setPayPasswd("123456");
		AttachmentResponse<Long> t = mobileService.registe(para);
		logger.debug(t.getReturnMsg());
		if(t.isSuccess()){
			logger.debug("userId is :" + t.getAttachment().toString());
		}
		
	}
	
//	@Test
//	public void testGetUserBindingBankList()
//	{
//		AttachmentResponse<List<UserBandBankInfo>>  r= mobileService.getUserBindingBankList(32L);
//		List<UserBandBankInfo> bankcardlist = r.getAttachment();
//		if (bankcardlist.size() == 0){
//			logger.debug("无记录");
//			return;
//		}
//		for(int i=0;i<bankcardlist.size();i++){
//			logger.debug( bankcardlist.get(i).getBankAccountNo() + " "+ bankcardlist.get(i).getUpdateDatetime());	
//		}
//		assertTrue(r.isSuccess());
//	}
	
	@Test
	public void testGetUserBindingBankList()
	{
		AttachmentResponse<List<AppGetUserBindingBankListPara>>  r= mobileService.getUserBindingBankList(101L);
		List<AppGetUserBindingBankListPara> bankcardlist = r.getAttachment();
		if (bankcardlist.size() == 0){
			logger.debug("无记录");
			return;
		}
		for(int i=0;i<bankcardlist.size();i++){
			logger.debug(bankcardlist.get(i).getUserId() +" "+ bankcardlist.get(i).getBankName()+ bankcardlist.get(i).getBankAccountNo() + " "+ bankcardlist.get(i).getUpdateDatetime());	
		}
		assertTrue(r.isSuccess());
	}
	

	@Test
	public void testGetCooperativeBankList(){
		AttachmentResponse<List<BankInfo>>  r = mobileService.getCooperativeBankList();
		List<BankInfo> bankList = r.getAttachment();
		if (bankList.size()==0){
			logger.debug(r.getReturnMsg());
			return;
		}
		for(int i=0;i<bankList.size();i++){
			logger.debug( bankList.get(i).getBankName() + " "+ bankList.get(i).getPaymentId());	
		}
		assertTrue(r.isSuccess());
		logger.debug(r.getReturnMsg());
	}
	
	@Test
	public void testBindBank(){
		//AttachmentResponse r= mobileService.bindBank(100L, "ICBC2", "1", "123456", "张三", "20141218", "12", "01", "211114","xxxxxxxxx");
		AppBindBankPara para = new AppBindBankPara();  
		
//		AttachmentResponse r= mobileService.bindBank(101L, "ICBC2", "1", "123456789", "张三", "20141218", "12", "01", "211114",
//				"xxxxxxxxx");
		para.setUserId(32L);
		para.setBankName("ICBC2");
		
//		public AttachmentResponse bindBank(Long userId, String bankName,
//		String bankCardType, String bankAccountNo, String holderName,
//		String expirDate, String cvcCode, String idType, String idNumber,
//		String bankCardPassword) {
		para.setBankCardType("1");
		para.setBankAccountNo("123456789");
		para.setHolderName("张三");
		para.setExpirDate("2014");
		para.setCvcCode("12");
		para.setIdType("0");
		para.setIdNumber("23423");
		para.setBankCardPassword("xxxxx");
		AttachmentResponse r= mobileService.bindBank(para);
		//assertTrue(r.isSuccess());
		logger.debug(r.getReturnMsg());
	}
	
	@Test
	public void testUnbindBank(){
		AttachmentResponse r = mobileService.unbindBank(100L, "123456");
		logger.debug(r.getReturnMsg());
		assertTrue(r.isSuccess());		
	}
	
	@Test
	public void testGetAccountInfo(){
		AttachmentResponse<AccountInfo> r = mobileService.getAccountInfo(3L);
		AccountInfo acc = r.getAttachment();
		logger.debug(acc.getAccountId().toString());
		
	}
	
	@Test
	public void testGetAccountTradingHistory() throws ParseException{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = df.parse("2014-07-20");
		AttachmentResponse<List<AccountMoneyChgDetail>> r= mobileService.getAccountTradingHistory(3L, beginDate, new Date());
		assertTrue(r.isSuccess());
		
	}
	
	@Test
	public void testUpdatePaswd(){
		AttachmentResponse t = mobileService.changePassword(5341L, 1, "loginPasswd","shilei");
//		AttachmentResponse t = mobileService.changePassword(32L, 2, "loginPasswd","newLoginPasswd");
		logger.debug(t.getReturnMsg());
		assertTrue(t.isSuccess());
	}
	
	@Test
	public void testLogin(){
		//AttachmentResponse<Long> t = mobileService.login("cssweb@cssweb.com.cn", "loginPwd2");
		AttachmentResponse<Long> t = mobileService.login("test8524@cssweb.com.cn", "loginPasswd");
		logger.debug(t.getReturnMsg());
		if (t.isSuccess()){
			logger.debug(t.getAttachment().toString());
		}
		
	}
	
	@Test
	public void testCreateMobileAccount(){
		AttachmentResponse<CreateMobileAccountResponse> t = mobileService.createMobileAccount(5334L,"iccidType","1337701","356899","2","345","25","3.0");
		logger.debug(t.getReturnMsg());
		if (t.isSuccess()){
			logger.debug(t.getAttachment().toString());
		}
	}
	
	@Test 
	public void testGetMobileAccount(){
		AttachmentResponse<GetMobileAccountResponse> t =mobileService.getMobileAccount("13316",null,null);
		logger.debug(t.getReturnCode().toString());
		logger.debug(t.getReturnMsg());
		assertTrue(t.isSuccess());
		logger.debug(t.getAttachment().toString());
	}
	
	@Test
	public void testTopupToMobileAccountByDefault(){
		AttachmentResponse t = mobileService.topupToMobileAccountByDefault(5341L, BigDecimal.valueOf(150),BigDecimal.valueOf(150),"123456","a","b");
		//assertTrue(t.isSuccess());
		logger.debug(t.getReturnMsg());
		logger.debug(t.getReturnCode().toString());
	}
	
	@Test
	public void testVerifyPayPasswd(){
		
		String loginPassword = "123456";
		String payPassword = "111111";
		Long userId = 5550L;
		UserLoginInfo logIn = new UserLoginInfo();
		logIn.setUserId(userId);
		logIn = userLoginDao.selectOne(logIn);
		System.out.println("CreateDateTime is " + logIn.getCreateDatetime().toString());
		System.out.println("Before encrupt,loginPassword is : " + loginPassword);
		System.out.println("Before encrupt,payPassword is : " + payPassword);
		try {
			loginPassword = securityUtils.encryptMD5(loginPassword, logIn.getCreateDatetime());
			payPassword = securityUtils.encryptMD5(payPassword, logIn.getCreateDatetime());
			System.out.println("After encrupt,loginPasswordis : " + loginPassword);
			System.out.println("After encrupt,payPasswordis : " + payPassword);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		AttachmentResponse<Boolean> t= mobileService.verifyPayPasswd(5415L, "qwerty");
//		assertTrue(t.isSuccess());
//		logger.debug(t.getAttachment().toString());
	}
	
	@Test
	public void testGetMobileAccountTradingHistory() throws ParseException{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = df.parse("2014-07-20");
		
		AttachmentResponse<List<AccountMoneyChgDetail>> t =
			mobileService.getMobileAccountTradingHistory(82L, "4", beginDate, new Date());
		
		List<AccountMoneyChgDetail> l = t.getAttachment();
		for(int i=0;i< l.size();i++ ){
			logger.debug(l.get(i).getAccountId().toString());
			logger.debug(l.get(i).getChgAccount().toString());
			logger.debug(l.get(i).getTxnType());
			logger.debug(l.get(i).getChangeDateTime().toString());
		}
				
	}
		
	@Test
	public void testModifyUserInfo(){
		PersonalUser user = new PersonalUser();
		user.setUserId(46L);
		user.setEmailAddress("whatisnew");
		AttachmentResponse t =mobileService.modifyUserInfo(user);
		assertTrue(t.isSuccess());
	}
	
	@Test
	public void testIsPasswdRequired(){
		AttachmentResponse<Boolean> r = mobileService.isBankCardPasswordRequired("122211231");
		if(r.isSuccess()){
			logger.debug("hhh="+r.getAttachment().toString());
		}
	}
	
	
	@Test
	public void testInsertTsm(){
		UserTsmMsgInfo utm = new UserTsmMsgInfo();
		utm.setId(3L);
		utm.setMessageId("190");
		utm.setSenderId("140");
		utm.setReserve2("你好吗???");
		utm.setReMessageId("20");
		utm.setCreateDatetime(new Date());
		utm.setUpdateDatetime(new Date());
		AttachmentResponse<String> r1 = mobileService.tsmRequestDataUpdateCallBack(utm);
		logger.debug("状态码："+r1.getReturnCode().toString());
		logger.debug("状态信息："+r1.getReturnMsg());
		logger.debug(utm.getMessageId());
		assertTrue(r1.isSuccess());
	}

	@Test
	public void testSelectTsm(){
		AttachmentResponse<UserTsmMsgInfo> r1 = mobileService.getTsmMessage("520");
		logger.debug("状态码："+r1.getReturnCode().toString());
		logger.debug("状态信息："+r1.getReturnMsg());
		logger.debug(r1.getAttachment().getReMessageId());
		assertTrue(r1.isSuccess());
	}
	
//	@Test
//	public void testGetMobileAccountByAccountId(){
//		AttachmentResponse<UserMobileAccountInfo> r1 = mobileService.getMobileAccountByAccountId("701");
//		logger.debug("状态码："+r1.getReturnCode().toString());
//		logger.debug("状态信息："+r1.getReturnMsg());
////		logger.debug(r1.getAttachment());
////		assertTrue(r1.isSuccess());
//	}
	@Test
	public void testGetInputPersonData(){
		Long seq = 123L;
		String accountId = "35654";
		String name = "Jim";
		String IdType = "id";
		String IdNum = "345678910";
		
		List<PersonData> result = mobileService.getInputPersonData(seq,accountId,name,IdType,IdNum);
		if (null == result){
			logger.debug("getInputPersonData execution error");
		} else {
			logger.info("Get " + result.size() + " PersonData");
		}
	}
}
