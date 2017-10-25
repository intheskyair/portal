 <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 <script type="text/javascript">
var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
var page_num = $(window.self.document).find("#page_num").val();
var page_num =30;

function showInfo(rowData){
						var dlg_lhxwgl_qymc = rowData.ENTERPRISENAME;
						var dlg_lhxwgl_nr = rowData.CONTENT;
						var dlg_lhxwgl_lx = rowData.TYPE;
						var dlg_lhxwgl_sj = rowData.TIME;
						$("#dlg_lhxwgl_qymc").val(dlg_lhxwgl_qymc);
						$("#dlg_lhxwgl_nr").val(dlg_lhxwgl_nr);
						$("#dlg_lhxwgl_lx").val(dlg_lhxwgl_lx);
						$("#dlg_lhxwgl_sj").val(dlg_lhxwgl_sj);
						
						var dlgParent = $("#dlg_lhxwgl").parent();
				        var dlgOwn = $("#dlg_lhxwgl").clone();
				        var dlg_lhxwgl_ck = 
					    $("#dlg_lhxwgl").dialog({
					    	title:"查看",
					    	autoOpen: true,
					    	height:$(window).height()*0.8,
							width:$(window).width()*0.5,
							open: function (event, ui) {
								 $(".ui-dialog-titlebar-close", $(this).parent()).hide();
							},
					    	buttons:{
								"关闭": function() {
									clean_input_for_dailog($("#dlg_lhxwgl"));
			         				dlgOwn.appendTo(dlgParent);
			         				$(this).dialog("destroy").remove();
			         			}
							}
					    });
					    dlg_lhxwgl_ck.dialog("open");
}




var	dlg_lhxwgl = function(data){
	dlg_lhxwgl_dialog = $("#dlg_lhxwgl").dialog({
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
							lhxwgl_grid.saveData(data.operation);
						},
						"关闭": function() {
							clean_input_for_dailog($("#dlg_lhxwgl"));
							//console.log(dlgParent.attr("id"));
							//$(this).dialog("destroy").remove();
							//dlgOwn.appendTo(dlgParent);
	         				$(this).dialog("close");
	         			}
					}
				});
				
				dlg_lhxwgl_load = $("#dlg_lhxwgl_upload").dialog({
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
							lhxwgl_grid.saveData(data.operation);
					
						},
						"关闭": function() {
							clean_input_for_dailog($("#dlg_lhxwgl_upload"));
							//console.log(dlgParent.attr("id"));
							//$(this).dialog("destroy").remove();
							//dlgOwn.appendTo(dlgParent);
	         				$(this).dialog("close");
	         			}
					}
				});
				
  
				
};


 var lhxwgl_grid = {
	//绑定参数信息grid
	lhxwgl_grid_result:"",
	dlg_lhxwgl:"",
	lhxwglGridHeight:flexHeight("lhxwgl_grid"),
	bindParamGrid:function(queryP,queryM){
		lhxwgl_grid.lhxwgl_grid_result = $("#lhxwgl_grid").flexigrid({
			url:"cxgl!getLhxwglList.action?operation=init",
			colModel: queryM,
			buttons : queryP,
			rowId:"id",
			showToggleBtn: true,
			usepager: true,
			title:"良好行为管理列表",
			//rp:pageSize,
			onRowDblclick:showInfo,
			rp:page_num,
			showTableToggleBtn: false,
			resizable: false,
			height: lhxwgl_grid.lhxwglGridHeight,
			singleSelect: true,
			onChangePage:lhxwgl_grid.loadData,
			checkbox:false,
			filterParams : [{key : "enterpriseName", name : "#serch_qymc_lhxwgl"},
			                {key : "content", name : "#serch_nr_lhxwgl"},
			                {key : "type", name : "#serch_lx_lhxwgl"} 
			               ]
		});
	},			
		// 查询子任务并且显示
	showDialog : function(com,operation,divobj,h,w) 
		{
		var dlgParent = divobj.parent();
		var dlgOwn = divobj.clone();
		lhxwgl_grid.dlg_lhxwgl = divobj.dialog({
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
						lhxwgl_grid.saveData(operation);
		              	//$(this).dialog("destroy").remove();
					}
				},
			    close : function() {
		              	dlgOwn.appendTo(dlgParent);
		              	$(this).dialog("destroy").remove();
		         }
			});
			
		lhxwgl_grid.dlg_lhxwgl.dialog("option", "title", com).dialog("open");
	},
	add:function(com,grid)
	{
		
		if (com == "新建") 
				{
				    lhxwgl_grid.showDialog("新增良好行为管理","add",$("#dlg_lhxwgl"));
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
									url: "cxgl!deleteLhxwgl.action",
									cache: false,
									data: params,
									success: function(msg)
									{
										var result=eval("("+msg+")");
										if (result.msg=="ok") 
										{
											showMsg("删除成功！");
											$('#dlg_lhxwgl').dialog('close');
											lhxwgl_grid.loadData({page:1});
										} else 
										{
											showMsg(result.msg);
											$('#dlg_lhxwgl').dialog('close');
										}
									}
								}); 
							}else
							{
								$('#dlg_lhxwgl').dialog('close');
							}
						
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_lhxwgl').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_lhxwgl').dialog('close');
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
					   lhxwgl_grid.showDialog("修改良好行为管理","edit",$("#dlg_lhxwgl"));
				       $("#action").val("edit");
						var id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
						var dlg_lhxwgl_qymc = $('.trSelected', grid).eq(0).find("td:eq(2)").text();
						var dlg_lhxwgl_nr = $('.trSelected', grid).eq(0).find("td:eq(3)").text();
						var dlg_lhxwgl_lx = $('.trSelected', grid).eq(0).find("td:eq(4)").text();
						var dlg_lhxwgl_sj = $('.trSelected', grid).eq(0).find("td:eq(5)").text();
						$("#dlg_id").val(id);	
						$("#dlg_lhxwgl_qymc").val(dlg_lhxwgl_qymc);
						$("#dlg_lhxwgl_nr").val(dlg_lhxwgl_nr);
						$("#dlg_lhxwgl_lx").val(dlg_lhxwgl_lx);
						$("#dlg_lhxwgl_sj").val(dlg_lhxwgl_sj);
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
					queryP.nameType = "lhxwglInfo";
					queryP.suffix = $.trim($("#file_name_lhxwgl").val()).split("\.")[1];
					if(queryP.suffix === '' || queryP.suffix === undefined || typeof queryP.suffix === 'undefined' )
						 {
							showMsg("未选择需要导入的文件！");
							return;
						 }
					$.ajaxFileUpload({
						url:"upload!importExcel.action?radom="+Math.random(),
						fileElementId:"file_name_lhxwgl",
						secureuri:false,
						type:"POST",
						data:queryP,
						dataType:"json",
						success:function(data)
						{
							if(data != null && data != "" && data.result == "00")
							{
								showMsg("良好行为管理导管理入成功！");
								lhxwgl_grid.loadData({page:1});
								$("#dlg_lhxwgl_upload").dialog('close');
							}else{
								showMsg("良好行为管理管理导入失败！");
							}
						},
						error:function(){
							showMsg("文件导入失败");
						}
					});

	          }else{
	          var params=new Object();
				params.enterpriseName = $.trim($("#dlg_lhxwgl_qymc").val());
				params.content = $.trim($("#dlg_lhxwgl_nr").val());
				params.type = $.trim($("#dlg_lhxwgl_lx").val());
				params.time  = $.trim($("#dlg_lhxwgl_sj").val());
			    // 提交方式
				   var action = $("#action").val();	
				//新增的时候才判断密码，修改的时候不需要
					if(params.enterpriseName==null || params.enterpriseName=='')
					{
						showMsg("企业名称不能为空！");
						$("#dlg_lhxwgl_qymc").focus();
						return;
					}
					if(params.content==null || params.content=='')
					{
						showMsg("内容不能为空！");
						$("#dlg_lhxwgl_nr").focus();
						return;
					}
					if(params.type==null || params.type=='')
					{
						showMsg("类型不能为空！");
						$("#dlg_lhxwgl_lx").focus();
						return;
					}
					if(params.time==null || params.time=='')
					{
						showMsg("时间不能为空！");
						$("#dlg_lhxwgl_sj").focus();
						return;
					}
								
				// 新增和修改通用字符串
				var url ='';
				var indicate='';
				if ("add" == action) 
				{
					indicate = "确认新增" + params.enterpriseName + ",这个良好行为?";
					url = "cxgl!saveLhxwgl.action";
				} 
				else if ("edit" == action) 
				{
					params.id = $("#dlg_id").val();
					indicate = "确认更新" + params.enterpriseName + ",这个良好行为?";
					url = "cxgl!updateLhxwgl.action";
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
								clean_input_for_dailog($("#dlg_lhxwgl"));
								$('#dlg_lhxwgl').dialog('close');
								lhxwgl_grid.loadData({page:1});
							} else 
							{
								showMsg(result.msg);
							}
						}
					}); 
				}
				else
				{
				    $('#dlg_lhxwgl').dialog('close');
				}
	          }
			    
		
	},
	// 导出数据
	commitExport : function() 
	{
				var condition = "";
				var serch_qymc_lhxwgl=$("#serch_qymc_lhxwgl")==null?'':$('#serch_qymc_lhxwgl').val();
				if (serch_qymc_lhxwgl && $.trim(serch_qymc_lhxwgl).length > 0) 
				{
					condition += "&enterpriseName=" + encodeURI(encodeURI($.trim(serch_qymc_lhxwgl)));
				}
				var serch_nr_lhxwgl=$("#serch_nr_lhxwgl")==null?'':$('#serch_nr_lhxwgl').val();
				if (serch_nr_lhxwgl && $.trim(serch_nr_lhxwgl).length > 0) 
				{
					condition += "&content=" + encodeURI(encodeURI($.trim(serch_nr_lhxwgl)));
				}
				var serch_lx_lhxwgl=$("#serch_lx_lhxwgl")==null?'':$('#serch_lx_lhxwgl').val();
				if (serch_lx_lhxwgl && $.trim(serch_lx_lhxwgl).length > 0) 
				{
					condition += "&type=" + encodeURI(encodeURI($.trim(serch_lx_lhxwgl)));
				}
				location.href='cxgl!getExportDara_lhxwgl.action?'+condition;
			
   },
    importlhxwgl:function(name,grid)
   	{
		//showMsg("车辆信息批量导入");
		lhxwgl_grid.showDialog("良好行为管理导入","import", $("#dlg_lhxwgl_upload"),500,400);
	},
   mydownload:function(path)
   {
   		
        location.href='upload!download.action?&path='+ encodeURI(encodeURI(unescape(path))); 
   },
	loadData:function(param){
		var queryP = {};
		queryP.enterpriseName = $('#serch_qymc_lhxwgl').val()==null?'':$('#serch_qymc_lhxwgl').val();
		queryP.content = $('#serch_nr_lhxwgl').val()==null?'':$('#serch_nr_lhxwgl').val();
		queryP.type = $('#serch_lx_lhxwgl').val()==null?'':$('#serch_lx_lhxwgl').val();
		var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
		queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
		queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
		queryP.sortname = param.sortname? param.sortname : "";
		queryP.sortorder = param.sortorder? param.sortorder : "";
		$.ajax({
			type: "POST",
			url: "cxgl!getLhxwglList.action?operation=init",
			cache: false,
			data: queryP,
			success: function(msg){
				var data=eval("("+msg+")");
				try{
					lhxwgl_grid.lhxwgl_grid_result.flexAddData(data);
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
		window.location.href = "upload!exportExcelModelForTable.action?suffix="+obj+"&nameType=lhxwglInfo"+"&radom="+Math.random();
     }
	
	 $(function(){
		//初始化所有操作按钮
		var  parm_button = [
			  {name: '新建', bclass: 'add', onpress : lhxwgl_grid.add},
			  {name: '修改', bclass: 'edit', onpress : lhxwgl_grid.edit},
			  {name: '删除', bclass: 'delete', onpress : lhxwgl_grid.del},
			  {name: '导入', bclass: 'distribute', onpress : lhxwgl_grid.importlhxwgl},
			  {name: '导出', bclass: 'excel', onpress : lhxwgl_grid.commitExport}
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
				lhxwgl_grid.bindParamGrid(buttons,colModel);
			}
		});
		 
		/*参数管理-查询按钮功能*/
		$("#btn_lhxwgl_search_lhxwgl").button().on("click",function(){
			lhxwgl_grid.loadData({page:1});
		});
				//清空筛选框
		$('#btn_result_empty_lhxwgl').on("click",function(){
			$("#serch_qymc_lhxwgl").val("");
			$("#serch_nr_lhxwgl").val("");
			$("#serch_lx_lhxwgl").val("");
			lhxwgl_grid.loadData({page:1});
		});
		
	});
</script>
<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='../images/fold.png' onclick="showAndHide(this,'lhxwgl_grid');"  class="imgShrotate">
				<font>查询条件</font>
			</h3>
		</div>
	<div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>

					<td >
						<label for="input_1" class="lbl-1">
							企业名称:
						</label>
						<input type="text" id="serch_qymc_lhxwgl" name="serch_qymc_lhxwgl" class="wi-ipt-1" />
					</td>
					<td >
						<label for="input_1" class="lbl-1">
							内容:
						</label>
						<input type="text" id="serch_nr_lhxwgl" name="serch_nr_lhxwgl" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<label for="input_1" class="lbl-1">
							类型:
						</label>
						<input type="text" id="serch_lx_lhxwgl" name="serch_lx_lhxwgl" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<button id="btn_lhxwgl_search_lhxwgl" class="ui-state-default ui-state-qik">查询
						</button>
						<button id="btn_result_empty_lhxwgl" class="ui-state-default ui-state-qik">清空
						</button>
					</td>
				</tr>
			</table>
		</div>
</div>
<div class="div div-2" style="overflow:auto;">
	<table id="lhxwgl_grid" style="display:none;padding:0 0 0 0;"></table>
</div>
<div id="dlg_lhxwgl" style="display:none;overflow-y: hidden;">
	<input type="hidden" id="action" />
	<input type="hidden" id="dlg_id" /> 
	<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">企业名称：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_lhxwgl_qymc" name="dlg_lhxwgl_bt"  class="wi-ipt-1" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">内容：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_lhxwgl_nr" value="" class="wi-ipt-1" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">类型：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_lhxwgl_lx" value="" class="wi-ipt-1"/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">时间：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_lhxwgl_sj" value="" onFocus="WdatePicker({readOnly:true})" class="wi-ipt-1"/>
			</td>
		</tr>
	</table>
	<div style="overflow: hidden">
		
	</div>
</div>
<div id="dlg_lhxwgl_upload" style="display:none;overflow-y: hidden;">
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
				<input type="file" id="file_name_lhxwgl" name="file_name_lhxwgl" accept=".xls,.xlsx"/>
			</td>
		</tr>
	</table>
</div>	