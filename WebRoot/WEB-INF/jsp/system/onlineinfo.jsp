 <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 
 <style type="text/css">
<!--
	.title h3{ padding:0 6px; font-size:100%; color:#000; font-weight:bold;}
	.title-gf{ height:35px; line-height:35px; }
	.title-1 h3{}
-->
.thHideContent{
	word-break:break-all;
	overflow: hidden;
}
</style>

 
 <script type="text/javascript">
 var param_online = {
			
			loadData:function(){
				var queryP = {};
				$.ajax({
					type: "POST",
					url: "admin!tjBusiness.action?operation=init",
					cache: false,
					data: queryP,
					success: function(msg)
					{
					  var result=eval("("+msg+")");
                      $('#yw_zx').html(result.yw_zx);
                      $('#client_zx').html(result.client_zx);
                      $('#yw_total').html(result.yw_total);
                      $('#web_total').html(result.web_total);
                      $('#web_total_mon').html(result.web_total_mon);
                      $('#web_total_date').html(result.web_total_date);
                      $('#web_zx').html(result.web_zx);
                      $('#web_storage').html(result.storage);
                      if(result.storage=="正常")
                      {
                        $('#web_storage').css("color","blue");
                      }
                      else
                      {
                         $('#web_storage').css("color","red");
                      }
                      $('#web_device').html(result.device);
                      if(result.device=="正常")
                      {
                        $('#web_device').css("color","blue");
                      }
                      else
                      {
                         $('#web_device').css("color","red");
                      }
					}
				}); 
			},
			showClientDetail :function() {
				if ($("#client_online_detail").css("display") == "block") {
					$("#client_online_detail").css("display", "none");
				} else {
					$("#client_online_detail").css("display", "block");
				}

			},
			//用户概况
			show_user_Details : function(user_id) {
		        var dlgParent = $("#dlg_online_detail").parent();
		        var dlgOwn = $("#dlg_online_detail").clone();
		        dlg_online_detail = $("#dlg_online_detail").dialog({
					autoOpen: false,
					height:350,
					width:650,
					show: "slide",
					modal:true,
					cache:false,
					/* hide: "explode", */
					title:'用户概况',
		            close : function() {
		            	dlgOwn.appendTo(dlgParent);
		            	$(this).dialog("destroy").remove();
		            }
				});
				$.ajax({
					type:"POST",
					data:{"user_id" : user_id},
					cache: false,
					async:true,
					url: "manageRender!getUserDetail.action",
					success:function(result)
					{	
						var data=eval("("+result+")");
						$("#dlg_onlinedetail_name").text(data[0].NICKNAME);
						$("#dlg_onlinedetail_rechargeTime").text(data[0].RECHARGE_NUM);
						$("#dlg_onlinedetail_phone").text(data[0].PHONE);
						$("#dlg_onlinedetail_rechargeAll").text(data[0].RECHARGE_MONEY+"元");
						$("#dlg_onlinedetail_email").text(data[0].EMAIL);
						$("#dlg_onlinedetail_lastRecharge").text(data[0].LAST_SUBTIME);
						$("#dlg_onlinedetail_place").text(data[0].PRCITY);
						$("#dlg_onlinedetail_costTime").text(data[0].COST_NUM);
						$("#dlg_onlinedetail_createtime").text(data[0].CREATETIME);
						$("#dlg_onlinedetail_costAll").text(data[0].COST_MONEY+"元");
						$("#dlg_onlinedetail_money").text(data[0].BALANCE+"元");
						$("#dlg_onlinedetail_zt_money").text(data[0].ZT_MONEY+"元");
						$("#dlg_onlinedetail_zx_money").text(data[0].ZX_MONEY+"元");
						$("#dlg_onlinedetail_free_money").text(data[0].FREE_BALANCE+"元");
						$("#dlg_onlinedetail_expressfee").text(data[0].KD_MONEY+"元");
						$("#dlg_onlinedetail_renderhe").text((data[0].TIME_HE/3600).toFixed(2)+"核小时");
						$("#dlg_onlinedetail_renderMoney").text(data[0].RENDER_MONEY.toFixed(4)+"元/核小时");
					}
				});
				dlg_online_detail.dialog("option", "title", '用户详细信息').dialog("open");
			}
		};
 
 $(function(){
		/*参数管理-查询按钮功能*/
		$('#btn_refush').button().on("click",function(){
		
           $('#yw_zx').html("");
           $('#client_zx').html("");
           $('#yw_total').html("");
           $('#web_total').html("");
           $('#web_total_mon').html("");
           $('#web_total_date').html("");
		   $('#web_storage').html("");
		   $('#web_device').html("");
			param_online.loadData();
			$("#client_online_detail").html("");
			$.ajax({
				type:"POST",
				cache: false,
				url: "admin!getOnlineClientInfo.action",
				success:function(result)
				{
					var data=eval("("+result+")");
					var html = "";
					html +=  '<table  border="0" style="overflow:hidden">';
					for (var i in data) 
					{
					   if(i%4==0)
					   {
					          if(i==0)
					          {
						        html    += '</tr><tr>';    
						      }
						      else
						      {
						        html    += '<tr>';  
						      }
							  html	  += ''
									+        '<td class="wi-align-l" style="width:350px;>'
									+            '<label for="input_1"><a href="#" onclick="param_online.show_user_Details('+data[i].ID+')">'+ data[i].NICKNAME +"["+data[i].PHONE+'] </a></label>'
									+        '</td>';
					   
					   }
					   else
					   {
						      html	  += ''
									+        '<td class="wi-align-l" style="width:350px;>'
									+            '<label for="input_1"><a href="#" onclick="param_online.show_user_Details('+data[i].ID+')">'+ data[i].NICKNAME +"["+data[i].PHONE+'] </a></label>'
									+        '</td>';
					   }
					}
						html += '</tr></table>';
				$("#client_online_detail").html(html);
			}
		     });
			
		});
		$.ajax({
			type:"POST",
			cache: false,
			url: "admin!getOnlineClientInfo.action",
			success:function(result)
			{
				var data=eval("("+result+")");
				var html = "";
				html +=  '<table  border="0" class="table-1">';
				for (var i in data) 
				{
				   if(i%4==0)
				   {
				          if(i==0)
				          {
					        html    += '</tr><tr>';    
					      }
					      else
					      {
					        html    += '<tr>';  
					      }
						  html	  += ''
								+        '<td class="wi-align-l" style="width:350px;>'
								+            '<label for="input_1"> <a href="#" onclick="param_online.show_user_Details('+data[i].ID+')">'+ data[i].NICKNAME +"["+data[i].PHONE+'] </a></label>'
								+        '</td>';
				   
				   }
				   else
				   {
					      html	  += ''
								+        '<td class="wi-align-l" style="width:350px;>'
								+            '<label for="input_1"> <a href="#" onclick="param_online.show_user_Details('+data[i].ID+')">'+ data[i].NICKNAME +"["+data[i].PHONE+'] </a></label>'
								+        '</td>';
				   }
				}
					html += '</tr></table>';
				$("#client_online_detail").html(html);
			}
		});
			
		// 初始化数据
		param_online.loadData();
		param_online.showClientDetail();
		$('#title_client_zx').on("click",function(){
			param_online.showClientDetail();
		});
	});
</script>
<div class="div div-1" >
	<div class="title title-gf">
			<h3>
				<img src='../images/fold.png' onclick="showAndHide(this);"  class="imgShrotate">
					<font>查询条件</font>
			</h3>
	</div>
	<div class="div-main-1" >
		<div class="title title-gf" id="title_yw_zx" style="overflow-y:auto;">
			<h3>运维在线人数：<span id="yw_zx"></span></h3>
		</div>
		<div class="title title-gf" id="title_web_zx" style="overflow-y:auto;">
			<h3>网站在线人数：<span id="web_zx"></span></h3>
		</div>
		<div class="title title-gf" id="title_client_zx" style="overflow-y:auto;">
			<h3>客户端在线人数：<span id="client_zx"></span></h3>
		</div>
		<div id="client_online_detail" class="div-main-1" style="display:none;overflow-y:scroll;height:300px;">
		</div>
		<div class="title title-gf" id="title_yw_total" style="overflow-y:auto;">
			<h3>运维注册有效总数：<span id="yw_total"></span></h3>
		</div>
		<div class="title title-gf" id="title_web_total" style="overflow-y:auto;">
			<h3>网站注册有效总数：<span id="web_total"></span></h3>
		</div>
		<div class="title title-gf" id="title_web_total_mon" style="overflow-y:auto;">
			<h3>网站当月注册数：<span id="web_total_mon"></span></h3>
		</div>
		<div class="title title-gf" id="title_web_total_date" style="overflow-y:auto;">
			<h3>网站当日注册数：<span id="web_total_date"></span></h3>
		</div>
		<div class="title title-gf" id="title_storage" style="overflow-y:auto;">
			<h3>存储节点状态：<span id="web_storage"></span></h3>
		</div>
		<div class="title title-gf" id="title_device" style="overflow-y:auto;">
			<h3>缓存硬盘状态：<span id="web_device"></span></h3>
		</div>
		<a href="#" id="btn_refush">刷新</a>
	</div>
</div>
<div id="dlg_online_detail" style="display:none">
	<table  border="0" class="table-1">
		<tr>
			<td style="width:140px;font-size:14px"><strong><label for="input_1">昵称：</label></strong></td>
			<td class="wi-align-l" style="font-size:14px"><span id="dlg_onlinedetail_name"></span></td>
			<td style="width:140px;font-size:14px"><strong><label for="input_1">充值次数：</label></strong></td>
			<td class="wi-align-l" style="font-size:14px;color:blue"><span id="dlg_onlinedetail_rechargeTime"></span></td>
		</tr>
		<tr>
			<td style="width:140px;font-size:14px"><strong><label for="input_1">手机号码：</label></strong></td>
			<td class="wi-align-l" style="font-size:14px"><span id="dlg_onlinedetail_phone"></span></td>
			<td style="width:140px;font-size:14px;"><strong><label for="input_1">充值总额：</label></strong></td>
			<td class="wi-align-l" style="font-size:14px;color:red"><span id="dlg_onlinedetail_rechargeAll"></span></td>
		</tr>
			<tr>
			<td style="width:140px;font-size:14px"><strong><label for="input_1">邮箱：</label></strong></td>
			<td class="wi-align-l" style="font-size:14px" class="thHideContent"><span id="dlg_onlinedetail_email"></span></td>
			<td style="width:140px;font-size:14px"><strong><label for="input_1">最近充值时间：</label></strong></td>
			<td class="wi-align-l" style="font-size:14px"><span id="dlg_onlinedetail_lastRecharge"></span></td>
		</tr>
		<tr>
			<td style="width:140px;font-size:14px"><strong><label for="input_1">所在地：</label></strong></td>
			<td class="wi-align-l" style="font-size:14px"><span id="dlg_onlinedetail_place"></span></td>
			<td style="width:140px;font-size:14px"><strong><label for="input_1">渲染消费笔数：</label></strong></td>
			<td class="wi-align-l" style="font-size:14px;color:blue"><span id="dlg_onlinedetail_costTime"></span></td>
		</tr>
		<tr>
			<td style="width:140px;font-size:14px"><strong><label for="input_1">注册日期：</label></strong></td>
			<td class="wi-align-l" style="font-size:14px"><span id="dlg_onlinedetail_createtime"></span></td>
			<td style="width:140px;font-size:14px"><strong><label for="input_1">渲染消费总额：</label></strong></td>
			<td class="wi-align-l" style="font-size:14px;color:red"><span id="dlg_onlinedetail_costAll"></span></td>
		</tr>
		<tr>
			<td style="width:140px;font-size:14px"><strong><label for="input_1">实充余额：</label></strong></td>
			<td class="wi-align-l" style="font-size:14px;color:red"><span id="dlg_onlinedetail_money"></span></td>
			<td style="width:140px;font-size:14px"><strong><label for="input_1">其他消费总额：</label></strong></td>
			<td class="wi-align-l" style="font-size:14px;color:red"><span id="dlg_onlinedetail_expressfee"></span></td>
		</tr>
		<tr>
			<td style="width:140px;font-size:14px"><strong><label for="input_1">赠送余额：</label></strong></td>
			<td class="wi-align-l" style="font-size:14px;color:red"><span id="dlg_onlinedetail_free_money"></span></td>
			<td style="width:140px;font-size:14px"><strong><label for="input_1">渲染总核小时数：</label></strong></td>
			<td class="wi-align-l" style="font-size:14px"><span id="dlg_onlinedetail_renderhe"></span></td>
		</tr>
		<tr>
			<td style="width:140px;font-size:14px"><strong><label for="input_1">在途金额：</label></strong></td>
			<td class="wi-align-l" style="font-size:14px;color:red"><span id="dlg_onlinedetail_zt_money"></span></td>
			<td style="width:140px;font-size:14px"><strong><label for="input_1">渲染核小时成本：</label></strong></td>
			<td class="wi-align-l" style="font-size:14px"><span id="dlg_onlinedetail_renderMoney"></span></td>
		</tr>
		<tr>
			<td style="width:140px;font-size:14px"><strong><label for="input_1">在渲金额：</label></strong></td>
			<td class="wi-align-l" style="font-size:14px;color:red"><span id="dlg_onlinedetail_zx_money"></span></td>
		</tr>
	</table>
</div>

	<!-- 	<table cellpadding="0" cellspacing="0" border="0" class="table-1">
<!-- 			<tr>
				<td style="width:150px;"><label for="input_1" class="lbl-1">运维在线人数：</label></td>
				<td class="wi-align-l" style="width:170px;" id="yw_zx">
				</td>
			</tr>
			<tr>
				<td style="width:150px;"><label for="input_1" class="lbl-1">网站在线人数：</label></td>
				<td class="wi-align-l" style="width:170px;" id="web_zx">
				</td>
			</tr>
			<tr>
				<td style="width:150px;"><label for="input_1" class="lbl-1">客户端在线人数：</label></td>
				<td class="wi-align-l" style="width:170px;" id="client_zx">
				</td>
			</tr>
			<tr>
				<td style="width:150px;"><label for="input_1" class="lbl-1">运维注册有效总数：</label></td>
				<td class="wi-align-l" style="width:170px;" id="yw_total">
				</td>
			</tr>
			<tr>
				<td style="width:150px;"><label for="input_1" class="lbl-1">网站注册有效总数：</label></td>
				<td class="wi-align-l" style="width:170px;" id="web_total">
				</td>
			</tr>
			<tr>
				<td style="width:150px;"><label for="input_1" class="lbl-1">网站当月注册数：</label></td>
				<td class="wi-align-l" style="width:170px;" id="web_total_mon">
				</td>
			</tr>
			<tr>
				<td style="width:150px;"><label for="input_1" class="lbl-1">网站当日注册数：</label></td>
				<td class="wi-align-l" style="width:170px;" id="web_total_date">
				</td>
				<td class="wi-align-l"><a href="#" id="btn_refush">刷新</a></td>
			</tr>
		</table> -->
