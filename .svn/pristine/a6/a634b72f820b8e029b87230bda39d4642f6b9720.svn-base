package com.zqkj.bsm.webservice;
import java.math.BigDecimal;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.zqkj.bsm.sms.SMSClient;


public class SendSMSImpl 
{
	Logger log = Logger.getLogger(SendSMSImpl.class);

	public boolean sendsms_bywebs(String phonenum,String  notes,String sys_name)
	{
		
		
		boolean re_bz = false;
		if(isNumeric(phonenum))
		{
			re_bz = SMSClient.sendSMS(sys_name,phonenum,notes);
		}
		
		return re_bz;
	}
	
	
	private boolean isNumeric(String str)
	{ 
		   Pattern pattern = Pattern.compile("[0-9]*"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
	}

	  
     /**
      * 是否企业子账号
      * //1普通用户，2企业母账号，3企业子账号，4母账号的外部客户，5运维账号，9后台管理员		
      * @param usr_map
      * @return 
      */
     private long isSubAccount(Map<String, Object> usr_map)
     {
     	long parent_user_id = 0;
 		if(String.valueOf(usr_map.get("type".toUpperCase())).equals("3"))
 		{
 			parent_user_id = ((BigDecimal)usr_map.get("parent".toUpperCase())).longValue();
 		}
         return parent_user_id;
     }  
     
     
     

    /**
      * 项目关联具体任务信息查询
      * @param projectSeqId 项目单号  
      * @param name 任务名称
      * @param renderType 任务类型  
      * @param renderStatus 渲染状态
      * @param startTime 开始时间  
      * @param endTime 结束时间
      * @return 
      */
//     public  String lineToProjectSelect(String projectSeqId,String name,int renderType,String renderStatus,
//    		 String startTime,String endTime,int pageNum,int pageSize)
//     {         
// 	        ClientDao dao = ClientDao.getInstance();
//// 	        log.info("查找项目对应任务信息"); 
// 	        //分页封装方法
// 	        String pageNums = Integer.toString(pageNum);
// 	        OraPaginatedList list_tmp = dao.lineToProjectSelect(projectSeqId,renderStatus,startTime,endTime,pageNums,pageSize,name);
// 	        List<Map<String, Object>> list = list_tmp.getList();
// 	        // 分页对象 
// 	        PageData pg = CommonUtil.fomateResult(list, pageNum + 1, pageSize, list_tmp.getFullListSize());   
// 			String json = JSONValue.toJSONString(CommonUtil.formatFGMap_client(pg));;
// 			if (null == json) 
// 			{
// 				return "";
// 			}
// 			else
// 			{
// 				return json;
// 		    }       
//     }
//     
//     /**
//      * 网站赠送金额
//      * @param userid
//      * @param zs_money
//      * @param remark
//      * @return
//      */
//     public boolean getGiveMoney(int userid,float zs_money,String remark,String orderNo)
//     {
//     	ClientDao clientDao = ClientDao.getInstance();
//     	boolean result = clientDao.insertGiveMoney(userid,zs_money,remark,orderNo);
//     	log.info("网站赠送充值，用户id："+ userid+"，赠送金额："+zs_money+"充值状态："+result+",赠送记录与实充的关联:"+orderNo);
//     	return result;
//     }
//     
//    /**
//     * 网站接口 根据省份获得主业务员的ID，默认百分比为100%
//     * @param province  省份
//     * @return 主业务员ID
//     */
//    public int getSalesmanId(String province)
//    {       
//    	int salesmanId = -1;
//    	if (!StringUtils.isEmpty(province)){
//    		ClientDao cd = ClientDao.getInstance();
//    		salesmanId = cd.getSalesmanId(province);
//    	}
//    	return salesmanId;
//    }
//    
//    /**
//     * 套餐预约查询(根据用户id)
//     * @param userid
//     * @return
//     */
//    public Map<String,Object> queryRenewInfo(int userid){
//    	ClientDao clientDao = ClientDao.getInstance();
//    	return clientDao.queryRenewDetail(userid);
//    }
//    
//    public boolean renderMissionYKF(String trans_id,Long user_id,double money,double balance_sc,double balance_zs,int res_fee)
//    {
//    	ClientDao clientDao = ClientDao.getInstance();	
//    	log.info("接收到sserver的预扣费通知消息trans_id="+trans_id+",user_id="+user_id+",money="+money+",res_fee="+res_fee);
//    	return clientDao.YU_KFSW(trans_id, user_id, money, balance_sc, balance_zs, res_fee);
//    }
//    
//    public boolean renderMissionYKFRoll(String trans_id,Long user_id,double money,String error_msg,int res_fee)
//    {
//    	ClientDao clientDao = ClientDao.getInstance();	
//    	log.info("接收到sserver的预扣费回滚通知消息trans_id="+trans_id+",user_id="+trans_id+",money="+money+",res_fee="+res_fee);
//    	return clientDao.YU_KFSW_ROLL(trans_id, user_id, money, error_msg, res_fee);
//    }
//      
//    /**
//     * sserver发送计费消息到监控中心，监控中心只负责计费，任务状态以后都不处理
//     * @param map
//     */
//    public boolean chargeNotify(String Josn_map)
//    {
//    	log.info("接收到sserver的计费通知json格式消息="+Josn_map);
//    	Map<String, Object> map = NotificationsList.getInstance().jsontoMap(Josn_map);
//    	ClientDao clientDao = ClientDao.getInstance();
//    	return clientDao.charge(map);
//    }
    
    
}
