package com.zqkj.bsm.downmodel.bean;

import com.zqkj.bsm.downmodel.annotation.ModelTable;

@ModelTable(value="zcgl_assetinfo",describe="资产信息管理基本信息")
public class AssetinfoInfo 
{
	
	@ModelTable(value="assetname",describe="资产名称")
	private String assetname;
	@ModelTable(value="code",describe="资产编号")
	private String code;
	@ModelTable(value="place",describe="存放地点")
	private String place;
	@ModelTable(value="remark",describe="备注")
	private String remark;
	@ModelTable(value="assettype",describe="资产类型")
	private String assettype;
}
