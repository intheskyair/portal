<%@ page contentType="text/html; charset=UTF-8"%>
<html>
    <head>
	<title>民主测评表配置管理</title>
		<script>x=-1;</script>			
	</head>	
	
	<body>
	
	<div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='../images/fold.png' onclick="showAndHide(this,'mzpc_grid');"  class="imgShrotate">
				<font>民主测评表配置管理</font>
			</h3>
		</div>
	<div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>
					<td class="wi-align-l" ><label for="input_1" class="lbl-1">测评标题：</label>
						<input type="text" id="title" name="title" class="wi-ipt-1" />
					</td>
					<td class="wi-align-l">
						<button id="btn_mzpcc_search" class="ui-state-default ui-state-qik">查询
						</button>
					<button id="btn_mzpc_empty" class="ui-state-default ui-state-qik">清空</button></td>
				</tr>
			</table>
		</div>
	</div>
	<div class="div div-2" style="">
		<table id="mzpc_grid" style="display:none;padding:0 0 0 0;"></table>
	</div>
	
	<div id="showUserList" style="display: none;">
	
	</div>
	
	<div style="display: none; margin-left: auto; 
    margin-right: auto;  overflow-x:hidden" id="showRadom">
		<textarea rows="10" cols="30" resize="none" id="radom" readonly="readonly" style="margin-top:10px; height:95%;overflow: auto;"></textarea>
	</div>
	
	<div style="display: none; margin-left: auto; 
    margin-right: auto;  overflow-x:hidden" id="updateRadom">
    <input type="hidden" id="upmainId"/>
		<textarea rows="10" cols="30" resize="none" id="upRadom" style="margin-top:10px; height:95%;overflow: auto;"></textarea>
	</div>
	
	
	<div id="dlg_mzpc" style="display:none;overflow: auto;">
		<input type="hidden" id="action" />
		<input type="hidden" id="dlg_id" /> 
		<input type="hidden" id="type" />
		<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl" style="">
			<tr>
				<td style="width:100px;"><label style="color:red;">*</label><label for="input_1">标题：</label></td>
				<td class="wi-align-l">
					<input type="text" id="dlg_title"  value="" class="wi-ipt-1"/>
				</td>
			</tr>
			<tr>
				<td style="width:100px;"><label style="color:red;">*</label><label for="input_1">测评表类型：</label></td>
				<td class="wi-align-l">
					<!-- <input type="text" id="dlg_type" value="" class="wi-ipt-1" /> -->
					<select id="dlg_type" class="wi-ipt-1">					
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="height: 200px;">
					<div id="pcb">
					
					</div>
				</td>
			</tr>
			<tr >
				<td style="width:100px;"><label style="color:red;">*</label><label for="input_1">开始时间：</label></td>
				<td class="wi-align-l">
					<input type="text" id="dlg_bg_time"
							readonly class="Wdate" style="width:200px;"
						onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'%y-%M-%d'})"/>
				</td>
			</tr>
			<tr>
					<td style="width:100px;"><label style="color:red;">*</label><label for="input_1">结束时间：</label></td>
					<td class="wi-align-l">
						<input type="text" id="dlg_ed_time"
							readonly class="Wdate" style="width:200px;"
						onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
					</td>
			</tr>
			<tr id="ds_pjxr">
				<td style="width:100px;"><label style="color:red;">*</label><label for="input_1">添加人员：</label></td>
				<td class="wi-align-l">
					<!-- <input type="text" id="dlg_qq_userlist" value="" class="wi-ipt-1" /> -->
					<div id="pjxr" style="width:700px;"></div>
				</td>
			</tr>	
			<tr id="ds_pjdj">
				<td style="width:100px;"><label style="color:red;">*</label><label for="input_1">评价等级：</label></td>
				<td class="wi-align-l">
					<!-- <input type="text" id="dlg_qq_userlist" value="" class="wi-ipt-1" /> -->
					<textarea rows="5" cols="30" resize="none" style="width:90%" id="dlg_pjdj">A,B,C,D</textarea>
				</td>
			</tr>	
			<tr id="ds_pwgr">
				<td style="width:100px;"><label style="color:red;">*</label><label for="input_1">单位或个人：</label></td>
				<td class="wi-align-l">
					<!-- <input type="text" id="dlg_phoneNum" value="" class="wi-ipt-1" valid="phone"/> -->
					<textarea rows="5" cols="15" style="width:90%"  id="dlg_dwgr"></textarea>
					<a href="javascript:depart()"  role="button" >
						<span class="ui-button-text choice" style="margin-top: 25px;">选择</span>
					</a>
					<!-- 对树结构/树列表匹配所需input -->
					<input type="hidden" id="dlg_dwgr_common_tree" style="width:200px"/>
					<input type="hidden" id="dlg_post_common_tree" style="width:200px"/>
					<input type="hidden" id="dlg_dwgr_common_list" style="width:200px"/>
					<input type="hidden" id="dlg_post_common_list" style="width:200px"/>
					<!-- end -->
				</td>
			</tr>	
			<tr id="ds_post">
				<td style="width:100px;"><label style="color:red;">*</label><label for="input_1">职务：</label></td>
				<td class="wi-align-l">
					<!-- <input type="text" id="dlg_phoneNum" value="" class="wi-ipt-1" valid="phone"/> -->
					<textarea rows="5" cols="30" resize="none" style="width:90%" id="dlg_post" ></textarea>
				</td>
			</tr>
			
			<tr>
				<td style="width:100px;"><label style="color:red;">*</label><label for="input_1">随机码个数：</label></td>
				<td class="wi-align-l">
					<input type="text" id="dlg_person"  value="" placeholder="最大人数为999" class="wi-ipt-1"/>
					<input type="hidden" id="dlg_person_edit"  value="" placeholder="最大人数为999" class="wi-ipt-1"/>
				</td>
			</tr>
			<tr id="ds_person">
				<td style="width:100px;"><label style="color:red;">*</label><label for="input_1">优秀或嘉奖最大数：</label></td>
				<td class="wi-align-l">
					<input type="text" id="max_person"  value="1" class="wi-ipt-1" onfocus="sumperson()"/>
				</td>
			</tr>
		</table>
	</div> 
	
	<div id="dlg_mzpclist_export" style="display:none">
		<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
			<tr>
				<td>登录账号<input type="checkbox" id="export-username" checked="checked"></input></td>
			</tr>
			<tr>
				<td>角色<input type="checkbox" id="export-auth" checked="checked"></input></td>
			</tr>
		</table>
	</div>
	
		
		<div id="departMent-mzpc" style="display:none;    overflow: scroll;">
			<div class="outer">
				<ul class="tab">
					<li class="current">按组织</li>
					<li>按条件</li>
	
				</ul>
	
				<div class="content">
					<ul name="tab_ul_t">
						<li class="current">
							<div id="dlg_rechargeList_table1">
								<ul id="treeDemo-mzpc" class="ztree" style="margin-top:0;background: #f0f6e4;"></ul>
							</div>
							<div>
								<a href="javascript:arrSplit()"  role="button" >
									<span class="ui-button-text choice" style="margin-top: 25px;">确认</span>
								</a>
							</div>
						</li>
					</ul>
					<ul name="tab_ul_t" style="display:none">
						<li>
							<div id="dlg_rechargeList_table">
									<div class="div-main-1" >
									<table cellpadding="0" cellspacing="0" border="0" class="table-1" >
										<tr>
											<td ><label for="input_1" class="lbl-1">员工姓名：</label></td>
											<td class="wi-align-l" >
												<input type="text" id="username" name="username" class="wi-ipt-1" />
											    <input type="hidden" id="department_id_tmp" />
											</td>
											<td ><label for="input_1" class="lbl-1">备注：</label></td>
											<td class="wi-align-l" >
												<select id="remark"  style="width:100px">
													<option value="">请选择备注</option>
													<option value="领导班子成员">领导班子成员</option>
													<option value="公务员">公务员</option>
													<option value="借用人员">借用人员</option>
													<option value="机关公勤">机关公勤</option>
												</select>
											</td>
											<td class="wi-align-l"><button id="btn_user_search_mz" style="width:74px; height:29px;font-size:14px;background-color:#3ea4e8;color: #fff">查询</button></td>
										</tr>
									</table>
								</div>
								<table id="user_tree_grid_t_mzpc" style="display:none;padding:0 0 0 0;"></table>
								<div>
									<a href="javascript:arrSplit()"  role="button" >
										<span class="ui-button-text choice" style="margin-top: 25px;">确认</span>
									</a>
								</div>		
							</div>
						</li>
					</ul>
	
				</div>
			</div>
	 </div>
		

		
		

		
		<script type="text/javascript">	
		
		 var data_all;
		 var paramGridHeight_mzpc = flexHeight("mzpc_grid");
		 var menuTree;
		 var treeNodes;
		 var treeObj;
         var dlg_mzpc;
         var dlg_radom;
         var dlg_upradom;
         var has_dlg_radom = function(){
        	 create_dlg_radom();
        	 dlg_radom.dialog("option", "title", "查看随机码").dialog("open");
         }
         var has_dlg_upradom = function(id){
        	 create_dlg_upradom();
        	 dlg_upradom.dialog("option", "title", "更新随机码").dialog("open");
        	 $("#upmainId").val(id);
         }
         
         var create_dlg_radom = function(){
         	var dlgParent = $("#showRadom").parent();
	        var dlgOwn = $("#showRadom").clone();
        	 dlg_radom = $("#showRadom").dialog({
     			autoOpen: false,
     			height:$(window).height()*0.6,
				width:$(window).width()*0.4,
     			modal:true,
     			cache:false,
     			/* hide: "explode", */
     			title:'随机码',
     			close : function() 
     			{
     			        clean_input_for_dailog($("#showRadom"));
	                	dlgOwn.appendTo(dlgParent);
	                	$(this).dialog("destroy").remove();
	            }
     		});
         }
         
         var create_dlg_upradom = function(){
          	var dlgParent = $("#updateRadom").parent();
 	        var dlgOwn = $("#updateRadom").clone();
         	 dlg_upradom = $("#updateRadom").dialog({
      			autoOpen: false,
      			height:$(window).height()*0.6,
 				width:$(window).width()*0.4,
      			modal:true,
      			cache:false,
      			/* hide: "explode", */
      			title:'随机码',
      			buttons:{
					"保存":function(){
						param_mzpc.updateRadom();
					}
				},
      			close : function() 
      			{
      			        clean_input_for_dailog($("#updateRadom"));
 	                	dlgOwn.appendTo(dlgParent);
 	                	$(this).dialog("destroy").remove();
 	            }
      		});
          }
         
         var has_dlg_mzpc = function(){
        	 create_dlg_mzpc();
        	 dlg_mzpc.dialog("option", "title", "新增民主测评配置").dialog("open");
         }
         
          var has_dlg_mzpce = function(){
        	 create_dlg_mzpc();
        	 dlg_mzpc.dialog("option", "title", "修改民主测评配置").dialog("open");
         }
         var create_dlg_mzpc = function(){
            var dlgParent = $("#dlg_mzpc").parent();
	        var dlgOwn = $("#dlg_mzpc").clone();
        	 dlg_mzpc = $("#dlg_mzpc").dialog({
					autoOpen: false,
					height:$(window).height()*0.8,
					width:$(window).width()*0.6,
					modal:true,
					cache:false,
					title:'新增配置',
					buttons:{
						"保存":function(){
							param_mzpc.saveParam();
						}
					},
				    close : function() {
	                	dlgOwn.appendTo(dlgParent);
	                	$(this).dialog("destroy").remove();
	                }
				});
         }
     
		
		 var param_mzpc = {
			//绑定参数信息grid
			param_grid_mzpc:"",
			bindParamGrid:function(queryP,queryM){
				param_mzpc.param_grid_mzpc = $("#mzpc_grid").flexigrid({
					url:"mzpc!MzpcList.action?operation=init",
					colModel: queryM,
					buttons : queryP,
					rowId:"id",
					showToggleBtn: true,
					usepager: true,
					title:"测评表配置列表",
					rp:page_num,
					showTableToggleBtn: false,
					resizable: false,
					height: paramGridHeight_mzpc,
					singleSelect: true,
					onChangePage:param_mzpc.loadData,
                    checkbox:true,
					filterParams : [{key : "title", name : "#title"}]
				});
			},						
			//新增和编辑参数信息
			editParam:function(com, grid)
			{
				
				if (com == "新增配置") 
				{
					org_arr = new Array();
				  	name_arr = new Array();
				    $.ajax({
				    		url : "mzpc!kh_dic.action",
							type : "get",
							dataType : "json",
							data: params,
							success : function(data){
								$("#dlg_type").table_head($("#pcb"),data);
								has_dlg_mzpc();
							}
				    });
				$("#dlg_title").val("");
			    $("#dlg_bg_time").val("");
			    $("#dlg_ed_time").val("");
			    $("#dlg_dwgr").val("");
			    $("#dlg_post").val("");
			    $("#dlg_person").val("");
			    $("#max_person").val("1");
			    $("#dlg_person_edit").val("");		    
			    $("#dlg_type").removeAttr("disabled");
			    $("#dlg_pjdj").removeAttr("disabled");
				$("#action").val("add");
				}
				else if (com == "修改配置") 
				{
					if($('.trSelected', grid).length==1)
					{
						var id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
						$("#dlg_id").val(id);
							var params=new Object();
				            params.id = id;
						    $.ajax({
							url : "mzpc!getMzpcInfo.action",
							type : "post",
							dataType : "json",
							data: params,
							success : function(data_res) 
							{		
								$("#dlg_title").val(data_res.TITLE);
								 var type = data_res.DIC_ID;
								  $.ajax({
							    		url : "mzpc!kh_dic.action",
										type : "get",
										dataType : "json",
										data: params,
										success : function(data){
											$("#dlg_type").table_head($("#pcb"),data,type,data_res);
											has_dlg_mzpce();
										}
							    });
								 $("#dlg_bg_time").val(data_res.BEGTIME);
								 $("#dlg_ed_time").val(data_res.ENDTIME);
								 $("#dlg_person").val(data_res.PERSON);
								  $("#dlg_person_edit").val(data_res.PERSON)
								    $("#dlg_type").attr("disabled","disabled");
								if(data_res.BTYPE != 3){
									 $("#dlg_pjdj").val(data_res.ZB);
									 $("#dlg_dwgr").val(data_res.PARAVALUE);
									 org_arr = new Array();
									    name_arr = new Array();
									    var prv = data_res.PARAVALUE.split(",");
									    for(var i in prv){
									    	name_arr.push(prv[i]);
									    }
									    $("#dlg_post").val(data_res.POST);
									    var pos = data_res.POST.split(",");
									    for(var i in pos){
									    	org_arr.push(pos[i]);
									    }
									    $("#max_person").val(data_res.MAX);
								    	$("#dlg_pjdj").attr("disabled","disabled");
								}
							    $("#action").val("edit");
							  
							    
							    	
							}});									
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_mzpc').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_mzpc').dialog('close');
						showMsg("未选中行！");
					}
				}
				else if(com == '删除配置')
				{
				   	if($('.trSelected', grid).length==1)
					{		
					       var id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
					       var title = $('.trSelected', grid).eq(0).find("td:eq(2)").text();
					        var indicate = "确认需要删除" + title+ ",这个民主测评活动吗?";	
					        var params=new Object();
				                params.id = id;					
							if(confirm(indicate)) 
							{
								$.ajax({
									type: "POST",
									url: "mzpc!delfMain.action",
									cache: false,
									data: params,
									success: function(msg)
									{
										var result=eval("("+msg+")");
										if (result.msg=="ok") 
										{
											showMsg("删除成功！");
											$('#dlg_mzpc').dialog('close');
											param_mzpc.loadData({});
										} else 
										{
											showMsg(result.msg);
											$('#dlg_mzpc').dialog('close');
										}
									}
								}); 
							}else{
								$('#dlg_mzpc').dialog('close');
							}
						
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_mzpc').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_mzpc').dialog('close');
						showMsg("未选中行！");
					}
				}			 				
			},
			saveAuth:function(){
				var admin_id ="";
				var username ="";
				if(document.authchoose.username_list2.options.length==0){
					showMsg("请选择用户！");
					return;
				}
				 for(var i=0;i<document.authchoose.username_list2.options.length;i++){
					admin_id += document.authchoose.username_list2.options[i].value+",";
					username += document.authchoose.username_list2.options[i].text+",";
				}
				$("#dlg_dwgr").val(username);
				$("#dlg_post").val(admin_id);
				$('#dlg_auth_distribute').dialog('close');
			},
			//全选
			selecAll : function() 
			{
				$("#dlg_mzpclist_export input").each(function(){
					$(this).attr("checked",true);
				});
			},					
			//保存参数
			saveParam:function()
			{	
				var params=new Object();
				params.title = $.trim(HtmlEncode($("#dlg_title").val()));
				console.log(params.title);
				if($.trim($("#dlg_person").val()) == $.trim($("#dlg_person_edit").val())){
					params.editType = 0;
				}else{
					params.editType = 1;
				}
				params.person = $.trim($("#dlg_person").val());
				params.max = $.trim($("#max_person").val());
				params.type = $("#dlg_type").val();
				params.pjdj = $("#dlg_pjdj").val();
				params.dwgr  = $("#dlg_dwgr").val().replace(/，/ig,',');
				params.begtime  = $("#dlg_bg_time").val();
				params.endtime  = $("#dlg_ed_time").val();
				params.post  = $("#dlg_post").val().replace(/，/ig,',');
				
			    // 提交方式
				var action = $("#action").val();	
				//新增的时候才判断密码，修改的时候不需要
					if(params.title==null || params.title=='')
					{
						showMsg("评测标题不能为空！");
						return;
					}
					if(params.type==null || params.type=='')
					{
						showMsg("请选择一个评测类型！");
						return;
					}
					if(params.begtime==null || params.begtime=='')
					{
						showMsg("请选择开始时间！");
						return;
					}
					if(params.endtime==null || params.endtime=='')
					{
						showMsg("请选择结束时间！");
						return;
					}
					if(params.person==null || params.person=='')
					{
						showMsg("请添加随机码个数");
						return;
					}else{
						var reg = /^[1-9]\d{0,2}$/;
						if(!reg.test(params.person)){
							showMsg("请输入小于1000的正整数");
							return;
						}
					}
					if($("#type").val() != "3" && $("#type").val() != "5"){
						if(params.max==null || params.max=='')
						{ 
							showMsg("请优秀和嘉奖的最大数");
							return;
						}else{
							var reg = /^[1-9]\d*$/;
							if(!reg.test(params.max)){
								showMsg("请输入大于0的正整数");
								return;
							}
						}
						
						if(params.dwgr==null || params.dwgr=='')
						{
							showMsg("请输入本次评测的人或者单位，多人请用','分割！");
							return;
						}
					}
					//时间判断
					var d1 = new Date(params.begtime.replace(/\-/g, "\/")); 
					var d2 = new Date(params.endtime.replace(/\-/g, "\/"));
					if(params.begtime!=""&&params.endtime!=""&&d1 >=d2){ 
						showMsg("开始时间不能大于结束时间！"); 
					  	return; 
					}
					if(params.pjdj==null || params.pjdj=='')
					{
						showMsg("评价等级不能为空！");
						return;
					}
					/*
					if(params.pjdj==null || params.pjdj=='')
					{
						showMsg("请输入评价的指标或等级，多个等级请用','分割");
						return;
					}*/
					
				
				// 新增和编辑通用字符串
				var url ='';
				var indicate='';
				if ("add" == action) 
				{
					indicate = "确认新增" + params.title + ",这个评测活动?";
					url = "mzpc!savepfMain.action";
				} 
				else if ("edit" == action) 
				{
					params.id = $("#dlg_id").val();
					indicate = "确认更新" + params.title + ",这个评测活动?(注：修改测评人数会重新生成随机码)";
					url = "mzpc!updatefMain.action";
				}
				var val;
				if($("#type").val() == 5){
					val = tjxr();
					if(val == 'error'){
						showMsg("添加人员姓名栏不能为空");
						return;
					}
					params.dwgr = val.name;
					params.sex = val.sex;
					params.birthday = val.birthday;
					params.oldjob = val.oldjob;
					params.newjob = val.newjob;
					params.jobtime = val.jobtime;
					//用于后台判断上述参数是否需要保存，1保存
					params.sss  = 1;
				}
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
								$("#departMent-mzpc").dialog("close");
								$('#dlg_mzpc').dialog('close');
								param_mzpc.loadData({});
							} else 
							{
								showMsg(result.msg);
							}
						}
					}); 
				}
			},
			loadData:function(param){
				var queryP = {};
				queryP.title = $('#title').val()==null?'':$('#title').val();
				var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
				queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
				queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
				queryP.sortname = param.sortname? param.sortname : "";
				queryP.sortorder = param.sortorder? param.sortorder : "";
				$.ajax({
					type: "POST",
					url: "mzpc!MzpcList.action?operation=init",
					cache: false,
					data: queryP,
					success: function(msg){
						var data=eval("("+msg+")");
						try{
							param_mzpc.param_grid_mzpc.flexAddData(data);
						}catch(e){
							showErrorMsg("查询结果信息出错！");
						}
					}
				}); 
			},
			loadDataUser:function(){
				var queryP = {};
				queryP.username = $.trim($("#username").val());
				$.ajax({
					type: "POST",
					url: "queryUser!getUserList.action",
					cache: false,
					data: queryP,
					success: function(result){
					$("#username_list1 option").remove();
						var data=eval("("+result+")");
							var html = '';
							for (var i in data) {
								var flag = false;
								for(var j=0;j<document.authchoose.username_list2.options.length;j++){
										if(document.authchoose.username_list2.options[j].text == data[i].TRUENAME){
											flag = true;
										}
								}
									if(!flag){
										html += '<option value="'+data[i].ORG_INFOR+'">'+data[i].TRUENAME+'</option>';
									}
								}
								$("#username_list1").append(html);
					}
				}); 
			},
			 showRadom : function(id){
				 $.ajax({
						type: "POST",
						url: "mzpc!showRadom.action",
						cache: false,
						data: {id:id},
						success: function(result){
							var rs = JSON2.parse(result);
							if(rs.length == 0){
								showMsg("随机码不存在");
							}else{
								$("#radom").html("");
								for(var i in rs){
									$("#radom").append(rs[i].RADOM+",\n");
								}
								has_dlg_radom();
							}
						}
					});
				 
				
			},
			 upRadom : function(id){
				 $.ajax({
						type: "POST",
						url: "mzpc!showRadom.action",
						cache: false,
						data: {id:id},
						success: function(result){
							var rs = JSON2.parse(result);
							if(rs.length == 0){
								showMsg("随机码不存在");
							}else{
								$("#upRadom").html("");
								for(var i in rs){
									$("#upRadom").append(rs[i].RADOM+",\n");
								}
								has_dlg_upradom(id);
							}
						}
					});
				 
				
			},updateRadom:function(){
				var val = $("#upRadom").val();
				val.replace(/，/ig,',');
				if(confirm("是否重新生成此测评表的随机码")) 
				{
					  $.ajax({
							type: "POST",
							url: "mzpc!upRadom.action",
							cache: false,
							data: {mainid:$("#upmainId").val(),radom:$.trim(val)},
							success: function(result){
								if(result == 'success'){
									 $('#updateRadom').dialog('close');
									 param_mzpc.loadData({});
								}
							}
						});	
				}
				 		
				
			}
		};
	
		    var param_user_mzpc = {
		    		param_grid_user:"",
					//绑定参数信息grid
					bindParamGrid:function(queryP,queryM){
						param_user_mzpc.param_grid_user = $("#user_tree_grid_t_mzpc").flexigrid({
							url:"queryUser!queryUser.action?operation=init",
							colModel: queryM,
							buttons : queryP,
							rowId:"id",
							showToggleBtn: true,
							usepager: true,
							title:"用户列表",
							rp:pageSize,
							rp:page_num,
							showTableToggleBtn: false,
							resizable: false,
							//height: paramGridHeight_lczl,
							height:$(window).height()*0.5,
							singleSelect: true,
							onChangePage:param_user_mzpc.loadData,
							checkbox:true,
							filterParams : []
						});
					},
				    selectAccount : function () {
						var tr_len = $('#user_tree_grid_t_mzpc .trSelected').length;
						var strs_name = new Array();//姓名
						var strs_zw = new Array();//职务
						for(var i=0;i<tr_len;i++){
							strs_name.push( $('#user_tree_grid_t_mzpc .trSelected').eq(i).find("td:eq(2)").text());
							strs_zw.push($('#user_tree_grid_t_mzpc .trSelected').eq(i).find("td:eq(4)").text());
						}
						$("#dlg_dwgr_common_list").val(strs_name);
						$("#dlg_post_common_list").val(strs_zw);
				    },
					loadData:function(param){
						var queryP = {};
						queryP.username = $('#username').val()==null?'':$('#username').val();
						queryP.remark = $('#remark').val()==null?'':$('#remark').val();
						var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
						queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
						queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
						$.ajax({ 
							type: "POST",
							url: "queryUser!queryUser.action?operation=init",
							cache: false,
							data: queryP,
							success: function(msg){
								var data=eval("("+msg+")");
								try{
									param_user_mzpc.param_grid_user.flexAddData(data);
								}catch(e){
									//console.log(e);
									showErrorMsg("查询结果信息出错！");
								}
							}
						}); 
					}
		}	 
	 $(function(){
		 
		//初始化所有操作按钮
		 var  parm_button = [{name: '新增配置', bclass: 'add', onpress : param_mzpc.editParam},
							{name: '修改配置', bclass: 'edit', onpress : param_mzpc.editParam},
							{name: '删除配置', bclass: 'delete', onpress : param_mzpc.editParam}];
		 //权限操作按钮
		 var colModel = [						
		                {display: '序号', name : 'xh', width : 40, align: 'center'},
						{display: 'id', name : 'ID', width : 40, align: 'center',hide : true,sortable: true},
						{display: '评测标题', name : 'TITLE', width : 300, align: 'left',sortable: true},
						{display: '评测类型', name : 'DIC_NAME', width : 150, align: 'left',sortable: true},
						{display: '开始时间', name : 'BEGTIME_FORMAT_DATE', width : 120, align: 'left',sortable: false},
						{display: '结束时间', name : 'ENDTIME_FORMAT_DATE', width : 120,  align: 'left',sortable : false},
						{display: '状态', name : 'STATUS', width : 100,  align: 'left',sortable: true,						
							process:function(obj,pid)
							{
								if ($(obj).html() == "0") 
								{
								   $(obj).html("有效");
								   $(obj).css({color:"blue"});
								}
								else 
								{
									if ($(obj).html() == "0") 
									{
									   $(obj).html("有效");
									   $(obj).css({color:"blue"});
									}
									else 
									{
										$(obj).html("无效");
										$(obj).css({color:"red"});								
									}
							    }					
							}
						 },
						{display:'预览',width:100,align:'center',
							process:function(obj,pid,$td){
								$tr = $td.closest("tr");
								var id = $('div', $tr).eq(1).text();
								$(obj).html('<span><a href="#" onclick="showpc(' + id +');" style="color:blue;">预览</a></span>');
							}
					    },
					    {display: '查看随机码', width : 100, align: 'center',sortable: true,
					    	process:function(obj,pid,$td){
								$tr = $td.closest("tr");
								var id = $('div', $tr).eq(1).text();
								$(obj).html('<span><a href="#" onclick="param_mzpc.showRadom(' + id +');" style="color:blue;">查看随机码</a></span>');
							}
					    },
					    {display: '更新随机码', width : 100, align: 'center',sortable: true,
					    	process:function(obj,pid,$td){
								$tr = $td.closest("tr");
								var id = $('div', $tr).eq(1).text();
								$(obj).html('<span><a href="#" onclick="param_mzpc.upRadom(' + id +');" style="color:blue;">更新随机码</a></span>');
							}
					    }
						];
		 	 			// 初始化数据
		 param_mzpc.bindParamGrid(parm_button,colModel);
		
	
		 //获得当前菜单的id
		 /*
		 var menu_id;
		 $('#tabs ul li').each(function(){
			if($(this).hasClass("ui-tabs-selected")){
				var this_id = $(this).children("div").children("a").attr("href");
				menu_id =this_id.substring(5);
				}
			});
		 
		 // 根据菜单id，初始化操作按钮
		 $.ajax({
			type: "GET",
			url: "doauth!getButtons.action?menu_id="+menu_id,
			cache: false, 
			success: function(data){
				var data=eval("("+data+")");
				for (var i in data.buttons) {
					var buttonName = data.buttons[i].BUTTON_NAME;
					$(parm_button).each(function(index){
						var val = parm_button[index].name;
						if(val == buttonName){
							buttons.push(parm_button[index]);
							buttons.push({separator: true});
						}
					});
				}
			}
		});
	    */
	
		/*参数管理-查询按钮功能*/
		 $('#btn_mzpcc_search').button({ icons: {primary:'ui-icon-search'} }).on("click",function(){
			param_mzpc.loadData({page:1});
		});
		$('#btn_mzpc_search1').button({ icons: {primary:'ui-icon-search'} });
		 $(document).off("click","#btn_mzpc_search1").on("click","#btn_mzpc_search1",function(){
			param_mzpc.loadDataUser();
		 });
		 
		/* $('#btn_mzpc_search1').button({ icons: {primary:'ui-icon-search'} }).on("click",function(){
			param_mzpc.loadDataUser();
		}); */
		//清空筛选框
		 $('#btn_mzpc_empty').on("click",function(){
			$("#title").val("");
			//param_mzpc.loadData({page:1});
		});
		
	});	
		 function showpc(id){
		 	var mainId = parseInt(id);
		 	$.ajax({
		 		type: "get",
				url: "mzpc!jumpHtml.action",
				cache: false,
				data: {mainid:mainId},
				success: function(data){
					var res = (new Function("","return " + data))();
					var option = {};
					option.key = res.mainid;
					option.title = "预览";
					option.url = "../" + res.url + "?mainid=" + res.mainid;
					option.isIframe = true;
					addTab(option);
				}
		 	});
		 }
		 
		 

  //ztree

		var org_arr = new Array();
  	    var name_arr = new Array();
		function onChooseOrg(e, treeId, treeNode) {
			var orgId = treeNode.ORG_ID;
			var org_infor = treeNode.ORG_INFOR;
			if(orgId.indexOf("_personId_") != -1 && treeNode.checked){
				
				//zTree.expandNode(treeNode);
				if($("#dlg_dwgr_common_tree").val().indexOf(treeNode.ORG_NAME) == -1){
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
	//初始化所有操作按钮
	 var  parm_button_u = [];
	 //权限操作按钮
	 var colModel_u =  [
					{display: '序号', name : 'xh', width: 40, align: 'center',hide : true},
					{display: '登录账号', name : 'USERNAME',width:50, sortable: true,hide : true},
					{display: '员工姓名', name : 'TRUENAME',width:60, align: 'center',sortable: true},
					{display: '部门名称', name : 'ORG_NAME',width:170,  align: 'center',sortable: true},
					{display: '岗位信息', name : 'ORG_INFOR',width:200, align: 'center',hide : true},
					{display: '备注', name : 'REMARK', width : 70, align: 'center',hide : true}
				];
	 	 			// 初始化数据
	 param_user_mzpc.bindParamGrid(parm_button_u,colModel_u);
	//*****************************param_user_mzpc.selectAccount***************************************
	 $("#user_tree_grid_t_mzpc ").click(param_user_mzpc.selectAccount);
	 //********************************************************************* 	 
	/*参数管理-查询按钮功能*/
	$("#btn_user_search_mz").button({ icons: {primary:'ui-icon-search'} }).on("click",function(){
		param_user_mzpc.loadData({page:1});
	});
 
	 $("#departMent-mzpc .tab li").click(function() {
			$("#departMent-mzpc .tab li").removeClass("current");
			$(this).addClass("current");
			var n = $(this).index();
			if(n == 1){
				param_user_mzpc.loadData({page:1});
			}
			$("#departMent-mzpc .content ul[name='tab_ul_t']").hide();
			$("#departMent-mzpc .content ul[name='tab_ul_t']").eq(n).show();
	});
	
	$("#departMent-mzpc .tab li").eq(0).click();
	  var dlgParent = $("#departMent-mzpc").parent();
      var dlgOwn = $("#departMent-mzpc").clone();
	 var depart  = $("#departMent-mzpc").dialog({
					position: ['right','middle'],
        			draggable :true,     
        			autoOpen: false,
					height:$(window).height()*0.82,
					width:$(window).width()*0.3,
					show: "slde",
					/* modal:true, */
					cache:false,
					/* hide: "explode", */
					title:'部门列表',
					onClose:function()
			      {  
	            	dlgOwn.appendTo(dlgParent);
	            	$(this).dialog("destroy").remove();
	            	$("#dlg_dwgr_common_tree").val("");
	            	$("#dlg_post_common_tree").val("");
	            	$("#dlg_dwgr_common_list").val("");
	            	$("#dlg_post_common_list").val("");
                 } 
        	}).dialog("open");
    	$.ajax({
			type: "POST",
			url: "docum!queryOrganizationInfos.action?math_radom="+Math.random(),
			cache: false,
			data: null,
			success: function(msg){
				var data=eval("("+msg+")");
				$.fn.zTree.init($("#treeDemo-mzpc"), setting, data);
				/* treeObj  =$.fn.zTree.getZTreeObj("treeDemo");
				//var nodes = treeObj.transformToArray();
				var nodes = treeObj.getNodes();
				for (var i = 0; i < nodes.length; i++) { //设置节点展开
					var nodes2 = nodes[i].children;
					for (var j = 0; j < nodes2.length; j++) {
						treeObj.expandNode(nodes2[j], true, false, true);
					}
	            } */
				treeObj  =$.fn.zTree.getZTreeObj("treeDemo-mzpc");
				//var nodes = treeObj.transformToArray();
				var nodes = treeObj.getNodes();
				var nodes2;
// 				for (var i = 0; i < nodes.length; i++) { //设置节点展开
					nodes2 = nodes[0].children;
// 					console.log("i:"+i+"-nodes1.length-"+nodes.length+"-nodes2.length:"+nodes2.length);
					for (var j = 1; j < nodes2.length; j++) {
						treeObj.expandNode(nodes2[j], true, false, true);
					}
// 	            }


					var val = $("#dlg_dwgr").val();
					if($.trim(val).length != 0){
						var array =	val.split(",");
						var node;
						for(var i = 0 ; i < array.length ; i++){
							node  =treeObj.getNodeByParam("ORG_NAME",array[i],null);
							treeObj.checkNode(node,true, true);
//	 						checkParentNode(node.SUPER_ORG_ID);
						}
					}				
			}
		});
    }
	function add_html(){
		$("#dlg_dwgr_common_tree").val(name_arr);
		$("#dlg_post_common_tree").val(org_arr);
		//去重
	}
   
	function checkParentNode(super_Id){
		var node  =treeObj.getNodeByParam("ORG_ID",super_Id);
		treeObj.checkNode(node,true, false);
		if(node.SUPER_ORG_ID != 0){
			checkParentNode(node.SUPER_ORG_ID);
		}
	}
	   
    	
	function sumperson(){
		var dwr = $("#dlg_dwgr").val().split(",");
		if(dwr != 0){
			$("#max_person").val(Math.ceil(dwr.length * 20 / 100));	
		}
	}
	
	
	function showOrgSub(){
		arrSplit();
	}
	
	//匹配树列表和树结构数据，去重
	function arrSplit(){
		  var dstr = $('#dlg_dwgr_common_tree').val()+","+$('#dlg_dwgr_common_list').val();  
		  var pstr = $('#dlg_post_common_tree').val()+","+$('#dlg_post_common_list').val();  
          var strArr=dstr.split(",");//把字符串分割成一个数组  
          strArr.sort();//排序  
          var result = new Array();//创建出一个结果数组  
          var tempStr = "";  
          for(var i in strArr){  
               if(strArr[i] != tempStr){  
                    result.push(strArr[i]);  
                    tempStr = strArr[i];  
               }else{  
                    continue;  
               }  
          }
          $('#dlg_dwgr').val(result)//把数组连成字符串并展示到页面  
          
          var strArr_p = pstr.split(",");//把字符串分割成一个数组  
          strArr_p.sort();//排序  
          var result_p = new Array();//创建出一个结果数组  
          var tempStr_p = "";  
          for(var i in strArr_p){  
               if(strArr_p[i] != tempStr_p) {  
                    result_p.push(strArr_p[i]);  
                    tempStr_p = strArr_p[i];  
               }else{  
                    continue;  
               }  
          }
          $('#dlg_post').val(result_p)//把数组连成字符串并展示到页面  
          
          $("#departMent-mzpc").dialog("close");
	}
		</script>
	</body>
</html>
