<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css"/>
<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/common.css"></link>
<link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquery-ui-jquiGui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/flexigrid/flexigrid.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquiGui.css"/>
<script type="text/javascript" src="${ctx}/views/common/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/views/common/js/jquery-ui.custom.min.js"></script>
<script src="${ctx}/views/common/js/common.js" type="text/javascript"></script>
<script src="${ctx}/views/common/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<style type="text/css">
		#add{
			display:block;
			width: 20px;
			height: 18px;
			background:url(./views/common/css/themes/css/flexigrid/images/icon/add.png) no-repeat center left;
		}
   .dwb{ width:95%;}
	</style>
<title>常州市水利局文件阅办单</title>
</head>
<body style="overflow:scroll">
<p class="btt" style=" font-size:20px;">常州市水利局收文阅办单<span id="detail"></span></p>
	<input type="hidden" value="${map.step}" name="step" id="step">
	<input type="hidden" id="processId" name="processId" value="${map.processId}" />
	<input type="hidden" id="orderId" name="orderId" value="${map.orderId}" />
	<input type="hidden" id="taskId" name="taskId" value="${map.taskId}" />
	<!-- 下步流程权限ID -->
	<input type="hidden" id="assignee" name="assignee" value="${map.assignee}"/>
	<!-- 当前流程名称 -->
	<input type="hidden" id="taskName" name="taskName" value="${map.taskName}" />
	<input type="hidden" id ="call" name="call" class="input_240" value="${map.call}"/>

<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px">
				<tr>
					<td class="td_table_1"><span>来文类型：</span></td>
					<td class="td_table_2">
						<select id="sw_type" class="input_240" onchange="swTypeNum()" onblur="checkEmpty('sw_type','类别')"  >
							<option value="">选择类别</option>
							<option value="sw_sllt">省水利厅</option>
							<option value="sw_swszf">市委市政府</option>
							<option value="sw_sydw">事业单位</option>
							<option value="sw_sxq">市辖区</option>
						</select>
						<span class="LV_invalid none" style="color: red;"></span>
						<input type="text" id ="num" name="num" class="input_240"   readonly="readonly" />号
						
						<input type="radio" id="bcqx" name="bcqx" checked="checked" value="普通"/>普通
						<input type="radio" id="bcqx" name="bcqx" value="10年"/>10年
						<input type="radio" id="bcqx" name="bcqx" value="20年"/>20年
						<input type="radio" id="bcqx" name="bcqx" value="永久"/>永久
					</td>
				</tr>
					<tr>
					<td class="td_table_1"><span>来文日期：</span></td>
					<td class="td_table_2">
						<input type="text" id="rec_time" name="rec_time"  class="input_240"  value="${map.rec_time}"  onFocus="WdatePicker({readOnly:true})" readonly="readonly"	 onblur="checkEmpty('rec_time','来文日期')"/>
						<span class="LV_invalid none" style="color: red;"></span>
					</td>				
				</tr>
				<tr>
					<td class="td_table_1"><span>来文单位：</span></td>
					<td class="td_table_2">
						<input type="text" class="input_240" id ="org_name" name="org_name" style="width: 60%;" value="${map.org_name}" onblur="checkEmpty('org_name','来文单位')" />
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
				</tr>
			
				<tr>
					<td class="td_table_1"><span>来文标题：</span></td>
					<td class="td_table_2">
						<input type="text" class="input_240" id ="title" name="title" style="width: 60%;"  value="${map.title}" onblur="checkEmpty('title','来文标题')" />
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
				</tr>
				<tr>
					<td class="td_table_1"><span>上传附件：</span></td>
					<td class="td_table_2">
					<form action="upload!upl.action" method="POST" enctype="multipart/form-data" id="upfile_form" target="result_upfile" >				  
					 <input type="file" id="plann_file" name="plann_file" style="margin-left: 5px;"/>
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
						<input type="text"  class="input_240" readonly="readonly" style="width: 60%;" name="promotionShow" value="${map.promotionShow}" id="promotionShow" onblur="checkEmpty('promotionShow','附件')"/>			
						<span class="LV_invalid none" style="color: red;"></span>
					</td>
				</tr>
				<tr>
					<td class="td_table_1"><span>备注：</span></td>
					<td class="td_table_2">
						<textarea class="input_textarea_320" readonly="readonly" id="bz" name="bz" style="color: red">1.必退文件注意保管   2.文件请速阅速退，并标明推文时间。</textarea>
					</td>
			</table>
		<table align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr align="center">
					<td colspan="1" class="h50">
						<input type="button" class="selectBtn" id="submit" style="" value="提交" onclick="choosesub();"/>
						&nbsp;&nbsp;
						<input type="button" class="selectBtn" name="reback" value="返回"
							onclick="bakIframe('${map.label}','${map.bakurl}')">
					</td>
				</tr>
        </table>

<div id="dlg_auth_distribute" style="display:none;">
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
						<input style="width:50px" type ="button" value ="添加" onclick="moveOption(document.authchoose.username_list1,document.authchoose.username_list2);"><br/><br/>
						<input style="width:50px" type ="button" value ="移除" onclick="moveOption(document.authchoose.username_list2,document.authchoose.username_list1);"><br/>
					</td>
					<td>
						<select multiple name="username_list2" id="username_list2" class="wi-sel-1" style="width:150px;height:260px" >
						</select>
					</td>
				</tr>
			</table>
      </form>
	</div>
	
	
	<div id="view_recdoc" style="display:none;">	
		<div class="outer">
					<ul class="tab">
						<li class="current" onclick="personDetails(1)" id="begin">流程详情</li>
						<li onclick="personDetails(2)">查看表单</li>
					    
					</ul>
					<div class="content_y">
					     		<div id="flowTable_recdoc"></div>
						    	<div id="formTable_recdoc"></div>
					</div>			
		</div>
	</div>
	<div><a id="msg_end" name="1" href="#1">&nbsp</a></div>
</body>
<script type="text/javascript">

	$(function() {
		$("#begin").click();
	});
	function mydownload(path) {
		location.href = 'upload!download.action?&path='
				+ encodeURI(encodeURI(unescape(path)));
	}

	$(function() {

		var myDate = new Date();
		$("#rec_time").val(
				myDate.getFullYear() + '-' + (myDate.getMonth() + 1) + '-'
						+ myDate.getDate());
		if ("${map.orderId}" != '') {
			$("#sw_type option:contains('${map.call}')").attr("selected", true);
			$("input[type=radio][name='bcqx'][value='${map.bcqx}']").attr(
					"checked", 'checked');
			//$("#upButton").val("重新上传");
			$("#view_recdoc").show();
				$("#submit").val("重新提交");
	    		$("#submit").attr("style","color:red");
	    		$("#detail").html("【被退回】");
	    		$("#detail").attr("style","color:red");
		} else {
			$("#view_recdoc").hide();
		}
		//加载拟办意见
		//此操作3个页面都用到了，但是没有做通用处理，就这一个，就这样了，时间太少， recdoc.jsp,recdocSend.jsp,recdocView.jsp
		if ('${map.rect3method}' != "") {
			var method = "";
			var suggest = "";
			if ("${map.rect3method}" == 0) {
				method = "同意";
			} else {
				method = "不同意";
			}
			if ("${map.rect3suggest}" != '') {
				suggest = "(" + "${map.rect3suggest}" + ")";
			} else {

			}
			$("#viewrecd")
					.append(
							"<tr><td>拟办意见</td><td colspan='4'><textarea readonly='readonly' class='input_textarea_320'>"
									+ method
									+ suggest
									+ "</textarea></td></tr>");
		}

		if ('${map.rect5}' != "") {
			var data = JSON2.parse('${map.rect5}');
			$("#viewrecd")
					.append(
							"<tr><td>职位</td><td>姓名</td><td>处理时间</td><td>意见</td><td>备注</td></tr>");
			for ( var i in data) {
				var method = data[i].method;
				if (method == 0) {
					method = "同意";
				} else {
					method = "不同意";
				}
				$("#viewrecd").append(
						"<tr><td>局领导</td><td>" + data[i].truename + "</td><td>"
								+ data[i].time + "</td><td>" + method
								+ "</td><td>" + data[i].suggest + "</td></tr>");
			}
		}

		if ('${map.rect7}' != "") {
			var data = JSON2.parse('${map.rect7}');
			for ( var i in data) {
				var method = data[i].method;
				if (method == 0) {
					method = "同意";
				} else {
					method = "不同意";
				}
				$("#viewrecd").append(
						"<tr><td>处室领导</td><td>" + data[i].truename
								+ "</td><td>" + data[i].time + "</td><td>"
								+ method + "</td><td>" + data[i].suggest
								+ "</td></tr>");
			}

		}

		if ('${map.rect9}' != "") {
			var data = JSON2.parse('${map.rect9}');
			for ( var i in data) {
				var method = data[i].method;
				if (method == 0) {
					method = "同意";
				} else {
					method = "不同意";
				}
				$("#viewrecd").append(
						"<tr><td>最终承办人</td><td>" + data[i].truename
								+ "</td><td>" + data[i].time + "</td><td>"
								+ method + "</td><td>" + data[i].suggest
								+ "</td></tr>");
			}
		}

		$("#plann_file").change(function() {
			$("#upfile_form").submit();
		});

	});

	function subAjax(queryP) {
		$.ajax({
			url : "recdoc!process.action",
			type : "POST",
			cache : false,
			data : queryP,
			success : function(msg) {
				if ("success" === msg) {
					showIframeMsg("提交任务成功!");
				}
			}
		});

	}

	function choosesub() {
		$("#call").blur();
		$("#sw_type").blur();
		$("#org_name").blur();
		$("#rec_time").blur();
		$("#title").blur();
		$("#promotionShow").blur();
		if (($("span[class='LV_invalid']").length != 0)) {
			return;
		} else {
			leave_user.changeAuth();
		}
	}
	function moveOption(list1, list2) {
		var options = list1.options;
		for (var i = 0; i < options.length; i++) {
			if (options[i].selected) {
				list2.appendChild(options[i]);
				i--;
			}
		}
	}
	var leave_user = {
		changeAuth : function() {
			var dlg_auth_distribute = $("#dlg_auth_distribute").dialog({
				position : [ 'center', 'top' ],
				draggable : false,
				autoOpen : false,
				height : $(window).height() * 0.8,
				width : $(window).width() * 0.5,
				show : "slide",
				modal : true,
				cache : false,
				/* hide: "explode", */
				title : '选择下一步审批人',
				buttons : {
					"确定" : function() {
						$("#submit").attr("style", "background-color:gray");
						$("#submit").attr("disabled", true);
						leave_user.saveAuth();
					}
				}
			});
			$
					.ajax({
						type : "get",
						dataType : "text",
						async : false,
						url : "queryUser!getUserList.action?assignee=${map.assignee}",
						success : function(result) {
							$("#username_list1").html("");
							var data = eval("(" + result + ")");
							var html = '';
							var html2 = '';
							var leftValue = $("#userList").val();
							if (leftValue.length == 0) {
								for ( var i in data) {
									html += '<option value="'+data[i].USERNAME+'">'
											+ data[i].TRUENAME + '</option>';
								}
								$("#username_list1").append(html);
							} else {
								var leftVals = leftValue.split(",");
								for ( var i in data) {
									var flag = false;
									for ( var j in leftVals) {
										if (data[i].TRUENAME == leftVals[j]) {
											flag = true;
											html2 += '<option value="'+data[i].USERNAME+'">'
													+ data[i].TRUENAME
													+ '</option>';
										}
									}
									if (!flag) {
										html += '<option value="'+data[i].USERNAME+'">'
												+ data[i].TRUENAME
												+ '</option>';
									}
								}
								$("#username_list1").append(html);
								$("#username_list2").append(html2);
							}
						}
					});
			dlg_auth_distribute.dialog("open");
		},
		saveAuth : function() {
			var admin_id = "";
			var username = "";
			if (document.authchoose.username_list2.options.length == 0) {
				showMsg("请选择用户！");
				$("#submit").attr("disabled", false);
				$("#submit").removeAttr("style");
				return;
			}
			for (var i = 0; i < document.authchoose.username_list2.options.length; i++) {
				admin_id += document.authchoose.username_list2.options[i].value
						+ ",";
				username += document.authchoose.username_list2.options[i].text
						+ ",";
			}
			//$("#userList").val(admin_id);
			$('#dlg_auth_distribute').dialog('close');
			var queryP = {};
			queryP.userList = admin_id;
			queryP.call = HtmlEncode($("#call").val());
			queryP.bcqx = HtmlEncode($("#bcqx").val());
			queryP.org_name = HtmlEncode($("#org_name").val());
			queryP.title = HtmlEncode($("#title").val());
			queryP.rec_time = HtmlEncode($("#rec_time").val());
			queryP.num = HtmlEncode($("#num").val());
			queryP.processId = $("#processId").val();
			queryP.orderId = $("#orderId").val();
			queryP.taskId = $("#taskId").val();
			queryP.step = $("#step").val();
			queryP.assignee = $("#assignee").val();
			queryP.taskName = $("#taskName").val();
			queryP.path_plann_file = $("#path_plann_file").val().replace(/\\/g,
					"@");
			queryP.promotionShow = $("#promotionShow").val()
					.replace(/\\/g, "@");
			queryP.sw_type = $("#sw_type").val();
			//console.log(queryP);
			$.ajax({
				url : "recdoc!process.action",
				type : "POST",
				cache : false,
				data : queryP,
				success : function(msg) {
					if ("success" === msg) {
						alert("提交成功!");
						bakIframe("${map.label}","${map.bakurl}");
					}
				}
			});
		}

	};
	
	function personDetails(com) {

		$("#formTable_recdoc").html("");
		$("#flowTable_recdoc").html("");

		var orderId = "${map.orderId}";
		var pid = "${map.processId}";

		if (com == 2) {
			url = "borrow!view.action";
			data = {
				"orderId" : orderId,
				"processId" : pid,
				"path" : "recdocView"
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
					$("#flowTable_recdoc").append(result_str);
				} else {
					$("#formTable_recdoc").append(result);
				}
				$("#view_recdoc .tab li").click(function(){
					$("#view_recdoc .tab li").removeClass("current");
					$(this).addClass("current");
					var n = $(this).index();
					$("#view_recdoc .content_y ul").hide();
					$("#view_recdoc .content_y ul").eq(n).show();			
				});
		       
			}
		});
		document.getElementById("msg_end").click(); 

	}

	//调用返回上传结果 
	function get_upfile_result(res) {
		if (1 == Number(res.mess_state)) {
			alert(res.message);
			$("#promotionShow").val(res.newFileName);
			$("#path_plann_file").val(res.rs_path);
		} else {
			alert(res.message);
		}
	};

	function swTypeNum() {
		if ($.trim($("#sw_type").val()) != "") {
			$("#call").val($("#sw_type").find("option:selected").text());
			$.ajax({
				url : "recdoc!getSeqByType.action?sw_type="
						+ $.trim($("#sw_type").val()),
				type : "POST",
				async : false,
				cache : false,
				success : function(result) {
					$("#num").val(eval("(" + result + ")"));
				}
			});
		}
	}
</script>
</html>

