<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
<base target="_self">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>文件上传</title>
<script language="JavaScript" type="text/javascript" src="${path}js/JSTools.js"></script>
<script type='text/javascript' src='dwr/interface/UploadMonitor.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<link href="style/ballwindow.css" rel="stylesheet" type="text/css">
</head>

<body>
<div id="location">
  <div class="notes">
   <img src="${path}images/icon_3.gif" align="absmiddle" alt="文件上传" >&nbsp;文件上传
  </div>
</div>
<form action="${path}fileupload.jsp" enctype="multipart/form-data" method="post" id="form1" onsubmit="return startProgress()">
  <div class="title"><img src="${path}images/Createproject.gif" align="absmiddle"> 请选择要上传的文件</div>
  <div>
      <input type="hidden" id="path" name="path"/>
      <input type="hidden" id="type" name="type"/>
      <input type="file" id="file1" name="file1" style="width:300px;" onKeyDown="return false;" onChange="FileChange(this.value);"/>
      <input type="submit" id="uploadbutton" value="上 传" onclick="startProgress" class="buttonb"/>
  </div>
  <div id="progressBar" style="display: none;"> 
    <div id="theMeter"> 
        <div id="progressBarText"></div>
        <div id="progressBarBox"> 
          <div id="progressBarBoxContent"></div>
        </div>
    </div>
  </div>
  <img id=uploadimage height=10 width=10 src=""  onload="DrawImage(this);" style="display: none;">
</form>

</body>
<script src='${path}js/upload.js'></script>
</html>
