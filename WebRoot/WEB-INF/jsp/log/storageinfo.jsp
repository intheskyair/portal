 <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 <script type="text/javascript">
 var param_storage = {
			
			loadData:function(){
				var queryP = {};
				$.ajax({
					type: "POST",
					url: "log!logForStorage.action?operation=init",
					cache: false,
					data: queryP,
					success: function(msg)
					{
					  var result=eval("("+msg+")");
                      $('#storage_yw_zx').html(result);
					}
				}); 
			} 
		};
 
 $(function(){
		/*参数管理-查询按钮功能*/
		$('#btn_storage_refush').button().on("click",function(){
		
		              $('#storage_yw_zx').html("");
		
			param_storage.loadData();
		});
		// 初始化数据
		param_storage.loadData();
	});
</script>
<div class="div div-1">
	<div class="title title-1">
		<h3>
			<img src='../images/fold.png' onclick="showAndHide(this);"  class="imgShrotate">
			<font>存储使用情况</font>
		</h3>
	</div>
	<div class="div-main-1" >
		<table cellpadding="0" cellspacing="0" border="0" class="table-1">
			<tr>
				<td style="width:150px;"><label for="input_1" class="lbl-1">储存使用情况：</label></td>
				<td class="wi-align-l" style="width:570px;" id="storage_yw_zx">
				</td>
				<td class="wi-align-l"><a href="#" id="btn_storage_refush">刷新</a></td>
			</tr>
		</table>
	</div>
</div>
