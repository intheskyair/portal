<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script type="text/javascript">
/* 计算flexGrid 的height
*@param objNext flexGrid后面一个对象
*@param objWrap flexGrid外面包围的对象
*@return flexGridHeight flexGrid的高
* 
* 解决ui-layout-south不存在出错BUG
*/
function dmh()
{
	var hh1 = 0;
	var hh2 = $(".tDiv").height();
	if ($(".div-main-1").length>0)
	{
		hh1 = $(".div-main-1").height();
	}
	return hh1+hh2+113;
}
function flexHeight(fgId,objNext){
	var objWrap = $("#"+fgId).parent();
	var $tabs = $('#tabs').find("div.ui-tabs-panel,div.ui-widget-content,div.ui-corner-bottom");
	var temp_h = 0;
	if(!objNext){
		if($(".ui-layout-south:visible").length >0)
			objNext = $(".ui-layout-south:visible");
		else
			objNext = $(".wi-width");
	}		
	if(false == arguments[2]){
		temp_h = 85;
	}else{
		temp_h =dmh();
	}
	var FlexWrapHeight
		 = getIntValue(objNext.offset().top)
			 - parseInt(getIntValue(objNext.css("margin-top")))
			 - parseInt(getIntValue(objNext.css("border-top-width")))
			 - getIntValue(objWrap.offset().top) 
			 - getIntValue(objArea(objWrap)[1])
			 - parseInt(getIntValue($tabs.css("padding-bottom")))
			 - parseInt(getIntValue($tabs.css("border-bottom-width")))
			 - parseInt(getIntValue($tabs.css("margin-bottom")))
			 - parseInt(getIntValue($tabs.find(".wi-tabs-panel-wrap").css("padding-bottom")))
			 - parseInt(getIntValue($tabs.find(".wi-tabs-panel-wrap").css("border-bottom-width")))
			 - parseInt(getIntValue($tabs.find(".wi-tabs-panel-wrap").css("margin-bottom")));
	var FlexHeight = FlexWrapHeight - temp_h;// 122 = $(".tDiv").outerHeight(true) + $(".hDiv").outerHeight(true)+ $(".pDiv").outerHeight(true)
	objWrap.height(FlexWrapHeight-(($.browser.msie && parseInt($.browser.version)<=6)?0:0));	
	return FlexHeight -(($.browser.msie && parseInt($.browser.version)<=6)?0:0);
}



var roleGridHeight = flexHeight("role_grid");
// 菜单树形结构队形
var menuTree;
// 表格数据
var role_grid;
// 权限编辑对话框
var dlg_role;

// 树形结构设置
var roleTreeSetting = {
		view: {
			dblClickExpand: true,
			showLine: false,
			selectedMulti: true,
			expandSpeed: ($.browser.msie && parseInt($.browser.version)<=6)?"":"fast"
		},
		check : {
			enable : true,
			chkboxType : {"Y" : "ps", "N" : "ps"}
		},
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pId"
			}
		},
		callback:{
			onClick: function(event, treeId, treeNode, clickFlag){
				$('#dlg_role_menu_id').val(treeNode.id);
				$('#dlg_role_menu_name').val(treeNode.name);
			}
		}
	};
var role = {
       dlg_role_menu:"",
		// 绑定grid
		bindGrid : function(queryP) {
			role_grid = $("#role_grid").flexigrid({
				url : "queryRole!queryRole.action?operation=init",
				colModel : [
					{display: '序号', name : 'xh', width : 40, align: 'center'},
					{display: '菜单id', name : 'ID', width : 10, align: 'left',hide:true},
					{display: '角色名称', name : 'AUTHNAME', width : 150,  align: 'left'},
					{display: '角色描述', name : 'REMARK', width : 250,  align: 'left'}
				],
				buttons :queryP,
				rowId : "ID",
				showToggleBtn : false,
				usepager : true,
				title : "角色管理",
				rp:page_num,
				showTableToggleBtn : false,
				resizable : false,
				height : roleGridHeight,
				singleSelect : false,
				onChangePage : role.loadData,
				checkbox : true,
			});
		},
		// 新增权限
		editRole : function(com, grid) {
			var dlgParent = $("#dlg_role").parent();
			var dlgOwn = $("#dlg_role").clone();
			dlg_role = $("#dlg_role").dialog({
				autoOpen: false,
				height:245,
				width:450,
				show: "slide",
				modal:true,
				cache:false,
				/* hide: "explode", */
				title:'菜单',
				buttons:{
					"保存":function(){
						role.saveData();
					}
				},
	            close : function() {
	            	dlgOwn.appendTo(dlgParent);
	            	$(this).dialog("destroy").remove();
	            }
			});
			if (com == '新建') {
				$("#role_id_edit").val("");
				$("#role_name_edit").val("");
				$("#role_remark_edit").val("");
				$("#role_action").val("add");
				dlg_role.dialog("option", "title", '新增权限').dialog("open");
			} else {
				if($('.trSelected', grid).length==1){
					var role_id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
					var role_name = $('.trSelected', grid).eq(0).find("td:eq(2)").text();
					var role_remark = $('.trSelected', grid).eq(0).find("td:eq(3)").text();
					
					$("#role_id_edit").val(role_id);
					$("#role_name_edit").val(role_name);
					$("#role_remark_edit").val(role_remark);
					$("#role_action").val("edit");
					dlg_role.dialog("option", "title", '修改权限').dialog("open");
				}else if($('.trSelected', grid).length>1){
					$('#dlg_role').dialog('close');
					showMsg("不能同时选择多行记录！");
				}else{
					$('#dlg_role').dialog('close');
					showMsg("未选中行！");
				}
			}
		},
		// 分配权限
		authRole : function(com, grid) {
	        var dlgParent = $("#dlg_role_menu").parent();
	        var dlgOwn = $("#dlg_role_menu").clone();
	        role.dlg_role_menu = $("#dlg_role_menu").dialog({
				autoOpen: false,
				height:550,
				width:700,
				show: "slide",
				modal:true,
				cache:false,
				/* hide: "explode", */
				title:"分配权限",
				buttons:{
					"确定":function(){
						role.selectMenus();
					}
				},
	            close : function() {
	            	dlgOwn.appendTo(dlgParent);
	            	$(this).dialog("destroy").remove();
	            }
			});

	        if ($('.trSelected', grid).length == 1) {
	        	var roleId = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
	        	$("#dlg_role_id").val(roleId);
				$('#dlg_role_menu_id').val('');
			    $('#dlg_role_menu_name').val('');
			    role.dlg_role_menu.dialog("open");
				$("#fmenu_tree").html("");
				var queryP = {};
				queryP.pagenum = 1;
				queryP.pagesize = 10000;
				queryP.roleId = roleId;
				$.ajax({
					type: "POST",
					url: "doauth!qxToRole.action",
					cache: false,
					data: queryP,
					success: function(msg){
						var data=eval("("+msg+")");
						//显示树结构
						menuTree = $.fn.zTree.init($("#dlg_role_menu_tree"), roleTreeSetting, data.allMenu);
						//展开所有
						var treeObj = $.fn.zTree.getZTreeObj("dlg_role_menu_tree");
						treeObj.expandAll(true);
						
						//菜单权限
						for (var i in data.authedMenu) {
							menuTree.checkNode(menuTree.getNodeByParam("id", data.authedMenu[i].ID), true, false);
						}
// 						alert("tree==="+$("#dlg_role_menu_tree li ul span").length);
// 						alert("data.allButton==="+data.allButton.length);
						//显示操作按钮
						for (var j in $("#dlg_role_menu_tree li ul span")) {
							var pname = $("#dlg_role_menu_tree li ul span").eq(j).text();
							var html='<div style="position:absolute;left:190px">';
							for (var k in data.allButton) {
// 								alert(pname +"=="+ data.allButton[k].pName);
// 								if(data.allButton[k].pId == "20056"){
// 									console.log(pname +"=="+ data.allButton[k].pName);
// 								}
								if(pname == data.allButton[k].pName){
									html +='<label style="position:relative;top:-18px"><input type="checkbox" id="'
										 +data.allButton[k].id+'"value="'+data.allButton[k].name+'"/>'+data.allButton[k].name+'&nbsp;&nbsp;</label>';
								}
							}
							html +='</div>';
							var menuId = $("#dlg_role_menu_tree li ul span").eq(j).parent().parent().attr("id");
							if(html!='<div style="position:absolute;left:190px"></div>'){
								$("#"+menuId).append(html);
							}
						}
						//操作按钮的权限
						for (var p in data.authedButton) {
							$("#"+data.authedButton[p].ID).attr("checked",true);
						}
					}
				});
	        } else if ($('.trSelected', grid).length > 1) {
				$('#dlg_role_menu').dialog('close');
				showMsg("不能同时选择多行记录！");
	        } else {
				$('#dlg_role_menu').dialog('close');
				showMsg("未选中行！");
	        }

		},
		// 更改用户权限
		selectMenus : function() {
			var nodes = menuTree.getCheckedNodes(true);
			var menuIds = [];
			var buttonIds = new Array();
			$("#dlg_role_menu_tree input").each(function(){
				if($(this).attr("checked")){
					buttonIds.push(this.id);
				}
			});
			for (var i in nodes) {
				menuIds.push(nodes[i].id);
			}
			var queryP = {};
			queryP.menuIds = menuIds.join(",");
			queryP.buttonIds = buttonIds.join(",");
			queryP.roleId = $("#dlg_role_id").val();
			$.ajax({
				type: "POST",
				url: "doauth!doModifyMenuToRole.action",
				cache: false,
				data: queryP,
				success: function(msg){
					if(msg=="yes"){
						showMsg("分配成功");
					} else {
						showMsg("分配失败");
					}
					role.loadData({});
					$('#dlg_role_menu').dialog('close');
				}
			});
		},
		// 保存数据
		saveData : function() {
			var queryP = {};
			queryP.roleName = $("#role_name_edit").val();
			queryP.roleRemark = $("#role_remark_edit").val();
			queryP.id = $("#role_id_edit").val();
			queryP.action = $("#role_action").val();
			if($("#role_name_edit").val()==null || $.trim($("#role_name_edit").val())=='')
			{
				showMsg("权限名称不能为空！");
				return;
			}
			if($("#role_remark_edit").val()==null|| $.trim($("#role_remark_edit").val())=='')
			{
				showMsg("权限说明不能为空！");
				return;
			}
			$.ajax({
				type: "POST",
				url: "queryRole!edit.action",
				cache: false,
				data: queryP,
				success: function(msg){
					if(msg=="yes"){
						showMsg("操作成功");
					} else {
						showMsg("操作失败");
					}
					role.loadData({});
					$('#dlg_role').dialog('close');
				}
			});
		},
		// 加载数据
		loadData : function(param) {
			var queryP = {};
			var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
			queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
			queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();

			$.ajax({
				type: "POST",
				url: "queryRole!queryRole.action?operation=init",
				cache: false,
				data: queryP,
				success: function(msg){
					var data=eval("("+msg+")");
					try{
						role_grid.flexAddData(data);
					}catch(e){
						showErrorMsg("查询权限信息出错！");
					}
				}
			});
		}
};
$(function(){
	//初始化所有操作按钮
	 var  parm_button = [{name: '新建', bclass: 'add', onpress : role.editRole},
						{name: '修改', bclass: 'edit', onpress : role.editRole},
						{name: '分配权限', bclass: 'distribute', onpress : role.authRole}];
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
						buttons.push({separator: true});
					}
				});
			}
 			// 初始化数据
			role.bindGrid(buttons);
		}
	});
	
});
</script>
<div class="div div-2">
	<table id="role_grid" style="display:none;padding:0 0 0 0;"></table>
</div>
<div id="dlg_role" style="display:none">
	<input type="hidden" id="role_action" />
	<input type="hidden" id="role_id_edit" />
	<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
		<tr>
			<td style="width:100px;"><label for="input_1">权限名称：</label></td>
			<td colspan="2" class="wi-align-l" style="width:120px;">
				<input type="text" id="role_name_edit" value="" class="wi-ipt-1" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;"><label for="input_1">权限描述：</label></td>
			<td colspan="2" class="wi-align-l" style="width:120px;">
				<input type="text" id="role_remark_edit" value="" class="wi-ipt-1" />
			</td>
		</tr>
	</table>
</div>
<div id="dlg_role_menu" style="display:none;    overflow: scroll;">  
		<ul id="dlg_role_menu_tree" class="ztree">
		</ul>

	<input type="hidden" id="dlg_role_id" /> 
</div>  