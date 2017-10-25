<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
				<script>
 x=-1;
		</script>
<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='../images/fold.png' onclick="showAndHide(this,'szxc_grid');"  class="imgShrotate">
				<font>${head}</font>
			</h3>
		</div>
	<div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>
					
					<td >
					<label for="input_1" class="lbl-1">
							上报人:
					</label>
                        <input type="text" id="serch_szxc_stSbry" name="serch_szxc_stSbry" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<label for="input_1" class="lbl-1">
							巡查时间:
						</label>			
					<input type="text" id="serch_szxc_time" name="serch_szxc_time" readonly class="Wdate" onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
					</td>
					<td class="wi-align-l ">
						<button id="btn_szxc_search" class="ui-state-default ui-state-qik">查询
						</button>
					
						<button id="btn_szxc_empty" class="ui-state-default ui-state-qik">清空
						</button>
					</td>
				</tr>
			</table>
		</div>
</div>
<div class="div div-2" style="">
	<table id="szxc_grid" style="display:none;padding:0 0 0 0;"></table>
</div>
<div id="dlg_szxc" style="display:none;">
	<input type="hidden" id="action" />
	<input type="hidden" id="dlg_id" /> 
	
<table class="table12" cellpadding="0" cellspacing="0">
  <tr>
   <td>事件类型名称</td>
   <td><input type="text" id="dlg_stSjlxmc" name="dlg_stSjlxmc"  class="wi-ipt-1"  /></td>
  </tr>
  <tr>
   <td>情况描述</td>
   <td><input type="text" id="dlg_stQkms" name="dlg_stQkms"  class="wi-ipt-1" /></td>
  </tr>
   <tr>
   <td>经度</td>
   <td>
	  <input type="text" id="dlg_nJd" name="dlg_nJd"  class="wi-ipt-1" />
   </td>
  </tr>
   <tr>
   <td >纬度</td>
   <td>	
      <input type="text" id="dlg_nWd" name="dlg_nWd"  class="wi-ipt-1" />
  </td>
  </tr>
   <tr>
   <td>上报日期</td>
   <td>
       <input type="text" id="dlg_dSbrq" name="dlg_dSbrq"  class="wi-ipt-1" />
   </td>
  </tr>
   <tr>
   <td>上报人员</td>
   <td>
      <input type="text" id="dlg_stSbry" name="dlg_stSbry"  class="wi-ipt-1" />
   </td>
  </tr>
   <tr>
   <td>上报人员部门</td>
   <td>
      <input type="text" id="dlg_stSbbm" name="dlg_stSbbm"  class="wi-ipt-1" />
   </td>
  </tr>
   <tr>
   <td>事件状态</td>
   <td>
      <input type="text" id="dlg_stSjzt" name="dlg_stSjzt"  class="wi-ipt-1" />
   </td>
  </tr>  
   <tr>
   <td>附件列表</td>
   <td>
      <div id="fjlist"></div>
   </td>
  </tr>
</table>
</div>	
<script>		
var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
var page_num = $(window.self.document).find("#page_num").val();
var page_num =30;
var szxc_grid = {
	//绑定参数信息grid
	szxc_grid_result:"",
	GridHeight:flexHeight("szxc_grid"),
	bindParamGrid:function(queryP,queryM){
		szxc_grid.szxc_grid_result = $("#szxc_grid").flexigrid({
			url:"szzf!szxcList.action?operation=init",
			colModel: queryM,
			buttons : queryP,
			rowId:"id",
			showToggleBtn: true,
			usepager: true,
			title:"${head}",
			//rp:pageSize,
			rp:page_num,
			showTableToggleBtn: false,
			resizable: false,
			height: szxc_grid.GridHeight,
			singleSelect: true,
			onChangePage:szxc_grid.loadData,
			checkbox:false,
			filterParams : [{key : "stSbry", name : "#serch_szxc_stSbry"},
			                {key : "time", name : "#serch_szxc_time"}
			                ]
		});
	},			
	// 查询子任务并且显示
	showDialog : function(com,operation,divobj,h,w) 
		{
		var dlgParent = divobj.parent();
		var dlgOwn = divobj.clone();
		btn_szxc = divobj.dialog({
		        position: ['center','center'],
				autoOpen: false,
				height:h ? h : 600,
				width:w ? w : 800,
				minHeight:600,
				minWidth:800,
				show: "slide",
				modal:true,
				cache:false,
				/* hide: "explode", */
				title:com,
				buttons:{
					"取消":function(){
						dlgOwn.appendTo(dlgParent);
						$(this).dialog("destroy").remove();
					}
//保存操作先取消		
// 		,
// 					"保存":function(){
// 						dlgOwn.appendTo(dlgParent);
// 						szxc_grid.saveData(operation);
// 		              	$(this).dialog("destroy").remove();
// 					}
				},
			    close : function() {
		              	dlgOwn.appendTo(dlgParent);
		              	$(this).dialog("destroy").remove();
		         }
			});
			
		btn_szxc.dialog("option", "title", com).dialog("open");
	},
	edit:function(com,grid)
	{
		
					if($('.trSelected', grid).length==1)
					{
					    szxc_grid.showDialog("查看","edit",$("#dlg_szxc"),"auto","600");
						var id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
						var params=new Object();
				            params.id = id;
							$.ajax({
									type: "POST",
									url: "szzf!getszxcInfo.action",
									cache: false,
									data: params,
									success: function(msg)
									{
									    if(msg != null)
									    {
											var result=eval("("+msg+")");									
											$("#dlg_stSjlxmc").val(result.stSjlxmc);	
											$("#dlg_stQkms").val(result.stQkms);
		                                    $("#dlg_nJd").val(result.nJd);
											$("#dlg_nWd").val(result.nWd);
											$("#dlg_dSbrq").val(result.dSbrq);
											$("#dlg_stSbry").val(result.stSbry);
											if(result.stSbbm == null){
											$("#dlg_stSbbm").val(result.stSbbm);
											}else{
											  $("#dlg_stSbbm").val("");
											}
											$("#dlg_stSjzt").val(result.stSjzt);	
											var data=result.list;	
											var result = "";
											for (var i in data) 
								            {
								              	result += "<span style='display:inline-block' ><a id='down' href='javascript:void(0);' onclick='szxc_grid.mydownload(\""+data[i].stFjlj+"\")'>附件</a></span></br>";
								            }		
								            $("#fjlist").append(result);						
									  }
									}
								}); 				
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_szxc').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_szxc').dialog('close');
						showMsg("未选中行！");
					}
	},
		// 导出数据
	commitExport : function() 
	{
		/*		var condition = "";
				var serch_szxc_name=$("#serch_szxc_name")==null?'':$('#serch_szxc_name').val();
				if (serch_szxc_name && serch_szxc_name.trim().length > 0) 
				{
					condition += "&project_name=" + encodeURI(encodeURI(serch_szxc_name.trim()));
				}
				var serch_szxc_bianhao=$("#serch_szxc_bianhao")==null?'':$('#serch_szxc_bianhao').val();
				if (serch_szxc_bianhao && serch_szxc_bianhao.trim().length > 0) 
				{
					condition += "&project_bianhao=" + encodeURI(encodeURI(serch_szxc_bianhao.trim()));
				}
				location.href='ztb!exportJobs_zbyqh.action?'+condition;
		*/	
   },
      //下载附件
   mydownload:function(path)
   {
	 if(path === '' || path === undefined || typeof path === 'undefined' )
	 {
		showErrorMsg("上传附件不存在或你未上传附件！");
		return;
	 }
	 else
	 {
        location.href='upload!download_yc.action?&path='+ encodeURI(encodeURI(unescape(path))); 
     }
   },
   
   	loadData:function(param)
   	{
   	
		var queryP = {};
		queryP.stSbry = $('#serch_szxc_stSbry').val()==null?'':$('#serch_szxc_stSbry').val();
		queryP.time = $('#serch_szxc_time').val()==null?'':$('#serch_szxc_time').val();
		var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
		queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
		queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
		queryP.sortname = param.sortname? param.sortname : "";
		queryP.sortorder = param.sortorder? param.sortorder : "";
		$.ajax({
			type: "POST",
			url: "szzf!szxcList.action?operation=init",
			cache: false,
			data: queryP,
			success: function(msg)
			{
				var data=eval("("+msg+")");
				//console.log(data);
				try{
					szxc_grid.szxc_grid_result.flexAddData(data);
				}catch(e){
					showErrorMsg("查询结果信息出错！");
				}
			}
		}); 
	}
};


	 $(function(){
		//初始化所有操作按钮
		 var  parm_button = [{name: '查看详情', bclass: 'edit', onpress : szxc_grid.edit}];
		 //权限操作按钮
		 var colModel = [						
	            {display: '序号', name : 'xh', width : 40, align: 'center'},
				{display: 'id', name : 'id', width : 40, align: 'center',hide : true},
				{display: '巡查事件', name : 'stXcdxmc', width : 350, align: 'left',sortable: true},
				{display: '事件类型名称', name : 'stSjlxmc', width : 150, align: 'left',sortable: true},
				{display: '情况描述', name : 'stQkms', width : 120,  align: 'left',sortable : true},
				{display: '上报日期', name : 'dSbrq', width : 120,  align: 'left',sortable : true},
				{display: '上报人员名称', name : 'stSbry', width : 120,  align: 'left',sortable : true},
				{display: '上报人员部门', name : 'stSbbm', width : 120,  align: 'left',sortable : true,process:function(value){
					var stSbbm = $.trim($(value).html());
					if(stSbbm == null || stSbbm == ""){
					   $(value).html("");
					}
					}}			
		 ];
		 	 			// 初始化数据
		 szxc_grid.bindParamGrid(parm_button,colModel); 
		 
		 
		 		/*参数管理-查询按钮功能*/
		$('#btn_szxc_search').button().on("click",function(){
			
			szxc_grid.loadData({page:1});
			
		});
				//清空筛选框
		$('#btn_szxc_empty').on("click",function(){
			$("#serch_szxc_stSbry").val("");
			$("#serch_szxc_time").val("");
		});  
		 		 
	});
	
</script>
