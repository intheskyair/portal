package com.zqkj.bsm.downmodel.bean;

import com.zqkj.bsm.downmodel.annotation.ModelTable;

@ModelTable(value="jsgl_qualityreport",describe="质监申报基本信息")
public class QualityreportInfo 
{
	@ModelTable(value="publisher",describe="申请人")
	private String publisher;
	@ModelTable(value="type",describe="类型")
	private String type;
	@ModelTable(value="createtime",describe="创建时间")
	private String createtime;	
}
