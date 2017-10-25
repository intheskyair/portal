 <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 <script type="text/javascript">
var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
var page_num = $(window.self.document).find("#page_num").val();
var page_num =30;

function showInfo(rowData){
						var dlg_fundmanage_xmmc = rowData.PROJECTNAME;
						var dlg_fundmanage_jsgm = rowData.BULIDSCALE;
						var dlg_fundmanage_ztz = rowData.TOTALINVEST;
						var dlg_fundmanage_d1jd = rowData.FIRSTQUARTER;
						var dlg_fundmanage_d2jd= rowData.SECONDQUARTER;
						var dlg_fundmanage_d3jd= rowData.THIRDQUARTER;
						var dlg_fundmanage_d4jd= rowData.FOURTHQUARTER;
						$("#dlg_fundmanage_xmmc").val(dlg_fundmanage_xmmc);
						$("#dlg_fundmanage_jsgm").val(dlg_fundmanage_jsgm);
						$("#dlg_fundmanage_ztz").val(dlg_fundmanage_ztz);
						$("#dlg_fundmanage_d1jd").val(dlg_fundmanage_d1jd);
						$("#dlg_fundmanage_d2jd").val(dlg_fundmanage_d2jd);
						$("#dlg_fundmanage_d3jd").val(dlg_fundmanage_d3jd);
						$("#dlg_fundmanage_d4jd").val(dlg_fundmanage_d4jd);
						
						
						var dlgParent = $("#dlg_fundmanage").parent();
				        var dlgOwn = $("#dlg_fundmanage").clone();
				        var dlg_fundmanage_ck =
					    $("#dlg_fundmanage").dialog({
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
									clean_input_for_dailog($("#dlg_fundmanage"));
			         				$(this).dialog("destroy").remove();
			         			}
							}
					    });
					    dlg_fundmanage_ck.dialog("open");
}


var	dlg_fundmanage = function(data){
	dlg_fundmanage_dialog = $("#dlg_fundmanage").dialog({
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
							fundmanage_grid.saveData(data.operation);
						},
						"关闭": function() {
							clean_input_for_dailog($("#dlg_fundmanage"));
							//console.log(dlgParent.attr("id"));
							//$(this).dialog("destroy").remove();
							//dlgOwn.appendTo(dlgParent);
	         				$(this).dialog("close");
	         			}
					}
				});
				
				dlg_fundmanage_load = $("#dlg_fundmanage_upload").dialog({
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
							fundmanage_grid.saveData(data.operation);
					
						},
						"关闭": function() {
							clean_input_for_dailog($("#dlg_fundmanage_upload"));
							//console.log(dlgParent.attr("id"));
							//$(this).dialog("destroy").remove();
							//dlgOwn.appendTo(dlgParent);
	         				$(this).dialog("close");
	         			}
					}
				});
				
  
				
};

 var fundmanage_grid = {
	//绑定参数信息grid
	fundmanage_grid_result:"",
	dlg_fundmanage:"",
	fundmanageGridHeight:flexHeight("fundmanage_grid"),
	bindParamGrid:function(queryP,queryM){
		fundmanage_grid.fundmanage_grid_result = $("#fundmanage_grid").flexigrid({
			url:"fundmanage!getFundManageList.action?operation=init",
			colModel: queryM,
			buttons : queryP,
			rowId:"id",
			showToggleBtn: true,
			usepager: true,
			title:"下达资金管理列表",
			//rp:pageSize,
			onRowDblclick:showInfo,
			rp:page_num,
			showTableToggleBtn: false,
			resizable: false,
			height: fundmanage_grid.fundmanageGridHeight,
			singleSelect: true,
			onChangePage:fundmanage_grid.loadData,
			checkbox:false,
			filterParams : [{key : "projectname", name : "#serch_xmmc_fundmanage"}
			                 ]
		});
	},			
		// 查询子任务并且显示
	showDialog : function(com,operation,divobj,h,w) 
		{
		var dlgParent = divobj.parent();
		var dlgOwn = divobj.clone();
		fundmanage_grid.dlg_fundmanage = divobj.dialog({
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
						fundmanage_grid.saveData(operation);
		              	//$(this).dialog("destroy").remove();
					}
				},
			    close : function() {
		              	dlgOwn.appendTo(dlgParent);
		              	$(this).dialog("destroy").remove();
		         }
			});
			
		fundmanage_grid.dlg_fundmanage.dialog("option", "title", com).dialog("open");
	},	
	add:function(com,grid)
	{
		
		if (com == "新建") 
				{
				    fundmanage_grid.showDialog("新增下达资金管理","add",$("#dlg_fundmanage"));
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
					        var indicate = "确认需要删除" + title+ ",这个下达资金管理吗?";	
					        var params=new Object();
				                params.id = id;					
							if(confirm(indicate)) 
							{
								$.ajax({
									type: "POST",
									url: "fundmanage!deleteFundManage.action",
									cache: false,
									data: params,
									success: function(msg)
									{
										var result=eval("("+msg+")");
										if (result.msg=="ok") 
										{
											showMsg("删除成功！");
											$('#dlg_fundmanage').dialog('close');
											fundmanage_grid.loadData({page:1});
										} else 
										{
											showMsg(result.msg);
											$('#dlg_fundmanage').dialog('close');
										}
									}
								}); 
							}else
							{
								$('#dlg_fundmanage').dialog('close');
							}
						
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_fundmanage').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_fundmanage').dialog('close');
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
					   fundmanage_grid.showDialog("修改下达资金管理","edit",$("#dlg_fundmanage"));
				       $("#action").val("edit");
						var id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
						var dlg_fundmanage_xmmc = $('.trSelected', grid).eq(0).find("td:eq(2)").text();
						var dlg_fundmanage_jsgm = $('.trSelected', grid).eq(0).find("td:eq(3)").text();
						var dlg_fundmanage_ztz = $('.trSelected', grid).eq(0).find("td:eq(4)").text();
						var dlg_fundmanage_d1jd = $('.trSelected', grid).eq(0).find("td:eq(5)").text();
						var dlg_fundmanage_d2jd= $('.trSelected', grid).eq(0).find("td:eq(6)").text();
						var dlg_fundmanage_d3jd= $('.trSelected', grid).eq(0).find("td:eq(7)").text();
						var dlg_fundmanage_d4jd= $('.trSelected', grid).eq(0).find("td:eq(8)").text();
						$("#dlg_id").val(id);	
						$("#dlg_fundmanage_xmmc").val(dlg_fundmanage_xmmc);
						$("#dlg_fundmanage_jsgm").val(dlg_fundmanage_jsgm);
						$("#dlg_fundmanage_ztz").val(dlg_fundmanage_ztz);
						$("#dlg_fundmanage_d1jd").val(dlg_fundmanage_d1jd);
						$("#dlg_fundmanage_d2jd").val(dlg_fundmanage_d2jd);
						$("#dlg_fundmanage_d3jd").val(dlg_fundmanage_d3jd);
						$("#dlg_fundmanage_d4jd").val(dlg_fundmanage_d4jd);
						
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
					queryP.nameType = "fundmanageInfo";
					queryP.suffix = $.trim($("#file_name_fundmanage").val()).split("\.")[1];
					if(queryP.suffix === '' || queryP.suffix === undefined || typeof queryP.suffix === 'undefined' )
						 {
							showMsg("未选择需要导入的文件！");
							return;
						 }
					$.ajaxFileUpload({
						url:"upload!importExcel.action?radom="+Math.random(),
						fileElementId:"file_name_fundmanage",
						secureuri:false,
						type:"POST",
						data:queryP,
						dataType:"json",
						success:function(data)
						{
							if(data != null && data != "" && data.result == "00")
							{
								showMsg("下达资金管理导入成功！");
								fundmanage_grid.loadData({page:1});
								$("#dlg_fundmanage_upload").dialog('close');
							}else{
								showMsg("下达资金管理导入失败！");
							}
						},
						error:function(){
							showMsg("文件导入失败");
						}
					});

	          }else{
			    var params=new Object();
					params.projectname = $.trim($("#dlg_fundmanage_xmmc").val());
					params.bulidscale = $.trim($("#dlg_fundmanage_jsgm").val());
					params.totalinvest = $.trim($("#dlg_fundmanage_ztz").val());
					params.firstquarter = $.trim($("#dlg_fundmanage_d1jd").val());
					params.secondquarter = $.trim($("#dlg_fundmanage_d2jd").val());
					params.thirdquarter = $.trim($("#dlg_fundmanage_d3jd").val());	
					params.fourthquarter = $.trim($("#dlg_fundmanage_d4jd").val());	
			    // 提交方式
				   var action = $("#action").val();	
				//新增的时候才判断密码，修改的时候不需要
					if(params.projectname==null || params.projectname=='')
					{
						showMsg("项目名称不能为空！");
						$("#dlg_fundmanage_xmmc").focus();
						return;
					}
					if(params.bulidscale==null || params.bulidscale=='')
					{
						showMsg("建设规模不能为空！");
						$("#dlg_fundmanage_jsgm").focus();
						return;
					}
					if(params.totalinvest==null || params.totalinvest=='')
					{
						showMsg("总投资不能为空！");
						$("#dlg_fundmanage_ztz").focus();
						return;
					}
					if(params.firstquarter==null || params.firstquarter=='')
					{
						showMsg("第一季度为空！");
						$("#dlg_fundmanage_d1jd").focus();
						return;
					}
					if(params.secondquarter==null || params.secondquarter=='')
					{
						showMsg("第二季度为空！");
						$("#dlg_fundmanage_d2jd").focus();
						return;
					}
					if(params.thirdquarter==null || params.thirdquarter=='')
					{
						showMsg("第三季度为空！");
						$("#dlg_fundmanage_d3jd").focus();
						return;
					}
					if(params.fourthquarter==null || params.fourthquarter=='')
					{
						showMsg("第四季度为空！");
						$("#dlg_fundmanage_d4jd").focus();
						return;
					}
				// 新增和修改通用字符串
				var url ='';
				var indicate='';
				if ("add" == action) 
				{
					
					
					indicate = "确认新增" + params.projectname + ",这个下达资金管理?";
					url = "fundmanage!addFundManage.action";
				} 
				else if ("edit" == action) 
				{
					params.id = $("#dlg_id").val();
					indicate = "确认更新" + params.projectname + ",这个下达资金管理?";
					url = "fundmanage!updateFundManage.action";
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
								clean_input_for_dailog($("#dlg_fundmanage"));
								$('#dlg_fundmanage').dialog('close');
								fundmanage_grid.loadData({page:1});
							} else 
							{
								showMsg(result.msg);
							}
						}
					}); 
				}
				else
				{
				    $('#dlg_fundmanage').dialog('close');
				}
		}
	},
	commitExport : function() 
	{
				var condition = "";
				var serch_xmmc_fundmanage=$("#serch_xmmc_fundmanage")==null?'':$('#serch_xmmc_fundmanage').val();
				if (serch_xmmc_fundmanage && $.trim(serch_xmmc_fundmanage).length > 0) 
				{
					condition += "&projectname=" + encodeURI(encodeURI($.trim(serch_xmmc_fundmanage)));
				}
				
				location.href='fundmanage!exportJobs.action?'+condition;
			
   },
   importfundmanage:function(name,grid)
   	{
		//showMsg("车辆信息批量导入");
		fundmanage_grid.showDialog("下达资金管理导入","import", $("#dlg_fundmanage_upload"),500,400);
	},
   mydownload:function(path)
   {
   		
        location.href='upload!download.action?&path='+ encodeURI(encodeURI(unescape(path))); 
   },
	loadData:function(param){
		var queryP = {};
		queryP.projectname = $('#serch_xmmc_fundmanage').val()==null?'':$('#serch_xmmc_fundmanage').val();
		var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
		queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
		queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
		queryP.sortname = param.sortname? param.sortname : "";
		queryP.sortorder = param.sortorder? param.sortorder : "";
		$.ajax({
			type: "POST",
			url: "fundmanage!getFundManageList.action?operation=init",
			cache: false,
			data: queryP,
			success: function(msg){
				var data=eval("("+msg+")");
				try{
					fundmanage_grid.fundmanage_grid_result.flexAddData(data);
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
		window.location.href = "upload!exportExcelModelForTable.action?suffix="+obj+"&nameType=fundmanageInfo"+"&radom="+Math.random();
     }
 
	 $(function(){
		//初始化所有操作按钮
		var  parm_button = [
			  {name: '新建', bclass: 'add', onpress : fundmanage_grid.add},
			  {name: '修改', bclass: 'edit', onpress : fundmanage_grid.edit},
			  {name: '删除', bclass: 'delete', onpress : fundmanage_grid.del},
			  {name: '导入', bclass: 'distribute', onpress : fundmanage_grid.importfundmanage},
			  {name: '导出', bclass: 'excel', onpress : fundmanage_grid.commitExport}
		 ];
		 //权限操作按钮
		 var colModel = [
						{display: '序号', name : 'xh', width : 40, align: 'center'},
						{display: 'id', name : 'ID', width : 40, align: 'center',hide : true,sortable: true},
						{display: '项目名称', name : 'PROJECTNAME', width : 320,  align: 'left'},
						{display: '建设规模', name : 'BULIDSCALE', width : 150,  align: 'left'},
						{display: '总投资', name : 'TOTALINVEST', width : 150,  align: 'left'},
						{display: '第一季度', name : 'FIRSTQUARTER', width : 150,  align: 'left'},
						{display: '第二季度', name : 'SECONDQUARTER', width : 120,  align: 'left'},
						{display: '第三季度', name : 'THIRDQUARTER', width : 100,  align: 'center'},
						{display: '第四季度', name : 'FOURTHQUARTER', width : 100,  align: 'center'}
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
				fundmanage_grid.bindParamGrid(buttons,colModel);
			}
		});
		 
		/*参数管理-查询按钮功能*/
		$("#btn_fundmanage_search").button({ icons: {primary:'ui-icon-search'} }).on("click",function(){
			fundmanage_grid.loadData({page:1});
		});
				//清空筛选框
		$('#btn_result_empty_fundmanage').on("click",function(){
			$("#serch_xmmc_fundmanage").val("");
			fundmanage_grid.loadData({page:1});
		});
		
	});
</script>
<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='../images/fold.png' onclick="showAndHide(this,'fundmanage_grid');"  class="imgShrotate">
				<font>查询条件</font>
			</h3>
		</div>
	<div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>
					<td >
						<label for="input_1" class="lbl-1">
							项目名称:
						</label>
						<input type="text" id="serch_xmmc_fundmanage" name="serch_xmmc_fundmanage" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
							<button id="btn_fundmanage_search" class="ui-state-default ui-state-qik">查询
						</button>					
						<button id="btn_result_empty_fundmanage" class="ui-state-default ui-state-qik">清空
						</button>
					</td>
				</tr>
			</table>
		</div>
</div>
<div class="div div-2" style="">
	<table id="fundmanage_grid" style="display:none;padding:0 0 0 0;"></table>
</div>
<div id="dlg_fundmanage" style="display:none;overflow-y: hidden;">
	<input type="hidden" id="action" />
	<input type="hidden" id="dlg_id" /> 
	<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">项目名称：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_fundmanage_xmmc" name="dlg_fundmanage_xmmc"  class="wi-ipt-1" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">建设规模：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_fundmanage_jsgm" value="" class="wi-ipt-1" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">总投资（万元）：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_fundmanage_ztz" value="" class="wi-ipt-1" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">第一季度：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_fundmanage_d1jd" value="" class="wi-ipt-1" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">第二季度：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_fundmanage_d2jd" value="" class="wi-ipt-1" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">第三季度：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_fundmanage_d3jd" value="" class="wi-ipt-1" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">第四季度：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_fundmanage_d4jd" value="" class="wi-ipt-1" />
			</td>
		</tr>
		
	</table>
</div>	
<div id="dlg_fundmanage_upload" style="display:none;overflow-y: hidden;">
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
				<input type="file" id="file_name_fundmanage" name="file_name_fundmanage" accept=".xls,.xlsx"/>
			</td>
		</tr>
	</table>
</div>	