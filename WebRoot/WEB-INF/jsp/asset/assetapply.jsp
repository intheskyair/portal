<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head> 
  <%@ include file="/common/meta.jsp"%>
    <title>资产领用申请</title>
    
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/My97DatePicker/skin/WdatePicker.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css"/>
    <link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
     <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquery-ui-jquiGui.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquiGui.css"/>
    <script src="${ctx}/views/common/js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/json2.js"></script>
	<script type="text/javascript" src="${ctx}/views/common/js/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
		#add{
			display:block;
			width: 20px;
			height: 18px;
			background:url(./views/common/css/themes/css/flexigrid/images/icon/add.png) no-repeat center left;
		}
	</style>
	<script type="text/javascript">
				var assetapply_user = {
					changeAuth:function(){
			        var dlg_auth_distribute = $("#dlg_auth_distribute").dialog({
				        position: ['center','top'],
        				draggable :false,     
						autoOpen: false,
						height:"400",
						width:"500",
						show: "slide",
						modal:true,
						cache:false,
						/* hide: "explode", */
						title:'下一步审批人',
						buttons:{
							"确定":function(){
								 //提交按钮背景设为灰色，改为不可点击状态
								$("#submit").attr("style", "background-color:gray");
								 $("#submit").attr("disabled", true);
								assetapply_user.saveAuth();
							}
						}
					});
					
			        $.ajax({
			            type:"get",
			            dataType:"text",
			            async:false,
			            url:"queryUser!getUserList.action?assignee=${map.assignee}",
			            success:function(result) {
			            $("#username_list1").html("");
			            $("#username_list2").html("");
			            	var data=eval("("+result+")");
							var html = '';
							var html2 = '';
							var leftValue = $("#userList").val();
							if(leftValue.length == 0){
								for (var i in data) {
									html += '<option value="'+data[i].USERNAME+'">'+data[i].TRUENAME+'</option>';
								}
								$("#username_list1").append(html);
							}else{
								var leftVals = leftValue.split(",");
								for (var i in data) {
								var flag = false;
									for(var j in leftVals){
										if(data[i].TRUENAME == leftVals[j]){
										flag = true;
											html2 += '<option value="'+data[i].USERNAME+'">'+data[i].TRUENAME+'</option>'; 
										}
									}
									if(!flag){
										html += '<option value="'+data[i].USERNAME+'">'+data[i].TRUENAME+'</option>';
									}
								}
								$("#username_list1").append(html);
								$("#username_list2").append(html2);
							}
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
				$("#userList").val(admin_id);
				$('#dlg_auth_distribute').dialog('close');
				 $.ajax({
		        	url:"assetapply!process.action",
		        	type:"POST",
		        	cache: false,
		    		data:$("#inputForm").serialize(),
		    		success: function(msg)
		    		{
		    			if("success" === msg){
		    				alert("提交成功");
		    				bakIframe("${map.label}","${map.bakurl}");
		    			}
		    		}     	
        		});   
			}
	};
	
	
	
    function choosesub(){
    	var id=$.trim($("#asset_id").val());
    	var user=$.trim($("#asset_user").val());
    	var applytime=$.trim($("#asset_applytime").val());
    	var status=$.trim($("#asset_status").val());
    	if(id==""){
    	showMsg("资产具体编号没有正确填写！");
    	return;
    	}
    	else
    	if(user==""){
    		showMsg("申请人没有正确填写！");
    		return;
    	}else
    	if(applytime==""){
    		showMsg("申请时间没有正确填写！");
    		return;
    	}else
    	if(status=="领用申请中"){
    		showMsg("您申请的资产正在被别人申请，请重新选择！");
    		return;
    	}
    	else if(status=="使用中"){
	    	showMsg("您申请的资产正在被人使用，请重新选择！");
	    	return;
    	}
    	else if($("span[class='LV_invalid']").length != 0){
			return;
    	}else{
			assetapply_user.changeAuth();
    	}
    }
    	

   
   
     $(function(){
    	 $("#apply_process_sig").hide();
    	$("#time").val(formateDate(new Date()));
    	$("#asset_id").val('${map.asset_id}');
		$("#asset_type").val('${map.asset_type}');
		$("#asset_name").val('${map.asset_name}');
		$("#asset_code").val('${map.asset_code}');
		$("#asset_brand").val('${map.asset_brand}');
		
		$("#asset_applytime").val('${map.asset_applytime}');
		$("#asset_remark").val('${map.asset_remark}');
						switch('${map.asset_status}'){
		    				case "1":
		    				$("#asset_status").val("可申请领用");
		    				break;
		    				case "2":
		    				$("#asset_status").val("领用申请中");
		    				break;
		    				case "3":
		    				$("#asset_status").val("使用中");
		    				break;
		    			}
				$.ajax({
			     		url:"assetapply!getAdminName.action",
			     		type:"POST",
			        	cache: false,
			    		success: function(result){
			    			var name=eval('('+result+')');
			    			$("#asset_user").val(name);
			    		}   
			     	});
		
		
    			$("#bt").hide();
    			
    			if("${map.orderId}"!=null&&"${map.orderId}"!=""){
    			
	    			$.ajax({
			     		url:"borrow!view.action?orderId=${map.orderId}&path=${map.path}",
			     		type:"POST",
			        	cache: false,
			    		success: function(result){
			    			$("#view").html(result);
			    				//隐藏显示表单框
							$("#view").hide();
			    		}   
			     	});
    				$("#bt").show();
    			}
    }); 
    			
    
    function getassetdetailinfo(){
			$.ajax({
		        	url:"assetapply!getAssetDetailInfoById.action?Id="+$.trim($("#asset_id").val()),
		        	type:"POST",
		        	async:false,
		        	cache: false,
		    		success: function(result)
		    		{
		    		try{
		    			var  res = eval("("+result+")");
		    			$("#asset_code").val(res[0].CODE);
		    			$("#asset_name").val(res[0].ASSETNAME);
		    			$("#asset_type").val(res[0].ASSETTYPE);
		    			$("#asset_brand").val(res[0].BRAND);
		    			switch(res[0].STATUS){
		    				case "1":
		    				$("#asset_status").val("可申请领用");
		    				break;
		    				case "2":
		    				$("#asset_status").val("领用申请中");
		    				showMsg("您申请的资产正在被别人申请，请重新选择！");
		    				$("#asset_id").focus();
		    				break;
		    				case "3":
		    				$("#asset_status").val("使用中");
		    				showMsg("您申请的资产正在被人使用，请重新选择！");
		    				$("#asset_id").focus();
		    				break;
		    			}
					}catch(e){
						
						showMsg("您输入的编号不正确！");
					}
		    		}
        		}); 
        		if($("#asset_id").val().trim()==""||$("#asset_id").val()==null||$("#asset_name").val().trim()==""||$("#asset_name").val()==null){
        				$("#asset_code").val("");
		    			$("#asset_name").val("");
		    			$("#asset_type").val("");
		    			$("#asset_brand").val("");
		    			$("#asset_status").val("");
        				showMsg("您输入的编号不正确！");
        		}
		
	}
	
      
    
    
    function moveOption(list1,list2){
			 var options = list1.options;
			 for(var i=0;i<options.length;i++){
				 if(options[i].selected){
					 list2.appendChild(options[i]);
					 i--;
				 }
			 }
	} 
	
	function show(){ 
		$("#view").show(); 
		$("#view").addClass("from-below"); 
		setTimeout(function(){$("#view").addClass("effeckt-show");},300); 
	}	
</script>	
  </head>
  <body>
   <form id="inputForm" action="" method="post" target="mainFrame">
    <input type="hidden" id="title" name="title" value="资产领用申请" />
    <input type="hidden" id="processId" name="processId" value="${map.processId}" />
	<input type="hidden" id="orderId" name="orderId" value="${map.orderId}"/>
	<input type="hidden" id="taskId" name="taskId" value="${map.taskId}" />
	<!-- 当前流程名称 -->
	<input type="hidden" id="taskName" name="taskName" value="${map.taskName}" />
	<input type="hidden" id="time" name="time"/>
	<!-- 下步流程权限ID -->
	<input type="hidden" id="assignee" name="assignee" value="${map.assignee}"/>
	<input type="hidden" name="userList" id="userList"/>
	
	
	
<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px">
				<tr>
					<td class="td_table_1"><span>资产具体编号：</span></td>
					<td class="td_table_2" style="width:35%">
						<input type="text" class="input_240" id="asset_id" name="asset_id" value="${map.asset_id}" onchange="getassetdetailinfo()"/>
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
					<td class="td_table_1"><span>资产编号：</span></td>
					<td class="td_table_2" style="width:35%">
					  <input type="text" readonly class="input_240" id="asset_code"  name="asset_code" value="${map.asset_code}"  />
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
				</tr>
				<tr>	
					<td class="td_table_1"><span>资产名称：</span></td>
					<td class="td_table_2">
						<input type="text" readonly class="input_240" id="asset_name"  name="asset_name" value="${map.asset_name}"  />
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
					<td class="td_table_1"><span>资产分类：</span></td>
					<td class="td_table_2">
						<input type="text" readonly class="input_240" id="asset_type"  name="asset_type" value="${map.asset_type}"  />
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
					
				</tr>
				<tr>
					<td class="td_table_1"><span>品牌：</span></td>
					<td class="td_table_2">
						<input type="text" readonly class="input_240" id="asset_brand"  name="asset_brand" value="${map.asset_brand}"  />
						<span class="LV_invalid none" style="color: red;"></span>
					</td>	
					<td class="td_table_1"><span>状态：</span></td>
					<td class="td_table_2">
						<input type="text" readonly class="input_240" id="asset_status"  name="asset_status" value="${map.asset_status}"  />
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
					
				</tr>
				<tr>
		            
					<td class="td_table_1"><span>申请人：</span></td>
					<td class="td_table_2">
						<input type="text"  readonly="true" class="input_240" id="asset_user" name="asset_user" value="${map.asset_user}" />
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
					<td class="td_table_1"><span>申请时间：</span></td>
					<td class="td_table_2" style="width: 100px; height: 26px;">
						<input type="text" id="asset_applytime" name="asset_applytime"  class="WdateFlow" style="width: 200px;" value="${map.asset_applytime}" 
						 onFocus="WdatePicker({readOnly:true})" />
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
				</tr>
				<tr>
					<td class="td_table_1"><span>备注：</span></td>
					<td class="td_table_2" colspan="3">
						<textarea class="input_textarea_320" id="asset_remark" name="asset_remark" value="${map.asset_remark}">${map.asset_remark}</textarea>
					</td>
							
				</tr>
			</table>
		</form>		
<table align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr align="center">
					<td colspan="1" class="h50">
						<input type="button" class="selectBtn" style="" id="submit" value="提交" onclick="choosesub();"/>
						&nbsp;&nbsp;
						<input type="button" class="selectBtn" name="reback" value="返回"
							onclick="bakIframe('${map.label}','${map.bakurl}')">
							<input type="button" id="bt" class="selectBtn" value="查看" onClick="show()">
							
							&nbsp;&nbsp;	
						<input type="button" class="selectBtn"  value="查看流程图"
							onclick="showProcess_sig('apply_process_sig','流程查看')">	
					</td>
				</tr>
			</table>

<div id="view"></div>
<div id="apply_process_sig" >
	<iframe width=620 height=410 frameborder=0 src="${ctx}/process/designer?processId=${map.processId}&status=0&orderId=${map.orderId}&orderIdStatus=0"></iframe>
</div>
	<div id="dlg_auth_distribute" style="display:none">
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
						<input style="width:50px" type ="button" value ="添加" onclick="moveOption(document.authchoose.username_list1,document.authchoose.username_list2);"><br/><br/>
						<input style="width:50px" type ="button" value ="移除" onclick="moveOption(document.authchoose.username_list2,document.authchoose.username_list1);"><br/>
					</td>
					<td >
						<select multiple name="username_list2" id="username_list2" class="wi-sel-1" style="width:150px;height:260px" >
						</select>
					</td>
				</tr>
					
			</table>
	</form>
	</div>


  </body>
</html>
