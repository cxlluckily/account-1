package com.cssweb.payment.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cssweb.payment.account.dao.IPersonDataDao;
import com.cssweb.payment.account.dao.IUserTsmMsgInfoDao;
import com.cssweb.payment.account.pojo.PersonData;
import com.cssweb.payment.account.pojo.PersonalUser;
import com.cssweb.payment.account.pojo.UserLoginInfo;
import com.cssweb.payment.account.pojo.UserTsmMsgInfo;
import com.cssweb.payment.common.ibatis.BaseDao;



@Repository
public class PersonDataDaoImpl extends BaseDao<PersonData> implements IPersonDataDao {

	public static final String CLASS_NAME = PersonData.class.getName();
	/**
	 * Title: insert
	 * Description:
	 * @date : 2014年9月10日
	 * @author Lilei
	 * @param personData
	 */
	@Override
	public void insert(PersonData param) {
		getSqlMapClientTemplate().insert(CLASS_NAME + ".insert",param);
	}

	/**
	 * Title: update
	 * Description:
	 * @date : 2014年9月10日
	 * @author Lilei
	 * @param personData
	 */
	@Override
	public void update(PersonData param) {
        getSqlMapClientTemplate().insert(CLASS_NAME + ".update",param);		
	}

	/**
	 * Title: selectOne
	 * Description:
	 * @date : 2014年9月10日
	 * @author Lilei
	 * @param personData
	 * @return
	 */
	@Override
	public PersonData selectOne(PersonData param) {
        return (PersonData) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".select",param);

	}

	/**
	 * Title: selectList
	 * Description:
	 * @date : 2014年9月10日
	 * @author Lilei
	 * @param personData
	 * @return
	 */
	@Override
	public List<PersonData> selectList(PersonData param) {
        return getSqlMapClientTemplate().queryForList(CLASS_NAME + ".select",param);
	}

	/**
	 * Title: selectCount
	 * Description:
	 * @date : 2014年9月10日
	 * @author Lilei
	 * @param user
	 * @return
	 */
	@Override
	public int selectCount(PersonData param) {
        return (int) getSqlMapClientTemplate().queryForObject(CLASS_NAME + ".selectCount",param);
	}

}
