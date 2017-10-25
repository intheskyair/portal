<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/commonCSSJS.jsp"%>
 <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/test/metroStyle/metroStyle.css" />
 <script type="text/javascript" src="${ctx}/views/common/js/zTree/jquery.ztree.core.js"></script>
 <script type="text/javascript" src="${ctx}/views/common/js/zTree/jquery.ztree.excheck.js"></script>
  <script>x=-38</script>
<body style="position:relative;">
<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='images/fold.png' onclick="showAndHide(this,'car_grid');"  class="imgShrotate">
				<font>车辆信息管理</font>
			</h3>
		</div>
     <div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>
					<td class="wi-align-l">
						<label for="input_1" class="lbl-1">调用状态:</label>
						<select name="status" id="status" style="height: 25px;line-height: 25px;border: 1px solid #ccc;font-size: 12px;">
							<option value="-1">全部</option>
							<option value="1">空闲</option>
							<option value="2">已出</option>
							<option value="3">维修</option>
						</select>
					</td>
					<td class="wi-align-l" >
						<label for="input_1" class="lbl-1">
							车牌号:
						</label>
						<input type="text" id="car_no" name="car_no" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<label for="input_1" class="lbl-1">
							所属部门：
						</label>
						<input type="text" id="org_id" name="org_id" class="wi-ipt-1"/>
					</td>
					<td class="wi-align-l">
						<button id="btn_plann_search" class="ui-state-default ui-state-qik">查询
						</button>
						<button id="btn_result_empty" class="ui-state-default ui-state-qik">清空
						</button>
					</td>
				</tr>
			</table>
		</div>
</div>
<div class="div div-2" style="">
	<table id="car_grid" style="display:none;padding:0 0 0 0;"></table>
</div>

<div id="dlg_carinfo" style="display:none;overflow-y: hidden;">
	<table  cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
	
 <input type="hidden" id="dlg_id" /> 
  
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">车牌号：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_carno" name="dlg_carno"  class="wi-ipt-1" onblur="judgeCarNo()" valid="carno"/>
				<input type="hidden" id="dlg_car_id" name="dlg_car_id">
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">车辆属性：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_car_attr" name="dlg_car_attr"  class="wi-ipt-1" valid="required"/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">车辆类别：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_car_category" name="dlg_car_category"  class="wi-ipt-1" valid="required"/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">座位数：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_car_seat_num" name="dlg_car_seat_num"  class="wi-ipt-1" valid="integer1"/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">车辆品牌：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_car_brand" name="dlg_car_brand"  class="wi-ipt-1" valid="required"/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">车辆型号：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_car_type" name="dlg_car_type"  class="wi-ipt-1" valid="required"/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">车辆状态：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<select name="dlg_car_status" id="dlg_car_status" class="wi-ipt-1">
					<option value="1">空闲</option>
					<option value="2">已出</option>
					<option value="3">维修</option>
				</select>
			</td>
		</tr>
		
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">所属部门：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
			
				<input type="text" id="dlg_department" name="dlg_department" class="wi-ipt-1" onclick="department_menu(dlg_id.value)"  valid="required"/>
					<input type="text" id="department_id" value="" class="wi-ipt-1" style="display:none;"/> 
					<input type="hidden" id="department_id_tmp" />
			</td>
		</tr>
	</table>
</div>	
<div id="dlg_department_menu" style="display:none">  
	<div style="overflow:auto;">
		<ul id="dlg_department_menu_tree" class="ztree">
		</ul>
	</div>
	<input type="hidden" id="dlg_department_id" /> 
</div>
<div >
	<ul id="department_tree" class="ztree">
	</ul>
</div>

<div id="dlg_car_upload" style="display:none;overflow-y: hidden;">
	<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
		<tr>
			<td>
				<label style="color:red;">*</label>
				<label for="input_1" style="color: red;">请选择需要导入的文件（文件只能为以xls/xlsx格式的）</label>
			</td>
		</tr>
		<tr>
			<td>
				<label style="color:red;">
					2003EXCEL模板下载:
				</label>
				<label>
				<a href="#" onclick="downloadModel('xls');"><img alt="2003EXCEL模板下载" src="images/excel2.jpg" style="width: 16px;height: 16px;"></a>
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label style="color:red;">
					2007EXCEL模板下载:
				</label>
				<label>
				<a href="#" onclick="downloadModel('xlsx');"><img alt="2007EXCEL模板下载" src="images/excel2.jpg" style="width: 16px;height: 16px;"></a>
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<input type="file" id="file_name" name="file_name" accept=".xls,.xlsx"/>
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript">
var data_all;
		 
		 var menuTree;
		 var treeNodes;
		 
var page_num = 30;
var car_grid = {
	//绑定参数信息grid
	car_grid_result:"",
	carGridHeight:flexHeight("car_grid"),
	bindParamGrid:function(queryP,queryM){
		car_grid.car_grid_result = $("#car_grid").flexigrid({
			url:"carManager!listCarInfo.action?operation=init",
			colModel: queryM,
			buttons : queryP,
			rowId:"id",
			showToggleBtn: true,
			usepager: true,
			title:"车辆信息列表",
			//rp:pageSize,
			rp:page_num,
			showTableToggleBtn: false,
			resizable: false,
			height: flexHeight("car_grid"),
			singleSelect: true,
			onChangePage:car_grid.loadData,
			checkbox:false,
			filterParams : null
		});
	},
	loadData:function(param){
		var queryP = {};
		//var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
		var selectedGridDiv = $("div.flexigrid .pDiv .pDiv2 .pGroup");
		if($("div[class='div-main-1'] input[valid]").validate()){
			queryP.status = $("#status").val();
			queryP.carno = $("#car_no").val();
			queryP.orgid = $("#org_id").val();
			queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
			queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
			queryP.sortname = param.sortname? param.sortname : "";
			queryP.sortorder = param.sortorder? param.sortorder : "";
			$.ajax({
				type: "POST",
				url: "carManager!listCarInfo.action?operation=init",
				cache: false,
				data: queryP,
				success: function(msg){
					var data=eval("("+msg+")");
					try{
						car_grid.car_grid_result.flexAddData(data);
					}catch(e){
						showMsg("查询结果信息出错！");
					}
				}
			});
		}
	},
	selectMenus : function() {
				var nodes = menuTree.getCheckedNodes(true);
				var nodes_child_name = [];
				var nodes_parent_name = [];
				var nodes_child_id = [];
				var nodes_parent_id = [];
				for (var i in nodes) {
					if(nodes[i].isParent){
						nodes_parent_name.push(nodes[i].name);
						nodes_parent_id.push(nodes[i].id);
					}else{
						nodes_child_name.push(nodes[i].name);
						nodes_child_id.push(nodes[i].id);
					}
				}
				/*if(nodes_child_name.length>1||nodes_parent_name.length>1)
				{
					showMsg("请您选择一个部门！");
					return;
				}*/
				if(nodes_child_name.length==1)
				{
					$("#dlg_department").val(nodes_parent_name+"("+nodes_child_name+")");
					$("#department_id").val(nodes_child_id);
				}
				else
				{
					$("#dlg_department").val(nodes_parent_name);
					$("#department_id").val(nodes_parent_id);
				}
				$('#dlg_department_menu').dialog('close');
			},
			
	showDialog : function(com,operation,divobj,h,w) {
		var dlgParent = divobj.parent();
		var dlgOwn = divobj.clone();
		dlg_plann = divobj.dialog({
				autoOpen: false,
				height:h ? h :400,
				width:w ? w : 800,
				show: "slide",
				modal:true,
				cache:false,
				/* hide: "explode", */
				title:com,
				buttons:{
					"取消":function(){
						dlgOwn.appendTo(dlgParent);
						$(this).dialog("destroy").remove();
					},
					"保存":function(){
						//dlgOwn.appendTo(dlgParent);
						car_grid.saveCarinfo(operation);
		              	//$(this).dialog("destroy").remove();
					}
				},
			    close : function() {
		              	dlgOwn.appendTo(dlgParent);
		              	$(this).dialog("destroy").remove();
		         }
			});
			
		dlg_plann.dialog("option", "title", com).dialog("open");
	},
	createCarInfo:function(name,grid){
		//showMsg("车辆新增");
		//由于之前，编辑时，逐个对input进行赋值，添加和编辑用的是同一个页面，所以编辑时值还会存在，
		//以下对div内的input值全部清空
		$("#dlg_carinfo :input").each(function () { 
			$(this).val(""); 
		}); 
		car_grid.showDialog("新增车辆信息","add", $("#dlg_carinfo"));
	},
	modifyCarInfo:function(name,grid){
		//showMsg("车辆编辑");
		if($(".trSelected", grid).length==1){
			//alert(name+"--->"+$(".trSelected", grid).eq(0).find("td:eq(1)").text());
			var carId = $.trim($(".trSelected", grid).eq(0).find("td:eq(1)").text());
			$.ajax({
				type: "POST",
				url: "carManager!getCarInfoById.action?carId="+carId+"&radom="+Math.random(),
				cache: false,
				data: null,
				success: function(msg){
					if(msg){
						var data=eval("("+msg+")");
						$("#dlg_carno").val(data.CAR_NO);
						$("#dlg_car_attr").val(data.CAR_ATTRIBUTE);
						$("#dlg_car_category").val(data.CAR_CATEGORY);
						$("#dlg_car_seat_num").val(data.CAR_SEAT_NUM);
						$("#dlg_car_brand").val(data.CAR_BRAND);
						$("#dlg_car_type").val(data.CAR_TYPE);
						$("#dlg_car_status").val(data.CAR_STATUS);
						$("#dlg_org_id").val(data.ORG_ID);
						$("#dlg_department").val(data.ORG_NAME);
						$("#dlg_car_id").val(data.ID);
						car_grid.showDialog("修改车辆信息","modify", $("#dlg_carinfo"));
					}else
						showMsg("车辆"+carType+"("+carNo+")"+"不存在！");
				}
			});
		}else
			showMsg("请选择一行数据进行操作");
		//car_grid.showDialog("修改车辆信息","modify", $("#dlg_carinfo"));
	},
	removeCarInfo:function(name,grid){
		//showMsg("车辆删除");
		if($(".trSelected", grid).length==1){
			var carId = $.trim($(".trSelected", grid).eq(0).find("td:eq(1)").text());
			  var indicate = "确认删除？";
			 if(confirm(indicate)) 
			{ 
			$.ajax({
				type: "POST",
				url: "carManager!removeCarInfoById.action?carId="+carId+"&radom="+Math.random(),
				cache: false,
				data: null,
				success: function(msg){
					if(msg){
						var data=eval("("+msg+")");
						if(data.result == "00"){
							showMsg("车辆信息删除成功！");
							car_grid.loadData({page:1});
						}else{
							showMsg("车辆信息删除出错！");
						}
					}else{
						showMsg("车辆信息删除出错！");
					}
				}
			});
			}else{
	   $('#dlg_carinfo').dialog('close');
	}
		}else{
			showMsg("请选择一行数据进行操作");
	}
	
	},
	importCarInfos:function(name,grid){
		//showMsg("车辆信息批量导入");
		car_grid.showDialog("车辆信息批量导入","import", $("#dlg_car_upload"),400,330);
	},
	exportCarInfos:function(name,grid){
		var condition = "";
		var status=$("#status")==null?'':$("#status").val();
		if (status && $.trim(status).length > 0) 
		{
			condition += "&status=" + $.trim(status);
		}
		var carno=$("#car_no")==null?'':$("#car_no").val();
		if (carno && $.trim(carno).length > 0) 
		{
			condition += "&carno=" + $.trim(carno);
		}
		var orgid=$("#org_id")==null?'':$("#org_id").val();
		if (orgid && $.trim(orgid).length > 0) 
		{
			condition += "&orgid=" +  encodeURI(encodeURI($.trim(orgid)));
		}
		window.location.href='carManager!exportJobs.action?'+condition+'&radom='+Math.random();
	},
	saveCarinfo:function(operation){
		if(operation == "add"){
			if($("#dlg_carinfo").validate()){
				var queryP = {};
				queryP.carno = $.trim($("#dlg_carno").val());
				queryP.carAttr = $.trim($("#dlg_car_attr").val());
				queryP.carCategory = $.trim($("#dlg_car_category").val());
				queryP.carSeatNum = $.trim($("#dlg_car_seat_num").val());
				queryP.carBrand = $.trim($("#dlg_car_brand").val());
				queryP.carType = $.trim($("#dlg_car_type").val());
				queryP.carStatus = $.trim($("#dlg_car_status").val());
				queryP.orgId = $.trim($("#dlg_org_id").val());
				queryP.departmentId = $.trim($("#dlg_department").val());
				$.ajax({
				type: "POST",
				url: "carManager!createCar.action",
				cache: false,
				data: queryP,
				success: function(msg){
					if(msg){
						var data=eval("("+msg+")");
						if(data.result == "00"){
							showMsg("新增车辆信息成功！");
							
							car_grid.loadData({page:1});
							$("#dlg_carinfo").dialog('close');
							
						}else{
							showMsg("新增车辆信息出错！");
						}
					}else
						showMsg("新增车辆信息出错！");
					}
				});
				
			}
			//$("#dlg_carinfo").dialog('close');
		}else if(operation == "modify"){
			//$('#div').validate({showMsg : function(){...}}).validate();
			if($("#dlg_carinfo").validate()){
				var queryP = {};
				queryP.carid = $.trim($("#dlg_car_id").val());
				queryP.carno = $.trim($("#dlg_carno").val());
				queryP.carAttr = $.trim($("#dlg_car_attr").val());
				queryP.carCategory = $.trim($("#dlg_car_category").val());
				queryP.carSeatNum = $.trim($("#dlg_car_seat_num").val());
				queryP.carBrand = $.trim($("#dlg_car_brand").val());
				queryP.carType = $.trim($("#dlg_car_type").val());
				queryP.carStatus = $.trim($("#dlg_car_status").val());
				queryP.orgId = $.trim($("#dlg_org_id").val());
				queryP.departmentId = $.trim($("#dlg_department").val());
				$.ajax({
				type: "POST",
				url: "carManager!modifyCarInfoById.action",
				cache: false,
				data: queryP,
				success: function(msg){
					if(msg){
						var data=eval("("+msg+")");
						if(data.result == "00"){
							showMsg("车辆信息修改成功！");
							
							car_grid.loadData({page:1});
							$("#dlg_carinfo").dialog('close');
							
						}else{
							showMsg("车辆信息修改出错！");
						}
					}else
						showMsg("车辆信息修改出错！");
					}
				});
				$("#dlg_carinfo").dialog('close');
			}
		}else if(operation == "import"){
			var queryP = {};
			queryP.nameType = "car";
			queryP.suffix = $.trim($("#file_name").val()).split("\.")[1];
			$.ajaxFileUpload({
				url:"upload!importExcel.action?radom="+Math.random(),
				fileElementId:"file_name",
				secureuri:false,
				type:"POST",
				data:queryP,
				dataType:"json",
				success:function(data){
					if(data != null && data != "" && data.result == "00"){
						showMsg("车辆信息导入成功！");
						car_grid.loadData({page:1});
						$("#dlg_car_upload").dialog('close');
					}else{
						showMsg("车辆信息导入失败！");
					}
				},
				error:function(){
					showMsg("文件导入失败");
				}
			});
		}
	}
};

	
function downloadModel(obj){
	if(obj != null && obj != "")
		window.location.href = "upload!exportExcelModelForTable.action?suffix="+obj+"&nameType=car"+"&radom="+Math.random();
}

	$(function(){
		//初始化所有操作按钮
		 var  parm_button = [
			  {name: '添加', bclass: 'add', onpress : car_grid.createCarInfo},
			  {name: '编辑', bclass: 'edit', onpress : car_grid.modifyCarInfo},
			  {name: '删除', bclass: 'delete', onpress : car_grid.removeCarInfo},
			  {name: '导入', bclass: 'distribute', onpress : car_grid.importCarInfos},
			  {name: '导出', bclass: 'excel', onpress : car_grid.exportCarInfos}
		 ];
		 //权限操作按钮
		 var colModel = [						
	            {display: '序号', name : 'xh', width : 40, align: 'center'},
	            {display: 'id', name : 'ID', width : 40, align: 'center',hide : true},
				{display: '车牌号', name : 'CAR_NO', width : 120, align: 'center',sortable: true},
				{display: '车辆属性', name : 'CAR_ATTRIBUTE', width : 150, align: 'left',sortable: true},
				{display: '车辆类别', name : 'CAR_CATEGORY', width : 150, align: 'left',sortable: true},
				{display: '座位数', name : 'CAR_SEAT_NUM', width : 120, align: 'left',sortable: true},
				{display: '车辆品牌', name : 'CAR_BRAND', width : 150,  align: 'left',sortable : true},
				{display: '车辆型号', name : 'CAR_TYPE', width : 150,  align: 'left',sortable : true},
				{display: '车辆状态', name : 'CAR_STATUS', width : 120,  align: 'left',sortable : true,process:function(value,pid){
					var status = $.trim($(value).html());
					       if(status == "1"){
							$(value).html("空闲");
							}else if(status == "2"){
							$(value).html("已出");
							}else if(status == "3"){
							$(value).html("维修");
							}else{
							 $(value).html("未知"); 
							}
						
				}},
				
				{display: '所属部门', name : 'ORG_NAME', width : 300,  align: 'left',sortable : true}
		 ];
		 
		 	 			// 初始化扯脸列表信息数据
		 car_grid.bindParamGrid(parm_button,colModel); 
		$("#btn_plann_search").button().on("click",function(){
			car_grid.loadData({page:1});
		});
		$("#btn_result_empty").on("click",function(){
			$("#status").val(0);
			$("#car_no").val("");
			$("#org_id").val("");
		});
	});
	
	
	function judgeCarNo(){
			   var car = {};
				car.carNo = $('#dlg_carno').val()==null?'':$('#dlg_carno').val();
				car.id = $('#dlg_car_id').val();
				$.ajax({
					type:"POST",
					url: "carManager!judgeCarNo.action",
					cache: false,
					data:car,
					success: function(msg){
						//var result=eval("("+msg+")");
						if (msg=="ok")
						{
							$("#dlg_carno").val("");
							showMsg("该车已存在，请重新输入！");
						}
					}	
				});
		   }
	
	// 树形结构设置
		 var departmentTreeSetting = {
		 		view: {
		 			dblClickExpand: true,
		 			showLine: false,
		 			selectedMulti: true,
		 			expandSpeed: ($.browser.msie && parseInt($.browser.version)<=6)?"":"fast"
		 		},
		 		check : {
		 			enable : true,
		 			chkboxType : {"Y" : "p", "N" : "s"}
		 		},
		 		data: {
		 			simpleData: {
		 				enable: true,
		 				idKey: "id",
		 				pIdKey: "pId"
		 			}
		 		},
		 		callback:{
		 			onClick: function(event, treeId, treeNode, clickFlag)
		 			{
		 				$('#dlg_department_menu_id').val(treeNode.id);
		 				$('#dlg_department_menu_name').val(treeNode.name);
		 			}
		 		}
		 };  
		 var dlg_department_menu;      	
		 //弹出部门树
		 function department_menu(id) {
			var department_id = $("#department_id").val();
			var dlgParent = $("#dlg_department_menu").parent();
	        var dlgOwn = $("#dlg_department_menu").clone();
	        dlg_department_menu = $("#dlg_department_menu").dialog({
				autoOpen: false,
				height:500,
				width:300,
				buttons:{
					"确定":function(){
						car_grid.selectMenus();
					}
				},
	            close : function() {
	            	dlgOwn.appendTo(dlgParent);
	            	$(this).dialog("destroy").remove();
	            }
			}); 
	       	$("#dlg_department_id").val(id);
			$('#dlg_department_menu_id').val('');
		    $('#dlg_department_menu_name').val('');
		    dlg_department_menu.dialog("open");
			var queryP = {};
			queryP.pagenum = 1;
			queryP.pagesize = 10000;
			queryP.id = id;
			$.ajax({
				type: "POST",
				url: "org!getDepartment.action?operation=init",
				cache: false,
				data: queryP,
				success: function(msg){
					var data=jQuery.parseJSON(msg);
					// 初始化部门树
					menuTree = $.fn.zTree.init($("#dlg_department_menu_tree"), departmentTreeSetting, data.allMenu);
					// 选中的部门id
					if(department_id==""){
						if(data.org_id!=""){
							menuTree.checkNode(menuTree.getNodeByParam("id", data.org_id), true, false);
						}
					}else{
						menuTree.checkNode(menuTree.getNodeByParam("id", department_id), true, false);
					}
				}
			});
		 }
		 
</script>
</body>