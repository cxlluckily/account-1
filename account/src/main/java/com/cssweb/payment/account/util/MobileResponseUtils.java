/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.util;

import com.cssweb.payment.account.constant.MobileConstants;
import com.cssweb.payment.common.response.AttachmentResponse;
import com.cssweb.payment.common.response.BasicResponse;
import com.cssweb.payment.common.spring.MessageAccessor;
import com.cssweb.payment.common.spring.WildcardResourceBundleMessageSource;

/**
 * 手机APP专用
 * <p>提供一系列创建返回值的静态方法。自动获取returnCode绑定的returnMsg。</P>
 * <p>此类依赖于messageSource，需在spring中配置{@link WildcardResourceBundleMessageSource}</P>
 * <p>在properties文件中，需按如下规范编写，如：</p>
 * <p>payment.response.code.0=业务处理成功</p>
 * <p>其中"payment.mobile.response.code."为固定前缀，后跟具体状态码。</p>
 * @author DuXiaohua
 * @version 1.3 (2014-7-16 下午7:45:24)
 * @since 1.3
 * @see MessageAccessor
 * @see WildcardResourceBundleMessageSource
 */
public class MobileResponseUtils {
	/**
	 * 固定前缀
	 */
	public static final String RESPONSE_MESSAGE_KEY_PREFIX = "payment.mobile.response.code.";
	
	/**
	 * 创建业务处理成功时的返回值。
	 * @author DuXiaohua
	 * @date 2014-7-17 上午12:01:45
	 * @return 可附加对象的返回值，
	 * 		   success属性为true，
	 *         returnCode属性为{@link MobileConstants#RESPONSE_CODE_SUCCESS}，
	 *         attachment为null。
	 * @since 1.3
	 */	
	public static <E> AttachmentResponse<E> buildSuccessResponse() {
		return buildSuccessResponse(null);
	}

	/**
	 * 创建业务处理成功时的返回值。
	 * @author DuXiaohua
	 * @date 2014-7-17 上午12:01:45
	 * @param attachment 附加对象
	 * @return 可附加对象的返回值，
	 * 		   success属性为true，
	 *         returnCode属性为{@link MobileConstants#RESPONSE_CODE_SUCCESS}，
	 * @since 1.3
	 */	
	public static <E> AttachmentResponse<E> buildSuccessResponse(E attachment) {
		return buildResponse(true, MobileConstants.RESPONSE_CODE_SUCCESS, attachment);
	}
	
	/**
	 * 创建程序异常时的返回值。
	 * @author DuXiaohua
	 * @date 2014-7-17 上午12:01:45
	 * @return 可附加对象的返回值，
	 * 		   success属性为false，
	 *         returnCode属性为{@link MobileConstants#RESPONSE_CODE_EXCEPTION}，
	 *         attachment为null。
	 * @since 1.3
	 */		
	public static <E> AttachmentResponse<E> buildExceptionResponse() {
		return buildExceptionResponse(null);
	}
	
	/**
	 * 创建程序异常时的返回值。
	 * @author DuXiaohua
	 * @date 2014-7-17 上午12:01:45
	 * @param attachment 附加对象
	 * @return 可附加对象的返回值，
	 * 		    success属性为false，
	 *          returnCode属性为{@link MobileConstants.RESPONSE_CODE_EXCEPTION}。
	 * @since 1.3
	 */	
	public static <E> AttachmentResponse<E> buildExceptionResponse(E attachment) {
		return buildFailureResponse(MobileConstants.RESPONSE_CODE_EXCEPTION, attachment);
	}

	/**
	 * 创建业务处理失败时的返回值。
	 * @author DuXiaohua
	 * @date 2014-7-17 上午12:01:45
	 * @param returnCode 状态码
	 * @return 可附加对象的返回值，success属性为false，attachment为null。
	 * @since 1.3
	 */
	public static <E> AttachmentResponse<E> buildFailureResponse(Integer returnCode) {
		return buildFailureResponse(returnCode, null, null);
	}
	
	/**
	 * 创建业务处理失败时的返回值。
	 * @author DuXiaohua
	 * @date 2014-7-17 上午12:01:45
	 * @param returnCode 状态码
	 * @param args 资源文件参数
	 * @return 可附加对象的返回值，success属性为false，attachment为null。
	 * @since 1.3
	 */
	public static <E> AttachmentResponse<E> buildFailureResponse(Integer returnCode, Object[] args) {
		return buildFailureResponse(returnCode, args, null);
	}
	
	/**
	 * 创建业务处理失败时的返回值。
	 * @author DuXiaohua
	 * @date 2014-7-17 上午12:01:45
	 * @param returnCode 状态码
	 * @param attachment 附加对象
	 * @return 可附加对象的返回值，success属性为false。
	 * @since 1.3
	 */
	public static <E> AttachmentResponse<E> buildFailureResponse(Integer returnCode, E attachment) {
		return buildResponse(false, returnCode, attachment);
	}
	
	/**
	 * 创建业务处理失败时的返回值。
	 * @author DuXiaohua
	 * @date 2014-7-17 上午12:01:45
	 * @param returnCode 状态码
	 * @param args 资源文件参数
	 * @param attachment 附加对象
	 * @return 可附加对象的返回值，success属性为false。
	 * @since 1.3
	 */
	public static <E> AttachmentResponse<E> buildFailureResponse(Integer returnCode, Object[] args, E attachment) {
		return buildResponse(false, returnCode, args, attachment);
	}

	/**
	 * 创建可附加对象的返回值
	 * 
	 * @author DuXiaohua
	 * @date 2014-7-16 下午11:58:45
	 * @param success 业务是否处理成功
	 * @param returnCode 状态码
	 * @param attachment 附加对象
	 * @return 可附加对象的返回值
	 * @since 1.3
	 */
	public static <E> AttachmentResponse<E> buildResponse(boolean success, Integer returnCode, E attachment) {
		String returnMsg = MessageAccessor.getMessage(RESPONSE_MESSAGE_KEY_PREFIX + returnCode, "");
		return buildResponse(success, returnCode, returnMsg, attachment);
	}
	
	/**
	 * 创建可附加对象的返回值
	 * 
	 * @author DuXiaohua
	 * @date 2014-7-16 下午11:58:45
	 * @param success 业务是否处理成功
	 * @param returnCode 状态码
	 * @param args 资源文件中的参数值
	 * @param attachment 附加对象
	 * @return 可附加对象的返回值
	 * @since 1.3
	 */
	public static <E> AttachmentResponse<E> buildResponse(boolean success, Integer returnCode, Object[] args, E attachment) {
		String returnMsg = MessageAccessor.getMessage(RESPONSE_MESSAGE_KEY_PREFIX + returnCode, args, "");
		return buildResponse(success, returnCode, returnMsg, attachment);
	}
	
	/**
	 * 创建可附加对象的返回值
	 * 
	 * @author DuXiaohua
	 * @date 2014-7-16 下午11:58:45
	 * @param success 业务是否处理成功
	 * @param returnCode 状态码
	 * @param returnMsg 状态信息
	 * @param attachment 附加对象
	 * @return 可附加对象的返回值
	 * @since 1.3
	 */
	public static <E> AttachmentResponse<E> buildResponse(boolean success, Integer returnCode, String returnMsg, E attachment) {
		AttachmentResponse<E> response = new AttachmentResponse<E>();
		response.setSuccess(success);
		response.setReturnCode(returnCode);
		response.setAttachment(attachment);
		response.setReturnMsg(returnMsg);
		return response;
	}
	
	/**
	 * 将BasicResponse对象转换为AttachmentResponse对象。
	 * 只设置returnCode、returnMsg、success属性
	 * 
	 * @author DuXiaohua
	 * @date 2014年7月24日 下午2:12:32
	 * @since 1.0
	 */
	public static <E> AttachmentResponse<E> buildResponse(BasicResponse basicResponse) {
		AttachmentResponse<E> response = new AttachmentResponse<E>();
		response.setSuccess(basicResponse.isSuccess());
		response.setReturnCode(basicResponse.getReturnCode());
		response.setReturnMsg(basicResponse.getReturnMsg());
		return response;
	}
}
