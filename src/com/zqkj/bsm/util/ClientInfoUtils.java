package com.zqkj.bsm.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

public class ClientInfoUtils {

	/** 
	 * 根据User-Agent，得到用户浏览器和操作系统信息 
	 *  
	 * @param userAgentInfo 
	 * @return ClientInfo 
	 */
	public static ClientInfo getClientInfo(String userAgentInfo) {
		String info = userAgentInfo.toUpperCase();
		ClientInfo clientInfo = new ClientInfo();
		String[] strInfo = info.substring(info.indexOf("(") + 1,
				info.indexOf(")") - 1).split(";");
		if ((info.indexOf("MSIE")) > -1) {
			clientInfo.setBrowserInfo(strInfo[1].trim());
			clientInfo.setOsInfo(strInfo[2].trim());
		} else {
			String[] str = info.split(" ");
			if (info.indexOf("NAVIGATOR") < 0 && info.indexOf("FIREFOX") > -1) {
				clientInfo.setBrowserInfo(str[str.length - 1].trim());
				clientInfo.setOsInfo(info);
			} else if ((info.indexOf("OPERA")) > -1) {
				clientInfo.setBrowserInfo(str[0].trim());
				clientInfo.setOsInfo(strInfo[0].trim());
			} else if (info.indexOf("CHROME") < 0
					&& info.indexOf("SAFARI") > -1) {
				clientInfo.setBrowserInfo(str[str.length - 1].trim());
				clientInfo.setOsInfo(info);
			} else if (info.indexOf("CHROME") > -1) 
			{
				clientInfo.setBrowserInfo("CHROME");
				//clientInfo.setOsInfo(strInfo[1].trim());
				clientInfo.setOsInfo(info);
			} else if (info.indexOf("NAVIGATOR") > -1) 
			{
				clientInfo.setBrowserInfo(str[str.length - 1].trim());
				clientInfo.setOsInfo(info);
			} 
			else 
			{
				clientInfo.setBrowserInfo("未知浏览器");
				clientInfo.setOsInfo("未知操作系统");
			}
		}
		return clientInfo;
	}
	
	public static String getIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");  
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
            if(ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")){  
                //根据网卡取本机配置的IP  
                InetAddress inet=null;  
                try {  
                    inet = InetAddress.getLocalHost();  
                } catch (UnknownHostException e) {  
                    e.printStackTrace();  
                }  
                ip= inet.getHostAddress();  
            }  
        }  
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
        if(ip!=null && ip.length()>15){   
            if(ip.indexOf(",")>0){  
                ip = ip.substring(0,ip.indexOf(","));  
            }
        }

		return ip;
	}
	
}