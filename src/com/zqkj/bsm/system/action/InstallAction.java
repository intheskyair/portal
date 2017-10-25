package com.zqkj.bsm.system.action;
//package com.renderincloud.bsm.system.action;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import javax.servlet.http.HttpServletRequest;
//import org.apache.log4j.Logger;
//import org.apache.struts2.ServletActionContext;
//import com.ironhorse.install.ParseDBDumpFile;
//import com.ironhorse.install.ParseDBStructFile;
//import com.ironhorse.pub.bean.DbBean;
//import com.ironhorse.pub.util.BaseUtils;
//import com.ironhorse.pub.util.DBConnOperator;
//import com.ironhorse.pub.util.DBConnectionManager;
//import com.ironhorse.pub.util.FileCreate;
//import com.ironhorse.pub.util.StringHelper;
//import com.ironhorse.system.SysPara;
//import com.opensymphony.xwork2.ActionSupport;
//
//public class InstallAction extends ActionSupport
//{
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -6454157214400938725L;
//	
//	private Logger log = Logger.getLogger(InstallAction.class);
//	private FileCreate fc = null;
//	
//	public InstallAction()
//	{
//		fc = new FileCreate();
//	}
//	
//	/**
//	 * 跳转到安装界�?
//	 * @return
//	 * @throws Exception
//	 */
//	public String forwardInstall() throws Exception 
//	{
//		HttpServletRequest request = ServletActionContext.getRequest();
//		//取第�?个连接数据库连接启动(公共的数�?)
//		DbBean bean=(DbBean)DBConnectionManager.gatelist.get(0);	
//		String url = bean.getUrl();
//		String localhost = url.substring(url.indexOf("@")+1 ,url.lastIndexOf(":"));
//		String sid = url.substring(url.lastIndexOf(":")+1);
//		request.setAttribute("DbBean", bean);
//		request.setAttribute("localhost", localhost);
//		request.setAttribute("sid", sid);
//		return "forwardInstall";
//	}
//	
//	
//	public String forwardEditPasswd() throws Exception 
//	{
//		return "forwardEditPasswd";
//	}
//	
//	
//	public String getPasswd() throws Exception 
//	{
//		HttpServletRequest request = ServletActionContext.getRequest();
//		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
//		HashMap<String, String> db_map = fc.ReadDbTxtfile(DBConnectionManager.path);
//		//取第�?个连接数据库连接启动(公共的数�?)
//		for(DbBean bean_tmp:DBConnectionManager.gatelist)
//		{
//
//			String url = bean_tmp.getUrl();
//			String localhost = url;
//				//url.substring(url.indexOf("@")+1 ,url.lastIndexOf(":"));
//			String sid = "";
//				//url.substring(url.lastIndexOf(":")+1);
//			HashMap<String, String> map = new HashMap<String, String>();
//			map.put("localhost",localhost);
//			map.put("sid",sid);
//			map.put("username", bean_tmp.getUsername());
//			map.put("dbname", bean_tmp.getDbname());
//			if(db_map!=null)
//			{
//				map.put("passwd",db_map.get(bean_tmp.getDbname()));
//			}
//			list.add(map);
//		}
//		request.setAttribute("list", list);
//		return "getPasswd";
//	}
//	
//	public String save() throws Exception 
//	{
//		HttpServletRequest request = ServletActionContext.getRequest();
//		//取第�?个连接数据库连接启动(公共的数�?)
//		String dbpasswd = StringHelper.noEmpty(request.getParameter("dbpasswd"));
//		DbBean bean=(DbBean)DBConnectionManager.gatelist.get(0);	   
//		if(!dbpasswd.equals(""))
//		{
//			if(!dbpasswd.equals(bean.getPassword()))
//			{
//				request.setAttribute("status", "01");
//				request.setAttribute("notes", "安装失败，原因数据库密码不正�?");
//				request.setAttribute("url", "install_forwardInstall.do");
//			}
//		}
//		DBConnOperator db = new DBConnOperator();
//		String sql="SELECT * FROM user_tables WHERE table_name = 'SYS_MENU'";
//		int count = db.getCount(sql);
//		if(count==0)
//		{
//			String root = request.getSession().getServletContext().getRealPath("/");
//		    String name_drop = root + "/install/oracle_drop_tables.sql" ;
//		    String name_struct = root + "/install/oracle_db_struct.sql" ;
//		    String name_data = root + "/install/oracle_data_dump.sql" ;
//		    boolean bd_flag = false;
//		    ArrayList<String> list_drop = ParseDBStructFile.parse(name_drop);
//		    ArrayList<String> list_struct = ParseDBStructFile.parse(name_struct);
//		    ArrayList<String> list_data = ParseDBDumpFile.parse(name_data);
//	//	    for(String s_drop:list_drop)
//	//	    {
//	//	    	log.info("list_drop="+s_drop);
//	//	    }
//	//	    for(String s_struct:list_struct)
//	//	    {
//	//	    	log.info("list_struct="+s_struct);
//	//	    }
//	//	    for(String s_data:list_data)
//	//	    {
//	//	    	log.info("list_data="+s_data);
//	//	    }
//		    db.executeBatch_imdi(list_drop);
//	    	if(db.executeBatch_imdi(list_struct))
//	    	{
//	    		if(db.executeBatch_imdi(list_data))
//	    		{
//	    			bd_flag = true;
//	    		}
//	    	}
//	
//		    if(bd_flag)
//		    {
//		    	    log.info("再次初始化系统参�?");
//					SysPara.getInstance();
//					request.setAttribute("status", "00");
//					request.setAttribute("notes", "安装成功");
//					request.setAttribute("url", "install_forwardInstall.do");
//		    }
//		    else
//		    {
//					request.setAttribute("status", "01");
//					request.setAttribute("notes", "安失败，请检查安装sql脚本");
//					request.setAttribute("url", "install_forwardInstall.do");
//		    }
//		}
//		else
//		{
//			request.setAttribute("status", "01");
//			request.setAttribute("notes", "已经成功安装过，不需要再次安�?");
//			request.setAttribute("url", "install_forwardInstall.do");
//		}
//		return "result";
//	}
//	
//	/**
//	 * 动�?�的修改数据库密�?
//	 * @return
//	 * @throws Exception
//	 */
//	public String edit_passwd() throws Exception 
//	{
//		HttpServletRequest request = ServletActionContext.getRequest();
//		//取第�?个连接数据库连接启动(公共的数�?)
//		String submit_data = StringHelper.noEmpty(request.getParameter("submit_data")); 
//		if(!submit_data.equals(""))
//		{
//			//系统上锁
//			BaseUtils.system_locker = true;
//			log.info("数据库新密码�?"+submit_data);
//			log.info("�?始重新初始化数据�?");
//			if(fc.writeTtxtfile(DBConnectionManager.path, submit_data))
//			{
//			    DBConnectionManager.reinit();
//				request.setAttribute("status", "00");
//				request.setAttribute("notes", "修改数据密码成功,请重新登入系�?");
//			}
//			else
//			{
//				request.setAttribute("status", "01");
//				request.setAttribute("notes", "修改数据密码失败,请重新设�?");
//				
//			}
//			//系统解锁
//			BaseUtils.system_locker = false;
//			request.setAttribute("url", "install_forwardEditPasswd.do");
//		}
//		else
//		{
//			request.setAttribute("status", "01");
//			request.setAttribute("notes", "修改数据密码失败，请重新设置");
//			request.setAttribute("url", "install_forwardEditPasswd.do");
//		}
//		return "result";
//	}
//	
//}
