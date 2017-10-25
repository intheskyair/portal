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
	<p class="btt">市级机关内设机构民主测评表</p>
	<!-- <p style="width:1000px; height:20px;text-align:left;margin: auto;" >
		 单位名称：常州市水利局
	</p> -->
	<table class="table01" cellpadding="0" cellspacing="0">
	 <tr class="blue_y">
	    <td rowspan="2" class="name_td">单位<br/>名称</td>
	    <td rowspan="2" class="eval_td">评价<br/>等次</td>
	    <td colspan="4">履行职责情况</td>
	    <td colspan="4">内部建设情况</td>
	    <td rowspan="2" class="last_td">总体<br/>评价</td>
	 </tr>
	 <tr class="title_t blue_y">
	    <td>目标<br/>任务</td>
	    <td>依法<br/>行政</td>
	    <td>创新<br/>创优</td>
	    <td>服务<br/>职能</td>
	    <td>精神<br/>面貌</td>
	    <td>工作<br/>作风</td>
	    <td>团结<br/>协作</td>
	    <td>廉洁<br/>自律</td>
	  </tr>
	</table>
</div>
<div class="t_table_y">

</div>
<script type="text/javascript">
$(function(){
	$(".t_table_y").getData_show($(".table01"),$(".btt"));
	setY();
	$(window).resize(function() {
  		setY();
	});
});

function setY(){
	var iy = $(window).height();
	//alert(iy);
	$(".t_table_y").height(iy-180);
	//alert(iy);
}
</script>
	</body>
</html>