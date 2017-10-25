<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<style>
.li_content{
	width: 60px;
	text-align: center;
}
</style>
<script type="text/javascript" src="views/common/js/zTree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="views/common/js/zTree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="views/common/js/zTree/jquery.ztree.exedit.js"></script>
<link rel="stylesheet" type="text/css" href="views/common/css/themes/css/zTreeStyle/zTreeStyle.css" />
<div>
	<p>
		<strong>
			您当前选择了<span>0</span>/ <span>12</span>个人员!
		</strong>
		<span>
			<a href="javascript:void(0);">清除已选</a>
			<a href="javascript:void(0);">全体人员</a>
			<input type='text' class="form-control" id="serch_text" style="width: 120px;display: inline;">
			<input type='button' class="btn btn-primary" style="margin-top: -5px;" value="搜索">
		</span>
	</p>
	<div>
		<ul class="list-inline" style="width: 520px;margin-left: 5px;">
			<li class="li_content" style="padding: 8px;">
				<input type="checkbox" id="all_check">
			</li>
			<li style="width: 400px;">
				<!-- <select class="form-control">
					<option>1</option>
  					<option>2</option>
  					<option>3</option>
				</select> -->
				<input class="form-control" onclick="showMenu(); return false;" id="citySel" />
			</li>
		</ul>	
	</div>
	<div style="overflow-y:auto; ">
		<table class="table table-striped" style="width: 520px;margin-left: 5px;">
			<thead class="text-center">
				<tr>
					<td >选择</td>
					<td>姓名</td>
					<td>岗位</td>
					<td>组织机构</td>
				</tr>
			</thead>
			<tbody id="depart_person" class="text-center">
				
			</tbody>
		</table>
	</div>
	<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
		<ul id="treeDemo" class="ztree" style="margin-top:0; width:260px;background: #f0f6e4;"></ul>
	</div>
</div>
<script type="text/javascript">
var getServer_data = function(){
	var queryP = {};
	queryP.info = $("#serch_text").val();
	$.ajax({
		url:"docum!queryUserDepartInfos.action",
	   	type:"GET",
	   	cache: false,
	   	data : queryP,
		success: function(data){
			if(data != null){
				var obj = eval("("+data+")");
				render_table(obj);
			}
			//render_table(data);
		}  
	}); 
};

var render_table = function(res){
	var html = "";
	//alert(res.length);
	for (var i = 0; i < res.length; i++) {
		html += "<tr><td class='li_content'>"+
		   "<input type='checkbox' class='ischeck' value='"+res[i].TRUENAME+"#"+res[i].ORG_NAME+"'></td><td>"+res[i].TRUENAME+"</td><td>"+res[i].ORG_INFOR+"</td><td>"+res[i].ORG_NAME+"</td>"+
		   "</tr>";
	}
	$("#depart_person").html(html);
};
	
	
$(function(){
	getServer_data();
	del_html();
	//render_table();
	var id_arr = new Array();
	$("#all_check").click(function(){
		if($(this).prop("checked")){
			$(".ischeck").prop("checked",true);
			add_html(rest_data());
		}else{
			$(".ischeck").prop("checked",false);
			id_arr.length = 0;
			del_html();
		}
	});
	
	$(document).off("click",".ischeck").on("click",".ischeck",function(){
		//var arr = $(this).val().split("#");
		if($(this).prop("checked")){
			add_html(rest_data());
		}else{
			//var sub_id = "";
			if(id_arr.length <= 0){
				alert("参数错误!");
				return;
			}
			$("#all_check").prop("checked",false);
			add_html(rest_data());
		}
	});
	
	
	function rest_data(){
		var html = "";
		id_arr.length = 0;
		$(".ischeck").each(function(){
			if($(this).prop("checked")){
				var arr = $(this).val().split("#");
				id_arr.push($(this).val());
				html += arr[0] + "(" + arr[1]  +"),";
			}
		});
		return html.substring(0,html.length - 1);
	}
	
	function add_html(html){
		if(type == 0){
			$("#nextOperatorName").val(html);
		}
		if(type == 1){
			$("#ccOperatorName").val(html);
		}
	}
	
	function del_html(){
		if(type == 0){
			$("#nextOperatorName").val("");
		}
		if(type == 1){
			$("#ccOperatorName").val("");
		}
	}
});

//ztree
var setting = {
			view: {
				dblClickExpand: false
			},
			data: {
				key:{
					name: "ORG_NAME"
				},
				simpleData: {
					enable: true,
					name: "ORG_NAME",
					idKey: "ORG_ID",
					pIdKey: "SUPER_ORG_ID",
					rootPId: 0
				}
			},
			callback: {
				beforeClick: beforeClick,
				onClick: onClick
			}
		};
		
		var zNodes =[
			{ORG_ID:1, SUPER_ORG_ID:0, ORG_NAME:"北京"},
			{ORG_ID:2, SUPER_ORG_ID:0, ORG_NAME:"天津"},
			{ORG_ID:3, SUPER_ORG_ID:0, ORG_NAME:"上海"},
			{ORG_ID:6, SUPER_ORG_ID:0, ORG_NAME:"重庆"},
			{ORG_ID:4, SUPER_ORG_ID:0, ORG_NAME:"河北省"},
			{ORG_ID:41, SUPER_ORG_ID:4, ORG_NAME:"石家庄"},
			{ORG_ID:42, SUPER_ORG_ID:4, ORG_NAME:"保定"},
			{ORG_ID:43, SUPER_ORG_ID:4, ORG_NAME:"邯郸"},
			{ORG_ID:44, SUPER_ORG_ID:4, ORG_NAME:"承德"},
			{ORG_ID:5, SUPER_ORG_ID:0, ORG_NAME:"广东省"},
			{ORG_ID:51, SUPER_ORG_ID:5, ORG_NAME:"广州"},
			{ORG_ID:52, SUPER_ORG_ID:5, ORG_NAME:"深圳"},
			{ORG_ID:53, SUPER_ORG_ID:5, ORG_NAME:"东莞"},
			{ORG_ID:54, SUPER_ORG_ID:5, ORG_NAME:"佛山"},
			{ORG_ID:6, SUPER_ORG_ID:0, ORG_NAME:"福建省"},
			{ORG_ID:61, SUPER_ORG_ID:6, ORG_NAME:"福州"},
			{ORG_ID:62, SUPER_ORG_ID:6, ORG_NAME:"厦门"},
			{ORG_ID:63, SUPER_ORG_ID:6, ORG_NAME:"泉州"},
			{ORG_ID:64, SUPER_ORG_ID:6, ORG_NAME:"三明"}
		 ];

		function beforeClick(treeId, treeNode) {
			var check = (treeNode && !treeNode.isParent);
			if (!check) alert("只能选择城市...");
			return check;
		}
		
		function onClick(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes(),
			v = "";
			nodes.sort(function compare(a,b){return a.id-b.id;});
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].ORG_NAME + ",";
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);
			var cityObj = $("#citySel");
			cityObj.attr("value", v);
		}

		function showMenu() {
			var cityObj = $("#citySel");
			var cityPosition = $("#citySel").position();
			$("#menuContent").css({"left":cityPosition.left + "px", "top":cityPosition.top + cityObj.outerHeight() + "px","position":"absolute"}).slideDown("fast");

			$("body").bind("mousedown", onBodyDown);
		}
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}
		
		/* function ajaxDataFilter(treeId, parentNode, responseData){
			alert(responseData);
			if (responseData) {
		      for(var i =0; i < responseData.length; i++) {
		        responseData[i].name += "_filter";
		      }
		    }
		    return responseData;
		} */
		
		$.ajax({
			type: "POST",
			url: "docum!queryOrganizationInfos.action",
			cache: false,
			data: null,
			success: function(msg){
				var data=eval("("+msg+")");
				$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			}
		});
</script>