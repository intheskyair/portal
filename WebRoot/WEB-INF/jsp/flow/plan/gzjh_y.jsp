<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/commonCSSJS.jsp"%>
<html>
    <head>
		<title>工作计划查询_月度</title>	
		  <script>x=-33</script>
	</head>
	<body  style="" >	
	<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='images/fold.png' onclick="showAndHide(this,'gzjh_y');"  class="imgShrotate">
				<font>工作计划查询_月度</font>
			</h3>
			
		</div>
	<div class="div-main-1" >
				<input type="hidden" id="gid">
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>
					<td class="wi-align-l" ><label for="input_1" class="lbl-1">年度：</label><input type="text" id="year" name="year" class="wi-ipt-1" /></td>
					<td class="wi-align-l" >
					<label for="input_1" class="lbl-1">月份：</label>
						<select id="month" class="wi-ipt-1">
							<option value="">请选择</option>
							<option value="第一月份">第一月份</option>
							<option value="第二月份">第二月份</option>
							<option value="第三月份">第三月份</option>
							<option value="第四月份">第四月份</option>
							<option value="第五月份">第五月份</option>
							<option value="第六月份">第六月份</option>
							<option value="第七月份">第七月份</option>
							<option value="第八月份">第八月份</option>
							<option value="第九月份">第九月份</option>
							<option value="第十月份">第十月份</option>
							<option value="第十一月份">第十一月份</option>
							<option value="第十二月份">第十二月份</option>
							
						</select>
					</td>
					
					<td class="wi-align-l" ><label for="input_1" class="lbl-1">发起人：</label>
					<input type="text" id="creatorr" name="creatorr" class="wi-ipt-1" />
					</td>
					
					<td class="wi-align-l"><button id="btn_summary" class="ui-state-default ui-state-qik">查询</button><button id="btn_summary_empty" class="ui-state-default ui-state-qik">清空</button></td>
				</tr>
			</table>
		</div>
	</div>
	<div class="div div-2" style="overflow-x: hidden;">
		<table id="gzjh_y" style="display:none;padding:0 0 0 0;"></table>
	</div>
	
<div id="gzjh_y_table" style="display:none;    overflow: scroll;">
			<div class="outer">
					<ul class="tab">
						<li class="current">流程详情</li>
						<li >查看表单</li>
					    
					</ul>

					<div class="content_y">
					     		 <ul>
					     	<li class="current">
					     		<div id="gzjh_y_flow"></div>
					     	</li>
					    </ul>
						<ul style="display:none">
							<li >
						    	<div id="gzjh_y_form"></div>
						    </li>
						</ul>				   
					</div>			
					</div>
 			
		</div> 
		
	
		<script type="text/javascript">		
		 var data_all;
		 var paramGridHeight_gwgd = flexHeight("gzjh_y");
		 var arr = new Array(); 
		 var show;
		 var param_gzjh_y = {
		 param_grid_user:"",
		 page_num : 30,
		 gwgd_update:"",
		 gwgd_List:"",
			//绑定参数信息grid
			bindParamGrid:function(queryP,queryM){
				param_gzjh_y.param_grid_user = $("#gzjh_y").flexigrid({
					url:"plan!planList.action?operation=init&processId=${processId}&type=${type}",
					colModel: queryM,
					buttons : queryP,
					rowId:"id",
					showToggleBtn: true,
					usepager: true,
					title:"工作计划月度列表",
					//rp:pageSize,
					rp:param_gzjh_y.page_num,
					showTableToggleBtn: false,
					resizable: false,
					height: paramGridHeight_gwgd,
					singleSelect: true,
					onChangePage:param_gzjh_y.loadData,
					filterParams : [{key : "title", name : "#title"}]
				});
			},
			loadData:function(param){
				var queryP = {};
				queryP.yd = $('#year').val()==null?'':$('#year').val();
				queryP.jd = $('#month').val()==null?'':$('#month').val();
				queryP.creator = $('#creatorr').val()==null?'':$('#creatorr').val();
				//var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
				var selectedGridDiv = $("div.flexigrid .pDiv .pDiv2 .pGroup");
				queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
				queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
				$.ajax({
					type: "POST",
					url: "plan!planList.action?operation=init&processId=${processId}&type=${type}",
					cache: false,
					data: queryP,
					success: function(msg){
						var data=eval("("+msg+")");
						try{
							param_gzjh_y.param_grid_user.flexAddData(data);
						}catch(e){
							showErrorMsg("查询结果信息出错！");
						}
					}
				}); 
			},
			
		//流程查询
			personDetails : function(com,grid) {
				$("#gzjh_y_flow").html("");
				$("#gzjh_y_form").html("");       
						if($('.trSelected', grid).length==1){
						
							var id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
								for(var i = 0; i < 2 ; i++){
								if(i == 0){
									com = '查看表单';
								}else{
									com = '流程详情';								
								}
								
								if(com == '查看表单'){
									url = "borrow!view.action";
									data = {"orderId" : id,"path":"planView"};
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
											 $("#gzjh_y_flow").append(result_str);
											}
											else
											{
												$("#gzjh_y_form").append(result);
											}
									},								
									error:function(result){
										$("#gzjh_y_form").append("<span style='font-size:14px;color:red'>数据出错，未查到相应流程！</span>");
									}
								});
								
							}	
						

							openDialog(com);
						}
						else if($('.trSelected', grid).length>1)
						{
							$('#gzjh_y_table').dialog('close');
							showMsg("不能同时选择多行记录！");
						}else
						{
							$('#gzjh_y_table').dialog('close');
							showMsg("未选中行！");
						}
			}
		};
 
	 $(function(){
		//初始化所有操作按钮
		 var  parm_button = [
		 	 {name: '查看详情', bclass: 'search', onpress :param_gzjh_y.personDetails}
		 	
		 ];
		 //权限操作按钮
		 var colModel = [
		                {display: '序号', name : 'xh', width : 40, align: 'center'},
		                {display: 'ID', name : 'ID', align: 'center',hide : true,sortable: true},
		                {display: '年度', name : 'YD', width : 60, align: 'center',sortable: true},
		                {display: '月度', name : 'JD', width : 60, align: 'center',sortable: true},
		                {display: '发起人', name : 'TRUENAME', width : 100, align: 'center',sortable: true},
		                {display: '工作计划处室领导意见', name : 'CSLDYJ', width : 150, align: 'center',sortable: true},
		                {display: '工作总结处室领导意见', name : 'CSLDPJ', width : 200, align: 'center',sortable: true},
						{display: '分管领导意见', name : 'FGLDPJ', width : 180, align: 'center',sortable: true},
						{display: '状态', name : 'ORDER_STATE', width : 50, align: 'center',sortable: true,
							process:function(obj)
							{	
								if ($.trim($(obj).html()) == '1') 
								{
								   $(obj).html("未结束");
								   $(obj).css({color:"red"});
								}else{
									$(obj).html("已结束");
								}
							}
						}];
		 	 			// 初始化数据
		 param_gzjh_y.bindParamGrid(parm_button,colModel);
		/*参数管理-查询按钮功能*/
		$('#btn_summary').button({ icons: {primary:'ui-icon-search'} }).on("click",function(){
			param_gzjh_y.loadData({page:1});
		});

		//清空筛选框
		$('#btn_summary_empty').on("click",function(){
			$("#year").val("");
			$("#month").val("");
			$("#creatorr").val("");
		});
	
	});	
	
	var openDialog = function openDia(){
		  var dlgParent = $("#gzjh_y_table").parent();
	        var dlgOwn = $("#gzjh_y_table").clone();
	        dlgOwn.hide();
			$("#gzjh_y_table").dialog({
				position: ['center','center'],
				overflow:"auto",
				autoOpen: false, 
				height:$(window).height()*0.8,
				width:$(window).width()*0.6,
				show: "slide",
				modal:true,
				cache:false,
				/* hide: "explode", */
				title:"流程详情",
	            close : function() {
	            	dlgOwn.appendTo(dlgParent);
	            	$(this).remove();
	            }
			});
		    $("#gzjh_y_table .tab li").click(function(){
				$("#gzjh_y_table .tab li").removeClass("current");				
				$(this).addClass("current");
				var n = $(this).index();
				
				$("#gzjh_y_table .content_y ul").hide();
				$("#gzjh_y_table .content_y ul").eq(n).show();
					
			});
			$("#gzjh_y_table").dialog("open");
	 }
		</script>
	</body>
</html>
