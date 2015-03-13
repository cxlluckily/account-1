/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 */
 

package com.cssweb.payment.account.util;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.cssweb.payment.common.response.AttachmentResponse;
import com.cssweb.payment.kms.sei.ISymService;
import com.cssweb.payment.kms.vo.SymResponseBean;

/**
 * @author shilei@cssweb.com.cn
 * @version 0.1 (2014年9月11日 下午5:37:08)
 * @since 0.1
 * @see
 */
@Component
public class SecurityUtils {
	
	@Resource
	private ISymService symService;
	private static Logger log = Logger.getRootLogger();

	
 
	
	
	 /***
	 * 
	  * 用MD5算法，对密码进行加密
	 * 
	 * @author shilei@cssweb.com.cn
	 * @date 2014年9月12日 上午9:55:06
	 * @param password  - 密码明文
	 * @param createDate
	 * @return
	 * @throws IllegalArgumentException - 传入的对象为空时抛出参数异常
	 * @throws Exception  -  加密机服务没有正常返回
	 * @since 0.1
	 * @see
	 */
	public  String encryptMD5(String password, Date createDate)
			throws IllegalArgumentException ,Exception {
		
		if(StringUtils.isEmpty(password) ){
			throw new IllegalArgumentException("密码明文是null，或者为空字符串");
		}
		if( createDate == null){
			throw new IllegalArgumentException("日期为null");
		}
		String currentDate = String.format("%1$TY-%1$Tm-%1$Td %1$tH:%1$tM:%1$tS", createDate);
		
		
		StringBuffer sb = new StringBuffer("{").append(currentDate.trim());
		sb.append(",").append(password.trim()).append("}");
		AttachmentResponse<SymResponseBean> response = symService
				.digestMD5(sb.toString().trim());
		if (!response.isSuccess()) {
			log.error("获取密文失败，请检查加密机服务,失败错误码为[" + response.getReturnCode()
					+ "],错误信息为：【" + response.getReturnMsg() + "】");
			throw new Exception("获取密文失败，请检查加密机服务");
		}
		return response.getAttachment().getResultData();
	} 
	/***
	 * 核对密码是否一致 ，一致返回ture   不一致返回false
	 * 
	 * @author shilei@cssweb.com.cn
	 * @date 2014年9月12日 上午10:33:25
	 * @param dataPWd  -  密文密码
	 * @param passWd   -  明文密码
	 * @param createDate - 账户创建日期
	 * @return
	 * @throws Exception  - 
	 * @since 0.1
	 * @see
	 */
	public boolean  checkPassWd(String dataPWd ,String passWd,Date createDate)throws Exception{
		try {
			String  resStr = encryptMD5(passWd, createDate).trim();
			return dataPWd.trim().equals(resStr);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public static void main(String[] args) {
		String currentDate = String.format("%1$TY-%1$Tm-%1$Td %1$tH:%1$tM:%1$tS %1$tL", new Date());
		System.out.println(currentDate);
		
	}
	 
	 
}
