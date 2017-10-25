 <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 <script type="text/javascript">
var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
var page_num = $(window.self.document).find("#page_num").val();
var page_num =30;

var dlg_startoperation_dialog;
var dlg_startoperation_load;

function showInfo(rowData){
						var dlg_startoperation_wjm = rowData.FILENAME;
						var dlg_startoperation_fbz = rowData.PUBLISHER;
						var dlg_startoperation_lx = rowData.TYPE;
						$("#dlg_startoperation_fbz").val(dlg_startoperation_fbz);
						$("#dlg_startoperation_lx").val(dlg_startoperation_lx);
						$("#promotionShow_startoperation").html(dlg_startoperation_wjm);
						
						$("#startoperation_file").hide();
						
						var dlgParent = $("#dlg_startoperation").parent();
				        var dlgOwn = $("#dlg_startoperation").clone();
				        var dlg_startoperation_ck =
					    $("#dlg_startoperation").dialog({
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
									clean_input_for_dailog($("#dlg_startoperation"));
									$("#upfile_startoperation_form")[0].reset();
								    $("#promotionShow_startoperation").html("");
								    $("#startoperation_file").show();
			         				$(this).dialog("destroy").remove();
			         			}
							}
					    });
					    dlg_startoperation_ck.dialog("open");
					    
}


//ie8下面的弹窗必须改成这样的方式
var	dlg_startoperation = function(data){
	dlg_startoperation_dialog = $("#dlg_startoperation").dialog({
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
							startoperation_grid.saveData(data.operation);
						},
						"关闭": function() {
							clean_input_for_dailog($("#dlg_startoperation"));
							$("#upfile_startoperation_form")[0].reset();
						         $("#promotionShow_startoperation").html("");
							//console.log(dlgParent.attr("id"));
							//$(this).dialog("destroy").remove();
							//dlgOwn.appendTo(dlgParent);
	         				$(this).dialog("close");
	         			}
					}
				});
				
				dlg_startoperation_load = $("#dlg_startoperation_upload").dialog({
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
							startoperation_grid.saveData(data.operation);
					
						},
						"关闭": function() {
							clean_input_for_dailog($("#dlg_startoperation_upload"));
							//console.log(dlgParent.attr("id"));
							//$(this).dialog("destroy").remove();
							//dlgOwn.appendTo(dlgParent);
	         				$(this).dialog("close");
	         			}
					}
				});
				
  
				
};
 var startoperation_grid = {
	//绑定参数信息grid
	startoperation_grid_result:"",
	startoperationGridHeight:flexHeight("startoperation_grid"),
	bindParamGrid:function(queryP,queryM){
		startoperation_grid.startoperation_grid_result = $("#startoperation_grid").flexigrid({
			url:"startoperation!getStartOperationList.action?operation=init",
			colModel: queryM,
			buttons : queryP,
			rowId:"id",
			showToggleBtn: true,
			usepager: true,
			title:"开工备案列表",
			//rp:pageSize,
			onRowDblclick:showInfo,
			rp:page_num,
			showTableToggleBtn: false,
			resizable: false,
			height: startoperation_grid.startoperationGridHeight,
			singleSelect: true,
			onChangePage:startoperation_grid.loadData,
			checkbox:false,
			filterParams : [{key : "filename", name : "#serch_wjm_startoperation"},
			                {key : "type", name : "#serch_lx_startoperation"},
			                {key : "publisher", name : "#serch_fbz_startoperation"} ]
		});
	},			
	// 查询子任务并且显示
	showDialog : function(com,operation,divobj,h,w) 
		{
		var dlgParent = divobj.parent();
		var dlgOwn = divobj.clone();
		startoperation_grid.dlg_startoperation = divobj.dialog({
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
						startoperation_grid.saveData(operation);
		              	//$(this).dialog("destroy").remove();
					}
				},
			    close : function() {
		              	dlgOwn.appendTo(dlgParent);
		              	$(this).dialog("destroy").remove();
		         }
			});
			
		startoperation_grid.dlg_startoperation.dialog("option", "title", com).dialog("open");
	},
	add:function(com,grid)
	{
		
		if (com == "新建") 
				{
				    startoperation_grid.showDialog("新增开工备案","add",$("#dlg_startoperation"));
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
					        var indicate = "确认需要删除" + title+ ",这个开工备案吗?";	
					        var params=new Object();
				                params.id = id;					
							if(confirm(indicate)) 
							{
								$.ajax({
									type: "POST",
									url: "startoperation!deleteStartOperation.action",
									cache: false,
									data: params,
									success: function(msg)
									{
										var result=eval("("+msg+")");
										if (result.msg=="ok") 
										{
											showMsg("删除成功！");
											$('#dlg_startoperation').dialog('close');
											startoperation_grid.loadData({page:1});
										} else 
										{
											showMsg(result.msg);
											$('#dlg_startoperation').dialog('close');
										}
									}
								}); 
							}else
							{
								$('#dlg_startoperation').dialog('close');
							}
						
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_startoperation').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_startoperation').dialog('close');
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
					   startoperation_grid.showDialog("修改开工备案","edit",$("#dlg_startoperation"));
				       $("#action").val("edit");
						var id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
						var dlg_startoperation_wjm = $('.trSelected', grid).eq(0).find("td:eq(2)").text();
						var dlg_startoperation_fbz = $('.trSelected', grid).eq(0).find("td:eq(3)").text();
						var dlg_startoperation_lx = $('.trSelected', grid).eq(0).find("td:eq(4)").text();
						var dlg_startoperation_cjsj = $('.trSelected', grid).eq(0).find("td:eq(5)").text();
						var dlg_startoperation_wjlj = $('.trSelected', grid).eq(0).find("td:eq(6)").text();
						$("#dlg_id").val(id);	
						$("#dlg_startoperation_fbz").val(dlg_startoperation_fbz);
						$("#dlg_startoperation_lx").val(dlg_startoperation_lx);
						$("#promotionShow_startoperation").html(dlg_startoperation_wjm);
						$("#path_startoperation_file").val(dlg_startoperation_wjlj);
						var reupload =$.trim(dlg_startoperation_wjm);
						if((reupload!=null)&&(reupload!="")){
						$("#sub_startoperation").val("重新上传");
						}
						
						
						
						
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
					queryP.nameType = "startoperationInfo";
					queryP.suffix = $.trim($("#file_name_startoperation").val()).split("\.")[1];
					if(queryP.suffix === '' || queryP.suffix === undefined || typeof queryP.suffix === 'undefined' )
						 {
							showMsg("未选择需要导入的文件！");
							return;
						 }
					$.ajaxFileUpload({
						url:"upload!importExcel.action?radom="+Math.random(),
						fileElementId:"file_name_startoperation",
						secureuri:false,
						type:"POST",
						data:queryP,
						dataType:"json",
						success:function(data)
						{
							if(data != null && data != "" && data.result == "00")
							{
								showMsg("开工备案导入成功！");
								startoperation_grid.loadData({page:1});
								$("#dlg_startoperation_upload").dialog('close');
							}else{
								showMsg("开工备案导入失败！");
							}
						},
						error:function(){
							showMsg("文件导入失败");
						}
					});

	          }else{
			    var params=new Object();
				params.filename = $.trim($("#promotionShow_startoperation").html());
				params.publisher = $.trim($("#dlg_startoperation_fbz").val());
				params.type = $.trim($("#dlg_startoperation_lx").val());
				params.filepath=$.trim($("#path_startoperation_file").val());
				params.createtime=$.trim(new Date().format("yyyy-MM-dd hh:mm:ss"));
				
			    // 提交方式
				   var action = $("#action").val();	
				//新增的时候才判断密码，修改的时候不需要
					if(params.publisher==null || params.publisher=='')
					{
						showMsg("发布者不能为空！");
						$("#dlg_startoperation_fbz").focus();
						return;
					}
					if(params.type==null || params.type=='')
					{
						showMsg("类型不能为空！");
						$("#dlg_startoperation_lx").focus();
						return;
					}
					if(params.filepath==null || params.filepath=='')
					{
						showMsg("请上传文件！");
						return;
					}
					
				// 新增和修改通用字符串
				var url ='';
				var indicate='';
				if ("add" == action) 
				{
					indicate = "确认新增" + params.filename + ",这个开工备案?";
					url = "startoperation!addStartOperation.action";
				} 
				else if ("edit" == action) 
				{
					params.id = $("#dlg_id").val();
					indicate = "确认更新" + params.filename + ",这个开工备案?";
					url = "startoperation!updateStartOperation.action";
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
								clean_input_for_dailog($("#dlg_startoperation"));
							$("#upfile_startoperation_form")[0].reset();
						         $("#promotionShow_startoperation").html("");
								$('#dlg_startoperation').dialog('close');
								startoperation_grid.loadData({page:1});
							} else 
							{
								showMsg(result.msg);
							}
						}
					}); 
				}
				else
				{
				    $('#dlg_startoperation').dialog('close');
				}
		}
	},
	commitExport : function() 
	{
				var condition = "";
				var serch_wjm_startoperation=$("#serch_wjm_startoperation")==null?'':$('#serch_wjm_startoperation').val();
				if (serch_wjm_startoperation && $.trim(serch_wjm_startoperation).length > 0) 
				{
					condition += "&filename=" + encodeURI(encodeURI($.trim(serch_wjm_startoperation)));
				}
				var serch_lx_startoperation=$("#serch_lx_startoperation")==null?'':$('#serch_lx_startoperation').val();
				if (serch_lx_startoperation && $.trim(serch_lx_startoperation).length > 0) 
				{
					condition += "&type=" + encodeURI(encodeURI( $.trim(serch_lx_startoperation)));
				}
				var serch_fbz_startoperation=$("#serch_fbz_startoperation")==null?'':$('#serch_fbz_startoperation').val();
				if (serch_fbz_startoperation && $.trim(serch_fbz_startoperation).length > 0) 
				{
					condition += "&publisher=" + encodeURI(encodeURI($.trim(serch_fbz_startoperation)));
				}
				location.href='startoperation!exportJobs.action?'+condition;
			
   },
   importstartoperation:function(name,grid)
   	{
		//showMsg("车辆信息批量导入");
		startoperation_grid.showDialog("开工备案导入","import", $("#dlg_startoperation_upload"),500,400);
	},
   mydownload:function(path)
   {
   		
        location.href='upload!download.action?&path='+ encodeURI(encodeURI(unescape(path))); 
   },
	loadData:function(param){
		var queryP = {};
		queryP.filename = $('#serch_wjm_startoperation').val()==null?'':$('#serch_wjm_startoperation').val();
		queryP.type = $('#serch_lx_startoperation').val()==null?'':$('#serch_lx_startoperation').val();
		queryP.publisher = $('#serch_fbz_startoperation').val()==null?'':$('#serch_fbz_startoperation').val();
		var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
		queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
		queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
		queryP.sortname = param.sortname? param.sortname : "";
		queryP.sortorder = param.sortorder? param.sortorder : "";
		$.ajax({
			type: "POST",
			url: "startoperation!getStartOperationList.action?operation=init",
			cache: false,
			data: queryP,
			success: function(msg){
				var data=eval("("+msg+")");
				try{
					startoperation_grid.startoperation_grid_result.flexAddData(data);
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
		window.location.href = "upload!exportExcelModelForTable.action?suffix="+obj+"&nameType=startoperationInfo"+"&radom="+Math.random();
     }
	
		function FileUp() 
		{       
         /*$.ajaxFileUpload
          (
            {
                url:'upload!upl.action',//用于文件上传的服务器端请求地址
                secureuri:false,//一般设置为false
                fileElementId:startoperation_file,//文件上传空间的id属性  <input type="file" id="file" name="file" />
                dataType: 'json',//返回值类型 一般设置为json
                success: function (data, status)  //服务器成功响应处理函数
                {
                    //从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量
                    if(data.mess_state==1)
                    {
                       $("#promotionShow_startoperation").html(data.newFileName);
                       $("#path_startoperation_file").val(data.rs_path);
                       $("#"+startoperation_file).val("");
                       $("#sub_startoperation").val("重新上传");
                    }
                },
                error: function (data, status, e)//服务器响应失败处理函数
                {
                    alert(e);
                    $("#"+startoperation_file).val("");
                  
                }
            }
        )*/
         $("#upfile_startoperation_form").submit();
		};
	
 
	 $(function(){
		//初始化所有操作按钮
		var  parm_button = [
			  {name: '新建', bclass: 'add', onpress : startoperation_grid.add},
			  {name: '修改', bclass: 'edit', onpress : startoperation_grid.edit},
			  {name: '删除', bclass: 'delete', onpress : startoperation_grid.del},
			  {name: '导入', bclass: 'distribute', onpress : startoperation_grid.importstartoperation},
			  {name: '导出', bclass: 'excel', onpress : startoperation_grid.commitExport}
		 ];
		 //权限操作按钮
		 var colModel = [
						{display: '序号', name : 'xh', width : 40, align: 'center'},
						{display: 'id', name : 'ID', width : 40, align: 'center',hide : true,sortable: true},
						{display: '文件名', name : 'FILENAME', width : 400,  align: 'left',},
						{display: '发布者', name : 'PUBLISHER', width : 150,  align: 'left'},
						{display: '类型', name : 'TYPE', width : 150,  align: 'left'},
						{display: '创建时间', name : 'CREATETIME', width : 150,  align: 'left'},
						{display: '文件路径', name : 'FILEPATH', width : 120,  align: 'left',sortable : false,hide : true},
						{display: '下载文件', name : 'OP', width : 100,  align: 'center',
						
						
						process:function(obj, pid, $td)
							{
								$tr = $td.closest("tr");
								var file_path = $('div', $tr).eq(6).text();
								var filename=$.trim($('div', $tr).eq(2).text());
								var  str_path = file_path.replace(/\@/g,"\\");
								var htmlContent="";
								if(filename.length == 0){
									htmlContent = '&nbsp;<span style="color:gray;cursor:pointer;disabled:true">下载</span>';
								}else{
									htmlContent = '&nbsp;<span style="color:blue;cursor:pointer" onclick="startoperation_grid.mydownload(\'' + escape(str_path) +'\')">下载</span>';	
								}
								$(obj).html(htmlContent);
							}
						}
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
				startoperation_grid.bindParamGrid(buttons,colModel);
			}
		});
		 
		 
		/*参数管理-查询按钮功能*/
		$("#btn_startoperation_search").button({ icons: {primary:'ui-icon-search'} }).on("click",function(){
			startoperation_grid.loadData({page:1});
		});
				//清空筛选框
		$('#btn_result_empty_startoperation').on("click",function(){
			$("#serch_wjm_startoperation").val("");
			$("#serch_lx_startoperation").val("");
			$("#serch_fbz_startoperation").val("");
			startoperation_grid.loadData({page:1});
		});
		
	});
	
	
	//调用返回上传结果 
    function get_upfile_result(res){
     	if(1 == Number(res.mess_state)){
     		alert(res.message);
     		$("#promotionShow_startoperation").html(res.newFileName);
	        $("#path_startoperation_file").val(res.rs_path);
     	}else{
     		alert(res.message);
     	}
    };
</script>
<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='../images/fold.png' onclick="showAndHide(this,'startoperation_grid');"  class="imgShrotate">
				<font>查询条件</font>
			</h3>
		</div>
	<div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>
					<td >
						<label for="input_1" class="lbl-1">
							文件名:
						</label>
						<input type="text" id="serch_wjm_startoperation" name="serch_wjm_startoperation" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<label for="input_1" class="lbl-1">
							类型:
						</label>
						<input type="text" id="serch_lx_startoperation" name="serch_lx_startoperation" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<label for="input_1" class="lbl-1">
							发布者：
						</label>
						<input type="text" id="serch_fbz_startoperation" name="serch_fbz_startoperation" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<button id="btn_startoperation_search" class="ui-state-default ui-state-qik">查询
						</button>					
						<button id="btn_result_empty_startoperation" class="ui-state-default ui-state-qik">清空
						</button>
					</td>
				</tr>
			</table>
		</div>
</div>
<div class="div div-2" style="">
	<table id="startoperation_grid" style="display:none;padding:0 0 0 0;"></table>
</div>
<div id="dlg_startoperation" style="display:none;overflow-y: hidden;">
	<input type="hidden" id="action" />
	<input type="hidden" id="dlg_id" /> 
	<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">类型：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_startoperation_lx" name="dlg_startoperation_lx"  class="wi-ipt-1" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">发布者：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_startoperation_fbz" value="" class="wi-ipt-1" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">上传文件：</label>
			</td>
			<td class="wi-align-l" style="width:300px;">
			 <img src="../images/loading.gif" id="loading" style="display: none;">
			    <form action="upload!upl.action" 
			    	method="POST" enctype="multipart/form-data" id="upfile_startoperation_form" target="result_startoperation_upfile" >
			    <input type="hidden" name="path_startoperation_file" id="path_startoperation_file" />
				<input type="file" id="startoperation_file" onchange="FileUp()" name="startoperation_file"/>
				</form>
				<!-- <input type="button" id="sub_startoperation" value="上传" onclick="uploadpic('startoperation_file');"> -->
				<div id="promotionShow_startoperation"></div>
				<iframe id="result_startoperation_upfile" name="result_startoperation_upfile" style="position:absolute; top:-9999px; left:-9999px"></iframe>
			</td>
		</tr>
	</table>
	<div style="overflow: hidden">
		
	</div>
</div>	
<div id="dlg_startoperation_upload" style="display:none;overflow-y: hidden;">
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
				<input type="file" id="file_name_startoperation" name="file_name_startoperation" accept=".xls,.xlsx"/>
			</td>
		</tr>
	</table>
</div>	