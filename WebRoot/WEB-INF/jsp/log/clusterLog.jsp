 <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
<script type="text/javascript" src="../views/common/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../views/common/js/common.js"></script>
 <script type="text/javascript">
 var clusterLogGridHeight = $(window.self).height() - $("body > div").outerHeight() - 190;
 var cluster_log_grid;
 var clusterLog_tab_num = $(window.parent.document).find("#clusterLog_tab_num").val();
 var cluster_log = {
			//绑定参数信息grid
			bindClusterLogGrid:function(queryP){
				cluster_log_grid = $("#cluster_log_grid").flexigrid({
					url:"manageLog!listLogsCluster.action?operation=init&fid="+clusterLog_tab_num,
					colModel : [
						{display: '序号', name : 'xh', width : 40, align: 'center'},
						{display: 'ID', name : 'id', width : 40, align: 'center', hide :true},
						{display: '节点IP', name : 'agent_ip', width : 400, align: 'left', sortable :true},
						{display: '内容', name : 'content', width : 650, align: 'left'},
						{display: '用户ID', name : 'job_user_id', width : 250,  align: 'left', hide: true},
						{display: '创建时间', name : 'create_time', width : 300,  align: 'left', sortable :true}
					],
					rowId:"id",
					showToggleBtn: false,
					usepager: true,
					title:"集群日志列表",
					//rp:page_num,
					rp:30,
					showTableToggleBtn: false,
					resizable: false,
					height:clusterLogGridHeight,
					singleSelect: true,
					onChangePage:cluster_log.loadData,
					filterParams : [{key : "dateType", name : "[name=text_cluster_log_date]:checked"},
									{key : "preDate", name : "#text_cluster_log_preDate"},
									{key : "postDate", name : "#text_cluster_log_postDate"}]
				});
			},
			loadData:function(param){

				var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
				param.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
				//param.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
				param.pagesize = param.rp?param.rp:$("[name=rp]").val();
				// 检索条件
				param.dateType = $('[name=text_cluster_log_date]:checked').val();
				param.preDate  = $("#text_cluster_log_preDate").val();
				param.postDate = $("#text_cluster_log_postDate").val();
				param.fid = clusterLog_tab_num;
				$.ajax({
					type: "POST",
					url: "manageLog!listLogsCluster.action?operation=init",
					cache: false,
					data: param,
					success: function(msg){
						var data=eval("("+msg+")");
						try{
							cluster_log_grid.flexAddData(data);
						}catch(e){
							showErrorMsg("查询节点日志出错！");
						}
					}
				}); 
			} 
		};
 
 $(function(){
		/*公告管理-查询按钮功能*/
		$('#btn_cluster_log_search').button({ icons: {primary:'ui-icon-search'} }).on("click",function(){
			cluster_log.loadData({page : 1});
		});
		// 初始化数据
		cluster_log.bindClusterLogGrid();
		$('#btn_cluster_log_empty').on("click",function(){
			$("#text_cluster_log_preDate").val("");
			$("#text_cluster_log_postDate").val("");
			$('[name=text_cluster_log_date]').attr("checked",false);
			$("#cluster_log_self_date").hide();
			$('[name=text_cluster_log_date][value=0] ').attr("checked",true);
		//	param.loadData({page:1});
		});
		// 绑定日期个格式radio变化的问题
		$("[name=text_cluster_log_date]").change(function() {
			var dateType = $('[name=text_cluster_log_date]:checked').val();
			if (dateType == '4') {
				$("#cluster_log_self_date").show();
			} else {
				$("#cluster_log_self_date").hide();
			}
		});
	});
</script>
<div class="div div-1">
	<div class="title title-1">
		<h3>
			<img src='../images/fold.png' onclick="showAndHide(this,'cluster_log_grid');"  class="imgShrotate">
			<font>查询条件</font>
		</h3>
	</div>
	<div class="div-main-1" >
		<table  border="0" class="table-1">
			<tr>
				<td style="width:100px;"><label for="input_1">提交时间：</label>&nbsp;&nbsp;</td>
				<td colspan="5" align="left">
					<input type="radio" name="text_cluster_log_date" value="0" checked />全部&nbsp;
					<input type="radio" name="text_cluster_log_date" value="1" />当天&nbsp;
					<input type="radio" name="text_cluster_log_date" value="2" />本周&nbsp;
					<input type="radio" name="text_cluster_log_date" value="3" />本月&nbsp;
					<input type="radio" name="text_cluster_log_date" value="4" />自定义<span id="cluster_log_self_date" style="display: none">：
					<input type="text" id="text_cluster_log_preDate" name="text_cluster_log_preDate" readonly class="Wdate" style="height:25px; width: 170px;"
					 onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />&nbsp;至&nbsp;
					<input type="text" id="text_cluster_log_postDate" name="text_cluster_log_postDate" readonly class="Wdate" style="height:25px; width: 170px;"
					 onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" /></span>
				</td>
				<td class="wi-align-l" rowspan="2" style="width:200px;text-align: right"><a href="javascript:void(0);" id="btn_cluster_log_search">查询</a></td>
				<td class="wi-align-l" rowspan="2" style="width: 100px;"><button id="btn_cluster_log_empty" style="width:74px; height:29px;font-size:14px;background-color:#3ea4e8;color: #fff">清空</button></td>
			</tr>
		</table>
	</div>
</div>
<div class="div div-2" style="overflow:auto;">
	<table id="cluster_log_grid" style="display:none;padding:0 0 0 0;"></table>
</div>