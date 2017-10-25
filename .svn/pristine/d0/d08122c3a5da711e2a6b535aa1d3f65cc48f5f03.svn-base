<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${path}css/top.css" rel="stylesheet" type="text/css">
	</head>	
	<body>
		<div id="top">
		<table cellspacing="0" cellpadding="0" border="0">	
			<tr>	
			<td width="10%" align="left" valign="top" nowrap>
				<img src="${path}images/top.jpg" width="522" height="63" border="0">
			</td>
			<td width="80%" nowrap align="left">	
			   <div class="toprightuser">
			                     在线用户：<s:property value="#session.admin.name"/>&nbsp;&nbsp;&nbsp;&nbsp;权限：<s:property value="#session.admin.privilege.privilegeName"/>	
				   <a href="javascript:window.parent.location.href='${path}admin!logout.action'">退出登录</a>&nbsp;&nbsp;&nbsp;&nbsp;
				  <div class="roll"><marquee direction="left" scrollamount=3 onmouseover="stop()" onmouseout="start()">${detail}</marquee></div> 
			   </div>
		    </td>
		   	</tr>
		</table>
		</div>
	</body>	
</html>

