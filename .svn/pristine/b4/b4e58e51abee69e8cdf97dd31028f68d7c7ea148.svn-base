<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
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
 	fieldset{border:1px solid #9AC3E1;margin-top:10px;}
	legend{font-size: 16px;color: #0071b1;font-weight: bold;}   
    </style>
    <script src="${ctx}/views/common/js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/common.js"></script>
		 		<script type="text/javascript">
		 		var szzf_user = {
					changeAuth:function(){
					$("#${map.taskName}").blur();
					if($("span[class='LV_invalid none']").length == 0){
						return;
					}
			        var dlg_auth_distribute = $("#dlg_auth_distribute").dialog({
				        position: ['center','top'],
        				draggable :false,     
						autoOpen: false,
						height:"400",
						width:"500",
						show: "slide",
						modal:true,
						cache:false,
						/* hide: "explode", */
						title:'批量分配角色',
						buttons:{
							"确定":function(){
								szzf_user.saveAuth();
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
			        dlg_auth_distribute.dialog("open");
			},
			saveAuth:function(){
				var admin_id ="";
				var username ="";
				if(document.authchoose.username_list2.options.length==0){
					showMsg("请选择用户！");
					return;
				}
				 for(var i=0;i<document.authchoose.username_list2.options.length;i++){
					admin_id += document.authchoose.username_list2.options[i].value+",";
					username += document.authchoose.username_list2.options[i].text+",";
				}
				$("#userList").val(admin_id);
				$('#dlg_auth_distribute').dialog('close');
				 $.ajax({
		        	url:"szzf!process.action",
		        	type:"POST",
		        	cache: false,
		    		data:$("#inputForm").serialize(),
		    		success: function(msg){
		    			if("success" === msg){
		    				alert("提交成功");
		    				//跳转到审批列表
		    				bakIframe("${map.label}","${map.bakurl}");		    				
		    			}
		    		}     	
        		});   
			}
	};
		 		
     $(function(){
    
    	 $("#begin").click();
     });
   
	
function personDetails(com) {
			$("#formTable_msg").html("");
				$("#flowTable_msg").html("");
		         
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
								    $("#flowTable_msg").append(result_str);
								    }else{
								    $("#formTable_msg").append(result);
								    }
								    
								    $("#view_msg .tab li").click(function()
								    {
										$("#view_msg .tab li").removeClass("current");
										$(this).addClass("current");
										var n = $(this).index();
										$("#view_msg .content_y ul").hide();
										$("#view_msg .content_y ul").eq(n).show();
		                            });
								}								
							});	
							document.getElementById("msg_end").click();
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
</script>
		
	</head>
	<body style=" overflow-y: scroll;">
		<form id="inputForm" action="${ctx}/szzf!process.action" method="post" target="mainFrame">
			<input type="hidden" name="processId" value="${map.processId }" />
			<input type="hidden" name="orderId" value="${map.orderId }" />
			<input type="hidden" name="taskId" value="${map.taskId }" />
			<input type="hidden" name="step" value="${map.step }" />
			<input type="hidden" name="taskName" value="${map.taskName }" />
			<input type="hidden" id="assignee" name="assignee" value="${map.assignee}"/>
			<input type="hidden" name="userList" id="userList"/>
		<fieldset>
			<legend>【${map.label}】</legend>	
			<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0" style="margin-top: 0px">
			<tr>
				<td class="td_table_1">
					<span>调查情况</span>
				</td>
				<td class="td_table_2" colspan="3">
					<textarea class="input_textarea_320" id="dxqk" name="dxqk" onblur="checkEmpty('dxqk','调查情况')"></textarea>
					<span class="LV_invalid none" style="color: red;"></span>
				</td>
			</tr>
			<tr>
				<td class="td_table_1">
					<span>处理情况</span>
				</td>
				<td class="td_table_2" colspan="3">
					<textarea class="input_textarea_320" id="clqk" name="clqk" onblur="checkEmpty('clqk','处理情况')"></textarea>
					<span class="LV_invalid none" style="color: red;"></span>
				</td>
			</tr>
			</table>
			</fieldset>
			<table align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr align="center">
					<td colspan="1" class="h50">
						<input type="button" class="selectBtn"  value="提交" onclick="szzf_user.changeAuth()"/>
						&nbsp;&nbsp;
						<input type="button" class="selectBtn" name="reback" value="返回"
							onclick="bakIframe('${map.label}','${map.bakurl}')">
					</td>
				</tr>
			</table>
		</form>
		<div id="view_msg">
		<div class="outer">
			<ul class="tab">
				<li class="current" onclick="personDetails(1)" id="begin">流程详情</li>
				<li onclick="personDetails(2)">查看表单</li>
			</ul>
			<div class="content_y">
						<div id="flowTable_msg"></div>
						<div id="formTable_msg"></div>
				</ul>
			</div>
		</div>
	</div>
		
<div><a id="msg_end" name="1" href="#1">&nbsp</a></div>		
	<div id="dlg_auth_distribute" style="display:none">
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
