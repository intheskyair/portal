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
	<p class="btt">局机关中层干部使用期满民主测评表</p>
	<div class="btn_div">
			<input type="button" class="selectBtn" value="提交" />
	        <span id="check_num_info" style="color:red;float: left;"></span>

	</div>
	<table class="table01" cellpadding="0" cellspacing="0">
		 <tr class="blue_y">
			 <td rowspan="2" class="name_td">姓名</td>
			 <td rowspan="2" class="eval_td_type1">职务</td>
			 <td colspan="3">综合评价</td>
			 <td colspan="2" >任用建议</td>
		 </tr>
		 <tr class="title_t blue_y">
			 <td class="mzbg4_w1">胜任</td>
			 <td class="mzbg4_w2">基本胜任</td>
			 <td class="mzbg4_w1">不胜任</td>
			 <td class="mzbg4_w1">任用</td>
			 <td class="mzbg4_w1">不任用</td>
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