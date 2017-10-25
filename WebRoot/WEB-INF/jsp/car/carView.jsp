<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head> 
  <%@ include file="/common/meta.jsp"%>
    <title>车辆申请审批详细信息</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css"/>
	<script type="text/javascript">
     $(function(){
    	 $("input[type=radio][value=${map.qtype}]").attr("checked",'checked'); 
    }); 
</script>	
  </head>
  <body style="overflow: scroll;">
	  <p class="btt" style=" font-size:30px; margin-top:30px; margin-bottom:20px">应急保障（防汛）车辆使用审批单</p>
<table class="table06" cellpadding="0" cellspacing="0" >
<tr>
    <td  style=" width:150px;">申请处室</td>
    <td colspan="2" style=" width:100px;"><input type="text" id="section" name="section" readonly="readonly" value="${map.section}" class="srk"/></td>
    <td>申请人</td>
    <td colspan="2"><input type="text"  id="name" name="name" value="${map.name}" readonly="readonly" class="srk"/></td>
  </tr>
<tr>
    <td rowspan="2">用车起至时间</td>
    <td colspan="2" style=" width:100px;">起<input type="text" id="stime" name="stime" readonly="readonly" value="${map.stime}" class="srk"/></td>

    <td rowspan="2">目的地</td>
      <td rowspan="2"><input type="text" id="mdd" name="mdd" readonly="readonly" value="${map.mdd}" class="srk"/></td>
</tr>
<tr>
    <td colspan="2" style=" width:100px;">至<input type="text" id="etime" name="etime" value="${map.etime}" readonly="readonly" class="srk"/></td> 
</tr>
<tr>
    <td>用车是由</td>
    <td colspan="2"  style=" width:100px;">
    	<input type="text" id="ycsy" name="ycsy" value="${map.ycsy}" readonly="readonly" class="srk"/>
    </td>  
    <td >随行人员</td>
    <td colspan="2">
    	<input type="text" id="sxry" name="sxry" value="${map.sxry}" readonly="readonly" class="srk"/>
    </td>
  </tr>
  <tr>
    <td >车号</td>
    <td colspan="2" style=" width:100px;"><input type="text" id="car_no" name="car_no" value="${map.car_no}" readonly="readonly" class="srk"/></td> 
    <td >驾驶员</td>
    <td colspan="2" style=" width:100px;"><input type="text" id="jsy" name="jsy" value="${map.jsy}" readonly="readonly" class="srk"/></td>
</tr>
<tr>
    <td>办公室意见</td>
    <td colspan="4" style=" width:100px;"><textarea  style="overflow:hidden" id="bmyj" name="bmyj" readonly="readonly"  class="srk">${map.rect3method == '0' ? '同意' : ''}${map.rect3method == '-1' ? '不同意' : ''}<c:if test="${not empty map.rect3suggest}">(${map.rect3suggest})</c:if></textarea></td>

</tr>

  <tr>
    <td>局领导意见</td>
    <td colspan="5">
    	<textarea style="overflow:hidden" id="jfgldyj" name="jfgldyj" readonly="readonly"  class="srk">${map.rect4method == '0' ? '同意' : ''}${map.rect4method == '-1' ? '不同意' : ''}<c:if test="${not empty map.rect4suggest}">(${map.rect4suggest})</c:if></textarea>
    </td>
    </tr>
</table>
<p></p>
  </body>
</html>
