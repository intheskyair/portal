<%@ page contentType="text/html; charset=UTF-8"%>
<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='../images/fold.png' onclick="showAndHide(this,'plann_grid');"  class="imgShrotate">
				<font>规划名录</font>
			</h3>
		</div>
	<div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>

					<td class="wi-align-l" >
						<label for="input_1" class="lbl-1">
							项目名称:
						</label>
						<input type="text" id="serch_xm" name="serch_xm" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l" >
						<label for="input_1" class="lbl-1">
							证书号:
						</label>
						<input type="text" id="serch_zs" name="serch_zs" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l"  >
						<label for="input_1" class="lbl-1">
							办证单位：
						</label>
						<input type="text" id="serch_dw" name="serch_dw" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l" >
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
	<table id="plann_grid" style="display:none;padding:0 0 0 0;"></table>
</div>
<div id="dlg_plann" style="display:none;overflow-y: hidden;">
	<input type="hidden" id="action" />
	<input type="hidden" id="dlg_id" /> 
	<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">项目名称：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_plann_xm" name="dlg_plann_xm"  class="wi-ipt-1" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">证书编号：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_plann_bh" value="" class="wi-ipt-1" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">办证单位：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_plann_dw" value="" class="wi-ipt-1"/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">用地位置：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_plann_wz" value=""   class="wi-ipt-1"/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">用地面积：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_plann_mj" value="" class="wi-ipt-1"/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">发证日期：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_plann_time"
							readonly class="Wdate" style="width:200px;"
						onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'%y-%M-%d'})"/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label for="input_1">上传文件：</label>
			</td>
			<td class="wi-align-l" style="width:300px;">
			    <img src="../images/loading.gif" id="loading" style="display: none;">
			    <form action="upload!upl.action" method="POST" enctype="multipart/form-data" id="upplann_form" target="result_upfile" >
			    <input type="hidden" name="path_plann_file_plann" id="path_plann_file_plann" />
				<input type="file" id="plann_file1" onchange="FileUp()" name="plann_file1" />
				</form>
				<!--  <input type="button" style="width:50px" value="上传" onclick="uploadpic('plann','plann_file1');"/>-->
				<div id="promotionShow_plann"></div>
				<iframe id="result_upfile" name="result_upfile" style="position:absolute; top:-9999px; left:-9999px">
					
				</iframe>
			</td>
		</tr>
	</table>
	</div>
	<div id="dlg_plann_upload" style="display:none;overflow-y: hidden;">
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
				<input type="file" id="file_name_ghml" name="file_name_ghml" accept=".xls,.xlsx"/>
			</td>
		</tr>
	</table>
</div>
	
<script>		
var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
var page_num = $(window.self.document).find("#page_num").val();
var page_num =30;
var dlg_plann_dialog;
var dlg_plann_load;

function showInfo(rowData){
						var dlg_plann_xm = rowData.PROJECT_NAME;
						var dlg_plann_bh = rowData.ZS_NUM;
						var dlg_plann_dw = rowData.BZ_COMPANY;
						var dlg_plann_wz = rowData.YD_ADDRESS;
						var dlg_plann_mj = rowData.YD_AREA;
						var dlg_plann_time = rowData.FZ_TIME_FORMAT_DATE;
						var promotionShow = rowData.PROMOTIONSHOW;
						$("#dlg_plann_xm").val(dlg_plann_xm);
						$("#dlg_plann_bh").val(dlg_plann_bh);
						$("#dlg_plann_dw").val(dlg_plann_dw);
						$("#dlg_plann_wz").val(dlg_plann_wz);
						$("#dlg_plann_mj").val(dlg_plann_mj);
						$("#dlg_plann_time").val(dlg_plann_time);
						$("#promotionShow_plann").html(promotionShow);
						
						$("#plann_file1").hide();
						
						var dlgParent = $("#dlg_plann").parent();
				        var dlgOwn = $("#dlg_plann").clone();
				        var dlg_plann_ck =
					    $("#dlg_plann").dialog({
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
									clean_input_for_dailog($("#dlg_plann"));
									$("#upplann_form")[0].reset();
								    $("#promotionShow_plann").html("");
								    $("#plann_file1").show();
			         				$(this).dialog("destroy").remove();
			         			}
							}
					    });
					     dlg_plann_ck.dialog("open");
}


//ie8下面的弹窗必须改成这样的方式
var	dlg_plann = function(data){
	dlg_plann_dialog = $("#dlg_plann").dialog({
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
							plann_grid.saveData(data.operation);
							//clean_input_for_dailog($("#dlg_plann"));
						},
						"关闭": function() {
							clean_input_for_dailog($("#dlg_plann"));
							
							$("#upplann_form")[0].reset();
						         $("#promotionShow_plann").html("");
							//console.log(dlgParent.attr("id"));
							//$(this).dialog("destroy").remove();
							//dlgOwn.appendTo(dlgParent);
	         				$(this).dialog("close");
	         			}
					}
				});
				
				dlg_plann_load = $("#dlg_plann_upload").dialog({
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
							plann_grid.saveData(data.operation);
							clean_input_for_dailog($("#dlg_plann_upload"));
						},
						"关闭": function() {
							clean_input_for_dailog($("#dlg_plann_upload"));
							//console.log(dlgParent.attr("id"));
							//$(this).dialog("destroy").remove();
							//dlgOwn.appendTo(dlgParent);
	         				$(this).dialog("close");
	         			}
					}
				});
				
};
var plann_grid = {
	//绑定参数信息grid
    plann_grid_result:"",
	plannGridHeight:flexHeight("plann_grid"),
	bindParamGrid:function(queryP,queryM){
		plann_grid.plann_grid_result = $("#plann_grid").flexigrid({
			url:"plann!plannIngList.action?operation=init",
			colModel: queryM,
			buttons : queryP,
			rowId:"id",
			showToggleBtn: true,
			usepager: true,
			title:"规划项目列表",
			onRowDblclick:showInfo,
			//rp:pageSize,
			rp:page_num,
			showTableToggleBtn: false,
			resizable: false,
			height: plann_grid.plannGridHeight,
			singleSelect: true,
			onChangePage:plann_grid.loadData,
			checkbox:false,
			filterParams : [{key : "project_name", name : "#serch_xm"},
			                {key : "zs_num", name : "#serch_zs"},
			                {key : "bz_company", name : "#serch_dw"} ]
		});
	},			
		// 查询子任务并且显示
	showDialog : function(com,operation,divobj,h,w) 
		{
		var dlgParent = divobj.parent();
		var dlgOwn = divobj.clone();
		plann_grid.dlg_plann = divobj.dialog({
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
						plann_grid.saveData(operation);
		              	//$(this).dialog("destroy").remove();
					}
				},
			    close : function() {
		              	dlgOwn.appendTo(dlgParent);
		              	$(this).dialog("destroy").remove();
		         }
			});
			
		plann_grid.dlg_plann.dialog("option", "title", com).dialog("open");
	},	
	add:function(com,grid)
	{
		
		if (com == "新建") 
				{
				    plann_grid.showDialog("新增规划名录","add", $("#dlg_plann"));
					$("#action").val("add");
					$("#dlg_plann_time").val(new Date().format("yyyy-MM-dd"));
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
									url: "plann!delplann.action",
									cache: false,
									data: params,
									success: function(msg)
									{
										var result=eval("("+msg+")");
										if (result.msg=="ok") 
										{
											showMsg("删除成功！");
											$('#dlg_plann').dialog('close');
											plann_grid.loadData({page:1});
										} else 
										{
											showMsg(result.msg);
											$('#dlg_plann').dialog('close');
										}
									}
								}); 
							}else
							{
								$('#dlg_plann').dialog('close');
							}
						
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_plann').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_plann').dialog('close');
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
					   plann_grid.showDialog("编辑规划名录","edit", $("#dlg_plann"));
				       $("#action").val("edit");
						var id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
						var dlg_plann_xm = $('.trSelected', grid).eq(0).find("td:eq(2)").text();
						var dlg_plann_bh = $('.trSelected', grid).eq(0).find("td:eq(3)").text();
						var dlg_plann_dw = $('.trSelected', grid).eq(0).find("td:eq(4)").text();
						var dlg_plann_wz = $('.trSelected', grid).eq(0).find("td:eq(5)").text();
						var dlg_plann_mj = $('.trSelected', grid).eq(0).find("td:eq(6)").text();
						var dlg_plann_time = $('.trSelected', grid).eq(0).find("td:eq(7)").text();
						var path_plann_file = $('.trSelected', grid).eq(0).find("td:eq(8)").text();
						var promotionShow = $('.trSelected', grid).eq(0).find("td:eq(9)").text();
						$("#dlg_id").val(id);	
						$("#dlg_plann_xm").val(dlg_plann_xm);
						$("#dlg_plann_bh").val(dlg_plann_bh);
						$("#dlg_plann_dw").val(dlg_plann_dw);
						$("#dlg_plann_wz").val(dlg_plann_wz);
						$("#dlg_plann_mj").val(dlg_plann_mj);
						$("#dlg_plann_time").val(dlg_plann_time);
						$("#path_plann_file_plann").val(path_plann_file);
						$("#promotionShow_plann").html(promotionShow);					
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_plann').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_plann').dialog('close');
						showMsg("未选中行！");
					}
				}
	},
	saveData:function(operation)
	{
	             if(operation == "import")	           
	                 {
	                 
	          		var queryP = {};
					queryP.nameType = "ghml";
					queryP.suffix = $.trim($("#file_name_ghml").val()).split("\.")[1];
					if(queryP.suffix === '' || queryP.suffix === undefined || typeof queryP.suffix === 'undefined' )
						 {
							showMsg("未选择需要导入的文件！");
							return;
						 }
					$.ajaxFileUpload({
						url:"upload!importExcel.action?radom="+Math.random(),
						fileElementId:"file_name_ghml",
						secureuri:false,
						type:"POST",
						data:queryP,
						dataType:"json",
						success:function(data)
						{
							if(data != null && data != "" && data.result == "00")
							{
								showMsg("规划名录导入成功！");
								plann_grid.loadData({page:1});
								$("#dlg_plann_upload").dialog('close');
							}else{
								showMsg("规划名录导入失败！");
							}
						},
						error:function(){
							showMsg("文件导入失败");
						}
					});

	          }else{
			    var params=new Object();
				params.project_name = $.trim($("#dlg_plann_xm").val());
				params.zs_num = $.trim($("#dlg_plann_bh").val());
				params.bz_company = $.trim($("#dlg_plann_dw").val());
				params.yd_address  = $.trim($("#dlg_plann_wz").val());
				params.yd_area  = $.trim($("#dlg_plann_mj").val());
				params.fz_time  = $.trim($("#dlg_plann_time").val());
				params.file_path  = $.trim($("#path_plann_file_plann").val());
				params.promotionShow  = $.trim($("#promotionShow_plann").html());
				var reg1 = /^[1-9]\d*$/;
				var reg2 = /^([0-9])|([1-9]\d+)\.\d?$/;
			    // 提交方式
				   var action = $("#action").val();	
				//新增的时候才判断密码，修改的时候不需要
					if(params.project_name==null || params.project_name=='')
					{
						showMsg("项目名称不能为空！");
						$("#dlg_plann_xm").focus();
						return;
					}
					if(params.zs_num==null || params.zs_num=='')
					{
						showMsg("证书编号不能为空！");
						$("#dlg_plann_bh").focus();
						return;
					}
					if(params.bz_company==null || params.bz_company=='')
					{
						showMsg("办证单位不能为空！");
						$("#dlg_plann_dw").focus();
						return;
					}
					if(params.yd_address==null || params.yd_address=='')
					{
						showMsg("用地位置不能为空！");
						$("#dlg_plann_wz").focus();
						return;
					}
					if(params.yd_area==null || params.yd_area=='')
					{
						showMsg("用地面积不能为空！");
						$("#dlg_plann_mj").focus();
						return;
					}else if(reg1.test(params.yd_area) == false && reg2.test(params.yd_area) == false){
					      showMsg("用地面积只能输入整数或小数！");
						$("#dlg_plann_mj").focus();
						return; 
					}
					if(params.fz_time==null || params.fz_time=='')
					{
						showMsg("发证日期不能为空！");
						$("#dlg_plann_time").focus();
						return;
					}				
				// 新增和编辑通用字符串
				var url ='';
				var indicate='';
				if ("add" == action) 
				{
					indicate = "确认新增" + params.project_name + ",这个项目规划?";
					url = "plann!saveplann.action";
				} 
				else if ("edit" == action) 
				{
					params.id = $("#dlg_id").val();
					indicate = "确认更新" + params.project_name + ",这个项目规划?";
					url = "plann!updateplann.action";
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
								clean_input_for_dailog($("#dlg_plann"));
								$("#upplann_form")[0].reset();
						         $("#promotionShow_plann").html("");
								$('#dlg_plann').dialog('close');
								plann_grid.loadData({page:1});
							} else 
							{
								showMsg(result.msg);
							}
						}
					}); 
				}
				else
				{
				    $('#dlg_plann').dialog('close');
				}
				}
		
	},
	// 导出数据
	commitExport : function() 
	{
				var condition = "";
				var serch_xm=$("#serch_xm")==null?'':$('#serch_xm').val();
				if (serch_xm && $.trim(serch_xm).length > 0) 
				{
					condition += "project_name=" + encodeURI(encodeURI($.trim(serch_xm)));
				}
				var serch_zs=$("#serch_zs")==null?'':$('#serch_zs').val();
				if (serch_zs && $.trim(serch_zs).length > 0) 
				{
					condition += "&zs_num=" + encodeURI(encodeURI($.trim(serch_zs)));
				}
				var serch_dw=$("#serch_dw")==null?'':$('#serch_dw').val();
				if (serch_dw && $.trim(serch_dw).length > 0) 
				{
					condition += "&bz_company=" + encodeURI(encodeURI($.trim(serch_dw)));
				}
				location.href='plann!exportJobs.action?'+condition;
				
			
   },
   //导入excel
   importghml:function(name,grid)
   	{
		//showMsg("车辆信息批量导入");
		plann_grid.showDialog("规划名录导入","import", $("#dlg_plann_upload"),500,400);
	},
   //下载附件
   mydownload:function(path)
   {
        location.href='upload!download.action?&path='+ encodeURI(encodeURI(unescape(path))); 
   },
	loadData:function(param){
		var queryP = {};
		queryP.project_name = $('#serch_xm').val()==null?'':$('#serch_xm').val();
		queryP.zs_num = $('#serch_zs').val()==null?'':$('#serch_zs').val();
		queryP.bz_company = $('#serch_dw').val()==null?'':$('#serch_dw').val();
		var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
		queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
		queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
		queryP.sortname = param.sortname? param.sortname : "";
		queryP.sortorder = param.sortorder? param.sortorder : "";
		$.ajax({
			type: "POST",
			url: "plann!plannIngList.action?operation=init",
			cache: false,
			data: queryP,
			success: function(msg){
				var data=eval("("+msg+")");
				try{
					plann_grid.plann_grid_result.flexAddData(data);
				}catch(e){
					showErrorMsg("查询结果信息出错！");
				}
			}
		}); 
	}
};



		function FileUp() 
		{        
        /* $.ajaxFileUpload
          (
            {
                url:'upload!upl.action',//用于文件上传的服务器端请求地址
                secureuri:false,//一般设置为false
                fileElementId:plann_file,//文件上传空间的id属性  <input type="file" id="file" name="file" />
                dataType: 'json',//返回值类型 一般设置为json
                success: function (data, status)  //服务器成功响应处理函数
                {
                    //从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量
                    if(data.mess_state==1)
                    {
                       $("#promotionShow_"+file_str).html(data.newFileName);
                       $("#path_plann_file_"+file_str).val(data.rs_path);
                       $("#"+plann_file).val("");
                    }
                },
                error: function (data, status, e)//服务器响应失败处理函数
                {
                    alert(e);
                    $("#"+plann_file).val("");
                }
            }
        );*/
           $("#upplann_form").submit();
		};
	
	 $(function(){
		//初始化所有操作按钮
		 var  parm_button = [
			  {name: '新建', bclass: 'add', onpress : plann_grid.add},
			  {name: '修改', bclass: 'edit', onpress : plann_grid.edit},
			  {name: '删除', bclass: 'delete', onpress : plann_grid.del},
			  {name: '导入', bclass: 'distribute', onpress : plann_grid.importghml},
			  {name: '导出', bclass: 'excel', onpress : plann_grid.commitExport}
		 ];
		 //权限操作按钮
		 var colModel = [						
	            {display: '序号', name : 'xh', width : 40, align: 'center'},
				{display: 'id', name : 'ID', width : 40, align: 'center',hide : true,sortable: true},
				{display: '项目名称', name : 'PROJECT_NAME', width : 150, align: 'left',sortable: true},
				{display: '证书号', name : 'ZS_NUM', width : 150, align: 'left',sortable: true},
				{display: '办证单位', name : 'BZ_COMPANY', width : 120, align: 'left',sortable: true},
				{display: '用地位置', name : 'YD_ADDRESS', width : 120,  align: 'left',sortable : true},
				{display: '用地面积', name : 'YD_AREA', width : 120,  align: 'left',sortable : true},
				{display: '发证日期', name : 'FZ_TIME_FORMAT_DATE', width : 120,  align: 'left',sortable : false},
				{display: '文件路径', name : 'FILE_PATH', width : 120,  align: 'left',sortable : true,hide : true},
				{display: '文件名', name : 'PROMOTIONSHOW', width : 200,  align: 'left',sortable : true},
				{display: '下载文件', name : 'OP', width : 100,  align: 'center',
						process:function(obj, pid, $td)
						{
							$tr = $td.closest("tr");
							var file_path = $('div', $tr).eq(8).text();
							var filename=$.trim($('div', $tr).eq(9).text());
							var  str_path = file_path.replace(/\@/g,"\\");
							var htmlContent="";
							if(filename.length == 0){
								htmlContent = '&nbsp;<span style="color:gray;cursor:pointer;disabled:true">下载</span>';
								}
							else{
								htmlContent = '&nbsp;<span style="color:blue;cursor:pointer" onclick="plann_grid.mydownload(\'' + escape(str_path) +'\')">下载</span>';
							}
							$(obj).html(htmlContent);
						}
				}
		 ];
// 		 	 			初始化数据
// 		 plann_grid.bindParamGrid(parm_button,colModel); 
		 
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
// 		 param_result.bindParamGrid(parm_button,colModel);
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
				plann_grid.bindParamGrid(buttons,colModel);
			}
		});
			 		
		 	 			
		 	 			
		 		/*参数管理-查询按钮功能*/
		$('#btn_plann_search').button().on("click",function(){
			plann_grid.loadData({page:1});
		});
				//清空筛选框
		$('#btn_result_empty').on("click",function(){
			$("#serch_xm").val("");
			$("#serch_zs").val("");
			$("#serch_dw").val("");
		}); 
		 		 
	});
	
	//导入的模板文件
	function downloadModel(obj)
	{
	    if(obj != null && obj != "")
		window.location.href = "upload!exportExcelModelForTable.action?suffix="+obj+"&nameType=ghml"+"&radom="+Math.random();
     }
	
	   //调用返回上传结果 
    function get_upfile_result(res){
     	if(1 == Number(res.mess_state)){
     		alert(res.message);
     		$("#promotionShow_plann").html(res.newFileName);
	        $("#path_plann_file_plann").val(res.rs_path);
     	}else{
     		alert(res.message);
     	}
    };
	/*function uploadpic(file_str,plann_file1) 
	{
		$("#"+plann_file).click();
		$("#"+plann_file).change(function() 
		{        
         $.ajaxFileUpload
          (
            {
                url:'upload!upl.action',//用于文件上传的服务器端请求地址
                secureuri:false,//一般设置为false
                fileElementId:plann_file,//文件上传空间的id属性  <input type="file" id="file" name="file" />
                dataType: 'json',//返回值类型 一般设置为json
                success: function (data, status)  //服务器成功响应处理函数
                {
                    //从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量
                    if(data.mess_state==1)
                    {
                       $("#promotionShow_"+file_str).html(data.newFileName);
                       $("#path_plann_file_"+file_str).val(data.rs_path);
                       $("#"+plann_file).val("");
                    }
                },
                error: function (data, status, e)//服务器响应失败处理函数
                {
                    alert(e);
                    $("#"+plann_file).val("");
                }
            }
        )

		});
	}
	*/
	
	
</script>
