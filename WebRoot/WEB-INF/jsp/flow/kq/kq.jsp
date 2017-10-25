<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head> 
  <%@ include file="/common/meta.jsp"%>
    <title>月度考勤</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/css/plan.css"/>
    <link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
    <link rel="stylesheet" type="text/css" href="styles/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="styles/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquiGui.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquery-ui-jquiGui.css"/>
    <script src="${ctx}/views/common/js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="styles/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/json2.js"></script>
    <script type="text/javascript" src="${ctx}/views/common/js/common.js"></script>
    <script type="text/javascript" src="styles/easyui/locale/easyui-lang-zh_CN.js"></script>    
	<style type="text/css">
		.t_full{
			width: 100%;
			height:200px;
			border: 0px;
			resize:none;
		}
		textarea {
			padding:6px;
			line-height: 18px;
    		overflow-y: auto;
		}
	</style>
	
  </head>
  <body style="overflow: scroll;">
   <form id="inputForm" action="" method="post" target="mainFrame">
    <input type="hidden" id="processId" name="processId" value="${map.processId}" />
	<input type="hidden" id="orderId" name="orderId" value="${map.orderId}" />
	<input type="hidden" id="taskId" name="taskId" value="${map.taskId}" />
	<input type="hidden" id="taskName" name="taskName" value="${map.taskName}" />
	<input type="hidden" id="title" name="title" value="月度考勤" />
	<input type="hidden" id="year" name="year" value="${map.year}" />
	<input type="hidden" id="month" name="month" value="${map.month}" />
	<input type="hidden" id="json" name="json"/>
	<!-- 下步流程权限ID -->
	<input type="hidden" id="assignee" name="assignee" value="${map.assignee}"/>
	<input type="hidden" name="userList" id="userList"/>
	
	<div id="div_t" class="datagrid-toolbar" style="padding:5px;height:auto">
				        月份: <input class="easyui-datebox" id="dstartTime"   style="width:100px">
		</div>	
	<div>
	<p class="btt">常州市水利局<span id="yearS">${map.year}</span>年<span id="monthS">${map.month}</span>月份考勤表<span id="detail"></span></p>
	<table style="width:98%; margin-top:20px;" cellpadding="0" cellspacing="0">
		<tr>
 			<td  style=" text-align:left">部门：<input class="nyr"  style="width:250px;" type="text" id="dw" name="dw" value="${admin.org_name}" readonly="readonly"/></td>
		</tr>
	</table>
	<br/>
	<div  style="width:100%" id="tabs">
	
	</div>
	</div>
<div class="div_btn">
<table align="center" border="0" cellpadding="0" cellspacing="0" style="margin-top: 10px;">
				<tbody><tr align="center">
					<td colspan="1" >
					<input type="button" class="selectBtn" id="submit" value="提交" onclick="kq_user.changeAuth()"/>
						&nbsp;&nbsp;
						<input type="button" class="selectBtn"  name="reback" value="返回"
							onclick="bakIframe('${map.label}','${map.bakurl}')">
						
					</td>
				</tr>
			</tbody></table>
</div>
</form>
	<div id="view">
		<div class="outer">
			<ul class="tab">
				<li class="current" onclick="kq_user.personDetails(1)" id="begin">流程详情</li>
				<li onclick="kq_user.personDetails(2)">查看表单</li>
			</ul>
			<div class="content_y">
						<div id="flowTable"></div>
						<div id="formTable"></div>
			</div>
		</div>
	</div>
<div><a id="msg_end" name="1" href="#1">&nbsp</a></div>		
<div id="kq_process_sig">
</div>

<div id="dlg_auth_distribute_kq" class="easyui-dialog" closed="true">
		<form method = "post" name ="authchoose">
			<table border="0">
				<tr style="height:35px">
					<td class="wi-align-l" style="text-align:center">待分配用户：</td>
					<td class="wi-align-l" ></td>
					<td class="wi-align-l" style="text-align:center">已选择用户：</td>					
				</tr>
				<tr>
					<td>
						<select multiple name="username_list1" id="username_list1" class="wi-sel-1" style="width:150px;height:260px" >
						</select>
					</td>
					<td style="width:200px;text-align:center">
						<input style="width:50px" type ="button" value ="添加" onclick="kq_user.moveOption(document.authchoose.username_list1,document.authchoose.username_list2);"><br/><br/>
						<input style="width:50px" type ="button" value ="移除" onclick="kq_user.moveOption(document.authchoose.username_list2,document.authchoose.username_list1);"><br/>
					</td>
					<td >
						<select multiple name="username_list2" id="username_list2" class="wi-sel-1" style="width:150px;height:260px" >
						</select>
					</td>
				</tr>
			</table>
		</form>
	</div>
    <div id="hovertreewindow"  class="easyui-window" closed="true">
   		<input type="radio" name="ck" value="0"/>出勤
   		<input type="radio" name="ck" value="1"/>星期天或节日
   		<input type="radio" name="ck" value="2"/>公休假
   		<input type="radio" name="ck" value="3"/>事假
   		<input type="radio" name="ck" value="4"/>病假
   		<input type="radio" name="ck" value="5"/>旷工
    </div>
	<script type="text/javascript">
	var h; //行号
	var msg = '';
     $(function(){
    	 $("#dstartTime").datebox({
				editable : false,
			            onShowPanel: function () { //显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
			                span.trigger('click'); //触发click事件弹出月份层
			                if (!tds) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
			                    tds = p.find('div.calendar-menu-month-inner td');
			                    tds.click(function (e) {
			                        e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
			                        var year = /\d{4}/.exec(span.html())[0]//得到年份
			                        , month = parseInt($(this).attr('abbr'), 10); //月份，这里不需要+1
			                        $('#dstartTime').datebox('hidePanel')//隐藏日期对象
			                        .datebox('setValue', year + '-' + month); //设置日期的值
			                    });
			                }, 0)
			            },
			            parser: function (s) {
			                if (!s) return new Date();
			                var arr = s.split('-');
			                return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
			            },
			            formatter: function (d) {
			            	//头部年份
			            	$("#yearS").text(d.getFullYear());
			            	$("#year").val(d.getFullYear());
			            	//头部月份
			            	$("#monthS").text(d.getMonth()<9?"0"+(d.getMonth()+1):d.getMonth()+1);
			            	$("#month").val(d.getMonth()<9?"0"+(d.getMonth()+1):d.getMonth()+1);
			            	//绘制表格
			            	kq_user.showBox(d.getFullYear(), d.getMonth()<9?"0"+(d.getMonth()+1):d.getMonth()+1);
			            	//关闭弹出框
			            	 $('#hovertreewindow').window('close', true);
			            	return d.getFullYear() + '-' + (d.getMonth()<9?"0"+(d.getMonth()+1):d.getMonth()+1);/*getMonth返回的是0开始的*/ 
			            },
			            onChange:function(value){
							
			            }
				});
    	 var p = $('#dstartTime').datebox('panel'), //日期选择对象
         tds = false, //日期选择对象中月份
         span = p.find('span.calendar-text'); //显示月份层的触发控件
        // $("#hovertreewindow").window("open");
         
         $("input[type='radio']").click(function(){
        	 var clickVal = $(this).val(); 
        	 var val;
        	 if(clickVal == 0){
        		 val = "<span>√</span>";
        	 }else if(clickVal == 1){
        		 val = "<span style='color:blue;'>★</span>"
        	 }else if(clickVal == 2){
        		 val = "<span style='color:red;'>※</span>"
        	 }else if(clickVal == 3){
        		 val = "<span style='color:red;'>△</span>"
        	 }else if(clickVal == 4){
        		 val = "<span style='color:red;'>●</span>"
        	 }else if(clickVal == 5){
        		 val = "<span style='color:red;'>×</span>"
        	 }
        	 $("#"+h).html(val);
        	 $('#hovertreewindow').window('close', true);
         });
	    	if("${map.orderId}" == '')
	    	{
	    		$("#view").hide();
	    	}else
	    	{
	    		$("#begin").click();
	    		$("#view").show();
	    		$("#submit").val("重新提交");
	    		$("#submit").attr("style","color:red");
	    		$("#detail").html("【被退回】");
	    		$("#detail").attr("style","color:red");
	    	}
	    	kq_user.showBox();
	    }); 
    var kq_user = {
			changeAuth:function(){
	        var dlg_auth_distribute_kq = $("#dlg_auth_distribute_kq").dialog({
				height:$(window).height()*0.5,
			    width:$(window).width()*0.5,
				show: "slide",
				modal:true,
				cache:false,
				title:'批量分配角色',
			    buttons : [ {
                       text : 'OK',
                       iconCls : 'icon-ok',
                       handler : function() {
                       	 $("#submit").attr("style", "background-color:gray");
  						 $("#submit").attr("disabled", true);
  						 kq_user.saveAuth();
                       }
               }]
			});
			
	        $.ajax({
	            type:"get",
	            dataType:"text",
	            async:false,
	            url:"queryUser!getUserList.action?assignee=${map.assignee}&org_id=${map.ord}&autoOrg=${map.autoOrg}",
	            success:function(result) {
	            $("#username_list1").html("");
	            $("#username_list2").html("");
	            	var data=eval("("+result+")");
					var html = '';
					var html2 = '';
					var leftValue = $("#userList").val();
					if(leftValue.length == 0){
						for (var i in data) {
							html += '<option value="'+data[i].USERNAME+'">'+data[i].TRUENAME+'</option>';
						}
						$("#username_list1").append(html);
					}else{
						var leftVals = leftValue.split(",");
						for (var i in data) {
						var flag = false;
							for(var j in leftVals){
								if(data[i].TRUENAME == leftVals[j]){
								flag = true;
									html2 += '<option value="'+data[i].USERNAME+'">'+data[i].TRUENAME+'</option>'; 
								}
							}
							if(!flag){
								html += '<option value="'+data[i].USERNAME+'">'+data[i].TRUENAME+'</option>';
							}
						}
						$("#username_list1").append(html);
						$("#username_list2").append(html2);
					}
	            }
	        });
	        dlg_auth_distribute_kq.dialog("open");
	},
	saveAuth:function(){
		var admin_id ="";
		var username ="";
		if(document.authchoose.username_list2.options.length==0){
			alert("请选择用户！");
			 $("#submit").attr("disabled", false);
			 $("#submit").removeAttr("style");
			return;
		}
		 for(var i=0;i<document.authchoose.username_list2.options.length;i++){
			admin_id += document.authchoose.username_list2.options[i].value+",";
			username += document.authchoose.username_list2.options[i].text+",";
		}
		$("#userList").val(admin_id);
		$("#json").val(kq_user.saveTab($("#tab").find("tr").length - 3));
		
	 	$('#dlg_auth_distribute_kq').dialog('close');
		  $.ajax({
        	url:"kq!process.action",
        	type:"POST",
        	cache: false,
    		data:$("#inputForm").serialize(),
    		success: function(msg){
    			if("success" === msg){
    				alert("提交成功");
    				bakIframe("${map.label}","${map.bakurl}"); 				
    			}
    		}     	
		});
	},
	cg:function(name,day,ap,val,id){
	    $("#hovertreewindow").window({
   		 onResize:function(){
   			$(this).window("open"); 
   		 },
   	   minimizable:false,
   	   maximizable:false,
   	   collapsible:false,
   	   title:name+"-"+$("#monthS").text()+"月"+day+"号"+"-"+ap
    }); 
	    $("input[type='radio'][value='"+val+"']").attr("checked","checked");
	    h = id;
	}
	,
	showBox:function(year,month){
		$.ajax({
			type:"POST",
			data:{year:year,month:month,orderId:'${map.orderId}'},
			cache: false,
			async:false,
			url: "kq!showDays.action",
			success:function(result)
			{	
				var data = JSON2.parse(result);
				var result = "<table id='tab' border='1'  class='table-all' cellspacing='0' align='center' style='margin-top:5px; width:100%; background: #d0e9fe;'><tr><td width='10%'>姓名</td>";
				for (var i = 1 ; i <= data.days ; i ++) {
					result += "<td colspan='2'>"+i+"</td>"
				}
				result +="</tr>"
				for(var i in data.list){
					var TRUENAME = data.list[i].TRUENAME;
					var ADMIN_ID = data.list[i].ADMIN_ID;
					result += "<tr id='row_" + i + "'>";
					result += "<td>"+TRUENAME+"</td><td style='display:none'>"+ADMIN_ID+"</td>";
					var n;
					for(var j in data.list[i].dayList){
						var m = data.list[i].dayList[j];
						if(m == 1){
							n = "<span style='color:blue;'>★</span>";
						}else if(m == 2){
							n = "<span style='color:red;'>※</span>";
						}else if(m == 3){
							n = "<span style='color:red;'>△</span>";
						}else if(m == 4){
							n = "<span style='color:red;'>●</span>";
						}else if(m == 5){
							n = "<span style='color:red;'>×</span>";
						}else{
							n = "<span>√</span>";
						}
						//获取日
						var status;
						//转成int
						j = Number(j);
						//判断上午下午
						var cc;
						if(j%2 == 0){
							cc = "上午";
							status = Number((j + 2)) / 2;
						}else{
							cc = "下午";
							status = Number((j + 1)) / 2;
						}
						result += "<td id='"+i+"_"+j+"' onclick='kq_user.cg(\""+TRUENAME+"\","+status+",\""+cc+"\",this.innerHTML,\""+i+"_"+j+"\")'>"+n+"</td>";
					}
					result += "</tr>";
				}
				result += "<tr><td>本月记事、汇总本科人员出勤、请假、调休</td>"
				if(data.MSG != undefined){
					msg = data.MSG;					
				}
				result +="<td colspan='"+ data.days * 2+"'><textarea id='msg' name='msg' class='t_full'>"+msg+"</textarea></td>";
				result +="</tr>";
				result += "<tr><td>说明</td>"
					result +="<td colspan='"+ data.days * 2+"'>出勤√，星期天或节日 <span style='color:blue;'>★</span>，公休假 <span style='color:red;'>※</span>，事假 <span style='color:red;'>△</span>，病假<span style='color:red;'>●</span>，旷工<span style='color:red;'>×</span></td>";
					result +="</tr>";
				result += "</table>"
				$("#tabs").html(result);
			}
		});
	},
	moveOption:function(list1,list2){
		 var options = list1.options;
		 for(var i=0;i<options.length;i++){
			 if(options[i].selected){
				 list2.appendChild(options[i]);
				 i--;
			 }
		 }
	},
	personDetails:function(com){
		$("#formTable").html("");
		$("#flowTable").html("");
		var orderId = "${map.orderId}"; 
		var pid = "${map.processId}";
		if(com == 2){
			url = "borrow!view.action";
			 data = {"orderId" : orderId,"processId":pid,"path":"${map.path}"};
		}else{
			
			 url = "lczl!viewStep.action";
			 data = {"orderId" : orderId};
		}
		$.ajax({
			type:"POST",
			data:data,
			cache: false,
			async:false,
			url: url,
			success:function(result)
			{	
			    if(com == 1){
			    var result_str = createHtml(result);
			    $("#flowTable").append(result_str);
			    }else{
			    $("#formTable").append(result);
			    }
			    $("#view .tab li").click(function()
			    {
					$("#view .tab li").removeClass("current");
					$(this).addClass("current");
					var n = $(this).index();
					$("#view .content ul").hide();
					$("#view .content ul").eq(n).show();
                });
			}
		});
		document.getElementById("msg_end").click();
		},
	saveTab:function(l){
		var arr = new Array();
		for(var i = 0 ; i < l ; i ++){
				var obj = {};
				var arr1 = new Array();
				$("#row_"+i).children("td").each(function(index){
					if(index == 0){
						obj.TRUENAME = $(this).text();
					}else if(index == 1){
						obj.ADMIN_ID = $(this).text();
					}else{
						var t = $(this).children("span").text();
						if(t == '√'){
							arr1.push(0);
						}else if(t == '★'){
							arr1.push(1);
						}else if(t == '※'){
							arr1.push(2);
						}else if(t == '△'){
							arr1.push(3);
						}else if(t == '●'){
							arr1.push(4);
						}else if(t == '×'){
							arr1.push(5);
						}
					}
				});
				obj.val = arr1;
				arr.push(obj);
		}
		return JSON2.stringify(arr);
	}

};
    
  


</script>	
  </body>
</html>
