package com.cudatec.render.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

/**
 * 文件路径工具类
 */
public class PathUtil {
	/**
	 * 根据文件的哈希值，确定文件在集中式存储中的路径
	 * @param sha1 文件哈希值
	 * @return 文件绝对路径
	 */
	public static String getPathFromHash(String sha1) {
		StringBuilder sb = new StringBuilder(Configuration.STORAGE);
		sb.append(sha1.substring(0, 2));
		sb.append('/');
		sb.append(sha1.substring(2, 4));
		sb.append('/');
		sb.append(sha1);
		return sb.toString();
	}
	
	public static String getfolderFromHash(String sha1) {
		StringBuilder sb = new StringBuilder(Configuration.STORAGE);
		sb.append(sha1.substring(0, 2));
		sb.append('/');
		sb.append(sha1.substring(2, 4));
		//sb.append('/');
		return sb.toString();
	}
	
	/**
	 * 根据文件名，确定文件的类型
	 * @param fullname 文件完整名称
	 * @return 文件类型
	 */
	public static String getFileType(String fullname) {
		int index = fullname.lastIndexOf(".");
		if (index >= 0) {
			String extension = fullname.substring(index + 1).toLowerCase();	// eg. hello.TXT -> txt
			if (Configuration.pictures.contains(extension)) {
				return "picture";
			} else if (Configuration.max.contains(extension)) {
				return "max";
			} else if (Configuration.maya.contains(extension)) {
				return "maya";
			} else if (Configuration.documents.contains(extension)) {
				return "document";
			} else if (Configuration.music.contains(extension)) {
				return "music";
			} else if (Configuration.videos.contains(extension)) {
				return "video";
			} else if (Configuration.archives.contains(extension)) {
				return "archive";
			}
		}
		return "other";
	}
	
	/**
	 * 根据文件路径，获取文件名
	 * @param path
	 * @return
	 */
	public static String getFileName(String path) {			// eg. D:/mytest/myfile.txt -> myfile.txt
		int index = path.lastIndexOf("/");
		if (index >= 0) {
			return path.substring(index + 1);
		} else {
			return path;
		}
	}
	
	/**
	 * 根据文件路径，获取文件父路径
	 * @param path
	 * @return
	 */
	public static String getParentPath(String path) {		// eg. D:/mytest/myfile.txt -> D:/mytest/
		int index = path.lastIndexOf("/");
		if (index >= 0) {
			return path.substring(0, index + 1);
		} else {
			return path;
		}
	}
	
	public static void Copy(File src, File dest) {
		File parent = dest.getParentFile();
		if (!parent.exists()) {
			parent.mkdirs();
		}
		FileInputStream fin = null;
		FileOutputStream fout = null;
		FileChannel in = null;
		FileChannel out = null;
		try {
			fin = new FileInputStream(src);
			fout = new FileOutputStream(dest);
			in = fin.getChannel();
			out = fout.getChannel();
			in.transferTo(0, in.size(), out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(fin!=null)
				fin.close();
				if(fout!=null)
				fout.close();
				if(in!=null)
				in.close();
				if(out!=null)
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String getTargetPath(String uid, String trans_id, String absolutepath) {
		StringBuilder sb = new StringBuilder(Configuration.CLUSTER_RENDER);
		sb.append(uid);
		sb.append('/');
		sb.append(trans_id);
		sb.append('/');
		sb.append(absolutepath.replace('\\', '/').replaceAll(":", ""));
		return sb.toString();
	}
	
	public static String getOutputpath(String uid, String trans_id) {
		StringBuilder sb = new StringBuilder(Configuration.CLUSTER_RESULT);
		sb.append(uid);
		sb.append('/');
		sb.append(trans_id);
		sb.append('/');
		return sb.toString();
	}
	
	
	
	//遍历指定文件夹下面的所有文件
    public static void refreshFileList(String strPath,ArrayList<String> list) { 
        File dir = new File(strPath); 
        File[] files = dir.listFiles(); 
        
        if (files == null) 
        {
            return; 
        }
        for (int i = 0; i < files.length; i++) 
        { 
            if (files[i].isDirectory()) 
            { 
                refreshFileList(files[i].getAbsolutePath(),list); 
            } 
            else 
            { 
                //String strFileName = files[i].getAbsolutePath();
            	//System.out.println("---"+strFileName);
                list.add(files[i].getAbsolutePath());                    
            } 
        } 
    }

	   public static void main(String[] args) 
	   { 
		   String path="D:/apach+tomcat+liunx";
		   ArrayList<String> list = new ArrayList<String>();
		   PathUtil.refreshFileList(path, list);
		   for(String str:list)
		   {
			   System.out.println(str);
		   }
	   }
	
}
