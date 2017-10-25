<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script type="text/javascript" src="../js/sms/smsList.js"></script>

<!-- 查询条件 -->
<div class="div div-1">
	<div class="title title-1">
		<h3>
			<img src='../images/fold.png' onclick="showAndHide(this,'sms_grid');"  class="imgShrotate">
				<font>查询条件</font>
		</h3>
	</div>
	<div class="div-main-1" s>
		<table cellpadding="0" cellspacing="0" border="0" class="table-1">
			<tr style="font-size: 14px">
				<td style="width: 100px;"><label for="input_1" class="lbl-1">手机号码：</label></td>
				<td class="wi-align-l" style="width: 160px;"><input
					type="text" id="phone_sms" name="phone_sms" class="wi-ipt-1" />
				</td>
				<td width="520px" align="center"><label for="input_2" class="lbl-1">发送时间段：</label> 
					<input type="text"  readonly id="send_time1_sms" name="send_time1_sms" class="Wdate" 
					onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>&nbsp;&nbsp;至&nbsp;
					<input type="text"  readonly id="send_time2_sms" name="send_time2_sms" class="Wdate" 
					onclick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
				</td>
				<td class="wi-align-l"><a href="#" id="btn_sms_search">查询</a></td>
				<td class="wi-align-l" style="width: 100px;">
				<button id="btn_sms_empty" style="width:74px; height:29px;font-size:14px;background-color:#3ea4e8;color: #fff">清空</button></td>
			</tr>
		</table>
	</div>
</div>

<!-- 数据填充 -->
<div class="div div-2" style="overflow: auto;">
	<table id="sms_grid" style="display: none; padding: 0 0 0 0;"></table>
</div>

<!-- 隐藏新增或修改 -->
<div id="dlg_sms" style="display: none">
	<input type="hidden" id="sms_action" /> 
	<input type="hidden" id="dlg_sms_id" />
	<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
		<tr>
			<td style="width: 100px;"><label for="input_1">系统名称：</label></td>
			<td class="wi-align-l" style="width: 200px;"><input type="text"
				id="dlg_sysname" value="" class="wi-ipt-1" /></td>
		</tr>
		<tr>
			<td style="width: 100px;"><label for="input_1">手机号码：</label></td>
			<td class="wi-align-l" style="width: 200px;"><input type="text"
				id="dlg_phone" value="" class="wi-ipt-1" maxlength="11" /></td>
		</tr>
		<tr>
			<td style="width: 100px;"><label for="input_1">信息内容：</label></td>
			<td class="wi-align-l" style="width: 200px;"><input type="text"
				id="dlg_content" value="" class="wi-ipt-1" maxlength="100"/></td>
		</tr>
		<tr>
			<td style="width: 100px;"><label for="input_1">发送时间：</label></td>
			<td class="wi-align-l" style="width: 200px;"><input type="text"
				id="dlg_time" value="" class="wi-ipt-1"/></td>
		</tr>
		<tr>
			<td style="width: 100px;"><label for="input_1">返回状态：</label></td>
			<td class="wi-align-l" style="width: 200px;"><input type="text"
				id="dlg_status" value="" class="wi-ipt-1"/></td>
		</tr>
		<tr>
			<td style="width: 100px;"><label for="input_1">状态描述：</label></td>
			<td class="wi-align-l" style="width: 200px;"><input type="text"
				id="dlg_message" value="" class="wi-ipt-1"/></td>
		</tr>
	</table>
</div>
<div id="dlg_sms_export" style="display:none">
	<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
		<tr >
			<td >序号<input type="checkbox" id="export-id" checked="checked"></input></td>
			<td >系统名称<input type="checkbox" id="export-sys_name" checked="checked"></input></td>
			<td >手机号码<input type="checkbox" id="export-phone" checked="checked"></input></td>
			<td >信息内容<input type="checkbox" id="export-content" checked="checked"></input></td>
		</tr>
		<tr >
			<td >发送时间<input type="checkbox" id="export-send_time" checked="checked"></input></td>
			<td >返回状态<input type="checkbox" id="export-returnstatus" checked="checked"></input></td>
			<td >状态描述<input type="checkbox" id="export-message" checked="checked"></input></td>
			<td ></td>
		</tr>
	</table>
</div>