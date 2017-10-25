<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>出错页面</title>
</head>
<body>
	<div align="center" style="padding-top:50px">
		<table id="tPrintCheck">
			<tr>
				<td>
					<img src="images/error.jpg">
				</td>
				<td style="font-family:仿宋_GB2312">
					<h2><font color='gray'>对不起，出错了！</font></h2><br>
					<h3><font color='gray'>#if($!exception && $!exception == "AppException")出错原因：$!msg
					#else
					出错原因：$!msg<br>
					错误日志：
					$!throwable
					#end
					</font></h3>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>