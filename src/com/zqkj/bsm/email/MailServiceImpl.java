package com.zqkj.bsm.email;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.apache.log4j.Logger;

import com.zqkj.bsm.security.MyAuthenticator;
import com.zqkj.bsm.util.ConfigUtil;

/**
 * @author gaofeng 
 * @version 创建时间：2014-11-26 下午2:03:15 类说明
 */
public class MailServiceImpl
{
	private static volatile MailServiceImpl instance = null;
	private static Logger log = Logger.getLogger(MailServiceImpl.class);
	private String user="servic";//邮件帐号
	private String password="123456";//邮件密码
	private String EMAIL_HOST;
	private Session session =null;
	private Transport transport=null;
	private MailServiceImpl()
	{
		user = ConfigUtil.getValue("user");
		password = ConfigUtil.getValue("password_email");
		EMAIL_HOST = ConfigUtil.getValue("EMAIL_HOST");
		try 
		{
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true"); //打开身份验证
			props.put("mail.smtp.host", EMAIL_HOST);
			MyAuthenticator myAuthenticator = new MyAuthenticator(user,password);
			session = Session.getInstance(props, myAuthenticator);
			//session.setDebug(true);					
			transport = session.getTransport("smtp");
//			try {
//				transport.connect();
//			} catch (MessagingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		} 
		catch (NoSuchProviderException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static MailServiceImpl getInstance()
	{
		if(instance==null)
		{
			synchronized (MailServiceImpl.class)
			{
			instance = new MailServiceImpl();
			}
		}
		return instance;
	}	
	
	
	@SuppressWarnings("static-access")
	public boolean send(String fromaddress, String to, String subject, String body)
	{
		if(session!=null && transport!=null)
		{
		    try {
		    	Message msg = new MimeMessage(session);
		    	String nick="";
		  	    if(fromaddress.equals("notice@renderincloud.com"))
		  	    {
		  	    	nick = "渲云";
		  	    }else if(fromaddress.equals("marketing@cudatec.com"))
		  	    {
		  	    	nick = "赞奇科技";
		  	    }
		  		try 
		  		{
		  			nick=javax.mail.internet.MimeUtility.encodeText(nick);
		  			msg.setFrom(new InternetAddress(nick+"<"+fromaddress+">"));
		  		} catch (UnsupportedEncodingException e) {
		  			e.printStackTrace();
		  		} catch (AddressException e) {
		  			e.printStackTrace();
		  		} 
		        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
		        msg.setSubject(subject);
		        msg.setSentDate(new Date());
		        msg.setHeader("X-Mailer", "sendhtml");
		    	StringBuffer sb = new StringBuffer();
		    	sb.append("<HTML>\n");
		    	sb.append("<HEAD>\n");
		    	sb.append("<TITLE>\n");
		    	sb.append(subject + "\n");
		    	sb.append("</TITLE>\n");
		    	sb.append("</HEAD>\n");
		    	sb.append("<BODY>\n");
		    	sb.append(body+"\n");
		    	sb.append("</BODY>\n");
		    	sb.append("</HTML>\n");
		    	msg.setDataHandler(new DataHandler(new ByteArrayDataSource(sb.toString(), "text/html;charset=utf-8")));
		    	if(!transport.isConnected())
		    	{
		    		transport.connect();
		    	}
		    	transport.send(msg);
			    } catch (MessagingException e) {
			    	//e.printStackTrace();
			    	log.info("邮件发送异常="+e.getMessage());
			        return false;
			    }catch (IOException e1) 
			    {
			    	log.info("邮件发送异常="+e1.getMessage());
					//e1.printStackTrace();
					return false;
				}
			    log.info("成功发送一条邮件给to="+to+"内容="+body); 
				return true;				 
			}
		else
		{
			log.info("邮箱地址不可达EMAIL_HOST="+EMAIL_HOST+"用户名="+user+",password="+password);
			return false;
		}
	}

}
