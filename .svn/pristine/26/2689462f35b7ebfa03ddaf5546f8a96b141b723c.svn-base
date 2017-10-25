<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script type="text/javascript" src="../js/sms/emailList.js"></script>

<!-- 查询条件 -->
<div class="div div-1">
	<div class="title title-1">
		<h3>
					<img src='../images/fold.png' onclick="showAndHide(this,'email_grid');"  class="imgShrotate">
				<font>查询条件</font>
			</h3>
	</div>
	<div class="div-main-1" >
		<table cellpadding="0" cellspacing="0" border="0" class="table-1">
		<tr style="font-size: 14px">
				<td style="width:10%;"><label for="input_1" class="lbl-1">邮件发送方：</label></td>
				<td class="wi-align-l" style="width:12%;">
					<input type="text" id="sender_email" name="sender_email" class="wi-ipt-1" />
				</td>
				<td style="width:10%;"><label for="input_1" class="lbl-1">邮件接收方：</label></td>
				<td class="wi-align-l" style="width:12%;">
					<input type="text" id="receive_email" name="receive_email" class="wi-ipt-1" />
				</td>
				<td style="width:10%;"><label for="input_1" class="lbl-1">发送状态：</label></td>
				<td class="wi-align-l" style="width:12%;">
					<select name="txt_email_state" id="txt_email_state" class="wi-sel-1">
						<option value="">全部</option>
						<option value="02">成功</option>
						<option value="03">失败</option>
					</select>
				</td>
         	</tr>
			<tr style="font-size: 14px">
				<td style="width:10%;"><label for="input_1" class="lbl-1">发送时间段：</label></td>
				<td colspan="6" align="left" style="width:60%;">
					<input type="radio" name="text_email_date" value="0" checked />全部&nbsp;
					<input type="radio" name="text_email_date" value="1" />当天&nbsp;
					<input type="radio" name="text_email_date" value="2" />本周&nbsp;
					<input type="radio" name="text_email_date" value="3" />本月&nbsp;
					<input type="radio" name="text_email_date" value="4" />自定义<span id="email_self_date" style="display: none">：
					<input type="text" id="text_email_preDate" name="text_email_preDate" readonly class="Wdate" style="height:22px;width:25%;"
					 onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />&nbsp;至&nbsp;
					<input type="text" id="text_email_postDate" name="text_email_postDate" readonly class="Wdate" style="height:22px;width:25%;"
					 onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" /></span>
				</td>
				<td class="wi-align-l" style="width:20%;text-align: right">
					<button id="btn_email_search" style="width:80px; height:28px;font-size:14px;background-color:#3ea4e8;color: #fff">查&nbsp;询</button>
					<button id="btn_email_empty"  style="width:80px; height:28px;font-size:14px;background-color:#3ea4e8;color: #fff">清&nbsp;空</button>
				</td>	
			</tr>
		</table>
	</div>
</div>

<!-- 数据填充 -->
<div class="div div-2" style="overflow: auto;">
	<table id="email_grid" style="display: none; padding: 0 0 0 0;"></table>
</div>

<div id="dlg_email_export" style="display:none">
	<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
		<tr >
			<td >邮件发送方<input type="checkbox" id="export-sender" checked="checked"></input></td>
			<td >邮件接收方<input type="checkbox" id="export-receive" checked="checked"></input></td>
			<td >邮件标题<input type="checkbox" id="export-title" checked="checked"></input></td>
		</tr>
		<tr >
			<td >邮件内容<input type="checkbox" id="export-content" checked="checked"></input></td>
			<td >发送状态<input type="checkbox" id="export-state" checked="checked"></input></td>
			<td >发送时间<input type="checkbox" id="export-send_time" checked="checked"></input></td>
		</tr>
	</table>
</div>