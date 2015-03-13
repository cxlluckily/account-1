/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.dao;
import java.util.List;
import com.cssweb.payment.account.pojo.PersonDataInfo;
/**
 * @author zhanwx
 * @version 0.1 (2014年8月22日 下午12:54:44)
 * @since 0.1
 * @see
 */
public interface IPersonDataInfo {
	/**
	 * 插入记录
	 * @author zhanwx
	 * @date 2014年8月22日 下午12:54:44
	 * @param pdInfo
	 * @since 0.1
	 * @see
	 */
	public void insert(PersonDataInfo pdInfo);
	
	/**
	 * 更新记录
	 * @author zhanwx
	 * @date 2014年8月22日 下午12:54:44
	 * @param pdInfo
	 * @since 0.1
	 * @see
	 */
	public void update(PersonDataInfo pdInfo);
	
	/**
	 * 查询单条记录
	 * @author zhanwx
	 * @date 2014年8月22日 下午12:54:44
	 * @param pdInfo
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public PersonDataInfo selectOne(PersonDataInfo pdInfo);
	
	/**
	 * 查询多条记录
	 * @author zhanwx
	 * @date 2014年8月22日 下午12:54:44
	 * @param pdInfo
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public List<PersonDataInfo> selectList(PersonDataInfo pdInfo);
	
	/**
	 * 查询记录条数
	 * @author zhanwx
	 * @date 2014年8月22日 下午12:54:44
	 * @param pdInfo
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public int selectCount(PersonDataInfo pdInfo);
}
