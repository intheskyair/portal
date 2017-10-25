<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head> 
 
  <%@ include file="/common/meta.jsp"%>
    <title>水政监察支队水事违法案件</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/css/style.css"  media="all" />
	<script type="text/javascript">
  
    $(function(){
    			if("${map.time}" != ''){
    				var a = "${map.time}";
    				var b = a.split("-"); 
    				$("#month").html(b[1]);
    				$("#day").html(b[2]);
    			}
    			$("#${map.cbfs}").val("${map.cbmsg}");
    			$("input[type='text']").attr("readonly","readonly");
    });
    
   function mydownload(path)
    {
        path = $("#"+path).val();
		//queryP.path = unescape(path);
		//alert(encodeURI(encodeURI(unescape(path))));
		location.href='upload!download.action?&path='+ encodeURI(encodeURI(unescape(path))); 
    }
  
</script>	
  </head>
  <body >
  <p class="btt" style=" font-size:20px;">常州市水政监察支队水事违法案件查办通知</p>
	<div style="width: 800px;margin: auto;">
	<div style="float: left;width: 50%;text-align: left;">
		<span style="color: red;"></span>
	</div>
	<div class="btt"  style=" font-size:18px; font-weight:normal;float: right;width: 50%;text-align: right;">
		( 编号[2016]: <input type="text" id="transidView"  class="nyr_2" value="${map.transid}" readonly="readonly"/> 号)
	</div>
</div>
<table cellpadding="0" cellspacing="0" class="table07">
  <tr>
    <td width="50" rowspan="6" id="ggbb">案<br/>件<br/>受<br/>理<br/>情<br/>况</td>
    <td colspan="2" width="100" >受理时间</td>
    <td width="120" rowspan="2">来电<br/>举报</td>
    <td width="120" rowspan="2">来信<br/>举报</td>
    <td width="120" rowspan="2">来访<br/>举报</td>
    <td width="100" rowspan="2">局机关<br/>督办</td>
    <td width="100" rowspan="2">市机关<br/>督办</td>
    <td width="100" rowspan="2">巡查<br/>发现</td>
    <td width="60" rowspan="2">其他</td>
  </tr>
  <tr>
    <td >月</td>
    <td >日</td>
  </tr>
  <tr>
    <td id="month"></td>
    <td id="day"></td>
    <td><input type="text" class="srk"  id="ldjb"  /></td>
    <td><input type="text" class="srk" id="lxjb"  /></td>
    <td><input type="text" class="srk" id="lfjb"  /></td>
    <td><input type="text" class="srk" id="jjgdb"  /></td>
    <td><input type="text" class="srk" id="sjgdb"  /></td>
    <td><input type="text" class="srk" id="xcfx" /></td>
    <td><input type="text" class="srk" id="qt"/></td>	
  </tr>
  <tr>
    <td colspan="2" rowspan="2">违（章）法</br>当事人</td>
    <td colspan="3">名  称（姓  名）</td>
    <td colspan="5"> 地 址（住 址）</td>      
  </tr>
  <tr>
    <td colspan="3"><input type="text"  value="${map.wzname}" class="srk" readonly="readonly" ></td>
    <td colspan="5"> <input type="text"  value="${map.wzaddress}" class="srk" readonly="readonly"/></td>   
  </tr>
  <tr>
    <td colspan="2">简要案情</td>
    <td colspan="7"><textarea  class="dwb" style=" height:70px;" readonly="readonly">${map.aqjy}</textarea></td>
  </tr>
  <tr>
    <td>转督办意见</td>
    <td colspan="9"><textarea  class="dwb" style=" height:70px;" readonly="readonly">${map.zdbyjmethod == '0' ? '同意' : ''}${map.zdbyjmethod == '-1' ? '不同意' : ''}<c:if test="${not empty map.zdbyjsuggest}">(${map.zdbyjsuggest})</c:if></textarea></td>
  </tr>
  <tr>
    <td>调查情况</td>
    <td colspan="9"><textarea  class="dwb" style=" height:70px;" readonly="readonly">${map.dxqk}</textarea></td>
  </tr>
  <tr>
    <td>处理情况</td>
    <td colspan="9"><textarea  class="dwb" style=" height:70px;" readonly="readonly">${map.clqk}</textarea></td>
  </tr>
  <tr>
    <td>结案情况</td>
    <td colspan="9"><textarea class="dwb" style=" height:70px;" readonly="readonly">${map.ja}</textarea></td>
  </tr>
  <tr>
    <td>下发附件</td>
    <td colspan="9"> <input type="hidden" name="issFile" id="issFile" value="${map.issFile}" />
		  	<c:if test="${!empty map.issFile}">
		  		<input type="button" id="down" value="下载" onclick="mydownload('issFile')">
		  	</c:if></td>
  </tr>
  <tr>
    <td>结案附件</td>
    <td colspan="9"> <input type="hidden" name="path_plann_file" id="path_plann_file" value="${map.path_plann_file}" />
		  	<c:if test="${!empty map.path_plann_file}">
		  		<input type="button" id="down" value="下载" onclick="mydownload('path_plann_file')">
		  	</c:if></td>
  </tr>
</table>

  </body>
