<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/commonCSSJS.jsp"%>
<html>
<head>
<meta charset="utf-8" />
	 <script>x=-12</script>
</head>
<body>
	<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='images/fold.png'
					onclick="showAndHide(this,'assetoverview_grid');" class="imgShrotate">
				<font>查询条件</font>
			</h3>
		</div>
		<div class="div-main-1">
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>

					<td class="wi-align-l"><label for="input_1" class="lbl-1">
							资产名称: </label> <input type="text" id="serch_zcmc" name="serch_zcmc"
						class="wi-ipt-1" /></td>
					<td class="wi-align-l" style="width:250px;"><label
						for="input_1" class="lbl-1"> 品牌： </label> <input type="text"
						id="serch_pp" name="serch_pp" class="wi-ipt-1" /></td>
					<td class="wi-align-l">
						<button id="btn_assetoverview_search_assetoverview"
							class="ui-state-default ui-state-qik">查询
						</button>
						<button id="btn_result_empty_assetoverview"
							class="ui-state-default ui-state-qik">清空
						</button>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="div div-2" style="">
		<table id="assetoverview_grid" style="display:none;padding:0 0 0 0;"></table>
		<input type="hidden" id="assetdetailinfo_code" />
		<input type="hidden" id="assetdetailinfo_brand" />
	</div>
<div id="dlg_assetdetailinfo" style="display:none;">
	<div class="div div-2 " style="height:350px;">
		<table id="assetdetailinfo_grid" style="display:none;padding:0 0 0 0;"></table>
	</div>
</div>	


	<script type="text/javascript">
	var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
		var param_grid_user;
		var sub_grid;
		page_num : 30;
		 
		 
		 var param_assetoverview = {
			//绑定参数信息grid
			GridHeight:flexHeight("assetoverview_grid"),
			dlg_assetdetailinfo:"",
			bindParamGrid:function(queryP,queryM){
				param_grid_user = $("#assetoverview_grid").flexigrid({
					url:"assetmanager!getAssetOverView.action?operation=init",
					colModel: queryM,
					buttons : queryP,
					rowId:"id",
					showToggleBtn: true,
					usepager: true,
					title:"资产总览",
					//rp:pageSize,
					rp:param_assetoverview.page_num,
					showTableToggleBtn: false,
					resizable: false,
					height:param_assetoverview.GridHeight,
					singleSelect: true,
					onChangePage:param_assetoverview.loadData,
					checkbox:false,
					filterParams : [
					{key : "assetname", name : "#serch_zcmc"},
					{key : "brand", name : "#serch_pp"}
					]
				});
			},
			
			bindLineSubMissionGrid : function() {
			sub_grid = $("#assetdetailinfo_grid").flexigrid({
				url:"assetmanager!getAssetDetailInfoByCodeAndBrand.action?code="+$("#assetdetailinfo_code").val()+"&brand="+encodeURI(encodeURI($("#assetdetailinfo_brand").val())),
				colModel : [
					{display: '序号', name : 'xh', width :30, align: 'center'},
					{display: '资产名称', name : 'ASSETNAME', width : 50,  align: 'center'},
					{display: '资产编号', name : 'CODE', width :50,  align: 'center'},
					{display: '资产具体编号', name : 'ID', width : 120,  align: 'center'},
					{display: '单价', name : 'PRICE', width :30,  align: 'center'},
					{display: '数量', name : 'TOTAL', width : 30,  align: 'center'},
					{display: '品牌', name : 'BRAND', width : 40,  align: 'center'},
					{display: '采购人', name : 'PURCHASER', width :40,  align: 'center'},
					{display: '采购时间', name : 'PURCHASETIME', width : 150,  align: 'center'},
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
					{display: '使用者', name : 'USER', width : 100,  align: 'center',
						process:function(obj){
							if($(obj).html()=="null"){
									$(obj).html("无");
							}
						
						}
					},
					{display: '采购批次', name : 'ORDERID', width : 300, align: 'center'}					
				],
				rowId:"xh",
				showToggleBtn: false,
				usepager: true,
				title:"资产详情列表",
				rp:60,
				page:1,
				showTableToggleBtn: false,
				resizable: false,
				height:270,//*32/50
				singleSelect: true,
				onChangePage:param_assetoverview.loadSubData,
				checkbox:false,
				filterParams : []
			});
		},
			
			
			
			loadSubData : function(param) {
			param.pagenum = param.page?param.page:$('.pcontrol input', $("#dlg_assetdetailinfo")).val();
			param.pagesize = param.rp?param.rp:$("[name=rp]", $("#dlg_assetdetailinfo")).val();
			//var jobId = $('#line_main_id').val();
			$.ajax({
				type: "POST",
				url: "assetmanager!getAssetDetailInfoByCodeAndBrand.action?code="+$("#assetdetailinfo_code").val()+"&brand="+encodeURI(encodeURI($("#assetdetailinfo_brand").val())),
				cache: false,
				data: param,
				success: function(result){
					//var data = (new Function("","return " + result))();
					var data=eval("("+result+")");
					try{
							sub_grid.flexAddData(data);
					}catch(e){
						showErrorMsg("查询结果信息出错！");
					}
				}
			});
		},
			
			showSubMission : function(code,brand) {
			var dlgParent = $("#dlg_assetdetailinfo").parent();
            var dlgOwn = $("#dlg_assetdetailinfo").clone();
            param_assetoverview.dlg_assetdetailinfo = $("#dlg_assetdetailinfo").dialog({
            	position: ['center','center'],
				autoOpen: false,
				height:"400",
				width:"800",
				show: "slide",
				modal:true,
				cache:false,
				/* hide: "explode", */
				title:'资产详情',
			    close : function() {
                	dlgOwn.appendTo(dlgParent);
                	$(this).dialog("destroy").remove();
                }
			});
			$("#assetdetailinfo_code").val(code);
			$("#assetdetailinfo_brand").val(brand);
			// 初始化grid
				param_assetoverview.bindLineSubMissionGrid();
				param_assetoverview.dlg_assetdetailinfo.dialog("option", "title", '资产详情').dialog("open");
		    },
	
			loadData : function(param) {
				var queryP = {};
				queryP.assetname = $('#serch_zcmc').val() == null ? '' : $(
						'#serch_zcmc').val();
				queryP.brand = $('#serch_pp').val() == null ? '' : $(
						'#serch_pp').val();
				var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
				queryP.pagenum = param.page ? param.page : $('.pcontrol input',
						selectedGridDiv).val();
				queryP.pagesize = param.rp ? param.rp : $("[name=rp]",
						selectedGridDiv).val();
				queryP.sortname = param.sortname ? param.sortname : "";
				queryP.sortorder = param.sortorder ? param.sortorder : "";
				$.ajax({
					type : "POST",
					url : "assetmanager!getAssetOverView.action?operation=init",
					cache : false,
					data : queryP,
					success : function(msg) {
						var data = eval("(" + msg + ")");
						try {
							param_grid_user
									.flexAddData(data);
						} catch (e) {
							showErrorMsg("查询结果信息出错！");
						}
					}
				});
			}
		};


		$(function() {
			//初始化所有操作按钮
			var assetoverview_button = [  
			/* 
				name : '资产详情',
				bclass : 'excel',
				onpress : param_assetoverview.commitExport */
			
			];
			
			var colModel = [ {
				display : '序号',
				name : 'xh',
				width : 40,
				align : 'center'
			}, {
				display : 'id',
				name : 'ID',
				width : 40,
				align : 'center',
				hide : true,
				sortable : true
			}, {
				display : '资产名称',
				name : 'ASSETNAME',
				width : 150,
				align : 'left'
			}, {
				display : '资产编号',
				name : 'ID',
				width : 150,
				align : 'left'
			},  {
				display : '品牌',
				name : 'BRAND',
				width : 150,
				align : 'left'
			}, {
				display : '数量',
				name : 'TOTAL',
				width : 150,
				align : 'left'
			}, {display:'操作',width:150,align:'center',
						process:function(obj, pid, $td){
							$tr = $td.closest("tr");
							var code = $('div', $tr).eq(3).text();
							var brand = $('div', $tr).eq(4).text();
							var htmlContent = '&nbsp;<span style="color:blue;cursor:pointer" onclick="param_assetoverview.showSubMission(\'' + code + '\',\'' + brand + '\')">获取资产详情</span>';
							$(obj).html(htmlContent);
					    }
			}

			];
			//绑定参数
			param_assetoverview.bindParamGrid(assetoverview_button, colModel);
			/*参数管理-查询按钮功能*/
			$("#btn_assetoverview_search_assetoverview").button().on("click", function() {
				param_assetoverview.loadData({
					page : 1
				});
			});
			//清空筛选框
			$("#btn_result_empty_assetoverview").on("click", function() {
				$("#serch_zcmc").val("");
				$("#serch_pp").val("");
				param_assetoverview.loadData({
					page : 1
				});
			});

		});
	</script>
</body>
</html>