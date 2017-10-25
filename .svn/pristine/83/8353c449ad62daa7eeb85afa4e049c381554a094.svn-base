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
	<div class="btn_div">
			<input type="button" class="selectBtn"  value="提交" />
	        <span id="check_num_info" style="color:red;float: left;"></span>
	<input id="radom" type="hidden"/>
	<input id="mainid" type="hidden"/>
	</div>
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
	 var args = window.location.search.substr(1);
		args = args.replace(/&/g,",");
		var d_arr = args.split(",");
		var obj = new Object();
		for ( i = 0; i < d_arr.length ; i++)
		{
			var d_arr_str = d_arr[i].split("=");
			obj[d_arr_str[0]] = d_arr_str[1];
		}
		$("#radom").val(obj.radom);
		$("#mainid").val(obj.mainid);
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
				var html = "<table id='tab5'  class='table01' cellpadding='0' cellspacing='0'>";
				for(var i = 0 ; i < name.length;i ++ ){
					html +="<tr id='row"+i+"' class= 'blue_x'>";
					html +="<td>"+name[i]+"</td>";
					html +="<td>"+sex[i]+"</td>";
					html +="<td>"+birthday[i]+"</td>";
					html +="<td>"+oldjob[i]+"</td>";
					html +="<td>"+newjob[i]+"</td>";
					html +="<td>"+jobtime[i]+"</td>";
					
						html +="<td><input type='checkbox' class='td_num_"+i+"'></td>";
						html +="<td><input type='checkbox' class='td_num_"+i+"'></td>";
						html +="<td><input type='checkbox' class='td_num_"+i+"'></td>"
						html +="<td><input type='checkbox' class='td_num_"+i+"'></td>"
						html +="<td><input type='checkbox' class='td_num_"+i+"_f'></td>"
						html +="<td><input type='checkbox' class='td_num_"+i+"_f'></td>"
						html +="<td><input type='checkbox' class='td_num_"+i+"_f'></td>"
					html +="</tr>";
				}
				html +="</table>";
				$(".t_table").html(html);
				$("input[type='checkbox']").click(function(){
					var box_class = $("." + $(this).attr("class"));
					if($(this).attr('checked')){
						box_class.removeAttr('checked');
						$(this).attr('checked','checked');
					}else{
						$(this).attr('checked','checked');
					}
				});
			}
		})

		
		$(".selectBtn").click(function(){
			var array = new Array();
			$("#tab5 tr").each(function(index){
				var obj = new Object();
				var arr2 = new Array();
				var status = 0;
				$(this).children("td").each(function(index1){
					if(index1 == 0){
						obj.NAME = $(this).text();
					}
					 if($(this).children("input[type='checkbox']").length){
						 if($(this).children("input[type='checkbox']").prop("checked")){
								arr2.push(1);
								status ++;
							}else{
								arr2.push(0);
							}	 
					 }
					
				});
				if(status != 2){
					$("#check_num_info").html("["+obj.NAME+"]有未选择的选项");
					return false;
				}else{
					$("#check_num_info").html("");
				}
				obj.list = arr2;
				array.push(obj);
			});
			if($("#tab5 tr").length == array.length){
				$.ajax({
					type: "POST",
					url: "mzpc!saveType3pdf.action",
					cache: false,
					data: {"json":JSON.stringify(array),"mainid":$("#mainid").val(),"radom":$("#radom").val()},
					success: function(data){
						var res = (new Function("","return " + data))();
						if(res.msg == "ok"){
							window.parent.showMsg("提交成功！");
							window.parent.clearNewTab();
							//$(window.parent.document).find("#span_close_tab_" + obj.mainid).click();
						}else{
							window.parent.showMsg(res.msg);
						}
					}
				})
			}
			
			
		});
		
	});
</script>
	</body>
</html>
