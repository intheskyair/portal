package com.zqkj.bsm.webservice;
import java.util.Map;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
public class TestWebservice 
{

    @SuppressWarnings({ "unchecked", "unchecked" })
	public static void main(String[] args) 
    {

    	 

        try {

               String endpoint = "http://10.23.100.51/portal/services_2/SendSMSImpl?wsdl";

               //直接引用远程的wsdl文件

              //以下都是套路 
               Service service = new Service();
               Call call = (Call) service.createCall();
               call.setTargetEndpointAddress(endpoint);
//               call.setOperationName("queryMission");//WSDL里面描述的接口名称
//		    	StringBuffer buffer3 = new StringBuffer();
//		    	buffer3.append("{");
//		    	buffer3.append("'page' : 1,");
//		    	buffer3.append("'pageSize' : 20,");
//		    	buffer3.append("'user_id' : 42");
//		    	buffer3.append("}");
//               String temp = buffer3.toString();
               //String result = (String)call.invoke(new Object[]{temp});
               //给方法传递参数，并且调用方法         
               
//               
//               call.setOperationName("sendsms_bywebs");
//               boolean result = (Boolean)call.invoke(new Object[]{"13776823296","测试","渲染业务"});
               
   
               
//             call.setOperationName("showMission");
//             Map<String, Object> result = (Map<String, Object>)call.invoke(new Object[]{"ZQ-XSXR-140613-2029",new Long(81)});
//               System.out.println("result is "+result.get("frames".toUpperCase()));
//               System.out.println("result is "+result.get("trans_id".toUpperCase()));
               call.setOperationName("renderMissionYKF");
               call.setTimeout(1000);
               boolean salseid =(boolean) call.invoke(new Object[]{"ZQ-XSXR-150924-6434", Long.valueOf(42), Double.valueOf(9), 0.0D, 0.0D, 0});
                 System.out.println("result is "+salseid);
        }

        catch (Exception e) 
        {
               System.err.println(e.toString());                           
        }

 }
}
