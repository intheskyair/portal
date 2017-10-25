<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
  <%@ include file="/common/meta.jsp"%>
    <title>考勤查看</title>
	<style type="text/css">
		.t_full{
			width: 100%;
			height: 100%;
			border: 0px;
			resize:none;
		}
		textarea {
			padding:6px;
			line-height: 18px;
    		overflow-y: auto;
		}
	</style>
	
  </head>
  <body style="overflow-y: scroll;">
	<div>
	<p class="btt">常州市水利局<span id="yearS">${map.year}</span>年<span id="monthS">${map.month}</span>月份考勤表</p>
	<table style="width:98%; margin-top:20px;" cellpadding="0" cellspacing="0">
		<tr>
 			<td  style=" text-align:left">部门：<input class="nyr"  style="width:250px;" type="text" id="dw" name="dw" value="${admin.org_name}" readonly="readonly"/></td>
		</tr>
	</table>
	<br/>
	<div  style="width:100%" id="tabs">
		<table border="1"  class='table-all' cellspacing='0' align='center' style='margin-top:5px; width:100%; background: #d0e9fe;'>
			<tr>
				<td>姓名</td>
				<c:forEach var="x" begin="1" end="${days}">
					<td colspan="2">${x}</td>
				</c:forEach>
			</tr>
			<c:forEach items="${list}" var="tmp">
				<tr>
					<td>${tmp.TRUENAME}</td>
					<c:forEach items="${tmp.dayList}" var="tmp1">
							<c:if test="${tmp1 == 0}"><td>√</td></c:if>
							<c:if test="${tmp1 == 1}"><td><span style="color: blue">★</span></td></c:if>
							<c:if test="${tmp1 == 2}"><td><span style="color: red">※</span></td></c:if>
							<c:if test="${tmp1 == 3}"><td><span style="color: red">△</span></td></c:if>
							<c:if test="${tmp1 == 4}"><td><span style="color: red">●</span></td></c:if>
							<c:if test="${tmp1 == 5}"><td><span style="color: red">×</span></td></c:if>
					</c:forEach>
				</tr>
			
			</c:forEach>
			<tr>
				<td>本月记事、汇总本科人员出勤、请假、调休</td>
				<td colspan="${days * 2}">${map.msg}</td>
			</tr>
			<tr>
				<td>说明</td>
				<td colspan="${days * 2}">出勤 √，星期天或节日<span style="color: blue;">★</span>，公休假<span style="color: red;">※</span>，事假<span style="color: red;">△</span>，病假<span style="color: red;">●</span>，旷工<span style="color: red;">×</span></td>
			</tr>
		</table>
	</div>
	</div>

  </body>
</html>
