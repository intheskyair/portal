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
	<p class="btt">机关借用人员考核评测表</p>
	<div class="btn_div">
      <input type="button" class="selectBtn" value="提交" />
	   <span id="check_num_info" style="color:red;float: left;"></span>
			
	</div>
		<table class="table01" cellpadding="0" cellspacing="0">
			<tr class="blue_y">
				<td rowspan="2" class="name_td" >姓名</td>
				<td rowspan="2" class="eval_td">二<br/>级<br/>指<br/>标</td>
				<td colspan="4" >德</td>
				<td colspan="3" >能</td>
				<td colspan="2" >勤</td>
				<td >绩</td>
				<td >廉</td>
				<td rowspan="2" class="last_td ">总<br/>体<br/>评<br/>价</td>
			</tr>
			<tr class="title_t blue_y">
				<td >思<br/>想<br/>品<br/>德</td>
				<td >明<br/>理<br/>诚<br/>信</td>
				<td >勤<br/>俭<br/>自<br/>强</td>
				<td >团<br/>结<br/>友<br/>善</td>
				<td >政<br/>策<br/>水<br/>平</td>
				<td >工<br/>作<br/>能<br/>力</td>
				<td >开<br/>拓<br/>创<br/>新</td>
				<td >敬<br/>业<br/>精<br/>神</td>
				<td >工<br/>作<br/>作<br/>风</td>
				<td >工<br/>作<br/>实<br/>绩</td>
				<td >廉<br/>洁<br/>自<br/>律</td>
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
	$(".t_table_y").height(iy-260);
	//alert(iy);
}
</script>
	</body>
</html>
