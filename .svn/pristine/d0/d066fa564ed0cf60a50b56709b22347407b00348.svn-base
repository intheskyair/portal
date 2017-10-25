package com.zqkj.bsm.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
public final class UploadFTPClient extends Thread{
	private static FTPClient ftpClient = new FTPClient();
	private String remoteFile;
	private String localFile;
	public UploadFTPClient(String remoteFile, String localFile){
		this.remoteFile= remoteFile;
		this.localFile = localFile;
		connect();
	}
	private static boolean connect() {
//			try {
//				FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
//			    conf.setServerLanguageCode("zh");
//			    ftpClient.configure(conf);
//			    System.out.println("连接文件服务器……");
//				ftpClient.connect(ConstantsDal.UPLOAD_IP, ConstantsDal.UPLOAD_PORT);
//				System.out.println("登录文件服务器……");
//				if (!ftpClient.login(ConstantsDal.UPLOAD_USERNAME, ConstantsDal.UPLOAD_PASSWORD)){
//					System.out.println("登录文件服务器失败");
//					ftpClient.logout();
//					return false;
//				}
//			} catch (SocketException e) {
//				e.printStackTrace();
//				return false;
//			} catch (IOException e) {
//				e.printStackTrace();
//				return false;
//			}
		return true;
	}
	@Override
	public void run() {
		InputStream input = null;
		try {
			if(!ftpClient.isAvailable()){
				if(!connect()){
					System.exit(1);
				}
			}
			ftpClient.setConnectTimeout(1800000);//30分钟=1800000毫秒
			ftpClient.setDataTimeout(1800000);//30分钟=1800000毫秒
			ftpClient.setControlKeepAliveTimeout(300);//5分钟=300秒
			ftpClient.setControlKeepAliveReplyTimeout(5000);//5分钟=5000毫秒
			ftpClient.setControlEncoding("utf-8");
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			int reply = ftpClient.getReplyCode();
			if(!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				System.err.println("文件服务器拒绝访问");
				System.exit(1);
			}
			ftpClient.makeDirectory(remoteFile.substring(0,remoteFile.lastIndexOf("/")));//先创建上传目录
			input = new FileInputStream(localFile);
			System.out.println("上传文件……："+localFile);
			boolean storeFile = ftpClient.storeFile(remoteFile, input);
			if(storeFile){
				File f = new File(localFile);
				f.deleteOnExit();//转移成功后删除原文件
				System.out.println("上传成功："+localFile);
			}else{
				System.out.println("上传失败："+localFile);
			}
			if(input != null){
				input.close();
			}
		} catch (SocketException e) {
			e.printStackTrace();
			System.out.println("上传文件失败："+localFile);
			System.exit(1);
		} catch (IOException e) {
			System.out.println("上传文件失败："+localFile);
			e.printStackTrace();
			System.exit(1);
		}
	}
}

