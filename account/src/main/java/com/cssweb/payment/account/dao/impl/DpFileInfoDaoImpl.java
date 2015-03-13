/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.dao.impl;
import java.util.List;

import com.cssweb.payment.account.dao.IDpFileInfo;
import com.cssweb.payment.account.pojo.DpFileInfo;
import com.cssweb.payment.common.ibatis.BaseDao;

import org.springframework.stereotype.Repository;
/**
 * @author zhanwx
 * @version 0.1 (2014年8月22日 下午1:02:11)
 * @since 0.1
 * @see
 */
@Repository
public class DpFileInfoDaoImpl extends BaseDao<DpFileInfo> implements IDpFileInfo{
	public final static String CLASSNAME = DpFileInfo.class.getName();
	public static final String STATEMENT_INSERT = CLASSNAME + ".insert";
	public static final String STATEMENT_UPDATE = CLASSNAME + ".update";
	public static final String STATEMENT_SELECT = CLASSNAME + ".select";
	public static final String STATEMENT_SELECTLIST = CLASSNAME + ".selectList";
	public static final String STATEMENT_SELECTCOUNT = CLASSNAME + ".selectCount";
	
	/**
	 * 更新记录
	 * @param dpInfo
	 * @return
	 * @author zhanwx
	 * @date 2014年8月22日 下午1:07:48
	 * @since 0.1
	 */
	@Override
	public void insert(DpFileInfo dpInfo){
		this.getSqlMapClientTemplate().insert(STATEMENT_INSERT,dpInfo);
	}
	
	/**
	 * 更新记录
	 * @param dpInfo
	 * @return
	 * @author zhanwx
	 * @date 2014年8月22日 下午1:07:48
	 * @since 0.1
	 */
	@Override
	public void update(DpFileInfo dpInfo){
		this.getSqlMapClientTemplate().insert(STATEMENT_UPDATE,dpInfo);
	}
	
	/**
	 * 查询记录条数
	 * @param dpInfo
	 * @return
	 * @author zhanwx
	 * @date 2014年8月22日 下午1:07:48
	 * @since 0.1
	 */
	@Override
	public int selectCount(DpFileInfo dpInfo){
		return (int)this.getSqlMapClientTemplate().insert(STATEMENT_SELECTCOUNT,dpInfo);
	}
	
	/**
	 * 查询多记录
	 * @param dpInfo
	 * @return
	 * @author zhanwx
	 * @date 2014年8月22日 下午1:07:48
	 * @since 0.1
	 */
	@Override
	public List<DpFileInfo> selectList(DpFileInfo dpInfo){
		return (List<DpFileInfo>)this.getSqlMapClientTemplate().insert(STATEMENT_SELECTLIST,dpInfo);
	}
	
	/**
	 * 查询单记录
	 * @param dpInfo
	 * @return
	 * @author zhanwx
	 * @date 2014年8月22日 下午1:07:48
	 * @since 0.1
	 */
	@Override
	public DpFileInfo selectOne(DpFileInfo dpInfo){
		return (DpFileInfo)this.getSqlMapClientTemplate().insert(STATEMENT_SELECT,dpInfo);
	}
}
