package com.cudatec.flow.app.dao;

import java.util.List;
import java.util.Map;

import com.zqkj.bsm.dal.dboper.DbOperator;

/** 
* @ClassName: CustomDao 
* @Description: TODO(自定义流程查询) 
* @author MJ002 
* @date 2016年8月1日 上午11:18:42 
*  
*/
public class CustomDao extends DbOperator {
	
	private static volatile CustomDao instance = null;
	
	private CustomDao(){};
	
	public static CustomDao getInstance(){
		if(instance == null){
			synchronized (CustomDao.class) {
				instance = new CustomDao();
			}
		}
		return instance;
	}
	
	/** 
	* @Title: getUserTask 
	* @Description: TODO(查询当前用户操作流程) 
	* @param @param username
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	*/
	public List<Map<String, Object>> getUserTask(String username,int pageNo,int pageSize){
		String sql = "SELECT wt.id as TASKID,wo.id as ORDERID,wp.id as PROCESSID,wt.display_Name as TDISPLAYNAME,a.truename as TRUENAME,wp.display_Name as PDISPLAYNAME,wo.creator as CREATOR,wt.parent_Task_Id as PARENTTASKID ";
		sql += " FROM wf_task_actor wta, wf_task wt,wf_order wo,wf_process wp,admin a WHERE wta.task_Id = wt.id AND wt.order_Id = wo.id AND wp.id = wo.process_Id AND a.username = wta.actor_Id AND wta.actor_Id  = ? AND wt.display_Name != '工作总结' AND  wp.id != '815dfa3477234a8da5accd0f3513cbd4' limit ?,?";
		return this.getList_Pre(sql,new Object[]{username,pageNo * pageSize,pageSize});
	}
	
	/** 
	* @Title: JudgeUser 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	*/
	public int JudgeUser(String username){
		String sql = "select su.authid from admin a,sys_user2auth su where a.admin_id = su.USERID and a.username = ? and authid in (2,33,1,36,40)";
		return this.getList_Pre(sql, new Object[]{username}).size();
	}
	
	/** 
	* @Title: getAdminMsg 
	* @Description: TODO(获取admin对象) 
	* @param @param username
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	public Map<String, Object> getAdminMsg(String username){
		String sql = "select * from admin where username = ?"; 
		return this.getHashMap_Pre(sql,new Object[]{username});
	}
	
	/** 
	* @Title: getZcName 
	* @Description: TODO(查询资产类别名称) 
	* @param @param id
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	public Map<String, Object> getZcName(String id){
		String sql = "select * from zcgl_assetinfo where id = ?";
		return this.getHashMap_Pre(sql,new Object[]{id});
		
	}
	
	
	/** 
	* @Title: getDownloadFlow_form 
	* @Description: TODO(获取收文上传文件的绝对路径) 
	* @param @param orderId
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	public Map<String, Object> getDownloadFlow_form(String orderId){
		String sql = "select * from flow_form where orderId = ? and html_key = 'path_plann_file'";
		return this.getHashMap_Pre(sql,new Object[]{orderId});
	}
	
}

