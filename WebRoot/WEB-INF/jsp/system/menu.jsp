<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script type="text/javascript">

var menuGridHeight = flexHeight("menu_grid");
var menu_grid;
var fmenuTree = null;
var dlg_menu = null;
var dlg_fmenu = null;
var fmenuTreeSetting = {
	view: {
		dblClickExpand: true,
		showLine: false,
		selectedMulti: false,
		expandSpeed: ($.browser.msie && parseInt($.browser.version)<=6)?"":"fast"
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
			$('#fmenu_id_tmp').val(treeNode.id);
			$('#fmenu_name_tmp').val(treeNode.name);
		}
	}
};
var menu = {
	//绑定菜单信息grid
	bindMenuGrid:function(queryP){
		menu_grid = $("#menu_grid").flexigrid({
			url:"system!menu.action?operation=init",
			colModel : [
				{display: '序号', name : 'xh', width : 40, align: 'center'},
				{display: '菜单id', name : 'menuId', width : 10, align: 'left',hide:true},
				{display: '菜单名称', name : 'menuName', width : 150,  align: 'left'},
				{display: '链接地址', name : 'menuUrl', width : 250,  align: 'left'},
				{display: '叶菜单', name : 'isLeaf', width : 100,  align: 'left',
					process:function(obj,pid){
						if($(obj).html()=="Y")
							$(obj).html("<font color='blue'>是</font>");
						else
							$(obj).html("<font color='red'>否</font>");
					}
				},
				{display: '父菜单ID', name : 'upMenuId', width : 10,  align: 'left',hide:true},
				{display: '父菜单名称', name : 'parentname', width : 150,  align: 'left'},
				{display: '是否单独frame', name : 'isIframe', width : 150,  align: 'left',hide:true}
			],
			buttons : queryP,
			rowId:"menu_id",
			showToggleBtn: true,
			usepager: true,
			title:"菜单列表",
			rp:page_num,
			showTableToggleBtn: false,
			resizable: false,
			height: menuGridHeight,
			singleSelect: true,
			onChangePage:menu.loadData,
			checkbox:true,
			filterParams : [{key : "menu_name", name : "#txt_menu_name"}]
		});
	},
	//新增和编辑用户组
	editMenu:function(com, grid){
        var dlgParent = $("#dlg_menu").parent();
        var dlgOwn = $("#dlg_menu").clone();
		dlg_menu = $("#dlg_menu").dialog({
			autoOpen: false,
			height:330,
			width:500,
			show: "slide",
			modal:true,
			cache:false,
			/* hide: "explode", */
			title:'菜单',
			buttons:{
				"保存":function(){
					menu.saveMenu();
				}
			},
            close : function() {
            	dlgOwn.appendTo(dlgParent);
            	$(this).dialog("destroy").remove();
            }
		});
		$('#btn_fmenu_show').button({ 
			icons: {primary:'ui-icon-search'} 
		}).on("click",function(){
			menu.openFmenuDlg();
		});
		$('#btn_new_button').button({ 
			icons: {primary:'ui-icon-search'} 
		}).on("click",function(){
			menu.newButton();
		});
		if (com == '新建') {
			$("#menu_id_edit").val("");
			$("#menu_name_edit").val("");
			$("#url_edit").val("");
			$("#fmenu_id_edit").val("");
			$("#fmenu_name_edit").val("");
			$("#menu_action").val("add");
			dlg_menu.dialog("option", "title", '新增菜单').dialog("open");
		}else if (com == '修改') {
			if($('.trSelected', grid).length==1){
				var menu_id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
				var menu_name = $('.trSelected', grid).eq(0).find("td:eq(2)").text();
				var url = $('.trSelected', grid).eq(0).find("td:eq(3)").text();
				var isLeaf = $('.trSelected', grid).eq(0).find("td:eq(4)").text();
				var fmenu_id = $('.trSelected', grid).eq(0).find("td:eq(5)").text();
				var fmenu_name = $('.trSelected', grid).eq(0).find("td:eq(6)").text();
				var isframe = $('.trSelected', grid).eq(0).find("td:eq(7)").text();
				if (menu_id == '0') {
					showMsg("根目录不能修改！");
					$('#dlg_menu').dialog('close');
					return;
				}else{
					$("#menu_id_edit").val(menu_id);
					$("#menu_name_edit").val(menu_name);
					$("#url_edit").val(url);
					$("#fmenu_id_edit").val(fmenu_id);
					$("#fmenu_name_edit").val(fmenu_name);
					if (isLeaf == "是") {
						$('#isleaf_edit').attr("checked", true);
						//$(".caozuo").css("display","block");
					} else {
						$('#isleaf_edit').attr("checked", false);
						$(".caozuo").css("display","none");
					}
					if (isframe == "1") 
					{
						$('#isframe').attr("checked", true);
					} else 
					{
						$('#isframe').attr("checked", false);
					}
					$("#menu_action").val("edit");
					
					var params=new Object();
		            params.menu_id = menu_id;
				    
		            $.ajax({
						url : "admin!readButtons.action",
						type : "post",
						dataType : "json",
						data: params,
						success : function(data) 
						{
							if (data.button_name) 
							{
							    data_all = data.button_name;
								for ( var i = 0; i < data_all.length; i++) 
								{
									if(data_all[i]=="新建"){
										$('#newb').attr("checked", true);
									}else if(data_all[i]=="修改"){
										$('#changeb').attr("checked", true);
									}else if(data_all[i]=="删除"){
										$('#delectb').attr("checked", true);
									}else if(data_all[i]=="导出"){
										$('#outb').attr("checked", true);
									}else{
										$("#buttionsId").append("<input type='checkbox' name='buttonname' checked='checked' value="+data_all[i]+">"+data_all[i]);
									}
							    }
							}
						}
		            });			
					dlg_menu.dialog("option", "title", '修改菜单').dialog("open");
				}
			}else if($('.trSelected', grid).length>1){
				showMsg("不能同时选择多行记录！");
				$('#dlg_menu').dialog('close');
			}else{
				showMsg("未选中行！");
				$('#dlg_menu').dialog('close');
			}
		}
	},
	//打开父菜单编辑窗口
	openFmenuDlg:function(){
        var dlgParent = $("#dlg_fmenu").parent();
        var dlgOwn = $("#dlg_fmenu").clone();
		dlg_fmenu = $("#dlg_fmenu").dialog({
			autoOpen: false,
			height:600,
			width:400,
			show: "slide",
			modal:true,
			cache:false,
			/* hide: "explode", */
			title:"选择父菜单",
			buttons:{
				"确定":function(){
					menu.selectMenu();
				}
			},
            close : function() {
            	dlgOwn.appendTo(dlgParent);
            	$(this).dialog("destroy").remove();
            }
		});
		$('#fmenu_id_tmp').val('');
	    $('#fmenu_name_tmp').val('');
	    dlg_fmenu.dialog("open");
		fmenuTree = null;
		$("#fmenu_tree").html("");
		var queryP = {};
		queryP.pagenum = 1;
		queryP.pagesize = 10000;
		$.ajax({
			type: "POST",
			url: "system!treeMenu.action",
			cache: false,
			data: queryP,
			success: function(msg){
				var data=eval("("+msg+")");
				fmenuTree = $.fn.zTree.init($("#fmenu_tree"), fmenuTreeSetting, data);
			}
		});
	},
	
	//返回选中的父菜单
	selectMenu:function(){
		var nodes = fmenuTree.getSelectedNodes();
		if(nodes.length>0){
			if(nodes[0].id==$("#menu_id_edit").val()){
				showMsg("不能选择自身作为父菜单，请重新选择！");
				return;
			}
			if(nodes[0].isleaf=='Y'){
				showMsg("叶节点不能作为父菜单，请重新选择！");
				return;
			}
		}
		$('#fmenu_id_edit').val($('#fmenu_id_tmp').val());
		$('#fmenu_name_edit').val($('#fmenu_name_tmp').val());
	    $("#dlg_fmenu").dialog('close');
	},
	//保存菜单
	saveMenu:function(){
		if($("#menu_name_edit").val()==null||$("#menu_name_edit").val()==''){
			showMsg("菜单名称不能为空！");
			return;
		}
		
		//避免重新分配权限之后，系统无法再操作菜单的修改功能
		if($("#menu_id_edit").val()==27&&$('#changeb').attr("checked")==undefined){
			showMsg("菜单管理的修改操作必选！");
			return;
		}
		//存放操作按钮的名字
		var buttonname = new Array();
    	$("input[name='buttonname']").each(function(){
		    if ($(this).attr("checked")) 
		    {
		    	buttonname.push(this.value);
		    }	
	    });	
    	buttonname=JSON.stringify(buttonname);
		var map = {};
		map.action = $('#menu_action').val();
		map.menu_id = $('#menu_id_edit').val();
		map.menu_name = $('#menu_name_edit').val();
		map.fmenu_id = $.trim($('#fmenu_id_edit').val())!=''?$('#fmenu_id_edit').val():"0";
		map.is_leaf = $('#isleaf_edit').is(":checked") ? "Y":"N";
		map.isIframe = $('#isframe').is(":checked") ? "1":"0";
		map.url = $.trim($("#url_edit").val());
		map.buttonname=buttonname;
		// 新增和编辑通用字符串
		var url ='';
		var indicate='';
		var msg='';
		if ("add" == map.action) {
			indicate = "确认新增" + map.menu_name + ",这个菜单?";
			msg = "新增菜单成功！操作权限分配后生效！";
			url = "system!createMenu.action";
		} else if ("edit" == map.action) {
			indicate = "确认更新" + map.menu_name + ",这个菜单?";
			msg = "更新菜单成功！操作权限重新分配后生效！";
			url = "system!updateMenu.action";
		}
		if(confirm(indicate)) {
			$.ajax({
				type: "POST",
				url: url,
				cache: false,
				data: map,
				success: function(data){
					if(data=="yes"){
						showMsg(msg);
						$('#dlg_menu').dialog('close');
						menu.loadData({});
					}
				}
			});
		}
	},
	
	//新建除新增、删除、修改、导出以外的其他操作按钮
	newButton:function(){
		var dlgParent = $("#dlg_new_button").parent();
        var dlgOwn = $("#dlg_new_button").clone();
        dlg_new_button = $("#dlg_new_button").dialog({
			autoOpen: false,
			height:150,
			width:350,
			show: "slide",
			modal:true,
			cache:false,
			/* hide: "explode", */
			title:"新增菜单操作",
			buttons:{
				"确定":function(){
					menu.addNewButton();
				}
			},
            close : function() {
            	dlgOwn.appendTo(dlgParent);
            	$(this).dialog("destroy").remove();
            }
		});
        dlg_new_button.dialog("open");
	},
	
	//将新增按钮增入操作列表 
	addNewButton:function(){
		var buttonName = $("#add_newbutton_name").val();
		$('#dlg_new_button').dialog('close');
		$("#buttionsId").append("<input type='checkbox' name='buttonname' checked='checked' value="+buttonName+">"+buttonName);
	},
	
	loadData:function(param){
		var queryP = {};
		queryP.menu_name = $('#txt_menu_name').val()==null?'':$('#txt_menu_name').val();
		var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
		queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
		queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();

		$.ajax({
			type: "POST",
			url: "system!menu.action?operation=init",
			cache: false,
			data: queryP,
			success: function(msg){
				var data=eval("("+msg+")");
				try{
					menu_grid.flexAddData(data);
				}catch(e){
					showErrorMsg("查询菜单信息出错！");
				}
			}
		});
	}
};
$(function(){
	//初始化所有操作按钮
	 var  parm_button = [{name: '新建', bclass: 'add', onpress : menu.editMenu},
	    				{name: '修改', bclass: 'edit', onpress : menu.editMenu}];
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
			menu.bindMenuGrid(buttons);
		}
	});
	/*菜单管理-查询按钮功能*/
	$('#btn_menu_search').button({ icons: {primary:'ui-icon-search'} }).on("click",function(){
		menu.loadData({page : 1});
	});
});
</script>
<div class="div div-1">
	<div class="title title-1">
			<h3>
					<img src='../images/fold.png' onclick="showAndHide(this,'menu_grid');"  class="imgShrotate">
					<font>查询条件</font>
			</h3>
	</div>
	<div class="div-main-1" >
		<table cellpadding="0" cellspacing="0" border="0" class="table-1">
			<tr>
				<td class="wi-align-l" ><label for="input_1" class="lbl-1">菜单名称：</label>
					<input type="text" id="txt_menu_name" name="txt_menu_name" class="wi-ipt-1" />
				</td>
				<td class="wi-align-l"><a href="#" id="btn_menu_search">查询</a></td>
			</tr>
		</table>
	</div>
</div>
<div class="div div-2">
	<table id="menu_grid" style="display:none;padding:0 0 0 0;"></table>
</div>

<div id="dlg_menu" style="display:none">
	<input type="hidden" id="menu_action" />
	<input type="hidden" id="menu_id_edit" />
	<input type="hidden" id="fmenu_id_edit" />
	<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
		<tr>
			<td style="width:100px;"><label for="input_1">菜单名称：</label></td>
			<td colspan="2" class="wi-align-l" style="width:120px;">
				<input type="text" id="menu_name_edit" value="" class="wi-ipt-y" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;"><label for="input_1">链接地址：</label></td>
			<td colspan="2" class="wi-align-l" style="width:120px;">
				<input type="text" id="url_edit"  class="wi-ipt-y" />
			</td>
		</tr>
		<tr>
			<td style="width:100px;"><label for="input_1">父菜单名称：</label></td>
			<td class="wi-align-l" style="">
				<input type="text" id="fmenu_name_edit"  class="wi-ipt-y" disabled/>
			</td>
			<td class="wi-align-l"><a href="#" id="btn_fmenu_show">选择</a></td>
		</tr>
		<tr>
			<td style="width:120px;"><label for="input_1">是否叶节点：</label></td>
			<td colspan="2" class="wi-align-l" style="width:120px;">
				<input type="checkbox" id="isleaf_edit" />
			</td>
		</tr>
		<tr>
			<td style="width:120px;"><label for="input_1">是否单独frame：</label></td>
			<td colspan="2" class="wi-align-l" style="width:120px;">
				<input type="checkbox" id="isframe" />
			</td>
		</tr>
		<tr class="caozuo">
			<td style="width:100px;"><label for="input_1">操作：</label></td>
			<td class="wi-align-l" style="width:200px;" >
                 <div id="buttionsId">
                 	<input id='newb' type='checkbox' name='buttonname' value='新建'>新建
                 	<input id='delectb' type='checkbox' name='buttonname' value='删除'>删除
                 	<input id='changeb' type='checkbox' name='buttonname' value='修改'>修改
                 	<input id='outb' type='checkbox' name='buttonname' value='导出'>导出
                 </div>
			</td>
			<td class="wi-align-l"><a href="#" id="btn_new_button">新增</a></td>
		</tr>
	</table>
</div>  
<div id="dlg_fmenu" style="display:none">  
	<div style="overflow-y: scroll;height:500px">
		<ul id="fmenu_tree" class="ztree">
		</ul>
	</div>
	<input type="hidden" id="fmenu_id_tmp" /> 
	<input type="hidden" id="fmenu_name_tmp" />  
</div>
<div id="dlg_new_button" style="display:none"> 
	<table cellpadding="0" cellspacing="0" border="0" class="table-1"> 
		<tr>
			<td style="width:80px;"><label for="input_1">操作：</label></td>
			<td colspan="2" class="wi-align-l" style="width:200px;">
				<input type="text" id="add_newbutton_name" value=""/>
			</td>
		</tr>
	</table>
</div>   