package com.zqkj.bsm.dwr;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.zqkj.bsm.dal.bean.Admin;
import com.zqkj.bsm.system.action.dao.SysUserDao;
import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;




public class DwrService 
{
	// 取昨天日期
	public String getLastDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DATE, -1);
		return sdf.format(cal.getTime());
	}

	// 取当月初日期跟当日日期
	public String[] get2Date() 
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy-MM");
		Calendar cal = new GregorianCalendar();
		String firstDate = sdfMonth.format(cal.getTime()) + "-01";
		String currDate = sdf.format(cal.getTime());
		String[] date = { firstDate, currDate };
		return date;
	}
	
	
	// 修改用户的密码
	@SuppressWarnings("deprecation")
	public boolean changepwd(String old_pwd,String userpwdnew) 
	{
		WebContext context = WebContextFactory.get();
		HttpServletRequest request = context.getHttpServletRequest();
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		SysUserDao xd = SysUserDao.getInstance();
		return xd.changepwd(String.valueOf(admin.getAdminId()), old_pwd,userpwdnew);
	}
	
	//判断旧密码是否正确
	@SuppressWarnings("deprecation")
	public boolean userLogin(String old_pwd)
	{
		WebContext context = WebContextFactory.get();
		HttpServletRequest request = context.getHttpServletRequest();
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		SysUserDao xd = SysUserDao.getInstance();
		return xd.userLogin(String.valueOf(admin.getAdminId()), old_pwd);
	}
	
}