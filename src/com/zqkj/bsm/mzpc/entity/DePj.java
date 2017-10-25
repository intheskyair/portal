package com.zqkj.bsm.mzpc.entity;

import java.math.BigDecimal;

/** 
* @ClassName: DePj 
* @Description: TODO(德的专项测评汇） 
* @author MJ002 
* @date 2017年1月9日 上午10:22:11 
*  
*/
public class DePj {
	
	public DePj(int total,int now,String type,String msg){
		this.now = now;
		this.type = type; 
		this.msg = msg;
		this.p5 = total - now;
		this.p5BFb = new BigDecimal(p5).divide(new BigDecimal(total),2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue();
	}
	
	
	public void  sum(int value){
		if(value == 1){
			p1 += 1;
			p1Bfb = new BigDecimal(p1).divide(new BigDecimal(now),2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue();
		}else if(value == 2){
			p2 += 1;
			p2Bfb = new BigDecimal(p2).divide(new BigDecimal(now),2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue();
		}else if(value == 3){
			p3 += 1;
			p3Bfb = new BigDecimal(p3).divide(new BigDecimal(now),2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue();
		}else if(value == 4){
			p4 += 1;
			p4Bfb = new BigDecimal(p4).divide(new BigDecimal(now),2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue();
		}
	}
	

	
	//当前头票数
	
	private int now;
	
	//未投票数
	private int new1;
	//未投票百分比
	private int new1Bfb;
	
	//评价内容
	private String type;
	//评价内容
	private String msg;
	//没问题票数
	private int p1;
	//没问题票数百分比
	private int p1Bfb;
	
	private int p2;
	
	private int p2Bfb;
	
	private int p3;
	
	private int p3Bfb;
	
	private int p4;
	
	private int p4Bfb;
	
	private int p5;
	
	private int p5BFb;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getP1() {
		return p1;
	}

	public void setP1(int p1) {
		this.p1 = p1;
	}

	public int getP1Bfb() {
		return p1Bfb;
	}

	public void setP1Bfb(int p1Bfb) {
		this.p1Bfb = p1Bfb;
	}

	public int getP2() {
		return p2;
	}

	public void setP2(int p2) {
		this.p2 = p2;
	}

	public int getP2Bfb() {
		return p2Bfb;
	}

	public void setP2Bfb(int p2Bfb) {
		this.p2Bfb = p2Bfb;
	}

	public int getP3() {
		return p3;
	}

	public void setP3(int p3) {
		this.p3 = p3;
	}

	public int getP3Bfb() {
		return p3Bfb;
	}

	public void setP3Bfb(int p3Bfb) {
		this.p3Bfb = p3Bfb;
	}

	

	


	public int getP4() {
		return p4;
	}


	public void setP4(int p4) {
		this.p4 = p4;
	}


	public int getP4Bfb() {
		return p4Bfb;
	}

	public void setP4Bfb(int p4Bfb) {
		this.p4Bfb = p4Bfb;
	}

	public int getP5() {
		return p5;
	}

	public void setP5(int p5) {
		this.p5 = p5;
	}

	public int getP5BFb() {
		return p5BFb;
	}

	public void setP5BFb(int p5bFb) {
		p5BFb = p5bFb;
	}


	public int getNew1Bfb() {
		return new1Bfb;
	}


	public void setNew1Bfb(int new1Bfb) {
		this.new1Bfb = new1Bfb;
	}
	
	

}
