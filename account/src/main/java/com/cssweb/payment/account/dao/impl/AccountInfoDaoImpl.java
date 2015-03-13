package com.cssweb.payment.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cssweb.payment.account.dao.IAccountInfoDao;
import com.cssweb.payment.account.pojo.AccountInfo;
import com.cssweb.payment.account.pojo.AccountMoneyChgDetail;
import com.cssweb.payment.account.pojo.PersonalUser;
import com.cssweb.payment.account.vo.AccountInfoQuery;
import com.cssweb.payment.account.vo.PersonalUserQuery;
import com.cssweb.payment.common.ibatis.BaseDao;
import com.cssweb.payment.common.util.Page;


/**
 * Title: AccountInfoDaoImpl
 * Description: 
 * Company: China Software & Service Company
 * Project:payment-account
 * @author liLei
 * @version 1.0
 * @date 2014年7月23日 下午4:06:05
 * @see 
 */
@Repository
public class AccountInfoDaoImpl extends BaseDao<AccountInfo> implements
		IAccountInfoDao {
	public static final String CLASS_NAME = AccountInfo.class.getName();
	
	/**
	 * 插入记录
	 * @param1 AccountInfo param
	 * @return
	 * @author liLei
	 * @date 2014年7月23日 下午4:06:05
	 * @since 0.1
	 */
	@Override
	public void insert(AccountInfo param) {
		getSqlMapClientTemplate().insert(CLASS_NAME + ".insert",param);
	}

	/**
	 * 更新记录
	 * @param1 AccountInfo param
	 * @return
	 * @author liLei
	 * @date 2014年7月23日 下午4:06:05
	 * @since 0.1
	 */
	@Override
	public void update(AccountInfo param) {
		getSqlMapClientTemplate().insert(CLASS_NAME + ".update",param);
	}
	
//-----------------------------------------------------------
	/**
	 * 分页查询
	 * @author zhaoxingwen
	 * @date 2014年7月25日 下午18:26:53
	 * @param param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public Page<AccountInfo> selectByPage(AccountInfoQuery param){
		Integer pageNo = param.getPageNo();
		Integer pageSize = param.getPageSize();
		if (pageNo == null || pageSize == null) {
			throw new IllegalArgumentException("pageNo or pageSize is null");
		}
		Integer firstResult = Page.getFirstResult(pageNo, pageSize);
		param.setFirstResult(firstResult);
		Page<AccountInfo> page = new Page<AccountInfo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		Integer count = selectCount(param);
		page.setTotalCount(count);
		if (count == 0) {
			return page;
		}
		List<AccountInfo> resList = selectList(param);
		page.setData(resList);
		return page;
	}

	/**
	 * 单条记录查询
	 * @author zhaoxingwen
	 * @date 2014年7月25日 下午18:26:53
	 * @param param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public AccountInfo selectOne(AccountInfoQuery param) {
		return (AccountInfo) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".select",param);
	}
	
	/**
	 * 单条记录查询更新
	 * @author wangpeng
	 * @date 2014年9月15日 下午16:43:50
	 * @param param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public AccountInfo selectOneForUpdate(AccountInfoQuery param) {
		return (AccountInfo) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".selectForUpdate",param);
	}
	
	@Override
	public AccountInfo selectAccByAcc(AccountInfoQuery param){
		return (AccountInfo) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".selectCashAccByPreAcc",param);
	}
	
	/**
	 * 多记录查询
	 * @author zhaoxingwen
	 * @date 2014年7月25日 下午18:26:53
	 * @param param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public List<AccountInfo> selectList(AccountInfoQuery param) {
		return getSqlMapClientTemplate().queryForList(CLASS_NAME + ".select",param);
	}

	/**
	 * 记录总数查询
	 * @author zhaoxingwen
	 * @date 2014年7月25日 下午1:26:53
	 * @param param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public int selectCount(AccountInfoQuery param) {
		return (int) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".selectCount",param);
	}
	/**
	 * 按日期范围查询
	 * @author zhaoxingwen
	 * @date 2014年7月25日 下午1:26:53
	 * @param param
	 * @return 
	 * @since 0.1
	 * @see
	 */
	@Override
	public Page<AccountInfo> selectBetweenDate(AccountInfoQuery param){
		Integer pageNo = param.getPageNo();
		Integer pageSize = param.getPageSize();
		if (pageNo == null || pageSize == null) {
			throw new IllegalArgumentException("pageNo or pageSize is null");
		}
		Integer firstResult = Page.getFirstResult(pageNo, pageSize);
		param.setFirstResult(firstResult);
		Page<AccountInfo> page = new Page<AccountInfo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		Integer count = selectCount(param);
		page.setTotalCount(count);
		if (count == 0) {
			return page;
		}
		List<AccountInfo> userList = getSqlMapClientTemplate().queryForList(CLASS_NAME + ".selectBetweenDate",param);
		page.setData(userList);
		return page;
	}
	
	@Override
	public void cleanErrorLoginTimes(){
		getSqlMapClientTemplate().insert(CLASS_NAME + ".cleanErrorLoginTime");
	}
		
}
