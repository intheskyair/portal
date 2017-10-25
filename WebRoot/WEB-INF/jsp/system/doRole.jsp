<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="include.jsp"%>
<html>
	<base target="_self">
	<meta http-equiv="pragma" content="no-cache">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script language="JavaScript" type="text/javascript" src='js/system/dorole.js?version=6'></script>
		<title>权限管理</title>
	</head>
	<body>
	    <div class="notes">
	    <a onClick="cwin()"> 
				<img src="${path}images/1.jpg" align="absmiddle" alt="关闭左栏"></a>&nbsp;&nbsp;菜单管理</div>
		<div class="tablestyle">
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
             <td colspan="3" id="ff">非专业人士请勿修改！谢谢...</td>
          </tr>
          <tr>
             <td valign="top">
                  <div id="font">${username}【${realname}】现没有的权限</div>
                  <div>
                  	  <select id="left" size="10" multiple="multiple" style="height:360px;" class="selectmut1">
						<s:if test="qxList2!=null">
						<s:iterator value="qxList2" id="qx2">
							<option value="${qx2.ID}">${qx2.AUTHNAME}</option>
						</s:iterator>
						</s:if>
					  </select>
                  </div>
             </td>
             <td class="selectmid">
 				<div><input type="button" id="1" value="&gt;&gt;" onClick="sa('left');move('left','right');" class="button"/></div>					
				<div id="menubut"><input type="button" id="2" value="&gt;" onClick="move('left','right');" class="button"/></div>						
                <div id="menubut"><input type="button"  id="3" value="&lt;" onClick="move('right','left');" class="button"/></div>	
				<div><input type="button"  id="4" value="&lt;&lt;" onClick="sa('right');move('right','left');" class="button"/> </div>	           
             </td>            
             <td valign="top">
                <div id="font">${username}【${realname}】已拥有的角色</div>
                <div>
                	<select id="right" size="10" multiple="multiple" style="height:360px;" class="selectmut3">
						<s:if test="qxList2!=null">
						<s:iterator value="qxList" id="qx">
							<option value="${qx.ID}">${qx.AUTHNAME}</option>
						</s:iterator>
						</s:if>
					</select>
                </div>
             </td>	
           </tr>	
           <tr>
             <td colspan="3">
               <div class="but">
               	  <input type='button' value='确定' id='submit' onclick='submit2()' class="buttonb">
               	  <input type='button' value='刷新' id='submit' onclick='window.location.reload()' class="buttonb">
               	  <input type='button' value='返回' id='submit' onclick='history.back()' class="buttonb">
               	  <input type='hidden' id='userid' value="${username}">
               </div>
             </td>
           </tr>	
        </table>
     </div>
    &copy;2010&nbsp;&nbsp;中国移动通信&reg;&nbsp;江苏有限公司&nbsp;常州分公司
	</body>
</html>