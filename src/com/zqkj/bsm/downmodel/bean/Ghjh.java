package com.zqkj.bsm.downmodel.bean;

import com.zqkj.bsm.downmodel.annotation.ModelTable;

@ModelTable(value="project_ghjh",describe="规划计划")
public class Ghjh {
	@ModelTable(value="file_type",describe="文件类型")
	private String file_type;
	@ModelTable(value="file_title",describe="标题")
	private String file_title;
	@ModelTable(value="create_time",describe="创建时间")
	private String create_time;
	@ModelTable(value="update_time",describe="更新时间")
	private String update_time;
}
