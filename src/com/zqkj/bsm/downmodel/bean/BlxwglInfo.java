package com.zqkj.bsm.downmodel.bean;

import com.zqkj.bsm.downmodel.annotation.ModelTable;

@ModelTable(value="cxgl_blxwgl",describe="不良行为管理基本信息")
public class BlxwglInfo 
{
	@ModelTable(value="enterpriseName",describe="企业名称")
	private String enterpriseName;
	@ModelTable(value="content",describe="内容")
	private String content;
	@ModelTable(value="type",describe="企业类型")
	private String type;
	@ModelTable(value="time",describe="时间")
	private String time;
}
