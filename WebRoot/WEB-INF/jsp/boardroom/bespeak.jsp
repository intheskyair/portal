<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/commonCSSJS.jsp"%>
<link rel="stylesheet" type="text/css"
	href="views/common/css/themes/css/test/metroStyle/metroStyle.css" />
<script type="text/javascript"
	src="${ctx}/views/common/js/zTree/jquery.ztree.core.js"></script>
<script type="text/javascript"
	src="${ctx}/views/common/js/zTree/jquery.ztree.excheck.js"></script>
<script type="text/javascript"
	src="${ctx}/views/common/js/zTree/jquery.ztree.exedit.js"></script>
<script>x=-8;xx=-20;</script>	
<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='images/fold.png' onclick="showAndHide(this,'bespeak_grid');"  class="imgShrotate">
				<font>会议预约详情</font>
			</h3>
		</div>
	<div class="div-main-1" >
	
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>
                     <td><input type="hidden" id="truename" name="truename" value="${admin.truename}"></td>
					<td class="wi-align-l" >					
					<label for="input_1" class="lbl-1">会议室名称:</label>
					<input type="text" id="serch_boardroom_name1" name="serch_boardroom_name1" class="wi-ipt-i" />
					</td>
					<td class="wi-align-l" >
						<label for="input_1" class="lbl-1">会议名称:</label>
						<input type="text" id="serch_meeting_name" name="serch_meeting_name" class="wi-ipt-i" />
					</td>
					<td class="wi-align-l" >
						<label for="input_1" class="lbl-1">开始时间：</label>
						<input type="text" id="s_time" name="s_time"  class="wi-ipt-i" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm'})"/>
						 <label for="input_1" class="lbl-1">&nbsp;至&nbsp;</label>
						  <input type="text" id="s2_time" name="s2_time" class="wi-ipt-i"  onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm'})"/>
					</td>	
<!-- 					<td class="wi-align-l" > -->
<!-- 						<label for="input_1" class="lbl-1">结束时间：</label> -->
<!-- 						<input type="text" id="e_time" name="e_time"  class="wi-ipt-i" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm'})" /> -->
<!-- 					</td> -->
					<td class="wi-align-l">
						<button id="btn_bespeak_search" class="ui-state-default ui-state-qik">查询
						</button>
						<button id="btn_bespeak_empty" class="ui-state-default ui-state-qik">清空
						</button>
					</td>
				</tr>
			</table>
		</div>
</div>
<div class="div div-2" style="">
	<table id="bespeak_grid" style="display:none;padding:0 0 0 0;"></table>
</div>

<div id="dlg_bespeak" style="display:none;overflow-y:auto;">
<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-y">
			
				<tr>
					<td ><span>申请人：</span></td>
					<td>
						<input type="text" class="input_240 input_m" id="truename" name="truename" value="${admin.truename}" />
						
					</td>
				
				</tr>
				<tr>
					<td ><span>所在部门：</span></td>
					<td>
						<input type="text" class="input_240 input_m" id="org_name" name="org_name" value="${admin.org_name}" style=""/>
						
					</td>
					
				</tr>
				<tr>
		            
					<td ><span>会议室名称：</span></td>
					<td>
					  <select name="Boardroom" id="Boardroom" class="wi-sel-1 input_m" onchange="getDetail()">
					      
					</select>
					</td>
					</tr>
				<tr >
					<td ><span>会议室使用状态：</span></td>
					<td id="soa" width="600px" height="50px">
						
						
					</td>
				</tr>
				<tr>
					<td ><span>会议名称：</span></td>
					<td>
						<input type="text" class="input_240 input_m" id="meeting_name" name="meeting_name" value="${map.meeting_name}" style=""/>
						
					</td>
				</tr>
				
				<tr>
					<td ><span>开始时间：</span></td>
					<td>
						<input type="text" id="start_time" name="start_time"  class="Wdate input_m"  
						onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
						
					</td>
					
					</tr>
				<tr>	
					<td ><span>结束时间：</span></td>
					
					<td>
						<input type="text" class="Wdate input_m" id="end_time"  name="end_time" 
						 onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})"  />
						
					</td>			
				</tr>
				
				<tr>
					<td ><span>开会人员：</span></td>
					<td>
						<textarea rows="5" cols="30" resize="none" id="meeting_staff" class="input_s" name="meeting_staff" readonly="readonly" style="border: #ccc 1px solid;"></textarea>
						<input id="kh_people" type="hidden">
						
						
		  <div class="choice_y"> 
   <a href="javascript:depart()" role="button"> <span class=" choice" style="float: left;">选择</span> </a> 
   <a href="javascript:removeDep()" role="button"> <span class="ui-button-text choice">清空</span> </a> 
  </div>				
					</td>
				</tr>
				
			</table>
				
	</div>
	
	<div class="modal fade" id="departMent-board" tabindex="-1" style="display: none;overflow-y:auto;" >
		   <div class="modal-dialog" style="width: 350px;">
		      <div class="modal-content">		        
		        <div id="menuContent-board" style="overflow-y:auto;">
					<ul id="treeDemo-board" class="ztree" style="margin-top:0;background: #f0f6e4;overflow-y:scroll;overflow-x:auto;"></ul>
				</div>
		         <div class="modal-footer">
		         </div>
		      </div>
		   </div>
		</div>

<script type="text/javascript">

var selectedGridDiv = $("div.flexigrid .pDiv .pDiv2 .pGroup");
var page_num =30;
var bespeak_grid = {
	//绑定参数信息grid
	bespeak_grid_result:"",
	dlg_bespeak:"",
	GridHeight:flexHeight("bespeak_grid"),
	bindParamGrid:function(queryP,queryM){
		bespeak_grid.bespeak_grid_result = $("#bespeak_grid").flexigrid({
			url:"boardroom!bespeak.action?operation=init",
			colModel: queryM,
			buttons : queryP,
			rowId:"id",
			showToggleBtn: true,
			usepager: true,
			title:"会议预约详情",
			//rp:pageSize,
			rp:page_num,
			showTableToggleBtn: false,
			resizable: false,
			height: bespeak_grid.GridHeight,
			singleSelect: true,
			onChangePage:bespeak_grid.loadData,
			checkbox:false,
			filterParams : [{key : "boardroom_name", name : "#serch_boardroom_name1"},
			                {key : "meeting_name", name : "#serch_meeting_name"},
			                {key : "start_time", name : "#s_time"},
			                {key : "start_time2", name : "#s2_time"}]
			                
			                
			                
		});
	},			
	// 查询子任务并且显示
	showDialog : function(com,operation,divobj,h,w) 
		{
		var dlgParent = divobj.parent();
		var dlgOwn = divobj.clone();
		bespeak_grid.dlg_bespeak = divobj.dialog({
		        position: ['center','top'],
				autoOpen: false,
				height:$(window).height()*0.5,
				width:$(window).width()*0.45,
				show: "slide",
				modal:true,
				cache:false,
				/* hide: "explode", */
				title:com,
				buttons:{
					"取消":function(){
						dlgOwn.appendTo(dlgParent);
						$(this).dialog("destroy").remove();
					},
					"保存":function(){
						//dlgOwn.appendTo(dlgParent);
						bespeak_grid.saveData(operation);
		              	//$(this).dialog("destroy").remove();
					}
				},
			    close : function() {
		              	dlgOwn.appendTo(dlgParent);
		              	$(this).dialog("destroy").remove();
		         }
			});
			
		bespeak_grid.dlg_bespeak.dialog("option", "title", com).dialog("open");
	},
	
	del:function(com,grid)
	{
		
		if (com == "删除") 
		{
		   	if($('.trSelected', grid).length==1)
			{	
					       var id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
					       var meeting_name = $('.trSelected', grid).eq(0).find("td:eq(3)").text();
					        var indicate = "确认需要删除" + meeting_name+"这个会议吗?";	
					        var params=new Object();
				                params.id = id;	
				                params.truename = $("#truename").val();				
							if(confirm(indicate)) 
							{
								$.ajax({
									type: "POST",
									url: "boardroom!deleteBespeak.action",
									cache: false,
									data: params,
									success: function(msg)
									{
										var result=eval("("+msg+")");
										if (result.msg=="ok") 
										{
											showMsg("删除成功！");
											$('#dlg_bespeak').dialog('close');
											bespeak_grid.loadData({page:1});
										} else 
										{
											showMsg(result.msg);
											$('#dlg_bespeak').dialog('close');
										}
									}
								}); 
							}else
							{
								$('#dlg_bespeak').dialog('close');
							}
						
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_bespeak').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_bespeak').dialog('close');
						showMsg("未选中行！");
					}
				}			
	},
	//预约
	bespeak:function(com,grid)
	{
		
		if (com == "预约") 
        {
				//获取可用会议室		
		        $.ajax({
		            type:"get",
		            dataType:"text",
		            async:false,
		            url:"boardroom!queryBoardroomByStatus.action",
		            success:function(result) 
		            {
		            	var data=eval("("+result+")");
						var html = '<option>---请选择---</option>';
						if(data.length > 0)
						{
							for (var i in data) 
							{
								html += '<option value="'+data[i].BOARDROOM_NAME+'">'+data[i].BOARDROOM_NAME+'</option>';
							}
							$("#Boardroom").html(html);
						}
					}
		        });   
				  	name_arr = new Array();
				    bespeak_grid.showDialog("预约会议室","bespeak", $("#dlg_bespeak"));
					$("#action").val("bespeak");
			}
	},
	
	//预约详情
	createTable:function(result)
	{
			    			var data=eval("("+result+")");
							var result = "";
							if(data!="")
							{
								result +=  '<table cellpadding="0" cellspacing="0" class="table-y">'
									 +     '<tr>'
									 +       '<td colspan="5" style="font-size:12px;text-align:center;" bgcolor="#dddddd">'+$("#Boardroom").val() +'会议室使用详情</td>'
									 +     '</tr>';
								result +=  '<tr>'
									 +       '<td style="width:150px;font-size:12px;font-weight: bold;text-align:center">会议名称</td>'
									 +       '<td style="width:100px;font-size:12px;font-weight: bold;text-align:center">开始时间</td>'
									 +       '<td style="width:200px;font-size:12px;font-weight: bold;text-align:center">结束时间</td>'
									
									 +   '</tr>';
								for (var i in data) 
								{
									
										result += '<tr>'
											 +      '<td style="width:150px;font-size:12px;text-align:center">'+ data[i].MEETING_NAME +'</td>'
											 +      '<td style="width:150px;font-size:12px;text-align:center">'+ data[i].START_TIME +'</td>'
											 +      '<td style="width:150px;font-size:12px;text-align:center">'+ data[i].END_TIME +'</td>'
											
											
											 result +=   '</tr>';
									
								}		
								result +='</table>';
							}
							else
							{  
							    if($("#Boardroom").val() != "---请选择---")
								result =  '<span style="font-size:14px;color:red">'+$("#Boardroom").val() +'会议室空闲,您可自由预约！</span>';
							}
							return result;			
	},	
  saveData:function(operation)
	{         
				var url ='';
				var indicate='';
				var params=new Object();
				    params.truename = $.trim($("#truename").val());
				    params.org_name = $.trim($("#org_name").val());
				    params.Boardroom = $("#Boardroom").val();
				    params.meeting_name =$.trim($("#meeting_name").val());
				    params.start_time = $("#start_time").val();
				    params.end_time = $("#end_time").val();
				    params.meeting_staff = $("#meeting_staff").val();
				    
				    if(params.truename==null || params.truename=='')
					{
						showMsg("申请人不能为空！");
						
						return;
					}
					if(params.org_name==null || params.org_name=='')
					{
						showMsg("申请人所在部门不能为空！");
						
						return;
					}
					if(params.Boardroom=='---请选择---')
					{
						showMsg("请选择会议室！");
						
						return;
					}
					if(params.meeting_name==null || params.meeting_name=='')
					{
						showMsg("会议名称不能为空！");
						
						return;
					}
					if(params.start_time==null || params.start_time=='')
					{
						showMsg("开始时间不能为空！");
						
						return;
						
					}
					if(params.end_time==null || params.end_time=='')
					{
						showMsg("结束时间不能为空！");
						
						return;
					}
					
					//时间判断
					var d1 = new Date(params.start_time.replace(/\-/g, "\/")); 
					var d2 = new Date(params.end_time.replace(/\-/g, "\/"));
					if(params.start_time!=""&&params.end_time!=""&&d1 >=d2){ 
						showMsg("结束时间不能早于开始时间！"); 
					  	return; 
					}
					if(params.meeting_staff==null || params.meeting_staff=='')
					{
						showMsg("开会人员不能为空！");
					
						return;
					}
					
					indicate = "确认预约?";
					url = "boardroom!bespeakBoardroom.action";
				    
				
				// 弹出确认框
				if(confirm(indicate))
				{
					$.ajax({
						type: "POST",
						url: url,
						cache: false,
						data: params,
						success: function(msg){
							var result=eval("("+msg+")");
							if (result.msg=="ok") 
							{
								showMsg("操作成功");
								
								
								$('#dlg_bespeak').dialog('close');
								bespeak_grid.loadData({page:1});
							} else 
							{
								showMsg(result.msg);
							}
						}
					}); 
				}
				else
				{
				  
				    $('#dlg_bespeak').dialog('close');
				}
		
	},
  
   	loadData:function(param){
		var queryP = {};
		queryP.boardroom_name = $('#serch_boardroom_name1').val()==null?'':$('#serch_boardroom_name1').val();
		queryP.meeting_name = $('#serch_meeting_name').val()==null?'':$('#serch_meeting_name').val();
		queryP.start_time = $('#s_time').val()==null?'':$('#s_time').val();
		queryP.start_time2 = $('#s2_time').val()==null?'':$('#s2_time').val();
		//var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
		var selectedGridDiv = $("div.flexigrid .pDiv .pDiv2 .pGroup");
		queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
		queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
		queryP.sortname = param.sortname? param.sortname : "";
		queryP.sortorder = param.sortorder? param.sortorder : "";
		$.ajax({
			type: "POST",
			url: "boardroom!bespeak.action?operation=init",
			cache: false,
			data: queryP,
			success: function(msg){
				var data=eval("("+msg+")");
				try{
					bespeak_grid.bespeak_grid_result.flexAddData(data);
				}catch(e){
					showErrorMsg("查询结果信息出错！");
				}
			}
		}); 
	}
  }



	 $(function(){
		//初始化所有操作按钮
		 var  parm_button = [
			  {name: '删除', bclass: 'delete', onpress : bespeak_grid.del},
			  {name: '预约', bclass: 'add', onpress : bespeak_grid.bespeak}
		 ];
		 //权限操作按钮
		 var colModel = [						
	            {display: '序号', name : 'xh', width : 40, align: 'center'},
				{display: 'id', name : 'ID', width : 40, align: 'center',hide : true},
				{display: '会议室名称', name : 'BOARDROOM_NAME', width : 150, align: 'left',sortable: true},
				{display: '会议名称', name : 'MEETING_NAME', width : 150, align: 'left',sortable: true},
				{display: '开始时间', name : 'START_TIME', width : 150,  align: 'left',sortable : true},
				{display: '结束时间', name : 'END_TIME', width : 150,  align: 'left',sortable : true},	
				{display: '申请人', name : 'TRUENAME', width : 150, align: 'left',sortable: true},
				{display: '所在部门', name : 'ORG_NAME', width : 150, align: 'left',sortable: true},
				{display: '申请时间', name : 'BESPEAK_TIME', width : 150,  align: 'left',sortable : true},
				{display: '开会人员', name : 'MEETING_STAFF', width : 150,  align: 'left',sortable : true}
		 ];
		 	 			// 初始化数据
		 bespeak_grid.bindParamGrid(parm_button,colModel); 
		 
		 
		 		/*参数管理-查询按钮功能*/
		$('#btn_bespeak_search').button().on("click",function(){
			bespeak_grid.loadData({page:1});
		});
				//清空筛选框
		$('#btn_bespeak_empty').on("click",function(){
			$("#serch_boardroom_name1").val("");
			$("#serch_meeting_name").val("");
			$("#s_time").val("");
			$("#s2_time").val("");
		}); 
		 		 
	});
	
	var org_arr = new Array();
  	  var name_arr = new Array();
		function onChooseOrg(e, treeId, treeNode) {
			var orgId = treeNode.ORG_ID;
			var org_infor = treeNode.ORG_INFOR;
			if(orgId.indexOf("_personId_") != -1 && treeNode.checked){
				//zTree.expandNode(treeNode);
				if($("#kh_people").val().indexOf(treeNode.ORG_NAME) == -1){
					name_arr.push(treeNode.ORG_NAME);
					org_arr.push($.trim(org_infor).length == 0 ? '无职务':org_infor);
					add_html();
				}
			}else if(orgId.indexOf("_personId_") != -1 && !treeNode.checked){
				for(var i = 0; i < name_arr.length; i++){
					if(treeNode.ORG_NAME == name_arr[i]){
						name_arr.splice(i,1);
						org_arr.splice(i,1);
					}
				}
				add_html();
			}
		}

		var setting = {
			view: {
				dblClickExpand: false
			},
			check:{
				enable: true,
				autoCheckTrigger: true,
				chkStyle: "checkbox",
				chkboxType: { "Y": "ps", "N": "ps" }
			},
			data: {
				key:{
					name: "ORG_NAME"
				},
				simpleData: {
					enable: true,
					name: "ORG_NAME",
					idKey: "ORG_ID",
					pIdKey: "SUPER_ORG_ID",
					rootPId: 0
				}
			},
			callback: {
				onCheck: onChooseOrg
			}
		};		 
function depart(){
	  var dlgParent = $("#departMent-board").parent();
      var dlgOwn = $("#departMent-board").clone();
	  var depart  = $("#departMent-board").dialog({
					position: ['right','top'],
        			draggable :true,     
        			autoOpen: false,
					height:600,
					width:"auto",
					show: "slde",
					modal:true,
					cache:false,
					/* hide: "explode", */
					title:'部门列表',
					buttons:{
						"确定":function(){
							arrSplit();
						}
					},
					onClose:function()
				    {  
		            	dlgOwn.appendTo(dlgParent);
		            	$(this).dialog("destroy").remove();
	                } 
        	});
    	    $("#departMent-board").dialog("open");
    	$.ajax({
			type: "POST",
			url: "docum!queryOrganizationInfos.action?math_radom="+Math.random(),
			cache: false,
			data: null,
			success: function(msg){
				var data=eval("("+msg+")");
				$.fn.zTree.init($("#treeDemo-board"), setting, data);
				treeObj  =$.fn.zTree.getZTreeObj("treeDemo-board");
				
				var nodes = treeObj.getNodes();
				var nodes2 = nodes[0].children;

					for (var j = 1; j < nodes2.length; j++) {
						treeObj.expandNode(nodes2[j], true, false, true);
					}
				var val = $("#meeting_staff").val();
				if($.trim(val).length != 0){
						var array =	val.split(",");
						var node;
						for(var i = 0 ; i < array.length ; i++){
							node  =treeObj.getNodeByParam("ORG_NAME",array[i],null);
							treeObj.checkNode(node,true,true);

						}
					}

			}
		});
    }
			
	function add_html(){
		$("#kh_people").val(name_arr);
	}
   
// 	function checkParentNode(super_Id){
// 		var node  =treeObj.getNodeByParam("ORG_ID",super_Id);
// 		treeObj.checkNode(node,true, false);
// 		if(node.SUPER_ORG_ID != 0){
// 			checkParentNode(node.SUPER_ORG_ID);
// 		}
// 	}
	
	//匹配树列表和树结构数据，去重
	function arrSplit(){
		  var dstr = $('#kh_people').val();  
          var strArr=dstr.split(",");//把字符串分割成一个数组  
          $('#meeting_staff').val(strArr)//把数组连成字符串并展示到页面  
          $("#departMent-board").dialog("close");
	}
	
	
	//获取所选会议室的预约详情
    function getDetail()
     {  
        $("#soa").html("");
        var sboardroom = encodeURI(encodeURI($("#Boardroom").val()));
    	
        $.ajax({
            type:"POST",
			data:null,
			cache: false,
		    async:false,
            url:"boardroom!queryBoardroomByName.action?sboardroom=" + sboardroom,
            
            success:function(result) 
            {
            	
				var result_str = bespeak_grid.createTable(result);
				$("#soa").append(result_str);
			}
        });   
    }
    
    function removeDep(){
    	$("#kh_people").val("");
    	$("#meeting_staff").val("");
    }
	
</script>

