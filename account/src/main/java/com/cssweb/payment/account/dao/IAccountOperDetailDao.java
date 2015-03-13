package com.cssweb.payment.account.dao;
import java.util.List;

import com.cssweb.payment.account.pojo.AccountInfo;
import com.cssweb.payment.account.pojo.AccountOperDetail;
import com.cssweb.payment.account.vo.AccountInfoQuery;

/**
 *  账户资金变动流水表接口
 * @author zhaoxingwen 
 * @date 2014-07-22
 * @version V1.0
 *
 */
public interface IAccountOperDetailDao {
	/**
	 * 插入记录
	 * @author zhaoxingwen
	 * @date 2014年8月14日 下午3:41:58
	 * @param operDetail 
	 * @since 0.1
	 * @see
	 */
	public void insert(AccountOperDetail operDetail);
	/**
	 * 更新记录
	 * @author zhaoxingwen
	 * @date 2014年8月14日 下午3:42:23
	 * @param operDetail 
	 * @since 0.1
	 * @see
	 */
	public void update(AccountOperDetail operDetail);
	/**
	 * 查询单条记录
	 * @author zhaoxingwen
	 * @date 2014年8月14日 下午3:42:42
	 * @param operDetail
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public AccountOperDetail selectOne(AccountOperDetail operDetail);
	
	/**
	 * 查询多条记录
	 * @author zhaoxingwen
	 * @date 2014年8月14日 下午3:43:05
	 * @param operDetail
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public List<AccountOperDetail> selectList(AccountOperDetail operDetail);
	/**
	 * 查询记录数
	 * @author zhaoxingwen
	 * @date 2014年8月14日 下午3:43:22
	 * @param operDetail
	 * @return 
	 * @since 0.1
	 * @see
	 */
	public int selectCount(AccountOperDetail operDetail);
}
