<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>流程管理</title>
	<style type="text/css">.none{display: none;}</style>
	<script src="../styles/js/snaker/dialog.js" type="text/javascript"></script>
	<script type="text/javascript" src="../views/common/js/zTree/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="../views/common/js/zTree/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="../views/common/js/zTree/jquery.ztree.exedit.js"></script>
	<script type="text/javascript" src="${ctx}/views/common/js/common.js"></script>
</head>
<body>
<div class="div div-1">
		<div class="title title-1">
			<h3>查询条件</h3>
		</div>
		<div class="div-main-1">
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr style="font-size: 12px">
					<td class="wi-align-l" ><label for="input_1" align="center" class="lbl-1">流程名：</label>
					<input type="text" id="process_name" name="process_name" class="wi-ipt-1" /></td>
					<td class="wi-align-l"  >
					<label for="input_1" class="lbl-1">开始时间：</label>
						<input type="text" id="startTime" name="startTime" readonly class="wi-ipt-i"
						onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
						<label for="input_1" class="lbl-1">&nbsp;至&nbsp;</label>
						<input type="text" id="endTime" name="endTime" readonly  class="wi-ipt-i"
						onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
					</td>					
					<td class="wi-align-l" >
					<label for="input_1" align="center" class="lbl-1">流程状态：</label>	
						<select  name="process_status"  id="process_status"  class="lbl-1">
							<option value="">全部</option>
							<option value="1">有效</option>
							<option value="0">无效</option>
						</select>
					</td>
				
					<td class="wi-align-l" rowspan="1" >
						<button id="btn_process_search" class="ui-state-default ui-state-qik">查&nbsp;询</button>
						<button id="btn_process_empty" class="ui-state-default ui-state-qik">清&nbsp;空</button>
					</td>																																			
				</tr>			
			
			</table>
		</div>
	</div>
	<div class="div div-2">
		<table id="process_grid" style="display: none; padding: 0 0 0 0;"></table>
	</div>
	<div id="dlg_process_memu" style="height:100%;overflow-y: scroll;">
	<div id="flow_appoint_view">
		<div class="outer">
			<ul class="tab">
			    <li class="current" id="begin_appoint" onclick="appointShow()">分派任务</li>
				<li  onclick="personDetails(1)" id="begin">流程详情</li>
				<li onclick="personDetails(2)">查看表单</li>
			</ul>
			<div class="content_y">
						<div id="appoint_flowTable"></div>
						<div id="appoint_formTable"></div>

			</div>
		</div>
	</div>
	<div><a id="msg_end" name="1" href="#1">&nbsp</a></div>
	<div id="appiont">
		<input type="hidden" id="action" />
		<input type="hidden" id="dlg_process_id" /> 
		<input type="hidden" id="actor_id"/>
		<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
			<tr>
				<td ><label for="input_1" class="lbl-1">任务ID：</label></td>
				<td colspan="2" class="wi-align-l" >
				<span id="dlg_process_task_id"></span></td>
			</tr>	
			<tr>
				<td ><label for="input_1" class="lbl-1">流程名称：</label></td>
				<td colspan="2" class="wi-align-l" >
					<span id="dlg_process_process_name"></span>
				</td>
			</tr>
			<tr>
				<td ><label for="input_1" class="lbl-1">标题：</label></td>
				<td colspan="2" class="wi-align-l" >
				<span id="dlg_process_title"></span></td>
			</tr>
			<tr>
				<td ><label for="input_1" class="lbl-1">流程申请人：</label></td>
				<td class="wi-align-l" >
					<span id="dlg_process_creator"></span></td>
			</tr>
			<tr>
				<td ><label for="input_1" class="lbl-1">：流程发起时间</label></td>
				<td colspan="2" class="wi-align-l" >
				<span id="dlg_process_order_time"></span></td>
			</tr>	
			<tr>
				<td ><label for="input_1" class="lbl-1">任务处理名称：</label></td>
				<td class="wi-align-l" >
					<span id="dlg_process_task_name"></span></td>
				</td>
			</tr>
			<tr>
				<td ><label for="input_1" class="lbl-1">下一级处理人：</label></td>
				<td class="wi-align-l" >
					<span id=dlg_process_next_operator></span></td>
				</td>
			</tr>
			<tr>
				<td ><label for="input_1"  class="lbl-1">下一级处理人：</label></td>
					<td class="wi-align-l" >	
						<input type="hidden" id="nextOperator" readonly="readonly"  class="input_520" value="">
						<input type="text" id="nextOperatorName" readonly="readonly" name="task_next_name" class="input_520" value="">
						<div class="choice_y">
						<a class=' choice'  onclick="emptyGuy();">清空</a>
						<a class=' choice' id="selectOrgBtn" onclick="selectPart('departMent',depart,0,'select-body-process');">选择</a>
						
						</div>
					</td>		
			</tr>
		</table>
		</div>
	</div> 
    
		<div class="modal fade" id="departMent" tabindex="-1" style="display: none">
		   <div class="modal-dialog" style="width: 350px;">
		      <div class="modal-content">
		         <div class="modal-header">
		            <h4 class="modal-title">
		               	部门选择人员
		            </h4>
		         </div>
		         <div class="modal-body" id="select-body-process" style="overflow: auto;">
			        <div id="menuContent_process" style="overflow-y:auto;">
						<ul id="treeDemo_process" class="ztree" style="margin-top:0;background: #f0f6e4;overflow-y:scroll;overflow-x:auto;"></ul>
					</div>		            		
		         </div>
		         <div class="modal-footer">
		         </div>
		      </div>
		   </div>
		</div>
		 
	<script>
		var manualcprocessGridHeight = flexHeight("process_grid");
		var process_grid;
		var dlg_processManage;
		var manualc_process = {
		    dlg_process_memu:"",
			//绑定参数信息grid
			bindprocessManualcGrid : function(queryP) {
			process_grid = $("#process_grid").flexigrid(
			{
				url : "process!taskListManage.action?operation=init",
						//{display : '流程发起时间',name : 'ORDER_CREATE_TIME',width : 80,align : 'center'}
				colModel : [
// 				            {display : '序号',name : 'xh',width : 40,align : 'center'},
							{display : 'id',name : 'ID',width : 40,align : 'center',hide : true},
							{display : '流程实例ID',name : 'ORDER_ID',width :250,align : 'left',hide : true},
							{display : '流程名称',name : 'PROCESS_NAME',width :80,align : 'left',sortable : true},
							{display : '标题',name : 'TITLE',width :100,align : 'left',sortable:true},
							{display : '流程申请人',name : 'TRUENAME',width :90,align : 'left',sortable : true},
							{display : '流程发起时间',name : 'ORDER_CREATE_TIME',width :90,align : 'left',sortable : true},
							{display : '下一级处理人',name : 'ATRUENAME',width : 90,align : 'left',sortable : true},
							{display : '任务处理名称',name : 'TASK_NAME',width : 80,align : 'left',sortable : true},
							{display : '下一级处理人ID',name : 'ACTOR_ID',width : 80,align : 'center',hide : true},
							{display : 'PROCESS_ID',name : 'PROCESS_ID',hide: true,width : 80,align : 'center',hide : true},
							{display : 'ORDER_ID',name : 'ORDER_ID',hide: true,width : 80,align : 'center',hide : true},
							{display : '表单查询', name : 'PATH', width : 150,hide: true,align: 'center',sortable: true}
						],
						buttons : [
									{name: '任务指派', bclass: 'edit', onpress : manualc_process.editParam},
									{separator: true}
								],
				rowId : "id",
				showToggleBtn : false,
				usepager : true,
				title : "流程任务指派",
				rp:page_num,
				showTableToggleBtn : false,
				resizable : false,
				height : manualcprocessGridHeight,
				singleSelect : false,
                checkbox:true,
				onChangePage : manualc_process.loadData,
				filterParams : [{key : "processName",name : "#process_name"},
								{key : "status",name : "#process_status"}]
				});
			},
			loadData : function(param) {
				var queryP = {};
				queryP.processName = $('#process_name').val() == null ? '': $('#process_name').val();
				queryP.status = $('#process_status').val() == null ? '' : $('#process_status').val();
				var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
				queryP.pagenum = param.page ? param.page : $('.pcontrol input',selectedGridDiv).val();
				queryP.pagesize = param.rp ? param.rp : $("[name=rp]",selectedGridDiv).val();
				queryP.sortname = param.sortname ? param.sortname : "";
				queryP.sortorder = param.sortorder ? param.sortorder : "";
				$.ajax({
					type : "POST",
					url : "process!taskListManage.action?operation=init",
					cache : false,
					data : queryP,
					success : function(msg) {
						var data = eval("(" + msg + ")");
						try {
							process_grid.flexAddData(data);
						} catch (e) {
							showErrorMsg("查询待分派任务信息出错！");
						}
					}
				});
			},
			//分配任务
			editParam:function(com, grid){
		        var dlgParent = $("#dlg_process_memu").parent();
	            var dlgOwn = $("#dlg_process_memu").clone();
				manualc_process.dlg_process_memu = $("#dlg_process_memu").dialog({
					autoOpen: false,
					height:$(window).height()*0.8,
					width:$(window).width()*0.5,
					show: "slide",
					modal:true,
					/* hide: "explode", */
					title:'参数',
					buttons:{
						"保存":function(){
							manualc_process.saveParam();
						}
					},
				    close : function() {
	                	dlgOwn.appendTo(dlgParent);
	                	$(this).dialog("destroy").remove();
	                }
				});
				if (com == '任务指派')
				{
					$.ajax({
						type : "POST",
						url : "process!selectOpertor.action?operation=init",
						cache : false,
						data : null,
						success : function(msg) {
							var data = eval("(" + msg + ")");
						
						}
					});
					
					if($('.trSelected', grid).length==1)
					{
						var id = $('.trSelected', grid).eq(0).find("td:eq(0)").text();
						var orderId = $('.trSelected', grid).eq(0).find("td:eq(1)").text();						
						var processName = $('.trSelected', grid).eq(0).find("td:eq(2)").text();
						var title = $('.trSelected', grid).eq(0).find("td:eq(3)").text();
						var creator = $('.trSelected', grid).eq(0).find("td:eq(4)").text();
						var time = $('.trSelected', grid).eq(0).find("td:eq(5)").text();
						var actorId = $('.trSelected', grid).eq(0).find("td:eq(6)").text();	
						var taskName = $('.trSelected', grid).eq(0).find("td:eq(7)").text();	
						var nextname = $('.trSelected', grid).eq(0).find("td:eq(8)").text();	
						$("#dlg_process_order_id").text($.trim(orderId));
						$("#dlg_process_process_name").text($.trim(processName));
						$("#dlg_process_title").text($.trim(title));
						$("#dlg_process_task_id").text($.trim(id));						
						$("#dlg_process_creator").text($.trim(creator));
						$("#dlg_process_order_time").text(time);
						$("#dlg_process_task_name").text($.trim(taskName));
						$("#actor_id").val($.trim(nextname));	
						$("#dlg_process_next_operator").text($.trim(actorId));		
						$("#action").val("edit");
						manualc_process.dlg_process_memu.dialog("option", "title", '分派任务').dialog("open");
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_process_memu').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_process_memu').dialog('close');
						showMsg("未选中行！");
					}
				}
			},
			//保存参数
			saveParam:function()
			{
			    // 提交方式
				var action = $("#action").val();
				var params=new Object();
				params.taskId = $("#dlg_process_task_id").text();
				params.nextName = $("#nextOperator").val();
				params.operator = $("#actor_id").val();				
				/* params.type = $("#dlg_process_pl_type").val(); */
				// 新增和编辑通用字符串
				var url ='';
				var indicate='';
				 if ("edit" == action) 
				{
					params.id = $("#dlg_process_id").val();
					indicate = "确认重新指派这个任务?";
					url = "process!updateTaskNextName.action";
				}
				// 弹出确认框
				if(confirm(indicate)) 
				{
					$.ajax({
						type: "POST",
						url: url,
						cache: false,
						data: params,
						success: function(msg){
							var result=eval("("+msg+")");
							if (result.msg=="ok") 
							{
								showMsg("操作成功");
								$('#dlg_process_memu').dialog('close');
								manualc_process.loadData({});
							} else 
							{
								showMsg(result.msg);
							}
						}
					}); 
				}
				else
				{
				    $('#dlg_process_memu').dialog('close');
				}
			},			
			// 验证规则
			validate : function() {
				var flg = true;
				$("#dlg_processManage input").each(function() {
					if (!$(this).validate()) flg = false;
				});
				return flg;
			}
		};

		$(function() {
			/*参数管理-查询按钮功能*/
			$('#btn_process_search').button({icons : {primary : 'ui-icon-search'}}).on("click", function() {
				manualc_process.loadData({page : 1});
			});

			// 初始化数据
			manualc_process.bindprocessManualcGrid();

			//清空筛选框
			$('#btn_process_empty').on("click", function() {
				$("#process_name").val("");
				$("#process_status").val("");
				$("#process_type").val("");

			});
		});
		 var treeObj;
		    var id_arr = "";
		    var name_arr = "";
			function add_html(){
				if(type == 0){
					$("#nextOperatorName").val(name_arr);
					$("#nextOperator").val(id_arr);
				}
				if(type == 1){
					$("#ccOperatorName").val(name_arr);
					$("#ccOperator").val(id_arr);
				}
			}
			
		function onChooseOrg(e, treeId, treeNode) {
			var orgId = treeNode.ORG_ID;
			if(orgId.indexOf("_personId_") != -1 && treeNode.checked){
				//zTree.expandNode(treeNode);
				id_arr = orgId.substring(orgId.indexOf("_personId_")+"_personId_".length,orgId.length);
				name_arr = treeNode.ORG_NAME;
				add_html();
			}else if(orgId.indexOf("_personId_") != -1 && !treeNode.checked){
				for(var i = 0; i < name_arr.length; i++){
					if(treeNode.ORG_NAME == name_arr[i]){
						name_arr.splice(i,1);
						id_arr.splice(i,1);
					}
				}
				add_html();
			}		
		}
		var setting = {
				view: {
					dblClickExpand: false,
					showLine: false
				},
				check:{
					enable: true,
					autoCheckTrigger: true,
					chkStyle: "radio",
					chkboxType: { "Y": "ps", "N": "ps" }
				},
				data: {
					key:{
						name: "ORG_NAME"
					},
					simpleData: {
						enable: true,
						name: "ORG_NAME",
						idKey: "ORG_ID",
						pIdKey: "SUPER_ORG_ID",
						rootPId: 0
					}
				},
				callback: {
					onCheck: onChooseOrg
				}
			};
		
		  function depart(){
		    	type = arguments[0];
		    	//$("#select-body").load("../model_pc/department/department_person.jsp?math_radom="+Math.random());
				del_html();
		    	$.ajax({
					type: "POST",
					url: "../docum!queryOrganizationInfos.action?math_radom="+Math.random(),
					cache: false,
					data: null,
					success: function(msg){
						var data=eval("("+msg+")");
						$.fn.zTree.init($("#treeDemo_process"), setting, data);						
						treeObj  =$.fn.zTree.getZTreeObj("treeDemo_process");
						var nodes = treeObj.getNodes();
						var nodes2;
							nodes2 = nodes[0].children;
							for (var j = 1; j < nodes2.length; j++) {
								treeObj.expandNode(nodes2[j], true, false, true);
							}
						
						var val = $("#nextOperatorName").val();
						if($.trim(val).length != 0){
							var array =	val.split(",");
							var node;
							for(var i = 0 ; i < array.length ; i++){
								node  =treeObj.getNodeByParam("ORG_NAME",array[i],null);
								treeObj.checkNode(node,true, false);
//		 						checkParentNode(node.SUPER_ORG_ID);
							}
						}
					}
		    	
				});
		    }
		  function del_html(){
				if(type == 1){
					$("#ccOperatorName").val("");
					$("#ccOperator").val("");
				}
			}
			//获取流程详情和表单
			function personDetails(com,grid) {
                         $("#appiont").hide();
                         $("#appoint_formTable").show();
						$("#appoint_flowTable").show();
			      		$("#appoint_formTable").html("");
						$("#appoint_flowTable").html("");
							var id = $('.trSelected', grid).eq(0).find("td:eq(9)").text();
							var pid = $('.trSelected', grid).eq(0).find("td:eq(10)").text();
							var path = $('.trSelected', grid).eq(0).find("td:eq(11)").text();
		                    //alert(path);
								if(com == 2){
									url = "borrow!view.action";
									data = {"processId" : id,"orderId":pid,"path":path};
								}else{
									
									 url = "lczl!viewStep.action";
									 data = {"orderId" : pid};
								}
							
							$.ajax({
								type:"POST",
								data:data,
								cache: false,
								async:false,
								url: url,
								success:function(result)
								{	
								
								    if(com == 1){
								    var result_str = createHtml(result);
								    $("#appoint_flowTable").append(result_str);
								    }else{
								    $("#appoint_formTable").append(result);
								    }
								    $("#flow_appoint_view .tab li").click(function()
								    {
										$("#flow_appoint_view .tab li").removeClass("current");
										$(this).addClass("current");
										var n = $(this).index();
										$("#flow_appoint_view .content_y ul").hide();
										$("#flow_appoint_view .content_y ul").eq(n).show();
		                            });
								}								
							});
						document.getElementById("msg_end").click(); 					
			}
	
	
	function appointShow(){
	  $("#appoint_formTable").hide();
	  $("#appoint_flowTable").hide();
	  $("#appiont").show();
	}
	
	function emptyGuy(){
	  $("#nextOperatorName").val("");
	}
	</script>
</body>
</html>