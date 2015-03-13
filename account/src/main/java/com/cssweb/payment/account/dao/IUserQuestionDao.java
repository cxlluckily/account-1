/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.dao;

import java.util.List;

import com.cssweb.payment.account.pojo.UserQuestionInfo;

/**
 * @author zhanwx
 * @version 0.1 (2014年7月19日 下午5:41:53)
 * @since 0.1
 * @see
 */
public interface IUserQuestionDao {
	/**
	 * 插入记录
	 * @author zhanwx
	 * @date 2014年7月19日 上午11:28:37
	 * @param uq 
	 * @since 0.1
	 * @see
	 */
	public void insert(UserQuestionInfo uq);
	
	/**
	 * 更新记录
	 * @author zhanwx
	 * @date 2014年7月19日 上午11:29:19
	 * @param uq
	 * @since 0.1
	 * @see
	 */
	public void update(UserQuestionInfo uq);
	
	/**
	 * 删除记录
	 * @author wangpeng
	 * @date 2014年8月7日 上午11:29:19
	 * @param uq
	 * @since 0.1
	 * @see
	 */
	public void delete(UserQuestionInfo uq);
	
	
	/**
	 * 查询单条记录
	 * @author zhanwx
	 * @date 2014年7月19日 上午11:29:43
	 * @param uq
	 * @since 0.1
	 * @see
	 */
	public UserQuestionInfo  selectOne(UserQuestionInfo uq);
	
	/**
	 * 查询多条记录
	 * @author zhanwx
	 * @date 2014年7月19日 上午11:30:10
	 * @param uq
	 * @since 0.1
	 * @see
	 */
	public List<UserQuestionInfo> selectList(UserQuestionInfo uq);
	
	/**
	 * 查询记录条数
	 * @author zhanwx
	 * @date 2014年7月19日 上午11:30:45
	 * @param uq
	 * @since 0.1
	 * @see
	 */
	public int selectCount(UserQuestionInfo uq);
}
