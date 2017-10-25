package com.zqkj.bsm.dal.bean;

import java.io.Serializable;

/**
 * 上传进度状态
 */
public class UploadProgress implements Serializable{
	private static final long serialVersionUID = 1L;
	private long fileID;
	private long pBytesRead;//已经处理的字节数
	private long pContentLength;//需要处理的字节数
	private String fileSize;//一般小于pContentLength
	private long lastTime;
	public long getFileID() {
		return fileID;
	}
	public void setFileID(long fileID) {
		this.fileID = fileID;
	}
	public long getpBytesRead() {
		return pBytesRead;
	}
	public void setpBytesRead(long pBytesRead) {
		this.pBytesRead = pBytesRead;
	}
	public long getpContentLength() {
		return pContentLength;
	}
	public void setpContentLength(long pContentLength) {
		this.pContentLength = pContentLength;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public long getLastTime() {
		return lastTime;
	}
	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}
}
