<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head> 
  <%@ include file="/common/meta.jsp"%>
  <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <title>部门工作月报表</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquery-ui-jquiGui.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/flexigrid/flexigrid.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquiGui.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/test/metroStyle/metroStyle.css" />
    <script src="${ctx}/views/common/js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/json2.js"></script>
	<script src="${ctx}/styles/js/snaker/snaker.util.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx}/views/common/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/views/common/js/flexigrid/flexigrid.js"></script>
	<script type="text/javascript" src="${ctx}/views/common/js/zTree/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${ctx}/views/common/js/zTree/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="${ctx}/views/common/js/zTree/jquery.ztree.exedit.js"></script>
	<style type="text/css">
		.t_full{
			width: 100%;
			height: 100%;
			border: 0px;
			resize:none;
			text-align: center;
		}
				textarea {

line-height: 18px;
    overflow-y: auto;
}
		.div_btn{
			width: 98%;
			margin:0 auto; 
			text-align:center;
		}
		.t_left{
			text-align: left;
		}
		#add_btn{
			width:140px;
			height:23px;
			border-radius:6px;
			outline:none;
			background-color:#31b0d5;
			background:url(./views/common/css/themes/css/flexigrid/images/icon/add.png) no-repeat center left;
		}
	
		.choose_btn{
			width:140px;
			height:23px;
			border-radius:6px;
			outline:none;
			background-color:#31b0d5;
			background:url(./views/common/css/themes/css/flexigrid/images/icon/add.png) no-repeat center left;
		}
		#close{
		display:block;
		width: 20px;
		height: 18px;
		background:url(./views/common/css/themes/css/flexigrid/images/icon/close.png) no-repeat center left;
		}
		.nyr{width:100px}
	</style>
	<script type="text/javascript">
	var page_num = 0;
	var dlg_user;
 	 function sub(){
 	 	 if($("#tbdw").val() == ''){
 	 		showMsg("请选择填报单位");
 	 		return;
 	 	}
 	 	if($("#uid").val() == ''){
 	 		showMsg("请选择考核对象");
 	 		return;
 	 	} 
 	 	getData(); 
          
    } 
    
    function getData(){
    	var tableArray = ['tab','tabGg'];
    	var v = new Array();
    	for(var m = 0; m < tableArray.length ; m++ ){
    		var tr = $("#"+tableArray[m]+" tr");
    		var data = new Array();
    		for (var i = 2; i < tr.length; i++) {
    			var td = $(tr[i]).children("td");
				for (var j = 1; j < 2; j++) {
					if($.trim($(td[j]).children("textarea").val()).length == 0){
						showMsg("内容不能为空");
						return false;	
					}
				}
			}
    		v.push(data);
    	}
    	
    	   $.ajax({ 
           	url:"dcdb!process.action",
           	type:"POST",
           	cache: false,
       		data:$("#inputForm").serialize(),
       		success: function(msg){
       			if("success" === msg)
       			{
   	    				alert("提交成功");	    				
   	    				bakIframe("${map.label}","${map.bakurl}")
       			}
       		}      	
           }); 
    }
    
//     function closeIframe(){
//     	$(window.top.document).find(".ui-tabs-nav > li.ui-state-active > div > span.tabs-close").click();
//     }
  
  
  
       $(function(){
    	   //载入流程图之前要手动隐藏，不然ie加载不完整
       $("#dcdb_process_sig").hide();
       page_num = parseInt($("#page_num").val());
       
       org_Menu.loadTree();
       /*参数管理-查询按钮功能*/
		$('#btn_user_search').button({ icons: {primary:'ui-icon-search'} }).on("click",function(){
			param_user_dcdb.loadData({page:1});
		});
       
     
      
        //选择填报单位
        $("#tbdw_btn").click(function(){
        	var tbdw_dialog = $("#dlg_tbdw").dialog({
        			position: ['left','top'],
        			draggable :false,     
        			autoOpen: false,
					height:"auto",
					width:"auto",
					show: "slide",
					modal:true,
					cache:false,
					/* hide: "explode", */
					title:'部门列表'
        	});
        	tbdw_dialog.dialog("open");
        });
        
        
        var has_dlg_user = function(){
        	param_user_dcdb.loadData({page:1});
       		 create_dlg_user();
       		dlg_user.dialog("option", "title", "选择考核人员").dialog("open");
        }
		 
		 function create_dlg_user(){
			 var dlgParent = $("#dlg_user").parent();
		        var dlgOwn = $("#dlg_user").clone();
		        dlg_user = $('#dlg_user').dialog({
		        	position: ['left','top'],
    			draggable :false,
    			autoOpen: false,
					height:"auto",
					width:"auto",
					show: "slide",
					modal:true,
					cache:false,
					/* hide: "explode", */
					title:'选择考核人员',
					buttons:{
						"保存":function(){
							param_user_dcdb.saveParam();
						}
					},					 
    		});
		 }
        
        //选择考核人
        $("#choose_btn").click(function(){
        	//没选择填报单位，不能选择考核对象
        	if($("#tbdw").val() == ''){
     	 		showMsg("请先选择填报单位");
     	 		return;
     	 	}
        	
        		//初始化所有操作按钮
		 var  parm_button = [];
		 //权限操作按钮
		 var colModel =  [
						{display: '序号', name : 'xh', width: 40, align: 'center'},
						{display: 'id', name : 'ADMIN_ID',width: 40,  align: 'center'},
						{display: '登录账号', name : 'USERNAME',width:50, sortable: true,hide : true},
						{display: '员工姓名', name : 'TRUENAME',width:60, align: 'center',sortable: true},
						{display: '部门名称', name : 'ORG_NAME',width:170,  align: 'center',sortable: true},
						{display: '岗位信息', name : 'ORG_INFOR',width:220, align: 'center'},
						{display: '岗位信息', name : 'JODS_INFOR',width: 100, hide : true}
					];
		 	 			// 初始化数据
		 param_user_dcdb.bindParamGrid(parm_button,colModel);
		 has_dlg_user();
        });
    });
         
		 var param_user_dcdb = {
			//绑定参数信息grid
			bindParamGrid:function(queryP,queryM){
				param_grid_user = $("#user_grid").flexigrid({
					url:"queryUser!queryUser.action?operation=init&department_menu_id="+$("#tbdw_org_id").val(),
					colModel: queryM,
					buttons : queryP,
					rowId:"id",
					showToggleBtn: true,
					usepager: true,
					title:"考核用户列表",
					rp:pageSize,
					rp:page_num,
					showTableToggleBtn: false,
					resizable: false,
					//height: paramGridHeight_lczl,
					height: "240",
					singleSelect: true,
					onChangePage:param_user_dcdb.loadData,
					checkbox:false,
					filterParams : []
				});
			},
			saveParam:function()
			{
				var uid =$('.trSelected').eq(0).find("td:eq(2)").text(); 
				var uname =$('.trSelected').eq(0).find("td:eq(3)").text();
				if(uname == ''){
					showMsg("请选择考核人");
				}else{
					$("#uname").val(uname);
					$("#uid").val(uid);
					$('#dlg_user').dialog('close');				
				}	
			},
			loadData:function(param){
				var queryP = {};
				queryP.username = $('#username').val()==null?'':$('#username').val();
				var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
				queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
				queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
				$.ajax({ 
					type: "POST",
					url: "queryUser!queryUser.action?operation=init&department_menu_id="+$("#tbdw_org_id").val(),
					cache: false,
					data: queryP,
					success: function(msg){
						var data=eval("("+msg+")");
						try{
							param_grid_user.flexAddData(data);
						}catch(e){
							//console.log(e);
							showErrorMsg("查询结果信息出错！");
						}
					}
				}); 
			}
}



//左边树菜单点击事件
var org_menu_event = function(event,treeId,treeNode)
{				//ORG_TYPE为菜单等级
// 			 	if(treeNode.ORG_TYPE == 3){
			 	if(treeNode.SUPER_ORG_ID > 5){
			 		$('#tbdw').val(treeNode.ORG_NAME);
			 		//填报单位id
			 		$('#tbdw_org_id').val(treeNode.ORG_ID);
// 			 		console.log("*******"+treeNode.ORG_ID+"********");
		 			   $('#dlg_tbdw').dialog('close');
			 	}	
			 		   
};
var org_setting = {
		view: {
			dblClickExpand: false
		},
		edit : {
			enable :true,
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
			//onRightClick : treeNodeObj.onRightClick,
			//beforeRemove : treeNodeObj.beforeRename,
			//beforeRename : treeNodeObj.beforeRename,
			//onRename : treeNodeObj.onRename
			onClick:org_menu_event
		}
};

var org_Menu = {
	loadTree : function() {
		orgTree = null;
		$("#organ_table").html("");
		var queryP = {};
		queryP.pagenum = 1;
		queryP.pagesize = 10000;
		$.ajax({
			type: "POST",
			url: "org!toConfigOrg.action?operation=init",
			cache: false,
			data: queryP,
			success: function(msg){
				var data=jQuery.parseJSON(msg);
				orgTree = $.fn.zTree.init($("#organ_table"), org_setting, data);
				orgTree.expandAll(true)
				$("#organ_table > .level0").css("border-bottom",0);
				//orgTree = $.fn.zTree.getZTreeObj("org_tree");
				//rMenu = $("#rMenu");
				//addCount = 1;
			}
		});
	}
}

//表格删除与增加
var deltr =function(index,table){
		 $("tr[id='"+table+index+"']").remove();//删除当前行
		    var tr = $("#"+table+" tr");
		    var len = tr.length - 1 - index;
		    var nextIndex = index + 1;
		    for(var i = 0;i<len;i++){
		       $("#"+table+" tr[id='"+table+Number(nextIndex+i)+"'] td").eq(0).text(Number(nextIndex+i-1));
		       var _td = Number(nextIndex+i-1) * 3 - 2;
		       $("#"+table+" tr[id='"+table+Number(nextIndex+i)+"'] td").eq(1).children("textarea").attr("name",table+"val"+_td);
		       $("#"+table+" tr[id='"+table+Number(nextIndex+i)+"'] td").eq(4).html("<a href='#' id='close' onclick='deltr("+Number(nextIndex+i-1)+",&quot;"+table+"&quot;)'></a>");
		       $("#"+table+" tr[id='"+table+Number(nextIndex+i)+"']").attr("id",table+(nextIndex+i-1));
		    }
};
		 


   //增加<tr/>add_btn
var addtr = function(table){
    var _len = $("#"+table+" tr").length - 2;
    var status = Number(_len + 1);
    var _td = status * 3 - 2;
    $("#"+table).append("<tr id="+table+status+" align='center'>"
    				+"<td class='h50'>"+status+"</td>"
                    +"<td class='h50'><textarea name='"+table+"val"+_td+"' maxlength='5000' onchange='this.value=this.value.substring(0, 5000)' onkeydown='this.value=this.value.substring(0, 5000)' onkeyup='this.value=this.value.substring(0, 5000)'  class='t_full'></textarea></td>"
                    +"<td class='h50'><textarea class='t_full'  readonly='readonly' id='desc"+_len+"1' placeholder='不可填'></textarea></td>"
                    +"<td class='h50'><textarea class='t_full' readonly='readonly' id='desc"+_len+"2' placeholder='不可填'></textarea></td>"
                    +"<td class='h50'><a href=\'#\' id='close' onclick=\'deltr("+status+",&quot;"+table+"&quot;)\'></a></td>"
                    +"</tr>");        
   }
   
</script>	
  </head>
  <body style="overflow-y: auto;">
  <input type="hidden" value="${step}" id="step">
  <input type="hidden" id="page_num" name="page_num" value="10"/>
  <form id="inputForm" action="" method="post" target="mainFrame">
 	<input type="hidden" id="processId" name="processId" value="${map.processId}" />
	<input type="hidden" id="orderId" name="orderId"  value="${map.orderId}" />
	<input type="hidden" id="taskId" name="taskId" value="${map.taskId}" />
	<input type="hidden" id="uid" name="uid" value="" />
	<input type="hidden" id="jsonV" name="jsonV"/>
	
	<p class="btt">机关事业单位目标管理责任制完成情况督查表</p>
<table style="width:98%; margin-top:20px;" cellpadding="0" cellspacing="0">
<tr>
 <td  style=" text-align:left; color: red;">填报单位：<input type="text"  readonly="readonly" id="tbdw" name="tbdw"  class="nyr"/></td>
  <td  style=" text-align:left;  color: red; ">考核对象：<input type="text"  readonly="readonly" id="uname" class="nyr"/></td>
  
  <!-- 填报单位id -->
  <input type="hidden" id="tbdw_org_id"/>
  
 <td style=" text-align:right">日期:
 	<input type="text"  readonly="readonly" value="${map.year}" class="nyr"/>年 
 	<input type="text"  readonly="readonly" value="${map.month}" class="nyr"/>月
 	<input type="text"  readonly="readonly" value="${map.day}" class="nyr"/>日</td>
</tr>
</table>
<br/>
<div class="div_btn t_left">
	<input type="button" id="tbdw_btn" class="choose_btn" value="选择填报单位"/>
	<input type="button" id="choose_btn" class="choose_btn" value="选择考核对象"/>
</div>
<table  id="tab" class="table-all" cellspacing="0" align="center" style="margin-top:5px;background: #d0e9fe; ">
 <tr>
 	<td class="h30">序号</td>
	<td class="h30">目标考核内容</td>
    <td class="h30">工作目标任务开展及完成情况说明</td>
    <td class="h30">备注</td>
    <td class="h30">操作</td>
 </tr>
 <tr>
	<td class="h30" colspan="5" style="color: red;">业务类（70分）</td>
 </tr>
  <tr id="tab1" align="center">
 	<td class="h50">1</td>
 	<td class="h50"><textarea name="tabval1"  maxlength='5000' onchange='this.value=this.value.substring(0, 5000)' onkeydown='this.value=this.value.substring(0, 5000)' onkeyup='this.value=this.value.substring(0, 5000)'  class="t_full" id="desc00"></textarea></td>
 	<td class="h50"><textarea class="t_full" id="desc01" readonly="readonly" placeholder="不可填"></textarea></td>
 	<td class="h50"><textarea class="t_full" id="desc02" readonly="readonly" placeholder="不可填"></textarea></td>
 	<td class="h50"><a href="#" id="close" onclick="deltr(1,'tab')"></a></td>
 </tr>
</table>
<div class="div_btn t_left">
	<input type="button" id="add_btn" value="新增考核内容" onclick="addtr('tab')"/>
</div>


<table  id="tabGg" class="table-all" cellspacing="0" align="center" style="margin-top:5px;background: #d0e9fe; ">
 <tr>
 	<td class="h30">序号</td>
	<td class="h30">目标考核内容</td>
    <td class="h30">工作目标任务开展及完成情况说明</td>
    <td class="h30">备注</td>
    <td class="h30">操作</td>
 </tr>
 <tr>
	<td class="h30" colspan="5" style="color: red;">公共类（30分）</td>
 </tr>
  <tr id="tabGg1" align="center">
 	<td class="h50">1</td>
 	<td class="h50"><textarea name="tabGgval1"  maxlength='5000' onchange='this.value=this.value.substring(0, 5000)' onkeydown='this.value=this.value.substring(0, 5000)' onkeyup='this.value=this.value.substring(0, 5000)' class="t_full" id="desc00"></textarea></td>
 	<td class="h50"><textarea class="t_full" id="desc01" readonly="readonly" placeholder="不可填"></textarea></td>
 	<td class="h50"><textarea class="t_full" id="desc02" readonly="readonly" placeholder="不可填"></textarea></td>
 	<td class="h50"><a href="#" id="close" onclick="deltr(1,'tabGg')"></a></td>
 </tr>
</table>
<div class="div_btn t_left">
	<input type="button" id="add_btn" value="新增考核内容" onclick="addtr('tabGg')"/>
</div>

<table align="center" border="0" cellpadding="0"
				cellspacing="0">
</table>
<p></p>
<div class="div_btn">
	<input type="button" class="selectBtn"  value="提交" onclick="sub();"/>
						&nbsp;&nbsp;
						<input type="button" class="selectBtn" name="reback" value="返回"
							onclick="bakIframe('${map.label}','${map.bakurl}')">
						&nbsp;&nbsp;	
						<input type="button" class="selectBtn"  value="查看流程图"
							onclick="showProcess_sig('dcdb_process_sig','流程查看','${map.processId}','${map.orderId}',0,490,630)"/>
</div>
</form> 

<div id="dcdb_process_sig">
	<%-- <iframe width=630 height=460 frameborder=0 src="${ctx}/process/designer?processId=${map.processId}&status=0&orderId=${map.orderId}&orderIdStatus=0"></iframe> --%>
</div>

	<div  id="dlg_user" style="overflow:auto;display:none;">
		<div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1" >
				<tr>
					<td ><label for="input_1" class="lbl-1">员工姓名：</label></td>
					<td class="wi-align-l" >
						<input type="text" id="username" name="username" class="wi-ipt-1" />
					    <input type="hidden" id="department_id_tmp" />
					</td>
					<!-- 
					<td style="width:100px;"><label for="input_1" class="lbl-1">手机号码：</label></td>
					<td class="wi-align-l" style="width:170px;">
						<input type="text" id="mobile" name="mobile" class="wi-ipt-1" />
					</td>
					 -->
					<td class="wi-align-l"><button id="btn_user_search" style="width:74px; height:29px;font-size:14px;background-color:#3ea4e8;color: #fff">查询</button></td>
				</tr>
			</table>
		</div> 
		<div class="div div-2" style="overflow:hidden;height:350px;">
		<table id="user_grid" style="display:none;padding:0 0 0 0;"></table>
		</div>
	</div>
	
	
	<div id="dlg_tbdw" style="overflow:auto;display:none;">
		<div id="organ_table" class="ztree">
		</div>
	</div>
  </body>
</html>
