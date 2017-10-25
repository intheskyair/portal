<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
	    <script src="${ctx}/styles/CleverTabs/scripts/jquery.js" type="text/javascript"></script>
	    <script src="${ctx}/styles/CleverTabs/scripts/jquery-ui.js" type="text/javascript"></script>
		<%--
    	<link rel="stylesheet" href="${ctx}/styles/CleverTabs/context/themes/base/style.css" type="text/css" />
    	<link rel="stylesheet" href="${ctx}/styles/CleverTabs/context/themes/base/jquery-ui.css" type="text/css" />
	    <script src="${ctx}/styles/CleverTabs/scripts/jquery.contextMenu.js" type="text/javascript"></script>
	    <script src="${ctx}/styles/CleverTabs/scripts/jquery.cleverTabs.js" type="text/javascript"></script>
	    --%>
	  	<script type="text/javascript" src="${ctx}/views/common/js/common.js"></script>
	    <script type="text/javascript">
        var tabs;
        var taskName = "${task.taskName}";
        $(function () {
/*             tabs = $('#tabs').cleverTabs();
            $(window).bind('resize', function () {
                tabs.resizePanelContainer();
            }); */
            
            $.ajax({
				type:'GET',
				url:"${ctx}/borrow/node",
				data:"processId=${processId}&orderId=${orderId}&taskId=${taskId}",
				async: false,
				globle:false,
				error: function(){
					alert('数据处理错误！');
					return false;
				},
				success: function(data) {
						data = $.parseJSON(data);
						if(data.length == 0){
							showIframeMsg("流程已处理(原因：会签:被驳回,非会签:被处理)");
							return;
						}
						
						for(var i = 0; i < data.length; i++) {
						var node = data[i];
						var iframeUrl;
						var form_;
						iframeUrl = '${ctx}' + node.form + '&processId=${processId}&orderId=${orderId}&taskId=${taskId}&taskName=' + node.name;
// 						alert(iframeUrl);
						 if(node.form.indexOf("/")==0)
						    {
						       form_ = node.form;
						    }
						    else
						    {
						      form_ = "/"+node.form;
						    }
						if(node.form.indexOf("?")>-1)
						{
						       iframeUrl = '${ctx}' + form_ + '&processId=${processId}&orderId=${orderId}&taskId=${taskId}&taskName=' + node.name;
						}
						else
						{
						    iframeUrl = '${ctx}' + form_ + '?processId=${processId}&orderId=${orderId}&taskId=${taskId}&taskName=' + node.name;
						}	
// 			            var tab = tabs.add({
// 			                url: iframeUrl,
// 			                label: node.displayName,
// 			                locked: true
// 			            });
// 			            tab.activate();

						//当前流程		
						window.location.href = iframeUrl+"&label="+encodeURI(encodeURI(node.displayName));
						//alert(iframeUrl);
/* 			            tabs.add({
			                url: iframeUrl,
			                label: node.displayName,
			                locked: true
			            }).activate(); */
			            
// 			            tabs.add({
// 			            	url: '${ctx}/process/designer?processId=${processId}&status=0&orderId=${orderId}&orderIdStatus=0',
// 			            	label: '流程图',
// 			            	locked: false
// 			         	}); 
				    }
				}
			});
        });
        
    	</script>
	</head>
	<body   >
		<%-- <c:if test="${order != null }">
		<table border="0" width=98% align="center" >
    		<tr>
        		<td align="left">
        			<font color="blue">编号：</font><font color="#800080">${order.orderNo }</font> &nbsp;
        			<font color="blue">派单时间：</font><font color="#800080">${order.createTime }</font>&nbsp;
				</td>
			</tr>
		</table>
		</c:if> 
	    <div id="tabs" style="margin:0px;padding:0px;overflow:hidden!important;height:99.6%">
	        <ul >
	        </ul>
	    </div>
	    --%>
	</body>
</html>
