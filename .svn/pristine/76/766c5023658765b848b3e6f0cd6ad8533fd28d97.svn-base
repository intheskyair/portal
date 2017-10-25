<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head> 
  <%@ include file="/common/meta.jsp"%>
    <title>工作人员请假申请表</title>   
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/My97DatePicker/skin/WdatePicker.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css"/>
    <link rel="stylesheet" href="${ctx}/css/common.css"></link>
    <link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
     <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquery-ui-jquiGui.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/flexigrid/flexigrid.css"/>
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
				var url;
				var allleave;
				var year = "${map.year}";
				var hour;
				var day;
				var leave_user = {
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
						title:'选择审批人员',
						buttons:{
							"确定":function(){
								$("#submit").attr("style", "background-color:gray");
								 $("#submit").attr("disabled", true);
								leave_user.saveAuth();
							}
						}
					});
					/*
					if("${map.flag}"=="false"){
						url = "queryUser!getUserList.action?assignee=${map.assignee}&org_id=${map.ord}&autoOrg=${map.autoOrg}"; 
					}else{
						url = "queryUser!getUserList.action?assignee=${map.assignee}";
					}*/
					url = "queryUser!getUserList.action?assignee=${map.assignee}&org_id=${map.ord}&autoOrg=${map.autoOrg}"; 
			        $.ajax({
			            type:"get",
			            dataType:"text",
			            async:false,
			            url:url,
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
		        	url:"leave!process.action",
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
	
	
	
	
	
    function choosesub(){
    	$("#title").blur();
    	$("#startDate").blur();
    	$("#endDate").blur();
    	if(($("span[class='LV_invalid']").length != 0)){
			return;
		}
    	if("${map.assignee}" == 0){
    		showIframeMsg("您不需要请假");
    	}else{
    		leave_user.changeAuth();	
    	}
		
    }
    
    $(function() 
    {
		$("#begin").click();
	});
     $(function(){
    	 //载入流程图之前要手动隐藏，不然ie加载不完整
    	 $("#leave_process_sig").hide();
        if("${map.orderId}" != '')
        {
        	 $("#qtype").val("${map.qtype}");
        	 allleave = "${map.allleave}";
        	 if(allleave.indexOf(".") == -1){
        		 $("#day").val(parseInt(allleave) -1);
        		 $("#hour").val(8);
        	 }else{
        		 var m = allleave.split(".");
        		 $("#day").val(m[0]);
        		 $("#hour").val(m[1]);
        	 }
        		$.ajax({
		     		url:"borrow!view.action?orderId=${map.orderId}&path=${map.path}",
		     		type:"POST",
		        	cache: false,
		    		success: function(result){
		    			$("#view").html(result);
		    		}   
		     	});
        	//$("#view").show();
        	$("#view_fwg").show();
    		$("#submit").val("重新提交");
    		$("#submit").attr("style","color:red");
    		$("#detail").html("【被退回】");
    		$("#detail").attr("style","color:red");        	
        }
        else
        {
            $("#view_fwg").hide();
        	$("#hour").attr("disabled","disabled");      	
        }
       	$("#time").val(formateDate(new Date())); 
    }); 
   
    
    function cleaveTime(){
    	var stime = $("#startDate").val();
    	var etime = $("#endDate").val();
    	var time = daysBetween(stime,etime);
    	if(!isNaN(time)){
// 		if(time !== null || time !== undefined || time !== ''){
//     		var date_split = time.split(",");
//     		$("#day").val(date_split[0]);
    		$("#day").val(time);
    		count('day','hour');
    		$("#hour").removeAttr("disabled");
    	}else{
    		$("#hour").attr("disabled","disabled");
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
	
	
	
	function count(a,b){
		 day = $("#"+a).val();
		 hour = $("#"+b).val();
		if(hour < 8){
// 			allleave = hour/10 + parseInt(day);
			allleave = hour/10 + parseInt(day);
		}else{
			allleave = parseInt(day) +1;
		}
		$("#allleave").val(allleave);
		cedge(allleave,year);
	}
	
	
	   function cedge(a,b){
	 		if($("#qtype").val() == "年假"){
		 			if(a > b){
		 				$("#year").nextAll(".LV_invalid").removeClass("none").html("*您的年假剩余天数不足");
		 			}else{
		 				$("#year").nextAll(".LV_invalid").addClass("none").html("");
			 			}
	 		}else{
	 			if($("#year").nextAll(".LV_invalid").addClass("none").html() != ''){
	 				$("#year").nextAll(".LV_invalid").addClass("none").html("");
	 			}
	 		}
	 	}
	 	
	 function	closecudge(){
	 		if($("#year").nextAll(".LV_invalid").addClass("none").html() != ''){
	 				$("#year").nextAll(".LV_invalid").addClass("none").html("");
	 			}
	 	}

function personDetails(com) 
{
		$("#formTable").html("");
		$("#flowTable").html("");
		var orderId = "${map.orderId}";
		var pid = "${map.processId}";
		if (com == 2) 
		{
			url = "borrow!view.action";
			data = {
				"orderId" : orderId,
				"processId" : pid,
				"path" : "leaveView"
			};
		} 
		else
		{
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

				if (com == 1) 
				{
					var result_str = createHtml(result);
					$("#flowTable").append(result_str);
				} else 
				{
					$("#formTable").append(result);
				}
				$("#view_fwg .tab li").click(function()
				{
					$("#view_fwg .tab li").removeClass("current");
					$(this).addClass("current");
					var n = $(this).index();
					$("#view_fwg .content_y ul").hide();
					$("#view_fwg .content_y ul").eq(n).show();
		        });
			}
		});
		document.getElementById("msg_end").click(); 

	} 	 	
	 
</script>	
  </head>
  <body style="overflow: auto;">
   <form id="inputForm" action="" method="post" target="mainFrame">
    <input type="hidden" id="processId" name="processId" value="${map.processId}" />
	<input type="hidden" id="orderId" name="orderId" value="${map.orderId}"/>
	<input type="hidden" id="taskId" name="taskId" value="${map.taskId}" />
	<!-- 当前流程名称 -->
	<input type="hidden" id="taskName" name="taskName" value="${map.taskName}" />
	<input type="hidden" id="time" name="time"/>
	<!-- 下步流程权限ID -->
	<input type="hidden" id="assignee" name="assignee" value="${map.assignee}"/>
	<input type="hidden" name="userList" id="userList"/>
	<input type="hidden" name="result" id="result" value="${map.result}"/>
<p class="btt">请假申请<span id="detail"></span></p>	
<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px">
				<tr>
					<td class="td_table_1"><span>请假标题：</span></td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="title" name="title" value="${map.title}" onblur="checkEmpty('title','标题')" />
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
				</tr>
				<tr>
					<td class="td_table_1"><span>请假类型：</span></td>
					<td class="td_table_2">
						<select class="input_240" id="qtype" name="qtype" style="width: 100px; height: 26px;" onchange="closecudge()">
							<option value="事假">事假</option>
							<option value="婚假">婚假</option>
							<option value="产假">产假</option>
							<option value="陪产假">陪产假</option>
							<option value="丧假">丧假</option>
							<option value="年假">年假</option>
							<option value="病假">病假</option>
							<option value="其他">其他</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="td_table_1"><span>开始时间：</span></td>
					<td class="td_table_2" style="width: 100px; height: 26px;">
						<input type="text" id="startDate" name="stime"  class="WdateFlow" style="width: 200px;" value="${map.stime}"  onFocus="var endDate=$dp.$('endDate');WdatePicker({onpicked:function(){endDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}',readOnly:true})" onchange="cleaveTime()"  onblur="checkEmpty('startDate','开始时间')"/>
<!-- 						<input type="text" id="startDate" name="stime"  class="WdateFlow" style="width: 200px;" value="${map.stime}"  onFocus="var endDate=$dp.$('endDate');WdatePicker({onpicked:function(){endDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}',readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})" onchange="cleaveTime()"  onblur="checkEmpty('startDate','开始时间')"/> -->
						<span class="LV_invalid none" style="color: red;"></span>
					</td>				
				</tr>
				<tr>
					<td class="td_table_1"><span>结束时间：</span></td>
					<td class="td_table_2">
						<input type="text" class="WdateFlow" id="endDate"  name="etime" value="${map.etime}" style="width: 200px;" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}',readOnly:true})" onchange="cleaveTime()" onblur="checkEmpty('endDate','结束时间')"/>
<!-- 						<input type="text" class="WdateFlow" id="endDate"  name="etime" value="${map.etime}" style="width: 200px;" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}',readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})" onBlur="cleaveTime()" onblur="checkEmpty('endDate','结束时间')"/> -->
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
				</tr>
				<tr>
					<td class="td_table_1"><span>请假天数：</span></td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="day" style="width: 50px; text-align: center;"  value="${map.day}" onchange="count('day','hour')" />天
						<span>
						<select id="hour"  class="input_240" style="width: 50px" onchange="count('day','hour')">
							<option value="0">&nbsp;&nbsp;0</option>
							<option value="1">&nbsp;&nbsp;1</option>
							<option value="2">&nbsp;&nbsp;2</option>
							<option value="3">&nbsp;&nbsp;3</option>
							<option value="4">&nbsp;&nbsp;4</option>
							<option value="5">&nbsp;&nbsp;5</option>
							<option value="6">&nbsp;&nbsp;6</option>
							<option value="7">&nbsp;&nbsp;7</option>
							<option value="8" selected="selected">&nbsp;&nbsp;8</option>
						</select>
						小时,共<input id="allleave" class="input_240" readonly="readonly" value="${map.allleave}" style="width: 50px; text-align: center;" name="allleave" />天</span>
					</td>
				</tr>
				<tr>
					<td class="td_table_1"><span>剩余年假天数：</span></td>
					<td class="td_table_2">
						<input type="text" class="input_240"  readonly="readonly" id="year" value="${map.year}" />天
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
				</tr>
				<tr>
					<td class="td_table_1"><span>年度累计请假天数：</span></td>
					<td class="td_table_2">
						<input type="text" class="input_240"  readonly="readonly" name="allday" id="allday" value="${map.allday}" />天
						&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">注：此次请假天数不包含在内</span>
					</td>
				</tr>
				<tr>
					<td class="td_table_1"><span>备注：</span></td>
					<td class="td_table_2">
						<textarea class="input_textarea_320" id="bz" name="bz">${map.bz}</textarea>
					</td>
				</tr>
			</table>
				
			<table align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr align="center">
					<td colspan="1" class="h50">
						<input type="button" class="selectBtn" style="" id="submit"  value="提交" onclick="choosesub();"/>
						&nbsp;&nbsp;
						<input type="button" class="selectBtn" name="reback" value="返回"
							onclick="bakIframe('${map.label}','${map.bakurl}')">
								&nbsp;&nbsp;	
						<input type="button" class="selectBtn"  value="查看流程图"
							onclick="showProcess_sig('leave_process_sig','流程查看','${map.processId}','${map.orderId}',0,600,730)"/>
					</td>
				</tr>
</table>
<p></p>
</form>

<div id="leave_process_sig" style="">
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
					<td>
						<select multiple name="username_list2" id="username_list2" class="wi-sel-1" style="width:150px;height:260px" >
						</select>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
    <div id="view_fwg" style="display:none;">	
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
  </body>
</html>
