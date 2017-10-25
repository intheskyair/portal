<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/commonCSSJS.jsp"%>
<html>
    <head>
	<title>督察督办查询</title>
	 <script>x=-32</script>
	</head>
	<body style="">	
	<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='images/fold.png' onclick="showAndHide(this,'dcdb_grid');"  class="imgShrotate">
				<font>督察督办查询</font>
			</h3>
		</div>
	<div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>
					<td class="wi-align-l" ><label for="input_1" class="lbl-1">流程标题：</label>
						<input type="text" id="dtitle" name="dtitle" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l" ><label for="input_1" class="lbl-1">开始时间：</label>
						<input type="text" id="dstartTime" name="dstartTime" readonly class="wi-ipt-i"
						onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
						<label for="input_1" class="lbl-1">&nbsp;至&nbsp;</label>
						<input type="text" id="dendTime" name="dendTime" readonly  class="wi-ipt-i"
						onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
					</td>
					<td class="ui-button-text"><a href="#" id="btn_mzpc_search_dc">查询</a>
<button id="btn_mzpc_empty_dc" class="ui-state-default ui-state-qik">清空</button></td>

					
				</tr>
			</table>
		</div>
	</div>
	<div class="div div-2">
		<table id="dcdb_grid" style="display:none;padding:0 0 0 0;"></table>
	</div>
	

	<div id="dcdb_List" style="display:none;overflow: scroll;">
				<div class="outer">
					<ul class="tab">
						<li class="current">流程详情</li>
						<li >查看表单</li>					    
					</ul>
					<div class="content_y">
					     		<ul>
					     	<li class="current">
					     		<div id="dcdb_List_table1"></div>
					     	</li>
					    </ul>
						<ul style="display:none">
							<li >
						    	<div id="dcdb_List_table"></div>
						    </li>
						</ul>				   		   
					</div>			
					</div>
		     </div>
	
		<script type="text/javascript">		
		 var data_all;
		 var paramGridHeight_lczl = flexHeight("dcdb_grid");
		 var param_grid_mzpc;
		 var menuTree;
		 var treeNodes;
		
		 var param_lczl = {
			//绑定参数信息grid
			page_num : 30,
			//param_grid_user:"",
			dlg_dialog:"",
			bindParamGrid:function(queryP,queryM){
				param_grid_user = $("#dcdb_grid").flexigrid({
					url:"lczl!LczlList.action?operation=init&processId=${processId}",
					colModel: queryM,
					buttons : queryP,
					rowId:"id",
					showToggleBtn: true,
					usepager: true,
					title:"督察督办查询列表",
					//rp:pageSize,
					rp:param_lczl.page_num,
					showTableToggleBtn: false,
					resizable: false,
					//height: 240,
					height: paramGridHeight_lczl,
					singleSelect: true,
					onChangePage:param_lczl.loadData,
					checkbox:false,
					filterParams : [{key : "title", name : "#dtitle"}]
				});
			},
			loadData:function(param){
				var queryP = {};
				queryP.title = $('#dtitle').val()==null?'':$('#dtitle').val();
				queryP.startTime = $('#dstartTime').val()==null?'':$('#dstartTime').val();
				queryP.endTime = $('#dendTime').val()==null?'':$('#dendTime').val();
				var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
				queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
				queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
				$.ajax({
					type: "POST",
					url: "lczl!LczlList.action?operation=init&processId=${processId}",
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
			
			showDialog : function(com) 
		       {
			             var dlgParent = $("#dcdb_List").parent();
		        var dlgOwn = $("#dcdb_List").clone();
				param_lczl.dlg_dialog = $("#dcdb_List").dialog({
						        position: ['center','center'],
								autoOpen: false,
								height:$(window).height()*0.8,
								width:$(window).width()*0.8,
								show: "slide",
								modal:true,
								cache:false,
								title:com,
					            close : function() {
					            	dlgOwn.appendTo(dlgParent);
					            	$(this).dialog("destroy").remove();
					            }
			                 });
			    $("#dcdb_List .tab li").click(function()
			    {
					$("#dcdb_List .tab li").removeClass("current");					
					$(this).addClass("current");
					var n = $(this).index();					
					$("#dcdb_List .content_y ul").hide();
					$("#dcdb_List .content_y ul").eq(n).show();
					
			    });
		        param_lczl.dlg_dialog.dialog("option", "title", com).dialog("open");
	          },	
			//个人流程查询
			personDetails : function(com, grid) {
			$("#dcdb_List_table").html("");
			$("#dcdb_List_table1").html("");
			if($('.trSelected', grid).length==1)
						{
						    var url= '';
							var data = {};
							
							var orderId = $('.trSelected', grid).eq(0).find("td:eq(1)").text(); 
							var pid = $('.trSelected', grid).eq(0).find("td:eq(2)").text();
							for(var i = 0; i < 2 ; i++){
								if(i == 0){
									com = '考核表单';
								}else{
									com = '考核详情';								
								}
								
								if(com == '考核表单'){
									url = "borrow!view.action";
									 data = {"orderId" : orderId,"processId":pid,"path":"dcdbView"};
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
								    if(com == '考核详情')
								    {
								    var result_str = createHtml(result);
								    $("#dcdb_List_table1").append(result_str);
								    }else{
								    $("#dcdb_List_table").append(result);
								    }
								},
								error:function(result)
								{
									$("#dcdb_List_table").append("<span style='font-size:14px;color:red'>数据出错，未查到相应流程！</span>");
								}});
							}
							param_lczl.showDialog(com);
							}
						else if($('.trSelected', grid).length > 1)
						{
							$('#dcdb_List').dialog('close');
							showMsg("不能同时选择多行记录！");
						}else
						{
							$('#dcdb_List').dialog('close');
							showMsg("未选中行！");
						}
	        }		
			
		};
 
	 $(function(){
		//初始化所有操作按钮
		 var  parm_button = [
	 	{name: '查看', bclass: 'search', onpress : param_lczl.personDetails}
// 		 	{name: '考核表单', bclass: 'search', onpress : param_lczl.personDetails}
		 ];
		 //权限操作按钮
		 var colModel = [
		                {display: '序号', name : 'xh', width : 40, align: 'center'},
		                {display: 'ID', name : 'ID', align: 'center',hide : true,sortable: true},
		                {display: 'PID', name : 'PID',  align: 'center',hide : true,sortable: true},
						{display: '标题', name : 'VARIABLE', width : 350, align: 'center',sortable: true},
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
		 param_lczl.bindParamGrid(parm_button,colModel);
		/*参数管理-查询按钮功能*/
		$('#btn_mzpc_search_dc').button({ icons: {primary:'ui-icon-search'} }).on("click",function(){
			//时间判断
			var stime = $("#dstartTime").val();
			var etime = $("#dendTime").val();
			var d1 = new Date(stime.replace(/\-/g, "\/")); 
			var d2 = new Date(etime.replace(/\-/g, "\/"));
			if(stime!=""&&etime!=""&&d1 >=d2){ 
				showMsg("开始时间不能大于结束时间！"); 
			  	return; 
			}
			param_lczl.loadData({page:1});
		});

		//清空筛选框
		$('#btn_mzpc_empty_dc').on("click",function(){
			$("#dtitle").val("");
			$("#dstartTime").val("");
			$("#dendTime").val("");
		});
		
	
								
	});	
		</script>
	</body>
</html>
