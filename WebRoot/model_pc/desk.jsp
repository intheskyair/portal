<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="webkit">
<link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css"/>
<script type="text/javascript" src="${ctx}/views/common/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/views/common/js/jquery.bgiframe.js"></script>
<script type="text/javascript" src="${ctx}/views/common/js/jquery.contextmenu.r2.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquery-ui-jquiGui.css"/>
<script type="text/javascript" src="${ctx}/views/common/js/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/views/common/js/jquery.layout.min.js"></script>
<script type="text/javascript" src="${ctx}/views/common/js/jquery.wresize.js"></script>
<script type="text/javascript" src="${ctx}/views/common/js/ui.tabs.page.js"></script>
<script type="text/javascript" src="${ctx}/views/common/css/themes/js/GlobalValue.js"></script>
<script type="text/javascript" src="${ctx}/views/common/js/common.js"></script>
<%-- <script type="text/javascript" src="${ctx}/views/shell/js/desktop.js"></script> --%>
<title>常州市水政监察支队水事违法案件查办通知</title>
</head>
<body>
<table class="gzt">
<%--   <tr>
    <td style=" height:30px; " colspan="3"><table style=" border:1px solid #ccc; height:100%;font-size:12px;">
        <tr>
          <td style=" width:150px;">您好,欢迎您!${admin.truename}</td>
          <td style=" width:90px;">[ 查看资料 ]</td>
          <td style=" width:90px;">[ 修改密码 ]</td>
          <td style=" width:90px;">[ 机构导航 ]</td>
          <td></td>
          <td style=" text-align:right" >您的系统没有设置滚动公告，这里显示的是默认信息
                                                                                滚动公告在 系统管理=>资讯管理 中设置. 谢谢使用</td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td  style=" height:5px;" colspan="3"></td>
  </tr> --%>
  <tr>
  
  
   <td rowspan="2"><ul class="gztlb">
        <li class="gztlb-bt">快捷导航</li>
        <li>
<div class="one_type" style="float:left;    border-right: 1px #ccc solid;"> 
   <div class="one_type_top"> 
    <div style="float:left;" class="one_type_name"> 
     <span>日常办公</span>
    </div>
   </div> 
   <ul style="margin-left:10px;" class="cont" id="mytree"> 
   <li><a href="javascript:doprocess_noframe('../worklog!resultDfList.action','工作日志','20128')">工作日志</a></li> 
    <li><a href="javascript:doprocess('../carManager!carApply.action?processId=a24f18f112a2414aae76accbe62a4cc4&orderId=&taskId=&taskName=rec','车辆申请','20091')">车辆申请</a></li>
    <li><a href="javascript:doprocess('../leave!leave.action?processId=5ba62e2b649b493d8462b96c5a3e76ce&orderId=&taskId=&taskName=qjsh','请假申请','20038')">请假申请</a></li>  
    <li><a href="javascript:doprocess('../plan!summary.action?processId=0cfc092d8e44454382fd68d5538656bc&orderId=&taskId=&taskName=gzjh','工作计划','20042')">工作计划申报</a></li> 
    <li><a href="javascript:doprocess('../bmgz!bmgz.action?step=1&processId=bf79e27c4f4047f0b67ad6e6402c093f&orderId=&taskId=&taskName=rect2','部门工作月报','20052')">部门工作月报申报</a></li>
    <li><a href="javascript:doprocess('../dcdb!dcdb.action?step=1&processId=6058537983074b0f86269358270fefd9&orderId=&taskId=&taskName=rect2','督察督办','20054')">督察督办发起</a></li> 
    <li><a href="javascript:doprocess('../assetapply!assetApply.action?processId=830c84eb0c4f456d93f8e072f4695f8d&processName=assetapply','资产领用申请','20110')">资产领用申请</a></li>
    <li><a href="javascript:doprocess('../assetmanager!assetInput.action?processId=af57a3325ede4424a4d8f9868cc8c1e3&processName=assetinput','资产信息管理','20077')">资产录入</a></li> 
    <li><a href="javascript:doprocess('../carManager!active.action?processId=a24f18f112a2414aae76accbe62a4cc4','车辆审批','20092')">车辆审批</a></li>         
    <li><a href="javascript:doprocess('../leave!active.action?processId=5ba62e2b649b493d8462b96c5a3e76ce','请假审批','20049')">请假审批</a></li>  
    <li><a href="javascript:doprocess('../plan!active.action?processId=0cfc092d8e44454382fd68d5538656bc','工作计划待办','20044')">工作计划审批</a></li>  
    <li><a href="javascript:doprocess('../bmgz!active.action?processId=bf79e27c4f4047f0b67ad6e6402c093f','部门工作月报表审核','20053')">部门工作月报表审核</a></li>     
    <li><a href="javascript:doprocess('../dcdb!active.action?processId=6058537983074b0f86269358270fefd9','督察督办审核','dcdb_do')">督察督办审核</a></li>       
    <li><a href="javascript:doprocess('../assetmanager!active.action?processId=af57a3325ede4424a4d8f9868cc8c1e3','资产录入审核','20103')">资产录入审核</a></li>    
    <li><a href="javascript:doprocess('../assetapply!active.action?processId=830c84eb0c4f456d93f8e072f4695f8d','资产领用审核','20110')">资产领用审核</a></li>     
    <li><a href="javascript:doprocess_noframe('../boardroom!boardroom_infor.action','会议室查询','20107')">会议室预约</a></li>   
   </ul> 
  </div>           
    <div class="one_type" style="float:left;"> 
   <div class="one_type_top"> 
    <div style="float:left;" class="one_type_name"> 
     <span>公文管理</span>
    </div>
   </div> 
   <ul style="margin-left:10px;" class="cont"> 
    <li><a href="javascript:doprocess('../recdoc!recdoc.action?processId=739ba060ba08406abab4aa1fa0ae6346&orderId=&taskId=&taskName=rect2','收文签收','20048')">收文拟稿申请</a></li>  
    <li><a href="javascript:doprocess('../docum!fwg.action?processId=8289579875e346da8ddc563bacf23081&gkprocessId=815dfa3477234a8da5accd0f3513cbd4','发文拟稿','20045')">发文拟稿申请</a></li>
    <li><a href="javascript:doprocess('../recdoc!active.action?processId=739ba060ba08406abab4aa1fa0ae6346','收文审核','20050')">收文审核</a></li> 
    <li><a href="javascript:doprocess('../docum!active.action?processId=8289579875e346da8ddc563bacf23081','发文审核','20046')">发文审核</a></li>   
   <c:choose>
	   <c:when test="${admin.privilegeId == 1}"> 
	   		 <li><a href="javascript:doprocess_noframe('../gwgd!GwgdList.action?processId=739ba060ba08406abab4aa1fa0ae6346&type=0','收文归档','20126')">收文归档</a></li>  
	   		 <li><a href="javascript:doprocess_noframe('../gwgd!GwgdList.action?processId=8289579875e346da8ddc563bacf23081&type=1','发文归档','20127')">发文归档</a></li>  
	   </c:when>
   </c:choose>
   </ul> 
  </div> 
    <div class="one_type" style="float:left;   "> 
   <div class="one_type_top"> 
    <div style="float:left;" class="one_type_name"> 
     <span>水政执法管理</span>
    </div>
   </div> 
   <ul style="margin-left:10px;" class="cont" > 
   <c:choose>
	   <c:when test="${admin.privilegeId != 48 && admin.privilegeId != 37 && admin.privilegeId != 39}"> 
	    <li><a href="javascript:doprocess('../szzf!szzf.action?processId=78259d9e98944bbca8cce4315cfe1c85&orderId=&taskId=&taskName=jsjb','水政执法','20033')">水政执法</a></li> 
	    <li><a href="javascript:doprocess('../szzf!active.action?processId=78259d9e98944bbca8cce4315cfe1c85','水政执法代办','20043')">水政执法待办</a></li> 
	    <c:choose>
		   <c:when test="${admin.privilegeId != 40 && admin.privilegeId != 41}"> 
		   		  <li><a href="javascript:doprocess_noframe('../szzf!szxcList.action','水政巡查','20101')">水政巡查上报</a></li>   
		   </c:when>
	   </c:choose>
   
 	</c:when>
  </c:choose> 
   </ul> 
  </div>  
   </li>     
      </ul></td>  
     <td><ul class="gztlb">
        <li class="gztlb-bt">统计信息</li>
        <%--  <li><img src="${ctx}/images/al.gif"  class="gzt-img">『我的资讯』：( 0 )      『工作任务』：( 0/0 )</li>
           <li><img src="${ctx}/images/al.gif"  class="gzt-img">『工作流程』：( 0/0 )     <a href="javascript:void(0);" onclick="desg();">新建流程</a>  |  <a href="javascript:void(0);" onclick="queryFlow();">个人流程查询</a></li>
           <li><img src="${ctx}/images/al.gif"  class="gzt-img">『请假流程』：( 0 )      <a href="javascript:void(0);" onclick="leave();">请假流程</a>  |  <a href="javascript:void(0);" onclick="leave_do();">请假流程待办</a></li>
           <li><img src="${ctx}/images/al.gif"  class="gzt-img">『工作计划』：( 0 )      <a href="javascript:void(0);" onclick="plan_app();">工作计划</a>  |  <a href="javascript:void(0);" onclick="plan_do();">工作计划待办</a></li>           
           <li><img src="${ctx}/images/al.gif"  class="gzt-img">『部门工作月报』：( 0 )      <a href="javascript:void(0);" onclick="bmgz();">工作月报创建</a>  |  <a href="javascript:void(0);" onclick="bmgz_do();">工作月报待办</a></li>           
           <li><img src="${ctx}/images/al.gif"  class="gzt-img">『水政执法』：( 0 )      <a href="javascript:void(0);" onclick="szzf();">水政执法</a>  |  <a href="javascript:void(0);" onclick="szzf_do();">水政执法待办</a></li> --%>
           <li><img src="${ctx}/images/al.gif"  class="gzt-img">『收文』：( ${swCount == null ? 0 : swCount} )      <a href="javascript:void(0);" onclick="recdoc();">收文签收</a>  |  <a href="javascript:void(0);" onclick="recdoc_do();">收文待办</a></li>
           <li><img src="${ctx}/images/al.gif"  class="gzt-img">『发文』：( ${fwCount == null ? 0 : fwCount} )     <a href="javascript:void(0);" onclick="docum();">发文拟稿</a>  |  <a href="javascript:void(0);" onclick="docum_do();">发文待办</a></li>         
         <%--   <li><img src="${ctx}/images/al.gif"  class="gzt-img">『督察督办』：( 0 )    <a href="javascript:void(0);" onclick="dcdb();">督察督办创建</a>  |  <a href="javascript:void(0);" onclick="dcdb_do();">督察督办待办</a></li>  --%>       
      </ul></td>
  </tr>
  <tr>
  <td><ul class="gztlb">
        <li class="gztlb-bt">我的邮件</li>
         <li><img src="${ctx}/images/mail_isread.gif"  class="gzt-img"> [系统通知] : ${admin.truename}, 您好! 您的系统账号已开通, 欢迎使用本系统!</li>
      </ul></td>
  </tr>
</table>

<script type="text/javascript">
function queryFlow()
{
    var url = "${ctx}/personalProcess!personalProcessList.action";
    var title="个人流程查询";
    var key="queryFlow";
    doprocess_noframe(url,title,key);
}


function desg()
{
    var url = "${ctx}/process/designer";
    var title="新建流程";
    var key="addDesigner";
    doprocess(url,title,key);
}

function leave()
{
    var url = "${ctx}/leave!allLeave.action?processId=5ba62e2b649b493d8462b96c5a3e76ce";
    var title="请假申请";
    var key="leave";
    doprocess(url,title,key);
}

function leave_do()
{
    var url = "${ctx}/leave!active.action?processId=5ba62e2b649b493d8462b96c5a3e76ce";
    var title="请假流程待办";
    var key="leave_do";
    doprocess(url,title,key);
}

function plan_app()
{
    var url = "${ctx}/plan!allPlan.action?processId=0cfc092d8e44454382fd68d5538656bc&processName=personPlan";
    var title="工作计划申请";
    var key="plan_app";
    doprocess(url,title,key);
}

function plan_do()
{
    var url = "${ctx}/plan!active.action?processId=0cfc092d8e44454382fd68d5538656bc";
    var title="工作计划待办";
    var key="plan_do";
    doprocess(url,title,key);
}

function szzf()
{
    var url = "${ctx}/szzf!allSzzf.action?processId=78259d9e98944bbca8cce4315cfe1c85";
    var title="水政执法申请";
    var key="szzf";
    doprocess(url,title,key);
}

function szzf_do()
{
    var url = "${ctx}/szzf!active.action?processId=78259d9e98944bbca8cce4315cfe1c85";
    var title="水政执法待办";
    var key="szzf_do";
    doprocess(url,title,key);
}

function docum()
{
    var url = "${ctx}/docum!alldocumet.action?processId=8289579875e346da8ddc563bacf23081";
    var title="发文登记";
    var key="docum";
    doprocess(url,title,key);
}
function docum_do()
{
    var url = "${ctx}/docum!active.action?processId=8289579875e346da8ddc563bacf23081";
    var title="发文审核";
    var key="docum_do";
    doprocess(url,title,key);
}

function recdoc()
{
    var url = "${ctx}/recdoc!alldocumet.action?processId=739ba060ba08406abab4aa1fa0ae6346";
    var title="收文登记";
    var key="docum";
    doprocess(url,title,key);
}
function recdoc_do()
{
    var url = "${ctx}/recdoc!active.action?processId=739ba060ba08406abab4aa1fa0ae6346";
    var title="收文审核";
    var key="recdoc_do";
    doprocess(url,title,key);
}

function bmgz()
{
    var url = "${ctx}/bmgz!allBmgz.action?processId=bf79e27c4f4047f0b67ad6e6402c093f&processName=bmgzybb";
    var title="部门工作月报上报";
    var key="bmgz";
    doprocess(url,title,key);
}


function bmgz_do()
{
    var url = "${ctx}/bmgz!active.action?processId=bf79e27c4f4047f0b67ad6e6402c093f";
    var title="部门工作月报审核";
    var key="bmgz_do";
    doprocess(url,title,key);
}


function dcdb()
{
    var url = "${ctx}/dcdb!allDcdb.action?processId=6058537983074b0f86269358270fefd9&processName=dcdb";
    var title="督察督办上报";
    var key="dcdb";
    doprocess(url,title,key);
}


function dcdb_do()
{
    var url = "${ctx}/dcdb!active.action?processId=6058537983074b0f86269358270fefd9";
    var title="督察督办审核";
    var key="dcdb_do";
    doprocess(url,title,key);
}


function doprocess(url,title,key)
{
	var option = {};
	option.key = key;
	option.title = title;
	option.url =   url;
	option.isIframe = true;
	option.noClose = 0;
	window.parent.addTab(option);
	//addTab(option);    
}

function doprocess_noframe(url,title,key)
{
	var option = {};
	option.key = key;
	option.title = title;
	option.url =   url;
	option.isIframe = false;
	option.noClose = 0;
	window.parent.addTab(option);
	//addTab(option);    
}

</script>


</body>
</html>
