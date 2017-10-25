<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<title></title>
</head>

<frameset rows="65,*" cols="*" frameborder="no" border=0>
	<frame src="getMenu!toTop.action" name="top" scrolling="no" noresize id="top" >
	<frameset rows="*" cols="180,*" frameborder="no" border=0 id=testframeset>
	<frame src="getMenu!getMenu.action" name="left" frameborder="no" scrolling="no" id="left">
	<frame src="" name="main" frameborder="no" scrolling="yes" id="main">
	</frameset>
</frameset>

<noframes>
<body>
</body>
</noframes>
</html>