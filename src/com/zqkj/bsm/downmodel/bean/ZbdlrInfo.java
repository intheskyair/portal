package com.zqkj.bsm.downmodel.bean;

import com.zqkj.bsm.downmodel.annotation.ModelTable;

@ModelTable(value="project_zbdlrjbxx",describe="招标代理人基本信息")
public class ZbdlrInfo 
{
	@ModelTable(value="company_name",describe="企业名称")
	private String company_name;
	@ModelTable(value="company_yyzzh",describe="法人营业执照注册号")
	private String company_yyzzh;
	@ModelTable(value="company_phone",describe="企业联系电话")
	private String company_phone;
	@ModelTable(value="company_nj_time",describe="企业年检最新日期")
	private String company_nj_time;
	@ModelTable(value="company_gczbdlzzdj",describe="工程招标代理资质等级(甲乙）")
	private String company_gczbdlzzdj;
	@ModelTable(value="company_gczbdlzzdj_bh",describe="工程招标代理资质等级的证书编号")
	private String company_gczbdlzzdj_bh;
	@ModelTable(value="company_gczbdlzzdj_time",describe="招标代理资质证书的有效期")
	private String company_gczbdlzzdj_time;
	@ModelTable(value="company_gczjzxzzdj",describe="工程造价咨询资质等级")
	private String company_gczjzxzzdj;
	@ModelTable(value="company_gczjzxzzdj_bh",describe="造价咨询资质证书的编号")
	private String company_gczjzxzzdj_bh;
	@ModelTable(value="company_gczjzxzzdj_time",describe="造价咨询资质证书有效期")
	private String company_gczjzxzzdj_time;
	@ModelTable(value="company_address",describe="企业所在地址")
	private String company_address;
	@ModelTable(value="company_frdb",describe="企业法人代表")
	private String company_frdb;
	@ModelTable(value="company_frdb_phone",describe="法人代表的电话")
	private String company_frdb_phone;
}
