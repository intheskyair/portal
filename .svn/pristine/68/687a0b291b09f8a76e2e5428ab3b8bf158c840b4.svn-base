 <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 <script type="text/javascript">
var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
var page_num = $(window.self.document).find("#page_num").val();
var page_num =30;

// 		 function showInfo(rowData){
//     					var dlg_cydwgl_qymc = rowData.ENTERPRISENAME;
// 						var dlg_cydwgl_qyzz = rowData.ENTERPISEQUALIFICATION;
// 						var dlg_cydwgl_qylx = rowData.ENTERPRISETYPE;
// 						var dlg_cydwgl_frdb = rowData.JURIDICALPERSON;
// 						$("#dlg_cydwgl_qymc").val(dlg_cydwgl_qymc);
// 						$("#dlg_cydwgl_qyzz").val(dlg_cydwgl_qyzz);
// 						$("#dlg_cydwgl_qylx").val(dlg_cydwgl_qylx);
// 						$("#dlg_cydwgl_frdb").val(dlg_cydwgl_frdb);
						
// 			 var dlgParent = $("#dlg_cydwgl").parent();
// 		        var dlgOwn = $("#dlg_cydwgl").clone();
// 		        var dlg_cydwgl_ck = 
// 			 $("#dlg_cydwgl").dialog({
// 	    			draggable :false,
// 	    			autoOpen: false,
// 					height:$(window).height()*0.8,
// 					width:$(window).width()*0.5,
// 					show: "slide",
// 					modal:true,
// 					cache:false,
// 					/* hide: "explode", */
// 					title:'选择考核人员',
// 					buttons:{
// 						"关闭": function() {
// 								dlgOwn.appendTo(dlgParent);
// 			         				$(this).dialog("destroy").remove(); 
// 			         			}
// 					},					 
//     		});
// 						dlg_cydwgl_ck.dialog("option", "title", "选择考核人员").dialog("open");
// 		 }



function showInfo(rowData){
						var dlg_cydwgl_qymc = rowData.ENTERPRISENAME;
						var dlg_cydwgl_qyzz = rowData.ENTERPISEQUALIFICATION;
						var dlg_cydwgl_qylx = rowData.ENTERPRISETYPE;
						var dlg_cydwgl_frdb = rowData.JURIDICALPERSON;
						$("#dlg_cydwgl_qymc").val(dlg_cydwgl_qymc);
						$("#dlg_cydwgl_qyzz").val(dlg_cydwgl_qyzz);
						$("#dlg_cydwgl_qylx").val(dlg_cydwgl_qylx);
						$("#dlg_cydwgl_frdb").val(dlg_cydwgl_frdb);
						
						var dlgParent = $("#dlg_cydwgl").parent();
				        var dlgOwn = $("#dlg_cydwgl").clone();
				        var dlg_cydwgl_ck = 
					    $("#dlg_cydwgl").dialog({
					    	title:"查看",
					    	autoOpen: true,
					    	height:$(window).height()*0.8,
							width:$(window).width()*0.5,
							open: function (event, ui) {
								 $(".ui-dialog-titlebar-close", $(this).parent()).hide();
							},
					    	buttons:{
								"关闭": function() {
									clean_input_for_dailog($("#dlg_cydwgl"));
			         				dlgOwn.appendTo(dlgParent);
			         				$(this).dialog("destroy").remove(); 
			         			}
							}
					    });
					    dlg_cydwgl_ck.dialog("open");
}




var	dlg_cydwgl = function(data){
	dlg_cydwgl_dialog = $("#dlg_cydwgl").dialog({
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
							cydwgl_grid.saveData(data.operation);
						},
						"关闭": function() {
							clean_input_for_dailog($("#dlg_cydwgl"));
							//console.log(dlgParent.attr("id"));
							//$(this).dialog("destroy").remove();
							//dlgOwn.appendTo(dlgParent);
	         				$(this).dialog("close");
	         			}
					}
				});
				
				dlg_cydwgl_load = $("#dlg_cydwgl_upload").dialog({
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
							cydwgl_grid.saveData(data.operation);
					
						},
						"关闭": function() {
							clean_input_for_dailog($("#dlg_cydwgl_upload"));
							//console.log(dlgParent.attr("id"));
							//$(this).dialog("destroy").remove();
							//dlgOwn.appendTo(dlgParent);
	         				$(this).dialog("close");
	         			}
					}
				});
				
  
				
};

 var cydwgl_grid = {
	//绑定参数信息grid
	cydwgl_grid_result:"",
	dlg_cydwgl:"",
	cydwglGridHeight:flexHeight("cydwgl_grid"),
	bindParamGrid:function(queryP,queryM){
		cydwgl_grid.cydwgl_grid_result = $("#cydwgl_grid").flexigrid({
			url:"cxgl!getCydwglList.action?operation=init",
			colModel: queryM,
			buttons : queryP,
			rowId:"id",
			showToggleBtn: true,
			usepager: true,
			title:"从业单位列表",
			//rp:pageSize,
			onRowDblclick:showInfo,
			rp:page_num,
			showTableToggleBtn: false,
			resizable: false,
			height: cydwgl_grid.cydwglGridHeight,
			singleSelect: true,
			onChangePage:cydwgl_grid.loadData,
			checkbox:false,
			filterParams : [{key : "enterpriseName", name : "#serch_qymc_cydwgl"},
			                {key : "enterpiseQualification", name : "#serch_qyzz_cydwgl"},
			                {key : "enterpriseType", name : "#serch_qylx_cydwgl"}, 
			                {key : "juridicalPerson", name : "#serch_frdb_cydwgl"}
			               ]
		});
	},			
		// 查询子任务并且显示
	showDialog : function(com,operation,divobj,h,w) 
		{
		var dlgParent = divobj.parent();
		var dlgOwn = divobj.clone();
		cydwgl_grid.dlg_cydwgl = divobj.dialog({
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
						cydwgl_grid.saveData(operation);
		              	//$(this).dialog("destroy").remove();
					}
				},
			    close : function() {
		              	dlgOwn.appendTo(dlgParent);
		              	$(this).dialog("destroy").remove();
		         }
			});
			
		cydwgl_grid.dlg_cydwgl.dialog("option", "title", com).dialog("open");
	},
	add:function(com,grid)
	{
		
		if (com == "新建") 
				{
				    cydwgl_grid.showDialog("新增从业单位","add",$("#dlg_cydwgl"));
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
									url: "cxgl!deleteCydwgl.action",
									cache: false,
									data: params,
									success: function(msg)
									{
										var result=eval("("+msg+")");
										if (result.msg=="ok") 
										{
											showMsg("删除成功！");
											$('#dlg_cydwgl').dialog('close');
											cydwgl_grid.loadData({page:1});
										} else 
										{
											showMsg(result.msg);
											$('#dlg_cydwgl').dialog('close');
										}
									}
								}); 
							}else
							{
								$('#dlg_cydwgl').dialog('close');
							}
						
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_cydwgl').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_cydwgl').dialog('close');
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
					   cydwgl_grid.showDialog("修改从业单位","edit",$("#dlg_cydwgl"));
				       $("#action").val("edit");
						var id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
						var dlg_cydwgl_qymc = $('.trSelected', grid).eq(0).find("td:eq(2)").text();
						var dlg_cydwgl_qyzz = $('.trSelected', grid).eq(0).find("td:eq(3)").text();
						var dlg_cydwgl_qylx = $('.trSelected', grid).eq(0).find("td:eq(4)").text();
						var dlg_cydwgl_frdb = $('.trSelected', grid).eq(0).find("td:eq(5)").text();
						$("#dlg_id").val(id);	
						$("#dlg_cydwgl_qymc").val(dlg_cydwgl_qymc);
						$("#dlg_cydwgl_qyzz").val(dlg_cydwgl_qyzz);
						$("#dlg_cydwgl_qylx").val(dlg_cydwgl_qylx);
						$("#dlg_cydwgl_frdb").val(dlg_cydwgl_frdb);
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
					queryP.nameType = "cydwglInfo";
					queryP.suffix = $.trim($("#file_name_cydwgl").val()).split("\.")[1];
					if(queryP.suffix === '' || queryP.suffix === undefined || typeof queryP.suffix === 'undefined' )
						 {
							showMsg("未选择需要导入的文件！");
							return;
						 }
					$.ajaxFileUpload({
						url:"upload!importExcel.action?radom="+Math.random(),
						fileElementId:"file_name_cydwgl",
						secureuri:false,
						type:"POST",
						data:queryP,
						dataType:"json",
						success:function(data)
						{
							if(data != null && data != "" && data.result == "00")
							{
								showMsg("从业单位导管理入成功！");
								cydwgl_grid.loadData({page:1});
								$("#dlg_cydwgl_upload").dialog('close');
							}else{
								showMsg("从业单位管理导入失败！");
							}
						},
						error:function(){
							showMsg("文件导入失败");
						}
					});

	          }else{
	          var params=new Object();
				params.enterpriseName = $.trim($("#dlg_cydwgl_qymc").val());
				params.enterpiseQualification = $.trim($("#dlg_cydwgl_qyzz").val());
				params.enterpriseType = $.trim($("#dlg_cydwgl_qylx").val());
				params.juridicalPerson  = $.trim($("#dlg_cydwgl_frdb").val());
			    // 提交方式
				   var action = $("#action").val();	
				//新增的时候才判断密码，修改的时候不需要
					if(params.enterpriseName==null || params.enterpriseName=='')
					{
						showMsg("企业名称不能为空！");
						$("#dlg_cydwgl_qymc").focus();
						return;
					}
					if(params.enterpiseQualification==null || params.enterpiseQualification=='')
					{
						showMsg("企业资质不能为空！");
						$("#dlg_cydwgl_qyzz").focus();
						return;
					}
					if(params.enterpriseType==null || params.enterpriseType=='')
					{
						showMsg("企业类型不能为空！");
						$("#dlg_cydwgl_qylx").focus();
						return;
					}
					if(params.juridicalPerson==null || params.juridicalPerson=='')
					{
						showMsg("法人代表不能为空！");
						$("#dlg_cydwgl_frdb").focus();
						return;
					}
								
				// 新增和修改通用字符串
				var url ='';
				var indicate='';
				if ("add" == action) 
				{
					indicate = "确认新增" + params.enterpriseName + ",这个从业单位?";
					url = "cxgl!saveCydwgl.action";
				} 
				else if ("edit" == action) 
				{
					params.id = $("#dlg_id").val();
					indicate = "确认更新" + params.enterpriseName + ",这个从业单位?";
					url = "cxgl!updateCydwgl.action";
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
								clean_input_for_dailog($("#dlg_cydwgl"));
								$('#dlg_cydwgl').dialog('close');
								cydwgl_grid.loadData({page:1});
							} else 
							{
								showMsg(result.msg);
							}
						}
					}); 
				}
				else
				{
				    $('#dlg_cydwgl').dialog('close');
				}
	          }
			    
		
	},
	// 导出数据
	commitExport : function() 
	{
				var condition = "";
				var serch_qymc_cydwgl=$("#serch_qymc_cydwgl")==null?'':$('#serch_qymc_cydwgl').val();
				if (serch_qymc_cydwgl && $.trim(serch_qymc_cydwgl).length > 0) 
				{
					condition += "&enterpriseName=" + encodeURI(encodeURI($.trim(serch_qymc_cydwgl)));
				}
				var serch_qyzz_cydwgl=$("#serch_qyzz_cydwgl")==null?'':$('#serch_qyzz_cydwgl').val();
				if (serch_qyzz_cydwgl && $.trim(serch_qyzz_cydwgl).length > 0) 
				{
					condition += "&enterpiseQualification=" + encodeURI(encodeURI($.trim(serch_qyzz_cydwgl)));
				}
				var serch_qylx_cydwgl=$("#serch_qylx_cydwgl")==null?'':$('#serch_qylx_cydwgl').val();
				if (serch_qylx_cydwgl && $.trim(serch_qylx_cydwgl).length > 0) 
				{
					condition += "&enterpriseType=" + encodeURI(encodeURI($.trim(serch_qylx_cydwgl)));
				}
				var serch_frdb_cydwgl=$("#serch_frdb_cydwgl")==null?'':$('#serch_frdb_cydwgl').val();
				if (serch_frdb_cydwgl && $.trim(serch_frdb_cydwgl).length > 0) 
				{
					condition += "&juridicalPerson=" + encodeURI(encodeURI($.trim(serch_frdb_cydwgl)));
				}
				location.href='cxgl!getExportDara_cydwgl.action?'+condition;
			
   },
    importcydwgl:function(name,grid)
   	{
		//showMsg("车辆信息批量导入");
		cydwgl_grid.showDialog("从业单位导入","import", $("#dlg_cydwgl_upload"),500,400);
	},
   mydownload:function(path)
   {
   		
        location.href='upload!download.action?&path='+ encodeURI(encodeURI(unescape(path))); 
   },
	loadData:function(param){
		var queryP = {};
		queryP.enterpriseName = $('#serch_qymc_cydwgl').val()==null?'':$('#serch_qymc_cydwgl').val();
		queryP.enterpiseQualification = $('#serch_qyzz_cydwgl').val()==null?'':$('#serch_qyzz_cydwgl').val();
		queryP.enterpriseType = $('#serch_qylx_cydwgl').val()==null?'':$('#serch_qylx_cydwgl').val();
		queryP.juridicalPerson = $('#serch_frdb_cydwgl').val()==null?'':$('#serch_frdb_cydwgl').val();
		var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
		queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
		queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
		queryP.sortname = param.sortname? param.sortname : "";
		queryP.sortorder = param.sortorder? param.sortorder : "";
		$.ajax({
			type: "POST",
			url: "cxgl!getCydwglList.action?operation=init",
			cache: false,
			data: queryP,
			success: function(msg){
				var data=eval("("+msg+")");
				try{
					cydwgl_grid.cydwgl_grid_result.flexAddData(data);
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
		window.location.href = "upload!exportExcelModelForTable.action?suffix="+obj+"&nameType=cydwglInfo"+"&radom="+Math.random();
     }
	
	 $(function(){
		//初始化所有操作按钮
		var  parm_button = [
			  {name: '新建', bclass: 'add', onpress : cydwgl_grid.add},
			  {name: '修改', bclass: 'edit', onpress : cydwgl_grid.edit},
			  {name: '删除', bclass: 'delete', onpress : cydwgl_grid.del},
			  {name: '导入', bclass: 'distribute', onpress : cydwgl_grid.importcydwgl},
			  {name: '导出', bclass: 'excel', onpress : cydwgl_grid.commitExport}
		 ];
		 //权限操作按钮
		 var colModel = [
						{display: '序号', name : 'xh', width : 40, align: 'center'},
						{display: 'id', name : 'ID', width : 40, align: 'center',hide : true,sortable: true},
						{display: '企业名称', name : 'ENTERPRISENAME', width : 150,  align: 'left'},
						{display: '企业资质', name : 'ENTERPISEQUALIFICATION', width : 150,  align: 'left'},
						{display: '企业类型', name : 'ENTERPRISETYPE', width : 200, align: 'left'},
						{display: '法人代表', name : 'JURIDICALPERSON', width : 150,  align: 'left'}
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
				cydwgl_grid.bindParamGrid(buttons,colModel);
			}
		});
		 
		/*参数管理-查询按钮功能*/
		$("#btn_cydwgl_search_cydwgl").button().on("click",function(){
			cydwgl_grid.loadData({page:1});
		});
				//清空筛选框
		$('#btn_result_empty_cydwgl').on("click",function(){
			$("#serch_qymc_cydwgl").val("");
			$("#serch_qyzz_cydwgl").val("");
			$("#serch_qylx_cydwgl").val("");
			$("#serch_frdb_cydwgl").val("");
			cydwgl_grid.loadData({page:1});
		});
		
	});
</script>
<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='../images/fold.png' onclick="showAndHide(this,'cydwgl_grid');"  class="imgShrotate">
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
						<input type="text" id="serch_qymc_cydwgl" name="serch_qymc_cydwgl" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<label for="input_1" class="lbl-1">
							企业资质:
						</label>
						<input type="text" id="serch_qyzz_cydwgl" name="serch_qyzz_cydwgl" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<label for="input_1" class="lbl-1">
							企业类型:
						</label>
						<input type="text" id="serch_qylx_cydwgl" name="serch_qylx_cydwgl" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<label for="input_1" class="lbl-1">
							法人代表：
						</label>
						<input type="text" id="serch_frdb_cydwgl" name="serch_frdb_cydwgl" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<button id="btn_cydwgl_search_cydwgl" class="ui-state-default ui-state-qik">查询
						</button>
						<button id="btn_result_empty_cydwgl" class="ui-state-default ui-state-qik">清空
						</button>
					</td>
				</tr>
			</table>
		</div>
</div>
<div class="div div-2" style="overflow:auto;">
	<table id="cydwgl_grid" style="display:none;padding:0 0 0 0;"></table>
</div>
<div id="dlg_cydwgl" style="display:none;overflow-y: hidden;">
	<input type="hidden" id="action" />
	<input type="hidden" id="dlg_id" /> 
	<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">企业名称：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_cydwgl_qymc" name="dlg_cydwgl_bt"  class="wi-ipt-1" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">企业资质：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_cydwgl_qyzz" value="" class="wi-ipt-1" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">企业类型：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_cydwgl_qylx" value="" class="wi-ipt-1"/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">法人代表：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_cydwgl_frdb" value="" class="wi-ipt-1"/>
			</td>
		</tr>
	</table>
	<div style="overflow: hidden">
		
	</div>
</div>
<div id="dlg_cydwgl_upload" style="display:none;overflow-y: hidden;">
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
				<input type="file" id="file_name_cydwgl" name="file_name_cydwgl" accept=".xls,.xlsx"/>
			</td>
		</tr>
	</table>
</div>	