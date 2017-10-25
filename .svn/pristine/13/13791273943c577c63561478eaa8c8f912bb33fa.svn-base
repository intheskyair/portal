<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" >
	<head>
	<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css"/>
		 <link rel="stylesheet" href="${ctx}/css/bootstrap-3.3.5/css/bootstrap.min.css" /> 
		<link type="text/css" rel="stylesheet" href="${ctx}/css/common.css"></link>
		<link rel="stylesheet" href="${ctx}/js/kindeditor/themes/default/default.css" />
        <link rel="stylesheet" href="${ctx}/js/kindeditor/themes/simple/simple.css" />
             <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquery-ui-jquiGui.css"/>
             <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquiGui.css"/>
		<style>
		.li_content{
			width: 60px;
			text-align: center;
		}
	fieldset{border:1px solid #9AC3E1;margin-top:10px;}
	legend{font-size: 16px;color: #0071b1;font-weight: bold;}
		</style>
		<link rel="stylesheet" type="text/css" href="views/common/css/themes/css/test/metroStyle/metroStyle.css" />
		 <script src="${ctx}/css/bootstrap-3.3.5/js/jquery-1.9.1.min.js" type="text/javascript"></script> 
		<script type="text/javascript" src="${ctx}/views/common/js/jquery-ui.custom.min.js"></script>
		<script type="text/javascript" src="${ctx}/views/common/js/jquery-migrate-1.2.1.js"></script>  
		 <script src="${ctx}/css/bootstrap-3.3.5/js/bootstrap.min.js" type="text/javascript"></script> 
		<script src="${ctx}/styles/js/snaker/dialog.js" type="text/javascript"></script> 
		<script src="${ctx}/views/common/js/common.js" type="text/javascript"></script>		
		<script type="text/javascript" src="${ctx}/views/common/js/zTree/jquery.ztree.core.js"></script>
		<script type="text/javascript" src="${ctx}/views/common/js/zTree/jquery.ztree.excheck.js"></script>
		<script type="text/javascript" src="${ctx}/views/common/js/zTree/jquery.ztree.exedit.js"></script>
        <script src="${ctx}/styles/js/snaker/snaker.util.js" type="text/javascript"></script>
	<script type="text/javascript">
	
	 $(function(){
	        $("#begin_secrecy").click();
			
			if("${map.displayName}" == "end"){
				$("#nextOp").css("display","none");
			}
     }); 
	
    
    function sub(){
    	 $.ajax({
		        	url:"gkbm!process.action",
		        	type:"POST",
		        	cache: false,
		    		data:$("#inputForm1").serialize(),
		    		success: function(msg){
		    			if("success" === msg){
		    			alert("提交成功");
		    			}else if(msg == "repeat"){
		    				alert("该流程已批阅");
		    			}else if(msg == "error"){
		    				alert("系统出错");
		    			}
		    			bakIframe("${map.label}","${map.bakurl}");
		    		}      	
        		});
    }
    
       function closeIframe(){
    	$(window.top.document).find(".ui-tabs-nav > li.ui-state-active > div > span.tabs-close").click();
    }
    

        
        

	

	
	

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
	   			//var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	   			var orgId = treeNode.ORG_ID;
	   			if(orgId.indexOf("_personId_") != -1 && treeNode.checked){
	   				//zTree.expandNode(treeNode);
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
	       	$("#select-body").load("./model_pc/department/department_person.jsp?math_radom="+Math.random());
	       	//-------------------------------------------加载页面后初始化加载页面的内容
	   		del_html();
	       	$.ajax({
	   			type: "POST",
	   			url: "docum!queryOrganizationInfos.action?math_radom="+Math.random(),
	   			cache: false,
	   			data: null,
	   			success: function(msg){
	   				var data=eval("("+msg+")");
	   				$.fn.zTree.init($("#treeDemo"), setting, data);
	   				
	   				treeObj  =$.fn.zTree.getZTreeObj("treeDemo");
	   				//var nodes = treeObj.transformToArray();
	   				var nodes = treeObj.getNodes();
	   				var nodes2;
//	    				for (var i = 0; i < nodes.length; i++) { //设置节点展开
	   					nodes2 = nodes[0].children;
//	    					console.log("i:"+i+"-nodes1.length-"+nodes.length+"-nodes2.length:"+nodes2.length);
	   					for (var j = 1; j < nodes2.length; j++) {
	   						treeObj.expandNode(nodes2[j], true, false, true);
	   					}
//	    	            }
	   				
	   				var val = $("#nextOperatorName").val();
	   				if($.trim(val).length != 0){
	   					var array =	val.split(",");
	   					var node;
	   					for(var i = 0 ; i < array.length ; i++){
	   						node  =treeObj.getNodeByParam("ORG_NAME",array[i],null);
	   						treeObj.checkNode(node,true, true);
//	    						checkParentNode(node.SUPER_ORG_ID);
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
     
    
     
     //详情及表单
      function personDetails(com) {
         
			$("#formTable_secrecy").html("");
				$("#flowTable_secrecy").html("");
		         
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
								    $("#flowTable_secrecy").append(result_str);
								    }else{
								    $("#formTable_secrecy").append(result);
								    }
								    $("#view_secrecy .tab li").click(function()
								    {
										$("#view_secrecy .tab li").removeClass("current");
										$(this).addClass("current");
										var n = $(this).index();
										$("#view_secrecy .content ul").hide();
										$("#view_secrecy .content ul").eq(n).show();
		                            });
								},
								
							})
							
						
							
						
			}
</script>
		
	</head>
	<body style="height:100%;overflow: scroll;" >

		<form id="inputForm1" action="" method="post" target="mainFrame"  >
			<input type="hidden" name="processId" value="${map.processId }" />
			<input type="hidden" name="orderId" value="${map.orderId }" />
			<input type="hidden" name="taskId" value="${map.taskId }" />
			<input type="hidden" name="taskName" value="${map.taskName }" />
			<input type="hidden" name="displayName" value="${map.displayName}"/>
		<fieldset>
           <legend>【${map.label}】</legend>		


			<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0" style="margin-top: 0px">
			<tr>
				<td class="td_table_1">
					<span>公开审核意见：</span>
				</td>
				<td class="td_table_2" colspan="3">
					<textarea class="input_textarea_320" id="suggest" name="suggest"  ></textarea>
				</td>
			</tr>
			<tr id="nextOp">
			<td class="td_table_1"><span>下一步处理人：</span></td>
			<td class="td_table_2">
				<input type="hidden" id="nextOperator" name="nextOperator">
				<input type="text" id="nextOperatorName" class="input_240" readonly="readonly" value="${map.nextOperatorName}" onblur="checkEmpty('nextOperatorName','承办人')"/>
				<span class="LV_invalid none" style="color: red;"></span>
				<input type='button' class='button_100px' value='选择承办人' id="selectOrgBtn" onclick="selectPart('departMent',depart,0);"/>
			</td>
			</tr>
			</table>
			</fieldset>
			    <script type="text/javascript" src="${ctx}/views/shell/js/menu.js"></script>
			<table align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr align="center">
					<td colspan="1" class="h50">
						<input type="button" class="selectBtn" style="" id="submit"  value="提交" onclick="sub();"/>
						&nbsp;&nbsp;
						<input type="button" class="selectBtn" name="reback" value="返回"
							onclick="bakIframe('${map.label}','${map.bakurl}')">
						&nbsp;&nbsp;
<!-- 							<input type="button" id="bt" class="selectBtn" value="查看" onClick="show()">  -->
					</td>
				</tr>
			</table>
		</form>
	
		<div id="view_secrecy">
		  <div class="outer">
			<ul class="tab">
				<li class="current" onclick="personDetails(1)" id="begin_secrecy">流程详情</li>
				<li onclick="personDetails(2)">查看表单</li>
			</ul>
			<div class="content">
				<ul>
					<li class="current">
						<div id="flowTable_secrecy"></div>
					</li>
				</ul>
				<ul>
					<li>
						<div id="formTable_secrecy"></div>
					</li>
				</ul>
		   
		</div>
		</div>
	</div>	
				<div class="modal fade" id="departMent" tabindex="-1" >
			   <div class="modal-dialog" style="width: 350px;">
			      <div class="modal-content">
			         <div class="modal-header">
			            <button type="button" class="close" onclick="closeDialog('departMent');" >×</button>
			            <h4 class="modal-title">
			               	部门选择人员
			            </h4>
			         </div>
			         <div class="modal-body" id="select-body">
			            		
			         </div>
			         <div class="modal-footer">
			         </div>
			      </div>
			   </div>
			</div>
		
	</body>
</html>
