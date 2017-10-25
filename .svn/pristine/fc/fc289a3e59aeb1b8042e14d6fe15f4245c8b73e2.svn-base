<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head> 
  <%@ include file="/common/meta.jsp"%>
    <title>工作人员请假申请表</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css"/>
	<script type="text/javascript">
     $(function(){
    	 $("input[type=radio][value=${map.qtype}]").attr("checked",'checked'); 
    }); 
</script>	
  </head>
  <body>
	  <p class="btt" style=" font-size:30px; margin-top:30px; margin-bottom:20px">工作人员请假申请表</p>
<p class="btt"  style=" font-size:18px;">( 申请时间: <input type="text" id="time" name="time" readonly="readonly" value="${map.time}" class="nyr_1"/>)</p>
<table class="table06" cellpadding="0" cellspacing="0" >
<tr>
    <td  style=" width:150px;">姓名</td>
    <td colspan="2" style=" width:100px;"><input type="text"  id="name" name="name" value="${map.name}" readonly="readonly" class="srk"/></td>
    <td>所在部门</td>
    <td colspan="2"><input type="text" id="section" name="section" readonly="readonly" value="${map.section}" class="srk"/></td>
  </tr>
<tr>
    <td>职务</td>
    <td colspan="5"><input type="text" id="work" name="work" readonly="readonly" value="${map.work}" class="srk"/></td>
  </tr>
<tr>
    <td>请假类型</td>
    <td colspan="5">
    	 <input type="text" id="isleaf_edit" name="qtype" class="srk" readonly="readonly" value="${map.qtype}">
    </td>
 </tr>
<tr>
    <td rowspan="2">请假日期</td>
    <td colspan="2" style=" width:100px;">开始时间</td>
     <td ><input type="text" id="stime" name="stime" readonly="readonly" value="${map.stime}" class="srk"/></td>
    <td>结束日期</td>
      <td><input type="text" id="etime" name="etime" value="${map.etime}" readonly="readonly" class="srk"/></td>
</tr>
<tr>
    <td colspan="2" style=" width:100px;">假期天数</td> 
    <td ><input type="text" id="day" name="day" value="${map.allleave}" readonly="readonly" class="srk"/>天</td>
    <td style=" width:100px;">年度累计<br/>请假天数</td>
      <td><input type="text" id="allday" name="allday" value="${map.allday}" readonly="readonly" class="srk"/>天</td> 
</tr>
<tr>
    <td>所在部门意见</td>
    <td colspan="2"  style=" width:100px;">
    	<textarea  style="overflow:hidden" id="bmyj" name="bmyj" readonly="readonly"  class="srk">${map.bmyjmethod == '0' ? '同意' : ''}${map.bmyjmethod == '-1' ? '不同意' : ''}<c:if test="${not empty map.bmyjsuggest}">(${map.bmyjsuggest})</c:if></textarea>
    </td>
  
    <td >办公司意见</td>
    <td colspan="2">
    	<textarea style="overflow:hidden"  id="bgsyj" name="bgsyj" readonly="readonly" class="srk">${map.bgsyjmethod == '0' ? '同意' : ''}${map.bgsyjmethod == '-1' ? '不同意' : ''}<c:if test="${not empty map.bgsyjsuggest}">(${map.bgsyjsuggest})</c:if></textarea>
    </td>
  </tr>
<tr>
    <td>分管负责人意见</td>
    <td colspan="2"  style=" width:100px;">
    	<textarea style="overflow:hidden" id="fgfzryj" name="fgfzryj" readonly="readonly" class="srk">${map.fgfzryjmethod == '0' ? '同意' : ''}${map.fgfzryjmethod == '-1' ? '不同意' : ''}<c:if test="${not empty map.fgfzryjsuggest}">(${map.fgfzryjsuggest})</c:if>
    	</textarea>
    </td>
  
    <td>主要负责人意见</td>
    <td colspan="2">
    	<textarea style="overflow:hidden"  id="zyfzryj" name="zyfzryj" readonly="readonly" class="srk">${map.zyfzryjmethod == '0' ? '同意' : ''}${map.zyfzryjmethod == '-1' ? '不同意' : ''}<c:if test="${not empty map.zyfzryjsuggest}">(${map.zyfzryjsuggest})</c:if></textarea>
    </td>
  </tr><tr>
    <td>局分管领导意见</td>
    <td colspan="5">
    	<textarea style="overflow:hidden" id="jfgldyj" name="jfgldyj" readonly="readonly"  class="srk">${map.jfgldyjmethod == '0' ? '同意' : ''}${map.jfgldyjmethod == '-1' ? '不同意' : ''}<c:if test="${not empty map.jfgldyjsuggest}">(${map.jfgldyjsuggest})</c:if></textarea>
    </td>
    </tr>
<tr>
    <td>备注</td>
    <td colspan="5">
    	<input type="text" id="bz" name="bz"  class="srk" readonly="readonly" value="${map.bz}"/>
    </td>
  </tr>


</table>
<p></p>
  </body>
</html>
