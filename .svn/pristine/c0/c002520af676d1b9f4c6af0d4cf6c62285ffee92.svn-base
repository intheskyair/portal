<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/commonCSSJS.jsp"%>
<html>
    <head>	
	<title>人员年假设置</title>
		<script>x=-33;xx=0;</script>
	</head>
	<body>	
	<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='images/fold.png' onclick="showAndHide(this,'leave_grid');"  class="imgShrotate">
				<font>人员年假设置</font>
			</h3>
		</div>
	<div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>
					<td class="wi-align-l" ><label for="input_1" class="lbl-1">姓名：</label>
						<input type="text" id="truename" name="truename" class="wi-ipt-1" />
					</td>
					<td class="ui-button-text"><a href="#" id="btn_mzpc_search">查询</a><button id="btn_mzpc_empty" class="ui-state-default ui-state-qik">清空</button></td>
				</tr>
			</table>
		</div>
	</div>
	<div class="div div-2">
		<table id="leave_grid" style="display:none;padding:0 0 0 0;"></table>
	</div>	
	<div id="leave_List" style="display:none">
		<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
			<tr>
				<td style="width:100px;"><label style="color:red;">*</label><label for="input_1">年假天数：</label></td>
				<td class="wi-align-l" style="width:300px;">
					<input type="hidden" id="uid">
					<input type="text" id="year" name="year" class="wi-ipt-1"/>天
					<span class="LV_invalid none" style="color: red;"></span>
				</td>
				
			</tr>
			</table>
	</div>
	   
		<div class="modal fade" id="departMent" tabindex="-1" style="display: none" >
		   <div class="modal-dialog" style="width: 350px;">
		      <div class="modal-content">
		        
		        <div id="menuContent" style="overflow-y:auto;">
					<ul id="treeDemo" class="ztree" style="margin-top:0;background: #f0f6e4;overflow-y:scroll;overflow-x:auto;"></ul>
				</div>
		         <div class="modal-footer">
		         </div>
		      </div>
		   </div>
		</div>
	   
	   
	   
		<script type="text/javascript">		
		 var data_all;
		 var paramGridHeight_lczl = flexHeight("leave_grid");
		 var param_grid_mzpc;
		 var menuTree;
		
		 var param_lczl = {
			//绑定参数信息grid
			leave_List:"",
			page_num:30,
			bindParamGrid:function(queryP,queryM){
				param_grid_user = $("#leave_grid").flexigrid({
					url:"leave!leaveManager.action?operation=init",
					colModel: queryM,
					buttons : queryP,
					rowId:"id",
					showToggleBtn: true,
					usepager: true,
					title:"人员年假设置",
					//rp:pageSize,
					rp:param_lczl.page_num,
					showTableToggleBtn: false,
					resizable: false,
					height: paramGridHeight_lczl,
					singleSelect: true,
					onChangePage:param_lczl.loadData,
					checkbox:false,
					filterParams : [{key : "truename", name : "#truename"}]
				});
			},
			loadData:function(param){
				var queryP = {};
				queryP.truename = $('#truename').val()==null?'':$('#truename').val();
				var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
				queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
				queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
				$.ajax({
					type: "POST",
					url: "leave!leaveManager.action?operation=init",
					cache: false,
					data: queryP,
					success: function(msg){
						var data=eval("("+msg+")");
						try{
							param_grid_user.flexAddData(data);
						}catch(e){
							showErrorMsg("查询结果信息出错！");
						}
					}
				}); 
			},
			saveParam:function(){
				var queryP = {};
				queryP.year = $("#year").val();
				queryP.uid = $("#uid").val();
				//console.log(queryP);
				 var   reg =/^[0-9]*[1-9][0-9]*$/;
				 if(!reg.test(queryP.year)){
					 $("#year").nextAll(".LV_invalid").removeClass("none").html("请输入正整数");
					return;
				 }else
				{
				 $("#year").nextAll(".LV_invalid").addClass("none").html(""); 
				}
				$.ajax({
					type: "POST",
					url: "leave!saveParam.action",
					cache: false,
					data: queryP,
					success: function(msg){
						if (msg=="success") 
						{
							showMsg("操作成功");
							$('#leave_List').dialog('close');
							param_lczl.loadData({});
						} else 
						{
							showMsg("操作失败,请联系管理员");
						}
					}
				}); 
			}
			,	
			//个人流程查询
			personDetails : function(com, grid) {
		        var dlgParent = $("#leave_List").parent();
		        var dlgOwn = $("#leave_List").clone();
				param_lczl.leave_List = $("#leave_List").dialog({
					autoOpen: false,
					height:$(window).height()*0.8,
					width:$(window).width()*0.5,
					show: "slide",
					modal:true,
					cache:false,
					/* hide: "explode", */
					title:'考核详情',
		            close : function() {
		            	dlgOwn.appendTo(dlgParent);
		            	$(this).dialog("destroy").remove();
		            },
					buttons:{
						"保存":function(){
							param_lczl.saveParam();
						}
					}
				});
				
			
					if($('.trSelected', grid).length==1)
					{
						var uid = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
						var year = $('.trSelected', grid).eq(0).find("td:eq(5)").text();
						$("#uid").val(uid);
						$("#year").val(year);
						param_lczl.leave_List.dialog("open");
					}
					else if($('.trSelected', grid).length > 1)
					{
						$('#leave_List').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
						$('#leave_List').dialog('close');
						showMsg("未选中行！");
					}
				
				$("#leave_List").dialog("open");	
			}
		};
 
	 $(function(){
		//初始化所有操作按钮
		 var  parm_button = [
		 	{name: '编辑', bclass: 'edit', onpress : param_lczl.personDetails}
		 ];
		 //权限操作按钮
		 var colModel = [
		                {display: '序号', name : 'xh', width : 40, align: 'center'},
		                {display: 'ID', name : 'ADMIN_ID', align: 'center',hide : true,sortable: true},
		                {display: '姓名', name : 'TRUENAME',  align: 'center',width : 100,sortable: true},
						{display: '组织', name : 'ORG_NAME', width : 350, align: 'center',sortable: true},
						{display: '职务', name : 'ORG_INFOR', width : 150, align: 'center',sortable: true},
						{display: '年假时间(天)', name : 'YEAR', width : 150, align: 'center',sortable: true},
						{display: '已使用年假(天)', name : 'USED', width : 150,  align: 'center',sortable : true},
						{display: '年度累计(天)', name : 'ALL_TIME', width : 150,  align: 'center',sortable: true}	 				
						];
		 	 			// 初始化数据
		 param_lczl.bindParamGrid(parm_button,colModel);
		/*参数管理-查询按钮功能*/
		$('#btn_mzpc_search').button({ icons: {primary:'ui-icon-search'} }).on("click",function(){
			param_lczl.loadData({page:1});
		});

		//清空筛选框
		$('#btn_mzpc_empty').on("click",function(){
			$("#truename").val("");
		});
	});	
	 
	 
	 
	
		</script>
	</body>
</html>
