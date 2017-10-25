<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page language="java"%>
<%@ include file="include.jsp"%>
<html>
	<base target="_self">
	<meta http-equiv="pragma" content="no-cache">
	<link href="${path}style/allofpages.css" rel="stylesheet" type="text/css">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>密码修改</title>
		<script type='text/javascript' src='/renderincloud-bsm/dwr/interface/DwrService.js'></script>
		<script type='text/javascript' src='/renderincloud-bsm/dwr/engine.js'></script>
		<script type='text/javascript' src='/renderincloud-bsm/dwr/util.js'></script>	
     <style>  
		.state1
		 {  
		 color:#aaa;  
		 }  		
		.state2
		{  
		 color:#000;  
		}  		
		.state3
		{  
		color:red;  
		}  
		.state4
		{  
		 color:green;  
		}  
 </style>  
		
	</head>
	<body>
		<div id="tb1">
			<div class="right">
				<div id="rightmain">							
						<div class="sizeall ef">
							<div class="sizeinput">
								原密码：<input type='password' id='pwd1' maxLength="15"/><span class='state1' style="width: 200px">请输入原密码</span>
							</div>
							<div class="sizeinput">
								新密码：<input type='password' id='pwd2' maxLength="15"/><span class='state1' style="width: 200px">请输入新密码</span>
							</div>
							<div class="sizeinput">
						            确认新密码：<input type='password' id='pwd3' maxLength="15"/><span class='state1' style="width: 200px">请输入确认新密码</span>
							</div>
						</div>
						<div class="sizeall ff">
							<input type='button' id='sub' value='提交' class='btn'>
							<input type='button' id='clear' value='重置' class='btn'>
						</div>					
				</div>
			</div>

          <div class="foot">- -  版权所有 © 2014 赞奇科技- -</div>
			<script language="JavaScript" type="text/javascript"
				src="${path}js/system/changepwd.js"></script>
		  </div>
	</body>
</html>
