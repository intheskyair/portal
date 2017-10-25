<%@ page contentType="text/html; charset=UTF-8"%>
<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='../images/fold.png' onclick="showAndHide(this,'jihua_grid');"  class="imgShrotate">
				<font>规划计划</font>
			</h3>
		</div>
	<div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>
					<td>
					<label for="input_1" class="lbl-1">
							文件类型:
					</label>
					<select name="serch_jihua_type" id="serch_jihua_type" class="wi-sel-1" style="width:200px">
					        <option value="">---全部---</option>
							<option value="建设用地规划计划">建设用地规划计划</option>
							<option value="住宅用地规划计划">住宅用地规划计划</option>
							<option value="商业用地规划计划">商业用地规划计划</option>
					</select>
					</td>
					<td class="wi-align-l">
						<label for="input_1" class="lbl-1">
							标题:
						</label>
						<input type="text" id="serch_jihua_title" name="serch_jihua_title" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l" >
						<label for="input_1" class="lbl-1">
							创建时间：
						</label>
						<input type="text" id="serch_jihua_create_time" name="serch_jihua_create_time" readonly class="Wdate" style="height:22px;width:55%;"
					 onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
					</td>
					<td class="wi-align-l">
						<button id="btn_jihua_search" class="ui-state-default ui-state-qik">查询
						</button>
					
						<button id="btn_jihua_empty" class="ui-state-default ui-state-qik">清空
						</button>
					</td>
				</tr>
			</table>
		</div>
</div>
<div class="div div-2" style="">
	<table id="jihua_grid" style="display:none;padding:0 0 0 0;"></table>
</div>
<div id="dlg_jihua" style="display:none;overflow-y: hidden;">
	<input type="hidden" id="action" />
	<input type="hidden" id="dlg_id" /> 
	<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">文件类型：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<select name="dlg_jihua_type" id="dlg_jihua_type" class="wi-sel-1" style="width:200px">
				            <option value="">---请选择---</option>
							<option value="建设用地规划计划">建设用地规划计划</option>
							<option value="住宅用地规划计划">住宅用地规划计划</option>
							<option value="商业用地规划计划">商业用地规划计划</option>
				</select>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">标题：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_jihua_title" value="" class="wi-ipt-1" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">上传文件：</label>
			</td>
			<td class="wi-align-l" style="width:300px;">
			 <form action="upload!upl.action" method="POST" enctype="multipart/form-data" id="upjihua_form" target="result_upfile" >
			    <input type="hidden" name="path_plann_file_jihua" id="path_plann_file_jihua" />
				<input type="file" id="plann_file_jihua" onchange="FileUp()" name="plann_file_jihua" />
				<!--  <input type="button" style="width:50px" value="上传" onclick="uploadpic('jihua','plann_file_jihua');"/>-->
				</form>
				<div id="promotionShow_jihua"></div>
				<iframe id="result_upfile" name="result_upfile" style="position:absolute; top:-9999px; left:-9999px">
					
				</iframe>
			</td>
		</tr>
	</table>
</div>	

<div id="dlg_jihua_upload" style="display:none;overflow-y: hidden;">
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
				<input type="file" id="file_name_jihua" name="file_name_jihua" accept=".xls,.xlsx"/>
			</td>
		</tr>
	</table>
</div>
<script>		
var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
var page_num = $(window.self.document).find("#page_num").val();
var page_num =30;
var dlg_jihua_dialog;
var dlg_jihua_load;

function showInfo(rowData){
						var file_type = rowData.FILE_TYPE;
						var file_title = rowData.FILE_TITLE;
						var promotionShow = rowData.FILE_NAME;
						
						$("#dlg_jihua_type option[value='"+file_type+"']").attr("selected", true);
						$("#dlg_jihua_title").val(file_title);
						$("#promotionShow_jihua").html(promotionShow);
						
						$("#plann_file_jihua").hide();
						
						var dlgParent = $("#dlg_jihua").parent();
				        var dlgOwn = $("#dlg_jihua").clone();
				        var dlg_jihua_ck =
					    $("#dlg_jihua").dialog({
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
									clean_input_for_dailog($("#dlg_jihua"));
									$("#upjihua_form")[0].reset();
								    $("#promotionShow").html("");
								    $("#plann_file_jihua").show();
			         				$(this).dialog("destroy").remove();
			         			}
							}
					    });
					    dlg_jihua_ck.dialog("open");
}



//ie8下面的弹窗必须改成这样的方式
var	dlg_jihua = function(data){
	dlg_jihua_dialog = $("#dlg_jihua").dialog({
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
							jihua_grid.saveData(data.operation);
							//clean_input_for_dailog($("#dlg_jihua"));
						},
						"关闭": function() {
							clean_input_for_dailog($("#dlg_jihua"));
							$("#upjihua_form")[0].reset();
						         $("#promotionShow_jihua").html("");
							//console.log(dlgParent.attr("id"));
							//$(this).dialog("destroy").remove();
							//dlgOwn.appendTo(dlgParent);
	         				$(this).dialog("close");
	         			}
					}
				});
				
				dlg_jihua_load = $("#dlg_jihua_upload").dialog({
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
							jihua_grid.saveData(data.operation);
							clean_input_for_dailog($("#dlg_jihua_upload"));
						},
						"关闭": function() {
							clean_input_for_dailog($("#dlg_jihua_upload"));
							//console.log(dlgParent.attr("id"));
							//$(this).dialog("destroy").remove();
							//dlgOwn.appendTo(dlgParent);
	         				$(this).dialog("close");
	         			}
					}
				});
};
var jihua_grid = {
	//绑定参数信息grid
	jihua_grid_result:"",
	GridHeight:flexHeight("jihua_grid"),
	bindParamGrid:function(queryP,queryM){
		jihua_grid.jihua_grid_result = $("#jihua_grid").flexigrid({
			url:"plann!jihuaList.action?operation=init",
			colModel: queryM,
			buttons : queryP,
			rowId:"id",
			showToggleBtn: true,
			usepager: true,
			title:"计划管理列表",
			//rp:pageSize,
			onRowDblclick:showInfo,
			rp:page_num,
			showTableToggleBtn: false,
			resizable: false,
			height: jihua_grid.GridHeight,
			singleSelect: true,
			onChangePage:jihua_grid.loadData,
			checkbox:false,
			filterParams : [{key : "file_type", name : "#serch_jihua_type"},
			                {key : "file_title", name : "#serch_jihua_title"},
			                {key : "create_time", name : "#serch_jihua_create_time"} ]
		});
	},			
		// 查询子任务并且显示
	showDialog : function(com,operation,divobj,h,w) 
		{
		var dlgParent = divobj.parent();
		var dlgOwn = divobj.clone();
		jihua_grid.dlg_jihua = divobj.dialog({
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
						jihua_grid.saveData(operation);
		              	//$(this).dialog("destroy").remove();
					}
				},
			    close : function() {
		              	dlgOwn.appendTo(dlgParent);
		              	$(this).dialog("destroy").remove();
		         }
			});
			
		jihua_grid.dlg_jihua.dialog("option", "title", com).dialog("open");
	},	
	add:function(com,grid)
	{
		
		if (com == "新建") 
				{
		        $("#dlg_jihua :input").each(function () { 
			      $(this).val(""); 
			
		        }); 
		          $("#upjihua_form :input").each(function () { 
			     $(this).val(""); 
		         }); 
		         
				    jihua_grid.showDialog("新增规划计划","add",$("#dlg_jihua"));
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
					        var indicate = "确认需要删除" + title+ ",这个规划计划吗?";	
					        var params=new Object();
				                params.id = id;					
							if(confirm(indicate)) 
							{
								$.ajax({
									type: "POST",
									url: "plann!deljihua.action",
									cache: false,
									data: params,
									success: function(msg)
									{
										var result=eval("("+msg+")");
										if (result.msg=="ok") 
										{
											showMsg("删除成功！");
											$('#dlg_jihua').dialog('close');
											jihua_grid.loadData({page:1});
										} else 
										{
											showMsg(result.msg);
											$('#dlg_jihua').dialog('close');
										}
									}
								}); 
							}else
							{
								$('#dlg_jihua').dialog('close');
							}
						
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_jihua').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_jihua').dialog('close');
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
					   jihua_grid.showDialog("修改规划计划","edit", $("#dlg_jihua"));
				       $("#action").val("edit");
						var id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
						var file_type = $('.trSelected', grid).eq(0).find("td:eq(2)").text();
						var file_title = $('.trSelected', grid).eq(0).find("td:eq(3)").text();
						var path_plann_file = $('.trSelected', grid).eq(0).find("td:eq(6)").text();
						var promotionShow = $('.trSelected', grid).eq(0).find("td:eq(7)").text();
						$("#dlg_id").val(id);	
						$("#dlg_jihua_type option[value='"+file_type+"']").attr("selected", true);
						$("#dlg_jihua_title").val(file_title);
						$("#path_plann_file_jihua").val(path_plann_file);
						$("#promotionShow_jihua").html(promotionShow);					
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
					queryP.nameType = "ghjh";
					queryP.suffix = $.trim($("#file_name_jihua").val()).split("\.")[1];
					if(queryP.suffix === '' || queryP.suffix === undefined || typeof queryP.suffix === 'undefined' )
						 {
							showMsg("未选择需要导入的文件！");
							return;
						 }
					$.ajaxFileUpload({
						url:"upload!importExcel.action?radom="+Math.random(),
						fileElementId:"file_name_jihua",
						secureuri:false,
						type:"POST",
						data:queryP,
						dataType:"json",
						success:function(data)
						{
							if(data != null && data != "" && data.result == "00")
							{
								showMsg("规划计划导入成功！");
								jihua_grid.loadData({page:1});
								$("#dlg_jihua_upload").dialog('close');
							}else{
								showMsg("规划计划导入失败！");
							}
						},
						error:function(){
							showMsg("文件导入失败");
						}
					});

	          }else{
			    var params=new Object();
				params.FILE_TYPE = $.trim($("#dlg_jihua_type").val());
				params.FILE_TITLE = $.trim($("#dlg_jihua_title").val());
				params.FILE_PATH  = $.trim($("#path_plann_file_jihua").val());
				params.FILE_NAME  = $.trim($("#promotionShow_jihua").html());
			    // 提交方式
				   var action = $("#action").val();	
				   //alert(params.FILE_PATH);
				//新增的时候才判断密码，修改的时候不需要
					if(params.FILE_TYPE==null || params.FILE_TYPE=='')
					{
						showMsg("文件类型不能为空！");
						$("#dlg_jihua_type").focus();
						return;
					}
					if(params.FILE_TITLE==null || params.FILE_TITLE=='')
					{
						showMsg("标题不能为空！");
						$("#dlg_jihua_title").focus();
						return;
					}
					if(params.FILE_PATH==null || params.FILE_PATH=='')
					{
						showMsg("请上传一个对应的文件！");
						return;
					}				
				// 新增和修改通用字符串
				var url ='';
				var indicate='';
				if ("add" == action) 
				{
					indicate = "确认新增" + params.FILE_TITLE + ",这个规划计划?";
					url = "plann!savejihua.action";
				} 
				else if ("edit" == action) 
				{
					params.ID = $("#dlg_id").val();
					indicate = "确认更新" + params.FILE_TITLE + ",这个规划文件?";
					url = "plann!updatejihua.action";
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
								clean_input_for_dailog($("#dlg_jihua"));
								$("#upjihua_form")[0].reset();
						         $("#promotionShow_jihua").html("");
								$('#dlg_jihua').dialog('close');
								jihua_grid.loadData({page:1});
							} else 
							{
								showMsg(result.msg);
							}
						}
					}); 
				}
				else
				{
				    $('#dlg_jihua').dialog('close');
				}
				}
		
	},
	// 导出数据
	commitExport : function() 
	{
				var condition = "";
				var serch_jihua_type=$("#serch_jihua_type")==null?'':$("#serch_jihua_type").val();
				if (serch_jihua_type && $.trim(serch_jihua_type).length > 0) 
				{
					condition += "&file_type=" +  encodeURI(encodeURI($.trim(serch_jihua_type)));
				}
				var serch_file_title=$("#serch_jihua_title")==null?'':$("#serch_jihua_title").val();
				if (serch_file_title && $.trim(serch_file_title).length > 0) 
				{
					condition += "&file_title=" +  encodeURI(encodeURI($.trim(serch_file_title)));
				}
				var serch_create_time=$("#serch_jihua_create_time")==null?'':$("#serch_jihua_create_time").val();
				if (serch_create_time && $.trim(serch_create_time).length > 0) 
				{
					condition += "&create_time=" + $.trim(serch_create_time);
				}
				location.href='plann!exportjihua.action?'+condition;
			
   },
   
    //导入excel
   importghjh:function(name,grid)
   	{
		
		jihua_grid.showDialog("规划计划导入","import", $("#dlg_jihua_upload"),500,400);
	},
   //下载附件
//    mydownload:function(path)
//    {
//      var obj=$("#"+path);
//      alert(obj.val());
// 	 if(obj.val() === '' || obj.val() === undefined || typeof obj.val() === 'undefined' )
// 	 {
// 		showErrorMsg("上传附件不存在或你未上传附件！");
// 		return;
// 	 }
// 	 else
// 	 {
//         location.href='upload!download.action?&path='+ encodeURI(encodeURI(unescape(obj.val()))); 
//      }
//    },
   mydownload:function(path)
   {
        location.href='upload!download.action?&path='+ encodeURI(encodeURI(unescape(path))); 
   },
   	loadData:function(param){
		var queryP = {};
		queryP.file_type = $('#serch_jihua_type').val()==null?'':$('#serch_jihua_type').val();
		queryP.file_title = $('#serch_jihua_title').val()==null?'':$('#serch_jihua_title').val();
		queryP.create_time = $('#serch_jihua_create_time').val()==null?'':$('#serch_jihua_create_time').val();
		var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
		queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
		queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
		queryP.sortname = param.sortname? param.sortname : "";
		queryP.sortorder = param.sortorder? param.sortorder : "";
		$.ajax({
			type: "POST",
			url: "plann!jihuaList.action?operation=init",
			cache: false,
			data: queryP,
			success: function(msg){
				var data=eval("("+msg+")");
				try{
					jihua_grid.jihua_grid_result.flexAddData(data);
				}catch(e){
					showErrorMsg("查询结果信息出错！");
				}
			}
		}); 
	}
};


		function FileUp() 
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
         $("#upjihua_form").submit();
		};


	 $(function(){
		//初始化所有操作按钮
		 var  parm_button = [
			  {name: '新建', bclass: 'add', onpress : jihua_grid.add},
			  {name: '修改', bclass: 'edit', onpress : jihua_grid.edit},
			  {name: '删除', bclass: 'delete', onpress : jihua_grid.del},
			  {name: '导入', bclass: 'distribute', onpress : jihua_grid.importghjh},
			  {name: '导出', bclass: 'excel', onpress : jihua_grid.commitExport}
		 ];
		 //权限操作按钮
		 var colModel = [						
	            {display: '序号', name : 'xh', width : 40, align: 'center'},
				{display: 'id', name : 'ID', width : 40, align: 'center',hide : true},
				{display: '文件类型', name : 'FILE_TYPE', width : 100, align: 'left',sortable: true},
				{display: '标题', name : 'FILE_TITLE', width : 120, align: 'left',sortable: true},
				{display: '创建时间', name : 'CREATE_TIME_FORMAT_DATE', width : 50,  align: 'left',sortable : false},
				{display: '更新时间', name : 'UPDATE_TIME_FORMAT_DATE', width : 50,  align: 'left',sortable : false},
				{display: '文件路径', name : 'FILE_PATH', width : 100,  align: 'left',sortable : false,hide : true},
				{display: '文件名', name : 'FILE_NAME', width : 200,  align: 'left',sortable : false},
				{display: '下载文件', name : 'OP', width : 100,  align: 'center',
						process:function(obj, pid, $td)
						{
							$tr = $td.closest("tr");
							var file_path = $('div', $tr).eq(6).text();
							var filename=$.trim($('div', $tr).eq(7).text());
							var  str_path = file_path.replace(/\@/g,"\\");
							var htmlContent="";
							if(filename.length == 0){
								htmlContent = '&nbsp;<span style="color:gray;cursor:pointer;disabled:true">下载</span>';
								}else{
							 	htmlContent = '&nbsp;<span style="color:blue;cursor:pointer" onclick="jihua_grid.mydownload(\'' + escape(str_path) +'\')">下载</span>';
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
				jihua_grid.bindParamGrid(buttons,colModel);
			}
		}); 
		 
		 
		 		/*参数管理-查询按钮功能*/
		$('#btn_jihua_search').button().on("click",function(){
			jihua_grid.loadData({page:1});
		});
				//清空筛选框
		$('#btn_jihua_empty').on("click",function(){
			$("#serch_jihua_type").val("");
			$("#serch_jihua_title").val("");
			$("#serch_jihua_create_time").val("");
		}); 
		 		 
	});
	
	//导入的模板文件
	function downloadModel(obj)
	{
	    if(obj != null && obj != "")
		window.location.href = "upload!exportExcelModelForTable.action?suffix="+obj+"&nameType=ghjh"+"&radom="+Math.random();
     }
     
     //调用返回上传结果 
    function get_upfile_result(res){
     	if(1 == Number(res.mess_state)){
     		alert(res.message);
     		$("#promotionShow_jihua").html(res.newFileName);
	        $("#path_plann_file_jihua").val(res.rs_path);
     	}else{
     		alert(res.message);
     	}
    };
</script>
