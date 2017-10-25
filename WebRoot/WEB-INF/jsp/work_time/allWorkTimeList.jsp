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
	<script type="text/javascript" src="styles/easyui/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/test/metroStyle/metroStyle.css" />
 	<script type="text/javascript" src="${ctx}/views/common/js/zTree/jquery.ztree.core.js"></script>
 	<script type="text/javascript" src="${ctx}/views/common/js/zTree/jquery.ztree.excheck.js"></script>
	<title>考勤列表</title>
	<script>
	
	//左边树菜单点击事件
	var org_menu_event = function(event,treeId,treeNode){
	//alert(JSON.stringify(treeNode.ORG_ID));
		var orgId= treeNode.ORG_ID;
		//$("#kq_data").datagrid('loadData',[{"orgId":orgId}]);
		$("#orgId").val(orgId);
		$('#kq_data').datagrid({
    			queryParams:{
    				"orgId":orgId
    			}
    		});
	};
	var org_setting = {
		view: {
			dblClickExpand: false
		},
		edit : {
			enable :false,
			showRemoveBtn : false,
			showRenameBtn : false
		},
		data: {
			simpleData: {
				enable: true,
				idKey : "ORG_ID",
				pIdKey : "SUPER_ORG_ID",
				rootPID : 0
			},
			key : {
				name: "ORG_NAME",
			}
		},
		callback:{
			onClick:org_menu_event
			//beforeClick: zTreeBeforeClick
		}
	};
	
// 	function zTreeBeforeClick(treeId, treeNode, clickFlag) {
// 	    return !treeNode.isParent;//当是父节点 返回false 不让选取
// 	}
	var org_Menu = {
		loadTree : function() {
			orgTree = null;
			$("#organ_table_all_log").html("");
			var queryP = {};
			queryP.pagenum = 1;
			queryP.pagesize = 10000;
			$.ajax({
				type: "POST",
				url: "org!toConfigOrg1.action?operation=init&orgId=${admin.org_id}",
				cache: false,
				data: queryP,
				success: function(msg){
					var data=jQuery.parseJSON(msg);
					orgTree = $.fn.zTree.init($("#organ_table_all_log"), org_setting, data);
					//展开所有
					var treeObj = $.fn.zTree.getZTreeObj("organ_table_all_log");
					treeObj.expandAll(false);
					
					$("#organ_table_all_log > .level0").css("border-bottom",0);
				}
			});
		}
	}
		$(function(){
 			org_Menu.loadTree();
			$.extend($.fn.datagrid.methods, {

			    autoMergeCells: function (jq, fields) {

			        return jq.each(function () {

			            var target = $(this);

			            if (!fields) {

			                fields = target.datagrid("getColumnFields");

			            }

			            var rows = target.datagrid("getRows");

			            var i = 0,

			            j = 0,

			            temp = {};

			            for (i; i < rows.length; i++) {

			                var row = rows[i];

			                j = 0;

			                for (j; j < fields.length; j++) {

			                    var field = fields[j];

			                    var tf = temp[field];

			                    if (!tf) {

			                        tf = temp[field] = {};

			                        tf[row[field]] = [i];

			                    } else {

			                        var tfv = tf[row[field]];

			                        if (tfv) {

			                            tfv.push(i);

			                        } else {

			                            tfv = tf[row[field]] = [i];

			                        }

			                    }
			                }

			            }

			            $.each(temp, function (field, colunm) {

			                $.each(colunm, function () {

			                    var group = this;

			 

			                    if (group.length > 1) {

			                        var before,

			                        after,

			                        megerIndex = group[0];

			                        for (var i = 0; i < group.length; i++) {

			                            before = group[i];

			                            after = group[i + 1];

			                            if (after && (after - before) == 1) {

			                                continue;

			                            }

			                            var rowspan = before - megerIndex + 1;

			                            if (rowspan > 1) {

			                                target.datagrid('mergeCells', {

			                                    index: megerIndex,

			                                    field: field,

			                                    rowspan: rowspan

			                                });

			                            }

			                            if (after && (after - before) != 1) {

			                                megerIndex = after;

			                            }

			                        }

			                    }

			                });

			            });

			        });

			    }

			});
			
			
			
			$("#dstartTime").datebox({
				editable : false,
			            onShowPanel: function () {//显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
			                span.trigger('click'); //触发click事件弹出月份层
			                if (!tds) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
			                    tds = p.find('div.calendar-menu-month-inner td');
			                    tds.click(function (e) {
			                        e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
			                        var year = /\d{4}/.exec(span.html())[0]//得到年份
			                        , month = parseInt($(this).attr('abbr'), 10); //月份，这里不需要+1
			                        $('#dstartTime').datebox('hidePanel')//隐藏日期对象
			                        .datebox('setValue', year + '-' + month); //设置日期的值
			                    });
			                }, 0)
			            },
			            parser: function (s) {
			                if (!s) return new Date();
			                var arr = s.split('-');
			                return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
			            },
			            formatter: function (d) { 	            	
			            	return d.getFullYear() + '-' + (d.getMonth()<9?"0"+(d.getMonth()+1):d.getMonth()+1);/*getMonth返回的是0开始的*/ },
			            onChange:function(value){
							
			            }
			            
				});
				
		        var p = $('#dstartTime').datebox('panel'), //日期选择对象
	            tds = false, //日期选择对象中月份
	            span = p.find('span.calendar-text'); //显示月份层的触发控件
	            
	            var date=new Date();//
                 var year = date.getFullYear();
                 var month = date.getMonth()+1;
                 var day = date.getDate();
                 if (month < 10) {
                     month = "0" + month;
                 }
                 if (day < 10) {
                     day = "0" + day;
                 }
                 var time= year+"-"+month;
                 $('#dstartTime').datebox('setValue',time);
	            
		});
		
		  //搜索
    	function loadsea(){
    		$('#kq_data').datagrid({
    			queryParams:{
    				"startTime":$("#dstartTime").datebox('getValue'),
    				"username":encodeURI($("#username").val()),
    				"orgId":$("#orgId").val()
    			}
    		});
    	}
		
		function onLoadSuccess(){
			$("#kq_data").datagrid("autoMergeCells", ['name']);
		}
		function fixWidth(percent)     
		{     
		    return (document.body.clientWidth - 5) * percent ;      
		}
		function forData(value,row,index){
			if(value == "-1"){
				return "<font style='font-size:20px;'>√</font>";
			}else if(value == 1){
				return "<font style='font-size:20px;color:blue'>★</font>";
			}else if(value == 2){
				return "<font style='font-size:20px;color:red'>※</font>";
			}else if(value == 3){
				return "<font style='font-size:20px;color:red'>△</font>";
			}else if(value == 4){
				return "<font style='font-size:20px;color:red'>●</font>";
			}else if(value == 5){
				return "<font style='font-size:20px;color:red'>×</font>";
			}			
		}
		
$(function(){
     sety1();
	$(window).resize(function() {
  		sety1();
	});
});
function sety1(){
	var iy = $(window).height();
	//alert(iy);
	$(".ztree").height(iy-56);
	//alert(iy);
}		
	</script>
	</head>
	<body>	
	
	<input type="hidden" id="orgId"/>
	<div class="div div-2"  style="width:240px;position: absolute">
		<div class="div-main-1 div-height">
			<span style="font-weight: bold;">部门列表</span>
		</div>
		<div id="organ_table_all_log" class="ztree" style="overflow: auto;">
		</div>
	</div>
	
		<div id="div_t" class="datagrid-toolbar" style="padding:5px;height:auto;">
				        月份: <input class="easyui-datebox" id="dstartTime"   style="width:100px">
				   姓名:     <input class="easyui-textbox" id="username"  style="width:80px">
				         <a href="javascript:;" onclick="loadsea()" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
		</div>	


		<div style="margin-left:245px;height:100%">
		<table id="kq_data" class="easyui-datagrid" title="考勤汇总&nbsp;&nbsp;&nbsp;&nbsp;(说明：出勤 √，星期天或节日<span style='color:blue'>★</span>，公休假<span style='color:red'>※</span>，事假<span style='color:red'>△</span>，病假<span style='color:red'>●</span>，旷工<span style='color:red'>×</span>)" style="width:100%;height:100%"
			data-options="
				rownumbers: true,
				singleSelect: true,
				iconCls: 'icon-save',
				url: 'worklog!timeTable.action',
				method: 'get',
				onLoadSuccess: onLoadSuccess,
				pagination:true,pageSize:20,
				toolbar:'#div_t'
			">
		<thead>
			<tr>
				<th data-options="field:'name',width:fixWidth(0.04)">姓名</th>
				<th data-options="field:'anpm',width:fixWidth(0.025)">时段</th>
				<th data-options="field:'key1',width:fixWidth(0.025),align:'right',formatter:forData,formatter:forData">1</th>
				<th data-options="field:'key2',width:fixWidth(0.025),align:'right',formatter:forData">2</th>
				<th data-options="field:'key3',width:fixWidth(0.025),align:'right',formatter:forData">3</th>
				<th data-options="field:'key4',width:fixWidth(0.025),align:'right',formatter:forData">4</th>
				<th data-options="field:'key5',width:fixWidth(0.025),align:'right',formatter:forData">5</th>
				<th data-options="field:'key6',width:fixWidth(0.025),align:'right',formatter:forData">6</th>
				<th data-options="field:'key7',width:fixWidth(0.025),align:'right',formatter:forData">7</th>
				<th data-options="field:'key8',width:fixWidth(0.025),align:'right',formatter:forData">8</th>
				<th data-options="field:'key9',width:fixWidth(0.025),align:'right',formatter:forData">9</th>
				<th data-options="field:'key10',width:fixWidth(0.025),align:'right',formatter:forData">10</th>
				<th data-options="field:'key11',width:fixWidth(0.025),align:'right',formatter:forData">11</th>
				<th data-options="field:'key12',width:fixWidth(0.025),align:'right',formatter:forData">12</th>
				<th data-options="field:'key13',width:fixWidth(0.025),align:'right',formatter:forData">13</th>
				<th data-options="field:'key14',width:fixWidth(0.025),align:'right',formatter:forData">14</th>
				<th data-options="field:'key15',width:fixWidth(0.025),align:'right',formatter:forData">15</th>
				<th data-options="field:'key16',width:fixWidth(0.025),align:'right',formatter:forData">16</th>
				<th data-options="field:'key17',width:fixWidth(0.025),align:'right',formatter:forData">17</th>
				<th data-options="field:'key18',width:fixWidth(0.025),align:'right',formatter:forData">18</th>
				<th data-options="field:'key19',width:fixWidth(0.025),align:'right',formatter:forData">19</th>
				<th data-options="field:'key20',width:fixWidth(0.025),align:'right',formatter:forData">20</th>
				<th data-options="field:'key21',width:fixWidth(0.025),align:'right',formatter:forData">21</th>
				<th data-options="field:'key22',width:fixWidth(0.025),align:'right',formatter:forData">22</th>
				<th data-options="field:'key23',width:fixWidth(0.025),align:'right',formatter:forData">23</th>
				<th data-options="field:'key24',width:fixWidth(0.025),align:'right',formatter:forData">24</th>
				<th data-options="field:'key25',width:fixWidth(0.025),align:'right',formatter:forData">25</th>
				<th data-options="field:'key26',width:fixWidth(0.025),align:'right',formatter:forData">26</th>
				<th data-options="field:'key27',width:fixWidth(0.025),align:'right',formatter:forData">27</th>
				<th data-options="field:'key28',width:fixWidth(0.025),align:'right',formatter:forData">28</th>
				<th data-options="field:'key29',width:fixWidth(0.025),align:'right',formatter:forData">29</th>
				<th data-options="field:'key30',width:fixWidth(0.025),align:'right',formatter:forData">30</th>
				<th data-options="field:'key31',width:fixWidth(0.025),align:'right',formatter:forData">31</th>
		
				
			</tr>
		</thead>
	</table>
	</div>
	</body>
</html>
