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
	<p class="btt">副职领导干部民主测评表</p>
	<div class="btn_div">
		<input type="button" class="selectBtn" value="提交" />
	        <span id="check_num_info" style="color:red;float: left;"></span>
	
	</div>
	<table class="table01" cellpadding="0" cellspacing="0">
  <tr class="blue_y">
    <td rowspan="3" class="big_td1" >姓名</td>
    <td rowspan="3" class="big_td8">二级指标</td>
    <td colspan="10" class="big_td3">评价意见</td>
  </tr>
  <tr class="blue_y">
    <td colspan="2" style="width:109px;">A</td>
    <td colspan="2" style="width:112px;">B</td>
    <td colspan="3" style="width:168px;">C</td>
    <td colspan="3" style="width:170px;">D</td>
  </tr>
  <tr  class="title_t title_d">
    <td>10分</td>
    <td>9分</td>
    <td>8分</td>
    <td>7分</td>
    <td>6分</td>
    <td>5分</td>
    <td>4分</td>
    <td>3分</td>
    <td>2分</td>
    <td>1分</td>
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

