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
    <style type="text/css">
    	.t_full{
			width: 90%;
			height:100px;
			border: 0px;
			resize:none;
		}
		    	 .t_full_y{

			border: 0px;
			resize:none;
			text-align: left;
			text-indent: 2em;
			margin: 5px;
			height: 96px;
    overflow-y: auto;
		} 
		textarea {
padding:6px;
line-height: 18px;
    overflow-y: auto;
}
		.h30{width:20%; }
    </style>
	<script type="text/javascript">
</script>	
  </head>
  <body>
	<p class="btt">部门工作月报表</p>
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
<table id="tabview"  class="table-all" cellspacing="0" align="center" style="margin-top:5px; ">
 <tr>
	<td class="h30">${map.first}月份主要工作安排</td>
    <td class="h30">${map.second}月份主要工作完成情况</td>
    <td class="h30">${map.third}月份主要工作安排</td>
 </tr>
 <c:forEach items="${list}" var="item" varStatus="status">  
  	<tr id="tabview${status.count}" align='center'>  
    <td class="h50"><textarea class='t_full' readonly="readonly">${item.one}</textarea></td>  
    <td class="h50"><textarea class='t_full' readonly="readonly">${item.two}</textarea></td>  
    <td class="h50"><textarea class='t_full' readonly="readonly">${item.three}</textarea></td>  
  </tr>  
</c:forEach>  
</table>
<table  class="table-all" cellspacing="0" align="center" style="margin-top:5px;">
	<tr>
 		<td class="h30">分管领导意见</td>
 		<td ><textarea class='t_full_y' readonly="readonly">${map.rect3method == '0' ? '同意' : ''}${map.rect3method == '-1' ? '不同意' : ''}<c:if test="${not empty map.rect3suggest}">(${map.rect3suggest})</c:if></textarea></td>
 		</tr>
 		<tr>
 		<td class="h30">办公室意见</td><td><textarea class='t_full_y' readonly="readonly">${map.rect4method == '0' ? '同意' : ''}${map.rect4method == '-1' ? '不同意' : ''}<c:if test="${not empty map.rect4method}">(${map.rect4suggest})</c:if></textarea></td>	</tr>

	<tr>
		<td class="h30">领导审核</td>
 		
 		<td ><textarea class='t_full_y' readonly="readonly">${map.rect5method == '0' ? '同意' : ''}${map.rect5method == '-1' ? '不同意' : ''}<c:if test="${not empty map.rect5suggest}">(${map.rect5suggest})</c:if></textarea></td>
	</tr>
</table>
  </body>
</html>
