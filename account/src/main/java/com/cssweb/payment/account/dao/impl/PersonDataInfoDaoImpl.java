/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.dao.impl;
import java.util.List;

import com.cssweb.payment.account.dao.IPersonDataInfo;
import com.cssweb.payment.account.pojo.PersonDataInfo;
import com.cssweb.payment.common.ibatis.BaseDao;

import org.springframework.stereotype.Repository;
/**
 * @author zhanwx
 * @version 0.1 (2014年8月22日 下午1:02:11)
 * @since 0.1
 * @see
 */
@Repository
public class PersonDataInfoDaoImpl extends BaseDao<PersonDataInfo> implements IPersonDataInfo{
	public final static String CLASSNAME = PersonDataInfo.class.getName();
	public static final String STATEMENT_INSERT = CLASSNAME + ".insert";
	public static final String STATEMENT_UPDATE = CLASSNAME + ".update";
	public static final String STATEMENT_SELECT = CLASSNAME + ".select";
	public static final String STATEMENT_SELECTLIST = CLASSNAME + ".selectList";
	public static final String STATEMENT_SELECTCOUNT = CLASSNAME + ".selectCount";
	
	/**
	 * 更新记录
	 * @param pdInfo
	 * @return
	 * @author zhanwx
	 * @date 2014年8月22日 下午1:07:48
	 * @since 0.1
	 */
	@Override
	public void insert(PersonDataInfo pdInfo){
		this.getSqlMapClientTemplate().insert(STATEMENT_INSERT,pdInfo);
	}
	
	/**
	 * 更新记录
	 * @param pdInfo
	 * @return
	 * @author zhanwx
	 * @date 2014年8月22日 下午1:07:48
	 * @since 0.1
	 */
	@Override
	public void update(PersonDataInfo pdInfo){
		this.getSqlMapClientTemplate().insert(STATEMENT_UPDATE,pdInfo);
	}
	
	/**
	 * 查询记录条数
	 * @param pdInfo
	 * @return
	 * @author zhanwx
	 * @date 2014年8月22日 下午1:07:48
	 * @since 0.1
	 */
	@Override
	public int selectCount(PersonDataInfo pdInfo){
		return (int)this.getSqlMapClientTemplate().insert(STATEMENT_SELECTCOUNT,pdInfo);
	}
	
	/**
	 * 查询多记录
	 * @param pdInfo
	 * @return
	 * @author zhanwx
	 * @date 2014年8月22日 下午1:07:48
	 * @since 0.1
	 */
	@Override
	public List<PersonDataInfo> selectList(PersonDataInfo pdInfo){
		return (List<PersonDataInfo>)this.getSqlMapClientTemplate().insert(STATEMENT_SELECTLIST,pdInfo);
	}
	
	/**
	 * 查询单记录
	 * @param pdInfo
	 * @return
	 * @author zhanwx
	 * @date 2014年8月22日 下午1:07:48
	 * @since 0.1
	 */
	@Override
	public PersonDataInfo selectOne(PersonDataInfo pdInfo){
		return (PersonDataInfo)this.getSqlMapClientTemplate().insert(STATEMENT_SELECT,pdInfo);
	}
}
