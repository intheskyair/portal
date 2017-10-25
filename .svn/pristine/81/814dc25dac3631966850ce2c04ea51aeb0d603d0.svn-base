<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>套餐信息</title>
	<link rel="stylesheet" href="${ctx}/css/bootstrap-3.3.5/css/bootstrap.min.css" />
	<!-- kindeditor.js -->
	<link rel="stylesheet" href="${ctx}/js/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="${ctx}/js/kindeditor/themes/simple/simple.css" />
	<script src="${ctx}/css/bootstrap-3.3.5/js/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="${ctx}/css/bootstrap-3.3.5/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${ctx}/views/common/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx}/js/kindeditor/kindeditor.js"></script>
	<script type="text/javascript" src="${ctx}/js/kindeditor/lang/zh_CN.js"></script>
	<style type="text/css">
		.title,.title-1{
		 	background:url(./views/common/css/themes/images/wbg.gif) repeat-x 0 -1px #D7D7D7;
		 }
		 .div-main-1{
		 	background:url(./views/common/css/themes/images/bg.gif) repeat-x #f0f0f0;
		 	padding-left: 20px;
		 }
		 .mt_15{
		 	margin-top: 15px;
		 }
		 .warper{
		 	width: 100%;
		 	height: 97%;
		 	overflow:hidden;
		 	
		 }
	</style>
</head>
<body>

<div class="div div-1" style="border:1px solid #ccc;">
	<div class="title title-1">
		<span  title="" class="attribute initialism" ><strong>&emsp;我的公文</strong></span>
	</div>	
</div>
<div class="div-main-1 container-fluid warper" style="border:1px solid #ccc;padding-bottom: 50px;">
	<div class="gw row mt_15">
		<div class="form-horizontal">
			<div class="form-group col-md-12">
			    <label for="inputEmail3" class="col-sm-1 control-label text-danger">*公文分类</label>
				<div class="col-sm-6">
	      			<input type="text" class="form-control" id="inputEmail3" value="">
	    		</div>
	  		</div>
	  		<div class="form-group col-md-12">
			    <label for="inputEmail3" class="col-sm-1 control-label text-danger">*公文模型</label>
				<div class="col-sm-6">
	      			<input type="text" class="form-control" id="inputEmail3" value="" >
	    		</div>
	  		</div>
	  		<div class="form-group col-md-12">
			    <label for="inputEmail3" class="col-sm-1 control-label text-danger">*公文密级</label>
				<div class="col-sm-6">
	      			<input type="text" class="form-control" id="inputEmail3" value=""/>
	    		</div>
	  		</div>
	  		<div class="form-group col-md-12">
			    <label for="inputEmail3" class="col-sm-1 control-label text-danger">*公文标题</label>
				<div class="col-sm-6">
	      			<input type="text" class="form-control" id="inputEmail3" value=""/>
	    		</div>
	  		</div>
	  		<div class="form-group col-md-12">
			    <label for="inputEmail3" class="col-sm-1 control-label">发文字号</label>
				<div class="col-sm-6">
	      			<input type="text" class="form-control" id="inputEmail3" value=""/>
	    		</div>
	  		</div>
	  		<div class="form-group col-md-12">
			    <label for="inputEmail3" class="col-sm-1 control-label">归档日期[?]</label>
				<div class="col-sm-6">
	      			<input type="text" class="form-control" id="inputEmail3" value="" onclick="cleaveTime()"/>
	    		</div>
	  		</div>
	  		<div class="form-group col-md-12">
	  			 <label for="inputEmail3" class="col-sm-1 control-label">&emsp;</label>
	  			 <div class="col-sm-8">
		  			<textarea id="k_editor" name="content" class="form-control" style="width:800px;height:450px;">
						&lt;strong&gt;HTML内容&lt;/strong&gt;
					</textarea>
				</div>
	  		</div>
		</div>
	</div>
</div>   
<script type="text/javascript">
function cleaveTime(){
	WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});
}
KindEditor.ready(function (K) {
    window.editor = K.create('#k_editor',{
	       allowFileManager : false,
		   items:[
		       'undo', 'redo', '|', 'cut', 'copy', 'paste',
		       'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
		       'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
		       'superscript', 'quickformat', 'selectall', '|', 'fullscreen', '/',
		       'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
		       'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|',
		       'table', 'hr', 'pagebreak',
		        'link', 'unlink','emoticons'
		   ],
		   width:"100%",
		   height:400,
		   resizeType:0
	 }); 
});

</script>
</body>
</html>