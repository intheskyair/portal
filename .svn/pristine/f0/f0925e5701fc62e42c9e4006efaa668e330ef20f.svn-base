 <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 <script type="text/javascript">
var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
var page_num = $(window.self.document).find("#page_num").val();
var page_num =30;
var dlg_jys_dialog;
var dlg_jys_load;

function showInfo(rowData){
						var dlg_jys_bt = rowData.TITLE;
						var dlg_jys_sxbh = rowData.CODE;
						var dlg_jys_sqdw = rowData.UNIT;
						var dlg_jys_slbm = rowData.ACCEPT;
						var dlg_jys_spbm = rowData.APPROVAL;
						var dlg_jys_wjm = rowData.FILENAME;
						$("#dlg_jys_bt").val(dlg_jys_bt);
						$("#dlg_jys_sxbh").val(dlg_jys_sxbh);
						$("#dlg_jys_sqdw").val(dlg_jys_sqdw);
						$("#dlg_jys_slbm").val(dlg_jys_slbm);
						$("#dlg_jys_spbm").val(dlg_jys_spbm);
						$("#promotionShow_jys").html(dlg_jys_wjm);
						
						$("#jys_file").hide();
						
						var dlgParent = $("#dlg_jys").parent();
				        var dlgOwn = $("#dlg_jys").clone();
				        var dlg_jys_ck =
					    $("#dlg_jys").dialog({
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
									clean_input_for_dailog($("#dlg_jys"));
									$("#jys_file_form")[0].reset();
								    $("#promotionShow_jys").html("");
								    $("#jys_file").show();
			         				$(this).dialog("destroy").remove();
			         			}
							}
					    });
					    dlg_jys_ck.dialog("open");
}

//ie8下面的弹窗必须改成这样的方式
var	dlg_jys = function(data){
	dlg_jys_dialog = $("#dlg_jys").dialog({
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
							jys_grid.saveData(data.operation);
							//clean_input_for_dailog($("#dlg_jys"));
						},
						"关闭": function() {
							clean_input_for_dailog($("#dlg_jys"));
							$("#jys_file_form")[0].reset();
						         $("#promotionShow_jys").html("");
							//console.log(dlgParent.attr("id"));
							//$(this).dialog("destroy").remove();
							//dlgOwn.appendTo(dlgParent);
	         				$(this).dialog("close");
	         			}
					}
				});
				
				dlg_jys_load = $("#dlg_jys_upload").dialog({
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
							jys_grid.saveData(data.operation);
							clean_input_for_dailog($("#dlg_jys_upload"));
						},
						"关闭": function() {
							clean_input_for_dailog($("#dlg_jys_upload"));
							//console.log(dlgParent.attr("id"));
							//$(this).dialog("destroy").remove();
							//dlgOwn.appendTo(dlgParent);
	         				$(this).dialog("close");
	         			}
					}
				});
				
  
				
};

 var jys_grid = {
	//绑定参数信息grid
	jys_grid_result:"",
	jysGridHeight:flexHeight("jys_grid"),
	bindParamGrid:function(queryP,queryM){
		jys_grid.jys_grid_result = $("#jys_grid").flexigrid({
			url:"proposal!getProposalList.action?operation=init",
			colModel: queryM,
			buttons : queryP,
			rowId:"id",
			showToggleBtn: true,
			usepager: true,
			title:"项目建议书列表",
			//rp:pageSize,
			onRowDblclick:showInfo,
			rp:page_num,
			showTableToggleBtn: false,
			resizable: false,
			height: jys_grid.jysGridHeight,
			singleSelect: true,
			onChangePage:jys_grid.loadData,
			checkbox:false,
			filterParams : [{key : "title", name : "#serch_bt_jys"},
			                {key : "code", name : "#serch_sxbh_jys"},
			                {key : "approval", name : "#serch_spbm_jys"} ]
		});
	},			
		// 查询子任务并且显示
	showDialog : function(com,operation,divobj,h,w) 
		{
		var dlgParent = divobj.parent();
		var dlgOwn = divobj.clone();
		jys_grid.dlg_jys = divobj.dialog({
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
						jys_grid.saveData(operation);
		              	//$(this).dialog("destroy").remove();
					}
				},
			    close : function() {
		              	dlgOwn.appendTo(dlgParent);
		              	$(this).dialog("destroy").remove();
		         }
			});
			
		jys_grid.dlg_jys.dialog("option", "title", com).dialog("open");
	},	
	add:function(com,grid)
	{
		
		if (com == "新建") 
				{
				    jys_grid.showDialog("新增项目建议书","add",$("#dlg_jys"));
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
									url: "proposal!deleteProject_jys.action",
									cache: false,
									data: params,
									success: function(msg)
									{
										var result=eval("("+msg+")");
										if (result.msg=="ok") 
										{
											showMsg("删除成功！");
											$('#dlg_jys').dialog('close');
											jys_grid.loadData({page:1});
										} else 
										{
											showMsg(result.msg);
											$('#dlg_jys').dialog('close');
										}
									}
								}); 
							}else
							{
								$('#dlg_jys').dialog('close');
							}
						
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_jys').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_jys').dialog('close');
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
					   jys_grid.showDialog("修改项目建议书","edit",$("#dlg_jys"));
				       $("#action").val("edit");
						var id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
						var dlg_jys_bt = $('.trSelected', grid).eq(0).find("td:eq(2)").text();
						var dlg_jys_sxbh = $('.trSelected', grid).eq(0).find("td:eq(3)").text();
						var dlg_jys_sqdw = $('.trSelected', grid).eq(0).find("td:eq(4)").text();
						var dlg_jys_slbm = $('.trSelected', grid).eq(0).find("td:eq(5)").text();
						var dlg_jys_spbm = $('.trSelected', grid).eq(0).find("td:eq(6)").text();
						var dlg_jys_wjm = $('.trSelected', grid).eq(0).find("td:eq(9)").text();
						var dlg_jys_wjlj = $('.trSelected', grid).eq(0).find("td:eq(10)").text();
						$("#dlg_id").val(id);	
						$("#dlg_jys_bt").val(dlg_jys_bt);
						$("#dlg_jys_sxbh").val(dlg_jys_sxbh);
						$("#dlg_jys_sqdw").val(dlg_jys_sqdw);
						$("#dlg_jys_slbm").val(dlg_jys_slbm);
						$("#dlg_jys_spbm").val(dlg_jys_spbm);
						if(dlg_jys_wjm != null && dlg_jys_wjm !=""){
						$("#promotionShow_jys").html(dlg_jys_wjm);
						}else{
						$("#promotionShow_jys").html("");
						}
						$("#path_jys_file").val(dlg_jys_wjlj);
						var reupload =$.trim(dlg_jys_wjm);
						if((reupload!=null)&&(reupload!="")){
						$("#sub_jys").val("重新上传");
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
					queryP.nameType = "proposalInfo";
					queryP.suffix = $.trim($("#file_name_jys").val()).split("\.")[1];
					if(queryP.suffix === '' || queryP.suffix === undefined || typeof queryP.suffix === 'undefined' )
						 {
							showMsg("未选择需要导入的文件！");
							return;
						 }
					$.ajaxFileUpload({
						url:"upload!importExcel.action?radom="+Math.random(),
						fileElementId:"file_name_jys",
						secureuri:false,
						type:"POST",
						data:queryP,
						dataType:"json",
						success:function(data)
						{
							if(data != null && data != "" && data.result == "00")
							{
								showMsg("建议书导入成功！");
								jys_grid.loadData({page:1});
								$("#dlg_jys_upload").dialog('close');
							}else{
								showMsg("建议书导入失败！");
							}
						},
						error:function(){
							showMsg("文件导入失败");
						}
					});

	          }else{
	          var params=new Object();
				params.title = $.trim($("#dlg_jys_bt").val());
				params.code = $.trim($("#dlg_jys_sxbh").val());
				params.unit = $.trim($("#dlg_jys_sqdw").val());
				params.accept  = $.trim($("#dlg_jys_slbm").val());
				params.approval  = $.trim($("#dlg_jys_spbm").val());
				params.filename=$.trim($("#promotionShow_jys").html());
				params.filepath=$.trim($("#path_jys_file").val());
				params.createtime=$.trim(new Date().format("yyyy-MM-dd hh:mm:ss"));
				
			    // 提交方式
				   var action = $("#action").val();	
				//新增的时候才判断密码，修改的时候不需要
					if(params.title==null || params.title=='')
					{
						showMsg("项目标题不能为空！");
						$("#dlg_jys_bt").focus();
						return;
					}
					if(params.code==null || params.code=='')
					{
						showMsg("事项编号不能为空！");
						$("#dlg_jys_sxbh").focus();
						return;
					}
					if(params.unit==null || params.unit=='')
					{
						showMsg("申请单位不能为空！");
						$("#dlg_jys_sqdw").focus();
						return;
					}
					if(params.accept==null || params.accept=='')
					{
						showMsg("受理部门不能为空！");
						$("#dlg_jys_slbm").focus();
						return;
					}
					if(params.approval==null || params.approval=='')
					{
						showMsg("审批部门不能为空！");
						$("#dlg_jys_spbm").focus();
						return;
					}
					if(params.filepath==null || params.filepath=='')
					{
						showMsg("请上传一个对应的文件！");
						return;
					}
								
				// 新增和修改通用字符串
				var url ='';
				var indicate='';
				if ("add" == action) 
				{
					indicate = "确认新增" + params.title + ",这个项目规划?";
					url = "proposal!saveProject_jys.action";
				} 
				else if ("edit" == action) 
				{
					params.id = $("#dlg_id").val();
					indicate = "确认更新" + params.title + ",这个项目规划?";
					url = "proposal!updateProject_jys.action";
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
								clean_input_for_dailog($("#dlg_jys"));
							$("#jys_file_form")[0].reset();
						         $("#promotionShow_jys").html("");
								$('#dlg_jys').dialog('close');
								jys_grid.loadData({page:1});
							} else 
							{
								showMsg(result.msg);
							}
						}
					}); 
				}
				else
				{
				    $('#dlg_jys').dialog('close');
				}
	          }
			    
		
	},
	// 导出数据
	commitExport : function() 
	{
				var condition = "";
				var serch_bt_jys=$("#serch_bt_jys")==null?'':$('#serch_bt_jys').val();
				if (serch_bt_jys && $.trim(serch_bt_jys).length > 0) 
				{
					condition += "&title=" + encodeURI(encodeURI($.trim(serch_bt_jys)));
				}
				var serch_sxbh_jys=$("#serch_sxbh_jys")==null?'':$('#serch_sxbh_jys').val();
				if (serch_sxbh_jys && $.trim(serch_sxbh_jys).length > 0) 
				{
					condition += "&code=" + encodeURI(encodeURI($.trim(serch_sxbh_jys)));
				}
				var serch_spbm_jys=$("#serch_spbm_jys")==null?'':$('#serch_spbm_jys').val();
				if (serch_spbm_jys && $.trim(serch_spbm_jys).length > 0) 
				{
					condition += "&approval=" + encodeURI(encodeURI($.trim(serch_spbm_jys)));
				}
				location.href='proposal!exportJobs.action?'+condition;
			
   },
    importjys:function(name,grid)
   	{
		//showMsg("车辆信息批量导入");
		jys_grid.showDialog("建议书导入","import", $("#dlg_jys_upload"),500,400);
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
   mydownload:function(path)
   {
   		
        location.href='upload!download.action?&path='+ encodeURI(encodeURI(unescape(path))); 
   },
	loadData:function(param){
		var queryP = {};
		queryP.title = $('#serch_bt_jys').val()==null?'':$('#serch_bt_jys').val();
		queryP.code = $('#serch_sxbh_jys').val()==null?'':$('#serch_sxbh_jys').val();
		queryP.approval = $('#serch_spbm_jys').val()==null?'':$('#serch_spbm_jys').val();
		var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
		queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
		queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
		queryP.sortname = param.sortname? param.sortname : "";
		queryP.sortorder = param.sortorder? param.sortorder : "";
		$.ajax({
			type: "POST",
			url: "proposal!getProposalList.action?operation=init",
			cache: false,
			data: queryP,
			success: function(msg){
				var data=eval("("+msg+")");
				try{
					jys_grid.jys_grid_result.flexAddData(data);
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
		window.location.href = "upload!exportExcelModelForTable.action?suffix="+obj+"&nameType=proposalInfo"+"&radom="+Math.random();
     }
	
	//调用返回上传结果 
    function get_upfile_result(res){
     	if(1 == Number(res.mess_state)){
     		alert(res.message);
     		$("#promotionShow_jys").html(res.newFileName);
	        $("#path_jys_file").val(res.rs_path);
     	}else{
     		alert(res.message);
     	}
    };
		function FileUp() 
		{        
         /*$.ajaxFileUpload
          (
            {
                url:'upload!upl.action',//用于文件上传的服务器端请求地址
                secureuri:false,//一般设置为false
                fileElementId:jys_file,//文件上传空间的id属性  <input type="file" id="file" name="file" />
                dataType: 'json',//返回值类型 一般设置为json
                success: function (data, status)  //服务器成功响应处理函数
                {
                    //从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量
                    if(data.mess_state==1)
                    {	
                    	
                       $("#promotionShow_jys").html(data.newFileName);
                       $("#path_jys_file").val(data.rs_path);
                       $("#"+jys_file).val("");
                       $("#sub_jys").val("重新上传");
                    }
                },
                error: function (data, status, e)//服务器响应失败处理函数
                {
                    alert(e);
                    $("#"+jys_file).val("");
                  
                }
            }
        )*/
            $("#jys_file_form").submit();
		};

 
	 $(function(){
		//初始化所有操作按钮
		var  parm_button = [
			  {name: '新建', bclass: 'add', onpress : jys_grid.add},
			  {name: '修改', bclass: 'edit', onpress : jys_grid.edit},
			  {name: '删除', bclass: 'delete', onpress : jys_grid.del},
			  {name: '导入', bclass: 'distribute', onpress : jys_grid.importjys},
			  {name: '导出', bclass: 'excel', onpress : jys_grid.commitExport}
		 ];
		 //权限操作按钮
		 var colModel = [
						{display: '序号', name : 'xh', width : 40, align: 'center'},
						{display: 'id', name : 'ID', width : 40, align: 'center',hide : true,sortable: true},
						{display: '标题', name : 'TITLE', width : 150,  align: 'left'},
						{display: '事项编号', name : 'CODE', width : 150,  align: 'left'},
						{display: '申请单位', name : 'UNIT', width : 200, align: 'left'},
						{display: '受理部门', name : 'ACCEPT', width : 150,  align: 'left'},
						{display: '审批部门', name : 'APPROVAL', width : 150, align: 'center',},
						{display: '创建时间', name : 'CREATETIME', width : 150,  align: 'left'},
						{display: '操作人id', name : 'UID', width : 250,  align: 'left', hide : true},
						{display: '文件名', name : 'FILENAME', width : 250,  align: 'left',process:function(value){
						  var file_name =$.trim($(value).html());
						  if(file_name == null || file_name == ""){
						  $(value).html("");
						  }
						}},
						{display: '文件路径', name : 'FILEPATH', width : 120,  align: 'left',sortable : false,hide : true},
						{display: '下载文件', name : 'OP', width : 100,  align: 'center',
						
						
						process:function(obj, pid, $td)
							{
								$tr = $td.closest("tr");
								var file_path = $('div', $tr).eq(10).text();
								var filename=$.trim($('div', $tr).eq(9).text());
								var  str_path = file_path.replace(/\@/g,"\\");
								var htmlContent="";
								if(filename.length == 0){
									htmlContent = '&nbsp;<span style="color:gray;cursor:pointer;disabled:true">下载</span>';
								}else{
									htmlContent = '&nbsp;<span style="color:blue;cursor:pointer" onclick="jys_grid.mydownload(\'' + escape(str_path) +'\')">下载</span>';	
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
				jys_grid.bindParamGrid(buttons,colModel);
			}
		});
		 
		  
		/*参数管理-查询按钮功能*/
		$("#btn_jys_search_jys").button().on("click",function(){
			jys_grid.loadData({page:1});
		});
				//清空筛选框
		$('#btn_result_empty_jys').on("click",function(){
			$("#serch_bt_jys").val("");
			$("#serch_sxbh_jys").val("");
			$("#serch_spbm_jys").val("");
			jys_grid.loadData({page:1});
		});
		
	});
</script>
<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='../images/fold.png' onclick="showAndHide(this,'jys_grid');"  class="imgShrotate">
				<font>查询条件</font>
			</h3>
		</div>
	<div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>

					<td >
						<label for="input_1" class="lbl-1">
							标题:
						</label>
						<input type="text" id="serch_bt_jys" name="serch_bt_jys" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<label for="input_1" class="lbl-1">
							事项编号:
						</label>
						<input type="text" id="serch_sxbh_jys" name="serch_sxbh_jys" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<label for="input_1" class="lbl-1">
							审批部门：
						</label>
						<input type="text" id="serch_spbm_jys" name="serch_spbm_jys" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<button id="btn_jys_search_jys" class="ui-state-default ui-state-qik">查询
						</button>			
						<button id="btn_result_empty_jys" class="ui-state-default ui-state-qik">清空
						</button>
					</td>
				</tr>
			</table>
		</div>
</div>
<div class="div div-2" style="">
	<table id="jys_grid" style="display:none;padding:0 0 0 0;"></table>
</div>
<div id="dlg_jys" style="display:none;overflow-y: hidden;">
	<input type="hidden" id="action" />
	<input type="hidden" id="dlg_id" /> 
	<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">标题：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_jys_bt" name="dlg_jys_bt"  class="wi-ipt-1" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">事项编号：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_jys_sxbh" value="" class="wi-ipt-1" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">申请单位：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_jys_sqdw" value="" class="wi-ipt-1"/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">受理部门：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_jys_slbm" value="" class="wi-ipt-1"/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">
				<label style="color:red;">*</label>
				<label for="input_1">审批部门：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_jys_spbm" value="" class="wi-ipt-1"/>
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
			    	method="POST" enctype="multipart/form-data" id="jys_file_form" target="result_upfile" >
			    <input type="hidden" name="path_jys_file" id="path_jys_file" />
				<input type="file" id="jys_file" onchange="FileUp()" name="jys_file"/>
				<!--  <input type="button" id="sub_jys" value="上传" onclick="uploadpic('jys_file');">-->
				</form>
				<div id="promotionShow_jys"></div>
				<iframe id="result_upfile" name="result_upfile" style="position:absolute; top:-9999px; left:-9999px">
					
				</iframe>
			</td>
		</tr>
	</table>
	<div style="overflow: hidden">
		
	</div>
</div>
<div id="dlg_jys_upload" style="display:none;overflow-y: hidden;">
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
				<input type="file" id="file_name_jys" name="file_name_jys" accept=".xls,.xlsx"/>
			</td>
		</tr>
	</table>
</div>	