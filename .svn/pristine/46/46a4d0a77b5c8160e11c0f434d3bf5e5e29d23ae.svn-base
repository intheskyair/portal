<%@ page contentType="text/html; charset=UTF-8"%>
<html>
    <head>
	<title>流程总览</title>
		   <script>x=-1</script>
	</head>
	<body  style="overflow:-Scroll;overflow-x:hidden" >	
	<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='../images/fold.png' onclick="showAndHide(this,'lczl_grid');"  class="imgShrotate">
				<font>流程总览</font>
			</h3>
			
		</div>
	<div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>
					<td class="wi-align-l" ><label for="input_1" class="lbl-1">流程标题：</label>
						<input type="text" id="title" name="title" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l" ><label for="input_1" class="lbl-1">开始时间：</label>
						<input type="text" id="startTime" name="startTime" readonly class="wi-ipt-i"
						onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
						<label for="input_1" class="lbl-1">&nbsp;至&nbsp;</label>
						<input type="text" id="endTime" name="endTime" readonly  class="wi-ipt-i"
						onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
					</td>
					<td ><label for="input_1" class="lbl-1">流程类型：</label>
						<select id="snakerName" class="lbl-1">
							<option value="">请选择</option>
						</select>
					</td>
					<td class="wi-align-l"><button id="btn_mzpc_search_lczl"  class="ui-state-default ui-state-qik">查询</button>
					<button id="btn_mzpc_empty_lczl" class="ui-state-default ui-state-qik">清空</button></td>
				</tr>
			</table>
		</div>
	</div>
	<div class="div div-2" style="">
		<table id="lczl_grid" style="display:none;padding:0 0 0 0;"></table>
	</div>
	
 			<div id="lczl_List" style="display:none;overflow: scroll;">
				<div class="outer">
					<ul class="tab">
						<li class="current">流程详情</li>
						<li >查看表单</li>
					    
					</ul>

					<div class="content_y">
					     		 <ul>
					     	<li class="current">
					     		<div id="lczl_List_table1"></div>
					     	</li>
					    </ul>
						<ul style="display:none">
							<li >
						    	<div id="lczl_List_table"></div>
						    </li>
						</ul>				   
					</div>			
					</div>
		     </div>
 			
		<script type="text/javascript">		
		 var data_all;
		 var paramGridHeight_lczl = flexHeight("lczl_grid");
		 var param_grid_mzpc;
		 var menuTree;
		 var treeNodes;
		
		 var param_lczl = {
			//绑定参数信息grid
			lczl_List:"",
			bindParamGrid:function(queryP,queryM){
				param_grid_user = $("#lczl_grid").flexigrid({
					url:"lczl!LczlList.action?operation=init",
					colModel: queryM,
					buttons : queryP,
					rowId:"id",
					showToggleBtn: true,
					usepager: true,
					title:"流程总览列表",
					//rp:pageSize,
					rp:page_num,
					showTableToggleBtn: false,
					resizable: false,
					height: paramGridHeight_lczl,
					singleSelect: true,
					onChangePage:param_lczl.loadData,
					checkbox:false,
					filterParams : [{key : "title", name : "#title"}]
				});
			},
			loadData:function(param){
				var queryP = {};
				queryP.title = $('#title').val()==null?'':$('#title').val();
				queryP.startTime = $('#startTime').val()==null?'':$('#startTime').val();
				queryP.endTime = $('#endTime').val()==null?'':$('#endTime').val();
				queryP.processId = $('#snakerName').val()==null?'':$('#snakerName').val();
				var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
				queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
				queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
				$.ajax({
					type: "POST",
					url: "lczl!LczlList.action?operation=init",
					cache: false,
					data: queryP,
					success: function(msg){
						var data=eval("("+msg+")");
						try{
							param_grid_user.flexAddData(data);
						}catch(e){
							showErrorMsg("查询结果信息出错！");
						}
					}
				}); 
			},	
			//个人流程查询
			personDetails : function(com,grid) {
				$("#lczl_List_table").html("");
				$("#lczl_List_table1").html("");
						if($('.trSelected', grid).length==1){
							var id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
							var pid = $('.trSelected', grid).eq(0).find("td:eq(2)").text();
							var path = $('.trSelected', grid).eq(0).find("td:eq(10)").text();
							var url= '';
							var data = {};
							for(var i = 0; i < 2 ; i++){
								if(i == 0){
									com = '查看表单';
								}else{
									com = '流程详情';								
								}
								
								if(com == '查看表单'){
									url = "borrow!view.action";
									data = {"orderId" : id,"processId":pid,"path":path};
								}else{
									url = "lczl!viewStep.action";
									data = {"orderId" : id};
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

                                            var result_str =  createHtml(result);
											$("#lczl_List_table1").append(result_str);
										}
										else
										{
											$("#lczl_List_table").append(result);
										}
									},
									error:function(result){
										$("#lczl_List_table").append("<span style='font-size:14px;color:red'>数据出错，未查到相应流程！</span>");
									}
								});
								
								
							}
							openDialog(com);
						}
						else if($('.trSelected', grid).length>1)
						{
							$('#lczl_List').dialog('close');
							showMsg("不能同时选择多行记录！");
						}else
						{
							$('#lczl_List').dialog('close');
							showMsg("未选中行！");
						}
			}
		};
 
	 $(function(){
		//初始化所有操作按钮
		 var  parm_button = [
		 	 {name: '查看', bclass: 'search', onpress : param_lczl.personDetails}
		 	 /* ,
		 	{name: '流程详情', bclass: 'search', onpress : param_lczl.personDetails} */
		 ];
		 //权限操作按钮
		 var colModel = [
		                {display: '序号', name : 'xh', width : 40, align: 'center'},
		                {display: 'ID', name : 'ID', width : 100, align: 'center',hide : true,sortable: true},
		                {display: 'PID', name : 'PID', width : 100, align: 'center',hide : true,sortable: true},
						{display: '流程名称', name : 'DISPLAY_NAME', width : 150, align: 'center',sortable: true},
						{display: '标题', name : 'VARIABLE', width : 350, align: 'center',sortable: true},
						{display: '创建人', name : 'CREATOR', width : 100, align: 'center',sortable: true},
						{display: '开始时间', name : 'CREATE_TIME', width : 150, align: 'center',sortable: true},
						{display: '结束时间', name : 'END_TIME', width : 150,  align: 'center',sortable : true,
						process:function(obj,pid)
							{	
								if ($.trim($(obj).html()) == "&nbsp;" || $.trim($(obj).html()) == "null") 
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
							}},
						{display: '表单查询', name : 'PATH', hide: true, width : 150,  align: 'center',sortable: true,
						}];
		 	 			// 初始化数据
		 param_lczl.bindParamGrid(parm_button,colModel);
		/*参数管理-查询按钮功能*/
		$('#btn_mzpc_search_lczl').button({ icons: {primary:'ui-icon-search'} }).on("click",function(){
			//时间判断
			var stime = $("#startTime").val();
			var etime = $("#endTime").val();
			var d1 = new Date(stime.replace(/\-/g, "\/")); 
			var d2 = new Date(etime.replace(/\-/g, "\/"));
			if(stime!=""&&etime!=""&&d1 >=d2){ 
				showMsg("开始时间不能大于结束时间！"); 
			  	return; 
			}
			param_lczl.loadData({page:1});
		});

		//清空筛选框
		$('#btn_mzpc_empty_lczl').on("click",function(){
			$("#title").val("");
			$("#startTime").val("");
			$("#endTime").val("");
			$("#snakerName").val("");
			//param_lczl.loadData({page:1});
		});
		
		$.ajax({
				type:"POST",
				cache: false,
				async:true,
				url: "lczl!viewProcess.action",
				success:function(result){
					var date = eval("("+result+")");
					for (var i in date) {
						$("#snakerName").append("<option value="+date[i].id+">"+date[i].displayName+"</option>");
					}
				}
		});
		
		});	
	 
	 
	 var openDialog = function openDia(com){
		  var dlgParent = $("#lczl_List").parent();
	        var dlgOwn = $("#lczl_List").clone();
	        dlgOwn.hide();
			$("#lczl_List").dialog({
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
			$("#lczl_List .tab li").click(function(){
				$("#lczl_List .tab li").removeClass("current");				
				$(this).addClass("current");
				var n = $(this).index();
				
				$("#lczl_List .content_y ul").hide();
				$("#lczl_List .content_y ul").eq(n).show();
					
			});
			$("#lczl_List").dialog("open");
	 }

		</script>
	</body>
</html>
