/*
 * Copyright(C) 2014,  CSSWEB TECHNOLOGY CO.,LTD.
 * All rights reserved.
 *
 */

package com.cssweb.payment.account.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.cssweb.payment.account.dao.ISecurityClassInfoDao;
import com.cssweb.payment.account.pojo.SecurityClassInfo;
import com.cssweb.payment.common.ibatis.BaseDao;
/**
 * @author zhanwx
 * @version 0.1 (2014年8月13日 下午2:58:05)
 * @since 0.1
 * @see
 */
@Repository
public class SecurityClassInfoImpl extends BaseDao<SecurityClassInfo> implements ISecurityClassInfoDao{
	
	public final static String CLASSNAME = SecurityClassInfo.class.getName();
	public static final String STATEMENT_INSERT = CLASSNAME + ".insert";
	public static final String STATEMENT_UPDATE = CLASSNAME + ".update";
	public static final String STATEMENT_SELECT = CLASSNAME + ".select";
	public static final String STATEMENT_SELECTLIST = CLASSNAME + ".selectList";
	public static final String STATEMENT_SELECTCOUNT = CLASSNAME + ".selectCount";
	/**
	 * 插入记录
	 * @param sci
	 * @return
	 * @author zhanwx
	 * @date 2014年8月13日 下午2:58:05
	 * @since 0.1
	 */
	@Override
	public void insert(SecurityClassInfo sci){
		this.getSqlMapClientTemplate().insert(STATEMENT_INSERT, sci);
	}
	/**
	 * 更新记录
	 * @param sci
	 * @return
	 * @author zhanwx
	 * @date 2014年8月13日 下午2:58:05
	 * @since 0.1
	 */
	@Override
	public void update(SecurityClassInfo sci){
		this.getSqlMapClientTemplate().update(STATEMENT_UPDATE, sci);
	}
	/**
	 * 查询记录总数
	 * @param sci
	 * @return
	 * @author zhanwx
	 * @date 2014年8月13日 下午2:58:05
	 * @since 0.1
	 */
	@Override
	public int selectCount(SecurityClassInfo sci){
		return (int) this.getSqlMapClientTemplate().queryForObject(STATEMENT_SELECTCOUNT, sci);
	}
	/**
	 * 单记录查询
	 * @param sci
	 * @return
	 * @author zhanwx
	 * @date 2014年8月13日 下午2:58:05
	 * @since 0.1
	 */
	@Override
	public SecurityClassInfo selectOne(SecurityClassInfo sci) {
		return (SecurityClassInfo) this.getSqlMapClientTemplate().queryForObject(STATEMENT_SELECT, sci);
	}
	/**
	 * 多记录查询
	 * @param sci
	 * @return
	 * @author zhanwx
	 * @date 2014年8月13日 下午2:58:05
	 * @since 0.1
	 */
	@Override
	public List<SecurityClassInfo> selectList(SecurityClassInfo sci){
		return (List<SecurityClassInfo>) this.getSqlMapClientTemplate().queryForList(STATEMENT_SELECTLIST, sci);
	}
	
}
