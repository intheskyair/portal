<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/commonCSSJS.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
</head>
<body style="position:relative;">
<div class="div div-2" style="">
	<table id="myasset_grid" style="display:none;padding:0 0 0 0;"></table>
</div>
 <script type="text/javascript">
var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
var page_num = $(window.self.document).find("#page_num").val();
var page_num =30;
 var myasset_grid = {
	//绑定参数信息grid
	myasset_grid_result:"",
	myassetGridHeight:flexHeight("myasset_grid"),
	bindParamGrid:function(queryP,queryM){
		myasset_grid.myasset_grid_result = $("#myasset_grid").flexigrid({
			url:"assetapply!getMyAssetList.action?operation=init",
			colModel: queryM,
			buttons : queryP,
			rowId:"id",
			showToggleBtn: true,
			usepager: true,
			title:"我的资产列表",
			//rp:pageSize,
			rp:page_num,
			showTableToggleBtn: false,
			resizable: false,
			height:myasset_grid.myassetGridHeight,
			singleSelect: true,
			onChangePage:myasset_grid.loadData,
			checkbox:false,
		});
	},	
	
	loadData:function(param){
		var queryP = {};
		queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
		queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
		$.ajax({
			type: "POST",
			url: "assetapply!getMyAssetList.action?operation=init",
			cache: false,
			data: queryP,
			success: function(msg){
				var data=eval("("+msg+")");
				try{
					myasset_grid.myasset_grid_result.flexAddData(data);
				}catch(e){
					showErrorMsg("查询结果信息出错！");
				}
			}
		}); 
	}		

};
	 $(function(){
		//初始化所有操作按钮
		var  myasset_button = [
		 ];
		 //权限操作按钮
		var colModel = [
					{display: '序号', name : 'xh', width : 40, align: 'center'},
					{display: '资产名称', name : 'ASSETNAME', width : 100,  align: 'center'},
					{display: '资产编号', name : 'CODE', width : 50,  align: 'center'},
					{display: '资产具体编号', name : 'ID', width : 120,  align: 'center'},
					{display: '单价', name : 'PRICE', width : 50,  align: 'center'},
					{display: '数量', name : 'TOTAL', width : 50,  align: 'center'},
					{display: '品牌', name : 'BRAND', width : 100,  align: 'center'},
					{display: '使用者', name : 'USER', width : 100,  align: 'center'},
					{display: '状态', name : 'STATUS', width : 70,  align: 'center',
						process:function(obj){
							switch($(obj).html()){
								case "1":
									$(obj).html("可申请领用");
									break;
								case "2":
									$(obj).html("领用申请中");
									break;
								case "3":
									$(obj).html("使用中");
									break;
							}
						
						}
					},
					{display: '采购批次', name : 'ORDERID', width : 300, align: 'center'}					
				];
		
		 //绑定参数
		 myasset_grid.bindParamGrid(myasset_button,colModel); 
		/*参数管理-查询按钮功能*/
	});
</script>
</body>
</html>