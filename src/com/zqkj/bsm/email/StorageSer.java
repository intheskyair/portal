package com.zqkj.bsm.email;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.zqkj.bsm.util.ConfigUtil;

public class StorageSer 
{
	private static volatile StorageSer instance = null;
	private static Logger log = Logger.getLogger(StorageSer.class);
	public static StorageSer getInstance()
	{
		if(instance==null)
		{
			synchronized (StorageSer.class)
			{
			instance = new StorageSer();
			}
		}
		return instance;
	}	
	
	private StorageSer()
	{
		
	}
	
	public Map<String, ArrayList<Map<String, Object>>> getStorageIo()
	{
		Map<String, ArrayList<Map<String, Object>>> map_return = new HashMap<String, ArrayList<Map<String, Object>>>();
		String compare = ConfigUtil.getValue("compare");
		String iopath = ConfigUtil.getValue("iopath");
		String[] tms = compare.trim().split(",");
		for(int i=0,len=tms.length;i<len;i++)
		{
			String[] ips = tms[i].split("\\s+");
			String ip = ips[0];
			String name = ips[1];
			String pathUrl= iopath+name+"io.cache";
			ArrayList<Map<String, Object>> list = this.getStorageIo(pathUrl);
			map_return.put(ip, list);
		}
		return map_return;
	}
	
	
	
	
	/**
	 * 根据挂在命令，遍历挂在是否正常
	 * @return
	 */
	public Map<String, StoreBean> getStoreInfo()
	{
		  BufferedReader bufferedReader=null;
		  Map<String, StoreBean> map = new HashMap<String, StoreBean>();
		  try 
		  {
			  Process exec = Runtime.getRuntime().exec("df -Ph");
			  InputStream inputStream = exec.getInputStream();
			  InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			  bufferedReader = new BufferedReader(inputStreamReader);
			  String tmp;
			   while ((tmp = bufferedReader.readLine()) != null) 
			   {
					if(tmp.indexOf(ConfigUtil.getValue("storage_ip"))>-1)
					{
						//log.info("*******"+tmp);
                        String[] tms = tmp.trim().split("\\s+");
                        StoreBean bean = new StoreBean();
                        bean.setNoteInfo(tms[0]);
                        bean.setTotal(tms[1]);
                        bean.setUserd(tms[2]);
                        bean.setYuxia(tms[3]);
                        bean.setPer(tms[4]);
                        bean.setLocalDir(tms[5]);
                        bean.setGz_bz(this.isDirExist(tms[5]));
                        map.put(tms[0], bean);
                    }
			   }
		  } 
		  catch (IOException e) 
		  {
		     //e.printStackTrace();
		     log.info("getStoreInfo发生异常:"+e.getMessage());
		  } 
		  finally 
		  {
		     try 
		     {
		    	 if(bufferedReader!=null)
		    	 {
				    bufferedReader.close();
		    	 }
			  } 
		     catch (IOException e) 
		     {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	 
		  }	
		  
		  return map;
	  }
	
	
	
	/**
	 * 判断目录是不是存在
	 * @param path
	 * @return
	 */
	private boolean isDirExist(String path)
	{
		boolean return_bz = false;
		try
		{
			File destDir = new File(path);
			if (!destDir.exists()) 
			{
				return_bz = false;
			}
			else
			{
				return_bz =true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return_bz = false;
		}
		return return_bz;
	}
	
	
	/**
	 * 读取指定路径的io文件
	 * @return
	 */
	private ArrayList<Map<String, Object>> getStorageIo(String pathURL)
	{
		  BufferedReader in=null;
		  InputStreamReader read;
		  ArrayList<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();
		  try 
		  {
					File file = new File(pathURL);				
					read = new InputStreamReader (new FileInputStream(file));				 
					in = new BufferedReader(read);
					//第一行是垃圾抛弃
					in.readLine();
					boolean done = false;
					int i=0;
				    while(!done)
					{
				    	i++;
						String substr = in.readLine();
						if(substr == null)
						{
							done = true;
							break;
						}
						else if(substr.trim().equals(""))
						{
							done = true;
							break;
						}
						else
						{
							
							String[] tms = substr.trim().split("\\s+");
							if(tms.length ==12)
							{
								Map<String, Object> map = new HashMap<String, Object>();
								map.put("Device", tms[0]);
								map.put("rrqm", tms[1]);
								map.put("wrqm", tms[2]);
								map.put("r", tms[3]);
								map.put("w", tms[4]);
								map.put("rsec", tms[5]);
								map.put("wsec", tms[6]);
								map.put("avgrp", tms[7]);
								map.put("avgqu", tms[8]);
								map.put("await", tms[9]);
								map.put("svctm", tms[10]);
								if(i>1)
								{
								    map.put("util", new BigDecimal(tms[11]));
								}
								else
								{
									map.put("util", tms[11]);
								}
								return_list.add(map);
							}
							
						}				        

					}//while
		  } 
		  catch (IOException e) 
		  {
		     e.printStackTrace();
		  } 
		  finally 
		  {
		     try 
		     {
		    	 if(in!=null)
		    	 {
		    		 in.close();
		    	 }
			  } 
		     catch (IOException e) 
		     {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	 
		  }	
		  
		  return return_list;
	  }
	
	
	
}



