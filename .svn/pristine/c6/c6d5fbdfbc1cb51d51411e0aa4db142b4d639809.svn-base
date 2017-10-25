 <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 <script type="text/javascript">
var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
var page_num = $(window.self.document).find("#page_num").val();
var page_num =30;

function showInfo(rowData){
						var dlg_blxwgl_qymc = rowData.ENTERPRISENAME;
						var dlg_blxwgl_nr = rowData.CONTENT;
						var dlg_blxwgl_lx = rowData.TYPE;
						var dlg_blxwgl_sj = rowData.TIME;
						$("#dlg_blxwgl_qymc").val(dlg_blxwgl_qymc);
						$("#dlg_blxwgl_nr").val(dlg_blxwgl_nr);
						$("#dlg_blxwgl_lx").val(dlg_blxwgl_lx);
						$("#dlg_blxwgl_sj").val(dlg_blxwgl_sj);
						
						var dlgParent = $("#dlg_blxwgl").parent();
				        var dlgOwn = $("#dlg_blxwgl").clone();
				        var dlg_blxwgl_ck = 
					    $("#dlg_blxwgl").dialog({
					    	title:"查看",
					    	autoOpen: true,
					    	height:$(window).height()*0.8,
							width:$(window).width()*0.5,
							open: function (event, ui) {
								 $(".ui-dialog-titlebar-close", $(this).parent()).hide();
							},
					    	buttons:{
								"关闭": function() {
									clean_input_for_dailog($("#dlg_blxwgl"));
			         				dlgOwn.appendTo(dlgParent);
			         				$(this).dialog("destroy").remove();
			         			}
							}
					    });
					    dlg_blxwgl_ck.dialog("open");
}




var	dlg_blxwgl = function(data){
	dlg_blxwgl_dialog = $("#dlg_blxwgl").dialog({
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
							blxwgl_grid.saveData(data.operation);
						},
						"关闭": function() {
							clean_input_for_dailog($("#dlg_blxwgl"));
							//console.log(dlgParent.attr("id"));
							//$(this).dialog("destroy").remove();
							//dlgOwn.appendTo(dlgParent);
	         				$(this).dialog("close");
	         			}
					}
				});
				
				dlg_blxwgl_load = $("#dlg_blxwgl_upload").dialog({
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
							blxwgl_grid.saveData(data.operation);
					
						},
						"关闭": function() {
							clean_input_for_dailog($("#dlg_blxwgl_upload"));
							//console.log(dlgParent.attr("id"));
							//$(this).dialog("destroy").remove();
							//dlgOwn.appendTo(dlgParent);
	         				$(this).dialog("close");
	         			}
					}
				});
				
  
				
};

 var blxwgl_grid = {
	//绑定参数信息grid
	blxwgl_grid_result:"",
	dlg_blxwgl:"",
	blxwglGridHeight:flexHeight("blxwgl_grid"),
	bindParamGrid:function(queryP,queryM){
		blxwgl_grid.blxwgl_grid_result = $("#blxwgl_grid").flexigrid({
			url:"cxgl!getBlxwglList.action?operation=init",
			colModel: queryM,
			buttons : queryP,
			rowId:"id",
			showToggleBtn: true,
			usepager: true,
			title:"不良行为管理列表",
			//rp:pageSize,
			onRowDblclick:showInfo,
			rp:page_num,
			showTableToggleBtn: false,
			resizable: false,
			height: blxwgl_grid.blxwglGridHeight,
			singleSelect: true,
			onChangePage:blxwgl_grid.loadData,
			checkbox:false,
			filterParams : [{key : "enterpriseName", name : "#serch_qymc_blxwgl"},
			                {key : "content", name : "#serch_nr_blxwgl"},
			                {key : "type", name : "#serch_lx_blxwgl"}
			               ]
		});
	},			
	// 查询子任务并且显示
	showDialog : function(com,operation,divobj,h,w) 
		{
		var dlgParent = divobj.parent();
		var dlgOwn = divobj.clone();
		blxwgl_grid.dlg_blxwgl = divobj.dialog({
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
						blxwgl_grid.saveData(operation);
		              	//$(this).dialog("destroy").remove();
					}
				},
			    close : function() {
		              	dlgOwn.appendTo(dlgParent);
		              	$(this).dialog("destroy").remove();
		         }
			});
			
		blxwgl_grid.dlg_blxwgl.dialog("option", "title", com).dialog("open");
	},
	
	add:function(com,grid)
	{
		
		if (com == "新建") 
				{
				    blxwgl_grid.showDialog("新增不良行为管理","add",$("#dlg_blxwgl"));
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
									url: "cxgl!deleteBlxwgl.action",
									cache: false,
									data: params,
									success: function(msg)
									{
										var result=eval("("+msg+")");
										if (result.msg=="ok") 
										{
											showMsg("删除成功！");
											$('#dlg_blxwgl').dialog('close');
											blxwgl_grid.loadData({page:1});
										} else 
										{
											showMsg(result.msg);
											$('#dlg_blxwgl').dialog('close');
										}
									}
								}); 
							}else
							{
								$('#dlg_blxwgl').dialog('close');
							}
						
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_blxwgl').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_blxwgl').dialog('close');
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
					   blxwgl_grid.showDialog("修改不良行为管理","edit",$("#dlg_blxwgl"));
				       $("#action").val("edit");
						var id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
						var dlg_blxwgl_qymc = $('.trSelected', grid).eq(0).find("td:eq(2)").text();
						var dlg_blxwgl_nr = $('.trSelected', grid).eq(0).find("td:eq(3)").text();
						var dlg_blxwgl_lx = $('.trSelected', grid).eq(0).find("td:eq(4)").text();
						var dlg_blxwgl_sj = $('.trSelected', grid).eq(0).find("td:eq(5)").text();
						$("#dlg_id").val(id);	
						$("#dlg_blxwgl_qymc").val(dlg_blxwgl_qymc);
						$("#dlg_blxwgl_nr").val(dlg_blxwgl_nr);
						$("#dlg_blxwgl_lx").val(dlg_blxwgl_lx);
						$("#dlg_blxwgl_sj").val(dlg_blxwgl_sj);
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
					queryP.nameType = "blxwglInfo";
					queryP.suffix = $.trim($("#file_name_blxwgl").val()).split("\.")[1];
					if(queryP.suffix === '' || queryP.suffix === undefined || typeof queryP.suffix === 'undefined' )
						 {
							showMsg("未选择需要导入的文件！");
							return;
						 }
					$.ajaxFileUpload({
						url:"upload!importExcel.action?radom="+Math.random(),
						fileElementId:"file_name_blxwgl",
						secureuri:false,
						type:"POST",
						data:queryP,
						dataType:"json",
						success:function(data)
						{
							if(data != null && data != "" && data.result == "00")
							{
								showMsg("不良行为管理导管理入成功！");
								blxwgl_grid.loadData({page:1});
								$("#dlg_blxwgl_upload").dialog('close');
							}else{
								showMsg("不良行为管理管理导入失败！");
							}
						},
						error:function(){
							showMsg("文件导入失败");
						}
					});

	          }else{
	          var params=new Object();
				params.enterpriseName = $.trim($("#dlg_blxwgl_qymc").val());
				params.content = $.trim($("#dlg_blxwgl_nr").val());
				params.type = $.trim($("#dlg_blxwgl_lx").val());
				params.time  = $.trim($("#dlg_blxwgl_sj").val());
			    // 提交方式
				   var action = $("#action").val();	
				//新增的时候才判断密码，修改的时候不需要
					if(params.enterpriseName==null || params.enterpriseName=='')
					{
						showMsg("企业名称不能为空！");
						$("#dlg_blxwgl_qymc").focus();
						return;
					}
					if(params.content==null || params.content=='')
					{
						showMsg("内容不能为空！");
						$("#dlg_blxwgl_nr").focus();
						return;
					}
					if(params.type==null || params.type=='')
					{
						showMsg("类型不能为空！");
						$("#dlg_blxwgl_lx").focus();
						return;
					}
					if(params.time==null || params.time=='')
					{
						showMsg("时间不能为空！");
						$("#dlg_blxwgl_sj").focus();
						return;
					}
								
				// 新增和修改通用字符串
				var url ='';
				var indicate='';
				if ("add" == action) 
				{
					indicate = "确认新增" + params.enterpriseName + ",这个不良行为?";
					url = "cxgl!saveBlxwgl.action";
				} 
				else if ("edit" == action) 
				{
					params.id = $("#dlg_id").val();
					indicate = "确认更新" + params.enterpriseName + ",这个不良行为?";
					url = "cxgl!updateBlxwgl.action";
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
								clean_input_for_dailog($("#dlg_blxwgl"));
								$('#dlg_blxwgl').dialog('close');
								blxwgl_grid.loadData({page:1});
							} else 
							{
								showMsg(result.msg);
							}
						}
					}); 
				}
				else
				{
				    $('#dlg_blxwgl').dialog('close');
				}
	          }
			    
		
	},
	// 导出数据
	commitExport : function() 
	{
				var condition = "";
				var serch_qymc_blxwgl=$("#serch_qymc_blxwgl")==null?'':$('#serch_qymc_blxwgl').val();
				if (serch_qymc_blxwgl && $.trim(serch_qymc_blxwgl).length > 0) 
				{
					condition += "&enterpriseName=" + encodeURI(encodeURI($.trim(serch_qymc_blxwgl)));
				}
				var serch_nr_blxwgl=$("#serch_nr_blxwgl")==null?'':$('#serch_nr_blxwgl').val();
				if (serch_nr_blxwgl && $.trim(serch_nr_blxwgl).length > 0) 
				{
					condition += "&content=" + encodeURI(encodeURI($.trim(serch_nr_blxwgl)));
				}
				var serch_lx_blxwgl=$("#serch_lx_blxwgl")==null?'':$('#serch_lx_blxwgl').val();
				if (serch_lx_blxwgl && $.trim(serch_lx_blxwgl).length > 0) 
				{
					condition += "&type=" + encodeURI(encodeURI($.trim(serch_lx_blxwgl)));
				}
				location.href='cxgl!getExportDara_blxwgl.action?'+condition;
			
   },
    importblxwgl:function(name,grid)
   	{
		//showMsg("车辆信息批量导入");
		blxwgl_grid.showDialog("不良行为管理导入","import", $("#dlg_blxwgl_upload"),500,400);
	},
   mydownload:function(path)
   {
   		
        location.href='upload!download.action?&path='+ encodeURI(encodeURI(unescape(path))); 
   },
	loadData:function(param){
		var queryP = {};
		queryP.enterpriseName = $('#serch_qymc_blxwgl').val()==null?'':$('#serch_qymc_blxwgl').val();
		queryP.content = $('#serch_nr_blxwgl').val()==null?'':$('#serch_nr_blxwgl').val();
		queryP.type = $('#serch_lx_blxwgl').val()==null?'':$('#serch_lx_blxwgl').val();
		var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
		queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
		queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
		queryP.sortname = param.sortname? param.sortname : "";
		queryP.sortorder = param.sortorder? param.sortorder : "";
		$.ajax({
			type: "POST",
			url: "cxgl!getBlxwglList.action?operation=init",
			cache: false,
			data: queryP,
			success: function(msg){
				var data=eval("("+msg+")");
				try{
					blxwgl_grid.blxwgl_grid_result.flexAddData(data);
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
		window.location.href = "upload!exportExcelModelForTable.action?suffix="+obj+"&nameType=blxwglInfo"+"&radom="+Math.random();
     }
	
	 $(function(){
		//初始化所有操作按钮
		var  parm_button = [
			  {name: '新建', bclass: 'add', onpress : blxwgl_grid.add},
			  {name: '修改', bclass: 'edit', onpress : blxwgl_grid.edit},
			  {name: '删除', bclass: 'delete', onpress : blxwgl_grid.del},
			  {name: '导入', bclass: 'distribute', onpress : blxwgl_grid.importblxwgl},
			  {name: '导出', bclass: 'excel', onpress : blxwgl_grid.commitExport}
		 ];
		 //权限操作按钮
		 var colModel = [
						{display: '序号', name : 'xh', width : 40, align: 'center'},
						{display: 'id', name : 'ID', width : 40, align: 'center',hide : true,sortable: true},
						{display: '企业名称', name : 'ENTERPRISENAME', width : 150,  align: 'left'},
						{display: '内容', name : 'CONTENT', width : 150,  align: 'left'},
						{display: '类型', name : 'TYPE', width : 150,  align: 'left'},
						{display: '时间', name : 'TIME', width : 200, align: 'left'}
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
				blxwgl_grid.bindParamGrid(buttons,colModel);
			}
		});
		 
		/*参数管理-查询按钮功能*/
		$("#btn_blxwgl_search_blxwgl").button().on("click",function(){
			blxwgl_grid.loadData({page:1});
		});
				//清空筛选框
		$('#btn_result_empty_blxwgl').on("click",function(){
			$("#serch_qymc_blxwgl").val("");
			$("#serch_nr_blxwgl").val("");
			$("#serch_lx_blxwgl").val("");
			blxwgl_grid.loadData({page:1});
		});
		
	});
</script>
<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='../images/fold.png' onclick="showAndHide(this,'blxwgl_grid');"  class="imgShrotate">
				<font>查询条件</font>
			</h3>
		</div>
	<div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>

					<td class="wi-align-l">
						<label for="input_1" class="lbl-1">
							企业名称:
						</label>
						<input type="text" id="serch_qymc_blxwgl" name="serch_qymc_blxwgl" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<label for="input_1" class="lbl-1">
							内容:
						</label>
						<input type="text" id="serch_nr_blxwgl" name="serch_nr_blxwgl" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<label for="input_1" class="lbl-1">
							类型:
						</label>
						<input type="text" id="serch_lx_blxwgl" name="serch_lx_blxwgl" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<button id="btn_blxwgl_search_blxwgl" class="ui-state-default ui-state-qik">查询
						</button>
						<button id="btn_result_empty_blxwgl" class="ui-state-default ui-state-qik">清空
						</button>
					</td>
				</tr>
			</table>
		</div>
</div>
<div class="div div-2" style="overflow:auto;">
	<table id="blxwgl_grid" style="display:none;padding:0 0 0 0;"></table>
</div>
<div id="dlg_blxwgl" style="display:none;overflow-y: hidden;">
	<input type="hidden" id="action" />
	<input type="hidden" id="dlg_id" /> 
	<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">企业名称：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_blxwgl_qymc" name="dlg_blxwgl_bt"  class="wi-ipt-1" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">内容：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_blxwgl_nr" value="" class="wi-ipt-1" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">类型：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_blxwgl_lx" value="" class="wi-ipt-1"/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">时间：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_blxwgl_sj" value="" onFocus="WdatePicker({readOnly:true})" class="wi-ipt-1"/>
			</td>
		</tr>
	</table>
	<div style="overflow: hidden">
		
	</div>
</div>
<div id="dlg_blxwgl_upload" style="display:none;overflow-y: hidden;">
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
				<input type="file" id="file_name_blxwgl" name="file_name_blxwgl" accept=".xls,.xlsx"/>
			</td>
		</tr>
	</table>
</div>	