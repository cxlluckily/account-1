/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.util;

import javax.annotation.Resource;

import com.cssweb.payment.account.constant.DictionaryKey;
import com.cssweb.payment.account.constant.GlobalConstants;
import com.cssweb.payment.account.pojo.AccountInfo;
import com.cssweb.payment.account.pojo.AccountOperInfo;
import com.cssweb.payment.common.provider.sei.IIdGeneratorService;
import com.cssweb.payment.common.response.AttachmentResponse;
import com.cssweb.payment.common.util.ResponseUtils;
import com.cssweb.payment.omp.sei.IDictionaryService;

/**
 * 校验位计算工具
 * @author zhanwx
 * @version 0.1 (2014年8月21日 下午5:13:16)
 * @since 0.1
 * @see
 */
public class CreateCardNoUtils {

	/**
	 * 用Luhn算法生成第19位校验位
	 * @author zhanwx
	 * @date 2014年8月21日 下午5:53:46
	 * @param CardNo
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public static String createCheck(String CardNo){
		char[] codeArray = new char[19];
		int total = 0;
		int temp,check;
		if(18 == CardNo.length()){	
			codeArray = CardNo.toCharArray();
			for(int i = 0 ; i < codeArray.length; i++){
				if(i%2 == 0){
					temp = Integer.parseInt(codeArray[i]+"")*2;
					if(temp > 9){
						temp = temp - 9;
					}
					total = total+temp;	
				}
				if(i%2 == 1){
					total = total + Integer.parseInt(codeArray[i]+"");
				}
			}
			check = 10 - total%10;
			if(10 == check){
				return Integer.toString(0);
			}
			return Integer.toString(check);
		}
		else{
			return null;
		}
	}
}
