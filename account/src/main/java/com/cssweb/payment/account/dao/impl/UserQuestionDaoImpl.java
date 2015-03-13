package com.cssweb.payment.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cssweb.payment.account.dao.IUserQuestionDao;
import com.cssweb.payment.account.pojo.UserQuestionInfo;
import com.cssweb.payment.common.ibatis.BaseDao;

/**
 * 用户安全问题表操作
 * @author zhanwx
 * @version 0.1 (2014年7月22日 下午2:20:18)
 * @since 0.1
 * @see
 */
@Repository
public class UserQuestionDaoImpl extends BaseDao<UserQuestionInfo> implements IUserQuestionDao{

	public final static String CLASSNAME = UserQuestionInfo.class.getName();
	
	public static final String STATEMENT_INSERT = CLASSNAME + ".insert";
	public static final String STATEMENT_UPDATE = CLASSNAME + ".update";
	public static final String STATEMENT_SELECT = CLASSNAME + ".select";
	public static final String STATEMENT_SELECTLIST = CLASSNAME + ".selectList";
	public static final String STATEMENT_SELECTCOUNT = CLASSNAME + ".selectCount";
	public static final String STATEMENT_DELETE = CLASSNAME + ".delete";
	
	/**
	 * 插入记录
	 * @param uq
	 * @author zhanwx
	 * @date 2014年7月22日 下午2:20:18
	 * @since 0.1
	 */
	@Override
	public void insert(UserQuestionInfo uq){
		this.getSqlMapClientTemplate().insert(STATEMENT_INSERT, uq);
	}
	/**
	 * 更新记录
	 * @param uq
	 * @author zhanwx
	 * @date 2014年7月22日 下午2:20:18
	 * @since 0.1
	 */
	@Override
	public void update(UserQuestionInfo uq){
		this.getSqlMapClientTemplate().update(STATEMENT_UPDATE, uq);
	}
	/**
	 * 记录总数查询
	 * @param uq
	 * @author zhanwx
	 * @date 2014年7月22日 下午2:20:18
	 * @since 0.1
	 */
	@Override
	public int selectCount(UserQuestionInfo uq){
		return (int) this.getSqlMapClientTemplate().queryForObject(STATEMENT_SELECTCOUNT, uq);
	}
	/**
	 * 单记录查询
	 * @param uq
	 * @author zhanwx
	 * @date 2014年7月22日 下午2:20:18
	 * @since 0.1
	 */
	@Override
	public UserQuestionInfo selectOne(UserQuestionInfo uq) {
		return (UserQuestionInfo) this.getSqlMapClientTemplate().queryForObject(STATEMENT_SELECT, uq);
	}
	/**
	 * 多记录查询
	 * @param uq
	 * @author zhanwx
	 * @date 2014年7月22日 下午2:20:18
	 * @since 0.1
	 */
	@Override
	public List<UserQuestionInfo> selectList(UserQuestionInfo uq){
		return (List<UserQuestionInfo>) this.getSqlMapClientTemplate().queryForList(STATEMENT_SELECTLIST, uq);
	}
	/**
	 * 删除记录
	 * @param uq
	 * @author zhanwx
	 * @date 2014年7月22日 下午2:20:18
	 * @since 0.1
	 */
	@Override
	public void delete(UserQuestionInfo uq) {
		this.getSqlMapClientTemplate().delete(STATEMENT_DELETE, uq);
	}
	
}
