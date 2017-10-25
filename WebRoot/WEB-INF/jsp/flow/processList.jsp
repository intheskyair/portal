<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>流程定义</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		    <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquiGui.css"/>
		    	 <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquery-ui-jquiGui.css"/>
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/table.js" type="text/javascript"></script>
		
		<link rel="stylesheet" type="text/css" href="styles/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="styles/easyui/themes/icon.css">
		<script type="text/javascript" src="styles/jquery.min.js"></script>
		<script type="text/javascript" src="styles/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="styles/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script>
		function optSet(value,row,index){
		var opt;
		if(row.state==1){
			opt="<a href='${ctx}"+row.instanceUrl+"?processId="+row.id+"&processName="+row.name+"' class='btnStart' title='启动流程'>启动流程</a>";
		}
			opt+="<shiro:hasPermission name='PROCESSDEPLOY'>";
			opt+="<a href='${ctx}/process/edit?id="+row.id+"' class='btnEdit' title='编辑'>编辑</a>";
			opt+="<a href='${ctx}/process/designer?processId="+row.id+"' class='btnDesigner' title='设计'>设计</a>";
			opt+="<a href='${ctx}/process/delete?id="+row.id+"' class='btnDel' title='删除' onclick='return confirmDel();'>删除</a>";
			opt+="<a href='${ctx}/process/reStart?id="+row.id+"' class='btnAdd' title='重新启用'>重新启用</a>";
			opt+="</shiro:hasPermission>";
			return opt;
		}	
		
		
		function stateFmt(value,row,index){
		var opt;
		if(row.state==1){
			opt="启用";
		}else{
			opt="禁用";
		}
			return opt;
		}
		
		function search_process(){
			var pname=$("#process_name_search").val();
			$('#process_data').datagrid({
    			queryParams:{
    				"displayName":encodeURI(pname)
    			}
    		});
			
		}
		</script>
	</head>
	<body>
<!-- 	<form id="mainForm" action="${ctx}/process/list" method="get"> -->
		<input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}"/>				
		
		<div id="div_t" class="datagrid-toolbar" style="padding:5px;height:auto;">
<!-- 			<table cellpadding="0" cellspacing="0" border="0" class="table-1"> -->
<!-- 				<tbody><tr style="font-size: 12px"> -->
<!-- 					<td class="wi-align-l"> -->
					<label for="input_1" align="center" class="lbl-1" style="padding-left: 10px;">流程名：</label>
					<input type="text" id="process_name_search"  class="easyui-textbox" name="displayName" />						
<!-- 					<td class="wi-align-l" rowspan="1"> -->
<!-- 						<button id="btn_process_search" onclick="search_process()" class="easyui-linkbutton" iconCls="icon-search" >查询</button> -->
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="search_process()" iconCls="icon-search">查询</a>
<!-- 					</td> -->
<!-- 					<td align="left"> -->
					<shiro:hasPermission name="PROCESSDEPLOY">
						<a href="javascript:void(0)" onclick="addNew('${ctx}/process/designer')" class='easyui-linkbutton' iconCls="icon-add">新建流程</a>
						<%-- <input type='button' onclick="addNew('${ctx}/process/deploy')" class='button_70px' value='部署'/>
						<input type='button' onclick="addNew('${ctx}/process/init')" class='button_70px' value='初始化'/> --%>
					</shiro:hasPermission>
<!-- 					</td>																																			 -->
<!-- 				</tr>			 -->
			
<!-- 				</tbody> -->
<!-- 			</table> -->
		</div>
		
		
				
		<div style="height:100%">
		<table id="process_data" class="easyui-datagrid" title="流程定义" style="width:100%;height:100%"
			data-options="
				rownumbers: true,
				singleSelect: true,
				iconCls: 'icon-save',
				url: 'process!getProcessList.action',
				method: 'get',
				pagination:true,pageSize:20,
				toolbar:'#div_t'
			">
		
		<thead>
			<tr>
				<th data-options="field:'id',width:'15%',align:'center'">ProcessId</th>
				<th data-options="field:'name',width:'15%',align:'center'">Name</th>
				<th data-options="field:'displayName',width:'35%',align:'center'">流程名称</th>
				<th data-options="field:'state',width:'10%',align:'center',formatter:stateFmt">状态</th>
				<th data-options="field:'version',width:'10%',align:'center'">版本号</th>
				<shiro:hasPermission name="PROCESSDEPLOY">
				<th data-options="field:'ck',align:'center',formatter:optSet" width="15%">操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		
		</table>
	</div>
<!-- 		<table class="table_all" align="center" border="0" cellpadding="0" -->
<!-- 			cellspacing="0" style="margin-top: 0px"> -->
<!-- 			<tr> -->
<!-- 				<td align=center width=15% class="td_list_1" nowrap> -->
<!-- 					ProcessId -->
<!-- 				</td> -->
<!-- 				<td align=center width=15% class="td_list_1" nowrap> -->
<!-- 					Name -->
<!-- 				</td> -->
<!-- 				<td align=center width=35% class="td_list_1" nowrap> -->
<!-- 					流程名称 -->
<!-- 				</td> -->
<!-- 				<td align=center width=10% class="td_list_1" nowrap> -->
<!-- 					状态 -->
<!-- 				</td> -->
<!-- 				<td align=center width=10% class="td_list_1" nowrap> -->
<!-- 					版本号 -->
<!-- 				</td> -->
<!-- 				<td align=center width=30% class="td_list_1" nowrap> -->
<!-- 					操作 -->
<!-- 				</td>				 -->
<!-- 			</tr> -->
<!-- 			<c:forEach items="${page.result}" var="process"> -->
<!-- 				<tr> -->
<!-- 					<td class="td_list_2" align=left nowrap> -->
<!-- 						${process.id}&nbsp; -->
<!-- 					</td> -->
<!-- 					<td class="td_list_2" align=left nowrap> -->
<!-- 						${process.name}&nbsp; -->
<!-- 					</td> -->
<!-- 					<td class="td_list_2" align=left nowrap> -->
<!-- 						${process.displayName}&nbsp; -->
<!-- 					</td> -->
<!-- 					<td class="td_list_2" align=left nowrap> -->
<!-- 						${process.state == 1 ? '启用' : '禁用'}&nbsp; -->
<!-- 					</td> -->
<!-- 					<td class="td_list_2" align=left nowrap> -->
<!-- 						${process.version}&nbsp; -->
<!-- 					</td>					 -->
<!-- 					<td class="td_list_2" align=left nowrap> -->
					   <!--  <a href="#" id="btn_param_search" class="btnStart" title="启动流程">启动流程</a> -->
<!-- 					   <c:if test="${process.state == 1 }"> -->
<!-- 						 <a href="${ctx}${process.instanceUrl }?processId=${process.id }&processName=${process.name }" class="btnStart" title="启动流程">启动流程</a>  -->
<!-- 						</c:if> -->
<!-- 						<shiro:hasPermission name="PROCESSDEPLOY"> -->
<!-- 						<a href="${ctx}/process/edit?id=${process.id }" class="btnEdit" title="编辑">编辑</a> -->
<!-- 						<a href="${ctx}/process/designer?processId=${process.id }" class="btnDesigner" title="设计">设计</a> -->
<!-- 						<a href="${ctx}/process/delete?id=${process.id }" class="btnDel" title="删除" onclick="return confirmDel();">删除</a> -->
<!-- 						<a href="${ctx}/process/reStart?id=${process.id }" class="btnAdd" title="重新启用">重新启用</a> -->
<!-- 						</shiro:hasPermission> -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 			</c:forEach> -->
<!-- 			<frame:page curPage="${page.pageNo}" totalPages="${page.totalPages }" totalRecords="${page.totalCount }"/> -->
<!-- 		</table> -->
<!-- 	</form> -->
	</body>
</html>
