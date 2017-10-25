/**
* <p>Copyright: ©2012 tohot Co., Ltd. All rights reserved</p>
* <p>Title: 登录后框架页面用到的JS</p> 
* <p>Description: jquery ui layout布局设置; tabs组件设置; 页面刷新时方法计算; 等</p>
* @author xielh zhaof
* @version 2.1 2012-3-21
*/ 
var page_num = 0;
var timer;//定时器
var myLayout; 
var cur_winWidth;
var cur_winHeight;
var win_resizeTimer;
$(function(){
    page_num = parseInt($("#page_num").val());
	cur_winHeight = $("body").height()-1;
	cur_winWidth = $("body").width();
	$("#wiWrap").width(cur_winWidth).height(cur_winHeight);
	/*******************************layout***************************/
	myLayout = $('#wiWrap').layout({
		defaults: {
			applyDefaultStyles:false, 	
			closable:false,        //可以被关闭
			resizable:false,      //可以改变大小
			togglerTip_open:"隐藏", //pane打开时，当鼠标移动到边框上按钮上，显示的提示语 
			togglerTip_closed:"显示", //pane关闭时，当鼠标移动到边框上按钮上，显示的提示语
			resizerTip:"可调整大小",  //鼠标移到边框时，提示语
			onclose:resizeTabsPanel, //关闭时调用
			onopen:resizeTabsPanel,  //打开时调用 
			onresize:allElementResize //改变大小时调用
		}, 
		west:{size:200,spacing_open:GlobalValue.westSpacingOpen,closable:true,resizable:true,minSize:200},
		north:{size:35,spacing_open:GlobalValue.northSpacingOpen},
		south:{size:38,spacing_open:GlobalValue.southSpacingOpen},
		east:{size:100,spacing_open:GlobalValue.eastSpacingOpen,closable:true,initClosed:false}
	});
	//myLayout.close("east");
	/*******************************layout***** **********************/
	//在IE中load()方法去是拿缓存的数据而不是向服务器拿,1：可以通过设置Internet选项  缓存设置解决，2：cache: false，3传一个随机参数
	$.ajaxSetup ({ cache: false });
	$(".ui-layout-north").load("getMenu!toTop.action"); 
	/********************************tabs*********************************/
	var $tabs = $('#tabs').width(-1)
		.height($(".ui-layout-center").height()-1)
		.html('<ul></ul>')
		.tabs();
	//若是多页签方式，则初始化分页控件
	if(GlobalValue.tabsType==0){
		$tabs.tabs("page",{nextButton: '&#187;',prevButton: '&#171;'}).hide();
		//addTab({"title":"欢迎页面","key":"welcome",url:"views/cxtj/yhscx.html",noClose:1});
		var option = {};
		option.key = "welcome";
		option.title = "我的工作台";
		option.url = "../admin!todesk.action";
		option.isIframe = true;
		option.noClose = 1;
		addTab(option);
	}else{
		$tabs.tabs({tabTemplate: '<li><div class="tabs-li-wrap"><a href="#{href}" hidefocus="true" id="a_title">#{label}</a></li>'}).hide();
	}
	/********************************tabs*********************************/

	/********************************tree menu*********************************/
	$("#div_menu").load("getMenu!viewMenu.action?mk="+$("#mk").val());
	/********************************tree menu*********************************/
	$(window).wresize(allElementResize); 
	//$( "#user_logoff" ).button({ icons: {primary:'ui-icon-key'} });
	//$( "#user_exit" ).button({ icons: {primary:'ui-icon-power'} });
	
//	var option = {};
//	option.key = 115;
//	option.title = '文件上传交换项';
//	option.url = 'views/imports/txtItemImport.html';
//	option.isIframe = false;
//	//option.style = treeNode.isIframe?"width:100%;height:100%":"";
//	addTab(option);
	require.config({
		paths:{
			echarts:'../js/js_echarts'
		}
	});
});


//窗口大小变更事件
function allElementResize() {	
	cur_winHeight = $("body").height()-1;
	cur_winWidth = $("body").width();
	$("#wiWrap").width(cur_winWidth).height(cur_winHeight);
	resizeTabsPanel();
}
//标签页内部div重新计算大小
function resizeTabsPanel(){
	cur_winHeight = $("body").height()-1;
	cur_winWidth = $("body").width();
	$("#wiWrap").width(cur_winWidth).height(cur_winHeight);
	var centerHeight = cur_winHeight 
		- Number($(".ui-layout-north:visible").outerHeight(true))
		- Number($(".ui-layout-south:visible").outerHeight(true))
		- Number(GlobalValue.northSpacingOpen)
		- Number(GlobalValue.southSpacingOpen);
	var centerWidth = cur_winWidth 
		- Number($(".ui-layout-west:visible").outerWidth(true))
		- Number($(".ui-layout-east:visible").outerWidth(true))
		- Number(GlobalValue.westSpacingOpen)
		- Number(GlobalValue.eastSpacingOpen);
	
	$('#tabs')
		.height(centerHeight)
		.find("div.ui-tabs-panel,div.ui-tabs-hide").css({
			height:centerHeight - objArea($('#tabs').find("div.ui-tabs-panel,div.ui-tabs-hide"))[1] - $('#tabs').find(".ui-tabs-nav").outerHeight(true),
			width:centerWidth - objArea($('#tabs').find("div.ui-tabs-panel,div.ui-tabs-hide"))[0]
		}).end().find(".wi-tabs-panel-wrap").css({
			height:$('#tabs').find("div.ui-tabs-panel,div.ui-tabs-hide").height() - objArea($('#tabs').find(".wi-tabs-panel-wrap"))[1] ,
			width:$('#tabs').find("div.ui-tabs-panel,div.ui-tabs-hide").width() - objArea($('#tabs').find(".wi-tabs-panel-wrap"))[0] 
		});
}
//左侧菜单树重新计算大小
function resizeTreeMenu(){
	$('#container').accordion( "resize" );
}

//新增tab页
function addTab(data){
	var tabId = "";
	//data.key == "doprocess"  待处理流程，多个
	if(!data.key || data.key == "doprocess"){
		tabId = "tab_"+new Date().getTime();
	}else{
		tabId = "tab_"+data.key;
	}
	var $tabs = $('#tabs');
	var title = data.title?(data.title.length>6?(data.title.substring(0,6)+"..."):data.title):"新标签页";
	var url_tmp ="";
	
	if(data.url){
		var flag = true;
		var index = data.url.indexOf("?");
		index = -1;
		if(index> -1)
		{
		   url_tmp = data.url.substring(0,index);
		}
		else
		{
		   url_tmp = data.url;
		}
		$tabs.find("input:hidden").each(function(){
			if(this.value==url_tmp)
			{
				actTab(this.value,data.url,tabId);
				//actTab(data.url);
				flag = false;
				return false;
			}
		});
		// 当标签超过5个的时候自动关闭第一个打开的标签
		//console.log($("ul li:visible", $tabs).length);
		//ui-state-default
		//$("ul[class='ui-tabs-nav'] li:visible", $tabs).length >= 10
		if ($("#tabs").children("ul").eq(0).children("li").length >= 10) {
			closeTab($("#tabs").children("ul").eq(0).children("li").eq(2).find("input:hidden")[0].value);
		}
		if(!flag)
			return;
	}
	var url = data.url?data.url:"";
	if(!data.noClose){
		$tabs.tabs({tabTemplate: '<li><div class="tabs-li-wrap"><a href="#{href}"   hidefocus="true" title="'+data.title+'">#{label}</a><input type="hidden" value="'+url_tmp+'" /><span id="span_close_'+tabId+'" title="关闭标签页" class="tabs-close"><a href="###">x</a></span></div></li>'}).tabs('add', '#'+tabId, title).show();
		if(data.url&&!data.isIframe){
			$('#'+tabId).html("<div class='wi-tabs-panel-wrap'></div>");	
			$('#'+tabId+' .wi-tabs-panel-wrap').load(data.url);
		}else if(data.url){
			$('#'+tabId).html("<iframe src='"+data.url+"' frameborder='0' scrolling='no' class='tabs-iframe'></iframe>");
		}
	}else{
		$tabs.tabs({tabTemplate: '<li><div class="tabs-li-wrap"><a href="#{href}" hidefocus="true" title="'+data.title+'">#{label}</a><input type="hidden" value="'+url_tmp+'" /></div></li>'}).tabs('add', '#'+tabId,title).show();
		if(data.url&&!data.isIframe){
			$('#'+tabId).html("<div class='wi-tabs-panel-wrap'></div>");
			$('#'+tabId+' .wi-tabs-panel-wrap').load(data.url);
		}else if(data.url){
			$('#'+tabId).html("<iframe src='"+data.url+"' frameborder='0' scrolling='auto' class='tabs-iframe'></iframe>");
		}
	}
	$('#span_close_'+tabId).unbind('click').bind('click', function() {
		var index = $('li.ui-state-default',$('#tabs')).index($(this).parent().parent());
		$('#tabs').tabs('remove', index);
		if($('#tabs').tabs("length")==1)
			$('#tabs').hide();
	});
	resizeTabsPanel();
}

function myLoad(id,url){
	var temp = $("#"+ id).children("div").children("div").children("div").children("div").children("div").children("ul").eq(0);
	if(temp.length > 0){
		//temp.children("li").eq(0).trigger("click");
		//addTab(data);
		$("#"+ id + '.wi-tabs-panel-wrap').load(url);
	}
}

//激活标签页
function actTab(url,true_url,tabId){
	var index = -1;
	index = $('li.ui-state-default',$('#tabs')).index($("input:hidden[value='"+url+"']").parent().parent());
	$('#tabs').tabs('select', index , true);
	if($('#'+ tabId ).children("iframe").length == 1){
		$('#'+ tabId ).children("iframe").attr("src",true_url);
	}else{
		$('#'+tabId+' .wi-tabs-panel-wrap').load(true_url);
	}
	
}

//关闭标签页
function closeTab(url){
	var index = -1;
	if(url != "../admin!todesk.action"){
		index = $('li.ui-state-default',$('#tabs')).index($("input:hidden[value='"+url+"']").parent().parent());
		$('#tabs').tabs('remove', index);
	}else{
		index = $('li.ui-state-default',$('#tabs')).index(2);
		$('#tabs').tabs('remove', index);
	}
}

//加载tab页（单页签）
function addSingleTab(data){
	var tabId = "tab_main";
	var $tabs = $('#tabs');
	var title = data.title?data.title:"新标签页";
	var url = data.url?data.url:"";
	if($tabs.tabs("length")>0){
		$("#a_title").text(title);
		$tabs.show();
	}else{
		$tabs.tabs('add', '#'+tabId,title).show();
	}
	if(data.url&&!data.isIframe){
		$('#'+tabId).html("<div class='wi-tabs-panel-wrap'></div>");
		$('#'+tabId+' .wi-tabs-panel-wrap').load(data.url);
	}else if(data.url){
		$('#'+tabId).html("<iframe src='"+data.url+"' frameborder='0' scrolling='auto' class='tabs-iframe'></iframe>");
	}
	resizeTabsPanel();
}


/* 计算对象 的margin padding border的大小 zhaof
*@param a 必需 [对象] 需要计算的对象
*@return FitLayoutVarArr [数组]，0为水平位置的大小，1为垂直位置的大小
*/
function objArea(a) {
    var objAreaArr = [],
    MarginW = Number(parseInt( a.css("margin-left"))) + Number(parseInt(a.css("margin-right")) ),
    PaddingW = Number(parseInt( a.css("padding-left"))) + Number(parseInt(a.css("padding-right")) ),
    BorderW = Number(parseInt( a.css("border-left-width"))) + Number(parseInt(a.css("border-right-width")) ),
	
    MarginH = Number(parseInt( a.css("margin-top"))) + Number(parseInt(a.css("margin-bottom")) ),
    PaddingH = Number(parseInt( a.css("padding-top"))) + Number(parseInt(a.css("padding-bottom")) ),
    BorderH = Number(parseInt( a.css("border-top-width"))) + Number(parseInt(a.css("border-bottom-width")) );
    objAreaArr[0] = MarginW + PaddingW + BorderW;
    objAreaArr[1] = MarginH + PaddingH + BorderH;
    return objAreaArr;
};


/* 计算flexGrid 的height
*@param objNext flexGrid后面一个对象
*@param objWrap flexGrid外面包围的对象
*@return flexGridHeight flexGrid的高
* 
* 解决ui-layout-south不存在出错BUG
*/

function flexHeight(fgId,objNext){
	var objWrap = $("#"+fgId).parent();
	var $tabs = $('#tabs').find("div.ui-tabs-panel,div.ui-widget-content,div.ui-corner-bottom");
	var temp_h = 0;	
	if(!objNext){
		if($(".ui-layout-south:visible").length >0)
			objNext = $(".ui-layout-south:visible");
		else
			objNext = $(".wi-width");
	}		
	if(false == arguments[2]){
		temp_h = 85;
	}else{
		temp_h =113;
	}
	var FlexWrapHeight
		 = getIntValue(objNext.offset().top)
			 - parseInt(getIntValue(objNext.css("margin-top")))
			 - parseInt(getIntValue(objNext.css("border-top-width")))
			 - getIntValue(objWrap.offset().top) 
			 - getIntValue(objArea(objWrap)[1])
			 - parseInt(getIntValue($tabs.css("padding-bottom")))
			 - parseInt(getIntValue($tabs.css("border-bottom-width")))
			 - parseInt(getIntValue($tabs.css("margin-bottom")))
			 - parseInt(getIntValue($tabs.find(".wi-tabs-panel-wrap").css("padding-bottom")))
			 - parseInt(getIntValue($tabs.find(".wi-tabs-panel-wrap").css("border-bottom-width")))
			 - parseInt(getIntValue($tabs.find(".wi-tabs-panel-wrap").css("margin-bottom")));
	var FlexHeight = FlexWrapHeight - temp_h;// 122 = $(".tDiv").outerHeight(true) + $(".hDiv").outerHeight(true)+ $(".pDiv").outerHeight(true)
	objWrap.height(FlexWrapHeight-(($.browser.msie && parseInt($.browser.version)<=6)?0:0));	
	return FlexHeight -(($.browser.msie && parseInt($.browser.version)<=6)?0:0);
}


