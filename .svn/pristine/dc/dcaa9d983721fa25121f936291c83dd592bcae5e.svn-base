/*
 *  Copyright 2013-2014 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.Approval/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.cudatec.flow.framework.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cudatec.flow.framework.dao.ApprovalDao;
import com.cudatec.flow.framework.entity.Approval;
import com.zqkj.bsm.dal.dboper.DbOperator;
import com.zqkj.bsm.util.CommonUtil;

/**
 * @author yuqs
 */
@Component
public class ApprovalManager extends DbOperator{
    @Autowired
    private ApprovalDao dao;
	
	/**
	 * 保存实体
	 * @param entity
	 */
	public void save(Approval entity) {
		dao.save(entity);
	}
	
	/**
	 * 根据主键ID删除对应的
	 * @param id
	 */
	public void delete(Long id) {
		dao.delete(id);
	}
	
	/**
	 * 根据主键ID获取实体
	 * @param id
	 * @return
	 */
	public Approval get(Long id) {
		return dao.get(id);
	}
	
	/**
	 * 获取所有记录
	 * @return
	 */
	public List<Approval> getAll() {
		return dao.getAll();
	}
	
	public List<Approval> findByFlow(String orderId, String taskName) {
		return dao.find("from Approval where orderId = ? and taskName = ?", orderId, taskName);
	}
	
	
	public boolean updateHq(String orderId,String html_key,String html_value,ArrayList<Object[]> listObj)
	{
		boolean return_bz = false;
		String sql="select 1 from flow_form where orderId=? and html_key=?";
		if(this.getCount(sql, new Object[]{orderId,html_key})>0)
		{
			html_value ="\r\n"+html_value;
			String update_sql = "update flow_form set html_value= CONCAT(html_value,?) where orderId=? and html_key=?";
			return_bz = this.executeUpdate(update_sql, new Object[]{html_value,orderId,html_key});
		}
		else
		{
			return_bz = this.executeBatch_Pre("insert into flow_form (html_key,html_value,orderId,task_id) values (?,?,?,?)", listObj);
		}
		return return_bz;
	}
	
    /**
     * 插入审批提醒
     * @param map
     * @return
     */
	public boolean insertMessage(Map<String, Object> map)
	{
		ArrayList<Object[]> listObj = new ArrayList<Object[]>();
		String sql = "insert into message_center (user_id,message_content,orderId,taskId,processId,display_name,tx_time,status,fq_truename) values (?,?,?,?,?,?,now(),0,?)";
		String[] users = CommonUtil.nullToDefault(map.get("user_id"),"").split(",");
		for(String user:users)
		{
			Object[] ob = new Object[]{user,map.get("message_content"),map.get("orderId"),map.get("taskId"),map.get("processId"),map.get("display_name"),map.get("fq_truename")};
			listObj.add(ob);
		}
		return this.executeBatch_Pre(sql, listObj);		
	}
	
	/**
	 * 查询自己的审批提醒
	 * @param user_id
	 * @return
	 */
	public List<Map<String, Object>> queryMessage(String user_id)
	{
		String sql = "select * from message_center where user_id=? and status=0 order by id desc limit 0,100";
		return  this.getList_Pre(sql,new Object[]{user_id});
	}
	
	
	public boolean updateMessage(String user_id,String taskId,String orderId)
	{		
		String sql = "update message_center set status=1 where taskId = ? and orderId=?";
		return this.executeUpdate(sql, new Object[]{taskId,orderId});
	}
	
	
	public boolean updateTask(String taskId,String operator)
	{		
		String sql = "update wf_task set operator= ? where id = ?";
		return this.executeUpdate(sql, new Object[]{operator,taskId});
	}
	
	
	public boolean updateMessage1(String user_id,String taskId,String newUser_id,String newTaskId)
	{		
		String sql = "update message_center set user_id=?,taskId=?  where user_id = ? and taskId=?";
		return this.executeUpdate(sql, new Object[]{newUser_id,newTaskId,user_id,taskId});
	}
	
	
	public List<Map<String, Object>> querydocument_dic()
	{
		String sql = "select * from document_dic limit 0,100";
		return  this.getList_Pre(sql);
	}
	
}
