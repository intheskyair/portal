<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<title>上报</title>
		<%@ include file="/common/meta.jsp"%>
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/css/style.css"  media="all" />
     <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquery-ui-jquiGui.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/flexigrid/flexigrid.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquiGui.css"/>
    <style> 
		.from-below,.from-below-to-below .effeckt-modal {
			-webkit-transform: translateX(100%);
			-ms-transform: scale(0.5);
			-o-transform: scale(0.5);
			transform: scale(0.5);
			opacity: 0;
			-webkit-transition: all 500ms;
			-o-transition: 500ms;
			transition: 500ms;
		}		
		.effeckt-show,.effeckt-show.from-below-to-below .effeckt-modal {
			-webkit-transform: translateX(0);
			-ms-transform: scale(1);
			-o-transform: scale(1);
			transform: scale(1);
			opacity: 1;
		}
		
		.effeckt-show .effeckt-modal {
			visibility: visible;
		}
	 	fieldset{border:1px solid #9AC3E1;margin-top:10px;margin-bottom:10px;}
		legend{font-size: 16px;color: #0071b1;font-weight: bold;}   
    </style>
    <script src="${ctx}/views/common/js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/common.js"></script>
	<script type="text/javascript">
     function sub(){
    	 $("#${map.taskName}").blur();
     		if(($("span[class='LV_invalid']").length != 0)){
 			return;
 		}
     		$("#result").val("end");
        $.ajax({
        	url:"szzf!process.action",
        	type:"POST",
        	cache: false,
    		data:$("#inputForm").serialize(),
    		success: function(msg){
    			if("success" === msg){
    				alert("提交成功!");
    				//parent.location = "process!proceList.action";
    			    bakIframe("${map.label}","${map.bakurl}");
    			}
    		}      	
        });     
   }
     
     function sub1(){
    	 $("#${map.taskName}").blur();
     		if(($("span[class='LV_invalid']").length != 0)){
 			return;
 		}
     	ja_user.changeAuth();
   }
  	    $(function(){
  	    	 $("#begin").click();
  	    	 $("#plann_file1").change(function(){        
			$("#upplann_form").submit();
  			 });
  	    });
 	
 
 //调用返回上传结果 
 function get_upfile_result(res){
  	if(1 == Number(res.mess_state)){
  		alert(res.message);
  		$("#promotionShow_plann").html(res.newFileName);
	        $("#path_plann_file").val(res.rs_path);
  	}else{
  		alert(res.message);
  	}
 };
 


function personDetails(com) {
	$("#formTable_zdja").html("");
	$("#flowTable_zdja").html("");
	var orderId = "${map.orderId}"; 
	var pid = "${map.processId}";
	if(com == 2){
		url = "borrow!view.action";
		 data = {"orderId" : orderId,"processId":pid,"path":"${map.path}"};
	}else{
		 url = "lczl!viewStep.action";
		 data = {"orderId" : orderId};
	}
	$.ajax({
		type:"POST",
		data:data,
		cache: false,
		async:false,
		url: url,
		success:function(result)
		{	
		    if(com == 1){
		    var result_str = createHtml(result);
		    $("#flowTable_zdja").append(result_str);
		    }else{
		    $("#formTable_zdja").append(result);
		    }
		    $("#view_zdja .tab li").click(function()
		    {
				$("#view_zdja .tab li").removeClass("current");
				$(this).addClass("current");
				var n = $(this).index();
				$("#view_zdja .content_y ul").hide();
				$("#view_zdja .content_y ul").eq(n).show();
            });
		}		
	});
	document.getElementById("msg_end").click();
}

function mydownload(path)
{
    path = $("#"+path).val();
	//queryP.path = unescape(path);
	//alert(encodeURI(encodeURI(unescape(path))));
	location.href='upload!download.action?&path='+ encodeURI(encodeURI(unescape(path))); 
}

</script>
		
	</head>

	<body style="overflow: scroll;">
		<form id="inputForm" action="${ctx }/szzf!process.action" method="post" target="mainFrame">
			<input type="hidden" name="processId" value="${map.processId }" />
			<input type="hidden" name="orderId" value="${map.orderId }" />
			<input type="hidden" name="taskId" value="${map.taskId }" />
			<input type="hidden" name="step" value="${map.step }" />
			<input type="hidden" name="taskName" value="${map.taskName }"/>
			<input type="hidden" id="assignee" name="assignee" value="${map.assignee}"/>
			<input type="hidden" name="result" id="result"/>
			<input type="hidden" name="userList" id="userList"/>
			<fieldset>
			<legend>当前步骤：【${map.label}】</legend>
			</form>
<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0" style="margin-top: 0px">
			<tr>
				<td class="td_table_1">
					<span>下发附件：</span>
				</td>
				<td class="td_table_2" colspan="2">
					<input type="hidden" name="issFile" id="issFile" value="${map.issFile}" />
						 <c:if test="${!empty map.issFile}">
						 	<input type="button" id="down" value="下载" onclick="mydownload('issFile')">
						 </c:if>
				</td>
			</tr>
			<tr>
				<td class="td_table_1">
					<span>结案附件：</span>
				</td>
				<td class="td_table_2" colspan="2">
					 <input type="hidden" name="path_plann_file" id="path_plann_file" value="${map.path_plann_file}" />
						 <c:if test="${!empty map.path_plann_file}">
						 	<input type="button" id="down" value="下载" onclick="mydownload('path_plann_file')">
						 </c:if>
				</td>
			</tr>
				<tr align="center">
					<td colspan="2" class="h50">
						<input type="button" class="selectBtn"  value="结束" onclick="sub();"/>
					</td>
				</tr>
</fieldset>
			</table>
 </fieldset>
<div id="view_zdja">
		<div class="outer">
			<ul class="tab">
				<li class="current" onclick="personDetails(1)" id="begin">流程详情</li>
				<li onclick="personDetails(2)">查看表单</li>

			</ul>

			<div class="content_y">
						<div id="flowTable_zdja"></div>
						<div id="formTable_zdja"></div>
			</div>
		</div>
	</div>
	<div><a id="msg_end" name="1" href="#1">&nbsp</a></div>
	<iframe id="result_upfile" name="result_upfile" style="position:absolute; top:-9999px; left:-9999px"></iframe>

	
	</body>
</html>
