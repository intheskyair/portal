<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head> 
  <%@ include file="/common/meta.jsp"%>
  <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <title>资产录入</title>
    
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/My97DatePicker/skin/WdatePicker.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css"/>
    <link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
     <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquery-ui-jquiGui.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquiGui.css"/>
    <script src="${ctx}/views/common/js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/common.js"></script>
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
				var leave_user = {
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
								$("#submit").attr("style", "background-color:gray");
								 $("#submit").attr("disabled", true);
								leave_user.saveAuth();
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
		        	url:"assetmanager!process.action",
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
    	var name=$.trim($("#asset_name").val());
    	var type=$.trim($("#asset_type").val());
    	var brand=$.trim($("#asset_brand").val());
    	var price=$.trim($("#asset_price").val());
    	var total=$.trim($("#asset_total").val());
    	var purchaser=$.trim($("#asset_purchaser").val());
    	var purchasetime=$.trim($("#asset_purchasetime").val());
    	var reg = /^[0-9]*$/;
    	if(name==""||type==""||brand==""||price=="" ||total=="" ||purchaser=="" || purchasetime=="" ){
    	showMsg("数据没有填写完整或填写错误！");
    	}else{
    		
    		if(reg.test(price) == false||reg.test(total) == false){
				 	showMsg("单价和数量必须是数字！");
						return;
           		  }else
	    	if(($("span[class='LV_invalid']").length != 0)){
				return;
			}
			leave_user.changeAuth();
    	}
    }
    	
 /*    function getAssetNameAndCode(){
        			 
          } */
   
   
     $(function(){
    	 $("#input_process_sig").hide();
    	$("#time").val(formateDate(new Date()));
        $("#qtype").val("${map.qtype}");
        if("${map.orderId}" == '')
        {
        	$("#view").hide();
        }		
     	$("#asset_name").append("<option value=''>请选择</option>")	
			$("#asset_type").val(${map.asset_type}); 
			
			
        	if($("#asset_code").val()!=null){
        		$.ajax({
		        	url:"assetmanager!getAssetNameByType.action?assettype="+$("#asset_type").val(),
		        	type:"POST",
		        	async:false,
		        	cache: false,
		    		success: function(result)
		    		{
		    			var assetname = JSON.parse(result);
		    			$("#asset_name").html(null);
		    			$("#asset_name").append("<option value=''>请选择</option>")
		    			for(var i in assetname){
     						$("#asset_name").append("<option  value='"+assetname[i].ASSETNAME+"'>"+assetname[i].ASSETNAME+"</option>")
			    			if($("#asset_code").val()==assetname[i].ID){
			    				$("#asset_name").val(assetname[i].ASSETNAME);
			    				continue;
			    			}
     					}
		    		}     	
        		}); 
			}
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
    				
    			
    			
    			
    
    function getassetname(){
			$.ajax({
		        	url:"assetmanager!getAssetNameByType.action?assettype="+$("#asset_type").val(),
		        	type:"POST",
		        	async:false,
		        	cache: false,
		    		success: function(result)
		    		{
		    			var assetname = JSON.parse(result);
		    			$("#asset_name").html(null);
		    			$("#asset_name").append("<option value=''>请选择</option>")
		    			for(var i in assetname){
     						$("#asset_name").append("<option  value='"+assetname[i].ASSETNAME+"'>"+assetname[i].ASSETNAME+"</option>")
     					}
		    		}     	
        		}); 
		
	}
	function getassetcode(){
		
     	$.ajax({
		        	url:"assetmanager!getAssetCodeByAssetName.action?assetname="+encodeURI(encodeURI($("#asset_name").val())),
		        	type:"POST",
		        	async:false,
		        	cache: false,
		    		success: function(result)
		    		{
		    			var assetcode = JSON.parse(result);
		    			$("#asset_code").val(assetcode[0].ID);
		    		}     	
        		}); 
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
    <input type="hidden" id="title" name="title" value="资产录入" />
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
					<td class="td_table_1"><span>分类：</span></td>
					<td class="td_table_2" style="width:35%">
						<select name="asset_type" id="asset_type" class="wi-ipt-1" onchange="getassetname()" value="${map.asset_type}">
							<option value="">请选择</option>
							<option value="1">房屋及构筑物</option>
							<option value="2">专用设备</option>
							<option value="3">通用设备</option>
							<option value="4">文物和陈列品</option>
							<option value="5">图书、档案</option>
							<option value="6">家具、用具、装具、动植物</option>
						</select>
					</td>
					<td class="td_table_1"><span>资产名称：</span></td>
					<td class="td_table_2" style="width:35%">
					  <select id="asset_name" name="asset_name" class="wi-ipt-1"  onchange="getassetcode()" value="${map.asset_name}" >
						
				      </select>
					</td>
					
				</tr>
				<tr>	
					<td class="td_table_1"><span>编号：</span></td>
					<td class="td_table_2">
						<input type="text" readonly="true" class="input_240" id="asset_code"  name="asset_code" value="${map.asset_code}" />
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
					<td class="td_table_1"><span>品牌：</span></td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="asset_brand" name="asset_brand" value="${map.asset_brand}" />
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
					
				</tr>
				<tr>
					<td class="td_table_1"><span>单价（元）：</span></td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="asset_price" name="asset_price" value="${map.asset_price}" />
						<span class="LV_invalid none" style="color: red;"></span>
					</td>	
					<td class="td_table_1"><span>数量：</span></td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="asset_total" name="asset_total" value="${map.asset_total}" />
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
					
				</tr>
				<tr>
		            
					<td class="td_table_1"><span>采购人：</span></td>
					<td class="td_table_2">
						<input type="text"  class="input_240" id="asset_purchaser" name="asset_purchaser" value="${map.asset_purchaser}" />
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
					<td class="td_table_1"><span>采购时间：</span></td>
					<td class="td_table_2" style="width: 100px; height: 26px;">
						<input type="text" id="asset_purchasetime" name="asset_purchasetime"  class="WdateFlow" style="width: 200px;" value="${map.asset_purchasetime}" 
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
<table align="center" border="0" cellpadding="0"cellspacing="0">
				<tr align="center">
					<td colspan="1" class="h50">
						<input type="button" style=""  class="selectBtn" id="submit" value="提交" onclick="choosesub();"/>
						&nbsp;&nbsp;
						<input type="button" class="selectBtn" name="reback" value="返回"
							onclick="bakIframe('${map.label}','${map.bakurl}')">
							<input type="button" id="bt" class="selectBtn"   value="查看" onClick="show() " >
							
								&nbsp;&nbsp;	
						<input type="button" class="selectBtn"  value="查看流程图"
							onclick="showProcess_sig('input_process_sig','流程查看','${map.processId}','${map.orderId}',0,490,500)"/>	
					</td>
				</tr>
			</table>

<div id="view"></div>
<div id="input_process_sig" >
	<%-- <iframe width=630 height=460 frameborder=0 src="${ctx}/process/designer?processId=${map.processId}&status=0&orderId=${map.orderId}&orderIdStatus=0"></iframe> --%>
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
