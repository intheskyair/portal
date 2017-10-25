<%@ page contentType="text/html; charset=UTF-8"%>
<html>
    <head>
	<title>后台用户管理</title>
	  <script>x=-26</script>
	</head>
	<body>	
	<!-- <div class="div div-1">
		<div class="title title-1">
			<h3>
				<img src='../images/fold.png' onclick="showAndHide(this,'user_grid');"  class="imgShrotate">
				<font>后台用户管理</font>
			</h3>
		</div>	
	</div> -->
	<div class="div div-2"  style="width:246px;position: absolute;">
		<div class="div-main-1 div-height">
			<span style="font-weight: bold;">部门列表</span>
		</div>
		<div id="organ_table" class="ztree">
		</div>
	</div>
	<div class="div div-2" style="width: 85%;position: absolute;margin-left:250px;">
		<div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1" >
				<tr>
					<td style="width:100px;"><label for="input_1" class="lbl-1">员工姓名：</label></td>
					<td class="wi-align-l" style="width:170px;">
						<input type="text" id="username" name="username" class="wi-ipt-1" />
					    <input type="hidden" id="department_id_tmp" />
					</td>
					<!-- 
					<td style="width:100px;"><label for="input_1" class="lbl-1">手机号码：</label></td>
					<td class="wi-align-l" style="width:170px;">
						<input type="text" id="mobile" name="mobile" class="wi-ipt-1" />
					</td>
					 -->
					 
					 <td style="width:100px;"><label for="input_1" class="lbl-1">岗位信息：</label></td>
					<td class="wi-align-l" style="width:170px;">
						<input type="text" id="org_infor" name="org_infor" class="wi-ipt-1" />
						
					</td>
					<td class="wi-align-l"><a href="#" id="btn_user_search">查询</a>
					<button id="btn_user_empty" class="ui-state-default ui-state-qik">清空</button></td>
				</tr>
			</table>
		</div>
		<div>
			<table id="user_grid" style="display:none;padding:0 0 0 0;"></table>
		</div>
	</div>
	<div id="dlg_user" style="display:none;overflow: scroll;">
		<input type="hidden" id="action" />
		<input type="hidden" id="dlg_id" /> 
		<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
			<tr>
				<td style="width:100px;"><label style="color:red;">*</label><label for="input_1">登录账号：</label></td>
				<td class="wi-align-l" style="width:200px;">
					<input type="text" id="dlg_name"  value="" class="wi-ipt-1" onblur="judgeName()" valid="uname"  />
				</td>
			</tr>
			<tr>
				<td style="width:100px;"><label style="color:red;">*</label><label for="input_1">员工姓名：</label></td>
				<td class="wi-align-l" style="width:200px;">
					<input type="text" id="dlg_truename" value="" class="wi-ipt-1" />
				</td>
			</tr>
			<tr id="pwd_show">
				<td style="width:100px;"><label style="color:red;">*</label><label for="input_1">密码：</label></td>
				<td class="wi-align-l" style="width:200px;">
					<input type="password" id="dlg_password" value="" class="wi-ipt-1"/>
					<br>
					<span style="width: 200px;color:red;font-size:10px;">必须数字,字母,特殊字符组合的6-20位</span>
				</td>
			</tr>
				<tr id="qrwd_show">
					<td style="width:100px;"><label style="color:red;">*</label><label for="input_1">确认密码：</label></td>
					<td class="wi-align-l" style="width:200px;">
						<input type="password" id="dlg_qr_password" value="" class="wi-ipt-1"/>
					</td>
				</tr>
			<tr>
				<td style="width:100px;"><label for="input_1">QQ号码：</label></td>
				<td class="wi-align-l" style="width:200px;">
					<input type="text" id="dlg_qq_userlist" value="" class="wi-ipt-1" />
				</td>
			</tr>	
			<tr>
				<td style="width:100px;"><label style="color:red;">*</label><label for="input_1">联系电话：</label></td>
				<td class="wi-align-l" style="width:200px;">
					<input type="text" id="dlg_phoneNum" value="" class="wi-ipt-1" valid="phone"/>
				</td>
			</tr>	
			<tr>
				<td style="width:100px;"><label for="input_1">邮箱：</label></td>
				<td class="wi-align-l" style="width:200px;">
					<input type="text" id="dlg_email_user" value="" class="wi-ipt-1"/>
				</td>
			</tr>
			<tr>
				<td style="width:100px;"><label style="color:red;">*</label><label for="input_1">角色：</label></td>
				<td class="wi-align-l" style="width:200px;" >
                  <div id="privilegeId"></div>
				</td>
			</tr>
			<tr>
				<td style="width:100px;"><label for="input_1">部门名称：</label></td>
				<td class="wi-align-l" style="width:200px;" >
					<input type="text" id="dlg_department" value="" class="wi-ipt-1" />
					<input type="text" id="department_id" value="" class="wi-ipt-1" style="display:none;"/>
					<div class="choice_y">
					<a href="javascript:department_menu(dlg_id.value)" role="button">
						<span class="ui-button-text choice" style="float: left;">选择</span>
					</a>
					<a href="javascript:removeDep()" role="button">
						<span class="ui-button-text choice">清空</span>
					</a>
					</div>
				</td>
			</tr>
			<tr>
				<td style="width:100px;"><label for="input_1">岗位信息：</label></td>
				<td class="wi-align-l" style="width:200px;">
					<textarea cols="8" style="height:90px" type="text" id="dlg_org_information" value="" class="wi-ipt-1"/>
				</td>
			</tr>
			<tr>
				<td style="width:100px;"><label for="input_1">备注：</label></td>
				<td class="wi-align-l" style="width:200px;">
					<input type="text" id="dlg_remark" value="" class="wi-ipt-1" />
				</td>
			</tr>
			<tr>
				<td style="width:100px;"><label for="input_1">借用部门：</label></td>
				<td class="wi-align-l" style="width:200px;" >
					<input type="text" id="dlg_department_2" value="" class="wi-ipt-1"/>
					<input type="text" id="department_id_2" value="" class="wi-ipt-1" style="display:none;"/>
						<div class="choice_y">
					<a href="javascript:department_menu_2(dlg_id.value)" role="button">
						<span class="ui-button-text choice" style="float: left;">选择</span>
					</a>
					<a href="javascript:removeJieYongDep()" role="button">
						<span class="ui-button-text choice">清空</span>
					</a>
					</div>
				</td>
			</tr>
		</table>
	</div> 
	<div id="dlg_userlist_export" style="display:none">
		<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
			<tr>
				<td>登录账号<input type="checkbox" id="export-username" checked="checked"></input></td>
				<td>员工姓名<input type="checkbox" id="export-truename" checked="checked"></input></td>
				<td>账号状态<input type="checkbox" id="export-status" checked="checked"></input></td>
				<td>QQ号码<input type="checkbox" id="export-qq" checked="checked"></input></td>
				<!--<td>手机号码<input type="checkbox" id="export-phone" checked="checked"></input></td> 
					<td>邮箱<input type="checkbox" id="export-email" checked="checked"></input></td> -->
			</tr>
			<tr>
				<td>角色<input type="checkbox" id="export-auth" checked="checked"></input></td>
				<td>部门名称<input type="checkbox" id="export-department" checked="checked"></input></td>
				<td>岗位信息<input type="checkbox" id="export-org_infor" checked="checked"></input></td>
				<!--<td>业务范围<input type="checkbox" id="export-area" checked="checked"></input></td>
				<td>名下渲染费用<input type="checkbox" id="export-rendermoney" checked="checked"></input></td>-->
			</tr>
		</table>
	</div>
	<!-- 批量分配角色 -->
	<div id="dlg_auth_distribute" style="display:none">
		<form method = "post" name ="authchoose">
			<table border="0">
				<tr style="height:35px">
					<td class="wi-align-l" style="">待分配用户：</td>
					<td class="wi-align-l" ></td>
					<td class="wi-align-l" style="">已选择用户：</td>
					<td class="wi-align-l" style="">请选择角色：</td>
				</tr>
				<tr>
					<td>
						<select multiple name="username_list1" id="username_list1" class="wi-sel-1" style="width:130px;height:270px" >
						</select>
					</td>
					<td style="text-align:center">
						<input style="width:50px" type ="button" value ="添加" onclick="moveOption(document.authchoose.username_list1,document.authchoose.username_list2);"><br/><br/>
						<input style="width:50px" type ="button" value ="移除" onclick="moveOption(document.authchoose.username_list2,document.authchoose.username_list1);"><br/>
					</td>
					<td >
						<select multiple name="username_list2" id="username_list2" class="wi-sel-1" style="width:130px;height:270px" >
						</select>
					</td>
					<td >
						<div style="font-size:14px;padding-left:5px;text-align: left;margin-left: 10px;overflow-y:scroll;overflow-x:auto;width:120px;height:270px;border:1px solid #ccc" id="authChoose"></div>
					</td>
				</tr>
			</table>
		</form>
	</div> 
<div id="dlg_department_menu" style="display:none">  
	<div style="overflow:auto;">
		<ul id="dlg_department_menu_tree" class="ztree">
		</ul>
	</div>
	<input type="hidden" id="dlg_department_id" /> 
</div>
<div >
	<ul id="department_tree" class="ztree">
	</ul>
</div>
		<script>	
		function removeDep(){
			$("#dlg_department").val("");
			$("#department_id").val("");
		}
		function removeJieYongDep(){
			$("#dlg_department_2").val("");
			$("#department_id_2").val("");
		}
		
			
		 var data_all;
		 var paramGridHeight_user = flexHeight("user_grid");
		 var menuTree;
		 var treeNodes;
		 var param_user = {
		 	 param_grid_user:"",
		 	 dlg_user:"",
		 	 dlg_userlist_export:"",
			//绑定参数信息grid
			bindParamGrid:function(queryP){
				param_user.param_grid_user = $("#user_grid").flexigrid({
					url:"queryUser!queryUser.action?operation=init",
					colModel : [
						{display: '序号', name : 'xh', width :25, align: 'center'},
						{display: 'id', name : 'ADMIN_ID', width :25, align: 'center'},
						{display: '登录账号', name : 'USERNAME', width :65, align: 'left',sortable: true},
						{display: '员工姓名', name : 'TRUENAME', width :60, align: 'left',sortable: true},
						{display: '手机号码', name : 'PHONE', width : 100,  align: 'left',hide : true},
						{display: '邮箱', name : 'EMAIL', width : 150,  align: 'left',hide : true},
						{display: 'QQ号码', name : 'QQ', width : 70, align: 'center'},
						{display: '账号状态', name : 'STATUS', width : 50,  align: 'left',sortable: true,						
							process:function(obj,pid)
							{
								if ($(obj).html() == "0") 
								{
								   $(obj).html("正常");
								   $(obj).css({color:"blue"});
								}
								else 
								{
									$(obj).html("被冻结");
									$(obj).css({color:"red"});								
								}
						    }					
						},						
						{display: '角色', name : 'AUTH', width : 130, align: 'center',sortable: true},
						{display: '部门名称', name : 'ORG_NAME', width : 180, align: 'center',sortable: true},
						{display: '岗位信息', name : 'ORG_INFOR', width : 200, align: 'center'},
						{display: '部门id', name : 'ORG_ID', width : 220, align: 'center',hide : true},
						{display: '岗位信息', name : 'JODS_INFOR', width : 200, align: 'center',hide : true},
						{display: '借用id', name : 'ORG_ID_LEND', width : 220, align: 'center',hide : true},
						{display: '借用部门', name : 'ORG_NAME_LEND', width :180, align: 'center',
						  	process:function(obj)
							{
								if ($(obj).html() == "null") 
								{
								   $(obj).html("");
								}
						    }	
						},
						{display: '备注', name : 'REMARK', width :140, align: 'center',
						  	process:function(obj)
							{
								if ($(obj).html() == "null") 
								{
								   $(obj).html("");
								}
						    }							
						}
					],
					buttons :queryP,
					rowId:"id",
					showToggleBtn: true,
					usepager: true,
					title:"",
					//rp:pageSize,
					rp:page_num,
					showTableToggleBtn: false,
					resizable: false,
					height: paramGridHeight_user+20,
					singleSelect: false,
					onChangePage:param_user.loadData,
					checkbox:true,
					filterParams : [{key : "username", name : "#username"},
					                {key : "org_infor", name : "#org_infor"}]
				});
				
				
			},

			// 重置密码
		    resetPasswd : function(user_id,user_name) {
			// 渲染任务id
			if(confirm("你确定需要重置"+user_name+"这个运维用户的密码?")) 
			{
				$.ajax({
					type:"POST",
					data:{"user_id" : user_id},
					cache: false,
					url: "queryUser!resetpwd.action",
					success:function(msg)
					{
						var result=eval("("+msg+")");
						if (result.msg=="ok") 
						{
					      showMsg("重置密码成功！");
					      $('#dlg_user').dialog('close');
					    }
					    else
					    {
					      showMsg(result.msg);
					      $('#dlg_user').dialog('close');
					    }
					},
					error:function()
					{
						showErrorMsg("重置密码出错！");
						$('#dlg_user').dialog('close');
					}
				});
			}
		  },
			// 重置密码
		    resetPassword : function(com,grid) {
		    	if($('.trSelected', grid).length==1)
				{
		    		var user_name = $('.trSelected', grid).eq(0).find("td:eq(2)").text();
					var user_id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
					if(confirm("你确定需要重置"+user_name+"这个运维用户的密码?")) 
					{
						$.ajax({
							type:"POST",
							data:{"user_id" : user_id},
							cache: false,
							url: "queryUser!resetpwd.action",
							success:function(msg)
							{
								var result=eval("("+msg+")");
								if (result.msg=="ok") 
								{
							      showMsg("重置密码成功！密码为: 1234abcd");
							      $('#dlg_user').dialog('close');
							    }
							    else
							    {
							      showMsg(result.msg);
							      $('#dlg_user').dialog('close');
							    }
							},
							error:function()
							{
								showErrorMsg("重置密码出错！");
								$('#dlg_user').dialog('close');
							}
						});
					}
				}
				else if($('.trSelected', grid).length>1)
				{
				    $('#dlg_user').dialog('close');
					showMsg("不能同时选择多行记录！");
				}else
				{
				    $('#dlg_user').dialog('close');
					showMsg("未选中行！");
				}
			},
						
			//新增和编辑参数信息
			editParam:function(com, grid){
		        var dlgParent = $("#dlg_user").parent();
	            var dlgOwn = $("#dlg_user").clone();
				param_user.dlg_user = $("#dlg_user").dialog({
					autoOpen: false,
					height:$(window).height()*0.8,
					width:$(window).width()*0.5,
					show: "slide",
					modal:true,
					cache:false,
					/* hide: "explode", */
					title:'参数',
					buttons:{
						"保存":function(){
							param_user.saveParam();
						}
					},
				    close : function() {
	                	dlgOwn.appendTo(dlgParent);
	                	$(this).dialog("destroy").remove();
	                }
				});
				if (com == '新建') 
				{
				    $("#pwd_show").show();
				    $("#qrwd_show").show();
				    $("#dlg_name").attr("readonly",false);
					$("#dlg_name").val("");
					$("#dlg_truename").val("");
					$("#dlg_password").val("");
					$("#dlg_qr_password").val("");
					$("#dlg_phoneNum").val("");
					$("#dlg_qq_userlist").val("");
					$("#dlg_email_user").val("");
					$("#dlg_id").val("");	
				    $.ajax({
					url : "queryUser!readprivileges.action",
					type : "post",
					dataType : "json",
					data: params,
					success : function(data) 
					{
						if (data.list_all) 
						{
						    data_all = data.list_all;								    						 
							for ( var i = 0; i < data_all.length; i++) 
							{

							   $("#privilegeId").append("<span style='display:inline-block'><input type='checkbox' name='auth' value="+data_all[i].ID+">"+data_all[i].AUTHNAME+"</span>");			
				
						    }
						}
					}});
					$("#action").val("add");
					param_user.dlg_user.dialog("option", "title", '新增后台用户').dialog("open");
				}
				else if (com == '修改') 
				{
					if($('.trSelected', grid).length==1)
					{
						var name = $('.trSelected', grid).eq(0).find("td:eq(2)").text();
						var id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
						var truename = $('.trSelected', grid).eq(0).find("td:eq(3)").text();
						var phoneNum = $('.trSelected', grid).eq(0).find("td:eq(4)").text();
						var email = $('.trSelected', grid).eq(0).find("td:eq(5)").text();		
						var qq = $('.trSelected', grid).eq(0).find("td:eq(6)").text();		
						var department = $('.trSelected', grid).eq(0).find("td:eq(9)").text();		
						var departmentid = $('.trSelected', grid).eq(0).find("td:eq(11)").text();
						var departmentid_2 = $('.trSelected', grid).eq(0).find("td:eq(13)").text();
						var org_infor = $('.trSelected', grid).eq(0).find("td:eq(10)").text();		
						//var salesplace = $('.trSelected', grid).eq(0).find("td:eq(13)").text();		
						var department_t = $('.trSelected', grid).eq(0).find("td:eq(14)").text();	
						var remark = $('.trSelected', grid).eq(0).find("td:eq(15)").text();	
						//var privilegeName = $('.trSelected', grid).eq(0).find("td:eq(6)").text();	
						$("#dlg_name").val($.trim(name));
						$("#dlg_name").attr("readonly",true);
						$("#dlg_truename").val($.trim(truename));
						$("#dlg_remark").val($.trim(remark));
						$("#dlg_phoneNum").val($.trim(phoneNum));
						$("#dlg_qq_userlist").val($.trim(qq));
						$("#dlg_email_user").val($.trim(email));		
						$("#dlg_department").val($.trim(department));		
						$("#department_id").val($.trim(departmentid));		
						$("#dlg_department_2").val($.trim(department_t));		
						$("#department_id_2").val($.trim(departmentid_2));		
						$("#dlg_org_information").val($.trim(org_infor));		
						$("#dlg_id").val(id);	
							var params=new Object();
				            params.sys_id = id;
						    $.ajax({
							url : "queryUser!readprivileges.action",
							type : "post",
							dataType : "json",
							data: params,
							success : function(data) 
							{
								if (data.list_all) 
								{
								    data_all = data.list_all;								    
								    var data_select = data.list_select;							 
									for ( var i = 0; i < data_all.length; i++) 
									{
									   var bz = false;
									   for(var j=0;j<data_select.length;j++)
									   {
										    if(data_all[i].ID == data_select[j])
										    {
	                                          
	                                           bz = true; 
	                                           break;
	                                        }
                                       		                              
									   }
									   if(bz)
									   {
									      $("#privilegeId").append("<span style='display:inline-block'><input type='checkbox' name='auth' checked='checked' value="+data_all[i].ID+">"+data_all[i].AUTHNAME+"</span>");
									   }
									   else
									   {
									      $("#privilegeId").append("<span style='display:inline-block'><input type='checkbox' name='auth' value="+data_all[i].ID+">"+data_all[i].AUTHNAME+"</span>");			
									   }						
								    }
								}
							}});									
						$("#action").val("edit");
						$("#dlg_id").val(id);
						param_user.dlg_user.dialog("option", "title", '编辑用户').dialog("open");
						$("#pwd_show").hide();
				        $("#qrwd_show").hide();
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_user').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_user').dialog('close');
						showMsg("未选中行！");
					}
				}
				else if(com == '删除')
				{
				   	if($('.trSelected', grid).length==1)
					{		
					       var NAME = $('.trSelected', grid).eq(0).find("td:eq(2)").text();	
					       var id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
					       var status = $('.trSelected', grid).eq(0).find("td:eq(7)").text();
					       if(status=='被冻结')
					       {
					        var indicate = "确认需要删除" + NAME+ ",这个运维用户吗?";	
					        var params=new Object();
				            params.id = id;					
							if(confirm(indicate)) 
							{
								$.ajax({
									type: "POST",
									url: "queryUser!deleteOperUser.action",
									cache: false,
									data: params,
									success: function(msg)
									{
										var result=eval("("+msg+")");
										if (result.msg=="ok") 
										{
											showMsg("删除成功！");
											$('#dlg_user').dialog('close');
											param_user.loadData({});
										} else 
										{
											showMsg(result.msg);
											$('#dlg_user').dialog('close');
										}
									}
								}); 
							}else{
								$('#dlg_user').dialog('close');
							}
						}
				       else
						{
						    showMsg("此运维用户正常，不可删除！");
						    $('#dlg_user').dialog('close');
						}
						
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_user').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_user').dialog('close');
						showMsg("未选中行！");
					}
				}
				else if(com == '冻结用户')
				{
				   	if($('.trSelected', grid).length==1)
					{		
					       var NAME = $('.trSelected', grid).eq(0).find("td:eq(2)").text();	
					       var id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
					       var status = $('.trSelected', grid).eq(0).find("td:eq(7)").text();
					       if(status=='正常')
					       {
					        var indicate = "确认需要冻结" + NAME+ ",这个运维用户吗?";	
					        var params=new Object();
				            params.id = id;					
							if(confirm(indicate)) 
							{
								$.ajax({
									type: "POST",
									url: "queryUser!DJUser.action",
									cache: false,
									data: params,
									success: function(msg)
									{
										var result=eval("("+msg+")");
										if (result.msg=="ok") 
										{
											showMsg("冻结成功");
											$('#dlg_user').dialog('close');
											param_user.loadData({});
										} else 
										{
											showMsg(result.msg);
											$('#dlg_user').dialog('close');
										}
									}
								}); 
							}
							else
							{
							    $('#dlg_user').dialog('close');
							}
						}
						else
						{
						  showMsg("此用户已经被冻结，无需再次冻结");
						  $('#dlg_user').dialog('close');
						}
						
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_user').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_user').dialog('close');
						showMsg("未选中行！");
					}
				}
				
				else if(com == '恢复用户')
				{
				   	if($('.trSelected', grid).length==1)
					{		
					       var NAME = $('.trSelected', grid).eq(0).find("td:eq(2)").text();	
					       var id = $('.trSelected', grid).eq(0).find("td:eq(1)").text();
					       var status = $('.trSelected', grid).eq(0).find("td:eq(7)").text();
					       if(status=='被冻结')
					       {
					        var indicate = "确认需要恢复" + NAME+ ",这个运维用户吗?";	
					        var params=new Object();
				            params.id = id;					
							if(confirm(indicate)) 
							{
								$.ajax({
									type: "POST",
									url: "queryUser!ResumeUser.action",
									cache: false,
									data: params,
									success: function(msg)
									{
										var result=eval("("+msg+")");
										if (result.msg=="ok") 
										{
											showMsg("恢复成功");
											$('#dlg_user').dialog('close');
											param_user.loadData({});
											
										} else 
										{
											showMsg(result.msg);
											$('#dlg_user').dialog('close');
										}
									}
								}); 
							}
							else
							{
							   $('#dlg_user').dialog('close');
							}
						}
						else
						{
						    showMsg("此用户正常，无需恢复");
						    $('#dlg_user').dialog('close');
						}
						
					}
					else if($('.trSelected', grid).length>1)
					{
					    $('#dlg_user').dialog('close');
						showMsg("不能同时选择多行记录！");
					}else
					{
					    $('#dlg_user').dialog('close');
						showMsg("未选中行！");
					}
				}
			},
			//全选
			selecAll : function() 
			{
				$("#dlg_userlist_export input").each(function(){
					$(this).attr("checked",true);
				});
			},
					
			// 打开导出数据对话框
			exportData : function() {
		        var dlgParent = $("#dlg_userlist_export").parent();
		        var dlgOwn = $("#dlg_userlist_export").clone();
				param_user.dlg_userlist_export = $("#dlg_userlist_export").dialog({
					autoOpen: false,
					height:200,
					width:500,
					show: "slide",
					modal:true,
					cache:false,
					/* hide: "explode", */
					title:'运维用户信息导出',
					buttons:{
						"全选":function(){
							param_user.selecAll();
						},
						"导出":function(){
							param_user.commitExport();
						}
					},
		            close : function() {
		            	dlgOwn.appendTo(dlgParent);
		            	$(this).dialog("destroy").remove();
		            }
				});
				param_user.dlg_userlist_export.dialog("option", "title", '运维用户信息').dialog("open");
			},
			
			// 导出数据
			commitExport : function() {
			
			    var term="";
// 				var cols = new Array();
// 				$("#dlg_userlist_export input").each(function(){
// 					if($(this).attr("checked")){
// 						cols.push(this.id.slice(7,this.id.length));
// 					}
// 				});
				//alert(JSON.stringify(cols));
				var condition = "";
				var username=$("#username")==null?'':$('#username').val();
				if (username && $.trim(username).length > 0) {
					condition += "&truename=" + encodeURI(encodeURI($.trim(username)));
				}
				
				var org_infor=$("#org_infor")==null?'':$('#org_infor').val();
				if (org_infor && org_infor.trim().length > 0) {
					condition += "&org_infor=" +  encodeURI(encodeURI($.trim(org_infor)));
				}
				if($("#export-username").attr("checked")){
			   term += "&un=username";
			}
			if($("#export-truename").attr("checked")){
			   term += "&tn=truename";
			}
			if($("#export-status").attr("checked")){
			   term += "&st=status";
			}
			if($("#export-qq").attr("checked")){
			   term += "&q=qq";
			}
			if($("#export-auth").attr("checked")){
			   term += "&au=auth";
			}
			if($("#export-department").attr("checked")){
			   term += "&dm=department";
			}
			if($("#export-org_infor").attr("checked")){
			   term += "&oi=org_infor";
			}
				window.location.href='queryUser!exportJobs_user.action?radom='+Math.random() + condition + term;
				$('#dlg_userlist_export').dialog('close');
			},
			
			//保存参数
			saveParam:function()
			{
			    // 提交方式
				var action = $("#action").val();
				
				//新增的时候才判断密码，修改的时候不需要
				if ("add" == action) 
				{
					if($("#dlg_name").val()==null || $("#dlg_name").val()=='')
					{
						showMsg("用户账号不能为空！");
						return;
					}
					if(!$("#dlg_name").validate()){
						return false;
					}
					if($("#dlg_truename").val()==null||$("#dlg_truename").val()=='')
					{
						showMsg("员工姓名不能为空！");
						return;
					}
					if($("#dlg_password").val()==null || $("#dlg_password").val()=='' )
					{
					    
						showMsg("用户密码不能为空！");
						return;
					}
					else if($("#dlg_password").val().length < 6 || $("#dlg_password").val().length >20)
					{
					    showMsg("用户密码必须在6-20位之间！");
						return;
					}
					else
					{
					   if(!pwStrength($("#dlg_password").val()))
					   {
					     showMsg("用户密码不符合信息安全，必须有数字,字母,特殊符号组成！");
					     return;
					   }
					
					}
					if($("#dlg_qr_password").val()==null || $("#dlg_qr_password").val()=='')
					{
						showMsg("确认密码不能为空！");
						return;
					}
					else if ($("#dlg_qr_password").val().length < 6 && $("#dlg_qr_password").val().length > 20)
					{
						showMsg("确认密码必须在6-20位之间！");
						return;
					
					}				
					else if($("#dlg_password").val() != $("#dlg_qr_password").val())
				    {
				        showMsg("密码与确认密码不一致！");
						return;
				    }
					if($.trim($("#dlg_qq_userlist").val()).length > 0){
						var reg = /^[1-9][0-9]{4,20}/;
						if(!reg.test($("#dlg_qq_userlist").val())){
							showMsg("QQ格式错误");
							return;
						}
					}
					if($("#dlg_phoneNum").val().length > 0){
						if(!$("#dlg_phoneNum").validate()){
							return false;
						}
					}else{
						showMsg("手机号码不能为空");
						return;
					}
					
					if($("#dlg_email_user").val().length > 0){
						//var reg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
						var reg = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i;
						if(!reg.test($("#dlg_email_user").val())){
							showMsg("邮箱格式不正确");
							return;
						}
					}
			    }
				/*
				if($("#dlg_org_information").val()==null||$("#dlg_org_information").val()=='')
				{
					showMsg("请输入您的岗位信息！");
					return;
				}
				*/
				if("edit" == action){
					if($("#dlg_name").val()==null || $("#dlg_name").val()=='')
					{
						showMsg("用户账号不能为空！");
						return;
					}
					//if(!$("#dlg_name").validate()){
					//	return false;
					//}
					if($("#dlg_truename").val()==null||$("#dlg_truename").val()=='')
					{
						showMsg("员工姓名不能为空！");
						return;
					}
					
					if($.trim($("#dlg_qq_userlist").val()).length > 0){
						var reg = /^[1-9][0-9]{4,20}/;
						if(!reg.test($("#dlg_qq_userlist").val())){
							showMsg("QQ格式错误");
							return;
						}
					}
					
					if($("#dlg_phoneNum").val().length > 0){
						if(!$("#dlg_phoneNum").validate()){
							return false;
						}
					}else{
						showMsg("手机号码不能为空");
						return;
					}
					
					if($("#dlg_email_user").val().length > 0){
						//var reg = /^[a-zA-Zd]+([-_.][a-zA-Zd]+)*@([a-zA-Zd]+[-.])+[a-zA-Zd]{2,5}$/;
						var reg = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i;
						if(!reg.test($("#dlg_email_user").val())){
							showMsg("邮箱格式不正确");
							return;
						}
					}
				}
				
				
				
		        var has = false;
		        var auth="";
		    	$("input[name='auth']").each(function(){
			    if ($(this).attr("checked")) 
			    {
			        has = true;
			        auth += $(this).val()+",";
			    }	
			    });		
				if(!has)
				{
				    
					showMsg("请您至少选择一个用户的权限！");
					return;
				}
				/**
				if($("#dlg_department").val()==""){
					showMsg("请您选择用户所在部门！");
					return;
				}*/
				var params=new Object();
				params.name = HtmlEncode($("#dlg_name").val());
				params.id = $("#dlg_id").val();
				params.truename = $("#dlg_truename").val();
				params.password  = $("#dlg_password").val();
				params.phoneNum  = $("#dlg_phoneNum").val();
				params.qq  = $("#dlg_qq_userlist").val();
				params.email  = $("#dlg_email_user").val();
				params.remark  = $("#dlg_remark").val();
				if($("#department_id").val()==""){
					params.departId  = -1;
				}else{
					params.departId  = $("#department_id").val();
				}
				if($("#department_id_2").val()==""){
					params.departId_t  = -1;
				}else{
					params.departId_t  = $("#department_id_2").val();
				}
				params.department  = $("#dlg_department").val();
				params.department_2  = $("#dlg_department_2").val();
				params.org_infor  = $("#dlg_org_information").val();
				params.privilegeId  = auth;
				// 新增和编辑通用字符串
				var url ='';
				var indicate='';
				if ("add" == action) 
				{
					indicate = "确认新增" + params.name + ",这个用户?";
					url = "queryUser!addUser.action";
				} else if ("edit" == action) 
				{
					params.id = $("#dlg_id").val();
					indicate = "确认更新" + params.name + ",这个用户信息?";
					url = "queryUser!updateUser.action";
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
								$('#dlg_user').dialog('close');
								param_user.loadData({department_menu_id:$("#department_id_tmp").val()});
							} else 
							{
								showMsg(result.msg);
							}
						}
					}); 
				}
				else
				{
				    $('#dlg_user').dialog('close');
				}
			},
			loadData:function(param){
			
				var queryP = {};
				queryP.username = $('#username').val()==null?'':$('#username').val();
				queryP.org_infor = $('#org_infor').val()==null?'':$('#org_infor').val();
				queryP.mobile = $('#mobile').val()==null?'':$('#mobile').val();
				queryP.sys_id = $('#sys_id').val()==null?'':$('#sys_id').val();
				var selectedGridDiv = $("div.ui-tabs-panel.ui-widget-content.ui-corner-bottom:not(.ui-tabs-hide)");
				queryP.pagenum = param.page?param.page:$('.pcontrol input', selectedGridDiv).val();
				queryP.pagesize = param.rp?param.rp:$("[name=rp]", selectedGridDiv).val();
				queryP.sortname = param.sortname? param.sortname : "";
				queryP.sortorder = param.sortorder? param.sortorder : "";
				queryP.department_menu_id = param.department_menu_id? param.department_menu_id : "";
				
				$.ajax({
					type: "POST",
					url: "queryUser!queryUser.action?operation=init",
					cache: false,
					data: queryP,
					success: function(msg){
						var data=eval("("+msg+")");
						try{
							param_user.param_grid_user.flexAddData(data);
						}catch(e){
							showErrorMsg("查询参数信息出错！");
						}
					}
				}); 
			},
			// 选定部门
			selectMenus : function() {
				var nodes = menuTree.getCheckedNodes(true);
				var nodes_child_name = [];
				var nodes_parent_name = [];
				var nodes_child_id = [];
				var nodes_parent_id = [];
				for (var i in nodes) {
					if(nodes[i].isParent){
						nodes_parent_name.push(nodes[i].name);
						nodes_parent_id.push(nodes[i].id);
					}else{
						nodes_child_name.push(nodes[i].name);
						nodes_child_id.push(nodes[i].id);
					}
				}
				/*if(nodes_child_name.length>1||nodes_parent_name.length>1)
				{
					showMsg("请您选择一个部门！");
					return;
				}*/
				if(nodes_child_name.length==1)
				{
					$("#dlg_department").val(nodes_parent_name+"("+nodes_child_name+")");
					$("#department_id").val(nodes_child_id);
				}
				else
				{
					$("#dlg_department").val(nodes_parent_name);
					$("#department_id").val(nodes_parent_id);
				}
				$('#dlg_department_menu').dialog('close');
			},
			// 选定部门
			selectMenus_2 : function() {
				var nodes = menuTree.getCheckedNodes(true);
				var nodes_child_name = [];
				var nodes_parent_name = [];
				var nodes_child_id = [];
				var nodes_parent_id = [];
				for (var i in nodes) {
					if(nodes[i].isParent){
						nodes_parent_name.push(nodes[i].name);
						nodes_parent_id.push(nodes[i].id);
					}else{
						nodes_child_name.push(nodes[i].name);
						nodes_child_id.push(nodes[i].id);
					}
				}
				/*if(nodes_child_name.length>1||nodes_parent_name.length>1)
				{
					showMsg("请您选择一个部门！");
					return;
				}*/
				if(nodes_child_name.length==1)
				{
					$("#dlg_department_2").val(nodes_parent_name+"("+nodes_child_name+")");
					$("#department_id_2").val(nodes_child_id);
				}
				else
				{
					$("#dlg_department_2").val(nodes_parent_name);
					$("#department_id_2").val(nodes_parent_id);
				}
				$('#dlg_department_menu').dialog('close');
			},
			
			//批量分配角色
			changeAuth:function(com,grid){
					var dlgParent = $("#dlg_auth_distribute").parent();
			        var dlgOwn = $("#dlg_auth_distribute").clone();
			        dlg_auth_distribute = $("#dlg_auth_distribute").dialog({
						autoOpen: false,
						height:400,
						width:500,
						show: "slide",
						modal:true,
						cache:false,
						/* hide: "explode", */
						title:'批量分配角色',
						buttons:{
							"确定":function(){
								param_user.saveAuth();
							},
							"取消" : function() {
								dlgOwn.appendTo(dlgParent);
				            	$(this).dialog("destroy").remove();
							}
						},
			            close : function() {
			            	dlgOwn.appendTo(dlgParent);
			            	$(this).dialog("destroy").remove();
			            }
					});
			        $.ajax({
			            type:"get",
			            dataType:"text",
			            async:false,
			            url:"queryUser!getUserList.action",
			            success:function(result) {
			            	var data=eval("("+result+")");
							var html = '';
							for (var i in data) {
								html += '<option value="'+data[i].ADMIN_ID+'">'+data[i].USERNAME+'</option>';
							}
							$("#username_list1").append(html);
			            }
			        });
			        $.ajax({
						url : "queryUser!readprivileges.action",
						type : "post",
						dataType : "json",
						success : function(data) {
							if (data.list_all) {
							    data_all = data.list_all;
							    var html="";
								for ( var i = 0; i < data_all.length; i++) 
								{
									html +="<input type='checkbox'name='auth_pl' value="+data_all[i].ID+">"+data_all[i].AUTHNAME+"<br/>";			
							    }
								$("#authChoose").append(html);
							}
						}});
			        dlg_auth_distribute.dialog("open");
			},
			saveAuth:function(){
				var has = false;
				var admin_id ="";
				var username ="";
		        var auth_pl="";
				if(document.authchoose.username_list2.options.length==0){
					showMsg("请选择需要分配角色的用户！");
					return;
				}
		    	$("input[name='auth_pl']").each(function(){
			    if ($(this).attr("checked")) 
			    {
			        has = true;
			        auth_pl += $(this).val()+",";
			    }	
			    });		
				if(!has)
				{
					showMsg("请为已选用户分配角色！");
					return;
				}
				for(var i=0;i<document.authchoose.username_list2.options.length;i++){
					admin_id += document.authchoose.username_list2.options[i].value+",";
					username += document.authchoose.username_list2.options[i].text+",";
				}
				var params=new Object();
				params.privilegeId  = auth_pl;
				params.admin_id  = admin_id;
				params.username  = username;
				$.ajax({
					type: "POST",
					url: "queryUser!distributeAuth.action",
					cache: false,
					data: params,
					success: function(msg){
						var result=eval("("+msg+")");
						if (result.msg=="ok") 
						
						{
							showMsg("操作成功");
							$('#dlg_auth_distribute').dialog('close');
							param_user.loadData({page:1})
						} else 
						{
							showMsg(result.msg);
						}
					}
				}); 
			}
		};
//左边树菜单点击事件
var org_menu_event = function(event,treeId,treeNode)
{
			 		   $('#department_id_tmp').val(treeNode.ORG_ID);
		 				param_user.loadData({page:1,department_menu_id:treeNode.ORG_ID});
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
				//展开所有
					var treeObj = $.fn.zTree.getZTreeObj("organ_table");
					treeObj.expandAll(true);
				
				$("#organ_table > .level0").css("border-bottom",0);
				//orgTree = $.fn.zTree.getZTreeObj("org_tree");
				//rMenu = $("#rMenu");
				//addCount = 1;
			}
		});
	}
}
	 $(function(){
		 org_Menu.loadTree();
		//初始化所有操作按钮
		 var  parm_button = [{name: '新建', bclass: 'add', onpress : param_user.editParam},
							{name: '修改', bclass: 'edit', onpress : param_user.editParam},
							{name: '删除', bclass: 'delete', onpress : param_user.editParam},
							{name: '冻结用户', bclass: 'edit', onpress : param_user.editParam},
							{name: '恢复用户', bclass: 'edit', onpress : param_user.editParam},
							{name: '导出', bclass: 'excel', onpress : param_user.exportData},
							{name: '重置密码', bclass: 'edit', onpress : param_user.resetPassword},
							{name: '批量分配角色', bclass: 'distribute', onpress : param_user.changeAuth}];
		 //权限操作按钮
		 var buttons = [];
		 
		 //获得当前菜单的id
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
	 			// 初始化数据
				param_user.bindParamGrid(buttons);
			}
		});
	
		/*参数管理-查询按钮功能*/
		$('#btn_user_search').button({ icons: {primary:'ui-icon-search'} }).on("click",function(){
			param_user.loadData({page:1});
		});

		//清空筛选框
		$('#btn_user_empty').on("click",function(){
			$("#username").val("");
			$("#org_infor").val("");
			//param_user.loadData({page:1});
		});
		
		
	});
		
		function isPasswd(str)
		{ 
			var reg = /^[a-zA-Z0-9!@#$%^&*()_+|{}?><\-\]\\[\/]*$/; 
			return reg.test(str); 
		} 	
		
		function CharMode(iN)
		{  
				if(iN>=48 && iN <=57) //数字  
				return 1;  
				if(iN>=65 && iN <=90) //大写  
				return 2;  
				if(iN>=97 && iN <=122) //小写 
				return 4;  
				//else  return 8;  //特殊字符
        }  
		
		//bitTotal函数，计算密码模式  
		function bitTotal(num)
		{  
			modes=0;  
			for(var i=0;i<3;i++)
			{  
			if(num & 1) modes++;  
			num>>>=1;  
			}  
			return modes;  
		}
			
			//返回强度级别  
		function checkStrong(password)
		{  
			if(password.length<6)  
			return 0; //密码太短  
			var Modes=0;  
			for(var i=0;i<password.length;i++)
			{ 
			//密码模式  
			   Modes|=CharMode(password.charCodeAt(i));  
			}  
			return bitTotal(Modes);  
        } 
        
        //判断强度  
		function pwStrength(password)
		{ 
			var S_level=checkStrong(password);     
				if(S_level<2)  	
				{
				  //alert("密码必须由大写字母、小写字母、数字和字符组成！");
				 return false;
				}
				else
				{
				    return true;
				}
	    } 
        
		 //判断用户名是否存在
		   function judgeName(){
			   var user = {};
				user.name = $('#dlg_name').val()==null?'':$('#dlg_name').val();
				user.id = $('#dlg_id').val();
				$.ajax({
					type:"POST",
					url: "queryUser!judgeUserName.action",
					cache: false,
					data:user,
					success: function(msg){
						//var result=eval("("+msg+")");
						if (msg=="ok")
						{
							$("#dlg_name").val("");
							showMsg("该用户名已存在，请重新命名");
						}
					}	
				});
		   }
 		// 树形结构设置
		 var departmentTreeSetting = {
		 		view: {
		 			dblClickExpand: true,
		 			showLine: false,
		 			selectedMulti: true,
		 			expandSpeed: ($.browser.msie && parseInt($.browser.version)<=6)?"":"fast"
		 		},
// 		 		check : {
// 		 			enable : true,
// 		 			chkStyle: "radio",
// 		 			chkboxType : {"Y" : "p", "N" : "s"}
// 		 		},
		 		check: {enable: true,chkStyle: "radio",radioType: "all"},
		 		data: {
		 			simpleData: {
		 				enable: true,
		 				idKey: "id",
		 				pIdKey: "pId"
		 			}
		 		},
		 		callback:{
		 			onClick: function(event, treeId, treeNode, clickFlag)
		 			{
		 				$('#dlg_department_menu_id').val(treeNode.id);
		 				$('#dlg_department_menu_name').val(treeNode.name);
		 			}
		 		}
		 };
		// 树形结构设置
		 var departmentTreeSetting_2 = {
		 		view: {
		 			dblClickExpand: true,
		 			showLine: false,
		 			selectedMulti: true,
		 			expandSpeed: ($.browser.msie && parseInt($.browser.version)<=6)?"":"fast"
		 		},
// 		 		check : {
// 		 			enable : true,
// 		 			chkStyle: "radio",
// 		 			chkboxType : {"Y" : "p", "N" : "s"}
// 		 		},
		 		check: {enable: true,chkStyle: "radio",radioType: "all"},
		 		data: {
		 			simpleData: {
		 				enable: true,
		 				idKey: "id",
		 				pIdKey: "pId"
		 			}
		 		},
		 		callback:{
		 			onClick: function(event, treeId, treeNode, clickFlag)
		 			{
		 				$('#department_id_2').val(treeNode.id);
		 				$('#dlg_department_2').val(treeNode.name);
		 			}
		 		}
		 };       
		 var dlg_department_menu;
		 //弹出部门树
		 function department_menu(id) {
			var department_id = $("#department_id").val();
			var dlgParent = $("#dlg_department_menu").parent();
	        var dlgOwn = $("#dlg_department_menu").clone();
	        dlg_department_menu = $("#dlg_department_menu").dialog({
				autoOpen: false,
				height:500,
				width:300,
				buttons:{
					"确定":function(){
						param_user.selectMenus();
					}
				},
	            close : function() {
	            	dlgOwn.appendTo(dlgParent);
	            	$(this).dialog("destroy").remove();
	            }
			}); 
	       	$("#dlg_department_id").val(id);
			$('#dlg_department_menu_id').val('');
		    $('#dlg_department_menu_name').val('');
		    dlg_department_menu.dialog("open");
			var queryP = {};
			queryP.pagenum = 1;
			queryP.pagesize = 10000;
			queryP.id = id;
			$.ajax({
				type: "POST",
				url: "org!getDepartment.action?operation=init",
				cache: false,
				data: queryP,
				success: function(msg){
					var data=jQuery.parseJSON(msg);
					// 初始化部门树
					menuTree = $.fn.zTree.init($("#dlg_department_menu_tree"), departmentTreeSetting, data.allMenu);
					// 选中的部门id
					if(department_id==""){
						if(data.org_id!=""){
							menuTree.checkNode(menuTree.getNodeByParam("id", data.org_id), true, false);
						}
					}else{
						menuTree.checkNode(menuTree.getNodeByParam("id", department_id), true, false);
					}
				}
			});
		 }
		 //弹出部门树
		 function department_menu_2(id) {
			var department_id = $("#department_id_2").val();
			var dlgParent = $("#dlg_department_menu").parent();
	        var dlgOwn = $("#dlg_department_menu").clone();
	        dlg_department_menu = $("#dlg_department_menu").dialog({
				autoOpen: false,
				height:500,
				width:300,
				buttons:{
					"确定":function(){
						param_user.selectMenus_2();
					}
				},
	            close : function() {
	            	dlgOwn.appendTo(dlgParent);
	            	$(this).dialog("destroy").remove();
	            }
			}); 
	       	$("#dlg_department_id_2").val(id);
			$('#dlg_department_menu_id').val('');
		    $('#dlg_department_menu_name').val('');
		    dlg_department_menu.dialog("open");
			var queryP = {};
			queryP.pagenum = 1;
			queryP.pagesize = 10000;
			queryP.id = id;
			$.ajax({
				type: "POST",
				url: "org!getDepartment.action?operation=init",
				cache: false,
				data: queryP,
				success: function(msg){
					var data=jQuery.parseJSON(msg);
					// 初始化部门树
					menuTree = $.fn.zTree.init($("#dlg_department_menu_tree"), departmentTreeSetting_2, data.allMenu);
					// 选中的部门id
					if(department_id==""){
						if(data.org_id!=""){
							menuTree.checkNode(menuTree.getNodeByParam("id", data.org_id), true, false);
						}
					}else{
						menuTree.checkNode(menuTree.getNodeByParam("id", department_id), true, false);
					}
				}
			});
		 }
		 function moveOption(list1,list2){
			 var options = list1.options;
			 for(var i=0;i<options.length;i++){
				 if(options[i].selected){
					 list2.appendChild(options[i]);
					 i--;
				 }
			 }
		 }
		</script>
	</body>
</html>
