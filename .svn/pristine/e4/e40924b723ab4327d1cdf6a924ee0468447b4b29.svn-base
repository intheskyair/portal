<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/meta.jsp"%>
<title>资产录入审批详情</title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css" />
<script type="text/javascript">
	$(function() {
	
				
				
		$("input[type=radio][value=${map.qtype}]").attr("checked", 'checked');
		
		
        		
        		switch($("#assettype").val()){
							case "1":
								$("#assettype").val("房屋及构筑物");
								break;
							case "2":
								$("#assettype").val("专用设备");
								break;
							case "3":
								$("#assettype").val("通用设备");
								break;
							case "4":
								$("#assettype").val("文物和陈列品");
								break;
							case "5":
								$("#assettype").val("图书、档案");
								break;
							case "6":
								$("#assettype").val("家具、用具、装具、动植物");
								break;
						} 
        		
	});
	
	
	
	
	
</script>
</head>
<body style="overflow: scroll;">
	<p class="btt"
		style=" font-size:30px; margin-top:30px; margin-bottom:20px">资产领用审批详情</p>
	<table class="table06" cellpadding="0" cellspacing="0">
		<tr>
			<td style=" width:150px;">资产名称</td>
			<td colspan="2" style=" width:100px;"><input type="text"
				id="asset_name" readonly="readonly" value="${map.asset_name}"
				class="srk" /></td>
			<td>资产编号</td>
			<td colspan="2"><input type="text" id="asset_code"
				value="${map.asset_code}" readonly="readonly" class="srk" /></td>
		</tr>
		<tr>
			<td style=" width:150px;">资产分类</td>
			<td colspan="2" style=" width:100px;"><input type="text"
				id="assettype" readonly="readonly" value="${map.asset_type}"
				class="srk" /></td>

			<td>品牌</td>
			<td colspan="2"><input type="text" id="asset_brand"
				readonly="readonly" value="${map.asset_brand}" class="srk" /></td>
		</tr>
		<tr>
			<td style=" width:150px;">申请人</td>
			<td colspan="2" style=" width:100px;"><input type="text"
				id="asset_user" value="${map.asset_user}"
				readonly="readonly" class="srk" /></td>
			<td>申请时间</td>
			<td colspan="2" style=" width:100px;"><input type="text"
				id="asset_applytime" value="${map.asset_applytime}"
				readonly="readonly" class="srk" /></td>
		</tr>
		<tr>
			<td>资产具体编号</td>
			<td colspan="5" style=" width:100px;"><input type="text"
				id="asset_id" value="${map.asset_id}"
				readonly="readonly" class="srk" /></td>
		</tr>
		<tr>
			<td>备注</td>
			<td colspan="5"><input type="text" id="asset_remark"
				value="${map.asset_remark}" readonly="readonly" class="srk" /></td>
		</tr>
		<tr>
			<td>审批意见</td>
			<td colspan="5" style=" width:100px;"><textarea
					style="overflow:hidden" id="spyj" readonly="readonly" class="srk">${map.rect3method == '0' ? '同意' : ''}${map.rect3method == '-1' ? '不同意' : ''}<c:if
						test="${not empty map.rect3suggest}">(${map.rect3suggest})</c:if></textarea></td>

		</tr>
	</table>
	<div id="asset_status0_list"></div>
	<p></p>
</body>
</html>
