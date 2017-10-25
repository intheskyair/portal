 <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 <script type="text/javascript">
 var paramGridHeight_can = flexHeight("param_grid");
 var param_grid_can;
 var param_can = {
			//绑定参数信息grid
			bindParamGrid:function(queryP){
				param_grid_can = $("#param_grid").flexigrid({
					url:"queryUser!showSyspara.action?operation=init",
					colModel : [
						{display: '序号', name : 'xh', width : 40, align: 'center'},
						{display: 'id', name : 'ID', width : 40, align: 'center', hide : true},
						{display: '参数名称', name : 'PARANAME', width : 200, align: 'left'},
						{display: '参数值', name : 'PARAVALUE', width : 250, align: 'left'},
						{display: '参数说明', name : 'REMARK', width : 250,  align: 'left'},
						{display: '生效方式', name : 'ACTIVE_STR', width : 150,  align: 'left'}
					],
					buttons :queryP ,
					rowId:"id",
					showToggleBtn: true,
					usepager: true,
					title:"参数列表",
					//rp:pageSize,
					rp:page_num,
					showTableToggleBtn: false,
					resizable: false,
					height: paramGridHeight_can,
					singleSelect: false,
					onChangePage:param_can.loadData,
					checkbox:true,
					filterParams : [{key : "paraName", name : "#txt_param_name"}]
				});
			},
			//新增和编辑参数信息
			editParam:function(com, grid){
	            var dlgParent = $("#dlg_param").parent();
	            var dlgOwn = $("#dlg_param").clone();
				dlg_param = $("#dlg_param").dialog({
					autoOpen: false,
					height:200,
					width:450,
					show: "slide",
					modal:true,
					cache:false,
					/* hide: "explode", */
					title:'参数',
					buttons:{
						"立即生效":function(){
							param_can.saveParam(0);
						},
						"重启生效":function(){
							param_can.saveParam(1);
						}
					},
	                close : function() {
	                	dlgOwn.appendTo(dlgParent);
	                	$(this).dialog("destroy").remove();
	                }
				});
				if (com == '新建') {
					$(".another_show").css("display","block");
					$(".pagenum_show").css("display","none");
					$("#dlg_paraName").val("");
					$("#dlg_paraVal").val("");
					$("#dlg_remark").val("");
					$("#action").val("add");
					dlg_param.dialog("option", "title", '新增参数').dialog("open");
				}else if (com == '修改') {
					if($('.trSelected', grid).length==1){
						var paraName = $('.trSelected', grid).eq(0).find("td:eq(2)").text();
						var id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
						var paraValue = $('.trSelected', grid).eq(0).find("td:eq(3)").text();
						var remark = $('.trSelected', grid).eq(0).find("td:eq(4)").text();
						if(paraName=="page_num"){
							$(".another_show").css("display","none");
							$(".pagenum_show").css("display","block");
							$("#pagenum_value").val(paraValue);
							
						}else{
							$(".another_show").css("display","block");
							$(".pagenum_show").css("display","none");
							$("#dlg_paraVal").val(paraValue);
						}
						$("#dlg_paraName").val(paraName);
						$("#dlg_remark").val(remark);
						$("#action").val("edit");
						$("#dlg_id").val(id);
						dlg_param.dialog("option", "title", '编辑参数').dialog("open");
					}else if($('.trSelected', grid).length>1){
						$('#dlg_param').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else{
						$('#dlg_param').dialog('close');
						showMsg("未选中行！");
					}
				}
			},
			//保存参数
			saveParam:function(type){
				if($("#dlg_paraName").val()==null || $.trim($("#dlg_paraName").val())=='')
				{
					showMsg("参数名称不能为空！");
					return;
				}
				if($("#dlg_paraVal").val()==null|| $.trim($("#dlg_paraVal").val())=='')
				{
					if($("#pagenum_value").val()==null|| $.trim($("#pagenum_value").val())==''){
						showMsg("参数值不能为空！");
						return;
					}
				}
				// 提交方式
				var action = $("#action").val();
				var params=new Object();
				params.PARANAME = $.trim($("#dlg_paraName").val());
				if($.trim($("#dlg_paraName").val())=="page_num")
				{
					params.PARAVALU = $.trim($("#pagenum_value").val());
				}
				else
				{
					params.PARAVALU = $.trim($("#dlg_paraVal").val());
				}
				params.REMARK   = $.trim($("#dlg_remark").val());
				params.SXTYPE   = type;
				// 新增和编辑通用字符串
				var url ='';
				var indicate='';
				if ("add" == action) {
					indicate = "确认新增" + params.PARANAME + ",这个参数?";
					url = "queryUser!addSyspara.action";
				} else if ("edit" == action) {
					params.id = $("#dlg_id").val();
					indicate = "确认更新" + params.PARANAME + ",这个参数?";
					url = "queryUser!updateSyspara.action";
				}
				// 弹出确认框
				if(confirm(indicate)) {
					$.ajax({
						type: "POST",
						url: url,
						cache: false,
						data: params,
						success: function(msg){
							var result=eval("("+msg+")");
							if (result.msg=="ok") {
								showMsg("保存成功");
								$('#dlg_param').dialog('close');
								/* param_grid.flexReload();   */
								param_can.loadData({});
							} else {
								showMsg(result.msg);
							}
						}
					}); 
				}
			},
			loadData:function(param){
				var queryP = {};
				queryP.paraName = $('#txt_param_name').val()==null?'':$('#txt_param_name').val();
				var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
				queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
				queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
				$.ajax({
					type: "POST",
					url: "queryUser!showSyspara.action?operation=init",
					cache: false,
					data: queryP,
					success: function(msg){
						var data=eval("("+msg+")");
						try{
							param_grid_can.flexAddData(data);
						}catch(e){
							showErrorMsg("查询参数信息出错！");
						}
					}
				}); 
			} 
		};
 
	 $(function(){
		//初始化所有操作按钮
		 var  parm_button = [{name: '新建', bclass: 'add', onpress : param_can.editParam},
							{name: '修改', bclass: 'edit', onpress : param_can.editParam}];
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
						}
					});
					buttons.push({separator: true});
				}
	   	// 初始化数据
				param_can.bindParamGrid(buttons);
			}
		});
		/*参数管理-查询按钮功能*/
		$('#btn_param_search').button({ icons: {primary:'ui-icon-search'} }).on("click",function(){
			param_can.loadData({page : 1});
		});
	});
</script>
<div class="div div-1">
	<div class="title title-1">
		<h3>
					<img src='../images/fold.png' onclick="showAndHide(this,'param_grid');"  class="imgShrotate">
					<font>系统参数管理</font>
			</h3>
	</div>
	<div class="div-main-1" >
		<table cellpadding="0" cellspacing="0" border="0" class="table-1">
			<tr>
				<td class="wi-align-l"><label for="input_1" class="lbl-1">参数名称：</label>
					<input type="text" id="txt_param_name" name="txt_param_name" class="wi-ipt-1" />
				</td>
				<td class="wi-align-l"><a href="#" id="btn_param_search">查询</a></td>
			</tr>
		</table>
	</div>
</div>
<div class="div div-2" style="">
	<table id="param_grid" style="display:none;padding:0 0 0 0;"></table>
</div>
<div id="dlg_param" style="display:none">
	<input type="hidden" id="action" />
	<input type="hidden" id="dlg_id" />
	<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
		<tr>
			<td style="width:100px;"><label for="input_1">参数名称：</label></td>
			<td class="wi-align-l" style="width:240px;">
				<input type="text" id="dlg_paraName"  value="" class="wi-ipt-1" style="width:200px;"/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;"><label for="input_1">参数值：</label></td>
			<td class="wi-align-l" style="width:240px;">
				<input type="text" id="dlg_paraVal" value="" class="another_show" style="width:200px;"/>
				<select name="pagenum_value" id="pagenum_value" class="pagenum_show">
					<option value="10">10</option>
					<option value="15">15</option>
					<option value="20">20</option>
					<option value="30">30</option>
					<option value="50">50</option>
					<option value="60">60</option>
				</select>
			</td>
		</tr>
		<tr>
			<td style="width:100px;"><label for="input_1">参数描述：</label></td>
			<td class="wi-align-l" style="width:240px;">
				<input type="text" id="dlg_remark" value="" class="wi-ipt-1" style="width:200px;"/>
			</td>
		</tr>
	</table>
</div> 