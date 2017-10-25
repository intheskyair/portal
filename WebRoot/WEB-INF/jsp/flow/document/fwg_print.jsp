 <%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<title>发文稿</title>
<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
<link rel="stylesheet" href="${ctx}/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="${ctx}/js/kindeditor/themes/simple/simple.css" />
<link rel="stylesheet" href="${ctx}/css/common.css"></link>
<link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquery-ui-jquiGui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/flexigrid/flexigrid.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquiGui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css"/>
<script type="text/javascript" src="${ctx}/views/common/js/jquery.min.js"></script>
<script src="${ctx}/styles/js/snaker/snaker.util.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/views/common/js/jquery-ui.custom.min.js"></script>
<script src="${ctx}/views/common/js/common.js" type="text/javascript"></script>
<script src="${ctx}/views/common/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="${ctx}/js/kindeditor/lang/zh_CN.js"></script>
<style type="text/css">	
   .dwb{ width:95%;}
   .rx{border-right: 1px solid #999;}
   .table-1 td{border: 1px solid #ccc;}
	fieldset{border:1px solid #9AC3E1;margin-top:10px;}
	legend{font-size: 16px;color: #0071b1;font-weight: bold;}
	</style>
</head>
<body style="position:relative;overflow: scroll;">
	<div class="sl-content" >
	<input type="hidden" value="${map.step}" name="step" id="step">
	<input type="hidden" id="processId" name="processId" value="${map.processId}" />
	<input type="hidden" id="orderId" name="orderId" value="${map.orderId}" />
	<input type="hidden" id="taskId" name="taskId" value="${map.taskId}" />
	<input type="hidden" id="orgId" name="orgId" value="${admin.org_id}" />
	<input type="hidden" id="taskName" name="taskName" value="${map.taskName}" />
	<fieldset>
    <legend>【${map.label}】</legend>
	<table class="table_all" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-top: 0px">	
				<tr>
					<td class="td_table_1"><span>发文文号：</span></td>
					<td class="td_table_2">
						<input type="text" class="input_240" value="${map.document_wh}" readonly="readonly"  />
						<input type="text" id ="wh_year1" name="wh_year1" class="input_240" value="${map.wh_year}" onblur="checkEmpty('wh_year1','文号年份')" />
						<input type="text"  name="wh_num" class="input_240" onblur="checkEmpty('wh_num','编号')" />号
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
				</tr>
				<tr>
					<td class="td_table_1"><span>打印数量：</span></td>
					<td class="td_table_2">
						<input type="text" id="num" name="num" class="input_240" value="1" onblur="checkEmpty('num','打印数量')"/>
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
				</tr>
			</table>
 </fieldset>
		<table align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr align="center">
					<td colspan="1" class="h50">
						<input type="button" class="selectBtn" id="submit" style="" value="提交" onclick="sub();"/>
						&nbsp;&nbsp;
						<input type="button" class="selectBtn" name="reback" value="返回"
							onclick="bakIframe('${map.label}','${map.bakurl}')">
					</td>
				</tr>
</table>
				<div id="show_form_fprint" >
				<div class="outer">
					<ul class="tab" >
				        <li class="current rx" onclick="personDetails(1)" id="begin">流程详情</li>
						<li onclick="personDetails(2)" class="rx">查看表单</li>					
					    <li id="gk_from_fprint" >公开保密审查单</li>
				    </ul>
				</div>
				<div class="content_y">
				           <ul>
					<li class="current">
					     		<div id="flowTable_fprint"></div>
					     		</li>
					     		</ul>
					     		<ul>
					<li class="current">
						    	<div id="formTable_fprint"></div>
						    	</li>
						    	</ul>
						    	<ul style="display:none">
					<li >
				    	<div id="gk_view_fprint" ></div>
                    </li>
                    </ul>
			</div>	
		</div>
		</div>
			
<div><a id="msg_end" name="1" href="#1">&nbsp</a></div>		
</body>
<script type="text/javascript">
	    
function cleaveTime(){
	WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});
}
  
    $(function()
    {    	       
       $("#begin").click();		
	  	if("${map.sfgk}" == "是"){
	  		$.ajax({
	     		url:"borrow!view.action?orderId=${map.gk_orderId}&path=${map.gk_path}",
	     		type:"POST",
	        	cache: false,
	    		success: function(result){
	    		
	    			$("#gk_view_fprint").html(result);
	    		}   
	     	});
	  	}else{
	  		$("#gk_from_fprint").hide();
	  	}	      
    });
    
     function show(){ 
		$("#show_form").toggle(); 
		$("#show_form").addClass("from-below"); 
		setTimeout(function(){$("#show_form").addClass("effeckt-show");},300); 
	} 
    
    function sub()
    {			
    	$("#wh").blur();
    	$("#num").blur();
    	if(($("span[class='LV_invalid']").length != 0)){
			return;
		}else{
			var queryP = {};
			queryP.first_ng = HtmlEncode($("#first_ng").val());
            queryP.title = HtmlEncode($("#title").val());
            queryP.processId = HtmlEncode($("#processId").val());
            queryP.orderId = HtmlEncode($("#orderId").val());
            queryP.taskId = HtmlEncode($("#taskId").val());
            queryP.taskName = $("#taskName").val();
            queryP.wh_year = HtmlEncode($("#wh_year1").val());
            queryP.wh_num = HtmlEncode($("#wh_num").val());
            queryP.num = HtmlEncode($("#num").val());
            $.ajax({
	        	url:"docum!process.action",
	        	type:"POST",
	        	cache: false,
	    		data:queryP,
	    		success: function(msg){
	    			if("success" === msg)
	    			{
	    				alert("提交任务成功!");	    				
	    				//跳转到审批列表
		    			bakIframe("${map.label}","${map.bakurl}");
	    			}
	    		}      	
    		}); 
		}
          
    }
    
    
    
	function personDetails(com) {
		$("#formTable_fprint").html("");
		$("#flowTable_fprint").html("");

		var orderId = "${map.orderId}";
		var pid = "${map.processId}";

		if (com == 2) {
			url = "borrow!view.action";
			data = {
				"orderId" : orderId,
				"processId" : pid,
				"path" : "documView"
			};
		} else {

			url = "lczl!viewStep.action";
			data = {
				"orderId" : orderId
			};
		}

		$.ajax({
			type : "POST",
			data : data,
			cache : false,
			async : false,
			url : url,
			success : function(result) 
			{
				if (com == 1) {
					var result_str = createHtml(result);
					$("#flowTable_fprint").append(result_str);
				} else {
					$("#formTable_fprint").append(result);
				}
				$("#show_form_fprint .tab li").click(function()
				{
					$("#show_form_fprint .tab li").removeClass("current");
					$(this).addClass("current");
					var n = $(this).index();
					$("#show_form_fprint .content_y ul").hide();
					$("#show_form_fprint .content_y ul").eq(n).show();
		        });
			}
		});
		document.getElementById("msg_end").click(); 

	}				
    
    
    
</script>
</html>
