package com.zqkj.bsm.sms;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import com.zqkj.bsm.sms.dao.SmsDao;
import com.zqkj.bsm.util.ConfigUtil;

public class SMSClient 
{
	private static final String ACCOUNT = ConfigUtil.getValue("account");
	private static final String PASSWORD = ConfigUtil.getValue("password");
	private static final String POST_URL = ConfigUtil.getValue("url");
	private static final String COMID = ConfigUtil.getValue("comid");
	private static Logger log = Logger.getLogger(SMSClient.class);
	private static SmsDao dao = SmsDao.getInstance();
	
	
	/**
 * 			<returnstatus>status</returnstatus> ---------- 返回状态值：成功返回Success 失败返回：Faild
			<message>message</message> ---------- 返回信息：见下表
			<remainpoint> remainpoint</remainpoint> ---------- 返回余额			
			<taskID>taskID</taskID>  -----------  返回本次任务的序列ID
			<successCounts>successCounts</successCounts> --成功短信数：当成功后返回提交成功短信数
	 * @param mobile
	 * @param content
	 * @return
	 */
	public static boolean sendSMS(String sys_name,String mobile, String content) 
	{
		HttpClient client = new DefaultHttpClient();
		// 百度IP定位APi
		HttpPost get = new HttpPost(POST_URL + "?action=send&userid=" + COMID + "&account=" + ACCOUNT + "&password="
				+ PASSWORD + "&mobile=" + mobile + "&content=" + content);
		log.info("开始发送短信，号码" + mobile + "，内容" + content);
		HttpResponse res;
		StringBuffer sb = new StringBuffer();
		try 
		{
			res = client.execute(get);
			InputStream inputStream = res.getEntity().getContent();
			InputStreamReader inputReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferReader = new BufferedReader(inputReader);
			String inputLine = null;
			while ((inputLine = bufferReader.readLine()) != null) {
				sb.append(inputLine);
			}
			log.info("运营商返回结果XML=" + sb.toString());
			Map<String, Object> map_res = SMSClient.ReadConfigXml(sb.toString());
			map_res.put("phone", mobile);
			map_res.put("content", content);
			map_res.put("sys_name", sys_name);
			boolean inser_bz = dao.addSMS(map_res);
			log.info("保存短信到数据库状态" + inser_bz+"mobile="+mobile+"运营商返回="+map_res.get("returnstatus"));
			bufferReader.close();
			inputReader.close();
			inputStream.close();
		} catch (IOException e) 
		{
			Map<String, Object> map_res = new HashMap<String, Object>();
			map_res.put("phone", mobile);
			map_res.put("content", content);
			map_res.put("sys_name", sys_name);
			map_res.put("returnstatus", "failure");
			map_res.put("message", "接口不可达");
			boolean inser_bz = dao.addSMS(map_res);
			log.info("保存短信到数据库状态" + inser_bz+"mobile="+mobile+"运营商返回="+map_res.get("returnstatus"));
			return false;
		}
		finally
		{
		   client.getConnectionManager().shutdown();
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> ReadConfigXml(String url) 
	{
		Map<String, Object> param = new HashMap<String, Object>();
		Document doc = null; 
		try 
		{
			doc = DocumentHelper.parseText(url);   
			Element root = doc.getRootElement();  
			List list = root.elements();
			for (int i = 0,len=list.size(); i < len; i++) 
			{
				  //依次取每个"db"元素 
				  Element dbnode = (Element)list.get(i);
				  param.put(dbnode.getName(), dbnode.getTextTrim());
			}
			
		}
		catch (DocumentException e2) 
		{   // TODO 自动生成 catch 块           
			e2.printStackTrace();         
			
		}   
		return param;
	}
	
	
	
	public static void main(String[] args) throws IOException {
		//SMSClient.sendSMS("13918293138", "測試新接口-");
		/**
		 * 获取URL下得所有cookie
		 */
		/*
		 * String cookieVal = null ; String key = null; String cookies = ""; //
		 * System.out.println("url="+request.getRequestURL().toString()); //
		 * System.out.println("uri="+request.getRequestURI()); URL url = new
		 * URL("http://www.taobao.com");
		 * 
		 * HttpURLConnection httpURLConnection =
		 * (HttpURLConnection)url.openConnection(); for (int i = 1; (key =
		 * httpURLConnection.getHeaderFieldKey(i)) != null; i++) { if
		 * (key.equalsIgnoreCase("set-cookie")){ cookieVal =
		 * httpURLConnection.getHeaderField(i);
		 * System.out.println("cookieVal("+i+")="+cookieVal); cookieVal =
		 * cookieVal.substring(0,cookieVal.indexOf(";")); cookies = cookies +
		 * cookieVal +";"; } } System.out.println("cookie = " + cookies);
		 */
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version='1.0' encoding='utf-8' ?>");
		buffer.append("<returnsms>");
		buffer.append("<returnstatus>sucess</returnstatus>");
		buffer.append("<message>ok</message>");
		buffer.append("<remainpoint>124</remainpoint>");
		buffer.append("<taskID>505555</taskID>");
		buffer.append("<successCounts>1</successCounts>");
		buffer.append("</returnsms>");
		Map<String, Object> map = SMSClient.ReadConfigXml(buffer.toString());
		 System.out.println(map.get("returnstatus"));
		 System.out.println(map.get("message"));
		
	}
}
