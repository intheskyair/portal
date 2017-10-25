<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" style="padding-right:10px">
	<head>
		<title>收文登记流程</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css"/>
		<link type="text/css" rel="stylesheet" href="${ctx}/css/common.css"></link>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<style>
		.li_content{
			width: 60px;
			text-align: center;
		}
		body{overflow: scroll;}
		</style>
	</head>
<body>
<form  action="" method="post" target="mainFrame">
<p class="btt">常州市水利局文件阅办单</p>
<table style="width:98%; margin-top:20px;" cellpadding="0" cellspacing="0">
<tr>
 <td  style=" text-align:left">必退文件注意保管</td>
 <td style=" text-align:right">${map.call}(<input type="text" readonly="readonly" value="${map.num}" class="sl-width-one-px sl-height-five"/>）号</td>
</tr>
</table>
<br/>
<table class="table10" cellpadding="0" cellspacing="0" id="viewdoc"  style=" width:100%; height:600px">
<tr>
    <td width="10%">来文单位</td>
    <td width="25%" colspan="2"><input type="text" readonly="readonly" value="${map.org_name}" class="sl-div-input"/></td>
    <td width="10%">来文日期</td>
    <td width="55%"><input type="text" readonly="readonly" value="${map.rec_time}" class="sl-div-input" /></td>
</tr>
<tr>
    <td>来文标题</td>
    <td colspan="4"><input type="text" readonly="readonly" value="${map.title}" class="sl-div-input"/></td>
  </tr>
  
 <tr>
<td>
			<label style="color:red;">*</label>
				<label for="input_1">附件：</label>
			</td>
			<td colspan="4"  id="upload_add">
			    <div id="showupload">		
				<input type="hidden" name="path_plann_file" id="path_plann_file" />
				<input type="file" id="plann_file" name="plann_file" value="" style="display: none;" />
				<div id="promotionShow"></div>		
				</div>
			</td>
</tr> 

</table>						
		</form>
<script type="text/javascript">

    $(function()
    {
			    	if(typeof JSON == 'undefined'){
						$('head').append($("<script type='text/javascript' src='${ctx}/views/common/js/json2.js'>"));
					}
    		
        		  $("#showupload").css("display", "none");
    		      var  str_path = "${map.path_plann_file}".replace(/\@/g,"\\");
    		      var  str_file_name = "${map.promotionShow}".replace(/\@/g,"\\");
    		      $("#upload_add").append("<span style='color: red;'>"+str_file_name+"</span>");
    		      $("#upload_add").append("<a href='javascript:void(0);' onclick=mydownload('"+escape(str_path)+"');>点击下载</a>"); 		
    		    //此操作3个页面都用到了，但是没有做通用处理，就这一个，就这样了，时间太少， recdoc.jsp,recdocSend.jsp,recdocView.jsp
    		      if('${map.rect3method}' != ""){
    	    			var method = "";
    	    			var suggest = "";
    	    			if("${map.rect3method}" == 0){
    	    				method = "同意";
    	    			}else{
    	    				method = "不同意";
    	    			}
    	    			if("${map.rect3suggest}" != ''){
    	    				suggest = "("+"${map.rect3suggest}"+")";
    	    				
    	    			}
    	    			$("#viewdoc").append("<tr><td>拟办意见</td><td colspan='4'>" + method + suggest); 
    	    		}
    			
    		  	if('${map.rect5}' != ""){
    				var data = JSON2.parse('${map.rect5}');
    	    		$("#viewdoc").append("<tr><td>职位</td><td>姓名</td><td>处理类型</td><td>意见</td><td>备注</td></tr>");
    	    		for(var i in data){
    	    			var method = data[i].method;
    	    			if(method == 0){
    	    				method = "同意";
    	    			}else{
    	    				method = "不同意";
    	    			}
    	    			$("#viewdoc").append("<tr><td>局领导</td><td>"+data[i].truename+"</td><td>会签</td><td>"+method+"</td><td>"+data[i].suggest+"</td></tr>");
    	    		}
    			}
    			
    			if('${map.rect7}' != ""){
    				var data = JSON2.parse('${map.rect7}'.replace("\r\n", ""));
    	    		for(var i in data){
    	    			var method = data[i].method;
    	    			if(method == 0){
    	    				method = "同意";
    	    			}else{
    	    				method = "不同意";
    	    			}
    	    			$("#viewdoc").append("<tr><td>处室领导</td><td>"+data[i].truename+"</td><td>会签</td><td>"+method+"</td><td>"+data[i].suggest+"</td></tr>");
    	    		}
    	    		
    			}
    			
    			if('${map.rect9}' != ""){
    				var data = JSON2.parse('${map.rect9}');
    	    		for(var i in data){
    	    			var method = data[i].method;
    	    			if(method == 0){
    	    				method = "同意";
    	    			}else{
    	    				method = "不同意";
    	    			}
    	    			$("#viewdoc").append("<tr><td>最终承办人</td><td>"+data[i].truename+"</td><td>非会签</td><td>"+method+"</td><td>"+data[i].suggest+"</td></tr>");
    	    		}
    			}
		    		
		    		
		    		
    		
    });       
    function changeCss(id){
    	$("#" + id).each(function(){
    		$(this).removeAttr("readonly","readonly");
    	});
    }
    
    function mydownload(path)
    {
    	location.href='upload!download.action?&path='+ encodeURI(encodeURI(unescape(path))); 
    }
</script>
	</body>
</html>
