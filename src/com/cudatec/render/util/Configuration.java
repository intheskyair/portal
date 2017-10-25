package com.cudatec.render.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * 存储配置类
 * 部署时，src/storage.properties 会自动复制到 classes/storage.properties
 * 当前这个类会取得 classes/storage.properties 这个配置文件
 */
public class Configuration {
	public static String UPLOAD;			// 文件分块上传路径
	public static String STORAGE;			// 网盘文件存储路径
	public static String RENDER;			// 待渲染文件存储路径
	public static String RESULT;			// 结果文件存储路径
	public static String CLUSTER_RENDER;	// 集群访问渲染文件路径
	public static String CLUSTER_RESULT;	// 集群访问结果文件路径
	
	public static String PORTAL_URL;		// 文件同步完成要调用的运维接口
	
	public static String DBURL;				// MySQL数据库URL
	public static String DBUSER;			// MySQL数据库用户名
	public static String DBPWD;				// MySQL数据库密码
	
	
	public static String YW_VERSION;				// 运维的版本号
	
	public static boolean onWindows = false;		// 是否在 Windows 中运行Tomcat
	
	public static int BLOCK_SIZE = 10485760;	// 1024 * 1024 * 10，分块上传时的块大小
		
	public static final int SHA1_LENGTH = 40;		// SHA-1哈希值的长度为40个字符（20个字节）
	
	public static List<String> documents = null;	// 文档文件的后缀列表，以下相同
	public static List<String> pictures = null;
	public static List<String> music = null;
	public static List<String> videos = null;
	public static List<String> archives = null;
	public static List<String> max = null;
	public static List<String> maya = null;
	
	static {
		// 获取存储的配置文件
		reload();
	}
	
	/**
	 * 重新读取配置文件
	 */
	public static void reload() {
//		Properties properties = new Properties();
//		try {
//			properties.load(Configuration.class.getResourceAsStream("/storage.properties"));
//			UPLOAD = properties.getProperty("UPLOAD");
//			STORAGE = properties.getProperty("STORAGE");
//			RENDER = properties.getProperty("RENDER");
//			RESULT = properties.getProperty("RESULT");
//			CLUSTER_RENDER = properties.getProperty("CLUSTER_RENDER");
//			CLUSTER_RESULT = properties.getProperty("CLUSTER_RESULT");
//			
//			PORTAL_URL = properties.getProperty("PORTAL_URL");
//			
//			DBURL = properties.getProperty("DBURL");
//			DBUSER = properties.getProperty("DBUSER");
//			DBPWD = properties.getProperty("DBPWD");
//			YW_VERSION = properties.getProperty("YW_VERSION");
//			BLOCK_SIZE = Integer.valueOf(properties.getProperty("PER_SIZE")).intValue()*1024 * 1024;
//			System.out.println("每块文件大小BLOCK_SIZE="+BLOCK_SIZE);
//			documents = Arrays.asList(properties.getProperty("DOCUMENT").split("\\|"));
//			pictures = Arrays.asList(properties.getProperty("PICTURE").split("\\|"));
//			music = Arrays.asList(properties.getProperty("MUSIC").split("\\|"));
//			videos = Arrays.asList(properties.getProperty("VIDEO").split("\\|"));
//			archives = Arrays.asList(properties.getProperty("ARCHIVE").split("\\|"));
//			max = Arrays.asList(properties.getProperty("MAX").split("\\|"));
// 			maya = Arrays.asList(properties.getProperty("MAYA").split("\\|"));
//			
//			onWindows = "Windows_NT".equals(System.getenv("OS"));
//		} catch (IOException e) {
//			System.err.println("没有找到 storage.properties，无法读取存储配置");
//			e.printStackTrace();
//		}
	}
}

