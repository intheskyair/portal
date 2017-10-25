package com.cudatec.render.util;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response.Status;

import com.zqkj.bsm.system.action.session.StoreFactory;

/**
 * 用户管理工具类
 */
public class UserUtil {
	/**
	 * 根据客户端发送的头部信息，判断用户是否合法
	 * @param hh HTTP请求的头部信息
	 * @return 用户的SessionID
	 */
	public static String authorize(HttpHeaders hh) {
		MultivaluedMap<String, String> values = hh.getRequestHeaders();
		if (values.containsKey("cookie")) {
			String cookie = values.get("cookie").get(0);
			return cookie.substring(cookie.indexOf('=') + 1);
		} else {
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}
	}
	
	/**
	 * 根据用户的SessionID，获取用户在数据库中的ID
	 * @param sessionID
	 * @return 用户ID
	 */
	@SuppressWarnings("rawtypes")
	public static String getUserID(String sessionID) {
		List users = StoreFactory.getStore().getUsers();
		for (Object obj : users) {
			Object[] user = (Object[]) obj;
			if (sessionID.equals(user[1])) {
				return (String)user[0];
			}
		}
		return null;
		//throw new WebApplicationException(555);		// session invalid
	}
}
