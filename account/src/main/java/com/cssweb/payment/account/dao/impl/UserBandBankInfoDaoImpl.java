package com.cssweb.payment.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cssweb.payment.account.dao.IUserBandBankInfoDao;
import com.cssweb.payment.account.pojo.UserBandBankInfo;
import com.cssweb.payment.account.vo.AppGetUserBindingBankListPara;
import com.cssweb.payment.common.ibatis.BaseDao;
/**
 * 用户绑定银行信息表操作
 * @author zhanwx
 * @version 0.1 (2014年7月23日 下午2:03:37)
 * @since 0.1
 * @see
 */
@Repository
public class UserBandBankInfoDaoImpl extends BaseDao<UserBandBankInfo> implements IUserBandBankInfoDao{

	public final static String CLASSNAME = UserBandBankInfo.class.getName();
	
	public static final String STATEMENT_INSERT = CLASSNAME + ".insert";
	public static final String STATEMENT_UPDATE = CLASSNAME + ".update";
	public static final String STATEMENT_UPDATEBYKEY = CLASSNAME + ".updateStatusByUseridAccno";
	public static final String STATEMENT_DELETE = CLASSNAME + ".delete";
	public static final String STATEMENT_SELECT = CLASSNAME + ".select";
	public static final String STATEMENT_SELECTLIST = CLASSNAME + ".selectList";
	public static final String STATEMENT_SELECTCOUNT = CLASSNAME + ".selectCount";
	public static final String STATEMENT_SELECTFULLINFO = CLASSNAME + ".selectFullInfo";
	
	/**
	 * 插入记录
	 * @param ub
	 * @return
	 * @author zhanwx
	 * @date 2014年7月23日 下午2:03:37
	 * @since 0.1
	 */
	@Override
	public void insert(UserBandBankInfo ub){
		this.getSqlMapClientTemplate().insert(STATEMENT_INSERT, ub);
	}
	/**
	 * 更新记录
	 * @param ub
	 * @return
	 * @author zhanwx
	 * @date 2014年7月23日 下午2:03:37
	 * @since 0.1
	 */
	@Override
	public void update(UserBandBankInfo ub){
		this.getSqlMapClientTemplate().update(STATEMENT_UPDATE,ub);
	}
	/**
	 * 删除记录
	 * @param ub
	 * @return
	 * @author zhanwx
	 * @date 2014年7月23日 下午2:03:37
	 * @since 0.1
	 */
	@Override
	public void delete(UserBandBankInfo ub){
		this.getSqlMapClientTemplate().delete(STATEMENT_DELETE, ub);
	}
	/**
	 * 查询记录列表
	 * @param ub
	 * @return
	 * @author zhanwx
	 * @date 2014年7月23日 下午2:03:37
	 * @since 0.1
	 */
	@Override
	public int selectCount(UserBandBankInfo ub){
		return (int) this.getSqlMapClientTemplate().queryForObject(STATEMENT_SELECTCOUNT, ub);
	}
	/**
	 * 单记录查询
	 * @param ub
	 * @return
	 * @author zhanwx
	 * @date 2014年7月23日 下午2:03:37
	 * @since 0.1
	 */
	@Override
	public UserBandBankInfo selectOne(UserBandBankInfo ub) {
		return (UserBandBankInfo) this.getSqlMapClientTemplate().queryForObject(STATEMENT_SELECT, ub);
	}
	/**
	 * 多记录查询
	 * @param ub
	 * @return
	 * @author zhanwx
	 * @date 2014年7月23日 下午2:03:37
	 * @since 0.1
	 */
	@Override
	public List<UserBandBankInfo> selectList(UserBandBankInfo ub){
		return (List<UserBandBankInfo>) this.getSqlMapClientTemplate().queryForList(STATEMENT_SELECTLIST, ub);
	}
	
	/**
	 * 通过用户ID和账户ID查询并更新记录
	 * @author zhaoxingwen
	 * @date 2014-07-30
	 */
	@Override
	public void updateByUseridAccountid(UserBandBankInfo ubb){
		this.getSqlMapClientTemplate().update(STATEMENT_UPDATEBYKEY,ubb);
	}
	
	/**
	 * 手机端查询所有绑定信息
	 * @author zhaoxingwen
	 * @date 2014-07-30
	 */
	@Override
	public List<AppGetUserBindingBankListPara> selectBandCardFullInfo(UserBandBankInfo ubb){
		return (List<AppGetUserBindingBankListPara>) this.getSqlMapClientTemplate().queryForList(STATEMENT_SELECTFULLINFO, ubb);
	}
}
