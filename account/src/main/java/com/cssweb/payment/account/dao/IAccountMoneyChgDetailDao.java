package com.cssweb.payment.account.dao;
import java.math.BigDecimal;
import java.util.List;

import com.cssweb.payment.account.pojo.AccountMoneyChgDetail;
import com.cssweb.payment.account.vo.AccountMoneyChgDetailQuery;
import com.cssweb.payment.common.util.Page;

/**
 * 账户资金变动流水表接口
 * @author zhaoxingwen 
 * @version V1.0
 * @since 2014-07-19
 *
 */
public interface IAccountMoneyChgDetailDao {
	/**
	 * 插入记录
	 * @author zhaoxingwen
	 * @date 2014年8月4日 下午4:35:34
	 * @param 
	 * @since 0.1
	 * @see
	 */
	public void insert(AccountMoneyChgDetail chgDetail);
	/**
	 * 更新记录
	 * @author zhaoxingwen
	 * @date 2014年8月4日 下午4:35:34
	 * @param 
	 * @since 0.1
	 * @see
	 */
	public void update(AccountMoneyChgDetail chgDetail);
	/**
	 * 查询单条记录
	 * @author zhaoxingwen
	 * @date 2014年8月4日 下午4:35:34
	 * @param 
	 * @since 0.1
	 * @see
	 */
	public AccountMoneyChgDetail selectOne(AccountMoneyChgDetailQuery param);
	/**
	 * 查询多条记录
	 * @author zhaoxingwen
	 * @date 2014年8月4日 下午4:47:09
	 * @param param
	 * @since 0.1
	 * @see
	 */
	public List<AccountMoneyChgDetail> selectList(AccountMoneyChgDetailQuery param);
	
	/**
	 * 查询记录数
	 * @author zhaoxingwen
	 * @date 2014年8月4日 下午4:58:45
	 * @param param
	 * @since 0.1
	 * @see
	 */
	public int selectCount(AccountMoneyChgDetailQuery param);
	/**
	 * 通过页码查询记录
	 * @author zhaoxingwen
	 * @date 2014年8月4日 下午4:59:15
	 * @param param
	 * @since 0.1
	 * @see
	 */
	public Page<AccountMoneyChgDetail> selectByPage(AccountMoneyChgDetailQuery param);
	/**
	 * 查询交易金额
	 * @author DuXiaohua
	 * @date 2014年7月26日 下午2:02:04
	 * @param param 查询条件对象
	 * @return 交易金额 
	 * @since 1.0
	 */
	public BigDecimal selectTradeAmount(AccountMoneyChgDetailQuery param);
	
	
}
