<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" >
	<head>
	<%@ include file="/common/meta.jsp"%>
		<title>${map.head_title}</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css"/>
    <link rel="stylesheet" href="${ctx}/css/common.css"></link>
    <link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
    <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquery-ui-jquiGui.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/flexigrid/flexigrid.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquiGui.css"/>
<style> 
	.from-below, 
	.from-below-to-below .effeckt-modal { 
	-webkit-transform: translateX(100%); 
	-ms-transform: scale(0.5); 
	-o-transform: scale(0.5); 
	transform: scale(0.5); 
	opacity: 0; 
	-webkit-transition: all 500ms; 
	-o-transition: 500ms; 
	transition: 500ms; 
	} 
	.effeckt-show, 
	.effeckt-show.from-below-to-below .effeckt-modal { 
	-webkit-transform: translateX(0); 
	-ms-transform: scale(1); 
	-o-transform: scale(1); 
	transform: scale(1); 
	opacity: 1; 
	} 
	.effeckt-show .effeckt-modal { 
	visibility: visible; 
	} 
	fieldset{border:1px solid #9AC3E1;margin-top:10px;}
	legend{font-size: 16px;color: #0071b1;font-weight: bold;}

</style> 
	<script type="text/javascript" src="${ctx}/js/kindeditor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctx}/js/kindeditor/lang/zh_CN.js"></script>
    <script src="${ctx}/views/common/js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/common.js"></script>
	<script type="text/javascript">
	 $(function() {
		$("#begin").click();
	});
	 $(function(){
		 $("#suggest").text('${map.userName}${map.thisTime}');
// 		  	$.ajax({
// 	     		url:"borrow!view.action?orderId=${map.orderId}&path=${map.path}",
// 	     		type:"POST",
// 	        	cache: false,
// 	    		success: function(result){
// 	    			$("#view").html(result);
// 	    		}   
// 	     	}); 
			
		  	if("${map.sfgk}" == "是"){
		  		$.ajax({
		     		url:"borrow!view.action?orderId=${map.gk_orderId}&path=${map.gk_path}",
		     		type:"POST",
		        	cache: false,
		    		success: function(result){
		    			$("#gk_view").html(result);
		    		}   
		     	});
		  	}else{
		  		$("#gk_from").hide();
		  	}	
     }); 

	
	
	
			var approve_user = 
			{
			
			    dirchange:function()
			    {	
			       var userlist="";		    
			       $.ajax({
			            type:"get",
			            dataType:"text",
			            async:false,
			            url:"queryUser!getUserList.action?assignee=${map.assignee}",
			            success:function(result) 
			            {
			            	var data=eval("("+result+")");
							$("#userList").val("");
								for (var i in data) 
								{
									userlist += data[i].USERNAME+","
								}
								$("#userList").val(userlist);
			            }
			        }); 
			        sub();			    
			    }, 
				changeAuth:function(){
			       var  approvedistribute  = $("#approvedistribute").dialog({
				        position: ['center','top'],
        				draggable :false,     
						autoOpen: false,
						height:"400",
						width:"450",
						show: "slide",
						modal:true,
						cache:false,
						/* hide: "explode", */
						title:'选择下一步操作人',
						buttons:{
							"确定":function(){
								$("#submit").attr("style", "background-color:gray");
								 $("#submit").attr("disabled", true);
								approve_user.saveAuth();
							}
						}
					});
			        $.ajax({
			            type:"get",
			            dataType:"text",
			            async:false,
			            url:"queryUser!getUserList.action?assignee=${map.assignee}",
			            success:function(result) {
			            $("#username_list1").html("");
			            $("#username_list2").html("");
			            	var data=eval("("+result+")");
							var html = '';
							var html2 = '';
							var leftValue = $("#userList").val();
							if(leftValue.length == 0){
								for (var i in data) {
									html += '<option value="'+data[i].USERNAME+'">'+data[i].TRUENAME+'</option>';
								}
								$("#username_list1").append(html);
							}else{
								var leftVals = leftValue.split(",");
								for (var i in data) {
								var flag = false;
									for(var j in leftVals){
										if(data[i].TRUENAME == leftVals[j]){
										flag = true;
											html2 += '<option value="'+data[i].USERNAME+'">'+data[i].TRUENAME+'</option>'; 
										}
									}
									if(!flag){
										html += '<option value="'+data[i].USERNAME+'">'+data[i].TRUENAME+'</option>';
									}
								}
								$("#username_list1").append(html);
								$("#username_list2").append(html2);
							}
			            }
			        });
			        approvedistribute.dialog("open");
			},
			saveAuth:function(){
				var admin_id ="";
				var username ="";
				if(document.authchoose.username_list2.options.length==0){
					showMsg("请选择用户！");
					 $("#submit").attr("disabled", false);
					 $("#submit").removeAttr("style");
					return;
				}
				 for(var i=0;i<document.authchoose.username_list2.options.length;i++){
					admin_id += document.authchoose.username_list2.options[i].value+",";
					username += document.authchoose.username_list2.options[i].text+",";
				}
				$("#userList").val(admin_id);
				$('#approvedistribute').dialog('close');
				sub();
			}
	
	};
	
	
	  function choosesub()
	  {
	  	// -1,最后一步， -2返回第一步操作人
	  	if($('input[name="method"]:checked').val() == -1)
	  	{
	  		sub();
	  	}
	  	else
	  	{
	  		if("${map.assignee}" == -1 || "${map.assignee}" == -2)
	  		{
	  			  sub();		
	  		}
	  		//领导不需要弹窗
	  		else if("${map.no_dig}" == "1")
	  		{
	  			$("#submit").attr("style", "background-color:gray");
				 $("#submit").attr("disabled", true);
	  			approve_user.dirchange();
	  		}
	  		else
	  		{
	  		   approve_user.changeAuth();
	  		}
	  		
	  		
	  	}
		
    }
    
    function sub(){
    	 $.ajax({
		        	url:"docum!process.action",
		        	type:"POST",
		        	cache: false,
		    		data:$("#inputForm1").serialize(),
		    		success: function(msg){
		    			if("success" === msg){
		    				alert("提交成功");
		    			}else if("repeat" === msg){
		    				alert("该流程已批阅");
		    			}else{
		    				alert("流程已处理(原因：会签:被驳回,非会签:被处理)");
		    			}
		    			bakIframe("${map.label}","${map.bakurl}");
		    		}      	
        		});
    }
    
       function closeIframe(){
    	$(window.parent.parent.document).find(".ui-tabs-nav > li.ui-state-active > div > span.tabs-close").click();
    }
    
 
     function moveOption(list1,list2){
			 var options = list1.options;
			 for(var i=0;i<options.length;i++){
				 if(options[i].selected){
					 list2.appendChild(options[i]);
					 i--;
				 }
			 }
	}
	
	
	function show(){ 
		$("#show_form").toggle(); 
		$("#show_form").addClass("from-below"); 
		setTimeout(function(){$("#show_form").addClass("effeckt-show");},300); 
	} 
	
 
	function personDetails(com) {

		$("#formTable_gk").html("");
		$("#flowTable_gk").html("");

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
			success : function(result) {

				if (com == 1) {
					var result_str = createHtml(result);
					$("#flowTable_gk").append(result_str);
				} else {
					$("#formTable_gk").append(result);
				}
				$("#show_form .tab li").click(function()
				{
					$("#show_form .tab li").removeClass("current");
					$(this).addClass("current");
					var n = $(this).index();
					$("#show_form .content_y ul").hide();
					$("#show_form .content_y ul").eq(n).show();
		        });
			}

		});
		document.getElementById("msg_end").click(); 

	}

</script>
		
	</head>
	<body style="height:100%;overflow-y: scroll;" >
		<form id="inputForm1" action="" method="post" target="mainFrame"  >
			<input type="hidden" name="processId" value="${map.processId }" />
			<input type="hidden" name="orderId" value="${map.orderId }" />
			<input type="hidden" name="taskId" value="${map.taskId }" />
			<input type="hidden" name="taskName" value="${map.taskName }" />
			<input type="hidden" name="userList" id="userList"/>
<fieldset>
<legend>【${map.label}】</legend>
<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0" style="margin-top:10px">
			<tr>
				<td class="td_table_1">
					<span>领导审批结果：</span>
				</td>
				<td class="td_table_2" colspan="3">
					<input type="radio" id="agree" name="method" value="0" checked="checked"/>同意
					<input type="radio" id="noagree" name="method" value="-1" />不同意
				</td>
			</tr>
			<tr>
				<td class="td_table_1">
					<span>领导审批意见：</span>
				</td>
				<td class="td_table_2" colspan="3">
					<textarea class="input_textarea_320" id="suggest" name="suggest"  ></textarea>
				</td>
			</table>
 </fieldset>	
			<table align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr align="center">
					<td colspan="1" class="h50">
						<input type="button" class="selectBtn" id="submit" style=""  value="提交" onclick="choosesub();"/>
						&nbsp;&nbsp;
						<input type="button" class="selectBtn" name="reback" value="返回"
							onclick="bakIframe('${map.label}','${map.bakurl}')">
					</td>
				</tr>
			</table>
		</form>
		<div id="show_form">
			<div class="outer">
				<ul class="tab">
				<li class="current" onclick="personDetails(1)" id="begin">流程详情</li>
				<li onclick="personDetails(2)">查看表单</li>				
			    <li id="gk_from" >公开保密审查单</li>
				</ul>
			</div>
			<div class="content_y">
			 	 <ul>
					     	<li class="current">
					     		<div id="flowTable_gk"></div>
					     	</li>
					    </ul>
						<ul >
							<li >
						    	<div id="formTable_gk"></div>
						    </li>
						</ul>
				<ul style="display:none">
					<li>
				    	<div id="gk_view" ></div>
				    </li>
				</ul>
			</div>

		</div>	
<div><a id="msg_end" name="1" href="#1">&nbsp</a></div>		
		<div id="approvedistribute" style="display:none">
		<form method = "post" name ="authchoose">
			<table border="0">
				<tr style="height:35px">
					<td class="wi-align-l" style="text-align:center">待分配用户：</td>
					<td class="wi-align-l" ></td>
					<td class="wi-align-l" style="text-align:center">已选择用户：</td>
					
				</tr>
				<tr>
					<td>
						<select multiple name="username_list1" id="username_list1" class="wi-sel-1" style="width:150px;height:260px" >
						</select>
					</td>
					<td style="width:200px;text-align:center">
						<input style="width:50px" type ="button" value ="添加" onclick="moveOption(document.authchoose.username_list1,document.authchoose.username_list2);"><br/><br/>
						<input style="width:50px" type ="button" value ="移除" onclick="moveOption(document.authchoose.username_list2,document.authchoose.username_list1);"><br/>
					</td>
					<td >
						<select multiple name="username_list2" id="username_list2" class="wi-sel-1" style="width:150px;height:260px" >
						</select>
					</td>
				</tr>
			</table>
		</form>
	</div>	
	</body>
</html>
