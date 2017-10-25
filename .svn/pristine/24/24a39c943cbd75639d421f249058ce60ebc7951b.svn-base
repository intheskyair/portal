<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head> 
  <%@ include file="/common/meta.jsp"%>
    <title>个人季度计划与总结</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css"/>
	<script type="text/javascript">
		 $(function(){ 
			var gzjhtime = "${map.gzjhtime}";
			$("#gzjhyear").val(gzjhtime.split("-")[0]);
			$("#gzjhmonth").val(gzjhtime.split("-")[1]);
			$("#gzjhday").val(gzjhtime.split("-")[2]);
			var gzzjtime = "${map.gzzjtime}";
			$("#gzzjyear").val(gzzjtime.split("-")[0]);
			$("#gzzjmonth").val(gzzjtime.split("-")[1]);
			$("#gzzjday").val(gzzjtime.split("-")[2]);
		});
		
		$(function(){$("body").scrollTop( 100000);})




	</script>
   <style type="text/css">
	.bg_gray{
		background-color: gray;
	}
	.dwb_y{
    /* width: 95%; */
    border: 1px solid #fff;
    padding: 5px;
			border: 0px;
			resize:none;
			text-align: left;
			text-indent: 2em;
			margin: 5px;
			height:120px;
    overflow-y: auto;
    
    
    }
	</style>
  </head>
  
  <body>
   	<!--<p class="btt" style=" font-size:30px;">个人季度计划与总结</p> -->
   
<p class="btt"  style="font-size:18px;">
	( <input type="text" class="nyr" readonly="readonly" value="${map.yd}"/>
	年度 <input type="text" class="nyr" readonly="readonly"  value="${map.jd}"/>)</p>
<table class="table05" cellpadding="0" cellspacing="0">
<tr>
	<td style="height:50px;width:100px;">姓名</td>
    <td> 
    	<input type="text" class="nyr" readonly="readonly"  value="${map.name}"  />
    </td>
    <td style="height:50px;width:150px;">所在部门</td>
   	<td> <input type="text" class="nyr" readonly="readonly"  value="${map.section}"/></td>
    <td style="height:50px;width:150px;">职务</td>
   	<td> <input type="text" class="nyr" readonly="readonly" value="${map.work}"/></td>
 </tr>
 <tr>
	<td style="height:150px">工作计划</td>
    <td colspan="5">
    <textarea name="gzjh" readonly="readonly" class="dwb_y">${map.gzjh}</textarea>
    <p  class="p-nyr">
    <input type="text" class="nyr" readonly="readonly" id="gzjhyear"/><span>年</span>
    <input type="text" class="nyr" readonly="readonly" id="gzjhmonth" /><span>月</span>
    <input type="text" class="nyr" readonly="readonly" id="gzjhday" /><span>日</span>
    </p>
    </td>
  </tr>
  <tr>
	<td style=" height:100px!important;">处室领导<br/>意见</td>
    <td colspan="5">
    	 	<textarea readonly="readonly" class="dwb_y" style="height:70px;" >${map.csldyjmethod == 0 ? '同意' : ''}${map.csldyjmethod == -1 ? '不同意' : ''}<c:if test="${not empty map.csldyjsuggest}">(${map.csldyjsuggest})</c:if></textarea>
   </td>
  </tr>
  <tr>
	<td style="height:250px">工作总结</td>
    <td colspan="5">
    <textarea name="gzjh" readonly="readonly" class="dwb_y">${map.gzzj}</textarea> 
    <p  class="p-nyr">
    <input type="text" class="nyr" readonly="readonly" id="gzzjyear" value="${map.gzzjyear}" /><span>年</span>
    <input type="text" class="nyr" readonly="readonly" id="gzzjmonth" value="${map.gzzjmonth}" /><span>月</span>
    <input type="text" class="nyr" readonly="readonly" id="gzzjday" value="${map.gzzjday}"/><span>日</span>
    </p></td>
  </tr>
  <tr>
	<td>处室领导<br/>评价</td>
    <td colspan="5">
     <textarea readonly="readonly" class="dwb_y" style="height:70px;" >${map.csldpjmethod == 0 ? '同意' : ''}${map.csldpjmethod == -1 ? '不同意' : ''}<c:if test="${not empty map.csldpjsuggest}">(${map.csldpjsuggest})</c:if></textarea>
     
     </td>
  </tr>
  <tr>
	<td>分管领导<br/>评鉴</td>
    <td colspan="5">
     <textarea readonly="readonly" class="dwb_y" style="height:70px;" >${map.fgldpjmethod == 0 ? '同意' : ''}${map.fgldpjmethod == -1 ? '不同意' : ''}<c:if test="${not empty map.fgldpjsuggest}">(${map.fgldpjsuggest})</c:if></textarea>
     
     </td>
  </tr>
</table>
  </body>
</html>
