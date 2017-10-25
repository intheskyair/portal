<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<title>审批</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/snaker/dialog.js" type="text/javascript"></script>
		 		<script type="text/javascript">
    function sub(){
    	var description = $.trim($("#description").val());
    	if (description.length ==0){
    		alert("请输入审批意见!");
			return;
		}
    	if (description.length > 50){
    		alert("审批意见请输入少于50个汉字!");
			return;
		}    	
        $.ajax({
        	url:"flow!doApproval.action",
        	type:"POST",
        	cache: false,
    		data:$("#inputForm").serialize(),
    		success: function(msg){
    			if("success" === msg){
    				parent.location = "task!active.action";
    			}
    		}      	
        });     
    }
</script>
	</head>

	<body>
		<form id="inputForm" action="${ctx }/flow/doApproval" method="post" target="mainFrame">
			<input type="hidden" name="processId" value="${processId }" />
			<input type="hidden" name="orderId" value="${orderId }" />
			<input type="hidden" name="taskId" value="${taskId }" />
			<input type="hidden" name="taskName" value="${taskName }" />
			<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0" style="margin-top: 0px">
				<tr>
					<td class="td_table_1">
						<span>审批结果：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<input type="radio" name="method" value="0" checked="checked"/>同意
						<input type="radio" name="method" value="-1"/>不同意
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>审批意见：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<textarea class="input_textarea_320" id="description" name="description"></textarea>
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
