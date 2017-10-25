<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head> 
  <%@ include file="/common/meta.jsp"%>
    <title>个人季度计划与总结</title>
    	<link rel="stylesheet" type="text/css" href="${ctx}/styles/My97DatePicker/skin/WdatePicker.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css"/>
    <link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
     <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquery-ui-jquiGui.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquiGui.css"/>
    <script src="${ctx}/views/common/js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	$(function() {
		$("#begin").click();
            	 var date=new Date();//
                 var year = date.getFullYear();
                 var month = date.getMonth()+1;
                 var day = date.getDate();
                 if (month < 10) {
                     month = "0" + month;
                 }
                 if (day < 10) {
                     day = "0" + day;
                 }
                 $("#gzjhtime").val(year+"-"+month+"-"+day);
	});
	function choosesub(){
		$("#yd").blur();
		if ($("#jd").val() == "") {
    		$("#jd").nextAll(".LV_invalid").removeClass("none").html("*必选");
    	}else{
    		$("#jd").nextAll(".LV_invalid").addClass("none").html("");
    	}
		$("#gzjh").blur();
		$("#gzjhtime").blur();
		if(($("span[class='LV_invalid']").length != 0)){
			return;
		}
		plan_user.changeAuth();
	}
    
    var plan_user = {
					changeAuth:function(){
			        var dlg_auth_distribute = $("#dlg_auth_distribute").dialog({
				        position: ['center','top'],
        				draggable :false,     
						autoOpen: false,
						height:$(window).height()*0.8,
						width:$(window).width()*0.5,
						show: "slide",
						modal:true,
						cache:false,
						/* hide: "explode", */
						title:'批量分配角色',
						buttons:{
							"确定":function(){
								$("#submit").attr("style", "background-color:gray");
								 $("#submit").attr("disabled", true);
								plan_user.saveAuth();
							}
						}
					});
			        $.ajax({
			            type:"get",
			            dataType:"text",
			            async:false,
			            url:"queryUser!getUserList.action?assignee=${map.assignee}&org_id=${map.ord}&autoOrg=${map.autoOrg}",
			            success:function(result) {
			            $("#username_list1").html("");
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
		        	url:"plan!process.action",
		        	type:"POST",
		        	cache: false,
		    		data:$("#inputForm").serialize(),
		    		success: function(msg){
		    			if("success" === msg){
		    				alert("提交成功");
		    				bakIframe("${map.label}","${map.bakurl}");
		    			}
		    		}     	
        		});   
			}
	
	};
	
    
    $(function(){
    	 //载入流程图之前要手动隐藏，不然ie加载不完整
   	 	$("#plan_process_sig").hide();
    	if("${map.orderId}" == ""){
    		$("#view").hide();
    	}else{
    		$("input[type='radio'][value='${map.type}']").attr("checked",'checked')
    		if('${map.type}' == 0){
    			$("#jd").val("${map.jd}");
    		}else{
    			makeSelect(1,"${map.jd}");
    		}
    		$("#view").show(); 
    			$("#submit").val("重新提交");
	    		$("#submit").attr("style","color:red");
	    		$("#detail").html("【被退回】");
	    		$("#detail").attr("style","color:red");
    	}
	   	$("input[type = 'radio']").change(function(){
	   		makeSelect($(this).val());
	   	});
    });
    
    
    	function makeSelect(val,val1){
    		$("#jd").empty();
	   		var result;
	   		result = "<option value=''>请选择</option>";
	   		if(val == 0){
	   			result += "<option value='第一季度'>第一季度</option>";
	   			result += "<option value='第二季度'>第二季度</option>";
	   			result += "<option value='第三季度'>第三季度</option>";
	   			result += "<option value='第四季度'>第四季度</option>";
	   		}else{
	   			result += "<option value='第一月份'>第一月份</option>";
	   			result += "<option value='第二月份'>第二月份</option>";
	   			result += "<option value='第三月份'>第三月份</option>";
	   			result += "<option value='第四月份'>第四月份</option>";
	   			result += "<option value='第五月份'>第五月份</option>";
	   			result += "<option value='第六月份'>第六月份</option>";
	   			result += "<option value='第七月份'>第七月份</option>";
	   			result += "<option value='第八月份'>第八月份</option>";
	   			result += "<option value='第九月份'>第九月份</option>";
	   			result += "<option value='第十月份'>第十月份</option>";
	   			result += "<option value='第十一月份'>第十一月份</option>";
	   			result += "<option value='第十二月份'>第十二月份</option>";
	   		}
	   		$("#jd").append(result);
	   		if(typeof(val1) != 'undefined'){
	   			$("#jd").val(val1)
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
	
	
	function personDetails(com) {

		$("#formTable").html("");
		$("#flowTable").html("");

		var orderId = "${map.orderId}";
		var pid = "${map.processId}";

		if (com == 2) {
			url = "borrow!view.action";
			data = {
				"orderId" : orderId,
				"processId" : pid,
				"path" : "planView"
			};
		} else {

			url = "lczl!viewStep.action";
			data = {
				"orderId" : orderId
			};
		}

		$.ajax({
			type : "POST",
			data : data,
			cache : false,
			async : false,
			url : url,
			success : function(result) 
			{
				if (com == 1) {
					var result_str = createHtml(result);
					$("#flowTable").append(result_str);
				} else {
					$("#formTable").append(result);
				}
				$("#view .tab li").click(function(){
					$("#view .tab li").removeClass("current");
					$(this).addClass("current");
					var n = $(this).index();
					$("#view .content_y ul").hide();
					$("#view .content_y ul").eq(n).show();			
				});
		       
			}
		});
		document.getElementById("msg_end").click();

	}
	
</script>	
   <style type="text/css">
	.bg_gray{
		background-color: gray;
	}
   .dwb{ width:95%;}
	</style>
  </head>  
  <body style="overflow: scroll;">
   	<p class="btt" style=" font-size:30px;">个人季度计划与总结<span id="detail"></span></p>
   	 <form id="inputForm" action="" method="post" target="mainFrame">
    <input type="hidden" id="processId" name="processId" value="${map.processId}" />
	<input type="hidden" id="orderId" name="orderId" value="${map.orderId}"/>
	<input type="hidden" id="taskId" name="taskId" value="${map.taskId}" />
	<input type="hidden" id="title" name="title" value="工作计划" />
	<input type="hidden" id="ord" name="ord" value="${map.ord}" />
	<!-- 当前流程名称 -->
	<input type="hidden" id="taskName" name="taskName" value="${map.taskName}" />
	<!-- 下步流程权限ID -->
	<input type="hidden" id="assignee" name="assignee" value="${map.assignee}"/>
	<input type="hidden" name="userList" id="userList"/>
	
<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px">
				<tr>
					<td class="td_table_1"><span>年度：</span></td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="yd" name="yd" value="${map.yd}" onclick="WdatePicker({dateFmt:'yyyy',readOnly:true})" onblur="checkEmpty('yd','年度')" />年度
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
				</tr>
				<tr>
					<td class="td_table_1"><span>类型：</span></td>
					<td class="td_table_2">
						 <input type="radio" name="type" checked="checked" value="0"/>按季度
						 <input type="radio" name="type" value="1"/>按月份
					</td>
				</tr>
				<tr>
					<td class="td_table_1"><span>季月：</span></td>
					<td class="td_table_2">
						<select id="jd" name="jd" class ="input_240">
							<option value="">请选择</option>
							<option value="第一季度">第一季度</option>
							<option value="第二季度">第二季度</option>
							<option value="第三季度">第三季度</option>
							<option value="第四季度">第四季度</option>
						</select>
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
				</tr>
				<tr>
					<td class="td_table_1"><span>姓名：</span></td>
					<td class="td_table_2">
					<input type="text" class="input_240"  readonly="readonly"  value="${admin.truename}"  />
					</td>
				</tr>
				<tr>
					<td class="td_table_1"><span>职务：</span></td>
					<td class="td_table_2">
					<input type="text" class="input_240"  readonly="readonly"  value="${admin.org_infor}"  />
					</td>
				</tr>
				<tr>
					<td class="td_table_1"><span>所在部门：</span></td>
					<td class="td_table_2">
					<input type="text" class="input_240"  readonly="readonly"  value="${admin.org_name}"  />
					</td>
				</tr>
			
				<tr>
					<td class="td_table_1"><span>填写时间：</span></td>
					<td class="td_table_2" style="width: 100px; height: 26px;">
						<input type="text" id="gzjhtime" name="gzjhtime"  class="WdateFlow" style="width: 200px;" value="${map.gzjhtime}" onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,readOnly:true})" onblur="checkEmpty('gzjhtime','填写时间')"/>
						<span class="LV_invalid none" style="color: red;"></span>
					</td>	
				</tr>
					<tr>
					<td class="td_table_1"><span>工作计划：</span></td>
					<td class="td_table_2">
						<textarea class="input_textarea_320" id="gzjh" name="gzjh" onblur="checkEmpty('gzjh','工作计划')">${map.gzjh}</textarea>
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
				</tr>
			</table>
				
			<table align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr align="center">
					<td colspan="1" class="h50">
						<input type="button" class="selectBtn" id="submit" style=""  value="提交" onclick="choosesub();"/>
						&nbsp;&nbsp;
						<input type="button" class="selectBtn" name="reback" value="返回"
							onclick="bakIframe('${map.label}','${map.bakurl}')">
						&nbsp;&nbsp;	
						<input type="button" class="selectBtn"  value="查看流程图"
							onclick="showProcess_sig('plan_process_sig','流程查看','${map.processId}','${map.orderId}',0,490,580)"/>	
					</td>
				</tr>
</table>
<p></p>
</form>
   	
<div id="plan_process_sig">
	<%-- <iframe width=630 height=460 frameborder=0 src="${ctx}/process/designer?processId=${map.processId}&status=0&orderId=${map.orderId}&orderIdStatus=0"></iframe> --%>
</div>
<p></p>
<div id="view" >	
		<div class="outer">
					<ul class="tab">
						<li class="current" onclick="personDetails(1)" id="begin">流程详情</li>
						<li onclick="personDetails(2)">查看表单</li>
					    
					</ul>
					<div class="content_y">
					     		<div id="flowTable"></div>	
						    	<div id="formTable"></div>
					   					</div>			
		</div>
	</div>
<div><a id="msg_end" name="1" href="#1">&nbsp</a></div>
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
