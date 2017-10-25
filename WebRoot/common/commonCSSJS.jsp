<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta name="renderer" content="webkit">
<script type="text/javascript" src="${ctx}/views/common/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/views/common/js/json2.js"></script>
<script type="text/javascript" src="${ctx}/views/common/js/jquery.bgiframe.js"></script>
<script type="text/javascript" src="${ctx}/views/common/js/jquery.contextmenu.r2.js"></script>
<!--=B 模板库  -->
<link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquery-ui-jquiGui.css"/>
<script type="text/javascript" src="${ctx}/views/common/js/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/views/common/js/jquery.layout.min.js"></script>
<script type="text/javascript" src="${ctx}/views/common/js/jquery.wresize.js"></script>
<script type="text/javascript" src="${ctx}/views/common/js/ui.tabs.page.js"></script>
<!-- flexigrid  -->
<script type="text/javascript" src="${ctx}/views/common/js/flexigrid/flexigrid.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/flexigrid/flexigrid.css"/>
<!-- 验证插件 -->
<link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/validate/jquery_validate.css"/>
<script type="text/javascript" src="${ctx}/views/common/js/validate/jquery.validate.js"></script>
<!-- 向导插件 -->
<link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/smartWizard/smart_wizard.css"/>
<!-- 扩展jQuery以及jQuery插件功能的js -->
<script type="text/javascript" src="${ctx}/views/common/js/jquery.extend.js"></script>
<!-- 样式库  -->
<link rel="stylesheet" type="text/css" href="${ctx}/views/common/css/themes/css/jquiGui.css" />
<!-- 模板调用变量库  -->
<script type="text/javascript" src="${ctx}/views/common/css/themes/js/GlobalValue.js"></script>
<!--=E 模板库  -->
<script type="text/javascript" src="${ctx}/views/common/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/views/common/js/common.js"></script>
<!-- echart框架-->
<script type="text/javascript" src="${ctx}/js/js_echarts/echarts.js"></script>
<script type="text/javascript" src="${ctx}/js/headUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
<style>
.imgShrotate{
	float: left;
	margin-top: 5px;
	-webkit-transform:rotate(90deg);
	-moz-transform:rotate(90deg);
	filter:progid:DXImageTransform.Microsoft.BasicImage(rotation=3);
	display: block;
	cursor: pointer;
}
</style>
</html> 
<script type="text/javascript">
/* 计算flexGrid 的height
*@param objNext flexGrid后面一个对象
*@param objWrap flexGrid外面包围的对象
*@return flexGridHeight flexGrid的高
* 
* 解决ui-layout-south不存在出错BUG
*/
var x = 0;
function flexHeight(htmlSrc){
	/* var objWrap = $("#"+fgId).parent();
	var $tabs = $('#tabs').find("div.ui-tabs-panel,div.ui-widget-content,div.ui-corner-bottom");
	var temp_h = 0;
	alert($($("#"+fgId).parent().parent().children()[0]).height());
	alert($("#"+fgId).parent().parent().height()); //浏览器当前窗口可视区域高度 
	alert($("#"+fgId).parent().width()); 
	var FlexHeight = FlexWrapHeight - temp_h;// 122 = $(".tDiv").outerHeight(true) + $(".hDiv").outerHeight(true)+ $(".pDiv").outerHeight(true)
	objWrap.height(FlexWrapHeight-(($.browser.msie && parseInt($.browser.version)<=6)?0:0));	 */
	var bodyH = document.body.clientHeight;
	//var tabH = $(window.parent.document.body).find(".ui-corner-all").eq(0).children(0).outerHeight();
	if(bodyH == 0){
		/* var $tab = $(window.parent.document.body).find(".ui-tabs-hide").eq(2);
		$(window.parent.document.body).find(".ui-tabs-hide").each(function(i){
			alert($(window.parent.document.body).find(".ui-tabs-hide").eq(i).children(0).html());
		}); */
		//alert(htmlSrc);
		var $tab = {};
		$(window.parent.document.body).find("iframe").each(function(i){
			if($(window.parent.document.body).find("iframe").eq(i).attr("src") == htmlSrc){
				$tab = $(window.parent.document.body).find("iframe").eq(i).parent();
			
				return;
			}
		});
		
		//alert();
// 		$tab.removeClass("ui-tabs-hide");
		bodyH = document.body.clientHeight;
		var headH = $("div.div-1").outerHeight();
		//$tab.addClass("ui-tabs-hide");
		return bodyH - headH - 88+x;
	}else{
		return bodyH - $("div.div-1").outerHeight() - 88+x;
	}
}
</script>