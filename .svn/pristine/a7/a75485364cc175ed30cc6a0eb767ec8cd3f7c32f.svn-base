<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html> 

<head>
		<title>文件上传</title>   
		<script type="text/javascript" src="${path}/uploadify/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${path}/uploadify/swfobject.js"></script> 
		<script type="text/javascript" src="${path}/uploadify/jquery.uploadify-3.1.min.js"></script> 
		<link rel="stylesheet" type="text/css" href="${path}/uploadify/uploadify.css"/>
		<script type="text/javascript"> 

		        $(document).ready(function() 
		        { 
		            var path ='${path}';
		            $("#fileupload").uploadify({ 
		                /*注意前面需要书写path的代码*/ 
		                'debug'          : false,//开启调试
		                'swf'            : path+'uploadify/uploadify.swf', 
		                'uploader'       : path+'uploadUtil!uploadFile.action', //处理上传的Action
		                'cancelImg'      : path+'uploadify/cancel.png', 
		                'queueID'        : 'fileQueue', //和存放队列的DIV的id一致 
		                'fileObjName'    : 'fileupload', //和以下input的name属性一致
		                'auto'           : false, //是否自动开始 
		                'multi'          : true, //是否支持多文件上传
		                'removeCompleted': true,
		                'buttonText'     : '选择文件', //按钮上的文字 
		                'width'          : '120',//浏览按钮的宽度
		                'height'         : '32',//浏览按钮的高度
		                'simUploadLimit' : 1, //一次同步上传的文件数目 
			            'fileSizeLimit'  : '50MB', //设置单个文件大小限制 
			            'queueSizeLimit' : 1, //队列中同时存在的文件个数限制 
			            'fileTypeDesc'   : '支持格式:xlsx.', //如果配置了以下的'fileExt'属性，那么这个属性是必须的 
			            'fileTypeExts'   : '*.xlsx;',//允许的格式   
			            'onUploadSuccess': function(file, data, response) 
			            {
			                 var jsonObject=eval("("+data+")");
			                    //转换为json对象   			                    
			            	$('<li style=\"margin-top:5px;\"></li>').appendTo('.files').html(jsonObject["message"]);
			            	$("#filepath").val(jsonObject["filepath"]);
			            	document.frames('picFileList').location.reload()
			            }, 
			            onError: function(event, queueID, fileObj) {
			            	alert("文件:" + fileObj.name + "上传失败");
			            }, 
			            onCancel: function(event, queueID, fileObj){} 
			            	}); 
			            });
			            
			            
			     function chooseThis()
			     {
			        window.returnValue = $("#filepath").val();
		            window.close();
			     }
			      

			      function  myclose()   
			      {   
 
					   window.returnValue = "";
		               window.close();
			      }

			            
		</script>
</head>
	<body  onUnload="myclose()">
	<div id="fightingYAAN"><a href="#" class="fightingYAAN_banner "></a></div>
		<div id="wrapper">
			<div id="header">
				<div class="wrapper">
					<h1>上传文件</h1>
				</div>
			</div>
	
		<div class="container">
			<div id="fileQueue" align="center"></div>
			<br/>
			<input type="file" name="fileupload" id="fileupload"/>
			<input type="hidden" name="filepath" id="filepath"/>
		</div>
		<p>
			<a href="javascript:jQuery('#fileupload').uploadify('upload','*');" class="blue btn">开始上传</a>&nbsp;
			<a href="javascript:jQuery('#fileupload').uploadify('cancel','*');" class="red btn">取消所有上传</a>&nbsp;
			<a href="javascript:chooseThis();" class="red btn">确认</a>&nbsp;
		</p>
		</div>
		<div id="wrapper">
			<br>
			<ol class=files align="left"></ol>
		</div>
	</body>
</html>
