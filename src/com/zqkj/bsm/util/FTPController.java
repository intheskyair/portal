package com.zqkj.bsm.util;
//package com.zqkj.bsm.dal.util;
//
//import java.io.IOException;
//
//import sun.net.ftp.FtpClient;
//
//public class FTPController {
//	/**
//	 * @param directory服务器保存文件目录
//	 * @param localFile当地文件
//	 */
//	public void upload(String directory,String localFile){
//		try {
//			FtpClient client=new FtpClient("ip");//打开ftp客户端：主机、端口（可省略默认端口23）
//			client.login("user", "password");//用户、密码
//			client.cd(directory);//服务器目录
//			client.binary();//二进制传输
//			client.put(localFile);//将该文件写到文件服务器
//			client.closeServer();//关闭
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//}
