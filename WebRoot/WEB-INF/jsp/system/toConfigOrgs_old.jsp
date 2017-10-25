<%-- <%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
 <%@ page isELIgnored="false"%>
<HTML>
<HEAD>
<TITLE>公司组织架构</TITLE>
	<SCRIPT type="text/javascript" src="${path}js/jquery/jquerylast.js"></SCRIPT>
	<script type="text/javascript" src="${path}js/jquery/jquery.easyui.min.js"></script>
	<SCRIPT type="text/javascript" src="${path}js/jquery/jquerylayout.js"></SCRIPT>
	<link rel="stylesheet" href="${path}css/demo.css" type="text/css">
	<link rel="stylesheet" href="${path}css/zTreeStyle/zTreeStyle.css" type="text/css">
	<link href="${path}css/mainright.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${path}js/jquery/jquery.ztree.core-3.1.js"></script>
	<script type="text/javascript" src="${path}js/jquery/jquery.ztree.exedit-3.1.js"></script>
	<link rel="stylesheet" type="text/css" href="${path}js/jquery/easyui.css">
	<link rel="stylesheet" type="text/css" href="${path}js/jquery/icon.css">	
	<link rel="stylesheet" href="jqui/themes/base/jquery.ui.all.css">
	<script src="${path}js/jquery/jqui/external/jquery.bgiframe-2.1.2.js"></script> 
	<script src="${path}js/jquery/jqui/ui/jquery.ui.core.js"></script>
	<script src="${path}js/jquery/jqui/ui/jquery.ui.widget.js"></script>
	<script src="${path}js/jquery/jqui/ui/jquery.ui.mouse.js"></script>
	<script src="${path}js/jquery/jqui/ui/jquery.ui.draggable.js"></script>
	<script src="${path}js/jquery/jqui/ui/jquery.ui.position.js"></script>
	<script src="${path}js/jquery/jqui/ui/jquery.ui.resizable.js"></script>
	<script src="${path}js/jquery/jqui/ui/jquery.ui.dialog.js"></script>
	<link rel="stylesheet" href="${path}js/jquery/jqui/demos/demos.css">	
		<link rel="stylesheet" href="${path}css/demo.css" type="text/css">
	
	<SCRIPT type="text/javascript">
		/* 当前组织ID */
		var curr_org_id;
		/* ROOT节点 */
		var rootNode;
		/* 当前右击的节点 */
		var current_node;
		
		function showRMenu(type, x, y) {
			$("#rMenu ul").show();
			if (type=="root") {
				$("#m_add").show();
				$("#m_del").hide();
			}else if(type=="root2"){
				$("#m_add").show();
				$("#m_del").show();
			}else{
				$("#m_add").hide();
				$("#m_del").show();
			}
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

			$("body").bind("mousedown", onBodyMouseDown);
		}
		function hideRMenu() {
			if (rMenu) rMenu.css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDown);
		}
		function onBodyMouseDown(event){
			if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
				rMenu.css({"visibility" : "hidden"});
			}
		}
		var addCount = 1;
		function addTreeNode() 
		{
			hideRMenu();
			$("div[id^='addNodePanel']").hide();
			
			/* 清空模糊查询输入框 */
			$("#org_name").val("");
			/* 获取根节点 */
			rootNode=zTree.getSelectedNodes()[0];
			/* 如果组织级别为班组，则提供两种添加方式 */
			//if(rootNode.orgtype=="2"){
			//	$("#addNodePanel2").show();
			//}

				//$("#addNodePanel").show();
				//$("#gobackBtn").hide();
				$("#addNodePanel4").show();
			
			//alert(rootNode.orgtype+"\t"+rootNode.name);
			/* 将组织类型写入表单，用于查询提交 */
			$("#org_type").val(rootNode.orgtype);			
			var super_org_name=current_node.name;
			$("#panel4_super_org_name").html(super_org_name);
			$("#panel4_org_name").val("");
			/*
			var people_count=$("#panel4_org_people").val();
			var org_jifen=$("#panel4_org_jifen").val();
			
			if($("#sts_org_count").is(":visible")){
				var pattern=/^[0-9]*[1-9][0-9]*$/;
				var flag=false;
				flag = pattern.test(people_count);
				if(flag==false){
					alert("组织人数必须是数字");
					return false;
				}
			}*/

			
			

									
			//zTree.addNodes(zTree.getSelectedNodes()[0], [{id:1111111, pId:1, name:"增加" + (addCount++)}]);
		}
		
		function toNextAddOrg(){
			var radioVal="";
			$("#addNodePanel2 input[name='addWay']").each(function(){
				if(this.checked){
					radioVal=this.value;
					return false;
				}
			});
			if(radioVal==""){
				alert("请选择一个录入方式");
				return false;
			}else if(radioVal=="czportal"){
				$("div[id^='addNodePanel']").hide();
				$("#addNodePanel").show();
				$("#gobackBtn").show();
			}else if(radioVal=="my_input"){
				$("div[id^='addNodePanel']").hide();
				$("#addNodePanel3").show();
			}else if(radioVal=="yyt_input"){
				$("div[id^='addNodePanel']").hide();
				showSubordinateOrgs();
			}
		}
		
		function gobackPrevious(){
			$("div[id^='addNodePanel']").hide();
			$("#addNodePanel2").show();
		}
		
		/* 显示该组织在综合平台的全部下属组织 */
		function showSubordinateOrgs(){
			var params=new Object();
			params.org_id=current_node.id;
			var html="";
			html+="	<TABLE class=mobile cellSpacing=0 cellPadding=0 width=\"100%\" border=0>";
			html+="		<TBODY>";
			html+="		<TR>";
			html+="		<TH> "+current_node.name+" 综合平台下属营业厅 </TH>";
			html+="		</TR>";
			html+="		</TBODY>";
			html+="	</TABLE><BR>";
			html+="	<DIV id=orgList>";
			html+="		<TABLE class=mobile cellPadding=0>";
			html+="			<THEAD>";
			html+="				<TR>";
			html+="				<TH>&nbsp;</TH>";
			html+="				<TH>组织ID</TH>";
			html+="				<TH>组织名称</TH></TR>";
			html+="			</THEAD>";
			html+="			<TBODY>";
			$.ajax({
			   type: "POST",
			   url: "configOrgs_getSubordinateOrgs.do",
			   cache: false,
			   data: params,
			   success: function(msg){
			   	   var result=eval("("+msg+")");
			       var list=result.result;
			       for(var i=0;i<list.length;i++){
			           var tmp=list[i];
			           var org_id=tmp.ORG_ID;
			           var org_name=tmp.ORG_NAME;
					   html+="<TR class=odd>";
					   html+=" <TD><INPUT type=radio value=\""+org_id+" _ "+org_name+"\" name=org_id></TD>";
					   html+=" <TD>"+org_id+" </TD>";
					   html+=" <TD>"+org_name+"</TD>";
					   html+="</TR>";
			       }
				   html+="<TR class=odd>";
				   html+="<TD colSpan=3>";
				   html+="	<CENTER><INPUT onclick=addOrg(); type=button value=添加><input type=\"button\" value=\"返回\" onclick=\"gobackPrevious();\" /></CENTER></TD>";
				   html+="	</TR>";
				   html+="</TBODY>";
				   html+="</TABLE>";
				   html+="</DIV>";
			       $("#addNodePanel5").html(html);
			       $("#addNodePanel5").show();
			   }
			}); 
		}
		
		
		
		function addBanzu(){
			var org_name=$("#addNodePanel3 input[name='banzu_name']").val();
			var org_peole_count=$("#addNodePanel3 input[name='banzu_people_count']").val();
			var org_jifen=$("#addNodePanel3 input[name='banzu_jifen']").val();
			if($.trim(org_name)==""){
				alert("请输入班组名称");
				return false;
			}
			var pattern=/^[0-9]*[1-9][0-9]*$/;
			var flag=false;
			flag = pattern.test(org_peole_count);
			if(flag==false){
				alert("组织人数必须是数字");
				return false;
			}

			
			var super_org_id=current_node.id;
			var super_org_name=current_node.name;
			var org_type=parseInt(current_node.orgtype)-1;
			
			var params=new Object();
			params.org_name=org_name;
			params.super_org_id=super_org_id;
			params.super_org_name=super_org_name;
			params.org_type=org_type;
			params.org_peole_count=org_peole_count;
			params.org_jifen=org_jifen;
			
			
			$.ajax({
			   type: "POST",
			   url: "configOrgs_addOrg2.do",
			   cache: false,
			   data: params,
			   success: function(msg){
			   	   var result=eval("("+msg+")");
			       if(result.msg=="ok"){
				       zTree.addNodes(current_node, [{id:result.orgid, pId:super_org_id, name:org_name,orgtype:org_type}]);
				       $("#addNodePanel3 input[name='banzu_name']").val("");
					   $("#addNodePanel3 input[name='banzu_people_count']").val("0");
					   $("#addNodePanel3 input[name='banzu_jifen']").val("0");
				       alert("新增工会积分系统组织成功！");
			       }else{
			           alert(result.msg);
			       }
			   }
			}); 
		}
		
		
		function editTreeNode(){
			hideRMenu();
			var nodes = zTree.getSelectedNodes();
			nodes[0].name="增加111";
			nodes[0].id="22222";
			
			zTree.updateNode(nodes[0]);
		}
		function removeTreeNode() 
		{
			hideRMenu();
			//current_node
			//org_id
			var params=new Object();
			params.org_id=current_node.id;
			
			if (current_node) {
				if(confirm("确认删除"+current_node.name+",及其下属组织?")){
					$.ajax({
					   type: "POST",
					   url: " org!deleteOrg.action",
					   cache: false,
					   data: params,
					   success: function(msg)
					   {
					       if($.trim(msg)=="ok")
					       {
						       zTree.removeNode(current_node);
						       alert("删除组织成功！");
					       }else
					       {
					           alert(msg);
					       }
					   }
					}); 
				}

			}
		}
		function checkTreeNode(checked) 
		{
			var nodes = zTree.getSelectedNodes();
			if (nodes && nodes.length>0) {

				zTree.checkNode(nodes[0], checked, true);
			}
			hideRMenu();
		}
		function resetTree() {
			hideRMenu();
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		}
		
		function modifyPeopleCount(){
			var params=new Object();
			var zhixingFlag=1;
			//var str="";
			$("#addNodePanel6 input").each(function(){
				var attrName=$(this).attr("name");
				var attrVal=$(this).val();
				
				if(attrName=="banzu_people_count"){
					var pattern=/^[0-9]*[1-9][0-9]*$/;
					var flag=false;
					flag = pattern.test(attrVal);
					if($.trim(attrVal)=="0"){
					    flag=true;
					}
					if(flag==false){
						alert("组织人数必须是数字");
						zhixingFlag=0;
						return false;
					}
				}
				params[attrName]=attrVal;
				//str+=attrName+":"+attrVal+"\n";
			});
			if(zhixingFlag!=1){
			    return false;
			}
			
			$.ajax({
			   type: "POST",
			   url: "configOrgs_updatePeopleCount.do",
			   cache: false,
			   data: params,
			   success: function(msg){
			       if($.trim(msg)=="ok"){
			           alert("修改成功");
			       }else{
			           alert(msg);
			       }
			   }
			}); 
		}
		
		
		//节点，点击事件
		function toModifyPeopleCount(org_id,org_name){
		    $("div[id^='addNodePanel']").hide();
		    var html="";
		    
		    /*var params=new Object();
		    params.org_id=org_id;
		    
			$.ajax({
			   type: "POST",
			   url: "configOrgs_getOrgInfo.do",
			   cache: false,
			   data: params,
			   success: function(msg){
			        var result=eval("("+msg+")");
			        var list=result.result;
			        var map=list[0];
			    */    
					html+="<TABLE class=mobile cellSpacing=0 cellPadding=0 width=\"100%\" border=0>";
					html+="	<TBODY>";
					html+="		<TR>";
					html+="			<TH>组织名称： </TH>";
					html+="			<TD align=middle>"+org_name+"</TD>";
					html+="			</TR>";
					html+="			<TR>";
					html+="			<TH>组织人数： </TH>";
					html+="			<TD align=middle>";

					html+="<INPUT value=\"0\" name=banzu_people_count>";
					//}
					html+="<input type=hidden name=org_id value=\""+org_id+"\" />";
					html+=" </TD>";
					html+=" </TR>";
					/*
					if(map.ORG_TYPE=="1")
					{
						html+="		<TR>";
						html+="			<TD colSpan=2>";
						html+="			<INPUT onclick=modifyPeopleCount(); type=button value=提交>";
						html+="			</TD>";
						html+="		</TR>";
					}
                   */
					html+="	</TBODY>";
					html+="</TABLE>";
					$("#addNodePanel6").html(html);
					$("#addNodePanel6").show();
			  // }
			//}); 
		}
		
		

		var zTree, rMenu;
		
		var setting = {
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			check: {
				enable: true
			},
			callback:{
				onClick:function(event, treeId, treeNode)
				{
					toModifyPeopleCount(treeNode.id,treeNode.name);
				},
				onRightClick:function(event, treeId, treeNode) {
					//alert(treeNode.orgtype);
					/* 修改当前节点为右击节点 */
					current_node=treeNode;
					$("div[id^='addNodePanel']").hide();
					$("#orgList").html("");
					var orgTypeNum=parseInt(treeNode.orgtype);
					/* 如果是根目录 */
					if(orgTypeNum==4){
						zTree.selectNode(treeNode);
						showRMenu("root", event.clientX, event.clientY);
					}else if(orgTypeNum>1){
						/* 如果组织类型在班组级别之上 */
						zTree.selectNode(treeNode);
						showRMenu("root2", event.clientX, event.clientY);
					}else{
						/* 如果组织类型是班组级别 */
						zTree.selectNode(treeNode);
						showRMenu("node", event.clientX, event.clientY);
					}
					/*
					if(treeNode.id && treeNode.id==1){
						zTree.selectNode(treeNode);
						showRMenu("root", event.clientX, event.clientY);
					}else if (treeNode && !treeNode.noR) {
						zTree.selectNode(treeNode);
						showRMenu("node", event.clientX, event.clientY);
					}*/
				}
			}
		};

		var zNodes =[
			${orgJson}
		];



		$(document).ready(function(){
			/* 布局 */
			$('body').layout({
				resizable:true,
				applyDefaultStyles: true,
				west__resizable: true,
				west__size: 230
			});
			/* 生成树 */
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			zTree = $.fn.zTree.getZTreeObj("treeDemo");
			rMenu = $("#rMenu");
			
			/* 动态表格 */
			
			
			
		});

		function queryOrgForAdd(){
			var org_name=$("#org_name").val();
			var org_type=parseInt($("#org_type").val())-1;
			
			if($.trim(org_name)==""){
				alert("请输入组织名称");
				return false;
			}
			
			
			var params=new Object();
			params.org_name=org_name;
			params.org_type=org_type;
			$.ajax({
			   type: "POST",
			   url: "configOrgs_queryCzportalOrgs.do",
			   cache: false,
			   data: params,
			   success: function(msg){
			        var org_list=eval("("+msg+")");
			        var list=org_list.result;
			        var html="";
					html+="<TABLE class=mobile cellPadding=0><THEAD>";
					html+="<TR>";
					html+="<TH>&nbsp;</TH>";
					html+="<TH>组织ID</TH>";
					html+="<TH>组织名称</TH>";
					html+="</TR></THEAD>";
					html+="<TBODY>";
					for(var i=0;i<list.length;i++){
						var tmp=list[i];
						var org_id=tmp.ORG_ID;
						var org_name=tmp.ORG_NAME;
						
						html+="<TR class=odd>";
						html+="<TD><input type=\"radio\" name=\"org_id\" value=\""+org_id+" _ "+org_name+"\" /></TD>";
						html+="<TD>"+org_id+" </TD>";
						html+="<TD>"+org_name+"</TD>";
						html+="</TR>";
					}
					html+="<TR class=odd>";
					html+="<TD colspan=\"3\"><CENTER><input type=\"button\" value=\"添加\" onclick=\"addOrg();\" /></CENTER></TD>";
					html+="</TR>";
					html+="</TBODY></TABLE>";
					$("#orgList").html(html);
			   }
			}); 
		}
		
		var obj_params;
		function addOrg(){
			var list=$("#orgList input[name='org_id']");
			var org_id_name;
			var flag=0;
			for(var i=0;i<list.length;i++){
				var tmp=list.get(i);
				if(tmp.checked){
					org_id_name=$(tmp).val();
					flag=1;
				}
			}
			if(flag==0){
				alert("请选择一个组织！");
				return false;
			}
			var orgArr=org_id_name.split("_");
			var org_id=$.trim(orgArr[0]);
			var org_name=$.trim(orgArr[1]);
			var super_org_id=current_node.id;
			var super_org_name=current_node.name;
			var org_type=parseInt(current_node.orgtype)-1;
			
			var params=new Object();
			params.org_id=org_id;
			params.org_name=org_name;
			params.super_org_id=super_org_id;
			params.super_org_name=super_org_name;
			params.org_type=org_type;
			
			$("#panel4_super_org_name").html(params.super_org_name);
			$("#panel4_org_name").html(params.org_name);
			$("#panel4_org_people").val("0");
			$("#panel4_org_jifen").val("0");
			
			if(params.org_type==1){
			    $("#sts_org_count").show();
			}else{
			    $("#sts_org_count").hide();
			}
			$("#addNodePanel4").css("display","block");
	        $("#addNodePanel4").dialog({
	        	width:400,
	        	height:200,
	        	title:"添加积分系统组织"
	        });
			obj_params=params;
		}
		
		
		function addOrgSubmit(){
			//obj_params
			/*
			var people_count=$("#panel4_org_people").val();
			var org_jifen=$("#panel4_org_jifen").val();
			
			if($("#sts_org_count").is(":visible")){
				var pattern=/^[0-9]*[1-9][0-9]*$/;
				var flag=false;
				flag = pattern.test(people_count);
				if(flag==false){
					alert("组织人数必须是数字");
					return false;
				}
			}

			obj_params.people_count=people_count;
			obj_params.org_jifen=org_jifen;
			
			
			$.ajax({
			   type: "POST",
			   url: "configOrgs_addOrg.do",
			   cache: false,
			   data: obj_params,
			   success: function(msg){
			       if($.trim(msg)=="ok"){
				       zTree.addNodes(current_node, [{id:obj_params.org_id, pId:obj_params.super_org_id, name:obj_params.org_name,orgtype:obj_params.org_type}]);
				       alert("新增工会积分系统组织成功！");
				       $("#addNodePanel4").dialog("close");
			       }else{
			           alert(msg);
			       }
			   }
			}); */
			
			var super_org_id=current_node.id;
			var super_org_name=current_node.name;
			var org_type=parseInt(current_node.orgtype)-1;
			var org_name=$("#panel4_org_name").val();		
			var params=new Object();
			params.org_name=org_name;
			params.super_org_id=super_org_id;
			params.super_org_name=super_org_name;
			params.org_type=org_type;
			if(confirm("确认新增"+org_name+",这个组织?"))
			{
				   $.ajax({
				   type: "POST",
				   url: "org!addOrg.action",
				   cache: false,
				   data: params,
				   success: function(msg){
				       var result=eval("("+msg+")");
				       if(result.msg=="ok")
				       {
					       zTree.addNodes(current_node, [{id:result.orgid, pId:params.super_org_id, name:params.org_name,orgtype:params.org_type}]);
					       alert("新增组织成功！");
					      // $("#addNodePanel4").dialog("close");
					      $("div[id^='addNodePanel']").hide();
				       }else{
				           alert(result.msg);
				       }
				   }
				}); 
			}
		}




	</SCRIPT>
	<style type="text/css">
		div#rMenu {position:absolute; visibility:hidden;background-color: #555;text-align: left;padding: 2px;}
		div#rMenu ul li{
			margin: 1px 0;
			padding: 0 5px;
			cursor: pointer;
			list-style: none outside none;
			background-color: #DFDFDF;
		}
	</style>
</HEAD>
<BODY>
<DIV class="ui-layout-center">
	<div>
		<p style="font-size: large;">
			请右击左侧组织树，进行操作。
		</p>
	</div>
	<br/>
	
	
	<div id="addNodePanel6" style="display: none;">
		<table  width="100%" border="0" cellpadding="0" cellspacing="0" class="mobile">
			<tr>
				<th>
					班组名称：
				</th>
				<td align="center">
					
				</td>
			</tr>
			<tr>
				<th>
					组织人数：
				</th>
				<td align="center">
					<input type="text" name="banzu_people_count" value="0" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="修改人数" onclick="modifyPeopleCount();"/>
				</td>
			</tr>
		</table>
	</div>
	
	
	<div id="addNodePanel5" style="display: none;">
		
	</div>
	
	
	<!--  -->
	<div id="addNodePanel4" style="display: none;">
		<table  width="100%" border="0" cellpadding="0" cellspacing="0" class="mobile">
			<tr>
				<th>
					上级组织：
				</th>
				<td align="center" id="panel4_super_org_name">
					
				</td>
			</tr>
			<tr>
				<th>
					组织名称：
				</th>
				<td align="center">
					<input type="text" id="panel4_org_name" value="" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="提交" onclick="addOrgSubmit();"/>
				</td>
			</tr>
		</table>
	</div>
	
	
	
	
	
	<div id="addNodePanel3" style="display: none;">
		<table  width="100%" border="0" cellpadding="0" cellspacing="0" class="mobile">
			<tr>
				<th>
					班组名称：
				</th>
				<td align="center">
					<input type="text" name="banzu_name" value="" />
				</td>
			</tr>
			<tr>
				<th>
					组织人数：
				</th>
				<td align="center">
					<input type="text" name="banzu_people_count" value="0" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="提交" onclick="addBanzu();"/>
					<input type="button" value="返回" onclick="gobackPrevious();" />
				</td>
			</tr>
		</table>
	</div>
		
	<div id="addNodePanel" style="display: none;">
		 <table  width="100%" border="0" cellpadding="0" cellspacing="0" class="mobile">
		 	<tr>
		 		<th>组织名称：</th>
		 		<td>
		 			<input type="text" id="org_name" name="org_name" value=""/>
		 			<input type="hidden" id="org_type" name="org_type" value="" />
		 		</td>
		 		<td>
					<input type="button" style="width: 100px;" value="查询" onclick="queryOrgForAdd();"/>
					<input type="button" id="gobackBtn" style="width: 100px;" value="返回" onclick="gobackPrevious();" />
				</td>
		 	</tr>
		 </table><br/>
		 <div id="orgList">
		 	
		 </div>
	</div>

</DIV>
<!--<DIV class="ui-layout-north">North</DIV>-->
<!--<DIV class="ui-layout-south">South</DIV>-->
<!--<DIV class="ui-layout-east">East</DIV>-->
<DIV class="ui-layout-west">
	<ul id="treeDemo" class="ztree" style="width: 100%;height: 100%;overflow: auto;"></ul>
</DIV>
<div id="rMenu">
	<ul>
		<li id="m_add" onclick="addTreeNode();">新增组织</li>
		<li id="m_del" onclick="removeTreeNode();">删除该组织</li>
<!--		<li id="m_check" onclick="checkTreeNode(true);">Check节点</li>-->
<!--		<li id="m_unCheck" onclick="checkTreeNode(false);">unCheck节点</li>-->
<!--		<li id="m_reset" onclick="resetTree();">恢复zTree</li>-->
	</ul>
</div>
</BODY>
</HTML> --%>