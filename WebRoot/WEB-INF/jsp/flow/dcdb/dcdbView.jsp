<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head> 
  <%@ include file="/common/meta.jsp"%>
    <title>督察督办</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css"/>
	<style type="text/css">
		.t_full{
			width: 100%;
			height: 100%;
			border: 0px;
			resize:none;
			text-align: center;
			overflow-y:visible;
		}
		
		.div_btn{
			width: 1000px;
			margin:0 auto; 
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
	</style>
	<script type="text/javascript">
     $(function(){
    		 $("#tab tr").attr("align","center");
    		 /*var a = '${map.jsonV}';
    		var b = '${map.jsonV1}';
    		 var val;
    		 
    		if(b.length == 0){
    			val = $.parseJSON(a);
    		}else{
    			val = $.parseJSON(b);
    		}
    		
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
	    						if(b.length == 0){
	    							$("#"+i.toString()+j).append("<td class='h50'><textarea  readonly='readonly' class='t_full'>"+tdval[j]+"</textarea></td>");
		    						$("#"+i.toString()+j).append("<td class='h50'><textarea  readonly='readonly' class='t_full'></textarea></td>");
		    						$("#"+i.toString()+j).append("<td class='h50'><textarea  readonly='readonly' class='t_full'></textarea></td>");
	    						}else{
	    							$("#"+i.toString()+j).append("<td class='h50'><textarea  readonly='readonly' class='t_full'>"+tdval[j][0]+"</textarea></td>");
		    						$("#"+i.toString()+j).append("<td class='h50'><textarea  readonly='readonly' class='t_full'>"+tdval[j][1]+"</textarea></td>");
		    						$("#"+i.toString()+j).append("<td class='h50'><textarea  readonly='readonly' class='t_full'>"+tdval[j][2]+"</textarea></td>");
	    						}
	    	     		}
	         	} */
           
          
    }); 
     

 
    
</script>	
  </head>
  <body>
	<p class="btt">机关事业单位目标管理责任制完成情况督查表</p>
	
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
 	<td class="h50"><textarea class="t_full" readonly="readonly">${item.two}</textarea></td>
 	<td class="h50"><textarea class="t_full" readonly="readonly">${item.three}</textarea></td>
 	</tr>
 
 </c:forEach>
  <tr>
	<td class="h30" colspan="5" style="color: red;">公共类（30分）</td>
 </tr>
   <c:forEach items="${tabGgList}" var="item" varStatus="status">
   <tr id="tab${status.count}" align="center">
 	<td class="h50">${status.count}</td>
 	<td class="h50"><textarea class="t_full"  readonly="readonly">${item.one}</textarea></td>
 	<td class="h50"><textarea class="t_full" readonly="readonly">${item.two}</textarea></td>
 	<td class="h50"><textarea class="t_full" readonly="readonly">${item.three}</textarea></td>
 	</tr>
 
 </c:forEach>
 </table>
<br/>


<table align="center" id="tab" border="0" cellpadding="0"
				cellspacing="0">
</table>

<p></p>
  </body>
</html>
