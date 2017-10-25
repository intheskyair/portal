package com.zqkj.bsm.dal.client;

import org.apache.ibatis.session.SqlSession;

public class BaseOpera {
	public SqlSession getSession() {
		return SessionClient.getInstance().get("development").openSession();
	}
}
