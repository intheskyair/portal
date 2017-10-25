<%-- <%@ page contentType="text/html; charset=UTF-8"%>
<html>
    <head>
	<title>民主评测表打分</title>
	</head>
	<body>	
	<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='../images/fold.png' onclick="showAndHide(this,'canpc_grid');"  class="imgShrotate">
				<font>民主评测表打分</font>
			</h3>
		</div>
	<div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>
					<td ><label for="input_1" class="lbl-1">评测标题：</label></td>
					<td class="wi-align-l" >
						<input type="text" id="title_can" name="title_can" class="wi-ipt-1" />
					</td>
							<td class="wi-align-l">
						<button id="btn_canpc_search" style="width:74px; 
							height:29px;font-size:14px;background-color:#3ea4e8; margin-left: 5px;
    margin-right: 5px;">查询
						</button>
					</td>
					<td class="wi-align-l" style="width: 100px;">
						<button id="btn_szxc_empty" class="ui-state-default ui-state-qik">清空
						</button>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="div div-2" style="overflow:auto;">
		<table id="canpc_grid" style="display:none;padding:0 0 0 0;"></table>
	</div>
	
		<script>		
		 var data_all;
		 var paramGridHeight_canpc = flexHeight("canpc_grid");
		 var param_grid_canpc;
		 var menuTree;
		 var treeNodes;
		 var param_canpc = {
			//绑定参数信息grid
			bindParamGrid:function(queryP,queryM){
				param_grid_canpc = $("#canpc_grid").flexigrid({
					url:"mzpc!canDfList.action?operation=init",
					colModel: queryM,
					buttons : queryP,
					rowId:"id",
					showToggleBtn: true,
					usepager: true,
					title:"评测表可打分列表",
					//rp:pageSize,
					rp:page_num,
					showTableToggleBtn: false,
					resizable: false,
					height: paramGridHeight_canpc,
					singleSelect: false,
					onChangePage:param_canpc.loadData,
					checkbox:true,
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
					url: "mzpc!canDfList.action?operation=init",
					cache: false,
					data: queryP,
					success: function(msg){
						var data=eval("("+msg+")");
						try{
							param_grid_canpc.flexAddData(data);
						}catch(e){
							showErrorMsg("查询结果信息出错！");
						}
					}
				}); 
			}
		};
 
	 $(function(){
		//初始化所有操作按钮
		 var  parm_button = [];
		 //权限操作按钮
		 var colModel = [						
		                {display: '序号', name : 'xh', width : 40, align: 'center'},
						{display: 'id', name : 'ID', width : 40, align: 'center',hide : true,sortable: true},
						{display: 'dfbz', name : 'DF_BZ', width : 40, align: 'center',hide : true,sortable: true},
						{display: '评测标题', name : 'TITLE', width : 350, align: 'left',sortable: true},
						{display: '评测类型', name : 'DIC_NAME', width : 150, align: 'left',sortable: true},
						{display: '开始时间', name : 'BEGTIME_FORMAT_DATE', width : 120, align: 'left',sortable: false},
						{display: '结束时间', name : 'ENDTIME_FORMAT_DATE', width : 120,  align: 'left',sortable : false},
						{display:'操作',width:150,align:'center',
							process:function(obj,pid,$td){
								var tr = $td.closest("tr");
								var id = $('div',tr).eq(1).text(); 
								var dfbz = $('div',tr).eq(2).text(); 
								if(dfbz>0)
								{
								  $(obj).html('<span><a href="javascript:void(0);" onclick="showpc(' + id +');" style="color:red;">已打分预览</a></span>');
								}
								else
								{
								  $(obj).html('<span><a href="javascript:void(0);" onclick="mzMark(' + id +');" style="color:blue;">民主打分</a></span>');
								}
								
							}
					    }					
						];
		 	 			// 初始化数据
		 param_canpc.bindParamGrid(parm_button,colModel);
		 //获得当前菜单的id
		 /*
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
			}
		});
	    */
		/*参数管理-查询按钮功能*/
		$('#btn_canpc_search').button({ icons: {primary:'ui-icon-search'} }).on("click",function(){
			param_canpc.loadData({page:1});
		});
		

		//清空筛选框
		$('#btn_canpc_empty').on("click",function(){
			$("#title_can").val("");
			//param_mzpc.loadData({page:1});
		});
		
		
	});
	function mzMark(id){
		$.ajax({
			type: "get",
			url: "mzpc!jumpHtml.action",
			cache: false,
			data: {mainid:id,type:"edit"},
			success: function(data){
				var res = (new Function("","return " + data))();
				if(res.error)
				{
				   showErrorMsg(res.error);
				   param_canpc.loadData({page:1});
				}
				else
				{
					var option = {};
					option.key = res.mainid;
					option.title = "民主打分";
					option.url = "../" + res.url + "?mainid=" + res.mainid;
					option.isIframe = true;
					console.log("../" + res.url + "?mainid=" + res.mainid);
					addTab(option);
				}
			}		
		});
	}	
	
		function showpc(id){
		 	var mainId = parseInt(id);
		 	$.ajax({
		 		type: "get",
				url: "mzpc!jumpHtml.action",
				cache: false,
				data: {mainid:mainId,type:"viewdf"},
				success: function(data){
					var res = (new Function("","return " + data))();
					var option = {};
					option.key = res.mainid;
					option.title = "打分结果预览";
					option.url = "../" + res.url + "?mainid=" + res.mainid+"&view_type=1";
					option.isIframe = true;
					addTab(option);
				}
		 	});
		 }
	
		</script>
	</body>
</html>
 --%>