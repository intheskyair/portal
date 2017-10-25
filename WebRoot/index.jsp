<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags"  prefix="s" %>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<html style="height:100%;overflow-y:hidden">
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/views/common/js/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="icon" href="<%=request.getContextPath()%>/images/favicon.ico" mce_href="<%=request.getContextPath()%>/images/favicon.ico" type="image/x-icon" />
<title>常州市水利局办公系统登录</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/login.css"/>
	<style>
	
	.xt-bg{background-image:url(${pageContext.request.contextPath}/images/bg.png);
    filter: "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale')";
    -moz-background-size: 100% 100%;
    background-size: 100% 100%;
    background-position: center 0;
	height:100%;
	background-color:#3385ff;
	backgroud-repeat:no-repeat;}
    .dl{font-family:微软雅黑;height:30px;width:60px;background-color:#35ace4;border:1px solid #0a86c1;color:#fff;margin-left:0px;cursor:pointer;float:right}
    .dl:hover{background-color:#1d96cf;border:1px solid #0a86c1;}
	</style>
</head>
<body class="xt-bg" style="overflow-y:hidden ">
		<form action="<s:url value='admin!login.action' />" method="post">
		<div class="wi-wrap-login">
			<div class="wi-wrap-login-main">
				<div class="wi-bg-login-left">
					<div class="wi-bg-login-right" >
						<div class="fn-clear wi-logo-login">
							<h1 style="margin-left: 100px; width:300px; font-family:微软雅黑;color:#09a5e2;margin-bottom:10px">常州市水利局办公系统</h1>
						</div>
						<div class="logo">
						</div>
						<div class="fn-clear wi-login-main">
							<ul class="fn-left wi-li-login">
								<li class="wi-li-login-item">
									<label for="txt_userid">用户名 : </label>
									<input name="name" id="txt_userid" type="text" class="wi-ipt-1" />
								</li>
								<li class="wi-li-login-item">
									<label for="txt_pwd">密<font style="margin-left:14px !important;"></font>码 : </label>
									<input name="password" id="txt_pwd"  type="password" class="wi-ipt-1" />
								</li>
								<li  class="wi-li-login-item">
								  <span id="error" style="width: 200px;color:red;font-family:微软雅黑;font-size:12px;height:16px">${errorMessage}</span>
								</li>
							</ul>
							
						</div>
						<div class="fn-clear wi-login-bottom">
							<div class="wi-login-bottom-txt">
							<a href="<%=request.getContextPath()%>/mzpf!begin.action#" style=" font-family:微软雅黑;text-decoration:none;font-size:12px;margin-left:90px;color:#09a5e2">【民主评测】</a>
								<div class="warn ui-state-error-text" id="span_wran"><span class="ui-icon ui-icon-info"></span></div>
							</div>
							<div class="wi-login-bottom-btn"><input type="submit" value="登录" class="dl"/></div>
						</div>
						<div style="text-align:center; margin-bottom:10px">
							<br/>	<br/>
						</div>
						<div class="fn-clear wi-login-shadow">
							<div class="wi-login-shadow-left">
								<div class="wi-login-shadow-right"></div>
							</div>
						</div>
							
					</div>
					
				</div>
			
			</div>
				<div id="span_copyright" style="font-size:12px;color:#005cc6;font-family:微软雅黑;text-align:center">技术支持：江苏首创高科技术工程股份有限公司</div>
		</div>
	
		</form>
		
		
		
	<script type="text/javascript">	
	$(function()
	{           
	           
	     		$("input").each(function()
	     		{
			    $(this).focus(function()
			    {	
                    $("#error").text("");
			    })	
			    });
	});
	     
</script>
</body>
</html>