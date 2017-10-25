package com.zqkj.bsm.dal.client;


import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.zqkj.bsm.dal.bean.LoginLog;


public class LoginLogOpera extends BaseOpera{
	private static LoginLogOpera loginLogOpera=null;
	public static LoginLogOpera getInstance(){
		if(loginLogOpera==null){
			loginLogOpera=new LoginLogOpera();
		}
		return loginLogOpera;
	}
	public List<LoginLog> getLoginLogById(long userId){
		SqlSession session = getSession();
		List<LoginLog> LoginLogs=null;
		try{
			LoginLogs=session.selectList("selectLoginLogById", userId);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return LoginLogs;
	}
	
	public int addLoginLog(LoginLog loginLog){
		SqlSession session = getSession();
		int result=0;
		try{
			result=session.insert("insertLoginLog", loginLog);
			session.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return result;
	}
	public LoginLog readLastLoginLog(long userId) {
		SqlSession session = getSession();
		LoginLog LoginLog=null;
		try{
			LoginLog=session.selectOne("readLastLoginLog", userId);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return LoginLog;
	}
	
}
