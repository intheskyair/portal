<%@ page contentType="text/html; charset=UTF-8"%>
			<script>x=-8;xx=0;</script>	
<body style="position:relative;overflow:scroll">
<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='../images/fold.png' onclick="showAndHide(this,'boardroom_grid');"  class="imgShrotate">
				<font>会议室查询</font>
			</h3>
		</div>
	<div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>

					<td >
					<label for="input_1" class="lbl-1">
							会议室名称:
					</label>
					<input type="text" id="serch_Boardroom_name" name="serch_Boardroom_name" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l" >
						<label for="input_1" class="lbl-1">
							会议室地点:
						</label>
						<input type="text" id="serch_boardroom_location" name="serch_boardroom_location" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l" >
						<label for="input_1" class="lbl-1">
							会议室状态：
						</label>
						<select name="status" id="status" class="wi-ipt-1">
							<option value="-1">全部</option>
							<option value="1">可用</option>
							<option value="2">专用</option>
							<option value="3">装修</option>
						</select>
					</td>
					
					<td class="wi-align-l">
						<button id="btn_boardroom_search" class="ui-state-default ui-state-qik">查询
						</button>
						<button id="btn_boardroom_empty" class="ui-state-default ui-state-qik">清空
						</button>
					</td>
				</tr>
			</table>
		</div>
</div>
<div class="div div-2" style="">
	<table id="boardroom_grid" style="display:none;padding:0 0 0 0;"></table>
</div>

<script type="text/javascript">
   
    
</script>


				
<div id="dlg_boardroom" style="display:none;overflow-y: hidden;">
	<input type="hidden" id="action" />
	<input type="hidden" id="dlg_id" /> 
	<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
		<tr>
			<td style="width: 126px; height: 33px">
				<label style="color:red;">*</label>
				<label for="input_1">会议室名称：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" name="dlg_boardroom_name" id="dlg_boardroom_name" class="wi-sel-1" style="width:200px">
				            
			</td>
		</tr>
		<tr>
			<td style="width: 126px; height: 33px">
				<label style="color:red;">*</label>
				<label for="input_1">会议室地点：</label>
			</td>
			<td class="wi-align-l" style="width:200px;">
				<input type="text" id="dlg_boardroom_location" value="" class="wi-ipt-1" style="width: 200px; "/>
			</td>
		</tr>
		<tr>
			<td style="width: 126px; height: 33px">
				<label style="color:red;">*</label>
			
						<label for="input_1">
							会议室状态：
						</label>
						
						
						</td>
						<td class="wi-align-l" style="width:200px;">
						<select name="dlg_boardroom_status" id="dlg_boardroom_status" class="wi-ipt-1" style="width: 200px; ">
							<option value="--请选择--">--请选择--</option>
							<option value="1">可用</option>
							<option value="2">专用</option>
							<option value="3">装修</option>
						</select>
					
					</td>
		</tr>
	</table>
</div>	

<script type="text/javascript">
		
var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
var page_num = $(window.self.document).find("#page_num").val();
var page_num =30;
var boardroom_grid = {
	//绑定参数信息grid
	boardroom_grid_result:"",
	dlg_boardroomm:"",
	GridHeight:flexHeight("boardroom_grid"),
	bindParamGrid:function(queryP,queryM){
		boardroom_grid.boardroom_grid_result = $("#boardroom_grid").flexigrid({
			url:"boardroom!boardroom_infor.action?operation=init",
			colModel: queryM,
			buttons : queryP,
			rowId:"id",
			showToggleBtn: true,
			usepager: true,
			title:"会议室查询",
			//rp:pageSize,
			rp:page_num,
			showTableToggleBtn: false,
			resizable: false,
			height: boardroom_grid.GridHeight,
			singleSelect: true,
			onChangePage:boardroom_grid.loadData,
			checkbox:false,
			filterParams : [{key : "boardroom_name", name : "#serch_Boardroom_name"},
			                {key : "boardroom_location", name : "#serch_boardroom_location"},
			                {key : "status", name : "#status"}]
			                
		});
	},			
	// 查询子任务并且显示
	showDialog : function(com,operation,divobj,h,w) 
		{
		var dlgParent = divobj.parent();
		var dlgOwn = divobj.clone();
		boardroom_grid.dlg_boardroomm = divobj.dialog({
		        position: ['center','center'],
				autoOpen: false,
				height:"auto",
				width:"auto",
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
						boardroom_grid.saveData(operation);
						
		              	//$(this).dialog("destroy").remove();
					}
				},
			    close : function() {
		              	dlgOwn.appendTo(dlgParent);
		              	$(this).dialog("destroy").remove();
		         }
			});
			
		boardroom_grid.dlg_boardroomm.dialog("option", "title", com).dialog("open");
	},
	add:function(com,grid)
	{
		
		if (com == "添加") 
				{
				    boardroom_grid.showDialog("新增会议室","add", $("#dlg_boardroom"));
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
					       var boardroom_name = $('.trSelected', grid).eq(0).find("td:eq(2)").text();
					        var indicate = "确认需要删除" + boardroom_name+ ",这个会议室吗?";	
					        var params=new Object();
				                params.id = id;					
							if(confirm(indicate)) 
							{
								$.ajax({
									type: "POST",
									url: "boardroom!deleteBoardroom.action",
									cache: false,
									data: params,
									success: function(msg)
									{
										var result=eval("("+msg+")");
										if (result.msg=="ok") 
										{
											showMsg("删除成功！");
											$('#dlg_boardroom').dialog('close');
											boardroom_grid.loadData({page:1});
										} else 
										{
											showMsg(result.msg);
											$('#dlg_boardroom').dialog('close');
										}
									}
								}); 
							}else
							{
								$('#dlg_boardroom').dialog('close');
							}
						
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_boardroom').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_boardroom').dialog('close');
						showMsg("未选中行！");
					}
				}			
	},
	edit:function(com,grid)
	{
		
                if (com == "编辑") 
				{

					if($('.trSelected', grid).length==1)
					{
					   boardroom_grid.showDialog("编辑会议室信息","edit", $("#dlg_boardroom"));
				       $("#action").val("edit");
						var id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
						var boardroom_name = $('.trSelected', grid).eq(0).find("td:eq(2)").text();
						var boardroom_location = $('.trSelected', grid).eq(0).find("td:eq(3)").text();
						var boardroom_status = $('.trSelected', grid).eq(0).find("td:eq(4)").text();
						var promotionShow = $('.trSelected', grid).eq(0).find("td:eq(7)").text();
						$("#dlg_id").val(id);	
						$("#dlg_boardroom_name").val(boardroom_name);
						$("#dlg_boardroom_location").val(boardroom_location);
						if(boardroom_status == "可用"){
						$("#dlg_boardroom_status option[value='1']").attr("selected", true);
						}else if(boardroom_status == "专用"){
						$("#dlg_boardroom_status option[value='2']").attr("selected", true);
						}else{
						$("#dlg_boardroom_status option[value='3']").attr("selected", true);
						}
						$("#promotionShow").html(promotionShow);					
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
	{           // 提交方式
				   var action = $("#action").val();	
				   var params=new Object();
	            if(operation == "add" || operation == "edit"){
	          		
			    
				params.boardroom_name = $.trim($("#dlg_boardroom_name").val());
				params.boardroom_location = $.trim($("#dlg_boardroom_location").val());
				params.boardroom_status  = $("#dlg_boardroom_status").val();
				
			   
				//新增的时候才判断密码，修改的时候不需要
					if(params.boardroom_name==null || params.boardroom_name=='')
					{
						showMsg("会议室名称不能为空！");
						$("#dlg_boardroom_name").focus();
						return;
					}
					if(params.boardroom_location==null || params.boardroom_location=='')
					{
						showMsg("会议室地点不能为空！");
						$("#dlg_boardroom_location").focus();
						return;
					}
					if(params.boardroom_status =='--请选择--')
					{
						showMsg("请选择会议室状态！");
						return;
					}	
					}			
				// 新增和编辑通用字符串
				var url ='';
				var indicate='';
				if ("add" == action) 
				{
				
				   
					indicate = "确认新增" + params.boardroom_name + ",这个会议室?";
					url = "boardroom!addBoardroom.action";
				} 
				else if ("edit" == action) 
				{   
					params.id = $("#dlg_id").val();
					indicate = "确认更新" + params.boardroom_name + ",这个会议室信息?";
					url = "boardroom!editBoardroom.action";
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
								
								$('#dlg_boardroom').dialog('close');
								boardroom_grid.loadData({page:1});
								$('#dlg_bespeak').dialog('close');
								boardroom_grid.loadData({page:1});
							} else 
							{
								showMsg(result.msg);
							}
						}
					}); 
				}
				else
				{
				    $('#dlg_boardroom').dialog('close');
				    $('#dlg_bespeak').dialog('close');
				}
		
	},
  
   	loadData:function(param){
		var queryP = {};
		
		queryP.boardroom_name = $('#serch_Boardroom_name').val()==null?'':$('#serch_Boardroom_name').val();
		queryP.boardroom_location = $('#serch_boardroom_location').val()==null?'':$('#serch_boardroom_location').val();
		queryP.status = $('#status').val()==null?'':$('#status').val();
		
		var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
		queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
		queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
		queryP.sortname = param.sortname? param.sortname : "";
		queryP.sortorder = param.sortorder? param.sortorder : "";
		$.ajax({
			type: "POST",
			url: "boardroom!boardroom_infor.action?operation=init",
			cache: false,
			data: queryP,
			success: function(msg){
				var data=eval("("+msg+")");
				try{
					boardroom_grid.boardroom_grid_result.flexAddData(data);
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
			  {name: '添加', bclass: 'add', onpress : boardroom_grid.add},
			  {name: '编辑', bclass: 'edit', onpress : boardroom_grid.edit},
			  {name: '删除', bclass: 'delete', onpress : boardroom_grid.del}
			 
		 ];
		 //权限操作按钮
		 var colModel = [						
	            {display: '序号', name : 'xh', width : 40, align: 'center'},
				{display: 'id', name : 'ID', width : 40, align: 'center',hide : true},
				{display: '会议室名称', name : 'BOARDROOM_NAME', width : 150, align: 'left',sortable: true},
				{display: '会议室地点', name : 'BOARDROOM_LOCATION', width : 150, align: 'left',sortable: true},
				{display: '会议室状态', name : 'BOARDROOM_STATUS', width : 120,  align: 'left',sortable : true,process:function(value,pid){
					var status = parseInt($.trim($(value).html()));
					switch(status){
						case 1 : 
							$(value).html("可用");
							break;
						case 2 : 
							$(value).html("专用");
							break;
						case 3 : 
							$(value).html("装修");
							break;
						default : 
							$(value).html("未知");
							break;
					}
				}}	
		 ];
		 	 			// 初始化数据
		 boardroom_grid.bindParamGrid(parm_button,colModel); 
		 
		 
		 		/*参数管理-查询按钮功能*/
		$('#btn_boardroom_search').button().on("click",function(){
			boardroom_grid.loadData({page:1});
		});
				//清空筛选框
		$('#btn_boardroom_empty').on("click",function(){
			$("#serch_Boardroom_name").val("");
			$("#serch_boardroom_location").val("");
			$("#status").val(-1);
		}); 
		 		 
	});
	
	
	
</script>
</body>
