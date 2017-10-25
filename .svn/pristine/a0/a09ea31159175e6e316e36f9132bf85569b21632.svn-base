package com.zqkj.bsm.system.action.dao;

import java.util.List;
import java.util.Map;

import com.zqkj.bsm.dal.dboper.DbOperator;
import common.Logger;

public class CrosswiseDao extends DbOperator {
	private Logger log = Logger.getLogger(CrosswiseDao.class);
	private static CrosswiseDao newInstance;
	private CrosswiseDao(){
		
	}
	
	public static CrosswiseDao getNewInstance(){
		if(newInstance == null){
			synchronized (CrosswiseDao.class) {
				if(newInstance == null)
					newInstance = new CrosswiseDao();
			}
		}
		return newInstance;
	}
	
	public List<Map<String,Object>> getSubMenu(String menuId){
		StringBuffer sb = new StringBuffer();
		sb.append("select * from sys_menu t where t.PARENTID = '").append(menuId).append("'");
		log.debug("根据菜单ID获取子菜单-->CrosswiseDao.getSubMenu:"+sb.toString());
		return this.getList_Pre(sb.toString());
	}
}
