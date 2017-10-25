package com.zqkj.bsm.dal.client;

import org.apache.ibatis.session.SqlSession;

import com.zqkj.bsm.dal.bean.Admin;

public class AdminOpera extends BaseOpera{
	private static AdminOpera adminOpera=null;
	private AdminOpera(){}
	public static AdminOpera getInstance(){
		synchronized (AdminOpera.class) {
			if (adminOpera==null) {
				adminOpera=new AdminOpera();
			}
		}
		return adminOpera;
	}
	public boolean createAdmin(Admin admin){
		SqlSession session=getSession();
		int flag = session.insert("createAdmin", admin);
		session.commit();
		session.close();
		return flag==1?true:false;
	}
	
	
	public boolean updateAdmin(Admin admin){
		SqlSession session=getSession();
		int flag = session.update("updateAdmin", admin);
		session.commit();
		session.close();
		return flag==1?true:false;
	}
	
	public boolean updateUser(Admin admin)
	{
		SqlSession session=getSession();
		int flag = session.update("updateUser", admin);
		session.commit();
		session.close();
		return flag==1?true:false;
	}
	
	/**
	 * 根据管理员ID查询管理员
	 * @param adminId
	 * @return
	 */
	public Admin readAdminById(long adminId) {
		SqlSession session=getSession();
		Admin admin=session.selectOne("selectAdminById", adminId);
		session.close();
		return admin;
	}
	
	/**
	 * @author 高峰
	 * @date 2016-0325
	 * @param name
	 * @return
	 */
	public Admin readAdminByName(String name) {
		SqlSession session=getSession();
		Admin admin=session.selectOne("readAdminByName", name);
		session.close();
		return admin;
	}
}