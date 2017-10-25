package com.zqkj.bsm.downmodel.bean;

import com.zqkj.bsm.downmodel.annotation.ModelTable;

/**
 * @author user
 *
 */

@ModelTable(value="tzgl_investplan",describe="投资计划安排基本信息")
public class InvestplanInfo 
{
	@ModelTable(value="projectname",describe="项目名称")
	private String projectname;
	@ModelTable(value="bulidscale",describe="建设规模")
	private String bulidscale;
	@ModelTable(value="totalinvest",describe="总投资")
	private String totalinvest;
	@ModelTable(value="firstquarter",describe="第一季度")
	private String firstquarter;
	@ModelTable(value="secondquarter",describe="第二季度")
	private String secondquarter;
	@ModelTable(value="thirdquarter",describe="第三季度")
	private String thirdquarter;
	@ModelTable(value="fourthquarter",describe="第四季度")
    private String fourthquarter;
}
