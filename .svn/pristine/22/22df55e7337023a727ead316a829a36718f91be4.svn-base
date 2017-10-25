<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<title>借款流程</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/snaker/snaker.util.js" type="text/javascript"></script>
		 		<script type="text/javascript">
    function sub(){
    	var amount = $("#amount").val().trim();
    	var description = $("#description").val().trim();
    	if (amount.length ==0||isNaN(amount)){
    		alert("请输入正确借款数额!");
			return;
		}
    	if (description.length ==0){
    		alert("请输入借款理由!");
			return;
		}
    	if (description.length > 50){
    		alert("借款理由请输入少于50个汉字!");
			return;
		}
        $.ajax({
        	url:"flow!process.action",
        	type:"POST",
        	cache: false,
    		data:$("#inputForm").serialize(),
    		success: function(msg){
    			if("success" === msg){
    				alert("提交成功!");
    				parent.location = "process!proceList.action";
    			}
    		}      	
        });     
    }
</script>
	</head>

	<body>
		<form id="inputForm" action="${ctx }/flow!process.action" method="post" target="mainFrame">
			<input type="hidden" name="processId" value="${processId }" />
			<input type="hidden" name="orderId" value="${orderId }" />
			<input type="hidden" name="taskId" value="${taskId }" />
			<input type="hidden" name="borrow.id" value="${borrow.id }" />
			<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px">
				<tr>
					<td class="td_table_1"><span>借款人：</span></td>
					<td class="td_table_2" style="width: 100px; height: 26px;"><input type="text"
					class="input_240" readonly="readonly" name="S_apply.operator"
					value="${operator  }" /></td>				
					<td class="td_table_1"><span>借款日期：</span></td>
					<td class="td_table_2">
						&nbsp;${empty operateTime ? borrow.operateTime : operateTime }
					</td>
				</tr>
				<tr>
					<td class="td_table_1"><span>借款数额：</span></td>
					<td class="td_table_2" colspan="3">
						<input type="text" class="input_240" id="amount" name="amount" value="${borrow.amount }" />元
					</td>
				</tr>
				<tr>
					<td class="td_table_1"><span>借款理由：</span></td>
					<td class="td_table_2" colspan="3">
						<textarea class="input_textarea_320" id="description" name="description">${borrow.description }</textarea>
					</td>
				</tr>
				<tr>
				<td class="td_table_1"><span>部门经理：</span></td>
				<td class="td_table_2" colspan="3">
					<select class="input_240" name="S_approveDept.operator"
					style="width: 100px; height: 26px;">
						<c:forEach items="${approveDeptList }" var="dept">
							<option value=${dept.USERNAME}>${dept.AUTHNAME}${dept.TRUENAME}</option>
						</c:forEach>					
				</select>
				</td>
			</tr>
			<tr>
				<td class="td_table_1"><span>总经理：</span></td>
				<td class="td_table_2" colspan="3">
					<select class="input_240" name="S_approveBoss.operator"
					style="width: 100px; height: 26px;">
						<c:forEach items="${approveBossList }" var="boss">
							<option value=${boss.USERNAME}>${boss.AUTHNAME}${boss.TRUENAME}</option>
						</c:forEach>
				</select>
				</td>
			</tr>
			</table>
			<table align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr align="left">
					<td colspan="1">
						<input type="button" class="button_70px"  value="提交" onclick="sub();"/>
						&nbsp;&nbsp;
						<input type="button" class="button_70px" name="reback" value="返回"
							onclick="history.back()">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
