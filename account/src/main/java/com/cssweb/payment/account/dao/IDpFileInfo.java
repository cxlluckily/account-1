/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.dao;
import java.util.List;

import com.cssweb.payment.account.pojo.DpFileInfo;
/**
 * @author zhanwx
 * @version 0.1 (2014年8月22日 下午12:57:57)
 * @since 0.1
 * @see
 */
public interface IDpFileInfo {
	/**
	 * 插入记录
	 * @author zhanwx
	 * @date 2014年8月22日 下午12:58:44
	 * @param dfInfo
	 * @since 0.1
	 * @see
	 */
	public void insert(DpFileInfo dfInfo);
	
	/**
	 * 更新记录
	 * @author zhanwx
	 * @date 2014年8月22日 下午12:58:44
	 * @param dfInfo
	 * @since 0.1
	 * @see
	 */
	public void update(DpFileInfo dfInfo);
	
	/**
	 * 查询单条记录
	 * @author zhanwx
	 * @date 2014年8月22日 下午12:58:44
	 * @param dfInfo
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public DpFileInfo selectOne(DpFileInfo dfInfo);
	
	/**
	 * 查询多条记录
	 * @author zhanwx
	 * @date 2014年8月22日 下午12:58:44
	 * @param dfInfo
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public List<DpFileInfo> selectList(DpFileInfo dfInfo);
	
	/**
	 * 查询记录条数
	 * @author zhanwx
	 * @date 2014年8月22日 下午12:58:44
	 * @param dfInfo
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public int selectCount(DpFileInfo dfInfo);
}
