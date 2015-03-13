package com.cssweb.payment.account.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.cssweb.payment.account.constant.DictionaryKey;
import com.cssweb.payment.account.pojo.EnterpriseUser;
import com.cssweb.payment.account.pojo.FinancialSummaryDay;
import com.cssweb.payment.account.pojo.FinancialSummaryMonth;
import com.cssweb.payment.account.pojo.PersonalUser;
import com.cssweb.payment.account.pojo.UserBandBankInfo;
import com.cssweb.payment.account.pojo.UserBankAuInfo;
import com.cssweb.payment.account.pojo.UserQuestionInfo;
import com.cssweb.payment.account.sei.IMobileService;
import com.cssweb.payment.account.sei.IPortalService;
import com.cssweb.payment.account.sei.ITradeService;
import com.cssweb.payment.account.test.TestCase;
import com.cssweb.payment.account.util.DictionaryUtils;
import com.cssweb.payment.account.vo.AppGetUserBindingBankListPara;
import com.cssweb.payment.account.vo.ApplyMoneyResponse;
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

public class PortalServiceTests extends TestCase {

	@Resource
	private IPortalService portalService;
	@Resource
	private IMobileService mobileService;
	@Resource
	private ITradeService tradeService;
	
	private String personalEmail = "ganlinbupt@163.com";
	private String personalMobile = "15210830453";
	private String enterpriseEmail = "cssweb@cssweb.com.cn";
	private String loginPwd = "loginPwd";
	private String payPwd = "payPwd";
	
	/**
	 * 测试3.18	账户支付密码验证接口
	 * @author Ganlin
	 * @date 2014年7月25日 下午1:28:28 
	 * @since 0.1
	 *//*
	@Test
	public void testVerifyPayPwd () {
		String nationality = DictionaryUtils.getStringValue(DictionaryKey.COUNTRY_CODE_CN);
		String name = "吴此人";
		String email = "1595831939@qq.com";
		String regType = DictionaryUtils.getStringValue(DictionaryKey.REG_TYPE_EMAIL);
		String source = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);
		String cardType = DictionaryUtils.getStringValue(DictionaryKey.ID_TYPE_IDCARD);
		String cardId = "1111111111111111111111";
		String loginPwd = "loginPwd";
		String payPwd = "111111";
		
		AttachmentResponse<Boolean> response;
		//密码错误

		response = portalService.verifyPayPwd(email, payPwd);
		logger.debug("状态码：" + response.getReturnCode());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		assertFalse(response.getAttachment());
		//密码正确
		response = portalService.verifyPayPwd(email, payPwd);
		logger.debug("状态码：" + response.getReturnCode());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		assertTrue(response.getAttachment());
	}
	
	*//**
	 * 测试3.19	用户名是否存在接口
	 * @author Ganlin
	 * @date 2014年7月25日 下午1:29:49 
	 * @since 0.1
	 *//*
	@Test
	public void testCheckUserNameIsExits () {
		AttachmentResponse<Boolean> response = portalService.checkUserNameIsExits(null);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		AttachmentResponse<Boolean> response1 = portalService.checkUserNameIsExits("notExist");
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		logger.debug("Attachment：" + response1.getAttachment());
		assertTrue(response1.isSuccess());
		assertFalse(response1.getAttachment());
		AttachmentResponse<Boolean> response2 = portalService.checkUserNameIsExits("18705182732");
		logger.debug("状态码：" + response2.getReturnCode().toString());
		logger.debug("状态信息：" + response2.getReturnMsg());
		logger.debug("Attachment：" + response2.getAttachment());
		assertTrue(response2.isSuccess());
		assertTrue(response2.getAttachment());
		AttachmentResponse<Boolean> response3 = portalService.checkUserNameIsExits("heqiang@cssweb.sh.cn");
		logger.debug("状态码：" + response3.getReturnCode().toString());
		logger.debug("状态信息：" + response3.getReturnMsg());
		logger.debug("Attachment：" + response3.getAttachment());
		assertTrue(response3.isSuccess());
		assertTrue(response3.getAttachment());
	}
	
	*//**
	 * 测试3.20	用户（个人）注册接口
	 * @author Ganlin
	 * @date 2014年7月25日 下午1:30:30 
	 * @since 0.1
	 *//*
	@Test
	public void testPersonalRegistration() {
		
		AttachmentResponse<Long> response;

		response = portalService.personalRegistration("156","88552200","王欢","885522000@qq.com",
				 "1", "1", "1", "20102100161", "loginpwd", "paypwd", 1, "太平");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Attachment：" + response.getAttachment());
		assertTrue(response.isSuccess());
		
		
		
	}
	
	*//**
	 * 理财平台登录测试
	 * @author nyc
	 * @date 2014年12月19日 下午4:39:56 
	 * @since 0.1
	 * @see
	 *//*
	@Test
	public void testPersonalRegistrationLc() {
		
		AttachmentResponse<Long> response;
		response = portalService.personalRegistration("156","13341830268","1","1","loginPwd");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Attachment：" + response.getAttachment());
		assertTrue(response.isSuccess());
		
		
		
	}
	
	*//**
	 * 测试3.21	企业注册接口
	 * @author Ganlin
	 * @date 2014年7月25日 下午1:31:28 
	 * @since 0.1
	 *//*
	@Test
	public void testEnterpriseRegistration() {
		String source = "1"	;
		String answer = "I am answer";	
		AttachmentResponse<Long> response;
		

		response = portalService.enterpriseRegistration("sltest123@163.com", "loginPwd","PayPwd", 1, answer, source);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Attachment：" + response.getAttachment());
		assertFalse(response.isSuccess());
		//使用邮箱注册
		response = portalService.enterpriseRegistration(enterpriseEmail, loginPwd, payPwd, 1, answer, source);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Attachment：" + response.getAttachment());
		assertTrue(response.isSuccess());
		
	}
	
	*//**
	 * 测试3.22	用户登陆接口
	 * @author Ganlin
	 * @date 2014年7月25日 下午1:31:50 
	 * @since 0.1
	 *//*
	@Test
	public void testLogin() {
		//String email = "ganlinbupt@163.com";
		//String passWord = "123456";
		String source = "1";
		String ip = "192.168.5.123";
		String mac = "klajsdlfasdjfklj";
		
		AttachmentResponse<LoginResponse> response;
		
		response = portalService.login(null, loginPwd, ip, mac, source);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Attachment：" + response.getAttachment());
		assertFalse(response.isSuccess());
		
		//密码错误

		response = portalService.login("sltest@163.com", "loginPwd", ip, mac, source);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Attachment：" + response.getAttachment());
		assertFalse(response.isSuccess());
		//使用个人邮箱登录
		response = portalService.login(personalEmail, loginPwd, ip, mac, source);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Attachment：" + response.getAttachment());
		assertTrue(response.isSuccess());	
		//使用个人手机登录
		response = portalService.login(personalMobile, loginPwd, ip, mac, source);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Attachment：" + response.getAttachment());
		assertTrue(response.isSuccess());
		//使用企业邮箱登录
		response = portalService.login(enterpriseEmail, loginPwd, ip, mac, source);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Attachment：" + response.getAttachment());
		assertTrue(response.isSuccess());
		
	}
	
	*//**
	 * 测试3.23	用户登出接口
	 * @author Ganlin
	 * @date 2014年7月25日 下午1:55:48 
	 * @since 0.1
	 *//*
	@Test
	public void tesetLoginOut() {
	
		AttachmentResponse<String> response;
		
		response = portalService.logout((long) 10);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Attachment：" + response.getAttachment());
		assertFalse(response.isSuccess());
		
		response = portalService.logout((long) 34);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Attachment：" + response.getAttachment());
		assertTrue(response.isSuccess());
	
	}
	
	*//**
	 * 测试3.24	用户（个人和企业）银行打款认证接口
	 * @author Ganlin
	 * @date 2014年7月25日 下午1:56:47 
	 * @since 0.1
	 *//*
	@Test
	public void testBankAu() {
	
		AttachmentResponse<String> response;
		//认证记录不存在
		response = portalService.bankAu(999L, "工商银行", "666666", new BigDecimal("0.0012"), "01");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Attachment：" + response.getAttachment());
		assertFalse(response.isSuccess());
		//认证金额不正确，认证不通过
		response = portalService.bankAu(70L, "中国银行", "123", new BigDecimal("0.2"), "01");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Attachment：" + response.getAttachment());
		assertFalse(response.isSuccess());
		
		response = portalService.bankAu(174L, "01040000", "6322222", new BigDecimal("0.42"), "01");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Attachment：" + response.getAttachment());
		assertTrue(response.isSuccess());
		
		//认证通过
		response = portalService.bankAu(70L, "中国银行", "123", new BigDecimal("0.1"), "01");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Attachment：" + response.getAttachment());
		assertTrue(response.isSuccess());
		
		
	}*/
	
	/**
	 * 测试3.25	个人用户基本信息修改接口
	 * @author Ganlin
	 * @date 2014年7月25日 下午1:57:18 
	 * @since 0.1
	 */
	/*@Test
	public void testUpdatePersonal() {
		PersonalUser user = new PersonalUser();
		user.setUserId(546L);
		user.setPhoneNumber("13300000001");
		AttachmentResponse<String> response;
		response = portalService.updatePersonal(user,"1");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Attachment：" + response.getAttachment());
		assertTrue(response.isSuccess());
	}*/
	
	/**
	 * 测试3.26	个人用户实名认证进度查询接口
	 * @author Ganlin
	 * @date 2014年7月25日 下午1:57:41 
	 * @since 0.1
	 *//*
	@Test
	public void testPersonalAuProcess() {
		AttachmentResponse<PersonalAuProcessResponse> response;
		//参数null
		response = portalService.personalAuProcess(null);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Attachment：" + response.getAttachment());
		assertFalse(response.isSuccess());
		//用户不存在
		response = portalService.personalAuProcess((long) 0);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Attachment：" + response.getAttachment());
		assertFalse(response.isSuccess());
		//查询成功

		response = portalService.personalAuProcess(212L);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());

		logger.debug("Attachment：" + response.getAttachment().getErrorTimes());
		assertTrue(response.isSuccess());
	}
	
	*//**
	 * 测试3.27	个人用户实名认证申请时间查询接口
	 * @author Ganlin
	 * @date 2014年7月25日 下午1:58:11 
	 * @since 0.1
	 *//*
	@Test
	public void testGetPersonalAuDate() {
		AttachmentResponse<Date> response;
		//参数null
		response = portalService.getPersonalAuDate(null);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Attachment：" + response.getAttachment());
		assertFalse(response.isSuccess());
		//用户不存在
		response = portalService.getPersonalAuDate((long) 0);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Attachment：" + response.getAttachment());
		assertFalse(response.isSuccess());
		//查询成功

		response = portalService.getPersonalAuDate(212L);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Attachment：" + response.getAttachment());
		assertTrue(response.isSuccess());
	}
	
	*//**
	 * 测试3.28	个人用户撤销实名认证（未审核）接口
	 * @author Ganlin
	 * @date 2014年7月25日 下午1:58:36 
	 * @since 0.1
	 *//*
	@Test
	public void testPersonalAuCancel() {
		AttachmentResponse<String> response;
		//参数null
		response = portalService.personalAuCancel(null);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Attachment：" + response.getAttachment());
		assertFalse(response.isSuccess());
		//用户不存在
		response = portalService.personalAuCancel((long) 0);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Attachment：" + response.getAttachment());
		assertFalse(response.isSuccess());
		//撤消失败
		response = portalService.personalAuCancel((long) 35);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Attachment：" + response.getAttachment());
		assertFalse(response.isSuccess());
		//撤消成功
		response = portalService.personalAuCancel(65L);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Attachment：" + response.getAttachment());
		assertTrue(response.isSuccess());
	}
	
	*//**
	 * 测试 3.29	企业实名认证（法人、代理人）接口
	 * @author zhanwx
	 * @date 2014年7月27日 上午10:55:57 
	 * @since 0.1
	 * @see
	 *//*
	@Test
	public void testEnterpriselAu(){
		EnterpriseUser eu = new EnterpriseUser();
		eu.setUserId(214L);
		eu.setLegalPersonname("张三测的");
		eu.setLegalPersonphone("1234567890");
		
		UserBankAuInfo userBankAuInfo = new UserBankAuInfo();
		userBankAuInfo.setBankName("中国银行");
		userBankAuInfo.setBankNo("980129032431");
		userBankAuInfo.setBandAmount(BigDecimal.TEN);
		
		AttachmentResponse<String> response = portalService.enterpriselAu(null,null);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		AttachmentResponse<String> response1 = portalService.enterpriselAu(eu);
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		assertFalse(response1.isSuccess());
		AttachmentResponse<String> response2 = portalService.enterpriselAu(eu,userBankAuInfo);
		logger.debug("状态码：" + response2.getReturnCode().toString());
		logger.debug("状态信息：" + response2.getReturnMsg());
		assertTrue(response2.isSuccess());
		}
	
	*//**
	 * 测试 3.30企业客户基本信息查询接口
	 * @author zhanwx
	 * @date 2014年7月27日 上午10:49:44 
	 * @since 0.1
	 * @see
	 *//*
	@Test
	public void testGetEnterpriseUserBasicInfo(){
		AttachmentResponse<GetEnterpriseUserBasicInfoResponse> response = portalService.getEnterpriseUserBasicInfo(null);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		AttachmentResponse<GetEnterpriseUserBasicInfoResponse> response1 = portalService.getEnterpriseUserBasicInfo(26L);
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		assertFalse(response1.isSuccess());
		AttachmentResponse<GetEnterpriseUserBasicInfoResponse> response2 = portalService.getEnterpriseUserBasicInfo(447L);
		logger.debug("状态码：" + response2.getReturnCode().toString());
		logger.debug("状态信息：" + response2.getReturnMsg());
		logger.debug("实名描述：" + response2.getAttachment().getAuthDescribe());
		assertTrue(response2.isSuccess());
	}
	
	*//**
	 * 测试 3.31	企业客户基本信息修改接口
	 * @author zhanwx
	 * @date 2014年7月27日 上午10:43:13 
	 * @since 0.1
	 * @see
	 *//*
	@Test
	public void testUpdateEnterprise(){
		EnterpriseUser eu = new EnterpriseUser();
		eu.setUserId(214L);
		eu.setManagerMobile("2240538");
		eu.setEmail("158171@live.cn");
		AttachmentResponse<String> response2 = portalService.updateEnterprise(eu,null);
		logger.debug("状态码：" + response2.getReturnCode().toString());
		logger.debug("状态信息：" + response2.getReturnMsg());
		assertTrue(response2.isSuccess());
		}
	
	@Test
	public void testUpdateEnterprise2(){
		EnterpriseUser param = new EnterpriseUser();
		//param.setUserId(user.getUserId());
		param.setUserId(214L);
		param.setAuthStatus("6");// 6打款中
		//param.setAuthDescribe(content);//审核描述
		param.setAuthDescribe("123123");//审核描述
		AttachmentResponse response = portalService.updateEnterprise(param,null);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
		}
	
	*//**
	 * 测试 3.32企业客户实名认证进度查询接口
	 * @author zhanwx
	 * @date 2014年7月27日 上午10:27:42 
	 * @since 0.1
	 * @see
	 *//*
	@Test
	public void testEnterpriseAuProcessQuery(){
		AttachmentResponse<String> response = portalService.enterpriseAuProcessQuery(null);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		AttachmentResponse<String> response1 = portalService.enterpriseAuProcessQuery(26L);
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		assertFalse(response1.isSuccess());
		AttachmentResponse<EnterpriseAuProcessQueryResponse> response2 = portalService.enterpriseAuProcessQuery(223L);
		logger.debug("状态码：" + response2.getReturnCode().toString());
		logger.debug("状态信息：" + response2.getReturnMsg());
		logger.debug("---------------打印信息---------------------");
		logger.debug("认证状态："+response2.getAttachment().getAuthStatus());
		logger.debug("认证状态中文名："+response2.getAttachment().getAuthStatusName());
		logger.debug("认证步骤："+response2.getAttachment().getAuthStep());
		logger.debug("代理人认证："+response2.getAttachment().getDlrAuth());
		logger.debug("认证日期："+response2.getAttachment().getAuthDate());
		logger.debug("认证错误次数：" + response2.getAttachment().getErrorTimes());
		assertTrue(response2.isSuccess());
	}
	
	*//**
	 * 测试  3.33	企业客户撤销实名认证接口
	 * @author zhanwx
	 * @date 2014年7月27日 上午10:25:25 
	 * @since 0.1
	 * @see
	 *//*
	@Test
	public void testEnterpriselAuCancel(){
		AttachmentResponse<String> response = portalService.enterpriselAuCancel(null);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		AttachmentResponse<String> response1 = portalService.enterpriselAuCancel(26L);
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		assertFalse(response1.isSuccess());
		AttachmentResponse<String> response2 = portalService.enterpriselAuCancel(214L);
		logger.debug("状态码：" + response2.getReturnCode().toString());
		logger.debug("状态信息：" + response2.getReturnMsg());
		assertTrue(response2.isSuccess());
		}
	
	*//**
	 * 测试 3.34	实名认证人工审核接口
	 * @author zhanwx
	 * @date 2014年7月27日 上午10:06:23 
	 * @since 0.1
	 * @see
	 *//*
	@Test
	public void testEnterpriselAuApply(){
		
		AttachmentResponse<String> response = portalService.enterpriselAuApply(null,null);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		AttachmentResponse<String> response1 = portalService.enterpriselAuApply(26L,"PC");
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		assertFalse(response1.isSuccess());
		AttachmentResponse<String> response2 = portalService.enterpriselAuApply(36L,"PC");
		logger.debug("状态码：" + response2.getReturnCode().toString());
		logger.debug("状态信息：" + response2.getReturnMsg());
		assertTrue(response2.isSuccess());
		AttachmentResponse<String> response3 = portalService.enterpriselAuApply(214L,"PC");
		logger.debug("状态码：" + response3.getReturnCode().toString());
		logger.debug("状态信息：" + response3.getReturnMsg());
		assertTrue(response3.isSuccess());
		
		}
	
	
	*//**
	 * 测试 3.35	个人客户基本信息查询接口
	 * @author zhanwx
	 * @date 2014年7月27日 上午9:34:17 
	 * @since 0.1
	 * @see
	 *//*
	@Test
	public void testGetUserBasicInfo(){
		AttachmentResponse<UserBasicInfoResponse> t = portalService.getUserBasicInfo(438L);
		logger.debug("状态码：" + t.getReturnCode().toString());
		logger.debug("状态信息：" + t.getReturnMsg());
		logger.debug(t.getAttachment().getAuthCertTypeName());
		logger.debug(t.getAttachment().getAvailableBalance().toString());
		logger.debug(t.getAttachment().getLastLoginTime().toString());
		logger.debug(t.getAttachment().getBankName());
		logger.debug(String.valueOf(t.getAttachment().isBalanceStatus()));
		logger.debug(String.valueOf(t.getAttachment().isMessageStatus()));
		assertTrue(t.isSuccess());
		
	}
	
	*//**
	 * 测试 3.36添加银行卡接口
	 * @author zhanwx
	 * @date 2014年7月25日 下午7:25:05 
	 * @since 0.1
	 * @see
	 *//*
	@Test
	public void testaddBankCard(){
		UserBandBankInfo ubb = new UserBandBankInfo();
		ubb.setUserId(220L);
		ubb.setCreateDatetime(new Date());
		AttachmentResponse<String> response = portalService.addBankCard(null);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		AttachmentResponse<String> response2 = portalService.addBankCard(ubb);
		logger.debug("状态码：" + response2.getReturnCode().toString());
		logger.debug("状态信息：" + response2.getReturnMsg());
		assertTrue(response2.isSuccess());
	}
	
	
	*//**
	 * 测试 3.37	修改银行卡信息接口
	 * @author zhanwx
	 * @date 2014年7月25日 下午7:18:54 
	 * @since 0.1
	 * @see
	 *//*
	@Test
	public void testUpdateBankCard(){
		UserBandBankInfo ubb = new UserBandBankInfo();
		ubb.setId(81L);
		ubb.setUserId(222L);
		ubb.setUpdateDatetime(new Date());
		ubb.setBankAccountNo("9555500214035056000");
		ubb.setBankId("1234567");
		AttachmentResponse<String> response = portalService.updateBankCard(ubb);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		AttachmentResponse<String> response2 = portalService.updateBankCard(ubb);
		logger.debug("状态码：" + response2.getReturnCode().toString());
		logger.debug("状态信息：" + response2.getReturnMsg());
		assertTrue(response2.isSuccess());
	}
	
	
	*//**
	 * 测试 3.38	删除银行卡接口
	 * @author zhanwx
	 * @date 2014年7月25日 下午7:11:23 
	 * @since 0.1
	 * @see
	 *//*
	@Test
	public void testDeleteBankCard(){
		AttachmentResponse<String> response = portalService.deleteBankCard(1L,null,null);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		AttachmentResponse<String> response1 = portalService.deleteBankCard(null,null,null);
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		assertFalse(response1.isSuccess());
		AttachmentResponse<String> response2 = portalService.deleteBankCard(1111L,1111L,"PC");
		logger.debug("状态码：" + response2.getReturnCode().toString());
		logger.debug("状态信息：" + response2.getReturnMsg());
		assertTrue(response2.isSuccess());
	}
	
	*//**
	 * 测试 3.39	设置提现银行卡接口
	 * @author zhanwx
	 * @date 2014年7月25日 下午7:07:33 
	 * @since 0.1
	 * @see
	 *//*
	@Test
	public void testSetBankCardWithdrawals(){
		AttachmentResponse<String> response = portalService.setBankCardWithdrawals(0L,0L,"PC");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		AttachmentResponse<String> response1 = portalService.setBankCardWithdrawals(null,null,null);
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		assertFalse(response1.isSuccess());
		AttachmentResponse<String> response2 = portalService.setBankCardWithdrawals(0L,69L,"PC");
		logger.debug("状态码：" + response2.getReturnCode().toString());
		logger.debug("状态信息：" + response2.getReturnMsg());
		assertTrue(response2.isSuccess());
	}
	
	*//**
	 * 测试 3.40	安全问题是否匹配接口
	 * @author zhanwx
	 * @date 2014年7月25日 下午6:47:02 
	 * @since 0.1
	 * @see
	 *//*
	@Test
	public void testCheckSafeQuestionIsOk(){
		AttachmentResponse<String> response = portalService.checkSafeQuestionIsOk(0L,null,null);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		AttachmentResponse<String> response1 = portalService.checkSafeQuestionIsOk(null,null,null);
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		assertFalse(response1.isSuccess());
		AttachmentResponse<String> response2 = portalService.checkSafeQuestionIsOk(1L,"1","你好");
		logger.debug("状态码：" + response2.getReturnCode().toString());
		logger.debug("状态信息：" + response2.getReturnMsg());
		assertFalse(response2.isSuccess());
		AttachmentResponse<String> response4 = portalService.checkSafeQuestionIsOk(249L,"157","120");
		logger.debug("状态码：" + response4.getReturnCode().toString());
		logger.debug("状态信息：" + response4.getReturnMsg());
		assertTrue(response4.isSuccess());
	}
	
	*//**
	 * 测试 3.41余额支付功能开通关闭接口
	 * @author zhanwx
	 * @date 2014年7月25日 下午6:33:35 
	 * @since 0.1
	 * @see
	 *//*
	@Test
	public void testBalancePayOpenOrClose(){
		AttachmentResponse<String> response = portalService.balancePayOpenOrClose(15L,null,null);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		AttachmentResponse<String> response1 = portalService.balancePayOpenOrClose(212L,null,null);
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		assertFalse(response1.isSuccess());
		AttachmentResponse<String> response2 = portalService.balancePayOpenOrClose(212L,"0","PC");
		logger.debug("状态码：" + response2.getReturnCode().toString());
		logger.debug("状态信息：" + response2.getReturnMsg());
		assertTrue(response2.isSuccess());
	}
	
	*//**
	 * 测试 3.42短信提醒功能开通关闭接口
	 * @author zhanwx
	 * @date 2014年7月25日 下午6:20:19 
	 * @since 0.1
	 * @see
	 *//*
	@Test
	public void testSmsAlertsOpenOrClose(){
		AttachmentResponse<String> response = portalService.smsAlertsOpenOrClose(15L,null,null);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		AttachmentResponse<String> response1 = portalService.smsAlertsOpenOrClose(null,null,null);
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		assertFalse(response1.isSuccess());
		AttachmentResponse<String> response2 = portalService.smsAlertsOpenOrClose(212L,"0","PC");
		logger.debug("状态码：" + response2.getReturnCode().toString());
		logger.debug("状态信息：" + response2.getReturnMsg());
		assertTrue(response2.isSuccess());
	}
	
	*//**
	 * 测试 3.43设置（修改）安全保护问题接口 
	 * @author zhanwx
	 * @date 2014年7月25日 下午5:48:55 
	 * @since 0.1
	 * @see
	 *//*
	@Test
	public void testSetSafeQuestion(){
		AttachmentResponse<String> response = portalService.setSafeQuestion(null,"1","我","2","很","3","好");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		AttachmentResponse<String> response1 = portalService.setSafeQuestion(0L,"1","我","2","很","3","好");
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		assertFalse(response1.isSuccess());
		AttachmentResponse<String> response2 = portalService.setSafeQuestion(212L,"12","伤","2","不","3","起");
		logger.debug("状态码：" + response2.getReturnCode().toString());
		logger.debug("状态信息：" + response2.getReturnMsg());
		assertTrue(response2.isSuccess());
	}
	
	*//**
	 * 测试 3.44查询是否有安全保护问题 方法
	 * @author zhanwx
	 * @date 2014年7月22日 下午2:48:55 
	 * @since 0.1
	 * @see
	 *//*
	@Test
	public void testSafeQuestionIsExist() {
		AttachmentResponse<Integer> response1 = portalService.safeQuestionIsExist(null);
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		assertFalse(response1.isSuccess());
		
		
		
		AttachmentResponse<Integer> response2 = portalService.safeQuestionIsExist(212L);
		logger.debug("状态码：" + response2.getReturnCode().toString());
		logger.debug("状态信息：" + response2.getReturnMsg());
		logger.debug("安全问题数量：" + response2.getAttachment());
		assertTrue(response2.isSuccess());
	}
	
	*//**
	 * 测试 3.45更新密码 方法
	 * @author zhanwx
	 * @date 2014年7月22日 上午11:15:10 
	 * @since 0.1
	 * @see
	 *//*
	@Test
	public void testUpdatePwd() {

		AttachmentResponse<String> response = portalService.updatePwd(5344L, "123456", null, "PC");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		AttachmentResponse<String> response1 = portalService.updatePwd(null,"521314", "支付", null);
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		assertFalse(response1.isSuccess());

		AttachmentResponse<String> response3 = portalService.updatePwd(5344L,"123123", "1", "PC");
		logger.debug("状态码：" + response3.getReturnCode().toString());
		logger.debug("状态信息：" + response3.getReturnMsg());
		assertTrue(response3.isSuccess());
	}
	
	*//**
	 * 测试 3.46更新预留信息 方法
	 * @author zhanwx
	 * @date 2014年7月21日 下午5:31:49 
	 * @since 0.1
	 * @see
	 *//*
	@Test
	public void testReserveMessageUpdate() {
		AttachmentResponse<String> response = portalService.reserveMessageUpdate(1L,"更新后的预留信息","PC");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		AttachmentResponse<String> response1 = portalService.reserveMessageUpdate(null,"更新后的预留信息","PC");
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		assertFalse(response1.isSuccess());
		AttachmentResponse<String> response2 = portalService.reserveMessageUpdate(46L,null,"PC");
		logger.debug("状态码：" + response2.getReturnCode().toString());
		logger.debug("状态信息：" + response2.getReturnMsg());
		assertFalse(response2.isSuccess());
		AttachmentResponse<String> response3 = portalService.reserveMessageUpdate(46L,"更新后的预留信息",null);
		logger.debug("状态码：" + response3.getReturnCode().toString());
		logger.debug("状态信息：" + response3.getReturnMsg());
		assertFalse(response3.isSuccess());
		AttachmentResponse<String> response4 = portalService.reserveMessageUpdate(46L,"更新后的预留信息","PC");
		logger.debug("状态码：" + response4.getReturnCode().toString());
		logger.debug("状态信息：" + response4.getReturnMsg());
		assertTrue(response4.isSuccess());
	}
	
	*//**
	 * 测试 3.47查询用户安全问题列表 方法
	 * @author zhanwx
	 * @date 2014年7月21日 下午4:51:49 
	 * @since 0.1
	 * @see
	 *//*
	@Test
	public void testGetQuestion() {
		AttachmentResponse<List<UserQuestionInfo>> response = portalService.getQuestion(37L);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		AttachmentResponse<List<UserQuestionInfo>> response1 = portalService.getQuestion(null);
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		assertFalse(response1.isSuccess());
		AttachmentResponse<List<UserQuestionInfo>> response2 = portalService.getQuestion(1L);
		logger.debug("状态码：" + response2.getReturnCode().toString());
		logger.debug("状态信息：" + response2.getReturnMsg());
		assertTrue(response2.isSuccess());
	}
	*//**
	 * 测试 3.97获取提现银行卡列表
	 * @author zhanwx
	 * @date 2014年7月28日 下午5:30:18 
	 * @since 0.1
	 * @see
	 *//*
	@Test
	public void testGetBankCard(){
		AttachmentResponse<List<UserBandBankInfo>> response = portalService.getBankCard(null,null,null);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		AttachmentResponse<List<UserBandBankInfo>> response1 = portalService.getBankCard(0L, "1", "1");
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		assertFalse(response1.isSuccess());
		AttachmentResponse<List<UserBandBankInfo>> response2 = portalService.getBankCard(118L, "1", "2");
		logger.debug("状态码：" + response2.getReturnCode().toString());
		logger.debug("状态信息：" + response2.getReturnMsg());
		assertTrue(response2.isSuccess());
	}
	
	*//**
	 * 测试 3.100 按Id查询用户绑定银行卡对象
	 * @author zhanwx
	 * @date 2014年7月31日 上午9:31:02 
	 * @since 0.1
	 * @see
	 *//*
	@Test
	public void testGetUserBandBankInfo(){
		AttachmentResponse<UserBandBankInfo> response = portalService.getUserBandBankInfo(0L);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		AttachmentResponse<UserBandBankInfo> response1 = portalService.getUserBandBankInfo(null);
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		assertFalse(response1.isSuccess());

		AttachmentResponse<UserBandBankInfo> response2 = portalService.getUserBandBankInfo(9L);
		logger.debug("状态码：" + response2.getReturnCode().toString());
		logger.debug("状态信息：" + response2.getReturnMsg());
		assertTrue(response2.isSuccess());
	}
	
	
	*//**
	 * 测试安全问题模块
	 * @author zhanwx
	 * @date 2014年7月30日 下午3:48:44 
	 * @since 0.1
	 * @see
	 *//*
	@Test
	public void testQuestion(){
		//用户登录
		AttachmentResponse<LoginResponse> login = portalService.login("187011234", "521314", "125.123.2.1", "D3G4G6J5G4H3G4", "PC");
		logger.debug("状态码:" + login.getReturnCode().toString());
		logger.debug("状态信息:" + login.getReturnMsg());
		assertTrue(login.isSuccess());
		Long userId = login.getAttachment().getPersonalUser().getUserId();
		//查询是否有安全问题
		AttachmentResponse<Integer> isExist = portalService.safeQuestionIsExist(userId);
		if(isExist.getReturnCode() == 0){
			//存在安全问题
			logger.debug("状态码:" + isExist.getReturnCode().toString());
			logger.debug("状态信息:" + isExist.getReturnMsg());
			assertTrue(isExist.isSuccess());
			//返回安全问题列表
			AttachmentResponse<List<UserQuestionInfo>> qlist = portalService.getQuestion(userId);
			logger.debug("状态码:" + qlist.getReturnCode().toString());
			logger.debug("状态信息:" + qlist.getReturnMsg());
			assertTrue(qlist.isSuccess());
			//检查安全问题是否匹配
			int length = qlist.getAttachment().size();
			AttachmentResponse<String> question = null;
			String[] qid = {"1","2","3"};
			String[] ans = {"你","好","吗"};
			Boolean flag = true;
			for(int i = 0; i < length;i++ ){
				question = portalService.checkSafeQuestionIsOk(userId, qid[i],ans[i]);
				if(question.getReturnCode() == 1406 || question.getReturnCode() == 1408){
					flag = false;
					break;
				}
			}
			if(flag == false){
				logger.debug("状态码:1408" );
				logger.debug("状态信息:安全问题答案输入不正确" );
				assertFalse(question.isSuccess());
			}
			else{
				logger.debug("状态码:" + question.getReturnCode().toString());
				logger.debug("状态消息:" + question.getReturnMsg());
				assertTrue(question.isSuccess());
			}
			
			
		}
		else{
			AttachmentResponse<String> setQt = portalService.setSafeQuestion(userId, "1","我","2","很","3","好");
			logger.debug("状态码:" + setQt.getReturnCode().toString());
			logger.debug("状态消息:" + setQt.getReturnMsg());
			assertTrue(setQt.isSuccess());
		}
	}
	
	@Test
	public void testAddBankCard() {
		String nationality = DictionaryUtils.getStringValue(DictionaryKey.COUNTRY_CODE_CN);
		String name = "吴此人";
		String email = "nobody@cssweb.com.cn";
		String regType = DictionaryUtils.getStringValue(DictionaryKey.REG_TYPE_EMAIL);
		String source = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);
		String cardType = DictionaryUtils.getStringValue(DictionaryKey.ID_TYPE_IDCARD);
		String cardId = "1111111111111111111111";
		String loginPwd = "loginPwd";
		String payPwd = "payPwd";
		int questionId = DictionaryUtils.getIntegerValue(DictionaryKey.SECURITY_ISSUES_ONE);
		String answer = "魏知树";
		
		String bankId = "111111";
		String bankId2 = "222222";
		
		
		//用户登录
		logger.debug("用户登录");
		AttachmentResponse<LoginResponse> loginRes = portalService.login(email, loginPwd, "192.168.5.123", "AB:CD:EF:GH", source);
		logger.debug("状态码：" + loginRes.getReturnCode());
		logger.debug("状态信息：" + loginRes.getReturnMsg());
		assertTrue(loginRes.isSuccess());
		Long userId;
		if (loginRes.getAttachment().getUserType().equals(DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL))) {
			userId = loginRes.getAttachment().getPersonalUser().getUserId();
		} else {
			assertTrue(loginRes.getAttachment().getUserType().equals(DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_ENTERPRISE)));
			userId = loginRes.getAttachment().getEnterpriseUser().getUserId();
		}

		
		//获取绑定银行卡列表
		logger.debug("获取绑定银行卡列表");
		AttachmentResponse<List<AppGetUserBindingBankListPara>> listRes = mobileService.getUserBindingBankList(userId);
		logger.debug("状态码：" + listRes.getReturnCode());
		logger.debug("状态信息：" + listRes.getReturnMsg());
		assertTrue(listRes.isSuccess());
		assertTrue(listRes.getAttachment().size() == 1);
		//修改银行卡
		logger.debug("修改银行卡");
		UserBandBankInfo update = new UserBandBankInfo();
		AppGetUserBindingBankListPara appBankCard = listRes.getAttachment().get(0);
		update.setId(appBankCard.getId());
		update.setReserve1("修改银行卡成功");
		AttachmentResponse<String>response = portalService.updateBankCard(update);
		logger.debug("状态码：" + response.getReturnCode());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());

	}
	
	@Test	//测试用户打款认证流程
	public void testUserAuth() {
		//注册信息
		String name = "吴此人";
		String email = "nobody@cssweb.com.cn";
		String enterpriseEmail = "enterprise@cssweb.com.cn";
		String cardId = "1111111111111111111111";
		String loginPwd = "loginPwd";
		String payPwd = "payPwd";
		String answer = "魏知树";
		
		//登录信息
		String ip = "192.168.5.123";
		String mac = "AB:CD:EF:GH";
		
		//打款信息
		String bankId = "111111";
		String bankName = "神马银行";
		String bankCardNo = "6222000000000111111111";
		
		String bankId2 = "222222";
		String bankCardNo2 = "6222000000000222222222";
		
		String source = DictionaryUtils.getStringValue(DictionaryKey.SOURCE_PC);

		//用户登录
		logger.debug("用户登录");
		AttachmentResponse<LoginResponse> loginRes = portalService.login(enterpriseEmail, loginPwd, ip, mac, source);
		logger.debug("状态码：" + loginRes.getReturnCode());
		logger.debug("状态信息：" + loginRes.getReturnMsg());
		assertTrue(loginRes.isSuccess());
		Long userId;
		if (loginRes.getAttachment().getUserType().equals(DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_PERSONAL))) {
			userId = loginRes.getAttachment().getPersonalUser().getUserId();
		} else {
			assertTrue(loginRes.getAttachment().getUserType().equals(DictionaryUtils.getStringValue(DictionaryKey.USER_TYPE_ENTERPRISE)));
			userId = loginRes.getAttachment().getEnterpriseUser().getUserId();
			EnterpriseUser enterpriseUser = loginRes.getAttachment().getEnterpriseUser();
			//实名认证申请，现在只有企业用户有这个接口
			logger.debug("企业用户实名认证申请");
			AttachmentResponse<String> response = portalService.enterpriselAu(enterpriseUser,null);
			logger.debug("状态码：" + response.getReturnCode());
			logger.debug("状态信息：" + response.getReturnMsg());
			assertTrue(response.isSuccess());
			//人工审核
			logger.debug("人工审核");
			response = portalService.enterpriselAuApply(userId, source);
			logger.debug("状态码：" + response.getReturnCode());
			logger.debug("状态信息：" + response.getReturnMsg());
			assertTrue(response.isSuccess());
			//打款申请
			logger.debug("打款申请");
			AttachmentResponse<ApplyMoneyResponse> res2 = tradeService.applyMoney(userId, new BigDecimal("0.02"),"50", bankName, bankCardNo, bankId,"123", "098762789","我在测试...");
			logger.debug("状态码：" + res2.getReturnCode());
			logger.debug("状态信息：" + res2.getReturnMsg());
			assertTrue(res2.isSuccess());
			//用户撤消认证
			logger.debug("用户撤消认证");
			response = portalService.enterpriselAuCancel(userId);
			logger.debug("状态码：" + response.getReturnCode());
			logger.debug("状态信息：" + response.getReturnMsg());
			assertFalse(response.isSuccess());
			//确认打款金额
			logger.debug("确认打款金额");
			response = portalService.bankAu(userId, bankId, bankCardNo, new BigDecimal("0.02"), source);
			logger.debug("状态码：" + response.getReturnCode());
			logger.debug("状态信息：" + response.getReturnMsg());
			assertTrue(response.isSuccess());
		}

	}
	
	*//**
	 * 查询指定账户充值总金额接口测试
	 * @author zhanwx
	 * @date 2014年8月18日 上午11:10:36 
	 * @since 0.1
	 * @see
	 *//*
	@Test
	public void testSelectTotalBalance(){
		AttachmentResponse<BigDecimal> r1 = portalService.selectTotalBalance(null,null,null,null);
		logger.debug("状态码：" + r1.getReturnCode().toString());
		logger.debug("状态信息：" + r1.getReturnMsg());
		assertFalse(r1.isSuccess());
		AttachmentResponse<BigDecimal> r2 = portalService.selectTotalBalance(0L,"3",new Date(2014,2,5),new Date(2014,3,5));
		logger.debug("状态码：" + r2.getReturnCode().toString());
		logger.debug("状态信息：" + r2.getReturnMsg());
		logger.debug("充值总金额：" + r2.getAttachment());
		assertTrue(r2.isSuccess());
		AttachmentResponse<BigDecimal> r4 = portalService.selectTotalBalance(722L,"5",new Date(114,7,14),new Date(114,7,19));
		logger.debug("状态码：" + r4.getReturnCode().toString());
		logger.debug("状态信息：" + r4.getReturnMsg());
		logger.debug("提现总金额：" + r4.getAttachment());
		assertTrue(r4.isSuccess());	
	}
	*//**
	 * 测试解绑
	 *//*
	@Test
	public void testUnbindingMobilePhone(){

		AttachmentResponse response = portalService.unbindingMobilePhone(214L);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
	}
	
	@Test
	public void testUnbindingEmail(){
		AttachmentResponse response = portalService.unbindingEmail(214L);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
	}
	
	
	*//**
	 * 测试个人关联实名认证
	 *//*
	@Test
	public void testPersonalAuthRelation(){

		AttachmentResponse response = portalService.personalAuthRelation("15555555557","111111","15021855442","111111","320923199007296916","mac");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
	}
	
	*//**
	 * 测试企业关联实名认证
	 *//*
	@Test
	public void testEnterpriseAuthRelation(){
		//AttachmentResponse response = portalService.enterpriseAuthRelation("hahah@sina.com","123123","flashxx@live.cn","123456","440306105052939","mac");
		AttachmentResponse response = portalService.enterpriseAuthRelation("2278513590@qq.com","111111","zhaoqh@cssweb.sh.cn","111111","440306105052939","mac");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
	}
	
	@Test
	public void testLoginNameSynchronous(){

		AttachmentResponse<String> response = portalService.loginNameSynchronous(212L, "01");
		logger.debug("状态码:" + response.getReturnCode().toString());
		logger.debug("状态信息:" + response.getReturnMsg());
		assertFalse(response.isSuccess());
	}
	
	@Test
	public void testCancalLoginName(){
		AttachmentResponse<String> res = portalService.cancalLoginName(212L, "01");
		logger.debug("状态码:"+res.getReturnCode().toString());
		logger.debug("状态消息:"+res.getReturnMsg());
		assertFalse(res.isSuccess());
	}
	
	
	@Test
	public void testGetStatus(){
		AttachmentResponse<StatusInfo> res = portalService.getStatus(212L);
		logger.debug("状态码:"+res.getReturnCode().toString());
		logger.debug("状态消息:"+res.getReturnMsg());
		logger.debug("------------------打印信息---------------------");
		logger.debug(res.getAttachment().getAuthStatus());
		logger.debug(res.getAttachment().getUserStatus());
		logger.debug(res.getAttachment().getMainAccountStatus());
		logger.debug("");
		logger.debug("");
		logger.debug("");
		assertTrue(res.isSuccess());
	}
	
	@Test
	public void testGetUserRelAuInfoList(){
		AttachmentResponse<List<GetUserRelAuInfoResponse>> res = portalService.getUserRelAuInfoList(213L);
		logger.debug("状态码:"+res.getReturnCode().toString());
		logger.debug("状态消息:"+res.getReturnMsg());
		assertFalse(res.isSuccess());
	}
	
	
	
	
	
	
	
	
	*//**
	 * 测试3.20	用户（个人）注册接口
	 * @author Ganlin
	 * @throws ParseException 
	 * @date 2014年7月25日 下午1:30:30 
	 * @since 0.1
	 *//*
	@Test
	public void testPersonalRegistration() {
		
		AttachmentResponse<Long> response;

		response = portalService.personalRegistration("156","88552200","王欢","885522000@qq.com",
				 "1", "1", "1", "20102100161", "loginpwd", "paypwd", 1, "太平");
		
		response = portalService.personalRegistration("156","13471830264","1","1",loginPwd);
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		logger.debug("Attachment：" + response.getAttachment());
		assertTrue(response.isSuccess());
	}
	
	
	@Test
	public void testUpdatePwd() {

		AttachmentResponse<String> response = portalService.updatePwd(5344L, "123456", null, "PC");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertFalse(response.isSuccess());
		AttachmentResponse<String> response1 = portalService.updatePwd(null,"521314", "支付", null);
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		assertFalse(response1.isSuccess());

		AttachmentResponse<String> response3 = portalService.updatePwd(5344L,"123123", "1", "PC");
		logger.debug("状态码：" + response3.getReturnCode().toString());
		logger.debug("状态信息：" + response3.getReturnMsg());
		assertTrue(response3.isSuccess());
		
		AttachmentResponse<String> response4 = portalService.updatePwd(460L,"123321", "2", "MOBILE");
		logger.debug("状态码：" + response4.getReturnCode().toString());
		logger.debug("状态信息：" + response4.getReturnMsg());
		assertTrue(response4.isSuccess());
	}
	
	@Test
	public void testVerifyTradePwd(){
	
		
		AttachmentResponse<Boolean> response1 = portalService.verifyTradePwd("11111111111", "123123");
		logger.debug("状态码：" + response1.getReturnCode().toString());
		logger.debug("状态信息：" + response1.getReturnMsg());
		assertTrue(response1.isSuccess());
		
		AttachmentResponse<Boolean> response = portalService.verifyTradePwd("13341830262", "123321");
		logger.debug("状态码：" + response.getReturnCode().toString());
		logger.debug("状态信息：" + response.getReturnMsg());
		assertTrue(response.isSuccess());
	}
	
	@Test
	public void testCheckTradePwdExists(){
		AttachmentResponse<Boolean> t = portalService.checkTradePwdExists("11111111111");
		logger.debug("状态码：" + t.getReturnCode().toString());
		logger.debug("状态信息：" + t.getReturnMsg());
		logger.debug("ss：" + t.getAttachment());
		assertTrue(t.isSuccess());
	}*/
	
	
	/*@Test
	public void testpersonalBankCardAu(){
		PersonalUser user=new PersonalUser();
		user.setUserId(213l);
		user.setAuthDescribe("通过审核啦。。。");
		user.setAuthStep("2");
		AttachmentResponse<String> t = portalService.personalBankCardAu(user);
		logger.debug("状态码：" + t.getReturnCode().toString());
		logger.debug("状态信息：" + t.getReturnMsg());
		assertTrue(t.isSuccess());
	}*/
	
	
	/*@Test
	public void testgetPersonalBankCardAuStatus(){
		PersonalUser user=new PersonalUser();
		user.setUserId(212l);
		AttachmentResponse<Boolean> t = portalService.getPersonalBankCardAuStatus(user);
		logger.debug("状态码：" + t.getReturnCode().toString());
		logger.debug("状态信息：" + t.getReturnMsg());
		logger.debug("是否绑定银行卡：" + t.getAttachment());
		assertTrue(t.isSuccess());
	}*/
	
	
	
	@Test
	public void testgetFinancialSummaryDayList() throws ParseException{
		FinancialSummaryDayQuery query=new FinancialSummaryDayQuery();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		query.setEnterId(1l);
		query.setPageSize(5);
		query.setPageNo(1);
		query.setBeginDate(sdf.parse("2015-02-01"));
		query.setEndDate(sdf.parse("2015-02-09"));
		AttachmentResponse<Page<FinancialSummaryDay>> t = portalService.getFinancialSummaryDayList(query);
		logger.debug("状态码：" + t.getReturnCode().toString());
		logger.debug("状态信息：" + t.getReturnMsg());
		logger.debug("getPageSize:"+t.getAttachment().getPageSize());
		logger.debug("getPageCount:"+t.getAttachment().getPageCount());
		logger.debug("getTotalCount:"+t.getAttachment().getTotalCount());
		Page<FinancialSummaryDay> aa=t.getAttachment();
		List<FinancialSummaryDay> list=aa.getData();
		for(int i=0;i<list.size();i++){
			FinancialSummaryDay day=list.get(i);
			logger.debug("day.getIncome()=====>"+day.getIncome());
			logger.debug("day.getday()=====>"+day.getDay());
		}
		assertTrue(t.isSuccess());
	}
	
	
	@Test
	public void testgetFinancialSummaryMonthList() throws ParseException{
		FinancialSummaryMonthQuery query=new FinancialSummaryMonthQuery();
		query.setEnterId(1l);
		query.setPageSize(5);
		query.setPageNo(1);
		//query.setYear(2015l);
		query.setMonth(201501l);
		AttachmentResponse<Page<FinancialSummaryMonth>> t = portalService.getFinancialSummaryMonthList(query);
		logger.debug("状态码：" + t.getReturnCode().toString());
		logger.debug("状态信息：" + t.getReturnMsg());
		logger.debug("getPageSize:"+t.getAttachment().getPageSize());
		logger.debug("getPageCount:"+t.getAttachment().getPageCount());
		logger.debug("getTotalCount:"+t.getAttachment().getTotalCount());
		Page<FinancialSummaryMonth> aa=t.getAttachment();
		List<FinancialSummaryMonth> list=aa.getData();
		for(int i=0;i<list.size();i++){
			FinancialSummaryMonth day=list.get(i);
			logger.debug("day.getIncome()=====>"+day.getIncome());
			logger.debug("day.getday()=====>"+day.getMonth());
		}
		assertTrue(t.isSuccess());
	}
}
