<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>个人流程查询</title>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/commonCSSJS.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css"/>
<link rel="stylesheet" href="${ctx}/css/common.css"></link>
<script>x=-33;</script>
</head>
<body>
	<div class="div div-1">
		<div class="title title-1">
		    <h3>
			<img src='images/fold.png'
				onclick="showAndHide(this,'plans_grid');" class="imgShrotate">
			</h3>查询条件</h3>
		</div>
		<div class="div-main-1">
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>
					<td class="wi-align-l">
					<label for="input_1" class="lbl-1">流程标题：</label>
                    <input type="text" id="personal_process_top" name="personal_process_top" class="wi-ipt-1" /></td>
					<td class="wi-align-l"><label for="input_1" class="lbl-1">开始时间：</label>
					<input type="text" id="process_invoice_preDate" name="process_invoice_preDate" readonly class="wi-ipt-i"
						onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
						
						<label for="input_1" class="lbl-1">&nbsp;至&nbsp;</label>
						<input type="text" id="process_invoice_postDate"
						name="process_invoice_postDate" readonly class="wi-ipt-i"
						onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
					</td>
					<td class="wi-align-l"><button id="btn_mzpc_search_gr"
							class="ui-state-default ui-state-qik">查询</button><button id="btn_mzpc_empty_gr"
							class="ui-state-default ui-state-qik">清空</button></td>
				</tr>
			</table>
		</div>
	</div>
	<div class="div div-2">
		<table id="plans_grid" style="display: none; padding: 0 0 0 0;"></table>
	</div>
	<div id="dlg_rechargeList" style="display:none;    overflow: scroll;">
		<div class="outer">
			<ul class="tab">
				<li class="current">流程详情</li>
				<li>查看表单</li>

			</ul>

			<div class="content_y">
						<ul>
					<li class="current">
						<div id="dlg_perprocess_flow"></div>
					</li>
				</ul>
				<ul style="display:none">
					<li>
						<div id="dlg_perprocess_form"></div>
					</li>
				</ul>

			</div>
		</div>
	</div>

	<script>
		var manualcplansGridHeight = flexHeight("plans_grid");
		var plans_grid;
		var dlg_plansManage;
		var manualc_plans = {
			dlg_rechargeList : "",
			page_num : 30,
			//绑定参数信息grid
			bindplansManualcGrid : function(queryP) {
				plans_grid = $("#plans_grid")
						.flexigrid(
								{
									url : "personalProcess!personalProcessList.action?operation=init",
									colModel : [ {
										display : '序号',
										name : 'xh',
										width : 40,
										align : 'center'
									}, {
										display : 'id',
										name : 'ID',
										width : 40,
										align : 'center',
										hide : true
									}, {
										display : 'PID',
										name : 'PID',
										width : 100,
										align : 'center',
										hide : true,
										sortable : true
									}, {
										display : '流程名称',
										name : 'PROCESS_NAME',
										width : 150,
										align : 'center',
										sortable : true
									}, {
										display : '标题',
										name : 'TITLE',
										width : 200,
										align : 'center',
										sortable : true
									}, {
										display : '创建人',
										name : 'CREATOR',
										width : 90,
										align : 'center',
										sortable : true
									}, {
										display : '流程编号',
										name : 'PROCESS_ID',
										width : 335,
										align : 'center',
										hide : true
									}, 
									{display: '开始时间', name : 'CREATE_TIME', width : 150, align: 'center',sortable: true},
									{display: '结束时间', name : 'END_TIME', width : 150,  align: 'center',sortable : true,
									process:function(obj,pid)
										{	
											if ($.trim($(obj).html()) == "&nbsp;" || $.trim($(obj).html()) == "null") 
											{
											   $(obj).html(" ----");
											   $(obj).css({color:"red"});
											}
										}},
									{display: '状态', name : 'ORDER_STATE', width : 150,  align: 'center',sortable: true,						
										process:function(obj,pid)
										{
											if ($(obj).html() == "0") 
											{
											   $(obj).html("已结束");
											   $(obj).css({color:"blue"});
											}
											else 
											{
												if ($(obj).html() == "0") 
												{
												   $(obj).html("已结束");
												   $(obj).css({color:"blue"});
												}
												else 
												{
													$(obj).html("进行中");
													$(obj).css({color:"red"});								
												}
										    }					
										}},
										{display: '下一步流程', name : 'NEXTDISPLAYNAME', width : 150,  align: 'center',sortable: true,
										process:function(obj,pid)
											{	
												if ($.trim($(obj).html()) == "&nbsp;") 
												{
												   $(obj).html(" ----");
												   $(obj).css({color:"blue"});
												}
											}},
										{display: '表单查询', name : 'PATH', hide: true, width : 150,  align: 'center',sortable: true,
										}],
									buttons : queryP,
									rowId : "id",
									showToggleBtn : true,
									usepager : true,
									title : "个人流程查询",
									rp : manualc_plans.page_num,
									showTableToggleBtn : false,
									resizable : false,
									height : manualcplansGridHeight,
									singleSelect : true,
									checkbox : false,
									onChangePage : manualc_plans.loadData,
									filterParams : [ {
										key : "personalTop",
										name : "#personal_process_top"
									}, {
										key : "startTime",
										name : "#process_invoice_preDate"
									}, {
										key : "endTime",
										name : "#process_invoice_postDate"
									} ]
								});
			},
			loadData : function(param) {
				var queryP = {};
				queryP.personalTop = $('#personal_process_top').val() == null ? ''
						: $('#personal_process_top').val();
				queryP.startTime = $('#process_invoice_preDate').val() == null ? ''
						: $('#process_invoice_preDate').val();
				queryP.endTime = $('#process_invoice_postDate').val() == null ? ''
						: $('#process_invoice_postDate').val();
				var selectedGridDiv = $("div.flexigrid .pDiv .pDiv2 .pGroup");
				//选择所有的select 的子元素中被selected的元素 
				//alert($("select option:selected").html());
				queryP.pagenum = param.page ? param.page : $('.pcontrol input',
						selectedGridDiv).val();
                queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
				$
						.ajax({
							type : "POST",
							url : "personalProcess!personalProcessList.action?operation=init",
							cache : false,
							data : queryP,
							success : function(msg) {
								var data = eval("(" + msg + ")");
								try {
									plans_grid.flexAddData(data);
								} catch (e) {
									showErrorMsg("查询套餐信息出错！");
								}
							}
						});
			},

			//个人流程查询
			personDetails1 : function(com, grid) {
				$("#dlg_perprocess_form").html("");
				$("#dlg_perprocess_flow").html("");

				if ($('.trSelected', grid).length == 1) {
					var id = $('.trSelected', grid).eq(0).find("td:eq(1)")
							.text();
					var pid = $('.trSelected', grid).eq(0).find("td:eq(2)")
							.text();
					var path = $('.trSelected', grid).eq(0).find("td:eq(11)")
							.text();
					var url = '';
					var data = {};
					for (var i = 0; i < 2; i++) {
						if (i == 0) {
							com = '查看表单';
						} else {
							com = '流程详情';
						}

						if (com == '查看表单') {
							url = "borrow!view.action";
							data = {
								"orderId" : id,
								"processId" : pid,
								"path" : path
							};

						} else {
							url = "lczl!viewStep.action";
							data = {
								"orderId" : id
							};

						}
						$.ajax({
									type : "POST",
									data : data,
									cache : false,
									async : false,
									url : url,
									success : function(result) {
										if (com == '流程详情') 
										{
										    var result_str =  createHtml(result);
											$("#dlg_perprocess_flow").append(result_str);
										} 
										else 
										{
											$("#dlg_perprocess_form")
													.append(result);
										}
									},
									error : function(result) {
										$("#dlg_perprocess_form")
												.append(
														"<span style='font-size:14px;color:red'>数据出错，未查到相应流程！</span>");
									}
								});

					}
					openDialog1(com);
				} else if ($('.trSelected', grid).length > 1) {
					$('#dlg_rechargeList').dialog('close');
					showMsg("不能同时选择多行记录！");
				} else {
					$('#dlg_rechargeList').dialog('close');
					showMsg("未选中行！");
				}
			},

			// 验证规则
			validate : function() {
				var flg = true;
				$("#dlg_plansManage input").each(function() {
					if (!$(this).validate())
						flg = false;
				});
				return flg;
			}
		}

		var openDialog1 = function openDia1(com) {
			var dlgParent = $("#dlg_rechargeList").parent();
			var dlgOwn = $("#dlg_rechargeList").clone();
			manualc_plans.dlg_rechargeList = $("#dlg_rechargeList").dialog({
				position : [ 'center', 'center' ],
				overflow : "auto",
				autoOpen : false,
				height:$(window).height()*0.8,
				width:$(window).width()*0.6,
				show : "slide",
				modal : true,
				cache : false,
				/* hide: "explode", */
				title : com,
				close : function() {
					dlgOwn.appendTo(dlgParent);
					$(this).dialog("destroy").remove();
				}
			});

			$("#dlg_rechargeList .tab li").click(function() 
			{
				$("#dlg_rechargeList .tab li").removeClass("current");

				$(this).addClass("current");
				var n = $(this).index();

				$("#dlg_rechargeList .content_y ul").hide();
				$("#dlg_rechargeList .content_y ul").eq(n).show();

			});
			$("#dlg_rechargeList").dialog("open");
		}
		$(function() {
			//初始化所有操作按钮
			var parm_button = [ {
				name : '查看',
				bclass : 'search',
				onpress : manualc_plans.personDetails1
			} ];
			//权限操作按钮
			var buttons = [];

			//获得当前菜单的id
			var menu_id = null;
			$('#tabs ul li').each(
					function() {
						if ($(this).hasClass("ui-tabs-selected")) {
							var this_id = $(this).children("div").children("a")
									.attr("href");
							menu_id = this_id.substring(5);
						}
					});

			// 根据菜单id，初始化操作按钮
			$.ajax({
				type : "GET",
				url : "doauth!getButtons.action?menu_id=" + menu_id,
				cache : false,
				success : function(data) {
					var res = eval("(" + data + ")");
					for ( var i in res.buttons) {
						var buttonName = res.buttons[i].BUTTON_NAME;
						$(parm_button).each(function(index) {
							var val = parm_button[index].name;
							if (val == buttonName) {
								buttons.push(parm_button[index]);
								buttons.push({
									separator : true
								});
							}
						});
					}
					// 初始化数据
					manualc_plans.bindplansManualcGrid(parm_button);
				}
			});

			/*参数管理-查询按钮功能*/
			$('#btn_mzpc_search_gr').button({
				icons : {
					primary : 'ui-icon-search'
				}
			}).on("click", function() {
				//时间判断
				var stime = $("#process_invoice_preDate").val();
				var etime = $("#process_invoice_postDate").val();
				var d1 = new Date(stime.replace(/\-/g, "\/"));
				var d2 = new Date(etime.replace(/\-/g, "\/"));
				if (stime != "" && etime != "" && d1 >= d2) {
					showMsg("开始时间不能大于结束时间！");
					return;
				}
				manualc_plans.loadData({
					page : 1
				});
			});

			//清空筛选框
			$('#btn_mzpc_empty_gr').on("click", function() {
				$("#personal_process_top").val("");
				$("#process_invoice_preDate").val("");
				$("#process_invoice_postDate").val("");

			});
		});
	</script>
</body>
</html>