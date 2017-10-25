package com.zqkj.bsm.util;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;

import com.zqkj.bsm.client.dao.ClientDao;


public class BHUtil 
{
    private static SimpleDateFormat sf = new SimpleDateFormat("yyMMdd");
    private static SimpleDateFormat sf_date = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat sf_ = new SimpleDateFormat("yyyyMM");
    private static SimpleDateFormat sf_year= new SimpleDateFormat("yyyy");
    
    private static List<String[]> NOTE_LIST = new ArrayList<String[]>();
    
    /**
     * 获取季度
     * @param month
     * @return
     */
    public static String getThisSeasonTime()
    {     
        Calendar c=Calendar.getInstance();
        c.setTime(new Date());
        int month=c.get(Calendar.MONTH)+1;
        String quarter="";    
        if(month>=1&&month<=3){     
            quarter="第一";     
        }     
        if(month>=4&&month<=6){     
            quarter="第二";       
        }     
        if(month>=7&&month<=9){     
            quarter = "第三";     
        }     
        if(month>=10&&month<=12){     
            quarter = "第四";     
        }
        return quarter;
    }
    
    
	public static String getTime_year()
	{
		
		String hm =  sf_year.format(new Date()).toString();
		return hm;
	}
    
	/**
	 * 获取时间 当天
	 */
	public static String getTime()
	{
		
		String hm =  sf_date.format(new Date()).toString();
		return hm;
	}
	/**
	 * 获取时间 当月
	 */	
	public static String getTime_month()
	{
        Calendar c=Calendar.getInstance();
        c.setTime(new Date());
        int month=c.get(Calendar.MONTH)+1;
		return ""+month;
	}
	
	public static String getTime_date()
	{
        Calendar c=Calendar.getInstance();
        c.setTime(new Date());
        int date=c.get(Calendar.DATE);
		return ""+date;
	}
	
	/**
	 * @方法描述：水政执法s生成规则
	 * @输出：String类型编号
	 */
	public static synchronized String getTransID()
	{
		ClientDao dao = ClientDao.getInstance();
		String head = "SZZF-";
		String time = BHUtil.getTime();
        String seq = dao.getSeq("MovieSeq");
		return head+time+"-"+seq;
	}

	
	/**
	 * 文号生成规则
	 * @return
	 */
	public static synchronized String getXXTransID(String seq_name)
	{
		ClientDao dao = ClientDao.getInstance();
		String time = BHUtil.getTime_year();
        String seq = dao.getSeq(seq_name);
		return time+"-"+seq;
	}
	
	
	/**
	 * 明主评测规则
	 * @return
	 */
	public static synchronized String getMZPCTransID(String seq_name)
	{
		ClientDao dao = ClientDao.getInstance();
        String seq = dao.getSeq(seq_name);
		return seq;
	}
	
	
	public static synchronized void upMZPCTransID(String seq_name)
	{
		ClientDao dao = ClientDao.getInstance();
        dao.upSeq(seq_name);
	}
	/**
	 * Sqnence更新
	 * @return
	 */
	public static synchronized void updateTransID(String seq_name)
	{
		ClientDao dao = ClientDao.getInstance();
		dao.upSeq(seq_name);
	}
	
	/**
	 * 当前文号
	 * @return
	 */
	public static synchronized String getCurrTransID(String seq_name)
	{
		ClientDao dao = ClientDao.getInstance();
		String time = BHUtil.getTime_year();
		String seq = dao.getSeq_Curr(seq_name);
		
		 DecimalFormat df=new DecimalFormat("0000");
		return time+"-"+df.format(Integer.valueOf(seq)+1);
	}
	
	public static synchronized String getAssetID(String seq_name)
	{
		ClientDao dao = ClientDao.getInstance();
        String seq = dao.getNextSeq(seq_name);
        switch (seq.length()) {
		case 1:
			seq="00000"+seq;
			break;
		case 2:
			seq="0000"+seq;
			break;
		case 3:
			seq="000"+seq;
			break;
		case 4:
			seq="00"+seq;
			break;
		case 5:
			seq="0"+seq;
			break;
		case 6:
			break;
		}
		return seq;
	}

	
	/**
	 * 同步获取锁定账号集合
	 * @return
	 */
	public static synchronized List<String[]> getNOTE_LIST() {
		return NOTE_LIST;
	}
	
	/**
	 * 同步设置锁定账号集合
	 * @param nOTE_LIST
	 */
	public static synchronized void setNOTE_LIST(List<String[]> nOTE_LIST) {
		NOTE_LIST.addAll(nOTE_LIST);
	}
	/**
	 * 判断是否有重复登陆次数超过的IP和账号
	 * @param ip
	 * @param name
	 * @return
	 */
	public static synchronized boolean isMore(String ip,String name){
		boolean flag = false;
		int times = Integer.parseInt(CommonUtil.nullToDefault(ConfigUtil.getValue("login_times"),"5"));
		int unlock_time = Integer.parseInt(CommonUtil.nullToDefault(ConfigUtil.getValue("login_times"),"10"));
		for (int i = 0; i < BHUtil.getNOTE_LIST().size(); i++) {
			String[] arrs =  BHUtil.getNOTE_LIST().get(i);
			if( ip.equals(String.valueOf(arrs[0])) ){ //&& name.equals(String.valueOf(arrs[1]))
				if(Integer.parseInt(arrs[2]) >= times){
					Timer timer = new Timer();
					timer.schedule(new RemoveLoginTimes(ip,name,Integer.parseInt(arrs[2])), 
							new Date(System.currentTimeMillis() + unlock_time * 60 * 1000));
					flag = true;
					break;
				}else{
					String temp = String.valueOf(Integer.parseInt(arrs[2]) + 1);
					BHUtil.getNOTE_LIST().set(i, new String[]{ip,name,temp});
				}
			}
		}
		return flag;
	}
	
	/**
	 * 解锁被锁定10分钟的账号
	 * @param ip
	 * @param name
	 * @param times
	 */
	public static synchronized void reMoveLockUser(String ip,String name,int times){
		if(BHUtil.NOTE_LIST.size() > 0){
			Iterator<String[]> it = BHUtil.getNOTE_LIST().iterator();
			while (it.hasNext()) {
				String[]  arr = it.next();
				if(ip.equals(String.valueOf(arr[0])) && name.equals(String.valueOf(arr[1]))){
					it.remove();
				}
			}
		}
	}
	
	
	/**
	 *传入一个集合，更新集合中的TIME字段
	 *将一年的第几周转成 ： xx年xx月xx日~xx年xx月xx日 时差为:1000 * 60 * 60 * 24 * 6 为6天加上本身一天就是7天
	 *返回已更新的集合list
	 */
	public static List<Map<String, Object>> weekToDate(List<Map<String, Object>> list){
		SimpleDateFormat sdf = new SimpleDateFormat("yy年MM月dd日");
		Calendar cal = Calendar.getInstance();
		for (Map<String, Object> map : list) {
			String str = (String) map.get("TIME");
			String[] date = str.split("-");
			cal.clear();
			cal.set(Calendar.YEAR, Integer.valueOf(date[0]));
			cal.set(Calendar.WEEK_OF_YEAR, Integer.valueOf(date[1]));
			cal.setFirstDayOfWeek(Calendar.MONDAY);
			String bdate = sdf.format(cal.getTime());
			long time = cal.getTimeInMillis() + 1000 * 60 * 60 * 24 * 6;
			cal.clear();
			cal.setTimeInMillis(time);
			String edate = sdf.format(cal.getTime());
			String res = bdate + "~" + edate;
			map.remove("TIME");
			map.put("TIME", res);
		}
		return list;
	}
	
	/*
	 * 天数相加
	 */
	public static double js(double a,double b){
		int a_left = (int) a;
		int b_left = (int) b;
		int a_right = 0;
		int b_right = 0;
		String all = null;
		if(a % 1 != 0){
			a_right = (int)(Math.floor(a*10d)%10);
		}
		if(b % 1 != 0){
			b_right = (int)(Math.floor(b*10d)%10);
		}
		if((a_right + b_right) < 8){
			all = (a_left + b_left) + "." + (a_right + b_right);
		}else if((a_right + b_right) == 8){
			all = String.valueOf(a_left + b_left + 1);
		}else if((a_right + b_right) > 8){
			all = (a_left + b_left + 1) + "." + (a_right + b_right - 8);
		}
		return Double.valueOf(all);
	}
	
	
	
	/**
	 * 天数相减
	* @Title: js 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param a
	* @param @param b
	* @param @return    设定文件 
	* @return double    返回类型 
	* @throws
	 */
	public static double jsj(double a,double b){
		int a_left = (int) a;
		int b_left = (int) b;
		int a_right = 0;
		int b_right = 0;
		String all = null;
		if(a % 1 != 0){
			a_right = (int)(Math.floor(a*10d)%10);
		}
		if(b % 1 != 0){
			b_right = (int)(Math.floor(b*10d)%10);
		}
		if(a_right > b_right){
			all = (a_left - b_left) + "." + (a_right - b_right);
		}else if(a_right == b_right){
			all = String.valueOf(a_left - b_left);
		}else if(a_right < b_right){
			all = (a_left - b_left - 1) + "." + (a_right + 8 - b_right);
		}
		return Double.valueOf(all);
	}
	
	
	
	
	   public static String GetRandomString(int Len) {
	         
		   String no = "";

	       // 初始化备选数组

	       int[] defaultNums = new int[10];

	       for (int i = 0; i < defaultNums.length; i++) {

	           defaultNums[i] = i;

	       }

	       Random random = new Random();

	       int[] nums = new int[Len];

	       // 默认数组中可以选择的部分长度

	       int canBeUsed = 10;

	       // 填充目标数组

	       for (int i = 0; i < nums.length; i++) {

	           // 将随机选取的数字存入目标数组

	           int index = random.nextInt(canBeUsed);

	           nums[i] = defaultNums[index];

	           // 将已用过的数字扔到备选数组最后，并减小可选区域

	           swap(index, canBeUsed - 1, defaultNums);

	           canBeUsed--;

	       }

	       if (nums.length > 0) {

	           for (int i = 0; i < nums.length; i++) {

	               no += nums[i];

	           }

	       }

	       return no;
	    }
	   
	   
	   
		 private static void swap(int i, int j, int[] nums) {

		       int temp = nums[i];

		       nums[i] = nums[j];

		       nums[j] = temp;

		 }
		 
  /** 
     * 根据年 月 获取对应的月份 天数 
     * */  
    public static int getDaysByYearMonth(int year, int month) {  
          
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.YEAR, year);  
        a.set(Calendar.MONTH, month - 1);  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    }  
    
    
    
    public static String getDayOfWeekByDate(String date) {  
        String dayOfweek = "-1";  
        try {  
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");  
            Date myDate = myFormatter.parse(date);  
            SimpleDateFormat formatter = new SimpleDateFormat("E");  
            String str = formatter.format(myDate);  
            dayOfweek = str;  
              
        } catch (Exception e) {  
            System.out.println("错误!");  
        }  
        return dayOfweek;  
    }  
 
}
