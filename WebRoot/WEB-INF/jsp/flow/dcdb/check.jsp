<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head> 
  <%@ include file="/common/meta.jsp"%>
    <title>部门工作月报表</title>
    <script type="text/javascript" src="${ctx}/views/common/js/json2.js"></script>
	 <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquery-ui-jquiGui.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquiGui.css"/>
    <script src="${ctx}/views/common/js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/common.js"></script>
	<script src="${ctx}/styles/js/snaker/snaker.util.js" type="text/javascript"></script>
	<style type="text/css">
		.t_full{
			width: 100%;
			height: 100%;
			border: 0px;
			resize:none;
			overflow-y:visible;
		}
		

		.div_btn{
			width: 98%;
			margin:0 auto; 
			text-align:center;
			margin-top: 10px;
		}
		.t_left{
			text-align: left;
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
		
		
	</style>
	<script type="text/javascript">
  function sub(){
	  var tr = $("#tab tr");
  	for (var i = 2; i < tr.length; i++) {
  		var td = $(tr[i]).children("td"); 
  		if(td.length != 1){
  			for (var j = 1; j < td.length - 1; j++) {
  				if($.trim($(td[j]).children("textarea").val()).length == 0){
  					showMsg("内容不能为空");
  					return false;
  				}
				}
  		}
		}
			if(confirm("确认提交")){
				
			$.ajax({
	        	url:"dcdb!process.action",
	        	type:"POST",
	        	cache: false,
	    		data:$("#inputForm").serialize(),
	    		success: function(msg){
	    			if("success" === msg)
	    			{
	    				alert("提交成功");	    				
	    				bakIframe("${map.label}","${map.bakurl}")
	    			}
	    		}      	
        	});   
			}  		
           
    }
    

     $(function(){
    	$("#tab tr").attr("align","center");
       /*  var val = $.parseJSON('${map.jsonV}');
        var array = val.jsonV;
     	for(var i = 0;i < array.length ; i ++){
     		if(i == 0){
     			$("#tab").append("<tr><td class='h30' colspan='5' style='color: red;''>业务类（70分）</td></tr>");
     		}else{
     			$("#tab").append("<tr><td class='h30' colspan='5' style='color: red;''>公共类（30分）</td></tr>");
     		} 
	     		var tdval = array[i];
	     		for(var j = 0 ; j < tdval.length ; j++){
	     				$("#tab").append("<tr id="+i.toString()+j+"></tr>");
	     				$("#"+i.toString()+j).append("<td>"+Number(j+1)+"</td>");
						$("#"+i.toString()+j).append("<td class='h50'><textarea  class='t_full'>"+tdval[j]+"</textarea></td>");
						$("#"+i.toString()+j).append("<td class='h50'><textarea  class='t_full'></textarea></td>");
						$("#"+i.toString()+j).append("<td class='h50'><textarea  class='t_full'></textarea></td>");
	     		}
     	} */
    }); 
  
   
   
</script>	
  </head>
  <body style="overflow-y: auto;">
  <input type="hidden" value="${step}" id="step">
  <form id="inputForm" action="" method="post" target="mainFrame">
 	<input type="hidden" id="processId" name="processId" value="${map.processId}" />
	<input type="hidden" id="orderId" name="orderId"  value="${map.orderId}" />
	<input type="hidden" id="taskId" name="taskId" value="${map.taskId}" />
	<input type="hidden" id="jsonV" name="jsonV1"/>
	<p class="btt">机关事业单位目标管理责任制完成情况督查表</p>
<table style="width:1000px; margin-top:20px;" cellpadding="0" cellspacing="0">
<tr>
 <td  style=" text-align:left">填报单位：<input class="nyr" style="width: 250px" type="text" id="dw" value="${map.tbdw}" readonly="readonly"/></td>
 <td style=" text-align:right">日期:
 	<input type="text"  readonly="readonly" value="${map.year}" class="nyr"/>年 
 	<input type="text"  readonly="readonly" value="${map.month}" class="nyr"/>月
 	<input type="text"  readonly="readonly" value="${map.day}" class="nyr"/>日</td>
</tr>
</table>
<br/>

<table  id="tab" class="table-all" cellspacing="0" align="center" style="margin-top:5px; ">
 <tr>
 	<td class="h30">序号</td>
	<td class="h30">目标考核内容</td>
    <td class="h30">工作目标任务开展及完成情况说明</td>
    <td class="h30">备注</td>
 </tr>
  <tr>
	<td class="h30" colspan="5" style="color: red;">业务类（70分）</td>
 </tr>
 <c:forEach items="${tabList}" var="item" varStatus="status">
   <tr id="tab${status.count}" align="center">
 	<td class="h50">${status.count}</td>
 	<td class="h50"><textarea class="t_full"  readonly="readonly">${item.one}</textarea></td>
 	<td class="h50"><textarea name="tabval${status.count*3-1}" maxlength='5000' onchange='this.value=this.value.substring(0, 5000)' onkeydown='this.value=this.value.substring(0, 5000)' onkeyup='this.value=this.value.substring(0, 5000)' class="t_full"></textarea></td>
 	<td class="h50"><textarea name="tabval${status.count*3}" maxlength='5000' onchange='this.value=this.value.substring(0, 5000)' onkeydown='this.value=this.value.substring(0, 5000)' onkeyup='this.value=this.value.substring(0, 5000)' class="t_full"></textarea></td>
 	</tr>
 
 </c:forEach>
  <tr>
	<td class="h30" colspan="5" style="color: red;">公共类（30分）</td>
 </tr>
  <c:forEach items="${tabGgList}" var="item" varStatus="status">
   <tr id="tab${status.count}" align="center">
 	<td class="h50">${status.count}</td>
 	<td class="h50"><textarea class="t_full"  readonly="readonly">${item.one}</textarea></td>
 	<td class="h50"><textarea name="tabGgval${status.count*3-1}" maxlength='5000' onchange='this.value=this.value.substring(0, 5000)' onkeydown='this.value=this.value.substring(0, 5000)' onkeyup='this.value=this.value.substring(0, 5000)' class="t_full"></textarea></td>
 	<td class="h50"><textarea name="tabGgval${status.count*3}" maxlength='5000' onchange='this.value=this.value.substring(0, 5000)' onkeydown='this.value=this.value.substring(0, 5000)' onkeyup='this.value=this.value.substring(0, 5000)' class="t_full"></textarea></td>
 	</tr>
 
 </c:forEach>
 </table>
<p></p>
<div class="div_btn">
	<input type="button" class="selectBtn"  value="提交" onclick="sub();"/>
						&nbsp;&nbsp;
						<input type="button" class="selectBtn" name="reback" value="返回"
							onclick="bakIframe('${map.label}','${map.bakurl}')">
</div>
</form>
  </body>
</html>
