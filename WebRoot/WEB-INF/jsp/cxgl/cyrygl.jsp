 <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 <script type="text/javascript">
var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
var page_num = $(window.self.document).find("#page_num").val();
var page_num =30;


function showInfo(rowData){
						var dlg_cyrygl_xm = rowData.NAME;
						var dlg_cyrygl_xb = rowData.SEX;
						var dlg_cyrygl_xl = rowData.EDUCATION;
						var dlg_cyrygl_zy = rowData.MAJOR;
						var dlg_cyrygl_zc = rowData.TITLE;
						var dlg_cyrygl_gzdw = rowData.WORKUNIT;
						$("#dlg_cyrygl_xm").val(dlg_cyrygl_xm);
						$("#dlg_cyrygl_xb").val(dlg_cyrygl_xb);
						$("#dlg_cyrygl_xl").val(dlg_cyrygl_xl);
						$("#dlg_cyrygl_zy").val(dlg_cyrygl_zy);
						$("#dlg_cyrygl_zc").val(dlg_cyrygl_zc);
						$("#dlg_cyrygl_gzdw").val(dlg_cyrygl_gzdw);
						
						
						var dlgParent = $("#dlg_cyrygl").parent();
				        var dlgOwn = $("#dlg_cyrygl").clone();
				        var dlg_cyrygl_ck = 
					    $("#dlg_cyrygl").dialog({
					    	title:"查看",
					    	autoOpen: true,
					    	height:$(window).height()*0.8,
							width:$(window).width()*0.5,
							open: function (event, ui) {
								 $(".ui-dialog-titlebar-close", $(this).parent()).hide();
							},
					    	buttons:{
								"关闭": function() {
									clean_input_for_dailog($("#dlg_cyrygl"));
			         				dlgOwn.appendTo(dlgParent);
			         				$(this).dialog("destroy").remove();
			         			}
							}
					    });
					     dlg_cyrygl_ck.dialog("open");
}




var	dlg_cyrygl = function(data){
	dlg_cyrygl_dialog = $("#dlg_cyrygl").dialog({
					autoOpen: false,
					height:$(window).height()*0.8,
					width:$(window).width()*0.5,
					modal:true,
					cache:false,
					title:data.com,
					open: function (event, ui) {
						 $(".ui-dialog-titlebar-close", $(this).parent()).hide();
					},
					buttons:{
						"保存":function() {
							cyrygl_grid.saveData(data.operation);
						},
						"关闭": function() {
							clean_input_for_dailog($("#dlg_cyrygl"));
							//console.log(dlgParent.attr("id"));
							//$(this).dialog("destroy").remove();
							//dlgOwn.appendTo(dlgParent);
	         				$(this).dialog("close");
	         				
	         			}
					}
				});
				
				dlg_cyrygl_load = $("#dlg_cyrygl_upload").dialog({
					autoOpen: false,
					height:600,
					width:800,
					modal:true,
					cache:false,
					title:data.com,
					open: function (event, ui) {
						 $(".ui-dialog-titlebar-close", $(this).parent()).hide();
					},
					buttons:{
						"保存":function() {
							cyrygl_grid.saveData(data.operation);
					
						},
						"关闭": function() {
							clean_input_for_dailog($("#dlg_cyrygl_upload"));
							//console.log(dlgParent.attr("id"));
							//$(this).dialog("destroy").remove();
							//dlgOwn.appendTo(dlgParent);
	         				$(this).dialog("close");
	         			}
					}
				});
				
  
				
};

 var cyrygl_grid = {
	//绑定参数信息grid
	cyrygl_grid_result:"",
	dlg_cyrygl:"",
	cyryglGridHeight:flexHeight("cyrygl_grid"),
	bindParamGrid:function(queryP,queryM){
		cyrygl_grid.cyrygl_grid_result = $("#cyrygl_grid").flexigrid({
			url:"cxgl!getCyryglList.action?operation=init",
			colModel: queryM,
			buttons : queryP,
			rowId:"id",
			showToggleBtn: true,
			usepager: true,
			title:"从业人员列表",
			//rp:pageSize,
			onRowDblclick:showInfo,
			rp:page_num,
			showTableToggleBtn: false,
			resizable: false,
			height: cyrygl_grid.cyryglGridHeight,
			singleSelect: true,
			onChangePage:cyrygl_grid.loadData,
			checkbox:false,
			filterParams : [{key : "name", name : "#serch_xm_cyrygl"},
			                {key : "education", name : "#serch_xl_cyrygl"},
			                {key : "major", name : "#serch_zy_cyrygl"}, 
			                {key : "title", name : "#serch_zc_cyrygl"},
			                {key : "workUnit", name : "#serch_gzdw_cyrygl"}
			               ]
		});
	},			
		// 查询子任务并且显示
	showDialog : function(com,operation,divobj,h,w) 
		{
		var dlgParent = divobj.parent();
		var dlgOwn = divobj.clone();
		cyrygl_grid.dlg_cyrygl = divobj.dialog({
				autoOpen: false,
				height:$(window).height()*0.8,
				width:$(window).width()*0.5,
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
						cyrygl_grid.saveData(operation);
		              	//$(this).dialog("destroy").remove();
					}
				},
			    close : function() {
		              	dlgOwn.appendTo(dlgParent);
		              	$(this).dialog("destroy").remove();
		         }
			});
			
		cyrygl_grid.dlg_cyrygl.dialog("option", "title", com).dialog("open");
	},
	add:function(com,grid)
	{
		
		if (com == "新建") 
				{
				    cyrygl_grid.showDialog("新增从业人员","add",$("#dlg_cyrygl"));
					$("#action").val("add");
					
				}
	},
	del:function(com,grid)
	{
		
		if (com == "删除") 
		{
		   	if($('.trSelected', grid).length==1)
			{	
					       var id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
					       var title = $('.trSelected', grid).eq(0).find("td:eq(2)").text();
					        var indicate = "确认需要删除" + title+ ",这个规划项目吗?";	
					        var params=new Object();
				                params.id = id;					
							if(confirm(indicate)) 
							{
								$.ajax({
									type: "POST",
									url: "cxgl!deleteCyrygl.action",
									cache: false,
									data: params,
									success: function(msg)
									{
										var result=eval("("+msg+")");
										if (result.msg=="ok") 
										{
											showMsg("删除成功！");
											$('#dlg_cyrygl').dialog('close');
											cyrygl_grid.loadData({page:1});
										} else 
										{
											showMsg(result.msg);
											$('#dlg_cyrygl').dialog('close');
										}
									}
								}); 
							}else
							{
								$('#dlg_cyrygl').dialog('close');
							}
						
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_cyrygl').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_cyrygl').dialog('close');
						showMsg("未选中行！");
					}
				}			
	},
	edit:function(com,grid)
	{
		
                if (com == "修改") 
				{
						
					if($('.trSelected', grid).length==1)
					{
					   cyrygl_grid.showDialog("修改从业人员","edit",$("#dlg_cyrygl"));
				       $("#action").val("edit");
						var id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
						var dlg_cyrygl_xm = $('.trSelected', grid).eq(0).find("td:eq(2)").text();
						var dlg_cyrygl_xb = $('.trSelected', grid).eq(0).find("td:eq(3)").text();
						var dlg_cyrygl_xl = $('.trSelected', grid).eq(0).find("td:eq(4)").text();
						var dlg_cyrygl_zy = $('.trSelected', grid).eq(0).find("td:eq(5)").text();
						var dlg_cyrygl_zc = $('.trSelected', grid).eq(0).find("td:eq(5)").text();
						var dlg_cyrygl_gzdw = $('.trSelected', grid).eq(0).find("td:eq(5)").text();
						$("#dlg_id").val(id);	
						$("#dlg_cyrygl_xm").val(dlg_cyrygl_xm);
						$("#dlg_cyrygl_xb").val(dlg_cyrygl_xb);
						$("#dlg_cyrygl_xl").val(dlg_cyrygl_xl);
						$("#dlg_cyrygl_zy").val(dlg_cyrygl_zy);
						$("#dlg_cyrygl_zc").val(dlg_cyrygl_zc);
						$("#dlg_cyrygl_gzdw").val(dlg_cyrygl_gzdw);
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_mzpc').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_mzpc').dialog('close');
						showMsg("未选中行！");
					}
					
				}
	},
	saveData:function(com)
	{
				if(com == "import")	           
	          {
	          		var queryP = {};
					queryP.nameType = "cyryglInfo";
					queryP.suffix = $.trim($("#file_name_cyrygl").val()).split("\.")[1];
					if(queryP.suffix === '' || queryP.suffix === undefined || typeof queryP.suffix === 'undefined' )
						 {
							showMsg("未选择需要导入的文件！");
							return;
						 }
					$.ajaxFileUpload({
						url:"upload!importExcel.action?radom="+Math.random(),
						fileElementId:"file_name_cyrygl",
						secureuri:false,
						type:"POST",
						data:queryP,
						dataType:"json",
						success:function(data)
						{
							if(data != null && data != "" && data.result == "00")
							{
								showMsg("从业人员导管理入成功！");
								cyrygl_grid.loadData({page:1});
								$("#dlg_cyrygl_upload").dialog('close');
							}else{
								showMsg("从业人员管理导入失败！");
							}
						},
						error:function(){
							showMsg("文件导入失败");
						}
					});

	          }else{
	          var params=new Object();
				params.name = $.trim($("#dlg_cyrygl_xm").val());
				params.sex = $.trim($("#dlg_cyrygl_xb").val());
				params.education = $.trim($("#dlg_cyrygl_xl").val());
				params.major  = $.trim($("#dlg_cyrygl_zy").val());
				params.title  = $.trim($("#dlg_cyrygl_zc").val());
				params.workUnit  = $.trim($("#dlg_cyrygl_gzdw").val());
			    // 提交方式
				   var action = $("#action").val();	
				//新增的时候才判断密码，修改的时候不需要
					if(params.name==null || params.name=='')
					{
						showMsg("姓名不能为空！");
						$("#dlg_cyrygl_xm").focus();
						return;
					}
					if(params.sex==null || params.sex=='')
					{
						showMsg("性别不能为空！");
						$("#dlg_cyrygl_xb").focus();
						return;
					}
					if(params.education==null || params.education=='')
					{
						showMsg("学历不能为空！");
						$("#dlg_cyrygl_xl").focus();
						return;
					}
					if(params.major==null || params.major=='')
					{
						showMsg("专业不能为空！");
						$("#dlg_cyrygl_zy").focus();
						return;
					}
					if(params.title==null || params.title=='')
					{
						showMsg("职称不能为空！");
						$("#dlg_cyrygl_zc").focus();
						return;
					}
					if(params.workUnit==null || params.workUnit=='')
					{
						showMsg("工作单位不能为空！");
						$("#dlg_cyrygl_gzdw").focus();
						return;
					}
								
				// 新增和修改通用字符串
				var url ='';
				var indicate='';
				if ("add" == action) 
				{
					indicate = "确认新增" + params.name + ",这个从业人员?";
					url = "cxgl!saveCyrygl.action";
				} 
				else if ("edit" == action) 
				{
					params.id = $("#dlg_id").val();
					indicate = "确认更新" + params.name + ",这个从业人员?";
					url = "cxgl!updateCyrygl.action";
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
								clean_input_for_dailog($("#dlg_cyrygl"));
								$('#dlg_cyrygl').dialog('close');
								cyrygl_grid.loadData({page:1});
							} else 
							{
								showMsg(result.msg);
							}
						}
					}); 
				}
				else
				{
				    $('#dlg_cyrygl').dialog('close');
				}
	          }
			    
		
	},
	// 导出数据
	commitExport : function() 
	{			
				var condition = "";
				var serch_xm_cyrygl=$("#serch_xm_cyrygl")==null?'':$('#serch_xm_cyrygl').val();
				if (serch_xm_cyrygl && $.trim(serch_xm_cyrygl).length > 0) 
				{
					condition += "&name=" + encodeURI(encodeURI($.trim(serch_xm_cyrygl)));
				}
				var serch_xl_cyrygl=$("#serch_xl_cyrygl")==null?'':$('#serch_xl_cyrygl').val();
				if (serch_xl_cyrygl && $.trim(serch_xl_cyrygl).length > 0) 
				{
					condition += "&education=" + encodeURI(encodeURI($.trim(serch_xl_cyrygl)));
				}
				var serch_zy_cyrygl=$("#serch_zy_cyrygl")==null?'':$('#serch_zy_cyrygl').val();
				if (serch_zy_cyrygl && $.trim(serch_zy_cyrygl).length > 0) 
				{
					condition += "&major=" + encodeURI(encodeURI($.trim(serch_zy_cyrygl)));
				}
				var serch_zc_cyrygl=$("#serch_zc_cyrygl")==null?'':$('#serch_zc_cyrygl').val();
				if (serch_zc_cyrygl && $.trim(serch_zc_cyrygl).length > 0) 
				{
					condition += "&title=" + encodeURI(encodeURI($.trim(serch_zc_cyrygl)));
				}
				var serch_gzdw_cyrygl=$("#serch_gzdw_cyrygl")==null?'':$('#serch_gzdw_cyrygl').val();
				if (serch_gzdw_cyrygl && $.trim(serch_gzdw_cyrygl).length > 0) 
				{
					condition += "&workUnit=" + encodeURI(encodeURI($.trim(serch_gzdw_cyrygl)));
				}
				location.href='cxgl!getExportDara_cyrygl.action?'+condition;
			
   },
    importcyrygl:function(name,grid)
   	{
		//showMsg("车辆信息批量导入");
		cyrygl_grid.showDialog("从业人员导入","import", $("#dlg_cyrygl_upload"),500,400);
	},
   mydownload:function(path)
   {
   		
        location.href='upload!download.action?&path='+ encodeURI(encodeURI(unescape(path))); 
   },
	loadData:function(param){
		var queryP = {};
		queryP.name = $('#serch_xm_cyrygl').val()==null?'':$('#serch_xm_cyrygl').val();
		queryP.education = $('#serch_xl_cyrygl').val()==null?'':$('#serch_xl_cyrygl').val();
		queryP.major = $('#serch_zy_cyrygl').val()==null?'':$('#serch_zy_cyrygl').val();
		queryP.title = $('#serch_zc_cyrygl').val()==null?'':$('#serch_zc_cyrygl').val();
		queryP.workUnit = $('#serch_gzdw_cyrygl').val()==null?'':$('#serch_gzdw_cyrygl').val();
		var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
		queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
		queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
		queryP.sortname = param.sortname? param.sortname : "";
		queryP.sortorder = param.sortorder? param.sortorder : "";
		$.ajax({
			type: "POST",
			url: "cxgl!getCyryglList.action?operation=init",
			cache: false,
			data: queryP,
			success: function(msg){
				var data=eval("("+msg+")");
				try{
					cyrygl_grid.cyrygl_grid_result.flexAddData(data);
				}catch(e){
					showErrorMsg("查询结果信息出错！");
				}
			}
		}); 
	}
};

	function downloadModel(obj)
	{
	    if(obj != null && obj != "")
		window.location.href = "upload!exportExcelModelForTable.action?suffix="+obj+"&nameType=cyryglInfo"+"&radom="+Math.random();
     }
	
	 $(function(){
		//初始化所有操作按钮
		var  parm_button = [
			  {name: '新建', bclass: 'add', onpress : cyrygl_grid.add},
			  {name: '修改', bclass: 'edit', onpress : cyrygl_grid.edit},
			  {name: '删除', bclass: 'delete', onpress : cyrygl_grid.del},
			  {name: '导入', bclass: 'distribute', onpress : cyrygl_grid.importcyrygl},
			  {name: '导出', bclass: 'excel', onpress : cyrygl_grid.commitExport}
		 ];
		 //权限操作按钮
		 var colModel = [
						{display: '序号', name : 'xh', width : 40, align: 'center'},
						{display: 'id', name : 'ID', width : 40, align: 'center',hide : true,sortable: true},
						{display: '姓名', name : 'NAME', width : 150,  align: 'left'},
						{display: '性别', name : 'SEX', width : 150,  align: 'left'},
						{display: '学历', name : 'EDUCATION', width : 200, align: 'left'},
						{display: '专业', name : 'MAJOR', width : 150,  align: 'left'},
						{display: '职称', name : 'TITLE', width : 150,  align: 'left'},
						{display: '工作单位', name : 'WORKUNIT', width : 150,  align: 'left'}
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
				cyrygl_grid.bindParamGrid(buttons,colModel);
			}
		});
		 
		 
		/*参数管理-查询按钮功能*/
		$("#btn_cyrygl_search_cyrygl").button().on("click",function(){
			cyrygl_grid.loadData({page:1});
		});
				//清空筛选框
		$('#btn_result_empty_cyrygl').on("click",function(){
			$("#serch_xm_cyrygl").val("");
			$("#serch_xl_cyrygl").val("");
			$("#serch_zy_cyrygl").val("");
			$("#serch_zc_cyrygl").val("");
			$("#serch_gzdw_cyrygl").val("");
			cyrygl_grid.loadData({page:1});
		});
		
	});
</script>
<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='../images/fold.png' onclick="showAndHide(this,'cyrygl_grid');"  class="imgShrotate">
				<font>查询条件</font>
			</h3>
		</div>
	<div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>

					<td class="wi-align-l">
						<label for="input_1" class="lbl-1">
							姓名:
						</label>
						<input type="text" id="serch_xm_cyrygl" name="serch_xm_cyrygl" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<label for="input_1" class="lbl-1">
							学历:
						</label>
						<input type="text" id="serch_xl_cyrygl" name="serch_xl_cyrygl" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<label for="input_1" class="lbl-1">
							专业:
						</label>
						<input type="text" id="serch_zy_cyrygl" name="serch_zy_cyrygl" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<label for="input_1" class="lbl-1">
							职称：
						</label>
						<input type="text" id="serch_zc_cyrygl" name="serch_zc_cyrygl" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<label for="input_1" class="lbl-1">
							工作单位：
						</label>
						<input type="text" id="serch_gzdw_cyrygl" name="serch_gzdw_cyrygl" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<button id="btn_cyrygl_search_cyrygl" class="ui-state-default ui-state-qik">查询
						</button>
						<button id="btn_result_empty_cyrygl" class="ui-state-default ui-state-qik">清空
						</button>
					</td>
				</tr>
			</table>
		</div>
</div>
<div class="div div-2" style="">
	<table id="cyrygl_grid" style="display:none;padding:0 0 0 0;"></table>
</div>
<div id="dlg_cyrygl" style="display:none;overflow-y: hidden;">
	<input type="hidden" id="action" />
	<input type="hidden" id="dlg_id" /> 
	<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">姓名：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_cyrygl_xm" name="dlg_cyrygl_xm"  class="wi-ipt-1" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">性别：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_cyrygl_xb" value="" class="wi-ipt-1" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">学历：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_cyrygl_xl" value="" class="wi-ipt-1"/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">专业：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_cyrygl_zy" value="" class="wi-ipt-1"/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">职称：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_cyrygl_zc" value="" class="wi-ipt-1"/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">工作单位：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_cyrygl_gzdw" value="" class="wi-ipt-1"/>
			</td>
		</tr>
	</table>
	<div style="overflow: hidden">
		
	</div>
</div>
<div id="dlg_cyrygl_upload" style="display:none;overflow-y: hidden;">
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
				<a href="#" onclick="downloadModel('xls');"><img alt="2003EXCEL模板下载" src="../images/excel2.jpg" style="width: 16px;height: 16px;"></a>
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<label style="color:red;">
					2007EXCEL模板下载:
				</label>
				<label>
				<a href="#" onclick="downloadModel('xlsx');"><img alt="2007EXCEL模板下载" src="../images/excel2.jpg" style="width: 16px;height: 16px;"></a>
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<input type="file" id="file_name_cyrygl" name="file_name_cyrygl" accept=".xls,.xlsx"/>
			</td>
		</tr>
	</table>
</div>	