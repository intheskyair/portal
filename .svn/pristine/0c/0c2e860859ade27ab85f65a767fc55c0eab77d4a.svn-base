package com.zqkj.bsm.system.action.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import jxl.Sheet;
import jxl.Workbook;

public class ReadExcleBiz 
{
	@SuppressWarnings({ "unchecked", "static-access" })
	public ArrayList[] readXSSExcel(String path)
	{
		
		ArrayList[] al=new ArrayList[2];
		ArrayList errorlist=new ArrayList();
		ArrayList datelist=new ArrayList();
	            try
	            {      
	            	XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(path));         	    	                                     
	                XSSFSheet sheet = workbook.getSheetAt(0);  
	                 //行数
	                 int rowcount = sheet.getLastRowNum();    
                     //列数
	                 int colcount =  sheet.getRow(0).getLastCellNum();  
	                 // 遍历�?有行
	                 for (int i = 0; i < rowcount + 1; i++) 
	                 {
	                   // 获取每行的列数�?�和
	                	 XSSFRow row = sheet.getRow(i);
	                	 if(row!=null)
	                	 {
	                         int colCount_ = row.getLastCellNum();
	                         if(colCount_>colcount)
	                         {
	                        	 colcount = colCount_;
	                         }
	                	 }
	                  }
	                 	                 
	         		if(rowcount<=1)
	        		{
	        			errorlist.add("你导入的文件是空文件，请编辑正确文件导入");
	        		}
	        		// 保证只有�?�?
	        		if (colcount == 1) 
	        		{ 
		                 for (int i = 1; i < rowcount+1;i++) 
		                 {      
		                	 String columnName="";
		                	 //获取每行
		                         XSSFRow row = sheet.getRow(i); // i=0 indicate the first      
		                         //获取每行第一�?		                       		                         
		                         if(row!=null)
		                         {
		                        	 XSSFCell cell = row.getCell(0);
			                         cell.setCellType(Cell.CELL_TYPE_STRING);
			                         columnName = cell.getStringCellValue()==null?"":cell.getStringCellValue().trim();
		                         }
		                         int num = this.checkPhone(columnName);
		     					if (num == 0) 
		     					{
		     						datelist.add(columnName);
		     					} 
		     					else if (num == 1) 
		     					{
		     						errorlist.add("您的�?"+(i+1)+"行第�?列，输入格式有误，应该为邮箱地址:"+columnName);
		     					} 
		                         else if (num == 3) 
		     					{
		                         	//空号码直接跳�?
		                         	//errorlist.add("您的�?"+(i+1)+"行第�?列，号码输入为空");
		     					}		                 	   
		                 }  
	        		}
	        		else
	        		{
	        			errorlist.add("请不要输入多�?");
	        		}
	   
	             } catch (FileNotFoundException e) 
	             {    
	                // TODO Auto-generated catch block    
	                 e.printStackTrace();    
	             } catch (IOException e) 
	             {    
	                // TODO Auto-generated catch block    
	                 e.printStackTrace();    
	             } 
	     		datelist = this.removeDuplicate(datelist);
	    		al[0] = errorlist;
	    		al[1] = datelist;
	    		return al;
	}
	
	
	
	
	
	
	
	/**
	 * 导入邮件地址
	 * @param request
	 * @param path
	 * @return
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	public ArrayList[] bathExport(String path) 
	{
		ArrayList[] al=new ArrayList[2];
		ArrayList errorlist=new ArrayList();
		ArrayList datelist=new ArrayList();
		InputStream is=null;
		try 
		{
			is = new FileInputStream(path);
				Workbook workBook = Workbook.getWorkbook(is);
				// 首个单元�?
				Sheet sheet = workBook.getSheet(0);
				// �?
				int cols = sheet.getColumns();
				// �?
				int rows = sheet.getRows();
				if(rows<=1)
				{
					errorlist.add("你导入的文件是空文件，请编辑正确文件导入");
				}
				// 保证只有�?�?
				if (cols == 1) 
				{
					String columnName = sheet.getCell(0, 0).getContents() == null ? ""
							: sheet.getCell(0, 0).getContents().trim();
						int i=1;
						while(i<rows)
						{
							columnName = sheet.getCell(0, i).getContents() == null ? ""
									: sheet.getCell(0, i).getContents().trim();
							int num = this.checkPhone(columnName);
							if (num == 0) 
							{
								datelist.add(columnName);
							} 
							else if (num == 1) 
							{
								errorlist.add("您的�?"+(i+1)+"行第�?列，输入格式有误，应该为邮箱地址:"+columnName);
							} 
                            else if (num == 3) 
							{
                            	//空号码直接跳�?
                            	//errorlist.add("您的�?"+(i+1)+"行第�?列，号码输入为空");
							}
							++i;
					}
				}
				else
				{
					errorlist.add("请不要输入多�?");
				}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				is.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		datelist = this.removeDuplicate(datelist);
		al[0] = errorlist;
		al[1] = datelist;
		return al;
	}
	
	
	/**
	 * �?查手�?
	 * 
	 * @param phone
	 * @param movable_id
	 *            活动ID
	 * @param queue_id
	 *            批次
	 * @return
	 */
	public  int  checkPhone(String phone)
	{
		boolean flag = false;
		if(phone!=null && !"".equals(phone))
		{
			  String check = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
		       Pattern regex = Pattern.compile(check);  
		       Matcher matcher = regex.matcher(phone);  
		       flag = matcher.matches(); 						
			if(flag)
			{
			     return 0;
			}
			else
			{
				 return 1;
			}
		}
		else
		{
		        return 3;
		}
	}
	
	  @SuppressWarnings("unchecked")
	private   static   ArrayList  removeDuplicate(ArrayList list)  
	  {
	        HashSet h  =   new  HashSet(list);
	        list.clear();
	        list.addAll(h);
	        return list;
	  } 
}