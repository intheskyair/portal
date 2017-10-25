<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../system/include.jsp"%>
<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/common/css/themes/css/flexigrid/flexigrid.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/common/css/themes/css/jquery-ui-jquiGui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/common/css/themes/css/jquiGui.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/views/common/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/views/common/js/My97DatePicker/WdatePicker.js"></script>
    <head>
	<title>系统后台操作日志</title>
	<style>
	.btn{
		width:74px; 
		height:29px;
		font-size:14px;
		background-color:#3ea4e8;
		color: #fff;
	}
	.inputText{
		height: 25px;
		line-height: 25px;
		width: 150px;
	}
	.lib-1{
		font: 14px/1.5em Verdana, Arial,\5B8B\4F53, sans-serif;
		margin-right: 10px;
	}
	.div-main-1 td{
		margin-right: 10px;
	}
	.imgShrotate{
		float: left;
		margin-top: 5px;
		-webkit-transform:rotate(90deg);
		-moz-transform:rotate(90deg);
		filter:progid:DXImageTransform.Microsoft.BasicImage(rotation=3);
		display: block;
		cursor: pointer;
	}
	.imgSh{
		float: left;
		margin-top: 5px;
		cursor: pointer;
	}
	.p-content{
		margin-left:5px;
		text-align: left;
		line-height: 25px;
	}
	.p-content > span{
		color:#3ea4e8;
	}
	
</style>
	</head>
	<body style="background-color: #F0F0F0">	
		<div style="display: block;margin: 5px;overflow: hidden;">
		<%-- <div class="notes">
			<a onClick="cwin()"> 
				<img src="${pageContext.request.contextPath}/images/1.jpg" align="middle" alt="关闭左栏"></a>&nbsp;&nbsp;监控中心操作日志</div> --%>
		    <form id="form3" action="operation!operationLogList.action" method="post">
  <div class="div div-1">
					<div class="title title-1">
						<h3>
							<img src='${pageContext.request.contextPath}/images/1.jpg' onclick="showAndHide(this,'cluster_log_grid');"  class="imgShrotate">
							<font style="float: left">系统后台操作日志</font>
						</h3>
					</div> 
<div class="div-main-1" >	    
		    
		    
			<table border="0" cellpadding="0" cellspacing="0">

					<td><label class="lib-1">用户姓名:</label><input id ="cz_user" name="cz_user" maxLength="50" value="${cz_user}" class="inputText"></td>
					<td >&nbsp;&nbsp;</td>
					<td><label class="lib-1">操作时间:</label>					
					<input type="text" id="cz_time" name="cz_time" readonly class="Wdate" style="height:25px; width: 170px;" value= "${cz_time}"
					 onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
					 </td>
		<td >&nbsp;&nbsp;</td>

					<td >

						    <input type='hidden' id='nextnumber' name='nextnum' value="${nextnum}" >
						    <input type='hidden' id='updownbz' name='updownbz' value="0" class="ys">
							<input type="button" class="btn ui-state-default" value="搜 索" onclick="sub();"/>
							<input type="button" class="btn ui-state-default" id="emp_all" onclick="empAll()" value="清 空"/>
							<input type='button' id='uppage_button' value='上一页' onclick="up();" class='btn ui-state-default'>
							<input type='button' id='nextpage_button' value='下一页' onclick="down();" class='btn ui-state-default'>

					</td>

			</table>
			</div>
            <table id="log_action_table" class="tablestyle" cellspacing="0">
				<tr id="log_head">
					<th>操作名称</th>
					<th>操作url</th>
					<th>操作人</th>
					<th>操作时间</th>
				</tr>
				<s:iterator value="#request.log_list" status="t" var="item">
					<tr <s:if test="#t.Even">style='background-color:#d9ebf5;'</s:if>>
						<td id="<s:property value='#t.index'/>">${ACTION_NAME}</td>
						<td>${ACTION_URL}</td>
						<td>${CZ_USER}</td>
						<td>${CZ_TIME}</td>
					</tr>
				</s:iterator>
				<tr><td colspan="6">
						<div class="but">
							<input type='button' id='uppage_button_1' value='上一页' onclick="up();" class='btn'>
							<input type='button' id='nextpage_button_1' value='下一页' onclick="down();" class='btn'>
						</div>
					</td>
				</tr>
			</table>
		</form>
		</div>
       <script type="text/javascript">  
		
		   function sub()
		   {      
		       $('#nextnumber').val(0);
			   form3.submit();		
		   }	
		   
		   function empAll()
			{
				document.getElementById("cz_user").value = "";
				document.getElementById("cz_time").value = "";
				//location='log!logForPortal.action';
			}
		   
		   function down()
		   {      
		       //document.getElementsByID("updownbz")=0;
		       $('#updownbz').val(0);
			   form3.submit();		
		   }	
		   
		   function up()
		   {      
		       //document.getElementsByID("updownbz")=1;
		       $('#updownbz').val(1);
			   form3.submit();		
		   }		
		</script>
	</body>
</html>
