package com.cudatec.flow.framework.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zqkj.bsm.dal.dboper.DbOperator;
import com.zqkj.bsm.system.action.page.OraPaginatedList;
import com.zqkj.bsm.util.CommonUtil;

public class PersonalProcessDao extends DbOperator{
	 Logger log = Logger.getLogger(PersonalProcessDao.class);
	    private static PersonalProcessDao instance;
	    public static PersonalProcessDao getInstance()
	    {
	        if(instance ==null)
	        {
	            instance = new PersonalProcessDao();
	        }
	        return instance;
	    }
	    
	    /**
	     * 个人任务查询
	     * @param userid
	     * @return 菜单标题list
	     */
	    public OraPaginatedList getPerProcessList(Map<String, Object> param)
	    {
	    	//TODO
	        int pageSize = (Integer)param.get("pageSize");
	        int pageNum = (Integer)param.get("pageNum");
	        String userName = CommonUtil.nullToDefault(param.get("userName"), "");
	        String personalTop = CommonUtil.nullToDefault(param.get("personalTop"), "");
	        String startTime = CommonUtil.nullToDefault(param.get("startTime"), "");
	        String endTime = CommonUtil.nullToDefault(param.get("endTime"), "");
	        String processId = CommonUtil.nullToDefault(param.get("processId"), "");
	        String title = CommonUtil.nullToDefault(param.get("title"), "");
	        String condition = "";
	        String conditionP = "";
	      
	        if (!"".equals(userName)) {
	            condition += " and t.creator = ?";
	            conditionP += userName+ ",";
	        }
	        if (!"".equals(personalTop)) {
	            condition += " and p.display_Name like concat('%', ?, '%')";
	            conditionP += personalTop+ ",";
	        }
	        if (!"".equals(startTime)) {
                condition += " and date_format(t.create_Time, '%Y-%m-%d') >= ?";
                conditionP += startTime + ",";
            }
            if (!"".equals(endTime)) {
                condition += " and date_format(t.create_Time, '%Y-%m-%d') <= ?";
                conditionP += endTime + ",";
            }
            if (!"".equals(processId)) {
                condition += " and p.id= ?";
                conditionP += processId + ",";
            }
            if (!"".equals(title)) 
            {
                condition += " and t.id in(select c.orderId from flow_form c where c.html_key='title' and  c.html_value like concat('%', ?, '%'))";
                conditionP += title + ",";
            }
	        //condition += " order by t.create_Time desc";
	        String[] oArray = null;
	        if (conditionP != null && !"".equals(conditionP)) 
	        {
	            conditionP = conditionP.substring(0, conditionP.length()-1);
	            oArray = conditionP.split(",");
	        }
	        String sql = "SELECT t.process_Id,t.id,a.truename as creator,t.create_Time,t.order_State,t.end_Time,t.variable,p.display_Name as PROCESS_NAME,wt.display_Name as nextDisplayName "+
		                  " FROM wf_hist_order t  LEFT JOIN wf_process p ON p.id = t.process_id LEFT JOIN wf_task AS wt ON wt.order_Id = t.id LEFT JOIN admin AS a ON a.username = t.creator WHERE 1 = 1 "+condition;
	        OraPaginatedList list = new OraPaginatedList(sql, oArray);
	        list.setSortCriterion("order by create_Time desc");
	        list.setCurrentPage(pageNum);
	        list.setObjectsPerPage(pageSize);
	        return list;
	    }
		
		

		/**
		 * 获得个人任务详情
		 * @param invoiceno
		 * @return
		 */
		public List<Map<String, Object>> getPersonalList(String id) {
	        Object[] object = new Object[1];
	        object[0] = id;
	        String sql = "SELECT o.creator,p.display_Name AS process_Name,t.display_Name AS task_Name,t.operator,o.process_Id,t.create_Time AS task_Create_Time,"+
			"t.finish_Time AS task_End_Time,t.expire_Time AS task_Expire_Time,q.result,q.operate_time,q.content,t.order_Id,t.id AS id,t.id AS task_Id,"+
			"o.create_Time AS order_Create_Time,o.expire_Time AS order_Expire_Time,o.variable AS order_Variable,t.task_Type,t.perform_Type,t.variable AS task_Variable"+
		    " FROM  wf_hist_task t LEFT JOIN wf_hist_order o ON t.order_id = o.id LEFT JOIN wf_process p ON p.id = o.process_id LEFT JOIN flow_approval q on q.task_id=t.id"+
		    " WHERE 1 = 1 AND t.task_Type = 0 AND o.id = ?"+
		    "ORDER BY t.finish_Time ASC";
	        List<Map<String, Object>> result = this.getList_Pre(sql, object);
			return result;
		}
	    
	    
		
}
