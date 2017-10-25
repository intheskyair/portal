package com.zqkj.bsm.system.action.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zqkj.bsm.dal.dboper.DbOperator;

public class Multi_Fram_Tab extends DbOperator{
	 Logger log = Logger.getLogger(Multi_Fram_Tab.class);
	 private static Multi_Fram_Tab mft;
	 
	 public static Multi_Fram_Tab getInstance(){
		 if(null == mft){
			 mft = new Multi_Fram_Tab();
		 }
		 return mft;
	 }
	 
	 public List<Map<String, Object>> getFram(int stat){
		 String sql = "select id,agent_count,upload_count,priority,"
		 		    + "cluster_ip,cluster_port,fserver_lan_port,"
		 		    + "fserver_wlan_ip,fserver_wlan_port,domain_name,"
		 		    + "domain_port,is_forbidden,is_web_fserver,`name`,description,stat "
		 		    + "from render_farm where stat = ?";
		 return  super.getList_Pre(sql, new Object[]{stat});
	 }
	 
	 
	 public Map<String, Object> getFramInfo(int  id)
	 {
		 String sql = "select id,agent_count,upload_count,priority,"
		 		    + "cluster_ip,cluster_port,fserver_lan_port,"
		 		    + "fserver_wlan_ip,fserver_wlan_port,domain_name,"
		 		    + "domain_port,is_forbidden,is_web_fserver,`name`,description,stat "
		 		    + "from render_farm where id = ?";
		 return  super.getHashMap_Pre(sql, new Object[]{id});
	 }
	 
}
