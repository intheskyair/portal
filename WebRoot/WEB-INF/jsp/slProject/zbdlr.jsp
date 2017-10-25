<%@ page contentType="text/html; charset=UTF-8"%>
<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='../images/fold.png' onclick="showAndHide(this,'zbdlr_grid');"  class="imgShrotate">
				<font>招标代理人基本信息</font>
			</h3>
		</div>
	<div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>
					<td >
					<label for="input_1" class="lbl-1">
							企业名称:
					</label>
                        <input type="text" id="serch_company_name" name="serch_company_name" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l" >
						<label for="input_1" class="lbl-1">
							法人营业执照注册号:
						</label>
						<input type="text" id="serch_company_yyzzh" name="serch_company_yyzzh" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<button id="btn_zbdlr_search" class="ui-state-default ui-state-qik">查询
						</button>					
						<button id="btn_zbdlr_empty" class="ui-state-default ui-state-qik">清空
						</button>
					</td>
				</tr>
			</table>
		</div>
</div>
<div class="div div-2" style="">
	<table id="zbdlr_grid" style="display:none;padding:0 0 0 0;"></table>
</div>
<div id="dlg_zbdlr" style="display:none;overflow:auto;">
	<input type="hidden" id="action" />
	<input type="hidden" id="dlg_id" /> 
	
<table class="table12" cellpadding="0" cellspacing="0">
  <tr>
   <td>
   <label style="color:red;">*</label>
   	企业名称
   </td>
   <td><input type="text" id="dlg_company_name" name="dlg_company_name"  class="wi-ipt-y" /></td>
   <td>
 	<label style="color:red;">*</label>  
   	联系电话（√）
   </td>
   <td colspan="2"><input type="text" id="dlg_company_phone" name="dlg_company_phone"  class="wi-ipt-y" /></td>
  </tr>
    <tr>
   <td rowspan="2"><label style="color:red;">*</label>法人营业执照注册号</td>
   <td><input type="text" id="dlg_company_yyzzh" name="dlg_company_yyzzh"  class="wi-ipt-y" /></td>
   <td rowspan="2"><label style="color:red;">*</label>最新年检</td>
   <td colspan="2" rowspan="2">至<input type="text" id="dlg_company_nj_time"
							readonly class="Wdate" style="width:90%"
						onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/></td>
  </tr>
    <tr>
   <td>
                <img src="../images/loading.gif" id="loading" style="display: none;">
			    <form action="upload!upl.action?id=path_plann_file_yyzzh&show=promotionShow_yyzzh" 
			    	method="POST" enctype="multipart/form-data" id="upyyzzh_form" target="result_upfile" >
			    <input type="hidden" name="path_plann_file_yyzzh" id="path_plann_file_yyzzh" />
				<input type="file" id="plann_file_1" onchange="FileUp1()" name="plann_file_1" class="wi-ipt-x"/>
				</form>
				<!--  <input type="button" style="width:50px" value="上传" onclick="uploadpic('yyzzh','plann_file_1');"/>-->
				<div id="promotionShow_yyzzh"></div>
				<input type="button" style="width:80px" value="附件下载" onclick="zbdlr_grid.mydownload('path_plann_file_yyzzh');"/>
				<iframe id="result_upfile" name="result_upfile" style="position:absolute; top:-9999px; left:-9999px"></iframe>
   </td>
  </tr>
    <tr>
   <td rowspan="3"><label style="color:red;">*</label>工程招标代理资质等级</td>
   <td>	<select name="dlg_company_gczbdlzzdj" id="dlg_company_gczbdlzzdj" class="wi-sel-1">
					        <option value="">---未选择---</option>
							<option value="甲级">甲级</option>
							<option value="乙级">乙级</option>
		</select>
	</td>
   <td><label style="color:red;">*</label>证书编号</td>
   <td colspan="2"><input type="text" id="dlg_company_gczbdlzzdj_bh" name="dlg_company_gczbdlzzdj_bh"  class="wi-ipt-y" /></td>
  </tr>
    <tr>
   <td rowspan="2">
                 <img src="../images/loading.gif" id="loading" style="display: none;">
			    <form action="upload!upl.action?id=path_plann_file_gczbdlzzdj&show=promotionShow_gczbdlzzdj" 
			    	method="POST" enctype="multipart/form-data" id="upgczbdlzzdj_form" target="result_upfile" >
   			    <input type="hidden" name="path_plann_file_gczbdlzzdj" id="path_plann_file_gczbdlzzdj" />
				
				<input type="file" id="plann_file_2" onchange="FileUp2()" name="plann_file_2" class="wi-ipt-x"/>
				</form>
				<!--  <input type="button" style="width:50px" value="上传" onclick="uploadpic('gczbdlzzdj','plann_file_2');"/>-->
				<div id="promotionShow_gczbdlzzdj"></div>
				<input type="button" style="width:80px" value="附件下载" onclick="zbdlr_grid.mydownload('path_plann_file_gczbdlzzdj');"/>
				<iframe id="result_upfile" name="result_upfile" style="position:absolute; top:-9999px; left:-9999px"></iframe>
				</td>
   <td><label style="color:red;">*</label>有效期</td>
   <td colspan="2">至 <input type="text" id="dlg_company_gczbdlzzdj_time"
							readonly class="Wdate" style="width:90%;margin-bottom: 5px;"
						onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/></td>
  </tr>
    <tr>
   <td>可承接业务范围</td>
   <td colspan="2"><textarea id="dlg_company_gczbdlzzdj_ywfw" style="margin-bottom: 5px;margin-top: 5px;"></textarea></td>
  </tr>
    <tr>
   <td rowspan="3"><label style="color:red;">*</label>工程造价咨询资质等级</td>
   <td>
        <select name="dlg_company_gczjzxzzdj" id="dlg_company_gczjzxzzdj" class="wi-sel-1">
					        <option value="">---未选择---</option>
							<option value="甲级">甲级</option>
							<option value="乙级">乙级</option>
		</select>
	</td>
   <td><label style="color:red;">*</label>资质证书</td>
   <td colspan="2"><input type="text" id="dlg_company_gczjzxzzdj_bh" name="dlg_company_gczjzxzzdj_bh"  class="wi-ipt-y" /></td>
  </tr>
    <tr>
   <td rowspan="2">
                <img src="../images/loading.gif" id="loading" style="display: none;">
			    <form action="upload!upl.action?id=path_plann_file_gczjzxzzdj&show=promotionShow_gczjzxzzdj" 
			    	method="POST" enctype="multipart/form-data" id="upgczjzxzzdj_form" target="result_upfile" >
      			<input type="hidden" name="path_plann_file_gczjzxzzdj" id="path_plann_file_gczjzxzzdj" />
				
				<input type="file" id="plann_file_3" onchange="FileUp3()" name="plann_file_3" class="wi-ipt-x"/>
				</form>
				<!--  <input type="button" style="width:50px" value="上传" onclick="uploadpic('gczjzxzzdj','plann_file_3');"/>-->
				<div id="promotionShow_gczjzxzzdj"></div>
				<input type="button" style="width:80px" value="附件下载" onclick="zbdlr_grid.mydownload('path_plann_file_gczjzxzzdj');"/>
				<iframe id="result_upfile" name="result_upfile" style="position:absolute; top:-9999px; left:-9999px"></iframe>
				</td>
   <td><label style="color:red;">*</label>有效期</td>
   <td colspan="2">至  <input type="text" id="dlg_company_gczjzxzzdj_time"
							readonly class="Wdate" style="width:90%;margin-bottom: 5px;"
						onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
	</td>
  </tr>
    <tr>
   <td>可承接业务范围</td>
   <td colspan="2"><textarea id="dlg_company_gczjzxzzdj_ywfw" name="dlg_company_gczjzxzzdj_ywfw" style="margin-bottom: 5px;margin-top: 5px;"></textarea></td>
  </tr>
    <tr>
   <td><label style="color:red;">*</label>详细地址（√）</td>
   <td colspan="4" >
       <textarea name="dlg_company_address" id="dlg_company_address"  style="width:99%;margin-top: 5px;margin-bottom: 5px;"></textarea>
   </td>
  </tr>
    <tr>
   <td rowspan="3"><label style="color:red;">*</label>法定代表人（√）</td>
   <td colspan="2"><input type="text" id="dlg_company_frdb" name="dlg_company_frdb"  class="wi-ipt-y" /></td>
   <td rowspan="3"><label style="color:red;">*</label>联系电话（√）</td>
   <td rowspan="3"><input type="text" id="dlg_company_frdb_phone" name="dlg_company_frdb_phone"  class="wi-ipt-m" /></td>
  </tr>
  <tr>
   <td><label style="color:red;">*</label>职务（√）</td>
   <td><input type="text" id="dlg_company_frdb_zw" name="dlg_company_frdb_zw"  class="wi-ipt-y" /></td>
  </tr>
    <tr>
   <td><label style="color:red;">*</label>职称（√）</td>
   <td><input type="text" id="dlg_company_frdb_zc" name="dlg_company_frdb_zc"  class="wi-ipt-y" /></td>
  </tr>  
</table>
</div>	
<div id="dlg_zbdlr_upload" style="display:none;overflow-y: hidden;">
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
				<input type="file" id="file_name_zbdlr" name="file_name_zbdlr" accept=".xls,.xlsx"/>
			</td>
		</tr>
	</table>
</div>
<script>		
var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
var page_num = $(window.self.document).find("#page_num").val();
var page_num =30;
var dlg_zbdlr_dialog;
var dlg_zbdlr_load;

function showInfo(rowData){
						var id = rowData.ID;
						var params=new Object();
				        params.id = id;
							$.ajax({
									type: "POST",
									url: "ztb!getzbdlr.action",
									cache: false,
									data: params,
									success: function(msg)
									{
									    if(msg != null)
									    {
											var result=eval("("+msg+")");										
													$("#dlg_id").val(id);	
													$("#dlg_company_name").val(result.COMPANY_NAME);
				                                    $("#dlg_company_phone").val(result.COMPANY_PHONE);
													$("#dlg_company_yyzzh").val(result.COMPANY_YYZZH);
													$("#dlg_company_nj_time").val(result.COMPANY_NJ_TIME);
													$("#path_plann_file_yyzzh").val(result.COMPANY_YYZZH_PATH);
													$("#promotionShow_yyzzh").html(result.COMPANY_YYZZH_PATH.split("upload")[1]);
													
													$("#dlg_company_gczbdlzzdj option[value='"+result.COMPANY_GCZBDLZZDJ+"']").attr("selected", true);
													$("#path_plann_file_gczbdlzzdj").val(result.COMPANY_GCZBDLZZDJ_PATH);
													$("#promotionShow_gczbdlzzdj").html(result.COMPANY_GCZBDLZZDJ_PATH.split("upload")[1]);
													$("#dlg_company_gczbdlzzdj_bh").val(result.COMPANY_GCZBDLZZDJ_BH);
													$("#dlg_company_gczbdlzzdj_time").val(result.COMPANY_GCZBDLZZDJ_TIME);
													$("#dlg_company_gczbdlzzdj_ywfw").html(result.COMPANY_GCZBDLZZDJ_YWFW);
									                $("#dlg_company_gczjzxzzdj option[value='"+result.COMPANY_GCZJZXZZDJ+"']").attr("selected", true);
													$("#path_plann_file_gczjzxzzdj").val(result.COMPANY_GCZJZXZZDJ_PATH);
													$("#promotionShow_gczjzxzzdj").html(result.COMPANY_GCZJZXZZDJ_PATH.split("upload")[1]);
												    $("#dlg_company_gczjzxzzdj_bh").val(result.COMPANY_GCZJZXZZDJ_BH);
													$("#dlg_company_gczjzxzzdj_time").val(result.COMPANY_GCZJZXZZDJ_TIME);
													$("#dlg_company_gczjzxzzdj_ywfw").html(result.COMPANY_GCZJZXZZDJ_YWFW);				
													$("#dlg_company_address").val(result.COMPANY_ADDRESS);	
													$("#dlg_company_frdb_zw").val(result.COMPANY_FRDB_ZW);
													$("#dlg_company_frdb_zc").val(result.COMPANY_FRDB_ZC);
													$("#dlg_company_frdb").val(result.COMPANY_FRDB);
													$("#dlg_company_frdb_phone").val(result.COMPANY_FRDB_PHONE);
													
									  }
									}
								}); 
						
						
						
						$("#plann_file_1").hide();
						$("#plann_file_2").hide();
						$("#plann_file_3").hide();
						
						var dlgParent = $("#dlg_zbdlr").parent();
				        var dlgOwn = $("#dlg_zbdlr").clone();
				        var dlg_zbdlr_ck =
					    $("#dlg_zbdlr").dialog({
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
								$("#plann_file_1").show();
								 $("#plann_file_2").show();
								 $("#plann_file_3").show();
								clean_input_for_dailog($("#dlg_zbdlr"));
								$("#upyyzzh_form")[0].reset();
								$("#upgczbdlzzdj_form")[0].reset();
								$("#upgczjzxzzdj_form")[0].reset();
						        $("#promotionShow_yyzzh").html("");
						        $("#promotionShow_gczbdlzzdj").html("");
						        $("#promotionShow_gczjzxzzdj").html("");
								$(this).dialog("destroy").remove();
	         					}
							}
					    });
					    dlg_zbdlr_ck.dialog("open");
}


//ie8下面的弹窗必须改成这样的方式
var	dlg_zbdlr = function(data){
	dlg_zbdlr_dialog = $("#dlg_zbdlr").dialog({
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
							zbdlr_grid.saveData(data.operation);
							//clean_input_for_dailog($("#dlg_zbdlr"));
						},
						"关闭": function() {
							clean_input_for_dailog($("#dlg_zbdlr"));
							
							$("#upyyzzh_form")[0].reset();
							$("#upgczbdlzzdj_form")[0].reset();
							$("#upgczjzxzzdj_form")[0].reset();
						         $("#promotionShow_yyzzh").html("");
						         $("#promotionShow_gczbdlzzdj").html("");
						         $("#promotionShow_gczjzxzzdj").html("");
							//console.log(dlgParent.attr("id"));
							//$(this).dialog("destroy").remove();
							//dlgOwn.appendTo(dlgParent);
	         				$(this).dialog("close");
	         			}
					}
				});
				
				dlg_zbdlr_load = $("#dlg_zbdlr_upload").dialog({
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
							zbdlr_grid.saveData(data.operation);
							clean_input_for_dailog($("#dlg_zbdlr_upload"));
						},
						"关闭": function() {
							clean_input_for_dailog($("#dlg_zbdlr_upload"));
							//console.log(dlgParent.attr("id"));
							//$(this).dialog("destroy").remove();
							//dlgOwn.appendTo(dlgParent);
	         				$(this).dialog("close");
	         			}
					}
				});
				
  
				
};

var zbdlr_grid = {
	//绑定参数信息grid
	zbdlr_grid_result:"",
	GridHeight:flexHeight("zbdlr_grid"),
	bindParamGrid:function(queryP,queryM){
		zbdlr_grid.zbdlr_grid_result = $("#zbdlr_grid").flexigrid({
			url:"ztb!zbdlrList.action?operation=init",
			colModel: queryM,
			buttons : queryP,
			rowId:"id",
			showToggleBtn: true,
			usepager: true,
			title:"招标代理人基本信息",
			//rp:pageSize,
			onRowDblclick:showInfo,
			rp:page_num,
			showTableToggleBtn: false,
			resizable: false,
			height: zbdlr_grid.GridHeight,
			singleSelect: true,
			onChangePage:zbdlr_grid.loadData,
			checkbox:false,
			filterParams : [{key : "company_name", name : "#serch_company_name"},
			                {key : "company_yyzzh", name : "#serch_company_yyzzh"}
			                ]
		});
	},			
			// 查询子任务并且显示
	showDialog : function(com,operation,divobj,h,w) 
		{
		var dlgParent = divobj.parent();
		var dlgOwn = divobj.clone();
		zbdlr_grid.dlg_zbdlr = divobj.dialog({
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
						zbdlr_grid.saveData(operation);
		              	//$(this).dialog("destroy").remove();
					}
				},
			    close : function() {
		              	dlgOwn.appendTo(dlgParent);
		              	$(this).dialog("destroy").remove();
		         }
			});
			
		zbdlr_grid.dlg_zbdlr.dialog("option", "title", com).dialog("open");
	},	
	
	add:function(com,grid)
	{
		
		if (com == "新建") 
				{
				    zbdlr_grid.showDialog("新增招标代理人","add", $("#dlg_zbdlr"));
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
					        var indicate = "确认需要删除" + title+ ",这个招标代理人吗?";	
					        var params=new Object();
				                params.id = id;					
							if(confirm(indicate)) 
							{
								$.ajax({
									type: "POST",
									url: "ztb!delzbdlr.action",
									cache: false,
									data: params,
									success: function(msg)
									{
										var result=eval("("+msg+")");
										if (result.msg=="ok") 
										{
											showMsg("删除成功！");
											$('#dlg_zbdlr').dialog('close');
											zbdlr_grid.loadData({page:1});
										} else 
										{
											showMsg(result.msg);
											$('#dlg_zbdlr').dialog('close');
										}
									}
								}); 
							}else
							{
								$('#dlg_zbdlr').dialog('close');
							}
						
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_zbdlr').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_zbdlr').dialog('close');
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
									url: "ztb!getzbdlr.action",
									cache: false,
									data: params,
									success: function(msg)
									{
									    if(msg != null)
									    {
					    zbdlr_grid.showDialog("修改招标代理人信息","modify", $("#dlg_zbdlr"));
											var result=eval("("+msg+")");										
													$("#dlg_id").val(id);	
													$("#dlg_company_name").val(result.COMPANY_NAME);
				                                    $("#dlg_company_phone").val(result.COMPANY_PHONE);
													$("#dlg_company_yyzzh").val(result.COMPANY_YYZZH);
													$("#dlg_company_nj_time").val(result.COMPANY_NJ_TIME);
													$("#path_plann_file_yyzzh").val(result.COMPANY_YYZZH_PATH);
													$("#promotionShow_yyzzh").html(result.COMPANY_YYZZH_PATH.split("upload")[1]);
													
													$("#dlg_company_gczbdlzzdj option[value='"+result.COMPANY_GCZBDLZZDJ+"']").attr("selected", true);
													$("#path_plann_file_gczbdlzzdj").val(result.COMPANY_GCZBDLZZDJ_PATH);
													$("#promotionShow_gczbdlzzdj").html(result.COMPANY_GCZBDLZZDJ_PATH.split("upload")[1]);
													$("#dlg_company_gczbdlzzdj_bh").val(result.COMPANY_GCZBDLZZDJ_BH);
													$("#dlg_company_gczbdlzzdj_time").val(result.COMPANY_GCZBDLZZDJ_TIME);
													$("#dlg_company_gczbdlzzdj_ywfw").html(result.COMPANY_GCZBDLZZDJ_YWFW);
									                $("#dlg_company_gczjzxzzdj option[value='"+result.COMPANY_GCZJZXZZDJ+"']").attr("selected", true);
													$("#path_plann_file_gczjzxzzdj").val(result.COMPANY_GCZJZXZZDJ_PATH);
													$("#promotionShow_gczjzxzzdj").html(result.COMPANY_GCZJZXZZDJ_PATH.split("upload")[1]);
												    $("#dlg_company_gczjzxzzdj_bh").val(result.COMPANY_GCZJZXZZDJ_BH);
													$("#dlg_company_gczjzxzzdj_time").val(result.COMPANY_GCZJZXZZDJ_TIME);
													$("#dlg_company_gczjzxzzdj_ywfw").html(result.COMPANY_GCZJZXZZDJ_YWFW);				
													$("#dlg_company_address").val(result.COMPANY_ADDRESS);	
													$("#dlg_company_frdb_zw").val(result.COMPANY_FRDB_ZW);
													$("#dlg_company_frdb_zc").val(result.COMPANY_FRDB_ZC);
													$("#dlg_company_frdb").val(result.COMPANY_FRDB);
													$("#dlg_company_frdb_phone").val(result.COMPANY_FRDB_PHONE);
													
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
					queryP.nameType = "zbdlrInfo";
					queryP.suffix = $.trim($("#file_name_zbdlr").val()).split("\.")[1];
					if(queryP.suffix === '' || queryP.suffix === undefined || typeof queryP.suffix === 'undefined' )
						 {
							showMsg("未选择需要导入的文件！");
							return;
						 }
					$.ajaxFileUpload({
						url:"upload!importExcel.action?radom="+Math.random(),
						fileElementId:"file_name_zbdlr",
						secureuri:false,
						type:"POST",
						data:queryP,
						dataType:"json",
						success:function(data)
						{
							if(data != null && data != "" && data.result == "00")
							{
								showMsg("招标代理人基本信息导入成功！");
								zbdlr_grid.loadData({page:1});
								$("#dlg_zbdlr_upload").dialog('close');
							}else{
								showMsg("招标代理人基本信息导入失败！");
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
				params.company_name = $.trim($("#dlg_company_name").val());
				params.company_phone = $.trim($("#dlg_company_phone").val());
				params.company_yyzzh  = $.trim($("#dlg_company_yyzzh").val());
				params.company_nj_time  = $.trim($("#dlg_company_nj_time").val());
				params.company_yyzzh_path  = $.trim($("#path_plann_file_yyzzh").val());
				params.company_gczbdlzzdj  = $.trim($("#dlg_company_gczbdlzzdj").val());
				params.company_gczbdlzzdj_path  = $.trim($("#path_plann_file_gczbdlzzdj").val());
				params.company_gczbdlzzdj_bh  = $.trim($("#dlg_company_gczbdlzzdj_bh").val());
				params.company_gczbdlzzdj_time  = $.trim($("#dlg_company_gczbdlzzdj_time").val());
				params.company_gczbdlzzdj_ywfw  = $.trim($("#dlg_company_gczbdlzzdj_ywfw").val());
				params.company_gczjzxzzdj  = $.trim($("#dlg_company_gczjzxzzdj").val());
				params.company_gczjzxzzdj_path  = $.trim($("#path_plann_file_gczjzxzzdj").val());
				params.company_gczjzxzzdj_bh  = $.trim($("#dlg_company_gczjzxzzdj_bh").val());
				params.company_gczjzxzzdj_time  = $.trim($("#dlg_company_gczjzxzzdj_time").val());
				params.company_gczjzxzzdj_ywfw  = $.trim($("#dlg_company_gczjzxzzdj_ywfw").val());				
				params.company_address  = $.trim($("#dlg_company_address").val());	
				params.company_frdb_zw  = $.trim($("#dlg_company_frdb_zw").val());
				params.company_frdb_zc  = $.trim($("#dlg_company_frdb_zc").val());
				params.company_frdb  = $.trim($("#dlg_company_frdb").val());
				params.company_frdb_phone  = $.trim($("#dlg_company_frdb_phone").val());
			    // 提交方式
				   var action = $("#action").val();	
				//新增的时候才判断密码，修改的时候不需要
					if(params.company_name==null || params.company_name=='')
					{
						showMsg("企业名称不能为空！");
						$("#dlg_company_name").focus();
						return;
					}
					if(params.company_phone==null || params.company_phone=='')
					{
						showMsg("企业联系电话不能为空！");
						$("#dlg_company_phone").focus();
						return;
					}
					if(params.company_yyzzh==null || params.company_yyzzh=='')
					{
						showMsg("法人营业执照注册号不能为空！");
						$("#dlg_company_yyzzh").focus();
						return;
					}	
					if(params.company_nj_time==null || params.company_nj_time=='')
					{
						showMsg("请输入最新年检有效期！");
						$("#dlg_company_nj_time").focus();
						return;
					}
					
					
					if(params.company_gczbdlzzdj==null || params.company_gczbdlzzdj=='')
					{
						showMsg("请选择工程招标代理资质等级！");
						$("#dlg_company_gczbdlzzdj").focus();
						return;
					}
					if(params.company_gczbdlzzdj_bh==null || params.company_gczbdlzzdj_bh=='')
					{
						showMsg("工程招标代理资质等级证书编号不能为空！");
						$("#dlg_company_gczbdlzzdj_bh").focus();
						return;
					}
					if(params.company_gczbdlzzdj_time==null || params.company_gczbdlzzdj_time=='')
					{
						showMsg("请输入工程招标代理资质等级有效期！");
						$("#dlg_company_gczbdlzzdj_time").focus();
						return;
					}
					
					
					
					if(params.company_gczjzxzzdj==null || params.company_gczjzxzzdj=='')
					{
						showMsg("请选择工程造价咨询资质等级！");
						$("#dlg_company_gczjzxzzdj").focus();
						return;
					}
					if(params.company_gczjzxzzdj_bh==null || params.company_gczjzxzzdj_bh=='')
					{
						showMsg("请输入工程造价咨询资质等级资质证书！");
						$("#dlg_company_gczjzxzzdj_bh").focus();
						return;
					}
					if(params.company_gczjzxzzdj_time==null || params.company_gczjzxzzdj_time=='')
					{
						showMsg("请输入工程造价咨询资质等级有效期！");
						$("#dlg_company_gczjzxzzdj_time").focus();
						return;
					}	
					
					
					if(params.company_address==null || params.company_address=='')
					{
						showMsg("请输详细地址！");
						$("#dlg_company_address").focus();
						return;
					}
					if(params.company_frdb==null || params.company_frdb=='')
					{
						showMsg("请输入法人代表！");
						$("#dlg_company_frdb").focus();
						return;
					}
					if(params.company_frdb_zw==null || params.company_frdb_zw=='')
					{
						showMsg("请输入法人代表职务！");
						$("#dlg_company_frdb_zw").focus();
						return;
					}
					if(params.company_frdb_zc==null || params.company_frdb_zc=='')
					{
						showMsg("请输入法人代表职称！");
						$("#dlg_company_frdb_zc").focus();
						return;
					}
					if(params.company_frdb_phone==null || params.company_frdb_phone=='')
					{
						showMsg("请输入法人代表联系电话！");
						$("#dlg_company_frdb_phone").focus();
						return;
					}
					
							
				// 新增和修改通用字符串
				var url ='';
				var indicate='';
				if ("add" == action) 
				{
					indicate = "确认新增" + params.company_name + ",这个招标代理人?";
					url = "ztb!savezbdlr.action";
				} 
				else if ("edit" == action) 
				{
					params.id = $("#dlg_id").val();
					indicate = "确认更新" + params.company_name + ",这个招标代理人?";
					url = "ztb!updatezbdlr.action";
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
								clean_input_for_dailog($("#dlg_zbdlr"));
							
							$("#upyyzzh_form")[0].reset();
							$("#upgczbdlzzdj_form")[0].reset();
							$("#upgczjzxzzdj_form")[0].reset();
						         $("#promotionShow_yyzzh").html("");
						         $("#promotionShow_gczbdlzzdj").html("");
						         $("#promotionShow_gczjzxzzdj").html("");
								$('#dlg_zbdlr').dialog('close');
								zbdlr_grid.loadData({page:1});
							} else 
							{
								showMsg(result.msg);
							}
						}
					}); 
				}
				else
				{
				    $('#dlg_zbdlr').dialog('close');
				}
			}
		
	},
	// 导出数据
	commitExport : function() 
	{
				var condition = "";
				var company_name=$("#serch_company_name")==null?'':$('#serch_company_name').val();
				if (company_name && $.trim(company_name).length > 0) 
				{
					condition += "&company_name=" + encodeURI(encodeURI($.trim(company_name)));
				}
				var company_yyzzh=$("#serch_company_yyzzh")==null?'':$('#serch_company_yyzzh').val();
				if (company_yyzzh && $.trim(company_yyzzh).length > 0) 
				{
					condition += "&company_yyzzh=" + encodeURI(encodeURI($.trim(company_yyzzh)));
				}
				location.href='ztb!exportJobs_zbdlr.action?'+condition;
			
   },
   //导入excel
   importZbdlr:function(name,grid)
   	{
		//showMsg("车辆信息批量导入");
		zbdlr_grid.showDialog("招标代理人基本信息导入","import", $("#dlg_zbdlr_upload"),500,400);
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
		queryP.company_name = $('#serch_company_name').val()==null?'':$('#serch_company_name').val();
		queryP.company_yyzzh = $('#serch_company_yyzzh').val()==null?'':$('#serch_company_yyzzh').val();
		var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
		queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
		queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
		queryP.sortname = param.sortname? param.sortname : "";
		queryP.sortorder = param.sortorder? param.sortorder : "";
		$.ajax({
			type: "POST",
			url: "ztb!zbdlrList.action?operation=init",
			cache: false,
			data: queryP,
			success: function(msg){
				var data=eval("("+msg+")");
				try{
					zbdlr_grid.zbdlr_grid_result.flexAddData(data);
				}catch(e){
					showErrorMsg("查询结果信息出错！");
				}
			}
		}); 
	}
};


   function ajaxFileUpload(file_str)
   {        
     var obj=$("#plann_file");
	 if(obj.val() === '' || obj.val() === undefined || typeof obj.val() === 'undefined' )
	 {
		alert("请选择上传文件");
		return;
	 }
        
      /**  $("#loading")
        .ajaxStart(function(){
            $(this).show();
        })//开始上传文件时显示一个图片
        .ajaxComplete(function(){
            $(this).hide();
        });//文件上传完成将图片隐藏起来
        **/
        $.ajaxFileUpload
        (
            {
                url:'upload!upl.action',//用于文件上传的服务器端请求地址
                secureuri:false,//一般设置为false
                fileElementId:'plann_file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
                dataType: 'json',//返回值类型 一般设置为json
                success: function (data, status)  //服务器成功响应处理函数
                {
                    //从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量
                    if(data.mess_state==1)
                    {
                       $("#promotionShow_"+file_str).html(data.newFileName);
                       $("#path_plann_file_"+file_str).val(data.rs_path);
                       $("#plann_file").val("");
                    }
                },
                error: function (data, status, e)//服务器响应失败处理函数
                {
                    alert(e);
                    $("#plann_file").val("");
                }
            }
        )
    }

	 $(function(){
		//初始化所有操作按钮
		 var  parm_button = [
			  {name: '新建', bclass: 'add', onpress : zbdlr_grid.add},
			  {name: '修改', bclass: 'edit', onpress : zbdlr_grid.edit},
			  {name: '删除', bclass: 'delete', onpress : zbdlr_grid.del},
			  {name: '导入', bclass: 'distribute', onpress : zbdlr_grid.importZbdlr},
			  {name: '导出', bclass: 'excel', onpress : zbdlr_grid.commitExport}
		 ];
		 //权限操作按钮
		 var colModel = [						
	            {display: '序号', name : 'xh', width : 40, align: 'center'},
				{display: 'id', name : 'ID', width : 40, align: 'center',hide : true},
				{display: '企业名称', name : 'COMPANY_NAME', width : 350, align: 'left',sortable: true},
				{display: '法人营业执照注册号', name : 'COMPANY_YYZZH', width : 150, align: 'left',sortable: true},
				{display: '工程招标代理资质', name : 'COMPANY_GCZBDLZZDJ', width : 120,  align: 'left',sortable : true},
				{display: '工程造价咨询资质', name : 'COMPANY_GCZJZXZZDJ', width : 120,  align: 'left',sortable : true},				
				{display: '最新年检情况', name : 'COMPANY_NJ_TIME_FORMAT_DATE', width : 120, align : 'left',sortable : false,
						process:function(obj,pid)
						{
								$(obj).html("至"+$(obj).text());
						}
				},
				{display: '法人代表', name : 'COMPANY_FRDB', width : 120,  align: 'left',sortable : true}
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
				zbdlr_grid.bindParamGrid(buttons,colModel);
			}
		});
		 
		 
		 
		 		/*参数管理-查询按钮功能*/
		$('#btn_zbdlr_search').button().on("click",function(){
			zbdlr_grid.loadData({page:1});
		});
				//清空筛选框
		$('#btn_zbdlr_empty').on("click",function(){
			$("#serch_company_name").val("");
			$("#serch_company_yyzzh").val("");
		}); 
		 		 
	});
	
	//导入的模板文件
	function downloadModel(obj)
	{
	    if(obj != null && obj != "")
		window.location.href = "upload!exportExcelModelForTable.action?suffix="+obj+"&nameType=zbdlrInfo"+"&radom="+Math.random();
     }
	
	
	
		
		function FileUp1() 
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
        )*/
         $("#upyyzzh_form").submit();
		};
		
		//
		  
		function FileUp2() 
		{     
		   $("#upgczbdlzzdj_form").submit(); 
	    };
	      //
	      
	       
		function FileUp3() 
		{     
		   $("#upgczjzxzzdj_form").submit(); 
	      };
	      
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
