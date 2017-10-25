package com.zqkj.bsm.init;
//import java.awt.Color;
//import java.awt.Font;
//import java.io.File;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.Logger;
import com.zqkj.bsm.cluster.inter.HttpClientPool;
import com.zqkj.bsm.system.action.SysPara;
import com.zqkj.bsm.thread.ThreadControl;
import com.zqkj.bsm.thread.scheduleTimer;


/**
 * Servlet初始化所有的预加载
 * @author 0242gf
 *
 */
public class ConfigServlet extends HttpServlet
{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -6369761175932874823L;

	private scheduleTimer sctime = null;
	//private NotifyTime notifytime =null;
	public static String webroot;
	public static byte[] gj_pic;
	/**
	 * 完成初始化工作 <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException 
	{
		// 初始化log4j配置		
		//String webInf = getServletContext().getRealPath("/WEB-INF");
		webroot = getServletContext().getRealPath("/");
//		String file = getInitParameter("log4j");
//		if(file != null) {
//			PropertyConfigurator.configure(webInf+File.separator+"log4j.properties");
//		}

		Logger logger = Logger.getLogger(this.getClass());
		logger.info("初始化日志配置");
		logger.info("初始化图片配置");
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		BufferedInputStream bfs = null;
		String image_src = ConfigServlet.webroot+File.separator+"images"+File.separator+"gaojing.jpg";
		try {
 	         bfs = new BufferedInputStream(new FileInputStream(image_src));    	      
 			//设置每次写入缓存大小
 			byte[] b = new byte[2048];
 			int bytesindex;
 			while (-1 != (bytesindex = bfs.read(b, 0, b.length))) 
 			{
 				os.write(b, 0, bytesindex);
 			}
 			
			byte[] res = os.toByteArray();
			gj_pic = res;
	     } catch (IOException e) 
	     {
	         //e.printStackTrace();
	    	 logger.info("初始化加载特定图片码流发生异常"+e.getMessage());
	     }
	     finally 
	     {
				if (os != null) 
				{
					try {
						os.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					os = null;
				}
				if (bfs != null) 
				{
					try {
						bfs.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					bfs = null;
				}
	     
	     }
		
		
//		MessageGenerater.getInstance();
//		logger.info("初始化邮件发送配置线程");
		//MissionCheck.getInstance();
		logger.info("初始化渲染任务检测线程");
		logger.info("初始化系统参数");
		SysPara.getInstance();
		//logger.info("初始化storage.properties;BLOCK_SIZE="+Configuration.BLOCK_SIZE);
//		InetAddress addr=null;
//		try 
//		{
//			addr = InetAddress.getLocalHost();
//		} catch (UnknownHostException e) {
//			logger.info("获取服务器地址失败");
//			e.printStackTrace();
//		}
//		SysPara.local_ip=addr.getHostAddress().toString();
		
		
		Enumeration<?> allNetInterfaces;
		try 
		{
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			while (allNetInterfaces.hasMoreElements())
			{
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				//System.out.println(netInterface.getName());
				Enumeration<?> addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements())
				{
					ip = (InetAddress) addresses.nextElement();
					if (ip != null && ip instanceof Inet4Address)
					{
						if(ip.getHostAddress().indexOf("10")>-1 || ip.getHostAddress().indexOf("172")>-1)
						{
							SysPara.local_ip=ip.getHostAddress();
						     // System.out.println("本机的IP = " + ip.getHostAddress());
							logger.info("获取服务器地址成功ip="+SysPara.local_ip);
						}
					} 
				}
			}		
		} catch (SocketException e) 
		{
			// TODO Auto-generated catch block
			logger.info("获取服务器地址失败");
			e.printStackTrace();
		} 
		 //初始化集群通知定时器
		 //logger.info("初始化集群通知线程");
		 // NotifyTime.getInstance();
		//notifytime = new NotifyTime();
		  //logger.info("初始化储存节点监控定时器"); 
		 // sctime = new scheduleTimer();		  
		 // sdtime = new StatisticalDayTime();		  
		 // ClientInitThread.getInstance();
		  //logger.info("初始化套餐到期提醒");
//		  PlanOutTimeCheck.getInstance();
		 // PeakCountThread.getInstance();
		  //NotifyOutTimeCheck.getInstance();
		  //用户余额报表定时器跑批
		//  UserYECountCheck.getInstance();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() 
	{
		ThreadControl.stop();
		sctime.cancel();
		HttpClientPool.release();
	}	
}
