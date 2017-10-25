<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" style="padding-right:10px">
	<head>
		<title>转派收文公文流程</title>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
		<%@ include file="/common/meta.jsp"%>
	<style>
.li_content{
	width: 60px;
	text-align: center;
}
fieldset{border:1px solid #9AC3E1;margin-top:10px;}
legend{font-size: 16px;color: #0071b1;font-weight: bold;}
</style>
<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
<link rel="stylesheet" href="${ctx}/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="${ctx}/js/kindeditor/themes/simple/simple.css" />
<link rel="stylesheet" href="${ctx}/css/common.css"></link>
<link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquiGui.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css" />
<link rel="stylesheet" type="text/css" href="views/common/css/themes/css/test/metroStyle/metroStyle.css" />
<link rel="stylesheet" type="text/css" href="styles/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="styles/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquiGui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquery-ui-jquiGui.css"/>
<script src="${ctx}/views/common/js/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src="styles/jquery.easyui.min.js"></script>

<script src="${ctx}/views/common/js/common.js" type="text/javascript"></script>
	</head>

	<body style="overflow: scroll;">
		<form id="inputForm" action="" method="post" target="mainFrame">
		    <input type="hidden" value="${step}" name="step" id="step">
			<input type="hidden" id="processId" name="processId" value="${processId }" />
			<input type="hidden" id="orderId" name="orderId" value="${orderId }" />
			<input type="hidden" id="taskId" name="taskId" value="${taskId }" />
			<input type="hidden" id="nextOperator" name="nextOperator">
			<input type="hidden" id="task_Name" name="task_Name"/>
			<fieldset>
             <legend>【${map.label}】</legend>
             <table align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr align="center">
					<td colspan="3" class="h50">
						<input type="button" class="selectBtn" style="width: 90px" id="traf1" name="traf1" value="转发领导批示" onclick="recdoc_user.changeAuth('rect5',41);"/>
						&nbsp;&nbsp;
						<input type="button" class="selectBtn" style="width: 90px" id="traf2" name="traf2" value="转发处室阅办" onclick="recdoc_user.changeAuth('rect7',38);"/>
						&nbsp;&nbsp;
						<input type="button" class="selectBtn" id="traf3" name="traf3" value="转发最终承办" onclick="recdoc_user.changeAuth('rect9',41);"/>
						&nbsp;&nbsp;
						<input type="button" class="selectBtn" name="reback" value="返回"
							onclick="bakIframe('${map.label}','${map.bakurl}')">
					</td>
				</tr>
			</table>	
			<!-- <table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0" style="margin-top: 0px">
				 <tr id="transferDIV" >
					<td class="td_table_1">
						<span>转派给：</span>
					</td>
					<td class="td_table_2" colspan="3" style="text-align: center;">
						<input type="hidden" id="nextOperator" name="nextOperator">
						<input type="text" id="nextOperatorName" readonly="readonly" name="nextOperatorName" class="input_520" value="" style="float: left;">		
						<div class="choice_t">			
						<a href="javascript:selectPart('departMent',depart,0,'select-body-recdocsend');" role="button">
						<span id="selectOrgBtn" class="ui-button-text choice">选择</span>
					   </a>						
					    <a href="javascript:del_html()" role="button">
							<span id="deleteOrgBtn" class="ui-button-text choice">清空</span>
						</a>
						</div>
					</td>
				</tr> 
				 <tr>
					<td class="td_table_1"><span>抄送给：</span></td>
					<td class="td_table_2" colspan="3" style="text-align: center;">
						<input type="hidden" id="ccOperator" name="ccOperator" >
						<input type="text" id="ccOperatorName" readonly="readonly" name="ccOperatorName" class="input_520" value="">
						<input type='button' class='button_100px' value='选择抄送人员' onclick="selectPart('departMent',depart,1);"/>
					</td>
				</tr> 
			</table> -->
			</fieldset>
			
							
		</form>
<!-- 		<div id="view"></div> -->
		 <div class="modal-body" id="select-body-recdocsend" style="display:none;overflow: scroll;">
		 	<div id="menuContent-recdocsend" style="">
				<ul id="treeDemo-recdocsend" class="ztree" style="margin-top:0;width:300px;background: #f0f6e4;"></ul>
			</div>		 
		 </div>
		 
		 <div id="view_recsend" style="display:none;"></div>
		<div class="outer">
					<ul class="tab">
						<li class="current" onclick="personDetails(1)" id="begin">流程详情</li>
						<li onclick="personDetails(2)">查看表单</li>					    
					</ul>
					<div class="content_y">
					     		<div id="flowTable_recsend"></div>
						    	<div id="formTable_recsend"></div>
					</div>			
		</div>
	<div><a id="msg_end" name="1" href="#1">&nbsp</a></div>		
	<div id="dlg_auth_distribute" class="easyui-dialog" closed="true">
		<form method = "post" name ="authchoose">
			<table border="0">
				<tr style="height:35px">
					<td class="wi-align-l" style="text-align:center">待分配用户：</td>
					<td class="wi-align-l" ></td>
					<td class="wi-align-l" style="text-align:center">已选择用户：</td>
				</tr>
				<tr>
					<td>
						<select multiple name="username_list1" id="username_list1" class="wi-sel-1" style="width:150px;height:260px" >
						</select>
					</td>
					<td style="width:200px;text-align:center">
						<input style="width:50px" type ="button" value ="添加" onclick="recdoc_user.moveOption(document.authchoose.username_list1,document.authchoose.username_list2);"><br/><br/>
						<input style="width:50px" type ="button" value ="移除" onclick="recdoc_user.moveOption(document.authchoose.username_list2,document.authchoose.username_list1);"><br/>
					</td>
					<td >
						<select multiple name="username_list2" id="username_list2" class="wi-sel-1" style="width:150px;height:260px" >
						</select>
					</td>
				</tr>
			</table>
		</form>
	</div>
<script type="text/javascript">
   $(function() {
         $("#view_recsend").show();
		 $("#begin").click();
	});
	function mydownload(path)
	{
		location.href='upload!download.action?&path='+ encodeURI(encodeURI(unescape(path))); 
	}
     function personDetails(com) {
			   $("#formTable_recsend").html("");
				$("#flowTable_recsend").html("");
				var orderId = "${orderId}"; 
				var pid = "${processId}";
					if(com == 2){
						url = "borrow!view.action";
						 data = {"orderId" : orderId,"processId":pid,"path":"recdocView"};
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
					
					    if(com == 1){
					    var result_str = createHtml(result);
					    $("#flowTable_recsend").append(result_str);
					    }else{
					    $("#formTable_recsend").append(result);
					    }
					    
					    $("#view_recsend .tab li").click(function()
					    {
								$("#view_recsend .tab li").removeClass("current");
								$(this).addClass("current");
								var n = $(this).index();
								$("#view_recsend .content_y ul").hide();
								$("#view_recsend .content_y ul").eq(n).show();
                           });
					}								
				});
			document.getElementById("msg_end").click(); 				
			}

    function closeIframe(){
    	$(window.parent.parent.document).find(".ui-tabs-nav > li.ui-state-active > div > span.tabs-close").click();
    }
       

  
  
  var recdoc_user = {
			changeAuth:function(rect,assignee){
			$("#task_Name").val(rect);
	        var dlg_auth_distribute = $("#dlg_auth_distribute").dialog({
	        	height:$(window).height()*0.5,
			    width:$(window).width()*0.5,
				show: "slide",
				modal:true,
				cache:false,
				title:'批量分配角色',
			    buttons : [ {
                       text : 'OK',
                       iconCls : 'icon-ok',
                       handler : function() {
                       	 $("#submit").attr("style", "background-color:gray");
  						 $("#submit").attr("disabled", true);
  						recdoc_user.saveAuth();
                       }
               }]
			});
	        $.ajax({
	            type:"get",
	            dataType:"text",
	            async:false,
	            url:"queryUser!getUserList.action?assignee="+assignee,
	            success:function(result) {
	            $("#username_list1").html("");
	            $("#username_list2").html("");
	            	var data=eval("("+result+")");
					var html = '';
					for (var i in data) {
						html += '<option value="'+data[i].USERNAME+'">'+data[i].TRUENAME+'</option>';
					}
					$("#username_list1").append(html);
	            }
	        });
	        dlg_auth_distribute.dialog("open");
	},
	saveAuth:function(){
		var admin_id ="";
		var username ="";
		if(document.authchoose.username_list2.options.length==0){
			showMsg("请选择用户！");
			 $("#submit").attr("disabled", false);
			 $("#submit").removeAttr("style");
			return;
		}
		 for(var i=0;i<document.authchoose.username_list2.options.length;i++){
			admin_id += document.authchoose.username_list2.options[i].value+",";
			username += document.authchoose.username_list2.options[i].text+",";
		}
		$('#dlg_auth_distribute').window('close', true);
		$("#nextOperator").val(admin_id);
		 $.ajax({
		       	url:"recdoc!processtransfer.action",
		       	type:"POST",
		       	cache: false,
		   	 	data:$("#inputForm").serialize(),
		   	 	success: function(msg){
			   	 	if("success" === msg)
			   	 	{
		  				alert("提交任务成功!");				   				
		  				bakIframe("${map.label}","${map.bakurl}");
			   	 	}
		   		}
		     });  
	},
	moveOption:function(list1,list2){
		 var options = list1.options;
		 for(var i=0;i<options.length;i++){
			 if(options[i].selected){
				 list2.appendChild(options[i]);
				 i--;
			 }
		 }
	},

};
 //--------------------------------------department_person-----------------------------------------------
  /*   var id_arr = new Array();
    var name_arr = new Array();
	function add_html(){
		if(type == 0){
			$("#nextOperatorName").val(name_arr);
			$("#nextOperator").val(id_arr);
		}
		if(type == 1){
			$("#ccOperatorName").val(name_arr);
			$("#ccOperator").val(id_arr);
		}
	}
	
	function del_html(){
		if(type == 0){
			$("#nextOperatorName").val("");
			$("#nextOperator").val("");
		} 
		if(type == 1){
			$("#ccOperatorName").val("");
			$("#ccOperator").val("");
		}
	}
    //ztree
		function onChooseOrg(e, treeId, treeNode) {
			var orgId = treeNode.ORG_ID;
			if(orgId.indexOf("_personId_") != -1 && treeNode.checked){
				//zTree.expandNode(treeNode);
				id_arr.push(orgId.substring(orgId.indexOf("_personId_")+"_personId_".length,orgId.length));
				name_arr.push(treeNode.ORG_NAME);
				add_html();
			}else if(orgId.indexOf("_personId_") != -1 && !treeNode.checked){
				for(var i = 0; i < name_arr.length; i++){
					if(treeNode.ORG_NAME == name_arr[i]){
						name_arr.splice(i,1);
						id_arr.splice(i,1);
					}
				}
				add_html();
			}
			
		}
		
		var setting = {
			view: {
				dblClickExpand: false,
				showLine: false
			},
			check:{
				enable: true,
				autoCheckTrigger: true,
				chkStyle: "checkbox",
				chkboxType: { "Y": "ps", "N": "ps" }
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
				onCheck: onChooseOrg
			}
		};
    var type = null;
    function depart(){
    	type = arguments[0];
    	//$("#select-body").load("./model_pc/department/department_person.jsp?math_radom="+Math.random());
    	//-------------------------------------------加载页面后初始化加载页面的内容
	//	del_html();
    	$.ajax({
			type: "POST",
			url: "docum!queryOrganizationInfos.action?math_radom="+Math.random(),
			cache: false,
			data: null,
			success: function(msg){
				var data=eval("("+msg+")");
				$.fn.zTree.init($("#treeDemo-recdocsend"), setting, data);
				
				treeObj  =$.fn.zTree.getZTreeObj("treeDemo-recdocsend");
				//var nodes = treeObj.transformToArray();
				var nodes = treeObj.getNodes();
				var nodes2;
// 				for (var i = 0; i < nodes.length; i++) { //设置节点展开
					nodes2 = nodes[0].children;
// 					console.log("i:"+i+"-nodes1.length-"+nodes.length+"-nodes2.length:"+nodes2.length);
					for (var j = 1; j < nodes2.length; j++) {
						treeObj.expandNode(nodes2[j], true, false, true);
					}
// 	            }
				
				var val = $("#nextOperatorName").val();
				if($.trim(val).length != 0){
					var array =	val.split(",");
					var node;
					for(var i = 0 ; i < array.length ; i++){
						node  =treeObj.getNodeByParam("ORG_NAME",array[i],null);
						treeObj.checkNode(node,true, true);
// 						checkParentNode(node.SUPER_ORG_ID);
					}
				}
			}
    	
		});
    }
    function checkParentNode(super_Id){
		var node  =treeObj.getNodeByParam("ORG_ID",super_Id,null);
		treeObj.checkNode(node,true, false);
		if(node.SUPER_ORG_ID != 0){
			checkParentNode(node.SUPER_ORG_ID);
		}
	} */
</script>

    
</body>
</html>
<style>
.ui-button-icon-only .ui-icon {
    left: 2%!important;

}

.ui-button-icon-only .ui-icon, .ui-button-text-icon-primary .ui-icon, .ui-button-text-icon-secondary .ui-icon, .ui-button-text-icons .ui-icon, .ui-button-icons-only .ui-icon {
    top: 2%;!important;


</style>

