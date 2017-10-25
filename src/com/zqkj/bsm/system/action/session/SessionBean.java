package com.zqkj.bsm.system.action.session;

import javax.servlet.http.HttpSession;

public class SessionBean 
{
	private HttpSession session;
	private int login_tp;
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public int getLogin_tp() {
		return login_tp;
	}
	public void setLogin_tp(int login_tp) {
		this.login_tp = login_tp;
	}

}
