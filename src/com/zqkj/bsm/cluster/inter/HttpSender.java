package com.zqkj.bsm.cluster.inter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.PageData;

/**
 * HTTP请求发送器
 * @author admin
 *
 */
public class HttpSender {
	/**
	 * 发送	HTTP 请求
	 * @param method 请求方法：GET PUT POST DELETE
	 * @param url 请求的目标地址
	 * @param stringEntity 请求体内容
	 * @return 响应码和响应体内容
	 */
	public static ClusterResponse send(HttpClient httpclient,String method, String url, String stringEntity) 
	{
		ClusterResponse clusterResponse = new ClusterResponse();
		String result = null;
		//HttpClient httpclient = new DefaultHttpClient();
		// 有新的异常在大改
		try {
			url = CommonUtil.EncodeUrl(url);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			HttpUriRequest httpMethod = null;
			HttpResponse httpResponse = null;
			if ("GET".equals(method)) {
				httpMethod  = new HttpGet(url);
			} else if ("POST".equals(method)) {
				httpMethod  = new HttpPost(url);
				StringEntity entity = new StringEntity(stringEntity,"utf-8");
				entity.setContentType("application/json; charset=UTF-8");
				//entity.setContentType("text/html; charset=UTF-8");
				
				((HttpPost)httpMethod).setEntity(entity);
			} else if ("PUT".equals(method)) {
				httpMethod = new HttpPut(url);
				StringEntity entity = new StringEntity(stringEntity,"utf-8");
				entity.setContentType("application/json; charset=UTF-8");
				//entity.setContentType("text/html; charset=UTF-8");
				((HttpPut)httpMethod).setEntity(entity);
			} else if ("DELETE".equals(method)) {
				httpMethod = new HttpDelete(url);
			}
			httpResponse = httpclient.execute(httpMethod);
			HttpEntity resEntity = httpResponse.getEntity();
			int responseCode = httpResponse.getStatusLine().getStatusCode();
			result = EntityUtils.toString(resEntity);
			EntityUtils.consume(resEntity);
			clusterResponse.setResponseCode(responseCode);
			if(responseCode==500 || responseCode==400 || responseCode==404 )
			{
				JSONObject resultJson = JSONObject.fromObject(result);
				if(resultJson.containsKey("error"))
				{
				   JSONObject item = resultJson.getJSONObject("error");
					if(item.containsKey("code"))
					{
					  clusterResponse.setSubResponseCode(item.getInt("code"));
					}
					if(item.containsKey("description"))
					{
					  clusterResponse.setRes_detail(item.getString("description"));
					}
				}

			}
			clusterResponse.setResultBody(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clusterResponse;
	}
	
	
	/**
	 * 维持session登陆的http
	 * @param method
	 * @param url
	 * @param stringEntity
	 * @param cookie
	 * @return
	 */
	public static ClusterResponse send(String method, String url, String stringEntity) {
		ClusterResponse clusterResponse = new ClusterResponse();
		String result = null;
		HttpClient httpclient = HttpClientPool.getHttpClient();
		try {
			url = CommonUtil.EncodeUrl(url);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}// 有新的异常在大改
		try {
			HttpUriRequest httpMethod = null;
			HttpResponse httpResponse = null;
			if ("GET".equals(method)) {
				httpMethod  = new HttpGet(url);
			} else if ("POST".equals(method)) 
			{				
				httpMethod  = new HttpPost(url);
				StringEntity entity = new StringEntity(stringEntity,"utf-8");
				entity.setContentType("application/json; charset=UTF-8");
				((HttpPost)httpMethod).setEntity(entity);
			} else if ("PUT".equals(method)) 
			{
				httpMethod = new HttpPut(url);
				StringEntity entity = new StringEntity(stringEntity,"utf-8");
				entity.setContentType("application/json; charset=UTF-8");
				((HttpPut)httpMethod).setEntity(entity);
			} else if ("DELETE".equals(method)) 
			{
				httpMethod = new HttpDelete(url);
			}
			httpResponse = httpclient.execute(httpMethod);
			HttpEntity resEntity = httpResponse.getEntity();
			int responseCode = httpResponse.getStatusLine().getStatusCode();
			result = EntityUtils.toString(resEntity);
			EntityUtils.consume(resEntity);
			clusterResponse.setResponseCode(responseCode);
			if(responseCode==500 || responseCode==400 || responseCode==404 )
			{
				if(result!=null && !result.equals(""))
				{
					JSONObject item = JSONObject.fromObject(result);
						if(item.containsKey("code"))
						{
						  clusterResponse.setSubResponseCode(item.getInt("code"));
						}
						if(item.containsKey("description"))
						{
						  clusterResponse.setRes_detail(item.getString("description"));
						}
				}					
			}
			clusterResponse.setResultBody(result);
		} catch (Exception e) 
		{
			clusterResponse.setResultBody("");
			clusterResponse.setResponseCode(404);
			clusterResponse.setRes_detail("集群服务器不可达");
			//e.printStackTrace();
		}
		return clusterResponse;
	}
	
	
	
	public static ClusterResponse getResByte(String method, String url, String stringEntity) 
	{
		ClusterResponse clusterResponse = new ClusterResponse();
		byte[] result = null;
		HttpClient httpclient = HttpClientPool.getHttpClient();
		try {
			url = CommonUtil.EncodeUrl(url);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}// 有新的异常在大改
		try {
			HttpUriRequest httpMethod = null;
			HttpResponse httpResponse = null;
			if ("GET".equals(method)) {
				httpMethod  = new HttpGet(url);
			} else if ("POST".equals(method)) 
			{				
				httpMethod  = new HttpPost(url);
				StringEntity entity = new StringEntity(stringEntity,"utf-8");
				entity.setContentType("application/json; charset=UTF-8");
				((HttpPost)httpMethod).setEntity(entity);
			} else if ("PUT".equals(method)) 
			{
				httpMethod = new HttpPut(url);
				StringEntity entity = new StringEntity(stringEntity,"utf-8");
				entity.setContentType("application/json; charset=UTF-8");
				((HttpPut)httpMethod).setEntity(entity);
			} else if ("DELETE".equals(method)) 
			{
				httpMethod = new HttpDelete(url);
			}
			httpResponse = httpclient.execute(httpMethod);
			HttpEntity resEntity = httpResponse.getEntity();
			int responseCode = httpResponse.getStatusLine().getStatusCode();
			Header[] heads = httpResponse.getAllHeaders();
			for(Header head:heads)
			{
				if(head.getName().equals("Content-Length"))
				{
					clusterResponse.setContent_Length(head.getValue());
				}
				else if(head.getName().equals("Content-Type"))
				{
					clusterResponse.setContent_Type(head.getValue());
				}
			}
			result = EntityUtils.toByteArray(resEntity);
			EntityUtils.consume(resEntity);
			clusterResponse.setResponseCode(responseCode);
			if(responseCode==500 || responseCode==400 || responseCode==404 )
			{

					clusterResponse.setBody_by(null);
			}
			else
			{
				if(result!=null && result.length>0)
				{
					clusterResponse.setBody_by(result);
				}
				else
				{
					clusterResponse.setBody_by(null);
				}
			}
		} catch (Exception e) 
		{
			clusterResponse.setResultBody("");
			clusterResponse.setBody_by(null);
			clusterResponse.setResponseCode(404);
			clusterResponse.setRes_detail("集群服务器不可达");
			//e.printStackTrace();
		}
		return clusterResponse;
	}
	
	
	
	/**
	 * 获取集群的截图数据
	 * @param job_id
	 * @param subjob_id
	 * @return
	 */
//	public static ClusterResponse getPicByte(String job_id,String subjob_id,String ip,String port)
//	{
//		String url = SysPara.getPictureUrl.replace("{ip:port}", ip+":"+port);
//             url =   url.replace("{job_id}", job_id);
//             url =   url.replace("{subjob_id}", subjob_id);
//             //System.out.println("getPicByte="+url); 
//        return HttpSender.getResByte("GET", url, "");
//	}
//	
		
	   public static void main(String[] args) 
	   { 
		    System.out.println("请输入选择：");
		    
		    Scanner sca = new Scanner(System.in);
		    String input="";
		    HttpClient httpclient = HttpClientPool.getHttpClient();
		    while(!input.equals("bye"))
		    {
		     input = sca.next();
		     //System.out.println(input);
		     if(input.equals("1"))
		     {
//		        String url = "http://localhost:8080/portal/client!clientLogin.action";//论坛的登陆页面 
//		        String url_1 = "http://localhost:8080/portal/client!getAccount.action";
//		        String url_2 = "http://localhost:8080/portal/client!showMission.action";
//		        String url_3 = "http://localhost:8080/portal/client!queryMission.action";
//		        String url_4 = "http://localhost:8080/portal/autoup!getFilelist.action?version=3.0.7.0";
		        String url_5 = "http://112.25.139.27:8066/DataService/serviceconfig/eventQuery.action?pageSize=30&pageNo=1";
		    	//StringBuffer buffer = new StringBuffer();
		    	
//		    	ClusterResponse res=  HttpSender.send(httpclient,"POST", url_4, "");
//		    	System.out.println(res.getResponseCode());
//		    	//System.out.println(res.getRes_detail());
//		    	System.out.println(res.getResultBody());
		        
		        
		        
		    	StringBuffer buffer = new StringBuffer();
		    	
		    	buffer.append("{");
		    	buffer.append("'id' : '25',");
		    	buffer.append("'nickname' : '赞奇0242高峰',");
		    	buffer.append("'password' : '123456',");
		    	buffer.append("'client_version' : '3.1.6.0'}");
		    	ClusterResponse res=  HttpSender.send(httpclient,"POST", url_5, "");
		    	System.out.println(res.getResponseCode());
		    	//System.out.println(res.getResultBody());
		    	String json = res.getResultBody().substring(5,res.getResultBody().length()-1);
		    	System.out.println(json);
		    	JSONObject item =JSONObject.fromObject(json);
		    	int pageNum=0;
		    	int pageSize=30;
		    	int rowCount=0;
				if(item.containsKey("pageNo"))
				{
					pageNum = item.getInt("pageNo");
				}
				if(item.containsKey("pageSize"))
				{
					pageSize = item.getInt("pageSize");
				}	
				if(item.containsKey("rowCount"))
				{
					rowCount = item.getInt("rowCount");
				}	
				JSONArray jsonArray = item.getJSONArray("search_result");
				List<Map<String,Object>> mapListJson = (List)jsonArray;
				Map<String,Object> map = mapListJson.get(0);
				System.out.println(map.get("stXcid"));   
				System.out.println(mapListJson.size());    	
		     }
		     else if(input.indexOf("ZQ-XSXR") >=0 )
		     {
		    	   String trans_id = input;
		    	    HttpClient httpclient_ = HttpClientPool.getHttpClient();
			    	StringBuffer buffer3 = new StringBuffer();
			    	buffer3.append("{");
			    	buffer3.append("'trans_id' : '"+trans_id+"',");
			    	buffer3.append("'output_path' : 'D:/render/42/ZQ-XSXR-140701-2121/C/temp/plugin1.mb',");
			    	buffer3.append("'input_path' : 'D:/result/42/ZQ-XSXR-140701-2121/'");
			    	buffer3.append("}");
			    	String url = "http://localhost:8080/portal/store";
			    	ClusterResponse res_3=  HttpSender.send(httpclient_,"POST", url, buffer3.toString());
			    	System.out.println(res_3.getResponseCode());
			    	//System.out.println(res_3.getRes_detail());
			    	System.out.println(res_3.getResultBody());
		     }
		     else  if(input.equals("3"))
		     {
		    	    String url_3 = "http://localhost:8080/portal/client!cancleDD.action";
			    	StringBuffer buffer3 = new StringBuffer();
			    	buffer3.append("{");
			    	buffer3.append("'cancle' : {");
			    	buffer3.append("'trans_id' : 'ZQ-XSXR-140507-2013',");
			    	buffer3.append("'detail' : '用户取消'");
			    	buffer3.append("}}");
			    	ClusterResponse res_3=  HttpSender.send(httpclient,"POST", url_3, buffer3.toString());
			    	System.out.println(res_3.getResponseCode());
			    	//System.out.println(res_3.getRes_detail());
			    	System.out.println(res_3.getResultBody());
			    	
		     }
		     else  if(input.equals("4"))
		     {
		    	    String url_3 = "http://localhost:8080/portal/client!applyBFrame.action";
			    	StringBuffer buffer3 = new StringBuffer();
			    	buffer3.append("{");
			    	buffer3.append("'mul_flag' : 0,");
			    	buffer3.append("'name' : '补帧',");
			    	buffer3.append("'frames' : '10-200,708-600:-6',");
			    	buffer3.append("'trans_id' : 'ZQ-XSXR-140508-2015',");
			    	buffer3.append("'total_frame' : 200");
			    	buffer3.append("}");
			    	ClusterResponse res_3=  HttpSender.send(httpclient,"POST", url_3, buffer3.toString());
			    	System.out.println(res_3.getResponseCode());
			    	//System.out.println(res_3.getRes_detail());
			    	System.out.println(res_3.getResultBody());		    
			    	
			    	String url_1 = "http://localhost:8080/portal/client!getAccount.action";
			    	ClusterResponse res_=  HttpSender.send(httpclient,"POST", url_1, "");
			    	System.out.println("账户余额："+res_.getResultBody());
		     }
		     //测试客户端的实时上传网速与实时上传大小
		     else if(input.equals("6"))
		     {
		    	 String url_6 = "http://localhost:8080/portal/client!setRealtimeNet.action";//实时获取上传网速与大小
		    	 StringBuffer buffer = new StringBuffer();
			    	buffer.append("{");
			    	buffer.append("'trans_id' : 'ZQ-XSXR-141211-4608',");
			    	buffer.append("'ss_speed' : '666',");
			    	buffer.append("'ss_size' : '123456'}");
			    	ClusterResponse res_6=  HttpSender.send(httpclient,"POST", url_6, buffer.toString());
			    	System.out.println(res_6.getResponseCode());
			    	System.out.println(res_6.getResultBody());
		     }
		    }
	    	
	    } 
}
