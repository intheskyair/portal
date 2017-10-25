<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
    <%@ include file="/common/taglibs.jsp"%>
    <%@ include file="/common/commonCSSJS.jsp"%>
	<title>民主评测表结果统计列表</title>
		<script>x=-27;xx=30;</script>	
	</head>
	<body>	
	<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='images/fold.png' onclick="showAndHide(this,'result_grid');"  class="imgShrotate">
				<font>民主评测表结果统计列表</font>
			</h3>
		</div>
	<div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>
					<td class="wi-align-l" ><label for="input_1" class="lbl-1">评测标题：</label>
						<input type="text" id="title_can" name="title_can" class="wi-ipt-1" />
					</td>
							<td class="wi-align-l">
						<button id="btn_result_search" class="ui-state-default ui-state-qik">查询
						</button>
					
						<button id="btn_result_empty_de" class="ui-state-default ui-state-qik">清空
						</button>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="div div-2" style="">
		<table id="result_grid" style="display:none;padding:0 0 0 0;"></table>
		<input type="hidden" id="line_main_id" />
	</div>
<div id="dlg_line_resultdf" style="display:none;overflow-y: hidden;">
	<div class="div div-2" style="overflow:auto;">
		<table id="sub_resultdf_grid" style="display:none;padding:0 0 0 0; padding-bottom: 50px;"></table>
	</div>
</div>	
		<script>		
		//var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
		var selectedGridDiv = $("div.flexigrid .pDiv .pDiv2 .pGroup");
		//var page_num = $(window.self.document).find("#page_num").val();
		var page_num =30;
		var paramGridHeight_result = flexHeight("result_grid");
		 var param_grid_result;
		 var menuTree;
		 var treeNodes;
		 var sub_resultdf_grid;
		 var  dlg_line_resultdf;
		 var create_result = function(){
			 dlg_line_resultdf = $("#dlg_line_resultdf").dialog({
					autoOpen: false,
					height:$(window).height()*0.8,
					width:$(window).width()*0.5,
					modal:true,
					cache:false,
					title:'民主评分结果',
					buttons:{
						"关闭": function() {
	     					clean_input_for_dailog($("#showRadom"));
	     					//console.log(dlgParent.attr("id"));
	     					//$(this).dialog("destroy").remove();
	     					//dlgOwn.appendTo(dlgParent);
	          				$(this).dialog("close");
	          			}
					}
				   
				});
		 }
		 
         var has_dlg_result = function(){
        	 if("undefined" === typeof(dlg_line_resultdf)){
        		 create_result();
        		}
        	 dlg_line_resultdf.dialog("option", "title", "民主评分结果").dialog("open");
         }
         
         
         var sub_resultdf_grid;
         var create_grid = function(id){
            var subHi_result = flexHeight("sub_resultdf_grid");
        	 sub_resultdf_grid = $("#sub_resultdf_grid").flexigrid({
 				url:"mzpc!totalkh_pf.action?mainid="+$("#line_main_id").val(),
 				colModel : [
 					{display: '序号', name : 'xh', width : 40, align: 'center'},
 					{display: '用户或者单位', name : 'NAME', width : 140,  align: 'center'},
 					{display: '统计结果', name : 'DETAIL', width : 500, align: 'center'}					
 				],
 				rowId:"xh",
 				showToggleBtn: false,
 				title:"活动统计列表",
 				page:1,
 				showTableToggleBtn: false,
 				resizable: false,
 				height:subHi_result,//*32/50
 				singleSelect: true,
 				onChangePage:param_result.loadSubData,
 				checkbox:false,
 				filterParams : []
 			});
         }
         var has_dlg_grid = function(){
        	 if("undefined" === typeof(sub_resultdf_grid)){
        		 create_grid();
        		}else{
        			param_result.loadSubData({page : 1});
        		}
        	 
         }
		 
		 
		 var param_result = {
			//绑定参数信息grid
			bindParamGrid:function(queryP,queryM){
				param_grid_result = $("#result_grid").flexigrid({
					url:"mzpc!resultDfList.action?operation=init",
					colModel: queryM,
					buttons : queryP,
					rowId:"id",
					showToggleBtn: true,
					usepager: true,
					title:"评测表可打分列表结果统计列表",
					//rp:pageSize,
					rp:page_num,
					showTableToggleBtn: false,
					resizable: false,
					height: paramGridHeight_result,
					singleSelect: true,
					onChangePage:param_result.loadData,
					checkbox:false,
					filterParams : [{key : "title", name : "#title_can"}]
				});
			},			
			
          // 子任务grid
		bindLineSubMissionGrid : function() {
			has_dlg_grid();
		},
		// 导出数据
		commitExport : function(id) {
			location.href='mzpc!outExcel.action?mainid='+id;
		},	
		// 加载子任务数据
		loadSubData : function(param) {
			param.pagenum = param.page?param.page:$('.pcontrol input', $("#dlg_line_resultdf")).val();
			param.pagesize = param.rp?param.rp:$("[name=rp]", $("#dlg_line_resultdf")).val();
			//var jobId = $('#line_main_id').val();
			$.ajax({
				type: "POST",
				url: "mzpc!totalkh_pf.action?mainid="+$("#line_main_id").val(),
				cache: false,
				data: param,
				success: function(msg){
					//var data=eval("("+msg+")");
					sub_resultdf_grid.find("tbody").children().remove();
					var data = (new Function("","return " + msg))();
					try{
						if(data.rows.length > 0){
							sub_resultdf_grid.flexAddData(data);
						}
					}catch(e){
						showErrorMsg("查询结果信息出错！");
					}
				}
			});
		},			
		// 查询子任务并且显示
		showSubMission : function(main_id) {
			$("#line_main_id").val(main_id);
			 // 初始化grid
			 if ($(".flexigrid", selectedGridDiv).length == 1)
			 {
				param_result.bindLineSubMissionGrid();
			}
			else
			{
				alert("1");
				param_result.loadSubData({page : 1});
			} 
			 has_dlg_result();
		},		
		loadData:function(param){
				var queryP = {};
				queryP.title = $('#title_can').val()==null?'':$('#title_can').val();
				//var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
				var selectedGridDiv = $("div.flexigrid .pDiv .pDiv2 .pGroup");
				queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
				queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
				queryP.sortname = param.sortname? param.sortname : "";
				queryP.sortorder = param.sortorder? param.sortorder : "";
				$.ajax({
					type: "POST",
					url: "mzpc!resultDfList.action?operation=init",
					cache: false,
					data: queryP,
					success: function(msg){
						var data=eval("("+msg+")");
						try{
							param_grid_result.flexAddData(data);
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
						{display: '评测标题', name : 'TITLE', width : 350, align: 'left',sortable: true},
						{display: '评测类型', name : 'DIC_NAME', width : 250, align: 'left',sortable: true},
						{display: '开始时间', name : 'BEGTIME_FORMAT_DATE', width : 120, align: 'left',sortable: false},
						{display: '结束时间', name : 'ENDTIME_FORMAT_DATE', width : 120,  align: 'left',sortable : false},
						{display:'操作',width:150,align:'center',hide : true,
						process:function(obj, pid, $td){
							$tr = $td.closest("tr");
							var mainid = $('div', $tr).eq(1).text();
							//param_result.showSubMission(\'' + mainid + '\')
							var htmlContent = '&nbsp;<span style="color:blue;cursor:pointer" onclick="javascript:void(0)">获取汇总结果</span>';
							$(obj).html(htmlContent);
					    }},
					    {display: '操作', width : 100, align: 'center',sortable: true,
					    	process:function(obj,pid,$td){
								$tr = $td.closest("tr");
								var id = $('div', $tr).eq(1).text();
								$(obj).html('<span><a href="#" onclick="param_result.commitExport(' + id +');" style="color:blue;">结果导出</a></span>');
							}
					    }			
						];
		 	 			// 初始化数据
		 param_result.bindParamGrid(parm_button,colModel);
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
		$('#btn_result_search').button({ icons: {primary:'ui-icon-search'} }).on("click",function(){
			param_result.loadData({page:1});
		});

		//清空筛选框
		$('#btn_result_empty_de').on("click",function(){
			$("#title_can").val("");
			//param_mzpc.loadData({page:1});
		});
	});	
		</script>
	</body>
</html>
