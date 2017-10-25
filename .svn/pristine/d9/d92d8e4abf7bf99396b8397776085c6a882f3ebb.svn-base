<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/commonCSSJS.jsp"%>
<html>
    <head>
    <style>
.table-c table{
border-right:1px solid #9AC3E1;
border-bottom:1px solid #9AC3E1;
width: 100%; }
.table-c table td{
border-left:1px solid #9AC3E1;
border-top:1px solid #9AC3E1; 
font-size: 14px;
padding-left:6px;
height: 30px;
line-height: 30px;} 

.table_yxm_1 td{
    background-color: #b8def5;
    height: 26px;
    padding: 2px 4px 0;
    font-weight: bold;
    font-size: 16px;
} 
.btn-primary {
    color: #ffffff;
    background-color: #3498db;
    border-color: #258cd1;
} 
.btn {
padding: 2px 11px;
    font-size: inherit;
    line-height: 20px;
    display: inline-block;
    margin-bottom: 0;
    font-weight: normal;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    cursor: pointer;
    background-image: none;
    border: 1px solid transparent;
    border-radius: 2px;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    -o-user-select: none;
    user-select: none;
}
.btn_1 {
padding: 2px 4px;
    font-size: inherit;
    line-height: 20px;
    display: inline-block;
    margin-bottom: 0;
    font-weight: normal;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    cursor: pointer;
    background-image: none;
    border: 1px solid transparent;
    border-radius: 2px;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    -o-user-select: none;
    user-select: none;
}
    </style>
	<title>考勤登记</title>
	<script>
		function timeRecord(t_type){
			var params=new Object();
			params.t_type = t_type;
			params.uid  = "${admin.adminId}";
			$.ajax({
				type: "POST",
				url: "worklog!addWorkTime.action",
				cache: false,
				data: params,
				success: function(msg){
					var result=eval("("+msg+")");
					if (result.msg=="ok") 
					{
						alert("操作成功");
						window.location.href = "${ctx}/worklog!workRecord.action";
						
					} else 
					{
						showMsg(result.msg);
					}
				}
			});
		}
	</script>
	</head>

	<body>
	<div class="table-c">		
		<table cellpadding="0" cellspacing="0" border="0" >
			<tr class="table_yxm_1">
				<td>登记类型</td>
				<td>规定时间</td>
				<td>登记时间</td>
				<td>状态</td>
				<td>操作</td>
			</tr>
			
			
			<c:choose>
			   <c:when test="${empty zs}"> 
			       	<tr>
						<td>上班登记</td>
						<td>${work_time}</td>
						<td></td>
						<td>
						</td>
<!-- 						<td></td> -->
						<td><input type="button" onclick="timeRecord(1)" value="登记" class="btn-primary btn"/></td>
					</tr>
			   </c:when>
			   <c:otherwise>
			   		<tr>
						<td>上班登记</td>
						<td>${work_time}</td>
						<td>${zs['T_TIME']}</td>
						<td>
							<c:choose>
							   <c:when test="${zs['T_STATUS'] == 1}"> 
							       	<span style="color:#f70404">迟到  </span>
							   </c:when>
							   <c:when test="${zs['T_STATUS'] == 2}"> 
							       		正常 
							   </c:when>
							   <c:otherwise>
							   </c:otherwise>
							</c:choose>
						</td>
<!-- 						<td>${zs['T_REMARK']}</td> -->
						<td><input type="button" disabled="disabled"  value="已登记" class="btn_1"/></td>
					</tr>
			   </c:otherwise>
			</c:choose>
				
			
			
			<c:choose>
			   <c:when test="${empty xw}"> 
			       	<tr>
						<td>下班登记</td>
						<td>${off_time}</td>
						<td></td>
						<td>
						</td>
						<td><input type="button" value="登记" onclick="timeRecord(2)" class="btn-primary btn"/></td>
					</tr>
			   </c:when>
			   <c:otherwise>
			   		<tr class="table_yxm_2">
						<td>下班登记</td>
						<td>${off_time}</td>
						<td>${xw['T_TIME']}</td>
						<td>
							<c:choose>
							   <c:when test="${xw['T_STATUS'] == 1}"> 
							       		<span style="color:#f70404">早退 </span>
							   </c:when>
							   <c:when test="${xw['T_STATUS'] == 2}"> 
							       	正常 
							   </c:when>
							   <c:otherwise>
							   </c:otherwise>
							</c:choose>
						</td>
						<td><input type="button" disabled="disabled" value="已登记" class="btn_1"/></td>
					</tr>
			   </c:otherwise>
			</c:choose>
		</table>
		</div>
	</body>
</html>
