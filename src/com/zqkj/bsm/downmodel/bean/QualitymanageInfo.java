package com.zqkj.bsm.downmodel.bean;

import com.zqkj.bsm.downmodel.annotation.ModelTable;

@ModelTable(value="jsgl_qualitymanage",describe="质量管理基本信息")
public class QualitymanageInfo 
{
	@ModelTable(value="publisher",describe="发布者")
	private String publisher;
	@ModelTable(value="type",describe="类型")
	private String type;
	@ModelTable(value="createtime",describe="创建时间")
	private String createtime;	
}
