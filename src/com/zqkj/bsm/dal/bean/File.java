package com.zqkj.bsm.dal.bean;

import java.io.Serializable;

public class File implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private long fileID;
	private long userID;//所属用户
	private long folderID;//所属目录id（任务）
	private String folderName;//所属目录名（任务）
	private String name;//文件名
	private long fileSize;//大小
	private String url;//路径
	private String createTime;//创建时间
	private String deleteTime;//删除时间
	private String status;//1-正常，999-删除，888-渲染结果文件
	public long getFileID() {
		return fileID;
	}
	public void setFileID(long fileID) {
		this.fileID = fileID;
	}
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public long getFolderID() {
		return folderID;
	}
	public void setFolderID(long folderID) {
		this.folderID = folderID;
	}
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getDeleteTime() {
		return deleteTime;
	}
	public void setDeleteTime(String deleteTime) {
		this.deleteTime = deleteTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
