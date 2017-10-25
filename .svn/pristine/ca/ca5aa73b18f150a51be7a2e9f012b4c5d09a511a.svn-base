package com.zqkj.bsm.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONValue;
public class Tools {
	private static Tools tools=null;
	private Tools(){}
	public static Tools getInstance(){
		if(tools==null){
			tools=new Tools();
		}
		return tools;
	}
	public  String getSMSErrorReason(String errorCode) {
		Properties pro = new Properties();
		InputStream stream =null;
		try {
			stream=getClass().getResourceAsStream(
					"/com/curender/dal/dbconfig/smscode.properties");
			pro.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pro.getProperty(errorCode);
	}
	
	public String getIp(HttpServletRequest request){
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0
				|| "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	/**
	 * 充值订单号
	 * @param userId
	 * @return
	 */
	public  String getMerOrderNum(long userId){
		StringBuffer buffer=new StringBuffer();
		buffer.append(userId);
		buffer.append("CUP");
		buffer.append(getNowStr());
		buffer.append(getRandomInt());
		return buffer.toString();
	}
	/**
	 * 当前时间的字符串形式
	 * @return
	 */
	public  String getNowStr(){
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(new Date());
	}
	public  int getRandomInt() {
		return (int) (Math.random() * 100);
	}
	/**
	 * 根据总记录和分页规模算出页数
	 * @param total
	 * @param size
	 * @return
	 */
	public  int getTotalPage(int total,int size){
		int totalPage=0;
		if(total%size!=0){
			totalPage=total/size+1;
		}else{
			totalPage=total/size;
		}
		return totalPage;
	}
	/**
	 * 更新session余额
	 * @param session
	 * @param 渲染后台用户id
	 */
//		public boolean updateSessionBalance(HttpSession session,long suserId) {
//			SUser sUser = ServerUser.getInstance().getSUser(suserId);
//			if(sUser!=null){
//				session.setAttribute("balance", sUser.getBalance());
//				return true;
//			}else{
//				session.setAttribute("balance", 0);
//				return false;
//			}
//		}
	/**
	 * 页面显示5页的分页方法
	 * @param pageNo_int当前�?
	 * @param totalPage总页�?
	 * @param pages
	 * @return
	 */
	public   String[] getPages(int pageNo_int,int totalPage, String[] pages) {
		int totalPage_int = 0;
		totalPage_int=totalPage;
		int begin=1;
		int end=0;
		int minus=0;
		if(pageNo_int!=0){
			if(pageNo_int/5>0){//当前页大�?
				begin=pageNo_int-2;
				minus=totalPage_int-pageNo_int;
				switch (minus) {
					case 0:begin=begin-2;
					break;
					case 1 :begin=begin-1;
						break;
				}
				for(int i=0;i<5;i++){
					pages[i]=begin+i+"";
				}
			}else{//当前页小�?
				minus=5-pageNo_int;//�?小多�?
				switch (minus) {
					case 0 :
						if(totalPage_int-5>=2){
							end=7;
						}else if(totalPage_int-5==1){
							end=6;
						}else{
							end=pageNo_int;
						}
						break;
					case 1 :
						if(totalPage_int-5>=1){
							end=6;
						}else{
							end=totalPage_int;
						}
						break;
					default :
						if(totalPage_int-5>=1){
							end=5;
						}else{
							end=totalPage_int;
						}
						break;
				}
				if(end>=5){
					for(int i=0;i<5;i++){
						pages[i]=end+i-4+"";
					}
				}else{
					pages=new String[end];
					for(int i=0;i<end;i++){
						pages[i]=i+1+"";
					}
				}
				}
		}
		return pages;
	}
	/**
	 * 按照给定的long数和格式返回字符串时间
	 * @return
	 */
	public  String longToString(long time, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(new Date(time));
	}
	/**
	 * 按照给定的字符串时间返回long
	 * @return
	 */
	public long stringToLong(String time){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		long timeL=0;
		try {
			 date = dateFormat.parse(time);
			 timeL=date.getTime();
		} catch (ParseException e) {
			System.out.println("time is null format error,set value=0");
		}
		return timeL;
	}
	
    /**
     * 6位手机动态码
     * @return
     */
    public String getSMSCode(){
    	StringBuffer buffer=new StringBuffer();
    	Random random=new Random();
    	for(int i=0; i<6;i++){
    		buffer.append(random.nextInt(10));
    	}
    	return buffer.toString();
    }
	public String getMD5(String value) {
		MessageDigest md = null;
		try {
			byte[] valueByte = value.getBytes();
			md = MessageDigest.getInstance("MD5");
			md.update(valueByte);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		return toHex(md.digest());
	}
	// 将传递进来的字节数组转换成十六进制的字符串形式并返回
	private String toHex(byte[] buffer) {
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
		}
		return sb.toString();
	}
	

	/**
	 * 时间格式转换 String型转换成long型
	 * @author haifei.huang
	 * @date 2013-8-19
	 * @param time
	 * @return
	 */
    public long strToLong(String time){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=null;
        long timeL=0;
        try {
             date = dateFormat.parse(time);
             timeL=date.getTime();
        } catch (ParseException e) {
            System.out.println("time is null format error,set value=0");
        }
        return timeL;
    }
    
    /**
     * 支付宝充值订单号
     * @param userId
     * @return
     */
	public  String getAliMerOrderNum(long userId){
		StringBuffer buffer=new StringBuffer();
		buffer.append(userId);
		buffer.append("ALI");
		buffer.append(getNowStr());
		buffer.append(getRandomInt());
		return buffer.toString();
	}
	
	
	public String jsonValue(Object obj){
		if(obj != null){
			return JSONValue.toJSONString(obj);
		}else{
			return null;
		}
		
	}
}
