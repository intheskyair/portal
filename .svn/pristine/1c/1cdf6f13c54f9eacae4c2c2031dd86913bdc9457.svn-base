<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../system/include.jsp"%>
<html>
<style>
	.btn{
		width:74px; 
		height:29px;
		font-size:14px;
		background-color:#3ea4e8;
		color: #fff;
	}
	.inputText{
		height: 25px;
		line-height: 25px;
		width: 150px;
		margin-right:10px;
	}
	.lib-1{
		font: 14px/1.5em Verdana, Arial,\5B8B\4F53, sans-serif;
		margin-right: 10px;
	}
	.imgShrotate{
		float: left;
		margin-top: 5px;
		-webkit-transform:rotate(90deg);
		-moz-transform:rotate(90deg);
		filter:progid:DXImageTransform.Microsoft.BasicImage(rotation=3);
		display: block;
		cursor: pointer;
	}
	.imgSh{
		float: left;
		margin-top: 5px;
		cursor: pointer;
	}
	.p-content{
		margin-left:5px;
		text-align: left;
		line-height: 25px;
	}
	.p-content > span{
		color:#3ea4e8;
	}
	
</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/common/css/themes/css/jquiGui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/common/css/themes/css/flexigrid/flexigrid.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/common/css/themes/css/jquery-ui-jquiGui.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/views/common/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/views/common/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/views/common/js/common.js"></script>
    <head>
	<title>运维登陆日志管理</title>
	</head>
	<body style="background-color: #F0F0F0">	
		<div style="display: block;margin: 5px;overflow: hidden;">
		<%-- <div class="notes">
			<a onClick="cwin()"> 
				<img src="${pageContext.request.contextPath}/images/1.jpg" align="middle" alt="关闭左栏"></a>&nbsp;&nbsp;运维登陆日志管理</div> --%>
		    <form id="form2" action="log!logForPortal.action" method="post">
				<%-- <div >
					<table cellpadding="0" cellspacing="0" border="0" style="width: 95%;margin: 10px auto">
						<tr align="center">
							<td><label class="lib-1">员工姓名:</label><input id ="user_name" name="username" class="inputText"  value="${username}"></td>
							<td><label class="lib-1">IP地址:</label><input id ="id_ad" name="ipadd" class="inputText" value= "${ipadd}"></td>
							<td><label class="lib-1">登陆时间:</label><input type="text" id="logintime" name="logintime" readonly class="Wdate" value= "${logintime}"
							 onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" style="height: 27px" /></td>
						</tr>
						<tr align="center">
							<td colspan="3">
								<div class="but">
								    <input type='hidden' id='nextnum' name='nextnum' value="${nextnum}">
								    <input type='hidden' id='updownbz' name='updownbz' value="0">
									<input type="button" class="btn" value="搜 索" onclick="sub();" id="serchBtn"/>
									<input type="button" class="btn" id="emp_all" onclick="empAll()" value="清 空"/>
									<input type='button' id='uppage_button' value='上一页' onclick="up();" class='btn'>
									<input type='button' id='nextpage_button' value='下一页' onclick="down();" class='btn'>
								</div>
							</td>
						</tr>
					</table>
			    </div> --%>
			    
			    <div class="div div-1">
					<div class="title title-1">
						<h3>
							<img src='${pageContext.request.contextPath}/images/fold.png' onclick="showAndHide(this,'cluster_log_grid');"  class="imgShrotate">
							<font style="float: left">查询条件</font>
						</h3>
					</div>
					<div class="div-main-1" >
						<table  border="0" cellpadding="0" cellspacing="0" style="width:100%">
							<tr >
								<td><label class="lib-1">用户账号:</label><input id ="user_name" name="username" class="inputText"  value="${username}"></td>
								<td><label class="lib-1">手机号码:</label><input id ="phoneNO" name="phoneNO" class="inputText"  value="${phoneNO}"></td>
								<td><label class="lib-1">邮箱:</label><input id ="email" name="email" class="inputText"  value="${email}"></td>
								<td><label class="lib-1">IP地址:</label><input id ="id_ad" name="ipadd" class="inputText" value= "${ipadd}"></td>
								<td><label class="lib-1">登陆时间:</label><input type="text" id="logintime" name="logintime" readonly class="Wdate" value= "${logintime}"
								 onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" style="height: 27px" /></td>
								 <td></td>
								  <td></td>
								   <td></td>
								    <td></td>
							</tr>
							<tr >
						
							<td colspan="9">
								<div class="but">
								    <input type='hidden' id='nextnum' name='nextnum' value="${nextnum}">
								    <input type='hidden' id='updownbz' name='updownbz' value="0">
									<input type="button" class="btn ui-state-default" value="搜 索" onclick="sub();" id="serchBtn"/>
									<input type="button" class="btn ui-state-default" id="emp_all" onclick="empAll()" value="清 空"/>
									<input type='button' id='uppage_button' value='上一页' onclick="up();" class='btn ui-state-default'>
									<input type='button' id='nextpage_button' value='下一页' onclick="down();" class='btn ui-state-default'>
								</div>
							</td>
							</tr>
						</table>
					</div>
				</div>
			
			<div class="mDiv">
				<div class="title title-1">
					<h3>
						<font style="float: left">运维日志列表</font>
					</h3>
				</div>
			</div>    
            <table id="render_mission_table" class="tablestyle" cellspacing="0" style="width: 100%;margin-top: 0px;">
				<tr id="head">
					<th>用户账号</th>
					<th>手机号码</th>
					<th>邮箱</th>
					<th>IP地址</th>
					<th>IP归属地</th>
					<th>MAC地址</th>
					<th>运营商</th>
					<th>来源</th>
					<th>登陆时间</th>
					<th>退出时间</th>
				</tr>
			<s:iterator value="#request.list" status="t" var="item">
				<tr <s:if test="#t.Even">style='background-color:#d9ebf5;'</s:if>>
					<td id="<s:property value='#t.index'/>">${USERNAME}</td>
					<td>${PHONENO}</td>
					<td>${EMAIL}</td>
					<td>${IPADDR}</td>
					<td>${LOCATIONADDR}</td>
					<td>${MAC}</td>
					<td>${NET_BOSS}</td>
					<td>${USEROS}</td>
					<td>${LOGINTIME }</td>
					<td>${LOGOUTTIME }</td>
				</tr>
			</s:iterator>
			
				<tr><td colspan="10">
						<div class="but">
							<input type='button' id='uppage_button_1' value='上一页' onclick="up();" class='btn ui-state-default'>
							<input type='button' id='nextpage_button_1' value='下一页' onclick="down();" class='btn ui-state-default'>
						</div>
					</td>
				</tr>
			</table>
			<div>   
			
			<div id="showDiv" style="background-color: #f2f6f7;border: 1px solid #95B8E7;"> 
			<div id="show"></div> 
			</div> 		
		</form>
	</div>
       <script type="text/javascript">  
		
		   function sub()
		   {      
		       $('#nextnum').val(0);
			   form2.submit();		
		   }	
		   
		   function empAll()
			{
				document.getElementById("user_name").value = "";
				document.getElementById("email").value = "";
				document.getElementById("phoneNO").value = "";
				document.getElementById("id_ad").value = "";
				document.getElementById("logintime").value = "";
				document.getElementById("useros").value = "";
				//location='log!logForPortal.action';
			}
		   
		   function down()
		   {      
		       //document.getElementsByID("updownbz")=0;
		       $('#updownbz').val(0)
			   form2.submit();		
		   }	
		   
		   function up()
		   {      
		       //document.getElementsByID("updownbz")=1;
		       $('#updownbz').val(1)
			   form2.submit();		
		   }		
		   

var bz ="none";
$(function(){

	
//显示内容DIV   
var showDiv = document.getElementById('showDiv'); 

//默认不显示
showDiv.style.display="none"; 
 
//添加鼠标动作监听

	$('#render_mission_table td').mouseover(function(e)
	    {
	    
	    if(typeof($(this).attr('id')) != "undefined")   
	    {        
			DialogSwitch('showDiv','block',mouseOffset(e).left,mouseOffset(e).top,150,150,getText(this)); 
		}
		else
		{
		   // DialogSwitch('showDiv','none',0,0,0,0,""); 
		   showDiv.style.display="none";
		}
		
	});  

	$('#render_mission_table td').mouseout(function()
	{
		
		showDiv.style.display="none";  
		//DialogSwitch('showDiv','none',0,0,0,0,"");  
		 
	});
});
 
function getText(id)
{  
   return $(id).html(); 
}  
//弹出框开关控制
  
function DialogSwitch(id,sty,l,t,width,height,nick_name)
{  

    bz = sty;
    if(nick_name!="")
	{
	               /**
	                var xxxx ="";
	        		$.ajax({
					type:"POST",
					data:{"nick_name" : nick_name},
					cache: false,
					async:true,
					url: "manageRender!getDetailByNick.action",
					success:function(result)
					{	
						var data=eval("("+result+")");
						if(data.length > 0)
						{						 
						 xxxx += "<p class='p-content'>昵称:<span>"+data[0].NICKNAME+"</span></p>";
						 xxxx += "<p class='p-content'>手机号码:<span>"+data[0].PHONE+"</span></p>";
						 xxxx += "<p class='p-content'>账户余额:<span>"+data[0].BALANCE+"元</span></p>";
						 xxxx += "<p class='p-content'>所在地:<span>" + (data[0].PRCITY === null ? "" : data[0].PRCITY ) + "</span></p>";
						 xxxx += "<p class='p-content'>充值总额:<span>"+data[0].RECHARGE_MONEY+"元</span></p>";
						 xxxx += "<p class='p-content'>消费总额:<span>"+data[0].COST_MONEY+"元</span></p>";
						 $('#show').html(xxxx);  
						 }
					}});
			
			var showDiv = document.getElementById(id); 
			//此属性一定要设置
			showDiv.style.position="absolute"; 
			//显示隐藏控制
			showDiv.style.display=sty;  
			   $("#showDiv")
			   .css({
			   "top": t +"px",
			   "left":  l +"px",
			   "width":  width +"px",
			   "height": height+"px"
			  }).show("fast"); 
			  ***/  
	}
}  
//获取鼠标所在元素的位移

function getOffset(id)
{  
	var left = $('#'+id).offset().left; 
	var top = $('#'+id).offset().top; 
	return {left:left,top:top}; 
 
}  
 
var Mouse = function(e)
{   
	mouse = new MouseEvent(e); 
	var leftpos = mouse.x; 
	var toppos = mouse.y;  
	return {left:leftpos,top:toppos};  
}  
//获取鼠标位置
 
function mouseOffset(e)
{  
	var leftpos = e.pageX; 
	var toppos = e.pageY;  
	return {left:leftpos,top:toppos}; 
}  
 

	   
		</script>
	</body>
</html>
