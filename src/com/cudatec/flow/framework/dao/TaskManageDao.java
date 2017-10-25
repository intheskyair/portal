package com.cudatec.flow.framework.dao;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.log4j.Logger;
import org.snaker.engine.helper.StringHelper;
import org.springframework.transaction.annotation.Transactional;

import com.zqkj.bsm.cluster.inter.ClusterResponse;
import com.zqkj.bsm.cluster.inter.HttpClientPool;
import com.zqkj.bsm.cluster.inter.HttpSender;
import com.zqkj.bsm.dal.dboper.DbOperator;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.util.BHUtil;
import com.zqkj.bsm.util.CommonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.org.mozilla.javascript.internal.ObjArray;

public class TaskManageDao extends DbOperator{
	 Logger log = Logger.getLogger(TaskManageDao.class);
	    private static TaskManageDao instance;
	    public static TaskManageDao getInstance()
	    {
	        if(instance ==null)
	        {
	            instance = new TaskManageDao();
	        }
	        return instance;
	    }
	    
	    /**
	     * 获取套餐列表信息
	     * @param userid
	     * @return 菜单标题list
	     */
	    public OraPaginatedList getTaskInfoList(Map<String, Object> param)
	    {
	        int pageSize = (Integer)param.get("pageSize");
	        int pageNum = (Integer)param.get("pageNum");
	        String processName = CommonUtil.nullToDefault(param.get("processName"), "");
	        String status = CommonUtil.nullToDefault(param.get("status"), "");
	        String sortname = CommonUtil.nullToDefault(param.get("sortname"), "");
	        String sortorder = CommonUtil.nullToDefault(param.get("sortorder"), "");

	        String condition = "";
	        String conditionP = "";
	        if (!"".equals(processName)) {
	            condition += " and p.display_Name like concat('%', ?, '%')";
	            conditionP += processName + ",";
	        }
	        if (!"".equals(status)) {
	            condition += " and p.state = ?";
	            conditionP += status + ",";
	        }	   	     
             if (!"undefined".equals(sortname) && !"".equals(sortname)) {
     			condition += " order by " + sortname;
     			if ("desc".equals(sortorder) || "asc".equals(sortorder)) {
     				condition += " " + sortorder;
     			} else {
     				condition += " desc";
     			}
     		} else {
     			condition += " order by t.create_Time desc";
     		}
	        
	        String[] oArray = null;
	        if (conditionP != null && !"".equals(conditionP)) {
	            conditionP = conditionP.substring(0, conditionP.length() - 1);
	            oArray = conditionP.split(",");
	        }
	        String sql = "select a.truename,am.truename as atruename, o.process_Id,t.order_Id,t.id as id,t.id as task_Id,p.display_Name as process_Name,"+
        "p.instance_Url,o.parent_Id,o.creator,o.create_Time as order_Create_Time,o.expire_Time as order_Expire_Time,o.order_No,"+
	    "o.variable as order_Variable,t.display_Name as task_Name,t.task_Type,t.perform_Type,t.operator,t.action_Url,"+        
        "ta.actor_Id,t.create_Time as task_Create_Time,t.finish_Time as task_End_Time,t.expire_Time as task_Expire_Time,t.variable as task_Variable"+
	    " from wf_task t  left join wf_order o on t.order_id = o.id  left join wf_task_actor ta on ta.task_id=t.id  left join"+      
        " wf_process p on p.id = o.process_id    left join admin a on a.username = o.creator    left join admin am on am.username = ta.actor_Id where 1=1 and t.task_Type = 0 "+condition;
	        OraPaginatedList list = new OraPaginatedList(sql, oArray);
	        list.setCurrentPage(pageNum);
	        list.setObjectsPerPage(pageSize);
	        return list;
	    }
	    

	// TODO
	public List<Map<String, Object>> queryTaskId(String parent_task_id) {
		List<Map<String, Object>> list = null;

		String sql = "select id from wf_task where parent_task_id=?";
		list = this.getList_Pre(sql, new Object[]{parent_task_id});
		return list;
	}

	public List<Map<String, Object>> queryNameByOrderId(String orderId,
			String task_Id) {
		List<Map<String, Object>> list = null;
		String sql = "select a.*,b.* from wf_task a   LEFT JOIN wf_task_actor b on a.id = b.task_Id "
				+ "where a.order_Id = '"
				+ orderId
				+ "' and b.task_Id= '"
				+ task_Id + "'";
		list = this.getList_Pre(sql);
		return list;
	}

	public List<Map<String, Object>> queryTaskByTaskId(String task_Id) 
			{
		String sql = "select b.* from wf_task_actor b"
				+ " where b.task_Id = '"
				+ task_Id
				+ "'";
		return this.getList_Pre(sql);
	}
	
	
	public List<Map<String, Object>> queryHistNameByOrderId(String orderId) {
		List<Map<String, Object>> list = null;

		String sql = "select a.* from wf_hist_task a where a.order_Id = '"
				+ orderId + "' ORDER BY finish_Time ASC";
		list = this.getList_Pre(sql);
		return list;
	}

	public String getNowTaskId(String name){
		String sql = "select id from wf_task where task_Name = '" + name +"'";
		return this.getList_Pre(sql).get(0).get("ID").toString();
	}
	
	public String getNowTaskName(String task_id,String orderId){
		String sql = "select task_Name from wf_task where order_Id = '" + orderId +"' and id='"+task_id+"'";
		ArrayList<Map<String, Object>> list = this.getList_Pre(sql);
		if(list!=null && list.size()>0)
		{
		  return this.getList_Pre(sql).get(0).get("TASK_NAME").toString();
		}
		else
		{
			return "";
		}
	}
	
	
	public ArrayList<Object[]> save( Map<String, Object> params,String orderId,String taskId){
		ArrayList<Object[]> listObj = new ArrayList<Object[]>();
		for (String key : params.keySet()) {
			if(params.get(key) != null && StringHelper.isNotEmpty(params.get(key).toString())
					&& !key.equals("processId") 
					&& !key.equals("orderId") 
					&& !key.equals("taskId") 
					&& !key.equals("step") 
					&& !key.equals("taskName") 
					&& !key.equals("userList") 
					&& !key.equals("assignee")
					&& !key.equals("result")
					&& !key.equals("userName")
					&& !key.equals("taskName")
					&& !key.equals("ip"))
			{
				if(params.get(key).toString().length() <= 5000)
				{
				   listObj.add(new Object[]{key,params.get(key).toString(),orderId,taskId});
				}
			}
		}
		return listObj;
	}
	
	
	public ArrayList<Object[]> update( Map<String, Object> params,String orderId){
		ArrayList<Object[]> listObj = new ArrayList<Object[]>();
		for (String key : params.keySet()) {
			if(!key.equals("processId") 
					&& !key.equals("orderId") && !key.equals("taskId") && !key.equals("step"))
			{
				if(params.get(key).toString().length() <2000)
				{
				   listObj.add(new Object[]{params.get(key).toString(),key,orderId});
				}
			}
		}
		return listObj;
	}
	 
	public List<Map<String, Object>> getUserName(String auth,Map<String, Object> map,String step,String org_id){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT username FROM admin,sys_user2auth WHERE admin.admin_id = sys_user2auth.USERID AND sys_user2auth.AUTHID = " + auth);
		if(step != null && org_id != null && !org_id.equals(""))
		{
			sb.append(" and admin.org_id ='" +org_id + "'" );
		}
		return getList_Pre(sb.toString());
	}
	
	public Map<String, Object> getFormMsg(String TaskId){
		Map<String, Object> map = new HashMap<String, Object>();
		String sql = "select html_key,html_value from flow_form where task_id = '" + TaskId + "'";
		List<Map<String, Object>> value = this.getList_Pre(sql);
		for (Map<String, Object> map2 : value) {
			map.put(map2.get("HTML_KEY").toString(), map2.get("HTML_VALUE"));
		}
		return map; 
	}
	
	
	public Map<String, Object> getForm_List(String orderId){
		String sql = "select html_key,html_value from flow_form where orderId = '" + orderId + "'";
		List<Map<String, Object>> list = this.getList_Pre(sql);
		Map<String, Object> map = new HashMap<>();
		for (Map<String, Object> mapOld : list) 
		{
			String key = mapOld.get("HTML_KEY").toString();
			Object value= mapOld.get("HTML_VALUE");
			//对于会签的意见可能有多个
			if(map.containsKey(key))
			{
				String value_ = String.valueOf(map.get(key))+"<br/>"+value;
			    map.put(key, value_);
			}
			else
			{
					map.put(key, value);
			}
		}
		return map;
	}
	
	public boolean saveDispatch(String orderId,String content){
		StringBuffer sb = new StringBuffer();
		sb.append("insert into flow_file (order_id,file_content,time) values(?,?,NOW())");
		Object[] objs = new Object[]{orderId,content};
		String[] types = new String[]{"String","Blob"};
		return this.executeBlob(sb.toString(), types, objs);
	}
	
	
	public boolean updateDispatch(String orderId,String content){
		StringBuffer sb = new StringBuffer();
		sb.append("update flow_file set file_content = ? where order_id = ?");
		Object[] objs = new Object[]{content,orderId};
		String[] types = new String[]{"String","Blob"};
		return this.executeBlob(sb.toString(), types, objs);
	}
	
	
	public Map<String, Object> queryDispatchByOrderId(String orderId)
	{
		StringBuffer sb = new StringBuffer();
		if(!StringHelper.isEmpty(orderId))
		{
			sb.append("select * from flow_file t where t.order_id = '").append(orderId).append("'");
			ArrayList<Map<String, Object>> list =  this.getListBlob_Pre(sb.toString());
			if(list!=null && list.size()>0)
			{
			    return list.get(0);
			}
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}
	
	public boolean deleteHq(String orderId)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("delete from wf_task where order_Id=? and operator is null");
		return this.executeUpdate(sb.toString(),new Object[]{orderId});
	}

	public OraPaginatedList getList(String startTime,String endTime,int pageNum,int pageSize,String processId){
		String param = "";
		String sql = "SELECT distinct wt.parent_Task_Id, wp.id as pid,wo.id,wo.create_Time,wo.order_State,wo.end_Time,wo.variable,a.truename as creator,wp.display_Name,wt.display_Name as nextDisplayName FROM wf_process AS wp, "
				+ " wf_hist_order AS wo LEFT JOIN wf_task AS wt ON wt.order_Id = wo.id LEFT JOIN admin AS a ON a.username = wo.creator WHERE wp.id = wo.process_Id";
		 if (StringHelper.isNotEmpty(startTime)) {
			 sql += " and date_format(wo.create_Time, '%Y-%m-%d') >= ?";
			 param += startTime + ",";
         }
         if (StringHelper.isNotEmpty(endTime)) {
        	 sql += " and date_format(wo.create_Time, '%Y-%m-%d') <= ?";
        	 param += endTime + ",";
         }
         if (StringHelper.isNotEmpty(processId)) {
        	 sql += " and wp.id = ?";
        	 param += processId + ",";
         }
         Object[] oArray_count = null;
         if(StringHelper.isNotEmpty(param)){
        	 oArray_count = param.split(",");
         }
         OraPaginatedList list = new OraPaginatedList(sql,oArray_count);
         list.setSortCriterion("order by create_Time desc ");
         list.setCurrentPage(pageNum);
         list.setObjectsPerPage(pageSize);
         return list;
	}
	
	

	
	public List<Map<String, Object>> queryTaskNowByOrderId(String orderId) 
	{
		List<Map<String, Object>> list = null;

		String sql = "select a.* from wf_task a where a.order_Id = '"
				+ orderId + "' ";
		list = this.getList_Pre(sql);
		return list;
	}
	
	public List<Map<String, Object>> getHisTaskList(String orderId){
		List<Map<String, Object>> list = null;
		String sql = "SELECT wht.display_Name,wht.create_Time,wht.finish_Time,ad.truename,ad.org_infor,wht.task_Name,wht.variable FROM wf_hist_task AS wht,admin AS ad WHERE wht.order_Id = ? AND ad.username = wht.operator ORDER BY wht.finish_Time asc";
		list = this.getList_Pre(sql,new Object[]{orderId});
		return list;
	}
	
	public List<Map<String, Object>> getTaskList(String orderId){
		List<Map<String, Object>> list = null;
		String sql = "SELECT wht.display_Name,wht.create_Time,wht.finish_Time,ad.truename,ad.org_infor FROM wf_task AS wht, wf_task_actor as wta,admin as ad WHERE wht.id = wta.task_Id and ad.username = wta.actor_Id and wht.order_Id = ?";
		list = this.getList_Pre(sql,new Object[]{orderId});
		return list;
	}
	
	public List<Map<String, Object>> getUserAuth(long userid){
		String sql ="select authid from sys_user2auth where userid = ?";
		return  this.getList_Pre(sql,new Object[]{userid});	
	}
	
	public void DeActorId(String taskId){
		String sql = "delete from wf_task_actor where task_id = ?";
		this.executeUpdate(sql, new Object[]{taskId});
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, Object> getszxcList(String url_5)
	{
		 Map<String, Object> map_return = new HashMap<String, Object>();
		 HttpClient httpclient = HttpClientPool.getHttpClient();
		 ClusterResponse res=  HttpSender.send(httpclient,"POST", url_5, "");
		 int rowCount=0;
		 if(res.getResponseCode()==200)
		 {
		    String json = res.getResultBody().substring(5,res.getResultBody().length()-1);
		    JSONObject item =JSONObject.fromObject(json);
			if(item.containsKey("rowCount"))
			{
				rowCount = item.getInt("rowCount");
			}	
		    JSONArray jsonArray = item.getJSONArray("search_result");
			List<Map<String,Object>> mapListJson = (List)jsonArray;
			map_return.put("rowCount", rowCount);
			map_return.put("list", mapListJson);
			return map_return;
		 }
		 else
		 {
			 map_return.put("rowCount", rowCount);
			 map_return.put("list", null);
		 }
		 return map_return;
	}
	
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getszxcInfo(String url_5)
	{
		 HttpClient httpclient = HttpClientPool.getHttpClient();
		 ClusterResponse res=  HttpSender.send(httpclient,"POST", url_5, "");
		 if(res.getResponseCode()==200)
		 {
		    String json = res.getResultBody().substring(5,res.getResultBody().length()-1);
		    JSONObject item =JSONObject.fromObject(json);
		    Map<String,Object> map = (Map<String,Object>)item;
		    JSONArray jsonArray = item.getJSONArray("fjList");
			List<Map<String,Object>> mapListJson = (List)jsonArray;
			map.put("list", mapListJson);
			return map;
		 }
		 else
		 {
			 return null;

		 }
	}
	
	/**
	 * 查询用户年假天数
	* @Title: getUserYear 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param uid
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws
	 */
	public List<Map<String, Object>> getUserYear(long uid){
		String sql = "select year,all_time,used from admin_leave where uid = ? and year_time = ?";
		return this.getList_Pre(sql,new Object[]{uid,BHUtil.getTime_year()});
	}
	/**
	 * 
	 * 更新用户年假天数
	* @Title: updateUserleave 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean updateUserleave(Map<String, Object> map){
		boolean flag = false;
		int year = Integer.parseInt(BHUtil.getTime_year());
		long uid = map.get("uid") == null ? 0 : Long.parseLong(map.get("uid").toString()) ;
		double allleave = map.get("allleave") == null ? 0 : Double.valueOf(map.get("allleave").toString());
		double allday = map.get("allday") == null ? 0 : Double.valueOf(map.get("allday").toString());
		String qtype = map.get("qtype").toString();
		List<Map<String,Object>> listOld = TaskManageDao.getInstance().getUserYear(uid);
		Object[] obj = null;
		String sql = null;
		if(listOld.size() == 0){
				obj = new Object[]{uid,allleave,year};
				sql = "insert into admin_leave (uid,all_time,year_time) values (?,?,?)";
		}else{
			if(qtype.equals("年假")){
				obj = new Object[]{BHUtil.js(allleave, Double.valueOf(listOld.get(0).get("USED").toString())),BHUtil.js(allleave, allday),uid};
				sql = "update admin_leave set used = ? ,all_time = ? where uid = ?";
			}else{
				obj = new Object[]{BHUtil.js(allleave, allday),uid};
				sql = "update admin_leave set all_time = ? where uid = ?";
			}
		}
		flag = this.executeUpdate(sql, obj);
		return flag;
	}
	
	
	public OraPaginatedList getUserLeave(String truename,int pageNum,int pageSize){
		String sql = "select al.year,al.used,al.all_time,a.truename,a.admin_id,a.org_name,a.org_infor from admin as a  LEFT JOIN admin_leave as al on al.uid = a.admin_id";
		Object[] obj = null;
		if(truename != null && !truename.equals("")){
			 sql += " where a.truename like concat('%', ?, '%')";
			 obj = new Object[]{truename};
		}
		  OraPaginatedList list = new OraPaginatedList(sql,obj);
	      list.setCurrentPage(pageNum);
	      list.setObjectsPerPage(pageSize);
	      return list;
	}
	
	public boolean UpdateUserLeave1(String year,String uid){
		String sql = null;
		Object[] obj = null;
		List<Map<String, Object>> list = getUserYear(Long.parseLong(uid));
		if(list.size() == 0){
			sql = "insert into admin_leave (uid,year,year_time) values (?,?,?)";
			obj = new Object[]{uid,year,BHUtil.getTime_year()};
		}else{
			sql = "update admin_leave set year = ? where uid =?";
			obj = new Object[]{year,uid};
		}
		return this.executeUpdate(sql, obj);
	}
	@Transactional
	public void del(String parentId){
		String sql = "select id from wf_task where parent_Task_Id = ?";
		List<Map<String, Object>> list = this.getList_Pre(sql, new Object[]{parentId});
		if(list.size() != 0){
			ArrayList<Object[]> listOBJ = new ArrayList<Object[]>();
			Object[] obj = null;
			for (Map<String, Object> map : list) {
				obj = new Object[]{map.get("ID")};
				listOBJ.add(obj);
			}
			String sql1 = "delete  from wf_task_actor where task_Id = ?";
			this.executeBatch_Pre(sql1, listOBJ);
			String sql2 = "delete  from wf_task where id = ?";
			this.executeBatch_Pre(sql2, listOBJ);
			String sql3 = "update message_center set status = '1' where taskId = ?";
			this.executeBatch_Pre(sql3, listOBJ);
		}
	}
	
	
	public List<Map<String, Object>> getCounterJson(String taskName,String orderId){
		String sql = "select html_value from flow_form where html_key = ? and orderId = ?";
		return this.getList_Pre(sql, new Object[]{taskName,orderId});
	}
	
	public boolean delCounterJson(String taskName,String orderId){
		String sql ="delete from flow_form where html_key = ? and orderId = ?";
		return this.executeUpdate(sql, new Object[]{taskName,orderId});
	}
	
	
	public String getMessageCenterStatus(String taskId,String name){
		String sql = "select status from message_center where taskId = ?";
		return this.getString(sql,taskId,name);
	}
	
	public String getOrderCreater(String orderId,String name){
		String sql = "select creator from wf_order where id = ?";
		return this.getString(sql,orderId,name);
	}
	public boolean addFlowGWGD(List<Object> list){
		String sql = "insert into flow_gwgd (orderid,begintime,refer,title,fjaddress,type,bcqx,fjname,swtype,sfgk) values (?,?,?,?,?,?,?,?,?,?)";
		return this.executeUpdate(sql, list.toArray());
	}
	
	public OraPaginatedList getGwgdList(String sw_type,String bcqx,int type,String reference,String refer,String overtime,String sfgk,int pageNum,int pageSize){
		String sql = "select fg.*,a.truename from flow_gwgd as fg LEFT JOIN admin a on  fg.uid = a.admin_id where fg.type = ? ";
		List<Object> listVal = new ArrayList<Object>();
		listVal.add(type);
		if(reference != null && !reference.equals("")){
			 sql += " and reference like concat('%', ?, '%')";
			 listVal.add(reference);
		}
		if(refer != null && !refer.equals("")){
			 sql += " and refer like concat('%', ?, '%')";
			 listVal.add(refer);
		}
		if(overtime != null && !overtime.equals("")){
			 sql += " and date_format(overtime, '%Y-%m-%d') = ?";
			 listVal.add(overtime);
		} 
		if(sw_type != null && !sw_type.equals("")){
			 sql += " and swtype = ?";
			 listVal.add(sw_type);
		} 
		if(bcqx != null && !bcqx.equals("")){
			 sql += " and bcqx = ?";
			 listVal.add(bcqx);
		} 
		if(sfgk != null && !sfgk.equals("")){
			 sql += " and sfgk = ?";
			 listVal.add(sfgk);
		} 
		
		sql += " order by status asc";
		  OraPaginatedList list = new OraPaginatedList(sql,listVal.toArray());
	      list.setCurrentPage(pageNum);
	      list.setObjectsPerPage(pageSize);
	      return list;
	}
	
	public int getMax(){
		String sql = "select DISTINCT reference from flow_gwgd where reference != '' || reference <> null ";
		return this.getCount(sql, null);
	}
	
	public List<Map<String, Object>> getHisGwlist(){
		String sql = "select DISTINCT reference from flow_gwgd where reference != '' || reference <> null ";
		return this.getList_Pre(sql);
	}
	
	public boolean gwgdupdate(ArrayList<Object[]> list){
		String sql = "update flow_gwgd set status = 1,overtime = NOW(),uid = ? ,reference = ? where id = ?";
		return this.executeBatch_Pre(sql, list);
	}
	
	
	public boolean editGwgd(String id,String page,String remark,long uid){
		String sql = "update flow_gwgd set page = ?,remark = ?,uid = ?  where id = ?";
		return this.executeUpdate(sql, new Object[]{page,remark,uid,id});
	}
	/*
	 * 
	 * 此处in中参数非动态，如需查询其他流程请补？
	 */
	public List<Map<String, Object>> getTodesk(Object[] obj){
		String sql = "SELECT wo.process_Id, count(wo.process_Id) AS count1 FROM wf_task_actor wta,wf_task wt,wf_order wo WHERE wta.actor_Id = ?";
		sql += " AND wt.id = wta.task_Id AND wo.id = wt.order_Id ";
		sql += " and  wo.process_Id IN (?,?)";
		sql += "GROUP BY wo.process_Id ";
		return this.getList_Pre(sql, obj);
	}
	
	public OraPaginatedList getPlanList(int type,int pageNum,int pageSize,String processId){
		String sql = "SELECT wf.id,wf.order_State,a.truename,wf.create_Time from wf_hist_order wf,flow_form ff ,admin a where wf.process_Id = ?  and ff.orderId = wf.id and ff.html_key = 'type' and ff.html_value = ?  and a.username = wf.creator";
         OraPaginatedList list = new OraPaginatedList(sql,new Object[]{processId,type});
         list.setSortCriterion("order by A.create_Time desc ");
         list.setCurrentPage(pageNum);
         list.setObjectsPerPage(pageSize);
         return list;
	}
	
	
	public List<Map<String, Object>> getPlanOrderList(String processId,int type) 
	{
		List<Map<String, Object>> list = null;

		String sql = "select html_key,html_value,orderId,task_id from flow_form where orderId in (SELECT wf.id from wf_hist_order wf,flow_form ff where wf.process_Id = ? and wf.id = ff.orderId and  ff.html_key = 'type' and ff.html_value = ? )";
		list = this.getList_Pre(sql,new Object[]{processId,type});
		return list;
	}
	
	
	/**
     * 数据库字段和导出标题栏的对应关系
     * title,code,unit,accept,approval,uid,filename,filepath,createtime
     */
    public  Map<String, String> getList_Column()
    {
        StringBuilder sb=new StringBuilder();
        sb.append("SELECT ");
        sb.append(" fg.refer as '文号',");
        sb.append(" fg.title as '标题',");
        sb.append(" fg.begintime as '发文日期',");
        sb.append(" fg.sfgk as '是否公开',");
        sb.append(" fg.fjname as '附件',");
        sb.append(" fg.reference as '档号',");
        sb.append(" fg.status as '状态',");
        sb.append(" a.truename as '操作人'");
        sb.append(" FROM flow_gwgd fg,admin a");
        return this.getList_Column(sb.toString());
    }
    
    /**
     * 数据导出不分页，但是超过一定量会有性能问题
     * @param map
     * @return
     * @throws UnsupportedEncodingException 
     */
     @SuppressWarnings("deprecation")
    public List<Map<String, Object>> getExportDara(Map<String, Object> map) throws UnsupportedEncodingException 
     {
            String condition=this.getDesSql(map);
            String conditionP = "";
            String param = "";
            if(map!= null && !com.zqkj.bsm.system.action.util.StringHelper.noEmpty2(map.get("overtime")).trim().equals(""))
            {
                param += " and fg.overtime like concat('%',?,'%') ";
                conditionP += URLDecoder.decode(com.zqkj.bsm.system.action.util.StringHelper.noEmpty2(map.get("overtime")).trim()) + ",";
            }
            if(map!= null && !com.zqkj.bsm.system.action.util.StringHelper.noEmpty2(map.get("reference")).trim().equals(""))
            {
                param += " and fg.reference like concat('%',?,'%') ";
                conditionP += URLDecoder.decode(com.zqkj.bsm.system.action.util.StringHelper.noEmpty2(map.get("reference")).trim()) + ",";
            }
            if(map!= null && !com.zqkj.bsm.system.action.util.StringHelper.noEmpty2(map.get("refer")).trim().equals(""))
            {
                param += " and fg.refer like concat('%',?,'%') ";
                conditionP += URLDecoder.decode(com.zqkj.bsm.system.action.util.StringHelper.noEmpty2(map.get("refer")).trim()) + ",";
            }
            if(map!= null && !com.zqkj.bsm.system.action.util.StringHelper.noEmpty2(map.get("type")).trim().equals(""))
            {
                param += " and fg.type like concat('%',?,'%') ";
                conditionP += URLDecoder.decode(com.zqkj.bsm.system.action.util.StringHelper.noEmpty2(map.get("type")).trim()) + ",";
            }
            if(map!= null && !com.zqkj.bsm.system.action.util.StringHelper.noEmpty2(map.get("sfgk")).trim().equals(""))
            {
                param += " and fg.sfgk like concat('%',?,'%') ";
                conditionP += URLDecoder.decode(com.zqkj.bsm.system.action.util.StringHelper.noEmpty2(map.get("sfgk")).trim()) + ",";
            }
            if(map!= null && !com.zqkj.bsm.system.action.util.StringHelper.noEmpty2(map.get("type")).trim().equals(""))
            {
                param += " and fg.type = ? ";
                conditionP += URLDecoder.decode(com.zqkj.bsm.system.action.util.StringHelper.noEmpty2(map.get("type")).trim()) + ",";
            }
            String sql = "select fg.refer,fg.title,fg.begintime,fg.sfgk,fg.fjname,fg.reference,fg.status,a.truename from flow_gwgd fg LEFT JOIN admin a on  fg.uid = a.admin_id "
                    +" where 1=1  "+param + condition;
            Object[] oArray_count = null;
            if (conditionP != null && !"".equals(conditionP)) 
            {
                conditionP = conditionP.substring(0, conditionP.length() - 1);
                oArray_count = conditionP.split(",");
            }   
            return this.getList_Pre(sql, oArray_count);
         
     }
	
     public void insertLeave(Object[] obj){
    	 String sql = "insert into leave_count (uid,type,day,year,month)values (?,?,?,?,?)";
    	 this.executeInsert(sql, obj);
     }
     
     public List<Map<String, Object>> getAdminYear(int year,String username,int start,int rows){
    	Object[] obj;
    	StringBuffer sb = new StringBuffer();
    	sb.append("select m.* ,");
    	sb.append("IFNULL(n.one, '/') AS one,");
    	sb.append("IFNULL(n.two, '/') AS two,");
    	sb.append("IFNULL(n.three, '/') AS three,");
    	sb.append("IFNULL(n.four, '/') AS four,");
    	sb.append("IFNULL(n.five, '/') AS five,");
    	sb.append("IFNULL(n.six, '/') AS six,");
    	sb.append("IFNULL(n.seven, '/') AS seven,");
    	sb.append("IFNULL(n.eight, '/') AS eight,");
    	sb.append("IFNULL(n.nine, '/') AS nine,");
    	sb.append("IFNULL(n.ten, '/') AS ten,");
    	sb.append("IFNULL(n.eleven, '/') AS eleven,");
    	sb.append("IFNULL(n.twelve, '/') AS twelve");
    	sb.append(" from ");
    	sb.append(" ( ");
    	sb.append("SELECT ");
    	sb.append("IFNULL(b.shij,'/') as shij,");
    	sb.append("IFNULL(b.hunj,'/') as hunj,");
    	sb.append("IFNULL(b.bingj,'/') as bingj,");
    	sb.append("IFNULL(b.chanj,'/') as chanj,");
    	sb.append("IFNULL(b.peichanj,'/') as peichanj,");
    	sb.append("IFNULL(b.sangj,'/') as sangj,");
    	sb.append("IFNULL(b.nianj,'/') as nianj,");
    	sb.append("IFNULL(b.qt,'/') as qt, ");
    	sb.append("IFNULL(b.total, '/') as total, ");
    	sb.append("a.truename,a.admin_id,IFNULL(a.year,'/') as year ");
    	sb.append("from ");
    	sb.append("(SELECT "); 
    	sb.append("	CONCAT(floor(sum(CASE type WHEN '事假' THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END) / 8),'.',mod(sum(CASE type WHEN '事假' THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END),8)) AS shij, ");
    	sb.append("	CONCAT(floor(sum(CASE type WHEN '婚假' THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END) / 8),'.',mod(sum(CASE type WHEN '婚假' THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END),8)) as hunj, ");
    	sb.append("	CONCAT(floor(sum(CASE type WHEN '病假' THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END) / 8),'.',mod(sum(CASE type WHEN '病假' THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END),8)) AS bingj, ");
    	sb.append("	CONCAT(floor(sum(CASE type WHEN '产假' THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END) / 8),'.',mod(sum(CASE type WHEN '产假' THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END),8)) AS chanj, ");
    	sb.append("	CONCAT(floor(sum(CASE type WHEN '陪产假' THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END) / 8),'.',mod(sum(CASE type WHEN '陪产假' THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END),8)) AS peichanj, ");
    	sb.append("	CONCAT(floor(sum(CASE type WHEN '丧假' THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END) / 8),'.',mod(sum(CASE type WHEN '丧假' THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END),8)) AS sangj, ");
    	sb.append("	CONCAT(floor(sum(CASE type WHEN '年假' THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END) / 8),'.',mod(sum(CASE type WHEN '年假' THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END),8)) AS nianj, ");
    	sb.append("	CONCAT(floor(sum(CASE type WHEN '其他' THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END) / 8),'.',mod(sum(CASE type WHEN '其他' THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END),8)) AS qt, ");
    	sb.append("	CONCAT(FLOOR(sum(floor(`day`) * 8 + RIGHT (`day`, 1)) / 8),'.',mod(sum(floor(`day`) * 8 + RIGHT (`day`, 1)),8)) AS total, ");
    	sb.append("uid as admin_id ");
    	sb.append("FROM leave_count WHERE `year` = ? GROUP BY uid )  as b ");
    	sb.append("RIGHT JOIN ");
    	sb.append("(SELECT a.admin_id,a.seq,a.truename,b.`year` FROM admin AS a LEFT JOIN admin_leave AS b ON a.admin_id = b.uid and b.year_time = ?");
    	if(!username.equals("")){
    		sb.append(" where a.truename like concat('%', ?, '%') ");
    	}
    	sb.append(") as a ");
    	sb.append("on a.admin_id = b.admin_id ORDER BY a.admin_id asc limit ?,?");
    	sb.append(" ) as m ");
    	sb.append(" left join ");
    	sb.append(" ( ");
    	sb.append("select ");
    	sb.append("CONCAT(floor(sum(CASE month WHEN 1 THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END) / 8),'.',mod(sum(CASE month WHEN 1 THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END),8)) as 'one', ");
    	sb.append("CONCAT(floor(sum(CASE month WHEN 2 THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END) / 8),'.',mod(sum(CASE month WHEN 2 THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END),8)) as 'two',");
    	sb.append("CONCAT(floor(sum(CASE month WHEN 3 THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END) / 8),'.',mod(sum(CASE month WHEN 3 THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END),8)) as 'three',");
    	sb.append("CONCAT(floor(sum(CASE month WHEN 4 THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END) / 8),'.',mod(sum(CASE month WHEN 4 THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END),8)) as 'four',");
    	sb.append("CONCAT(floor(sum(CASE month WHEN 5 THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END) / 8),'.',mod(sum(CASE month WHEN 5 THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END),8)) as 'five',");
    	sb.append("CONCAT(floor(sum(CASE month WHEN 6 THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END) / 8),'.',mod(sum(CASE month WHEN 6 THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END),8)) as 'six',");
    	sb.append("CONCAT(floor(sum(CASE month WHEN 7 THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END) / 8),'.',mod(sum(CASE month WHEN 7 THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END),8)) as 'seven',");
    	sb.append("CONCAT(floor(sum(CASE month WHEN 8 THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END) / 8),'.',mod(sum(CASE month WHEN 8 THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END),8)) as 'eight',");
    	sb.append("CONCAT(floor(sum(CASE month WHEN 9 THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END) / 8),'.',mod(sum(CASE month WHEN 9 THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END),8)) as 'nine',");
    	sb.append("CONCAT(floor(sum(CASE month WHEN 10 THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END) / 8),'.',mod(sum(CASE month WHEN 10 THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END),8)) as 'ten',");
    	sb.append("CONCAT(floor(sum(CASE month WHEN 11 THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END) / 8),'.',mod(sum(CASE month WHEN 11 THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END),8)) as 'eleven',");
    	sb.append("CONCAT(floor(sum(CASE month WHEN 12 THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END) / 8),'.',mod(sum(CASE month WHEN 12 THEN floor(`day`) * 8 + RIGHT (`day`, 1) ELSE 0 END),8)) as 'twelve',");
    	sb.append("uid from leave_count GROUP BY uid ");
    	sb.append(") as n ");
    	sb.append(" on m.admin_id = n.uid");
    	if(!username.equals("")){
    		obj = new Object[]{year,year,username,start,rows}; 
    	}else{
    		obj = new Object[]{year,year,start,rows};
    	}
    	return this.getList_Pre(sb.toString(),obj);
     }
     
     
     
 	
 	public void saveKq(String json,int year,int month,String orderId,long org_id){
 		List<Map<String, Object>> listResult = getKqList(orderId);
 		if(listResult.size() != 0){
 			this.executeUpdate("delete from admin_kq where orderId = ?",new Object[]{orderId});
 		}
 		String sql = "insert into admin_kq (uid,msg,orderId,year,month,userName,org_id) values (?,?,?,?,?,?,?)";
 		JSONArray array = JSONArray.fromObject(json);
 		ArrayList<Object[]> list = new ArrayList<Object[]>();
 		for (int i = 0; i < array.size(); i++) {
 			JSONObject jsonObject = array.getJSONObject(i);
			Object[] obj = new Object[7];
			obj[0] = jsonObject.get("ADMIN_ID");
			obj[1] = jsonObject.get("val").toString();
			obj[2] = orderId;
			obj[3] = year;
			obj[4] = month;
			obj[5] = jsonObject.get("TRUENAME");
			obj[6] = org_id;
			list.add(obj);
		}
 		this.executeBatch_Pre(sql, list);
 	}
 	
 	
 	public List<Map<String, Object>> getKqList(String orderId){
 		String sql = "select ak.userName as TRUENAME,ak.msg as MSG,ak.uid as ADMIN_ID,ak.year as YEAR,ak.month as MONTH from admin_kq ak,admin a where ak.uid = a.admin_id and ak.orderId = ? order by a.seq asc";
 		return this.getList_Pre(sql,new Object[]{orderId});
 	}
 	
 	public void insertWorkLog(ArrayList<Object[]> list){
 		String sql = "insert work_time (t_date,t_type,uid,t_status,uname) values (?,?,?,?,?)";
 		this.executeBatch_Pre(sql, list);
 	}
 	
 	
 	public void deleteHisWorkLog(String year,String month,String duiList){
 		String sql = "delete FROM work_time WHERE DATE_FORMAT(t_date, '%Y-%m') = ? and uid in ("+duiList.substring(0, duiList.length()-1)+")";
 		System.out.println(String.format("%02d", Integer.parseInt(month)));
 		this.executeUpdate(sql, new Object[]{year+"-"+String.format("%02d", Integer.parseInt(month))});
 	}
}
