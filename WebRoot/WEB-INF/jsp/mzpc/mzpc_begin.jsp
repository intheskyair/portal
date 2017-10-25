<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/taglibs.jsp"%>
<html>
    <head>
	<title>民主测评表打分</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquery-ui-jquiGui.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquiGui.css"/>
    <script src="${ctx}/views/common/js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/common.js"></script>
    <script src="${ctx}/styles/CleverTabs/scripts/jquery.contextMenu.js" type="text/javascript"></script>
    <script src="${ctx}/styles/CleverTabs/scripts/jquery.cleverTabs.js" type="text/javascript"></script>
	</head>
	     
	<script>
	var clearNewTab = function(){
		$("#newTab").html(""); 


	}


	
	
	 $(function(){
		/*  tabs = $('#tabs').cleverTabs({
             setupContextMenu: false
		 });
         $(window).bind('resize', function () {
             tabs.resizePanelContainer();
         }); */

		/*参数管理-查询按钮功能*/
		$('#btn_canpc').on("click",function(){
			var mzpf_pf =$.trim($("#mzpf_pf").val());
			if(mzpf_pf.length == 0){
				$(".LV_invalid").addClass("none").html("请输入随机码");
			}else{
				mzMark(mzpf_pf)
			} 
		});

		//清空筛选框
		$('#btn_szxc_empty').on("click",function(){
			$("#mzpf_pf").val("");
		});
	});
	
	 
	 function mzMark(id){
			$.ajax({
				type: "get",
				url: "mzpf!jumpDiv.action",
				cache: false,
				data: {id:id},
				success: function(data){
					var res = (new Function("","return " + data))();
					if(res.error)
					{
						$(".LV_invalid").addClass("none").html(res.error);
						document.getElementById("newTab").innerHTML = "";
						$("#show_df").html("");
					}
					else
					{
						var res = (new Function("","return " + data))();
						var html = "<table border='1'>";
						for(var i = 0; i < res.list.length ; i ++){
							html+= "<tr><td>"+res.list[i].TITLE+"</td>"
							var bu;
							if(res.list[i].KR_STATUS == 0){
								bu = "打分";
							}else{
								bu = "查看";
							}
							html+="<td><input type='button' value='"+bu+"' onclick='df(\""+res.list[i].RADOM+"\","+res.list[i].ID+")'/></td></tr>"
						}
						html +="</table>";
						/* $(".LV_invalid").addClass("none").html("");
						var iframeUrl = "${ctx}/"+res.url + "?mainid=" + res.mainid + "&radom="+id +"&view_type="+res.view_type;
						document.getElementById("newTab").innerHTML=
							"<iframe frameborder='0' width='100%' height='88%' src='"+iframeUrl+"'></iframe>"; */
						$("#show_df").html(html);
					
					}
				}		
			});
		}	
	 
	 
	 function df(radom,id){
		 $.ajax({
				type: "get",
				url: "mzpf!jump.action",
				cache: false,
				data: {radom:radom,id:id},
				success: function(data){
					var res = (new Function("","return " + data))();
					if(res.error)
					{
						alert(res.error)
					}
					else
					{
						var res = (new Function("","return " + data))();
					
						 $(".LV_invalid").addClass("none").html("");
						var iframeUrl = "${ctx}/"+res.url + "?mainid=" + res.mainid + "&radom="+radom +"&view_type="+res.view_type;
						 document.getElementById("newTab").innerHTML=
							"<iframe frameborder='0' scrolling='no'  src='"+iframeUrl+"' width='100%' height='100%'></iframe>";  
					
					}
				}		
			});
	 }
	 
	
		function showpc(id){
		 	var mainId = parseInt(id);
		 	$.ajax({
		 		type: "get",
				url: "mzpc!jumpHtml.action",
				cache: false,
				data: {mainid:mainId,type:"viewdf"},
				success: function(data){
					var res = (new Function("","return " + data))();
					var option = {};
					option.key = res.mainid;
					option.title = "打分结果预览";
					option.url = "../" + res.url + "?mainid=" + res.mainid+"&view_type=1";
					option.isIframe = true;
					addTab(option);
				}
		 	});
		 }
	 
		
	/* 	
		function closeMZtabs(){
			$("#tabs").html("<ul></ul>");
			 tabs = $('#tabs').cleverTabs({
	             setupContextMenu: false
			 });
	         $(window).bind('resize', function () {
	             tabs.resizePanelContainer();
	         });

		} */
		</script>
	
	
	
	<body style="height:100%">	
	<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='${pageContext.request.contextPath}/images/fold.png' onclick="showAndHide(this,'canpc_grid');"  class="imgShrotate">
				<font>民主评测表打分</font>
			</h3>
		</div>
	<div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>
					<td ><label for="input_1" class="lbl-1">随机码：</label></td>
					<td class="wi-align-l" >
						<input type="text" id="mzpf_pf" name="mzpf_pf" class="wi-ipt-1" />
					</td>
							<td class="wi-align-l">
						<button id="btn_canpc" class="ui-state-default ui-state-qik">确定
						</button>
					</td>
					<td class="wi-align-l" style="width: 100px;">
						<button id="btn_szxc_empty" class="ui-state-default ui-state-qik">清空</button>
					</td>
					<td>
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
	<!--  <div id="tabs" style="margin:0px;padding:0px;overflow:hidden!important;height:99.6%">
	        <ul>
	        </ul>
	 </div> -->
	 
	 <div style="float:left;width:100%; height:100%;">
		 <div id="show_df" style="float:left;width:20%; ">		 
		 </div>		 
		 <div id="newTab" style="float:left;width:80%;height:100%;">
		 	
		 </div>
	 </div>
	</body>
</html>
