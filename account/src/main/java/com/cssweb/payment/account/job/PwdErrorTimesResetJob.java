package com.cssweb.payment.account.job;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.cssweb.payment.account.service.support.AccountServiceSupport;

@Component
public class PwdErrorTimesResetJob implements InitializingBean{
	
	@Resource
	private AccountServiceSupport accountServiceSupport;
	
	@Override
	public void afterPropertiesSet() throws Exception {
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    cal.add(Calendar.DAY_OF_MONTH, 1);
		new Timer().scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				accountServiceSupport.cleanWrongPassWord();
			}
			
		}, cal.getTime(), 1000 * 60 * 60 * 24);
	}
	
}
