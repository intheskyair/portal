<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page language="java"%>
<%@ page isELIgnored="false"%>
<html>
<body>
<input type=hidden id="url" value='<%=request.getAttribute("url")%>'>
<input type=hidden id="status" value='<%=request.getAttribute("status")%>'>
<input type=hidden id="notes" value='<%=request.getAttribute("notes")%>'>
</body>
<script>
if(document.getElementById("status").value == '00' || document.getElementById("status").value == '0'){
	alert(document.getElementById("notes").value);
	window.location.href = document.getElementById("url").value;
	//document.getElementById("tb1").style.display = "";
	//document.getElementById("tb2").style.display = "none";
}else if(document.getElementById("status").value == '99'){
	window.location.href = document.getElementById("url").value;
}else{
	alert(document.getElementById("notes").value);
	window.location.href = document.getElementById("url").value;
	//document.getElementById("tb1").style.display = "none";
	//document.getElementById("tb2").style.display = "";
}
</script>
</html>
