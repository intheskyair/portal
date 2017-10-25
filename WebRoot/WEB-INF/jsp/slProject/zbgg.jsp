<%@ page contentType="text/html; charset=UTF-8"%>
<style>
.sr {
	 border:0px;
	 border-bottom:1px solid #ccc;
	 height:25px; 
	 line-height:25px;
}
.btt{ font-size:24px; font-weight:bold;height:35px}
.bt{ font-size:12px;height:28px;margin-top: 10px;}
</style>
<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='../images/fold.png' onclick="showAndHide(this,'zbgg_grid');"  class="imgShrotate">
				<font>${head}</font>
			</h3>
		</div>
	<div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>
					<td >
					<label for="input_1" class="lbl-1">
							公告标题:
					</label>
                        <input type="text" id="serch_zb_title" name="serch_zb_title" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<label for="input_1" class="lbl-1">
							招标编号:
						</label>
						<input type="text" id="serch_zb_bianhao" name="serch_zb_bianhao" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<button id="btn_zbgg_search" class="ui-state-default ui-state-qik">查询
						</button>
					
						<button id="btn_zbgg_empty" class="ui-state-default ui-state-qik">清空
						</button>
					</td>
				</tr>
			</table>
		</div>
</div>
<div class="div div-2" style="">
	<table id="zbgg_grid" style="display:none;padding:0 0 0 0;"></table>
</div>
<div id="dlg_zbgg" class="main" style="display:none;overflow:auto;">
	<input type="hidden" id="action" />
	<input type="hidden" id="dlg_id" /> 
	<p class="btt"  style=" text-align:center">第一章 招标公告</p>
	<p class="bt" style=" text-align:center"><input type="text" class="sr" id="dlg_zb_title" style="width:600px "valid="required"/>标段招标实时公告</p>
	<br/>
	<p class="btt"  style=" text-align:left">招标条件</p>
	<p class="bt">本招标项目<input type="text" class="sr"  id="dlg_zb_project" style="width:300px" valid="required"/> 已由<input type="text" class="sr" id="dlg_zb_pizhun" style="width:400px " valid="required"/> 批准</p>
	<p class="bt">建设，项目业主为<input type="text" class="sr" id="dlg_zb_yezhu" style="width:300px" valid="required"/>建设资金来自<input type="text" class="sr"  id="dlg_zb_zjly" style="width:300px" valid="required"/>，招标人</p>
	<p class="bt">为<input type="text" class="sr" id="dlg_zb_ren" style="width:300px" valid="required"/>招标代理机构<input type="text" class="sr" id="dlg_zb_dljg"	 style="width:300px " valid="required"/>。项目已具备招标条件</p>
	<p class="bt">现对项目的实时工进行公开招标。</p>
	<p class="btt"  style=" text-align:left">项目概况与招标范围</p>
	<p class="bt">2.1招标编号<input type="text" class="sr" id="dlg_zb_bianhao" style="width:400px " valid="required"/></p>
	<p class="bt">2.2建设地点<input type="text" class="sr" id="dlg_zb_jsdd" style="width:400px " valid="required"/></p>
	<p class="bt">2.3工程规模：工程总投资约<input type="text" class="sr" id="dlg_zb_gcztz" style="width:400px " valid="required"/>万元</p>
	<p class="bt">2.4最高控制价<input type="text" class="sr" id="dlg_zb_zgkj" style="width:100px" valid="required"/>万元，不可竞争金额：<input type="text" class="sr" id="dlg_zb_bkjjje"  style="width:100px " valid="required"/>万元（最迟在开标5天前发布）</p>
	<p class="bt">2.5开标时间<input type="text" id="dlg_zb_begtime"
							 class="sr" style="width:120px"
						onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,readOnly: true})" valid="required"/>投标截止：<input type="text" id="dlg_zb_endtime"
							 class="sr" style="width:120px"
						onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,readOnly: true})" valid="required"/></p>
	<p class="bt">2.6招标范围:</p>
	<textarea id="dlg_zb_fanwei" class="dwb" style=" height:80px;width:95%;border:1px solid #ccc;"></textarea>
</div>	
<div id="dlg_zbgg_upload" style="display:none;overflow-y: hidden;">
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
				<input type="file" id="file_name_zbgg" name="file_name_zbgg" accept=".xls,.xlsx"/>
			</td>
		</tr>
	</table>
</div>	
<script>		
var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
var page_num = $(window.self.document).find("#page_num").val();
var page_num =30;
var dlg_zbgg_dialog;
var dlg_zbgg_load;


function showInfo(rowData){
						var id = rowData.ID;
                        var params=new Object();
				        params.id = id;
							$.ajax({
									type: "POST",
									url: "ztb!getzbgg.action",
									cache: false,
									data: params,
									success: function(msg)
									{
									    if(msg != null)
									    {
											var result=eval("("+msg+")");			
																													
													$("#dlg_id").val(id);	
													$("#dlg_zb_title").val(result.ZB_TITLE);
				                                    $("#dlg_zb_project").val(result.ZB_PROJECT);
													$("#dlg_zb_pizhun").val(result.ZB_PIZHUN);
													$("#dlg_zb_yezhu").val(result.ZB_YEZHU);
													$("#dlg_zb_zjly").val(result.ZB_ZJLY);
													$("#dlg_zb_ren").val(result.ZB_REN);
													$("#dlg_zb_dljg").val(result.ZB_DLJG);
													$("#dlg_zb_bianhao").val(result.ZB_BIANHAO);
													$("#dlg_zb_jsdd").val(result.ZB_JSDD);
													$("#dlg_zb_gcztz").val(result.ZB_GCZTZ);
												    $("#dlg_zb_zgkj").val(result.ZB_ZGKJ);
													$("#dlg_zb_bkjjje").val(result.ZB_BKJJJE);
													$("#dlg_zb_fanwei").val(unescape(result.ZB_FANWEI));
													$("#dlg_zb_begtime").val(result.ZB_BEGTIME_FORMAT_DATE);
													$("#dlg_zb_endtime").val(result.ZB_ENDTIME_FORMAT_DATE);
													
									  }
									}
								});
								
						var dlgParent = $("#dlg_zbgg").parent();
				        var dlgOwn = $("#dlg_zbgg").clone();
				        var dlg_zbgg_ck =		
					    $("#dlg_zbgg").dialog({
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
									clean_input_for_dailog($("#dlg_zbgg"));
									$(this).dialog("destroy").remove();
	         					}
							}
					    });
					     dlg_zbgg_ck.dialog("open");
					    
}

//ie8下面的弹窗必须改成这样的方式
var	dlg_zbgg = function(data){
	dlg_zbgg_dialog = $("#dlg_zbgg").dialog({
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
							zbgg_grid.saveData(data.operation);
							//clean_input_for_dailog($("#dlg_zbgg"));
						},
						"关闭": function() {
							clean_input_for_dailog($("#dlg_zbgg"));
							//console.log(dlgParent.attr("id"));
							//$(this).dialog("destroy").remove();
							//dlgOwn.appendTo(dlgParent);
	         				$(this).dialog("close");
	         			}
					}
				});
				
				dlg_zbgg_load = $("#dlg_zbgg_upload").dialog({
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
							zbgg_grid.saveData(data.operation);
							clean_input_for_dailog($("#dlg_zbgg_upload"));
						},
						"关闭": function() {
							clean_input_for_dailog($("#dlg_zbgg_upload"));
							//console.log(dlgParent.attr("id"));
							//$(this).dialog("destroy").remove();
							//dlgOwn.appendTo(dlgParent);
	         				$(this).dialog("close");
	         			}
					}
				});
				
  
				
};

var zbgg_grid = {
	//绑定参数信息grid
	zbgg_grid_result:"",
	GridHeight:flexHeight("zbgg_grid"),
	bindParamGrid:function(queryP,queryM){
		zbgg_grid.zbgg_grid_result = $("#zbgg_grid").flexigrid({
			url:"ztb!zbggList.action?operation=init",
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
			height: zbgg_grid.GridHeight,
			singleSelect: true,
			onChangePage:zbgg_grid.loadData,
			checkbox:false,
			filterParams : [{key : "zb_title", name : "#serch_zb_title"},
			                {key : "zb_bianhao", name : "#serch_zb_bianhao"} ]
		});
	},			
		// 查询子任务并且显示
	showDialog : function(com,operation,divobj,h,w) 
		{
		var dlgParent = divobj.parent();
		var dlgOwn = divobj.clone();
		zbgg_grid.dlg_zbgg = divobj.dialog({
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
						zbgg_grid.saveData(operation);
		              	//$(this).dialog("destroy").remove();
					}
				},
			    close : function() {
		              	dlgOwn.appendTo(dlgParent);
		              	$(this).dialog("destroy").remove();
		         }
			});
			
		zbgg_grid.dlg_zbgg.dialog("option", "title", com).dialog("open");
	},	
	add:function(com,grid)
	{
		
		if (com == "新建") 
				{
				    zbgg_grid.showDialog("新增招标公告信息","add",$("#dlg_zbgg"),"auto","auto");
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
					        var indicate = "确认需要删除" + title+ ",这个招标公告吗?";	
					        var params=new Object();
				                params.id = id;					
							if(confirm(indicate)) 
							{
								$.ajax({
									type: "POST",
									url: "ztb!delzbgg.action",
									cache: false,
									data: params,
									success: function(msg)
									{
										var result=eval("("+msg+")");
										if (result.msg=="ok") 
										{
											showMsg("删除成功！");
											$('#dlg_zbgg').dialog('close');
											zbgg_grid.loadData({page:1});
										} else 
										{
											showMsg(result.msg);
											$('#dlg_zbgg').dialog('close');
										}
									}
								}); 
							}else
							{
								$('#dlg_zbgg').dialog('close');
							}
						
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_zbgg').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_zbgg').dialog('close');
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
									url: "ztb!getzbgg.action",
									cache: false,
									data: params,
									success: function(msg)
									{
									    if(msg != null)
									    {
					   zbgg_grid.showDialog("修改招标公告","edit",$("#dlg_zbgg"),"auto","auto");
											var result=eval("("+msg+")");			
													$("#dlg_id").val(id);	
													$("#dlg_zb_title").val(result.ZB_TITLE);
				                                    $("#dlg_zb_project").val(result.ZB_PROJECT);
													$("#dlg_zb_pizhun").val(result.ZB_PIZHUN);
													$("#dlg_zb_yezhu").val(result.ZB_YEZHU);
													$("#dlg_zb_zjly").val(result.ZB_ZJLY);
													$("#dlg_zb_ren").val(result.ZB_REN);
													$("#dlg_zb_dljg").val(result.ZB_DLJG);
													$("#dlg_zb_bianhao").val(result.ZB_BIANHAO);
													$("#dlg_zb_jsdd").val(result.ZB_JSDD);
													$("#dlg_zb_gcztz").val(result.ZB_GCZTZ);
												    $("#dlg_zb_zgkj").val(result.ZB_ZGKJ);
													$("#dlg_zb_bkjjje").val(result.ZB_BKJJJE);
													$("#dlg_zb_fanwei").val(unescape(result.ZB_FANWEI));
													$("#dlg_zb_begtime").val(result.ZB_BEGTIME_FORMAT_DATE);
													$("#dlg_zb_endtime").val(result.ZB_ENDTIME_FORMAT_DATE);
													
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
					queryP.nameType = "zbggInfo";
					queryP.suffix = $.trim($("#file_name_zbgg").val()).split("\.")[1];
					if(queryP.suffix === '' || queryP.suffix === undefined || typeof queryP.suffix === 'undefined' )
						 {
							showMsg("未选择需要导入的文件！");
							return;
						 }
					$.ajaxFileUpload({
						url:"upload!importExcel.action?radom="+Math.random(),
						fileElementId:"file_name_zbgg",
						secureuri:false,
						type:"POST",
						data:queryP,
						dataType:"json",
						success:function(data)
						{
							if(data != null && data != "" && data.result == "00")
							{
								showMsg("招标公告基本信息导入成功！");
								zbgg_grid.loadData({page:1});
								$("#dlg_zbgg_upload").dialog('close');
							}else{
								showMsg("招标公告基本信息导入失败！");
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
				params.zb_title = $.trim($("#dlg_zb_title").val());
				params.zb_project = $.trim($("#dlg_zb_project").val());
				params.zb_pizhun  = $.trim($("#dlg_zb_pizhun").val());
				params.zb_yezhu  = $.trim($("#dlg_zb_yezhu").val());
				params.zb_zjly  = $.trim($("#dlg_zb_zjly").val());
				params.zb_ren  = $.trim($("#dlg_zb_ren").val());
				params.zb_dljg  = $.trim($("#dlg_zb_dljg").val());
				params.zb_bianhao  = $.trim($("#dlg_zb_bianhao").val());
				params.zb_jsdd  = $.trim($("#dlg_zb_jsdd").val());
				params.zb_gcztz  = $.trim($("#dlg_zb_gcztz").val());
				params.zb_zgkj  = $.trim($("#dlg_zb_zgkj").val());
				params.zb_bkjjje  = $.trim($("#dlg_zb_bkjjje").val());
				params.zb_fanwei  = $.trim($("#dlg_zb_fanwei").val());
				params.zb_begtime  = $.trim($("#dlg_zb_begtime").val());
				params.zb_endtime  = $.trim($("#dlg_zb_endtime").val());
				var map = {}; // Map map = new HashMap();
                map['dlg_zb_title'] = '招标标题'; // map.put(key, value);
				map['dlg_zb_project'] = '招标项目';
				map['dlg_zb_pizhun'] = '项目批准人';
				map['dlg_zb_yezhu'] = '项目业主';
				map['dlg_zb_zjly'] = '建设资金来源';
				map['dlg_zb_ren'] = '招标人';
				map['dlg_zb_dljg'] = '招标代理机构';
				map['dlg_zb_bianhao'] = '招标编号';
				map['dlg_zb_jsdd'] = '建设地点';
				map['dlg_zb_gcztz'] = '工程总投资';
				map['dlg_zb_zgkj'] = '招标最高控价';
				map['dlg_zb_bkjjje'] = '不可竞争金额';
				map['dlg_zb_begtime'] = '开标时间';
				map['dlg_zb_endtime'] = '投标截止';	
				    // 提交方式
				 var reg = /^[0-9]*$/;
				 if(params.zb_title==""||params.zb_title==null){
				 	showMsg("招标标题输入不正确！");
               	  	$("#dlg_zb_title").focus();
						return;
           		  }else
           		  if(params.zb_project==""||params.zb_project==null){
				 	showMsg("招标项目输入不正确！");
               	  	$("#dlg_zb_project").focus();
						return;
           		  }else
           		  if(params.zb_pizhun==""||params.zb_pizhun==null){
				 	showMsg("项目批准人输入不正确！");
               	  	$("#dlg_zb_pizhun").focus();
						return;
           		  }else
           		  if(params.zb_yezhu==""||params.zb_yezhu==null){
				 	showMsg("项目业主输入不正确！");
               	  	$("#dlg_zb_yezhu").focus();
						return;
           		  }else
           		  if(params.zb_zjly==""||params.zb_zjly==null){
				 	showMsg("建设资金来源输入不正确！");
               	  	$("#dlg_zb_zjly").focus();
						return;
           		  }else
           		  if(params.zb_ren==""||params.zb_ren==null){
				 	showMsg("招标人输入不正确！");
               	  	$("#dlg_zb_ren").focus();
						return;
           		  }else
           		  if(params.zb_dljg==""||params.zb_dljg==null){
				 	showMsg("招标代理机构输入不正确！");
               	  	$("#dlg_zb_dljg").focus();
						return;
           		  }else
           		  if(params.zb_bianhao==""||params.zb_bianhao==null){
				 	showMsg("招标编号输入不正确！");
               	  	$("#dlg_zb_bianhao").focus();
						return;
           		  }else
           		  if(params.zb_jsdd==""||params.zb_jsdd==null){
				 	showMsg("建设地点输入不正确！");
               	  	$("#dlg_zb_jsdd").focus();
						return;
           		  }else
           		  if(params.zb_gcztz==""||params.zb_gcztz==null){
				 	showMsg("工程总投资输入不正确！");
               	  	$("#dlg_zb_gcztz").focus();
						return;
           		  }else
           		  if(params.zb_zgkj==""||params.zb_zgkj==null){
				 	showMsg("招标最高控价输入不正确！");
               	  	$("#dlg_zb_zgkj").focus();
						return;
           		  }else
           		  if(params.zb_bkjjje==""||params.zb_bkjjje==null){
				 	showMsg("不可竞争金额输入不正确！");
               	  	$("#dlg_zb_bkjjje").focus();
						return;
           		  }
           		  else
				  if(params.zb_gcztz != "" && reg.test(params.zb_gcztz) == false){
               	  showMsg("工程总投资一栏输入必须是数字！");
               	  $("#dlg_zb_gcztz").focus();
						return;
           		  }else
           		  if(params.zb_zgkj != "" && reg.test(params.zb_zgkj) == false){
           		  showMsg("招标最高控价格式错误");
           		  $("#dlg_zb_zgkj").focus();
						return;
           		  }else
           		  if(params.zb_bkjjje != "" && reg.test(params.zb_bkjjje) == false){
           		  showMsg("不可竞争金额格式错误");
           		  $("#dlg_zb_bkjjje").focus();
						return;
           		  }else
           		  
           		  if(params.zb_begtime>params.zb_endtime){
		          showMsg("结束时间小于开始时间");
						return;
		          }
           		  else{
				 var action = $("#action").val();						
				// 新增和修改通用字符串
				var url ='';
				var indicate='';
				if($("#dlg_zbgg").validate())
				{
					if ("add" == action) 
					{
						indicate = "确认新增" + params.zb_title + ",这个招标公告?";
						url = "ztb!savezbgg.action";
					} 
					else if ("edit" == action) 
					{
						params.id = $("#dlg_id").val();
						indicate = "确认更新" + params.zb_title + ",这个招标公告?";
						url = "ztb!updatezbgg.action";
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
										clean_input_for_dailog($("#dlg_zbgg"));
						
										$('#dlg_zbgg').dialog('close');
										zbgg_grid.loadData({page:1});
									} else 
									{
										showMsg(result.msg);
									}
								}
							}); 
						}
						else
						{
						    $('#dlg_zbgg').dialog('close');
						}
			}
		}
	  }
	},
		// 导出数据
	commitExport : function() 
	{
				var condition = "";
				var serch_zb_title=$("#serch_zb_title")==null?'':$('#serch_zb_title').val();
				if (serch_zb_title && $.trim(serch_zb_title).length > 0) 
				{
					condition += "&zb_title=" + encodeURI(encodeURI($.trim(serch_zb_title)));
				}
				var serch_zb_bianhao=$("#serch_zb_bianhao")==null?'':$('#serch_zb_bianhao').val();
				if (serch_zb_bianhao && $.trim(erch_zb_bianhao).length > 0) 
				{
					condition += "&zb_bianhao=" + encodeURI(encodeURI($.trim(erch_zb_bianhao)));
				}
				location.href='ztb!exportJobs_zbgg.action?'+condition;
			
   },
   //导入excel
   importZbgg:function(name,grid)
   	{
		//showMsg("车辆信息批量导入");
		zbgg_grid.showDialog("招标公告基本信息导入","import", $("#dlg_zbgg_upload"),500,400);
	},
   mydownload:function(path)
   {
        location.href='upload!download.action?&path='+ encodeURI(encodeURI(unescape(path))); 
   },
   	loadData:function(param){
		var queryP = {};
		queryP.zb_title = $('#serch_zb_title').val()==null?'':$('#serch_zb_title').val();
		queryP.zb_bianhao = $('#serch_zb_bianhao').val()==null?'':$('#serch_zb_bianhao').val();
		var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
		queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
		queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
		queryP.sortname = param.sortname? param.sortname : "";
		queryP.sortorder = param.sortorder? param.sortorder : "";
		$.ajax({
			type: "POST",
			url: "ztb!zbggList.action?operation=init",
			cache: false,
			data: queryP,
			success: function(msg){
				var data=eval("("+msg+")");
				try{
					zbgg_grid.zbgg_grid_result.flexAddData(data);
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
			  {name: '新建', bclass: 'add', onpress : zbgg_grid.add},
			  {name: '修改', bclass: 'edit', onpress : zbgg_grid.edit},
			  {name: '删除', bclass: 'delete', onpress : zbgg_grid.del},
			  {name: '导入', bclass: 'distribute', onpress : zbgg_grid.importZbgg},
			  {name: '导出', bclass: 'excel', onpress : zbgg_grid.commitExport}
		 ];
		 //权限操作按钮
		 var colModel = [						
	            {display: '序号', name : 'xh', width : 40, align: 'center'},
				{display: 'id', name : 'ID', width : 40, align: 'center',hide : true},
				{display: '招标标题', name : 'ZB_TITLE', width : 150, align: 'left',sortable: true},
				{display: '招标编号', name : 'ZB_BIANHAO', width : 120,  align: 'left',sortable : true},
				{display: '开标时间', name : 'ZB_BEGTIME_FORMAT_DATE', width : 120,  align: 'left',sortable : false},
				{display: '投标截止', name : 'ZB_ENDTIME_FORMAT_DATE', width : 120,  align: 'left',sortable : false},
				{display: '工程总投资', name : 'ZB_GCZTZ', width : 120,  align: 'left',sortable : true,
						process:function(obj,pid)
						{
								$(obj).html(+$(obj).text()+"万元");
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
				zbgg_grid.bindParamGrid(buttons,colModel);
			}
		});
		 
		 
		 		/*参数管理-查询按钮功能*/
		$('#btn_zbgg_search').button().on("click",function(){
			zbgg_grid.loadData({page:1});
		});
				//清空筛选框
		$('#btn_zbgg_empty').on("click",function(){
			$("#serch_zb_title").val("");
			$("#serch_zb_bianhao").val("");
		}); 
		 		 
	});
	//导入的模板文件
	function downloadModel(obj)
	{
	    if(obj != null && obj != "")
		window.location.href = "upload!exportExcelModelForTable.action?suffix="+obj+"&nameType=zbggInfo"+"&radom="+Math.random();
     }
</script>
