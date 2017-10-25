<%@ page contentType="text/html; charset=UTF-8"%>
<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='../images/fold.png' onclick="showAndHide(this,'project_grid');"  class="imgShrotate">
				<font>规划项目</font>
			</h3>
		</div>
	<div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>
					<td >
						<label for="input_1" class="lbl-1">
							项目名称:
						</label>
						<input type="text" id="serch_project_name" name="serch_project_name" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l" >
						<label for="input_1" class="lbl-1">
							建设单位:
						</label>
						<input type="text" id="serch_jsdw" name="serch_jsdw" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<button id="btn_project_search" class="ui-state-default ui-state-qik">查询
						</button>					
						<button id="btn_project_empty" class="ui-state-default ui-state-qik">清空
						</button>
					</td>
				</tr>
			</table>
		</div>
</div>
<div class="div div-2" style="">
	<table id="project_grid" style="display:none;padding:0 0 0 0;"></table>
</div>
<div id="dlg_project" style="display:none;overflow-y: auto;">
	<input type="hidden" id="action" />
	<input type="hidden" id="dlg_id" /> 
	<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">项目名称：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_PROJECT_NAME" name="dlg_PROJECT_NAME"  class="wi-ipt-1" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">施置范围：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_SZFW" value="" class="wi-ipt-1" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">建设单位：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_JSDW" value="" class="wi-ipt-1"/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">概规或城市设计：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<select name="dlg_CSSJ" id="dlg_CSSJ" class="wi-sel-1" style="width:200px">
						<option value="已完成">已完成</option>
						<option value="未完成">未完成</option>
				</select>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">控规：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<select name="dlg_KG" id="dlg_KG" class="wi-sel-1" style="width:200px">
						<option value="已审批">已审批</option>
						<option value="未审批">未审批</option>
				</select>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">选址意见书：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<select name="dlg_XZYJS" id="dlg_XZYJS" class="wi-sel-1" style="width:200px">
						<option value="已出具">已出具</option>
						<option value="未出具">未出具</option>
				</select>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">总平面方案：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<select name="dlg_ZPMFA" id="dlg_ZPMFA" class="wi-sel-1" style="width:200px">
						<option value="已审定">已审定</option>
						<option value="未审定">未审定</option>
				</select>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">用地证：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<select name="dlg_YDZ" id="dlg_YDZ" class="wi-sel-1" style="width:200px">
						<option value="已核发">已核发</option>
						<option value="未核发">未核发</option>
				</select>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">工程证：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<select name="dlg_GCZ" id="dlg_GCZ" class="wi-sel-1" style="width:200px">
						<option value="已核发">已核发</option>
						<option value="未核发">未核发</option>
				</select>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">批后管理：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<select name="dlg_PHGL" id="dlg_PHGL" class="wi-sel-1" style="width:200px">
						<option value="已纳入">已纳入</option>
						<option value="未纳入">未纳入</option>
				</select>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">竣工核实：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<select name="dlg_JGHS" id="dlg_JGHS" class="wi-sel-1" style="width:200px">
						<option value="已完成">已完成</option>
						<option value="核实中">核实中</option>
				</select>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">责任部门：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
                  <input type="text" id="dlg_ZRBM" value="" class="wi-ipt-1"/>
			</td>
		</tr>
	</table>
</div>	

<div id="dlg_project_upload" style="display:none;overflow-y: hidden;">
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
				<input type="file" id="file_name_project" name="file_name_project" accept=".xls,.xlsx"/>
			</td>
		</tr>
	</table>
</div>
<script>		
var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
var page_num = $(window.self.document).find("#page_num").val();
var page_num =30;
var dlg_project_dialog;

function showInfo(rowData){
						var PROJECT_NAME = rowData.PROJECT_NAME;
						var SZFW = rowData.SZFW;
						var JSDW = rowData.JSDW;
						var CSSJ = rowData.CSSJ;
						var KG = rowData.KG;
						var XZYJS = rowData.XZYJS;
						var ZPMFA = rowData.ZPMFA;
						var YDZ = rowData.YDZ;
						var GCZ = rowData.GCZ;
						var PHGL = rowData.PHGL;
						var JGHS =rowData.JGHS;
						var ZRBM = rowData.ZRBM;
						$("#dlg_PROJECT_NAME").val(PROJECT_NAME);
						$("#dlg_SZFW").val(SZFW);
						$("#dlg_JSDW").val(JSDW);
						$("#dlg_CSSJ option[value='"+CSSJ+"']").attr("selected", true);
						$("#dlg_KG option[value='"+KG+"']").attr("selected", true);
						$("#dlg_XZYJS option[value='"+XZYJS+"']").attr("selected", true);
						$("#dlg_ZPMFA option[value='"+ZPMFA+"']").attr("selected", true);
						$("#dlg_YDZ option[value='"+YDZ+"']").attr("selected", true);
                        $("#dlg_GCZ option[value='"+GCZ+"']").attr("selected", true);
                        $("#dlg_PHGL option[value='"+PHGL+"']").attr("selected", true);
                        $("#dlg_JGHS option[value='"+JGHS+"']").attr("selected", true);	
						$("#dlg_ZRBM").val(ZRBM);	
						
						
						var dlgParent = $("#dlg_project").parent();
				        var dlgOwn = $("#dlg_project").clone();
				        var dlg_project_ck =
					    $("#dlg_project").dialog({
					    	title:"查看",
					    	autoOpen: true,
					    	height:$(window).height()*0.8,
							width:$(window).width()*0.5,
							open: function (event, ui) {
								 $(".ui-dialog-titlebar-close", $(this).parent()).hide();
							},
					    	buttons:{
								"关闭": function() {
									dlgOwn.appendTo(dlgParent);
									clean_input_for_dailog($("#dlg_project"));
			         				//$(this).dialog("close");
		              				$(this).dialog("destroy").remove();
			         			}
							}
					    });
					    dlg_project_ck.dialog("open");
}


//ie8下面的弹窗必须改成这样的方式
var	dlg_project = function(data){
	dlg_project_dialog = $("#dlg_project").dialog({
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
							project_grid.saveData(data.operation);
							clean_input_for_dailog($("#dlg_project"));
						},
						"关闭": function() {
							clean_input_for_dailog($("#dlg_project"));
							//console.log(dlgParent.attr("id"));
							//$(this).dialog("destroy").remove();
							//dlgOwn.appendTo(dlgParent);
	         				$(this).dialog("close");
	         			}
					}
				});
				
			dlg_project_load = $("#dlg_project_upload").dialog({
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
							file_grid.saveData(data.operation);
					
						},
						"关闭": function() {
							clean_input_for_dailog($("#dlg_project_upload"));
							//console.log(dlgParent.attr("id"));
							//$(this).dialog("destroy").remove();
							//dlgOwn.appendTo(dlgParent);
	         				$(this).dialog("close");
	         			}
					}
				});	
				
};
var project_grid = {
	//绑定参数信息grid
	project_grid_result:"",
	GridHeight:flexHeight("project_grid"),
	bindParamGrid:function(queryP,queryM){
		project_grid.project_grid_result = $("#project_grid").flexigrid({
			url:"plann!projectList.action?operation=init",
			colModel: queryM,
			buttons : queryP,
			rowId:"id",
			showToggleBtn: true,
			usepager: true,
			title:"规划项目列表",
			//rp:pageSize,
			onRowDblclick:showInfo,
			rp:page_num,
			showTableToggleBtn: false,
			resizable: false,
			height: project_grid.GridHeight,
			singleSelect: true,
			onChangePage:project_grid.loadData,
			checkbox:false,
			filterParams : [{key : "project_name", name : "#serch_project_name"},
			                {key : "jsdw", name : "#serch_jsdw"}]
		});
	},			
		// 查询子任务并且显示
	showDialog : function(com,operation,divobj,h,w) 
		{
		var dlgParent = divobj.parent();
		var dlgOwn = divobj.clone();
		project_grid.dlg_project = divobj.dialog({
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
						project_grid.saveData(operation);
		              	//$(this).dialog("destroy").remove();
					}
				},
			    close : function() {
		              	dlgOwn.appendTo(dlgParent);
		              	$(this).dialog("destroy").remove();
		         }
			});
			
		project_grid.dlg_project.dialog("option", "title", com).dialog("open");
	},	
	add:function(com,grid)
	{
		
		if (com == "新建") 
				{
				    project_grid.showDialog("新增规划项目","add", $("#dlg_project"));
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
									url: "plann!delproject.action",
									cache: false,
									data: params,
									success: function(msg)
									{
										var result=eval("("+msg+")");
										if (result.msg=="ok") 
										{
											showMsg("删除成功！");
											$('#dlg_project').dialog('close');
											project_grid.loadData({page:1});
										} else 
										{
											showMsg(result.msg);
											$('#dlg_project').dialog('close');
										}
									}
								}); 
							}else
							{
								$('#dlg_project').dialog('close');
							}
						
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_project').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_project').dialog('close');
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
					   project_grid.showDialog("修改规划项目","edit", $("#dlg_project"));
				       $("#action").val("edit");
						var id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
						var PROJECT_NAME = $('.trSelected', grid).eq(0).find("td:eq(2)").text();
						var SZFW = $('.trSelected', grid).eq(0).find("td:eq(3)").text();
						var JSDW = $('.trSelected', grid).eq(0).find("td:eq(4)").text();
						var CSSJ = $('.trSelected', grid).eq(0).find("td:eq(5)").text();
						var KG = $('.trSelected', grid).eq(0).find("td:eq(6)").text();
						var XZYJS = $('.trSelected', grid).eq(0).find("td:eq(7)").text();
						var ZPMFA = $('.trSelected', grid).eq(0).find("td:eq(8)").text();
						var YDZ = $('.trSelected', grid).eq(0).find("td:eq(9)").text();
						var GCZ = $('.trSelected', grid).eq(0).find("td:eq(10)").text();
						var PHGL = $('.trSelected', grid).eq(0).find("td:eq(11)").text();
						var JGHS = $('.trSelected', grid).eq(0).find("td:eq(12)").text();
						var ZRBM = $('.trSelected', grid).eq(0).find("td:eq(13)").text();
						$("#dlg_id").val(id);	
						$("#dlg_PROJECT_NAME").val(PROJECT_NAME);
						$("#dlg_SZFW").val(SZFW);
						$("#dlg_JSDW").val(JSDW);
						$("#dlg_CSSJ option[value='"+CSSJ+"']").attr("selected", true);
						$("#dlg_KG option[value='"+KG+"']").attr("selected", true);
						$("#dlg_XZYJS option[value='"+XZYJS+"']").attr("selected", true);
						$("#dlg_ZPMFA option[value='"+ZPMFA+"']").attr("selected", true);
						$("#dlg_YDZ option[value='"+YDZ+"']").attr("selected", true);
                        $("#dlg_GCZ option[value='"+GCZ+"']").attr("selected", true);
                        $("#dlg_PHGL option[value='"+PHGL+"']").attr("selected", true);
                        $("#dlg_JGHS option[value='"+JGHS+"']").attr("selected", true);	
						$("#dlg_ZRBM").val(ZRBM);			
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_project').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_project').dialog('close');
						showMsg("未选中行！");
					}
				}
	},
	saveData:function(operation)
	{
	             if(operation == "import")	           
	                 {
	          		var queryP = {};
					queryP.nameType = "ghxm";
					queryP.suffix = $.trim($("#file_name_project").val()).split("\.")[1];
					if(queryP.suffix === '' || queryP.suffix === undefined || typeof queryP.suffix === 'undefined' )
						 {
							showMsg("未选择需要导入的文件！");
							return;
						 }
					$.ajaxFileUpload({
						url:"upload!importExcel.action?radom="+Math.random(),
						fileElementId:"file_name_project",
						secureuri:false,
						type:"POST",
						data:queryP,
						dataType:"json",
						success:function(data)
						{
							if(data != null && data != "" && data.result == "00")
							{
								showMsg("规划项目导入成功！");
								project_grid.loadData({page:1});
								$("#dlg_project_upload").dialog('close');
							}else{
								showMsg("规划项目导入失败！");
							}
						},
						error:function(){
							showMsg("文件导入失败");
						}
					});

	          }else{
			    var params=new Object();
				params.PROJECT_NAME = $.trim($("#dlg_PROJECT_NAME").val());
				params.SZFW = $.trim($("#dlg_SZFW").val());
				params.JSDW = $.trim($("#dlg_JSDW").val());
				params.CSSJ  = $.trim($("#dlg_CSSJ").val());
				params.KG  = $.trim($("#dlg_KG").val());
				params.XZYJS  = $.trim($("#dlg_XZYJS").val());
				params.ZPMFA  = $.trim($("#dlg_ZPMFA").val());
				params.YDZ  = $.trim($("#dlg_YDZ").val());
				params.GCZ  = $.trim($("#dlg_GCZ").val());
				params.PHGL  = $.trim($("#dlg_PHGL").val());
				params.JGHS  = $.trim($("#dlg_JGHS").val());
				params.ZRBM  = $.trim($("#dlg_ZRBM").val());
			    // 提交方式
				   var action = $("#action").val();	
				//新增的时候才判断密码，修改的时候不需要
					if(params.PROJECT_NAME==null || params.PROJECT_NAME=='')
					{
						showMsg("规划项目名称不能为空！");
						$("#dlg_PROJECT_NAME").focus();
						return;
					}
					if(params.SZFW==null || params.SZFW=='')
					{
						showMsg("施置范围不能为空！");
						$("#dlg_SZFW").focus();
						return;
					}
					if(params.JSDW==null || params.JSDW=='')
					{
						showMsg("建设单位不能为空！");
						$("#dlg_JSDW").focus();
						return;
					}
					if(params.ZRBM==null || params.ZRBM=='')
					{
						showMsg("责任部门不能为空！");
						$("#dlg_ZRBM").focus();
						return;
					}				
				// 新增和修改通用字符串
				var url ='';
				var indicate='';
				if ("add" == action) 
				{
					indicate = "确认新增" + params.PROJECT_NAME + ",这个规划项目?";
					url = "plann!saveproject.action";
				} 
				else if ("edit" == action) 
				{
					params.ID = $("#dlg_id").val();
					indicate = "确认更新" + params.PROJECT_NAME + ",这个规划项目?";
					url = "plann!updateproject.action";
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
								$('#dlg_project').dialog('close');
								project_grid.loadData({page:1});
							} else 
							{
								showMsg(result.msg);
							}
						}
					}); 
				}
				else
				{
				    $('#dlg_project').dialog('close');
				}
		}
	},
	// 导出数据
	commitExport : function() 
	{
				var condition = "";
				var serch_xm=$("#serch_project_name")==null?'':$('#serch_project_name').val();
				if (serch_xm && $.trim(serch_xm).length > 0) 
				{
					condition += "&project_name=" + encodeURI(encodeURI($.trim(serch_xm)));
				}
				var serch_jsdw=$("#serch_jsdw")==null?'':$('#serch_jsdw').val();
				if (serch_jsdw && $.trim(serch_jsdw).length > 0) 
				{
					condition += "&jsdw=" + encodeURI(encodeURI($.trim(serch_jsdw)));
				}
				
				location.href='plann!exportProject.action?'+condition;
			
   },
   //导入excel
   importghxm:function(name,grid)
   	{
		
		project_grid.showDialog("规划项目导入","import", $("#dlg_project_upload"),500,400);
	},
   //下载附件
   mydownload:function(path)
   {
     var obj=$("#"+path);
	 if(obj.val() === '' || obj.val() === undefined || typeof obj.val() === 'undefined' )
	 {
		showErrorMsg("上传附件不存在或你未上传附件！");
		return;
	 }
	 else
	 {
        location.href='upload!download.action?&path='+ encodeURI(encodeURI(unescape(obj.val()))); 
     }
   },
	loadData:function(param){
		var queryP = {};
		queryP.project_name = $.trim($('#serch_project_name').val());
		queryP.jsdw = $.trim($('#serch_jsdw').val());
		var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
		queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
		queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
		queryP.sortname = param.sortname? param.sortname : "";
		queryP.sortorder = param.sortorder? param.sortorder : "";
		$.ajax({
			type: "POST",
			url: "plann!projectList.action?operation=init",
			cache: false,
			data: queryP,
			success: function(msg){
				var data=eval("("+msg+")");
				try{
					project_grid.project_grid_result.flexAddData(data);
				}catch(e){
					showErrorMsg("查询结果信息出错！");
				}
			}
		}); 
	}
};


	 $(function(){
		//初始化所有操作按钮
		 var  parm_button = [
			  {name: '新建', bclass: 'add', onpress : project_grid.add},
			  {name: '修改', bclass: 'edit', onpress : project_grid.edit},
			  {name: '删除', bclass: 'delete', onpress : project_grid.del},
			  {name: '导入', bclass: 'distribute', onpress : project_grid.importghxm},
			  {name: '导出', bclass: 'excel', onpress : project_grid.commitExport}
		 ];
		 //权限操作按钮
		 var colModel = [						
	            {display: '序号', name : 'xh', width : 40, align: 'center'},
				{display: 'id', name : 'ID', width : 40, align: 'center',hide : true,sortable: false},
				{display: '项目名称', name : 'PROJECT_NAME', width : 350, align: 'left',sortable: true},
				{display: '施置范围', name : 'SZFW', width : 150, align: 'left',sortable: false},
				{display: '建设单位', name : 'JSDW', width : 120, align: 'left',sortable: true},
				{display: '概规或城市设计', name : 'CSSJ', width : 120,  align: 'left',sortable : true},
				{display: '控规', name : 'KG', width : 120,  align: 'left',sortable : true},
				{display: '选址意见书', name : 'XZYJS', width : 120,  align: 'left',sortable : false},
				{display: '总平面方案', name : 'ZPMFA', width : 120,  align: 'left',sortable : false},
				{display: '用地证', name : 'YDZ', width : 120,  align: 'left',sortable : false},
				{display: '工程证', name : 'GCZ', width : 120,  align: 'left',sortable : false},
				{display: '批后管理', name : 'PHGL', width : 120,  align: 'left',sortable : false},
				{display: '竣工核实', name : 'JGHS', width : 120,  align: 'left',sortable : false},
				{display: '责任部门', name : 'ZRBM', width : 120,  align: 'left',sortable : false}
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
				project_grid.bindParamGrid(buttons,colModel);
			}
		});
		 
		 
		 
		 
		 /*参数管理-查询按钮功能*/
		$('#btn_project_search').button().on("click",function(){
			project_grid.loadData({page:1});
		});
				//清空筛选框
		$('#btn_project_empty').on("click",function(){
			$("#serch_project_name").val("");
			$("#serch_jsdw").val("");
		}); 
		 		 
	});
	
	//导入的模板文件
	function downloadModel(obj)
	{
	    if(obj != null && obj != "")
		window.location.href = "upload!exportExcelModelForTable.action?suffix="+obj+"&nameType=ghxm"+"&radom="+Math.random();
     }
	
</script>
