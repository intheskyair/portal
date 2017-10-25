package com.zqkj.bsm.system.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.zqkj.bsm.log.dao.LogoptDao;
import com.zqkj.bsm.system.action.dao.SysUserDao;
import com.zqkj.bsm.util.ConfigUtil;

public class SysPara
{
    Logger log = Logger.getLogger(SysPara.class);
    
    public SysPara()
    {
        this.init();
    }
    
    static private SysPara instance; // 唯一实例
    
    static synchronized public SysPara getInstance()
    {
        if (instance == null)
        {
            instance = new SysPara();
        }
        return instance;
    }
    
    public void init()
    {
        // SysPara.exceptionMap.put("storage", true);
        // SysPara.exceptionMap.put("storage_time", System.currentTimeMillis());
        // SysPara.exceptionMap.put("device", true);
        // SysPara.exceptionMap.put("device_time", System.currentTimeMillis());
        // SysPara.exceptionMap.put("warning", true);
        // SysPara.exceptionMap.put("warning_time", System.currentTimeMillis());
        SysUserDao sd = SysUserDao.getInstance();
        LogoptDao ld = LogoptDao.getInstance();
        actionMap = ld.getActionDic();
        HashMap<String, String> paramMap = sd.smsParam();
        if (paramMap != null)
        {
            if (paramMap.get("EMAIL_HOST") != null && !paramMap.get("EMAIL_HOST").equals(""))
            {
                EMAIL_HOST = paramMap.get("EMAIL_HOST").trim();
                log.info("加载系统参数：EMAIL_HOST=" + EMAIL_HOST);
            }
            if (paramMap.get("FEE_PER_ZHEN") != null && !paramMap.get("FEE_PER_ZHEN").equals(""))
            {
                FEE_PER_ZHEN = Double.valueOf(paramMap.get("FEE_PER_ZHEN").trim()).doubleValue();
                log.info("加载系统参数：FEE_PER_ZHEN=" + FEE_PER_ZHEN);
            }
            if (paramMap.get("FEE_EFFICITIVEPIC") != null && !paramMap.get("FEE_EFFICITIVEPIC").equals(""))
            {
                FEE_EFFICITIVEPIC = Double.valueOf(paramMap.get("FEE_EFFICITIVEPIC").trim()).doubleValue();
                log.info("加载系统参数：FEE_EFFICITIVEPIC=" + FEE_EFFICITIVEPIC);
            }
            if (paramMap.get("FEE_MODULE") != null && !paramMap.get("FEE_MODULE").equals(""))
            {
                FEE_MODULE = Integer.valueOf(paramMap.get("FEE_MODULE").trim()).intValue();
                log.info("加载系统参数：FEE_MODULE=" + FEE_MODULE);
            }
            if (paramMap.get("PER_INTEGRAL") != null && !paramMap.get("PER_INTEGRAL").equals(""))
            {
                PER_INTEGRAL = Integer.valueOf(paramMap.get("PER_INTEGRAL").trim()).intValue();
                log.info("加载系统参数：PER_INTEGRAL=" + PER_INTEGRAL);
            }
            if (paramMap.get("page_num") != null && !paramMap.get("page_num").equals(""))
            {
                page_num = Integer.valueOf(paramMap.get("page_num").trim()).intValue();
                log.info("加载系统参数：page_num=" + page_num);
            }
            if (paramMap.get("CLIENT_VESION") != null && !paramMap.get("CLIENT_VESION").equals(""))
            {
                CLIENT_VESION = String.valueOf(paramMap.get("CLIENT_VESION").trim());
                log.info("加载系统参数：CLIENT_VESION=" + CLIENT_VESION);
            }
            if (paramMap.get("szxcurl") != null && !paramMap.get("szxcurl").equals(""))
            {
                szxcurl = String.valueOf(paramMap.get("szxcurl").trim());
                log.info("加载系统参数：szxcurl=" + szxcurl);
            }
            if (paramMap.get("szxcInfourl") != null && !paramMap.get("szxcInfourl").equals(""))
            {
                szxcInfourl = String.valueOf(paramMap.get("szxcInfourl").trim());
                log.info("加载系统参数：szxcInfourl=" + szxcInfourl);
            }
            if (paramMap.get("szxc") != null && !paramMap.get("szxc").equals(""))
            {
                szxc = String.valueOf(paramMap.get("szxc").trim());
                log.info("加载系统参数：szxc=" + szxc);
            }
            if (paramMap.get("fileSavaPath") != null && !paramMap.get("fileSavaPath").equals(""))
            {
                fileSavaPath = String.valueOf(paramMap.get("fileSavaPath").trim());
                log.info("加载系统参数：fileSavaPath=" + fileSavaPath);
            }
            if (paramMap.get("rdc_url") != null && !paramMap.get("rdc_url").equals(""))
            {
                rdc_url = String.valueOf(paramMap.get("rdc_url").trim());
                log.info("加载系统参数：rdc_url=" + rdc_url);
            }
            if (paramMap.get("work_time") != null && !paramMap.get("work_time").equals(""))
            {
            	work_time = String.valueOf(paramMap.get("work_time").trim());
            	log.info("加载系统参数：work_time=" + work_time);
            }
            if (paramMap.get("off_time") != null && !paramMap.get("off_time").equals(""))
            {
            	off_time = String.valueOf(paramMap.get("off_time").trim());
            	log.info("加载系统参数：off_time=" + off_time);
            }
            //每天定时备份数据库
            messageProcessTimer();
        }
        
    }
    
    public static String EMAIL_HOST = "192.168.1.60";// email_ip
    
    public static String STORAGE_URL = "http://192.168.1.60";
    
    // MAX按帧收费单帧费用
    public static double FEE_PER_ZHEN = 0.25d;
    
    // MAX按单帧效果图收费
    public static double FEE_EFFICITIVEPIC = 80d;
    
    // ER按帧收费单帧费用
    public static double ER_FEE_PER_ZHEN = 0.25d;
    
    // ER按单帧效果图收费
    public static double ER_FEE_EFFICITIVEPIC = 80d;
    
    // 计费模式 0：按帧 1：按核小时
    public static int FEE_MODULE = 0;
    
    // 积分兑换人民币比例
    public static double PER_INTEGRAL = 0.001d;
    
    // 每页显示数量
    public static int page_num = 30;
    
    // 当前客户端版本
    public static String CLIENT_VESION = "v1.0";
    
    public static String webSeriviceAllowIp = "";// 允许访问webservice服务的ip列表
    
    public static int thread_start = 0;
    
    public static String local_ip = "127.0.0.1";
    
    // public static HashMap<String, Object> exceptionMap= new HashMap<String, Object>();
    
    public static Map<String, Object> actionMap = new HashMap<String, Object>();
    
    public static int bgtcBeginTime = 9;
    
    public static String szxcurl = "127.0.0.1";
    
    public static String szxcInfourl = "127.0.0.1";
    
    public static String szxc = "127.0.0.1";
    
    public static String fileSavaPath = "";
    
    public static String rdc_url = "";
    
    public static String off_time = "";
    
    public static String work_time = "";
    
    private Timer timer = new Timer();
    private static String hostIP=ConfigUtil.getValue("jdbc.hostIP");  
    private static String userName=ConfigUtil.getValue("jdbc.username");
    private static String password=ConfigUtil.getValue("jdbc.password");
    private static String databaseName = ConfigUtil.getValue("jdbc.databaseName");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    
    // 0表示mysql 1表示oracle
    public static int db_bz = 0;
    
    // 启动线程工作
    public void startThread()
    {
        SysPara.thread_start = 1;
    }
    
    // 关闭线程工作
    public void stopThread()
    {
        SysPara.thread_start = 0;
    }
    
public void messageProcessTimer(){
        
        TimerTask timerTask = new TimerTask()
        {
            
            public void run()
            {
                SysUserDao sd = SysUserDao.getInstance();
                String savePath = String.valueOf(sd.smsParam().get("fileSavaPath").trim());
                String fileName = dateFormat.format(new Date())+".sql";
                File saveFile = new File(savePath);
                if (!saveFile.exists()) {// 如果目录不存在
                    saveFile.mkdirs();// 创建文件夹
                }
                if (!savePath.endsWith(File.separator)) {
                    savePath = savePath + File.separator;
                }

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("mysqldump").append(" --opt").append(" -h ").append(hostIP);
                stringBuilder.append(" --user=").append(userName) .append(" --password=").append(password).append(" --lock-all-tables=true");
                stringBuilder.append(" --result-file=").append(savePath + fileName).append(" --default-character-set=utf8 ").append(databaseName);
                try {
                    System.out.println(stringBuilder.toString());
                    Process process = Runtime.getRuntime().exec(stringBuilder.toString());
                    if (process.waitFor() == 0) {// 0 表示线程正常终止。
                        System.out.println("--------------");
                        log.info("数据库定时备份：文件名--"+fileName+".sql--备份时间"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    }
                } catch (IOException e) {
                    log.info("数据库定时备份：备份出错-"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"-IOException-"+e);
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    log.info("数据库定时备份：备份出错-"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"-IOException-"+e);
                    e.printStackTrace();
                }
                
            }
        };
        
        Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.HOUR_OF_DAY,1); // 控制时  
        calendar.set(Calendar.MINUTE, 0);       // 控制分  
        calendar.set(Calendar.SECOND, 0);       // 控制秒  
  
        Date time = calendar.getTime();
        
        long intevalPeriod = 1000 * 60 * 60 * 24;
        timer.scheduleAtFixedRate(timerTask, time, intevalPeriod);
    }
    
    
}