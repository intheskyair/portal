<%@ page contentType="text/html; charset=UTF-8"%>
<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='../images/fold.png' onclick="showAndHide(this,'zbyqh_grid');"  class="imgShrotate">
				<font>${head}</font>
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
					<td class="wi-align-l">
						<label for="input_1" class="lbl-1">
							招标编号:
						</label>
						<input type="text" id="serch_project_bianhao" name="serch_project_bianhao" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<button id="btn_zbyqh_search" class="ui-state-default ui-state-qik">查询
						</button>					
						<button id="btn_zbyqh_empty" class="ui-state-default ui-state-qik">清空
						</button>
					</td>
				</tr>
			</table>
		</div>
</div>
<div class="div div-2" style="">
	<table id="zbyqh_grid" style="display:none;padding:0 0 0 0;"></table>
</div>
<div id="dlg_zbyqh" style="display:none;overflow: auto;">
	<input type="hidden" id="action" />
	<input type="hidden" id="dlg_id" /> 
	
<table class="table12" cellpadding="0" cellspacing="0">
  <tr>
   <td><label style="color:red;">*</label>项目名称</td>
   <td><input type="text" id="dlg_project_name" name="dlg_project_name"  class="wi-ipt-1" style="width:98%;height:90%" valid="required"/></td>
  </tr>
  <tr>
   <td><label style="color:red;">*</label>招标编号</td>
   <td><input type="text" id="dlg_project_bianhao" name="dlg_project_bianhao"  class="wi-ipt-1" style="width:98%;height:90%" valid="required"/></td>
  </tr>
   <tr>
   <td><label style="color:red;">*</label>立项批文</td>
   <td>
			    <!--<input type="hidden" name="zb_lxpw_path" id="zb_lxpw_path" valid="required"/>
				  <input type="file" id="file_zb_lxpw_path" name="file_zb_lxpw_path" value="" style="display: none;" />-->
				
				<img src="../images/loading.gif" id="loading" style="display: none;">
			    <form action="upload!upl.action?id=zb_lxpw_path&show=promotionShow_zb_lxpw_path" 
			    	method="POST" enctype="multipart/form-data" id="upfile_form" target="result_upfile"  style="float: left;margin-left: 9px;">
				     <input type="hidden" name="zb_lxpw_path" id="zb_lxpw_path" />
					  
					 <input type="file" id="plann_file" onchange="FileUp1()" name="plann_file" 
					 />
					 <!-- style="filter:Alpha(opacity=0);-moz-opacity:0;opacity:0;" -->
					<!--为了支持ie8的上传改成form表单
					 <input type="button" style="width:70px;" 
						value="上传" onclick="uploadpic('','plann_file');"/> -->
						
				</form>
				<div id="promotionShow_zb_lxpw_path"></div>
				<!--  <input type="button" style="width:50px" value="上传" onclick="uploadpic('zb_lxpw_path','file_zb_lxpw_path');"/>-->
				<iframe id="result_upfile" name="result_upfile" style="position:absolute; top:-9999px; left:-9999px">
					
				</iframe>
				<input type="button" style="width:80px" value="附件下载" onclick="zbyqh_grid.mydownload('zb_lxpw_path');"/>
   </td>
  </tr>
   <tr>
   <td >投标须知：</td>
   <td>	
         <input type="text" id="dlg_zb_tbxz" name="dlg_zb_tbxz"  class="wi-ipt-1" style="width:98%;height:90%"/>
  </td>
  </tr>
   <tr>
   <td><label style="color:red;">*</label>合同条款及合同协议格式</td>
   <td>
			    <!--  <input type="hidden" name="zb_httk_path" id="zb_httk_path" valid="required"/>
				<input type="file" id="file_zb_httk_path" name="file_zb_httk_path" value="" style="display: none;" />-->
				
				<img src="../images/loading.gif" id="loading" style="display: none;">
			    <form action="upload!upl.action?id=zb_httk_path&show=promotionShow_zb_httk_path" 
			    	method="POST" enctype="multipart/form-data" id="uphttk_form" target="result_upfile" style="float: left;margin-left: 9px;">
				     <input type="hidden" name="zb_httk_path" id="zb_httk_path" />
					  
					 <input type="file" id="httk_file" onchange="FileUp2()" name="httk_file" 
					 />
					 <!-- style="filter:Alpha(opacity=0);-moz-opacity:0;opacity:0;" -->
					<!--为了支持ie8的上传改成form表单
					 <input type="button" style="width:70px;" 
						value="上传" onclick="uploadpic('','plann_file');"/> -->
				</form>
				<div id="promotionShow_zb_httk_path"></div>
				<!--  <input type="button" style="width:50px" value="上传" onclick="uploadpic('zb_lxpw_path','file_zb_lxpw_path');"/>-->
				<iframe id="result_upfile" name="result_upfile" style="position:absolute; top:-9999px; left:-9999px">
					
				</iframe>
				<!--  <input type="button" style="width:50px" value="上传" onclick="uploadpic('zb_httk_path','file_zb_httk_path');"/>-->
				<input type="button" style="width:80px" value="附件下载" onclick="zbyqh_grid.mydownload('zb_httk_path');"/>
   </td>
  </tr>
   <tr>
   <td><label style="color:red;">*</label>工程量清单和预算书</td>
   <td>
			    <!--  <input type="hidden" name="zb_gclqd_path" id="zb_gclqd_path" valid="required"/>
				<input type="file" id="file_zb_gclqd_path" name="file_zb_gclqd_path" value="" style="display: none;" />-->
				
				<img src="../images/loading.gif" id="loading" style="display: none;">
			    <form action="upload!upl.action?id=zb_gclqd_path&show=promotionShow_zb_gclqd_path" 
			    	method="POST" enctype="multipart/form-data" id="upgclqd_form" target="result_upfile" style="float: left;margin-left: 9px;">
				     <input type="hidden" name="zb_gclqd_path" id="zb_gclqd_path" />
					  
					 <input type="file" id="gclqd_file" onchange="FileUp3()" name="gclqd_file" 
					 />
					 <!-- style="filter:Alpha(opacity=0);-moz-opacity:0;opacity:0;" -->
					<!--为了支持ie8的上传改成form表单
					 <input type="button" style="width:70px;" 
						value="上传" onclick="uploadpic('','plann_file');"/> -->
				</form>
				<div id="promotionShow_zb_gclqd_path"></div>
				<iframe id="result_upfile" name="result_upfile" style="position:absolute; top:-9999px; left:-9999px">
					
				</iframe>
				<!--  <input type="button" style="width:50px" value="上传" onclick="uploadpic('zb_gclqd_path','file_zb_gclqd_path');"/>-->
				<input type="button" style="width:80px" value="附件下载" onclick="zbyqh_grid.mydownload('zb_gclqd_path');"/>
   </td>
  </tr>
   <tr>
   <td>技术检验和要求(合同技术条款)</td>
   <td>
			    <!--  input type="hidden" name="zb_jsjy_path" id="zb_jsjy_path" />
				<input type="file" id="file_zb_jsjy_path" name="file_zb_jsjy_path" value="" style="display: none;" />-->
				
				<img src="../images/loading.gif" id="loading" style="display: none;">
			    <form action="upload!upl.action?id=zb_jsjy_path&show=promotionShow_zb_jsjy_path" 
			    	method="POST" enctype="multipart/form-data" id="upjsjy_form" target="result_upfile" style="float: left;margin-left: 9px;">
				     <input type="hidden" name="zb_jsjy_path" id="zb_jsjy_path" />
					  
					 <input type="file" id="jsjy_file" onchange="FileUp4()" name="jsjy_file" 
					 />
					 <!-- style="filter:Alpha(opacity=0);-moz-opacity:0;opacity:0;" -->
					<!--为了支持ie8的上传改成form表单
					 <input type="button" style="width:70px;" 
						value="上传" onclick="uploadpic('','plann_file');"/> -->
				</form>
				<div id="promotionShow_zb_jsjy_path"></div>
				<iframe id="result_upfile" name="result_upfile" style="position:absolute; top:-9999px; left:-9999px">
					
				</iframe>
				<!--<input type="button" style="width:50px" value="上传" onclick="uploadpic('zb_jsjy_path','file_zb_jsjy_path');"/>-->
				<input type="button" style="width:80px" value="附件下载" onclick="zbyqh_grid.mydownload('zb_jsjy_path');"/>
   </td>
  </tr>
   <tr>
   <td><label style="color:red;">*</label>施工图纸</td>
   <td>
			    <!--  <input type="hidden" name="zb_sgtz_path" id="zb_sgtz_path" valid="required"/>
				<input type="file" id="file_zb_sgtz_path" name="file_zb_sgtz_path" value="" style="display: none;" />-->
				
				<img src="../images/loading.gif" id="loading" style="display: none;">
			    <form action="upload!upl.action?id=zb_sgtz_path&show=promotionShow_zb_sgtz_path" 
			    	method="POST" enctype="multipart/form-data" id="upsgtz_form" target="result_upfile" style="float: left;margin-left: 9px;">
				     <input type="hidden" name="zb_sgtz_path" id="zb_sgtz_path" />
					  
					 <input type="file" id="sgtz_file" onchange="FileUp5()" name="sgtz_file" 
					 />
					 <!-- style="filter:Alpha(opacity=0);-moz-opacity:0;opacity:0;" -->
					<!--为了支持ie8的上传改成form表单
					 <input type="button" style="width:70px;" 
						value="上传" onclick="uploadpic('','plann_file');"/> -->
				</form>
				<div id="promotionShow_zb_sgtz_path"></div>
				<iframe id="result_upfile" name="result_upfile" style="position:absolute; top:-9999px; left:-9999px">
					
				</iframe>
				<!--  <input type="button" style="width:50px" value="上传" onclick="uploadpic('zb_sgtz_path','file_zb_sgtz_path');"/>-->
				<input type="button" style="width:80px" value="附件下载" onclick="zbyqh_grid.mydownload('zb_sgtz_path');"/>
   </td>
   </tr>
   <tr>
   <td>其他附件</td>
   <td>
			    <!-- <input type="hidden" name="zb_other_path" id="zb_other_path" />
				<input type="file" id="file_zb_other_path" name="file_zb_other_path" value="" style="display: none;" />-->
				
				<img src="../images/loading.gif" id="loading" style="display: none;">
			    <form action="upload!upl.action?id=zb_other_path&show=promotionShow_zb_other_path" 
			    	method="POST" enctype="multipart/form-data" id="upother_form" target="result_upfile" style="float: left;margin-left: 9px;">
				     <input type="hidden" name="zb_other_path" id="zb_other_path" />
					  
					 <input type="file" id="other_file" onchange="FileUp6()" name="other_file" 
					 />
					 <!-- style="filter:Alpha(opacity=0);-moz-opacity:0;opacity:0;" -->
					<!--为了支持ie8的上传改成form表单
					 <input type="button" style="width:70px;" 
						value="上传" onclick="uploadpic('','plann_file');"/> -->
				</form>
				<div id="promotionShow_zb_other_path"></div>
				<iframe id="result_upfile" name="result_upfile" style="position:absolute; top:-9999px; left:-9999px">
					</iframe>
				<!-- <input type="button" style="width:50px" value="上传" onclick="uploadpic('zb_other_path','file_zb_other_path');"/>-->
				<input type="button" style="width:80px" value="附件下载" onclick="zbyqh_grid.mydownload('zb_other_path');"/>
   </td>
   </tr>
   <tr>
   <td>备注</td>
   <td>
       <textarea name="dlg_remark" id="dlg_remark" style="width:98%;margin-top: 5px;margin-bottom: 5px;height: 100px;
    overflow-y: auto;"></textarea>
   </td>
   </tr>
</table>
</div>	
<div id="dlg_zbyqh_upload" style="display:none;overflow-y: hidden;">
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
				<input type="file" id="file_name_zbyqh" name="file_name_zbyqh" accept=".xls,.xlsx"/>
			</td>
		</tr>
	</table>
</div>
<script>

var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
var page_num = $(window.self.document).find("#page_num").val();
var page_num =30;
var dlg_zbyqh_dialog;
var dlg_zbyqh_load;

function showInfo(rowData){
						var id = rowData.ID;
						var params=new Object();
				        params.id = id;
							$.ajax({
									type: "POST",
									url: "ztb!getzbyqh.action",
									cache: false,
									data: params,
									success: function(msg)
									{
									    if(msg != null)
									    {
											var result=eval("("+msg+")");									
											$("#dlg_id").val(id);	
											$("#dlg_project_name").val(result.PROJECT_NAME);
											
		                                    $("#dlg_project_bianhao").val(result.PROJECT_BIANHAO);
		                                    $("#zb_lxpw_path").val(result.ZB_LXPW_PATH);
											$("#promotionShow_zb_lxpw_path").html(result.ZB_LXPW_PATH.split("upload")[1]);
											$("#zb_httk_path").val(result.ZB_HTTK_PATH);
											$("#promotionShow_zb_httk_path").html(result.ZB_HTTK_PATH.split("upload")[1]);
											$("#zb_gclqd_path").val(result.ZB_GCLQD_PATH);
											$("#promotionShow_zb_gclqd_path").html(result.ZB_GCLQD_PATH.split("upload")[1]);
											$("#zb_jsjy_path").val(result.ZB_JSJY_PATH);
											$("#promotionShow_zb_jsjy_path").html(result.ZB_JSJY_PATH.split("upload")[1]);
											$("#zb_sgtz_path").val(result.ZB_SGTZ_PATH);
											$("#promotionShow_zb_sgtz_path").html(result.ZB_SGTZ_PATH.split("upload")[1]);
											$("#zb_other_path").val(result.ZB_OTHER_PATH);
											$("#promotionShow_zb_other_path").html(result.ZB_OTHER_PATH.split("upload")[1]);
											$("#dlg_remark").html(result.REMARK);
											
											
									  }
									}
								}); 
								
								$("#plann_file").hide();
								$("#httk_file").hide();
								$("#gclqd_file").hide();
								$("#jsjy_file").hide();
								$("#sgtz_file").hide();
								$("#other_file").hide();
								
						var dlgParent = $("#dlg_zbyqh").parent();
				        var dlgOwn = $("#dlg_zbyqh").clone();
				        var dlg_zbyqh_ck =				
					    $("#dlg_zbyqh").dialog({
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
								clean_input_for_dailog($("#dlg_zbyqh"));
								$("#upfile_form")[0].reset();
								$("#uphttk_form")[0].reset();
								$("#upgclqd_form")[0].reset();
								$("#upjsjy_form")[0].reset();
								$("#upsgtz_form")[0].reset();
								$("#upother_form")[0].reset();
						         $("#promotionShow_zb_lxpw_path").html("");
						         $("#promotionShow_zb_httk_path").html("");
						         $("#promotionShow_zb_gclqd_path").html("");
						         $("#promotionShow_zb_jsjy_path").html("");
						         $("#promotionShow_zb_sgtz_path").html("");
						         $("#promotionShow_zb_other_path").html("");
						         
						         $("#plann_file").show();
								$("#httk_file").show();
								$("#gclqd_file").show();
								$("#jsjy_file").show();
								$("#sgtz_file").show();
								$("#other_file").show();
						         
									$(this).dialog("destroy").remove();
									
	         					}
							}
					    });
					    dlg_zbyqh_ck.dialog("open");
					    
}



var	dlg_zbyqh = function(data){
	dlg_zbyqh_dialog = $("#dlg_zbyqh").dialog({
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
							zbyqh_grid.saveData(data.operation);
							
								
						},
						"关闭": function() {
							clean_input_for_dailog($("#dlg_zbyqh"));
								$("#upfile_form")[0].reset();
								$("#uphttk_form")[0].reset();
							$("#upgclqd_form")[0].reset();
							$("#upjsjy_form")[0].reset();
							$("#upsgtz_form")[0].reset();
							$("#upother_form")[0].reset();
						         $("#promotionShow_zb_lxpw_path").html("");
						         $("#promotionShow_zb_httk_path").html("");
						         $("#promotionShow_zb_gclqd_path").html("");
						         $("#promotionShow_zb_jsjy_path").html("");
						         $("#promotionShow_zb_sgtz_path").html("");
						         $("#promotionShow_zb_other_path").html("");
							
	         				$(this).dialog("close");
	         			}
					}
				});
				
				dlg_zbyqh_load = $("#dlg_zbyqh_upload").dialog({
					autoOpen: false,
					height:400,
					width:600,
					modal:true,
					cache:false,
					title:data.com,
					open: function (event, ui) {
						 $(".ui-dialog-titlebar-close", $(this).parent()).hide();
					},
					buttons:{
						"保存":function() {
							zbyqh_grid.saveData(data.operation);
							//clean_input_for_dailog($("#dlg_zbyqh_upload"));
						},
						"关闭": function() {
							clean_input_for_dailog($("#dlg_zbyqh_upload"));
// 							console.log(dlgParent.attr("id"));
// 							$(this).dialog("destroy").remove();
// 							dlgOwn.appendTo(dlgParent);
	         				$(this).dialog("close");
	         			}
					}
				});
				
  
				
};

var zbyqh_grid = {
	//绑定参数信息grid
	zbyqh_grid_result:"",
	dlg_zbyqh:"",
	GridHeight:flexHeight("zbyqh_grid"),
	bindParamGrid:function(queryP,queryM){
		zbyqh_grid.zbyqh_grid_result = $("#zbyqh_grid").flexigrid({
			url:"ztb!zbyqhList.action?operation=init",
			colModel: queryM,
			buttons : queryP,
			rowId:"id",
			showToggleBtn: true,
			usepager: true,
			title:"${head}",
			//rp:pageSize,
			onRowDblclick:showInfo,
			rp:page_num,
			showTableToggleBtn: false,
			resizable: false,
			height: zbyqh_grid.GridHeight,
			singleSelect: true,
			onChangePage:zbyqh_grid.loadData,
			checkbox:false,
			filterParams : [{key : "project_name", name : "#serch_project_name"},
			                {key : "project_bianhao", name : "#serch_project_bianhao"}
			                ]
		});
	},			
		// 查询子任务并且显示
	showDialog : function(com,operation,divobj,h,w) 
		{
		var dlgParent = divobj.parent();
		var dlgOwn = divobj.clone();
		zbyqh_grid.dlg_zbyqh = divobj.dialog({
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
						zbyqh_grid.saveData(operation);
		              	//$(this).dialog("destroy").remove();
					}
				},
			    close : function() {
		              	dlgOwn.appendTo(dlgParent);
		              	$(this).dialog("destroy").remove();
		         }
			});
			
		zbyqh_grid.dlg_zbyqh.dialog("option", "title", com).dialog("open");
	},	
	add:function(com,grid)
	{
		
		if (com == "新建") 
				{
				   
				    zbyqh_grid.showDialog("新增招标文件、投标邀请函","add",$("#dlg_zbyqh"),"auto","auto");
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
					        var indicate = "确认需要删除" + title+ ",这个招标文件、投标邀请函吗?";	
					        var params=new Object();
				                params.id = id;					
							if(confirm(indicate)) 
							{
								$.ajax({
									type: "POST",
									url: "ztb!delzbyqh.action",
									cache: false,
									data: params,
									success: function(msg)
									{
										var result=eval("("+msg+")");
										if (result.msg=="ok") 
										{
											showMsg("删除成功！");
											$('#dlg_zbyqh').dialog('close');
											zbyqh_grid.loadData({page:1});
										} else 
										{
											showMsg(result.msg);
											$('#dlg_zbyqh').dialog('close');
										}
									}
								}); 
							}else
							{
								$('#dlg_zbyqh').dialog('close');
							}
						
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_zbyqh').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_zbyqh').dialog('close');
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
				        $("#action").val("edit");
						var id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
						var params=new Object();
				        params.id = id;
							$.ajax({
									type: "POST",
									url: "ztb!getzbyqh.action",
									cache: false,
									data: params,
									success: function(msg)
									{
									    if(msg != null)
									    {
					    zbyqh_grid.showDialog("修改招标文件、投标邀请函","edit",$("#dlg_zbyqh"),"auto","auto");
											var result=eval("("+msg+")");									
											$("#dlg_id").val(id);	
											$("#dlg_project_name").val(result.PROJECT_NAME);
											
		                                    $("#dlg_project_bianhao").val(result.PROJECT_BIANHAO);
		                                    $("#dlg_zb_tbxz").val(result.ZB_TBXZ);
		                                    $("#zb_lxpw_path").val(result.ZB_LXPW_PATH);
											$("#promotionShow_zb_lxpw_path").html(result.ZB_LXPW_PATH.split("upload")[1]);
											$("#zb_httk_path").val(result.ZB_HTTK_PATH);
											$("#promotionShow_zb_httk_path").html(result.ZB_HTTK_PATH.split("upload")[1]);
											$("#zb_gclqd_path").val(result.ZB_GCLQD_PATH);
											$("#promotionShow_zb_gclqd_path").html(result.ZB_GCLQD_PATH.split("upload")[1]);
											$("#zb_jsjy_path").val(result.ZB_JSJY_PATH);
											$("#promotionShow_zb_jsjy_path").html(result.ZB_JSJY_PATH.split("upload")[1]);
											$("#zb_sgtz_path").val(result.ZB_SGTZ_PATH);
											$("#promotionShow_zb_sgtz_path").html(result.ZB_SGTZ_PATH.split("upload")[1]);
											$("#zb_other_path").val(result.ZB_OTHER_PATH);
											$("#promotionShow_zb_other_path").html(result.ZB_OTHER_PATH.split("upload")[1]);
											$("#dlg_remark").html(result.REMARK);
											
											
									  }
									}
								}); 				
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
	saveData:function(operation)
	{
				if(operation == "import")	           
	          {
	          		var queryP = {};
					queryP.nameType = "zbyqhInfo";
					queryP.suffix = $.trim($("#file_name_zbyqh").val()).split("\.")[1];
					if(queryP.suffix === '' || queryP.suffix === undefined || typeof queryP.suffix === 'undefined' )
						 {
							showMsg("未选择需要导入的文件！");
							return;
						 }
					$.ajaxFileUpload({
						url:"upload!importExcel.action?radom="+Math.random(),
						fileElementId:"file_name_zbyqh",
						secureuri:false,
						type:"POST",
						data:queryP,
						dataType:"json",
						success:function(data)
						{
							if(data != null && data != "" && data.result == "00")
							{
								showMsg("招标文件、投标邀请函基本信息导入成功！");
								zbyqh_grid.loadData({page:1});
								$("#dlg_zbyqh_upload").dialog('close');
							}else{
								showMsg("招标文件、投标邀请函基本信息导入失败！");
							}
						},
						error:function(){
							showMsg("文件导入失败");
						}
					});

	          }
	          else
	          {
	          
	             
			    var params=new Object();
				params.project_name = $.trim($("#dlg_project_name").val());
				params.project_bianhao = $.trim($("#dlg_project_bianhao").val());
				params.zb_tbxz = $.trim($("#dlg_zb_tbxz").val());
				params.zb_lxpw_path  = $.trim($("#zb_lxpw_path").val());
				params.zb_httk_path  = $.trim($("#zb_httk_path").val());
				params.zb_gclqd_path  = $.trim($("#zb_gclqd_path").val());
				params.zb_jsjy_path  = $.trim($("#zb_jsjy_path").val());
				params.zb_sgtz_path  = $.trim($("#zb_sgtz_path").val());
				params.zb_other_path  = $.trim($("#zb_other_path").val());
				
				params.remark  = $.trim($("#dlg_remark").val());
				var mum =0;		
			       //提交方式
				   var action = $("#action").val();	
				   if(params.project_name==null || params.project_name=='')
					{
						showMsg("请输入项目名称！");
						$("#dlg_project_name").focus();
						return;
					}
					if(params.project_bianhao==null || params.project_bianhao=='')
					{
						showMsg("请输入招标编号！");
						$("#dlg_project_bianhao").focus();
						return;
					}	
					if(params.zb_lxpw_path==null || params.zb_lxpw_path=='')
					{
						showMsg("请上传立项批文！");
						return;
					}	
					else
					{
					   mum++;
					}
					
					if(params.zb_httk_path==null || params.zb_httk_path=='')
					{
						showMsg("请上传合同条款及合同协议格式！");
						return;
					}
					else
					{
					   mum++;
					}
					if(params.zb_gclqd_path==null || params.zb_gclqd_path=='')
					{
						showMsg("请上传工程量清单和预算书！");
						return;
					}
					else
					{
					   mum++;
					}	
					if(params.zb_sgtz_path==null || params.zb_sgtz_path=='')
					{
						showMsg("请上传施工图纸！");
						return;
					}
					else
					{
					   mum++;
					}	
					if(params.zb_jsjy_path!=null && params.zb_jsjy_path!='')
					{
                          mum++; 
					}
					if(params.zb_other_path!=null && params.zb_other_path!='')
					{
                          mum++; 
					}						
					params.zb_num  = mum;			
				// 新增和修改通用字符串
				var url ='';
				var indicate='';
				if ("add" == action) 
				{
					indicate = "确认新增" + params.project_name + ",这个招标文件、投标邀请函?";
					url = "ztb!savezbyqh.action";
				} 
				else if ("edit" == action) 
				{
					params.id = $("#dlg_id").val();
					indicate = "确认更新" + params.project_name + ",这个招标文件、投标邀请函?";
					url = "ztb!updatezbyqh.action";
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
								clean_input_for_dailog($("#dlg_zbyqh"));
								$("#upfile_form")[0].reset();
								$("#uphttk_form")[0].reset();
								$("#upgclqd_form")[0].reset();
								$("#upjsjy_form")[0].reset();
								$("#upsgtz_form")[0].reset();
								$("#upother_form")[0].reset();
						         $("#promotionShow_zb_lxpw_path").html("");
						         $("#promotionShow_zb_httk_path").html("");
						         $("#promotionShow_zb_gclqd_path").html("");
						         $("#promotionShow_zb_jsjy_path").html("");
						         $("#promotionShow_zb_sgtz_path").html("");
						         $("#promotionShow_zb_other_path").html("");
								$('#dlg_zbyqh').dialog('close');
								zbyqh_grid.loadData({page:1});
							} else 
							{
								showMsg(result.msg);
							}
						}
					}); 
				}
				else
				{
				    $('#dlg_zbyqh').dialog('close');
				}
				
		}
	},
		// 导出数据
	commitExport : function() 
	{
				var condition = "";
				var serch_project_name=$("#serch_project_name")==null?'':$('#serch_project_name').val();
				if (serch_project_name && $.trim(serch_project_name).length > 0) 
				{
					condition += "&project_name=" + encodeURI(encodeURI($.trim(serch_project_name)));
				}
				var serch_project_bianhao=$("#serch_project_bianhao")==null?'':$('#serch_project_bianhao').val();
				if (serch_project_bianhao && $.trim(serch_project_bianhao).length > 0) 
				{
					condition += "&project_bianhao=" + encodeURI(encodeURI($.trim(serch_project_bianhao)));
				}
				location.href='ztb!exportJobs_zbyqh.action?'+condition;
			
   },
    //导入excel
   importZbyqh:function(name,grid)
   	{
		//showMsg("车辆信息批量导入");
		zbyqh_grid.showDialog("招标文件、投标邀请函基本信息导入","import", $("#dlg_zbyqh_upload"),500,400);
	},
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
		queryP.project_name = $('#serch_project_name').val()==null?'':$('#serch_project_name').val();
		queryP.project_bianhao = $('#serch_project_bianhao').val()==null?'':$('#serch_project_bianhao').val();
		var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
		queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
		queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
		queryP.sortname = param.sortname? param.sortname : "";
		queryP.sortorder = param.sortorder? param.sortorder : "";
		$.ajax({
			type: "POST",
			url: "ztb!zbyqhList.action?operation=init",
			cache: false,
			data: queryP,
			success: function(msg){
				var data=eval("("+msg+")");
				try{
					zbyqh_grid.zbyqh_grid_result.flexAddData(data);
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
			  {name: '新建', bclass: 'add', onpress : zbyqh_grid.add},
			  {name: '修改', bclass: 'edit', onpress : zbyqh_grid.edit},
			  {name: '删除', bclass: 'delete', onpress : zbyqh_grid.del},
			  {name: '导入', bclass: 'distribute', onpress : zbyqh_grid.importZbyqh},
			  {name: '导出', bclass: 'excel', onpress : zbyqh_grid.commitExport}
		 ];
		 //权限操作按钮
		 var colModel = [						
	            {display: '序号', name : 'xh', width : 40, align: 'center'},
				{display: 'id', name : 'ID', width : 40, align: 'center',hide : true},
				{display: '项目名称', name : 'PROJECT_NAME', width : 350, align: 'left',sortable: true},
				{display: '招标编号', name : 'PROJECT_BIANHAO', width : 150, align: 'left',sortable: true},
				{display: '文件数量', name : 'ZB_NUM', width : 120,  align: 'left',sortable : true},
				{display: '备注', name : 'REMARK', width : 120,  align: 'left',sortable : true}			
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
				zbyqh_grid.bindParamGrid(buttons,colModel);
			}
		});
		 
		 
		 		/*参数管理-查询按钮功能*/
		$('#btn_zbyqh_search').button().on("click",function(){
			
			zbyqh_grid.loadData({page:1});
			
		});
				//清空筛选框
		$('#btn_zbyqh_empty').on("click",function(){
			$("#serch_project_name").val("");
			$("#serch_project_bianhao").val("");
		});  
		 		 
	});
	
	
	
		
		 
		function FileUp1() 
		{       
         /*$.ajaxFileUpload
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
                       $("#"+file_str).val(data.rs_path);
                       $("#"+plann_file).val("");
                    }
                },
                error: function (data, status, e)//服务器响应失败处理函数
                {
                    alert(e);
                    $("#"+plann_file).val("");
                }
            }
        )*/
           $("#upfile_form").submit();
		
		};
		
		//
		
		function FileUp2() 
		{   
		  $("#uphttk_form").submit();
		};     
       //
       
       
		function FileUp3() 
		{  
		  $("#upgclqd_form").submit();
		};   
		
		//jsjy_file
		  
		    function FileUp4() 
			{  
		     $("#upjsjy_form").submit();
		    }; 
		//sgtz_file
		 
		    function FileUp5() 
			{  
		     $("#upsgtz_form").submit();
		    }; 
		//other_file  
		   
		    function FileUp6() 
			{ 
		     $("#upother_form").submit();
		    }; 
	//导入的模板文件
	function downloadModel(obj)
	{
	    if(obj != null && obj != "")
		window.location.href = "upload!exportExcelModelForTable.action?suffix="+obj+"&nameType=zbyqhInfo"+"&radom="+Math.random();
     }
     
       //调用返回上传结果 
    function get_upfile_result(res){
        
     	if(1 == Number(res.mess_state)){
     		alert(res.message);
     		$("#"+res.show).html(res.newFileName);
     		$("#"+res.id).val(res.rs_path);
     		
     	}else{
     		alert(res.message);
     	}
    };
    
   
	
	
</script>
