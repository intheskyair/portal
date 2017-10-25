<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/commonCSSJS.jsp"%>
<html>
    <head>
	<title>车辆申请总览</title>
	 <script>x=-39</script>
	</head>
	<body>		
	<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='images/fold.png' onclick="showAndHide(this,'carApply_grid');"  class="imgShrotate">
				<font>我的车辆申请查询</font>
			</h3>
		</div>
	<div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>
					<td><label for="input_1" class="lbl-1">流程标题：</label></td>
					<td class="wi-align-l">
						<input type="text" id="title" name="title" class="wi-ipt-1" />
					</td>			
<td class="wi-align-l">
					<label for="input_1" class="lbl-1">开始时间：</label>
					<input type="text" id="startTime" name="startTime" readonly="" class="wi-ipt-i" 
					onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})">
						<label for="input_1" style="float: left">&nbsp;至&nbsp;</label>
						<input type="text" id="endTime" name="endTime" readonly="" class="wi-ipt-i" 
						onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"></td>
<td class="ui-button-text">
					<button id="btn_carApply_search" class="ui-state-default ui-state-qik">查询</button>
					<button id="btn_carApply_empty" class="ui-state-default ui-state-qik">清空</button>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="div div-2">
		<table id="carApply_grid" style="display:none;padding:0 0 0 0;"></table>
	</div>
<div id="carApply_List" style="display:none;overflow: scroll;">
				<div class="outer">
					<ul class="tab">
						<li class="current">流程详情</li>
						<li >查看表单</li>					    
					</ul>

					<div class="content_y">
					 <ul>
					     	<li class="current">
					     		<div id="carApply_List_table1"></div>
					     		  	</li>
					    </ul>
						<ul style="display:none">
							<li >
						    	<div id="carApply_List_table"></div>
						    	</li>
						</ul>		
					</div>			
					</div>
		     </div>
<!-- 		<div><a id="msg_end" name="1" href="#1">&nbsp</a></div>	 -->
    <script type="text/javascript">		
		 var param_carApply = {
			//绑定参数信息grid
			page_num : 30,
			param_grid_user:"",
			dlg_dialog:"",
			GridHeight:flexHeight("carApply_grid"),
			bindParamGrid:function(queryP,queryM){
				param_carApply.param_grid_user = $("#carApply_grid").flexigrid({
					url:"carManager!carApplyList.action?operation=init&processId=${processId}",
					colModel: queryM,
					buttons : queryP,
					rowId:"id",
					showToggleBtn: true,
					usepager: true,
					title:"我的车辆申请列表",
					//rp:pageSize,
					rp:param_carApply.page_num,
					showTableToggleBtn: false,
					resizable: false,
					//height:240,
					height: flexHeight("carManager!carApplyList.action?processId=${processId}"),
					singleSelect: true,
					onChangePage:param_carApply.loadData,
					checkbox:false,
					filterParams : [{key : "title", name : "#title"}]
				});
			},
			loadData:function(param){
				var queryP = {};
				queryP.title = $('#title').val()==null?'':$('#title').val();
				queryP.startTime = $('#startTime').val()==null?'':$('#startTime').val();
				queryP.endTime = $('#endTime').val()==null?'':$('#endTime').val();				
				//var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
				var selectedGridDiv = $("div.flexigrid .pDiv .pDiv2 .pGroup");
				queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
				queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
				$.ajax({
					type: "POST",
					url: "carManager!carApplyList.action?processId=${processId}&operation=init",
					cache: false,
					async: false,
					data: queryP,
					success: function(msg){
						var data=eval("("+msg+")");
						try{
							param_carApply.param_grid_user.flexAddData(data);
						}catch(e){
							showErrorMsg("查询结果信息出错！");
						}
					}
				}); 
			},	
		returnCar:function(com,grid){
		          if($('.trSelected', grid).length==1)
						{
							
							 var data = {};
							data.orderId = $('.trSelected', grid).eq(0).find("td:eq(1)").text(); 
						     var order_status = $('.trSelected', grid).eq(0).find("td:eq(7)").text(); 
						     
						     if(order_status == "已结束"){
							$.ajax({
								type:"POST",
								data:data,
								cache: false,
								async:true,
								url: "carManager!returnCar.action",
								success:function(msg)
								{
									if(msg){
						var data=eval("("+msg+")");
						if(data.result == "00"){
							showMsg("车辆归还成功！");
							
						}else{
							showMsg("车辆归还失败！");
						}
					}else{
						showMsg("车辆归还失败！");
					}
								},
								
							});
							}else{
							    showMsg("该车辆申请流程尚未结束，请待流程结束后执行车辆归还操作！");
							}
					
						}else if($('.trSelected', grid).length > 1)
						{
							$('#carApply_List').dialog('close');
							showMsg("不能同时选择多行记录！");
						}else
						{
							$('#carApply_List').dialog('close');
							showMsg("未选中行！");
							}
		},
		showDialog : function(com) 
		{
			             var dlgParent = $("#carApply_List").parent();
					        var dlgOwn = $("#carApply_List").clone();
					        dlgOwn.hide();
							param_carApply.dlg_dialog = $("#carApply_List").dialog({
						        position: ['center','center'],
								autoOpen: false,
								height:$(window).height()*0.8,
						        width:$(window).width()*0.6,
								show: "slide",
								modal:true,
								cache:false,
								title:com,
					            close : function() {
					            	dlgOwn.appendTo(dlgParent);
					            	$(this).dialog("destroy").remove();
					            }
			                 });
			    $("#carApply_List .tab li").click(function()
			    {
							$("#carApply_List .tab li").removeClass("current");				
							$(this).addClass("current");
							var n = $(this).index();				
							$("#carApply_List .content_y ul").hide();
							$("#carApply_List .content_y ul").eq(n).show();
					
			    });
			   // document.getElementById("msg_end").click(); 
		        param_carApply.dlg_dialog.dialog("open");
	  },
	    //个人流程查询
		personDetails:function(com,grid)
		{
			//由于之前，编辑时，逐个对input进行赋值，添加和编辑用的是同一个页面，所以编辑时值还会存在，
			//以下对div内的input值全部清空
// 			$("#carApply_List :input").each(function () 
// 			{ 
// 				$(this).val(""); 
// 			});
			$("#carApply_List_table").html("");
			$("#carApply_List_table1").html("");
		   
		                 
						if($('.trSelected', grid).length==1)
						{
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
									 data = {"orderId" : orderId,"processId":pid,"path":"carView"};
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
								    if(com == '流程详情')
								    {
									    var result_str = createHtml(result);
									    $("#carApply_List_table1").append(result_str);
								    }
								    else
								    {
								       $("#carApply_List_table").append(result);
								    }
								},
								error:function(result)
								{
									$("#carApply_List_table").append("<span style='font-size:14px;color:red'>数据出错，未查到相应流程！</span>");
								}});
							}
							
						 param_carApply.showDialog(com);
						 
						}else if($('.trSelected', grid).length > 1)
						{
							$('#carApply_List').dialog('close');
							showMsg("不能同时选择多行记录！");
						}else
						{
							$('#carApply_List').dialog('close');
							showMsg("未选中行！");
						}
	        }		
			
		};
 
	 $(function(){
// 		 parent.crosswise_tabs.add({
//          	url: 'carManager!carApplyList.action?processId=a24f18f112a2414aae76accbe62a4cc4',
//          	label: '流程图2',
//          	locked: false
//          });
// 		 var sa  = $('#crosswise_tabs').cleverTabs();
// 		 var tab = sa.getTabByUrl('carManager!carApplyList.action?processId=a24f18f112a2414aae76accbe62a4cc4'); 
// 		 alert(tab);
// 		 tab.activate();
		 
		//初始化所有操作按钮
		
		 var  parm_button = [
		 	{name: '查看', bclass: 'search', onpress : param_carApply.personDetails},
		 	//{name: '查看表单', bclass: 'search', onpress : param_carApply.personDetails},
		 	{name: '归还车辆', bclass: 'search', onpress : param_carApply.returnCar}
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
		 param_carApply.bindParamGrid(parm_button,colModel);
		/*参数管理-查询按钮功能*/
		$('#btn_carApply_search').button().on("click",function(){
			param_carApply.loadData({page:1});
		});

		//清空筛选框
		$('#btn_carApply_empty').on("click",function(){
			$("#title").val("");
			$("#startTime").val("");
			$("#endTime").val("");
		});
		
	
								
	});	
	
		</script>
	</body>
</html>
