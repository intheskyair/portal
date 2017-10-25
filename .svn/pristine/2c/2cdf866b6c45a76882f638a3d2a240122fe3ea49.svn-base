<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head> 
  <%@ include file="/common/meta.jsp"%>
    <title>公开保密审查表</title>
<style>
.biaoge{ width:600px;margin-bottom: 10px;}
.biaoge p{ float:right;}
.gridtable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
.gridtable td {
	border:1px #666666 solid;
	padding:5px;
	background-color: #ffffff;
}
.gridtable_a{ margin:10px;float:left;}
.gridtable_b{ margin:10px;overflow:hidden; float:left;}
.gridtable_b span{ height:25px; float:left; }
input {
	border: none;
	outline: medium;
}
textarea {
/* 	width: 400px; */
	border: none;
}
h2{ text-align:center;font-size: 28px;font-weight: 600;}
</style>
	<script type="text/javascript">
     $(function(){
    	 $("input[type=radio][value=${map.qtype}]").attr("checked",'checked'); 
    }); 
</script>	
  </head>
  <body >
	 <div class="biaoge">
<h2>常州市行政机关信息公开保密审查单</h2>
<p>${map.gk_time}</p>
<table border="0" class="gridtable">
  <tr>
    <td colspan="2">标题：${map.gk_title}</td>
  </tr>
  <tr>
    <td>初步审查</td>
    <td>
    <div class="gridtable_a">承办人意见：${map.rect2}
      </div>
      <div class="gridtable_b"><span style=" margin-left:250px;">签字：${map.rect2user}</span>
        <span style="margin-left:350px;">${map.rect2time}</span>
      </div>
    </td>
  </tr>
  <tr>
    <td>初步审查</td>
    <td>
    <div class="gridtable_a">处室负责人意见：${map.rect3}
      </div>
      <div class="gridtable_b"><span style=" margin-left:250px;">签字：${map.rect3user}</span>
        <span style="margin-left:350px;">${map.rect3time}</span>
      </div>
    </td>
  </tr>
  <tr>
    <td>专业审查</td>
    <td>
    <div class="gridtable_a">保密审查意见：${map.rect4}
      </div>
      <div class="gridtable_b"><span style=" margin-left:250px;">签字：${map.rect4user}</span>
        <span style="margin-left:350px;">${map.rect4time}</span>
      </div>
    </td>
  </tr>
  <tr>
    <td>复合审查</td>
    <td>
    <div class="gridtable_a">主管领导审查意见：${map.rect5}
      </div>
      <div class="gridtable_b"><span style=" margin-left:250px;">签字：${map.rect5user}</span>
       <span style="margin-left:350px;">${map.rect5time}</span>
      </div>
    </td>
  </tr>
   <tr>
    <td>程序审查</td>
    <td>
    <div class="gridtable_a">网站责任人意见：${map.rect6}
<!--         <textarea ></textarea> -->
      </div>
      <div class="gridtable_b"><span style=" margin-left:250px;">签字：${map.rect6user}</span>
       <span style="margin-left:350px;">${map.rect6time}</span>
      </div>
    </td>
  </tr>
</table>
</div>
  </body>
</html>
