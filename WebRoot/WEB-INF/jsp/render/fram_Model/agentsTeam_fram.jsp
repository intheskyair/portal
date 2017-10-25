<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="div div-1">
	<ul style="overflow: auto;margin-left: 60px;" id="agentsTeam_fram_tab"></ul>
	<input type="hidden" value="1" id="agentsTeam_tab_num">
	<div style="width: auto;height: auto;" class="agentsTeam_content"></div>
</div>
<script type="text/javascript">
$(function(){
	getFram_Tab._init($("#agentsTeam_fram_tab"),$(".agentsTeam_content"),"agentsTeam!listAgentsTeam.action?fid=",$("#agentsTeam_tab_num"));
});
</script>