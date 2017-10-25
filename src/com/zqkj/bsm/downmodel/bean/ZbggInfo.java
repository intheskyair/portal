package com.zqkj.bsm.downmodel.bean;

import com.zqkj.bsm.downmodel.annotation.ModelTable;

@ModelTable(value="project_zbgg",describe="招标公告基本信息")
public class ZbggInfo 
{
	@ModelTable(value="zb_title",describe="招标标题")
	private String zb_title;
	@ModelTable(value="zb_project",describe="招标项目")
	private String zb_project;
	@ModelTable(value="zb_pizhun",describe="项目批准人")
	private String zb_pizhun;
	@ModelTable(value="zb_yezhu",describe="项目业主")
	private String zb_yezhu;
	@ModelTable(value="zb_zjly",describe="建设资金来源")
	private String zb_zjly;
	@ModelTable(value="zb_ren",describe="招标人")
	private String zb_ren;
	@ModelTable(value="zb_dljg",describe="招标代理机构")
	private String zb_dljg;
	@ModelTable(value="zb_bianhao",describe="招标编号")
	private String zb_bianhao;
	@ModelTable(value="zb_jsdd",describe="建设地点")
	private String zb_jsdd;
	@ModelTable(value="zb_gcztz",describe="工程总投资（万元）")
	private String zb_gcztz;
	@ModelTable(value="zb_zgkj",describe="招标最高控价")
	private String zb_zgkj;
	@ModelTable(value="zb_bkjjje",describe="不可竞争金额")
	private String zb_bkjjje;
	@ModelTable(value="zb_fanwei",describe="招标范围")
	private String zb_fanwei;
	@ModelTable(value="zb_begtime",describe="招标开始时间")
	private String zb_begtime;
	@ModelTable(value="zb_endtime",describe="招标结束时间")
	private String zb_endtime;
}
