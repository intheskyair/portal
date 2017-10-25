package com.zqkj.bsm.dal.client;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SessionClient {
	private static Map<String,SqlSessionFactory> sqlSessionFactorys = new HashMap<String,SqlSessionFactory>();
	private SessionClient(){
		
	}
	
	public static Map<String,SqlSessionFactory> getInstance() {
		if (sqlSessionFactorys.size() == 0) {
			String resource = "Configuration.xml";
			Reader reader = null;
			try {
				reader = Resources.getResourceAsReader(resource);
				SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
				sqlSessionFactorys.put("development", builder.build(reader));
				reader = Resources.getResourceAsReader(resource);
				sqlSessionFactorys.put("development1", builder.build(reader, "development1"));
				reader = Resources.getResourceAsReader(resource);
				sqlSessionFactorys.put("development2", builder.build(reader, "development2"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sqlSessionFactorys;
	}
}
