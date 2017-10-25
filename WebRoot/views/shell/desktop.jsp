<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/taglibs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta name="renderer" content="webkit">
<title>常州市水利局办公系统登录</title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css"/>
<link rel="stylesheet" href="${ctx}/css/common.css"></link>
<link rel="stylesheet" href="${ctx}/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="${ctx}/js/kindeditor/themes/simple/simple.css" />
<link rel="icon" href="<%=request.getContextPath()%>/images/favicon.ico" mce_href="<%=request.getContextPath()%>/images/favicon.ico" type="image/x-icon" />
<script type="text/javascript" src="../views/common/js/jquery.min.js"></script>
<script type="text/javascript" src="../views/common/js/json2.js"></script>
<script type="text/javascript" src="../views/common/js/jquery.bgiframe.js"></script>
<script type="text/javascript" src="../views/common/js/jquery.contextmenu.r2.js"></script>
<!--=B 模板库  -->
<link rel="stylesheet" type="text/css" href="../views/common/css/themes/css/jquery-ui-jquiGui.css"/>
<script type="text/javascript" src="../views/common/js/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="../views/common/js/jquery.layout.min.js"></script>
<script type="text/javascript" src="../views/common/js/jquery.wresize.js"></script>
<script type="text/javascript" src="../views/common/js/ui.tabs.page.js"></script>
<script type="text/javascript" src="../views/common/js/zTree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="../views/common/js/zTree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="../views/common/js/zTree/jquery.ztree.exedit.js"></script>
<link rel="stylesheet" type="text/css" href="../views/common/css/themes/css/test/metroStyle/metroStyle.css" />
<!-- flexigrid  -->
<script type="text/javascript" src="../views/common/js/flexigrid/flexigrid.js"></script>
<link rel="stylesheet" type="text/css" href="../views/common/css/themes/css/flexigrid/flexigrid.css"/>
<!-- 验证插件 -->
<link rel="stylesheet" type="text/css" href="../views/common/css/themes/css/validate/jquery_validate.css"/>
<script type="text/javascript" src="../views/common/js/validate/jquery.validate.js"></script>
<!-- 向导插件 -->
<link rel="stylesheet" type="text/css" href="../views/common/css/themes/css/smartWizard/smart_wizard.css"/>
<!-- 扩展jQuery以及jQuery插件功能的js -->
<script type="text/javascript" src="../views/common/js/jquery.extend.js"></script>
<!-- 样式库  -->
<link rel="stylesheet" type="text/css" href="../views/common/css/themes/css/jquiGui.css" />
<!-- 模板调用变量库  -->
<script type="text/javascript" src="../views/common/css/themes/js/GlobalValue.js"></script>
<!--=E 模板库  -->
<script type="text/javascript" src="../views/common/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../views/common/js/common.js"></script>
<script type="text/javascript" src="../views/shell/js/desktop.js"></script>
<!-- echart框架-->
<script type="text/javascript" src="../js/js_echarts/echarts.js"></script>
<script type="text/javascript" src="../js/headUtil.js"></script>
<script type="text/javascript" src="../js/ajaxfileupload.js"></script>
<style>
.abposit{
	position: absolute;
}
.zindex998{
	z-index: 998;
}
.none{
	display: none;
}
.block{
	display: block;
}
.bColorgray3{
	background-color: #f2f6f7;
}
.fontColOrange{
	color: #FC8E5A;
	
}
.borderLineBlue1{
	border: 1px solid #95B8E7;
}
.imgSh{
	float: left;
	margin-top: 5px;
	cursor: pointer;
}
.imgShrotate{
	float: left;
	margin-top: 5px;
	-webkit-transform:rotate(90deg);
	-moz-transform:rotate(90deg);
	filter:progid:DXImageTransform.Microsoft.BasicImage(rotation=3);
	display: block;
	cursor: pointer;
}
.lileft{
	list-style: none;
	float: left;
}
.lirght{
	float: right;
}
.margin_right{
	margin-right: 30px;
}
.pd_rAndl_30{
	padding-left: 30px;
	padding-right: 30px;
}
.pd_bottom50{
	padding-bottom: 50px;
}
.h30{
	height: 30px;
}
.h100{
	height: 100px;
}
.border_lrt{
	border-left:solid 1px #e6e6e6;
	border-right:solid 1px #e6e6e6;
	border-top: solid 1px #e6e6e6; 
}
.lh30{
	line-height: 30px;
}
.lh_hundrend{
	line-height: 100px;;
}
.fontStyle{
	 font-family:微软雅黑, Arial, Helvetica, sans-serif;
}
.font120{
	font-size: 120%;
}

.bgwhite{
	background-color: #1490E3;
	color: #ffffff;
}
.bggray{
	background-color: #f5f5f5;
	color:#000000;
}
.mouser_cursor{
	cursor: pointer;
}
.contaniner_center{
	margin:0 auto;
}
.li_in_chart{
	width: 50%;
	height: 30px;
	text-align: center;
	line-height: 30px;
	cursor: pointer;
}
.border_li{
	border: solid 1px #cccccc;
}
.border_none{
	border: none;
}
.w80{
	width: 80px;
}

.input_radio{
	vertical-align: text-bottom;
	margin-bottom: -1px;
	*margin-bottom:-4px;
}
.w_hundred{
	width: 100%;
}
.w_five{
	width: 5%;
}
.w_ten{
	width: 10%;
}
.w_fifteen{
	width: 15%;
}
.w_twenty{
	width: 20%;
}
.w_thrity{
	width: 30%;
}
.w_forty{
	width: 40%;
}
.w_fifty{
	width: 50%;
}
.w_seventy{
	width: 70%;
}
.h_hundred{
	height: 100px;
}
.h60{
	height: 60px;
}
.h50{
	height: 50px;
}
.h30{
	height: 30px;
}
.h20{
	height: 20px;
}
.mAuto{
	margin: 0 auto;
}
.text_c{
	text-align: center;
}
.selectBtn{
	position: absolute;
	z-index: 999;
	color: white;
	-webkit-border-radius:8px;
	-moz-border-radius:8px;
	borde-radius:8px;
	background-color: #5cb85c;
	/* border:1px solid #4cae4c; */
	width: 80px;
	line-height: 1.5;
	padding: 5px 10px;
	outline: none;
}
.overf_h{
	overflow: hidden;
}
.font_whilte{
	color: white;
}
.font_b3{
	color:b3b3b3;
}
.bg_type1{
	background-color: #c2b9d0;
}
.bg_type2{
	background-color: #f7c68f;
}
.bg_type3{
	background-color: #f9a654;
}
.bg_type4{
	background-color: #ec7063;
}
.text_elipsis{
	white-space: nowrap;
	overflow: hidden;
	text-overflow:ellipsis;
}
</style>
</head>
<body>
<div id="wiWrap" class="wi-wrap">
	<div class="ui-layout-north wi-head overf_h"></div>
	<div class="ui-layout-west layoutWest menu" id="div_menu"> </div>
	<!--<div class="ui-layout-east" > <span>layout布局的east区域</span> </div>-->
	<div class="ui-layout-center layoutCenter content">
		<div id="tabs" >
			
		</div>
	</div>
</div>
<div class="wi-width"></div>
<div id="msg_dlg" >
	<p id="msg_content"></p>
	<input type="hidden" id="page_num" name="page_num" value="30"/>
	<input type="hidden" id="mk" name="mk" value="${mk}"/>
</div>
<div class="none" id="msg_report" style="position: absolute;z-index: 999;background-color: white;">
</div>
<div class="tip-dialog" id="my_tip">
	<div class="tip-content">
		<div class="tip-header">
			<button class="tip-close">X</button>
			<h4 class="tip-title">
			</h4>
		</div>
		<div class="tip-body">
		</div>
		<div class="tip-footer">
		</div>
	</div>
</div>
<script type="text/javascript">
var getMessageByTip = function()
{
	var html = "<ul class='gztlb'>";
	$.ajax({
		   type: "GET",
		   url: "message!message.action",
		   dataType:'json',
		   cache: false,
		   success: function(data)
		   {
		      if(data.length>0)
		      {
			       for (var i = 0; i < data.length; i++) 
			       {		
			         var url =  data[i].MESSAGE_CONTENT+"&orderId="+data[i].ORDERID+"&taskId="+data[i].TASKID+"&processId="+data[i].PROCESSID;   
			         html += "<li class='h20'><img src='${ctx}/images/al.gif'  class='gzt-img'><a href='javascript:void(0);' style='color:blue;' onclick='doprocess(" + "\"" + url + "\"" + ");'>『"+data[i].DISPLAY_NAME+"』『"+data[i].FQ_TRUENAME+"』</a></li>";
			       }
			       html +="</ul>"
					if($(".tip-dialog").height() == 0)
					{
						$.tipMsg(180,350,'提示信息',html);
					}
					else
					{
						$(".tip-body").html(html);
					}
			   }
			   else
			   {
			   
			   }
		   }
	});
}

var run_Scheduledexecutorservice = function(){
	setInterval(getMessageByTip, 1000 * 60 );
}
getMessageByTip();
run_Scheduledexecutorservice();
function doprocess(url)
{
var option = {};
option.key = "doprocess";
option.title = "我的待处理工作";
option.url = "../"+url;	
option.isIframe = true;
option.noClose = 0;
addTab(option);    
$("#my_tip").height(0);
}
// 跳转,新打开tab页   --by dsc
function doprocess_ju(url,key,title){
var option = {};
option.key = key;
option.title = title;
option.url = "../"+url;	
option.isIframe = true;
option.noClose = 0;
addTab(option);    
$("#my_tip").height(0);
}
</script>
</body>
</html>