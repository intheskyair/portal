<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/compart/common.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/views/common/js/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/views/common/js/json2.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/views/common/js/slj_common.js"></script>
	</head>
	<body>
<div class="title_div ">
	<p class="btt"></p>
	<div class="btn_div">
		<input type="button" class="selectBtn" value="提交" />
	        <span id="check_num_info" style="color:red;float: left;"></span>
	
	</div>
	<table class="table01" cellpadding="0" cellspacing="0">
		 <tr class="blue_y">
			<td rowspan="2" class="name_td" >姓名</td>
			<td rowspan="2" class="eval_td">二<br/>级<br/>指<br/>标</td>
			<td colspan="4" >德</td>
			<td colspan="4" >能</td>
			<td colspan="4" >勤</td>
			<td rowspan="2" class="last_td ">总<br/>体<br/>评<br/>价</td>
		</tr>
		<tr class="title_t blue_y">
			<td colspan="4">工作计划制定、完成工作的数量、质量、效率和所产生的效益</td>
			<td colspan="4">工作纪律、工作责任心、工作态度、工作作风等方面的表现</td>
			<td colspan="4">执行廉洁从政、改进工作作风、密切联系群众等规章制度情况</td>
		</tr>
</table>
</div>
<div class="t_table_y"></div>
<script type="text/javascript">
$(function(){
	$(".t_table_y").getData_show($(".table01"),$(".btt"),"edit",$(".selectBtn"));
	setY();
	$(window).resize(function() {
  		setY();
	});
});

function setY(){
	var iy = $(window).height();
	//alert(iy);
	$(".t_table_y").height(iy-280);
	//alert(iy);
}
</script>	
</body>
</html>
