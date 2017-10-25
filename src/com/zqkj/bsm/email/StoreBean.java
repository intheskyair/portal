package com.zqkj.bsm.email;

public class StoreBean 
{
	//节点信息
	private String noteInfo;
	//节点总大小
	private String total;
	//已经被使用
	private String userd;
	//剩下多少
	private String yuxia;
	//使用百分比
	private String per;
	//本机目录
	private String localDir;
	//挂在是否正常
	private boolean gz_bz; 
	public boolean isGz_bz() {
		return gz_bz;
	}
	public void setGz_bz(boolean gz_bz) {
		this.gz_bz = gz_bz;
	}
	public String getLocalDir() {
		return localDir;
	}
	public void setLocalDir(String localDir) {
		this.localDir = localDir;
	}
	public String getNoteInfo() {
		return noteInfo;
	}
	public void setNoteInfo(String noteInfo) {
		this.noteInfo = noteInfo;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getUserd() {
		return userd;
	}
	public void setUserd(String userd) {
		this.userd = userd;
	}
	public String getYuxia() {
		return yuxia;
	}
	public void setYuxia(String yuxia) {
		this.yuxia = yuxia;
	}
	public String getPer() {
		return per;
	}
	public void setPer(String per) {
		this.per = per;
	}

}
