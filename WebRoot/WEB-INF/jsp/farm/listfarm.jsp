<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
var jobGridHeight = flexHeight("farm_grid");
var farm_grid;

var farm_job = {
		// 绑定grid初始化数据
		bindJobGrid : function(queryP) {
			farm_grid = $("#farm_grid").flexigrid({
				url:"farm!listFarm.action",
				colModel : [
					{display: 'ID',name:'ID', width : 70, aliend : 'center'},
					{display: '农场名称', name : 'NAME', width : 250, aliend : 'center'},
					{display: '是否主文件服务器', name : 'IS_WEB_FSERVER', width : 120, aliend : 'center',
						process:function(obj,pid){
							if($(obj).text() == 1){
								$(obj).html("是");
							}else if($(obj).text() == 0){
								$(obj).html("否");
							}else{
								$(obj).html("");
							}
						}},
					{display: '优先级', name : 'PRIORITY', width : 70,  align: 'left'},
					{display: '禁用状态', name : 'IS_FORBIDDEN', width : 150,  align: 'left',
						process:function(obj,pid){
							if($(obj).text() == 1){
								$(obj).html("<span style='color:red;'>禁用</span>");
							}else if($(obj).text() == 0){
								$(obj).html("启用");
							}else if($(obj).text() == 2){
								$(obj).html("暂停接收");
							}else{
								$(obj).html("");
							}
						}},
					{display: '域名', name : 'DOMAIN_NAME', width : 150,  align: 'left'},
					{display: '总节点', name : 'AGENT_COUNT', width : 50,  align: 'left'},
					{display: '任务分发比', name : 'DISTRIBUTION_RATIO', width : 100,  align: 'left'},
					{display: '农场状态', name : 'STAT', width : 50,  align: 'left',
						process:function(obj,pid){
							if($(obj).text() == 0){
								$(obj).html("有效");
							}else{
								$(obj).html("<span style='color:red;'>已删除</span>");
							}
						}},
					{display: '农场备注', name : 'DESCRIPTION', width : 150,  align: 'left'}
				],
				buttons : queryP,
				rowId:"xh",
				showToggleBtn: true,
				usepager: true,
				title:"加载多农场参数设置",
				//rp:pageSize,
				rp:page_num,
				showTableToggleBtn: false,
				resizable: false,
				height: jobGridHeight,
				singleSelect: false,
				onChangePage:farm_job.loadData,
				singleSelect : true,
				filterParams : [{key : "farmName", name : "#txt_farm_name"}]
			});
		},
		// 加载数据
		loadData : function(param) {
			var queryP = {};
			queryP.farmName = $("#txt_farm_name").val();
			queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).eq(0).val();
			queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).eq(0).val();
			queryP.sortname = param.sortname? param.sortname : "";
			queryP.sortorder = param.sortorder? param.sortorder : "";
			$.ajax({
				type: "POST",
				url: "farm!listFarm.action",
				cache: false,
				data: queryP,
				success: function(msg){
					var data=eval("("+msg+")");
					try{
						farm_grid.flexAddData(data);
					}catch(e){
						showErrorMsg("查询加价参数配置信息出错！");
					}
				}
			});
		},
		//新增农场配置
		newFarmParam : function(){
			var dlgParent = $("#farm_parameter_add").parent();
	        var dlgOwn = $("#farm_parameter_add").clone();
			farm_param_add = $("#farm_parameter_add").dialog({
				autoOpen: false,
				height:500,
				width:730,
				show: "slide",
				modal:true,
				cache:false,
				/* hide: "explode", */
				title:'新增农场参数配置',
				buttons:{
					"确定":function(){
						//$(".ui-dialog-buttonpane button").eq(0).attr("disabled","disabled");
						if(confirm("确定要新增该农场?")){
							farm_job.commitNewFarm(dlgOwn,dlgParent,$(this));
						}
					},
					"取消":function(){
						//$(".ui-dialog-buttonpane button").eq(0).attr("disabled","disabled");
						//$(".ui-dialog-buttonpane button").eq(1).attr("disabled","disabled");
						dlgOwn.appendTo(dlgParent);
	            		$(this).dialog("destroy").remove();
					}
				},
	            close : function() {
	            	dlgOwn.appendTo(dlgParent);
	            	$(this).dialog("destroy").remove();
	            }
			});
			farm_param_add.dialog("open");
		},
		//保存新增农场配置信息
		commitNewFarm : function(own,parent,cl){
			var flag = true;
			if($('#add-farm_priority').val() < 0 || $('#add-farm_priority').val() > 100){
				falg = false;
				showMsg("优先级大小超出范围,请重设.");
				$(".ui-dialog-buttonpane button").eq(0).attr("disabled",false);
				return false;
			}
			$("#farm_parameter_add input[type='text']").each(function(i){
				if(!$("#farm_parameter_add input[type='text']:eq("+i+")").validate()){
					flag = false;
					$(".ui-dialog-buttonpane button").eq(0).attr("disabled",false);
					return false;
				}
			});
			if(flag){
				var queryP = {};
				queryP.farm_name = $('#add-farm_name').val();
				queryP.farm_agent_count = $('#add-farm_agent_count').val();
				queryP.farm_priority = $('#add-farm_priority').val();
				queryP.farm_cluster_port = $('#add-farm_cluster_port').val();
				queryP.farm_fserver_lan_port = $('#add-farm_fserver_lan_port').val();
				queryP.farm_fserver_wlan_port = $('#add-farm_fserver_wlan_port').val();
				queryP.farm_domain_name = $('#add-farm_domain_name').val();
				queryP.farm_domain_port = $('#add-farm_domain_port').val();
				queryP.farm_is_forbidden = $('#add-farm_is_forbidden').val();
				queryP.farm_is_web_fserver = $('#add-farm_is_web_fserver').val();
				queryP.farm_description = $('#add-farm_description').val();
				queryP.farm_fs_mysql_ip = $('#add-farm_fs_mysql_ip').val();
				queryP.farm_fs_mysql_port = $('#add-farm_fs_mysql_port').val();
				queryP.farm_fs_mysql_username = $('#add-farm_fs_mysql_username').val();
				queryP.farm_fs_mysql_password = $('#add-farm_fs_mysql_password').val();
				queryP.farm_stat = $('#add-farm_stat').val();
				queryP.farm_cluster_ip = $('#add-farm_cluster_ip').val();
				queryP.farm_fserver_lan_ip = $('#add-farm_fserver_lan_ip').val();
				queryP.farm_fserver_wlan_ip = $('#add-farm_fserver_wlan_ip').val();
				queryP.farm_distribution_ratio = $("#add-farm_distribution_ratio").val();
				$.ajax({
					type: "POST",
					url: "farm!createFarm.action",
					cache: false,
					data: queryP,
					success: function(msg){
						var data=eval("("+msg+")");
						if(data.result == "01"){
							showMsg("新增农场配置信息成功！");
							own.appendTo(parent);
		            		cl.dialog("destroy").remove();
		            		farm_job.loadData({page : 1});
						}else{
							showErrorMsg("新增农场配置信息失败！");
							$(".ui-dialog-buttonpane button").eq(0).attr("disabled",false);
						}
					}
				});
			}
		},
		//修改农场配置
		modifyFarmParam : function(){
			var cnt = $('.trSelected',$("#farm_grid")).length;
			if(cnt == 1){
				if($($('.trSelected td:nth-child(8) div',$("#farm_grid"))[0]).text() == "已删除"){
					showMsg("该农场已经失效无法修改！");
					return;
				}
				if($($('.trSelected td:nth-child(5) div',$("#farm_grid"))[0]).text() == "启用" || $($('.trSelected td:nth-child(5) div',$("#farm_grid"))[0]).text() == "暂停接收"){
					showMsg("该农场为启用/暂停接收状态，无法修改。只有在禁用状态下修改!");
					return;
				}
				var queryP = {};
				queryP.farmId =  $($('.trSelected td:nth-child(1) div',$("#farm_grid"))[0]).text();
				$.ajax({
					type: "POST",
					url: "farm!getFarmInfo.action",
					cache: false,
					data: queryP,
					success: function(msg){
						if(msg != null){
							var data=eval("("+msg+")");
							$("#modify-farm_id").val(queryP.farmId);
							$('#modify-farm_name').val(data.NAME);
							$('#modify-farm_agent_count').val(data.AGENT_COUNT);
							$('#modify-farm_priority').val(data.PRIORITY);
							$('#modify-farm_cluster_port').val(data.CLUSTER_PORT);
							$('#modify-farm_fserver_lan_port').val(data.FSERVER_LAN_PORT);
							$('#modify-farm_fserver_wlan_port').val(data.FSERVER_WLAN_PORT);
							$('#modify-farm_domain_name').val(data.DOMAIN_NAME);
							$('#modify-farm_domain_port').val(data.DOMAIN_PORT);
							$('#modify-farm_is_forbidden').val(data.IS_FORBIDDEN);
							$('#modify-farm_is_web_fserver').val(data.IS_WEB_FSERVER);
							$('#modify-farm_description').val(data.DESCRIPTION);
							$('#modify-farm_fs_mysql_ip').val(data.FS_MYSQL_IP);
							$('#modify-farm_fs_mysql_port').val(data.FS_MYSQL_PORT);
							$('#modify-farm_fs_mysql_username').val(data.FS_MYSQL_USERNAME);
							$('#modify-farm_fs_mysql_password').val(data.FS_MYSQL_PASSWORD);//data.FS_MYSQL_PASSWORD
							$('#modify-farm_stat').val(data.STAT);
							$('#modify-farm_cluster_ip').val(data.CLUSTER_IP);
							$('#modify-farm_fserver_lan_ip').val(data.FSERVER_LAN_IP);
							$('#modify-farm_fserver_wlan_ip').val(data.FSERVER_WLAN_IP);
							$("#modify-farm_distribution_ratio").val(data.DISTRIBUTION_RATIO);
							
							var dlgParent = $("#farm_parameter_modify").parent();
					        var dlgOwn = $("#farm_parameter_modify").clone();
							farm_param_modify = $("#farm_parameter_modify").dialog({
								autoOpen: false,
								height:500,
								width:730,
								show: "slide",
								modal:true,
								cache:false,
								/* hide: "explode", */
								title:'修改农场配置参数设置',
								buttons:{
									"确定":function(){
										farm_job.commitModifyFarm(dlgOwn, dlgParent, $(this));
									},
									"取消":function(){
										dlgOwn.appendTo(dlgParent);
					            		$(this).dialog("destroy").remove();
									}
								},
					            close : function() {
					            	dlgOwn.appendTo(dlgParent);
					            	$(this).dialog("destroy").remove();
					            }
							});
							farm_param_modify.dialog("open");
						}
					}
				});
			}else{
				showMsg("请选择一行数据！");
			}
		},
		//提交修改农场配置信息
		commitModifyFarm : function(own,parent,cl){
			var flag = true;
			if($('#modify-farm_priority').val() < 0 || $('#modify-farm_priority').val() > 100){
				falg = false;
				showMsg("优先级大小超出范围,请重设.");
				return false;
			}
			$("#farm_parameter_modify input[type='text']").each(function(i){
				if(!$("#farm_parameter_modify input[type='text']:eq("+i+")").validate()){
					flag = false;
					return false;
				}
			});
			if(!$("#farm_parameter_modify input[type='password']").validate()){
				flag = false;
				showMsg("数据库密码不能为空!");
				return;
			}
			var stat = $("#modify-farm_stat").val();
			if(stat == 1){
				flag = confirm("农场状态修改为无效既为逻辑意义上的删除,生效以后该农场将无法使用,确认要删除吗?");
			}
			if(flag){
				var queryP = {};
				queryP.farm_id = $("#modify-farm_id").val();
				queryP.farm_name = $('#modify-farm_name').val();
				queryP.farm_agent_count = $('#modify-farm_agent_count').val();
				queryP.farm_priority = $('#modify-farm_priority').val();
				queryP.farm_cluster_port = $('#modify-farm_cluster_port').val();
				queryP.farm_fserver_lan_port = $('#modify-farm_fserver_lan_port').val();
				queryP.farm_fserver_wlan_port = $('#modify-farm_fserver_wlan_port').val();
				queryP.farm_domain_name = $('#modify-farm_domain_name').val();
				queryP.farm_domain_port = $('#modify-farm_domain_port').val();
				queryP.farm_is_forbidden = $('#modify-farm_is_forbidden').val();
				queryP.farm_is_web_fserver = $('#modify-farm_is_web_fserver').val();
				queryP.farm_description = $('#modify-farm_description').val();
				queryP.farm_fs_mysql_ip = $('#modify-farm_fs_mysql_ip').val();
				queryP.farm_fs_mysql_port = $('#modify-farm_fs_mysql_port').val();
				queryP.farm_fs_mysql_username = $('#modify-farm_fs_mysql_username').val();
				queryP.farm_fs_mysql_password = $('#modify-farm_fs_mysql_password').val();
				queryP.farm_stat = $('#modify-farm_stat').val();
				queryP.farm_cluster_ip = $('#modify-farm_cluster_ip').val();
				queryP.farm_fserver_lan_ip = $('#modify-farm_fserver_lan_ip').val();
				queryP.farm_fserver_wlan_ip = $('#modify-farm_fserver_wlan_ip').val();
				queryP.farm_distribution_ratio = $("#modify-farm_distribution_ratio").val();
				$.ajax({
					type: "POST",
					url: "farm!modifyFarm.action",
					cache: false,
					data: queryP,
					success: function(msg){
						var data=eval("("+msg+")");
						if(data.result == "01"){
							showMsg("修改农场配置信息成功！");
							own.appendTo(parent);
		            		cl.dialog("destroy").remove();
		            		farm_job.loadData({page : 1});
						}else{
							showErrorMsg("修改农场配置信息失败！");
						}
					}
				});
			}
		},
		//农场配置信息详情
		showFarmDetail : function(){
			var cnt = $('.trSelected',$("#farm_grid")).length;
			if(cnt == 1){
				var queryP = {};
				queryP.farmId =  $($('.trSelected td:nth-child(1) div',$("#farm_grid"))[0]).text();
				$.ajax({
					type: "POST",
					url: "farm!getFarmInfo.action",
					cache: false,
					data: queryP,
					success: function(msg){
						if(msg != null){
							var data=eval("("+msg+")");
							$('#detail-farm_name').text(data.NAME);
							$('#detail-farm_agent_count').text(data.AGENT_COUNT);
							$('#detail-farm_priority').text(data.PRIORITY);
							$('#detail-farm_cluster_port').text(data.CLUSTER_PORT);
							$('#detail-farm_fserver_lan_port').text(data.FSERVER_LAN_PORT);
							$('#detail-farm_fserver_wlan_port').text(data.FSERVER_WLAN_PORT);
							$('#detail-farm_domain_name').text(data.DOMAIN_NAME);
							$('#detail-farm_domain_port').text(data.DOMAIN_PORT);
							$('#detail-farm_fs_mysql_ip').text(data.FS_MYSQL_IP);
							$('#detail-farm_fs_mysql_port').text(data.FS_MYSQL_PORT);
							$('#detail-farm_fs_mysql_username').text(data.FS_MYSQL_USERNAME);
							$('#detail-farm_fs_mysql_password').text("******"); //data.FS_MYSQL_PASSWORD
							$('#detail-farm_cluster_ip').text(data.CLUSTER_IP);
							$('#detail-farm_fserver_lan_ip').text(data.FSERVER_LAN_IP);
							$('#detail-farm_fserver_wlan_ip').text(data.FSERVER_WLAN_IP);
							$("#detail-farm_distribution_ratio").text(data.DISTRIBUTION_RATIO);
							if(data.IS_FORBIDDEN == 0)
								$('#detail-farm_is_forbidden').text("启用");
							else if(data.IS_FORBIDDEN == 1)
								$('#detail-farm_is_forbidden').text("禁用");
							else if(data.IS_FORBIDDEN == 2)
								$('#detail-farm_is_forbidden').text("暂停接收");
							else
								$('#detail-farm_is_forbidden').text("");
							
							if(data.IS_WEB_FSERVER == 1)
								$('#detail-farm_is_web_fserver').text("是");
							else
								$('#detail-farm_is_web_fserver').text("否");
							if(data.STAT == 0)
								$('#detail-farm_stat').text("有效");
							else
								$('#detail-farm_stat').text("失效");
							$('#detail-farm_description').text(data.DESCRIPTION);
							
							var dlgParent = $("#farm_parameter_detail").parent();
					        var dlgOwn = $("#farm_parameter_detail").clone();
							farm_param_detail = $("#farm_parameter_detail").dialog({
								autoOpen: false,
								height:500,
								width:730,
								show: "slide",
								modal:true,
								cache:false,
								/* hide: "explode", */
								title:'查看农场配置参数设置',
					            close : function() {
					            	dlgOwn.appendTo(dlgParent);
					            	$(this).dialog("destroy").remove();
					            }
							});
							farm_param_detail.dialog("open");
						}
					}
				});
			}else{
				showMsg("请选择一行数据！");
			}
		},
		disableEnable : function(bName){
			var cnt = $('.trSelected',$("#farm_grid")).length;
			if(cnt == 1){
				if($($('.trSelected td:nth-child(8) div',$("#farm_grid"))[0]).text() == "已删除"){
					showMsg("该农场已经失效无法操作！");
					return;
				}
				var info = "";
				var url = "";
				var queryP = {};
				var farmForbidden = $($('.trSelected td:nth-child(5) div',$("#farm_grid"))[0]).text();
				if(bName == "启用"){
					queryP.isForbidden = 0;
					url = "farm!disableEnable_qy.action";
					if(farmForbidden == "启用"){
						showMsg("该农场无法执行"+bName+"操作");
						return;
					}
				}else if(bName == "禁用"){
					queryP.isForbidden = 1;
					url = "farm!disableEnable_jy.action";
					if(farmForbidden == "禁用"){
						showMsg("该农场无法执行"+bName+"操作");
						return;
					}
				}else if(bName == "暂停接收"){
					queryP.isForbidden = 2;
					url = "farm!disableEnable_cs.action";
					if(farmForbidden == "暂停接收"){
						showMsg("该农场无法执行"+bName+"操作");
						return;
					}
				}
				queryP.farmId =  $($('.trSelected td:nth-child(1) div',$("#farm_grid"))[0]).text();
				if(window.confirm("是否确认要将该农场状态修改为'"+bName+"'状态？")){
					$.ajax({
						type: "POST",
						url: url,
						cache: false,
						data: queryP,
						success: function(msg){
							var data=eval("("+msg+")");
							if(data.result == "01"){
								showMsg("农场"+info+"成功！");
			            		farm_job.loadData({page : 1});
							}else{
								showErrorMsg("农场"+info+"失败！");
							}
						}
					});
				}
			}else
				showMsg("请选择一行数据！");
		}
};

$(function() {
	//初始化所有操作按钮
	 var  parm_button = [{name: '新建', bclass: 'add', onpress : farm_job.newFarmParam},
	 					{name: '修改', bclass: 'edit',onpress : farm_job.modifyFarmParam},
	 					{name: '详情',bclass: 'edit', onpress : farm_job.showFarmDetail},
	 					{name: '启用',bclass: 'edit', onpress : farm_job.disableEnable, parameter : 0},
	 					{name: '禁用',bclass: 'edit', onpress : farm_job.disableEnable, parameter : 1},
	 					{name: '暂停接收',bclass: 'edit', onpress : farm_job.disableEnable, parameter : 2}
	 					];
	 //权限操作按钮
	 var buttons = [];
	 
	 //获得当前菜单的id
	 var menu_id;
	 $('#tabs ul li').each(function(){
		if($(this).hasClass("ui-tabs-selected")){
			var this_id = $(this).children("div").children("a").attr("href");
			menu_id =this_id.substring(5);
			}
		});
	 
	 // 根据菜单id，初始化操作按钮
	 $.ajax({
		type: "GET",
		url: "doauth!getButtons.action?menu_id="+menu_id,
		cache: false,
		success: function(data){
			var data1=eval("("+data+")");
			for (var i in data1.buttons) {
				var buttonName = data1.buttons[i].BUTTON_NAME;
				$(parm_button).each(function(index){
					var val = parm_button[index].name;
					if(val == buttonName){
						buttons.push(parm_button[index]);
						buttons.push({separator: true});
					}
				});
			}
  			// 初始化数据
			farm_job.bindJobGrid(buttons);
		}
	});
	 
	/*公告管理-查询按钮功能*/
	$('#btn_farm_search').button({ icons: {primary:'ui-icon-search'} }).on("click",function(){
		farm_job.loadData({page : 1});
	});
	
	$('#btn_farm_empty').on("click",function(){
		$("#txt_farm_name").val("");
	});
});
</script>
<div class="div div-1">
	<div class="title title-1">
		<h3>
			<img src='../images/fold.png' onclick="showAndHide(this,'farm_grid');"  class="imgShrotate">
				<font>查询条件</font>
		</h3>
	</div>
	<div class="div-main-1">
		<table cellpadding="0" cellspacing="0" border="0" class="table-1">
			<tr style="font-size: 14px">
				<td style="width:10%;"><label for="input_1" class="lbl-1">农场名称：</label></td>
				<td class="wi-align-l" style="width:12%;">
					<input type="text" id="txt_farm_name" name="txt_farm_name" class="wi-ipt-1" />
				</td>
				<td class="wi-align-l" style="width:20%;text-align: right">
					<button id="btn_farm_search" style="width:80px; height:28px;font-size:14px;background-color:#3ea4e8;color: #fff">查&nbsp;询</button>
					<button id="btn_farm_empty"  style="width:80px; height:28px;font-size:14px;background-color:#3ea4e8;color: #fff">清&nbsp;空</button>
				</td>	
			</tr>
		</table>
	</div>
</div>
<div class="div div-2" style="overflow:auto;">
	<table id="farm_grid" style="display:none;padding:0 0 0 0;"></table>
</div>
<div id="farm_parameter_add" style="display:none">
	<table  border="0" class="table-1">
		<tr>
			<td style="width:180px;text-align: right;font-size: 12;font-weight: bolder;">农场名称：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="add-farm_name" valid="required">
			</td>
			<td style="width:180px;text-align: right;font-size: 12;font-weight: bolder;">集群总节点：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="add-farm_agent_count" valid="integer">
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">优先级：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="add-farm_priority" valid="integer"><br/><span style="color: red;font-size: 12;">农场优先级范围:100-0</span>
			</td>
			<td style="width:180px;text-align: right;font-size: 12;font-weight: bolder;">是否有效：</td>
			<td class="wi-align-l" style="width:200px;">
				<select id="add-farm_stat" class="wi-ipt-1" disabled="disabled">
					<option value="0">有效</option>
					<option value="1">无效</option>
				</select>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">集群IP：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="add-farm_cluster_ip" valid="ip">
			</td>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">集群内网端口：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="add-farm_cluster_port" valid="integer">
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">文件服务器内网IP：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="add-farm_fserver_lan_ip" valid="ip">
			</td>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">文件服务器内网端口：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="add-farm_fserver_lan_port" valid="integer">
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">文件服务器外网IP：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="add-farm_fserver_wlan_ip" valid="ip">
			</td>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">文件服务器外网端口：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="add-farm_fserver_wlan_port" valid="integer">
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">web文件服务器域名：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="add-farm_domain_name" valid="required">
			</td>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">web文件服务器对应端口：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="add-farm_domain_port" valid="integer">
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">禁用状态：</td>
			<td class="wi-align-l" style="width:200px;">
				<select id="add-farm_is_forbidden" class="wi-ipt-1">
					<option value="0">启用</option>
					<option value="1">禁用</option>
					<option value="2">暂停接收</option>
				</select>
			</td>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">是否为主文件服务器：</td>
			<td class="wi-align-l" style="width:200px;">
				<select id="add-farm_is_web_fserver" class="wi-ipt-1">
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">数据库IP：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="add-farm_fs_mysql_ip" valid="ip">
			</td>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">数据库端口：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="add-farm_fs_mysql_port" valid="integer">
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">数据库用户名：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="add-farm_fs_mysql_username" valid="required">
			</td>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">数据库密码：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="password" class="wi-ipt-1" id="add-farm_fs_mysql_password" valid="required">
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">任务分发比：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="add-farm_distribution_ratio" valid="integer1">
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">农场备注：</td>
			<td class="wi-align-l" style="width:500px;" colspan="3">
				<textarea rows="5" cols="58" id="add-farm_description"></textarea>
			</td>
		</tr>
	</table>
</div>
<div id="farm_parameter_modify" style="display:none">
	<table  border="0" class="table-1">
		<tr>
			<td style="width:180px;text-align: right;font-size: 12;font-weight: bolder;">农场名称：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="modify-farm_name" valid="required">
				<input type="hidden" id="modify-farm_id" value="">
			</td>
			<td style="width:180px;text-align: right;font-size: 12;font-weight: bolder;">集群总节点：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="modify-farm_agent_count" valid="integer">
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">优先级：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="modify-farm_priority" valid="integer"><br/><span style="color: red;font-size: 12;">农场优先级范围:100-0</span>
			</td>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">是否有效：</td>
			<td class="wi-align-l" style="width:200px;">
				<select id="modify-farm_stat" class="wi-ipt-1">
					<option value="0">有效</option>
					<option value="1">无效</option>
				</select>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">集群IP：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="modify-farm_cluster_ip" valid="ip">
			</td>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">集群内网端口：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="modify-farm_cluster_port" valid="integer">
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">文件服务器内网IP：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="modify-farm_fserver_lan_ip" valid="ip">
			</td>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">文件服务器内网端口：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="modify-farm_fserver_lan_port" valid="integer">
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">文件服务器外网IP：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="modify-farm_fserver_wlan_ip" valid="ip">
			</td>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">文件服务器外网端口：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="modify-farm_fserver_wlan_port" valid="integer">
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">web文件服务器域名：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="modify-farm_domain_name" valid="required">
			</td>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">web文件服务器对应端口：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="modify-farm_domain_port" valid="integer">
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">禁用状态：</td>
			<td class="wi-align-l" style="width:200px;">
				<select id="modify-farm_is_forbidden" class="wi-ipt-1" disabled="disabled">
					<option value="0">启用</option>
					<option value="1">禁用</option>
					<option value="2">暂停接收</option>
				</select>
			</td>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">是否为主文件服务器：</td>
			<td class="wi-align-l" style="width:200px;">
				<select id="modify-farm_is_web_fserver" class="wi-ipt-1">
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">数据库IP：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="modify-farm_fs_mysql_ip" valid="ip">
			</td>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">数据库端口：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="modify-farm_fs_mysql_port" valid="integer">
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">数据库用户名：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="modify-farm_fs_mysql_username" valid="required">
			</td>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">数据库密码：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="password" class="wi-ipt-1" id="modify-farm_fs_mysql_password" valid="required">
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">任务分发比：</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" class="wi-ipt-1" id="modify-farm_distribution_ratio" valid="integer1">
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">农场备注：</td>
			<td class="wi-align-l" style="width:500px;" colspan="3">
				<textarea rows="5" cols="58" id="modify-farm_description"></textarea>
			</td>
		</tr>
	</table>
</div>
<div id="farm_parameter_detail" style="display:none">
	<table  border="0" class="table-1">
		<tr>
			<td style="width:180px;text-align: right;font-size: 12;font-weight: bolder;">农场名称：</td>
			<td class="wi-align-l" style="width:200px;">
				<label class="wi-ipt-1" id="detail-farm_name"></label>
			</td>
			<td style="width:180px;text-align: right;font-size: 12;font-weight: bolder;">集群总节点：</td>
			<td class="wi-align-l" style="width:200px;">
				<label class="wi-ipt-1" id="detail-farm_agent_count"></label>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">优先级：</td>
			<td class="wi-align-l" style="width:200px;">
				<label class="wi-ipt-1" id="detail-farm_priority"></label>
			</td>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">是否有效：</td>
			<td class="wi-align-l" style="width:200px;">
				<label class="wi-ipt-1" id="detail-farm_stat"></label>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">集群IP：</td>
			<td class="wi-align-l" style="width:200px;">
				<label class="wi-ipt-1" id="detail-farm_cluster_ip"></label>
			</td>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">集群内网端口：</td>
			<td class="wi-align-l" style="width:200px;">
				<label class="wi-ipt-1" id="detail-farm_cluster_port"></label>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">文件服务器内网IP：</td>
			<td class="wi-align-l" style="width:200px;">
				<label class="wi-ipt-1" id="detail-farm_fserver_lan_ip"></label>
			</td>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">文件服务器内网端口：</td>
			<td class="wi-align-l" style="width:200px;">
				<label class="wi-ipt-1" id="detail-farm_fserver_lan_port"></label>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">文件服务器外网IP：</td>
			<td class="wi-align-l" style="width:200px;">
				<label class="wi-ipt-1" id="detail-farm_fserver_wlan_ip"></label>
			</td>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">文件服务器外网端口：</td>
			<td class="wi-align-l" style="width:200px;">
				<label class="wi-ipt-1" id="detail-farm_fserver_wlan_port"></label>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">web文件服务器域名：</td>
			<td class="wi-align-l" style="width:200px;">
				<label class="wi-ipt-1" id="detail-farm_domain_name"></label>
			</td>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">web文件服务器对应端口：</td>
			<td class="wi-align-l" style="width:200px;">
				<label class="wi-ipt-1" id="detail-farm_domain_port"></label>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">禁用状态：</td>
			<td class="wi-align-l" style="width:200px;">
				<label class="wi-ipt-1" id="detail-farm_is_forbidden"></label>
			</td>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">是否为主文件服务器：</td>
			<td class="wi-align-l" style="width:200px;">
				<label class="wi-ipt-1" id="detail-farm_is_web_fserver"></label>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">数据库IP：</td>
			<td class="wi-align-l" style="width:200px;">
				<label class="wi-ipt-1" id="detail-farm_fs_mysql_ip"></label>
			</td>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">数据库端口：</td>
			<td class="wi-align-l" style="width:200px;">
				<label class="wi-ipt-1" id="detail-farm_fs_mysql_port"></label>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">数据库用户名：</td>
			<td class="wi-align-l" style="width:200px;">
				<label class="wi-ipt-1" id="detail-farm_fs_mysql_username"></label>
			</td>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">数据库密码：</td>
			<td class="wi-align-l" style="width:200px;">
				<label class="wi-ipt-1" id="detail-farm_fs_mysql_password"></label>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">任务分发比：</td>
			<td class="wi-align-l" style="width:200px;">
				<label class="wi-ipt-1" id="detail-farm_distribution_ratio"></label>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;font-size: 12;font-weight: bolder;">农场备注：</td>
			<td class="wi-align-l" style="width:500px;" colspan="3">
				<label class="wi-ipt-1" id="detail-farm_description"></label>
			</td>
		</tr>
	</table>
</div>