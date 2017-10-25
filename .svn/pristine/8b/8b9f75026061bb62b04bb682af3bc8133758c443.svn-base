<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
  <style type="text/css">
<!--
	.title h3{ padding:0 6px; font-size:100%; color:#000; font-weight:bold;}
	.title-gf{ height:35px; line-height:35px; }
	.title-1 h3{}
-->
.fontColor{
	font:.81em/150% arial,helvetica,sans-serif;
	color:#666;
}
#header{
	width: 95%;
	height: 30px;
	margin: 0 auto;
}
.mcenter{
	margin: 0 auto;
}
.tc{
	text-align: center;	
}
.tablew{
	width: 95%;
}
.trh{
	height: 30px;
}
h1{
	font-size: 2em;
}
h2{
	font-size: 1.6em;
}
h3{
	font-size: 1.4em;	
	color: #000000;
}
h4{
	font-size: 1.2em;	
	color: #000000;
}
h5{
	font-size: 1.1em;	
}
h6{
	font-size: 1em;	
}
.mt5pct{
	margin-top: 3%;
}
.mb5pct{
	margin-bottom: 4%;
}
.fr{
	float: right;
}
.bgecf{
	background-color: #ECF5FF;
}
</style>
 <script type="text/javascript" src="../views/common/js/jquery.min.js"></script>
<script type="text/javascript" src="../views/common/js/json2.js"></script>
<script type="text/javascript" src="../views/common/js/jquery.bgiframe.js"></script>
<script type="text/javascript" src="../views/common/js/jquery.contextmenu.r2.js"></script>
<!--=B 模板库  -->
<link rel="stylesheet" type="text/css" href="../views/common/css/themes/css/jquery-ui-jquiGui.css"/>
<script type="text/javascript" src="../views/common/js/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="../views/common/js/jquery.layout.min.js"></script>
<script type="text/javascript" src="../views/common/js/jquery.wresize.js"></script>
<script type="text/javascript" src="../views/common/js/ui.tabs.page.js"></script>

<!-- flexigrid  -->
<script type="text/javascript" src="../views/common/js/flexigrid/flexigrid.js"></script>
<link rel="stylesheet" type="text/css" href="../views/common/css/themes/css/flexigrid/flexigrid.css"/>
<!-- 验证插件 -->
<link rel="stylesheet" type="text/css" href="../views/common/css/themes/css/validate/jquery_validate.css"/>
<script type="text/javascript" src="../views/common/js/validate/jquery.validate.js"></script>
<!-- 向导插件 -->
<link rel="stylesheet" type="text/css" href="../views/common/css/themes/css/smartWizard/smart_wizard.css"/>
<!-- 扩展jQuery以及jQuery插件功能的js -->
<script type="text/javascript" src="../views/common/js/jquery.extend.js"></script>
<!-- 样式库  -->
<link rel="stylesheet" type="text/css" href="../views/common/css/themes/css/jquiGui.css" />
<!-- 模板调用变量库  -->
<!--=E 模板库  -->
<script type="text/javascript" src="../views/common/js/common.js"></script>
<script type="text/javascript">
var agentsSummary_tab_num = $(window.parent.document).find("#agentsSummary_tab_num").val();
var param_agents = {
	loadData:function(){
		$.ajax({
			type: "GET",
			url: "agentsSummary!agentsSummaryInfo.action?operation=init&fid="+agentsSummary_tab_num,
			cache: false,
			success: function(msg)
			{
			  if(msg.length > 0){
				  var result=eval("("+msg+")");
	              $('#agent_count_total').html(result.agent_count_total);
	              $('#agent_count_offline').html(result.agent_count_offline);
	              $('#agent_count_idle').html(result.agent_count_idle);
	              $('#agent_count_working').html(result.agent_count_working);
	              $('#agent_count_disable').html(result.agent_count_disable);
	              $('#agent_count_offline_disable').html(result.agent_count_offline_disable);
	              $('#agent_count_idle_disable').html(result.agent_count_idle_disable);
	              $('#agent_count_idle_enable').html(result.agent_count_idle_enable);
	              $('#agent_count_idle_enable_software_ready').html(result.agent_count_idle_enable_software_ready);
	              $('#agent_count_idle_enable_software_notready').html(result.agent_count_idle_enable_software_notready);
	              $('#agent_count_idle_enable_mount_ready').html(result.agent_count_idle_enable_mount_ready);
	              $('#agent_count_idle_enable_mount_notready').html(result.agent_count_idle_enable_mount_notready);
	              $('#agent_count_idle_enable_ready').html(result.agent_count_idle_enable_ready);
	              $('#agent_count_idle_enable_notready').html(result.agent_count_idle_enable_notready);
			  }else{
				  $('#agent_count_total').html(0);
	              $('#agent_count_offline').html(0);
	              $('#agent_count_idle').html(0);
	              $('#agent_count_working').html(0);
	              $('#agent_count_disable').html(0);
	              $('#agent_count_offline_disable').html(0);
	              $('#agent_count_idle_disable').html(0);
	              $('#agent_count_idle_enable').html(0);
	              $('#agent_count_idle_enable_software_ready').html(0);
	              $('#agent_count_idle_enable_software_notready').html(0);
	              $('#agent_count_idle_enable_mount_ready').html(0);
	              $('#agent_count_idle_enable_mount_notready').html(0);
	              $('#agent_count_idle_enable_ready').html(0);
	              $('#agent_count_idle_enable_notready').html(0);
			  }
			}
		}); 
	}
};
 
 $(function(){
		// 初始化数据
		param_agents.loadData();
		// 刷新按钮 
		$('#btn_refresh').button().on("click",function(){
		   param_agents.loadData();
		});
		
	});
</script>

<div class="div div-1">
	<!--<div class="title title-gf">
		<h3>
		 <img src='../images/fold.png' onclick="showAndHide(this);"  class="imgShrotate"> 
			<font>查询条件</font>
		</h3>
	</div>-->
	<div class="div-main-1" >
		<!-- <div class="title title-gf" style="overflow-y:auto;">
			<h3>节点总数：<span id="agent_count_total"></span></h3>
		</div>
		<div class="title title-gf" style="overflow-y:auto;">
			<h3>离线节点总数：<span id="agent_count_offline"></span></h3>
		</div>
		<div class="title title-gf" style="overflow-y:auto;">
			<h3>空闲节点总数：<span id="agent_count_idle"></span></h3>
		</div>
		<div class="title title-gf" style="overflow-y:auto;">
			<h3>工作节点总数：<span id="agent_count_working"></span></h3>
		</div>
		<div class="title title-gf" style="overflow-y:auto;">
			<h3>禁用节点总数：<span id="agent_count_disable"></span></h3>
		</div>
		<div class="title title-gf" style="overflow-y:auto;">
			<h3>离线禁用节点总数：<span id="agent_count_offline_disable"></span></h3>
		</div>
		<div class="title title-gf" style="overflow-y:auto;">
			<h3>空闲禁用节点总数：<span id="agent_count_idle_disable"></span></h3>
		</div>
		<div class="title title-gf" style="overflow-y:auto;">
			<h3>空闲未禁用节点总数：<span id="agent_count_idle_enable"></span></h3>
		</div>
		<div class="title title-gf" style="overflow-y:auto;">
			<h3>空闲未禁用软件可用节点总数：<span id="agent_count_idle_enable_software_ready"></span></h3>
		</div>
		<div class="title title-gf" style="overflow-y:auto;">
			<h3>空闲未禁用软件不可用节点总数：<span id="agent_count_idle_enable_software_notready"></span></h3>
		</div>
		<div class="title title-gf" style="overflow-y:auto;">
			<h3>空闲未禁用存储可用节点总数：<span id="agent_count_idle_enable_mount_ready"></span></h3>
		</div>
		<div class="title title-gf" style="overflow-y:auto;">
			<h3>空闲未禁用存储不可用节点总数：<span id="agent_count_idle_enable_mount_notready"></span></h3>
		</div>
		<div class="title title-gf" style="overflow-y:auto;">
			<h3>空闲未禁用可用节点总数：<span id="agent_count_idle_enable_ready"></span></h3>
		</div>
		<div class="title title-gf" style="overflow-y:auto;">
			<h3>空闲未禁用不可用节点总数：<span id="agent_count_idle_enable_notready"></span></h3>
		</div>
		<a href="#" id="btn_refresh">刷新</a> -->
		<header id="header">
			<hgroup style="text-align: center;height: 40px;">
				<h1 style="font-size: 1.8em">节点概况统计</h1>
			</hgroup>
		</header>
		<table class="fontColor  tablew mb5pct mcenter" cellpadding="0" cellspacing="0" border="1" style="border-collapse: collapse;" >
			<thead>
				<tr class="trh bgecf">
					<td class="tc">
						<h4>节点总数</h4>
					</td>
					<td class="tc">
						<h4>离线节点总数</h4>
					</td>
					<td class="tc">
						<h4>空闲节点总数</h4>
					</td>
					<td class="tc">
						<h4>工作节点总数</h4>
					</td>
					<td class="tc">
						<h4>禁用节点总数</h4>
					</td>
					<td class="tc">
						<h4>离线禁用节点总数</h4>
					</td>
					<td class="tc">
						<h4>空闲禁用节点总数</h4>
					</td>
				</tr>
			</thead>
			<tbody>
				<tr class="trh ">
					<td class="tc">
						<span id="agent_count_total"></span>
					</td>
					<td class="tc">
						<span id="agent_count_offline"></span>
					</td>
					<td class="tc">
						<span id="agent_count_idle"></span>
					</td>
					<td class="tc">
						<span id="agent_count_working"></span>
					</td>
					<td class="tc">
						<span id="agent_count_disable"></span>
					</td>
					<td class="tc">
						<span id="agent_count_offline_disable"></span>
					</td>
					<td class="tc">
						<span id="agent_count_idle_disable"></span>
					</td>
				</tr>
				<tr class="trh bgecf">
					<td class="tc">
						<h4>空闲未禁用节点总数</h4>
					</td>
					<td class="tc" >
						<h4>空闲未禁用软件可用节点总数</h4>
					</td>
					<td class="tc">
						<h4>空闲未禁用软件不可用节点总数</h4>
					</td>
					<td class="tc">
						<h4>空闲未禁用存储可用节点总数</h4>
					</td>
					<td class="tc">
						<h4>空闲未禁用存储不可用节点总数</h4>
					</td>
					<td class="tc">
						<h4>空闲未禁用可用节点总数</h4>
					</td>
					<td class="tc">
						<h4>空闲未禁用不可用节点总数</h4>
					</td>
				</tr>
				<tr class="trh">
					<td class="tc">
						<span id="agent_count_idle_enable"></span>
					</td>
					<td class="tc">
						<span id="agent_count_idle_enable_software_ready"></span>
					</td>
					<td class="tc">
						<span id="agent_count_idle_enable_software_notready"></span>
					</td>
					<td class="tc">
						<span id="agent_count_idle_enable_mount_ready"></span>
					</td>
					<td class="tc">
						<span id="agent_count_idle_enable_mount_notready"></span>
					</td>
					<td class="tc">
						<span id="agent_count_idle_enable_ready"></span>
					</td>
					<td class="tc">
						<span id="agent_count_idle_enable_notready"></span>
					</td>
				</tr>
			</tbody>
		</table>
		<a href="javascript:void(0);" id="btn_refresh" class="mt5pct  fr">刷新</a>
	</div>
</div>