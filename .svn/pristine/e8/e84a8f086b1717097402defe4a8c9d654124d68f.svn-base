package com.zqkj.bsm.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zqkj.bsm.dal.bean.User;
import com.zqkj.bsm.dal.client.UserOpera;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
/**
 * 
 * 2007.09.21 by lyhapple
 * 
 * */
public class CookieUtil {
	// 保存cookie时的cookieName
	private final static String cookieDomainName = "www.curender.com";
	private final static String webKey = "curender"; // 加密cookie时的网站自定码
	private final static long cookieMaxAge = 60 * 60 * 24 * 30L; // 设置cookie有效期是一个月，根据需要自定义  单位是秒（s）

	// 保存Cookie到客户端--------------------------------------------------------------------------------------------------------
    // 在CheckLogonServlet.java中被调用
    // 传递进来的user对象中封装了在登录时填写的用户名与密码
    public static void saveCookie(User user, HttpServletResponse response) throws UnsupportedEncodingException {
        long validTime = System.currentTimeMillis() + (cookieMaxAge * 1000L); // cookie的有效期   单位为毫秒（ms）
        String cookieValueWithMd5 = getMD5(user.getEmail() + ":" + user.getRegPassword() + ":" + validTime + ":" + webKey);// MD5加密用户详细信息
        String cookieValue = user.getEmail() + ":" + validTime + ":" + cookieValueWithMd5;// 将要被保存的完整的Cookie值
        String cookieValueBase64 = new String(Base64.encode(cookieValue.getBytes("utf-8")));// 再一次对Cookie的值进行BASE64编码
        Cookie cookie = new Cookie(cookieDomainName, cookieValueBase64);
        cookie.setMaxAge(60 * 60 * 24 * 30 * 2); // 存两个月(这个值应该大于或等于validTime)
        cookie.setPath("/");// cookie有效路径是网站根目录
        response.addCookie(cookie);// 向客户端写

    }


	// 读取Cookie,自动完成登录操作--------------------------------------------------------------------------------------------
	// 在Filter程序中调用该方法,见AutoLogonFilter.java
	public static void readCookieAndLogon(String cookieValue,
			HttpServletRequest request, HttpServletResponse response,
			FilterChain chain) throws IOException, ServletException,
			UnsupportedEncodingException {

		String cookieValueAfterDecode = new String(Base64.decode(cookieValue), "utf-8"); // 先得到的CookieValue进行Base64解码
		// 对解码后的值进行分拆,得到一个数组,如果数组长度不为3,就是非法登录
		String cookieValues[] = cookieValueAfterDecode.split(":");

		if (cookieValues.length != 3) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("你正在用非正常方式进入本站...");
			out.close();
			return;
		}

		// 判断是否在有效期内,过期就删除Cookie

		long validTimeInCookie = new Long(cookieValues[1]);
		if (validTimeInCookie < System.currentTimeMillis()) {
			// 删除Cookie
			clearCookie(response);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<a href=user/userLogin'>一个月自动登录有效期已过,请重新登录</a>");
			out.close();
			return;
		}

		// 取出cookie中的用户名,并到数据库中检查这个用户名,
		String email = cookieValues[0];
		// 根据用户名到数据库中检查用户是否存在
		UserOpera opera = UserOpera.getInstance();
		User user = opera.readByEmail(email);
		// 如果user返回不为空,就取出密码,使用用户名+密码+有效时间+ webSiteKey进行MD5加密
		if (user != null) {
			String md5ValueInCookie = cookieValues[2];
			String md5ValueFromUser = getMD5(user.getEmail() + ":" + user.getRegPassword() + ":" + validTimeInCookie + ":" + webKey);

			// 将结果与Cookie中的MD5码相比较,如果相同,写入Session,自动登录成功,并继续用户请求
			if (md5ValueFromUser.equals(md5ValueInCookie)) {
				HttpSession session = request.getSession(true);
				session.setAttribute("user", user);
				//Tools.getInstance().updateSessionBalance(session,user.getSuserId());//更新账户余额
				chain.doFilter(request, response);
			}
		} else {
			// 返回为空执行
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			CookieUtil.clearCookie( response );
			out.println("<a href='user!userLogin.action'>自动登录失败,请重新登录</a>");
			out.close();
		}

	}

	// 用户注销时,清除Cookie,在需要时可随时调用------------------------------------------------------------

	public static void clearCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie(cookieDomainName, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

//	public static void main(String[] args) {
//		System.out.println(getMD5("```111"));
//	}
	// 获取Cookie组合字符串的MD5码的字符串----------------------------------------------------------------------------
	public static String getMD5(String value) {
		MessageDigest md = null;
		try {
			byte[] valueByte = value.getBytes();
			md = MessageDigest.getInstance("MD5");
			md.update(valueByte);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		if(md != null){
			return toHex(md.digest());
		}else{
			return null;
		}
	}

	// 将传递进来的字节数组转换成十六进制的字符串形式并返回
	private static String toHex(byte[] buffer) {
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
		}
		return sb.toString();
	}

}
