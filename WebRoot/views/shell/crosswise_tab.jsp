
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
	    
	    function sleep(numberMillis) { 
			var now = new Date(); 
			var exitTime = now.getTime() + numberMillis; 
			while (true) { 
			now = new Date(); 
			if (now.getTime() > exitTime) 
				return; 
			} 
		}
	    var crosswise_tabs = {};
        $(function () {
        	//if(!-[1,]){
        	//	$('#tabs').height($(document.body).height());	
        	//}else{
        	//	$('#tabs').height($(document.body).height() - 5);
        	//}
        	var submenus = ${submenu};
        	if(submenus){
        		crosswise_tabs = $('#crosswise_tabs').cleverTabs({setupContextMenu : false});
	            $(window).bind('resize', function () {
	                crosswise_tabs.resizePanelContainer();
	            });
	            for(var i = 0; i < submenus.length; i++){
	            //alert(submenus[i].MENUNAME+"|||||||||"+submenus[i].MENUURL+"|||");
	            	//window.setTimeout("eval(1);",1000);
	            	
	            	//window.setTimeout("alert(1);",10000);
	            	sleep(10);
	            	if(i == 0){
	            		crosswise_tabs.add({
			            	url: submenus[i].MENUURL,
			            	label: submenus[i].MENUNAME,
			            	locked: false
			            }).activate();//激活第一个tab
	            	}else{
	            		crosswise_tabs.add({
			            	url: submenus[i].MENUURL,
			            	label: submenus[i].MENUNAME,
			            	locked: false
			            }).deactivate();
	            	}
	            }
        	}
           
            /* 
            	 var diagramTab = crosswise_tabs.add({
		            	url: 'carManager!listCarInfo.action',
		            	label: '流程图1',
		            	locked: false
		            });
		            diagramTab.activate();
		            crosswise_tabs.add({
		            	url: '',
		            	label: '流程图2',
		            	locked: false
		            });crosswise_tabs.add({
		            	url: '',
		            	label: '流程图3',
		            	locked: false
		            });crosswise_tabs.add({
		            	url: '',
		            	label: '流程图4',
		            	locked: false
		            });crosswise_tabs.add({
		            	url: '',
		            	label: '流程图5',
		            	locked: false
		            });
            
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
						for(var i = 0; i < data.length; i++) {
						var node = data[i];
						var iframeUrl;
						var form_;
						iframeUrl = '${ctx}' + node.form + '&processId=${processId}&orderId=${orderId}&taskId=${taskId}&taskName=' + node.name;
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
			            var tab = crosswise_tabs.add({
			                url: iframeUrl,
			                label: node.displayName,
			                locked: true
			            });
			            tab.activate();
				}
				}
			}); */
        });
        
        function activeTab()
        {	
			var tab = crosswise_tabs.getTabByUrl('carManager!carApplyList.action?processId=a24f18f112a2414aae76accbe62a4cc4');	
			var tab_sq = crosswise_tabs.getTabByUrl('carManager!allCar.action?processId=a24f18f112a2414aae76accbe62a4cc4&processName=clsq');	
	        tab.activate();
	        tab.refresh();
	        tab_sq.refresh();
        }
          function activeTabAssInfo()
        {	
			var tab = crosswise_tabs.getTabByUrl('assetmanager!assetInput.action?processId=af57a3325ede4424a4d8f9868cc8c1e3&processName=assetinput');	
	        //tab.activate();
	        tab.refresh();
        }
        function refreshTabAssetOverView()
        {	
			var tab = crosswise_tabs.getTabByUrl('assetmanager!getAssetOverView.action');	
	        //tab.activate();
	        tab.refresh();
        }
        
    	</script>
	</head>
	<body>
	    <div id="crosswise_tabs" style="margin:0px;padding:0px;height:100%;overflow:hidden!important;">
	        <ul >
	        </ul>
	    </div>
	</body>
</html>
