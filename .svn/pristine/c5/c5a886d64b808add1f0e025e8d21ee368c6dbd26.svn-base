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
<style>
.blue_x td {
    width: 58px;
    overflow: hidden;
    word-break: break-all;
    height: 30px;
    line-height: 30px;
}
.blue_y td {
    width: 58px;
    overflow: hidden;
    word-break: break-all;
}
</style>
	</head>
	<body>
<div class="title_div ">
	<p class="btt">新选拔任用干部民主评议表</p>
		<table class="table01" cellpadding="0" cellspacing="0">
  <tr class="blue_y">
    <td colspan="6">被测评干部的基本情况</td>
    <td colspan="4">对任用该干部的看法</td>
    <td colspan="3">是否存在拉票、跑官、买官行为</td>
  </tr>
  <tr class="blue_y">
    <td>姓名</td>
    <td>性别</td>
    <td>出生<br/>年月</td>
    <td>原任<br/>职务</td>
    <td>现任<br/>职务</td>
    <td>任职<br/>时间</td>
    <td>满意</td>
    <td>基本满意</td>
    <td>不满意</td>
    <td>不了解</td>
    <td>存在</td>
    <td>不存在</td>
    <td>不了解</td>
  </tr>
</table>
	</div>
<div class="t_table"></div>
<script type="text/javascript">
$(function(){
	 $("input[type='radio']").attr("disabled",true);
	 var args = window.location.search.substr(1);
		args = args.replace(/&/g,",");
		var d_arr = args.split(",");
		var obj = new Object();
		for ( i = 0; i < d_arr.length ; i++)
		{
			var d_arr_str = d_arr[i].split("=");
			obj[d_arr_str[0]] = d_arr_str[1];
		}
		
		//获取德的评测人
		$.ajax({
			type: "POST",
			url: "mzpf!getType4User.action",
			cache: false,
			data: {mainId:obj.mainid},
			success: function(data){
				var res = (new Function("","return " + data))();
				var name = JSON.parse(res.PARAVALUE);
				var sex = JSON.parse(res.SEX);
				var birthday = JSON.parse(res.BIRTHDAY);
				var oldjob = JSON.parse(res.OLDJOB);
				var newjob = JSON.parse(res.NEWJOB);
				var jobtime = JSON.parse(res.JOBTIME);
				var html = "<table  class='table01' cellpadding='0' cellspacing='0'>";
				for(var i = 0 ; i < name.length;i ++ ){
					html +="<tr id='row"+i+"' class= 'blue_x'>";
					html +="<td>"+name[i]+"</td>";
					html +="<td>"+sex[i]+"</td>";
					html +="<td>"+birthday[i]+"</td>";
					html +="<td>"+oldjob[i]+"</td>";
					html +="<td>"+newjob[i]+"</td>";
					html +="<td>"+jobtime[i]+"</td>";
						html +="<td><input type='checkbox' class='td_num_"+i+"'/></td>";
						html +="<td><input type='checkbox' class='td_num_"+i+"'/></td>";
						html +="<td><input type='checkbox' class='td_num_"+i+"'/></td>"
						html +="<td><input type='checkbox' class='td_num_"+i+"'/></td>"
						html +="<td><input type='checkbox' class='td_num_"+i+"_f'/></td>"
						html +="<td><input type='checkbox' class='td_num_"+i+"_f'/></td>"
						html +="<td><input type='checkbox' class='td_num_"+i+"_f'/></td>"
					html +="</tr>";
				}
				html +="</table>";
				$(".t_table").html(html);
				$("input[type='checkbox']").attr("disabled",true);
			}
		});
			if(obj.view_type == 1){
				$.ajax({
					type: "get",
					url: "mzpc!getKHPF.action",
					cache: false,
					data: {mainid:obj.mainid,radom:obj.radom},
					success: function(data){
						 var res = (new Function("","return " + data))();
						var res1 = JSON.parse(res.PARA_VALUE);
						for(var i = 0; i < res1.length; i ++){
							var name = res1[i].NAME;
							var list = res1[i].list;
							var name1;
							$("#row"+i).children("td").each(function(index){
									if($(this).children("input[type='checkbox']").length){
										if(list[index-6] == 1){
											$(this).children("input[type='checkbox']").attr("checked","checked");
										}
									}
								
							});
							/* var res2 = res1[i].list; 
							for(var j = 0; j < res2.length; j++){
								$("input[name='td_"+i+"_"+j+"'][value='"+res2[j]+"']").attr("checked","checked");
							}
							 */
						}
					}
				});
				
				
				
			}
		

	});
</script>
	</body>
</html>
