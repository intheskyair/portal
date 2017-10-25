<%@ page contentType="text/html; charset=UTF-8"%>
<html>
    <head>
	<title>发文归档</title>
<script>x=-1;</script>	
	</head>
	<body  style="overflow:-Scroll;overflow-x:hidden" >	
	<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='../images/fold.png' onclick="showAndHide(this,'gwgd_grid1');"  class="imgShrotate">
				<font>发文归档</font>
			</h3>
			
		</div>
	<div class="div-main-1" >
				<input type="hidden" id="gid">
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>
					<td class="wi-align-l" ><label for="input_1" class="lbl-1">档号：</label>
						<input type="text" id="reference_fw" name="reference_fw" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l" ><label for="input_1" class="lbl-1">文号：</label>
						<input type="text" id="refer_fw" name="refer_fw" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l" ><label for="input_1" class="lbl-1">归档日期：</label>
						<input type="text" id="overtime_fw" name="overtime_fw" readonly class="wi-ipt-1"
						onclick="WdatePicker({readOnly:true})" />
					</td>
					<td class="wi-align-l" ><label for="input_1" class="lbl-1">发文类型：</label>
						<select id="document_type_fw" class="input_240">
						</select>
					</td>
					<td class="wi-align-l" ><label for="input_1" class="lbl-1">是否公开：</label>
						<select id="sfgk_fw" class="input_240">
							<option value="">请选择</option>
							<option value="0">是</option>
							<option value="1">否</option>
						</select>
					</td>
					<td class="wi-align-l"><button id="btn_mzpc_search_lczl1" class="ui-state-default ui-state-qik">查询</button><button id="btn_mzpc_empty_lczl1" class="ui-state-default ui-state-qik">清空</button></td>
				</tr>
			</table>
		</div>
	</div>
	<div class="div div-2" style="overflow-x: hidden;">
		<table id="gwgd_grid1" style="display:none;padding:0 0 0 0;"></table>
	</div>
	<div id="fwgd_dialog" style="display:none;">
			<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
		<tr>
			<td style="width:100px;"><label for="input_1">归档类型</label></td>
			<td class="wi-align-l" style="width:240px;">
				<input type="radio" id="ref11" name="type_ref" value="0">新建档号
				<input type="radio" id="ref22" name="type_ref" value="1">使用历史档号
			</td>
		</tr>
		<tr id="show_his_fw">
			<td style="width:100px;"><label for="input_1">历史档号</label></td>
			<td class="wi-align-l" style="width:240px;">
				<select id="his_reference1"></select>
			</td>
		</tr>
		<tr>
			<td style="width:100px;"><label for="input_1"></label></td>
			<td class="wi-align-l" style="width:240px;">
				<input type="text" class="wi-ipt-1" value="全宗号" readonly="readonly" style="width:125px;"/>-
				<input type="text" class="wi-ipt-1" value="年度" readonly="readonly"  style="width:125px;"/>-
				<input type="text" class="wi-ipt-1" value="保管期限代码或目录号"  readonly="readonly" style="width:125px;"/>-
				<input type="text" class="wi-ipt-1" value="盒号或案卷号"  readonly="readonly" style="width:125px;"/>-
				<input type="text" class="wi-ipt-1" value="件号或页号"  readonly="readonly" style="width:125px;"/>
			</td>
		</tr>
		<tr id="ro_msg">
			<td style="width:100px;"><label for="input_1">档号：</label></td>
			<td class="wi-align-l" style="width:240px;">
				<input type="text" id="dh11" name="dh11"  class="wi-ipt-1" style="width:125px;" readonly="readonly" value="B056"/>-
				<input type="text" id="dh22" name="dh22"  class="wi-ipt-1" style="width:125px;" onblur="check('dh22','年度')"/>-
				<select id="dh33" name="dh33" style="width: 125px">
					<option value="001">永久</option>
					<option value="002">30年</option>
					<option value="003">10年</option>
				</select>-
				<input type="text" id="dh44" name="dh44"  class="wi-ipt-1" style="width:125px;"  onblur="check('dh44','盒号和案卷号')"/>-
				<input type="text" id="dh55" name="dh55"  class="wi-ipt-1" style="width:125px;" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;"><label for="input_1">此次归档文件：</label></td>
			<td class="wi-align-l" style="width:240px;">
				<textarea id="showGdTitle11" readonly="readonly"></textarea>
			</td>
		</tr>
		<tr>
		<td colspan="2">
			<span id="error_fw" class="LV_invalid none" style="color: red;"></span>
		</td>
		</tr>
	</table>
	</div>
	

	
	
		<div id="fwgd_list" style="display:none;    overflow: scroll;">
				
		     </div>
		     
		    <div id="show_fj_fw" style="display:none;    overflow: scroll;">
				
		     </div> 
	
		<script type="text/javascript">		
		 var rowdbclick_fw  = function(rowData) {
			 fwgd.fjDetails(rowData.FJNAME, rowData.FJADDRESS);
		}
		 var data_all;
		 var paramGridHeight_gwgd = flexHeight("gwgd_grid1");
		 var arr_fw = new Array(); 
		 var show_fw;
		 var param_fwgd = {
		 param_grid_user:"",
		 fwgd_dialog:"",
			//绑定参数信息grid
			bindParamGrid:function(queryP,queryM){
				param_fwgd.param_grid_user = $("#gwgd_grid1").flexigrid({
					url:"gwgd!GwgdList.action?operation=init&type=1",
					colModel: queryM,
					buttons : queryP,
					rowId:"id",
					showToggleBtn: true,
					usepager: true,
					title:"发文归档列表",
					//rp:pageSize,
					rp:page_num,
					onRowDblclick:rowdbclick_fw,
					showTableToggleBtn: false,
					resizable: false,
					height: paramGridHeight_gwgd,
					singleSelect: true,
					onChangePage:param_fwgd.loadData,
					checkbox:true,
					filterParams : [{key : "title", name : "#title"}]
				});
			},
			loadData:function(param){
				var queryP = {};
				queryP.overtime = $('#overtime_fw').val()==null?'':$('#overtime_fw').val();
				queryP.reference = $('#reference_fw').val()==null?'':$('#reference_fw').val();
				queryP.refer = $('#refer_fw').val()==null?'':$('#refer_fw').val();
				queryP.sw_type = $('#document_type_fw').val()==null?'':$('#document_type_fw').val();
				queryP.sfgk = $('#sfgk_fw').val()==null?'':$('#sfgk_fw').val();
				var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
				queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
				queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
				$.ajax({
					type: "POST",
					url: "gwgd!GwgdList.action?operation=init&type=1",
					cache: false,
					data: queryP,
					success: function(msg){
						var data=eval("("+msg+")");
						try{
							param_fwgd.param_grid_user.flexAddData(data);
						}catch(e){
							showErrorMsg("查询结果信息出错！");
						}
					}
				}); 
			},
			//归档
			referGW : function(com, grid) {
			
				$("[name^='dh']").removeAttr("disabled","disabled");
				$("input[id='ref11']").attr("checked","checked");
				$("#show_his_fw").css("display","none");
				arr_fw = [];
				show_fw = '';
		        var dlgParent = $("#fwgd_dialog").parent();
		        var dlgOwn = $("#fwgd_dialog").clone();
				param_fwgd.fwgd_dialog = $("#fwgd_dialog").dialog({
					 position: ['center','center'],
					overflow:"auto",
					autoOpen: false, 
					height:$(window).height()*0.8,
					width:$(window).width()*0.5,
					show: "slide",
					modal:true,
					cache:false,
					/* hide: "explode", */
					title:'发文归档',
					close : function() 
				    {
		            	dlgOwn.appendTo(dlgParent);
		            	$(this).remove();
                    },	
					buttons:{
						"保存":function(){
							param_fwgd.saveParam_fw();
							
						}
					}
				});
				var len = $('.trSelected', grid).length;
				if(len == 0){
					showMsg("未选中");
				}else{
					for(var i =0 ;i < len; i++){
						if($('.trSelected', grid).eq(i).find("td:eq(8)").text() == '已归档'){
							showMsg("序号："+$('.trSelected', grid).eq(i).find("td:eq(0)").text()+",已归档");
							arr_fw = [];
							show_fw = '';
							
							break;
						}else{
							arr_fw[i] = ($('.trSelected', grid).eq(i).find("td:eq(1)").text());
							show_fw += $('.trSelected', grid).eq(i).find("td:eq(3)").text() + "|";
						}
					}
					if(arr_fw.length != 0){
						$("#showGdTitle11").val(show_fw);
						param_fwgd.showmsg();
						param_fwgd.fwgd_dialog.dialog("open");
					}
					else
					{
					   param_fwgd.fwgd_dialog.dialog("close");
					}
					
				}
			},
			showmsg:function(){
				$("#dh33").val("001");
				$("#dh44").val("");
				$("#his_reference1").html("");
				$.ajax({
					type: "get",
					url: "gwgd!getMax.action",
					dataType: "json", 
					success: function(data){
						$("#dh55").val(data.num);
						$("#dh22").val(data.year);
						$("#his_reference1").append("<option value=''>请选择</option>");
						for(var i in data.list){
							$("#his_reference1").append("<option value='"+data.list[i].REFERENCE+"'>"+data.list[i].REFERENCE+"</option>");
						}
					}
				});
			},
			saveParam_fw:function(){
            
				$("#dh22").blur();
				$("#dh44").blur();
				
				if(($("span[class='LV_invalid']").length != 0))
				{
				//param_fwgd.fwgd_dialog.dialog("close");
					return;
				}else
				{	
							
					var QueryP = {};
					QueryP.reference = $("#dh11").val() + "-" + $("#dh22").val() + "-" + $("#dh33").val() + "-" + $("#dh44").val() + "-" + $("#dh55").val();
					QueryP.id = JSON2.stringify(arr_fw);
					$.ajax({
						type: "get",
						url: "gwgd!referenceG.action",
						data:QueryP,
						success: function(data){
							if(data == "success"){
							
								showMsg("操作成功");
								param_fwgd.fwgd_dialog.dialog("close");	
								
							}else{
								showMsg("操作失败");
							}
							param_fwgd.loadData({page:1});
						}
					});
				}
			},
	// 导出数据
	commitExport : function() 
	{
				var condition = "";
				var overtime=$("#overtime_fw")==null?'':$('#overtime_fw').val();
				if (overtime && $.trim(overtime).length > 0) 
				{
					condition += "&overtime=" + encodeURI(encodeURI($.trim(overtime)));
				}
				var reference=$("#reference_fw")==null?'':$('#reference_fw').val();
				if (reference && $.trim(reference).length > 0) 
				{
					condition += "&reference=" + encodeURI(encodeURI($.trim(reference)));
				}
				var refer=$("#refer_fw")==null?'':$('#refer_fw').val();
				if (refer && $.trim(refer).length > 0) 
				{
					condition += "&refer=" + encodeURI(encodeURI($.trim(refer)));
				}
				var sw_type=$("#document_type_fw")==null?'':$('#document_type_fw').val();
				if (sw_type && $.trim(sw_type).length > 0) 
				{
					condition += "&type=" + encodeURI(encodeURI($.trim(sw_type)));
				}
				var sfgk=$("#sfgk_fw")==null?'':$('#sfgk_fw').val();
				if (sfgk && $.trim(sfgk).length > 0) 
				{
					condition += "&sfgk=" + encodeURI(encodeURI($.trim(sfgk)));
				}
				location.href='gwgd!exportJobs.action?type=1&'+condition;
			
   }
			
		};
 
 
 

 
 
	 $(function(){
		//初始化所有操作按钮
		 var  parm_button = [
		 	 {name: '发文归档', bclass: 'add', onpress : param_fwgd.referGW},
		 	 {name: '导出', bclass: 'excel', onpress : param_fwgd.commitExport} 
		 ];
		 //权限操作按钮
		 var colModel = [
		                {display: '序号', name : 'xh', width : 40, align: 'center'},
		                {display: 'ID', name : 'ID', width : 100, align: 'center',hide : true,sortable: true},
		                {display: '文号', name : 'REFER', width : 100, align: 'center',sortable: true},
		                {display: '标题', name : 'TITLE', width : 200, align: 'center',sortable: true},
		                {display: '发文日期', name : 'BEGINTIME', width : 150, align: 'center',sortable: true},
		                {display: '公开', name : 'SFGK', width : 30, align: 'center',sortable: true,
		                	process:function(obj)
							{	
								if ($.trim($(obj).html()) == '0') 
								{
									 $(obj).html("是");
								}else{
									 $(obj).html("否");
								}
							}
						},
		                {display: '附件', name : 'FJNAME', width : 200, align: 'center',sortable: true},
						{display: '档号', name : 'REFERENCE', width : 180, align: 'center',sortable: true,
		                	process:function(obj)
							{	
								if ($.trim($(obj).html()) == 'undefined' || $.trim($(obj).html()) == 'null') 
								{
									 $(obj).html("");
								}
							}
						},
						{display: '状态', name : 'STATUS', width : 150, align: 'center',sortable: true,
							process:function(obj)
							{	
								if ($.trim($(obj).html()) == '0') 
								{
								   $(obj).html("未归档");
								   $(obj).css({color:"red"});
								}else{
									$(obj).html("已归档");
								}
								
							}
						},
						{display: '操作人', name : 'TRUENAME', width : 100, align: 'center',sortable: true,						
						    process:function(obj)
							{	
								if ($.trim($(obj).html()) == 'undefined' || $.trim($(obj).html()) == 'null') 
								{
									 $(obj).html("");
								}
							}
						
						},
						{display: '详情', name : 'ORDERID', width : 150, align: 'center',sortable: true,
							process:function(obj)
							{	
								var ob = $.trim($(obj).html());
								 $(obj).html("<a href='#' onclick='fwgd.personDetails(\""+ob+"\")'>详情<a/>");
							}
						},
						{display: '文件地址', name : 'FJADDRESS', width : 100,hide : true, align: 'center',sortable: true}
						];
		 	 			// 初始化数据
		 param_fwgd.bindParamGrid(parm_button,colModel);
		/*参数管理-查询按钮功能*/
		$('#btn_mzpc_search_lczl1').button({ icons: {primary:'ui-icon-search'} }).on("click",function(){
			param_fwgd.loadData({page:1});
		});

		//清空筛选框
		$('#btn_mzpc_empty_lczl1').on("click",function(){
			$("#reference_fw").val("");
			$("#refer_fw").val("");
			$("#overtime_fw").val("");
			$("#document_type_fw").val("");
			$("#sfgk_fw").val("");
		});
		
		//档号查询
		$("input[name='type_ref']").click(function(){
			if($(this).val() == 1){
				if($("#his_reference1 option").length == 0){
					showMsg("未发现历史档号"); 
					$("input[id='ref11']").attr("checked","checked");
				}else{
					$("#show_his_fw").removeAttr("style");	
				}
				
				$("[name^='dh']").attr("disabled","disabled");
			}else{
				$("[name^='dh']").removeAttr("disabled","disabled");
				param_fwgd.showmsg();
				$("#show_his_fw").css('display','none');
				//$("#show_his").css('visibility','hidden');
			}
			
		});
				
		
		$("#his_reference1").change(function(){
			if($("#his_reference1").val() != ''){
				var val = $("#his_reference1").val();
				var arr = val.split("-");
				$("#dh11").val(arr[0]);
				$("#dh22").val(arr[1]);
				$("#dh33").val(arr[2]);
				$("#dh44").val(arr[3]);
				$("#dh55").val(arr[4]);
			}
			
		});
		
		
		 $.ajax({
	            type:"get",
	            dataType:"text",
	            async:false,
	            url:"docum!getdocument_dic.action",
	            success:function(result) 
	            {
	            	var data=eval("("+result+")");
					var html = '<option value="">请选择</option>';
						for (var i in data) 
						{
							html += '<option value="'+data[i].DIC_NAME+'">'+data[i].DIC_NAME+'</option>';
						}
					
					$("#document_type_fw").html(html);
	            }
	        });
	});	
	 function check(id,msg){
	 
		 if($("#"+id).val().length == 0){
		
			 $("#error_fw").removeClass("none").html(msg + "不能为空");
		 }else{
			 var reg = /^\d{4}$/;     
			 if(!reg.test($("#"+id).val())){
			
				 $("#error_fw").removeClass("none").html(msg + "必须为4位数字");	 
			 }else{
				 $("#error_fw").addClass("none").html("");
			 }
		 }
		 
	 }
	 

	 var fwgd = {
				//个人流程查询
				show_fj_fw:{},
				fwgd_list:{},
				fjDetails : function(fjname,fjaddress) {
					$("#show_fj_fw").html("");
			        var dlgParent = $("#show_fj_fw").parent();
			        var dlgOwn = $("#show_fj_fw").clone();
			        fwgd.show_fj_fw = $("#show_fj_fw").dialog({
						position: ['center','center'],
						overflow:"auto",
						autoOpen: false, 
						height:$(window).height()*0.8,
						width:$(window).width()*0.5,
						show: "slide",
						modal:true,
						cache:false,
						/* hide: "explode", */
						title:"附件下载",
					    close : function() 
					    {
			            	dlgOwn.appendTo(dlgParent);
			            	$(this).remove();
	                    }
					});
			        var  str_path = fjaddress.replace(/\@/g,"\\");
	    		      var  str_file_name = fjname.replace(/\@/g,"\\");
			        $("#show_fj_fw").append("附件名称："+str_file_name+"<br/><a href='javascript:void(0);' onclick=mydownload('"+escape(str_path)+"');>点击下载</a>")
			        fwgd.show_fj_fw.dialog("open");
				},
				personDetails : function(orderid) {
					$("#fwgd_list").html("");
			        var dlgParent = $("#fwgd_list").parent();
			        var dlgOwn = $("#fwgd_list").clone();
			        fwgd.fwgd_list = $("#fwgd_list").dialog({
						position: ['center','center'],
						overflow:"auto",
						autoOpen: false, 
						height:$(window).height()*0.8,
						width:$(window).width()*0.5,
						show: "slide",
						modal:true,
						cache:false,
						/* hide: "explode", */
						title:"流程总览",
						close : function() 
						{
			            	dlgOwn.appendTo(dlgParent);
			            	$(this).remove();
	                    }
					});
								var url= "lczl!viewStep.action";
								var data =  {"orderId" : orderid};
									$.ajax({
										type:"POST",
										data:data,
										cache: false,
										async:false,
										url: url,
										success:function(result)
										{	
												var data=eval("("+result+")");
												var result = "";
												if(data!=""){
													result +=  '<table cellpadding="0" cellspacing="0"  class="table-y">'
														 +   '<tr>'
														 +       '<td colspan="7" style="font-size:12px;text-align:center;" bgcolor="#dddddd">个人流程详情</td>'
														 +   '</tr>';
													result +=  '<tr>'
														 +       '<td style="width:150px;font-size:12px;font-weight: bold;text-align:center">审批环节</td>'
														 +       '<td style="width:100px;font-size:12px;font-weight: bold;text-align:center">处理人</td>'
														 +       '<td style="width:200px;font-size:12px;font-weight: bold;text-align:center">职务</td>'
														 +       '<td style="width:200px;font-size:12px;font-weight: bold;text-align:center">流程发起时间</td>'
														 +       '<td style="width:200px;font-size:12px;font-weight: bold;text-align:center">流程结束时间</td>'
														 +       '<td style="width:200px;font-size:12px;font-weight: bold;text-align:center">批示结果</td>'
														 +       '<td style="width:200px;font-size:12px;font-weight: bold;text-align:center">ip地址</td>'
														 +   '</tr>';
													for (var i in data) {
														if(data[i].status != 'end'){
														var mark = data[i].MARK;
														var ip = data[i].IP;
														if(mark == '0'){
															mark = "同意";	
														}else if(mark == '-1'){
															mark = "不同意";
														}else{
															mark = "-- --";	
														} 
														
														if(typeof(ip) == "undefined"){
															ip = "-- --";
														}
															result += '<tr>'
																 +      '<td style="width:150px;font-size:12px;text-align:center">'+ data[i].DISPLAY_NAME +'</td>'
																 +      '<td style="width:150px;font-size:12px;text-align:center">'+ data[i].TRUENAME +'</td>'
																 +      '<td style="width:150px;font-size:12px;text-align:center">'+ data[i].ORG_INFOR +'</td>'
																 +      '<td style="width:200px;font-size:12px;text-align:center">'+ data[i].CREATE_TIME +'</td>';
																 if(data[i].FINISH_TIME == undefined){
																	 result += '<td style="width:200px;font-size:12px;text-align:center;color:red;">未处理</td>';
																 }else{
																	 result +=  '<td style="width:200px;font-size:12px;text-align:center;">'+ data[i].FINISH_TIME +'</td>';
																 }
																 result +=  '<td style="width:200px;font-size:12px;text-align:center;">'+ mark +'</td>';
																 result +=  '<td style="width:200px;font-size:12px;text-align:center;">'+ ip +'</td>';
																 result +=   '</tr>';
														}else{
															result +=  '<tr>'
																 +       '<td colspan="7" style="font-size:12px;text-align:center;" bgcolor="#dddddd">流程结束</td>'
																 +   '</tr>';
														}
													}		
													result +='</table>';
														
													}else{
														result +=  '<span style="font-size:14px;color:red">数据出错，未查到相应流程！</span>';
													}
												$("#fwgd_list").append(result);
										},
										error:function(result){
											$("#fwgd_list").append("<span style='font-size:14px;color:red'>数据出错，未查到相应流程！</span>");
										}
									});
									fwgd.fwgd_list.dialog("open");
				} 
			 
	 }
	 
	 
	 function mydownload(path)
	    {
	    	location.href='upload!download.action?&path='+ encodeURI(encodeURI(unescape(path))); 
	    }
		</script>
	</body>
</html>
