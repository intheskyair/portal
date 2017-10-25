<%@ page contentType="text/html; charset=UTF-8"%>
<html>
    <head>
	<title>线程启停管理</title>
	</head>
	<body>	
	<div class="div div-1">
		<div class="title title-1">
			<h3>
					<img src='../images/fold.png' onclick="showAndHide(this);"  class="imgShrotate">
					<font>线程启停管理</font>
			</h3>
	</div>
	<div class="div-main-1" >
			<table cellpadding="0" cellspacing="0" border="0" class="table-1">
				<tr>
					<td style="width:200px;"><label for="input_1" class="lbl-1">运维线程启停管理：</label></td>
					<td class="wi-align-l">
					<div id="thread_start" >
					  <a href="#" id="btn_thread_start">启动线程</a>
					</div>
					</td>
				</tr>
				<tr>
					<td style="width:200px;"><label for="input_1" class="lbl-1">运维维护启停管理：</label></td>
					<td class="wi-align-l"><a href="#" id="btn_system_start">启动运维</a></td>
					<td class="wi-align-l"><a href="javascript:window.parent.location.href='admin!logout.action'" hidefocus="true" id="btn_system_stop">注销</a></td>
				</tr>
			</table>
		</div>
	</div>
		<script>		
		 var thread;
		 var param = {
			
			btn_thread_start:function()
			{
				if(confirm("你确定需要启动运维后台的线程?")) 
				{
				   var queryP = {};
					$.ajax({
						type: "POST",
						url: "queryUser!startThread.action?operation=init",
						cache: false,
						data: queryP,
						success: function(msg)
						{
								var result=eval("("+msg+")");
								if (result.msg=="ok") 
								{
									showMsg("启动线程成功");
									           $('#btn_thread_start').css('background','#e8e8e8');
                                               $('#btn_thread_start').button({ icons: {primary:'ui-icon-search'}}).unbind('click');
									param.loadData();
								} else 
								{
									showMsg("启动线程失败");
								}
						}
					}); 
				}
			}	,
			loadData:function()
			{
			   var queryP = {};
				$.ajax({
					type: "POST",
					url: "queryUser!queryThread.action?operation=init",
					cache: false,
					async:false,
					data: queryP,
					success: function(msg)
					{
						var data=eval("("+msg+")");
                        thread = data.thread;
					}
				}); 
			} 
		};	 
 
 $(function()
    {
        param.loadData();
        if(thread==1)
        {
           $('#btn_thread_start').css('background','#e8e8e8');
           $('#btn_thread_start').button({ icons: {primary:'ui-icon-search'} });
        } 
        else
        {
          	$('#btn_thread_start').button().on("click",function(){
			      param.btn_thread_start();
		    });
        }   
		/*参数管理-查询按钮功能*/
		$('#btn_system_start').button({ icons: {primary:'ui-icon-search'} });
		$('#btn_system_stop').button({ icons: {primary:'ui-icon-search'} });

	});
		


         	
		</script>
	</body>
</html>
