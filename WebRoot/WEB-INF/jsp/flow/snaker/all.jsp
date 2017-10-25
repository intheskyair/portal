<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
    	<link rel="stylesheet" href="${ctx}/styles/CleverTabs/context/themes/base/style.css" type="text/css" />
    	<link rel="stylesheet" href="${ctx}/styles/CleverTabs/context/themes/base/jquery-ui.css" type="text/css" />
	    <script src="${ctx}/styles/CleverTabs/scripts/jquery.js" type="text/javascript"></script>
	    <script src="${ctx}/styles/CleverTabs/scripts/jquery-ui.js" type="text/javascript"></script>
	    <script src="${ctx}/styles/CleverTabs/scripts/jquery.contextMenu.js" type="text/javascript"></script>
	    <script src="${ctx}/styles/CleverTabs/scripts/jquery.cleverTabs.js" type="text/javascript"></script>
	    <script type="text/javascript">
        var tabs;
        var taskName = "${task.taskName}";
        $(function () {
            tabs = $('#tabs').cleverTabs();
            $(window).bind('resize', function () {
                tabs.resizePanelContainer();
            });
             var diagramTab = tabs.add({
            	url: '${ctx}/process/designer?processId=${processId}&status=0',
            	label: '流程图',
            	locked: false
            }); 
            diagramTab.activate();
            $.ajax({
				type:'GET',
				url:"${ctx}/flow/node",
				data:"processId=${processId}&orderId=${orderId}&taskId=${taskId}",
				async: false,
				globle:false,
				error: function(){
					alert('数据处理错误！');
					return false;
				},
				success: function(data) {
					var curTab;
					var iscurrent = false;
					data = $.parseJSON(data);
					var len = 1;
					if("${orderId}" != '' && "${taskId}" != '' ){
						len = data.length;
					}
					for(var i = 0; i < len; i++) {
						var node = data[i];
						var iframeUrl;
						if(node.form.indexOf("?")>0)
						{
						   
						   iframeUrl = '${ctx }' + node.form + '&processId=${processId}&orderId=${orderId}&taskName=' + node.name;
						}
						else
						{
						   iframeUrl = '${ctx }' + node.form + '?processId=${processId}&orderId=${orderId}&taskName=' + node.name;
						}												
						if(taskName == node.name || (taskName == '' && i == 0)) {
							iscurrent = true;
							iframeUrl += '&taskId=${taskId}&readonly=1';
						} else {
							iscurrent = false;
							iframeUrl += '&readonly=0';
						}
						//alert(iframeUrl);
			            var tab = tabs.add({
			                url: iframeUrl,
			                label: node.displayName,
			                locked: true
			            });
			            tab.activate();
			            if(iscurrent) {
			            	curTab = tab;
			            	tab.mark();
			            }
					}
					if(curTab) curTab.activate();
				}
			});
        });
    	</script>
	</head>
	<body>
		<table border="0" width=100% align="center">
    		<tr>
        		<td align="center" class="snaker_title">${process.displayName }
        			<hr width=100% size=2 color="#71B2CF">
        		</td>
    		</tr>
		</table>
		<c:if test="${order != null }">
		<table border="0" width=98% align="center" style="margin-top:5">
    		<tr>
        		<td align="left">
        			<font color="blue">编号：</font><font color="#800080">${order.orderNo }</font> &nbsp;
        			<font color="blue">派单时间：</font><font color="#800080">${order.createTime }</font>&nbsp;
				</td>
			</tr>
		</table>
		</c:if>
	    <div id="tabs" style="margin: 0px;">
	        <ul>
	        </ul>
	    </div>
	</body>
</html>
