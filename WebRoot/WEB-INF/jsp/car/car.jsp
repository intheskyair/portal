<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/meta.jsp"%>
<title>车辆申请</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/styles/My97DatePicker/skin/WdatePicker.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css" />
<link rel="stylesheet" href="${ctx}/styles/css/style.css"
	type="text/css" media="all" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/views/common/css/themes/css/jquery-ui-jquiGui.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/views/common/css/themes/css/flexigrid/flexigrid.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/views/common/css/themes/css/jquiGui.css" />
<script src="${ctx}/views/common/js/jquery.min.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${ctx}/views/common/js/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/views/common/js/common.js"></script>
<script type="text/javascript"
	src="${ctx}/views/common/js/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
#add {
	display: block;
	width: 20px;
	height: 18px;
	background:
		url(./views/common/css/themes/css/flexigrid/images/icon/add.png)
		no-repeat center left;
}
</style>

<script type="text/javascript">
	
               
	 $(function() 
	{
		$("#begin_car").click();
	});
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
		        	url:"carManager!process.action",
		        	type:"POST",
		        	cache: false,
		    		data:$("#inputForm").serialize(),
		    		success: function(msg)
		    		{
		    			if("success" === msg)
		    			{
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
    	$("#car_no").blur();
    	if(($("span[class='LV_invalid']").length != 0)){
			return;
		}else{
			leave_user.changeAuth();
		}
    }
    
    
   
   
     $(function()
     {  
    	 //载入流程图之前要手动隐藏，不然ie加载不完整
    	 $("#car_process_sig").hide();
    	$("#time").val(formateDate(new Date())); 
        $("#qtype").val("${map.qtype}");
        if("${map.orderId}" == '')
        {
        	$("#view_car").hide();
        }else{
            $("#view_car").show();
    		$("#submit").val("重新提交");
    		$("#submit").attr("style","color:red");
    		$("#detail").html("【被退回】");
    		$("#detail").attr("style","color:red");            
        }		
        $.ajax({
            type:"get",
            dataType:"text",
            async:false,
            url:"carManager!getCarInfoByCarStatus.action",
            success:function(result) 
            {
            	var data=eval("("+result+")");
				var html = '';
				if(data.length > 0)
				{
					for (var i in data) 
					{
						html += '<option value="'+data[i].CAR_NO+'">'+data[i].CAR_NO+'</option>';
					}
					$("#car_no").append(html);
				}else{
					showMsg("暂无可用车辆！");
					$("#submit").attr("style", "background-color:gray");
					$("#submit").attr("disabled", true);
					return;
				}
			}
        });   
    }); 
    
    
    function cleaveTime(){
    	var stime = $("#startDate").val();
    	var etime = $("#endDate").val();
    	var time = daysBetween(stime,etime);
    	if(!isNaN(time)){
    		$("#day").val(time);
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

		$("#formTable_car").html("");
		$("#flowTable_car").html("");

		var orderId = "${map.orderId}";
		var pid = "${map.processId}";

		if (com == 2) {
			url = "borrow!view.action";
			data = {
				"orderId" : orderId,
				"processId" : pid,
				"path" : "carView"
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
				if (com == 1) 
				{
					var result_str = createHtml(result);
					$("#flowTable_car").append(result_str);
				} else 
				{
					$("#formTable_car").append(result);
				}
				$("#view_car .tab li").click(function()
				{
					$("#view_car .tab li").removeClass("current");
					$(this).addClass("current");
					var n = $(this).index();
					$("#view_car .content_y ul").hide();
					$("#view_car .content_y ul").eq(n).show();			
				});
		       
			},
		})

	}
     
</script>
</head>
<body style="overflow: scroll;">
	<form id="inputForm" action="" method="post" target="mainFrame">
		<input type="hidden" id="processId" name="processId"
			value="${map.processId}" /> <input type="hidden" id="orderId"
			name="orderId" value="${map.orderId}" /> <input type="hidden"
			id="taskId" name="taskId" value="${map.taskId}" />
		<!-- 当前流程名称 -->
		<input type="hidden" id="taskName" name="taskName"
			value="${map.taskName}" /> <input type="hidden" id="time"
			name="time" />
		<!-- 下步流程权限ID -->
		<input type="hidden" id="assignee" name="assignee"
			value="${map.assignee}" /> <input type="hidden" id="step" name="step"
			value="${map.step}" /> <input type="hidden" name="userList"
			id="userList" />


        <p class="btt" style=" font-size:30px;">应急保障（防汛）车辆使用申请<span id="detail"></span></p>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px">
			<tr>
				<td class="td_table_1"><span>申请标题：</span></td>
				<td class="td_table_2" colspan="3"><input type="text"
					class="input_240" id="title" name="title" style="width: 85%;margin-left: 5px;"
					value="${map.title}" onblur="checkEmpty('title','标题')" /> <span
					class="LV_invalid none" style="color: red;"></span></td>
			</tr>
			<tr>
				<td class="td_table_1"><span>申请处室：</span></td>
				<td class="td_table_2" style="width:35%"><input type="text"
					class="input_240" id="sqcs" name="sqcs" readonly
					value="${admin.org_name}" onblur="checkEmpty('sqcs','申请处室')" /> <!-- 						<span class="LV_invalid none" style="color: red;"></span> -->
				</td>
				<td class="td_table_1"><span>申请人：</span></td>
				<td class="td_table_2" style="width:35%"><input type="text"
					class="input_240" id="sqr" name="sqr" readonly
					value="${admin.truename}" onblur="checkEmpty('sqr','申请人')" /> <!-- 						<span class="LV_invalid none" style="color: red;"></span> -->
				</td>
			</tr>
			<tr>

				<td class="td_table_1"><span>车号：</span></td>
				<td class="td_table_2"><select id="car_no" name="car_no"
					class="input_240" onblur="checkEmpty('car_no','车号')">
				</select> <!-- 				      <span class="LV_invalid none" style="color: red;"></span> -->
				</td>
				<td class="td_table_1"><span>驾驶员：</span></td>
				<td class="td_table_2"><input type="text" class="input_240"
					id="jsy" name="jsy" value="${map.jsy}"
					onblur="checkEmpty('jsy','驾驶员')"> <!-- 						<span class="LV_invalid none" style="color: red;"></span> -->

				</td>
			</tr>
			<tr>
				<td class="td_table_1"><span>用车开始时间：</span></td>
				<td class="td_table_2" style="width: 100px; height: 26px;"><input
					type="text" id="startDate" name="stime" class="WdateFlow"
					style="width: 200px;" value="${map.stime}"
					onFocus="var endDate=$dp.$('endDate');WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){endDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}',readOnly:true})"
					onchange="cleaveTime()" onblur="checkEmpty('startDate','开始时间')" />
					<span class="LV_invalid none" style="color: red;"></span></td>
				<td class="td_table_1"><span>用车结束时间：</span></td>

				<td class="td_table_2"><input type="text" class="WdateFlow"
					id="endDate" name="etime" value="${map.etime}"
					style="width: 200px;"
					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\')}',readOnly:true})"
					onchange="cleaveTime()" onblur="checkEmpty('endDate','结束时间')">
					<span class="LV_invalid none" style="color: red;"></span></td>
			</tr>
			<tr>
				<td class="td_table_1"><span>目的地：</span></td>
				<td class="td_table_2"><textarea class="input_textarea_320"
						id="mdd" name="mdd" onblur="checkEmpty('mdd','目的地')">${map.mdd}</textarea>
				</td>
				<td colspan="2"></td>
			</tr>
			<tr>
				<td class="td_table_1"><span>用车事由：</span></td>
				<td class="td_table_2"><textarea class="input_textarea_320"
						id="ycsy" name="ycsy" onblur="checkEmpty('ycsy','用车是由')">${map.ycsy}</textarea>
				</td>
				<td class="td_table_1"><span>随行人员：</span></td>
				<td class="td_table_2"><textarea class="input_textarea_320"
						id="sxry" name="sxry" onblur="checkEmpty('sxry','随行人员')">${map.sxry}</textarea>
				</td>
			</tr>
		</table>

		<table align="center" border="0" cellpadding="0" cellspacing="0">
			<tr align="center">
				<td colspan="1" class="h50"><input type="button"
					class="selectBtn" style="" id="submit" value="提交"
					onclick="choosesub();" /> &nbsp;&nbsp; <input type="button"
					class="selectBtn" name="reback" value="返回"
					onclick="bakIframe('${map.label}','${map.bakurl}')">
					&nbsp;&nbsp; <input type="button" class="selectBtn" value="查看流程图"
					onclick="showProcess_sig('car_process_sig','流程查看','${map.processId}','${map.orderId}',0,490,630)" />


				</td>
			</tr>
		</table>
		<p></p>
	</form>

	<div id="car_process_sig">
	</div>
	<script type="text/javascript">
  $(document).ready(function(){
	    var bgsyj = $("#bgsyj").val();
        var jfgldyj = $("#jfgldyj").val();
      
       if(bgsyj == "不同意" || jfgldyj == "不同意"){
          //carNo.selected = false;
          
       }
      
	 });
</script>
	<div id="view_car" style="display:none;">
		<div class="outer">
			<ul class="tab">
				<li class="current" onclick="personDetails(1)" id="begin_car">流程详情</li>
				<li onclick="personDetails(2)">查看表单</li>

			</ul>
			<div class="content_y">
				<div id="flowTable_car"></div>
				<div id="formTable_car"></div>
			</div>
		</div>
	</div>
	<div id="dlg_auth_distribute" style="display:none">
		<form method="post" name="authchoose">
			<table border="0">
				<tr style="height:35px">
					<td class="wi-align-l" style="text-align:center">待分配用户：</td>
					<td class="wi-align-l"></td>
					<td class="wi-align-l" style="text-align:center">已选择用户：</td>

				</tr>
				<tr>
					<td><select multiple name="username_list1" id="username_list1"
						class="wi-sel-1" style="width:150px;height:260px">
					</select></td>
					<td style="width:200px;text-align:center"><input
						style="width:50px" type="button" value="添加"
						onclick="moveOption(document.authchoose.username_list1,document.authchoose.username_list2);"><br />
					<br /> <input style="width:50px" type="button" value="移除"
						onclick="moveOption(document.authchoose.username_list2,document.authchoose.username_list1);"><br />
					</td>
					<td><select multiple name="username_list2"
						id="username_list2" class="wi-sel-1"
						style="width:150px;height:260px">
					</select></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
