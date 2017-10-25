<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
     import="com.zqkj.bsm.dal.bean.Admin"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/views/shell/js/head.js"></script>
<input type="hidden" value="${pageContext.request.contextPath }" id="portalpath">
<div class="wi-logo">
	<div class="wi-logo-logo" style="padding:0px;">
	<!--  
		<img src="${pageContext.request.contextPath }/images/xuanyun_logo.png" style="width:441px;height: 42px;"/>
	-->
	</div>
</div>
<div class="wi-head-right" >
	<div class="wi-head-right-top">
		<ul class="ui-tabs ui-tabs-nav" id="top_menus" style="/* left: 350px; */top:4px;">
	
		</ul>
		<div class="wi-head-right-title">
	      <!--<h1 id="version"></h1>-->
		</div>
<%-- 		<div class="wi-user">
			<div class="wi-user-wrap">
				<dl class="wi-user-dl">
					<dt>当前用户：</dt>
					<dd>
<!-- 					<%=((Admin)session.getAttribute("admin")).getTruename() %> -->
<!-- 					${sessionScope.admin.truename} -->
						${admin.truename}
					</dd>
					<dd id="changePw"><a href="#">修改密码</a><dd>
					<dd class="wi-user-exit"><a href="javascript:window.parent.location.href='admin!logout.action'" hidefocus="true" id="user_logoff">注销</a></dd>
				</dl>
			</div>
		</div> --%>
	</div>
	<div class="wi-head-menu">
		<ul id="head_menu">
		</ul>
	</div>
	<div id="dlg_head_pw" style="display:none">  
		<table cellpadding="0" cellspacing="0" border="0" class="wi-frm-tbl">
			<tr>
				<td style="width:100px;"><label for="input_1">原密码：</label></td>
				<td colspan="2" class="wi-align-l" style="width:120px;">
					<input type="password" id="old_pw_edit" value="" class="wi-ipt-1" />
				</td>
			</tr>
			<tr>
				<td style="width:100px;"><label for="input_1">新密码：</label></td>
				<td colspan="2" class="wi-align-l" style="width:120px;">
					<input type="password" id="new_pw_edit" value="" class="wi-ipt-1" />
				</td>
			</tr>
			<tr>
				<td style="width:100px;"><label for="input_1">确认新密码：</label></td>
				<td colspan="2" class="wi-align-l" style="width:120px;">
					<input type="password" id="confirm_pw_edit" value="" class="wi-ipt-1" />
				</td>
			</tr>
		</table>
	</div> 
</div>
