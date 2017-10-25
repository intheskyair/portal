<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head> 
 
  <%@ include file="/common/meta.jsp"%>
    <title>水政监察支队水事违法案件</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/My97DatePicker/skin/WdatePicker.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/css/style.css"  media="all" />
     <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquery-ui-jquiGui.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/flexigrid/flexigrid.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquiGui.css"/>
    <script src="${ctx}/views/common/js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/common.js"></script>
	<script type="text/javascript" src="${ctx}/views/common/js/My97DatePicker/WdatePicker.js"></script>
	<style>
	 .srk{ width:50px;}
	</style>
	<script type="text/javascript">
	
	var szzf_user = {
					changeAuth:function(){
			        var dlg_auth_distribute = $("#dlg_auth_distribute").dialog({
				        position: ['center','center'],
        				draggable :false,     
						autoOpen: false,
						height:$(window).height()*0.8,
					    width:$(window).width()*0.5,
						show: "slide",
						modal:true,
						cache:false,
						/* hide: "explode", */
						title:'下一步审批人',
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
	
	
	
    function sub()
    {			
    	
    	$("#cbmsg").blur();
    	$("#cbfs").blur();
    	$("#time").blur();
    	$("#wzname").blur();
    	$("#wzaddress").blur();
    	$("#aqjy").blur();
    	if(($("span[class='LV_invalid']").length != 0)){
			return;
		}
	    szzf_user.changeAuth();
    }
    
   
     $(function(){
          if("${map.orderId}" != '')
          {
        	  $("#lxfs").val("${map.lxfs}");
              $("#cbfs").val("${map.cbfs}");
              $("#begin").click(); 
              	$("#submit").val("重新提交");
	    		$("#submit").attr("style","color:red");
	    		$("#detail").html("【被退回】");
	    		$("#detail").attr("style","color:red");
          }
          else
          {
        	  $("#view_zdja").css('display','none');
        	     var date=new Date();//
                 var year = date.getFullYear();
                 var month = date.getMonth()+1;
                 var day = date.getDate();
                 if (month < 10) {
                     month = "0" + month;
                 }
                 if (day < 10) {
                     day = "0" + day;
                 }
                 $("#time").val(year+"-"+month+"-"+day);
          }
          

          
    }); 
    
    
       function moveOption(list1,list2){
			 var options = list1.options;
			 for(var i=0;i<options.length;i++){
				 if(options[i].selected){
					 list2.appendChild(options[i]);
					 i--;
				 }
			 }
	} 
	
       
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

    
</script>
  </head>
  <body style="overflow:scroll;">
   <form id="inputForm" action="${ctx}/szzf!process.action" method="post" target="mainFrame" style="">
   	<input type="hidden" value="${map.step}" name="step" id="step">
   	<input type="hidden" id="processId" name="processId" value="${map.processId}" />
	<input type="hidden" id="orderId" name="orderId" value="${map.orderId}" />
	<input type="hidden" id="taskId" name="taskId" value="${map.taskId}" />
	<input type="hidden" id="title" name="title" value="水政监察" />
	<input type="hidden" id="taskName" name="taskName" value="${map.taskName}" />
	<input type="hidden" id="assignee" name="assignee" value="${map.assignee}"/>
	<input type="hidden" name="userList" id="userList"/>
  <p class="btt" style=" font-size:20px;">常州市水政监察支队水事违法案件查办通知<span id="detail"></span></p>
	
	
	<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px">
				<tr>
					<td class="td_table_1"><span>编号：</span></td>
					<td class="td_table_2">
						<input type="text" name="transid" class="input_240" value="${map.transid}" readonly="readonly"/>
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
				</tr>
				<tr>
					<td class="td_table_1"><span>查办方式：</span></td>
					<td class="td_table_2">
						<select id="cbfs" name="cbfs" style="width:244px; margin-left:5px;height: 27px;border: 1px solid #ccc;" onblur="checkEmpty('cbfs','查办方式')">
							<option value="">请选择</option>
							<option value="ldjb">来电举报</option>
							<option value="lxjb">来信举报</option>
							<option value="lfjb">来访举报</option>
							<option value="jjgdb">局机关督办</option>
							<option value="sjgdb">市机关督办</option>
							<option value="xcfx">巡查发现</option>
							<option value="qt">其他</option>
						</select>
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
				</tr>
				<tr>
					<td class="td_table_1"><span>查办内容：</span></td>
					<td class="td_table_2">
						<input type="text" id="cbmsg" name="cbmsg" class="input_240" value="${map.cbmsg}" onblur="checkEmpty('cbmsg','查办内容')"/>
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
				</tr>
				<tr>
					<td class="td_table_1"><span>受理时间：</span></td>
					<td class="td_table_2">
						<input type="text" id="time" name="time"  class="WdateFlow" style="width: 242px;height: 27px;border: 1px solid #ccc;" value="${map.time}" onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,readOnly:true})" onblur="checkEmpty('time','受理时间')"/>
						<span class="LV_invalid none" style="color: red;"></span>
					</td>	
				</tr>
				<tr>
					<td class="td_table_1"><span>违章人/单位：</span></td>
					<td class="td_table_2">
						<input type="text" id="wzname" name="wzname" value="${map.wzname}" class="input_240" onblur="checkEmpty('wzname','违章人')"/>
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
				</tr>
				<tr>
					<td class="td_table_1"><span>违章人地址：</span></td>
					<td class="td_table_2">
						<textarea name="wzaddress" id="wzaddress" class="input_textarea_320" onblur="checkEmpty('wzaddress','违章人地址')">${map.wzaddress}</textarea>
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
					
				</tr>
				<tr>
					<td class="td_table_1"><span>简要案情：</span></td>
					<td class="td_table_2">
						<textarea name="aqjy" id="aqjy" class="input_textarea_320" style=" height:70px;" onblur="checkEmpty('aqjy','简要案情')">${map.aqjy}</textarea>
					<span class="LV_invalid none" style="color: red;"></span>
					</td>
				</tr>
			</table>
<table align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr align="center">
					<td colspan="1" class="h50">
						<input type="button" class="selectBtn" id="submit" value="提交" onclick="sub();"/>
						&nbsp;&nbsp;
						<input type="button" class="selectBtn" name="reback" value="返回"
							onclick="bakIframe('${map.label}','${map.bakurl}')">
					</td>
				</tr>
</table>
</form> 

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
