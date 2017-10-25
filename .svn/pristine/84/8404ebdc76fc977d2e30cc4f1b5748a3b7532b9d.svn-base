//package com.cudatec.render.util;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//
//import org.apache.log4j.Logger;
//import org.apache.tools.zip.ZipEntry;
//import org.apache.tools.zip.ZipOutputStream;
//
//import com.cudatec.render.dao.DirectoryDao;
//import com.zqkj.bsm.system.action.SysPara;
//
///**
// * 完成渲染结果的压缩	
// */
//public class Compression {
//	public static Logger log = Logger.getLogger(Compression.class);
//	/**
//	 * 用zip打包压缩订单文件夹
//	 * @param uid 用户ID
//	 * @param transid 订单ID
//	 */
//	public static void getZip(String uid, String transid) {
//		File srcDir = new File(Configuration.RESULT + uid, transid);
//		System.out.println("压缩路径="+Configuration.RESULT + uid+"/"+transid);
//		String destPath = srcDir.getAbsolutePath() + ".zip";
//		try {
//			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(destPath)));
//			out.setEncoding("GBK");
//			zip(out, srcDir, "");
//			out.close();
//		}
//		catch (FileNotFoundException e2) 
//		{
//
//				SysPara.exceptionMap.put("storage", false);
//				SysPara.exceptionMap.put("storage_time", System.currentTimeMillis());
//			log.info("发生异常:"+e2.getMessage());
//		}
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		// 计算出压缩包的Hash值，并存入storage_directory
//		String hash =  HashUtil.calculate(destPath);
//		File zipname = new File(destPath);
//		long sizeZip = zipname.length();
//		log.info("压缩hash="+hash+ ",uid="+uid+",transid="+transid+"sizeZip="+sizeZip);
//		DirectoryDao.getInstance().updateHash(uid, transid,hash,sizeZip);
//		File renderDir = new File(Configuration.RENDER + uid, transid);
//		if (renderDir.exists()) {
//			RenderUtil.clean(renderDir);
//		}
//	}
//	
//	/**
//	 * 完成渲染结果的重新压缩
//	 * return 
//	 */
//	public static  Boolean getReZip(String uid, String transid) {
//		boolean result = false;
//		File srcDir = new File(Configuration.RESULT + uid, transid);
//		System.out.println("压缩路径="+Configuration.RESULT + uid+"/"+transid);
//		String destPath = srcDir.getAbsolutePath() + ".zip";
//		try {
//			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(destPath)));
//			out.setEncoding("GBK");
//			zip(out, srcDir, "");
//			out.close();
//		}
//		catch (FileNotFoundException e2) 
//		{
//
//				SysPara.exceptionMap.put("storage", false);
//				SysPara.exceptionMap.put("storage_time", System.currentTimeMillis());
//			log.info("发生异常:"+e2.getMessage());
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		// 计算出压缩包的Hash值，并存入storage_directory
//		String hash =  HashUtil.calculate(destPath);
//		File zipname = new File(destPath);
//		long sizeZip = zipname.length();
//		log.info("压缩hash="+hash+ ",uid="+uid+",transid="+transid+"sizeZip="+sizeZip);
//		DirectoryDao dd=DirectoryDao.getInstance();
//		if(dd.updateCom(uid, transid,hash,sizeZip)){
//			result = true;
//		}
//		File renderDir = new File(Configuration.RENDER + uid, transid);
//		if (renderDir.exists()) {
//			RenderUtil.clean(renderDir);
//		}
//		return result;
//	}
//	
//	public static void getRenderZip(String uid, String transid) 
//	{
//		if(!uid.equals("")&&!transid.equals(""))
//		{
//			File srcDir = new File(Configuration.RENDER + uid, transid);
//			log.info("压缩路径="+Configuration.RENDER + uid+"/"+transid);
//			String destPath = srcDir.getAbsolutePath() + ".zip";
//			try {
//				ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(destPath)));
//				out.setEncoding("GBK");
//				zip(out, srcDir, "");
//				out.close();
//			}
//			catch (FileNotFoundException e2) 
//			{
//
//					SysPara.exceptionMap.put("storage", false);
//					SysPara.exceptionMap.put("storage_time", System.currentTimeMillis());
//				log.info("发生异常:"+e2.getMessage());
//			}
//			catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		// 计算出压缩包的Hash值，并存入storage_directory
////		String hash =  HashUtil.calculate(destPath);
////		System.out.println("压缩hash="+hash+ ",uid="+uid+",transid="+transid);
////		DirectoryDao.getInstance().updateHash(uid, transid,hash);
////		File renderDir = new File(Configuration.RENDER + uid, transid);
////		if (renderDir.exists()) {
////			RenderUtil.clean(renderDir);
////		}
//	}
//	
//	public static void getresultZip(String uid, String transid) 
//	{
//		if(!uid.equals("")&&!transid.equals(""))
//		{
//			File srcDir = new File(Configuration.RESULT + uid, transid);
//			log.info("结果压缩路径="+Configuration.RESULT + uid+"/"+transid);
//			String destPath = srcDir.getAbsolutePath() + ".zip";
//			try {
//				ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(destPath)));
//				out.setEncoding("GBK");
//				zip(out, srcDir, "");
//				out.close();
//			} 
//			catch (FileNotFoundException e2) 
//			{
//					SysPara.exceptionMap.put("storage", false);
//					SysPara.exceptionMap.put("storage_time", System.currentTimeMillis());
//				log.info("发生异常:"+e2.getMessage());
//			}
//			catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		// 计算出压缩包的Hash值，并存入storage_directory
////		String hash =  HashUtil.calculate(destPath);
////		System.out.println("压缩hash="+hash+ ",uid="+uid+",transid="+transid);
////		DirectoryDao.getInstance().updateHash(uid, transid,hash);
////		File renderDir = new File(Configuration.RENDER + uid, transid);
////		if (renderDir.exists()) {
////			RenderUtil.clean(renderDir);
////		}
//	}
//	
//	
//	/**
//	 * 递归压缩子文件和子文件夹
//	 * @param out zip文件输出流
//	 * @param f 当前文件或文件夹
//	 * @param base 当前文件或文件夹名称
//	 */
//	private static void zip(ZipOutputStream out, File f, String base) throws Exception {
//		if (f.isDirectory()) {
//			out.putNextEntry(new ZipEntry(base + "/"));
//			base = base.length() != 0 ? base + "/" : "";
//			File[] fl = f.listFiles();
//			for (int i = 0; i < fl.length; ++i) {
//				zip(out, fl[i], base + fl[i].getName());
//			}
//		} else {
//			out.putNextEntry(new ZipEntry(base));
//			int nRead;
//			byte[] buffer = new byte[4096];
//			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
//			while ((nRead = bis.read(buffer)) != -1) {
//				out.write(buffer, 0, nRead);
//			}
//			bis.close();
//		}
//	}
//}
