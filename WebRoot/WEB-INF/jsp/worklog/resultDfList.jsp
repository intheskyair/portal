<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    <link rel="stylesheet" href="../styles/fullCalendar/css/fullcalendar.css">
	<link rel="stylesheet" href="../styles/fullCalendar/css/fullcalendar.print.css" media='print'>
	<script src="../styles/fullCalendar/js/fullcalendar.min.js"></script>
<!-- 	<script src="../styles/fullCalendar/js/custom.js"></script> -->
	<title>工作日志列表</title>
	</head>
	<body>	
	<div class="div div-1">
<!-- 	<div class="div-main-1" > -->
<!-- 			<table cellpadding="0" cellspacing="0" border="0" class="table-1"> -->
<!-- 				<tr> -->
<!-- 					<td ><label for="input_1" class="lbl-1">日志标题：</label></td> -->
<!-- 					<td class="wi-align-l" > -->
						
<!-- 					</td> -->
<!-- 							<td class="wi-align-l"> -->
<!-- 						<button id="btn_result_search" class="ui-state-default ui-state-qik">查询 -->
<!-- 						</button> -->
<!-- 					</td> -->
<!-- 					<td class="wi-align-l" style="width: 100px;"> -->
<!-- 						<button id="btn_szxc_empty" class="ui-state-default ui-state-qik">清空 -->
<!-- 						</button> -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
<!-- 		</div> -->
	</div>
	<input type="hidden" id="title_can" value="${admin.adminId}" name="title_can" class="wi-ipt-1" />
	<div class="div div-2" style="width:60%;float:left">
		<table id="worklog_grid" style="display:none;padding:0 0 0 0;"></table>
		<input type="hidden" id="line_main_id" />
	</div>
	<div style="border: 1px solid #97CCFD; float: right; height: 90%; width: 39%; margin-top: 0px;">
			<div class="container">
		<div class="content">
			<div class="row-fluid">
				<div class="span12">
					<div class="box">
						<div class="box-head">
							<h3>日历</h3>
						</div>
						<div class="box-content box-nomargin">
							<div class="calendar"></div>
						</div>
					</div>
				</div>
			</div>
		</div>	
		</div>
	</div>
<div id="dlg_line_worklog" style="display:none;overflow-y: hidden;">
	<div class="div div-2" style="overflow:auto;">
		<table id="sub_worklog_grid" style="display:none;padding:0 0 0 0; padding-bottom: 50px;"></table>
	</div>
</div>	



	<div id="dlg_wlog" style="display:none;overflow: auto;">
	<input type="hidden" id="dlg_id" /> 
	<input type="hidden" id="action" />
		<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl" style="">
			<tr>
				<td style="width:100px;"><label style="color:red;">*</label><label for="input_1">标题：</label></td>
				<td class="wi-align-l" style="width:200px;">
					<input type="text" id="dlg_title"  value="" class="wi-ipt-1" style=" width:100%"/>
				</td>
			</tr>
			<tr>
					<td style="width:100px;"><label style="color:red;">*</label><label for="input_1">日期：</label></td>
					<td class="wi-align-l" style="width:200px;">
						<input type="text" id="dlg_time"  onfocus="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d %H:%m:%s',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
							readonly class="Wdate" style="width:200px;"
						/>
					</td>
			</tr>
			<tr>
				<td style="width:100px;"><label style="color:red;">*</label><label for="input_1">内容：</label></td>
				<td class="wi-align-l" style="width:200px;">
					<textarea rows="24"  cols="300" id="dlg_content" style="overflow-y: auto;width:100%"></textarea>
				</td>
			</tr>
		</table>
	</div> 
	

		<script>
		
		function decodeTextAreaString(str) {
		    var reg = new RegExp("<br/>", "g");
		    str = str.replace(reg, "\n");
		    return str;
		}
		var paramGridHeight_worklog = flexHeight("worklog_grid");
		 var param_worklog = {
			 param_grid_result:"",
			//绑定参数信息grid
			bindParamGrid:function(queryP,queryM){
				param_worklog.param_grid_result = $("#worklog_grid").flexigrid({
					url:"worklog!resultDfList.action?operation=init",
					colModel: queryM,
					buttons : queryP,
					rowId:"id",
					showToggleBtn: true,
					usepager: true,
					title:"工作日志列表",
					//rp:pageSize,
					rp:page_num,
					height: paramGridHeight_worklog,
					showTableToggleBtn: false,
					resizable: false,
					singleSelect: true,
					onChangePage:param_worklog.loadData,
					checkbox:false,
					filterParams : [{key : "title", name : "#title_can"}]
				});
			},
			loadData:function(param){
				var queryP = {};
				queryP.title = $('#title_can').val()==null?'':$('#title_can').val();
				var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
				queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
				queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
				queryP.sortname = param.sortname? param.sortname : "";
				queryP.sortorder = param.sortorder? param.sortorder : "";
				$.ajax({
					type: "POST",
					url: "worklog!resultDfList.action?operation=init",
					cache: false,
					data: queryP,
					success: function(msg){
						var data=eval("("+msg+")");
						try{
							param_worklog.param_grid_result.flexAddData(data);
						}catch(e){
							showErrorMsg("查询结果信息出错！");
						}
					}
				}); 
			}
		};
 	
	var dlg_wlog;	 
	 var param_mzpc = {
		//新增和编辑参数信息
		editParam:function(com, grid){
			var dlgOwn = $("#dlg_wlog").clone();
			var dlgParent = $("#dlg_wlog").parent();
            dlg_wlog = $("#dlg_wlog").dialog({
				autoOpen: false,
				height:$(window).height()*0.8,
				width:$(window).width()*0.5,
				modal:true,
				cache:false,
				title:'新增日志',
				buttons:{
					"保存":function(){
						param_mzpc.saveParam();
						}
				},
			    close : function() {
                	dlgOwn.appendTo(dlgParent);
                	$(this).dialog("destroy").remove();
                }
			});
            if ("新建" == com) {
            	 $("#action").val("add");
            	 var date=new Date();//
                 var year = date.getFullYear();
                 var month = date.getMonth()+1;
                 var day = date.getDate();
                 if (month < 10) {
                     month = "0" + month;
                 }
                 if (day < 10) {
                     day = "0" + day;
                 }
                 $("#dlg_time").val(year+"-"+month+"-"+day);
            	 dlg_wlog.dialog("option", "title", "新建工作日志").dialog("open");
            }else if (com == "修改"){
            	if($('.trSelected', grid).length==1){
            		$("#action").val("edit");
            		var id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
					$("#dlg_id").val(id);
					var params=new Object();
					params.id = id;
		            $.ajax({
						url : "worklog!getWorkInfo.action",
						type : "post",
						dataType : "json",
						data: params,
						success : function(data_res){
							$("#dlg_title").val(data_res.LOG_TITLE);
							$("#dlg_time").val(data_res.LOG_DATE);
							$("#dlg_content").val(decodeTextAreaString(data_res.LOG_CONTENT));
							dlg_wlog.dialog("option", "title", "修改工作日志").dialog("open");
						}
		            });
		            
            	}else{
            		 $('#dlg_mzpc').dialog('close');
 					showMsg("未选中行！");
            	}
            }
			
		},
		saveParam:function(){
			var params=new Object();
			params.title = $.trim(HtmlEncode($("#dlg_title").val()));
			params.content = con=$.trim(HtmlEncode($("#dlg_content").val()));
			params.logdate  = $("#dlg_time").val();
			params.username  = "${admin.truename}";
			params.user_id  = "${admin.adminId}";
			 // 提交方式
			var action = $("#action").val();	
			if(params.title==null || params.title==''){
				showMsg("标题不能为空！");
				return;
			}
			if(params.logdate==null || params.logdate==''){
				showMsg("日期不能为空！");
				return;
			}
			if(params.content==null || params.content==''){
				showMsg("内容能为空！");
				return;
			}
			// 新增和编辑通用字符串
			var url ='';
			if ("add" == action) {
				url = "worklog!addWorkLog.action";
			}else if ("edit" == action){
				params.id = $("#dlg_id").val();
				url = "worklog!updateWork.action";
			}else{
				
			}
			
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
						$('#dlg_wlog').dialog('close');
						param_worklog.loadData({});
						
						$('.calendar').fullCalendar('refetchEvents');
						
					} else 
					{
						showMsg(result.msg);
					}
				}
			});
		}	
	 };
	 $(function(){
// 		 alert(1);
//     	 $("#dlg_time").val((new Date(), "yyyy-MM-dd HH:mm:ss"));
		 //权限操作按钮
		 var colModel = [						
		                {display: '序号', name : 'xh', width : 40, align: 'center'},
		                {display: 'id', name : 'LOG_ID', width : 40, align: 'center',hide : true,sortable: true},
						{display: '日志标题', name : 'LOG_TITLE', width : 350, align: 'left',sortable: false},
						{display: '用户名', name : 'USERNAME', width : 150, align: 'left',sortable: false},
						{display: '日期', name : 'LOG_DATE', width : 150, align: 'left',sortable: false}
						];
		//初始化所有操作按钮
		 var  parm_button = [{name: '新建', bclass: 'add', onpress : param_mzpc.editParam},
							{name: '修改', bclass: 'edit', onpress : param_mzpc.editParam}];
		
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
		 	 			// 初始化数据
// 		 param_worklog.bindParamGrid(parm_button,colModel);
		
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
				param_worklog.bindParamGrid(buttons,colModel);
			}
		});
			 			
		 	 			
		/*参数管理-查询按钮功能*/
		$('#btn_result_search').button({ icons: {primary:'ui-icon-search'} }).on("click",function(){
// 			param_worklog.loadData({page:1});
		});

		//清空筛选框
		$('#btn_result_empty').on("click",function(){
			$("#title_can").val("");
			//param_mzpc.loadData({page:1});
		});
		
		//動態加載日志日曆標記事件
		$('.calendar').fullCalendar({
		 	events: {
                url: 'worklog!dateToJson.action',
                type: 'POST',
                error: function() {
                    alert('参数错误');
                },
                color:'#BFE1FE',// 背景色
                textColor:'black'// 文字颜色
            }
		});
		
		
		
	});
		
	 
		</script>
	</body>
</html>
