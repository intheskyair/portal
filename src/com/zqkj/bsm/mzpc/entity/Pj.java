package com.zqkj.bsm.mzpc.entity;

/** 
* @ClassName: Pj 
* @Description: TODO(选人用人专用实体类) 
* @author MJ002 
* @date 2017年1月9日 上午10:21:58 
*  
*/
public class Pj {
	public Pj(String title){
		this.A = 0;
		this.B = 0;
		this.C = 0;
		this.D = 0;
		this.E = 0;
		this.F = 0;
		this.G = 0;
		this.H = 0;
		this.title = title;
	}
	
	private int A;
	
	private String title;
	
	private int B;
	
	private int C;
	
	private int D;
	
	private int E;
	
	private int F;
	
	private int G;
	
	private int H;
	
	
	public void add(int num){
		if(num == 1){
			A = A + 1;
		}
		if(num == 2){
			B = B + 1;
		}
		if(num == 3){
			C = C + 1;
		}
		if(num == 4){
			D = D + 1;
		}
		if(num == 5){
			E = E + 1;
		}
		if(num == 6){
			F = F + 1;
		}
		if(num == 7){
			G = G + 1;
		}
		if(num == 8){
			H = H + 1;
		}
	}
	
	
	

	public int getA() {
		return A;
	}

	public void setA(int a) {
		A = a;
	}

	public int getB() {
		return B;
	}

	public void setB(int b) {
		B = b;
	}

	public int getC() {
		return C;
	}

	public void setC(int c) {
		C = c;
	}

	public int getD() {
		return D;
	}

	public void setD(int d) {
		D = d;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getE() {
		return E;
	}

	public void setE(int e) {
		E = e;
	}

	public int getF() {
		return F;
	}

	public void setF(int f) {
		F = f;
	}

	public int getG() {
		return G;
	}

	public void setG(int g) {
		G = g;
	}

	public int getH() {
		return H;
	}

	public void setH(int h) {
		H = h;
	}
	
	
}
