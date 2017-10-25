<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    <%@ include file="/common/taglibs.jsp"%>
	<%@ include file="/common/commonCSSJS.jsp"%>
	<link rel="stylesheet" type="text/css" href="styles/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="styles/easyui/themes/icon.css">
	<script type="text/javascript" src="styles/jquery.min.js"></script>
	<script type="text/javascript" src="styles/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="styles/easyui/plugins/datagrid-detailview.js"></script>
	<script type="text/javascript" src="styles/easyui/locale/easyui-lang-zh_CN.js"></script>
	<title>请假汇总</title>
	<script>
		$(function(){
			$("#yearChoose").combobox({   
				valueField:'year',    
				textField:'year',  
				panelHeight:'auto'
				});
			var data = [];//创建年度数组
			var startYear;//起始年份
			var thisYear=new Date().getUTCFullYear();//今年
			var endYear=thisYear+1;//结束年份
			//数组添加值（2012-2016）//根据情况自己修改
			for(startYear=endYear-4;startYear <endYear ; startYear++ ){
				data.push({"year":startYear});
			}
			$("#yearChoose").combobox("loadData", data);//下拉框加载数据
			$("#yearChoose").combobox("setValue",thisYear);//设置默认值为今年
		
		
			
		
		});
		
		  //搜索
    	function loadsea(){
    		$('#qj_data').datagrid({
    			method:'post',
    			queryParams:{
    				"year":$("#yearChoose").datebox('getValue'),
    				"username":$("#username").textbox('getValue')
    			}
    		});
    	}
		
		function onLoadSuccess(){
			$("#qj_data").datagrid("autoMergeCells", ['name']);
		}
	</script>
	</head>
	<body>	
		<div id="div_t" class="datagrid-toolbar" style="padding:5px;height:auto">
			<label class="ui-lable">年度</label>
			<input class="easyui-combobox" id="yearChoose" style="width:100px">
			<label class="ui-lable">姓名</label>
			<input class="easyui-textbox" id="username"  style="width:80px">
				         <a href="javascript:;" onclick="loadsea()" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
		</div>	



		<table id="qj_data" class="easyui-datagrid" title="请假汇总，例 3.5（3天零5小时）" style="width:100%;height:100%"
			data-options="
				rownumbers: true,
				singleSelect: true,
				iconCls: 'icon-save',
				url: 'leave!getLeaveList.action',
				method: 'get',
				onLoadSuccess: onLoadSuccess,
				pagination:true,pageSize:20,
				toolbar:'#div_t',
				view:detailview,
				detailFormatter: function(rowIndex, rowData){
					return '<table><tr>'
						+'<td>一月份</td>'
						+'<td>二月份</td>'
						+'<td>三月份</td>'
						+'<td>四月份</td>'
						+'<td>五月份</td>'
						+'<td>六月份</td>'
						+'<td>七月份</td>'
						+'<td>八月份</td>'
						+'<td>九月份</td>'
						+'<td>十月份</td>'
						+'<td>十一月份</td>'
						+'<td>十二月份</td>'
					+'</tr>'
					+'<tr>'
						+'<td>'+rowData.ONE+'</td>'
						+'<td>'+rowData.TWO+'</td>'
						+'<td>'+rowData.THREE+'</td>'
						+'<td>'+rowData.FOUR+'</td>'
						+'<td>'+rowData.FIVE+'</td>'
						+'<td>'+rowData.SIX+'</td>'
						+'<td>'+rowData.SEVEN+'</td>'
						+'<td>'+rowData.EIGHT+'</td>'
						+'<td>'+rowData.NINE+'</td>'
						+'<td>'+rowData.TEN+'</td>'
						+'<td>'+rowData.ELEVEN+'</td>'
						+'<td>'+rowData.TWELVE+'</td>'
					+'</tr>'
					+'</table>';
				}
			">
		<thead>
			<tr>
				<th data-options="field:'TRUENAME',width:100">姓名</th>
				<th data-options="field:'YEAR',width:100">本年年假天数(天)</th>
				<th data-options="field:'NIANJ',width:100">年假(天)</th>
				<th data-options="field:'SHIJ',width:100">事假(天)</th>
				<th data-options="field:'HUNJ',width:100">婚假(天)</th>
				<th data-options="field:'CHANJ',width:100">产假(天)</th>
				<th data-options="field:'BINGJ',width:100">病假(天)</th>
				<th data-options="field:'SANGJ',width:100">丧假(天)</th>
				<th data-options="field:'PEICHANJ',width:100">陪产假(天)</th>
				<th data-options="field:'QT',width:100">其他(天)</th>
				<th data-options="field:'TOTAL',width:100">小计(天)</th>
			</tr>
		</thead>
	</table>
	</body>
</html>
