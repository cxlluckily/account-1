package com.cssweb.payment.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cssweb.payment.account.dao.IRateInfoDao;
import com.cssweb.payment.account.pojo.RateInfo;
import com.cssweb.payment.common.ibatis.BaseDao;

/**
 * 费率信息表操作
 * @author zhanwx
 * @version 0.1 (2014年7月23日 下午1:56:42)
 * @since 0.1
 * @see
 */
@Repository
public class RateInfoDaoImpl extends BaseDao<RateInfo> implements IRateInfoDao{

	public final static String CLASSNAME = RateInfo.class.getName();
	
	public static final String STATEMENT_INSERT = CLASSNAME + ".insert";
	public static final String STATEMENT_UPDATE = CLASSNAME + ".update";
	public static final String STATEMENT_SELECT = CLASSNAME + ".select";
	public static final String STATEMENT_SELECTLIST = CLASSNAME + ".selectList";
	public static final String STATEMENT_SELECTCOUNT = CLASSNAME + ".selectCount";
	
	
	/**
	 * 插入记录
	 * @param rateInfo
	 * @return
	 * @author zhanwx
	 * @date 2014年7月23日 下午1:56:42
	 * @since 0.1
	 */
	@Override
	public void insert(RateInfo rateInfo){
		this.getSqlMapClientTemplate().insert(STATEMENT_INSERT, rateInfo);
	}
	/**
	 * 更新记录
	 * @param rt
	 * @return
	 * @author zhanwx
	 * @date 2014年7月23日 下午1:56:42
	 * @since 0.1
	 */
	@Override
	public void update(RateInfo rt){
		this.getSqlMapClientTemplate().update(STATEMENT_UPDATE, rt);
	}
	/**
	 * 查询记录总数
	 * @param rt
	 * @return
	 * @author zhanwx
	 * @date 2014年7月23日 下午1:56:42
	 * @since 0.1
	 */
	@Override
	public int selectCount(RateInfo rt){
		return (int) this.getSqlMapClientTemplate().queryForObject(STATEMENT_SELECTCOUNT, rt);
	}
	/**
	 * 单记录查询
	 * @param rt
	 * @return
	 * @author zhanwx
	 * @date 2014年7月23日 下午1:56:42
	 * @since 0.1
	 */
	@Override
	public RateInfo selectOne(RateInfo rt) {
		return (RateInfo) this.getSqlMapClientTemplate().queryForObject(STATEMENT_SELECT, rt);
	}
	/**
	 * 多记录查询
	 * @param rt
	 * @return
	 * @author zhanwx
	 * @date 2014年7月23日 下午1:56:42
	 * @since 0.1
	 */
	@Override
	public List<RateInfo> selectList(RateInfo rt){
		return (List<RateInfo>) this.getSqlMapClientTemplate().queryForList(STATEMENT_SELECTLIST, rt);
	}
	
}
