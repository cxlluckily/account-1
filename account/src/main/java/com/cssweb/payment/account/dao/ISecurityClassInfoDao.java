/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.dao;
import java.util.List;

import com.cssweb.payment.account.pojo.SecurityClassInfo;
/**
 * @author zhanwx
 * @version 0.1 (2014年8月13日 下午2:52:56)
 * @since 0.1
 * @see
 */
public interface ISecurityClassInfoDao {
	/**
	 * 插入记录
	 * @author zhanwx
	 * @date 2014年8月13日 下午2:56:13
	 * @param sci 
	 * @since 0.1
	 * @see
	 */
	public void insert(SecurityClassInfo sci);
	/**
	 * 更新记录
	 * @author zhanwx
	 * @date 2014年8月13日 下午2:56:32
	 * @param sci 
	 * @since 0.1
	 * @see
	 */
	public void update(SecurityClassInfo sci);
	/**
	 * 查询单条记录
	 * @author zhanwx
	 * @date 2014年8月13日 下午2:56:45
	 * @param sci
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public SecurityClassInfo selectOne(SecurityClassInfo sci);
	/**
	 * 查询多条记录
	 * @author zhanwx
	 * @date 2014年8月13日 下午2:56:57
	 * @param sci
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public List<SecurityClassInfo> selectList(SecurityClassInfo sci);
	/**
	 * 查询记录数
	 * @author zhanwx
	 * @date 2014年8月13日 下午2:57:22
	 * @param sci
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public int selectCount(SecurityClassInfo sci);
}
