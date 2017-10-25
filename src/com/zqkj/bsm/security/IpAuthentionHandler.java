package com.zqkj.bsm.security;
import javax.servlet.http.HttpServletRequest;  
import org.apache.axis.AxisFault; 
import org.apache.axis.MessageContext; 
import org.apache.axis.handlers.BasicHandler; 
import org.apache.axis.transport.http.HTTPConstants; 
import org.apache.log4j.Logger;

import com.zqkj.bsm.system.action.SysPara;
public class IpAuthentionHandler extends BasicHandler
{

  
	 private static final long serialVersionUID = 1L;
	 private Logger log = Logger.getLogger(IpAuthentionHandler.class); 
	 
	 public void invoke(MessageContext msgContext)throws AxisFault
	 {   
		 //String status = (String)this.getOption("status");   
		 //System.out.println("HelloWorldHandler's status is: "+status);      
		 log.info("webservice开始ip认证：service>>  "+msgContext.getSOAPActionURI()+"/"+msgContext.getTargetService());   
		 HttpServletRequest request = (HttpServletRequest)msgContext.getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST);   
		 String remoteAddr = request.getRemoteAddr();   
		 log.info("客户端ip："+remoteAddr);			 
		 String[] ips = SysPara.webSeriviceAllowIp.split(",");
		 if(SysPara.webSeriviceAllowIp.trim().equals("") || ips.length == 0){
			 log.info("请指定校验的客户端ip！");
			 throw new AxisFault("webservice暂不对外开放.");
		 }
		 boolean flag = true;	 
//		 boolean flag = false;
//		 for(int i=0,len=ips.length;i<len;i++)
//		 {
//			 if(ips[i].trim().equals(remoteAddr.trim())){
//				 flag = true;
//				 break;
//			 }
//		 }
		 if(!flag){
			 log.info("ip验证不通过！"); 					 
			 throw new AxisFault("wrong ip:"+remoteAddr);
		 }
		 //Pattern p = Pattern.compile(regx);   
//		 if("1".equals(switcher))
//		 {    
//			 if(regx!=null && regx!="")
//			 {     
//				 if(set.contains(remoteAddr.trim()))   
//				 {
//					 //System.out.println("ip验证通过！");    
//				 }
//				 else
//				 {     
//					    log.info("ip验证不通过！"); 					 
//					    throw new AxisFault("Server.Unauthenticate", Messages.getMessage("wrong ip:"+remoteAddr), null, null); 
//				 }   
//			}
//			else
//			{
//				log.info("请指定校验的客户端ip！");
//				throw new AxisFault();
//			}
//		}	
	 }
}
