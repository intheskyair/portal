<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head> 
  <%@ include file="/common/meta.jsp"%>
    <title>部门工作月报表</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css"/>
    <link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
    <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquiGui.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquery-ui-jquiGui.css"/>
    <script src="${ctx}/views/common/js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/json2.js"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/common.js"></script>
	<style type="text/css">
		.t_full{
			width: 90%;
			height:100px;
			border: 0px;
			resize:none;
		}
		
		.div_btn{
			width: 98%;
			margin:0 auto; 
			text-align:center;
		}
		.t_left{
			text-align: left;
			margin-top: 5px;
		}
		textarea {
padding:6px;
line-height: 18px;
    overflow-y: auto;
}
		#add_btn{
			width:70px;
			height:23px;
			border-radius:6px;
			outline:none;
			background-color:#31b0d5;
			background:url(./views/common/css/themes/css/flexigrid/images/icon/add.png) no-repeat center left;
		}
		#close{
		display:block;
		width: 20px;
		height: 18px;
		background:url(./views/common/css/themes/css/flexigrid/images/icon/close.png) no-repeat center left;
		}
		.blur {  
   		 border: 1px solid red;  
		}
	fieldset{border:1px solid #9AC3E1;margin-top:10px;}
	legend{font-size: 16px;color: #0071b1;font-weight: bold;}
	</style>
	
  </head>
  <body style="overflow-y: scroll;">
  <input type="hidden" value="${step}" id="step">
   <form id="inputForm" action="" method="post" target="mainFrame">
   <input type="hidden" id="processId" name="processId" value="${map.processId}" />
	<input type="hidden" id="orderId" name="orderId" value="${map.orderId}" />
	<input type="hidden" id="taskId" name="taskId" value="${map.taskId}" />
	<input type="hidden" id="taskName" name="taskName" value="${map.taskName}" />
	<input type="hidden" id="title" name="title" value="部门工作月报表" />
	<!-- 下步流程权限ID -->
	<input type="hidden" id="assignee" name="assignee" value="${map.assignee}"/>
	<input type="hidden" name="userList" id="userList"/>
	<p class="btt">部门工作月报表<span id="detail"></span></p>
<table style="width:98%; margin-top:20px;" cellpadding="0" cellspacing="0">
<tr>
 <td  style=" text-align:left">填报单位：<input class="nyr"  style="width:250px;" type="text" id="dw" name="dw" value="${admin.org_name}" readonly="readonly"/></td>
 <td style=" text-align:right">日期:
 	<input type="text"  readonly="readonly" name="year" value="${map.year}" class="nyr" style=" width:40px"/>年 
 	<input type="text" readonly="readonly" name="month" value="${map.month}" class="nyr"/>月
 	<input type="text"  readonly="readonly" name="day" value="${map.day}" class="nyr"/>日</td>
</tr>
</table>
<br/>

<table id="tab"  class="table-all" cellspacing="0" align="center" style="margin-top:5px; background: #d0e9fe;">
 <tr>
 	<td>序号</td>
	<td><input type="text" id="first" name="first" class="nyr" style="border:1px solid #ccc;text-align:center" value="${map.first}"/>月份主要工作安排</td>
    <td><input type="text" id="second" name="second" class="nyr" style="border:1px solid #ccc;text-align:center" value="${map.second}"/>月份主要工作完成情况</td>
    <td><input type="text" id="third" name="third" class="nyr" style="border:1px solid #ccc;text-align:center" value="${map.third}"/>月份主要工作安排</td>
    <td>操作</td>
 </tr>
 <c:if test="${map.orderId != ''}">  
 	<c:forEach items="${list}" var="item" varStatus="status">  
 	<tr id="tab${status.count}"  align='center'> 
	 	<td class="">${status.count}</td>
		<td class=""><textarea maxlength='5000' onchange='this.value=this.value.substring(0, 5000)' onkeydown='this.value=this.value.substring(0, 5000)' onkeyup='this.value=this.value.substring(0, 5000)'  class='t_full' name='val${status.count*3-2}' placeholder='这里填内容'>${item.one}</textarea></td>
	    <td class=""><textarea maxlength='5000' onchange='this.value=this.value.substring(0, 5000)' onkeydown='this.value=this.value.substring(0, 5000)' onkeyup='this.value=this.value.substring(0, 5000)'  class='t_full' name='val${status.count*3-1}' placeholder='这里填内容'>${item.two}</textarea></td>
	    <td class=""><textarea maxlength='5000' onchange='this.value=this.value.substring(0, 5000)' onkeydown='this.value=this.value.substring(0, 5000)' onkeyup='this.value=this.value.substring(0, 5000)'  class='t_full' name='val${status.count*3}' placeholder='这里填内容'>${item.three}</textarea></td>
	    <td class=""><a href='#' id='close' onclick='deltr(${status.count},"tab")'></a></td>	 
	</tr>
 	</c:forEach>
 </c:if>
</table>

<div class="div_btn t_left">
	<input type="button" id="add_btn" onclick="addtr('tab')" value="增加"/>
</div>
<table align="center" border="0" cellpadding="0"
				cellspacing="0">
</table>

<div class="div_btn">
	<input type="button" class="selectBtn" id="submit" style="" value="提交" onclick="sub();"/>
						&nbsp;&nbsp;
						<input type="button" class="selectBtn" name="reback" value="返回"
							onclick="bakIframe('${map.label}','${map.bakurl}')">
						&nbsp;&nbsp;	
						<input type="button" class="selectBtn"  value="查看流程图"
							onclick="showProcess_sig('bmgz_process_sig','流程查看','${map.processId}','${map.orderId}',0,500,650)"/>	
</div>
</form>
	<div id="view">
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
<div id="bmgz_process_sig">
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
	<script type="text/javascript">

  function sub(){
  if($.trim($("#first").val()).length == 0){
  	showMsg("月份不能为空");
  	return;
  }
  if($.trim($("#second").val()).length == 0){
  	showMsg("月份不能为空");
  	return;
  }
  if($.trim($("#third").val()).length == 0){
	 showMsg("月份不能为空");
  	return;
  }	
   getData();
}
    
    function getData(){
    	var tr = $("#tab tr");
    	//var data = new Array();
    	if(tr.length == 1){
    		showMsg("内容不能为空");
			return false;
    	}
    	for (var i = 1; i < tr.length; i++) {
    		var td = $(tr[i]).children("td");
			for (var j = 1; j < td.length - 1; j++) {
				if($.trim($(td[j]).children("textarea").val()).length == 0){
					showMsg("内容不能为空");
					return false;
				}
			}
		}
	    bmgz_user.changeAuth();  
    }

     $(function(){
    	 //载入流程图之前要手动隐藏，不然ie加载不完整
    	$("#bmgz_process_sig").hide();
     	if("${map.orderId}" != ""){
     		//makeTable("tabView", 0);
     		//makeTable("tab", 1);
     		$("#begin").click();
     			$("#submit").val("重新提交");
	    		$("#submit").attr("style","color:red");
	    		$("#detail").html("【被退回】");
	    		$("#detail").attr("style","color:red");
     	}else{ 
     		$("#view").hide();
     	}
    	 $("input[type=radio][value=${map.qtype}]").attr("checked",'checked'); 
    	 $("input:radio").attr("disabled","disabled"); 
    	  $("textarea").each(function(){
    	  		if($.trim($(this).val()) == '不同意'){
    	  			$(this).val("不同意(${map.msg})");
    	  		}
	      });
    }); 
   
    
  //表格删除与增加
    var deltr =function(index,table){
        $("tr[id='"+table+index+"']").remove();//删除当前行
        var tr = $("#"+table+" tr");
        var len = tr.length - index;
        var nextIndex = index + 1;
        for(var i = 0;i<len;i++){
           $("#"+table+" tr[id='"+table+Number(nextIndex+i)+"'] td").eq(0).text(Number(nextIndex+i-1));
           $("#"+table+" tr[id='"+table+Number(nextIndex+i)+"'] td").eq(1).children("textarea").attr("name","val"+Number((nextIndex+i-1)*3-2));
           $("#"+table+" tr[id='"+table+Number(nextIndex+i)+"'] td").eq(2).children("textarea").attr("name","val"+Number((nextIndex+i-1)*3-1));
           $("#"+table+" tr[id='"+table+Number(nextIndex+i)+"'] td").eq(3).children("textarea").attr("name","val"+Number((nextIndex+i-1)*3));
           $("#"+table+" tr[id='"+table+Number(nextIndex+i)+"'] td").eq(4).html("<a href='#' id='close' onclick='deltr("+Number(nextIndex+i-1)+",&quot;"+table+"&quot;)'></a>");
           $("#"+table+" tr[id='"+table+Number(nextIndex+i)+"']").attr("id",table+(nextIndex+i-1));
        } 
    };
    		 


       //增加<tr/>add_btn
    var addtr = function(table){
        var _len = $("#"+table+" tr").length - 1;        
        var num = Number(_len + 1);
        var name = num * 3 - 2;
        $("#"+table).append("<tr id="+table+num+" align='center'>"
        				+"<td class='h50'>"+num+"</td>"
                        +"<td class='h50'><textarea maxlength='5000' onchange='this.value=this.value.substring(0, 5000)' onkeydown='this.value=this.value.substring(0, 5000)' onkeyup='this.value=this.value.substring(0, 5000)'  class='t_full' name='val"+name+"' placeholder='这里填内容'></textarea></td>"
                        +"<td class='h50'><textarea maxlength='5000' onchange='this.value=this.value.substring(0, 5000)' onkeydown='this.value=this.value.substring(0, 5000)' onkeyup='this.value=this.value.substring(0, 5000)'   class='t_full' name='val"+Number(name+1)+"'  placeholder='这里填内容'></textarea></td>"
                        +"<td class='h50'><textarea maxlength='5000' onchange='this.value=this.value.substring(0, 5000)' onkeydown='this.value=this.value.substring(0, 5000)' onkeyup='this.value=this.value.substring(0, 5000)'   class='t_full' name='val"+Number(name+2)+"'  placeholder='这里填内容'></textarea></td>"
                        +"<td class='h50'><a href=\'#\' id='close' onclick=\'deltr("+num+",&quot;"+table+"&quot;)\'></a></td>"
                        +"</tr>");           
       };
       
       
             
    var bmgz_user = {
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
						 //提交按钮背景设为灰色，改为不可点击状态
						$("#submit").attr("style", "background-color:gray");
						 $("#submit").attr("disabled", true);
						bmgz_user.saveAuth();
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
        	url:"bmgz!process.action",
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
						
							
							if(com == 2){
								url = "borrow!view.action";
								 data = {"orderId" : orderId,"processId":pid,"path":"${map.path}"};
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
							    $("#flowTable").append(result_str);
							    }else{
							    $("#formTable").append(result);
							    }
							    $("#view .tab li").click(function()
							    {
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
  </body>
</html>
