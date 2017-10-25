package com.zqkj.bsm.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Encrypt {
	public static Encrypt encrypt;
	public static String SALT = "curender";

	public static Encrypt getInstance() {
		if (encrypt == null) {
			encrypt = new Encrypt();
		}
		return encrypt;
	}

	public static String getMD5(String value) {
		String result = null;
		try {
			byte[] valueByte = value.getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(valueByte);
			result = toHex(md.digest());
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		return result;
	}
	public static String get16MD5(String userName) {
		String result = null;
		try {
			byte[] valueByte = userName.getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(valueByte);
			result = toHex(md.digest());
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		if(result!=null&&result.length()==32){
			result=result.substring(8,24);
		}else{
			result=null;
		}
		return result;
	}

	// 将传递进来的字节数组转换成十六进制的字符串形式并返回
	private static String toHex(byte[] buffer) {
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));// 取一个字节的高四�?
			sb.append(Character.forDigit(buffer[i] & 0x0f, 16));// 取一个字节的低四�?
		}
		return sb.toString();
	}
	
	public static String getMD5Password(String password){
		return Encrypt.getMD5(Encrypt.getMD5(password)+Encrypt.getMD5(Encrypt.SALT));
	}
	public  String getEncryptPassword(String password) {
		return getMD5(getMD5(password) + SALT);
	}
	 public String getSixPass(){
		    	final int maxNum=62;
		    	int i=0;
		    	int count=0;
		    	char[] chars={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
		    				  'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
		    				  '0','1','2','3','4','5','6','7','8','9'};
		    	StringBuffer buffer=new StringBuffer();
		    	Random random=new Random();
		    	while(count<6){
		    		i=Math.abs(random.nextInt(maxNum));
		    		if(i>=0&&i<chars.length){
		       		 buffer.append(chars[i]);
		       		 count++;
		    	}
		    	}
		    	
		    	return buffer.toString();
		    }
	 
	 public String getSMSCode(){
	    	final int maxNum=62;
	    	int i=0;
	    	int count=0;
	    	char[] chars={'0','1','2','3','4','5','6','7','8','9'};
	    	StringBuffer buffer=new StringBuffer();
	    	Random random=new Random();
	    	while(count<6){
	    		i=Math.abs(random.nextInt(maxNum));
	    		if(i>=0&&i<chars.length){
	       		 buffer.append(chars[i]);
	       		 count++;
	    	}
	    	}
	    	
	    	return buffer.toString();
	    }

//	public static void main(String[] args) {
		/*CheckCode code = new CheckCode();
		for(int i=0;i<200;i++){
			code = code.createCheckCode();
			String randomStr=code.getCheckCodeStr();
		}*/
//		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
//	}
}
