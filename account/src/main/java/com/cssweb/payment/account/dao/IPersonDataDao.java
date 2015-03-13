package com.cssweb.payment.account.dao;

import java.util.List;

import com.cssweb.payment.account.pojo.UserLoginInfo;
import com.cssweb.payment.account.pojo.PersonData;


/**
 * Title: IUserTsmMsgInfoDao
 * Description: 
 * Company: China Software & Service Company
 * Project:payment-account
 * @author liLei
 * @version 1.0
 * @date 2014年7月23日 下午4:48:04
 * @see 
 */
public interface IPersonDataDao {
	

	/**
	 * Title: insert
	 * Description:
	 * @date : 2014年9月10日
	 * @author Lilei
	 * @param personData
	 */
	public void insert(PersonData personData);
	

	/**
	 * Title: update
	 * Description:
	 * @date : 2014年9月10日
	 * @author Lilei
	 * @param personData
	 */
	public void update(PersonData personData);
	

	/**
	 * Title: selectOne
	 * Description:
	 * @date : 2014年9月10日
	 * @author Lilei
	 * @param personData
	 * @return
	 */
	public PersonData selectOne(PersonData personData);

	/**
	 * Title: selectList
	 * Description:
	 * @date : 2014年9月10日
	 * @author Lilei
	 * @param personData
	 * @return
	 */
	public List<PersonData> selectList(PersonData personData);
	
	/**
	 * 查询记录数
	 * @author liLei
	 * @date 2014年7月23日 下午4:48:04
	 * @param personData 
	 * @since 0.1
	 * @see
	 */
	public int selectCount(PersonData user);
	
	
}
