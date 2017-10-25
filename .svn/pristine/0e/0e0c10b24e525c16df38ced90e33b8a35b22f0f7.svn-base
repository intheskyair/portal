<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
	<title>工作日志汇总</title>
	<%@ include file="/common/taglibs.jsp"%>
    <%@ include file="/common/commonCSSJS.jsp"%>
	 <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/test/metroStyle/metroStyle.css" />
 <script type="text/javascript" src="${ctx}/views/common/js/zTree/jquery.ztree.core.js"></script>
 <script type="text/javascript" src="${ctx}/views/common/js/zTree/jquery.ztree.excheck.js"></script>
  <script>x=-1</script>
	</head>
	<body>	
	<!-- <div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='../images/fold.png' onclick="showAndHide(this,'user_grid');"  class="imgShrotate">
				<font>后台用户管理</font>
			</h3>
		</div>
	
	</div> -->
	<input type="hidden" id="uid_sel"/>
	<div class="div div-2"  style="width:250px;position: absolute;">
		<div class="div-main-1 div-height">
			<span style="font-weight: bold;">部门列表</span>
		</div>
		<div id="organ_table_all_log" class="ztree">
		</div>
	</div>
	
		<div class="div-main-1" style="width:85%;margin-left:254px;" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>
					<td class="wi-align-l">
						<label for="input_1" class="lbl-1">
							用户名:
						</label>
						<input type="text" id="serch_wlog_user" name="serch_wlog_title" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l" >
						<label for="input_1" class="lbl-1">
							日期：
						</label>
						<input type="text" id="serch_wlog_create_time" name="serch_wlog_create_time" readonly class="Wdate" style="height:22px;width:55%;"
					 onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
					</td>
					<td class="wi-align-l">
						<button id="btn_wlog_search" class="ui-state-default ui-state-qik">查询
						</button>
					
						<button id="btn_wlog_empty" class="ui-state-default ui-state-qik">清空
						</button>
					</td>
				</tr>
			</table>
		</div>
		
	<div class="div div-2" style="width:85%;position: absolute;margin-left:254px;">
		<div>
			<table id="all_log_grid" style="display:none;padding:0 0 0 0;"></table>
		</div>
	</div>

<div >
	<ul id="department_tree" class="ztree">
	</ul>
</div>

<div id="dlg_wlog" style="display:none;overflow: auto;">
	<input type="hidden" id="dlg_id" /> 
	<input type="hidden" id="action" />
		<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl" style="">
			<tr>
				<td style="width:100px;"><label style="color:red;">*</label><label for="input_1">标题：</label></td>
				<td class="wi-align-l" style="width:200px;">
					<input type="text" id="dlg_title"  value="" class="wi-ipt-1"/>
				</td>
			</tr>
			<tr>
					<td style="width:100px;"><label style="color:red;">*</label><label for="input_1">日期：</label></td>
					<td class="wi-align-l" style="width:200px;">
						<input type="text" id="dlg_time" onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d %H:%m:%s',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
							readonly class="Wdate" style="width:200px;"
						/>
					</td>
			</tr>
			<tr>
				<td style="width:100px;"><label style="color:red;">*</label><label for="input_1">内容：</label></td>
				<td class="wi-align-l" style="width:200px;">
					<textarea rows="10"  cols="300" id="dlg_content" ></textarea>
				</td>
			</tr>
		</table>
	</div> 

<script>	

		function decodeTextAreaString(str) {
		    var reg = new RegExp("<br/>", "g");
		    str = str.replace(reg, "\n");
		    return str;
		}

function showInfo(rowData){
						var id = rowData.LOG_ID;
						var params=new Object();
						params.id = id;
						$.ajax({
							url : "worklog!getWorkInfo.action",
							type : "post",
							dataType : "json",
							data: params,
							success : function(data_res){
								$("#dlg_title").val(data_res.LOG_TITLE);
								$("#dlg_time").val(data_res.LOG_DATE);
								$("#dlg_content").val(decodeTextAreaString(data_res.LOG_CONTENT));
							}
		            	});
						
						var dlgOwn = $("#dlg_wlog").clone();
						var dlgParent = $("#dlg_wlog").parent();
						var dlg_wlog_ck = $("#dlg_wlog").dialog({
							autoOpen: false,
							height:$(window).height()*0.8,
							width:$(window).width()*0.5,
							modal:true,
							cache:false,
							title:'查看工作日志',
						    close : function() {
			                	dlgOwn.appendTo(dlgParent);
			                	clean_input_for_dailog($("#dlg_acceptancemanage"));
			                	$(this).dialog("destroy").remove();
			                }
						});
					    dlg_wlog_ck.dialog("open");
}


	var paramGridHeight_worklog = flexHeight("all_log_grid")-30;
	var param_all_worklog = {
		 param_grid_result:"",
		 page_num:30,
		//绑定参数信息grid
		bindParamGrid:function(queryP,queryM){
			param_all_worklog.param_grid_result = $("#all_log_grid").flexigrid({
				url:"worklog!logList.action",
				colModel: queryM,
				buttons : queryP,
				rowId:"id",
				showToggleBtn: true,
				usepager: true,
				title:"工作日志列表",
				//rp:pageSize,
				onRowDblclick:showInfo,
				rp:param_all_worklog.page_num,
				height: paramGridHeight_worklog,
				showTableToggleBtn: false,
				resizable: false,
				singleSelect: true,
				onChangePage:param_all_worklog.loadData,
				checkbox:false,
				filterParams : [
							{key : "uid_sel", name : "#uid_sel"},
			                {key : "username", name : "#serch_wlog_user"},
			                {key : "log_date", name : "#serch_wlog_create_time"}]
			});
		},
		loadData:function(param){
			var queryP = {};
			queryP.uid_sel = $('#uid_sel').val()==null?'':$('#uid_sel').val();
			queryP.username = $('#serch_wlog_user').val()==null?'':$('#serch_wlog_user').val();
			queryP.log_date = $('#serch_wlog_create_time').val()==null?'':$('#serch_wlog_create_time').val();
			//var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
			var selectedGridDiv = $("div.flexigrid .pDiv .pDiv2 .pGroup");
			queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
			queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
			queryP.sortname = param.sortname? param.sortname : "";
			queryP.sortorder = param.sortorder? param.sortorder : "";
			$.ajax({
				type: "POST",
				url: "worklog!logList.action?operation=init",
				cache: false,
				data: queryP,
				success: function(msg){
					var data=eval("("+msg+")");
					try{
						param_all_worklog.param_grid_result.flexAddData(data);
					}catch(e){
						showErrorMsg("查询结果信息出错！");
					}
				}
			}); 
		}
	};
			
	
	//左边树菜单点击事件
	var org_menu_event = function(event,treeId,treeNode){
		var uid = treeNode.ORG_ID.substring(0,treeNode.ORG_ID.indexOf("_"));
	    $("#uid_sel").val(uid);
		param_all_worklog.loadData({page:1});
	};
	var org_setting = {
		view: {
			dblClickExpand: false
		},
		edit : {
			enable :false,
			showRemoveBtn : false,
			showRenameBtn : false
		},
		data: {
			simpleData: {
				enable: true,
				idKey : "ORG_ID",
				pIdKey : "SUPER_ORG_ID",
				rootPID : 0
			},
			key : {
				name: "ORG_NAME",
			}
		},
		callback:{
			onClick:org_menu_event,
			beforeClick: zTreeBeforeClick
		}
	};
	
	function zTreeBeforeClick(treeId, treeNode, clickFlag) {
	    return !treeNode.isParent;//当是父节点 返回false 不让选取
	}
	var org_Menu = {
		loadTree : function() {
			orgTree = null;
			$("#organ_table_all_log").html("");
			var queryP = {};
			queryP.pagenum = 1;
			queryP.pagesize = 10000;
			$.ajax({
				type: "POST",
				url: "docum!queryOrganizationInfos1.action?math_radom="+Math.random()+"&orgId="+${admin.org_id},
				cache: false,
				data: queryP,
				success: function(msg){
					var data=jQuery.parseJSON(msg);
					orgTree = $.fn.zTree.init($("#organ_table_all_log"), org_setting, data);
					//展开所有
					var treeObj = $.fn.zTree.getZTreeObj("organ_table_all_log");
					treeObj.expandAll(false);
					
					$("#organ_table_all_log > .level0").css("border-bottom",0);
				}
			});
		}
	}
	$(function(){
		 org_Menu.loadTree();
		 //权限操作按钮
		 var colModel = [						
		                {display: '序号', name : 'xh', width : 40, align: 'center'},
		                {display: 'id', name : 'LOG_ID', width : 40, align: 'center',hide : true,sortable: true},
						{display: '日志标题', name : 'LOG_TITLE', width : 350, align: 'left',sortable: false},
						{display: '用户名', name : 'USERNAME', width : 150, align: 'left',sortable: false},
						{display: '日期', name : 'LOG_DATE', width : 150, align: 'left',sortable: false}
						];
		//初始化所有操作按钮
		 var  parm_button = [{name: '新建', bclass: 'add', onpress : ""},
							{name: '修改', bclass: 'edit', onpress : ""}];
		
		 //权限操作按钮
		 var buttons = [];
		 
		// 初始化数据
		param_all_worklog.bindParamGrid(buttons,colModel);
		
		 		/*参数管理-查询按钮功能*/
		$('#btn_wlog_search').button().on("click",function(){
			param_all_worklog.loadData({page:1});
		});
				//清空筛选框
		$('#btn_wlog_empty').on("click",function(){
			$("#serch_wlog_user").val("");
			$("#serch_wlog_create_time").val("");
		}); 
		
	});
	
$(function(){
     sety1();
	$(window).resize(function() {
  		sety1();
	});
});
function sety1(){
	var iy = $(window).height();
	//alert(iy);
	$(".ztree").height(iy-56);
	//alert(iy);
}
	


	
	
		</script>
	</body>
</html>
