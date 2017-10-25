package com.zqkj.bsm.downmodel.bean;

import com.zqkj.bsm.downmodel.annotation.ModelTable;

@ModelTable(value="cxgl_cyrygl",describe="从业人员管理基本信息")
public class CyryglInfo 
{
	@ModelTable(value="name",describe="姓名")
	private String name;
	@ModelTable(value="sex",describe="性别")
	private String sex;
	@ModelTable(value="education",describe="学历")
	private String education;
	@ModelTable(value="major",describe="专业")
	private String major;
	@ModelTable(value="title",describe="职称")
	private String title;
	@ModelTable(value="workUnit",describe="工作单位")
	private String workUnit;
}
