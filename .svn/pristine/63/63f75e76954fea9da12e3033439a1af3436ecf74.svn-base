<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/commonCSSJS.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
  <script>x=-38</script>
</head>
<body style="position:relative;">
<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='images/fold.png' onclick="showAndHide(this,'assetinfo_grid');"  class="imgShrotate">
				<font>查询条件</font>
			</h3>
</div>
	<div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>
					<td >
						<label for="input_1" class="lbl-1">
							资产名称:
						</label>
						<input type="text" id="serch_zcmc" name="serch_zcmc" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<label for="input_1" class="lbl-1">
							资产编号:
						</label>
						<input type="text" id="serch_zcbh" name="serch_zcbh" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<label for="input_1" class="lbl-1">
							存放地点：
						</label>
						<input type="text" id="serch_cfdd" name="serch_cfdd" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<button id="btn_assetinfo_search" class="ui-state-default ui-state-qik" >查询
						</button>
						<button id="btn_result_empty" class="ui-state-default ui-state-qik">清空
						</button>
					</td>
				</tr>
			</table>
		</div>
</div>
<div class="div div-2">
	<table id="assetinfo_grid" style="display:none;padding:0 0 0 0;"></table>
</div>
<div id="dlg_assetinfo" style="display:none;overflow-y: hidden;">
	<input type="hidden" id="action" />
	<input type="hidden" id="dlg_id" /> 
	<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">资产名称：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_assetinfo_zcmc" name="dlg_assetinfo_zcmc"  class="wi-ipt-1" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">资产类型：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<select name="dlg_assetinfo_zclx" id="dlg_assetinfo_zclx"  class="wi-ipt-1">
							<option value="">请选择</option>
							<option value="1">房屋及构筑物</option>
							<option value="2">专用设备</option>
							<option value="3">通用设备</option>
							<option value="4">文物和陈列品</option>
							<option value="5">图书、档案</option>
							<option value="6">家具、用具、装具、动植物</option>
						</select>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">存放地点：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_assetinfo_cfdd" value="" class="wi-ipt-1"/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">备注：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_assetinfo_bz" value="" class="wi-ipt-1"/>
			</td>
		</tr>
		
	</table>
	<div style="overflow: hidden">
		
	</div>
</div>
<div id="dlg_assetinfo_upload" style="display:none;overflow-y: hidden;">
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
				<input type="file" id="file_name_assetinfo" name="file_name_assetinfo" accept=".xls,.xlsx"/>
			</td>
		</tr>
	</table>
</div>	



 <script type="text/javascript">
var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
var page_num = $(window.self.document).find("#page_num").val();
var page_num =30;
 var assetinfo_grid = {
	//绑定参数信息grid
	assetinfo_grid_result:"",
	assetinfoGridHeight:flexHeight("assetinfo_grid"),
	dlg_assetinfo:"",
	bindParamGrid:function(queryP,queryM){
		assetinfo_grid.assetinfo_grid_result = $("#assetinfo_grid").flexigrid({
			url:"assetinfo!getAssetInfoList.action?operation=init",
			colModel: queryM,
			buttons : queryP,
			rowId:"id",
			showToggleBtn: true,
			usepager: true,
			title:"资产信息管理列表",
			//rp:pageSize,
			rp:page_num,
			showTableToggleBtn: false,
			resizable: false,
			//height:240,
			height: assetinfo_grid.assetinfoGridHeight,
			singleSelect: true,
			onChangePage:assetinfo_grid.loadData,
			checkbox:false,
			filterParams : [{key : "assetname", name : "#serch_zcmc"},
			                {key : "code", name : "#serch_zcbh"},
			                {key : "place", name : "#serch_cfdd"} ]
		});
	},			
	// 查询子任务并且显示
	showDialog : function(com,operation,divobj,h,w) 
		{
		var dlgParent = divobj.parent();
		var dlgOwn = divobj.clone();
		assetinfo_grid.dlg_assetinfo = divobj.dialog({
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
						assetinfo_grid.saveData(operation);
		              	//$(this).dialog("destroy").remove();
					}
				},
			    close : function() {
		              	dlgOwn.appendTo(dlgParent);
		              	$(this).dialog("destroy").remove();
		         }
			});
			
		assetinfo_grid.dlg_assetinfo.dialog("option", "title", com).dialog("open");
	},
	add:function(com,grid)
	{
		
		if (com == "添加") 
				{
				    assetinfo_grid.showDialog("新增资产信息管理","add",$("#dlg_assetinfo"));
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
					        var indicate = "确认需要删除" + title+ ",这个资产信息管理吗?";	
					        var params=new Object();
				                params.id = id;					
							if(confirm(indicate)) 
							{
								$.ajax({
									type: "POST",
									url: "assetinfo!deleteAssetInfo.action",
									cache: false,
									data: params,
									success: function(msg)
									{
										var result=eval("("+msg+")");
										if (result.msg=="ok") 
										{
											showMsg("删除成功！");
											$('#dlg_assetinfo').dialog('close');
											assetinfo_grid.loadData({page:1});
										} else 
										{
											showMsg(result.msg);
											$('#dlg_assetinfo').dialog('close');
										}
									}
								}); 
							}else
							{
								$('#dlg_assetinfo').dialog('close');
							}
						
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_assetinfo').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_assetinfo').dialog('close');
					}
				}			
	},
	edit:function(com,grid)
	{
		
                if (com == "编辑") 
				{
						
					if($('.trSelected', grid).length==1)
					{
					   assetinfo_grid.showDialog("编辑资产信息管理","edit",$("#dlg_assetinfo"));
				       $("#action").val("edit");
						var id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
						var dlg_assetinfo_zcmc = $('.trSelected', grid).eq(0).find("td:eq(2)").text();
						var dlg_assetinfo_zclx = $('.trSelected', grid).eq(0).find("td:eq(4)").text();
						var dlg_assetinfo_cfdd = $('.trSelected', grid).eq(0).find("td:eq(5)").text();
						var dlg_assetinfo_bz = $('.trSelected', grid).eq(0).find("td:eq(6)").text();
						$("#dlg_id").val(id);	
						$("#dlg_assetinfo_zcmc").val(dlg_assetinfo_zcmc);
						switch(dlg_assetinfo_zclx){
							case "房屋及构筑物":
									$("#dlg_assetinfo_zclx").val("1");
									break;
								case "专用设备":
									$("#dlg_assetinfo_zclx").val("2");
									break;
								case "通用设备":
									$("#dlg_assetinfo_zclx").val("3");
									break;
								case "文物和陈列品":
									$("#dlg_assetinfo_zclx").val("4");
									break;
								case "图书、档案":
									$("#dlg_assetinfo_zclx").val("5");
									break;
								case "家具、用具、装具、动植物":
									$("#dlg_assetinfo_zclx").val("6");
									break;
						}
						$("#dlg_assetinfo_cfdd").val(dlg_assetinfo_cfdd);
						$("#dlg_assetinfo_bz").val(dlg_assetinfo_bz);
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
					queryP.nameType = "assetinfoInfo";
					queryP.suffix = $.trim($("#file_name_assetinfo").val()).split("\.")[1];
					$.ajaxFileUpload({
						url:"upload!importExcel.action?radom="+Math.random(),
						fileElementId:"file_name_assetinfo",
						secureuri:false,
						type:"POST",
						data:queryP,
						dataType:"json",
						success:function(data)
						{
							if(data != null && data != "" && data.result == "00")
							{
								showMsg("资产信息管理导入成功！");
								assetinfo_grid.loadData({page:1});
								$("#dlg_assetinfo_upload").dialog('close');
							}else{
								showMsg("资产信息管理导入失败！");
							}
						},
						error:function(){
							showMsg("文件导入失败");
						}
					});

	          }else{
	          var params=new Object();
				params.assetname =$.trim($("#dlg_assetinfo_zcmc").val());
				params.code =$.trim($("#dlg_assetinfo_zcbh").val());
				params.assettype = $.trim($("#dlg_assetinfo_zclx").val());
				params.place = $.trim($("#dlg_assetinfo_cfdd").val());
				params.remark  = $.trim($("#dlg_assetinfo_bz").val());
			    // 提交方式
				   var action = $("#action").val();	
				//新增的时候才判断密码，修改的时候不需要
					if(params.assetname==null || params.assetname=='')
					{
						showMsg("资产名称不能为空！");
						$("#dlg_assetinfo_zcmc").focus();
						return;
					}
					if(params.assettype==null || params.assettype=='')
					{
						showMsg("资产类型不能为空！");
						$("#dlg_assetinfo_zclx").focus();
						return;
					}
					if(params.place==null || params.place=='')
					{
						showMsg("存放地点不能为空！");
						$("#dlg_assetinfo_cfdd").focus();
						return;
					}
					if(params.remark==null || params.remark=='')
					{
						showMsg("备注不能为空！");
						$("#dlg_assetinfo_bz").focus();
						return;
					}
					
								
				// 新增和编辑通用字符串
				var url ='';
				var indicate='';
				if ("add" == action) 
				{
					indicate = "确认新增" + params.assetname + ",这个资产信息管理?";
					url = "assetinfo!saveAssetInfo.action";
				} 
				else if ("edit" == action) 
				{
					params.id = $("#dlg_id").val();
					indicate = "确认更新" + params.assetname + ",这个资产信息管理?";
					url = "assetinfo!updateAssetInfo.action";
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
								showMsg("提交成功");
								$('#dlg_assetinfo').dialog('close');
								assetinfo_grid.loadData({page:1});
								parent.activeTabAssInfo();
								parent.refreshTabAssetOverView();
							} else 
							{
								showMsg(result.msg);
							}
						}
					}); 
				}
				else
				{
				    $('#dlg_assetinfo').dialog('close');
				}
	          }
			    
		
	},
	// 导出数据
	commitExport : function() 
	{
				var condition = "";
				var serch_zcmc=$("#serch_zcmc")==null?'':$('#serch_zcmc').val();
				if (serch_zcmc && $.trim(serch_zcmc).length > 0) 
				{
					condition += "&assetname=" + encodeURI(encodeURI($.trim(serch_zcmc)));
				}
				var serch_zcbh=$("#serch_zcbh")==null?'':$('#serch_zcbh').val();
				if (serch_zcbh && $.trim(serch_zcbh).length > 0) 
				{
					condition += "&code=" + encodeURI(encodeURI($.trim(serch_zcbh)));
				}
				var serch_cfdd=$("#serch_cfdd")==null?'':$('#serch_cfdd').val();
				if (serch_cfdd && serch_cfdd.trim().length > 0) 
				{
					condition += "&place=" + encodeURI(encodeURI($.trim(serch_cfdd)));
				}
				location.href='assetinfo!exportJobs.action?'+condition;
			
   },
    importassetinfo:function(name,grid)
   	{
		assetinfo_grid.showDialog("资产信息管理导入","import", $("#dlg_assetinfo_upload"),500,400);
	},
   mydownload:function(path)
   {
   		
        location.href='upload!download.action?&path='+ encodeURI(encodeURI(unescape(path))); 
   },
	loadData:function(param){
		var queryP = {};
		queryP.assetname = $('#serch_zcmc').val()==null?'':$('#serch_zcmc').val();
		queryP.code = $('#serch_zcbh').val()==null?'':$('#serch_zcbh').val();
		queryP.place = $('#serch_cfdd').val()==null?'':$('#serch_cfdd').val();
		var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
		queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
		queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
		queryP.sortname = param.sortname? param.sortname : "";
		queryP.sortorder = param.sortorder? param.sortorder : "";
		$.ajax({
			type: "POST",
			url: "assetinfo!getAssetInfoList.action?operation=init",
			cache: false,
			data: queryP,
			success: function(msg){
				var data=eval("("+msg+")");
				try{
					assetinfo_grid.assetinfo_grid_result.flexAddData(data);
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
		window.location.href = "upload!exportExcelModelForTable.action?suffix="+obj+"&nameType=assetinfoInfo"+"&radom="+Math.random();
     }
	
	function uploadpic(assetinfo_file) 
	{
	
		$("#"+assetinfo_file).click();
		$("#"+assetinfo_file).change(function() 
		{        
         $.ajaxFileUpload
          (
            {
                url:'upload!upl.action',//用于文件上传的服务器端请求地址
                secureuri:false,//一般设置为false
                fileElementId:assetinfo_file,//文件上传空间的id属性  <input type="file" id="file" name="file" />
                dataType: 'json',//返回值类型 一般设置为json
                success: function (data, status)  //服务器成功响应处理函数
                {
                    //从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量
                    if(data.mess_state==1)
                    {	
                    	
                       $("#promotionShow_assetinfo").html(data.newFileName);
                       $("#path_assetinfo_file").val(data.rs_path);
                       $("#"+assetinfo_file).val("");
                       $("#sub_assetinfo").val("重新上传");
                    }
                },
                error: function (data, status, e)//服务器响应失败处理函数
                {
                    alert(e);
                    $("#"+assetinfo_file).val("");
                  
                }
            }
        )

		});
	}
 
	 $(function(){
		//初始化所有操作按钮
		var  assetinfo_button = [
			  {name: '添加', bclass: 'add', onpress : assetinfo_grid.add},
			  {name: '编辑', bclass: 'edit', onpress : assetinfo_grid.edit},
			  {name: '删除', bclass: 'delete', onpress : assetinfo_grid.del}
			//{name: '导入', bclass: 'distribute', onpress : assetinfo_grid.importassetinfo},
			//{name: '导出', bclass: 'excel', onpress : assetinfo_grid.commitExport}
		 ];
		 //权限操作按钮
		 var colModel = [
						{display: '序号', name : 'xh', width : 40, align: 'center'},
						{display: 'id', name : 'ID', width : 40, align: 'center',hide : true,sortable: true},
						{display: '资产名称', name : 'ASSETNAME', width : 150,  align: 'left'},
						{display: '资产编号', name : 'ID', width : 150,  align: 'left'},
						{display: '资产类型', name : 'ASSETTYPE', width : 150,  align: 'left',
						process:function(obj){
							switch($(obj).html()){
								case "1":
									$(obj).html("房屋及构筑物");
									break;
								case "2":
									$(obj).html("专用设备");
									break;
								case "3":
									$(obj).html("通用设备");
									break;
								case "4":
									$(obj).html("文物和陈列品");
									break;
								case "5":
									$(obj).html("图书、档案");
									break;
								case "6":
									$(obj).html("家具、用具、装具、动植物");
									break;
							}
						
						}
						
						},
						{display: '存放地点', name : 'PLACE', width : 200, align: 'left'},
						{display: '备注', name : 'REMARK', width : 150,  align: 'left'}					
					];
		
		 //绑定参数
		 assetinfo_grid.bindParamGrid(assetinfo_button,colModel); 
		/*参数管理-查询按钮功能*/
		$("#btn_assetinfo_search").button().on("click",function(){
			assetinfo_grid.loadData({page:1});
		});
				//清空筛选框
		$('#btn_result_empty').on("click",function(){
			$("#serch_zcmc").val("");
			$("#serch_zcbh").val("");
			$("#serch_cfdd").val("");
			assetinfo_grid.loadData({page:1});
		});
		
	});
</script>
</body>
</html>