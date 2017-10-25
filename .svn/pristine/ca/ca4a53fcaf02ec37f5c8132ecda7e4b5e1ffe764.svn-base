<%@ page contentType="text/html; charset=UTF-8"%>
<html>
    <head>
	<title>业务员区域分配</title>
	<script type="text/javascript" src="../js/jquery.cityselect.js"></script>
	</head>
	<body>	
	<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='../images/fold.png' onclick="showAndHide(this,'area_grid');"  class="imgShrotate">
				<font>业务员区域分配</font>
			</h3>
		</div>
		<div class="div-main-1">
		
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>
					<td style="width:100px;"><label for="input_1" class="lbl-1">业务员账号：</label></td>
					<td class="wi-align-l" style="width:170px;">
						<input type="text" id="area_username" name="area_username" class="wi-ipt-1" />
					</td>
					<td style="width:100px;"><label for="input_1" class="lbl-1">业务员姓名：</label></td>
					<td class="wi-align-l" style="width:170px;">
						<input type="text" id="area_truename" name="area_truename" class="wi-ipt-1" />
					</td>
					<td style="width:100px;"><label for="input_1" class="lbl-1">部门：</label></td>
					<td class="wi-align-l" style="width:170px;">
						<select name="area_department" id="area_department" class="wi-sel-1" style="width:150px;">
							<option value="">销售部</option>
						</select>
					</td>
					<td class="wi-align-l" style="width: 100px;"><button id="btn_area_search" style="width:74px; height:29px;font-size:14px;background-color:#3ea4e8;color: #fff">查询</button></td>
					<td class="wi-align-l" style="width: 100px;"><button id="btn_area_empty" style="width:74px; height:29px;font-size:14px;background-color:#3ea4e8;color: #fff">清空</button></td>
				</tr>
			</table>
		</div>
	</div>
	<div class="div div-2" style="overflow:auto;">
		<table id="area_grid" style="display:none;padding:0 0 0 0;"></table>
	</div>
	<!-- 区域分配 -->
	<div id="dlg_area_distribute" style="display:none">
		<input type="hidden" id="area_distribute_id" /> 
		<form method = "post" name ="area">
			<table border="0">
				<tr style="height:35px">
					<td class="wi-align-l" style="text-align:center">可分配区域：</td>
					<td class="wi-align-l" ></td>
					<td class="wi-align-l" style="text-align:center">已辖区域：</td>
				</tr>
				<tr>
					<td>
						<select multiple name="department_list1" id="department_list1" class="wi-sel-1" style="width:200px;height:260px" >
						</select>
					</td>
					<td style="width:200px;text-align:center">
						<input style="width:50px" type ="button" value ="添加" onclick="moveOption(document.area.department_list1,document.area.department_list2);"><br/><br/>
						<input style="width:50px" type ="button" value ="移除" onclick="moveOption(document.area.department_list2,document.area.department_list1);"><br/>
					</td>
					<td >
						<select multiple name="department_list2" id="department_list2" class="wi-sel-1" style="width:200px;height:260px" >
						</select>
					</td>
				</tr>
			</table>
		</form>
	</div> 
	<!-- 区域分配情况 -->
	<div id="show_area_distribute" style="display:none;text-align:center">
		<table border="0" style="margin:auto;width:80%">
			<tr style="height:35px">
				<td class="wi-align-l" style="text-align:center">区域分配情况：</td>
			</tr>
			<tr>
				<td style="text-align:center">
					<select multiple name="department_list" id="department_list" class="wi-sel-1" style="width:300px;height:260px" >
					</select>
				</td>
			</tr>
		</table>
	</div> 
	<script>		
		 var paramGridHeight_area = flexHeight("area_grid");
		 var param_grid_area;
		 var area = {
			bindAreaGrid:function(queryP){
				param_grid_area = $("#area_grid").flexigrid({
					url:"salesman!areaDistribute.action?operation=init",
					colModel : [
						{display: '序号', name : 'xh', width : 40, align: 'center'},
						{display: 'id', name : 'ADMIN_ID', width : 40, align: 'center',hide : true},
						{display: '业务员账号', name : 'USERNAME', width : 100, align: 'center',sortable: true},
						{display: '业务员姓名', name : 'TRUENAME', width : 100, align: 'center',sortable: true},
						{display: '账号状态', name : 'STATUS', width : 100,  align: 'center',sortable: true,						
							process:function(obj,pid)
							{
								if ($(obj).html() == "0") 
								{
								   $(obj).html("正常");
								   $(obj).css({color:"blue"});
								}
								else 
								{
									$(obj).html("被冻结");
									$(obj).css({color:"red"});								
								}
						    }					
						},						
						{display: '业务员角色', name : 'AUTH', width : 100, align: 'center',sortable: true},
						{display: '部门名称', name : 'ORG_NAME', width : 150, align: 'center',sortable: true},
						{display: '部门id', name : 'ORG_ID', width : 40, align: 'center',hide : true},
						{display: '所辖区域', name : 'PROVINCE', width : 320, align: 'center'}
					],
					buttons : queryP,
					rowId:"id",
					showToggleBtn: true,
					usepager: true,
					title:"参数列表",
					rp:page_num,
					showTableToggleBtn: false,
					resizable: false,
					height: paramGridHeight_area,
					singleSelect: true,
					onChangePage:area.loadData,
					checkbox:true,
					filterParams : [{key : "username", name : "#area_username"},
					                {key : "truename", name : "#area_truename"},
					                {key : "department", name : "#area_department"}]
				});
			},

			loadData:function(param){
				var queryP = {};
				queryP.username = $('#area_username').val()==null?'':$('#area_username').val();
				queryP.truename = $('#area_truename').val()==null?'':$('#area_truename').val();
				queryP.department = $('#area_department').val()==null?'':$('#area_department').val();
				var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
				queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
				queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
				queryP.sortname = param.sortname? param.sortname : "";
				queryP.sortorder = param.sortorder? param.sortorder : "";
				$.ajax({
					type: "POST",
					url: "salesman!areaDistribute.action?operation=init",
					cache: false,
					data: queryP,
					success: function(msg){
						var data=eval("("+msg+")");
						try{
							param_grid_area.flexAddData(data);
						}catch(e){
							showErrorMsg("查询参数信息出错！");
						}
					}
				}); 
			},
			
			areadistribute:function(com,grid){
				if($('.trSelected', grid).length==1){
					var dlgParent = $("#dlg_area_distribute").parent();
			        var dlgOwn = $("#dlg_area_distribute").clone();
			        dlg_area_distribute = $("#dlg_area_distribute").dialog({
						autoOpen: false,
						height:400,
						width:500,
						show: "slide",
						modal:true,
						cache:false,
						/* hide: "explode", */
						title:'区域分配',
						buttons:{
							"确定":function(){
								area.saveParam();
							},
							"取消" : function() {
								dlgOwn.appendTo(dlgParent);
				            	$(this).dialog("destroy").remove();
							}
						},
			            close : function() {
			            	dlgOwn.appendTo(dlgParent);
			            	$(this).dialog("destroy").remove();
			            }
					});
			        var adminid = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
			        $("#area_distribute_id").val(adminid);
			        $.ajax({
			            type:"get",
			            dataType:"text",
			            async:false,
			            url:"salesman!supportAreaList.action",
			            success:function(result) {
			            	var data=eval("("+result+")");
							var html = '';
							for (var i in data) {
								html += '<option value="'+data[i].ID+'">'+data[i].PROVINCE+'</option>';
							}
							$("#department_list1").append(html);
			            }
			        });
			        $.ajax({
			            type:"get",
			            data:{"adminid" : adminid},
			            async:false,
			            url:"salesman!choosedAreaList.action",
			            success:function(result) {
			            	var data=eval("("+result+")");
							var html = '';
							for (var i in data) {
								html += '<option value="'+data[i].ID+'">'+data[i].PROVINCE+'</option>';
							}
							$("#department_list2").append(html);
			            }
			        });
			        dlg_area_distribute.dialog("open");
				}else if($('.trSelected', grid).length>1)
				{
				    $('#dlg_area_distribute').dialog('close');
					showMsg("不能同时选择多行记录！");
				}else
				{
				    $('#dlg_area_distribute').dialog('close');
					showMsg("未选中行！");
				}
			},

			 saveParam:function(){
				 var provinceid ="";
				 var province ="";
				 for(var i=0;i<document.area.department_list2.options.length;i++){
					 provinceid += document.area.department_list2.options[i].value+",";
					 province += document.area.department_list2.options[i].text+",";
				 }
				 var params=new Object();
				 params.provinceid = provinceid;
				 params.province = province;
				 params.id = $("#area_distribute_id").val();
				 $.ajax({
					type: "POST",
					url: "salesman!updateUserArea.action",
					cache: false,
					data: params,
					success: function(msg){
						var result=eval("("+msg+")");
						if (result.msg=="ok") 
						{
							showMsg("区域分配完成！线上用户的业务员将重新分配！");
							$('#dlg_area_distribute').dialog('close');
							area.loadData({});
						} else 
						{
							showMsg(result.msg);
						}
					}
				}); 
			 },
			 //区域分配情况
			 show_area_distribute:function(){
				var dlgParent = $("#show_area_distribute").parent();
		        var dlgOwn = $("#show_area_distribute").clone();
		        show_area_distribute = $("#show_area_distribute").dialog({
					autoOpen: false,
					height:400,
					width:450,
					show: "slide",
					modal:true,
					cache:false,
					/* hide: "explode", */
					title:'区域分配情况',
					buttons:{
						"知道了" : function() {
							dlgOwn.appendTo(dlgParent);
			            	$(this).dialog("destroy").remove();
						}
					},
		            close : function() {
		            	dlgOwn.appendTo(dlgParent);
		            	$(this).dialog("destroy").remove();
		            }
				});
		        $.ajax({
		            type:"get",
		            dataType:"text",
		            async:false,
		            url:"salesman!UserAreaList.action",
		            success:function(result) {
		            	var data=eval("("+result+")");
						var html = '';
						for (var i in data) {
							if(data[i].USERNAME == null){
								html += '<option>'+data[i].PROVINCE+"(待定)"+'</option>';
							}else{
								html += '<option>'+data[i].PROVINCE+"("+data[i].USERNAME+")"+'</option>';
							}
						}
						$("#department_list").append(html);
		            }
		        });
		        show_area_distribute.dialog("open");
			 },
			 
			 show_webuser:function(com,grid){
	            if($('.trSelected', grid).length==1)
				{		
			       var salesmanid = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
			       var option = {};
			       option.key = 500;
			       option.title = "线上用户管理";
			       option.url = "../custom!queryWebUser.action?salesmanid="+salesmanid;
			       option.isIframe = false;
			       addTab(option);	
			       
				}else if($('.trSelected', grid).length>1)
				{
					showMsg("不能同时选择多行记录！");
				}else
				{
					showMsg("未选中行！");
				}
			 }
		};
		 
 
	 $(function(){
		//初始化所有操作按钮
		 var  parm_button = [{name: '区域分配', bclass: 'distribute', onpress : area.areadistribute},
							{name: '区域分配情况', bclass: 'search', onpress : area.show_area_distribute},
							{name: '所辖渲云用户', bclass: 'search', onpress : area.show_webuser}];
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
				var data=eval("("+data+")");
				for (var i in data.buttons) {
					var buttonName = data.buttons[i].BUTTON_NAME;
					$(parm_button).each(function(index){
						var val = parm_button[index].name;
						if(val == buttonName){
							buttons.push(parm_button[index]);
							buttons.push({separator: true});
						}
					});
				}
	 			// 初始化数据
				area.bindAreaGrid(buttons);
			}
		});
	
		 $.ajax({
	            type:"get",
	            dataType:"text",
	            async:false,
	            url:"salesman!departmentList.action",
	            success:function(result) {
	            	var data=eval("("+result+")");
					var html = '';
					for (var i in data) {
						if(data[i].ORG_NAME!="销售部"){
							html += '<option value="'+data[i].ORG_ID+'">'+data[i].ORG_NAME+'</option>';
						}
					}
					$("#area_department").append(html);
	            }
	        });
		/*参数管理-查询按钮功能*/
		$('#btn_area_search').on("click",function(){
			area.loadData({page:1});
		});

		//清空筛选框
		$('#btn_area_empty').on("click",function(){
			$("#area_username").val("");
			$("#area_truename").val("");
			$("#area_department").val("");
		});
	});
	 
	 function moveOption(list1,list2){
		 var options = list1.options;
		 for(var i=0;i<options.length;i++){
			 if(options[i].selected){
				 list2.appendChild(options[i]);
				 i--;
			 }
		 }
	 }
		
	</script>
	</body>
</html>
