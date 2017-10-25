<%@ page contentType="text/html; charset=UTF-8"%>
<html>
    <head>
	<title>我的发文</title>
		 <script>x=-1</script>
	</head>
	<body style="">	
	<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='../images/fold.png' onclick="showAndHide(this,'mypost_grid');"  class="imgShrotate">
				<font>我的发文</font>
			</h3>
		</div>
	<div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>
					<td class="wi-align-l"><label for="input_1" class="lbl-1">流程标题：</label>
					<input type="text" id="title" name="title" class="wi-ipt-i " /></td>
					<td class="wi-align-l" >
					<label for="input_1" class="lbl-1">开始时间：</label>
					<input type="text" id="startTime" name="startTime" readonly class="wi-ipt-i"
						onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
						<label for="input_1" style="float: left">&nbsp;至&nbsp;</label>
						<input type="text" id="endTime" name="endTime" readonly  class="wi-ipt-i"
						onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" /></td>
					<td class="wi-align-l" >
					<button id="btn_mzpc_search" class="ui-state-default ui-state-qik">查询</button>
					<button id="btn_mzpc_empty" class="ui-state-default ui-state-qik">清空</button></td>
				</tr>
			</table>
		</div>
	</div>
	<div class="div div-2" style="overflow-x: hidden;height: 770px;">
		<table id="mypost_grid" style="display:none;padding:0 0 0 0;"></table>
	</div>
	
	<div id="mypost_List" style="display:none;overflow: scroll;">
				<div class="outer">
					<ul class="tab">
						<li class="current">流程详情</li>
						<li >查看表单</li>					    
					</ul>

					<div class="content_y">
					      <ul>
					     	<li class="current">
					     		<div id="mypost_List_table1"></div>
					     	</li>
					    </ul>
						<ul style="display:none">
							<li >
						    	<div id="mypost_List_table"></div>
						    </li>
						</ul>				   		   
					</div>			
					</div>
		     </div>
	
		<script type="text/javascript">		
		 var data_all;
		 var paramGridHeight_mypost = flexHeight("mypost_grid");
		 var param_grid_mypost;
		 var menuTree;
		 var treeNodes;
		
		 var param_lczl_mypost = {
			//绑定参数信息grid
			bindParamGrid:function(queryP,queryM){
				param_grid_mypost = $("#mypost_grid").flexigrid({
					url:"docum!mypost.action?operation=init&processId=${processId}",
					colModel: queryM,
					buttons : queryP,
					rowId:"id",
					showToggleBtn: true,
					usepager: true,
					title:"我的发文",
					//rp:pageSize,
					rp:page_num,
					showTableToggleBtn: false,
					resizable: false,
					height: paramGridHeight_mypost,
					singleSelect: true,
					onChangePage:param_lczl_mypost.loadData,
					checkbox:false,
					filterParams : [{key : "title", name : "#title"}]
				});
			},
			loadData:function(param){
				var queryP = {};
				queryP.title = $('#title').val()==null?'':$('#title').val();
				queryP.startTime = $('#startTime').val()==null?'':$('#startTime').val();
				queryP.endTime = $('#endTime').val()==null?'':$('#endTime').val();
				var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
				queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
				queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
				$.ajax({
					type: "POST",
					url: "docum!mypost.action?operation=init&processId=${processId}",
					cache: false,
					data: queryP,
					success: function(msg){
						var data=eval("("+msg+")");
						try{
							param_grid_mypost.flexAddData(data);
						}catch(e){
							showErrorMsg("查询结果信息出错！");
						}
					}
				}); 
			},	
			
			createHtml:function(result)
			{
			    			var data=eval("("+result+")");
							var result = "";
							if(data!="")
							{
								result +=  '<table cellpadding="0" cellspacing="0"  class="table-y">'
									 +     '<tr>'
									 +       '<td colspan="5" style="font-size:12px;text-align:center;" bgcolor="#dddddd">个人流程详情</td>'
									 +     '</tr>';
								result +=  '<tr>'
									 +       '<td style="width:150px;font-size:12px;font-weight: bold;text-align:center">审批环节</td>'
									 +       '<td style="width:100px;font-size:12px;font-weight: bold;text-align:center">处理人</td>'
									 +       '<td style="width:200px;font-size:12px;font-weight: bold;text-align:center">职务</td>'
									 +       '<td style="width:200px;font-size:12px;font-weight: bold;text-align:center">流程发起时间</td>'
									 +       '<td style="width:200px;font-size:12px;font-weight: bold;text-align:center">流程结束时间</td>'
									 +   '</tr>';
								for (var i in data) 
								{
									if(data[i].status != 'end')
									{
										result += '<tr>'
											 +      '<td style="width:150px;font-size:12px;text-align:center">'+ data[i].DISPLAY_NAME +'</td>'
											 +      '<td style="width:150px;font-size:12px;text-align:center">'+ data[i].TRUENAME +'</td>'
											 +      '<td style="width:150px;font-size:12px;text-align:center">'+ data[i].ORG_INFOR +'</td>'
											 +      '<td style="width:200px;font-size:12px;text-align:center">'+ data[i].CREATE_TIME +'</td>'
											 if(data[i].FINISH_TIME == undefined)
											 {
												 result += '<td style="width:200px;font-size:12px;text-align:center;color:red;">未处理</td>'
											 }else
											 {
												 result +=  '<td style="width:200px;font-size:12px;text-align:center;">'+ data[i].FINISH_TIME +'</td>'
											 }
											 result +=   '</tr>';
									}else
									{
										result +=  '<tr>'
											 +       '<td colspan="5" style="font-size:12px;text-align:center;" bgcolor="#dddddd">流程结束</td>'
											 +   '</tr>';
									}
								}		
								result +='</table>';
							}
							else
							{
								result =  '<span style="font-size:14px;color:red">数据出错，未查到相应流程！</span>';
							}
							return result;			
			},
			//个人流程查询
			personDetails : function(com,grid) {
			$("#mypost_List_table").html("");
				$("#mypost_List_table1").html("");
		         if($('.trSelected', grid).length==1){
		                    var url= '';
							var data = {};
							
							var orderId = $('.trSelected', grid).eq(0).find("td:eq(1)").text(); 
							var pid = $('.trSelected', grid).eq(0).find("td:eq(2)").text();
							for(var i = 0; i < 2 ; i++){
								if(i == 0){
									com = '查看表单';
								}else{
									com = '流程详情';								
								}
								
								if(com == '查看表单'){
									url = "borrow!view.action";
									 data = {"orderId" : orderId,"processId":pid,"path":"documView"};
								}else{
									
									 url = "lczl!viewStep.action";
									 data = {"orderId" : orderId};
								}
							
							$.ajax({
								type:"POST",
								data:data,
								cache: false,
								async:false,
								url: url,
								success:function(result)
								{	
								
								    if(com == '流程详情'){
								    var result_str = param_lczl_mypost.createHtml(result);
								    $("#mypost_List_table1").append(result_str);
								    }else{
								    $("#mypost_List_table").append(result);
								    }
								},
								error:function(result)
								{
									
									$("#mypost_List_table").append("<span style='font-size:14px;color:red'>数据出错，未查到相应流程！</span>");
								}});
								
								 
								}
							
							
							openDialog(com);
							
							}else if($('.trSelected', grid).length > 1)
						{
							$('#mypost_List').dialog('close');
							showMsg("不能同时选择多行记录！");
						}else
						{
							$('#mypost_List').dialog('close');
							showMsg("未选中行！");
						}
							
						
			}
		};
	 $(function(){
		//初始化所有操作按钮
		 var  parm_button = [
 		 	{name: '查看', bclass: 'search', onpress : param_lczl_mypost.personDetails}
// 		 	{name: '查看表单', bclass: 'search', onpress : param_lczl_mypost.personDetails}
		 ];
		 //权限操作按钮
		 var colModel = [
		                {display: '序号', name : 'xh', width : 40, align: 'center'},
		                {display: 'ID', name : 'ID', align: 'center',hide : true,sortable: true},
		                {display: 'PID', name : 'PID',  align: 'center',hide : true,sortable: true},
						{display: '标题', name : 'TITLE', width : 350, align: 'center',sortable: true},
						{display: '创建人', name : 'CREATOR', width : 150, align: 'center',sortable: true},
						{display: '开始时间', name : 'CREATE_TIME', width : 150, align: 'center',sortable: true},
						{display: '结束时间', name : 'END_TIME', width : 150,  align: 'center',sortable : true,
						process:function(obj,pid)
							{	
								if ($.trim($(obj).html()) == "&nbsp;") 
								{
								   $(obj).html(" ----");
								   $(obj).css({color:"red"});
								}
							}},
						{display: '状态', name : 'ORDER_STATE', width : 150,  align: 'center',sortable: true,						
							process:function(obj,pid)
							{
								if ($(obj).html() == "0") 
								{
								   $(obj).html("已结束");
								   $(obj).css({color:"blue"});
								}
								else 
								{
									if ($(obj).html() == "0") 
									{
									   $(obj).html("已结束");
									   $(obj).css({color:"blue"});
									}
									else 
									{
										$(obj).html("进行中");
										$(obj).css({color:"red"});								
									}
							    }					
							}},
						{display: '下一步流程', name : 'NEXTDISPLAYNAME', width : 150,  align: 'center',sortable: true,
						process:function(obj,pid)
							{	
								if ($.trim($(obj).html()) == "&nbsp;") 
								{
								   $(obj).html(" ----");
								   $(obj).css({color:"blue"});
								}
							}}	 				
						];
		 	 			// 初始化数据
		 param_lczl_mypost.bindParamGrid(parm_button,colModel);
		/*参数管理-查询按钮功能*/
		$('#btn_mzpc_search').button({ icons: {primary:'ui-icon-search'} }).on("click",function(){
			param_lczl_mypost.loadData({page:1});
		});

		//清空筛选框
		$('#btn_mzpc_empty').on("click",function(){
			$("#title").val("");
			$("#startTime").val("");
			$("#endTime").val("");
			//param_lczl_mypost.loadData({page:1});
		});
		
	
								
	});	
	
	 var openDialog = function openDia(com){
		  var dlgParent = $("#mypost_List").parent();
	        var dlgOwn = $("#mypost_List").clone();
	        dlgOwn.hide();
			$("#mypost_List").dialog({
				position: ['center','center'],
				overflow:"auto",
				autoOpen: false, 
				height:$(window).height()*0.8,
				width:$(window).width()*0.5,
				show: "slide",
				modal:true,
				cache:false,
				/* hide: "explode", */
				title:com,
	            close : function() {
	            	dlgOwn.appendTo(dlgParent);
	            	$(this).remove();
	            }
			});
			$("#mypost_List .tab li").click(function()
			{
				$("#mypost_List .tab li").removeClass("current");				
				$(this).addClass("current");
				var n = $(this).index();				
				$("#mypost_List .content_y ul").hide();
				$("#mypost_List .content_y ul").eq(n).show();					
			});
			$("#mypost_List").dialog("open");
	 }
		</script>
	</body>
</html>
