<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<title>发文稿</title>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
<link rel="stylesheet" href="${ctx}/css/common.css"></link>
<link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquery-ui-jquiGui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/flexigrid/flexigrid.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquiGui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css"/>
<link rel="stylesheet" type="text/css" href="views/common/css/themes/css/test/metroStyle/metroStyle.css" />
<style>
.li_content{
	width: 60px;
	text-align: center;
}
.input_240{width:700px;}
</style>
<script src="${ctx}/views/common/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/styles/js/snaker/dialog.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/views/common/js/zTree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx}/views/common/js/zTree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${ctx}/views/common/js/zTree/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="${ctx}/views/common/js/jquery-ui.custom.min.js"></script>
<script src="${ctx}/views/common/js/common.js" type="text/javascript"></script>
<script src="${ctx}/views/common/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</head>
<body style="overflow:scroll;">
<p class="btt" style=" font-size:20px;">常州市水利局发文阅办单<span id="detail"></span></p>
	<div class="sl-content" >
	<input type="hidden" value="${map.step}" name="step" id="step">
	<input type="hidden" id="processId" name="processId" value="${map.processId}" />
	<input type="hidden" id="gkprocessId" name="gkprocessId" value="${map.gkprocessId}" />
	<input type="hidden" id="orderId" name="orderId" value="${map.orderId}" />
	<input type="hidden" id="taskId" name="taskId" value="${map.taskId}" />
	<input type="hidden" id="orgId" name="orgId" value="${admin.org_id}" />
	<input type="hidden" id="taskName" name="taskName" value="${map.taskName}" />
	<input type="hidden" id="document_wh" name="document_wh" value="${map.document_wh}"/>
	<table class="table_all" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-top: 0px">
		<tr>
			<td class="td_table_1"><span>拟稿人：</span></td>
			<td class="td_table_2">
				<input type="text" id="first_ng" name="first_ng" class="input_240" value="${admin.truename}" readonly="readonly" />
			</td>
		</tr>
		<tr>
			<td class="td_table_1"><span>主办处室：</span></td>
			<td class="td_table_2">
				<input type="text" class="input_240" id ="zbcs" name="zbcs" value="${admin.org_name}" readonly="readonly" />
			</td>
		</tr> 
		<tr>
			<td class="td_table_1"><span>发文类型：</span></td>
			<td class="td_table_2">
				<select id="document_type" class="input_240"  name="document_type" ></select>
				<span class="LV_invalid none" style="color: red;"></span>
			</td>
		</tr>
		<tr>
			<td class="td_table_1"><span>是否公开：</span></td>
			<td class="td_table_2">
				<select id="sfgk" class="input_240">
					  <option value="">请选择</option>
					  <option value="是">是</option>
					  <option value="否">否</option>
				</select>
				<span class="LV_invalid none" style="color: red;"></span>
			</td>
		</tr>
		<tr id="gktitle_tr" style="display:none">
			<td class="td_table_1"><span>公开标题：</span></td>
			<td class="td_table_2">
				<input type="text" id="gk_title" name="gk_title" class="input_240" value="${map.gk_title}" onblur="checkEmpty('gk_title','公开标题')"/>
				<span class="LV_invalid none" style="color: red;"></span>
			</td>
		</tr>
		<tr id="cbr_tr" style="display:none">
			<td class="td_table_1"><span>保密承办人：</span></td>
			<td class="td_table_2">
				<input type="hidden" id="nextOperator" name="nextOperator">
				<input type="text" id="nextOperatorName" name="nextOperatorName" class="input_240" readonly="readonly" value="${map.nextOperatorName}" onblur="checkEmpty('nextOperatorName','承办人')"/>
				<span class="LV_invalid none" style="color: red;"></span>
				<input type='button' class='button_100px' value='选择承办人' id="selectOrgBtn" onclick="selectPart('departMent',depart,0,'select-body-bmassgin');"/>
			</td>
		</tr>
		<tr id="ly_tr" style="display:none">
			<td class="td_table_1"><span>理由：</span></td>
			<td class="td_table_2">
				<input type="text" id="ly" name="ly" class="input_240" value="${map.ly}" onblur="checkEmpty('ly','理由')"/>
				<span class="LV_invalid none" style="color: red;"></span>
			</td>
		</tr>
		<tr>
			<td class="td_table_1"><span>发文标题：</span></td>
			<td class="td_table_2">
			<input type="text" id="title" class="input_240" name="title" onblur="checkEmpty('title','标题')" value="${map.title}"/>
			<span class="LV_invalid none" style="color: red;"></span>
			</td>
		</tr>
		
			<tr>
					<td class="td_table_1"><span>上传附件：</span></td>
					<td class="td_table_2">
					<form action="upload!upl.action" method="POST" enctype="multipart/form-data" id="upfile_form" target="result_upfile" >				  
					 <input type="file" id="plann_file" name="plann_file" style="margin-left: 5px;" />
					<!--为了支持ie8的上传改成form表单 -->
				    </form>
					<iframe id="result_upfile" name="result_upfile" style="position:absolute; top:-9999px; left:-9999px">					
					</iframe>				
					</td>
				</tr>
			<tr>
					<td class="td_table_1"><span>文件详情：</span></td>
					<td class="td_table_2">
					<input type="hidden" name="path_plann_file" id="path_plann_file" value="${map.path_plann_file}"/>
					<input type="text"  class="input_240" readonly="readonly" name="promotionShow" value="${map.promotionShow}" id="promotionShow" onblur="checkEmpty('promotionShow','附件')"/>			
					<span class="LV_invalid none" style="color: red;"></span>
					</td>
			</tr>
		<tr>
			<td class="td_table_1"><span>主送机关：</span></td>
			<td class="td_table_2">
				<input type="text" class="input_240" id="first_zsjg"  name="first_zsjg"  onblur="checkEmpty('first_zsjg','主送机关')" value="${map.first_zsjg}" placeholder="抄送机关及附件列于文末" />
				<span class="LV_invalid none" style="color: red;"></span>
			</td>
		</tr>
	</table>
	<table align="center" border="0" cellpadding="0" cellspacing="0" style=" position: fixed;bottom:20px;">
		<tr align="center">
			<td colspan="1" class="h50">
				<input type="button" class="selectBtn" id="submit" style="" value="提交" onclick="sub();"/>
				&nbsp;&nbsp;
				<input type="button" class="selectBtn" name="reback" value="返回"
					onclick="bakIframe('${map.label}','${map.bakurl}')">
			</td>
		</tr>
	</table>
		</div>
	  		<div id="dlg_auth_distribute" style="display:none">
				<input type="hidden" name="userList" id="userList"/>
				<form  name ="authchoose">
					<table border="0">
						<tr style="height:35px">
							<td class="wi-align-l" style="text-align:center">可分配用户：</td>
							<td class="wi-align-l" ></td>
							<td class="wi-align-l" style="text-align:center">已选择用户：</td>
							
						</tr>
						<tr>
							<td>
								<select multiple name="username_list1" id="username_list1" class="wi-sel-1" style="width:150px;height:260px" >
								</select>
							</td>
							<td style="width:200px;text-align:center">
								<input style="width:50px" type ="button" value ="添加" onclick="leave_user.moveOption(document.authchoose.username_list1,document.authchoose.username_list2);"><br/><br/>
								<input style="width:50px" type ="button" value ="移除" onclick="leave_user.moveOption(document.authchoose.username_list2,document.authchoose.username_list1);"><br/>
							</td>
							<td>
								<select multiple name="username_list2" id="username_list2" class="wi-sel-1" style="width:150px;height:260px" >
								</select>
							</td>
						</tr>
					</table>
				 </form>
			</div>
			<div class="modal-body" id="select-body-bmassgin" style="display:none;overflow: scroll;">
		 		<div id="menuContent" style="">
				<ul id="treeDemo_bmassgin" class="ztree" style="margin-top:0;width:300px;background: #f0f6e4;"></ul>
			    </div>		 
		   </div>
		   
		   <div id="view_fwg" style="display:none;">	
		           <div class="outer">
					<ul class="tab">
						<li class="current" onclick="personDetails(1)" id="begin">流程详情</li>
						<li onclick="personDetails(2)">查看表单</li>
					    
					</ul>

					<div class="content_y">
					     		<div id="flowTable"></div>
						    	<div id="formTable"></div>

					</div>			
					</div>
					
					</div>
<div><a id="msg_end" name="1" href="#1">&nbsp</a></div>					
</body>
<script type="text/javascript">

    $(function() {
		$("#begin").click();
	});
	function cleaveTime(){
		WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});
	}

    $(function()
    {    
    	//$.browser这个api从jQuery1.9开始就正式废除,手动添加
    	//jQuery.browser={};(function(){jQuery.browser.msie=false; jQuery.browser.version=0;if(navigator.userAgent.match(/MSIE ([0-9]+)./)){ jQuery.browser.msie=true;jQuery.browser.version=RegExp.$1;}})();
    	
    	 $.ajax({
	            type:"get",
	            dataType:"text",
	            async:false,
	            url:"docum!getdocument_dic.action",
	            success:function(result) 
	            {
	            	var data=eval("("+result+")");
					var html = '<option value="">请选择</option>';
						for (var i in data) 
						{
							html += '<option value="'+data[i].DIC_NAME+'">'+data[i].DIC_NAME+'</option>';
						}
					
					$("#document_type").html(html);
	            }
	        });
	        $("#document_type").change(function()
	        { 	
				var  wh = $(this).children('option:selected').val(); 
				 $("#document_wh").val(wh);
				 //alert($(this).children('option:selected').text()); 
			});
	    	$("#sfgk").change(function(){
	    		if($(this).val() == ''){
	    			$("#ly_tr").css("display","none");
	    			$("#gktitle_tr").css("display","none");
	    			$("#cbr_tr").css("display","none");
	    		}else if($(this).val() == '是'){
	    			$("#cbr_tr").removeAttr("style");
	    			$("#gktitle_tr").removeAttr("style");
	    			$("#ly_tr").css("display","none");
	    		}else{
	    			$("#ly_tr").removeAttr("style");
	    			$("#gktitle_tr").css("display","none");
	    			$("#cbr_tr").css("display","none");
	    		}
	    	});
	        if('${map.orderId}' != ''){
	        	$("#document_type").val('${map.document_wh}');
	        	$("#sfgk").val('${map.sfgk}');
	        	if('${map.sfgk}' == '是'){
	    			$("#cbr_tr").removeAttr("style");
	    			$("#gktitle_tr").removeAttr("style");
	    			$("#ly_tr").css("display","none");
	    			$("#nextOperator").val('${map.nextOperator}');
	    			$("#nextOperatorName").val('${map.nextOperatorName}');
	    			$("#selectOrgBtn").css("display","none");
	    		}else{
	    			$("#ly_tr").removeAttr("style");
	    			$("#gktitle_tr").css("display","none");
	    			$("#cbr_tr").css("display","none");
	    		}
	    		$("#view_fwg").show();
	    		$("#submit").val("重新提交");
	    		$("#submit").attr("style","color:red");
	    		$("#detail").html("【被退回】");
	    		$("#detail").attr("style","color:red");	    		
	        } 
	        else
	        {
	        	$("#view_fwg").hide();
	        }
    });
     
   
    function sub()
    {			
    	$("#document_wh").blur();
    	$("#title").blur();
    	$("#first_zsjg").blur();
    	$("#promotionShow").blur();
    	if($("#sfgk").val() == '否'){
    		$("#ly").blur();
    		$("#gk_title").nextAll(".LV_invalid").addClass("none").html("");
    		$("#nextOperatorName").nextAll(".LV_invalid").addClass("none").html("");
    	}
    	if($("#sfgk").val() == '是'){
    		$("#gk_title").blur();
    		$("#nextOperatorName").blur();
    		$("#ly").nextAll(".LV_invalid").addClass("none").html("");
    	}
    	if ($("#document_type").val() == "") {
    		$("#document_type").nextAll(".LV_invalid").removeClass("none").html("*必选");
    	}else{
    		$("#document_type").nextAll(".LV_invalid").addClass("none").html("");
    	}
    	if ($("#sfgk").val() == "") {
    		$("#sfgk").nextAll(".LV_invalid").removeClass("none").html("*必选");
    	}else{
    		$("#sfgk").nextAll(".LV_invalid").addClass("none").html("");
    	}
    	if(($("span[class='LV_invalid']").length != 0)){
			return;
		}else{
			var queryP = {};
			queryP.first_ng = HtmlEncode($("#first_ng").val());
            queryP.title = HtmlEncode($("#title").val());
            queryP.first_zsjg = HtmlEncode($("#first_zsjg").val());
            queryP.zbcs = HtmlEncode($("#zbcs").val());
            queryP.processId = HtmlEncode($("#processId").val());
            queryP.orderId = HtmlEncode($("#orderId").val());
            queryP.taskId = HtmlEncode($("#taskId").val());
            queryP.orgId = $("#orgId").val();
            queryP.step = $("#step").val();
            queryP.taskName = $("#taskName").val();
            queryP.document_type=  $("#document_type option:selected").text();
            queryP.sfgk=  $("#sfgk option:selected").text();
            queryP.document_wh=  $("#document_wh").val();
            if( queryP.sfgk == '否'){
            	queryP.ly=  $("#ly").val();	
            }else{
            	queryP.gkprocessId = $("#gkprocessId").val();
            	queryP.gk_title=$("#gk_title").val();
            	queryP.nextOperator = $("#nextOperator").val();
            	queryP.nextOperatorName = $("#nextOperatorName").val();
            }
            queryP.path_plann_file = $("#path_plann_file").val().replace(/\\/g,"@");
            queryP.promotionShow = $("#promotionShow").val().replace(/\\/g,"@");
            queryP.isorg = 1;
            leave_user.changeAuth(queryP);	
		}	
    }
    
    
    var leave_user = 
				{
					changeAuth:function(queryP)
					{
			 			leave_user._queryP = queryP;
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
						title:'选择下一步审批人',
						buttons:						
						{
							"确定":function()
							{
								//提交按钮背景设为灰色，改为不可点击状态
								$("#sub_button_set").attr("style", "background-color:gray");
								 $("#sub_button_set").attr("disabled", true);
								leave_user.saveAuth();
							}
						}
					});
					var url_t="";
					/*
					if(queryP.isorg==1)
					{
					   url_t = "queryUser!getUserList.action?assignee=${map.assignee}&org_id=${admin.org_id}";
					}
					else
					{
					  url_t = "queryUser!getUserList.action?assignee=${map.assignee}";
					}*/
					url_t = "queryUser!getUserList.action?assignee=${map.assignee}&org_id=${map.ord}&autoOrg=${map.autoOrg}";
			        $.ajax({
			            type:"get",
			            dataType:"text",
			            async:false,
			            url:url_t,
			            success:function(result) 
			            {
			                $("#username_list1").html("");
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
			saveAuth:function()
			{
				var admin_id ="";
				var username ="";
				if(document.authchoose.username_list2.options.length==0)
				{
					showMsg("请选择用户！");
					return;
				}
				 for(var i=0;i<document.authchoose.username_list2.options.length;i++){
					admin_id += document.authchoose.username_list2.options[i].value+",";
					username += document.authchoose.username_list2.options[i].text+",";
				}
				//$("#userList").val(admin_id);
				$('#dlg_auth_distribute').dialog('close');
				var queryP = leave_user._queryP;
				    queryP.userList = admin_id;
				 $.ajax({
		        	url:"docum!process.action",
		        	type:"POST",
		        	cache: false,
		    		data:queryP,
		    		success: function(msg){
		    			if("success" === msg)
		    			{
		    				alert("提交成功!");
		    				bakIframe("${map.label}","${map.bakurl}");
		    			}
		    		}      	
        		});   
			},
		   moveOption:function(list1,list2)
		   {
				 var options = list1.options;				 
				 for(var i=0;i<options.length;i++)
				 {
					 if(options[i].selected){
						 list2.appendChild(options[i]);
						 i--;
					 }
			     }
	       } 
	};
    
    			    $("#plann_file").change(function(){
    		        	$("#upfile_form").submit();
    				}); 
    				
    				//调用返回上传结果 
    			    function get_upfile_result(res){
    			     	if(1 == Number(res.mess_state))
    			     	{
    			     		alert(res.message);
    			     		 $("#promotionShow").val(res.newFileName);
    				        $("#path_plann_file").val(res.rs_path);
    				        //$("#plann_file").val("");
    				        
    			            //$("#upButton").val("重新上传");
    			     	}else{
    			     		alert(res.message);
    			     	}
    			    };

    
    			  //--------------------------------------department_person-----------------------------------------------
    			    var treeObj;
    			       var id_arr = new Array();
    			       var name_arr = new Array();
    			   	function add_html(){
    			   		if(type == 0){
    			   			$("#nextOperatorName").val(name_arr);
    			   			$("#nextOperator").val(id_arr);
    			   		}
    			   		if(type == 1){
    			   			$("#ccOperatorName").val(name_arr);
    			   			$("#ccOperator").val(id_arr);
    			   		}
    			   	}
    			   	
    			   	function del_html(){
    			   		if(type == 1){
    			   			$("#ccOperatorName").val("");
    			   			$("#ccOperator").val("");
    			   		}
    			   	}
    			       //ztree

    			   		
    			   		function onChooseOrg(e, treeId, treeNode) {
    			   			var orgId = treeNode.ORG_ID;
    			   			if(orgId.indexOf("_personId_") != -1 && treeNode.checked){
    			   				id_arr.push(orgId.substring(orgId.indexOf("_personId_")+"_personId_".length,orgId.length));
    			   				name_arr.push(treeNode.ORG_NAME);
    			   				add_html();
    			   			}else if(orgId.indexOf("_personId_") != -1 && !treeNode.checked){
    			   				for(var i = 0; i < name_arr.length; i++){
    			   					if(treeNode.ORG_NAME == name_arr[i]){
    			   						name_arr.splice(i,1);
    			   						id_arr.splice(i,1);
    			   					}
    			   				}
    			   				add_html();
    			   			}
    			   		}

    			   		
    			   		var setting = {
    			   			view: {
    			   				dblClickExpand: false,
    			   				showLine: false
    			   			},
    			   			check:{
    			   				enable: true,
    			   				autoCheckTrigger: true,
    			   				chkStyle: "checkbox",
    			   				chkboxType: { "Y": "ps", "N": "ps" }
    			   			},
    			   			data: {
    			   				key:{
    			   					name: "ORG_NAME"
    			   				},
    			   				simpleData: {
    			   					enable: true,
    			   					name: "ORG_NAME",
    			   					idKey: "ORG_ID",
    			   					pIdKey: "SUPER_ORG_ID",
    			   					rootPId: 0
    			   				}
    			   			},
    			   			callback: {
    			   				onCheck: onChooseOrg
    			   			}
    			   		};
    			       var type = null;
    			       function depart(){
    			       	type = arguments[0];
    			       	//$("#select-body").load("./model_pc/department/department_person.jsp?math_radom="+Math.random());
    			       	//-------------------------------------------加载页面后初始化加载页面的内容
    			   		del_html();
    			       	$.ajax({
    			   			type: "POST",
    			   			url: "docum!queryOrganizationInfos.action?math_radom="+Math.random(),
    			   			cache: false,
    			   			data: null,
    			   			success: function(msg){
    			   				var data=eval("("+msg+")");
    			   				$.fn.zTree.init($("#treeDemo_bmassgin"), setting, data);
    			   				
    			   				treeObj  =$.fn.zTree.getZTreeObj("treeDemo_bmassgin");
    			   				//var nodes = treeObj.transformToArray();
    			   				var nodes = treeObj.getNodes();
    			   				var nodes2;
//    			    				for (var i = 0; i < nodes.length; i++) { //设置节点展开
    			   					nodes2 = nodes[0].children;
    			   					for (var j = 1; j < nodes2.length; j++) {
    			   						treeObj.expandNode(nodes2[j], true, false, true);
    			   					}
//    			    	            }
    			   				
    			   				var val = $("#nextOperatorName").val();
    			   				if($.trim(val).length != 0){
    			   					var array =	val.split(",");
    			   					var node;
    			   					for(var i = 0 ; i < array.length ; i++){
    			   						node  =treeObj.getNodeByParam("ORG_NAME",array[i],null);
    			   						treeObj.checkNode(node,true, true);
//    			    						checkParentNode(node.SUPER_ORG_ID);
    			   					}
    			   				}
    			   			}
    			       	
    			   		});
    			       }
    			       function checkParentNode(super_Id){
    			   		var node  =treeObj.getNodeByParam("ORG_ID",super_Id,null);
    			   		treeObj.checkNode(node,true, false);
    			   		if(node.SUPER_ORG_ID != 0){
    			   			checkParentNode(node.SUPER_ORG_ID);
    			   		}
    			   	}   
    			    
    			   
    
	function personDetails(com) {

		$("#formTable").html("");
		$("#flowTable").html("");

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
					$("#flowTable").append(result_str);
				} else {
					$("#formTable").append(result);
				}
				$("#view_fwg .tab li").click(function()
				{
					$("#view_fwg .tab li").removeClass("current");
					$(this).addClass("current");
					var n = $(this).index();
					$("#view_fwg .content_y ul").hide();
					$("#view_fwg .content_y ul").eq(n).show();
		        });
			}
		});
		document.getElementById("msg_end").click(); 

	} 
</script>
</html>

