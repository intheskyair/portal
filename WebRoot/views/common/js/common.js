/**
 * jquery的callback方法

 * author:skylen
 * 
 */
var jsCode = ['%20','%u2026','%u2014','%u3010','%u3011','%u3001','%u2018','%u2019','%u201C','%u201D','%u3002','%u3001'];
var phpCode = ['+','%A1%AD','%A1%AA','%A1%BE','%A1%BF','%A1%A2','%A1%AE','%A1%AF','%A1%B0','%A1%B1','%A1%A3','%A1%A2'];
var maskTimer = null;
/**
 * jquery的callback方法，可以直接调用service类中的方法，类似buffalo中的remoteCall
 * 
 * 参数说明
 * serviceName：service类的名称
 * methodName：要访问的方法名
 * paramDataList：参数列表,Array类型,可省略
 * paramTypeList：参数类型列表,Array类型,可省略
 * func：回调方法
 * 
 * @author:cj
 * 
 */
function jCallBack(serviceName,methodName,paramDataList,paramTypeList,func){
	var curObj = $("#wiWrap");
	if(typeof this !== 'function'){
		curObj = $(this);
	}
	var width = curObj.width();
	var height = curObj.height();
	//alert(width/2-90);
	$('<div name="mask"></div>').css({
		width:width,
		height:height,
		background:'#000',
		position:'relative',
		left:0,
		top:0,
		textAlign:'center',
		zIndex:99999,
		padding:(height/2-50)+"px 0 0 "+($.browser.msie&&$.browser.version.substring(0,1)=="7"?0:(width/2-90))+"px"
	}).append($('<div>正在处理中，请稍后。。。</div>').css({
		width:180,
		height:16,
		cursor:"wait",
		background:"#fff url('../views/common/images/loading.gif') no-repeat scroll 5px 10px",
		border:'2px solid #6593CF',
		color:'#222',
		textAlign:'center',
		padding:"8px 3px 8px 10px"
	})).appendTo(curObj).fadeTo(0,0.5);
	maskTimer = setTimeout(function(){
		$("div[name='mask']").remove();
	},10000);
	var dataList=[];
	var typeList=[];
	var callbackFunc=function(){};
	if(typeof paramDataList == 'function'){
		callbackFunc = paramDataList;
	}else{
		dataList = paramDataList;
	}
	if(typeof paramTypeList == 'function'){
		dataList=paramDataList;
		callbackFunc = paramTypeList;
	}else{
		typeList = paramTypeList;
	}
	if(typeof func == 'function'){
		dataList=paramDataList;
		typeList=paramTypeList;
		callbackFunc = func;
	}else if(func!=null){
		showErrorMsg("回调方法错误");
		return;
	}
	if(dataList!=null&&typeof dataList != 'object'&&dataList.length>=0){
		showErrorMsg("Ajax调用错误：参数必须是Array类型");
		return;
	}
	if(typeList!=null&&typeof typeList != 'object'&&typeList.length>=0){
		showErrorMsg("Ajax调用错误：参数类型必须是Array类型");
		return;
	}
	var datas = {};
	datas.paramDataList = dataList?dataList:[];
	datas.paramTypeList = typeList?typeList:[];
	datas.serviceName = serviceName?serviceName:"";
	datas.methodName = methodName?methodName:"";
	var paramData = {};
	paramData.jsonData =  JSON2.stringify(datas);
	$.ajax({
		url	: "../common/jqueryCallback",
		type: "POST",
		contentType:"application/x-www-form-urlencoded;charset=utf-8",
		data	: paramData,
		success : function(data){
			if(data!=null){
				if((data.startWith("{")&&data.endWith("}"))||(data.startWith("[")&&data.endWith("]"))){
					//alert(data);
					eval("data="+data);
					if(data.code=='error'){
						showErrorMsg(data.msg);
					}else
						callbackFunc(data);
				}else{
					if((data.startWith("\"")&&data.endWith("\""))||(data.startWith("'")&&data.endWith("'"))){
						data = data.substring(1,data.length-1);
					}
					callbackFunc(data);
				}
			}else{
				callbackFunc(data);
			}
		},
		error:function(){
			showErrorMsg("ajax请求出错！");
		},
		complete:function(){
			clearTimeout(maskTimer);
			$("div[name='mask']").remove();
		}
	});
}
$.jCallback = jCallBack;
$.fn.jCallback = jCallBack;

/**
 * jCallBack方法备份 带同步的
 *  * @modify:zhushukai 增加是否异步参数async,跟在会回调函数之后，默认为true,无回调函数的话设置无效
 * 					async为false的时候需要捕捉异常LazyError
 * 
function jCallBack(serviceName,methodName,paramDataList,paramTypeList,func,async){
	var isAsync = true;
	var curObj = $("#wiWrap");
	if(typeof this !== 'function'){
		curObj = $(this);
	}
	var width = curObj.width();
	var height = curObj.height();
	//alert(width/2-90);
	$('<div name="mask"></div>').css({
		width:width,
		height:height,
		background:'#000',
		position:'relative',
		left:0,
		top:0,
		textAlign:'center',
		zIndex:99999,
		padding:(height/2-50)+"px 0 0 "+($.browser.msie&&$.browser.version.substring(0,1)=="7"?0:(width/2-90))+"px"
	}).append($('<div>正在处理中，请稍后。。。</div>').css({
		width:180,
		height:16,
		cursor:"wait",
		background:"#fff url('views/common/images/loading.gif') no-repeat scroll 5px 10px",
		border:'2px solid #6593CF',
		color:'#222',
		textAlign:'center',
		padding:"8px 3px 8px 10px"
	})).appendTo(curObj).fadeTo(0,0.5);
	maskTimer = setTimeout(function(){
		$("div[name='mask']").remove();
	},10000);
	var dataList=[];
	var typeList=[];
	var callbackFunc=function(){};
	if(typeof paramDataList == 'function'){
		callbackFunc = paramDataList;
		if(typeof paramTypeList == 'boolean')
			isAsync = paramTypeList;
	}else{
		dataList = paramDataList;
	}
	if(typeof paramTypeList == 'function'){
		dataList=paramDataList;
		callbackFunc = paramTypeList;
		if(typeof func == 'boolean')
			isAsync = func;
	}else{
		typeList = paramTypeList;
	}
	if(typeof func == 'function'){
		dataList=paramDataList;
		typeList=paramTypeList;
		callbackFunc = func;
	}
	if(typeof async == 'boolean')
		isAsync = async;
	else if(async!=null){
		showErrorMsg("回调方法错误");
		return;
	}
	if(dataList!=null&&typeof dataList != 'object'&&dataList.length>=0){
		showErrorMsg("Ajax调用错误：参数必须是Array类型");
		return;
	}
	if(typeList!=null&&typeof typeList != 'object'&&typeList.length>=0){
		showErrorMsg("Ajax调用错误：参数类型必须是Array类型");
		return;
	}
		
	var datas = {};
	datas.paramDataList = dataList?dataList:[];
	datas.paramTypeList = typeList?typeList:[];
	datas.serviceName = serviceName?serviceName:"";
	datas.methodName = methodName?methodName:"";
	var paramData = {};
	paramData.jsonData =  JSON2.stringify(datas);
	$.ajax({
		url	: "jqueryCallback",
		async : isAsync,
		type: "POST",
		contentType:"application/x-www-form-urlencoded;charset=utf-8",
		data	: paramData,
		success : function(data){
			if(data!=null){
				if((data.startWith("{")&&data.endWith("}"))||(data.startWith("[")&&data.endWith("]"))){
					//alert(data);
					eval("data="+data);
					if(data.code=='error'){
						showErrorMsg(data.msg);
						if(!isAsync){
							clearTimeout(maskTimer);
							$("div[name='mask']").remove();
							throw new LazyError(data.msg);
						}	
					}else
						callbackFunc(data);
				}else{
					if((data.startWith("\"")&&data.endWith("\""))||(data.startWith("'")&&data.endWith("'"))){
						data = data.substring(1,data.length-1);
					}
					callbackFunc(data);
				}
			}else{
				callbackFunc(data);
			}
		},
		error:function(){
			showErrorMsg("请求出错,请检查网络！");
			if(!isAsync){
				clearTimeout(maskTimer);
				$("div[name='mask']").remove();
				throw new LazyError();
			}
		},
		complete:function(){
			clearTimeout(maskTimer);
			$("div[name='mask']").remove();
		}
	});
}
$.jCallback = jCallBack;
$.fn.jCallback = jCallBack;
 */


/**
 * 自定义错误
 * @param message  错误名称
 * @param element  HTML元素对象
 * @returns
 */
function LazyError(message,element){
	this.name = "LazyError";
	this.message = message;
	this.element = element;
}
LazyError.prototype = new Error();

/**
 * 显示错误信息，可以自己修改
 */
function showErrorMsg(msg){
	if($("div[name='jqueryErrorDiv']").length>0){
		$("div[name='jqueryErrorDiv']").html("<p>"+msg+"</p>").dialog('open');
	}else{
		$('<div name="jqueryErrorDiv" ><p>'+msg+'</p></div>').appendTo('body');
		$("div[name='jqueryErrorDiv']").dialog({
			autoOpen: false,
			position:['right','bottom'],
			height:100,
			show: "scale",
			title:"提示"
		}).dialog("open");
	}
}

/**
 * 显示信息，可以自己修改
 */
function showMsg(msg){
	if($("#jqueryMsgDiv").length>0){
		$("#jqueryMsgDiv").html("<p>"+msg+"</p>").dialog('open');
	}else{
		$('<div id="jqueryMsgDiv" ><p>'+msg+'</p></div>').appendTo('body');
		$("#jqueryMsgDiv").dialog({
			autoOpen: false,
			height:150,
			show: "slide",
			modal:true,
			/*hide: "explode",*/
			title:"提示",
			buttons:{
				"确定":function(){
					$(this).dialog("close");
				}
			}
		}).dialog("open");
	}
}


/**
 * 显示信息，可以自己修改
 */
function showIframeMsg(msg,url){
	if($("#jqueryMsgDiv").length>0){
		$("#jqueryMsgDiv").html("<p>"+msg+"</p>").dialog('open');
	}else{
		$('<div id="jqueryMsgDiv" ><p style="text-align:center;">'+msg+'</p></div>').appendTo('body');
		$("#jqueryMsgDiv").dialog({
			position: ['center','top'],
			autoOpen: false,
			height:150,
			show: "slide",
			modal:true,
			hide: "explode",
			title:"提示",
			buttons:{
				"确定":function(){
					if (url)
					{
						eval(url);
						$(this).dialog("close");

					}
					else
					{
						//$(window.top.document).find(".ui-tabs-nav > li.ui-state-active > div > span.tabs-close").click();
						var xx = $(window.parent.document).find(".ui-tabs-nav > li.ui-state-active > div > span.tabs-close").length;
						if(xx == 1)
						{
						      $(window.parent.document).find(".ui-tabs-nav > li.ui-state-active > div > span.tabs-close").click();
						
						}
						else
						{
							 $(window.parent.parent.document).find(".ui-tabs-nav > li.ui-state-active > div > span.tabs-close").click();
						}
						
					}
				}
			}
		}).dialog("open");
	}
}

/**
 * 等待对话框
 * @param msg
 */
function waitMsg(msg){
	if($("#jqueryWaitMsg").length>0){
		$("#jqueryWaitMsg").html("<p>"+msg+"</p>").dialog('open');
	}else{
		$('<div id="jqueryWaitMsg" ><p>'+msg+'</p></div>').appendTo('body');
		$("#jqueryWaitMsg").dialog({
			autoOpen: false,
			height:150,
			show: "slide",
			modal:true,
			hide: "explode",
			title:"提示",
			closeOnEscape:false,
			open: function(event,ui){
				$(".ui-dialog-titlebar-close").hide();
			}
		}).dialog("open");
	}
}

/**
 * 确认框
 * @param msg
 * @param func
 */
function showConfirmMsg(msg,func){
	if($("#jqueryConfirmDiv").length>0){
		$("#jqueryConfirmDiv").html("<p>"+msg+"</p>")
		.dialog('option','buttons',{
			"确定":function(){
				$(this).dialog('close');
				func(true);
			},
			"取消":function(){
				$(this).dialog('close');
				func(false);
			}
		}).dialog('open');
	}else{
		$('<div id="jqueryConfirmDiv" ><p>'+msg+'</p></div>').appendTo('body');
		$("#jqueryConfirmDiv").dialog({
			autoOpen: false,
			height:150,
			show: "slide",
			modal:true,
			hide: "explode",
			title:"确认",
			buttons:{
				"确定":function(){
					$(this).dialog('close');
					func(true);
				},
				"取消":function(){
					$(this).dialog('close');
					func(false);
				}
			}
		}).dialog("open");
	}
}

//中文转unicode 实现php的urlencode方法一样的效果，包括特殊字符
function UrlEncode(str){   
	var i,temp,p,q;   var result="";   
	for(i=0;i<str.length;i++){     
		temp = str.charCodeAt(i); 
		if(temp>=0x4e00 || temp==0x300A || temp==0x300B){ 
			execScript("ascCode=hex(asc(\""+str.charAt(i)+"\"))", "vbscript");  
			result+=ascCode.replace(/(.{2})/g, "%$1");     
		}else{    
			switch(temp){
				case 64: result += '%40';break;
				case 42: result += '%2A';break;
				case 47: result += '%2F';break;
				case 43: result += '%2B';break;
				case 215: result += '%A1%C1';break;
				case 183: result += '%A1%A4';break;
				default:result += escape(str.charAt(i));
			}
		}    
	} 
	for(var j=0;j<jsCode.length;j++){
		result = result.replace(new RegExp(jsCode[j],'gm'),phpCode[j]);
	}
	return result;   
}

/**
 * 对象的深度复制
 */
function deepCopy(destination, source){
    for (var property in source) {
        var copy = source[property];
        if ( destination === copy ) continue;
        if ( typeof copy === "object" ){
            destination[property] = deepCopy(destination[property] || {}, copy);
        }else{
            destination[property] = copy;
        }
    }
    return destination;
}
String.prototype.endWith = function (str) {
	if (str == null || str == "" || this.length == 0 || str.length > this.length) {
		return false;
	}
	if (this.substring(this.length - str.length) == str) {
		return true;
	} else {
		return false;
	}
	return true;
};
String.prototype.startWith = function (str) {
	if (str == null || str == "" || this.length == 0 || str.length > this.length) {
		return false;
	}
	if (this.substr(0, str.length) == str) {
		return true;
	} else {
		return false;
	}
	return true;
};
/*String.prototype.replaceAll = function(s1,s2) { 
    return this.replace(new RegExp(s1,"gm"),s2); 
};*/
function repalceBackslash(str){
	while(str.indexOf("\\")>0){
		str = str.replace("\\","####");
	}
	str = str.replace(new RegExp('####','gm'),'\\\\');
	return str;
}


/**
***  Time: 2010-11-25
***  author: skylen
*/
//获取数据
function getRemoteData(host,remoteFile,params,funcName){
	window.external.doGetRemoteData(host,remoteFile,params,funcName);
}

/**************************************************************************************************************************************/
/**************************************************************************************************************************************/
/************************************************************日期格式化***************************************************************/
/****************************************************************开始******************************************************************/
/**************************************************************************************************************************************/
/**************************************************************************************************************************************/
//字符串转日期型
/** year : /yyyy/ */
var _y4 = "([0-9]{4})";
/** year : /yy/ */
var _y2 = "([0-9]{2})";
/** index year */
var _yi = -1;
/** month : /MM/ */
var _M2 = "(0[1-9]|1[0-2])";
/** month : /M/ */
var _M1 = "([1-9]|1[0-2])";
/** index month */
var _Mi = -1;
/** day : /dd/ */
var _d2 = "(0[1-9]|[1-2][0-9]|30|31)";
/** day : /d/ */
var _d1 = "([1-9]|[1-2][0-9]|30|31)";
/** index day */
var _di = -1;
/** hour : /HH/ */
var _H2 = "([0-1][0-9]|20|21|22|23)";
/** hour : /H/ */
var _H1 = "([0-9]|1[0-9]|20|21|22|23)";
/** index hour */
var _Hi = -1;
/** minute : /mm/ */
var _m2 = "([0-5][0-9])";
/** minute : /m/ */
var _m1 = "([0-9]|[1-5][0-9])";
/** index minute */
var _mi = -1;
/** second : /ss/ */
var _s2 = "([0-5][0-9])";
/** second : /s/ */
var _s1 = "([0-9]|[1-5][0-9])";
/** index month */
var _si = -1;
var regexp;
function getDate(dateString, formatString) {
	if (validateDate(dateString, formatString)) {
		var now = new Date();
		var vals = regexp.exec(dateString);
		var index = validateIndex(formatString);
		var year = index[0] >= 0 ? vals[index[0] + 1] : now.getFullYear();
		var month = index[1] >= 0 ? (vals[index[1] + 1] - 1) : now.getMonth();
		var day = index[2] >= 0 ? vals[index[2] + 1] : now.getDate();
		var hour = index[3] >= 0 ? vals[index[3] + 1] : "";
		var minute = index[4] >= 0 ? vals[index[4] + 1] : "";
		var second = index[5] >= 0 ? vals[index[5] + 1] : "";
		var validate;
		if (hour == "") {
			validate = new Date(year, month, day);
		} else {
			validate = new Date(year, month, day, hour, minute, second);
		}
		if (validate.getDate() == day) {
			return validate;
		}
	}
	alert("wrong date");
}
function validateDate(dateString, formatString) {
	var dateString = trim(dateString);
	if (dateString == "") {
		return;
	}
	var reg = formatString;
	reg = reg.replace(/yyyy/, _y4);
	reg = reg.replace(/yy/, _y2);
	reg = reg.replace(/MM/, _M2);
	reg = reg.replace(/M/, _M1);
	reg = reg.replace(/dd/, _d2);
	reg = reg.replace(/d/, _d1);
	reg = reg.replace(/HH/, _H2);
	reg = reg.replace(/H/, _H1);
	reg = reg.replace(/mm/, _m2);
	reg = reg.replace(/m/, _m1);
	reg = reg.replace(/ss/, _s2);
	reg = reg.replace(/s/, _s1);
	reg = new RegExp("^" + reg + "$");
	regexp = reg;
	return reg.test(dateString);
}
function validateIndex(formatString) {
	var ia = new Array();
	var i = 0;
	_yi = formatString.search(/yyyy/);
	if (_yi < 0) {
		_yi = formatString.search(/yy/);
	}
	if (_yi >= 0) {
		ia[i] = _yi;
		i++;
	}
	_Mi = formatString.search(/MM/);
	if (_Mi < 0) {
		_Mi = formatString.search(/M/);
	}
	if (_Mi >= 0) {
		ia[i] = _Mi;
		i++;
	}
	_di = formatString.search(/dd/);
	if (_di < 0) {
		_di = formatString.search(/d/);
	}
	if (_di >= 0) {
		ia[i] = _di;
		i++;
	}
	_Hi = formatString.search(/HH/);
	if (_Hi < 0) {
		_Hi = formatString.search(/H/);
	}
	if (_Hi >= 0) {
		ia[i] = _Hi;
		i++;
	}
	_mi = formatString.search(/mm/);
	if (_mi < 0) {
		_mi = formatString.search(/m/);
	}
	if (_mi >= 0) {
		ia[i] = _mi;
		i++;
	}
	_si = formatString.search(/ss/);
	if (_si < 0) {
		_si = formatString.search(/s/);
	}
	if (_si >= 0) {
		ia[i] = _si;
		i++;
	}
	var ia2 = new Array(_yi, _Mi, _di, _Hi, _mi, _si);
	for (i = 0; i < ia.length - 1; i++) {
		for (var j = 0; j < ia.length - 1 - i; j++) {
			if (ia[j] > ia[j + 1]) {
				temp = ia[j];
				ia[j] = ia[j + 1];
				ia[j + 1] = temp;
			}
		}
	}
	for (i = 0; i < ia.length; i++) {
		for (var j = 0; j < ia2.length; j++) {
			if (ia[i] == ia2[j]) {
				ia2[j] = i;
			}
		}
	}
	return ia2;
}
function trim(str) {
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
//日期型格式化
Date.prototype.format = function(format) {  
	var o = {  
		"M+" : this.getMonth()+1, //month  
		"d+" : this.getDate(),//day  
		"h+" : this.getHours(), //hour  
		"m+" : this.getMinutes(), //minute  
		"s+" : this.getSeconds(), //second  
		"q+" : Math.floor((this.getMonth()+3)/3), //quarter  
		"S" : this.getMilliseconds() //millisecond  
	};
	if(/(y+)/.test(format))   
		format = format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));  
	for(var k in o)  
		if(new RegExp("("+ k +")").test(format))  
			format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] :("00"+ o[k]).substr((""+ o[k]).length));  
	return format;  
};  
/**************************************************************************************************************************************/
/**************************************************************************************************************************************/
/************************************************************日期格式化***************************************************************/
/****************************************************************结束******************************************************************/
/**************************************************************************************************************************************/
/**************************************************************************************************************************************/

// 解决ie6 ie7 下jquery ui button accordion tabs fakewindowcontain 等组件加载延时，显示定位不正常 
// item_id : 页面中任一 button accordion tabs fakewindowcontain 等组件ID名
// page_id : 当前页面载入的目的div
function jqueryUiResizeForIE6(item_id,page_obj){
	if($.browser.msie&&$.browser.version.substring(0,1)=="6"){
		$(item_id).focus().blur();
		page_obj.scrollTop(0);
	}
}
var heightDif = 0;
var pageSize = 10;
$(function(){
	heightDif = window.screen.height - 768;
	if(window.screen.height >= 1280)
		pageSize = 20;
	else if(window.screen.height >= 1024)
		pageSize = 15;
	
	
});

function getSwjg(divId){
	$("#"+divId).html('<select id="'+divId+'_qj" style="width:170px;"></select>&nbsp;&nbsp;' +
		'<select id="'+divId+'_fj" style="width:200px;"></select>&nbsp;&nbsp;' +
		'<select id="'+divId+'_ks" style="width:200px;"></select>');
	$("#"+divId).jCallback("commonQueryService","getSwjg",function(data){
		try{
			$.each(data.qj, function(i,item){
				$('#'+divId+'_qj').get(0).options.add(new Option(item.swjgmc,item.swjg_dm));
			});
			$.each(data.fj, function(i,item){
				$('#'+divId+'_fj').get(0).options.add(new Option(item.swjgmc,item.swjg_dm));
			});
			$.each(data.ks, function(i,item){
				$('#'+divId+'_ks').get(0).options.add(new Option(item.swjgmc,item.swjg_dm));
			});
		}catch(e){
			showErrorMsg("获取税务机关出错！");
		}
	});	
	$('#'+divId+'_qj').change(function(){
		getFj(divId);
	});
	$('#'+divId+'_fj').change(function(){
		getKs(divId);
	});
}
function getFj(divId){
	$('#'+divId+'_fj').html("");
	$('#'+divId+'_ks').html("");
	var qj = $('#'+divId+'_qj').val();
	if(qj=='')
		return;
	$("#"+divId).jCallback("commonQueryService","fjQuery",[{"qj":qj}],function(data){
		$('#'+divId+'_fj').get(0).options.add(new Option('所有',''));
		$.each(data, function(i,item){
			$('#'+divId+'_fj').get(0).options.add(new Option(item.swjgmc,item.swjg_dm));
		});
	});	
}
function getKs(divId){
	$('#'+divId+'_ks').html("");
	var fj = $('#'+divId+'_fj').val();
	if(fj=='')
		return;
	$("#"+divId).jCallback("commonQueryService","ksQuery",[{"fj":fj}],function(data){
	 	$('#'+divId+'_ks').get(0).options.add(new Option('所有',''));
		$.each(data, function(i,item){
			$('#'+divId+'_ks').get(0).options.add(new Option(item.swjgmc,item.swjg_dm));
		});
	});	
}

/**
 * 转换成数字
 * @param {} str
 */
function getIntValue(str){
	if(!str||isNaN(parseInt(str)))
		return 0;
	return parseInt(str);
}

/**
 * 展示地图信息
 * @param {} param
 * param.width iframe的宽度
 * param.height iframe的高度
 * param.src iframe的src
 * param.title dialog的标题
 * param.id dialog的id
 * param.func dialog中确定按钮点击事件
 * param.extendHtml dialog中自定义的html代码
 * param.extendHeight dialog中自定义的html代码的高度
 * param.showButtons dialog中是否显示按钮
 */
function showMap(param){
	param = param?param:{};
	var m_width = param.width?param.width:1040;

	var m_height = param.height?param.height:$("#wiWrap").height()-100;
	var m_src = param.src?param.src+"?ip="+local_ip:"";

	var m_title = param.title?param.title:"地图展示";
	var m_id = param.id?param.id:"map"+new Date().getTime();
	var m_func = param.func?param.func:null;
	var m_extendHtml = param.extendHtml?param.extendHtml:"";
	var m_extendHeight = param.extendHeight?param.extendHeight:0;
	var m_showButtons = param.showButtons?param.showButtons:false;
	var call_back_func=param.call_back_func?param.call_back_func:null;
	if($("#"+m_id).length>0){
		$("#iframe_"+m_id).attr("src",m_src);
		$("#"+m_id).dialog("option","title",m_title).dialog('open');
	}else{
		var buttons = {};
		if(m_showButtons){
			buttons = {
				"确定":function(){
					m_func(param.param1,param.param2,param.param3,param.param4,param.param5,param.param6,param.param7);
				},
				"取消":function(){
					$(this).dialog("close");
				}
			};
		}
		$('<div id="'+m_id+'" ><iframe id="iframe_'+m_id+'" src="'+m_src+'" width="'+m_width+'px" height="'+m_height+'px" frameborder="0" scrolling="auto"></iframe>'+m_extendHtml+'</div>').appendTo('body');
		$("#"+m_id).dialog({
			autoOpen: false,
			height:m_height+m_extendHeight+60,
			width:m_width+35,
			show: "slide",
			modal:true,
//			hide: "explode",
			title:m_title,
			buttons:buttons,
			close: function(event, ui) { if(call_back_func!=null){call_back_func();} }
		}).dialog("open");
	}
}

/**
 * 初始化年份下拉框
 * @param {} id
 */
function initYearSel(id){
	var ops = $('#'+id).get(0).options;
	ops.length=0;
	ops.add(new Option("2010","2010"));
	ops.add(new Option("2011","2011"));
	ops.add(new Option("2012","2012"));
	ops.add(new Option("2013","2013"));
	ops.add(new Option("2014","2014"));
	ops.add(new Option("2015","2015"));
	ops.add(new Option("2016","2016"));
	$('#'+id).get(0).value = new Date().getFullYear()+"";
}

/**
 * 初始化月份下拉框
 * @param {} id
 */
function initMonthSel(id){
	var ops = $('#'+id).get(0).options;
	ops.length=0;
	ops.add(new Option("一月","01"));
	ops.add(new Option("二月","02"));
	ops.add(new Option("三月","03"));
	ops.add(new Option("四月","04"));
	ops.add(new Option("五月","05"));
	ops.add(new Option("六月","06"));
	ops.add(new Option("七月","07"));
	ops.add(new Option("八月","08"));
	ops.add(new Option("九月","09"));
	ops.add(new Option("十月","10"));
	ops.add(new Option("十一月","11"));
	ops.add(new Option("十二月","12"));
	var m=new Date().getMonth();
	$('#'+id).get(0).value = m<9?"0"+(m+1):m+1;
}
function initDaySel(id){
	var ops = $('#'+id).get(0).options;
	ops.length=0;
	for(var i=1;i<=31;i++){
		ops.add(new Option(i,i));
	}
	var d=new Date().getDate();
	$('#'+id).get(0).value =d;
}
function initWeekSel(id){
	var ops = $('#'+id).get(0).options;
	ops.length=0;
	
	ops.add(new Option("周一","1"));
	ops.add(new Option("周二","2"));
	ops.add(new Option("周三","3"));
	ops.add(new Option("周四","4"));
	ops.add(new Option("周五","5"));
	ops.add(new Option("周六","6"));
	ops.add(new Option("周日","7"));
	$('#'+id).get(0).value =new Date().getDay();
}
function initTaskTimeTypeSel(id){
	var ops = $('#'+id).get(0).options;
	ops.length=0;
	ops.add(new Option("一次","0"));
	ops.add(new Option("每日","1"));
	ops.add(new Option("每周","2"));
	ops.add(new Option("每月","3"));
	ops.add(new Option("每年","4"));
}
/**
 * 初始化季度下拉列表
 */
function initQuarterSel(id){
	var ops = $('#'+id).get(0).options;
	ops.length=0;
	ops.add(new Option("第一季度","0A"));
	ops.add(new Option("第二季度","0B"));
	ops.add(new Option("第三季度","0C"));
	ops.add(new Option("第四季度","0D"));
	
}
function initMixQuarterSel(id){
	var ops = $('#'+id).get(0).options;
	ops.length=0;
	ops.add(new Option("第一季度","01"));
	ops.add(new Option("第二季度","02"));
	ops.add(new Option("第三季度","03"));
	ops.add(new Option("第四季度","04"));
	
}

/**
 * 初始化数据库类型下拉
 * @param id 不包含#的ID名
 */
function initDataBaseType(id){
	var ops = $('#'+id).get(0).options;
	ops.length=0;
	ops.add(new Option("--请选择数库类型--",""));
	ops.add(new Option("ORACLE","0"));
	ops.add(new Option("MYSQL","1"));
	ops.add(new Option("DB2","2"));
	ops.add(new Option("SQLSERVER","3"));
}

/**
 * 初始化数据库类字符集类型下拉
 * @param id 不包含#的ID名
 */
function initDataBaseCharacterType(id){
	var ops = $('#'+id).get(0).options;
	ops.length=0;
	ops.add(new Option("--请选择字符集--",""));
	ops.add(new Option("GBK","GBK"));
	ops.add(new Option("UTF-8","UTF-8"));
}

/**
 * @author
 * commbox插件,创建方法：
 * 			$( "#test" ).combobox();
 * 获取combobox的值：
 * 			$( "#test" ).combobox("option","input").val()
 
(function( $ ) {
	$.widget( "ui.combobox", {
		_create: function() {
			var input,
				self = this,
				select = this.element.hide(),
				selected = select.children( ":selected" ),
				value = selected.val() ? selected.text() : "",
				wrapper = this.wrapper = $( "<span>" )
					.addClass( "ui-combobox" )
					.insertAfter( select );

			input = $( "<input>" )
				.appendTo( wrapper )
				.val( value )
				.addClass( "ui-state-default ui-combobox-input" )
				.autocomplete({
					delay: 0,
					minLength: 0,
					source: function( request, response ) {
						var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
						response( select.children( "option" ).map(function() {
							var text = $( this ).text();
							if ( this.value && ( !request.term || matcher.test(text) ) )
								return {
									label: text.replace(
										new RegExp(
											"(?![^&;]+;)(?!<[^<>]*)(" +
											$.ui.autocomplete.escapeRegex(request.term) +
											")(?![^<>]*>)(?![^&;]+;)", "gi"
										), "<strong>$1</strong>" ),
									value: text,
									option: this
								};
						}) );
					},
					select: function( event, ui ) {
						ui.item.option.selected = true;		
						self._trigger( "selected", event, {
							item: ui.item.option
						});
					},
					change: function( event, ui ) {
						//当combobox有值改变时，同时应该改变options中的value以便外部调用
						//select.combobox("option","value",$(this).val());
						/**
						 * 判断combobox的input框中内容是否和下拉对应，若不对应将会进入清空内容的设置代码
						 * 本应用需要改变input中内容，所以valid应该始终为true
						 * 
						 * 
						if ( !ui.item ) {
							var matcher = new RegExp( "^" + $.ui.autocomplete.escapeRegex( $(this).val() ) + "$", "i" ),
								valid = false;
							select.children( "option" ).each(function() {
								if ( $( this ).text().match( matcher ) ) {
									this.selected = valid = true;
									return false;
								}
							});	
							if ( !valid ) {
								// remove invalid value, as it didn't match anything
								$( this ).val( "" );
								select.val( "" );
								input.data( "autocomplete" ).term = "";
								return false;
							}
							
						}
						
					}
				})
				.addClass( "ui-widget ui-widget-content ui-corner-left" );

			input.data( "autocomplete" )._renderItem = function( ul, item ) {
				return $( "<li></li>" )
					.data( "item.autocomplete", item )
					.append( "<a>" + item.label + "</a>" )
					.appendTo( ul );
			};

			$( "<a>" )
				.attr( "tabIndex", -1 )
				.attr( "title", "Show All Items" )
				.appendTo( wrapper )
				.button({
					icons: {
						primary: "ui-icon-triangle-1-s"
					},
					text: false
				})
				.removeClass( "ui-corner-all" )
				.addClass( "ui-corner-right ui-combobox-toggle" )
				.click(function() {
					// close if already visible
					if ( input.autocomplete( "widget" ).is( ":visible" ) ) {
						input.autocomplete( "close" );
						return;
					}

					// work around a bug (likely same cause as #5265)
					$( this ).blur();

					// pass empty string as value to search for, displaying all results
					input.autocomplete( "search", "" );
					input.focus();
				});
			select.combobox("option","input",input);
		},
		options : {
			input : null
		},
		destroy: function() {
			this.wrapper.remove();
			this.element.show();
			$.Widget.prototype.destroy.call( this );
		}
	});
})( jQuery );*/



/**
 * jquery数组转字符串
 * @param arr 为jquery数组对象
 * @return
 */
function arrayParseString(arr){
	var s="";
	arr.each(function(index){
		s+=($(this).val()==""?" ":$(this).val());
		if(index<arr.length-1)
			s+=";";
	});
	return s;
}


//下拉框 选中默认值op jquery对象 val sel值
function chooseSel(op,val){
	op.find("option").each(function(index){
		if($(this).val()==val){
			$(this).attr("selected","selected");
		}
	});
}
function showValidateErrorMsg(msg){
	showMsg(msg);
}
//切换层 hideDivs：需要隐藏的层id数组 showDivs:需要显示的成id数组
function changeDiv(hideDivs,showDivs){
	for(var i=0;i<hideDivs.length;i++){
		$("#"+hideDivs[i]).css("display","none");
	}
	for(var i=0;i<showDivs.length;i++){
		$("#"+showDivs[i]).css("display","block");
	}
}

// 将毫秒的时间转化为"yyyy/MM/dd hh:mm:ss"格式的时间
// mDate是毫秒数 或者 日期格式的事件
function formateDate(mDate) {
	var date = new Date(mDate);
	if (!date) return;
	return date.getFullYear() + '/' 
			+ formate(parseInt(date.getMonth()) + 1) + '/' 
			+ formate(date.getDate()) + " " 
			+ formate(date.getHours()) +  ':' 
			+ formate(date.getMinutes()) + ":" 
			+ formate(date.getSeconds());
}
// 将日期字符串转化为毫秒数
function date2mMillis(d) {
	if (d == "" || !d) return;
	d = d.replaceAll('-', '/');
	var date = new Date(d);

	if (!date) return;
	return date.getTime();
}
// 月 日 时分秒 是个位数的情况自动补0
function formate(o) {
	return o < 10 ? '0' + o : o;
}
// 判断中英文字符长度
function strlen(str) {
	var len = 0;
	for (var i = 0; i < str.length; i++) {
		var c = str.charCodeAt(i);
		if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
			len++;
		} else {
			len += 2;
		}
	}
	return len;
}


/**
 * 替换JS文本，变成普通文本
 */
function HtmlEncode(text){
	if( text == undefined){
		return text;
	}else if(text.length == 0 ){
		return text;
	}else{
		return text.replace(/\\/g,"\\\\").replace(/&/g,'&amp').replace(/\"/g,'&quot;').replace(/</g,'&lt;').replace(/>/g,'&gt;').replace(/\'/g,"\\'").replace(/\r/g,"<br/>").replace(/\n/g,"<br/>").replace(/\r\n/g,"<br/>");
		//.replaceAll(/\s/g,'');
	}
}


function HtmlDecode(text){
	if( text == undefined)
	{
		return text;
	}else if(text.length == 0 )
	{
		return text;
	}else{
		return text.replace(/<br\s*\/?\s*>/ig, '\n');
	}
}

//判断鼠标是否有e.pageX属性
function isPageX(e){
	var x = 0;
	if( 'undefined' === typeof e.pageX){
		x =  e.clientX;
	}else{
		x = e.pageX;
	}
	return x;	
};

//判断鼠标是否e.pageY属性
function isPageY(e){
	var y = 0;
	if( 'undefined' === typeof e.pageY){
		y= e.clientY; 
	}else{
		y =  e.pageY;
	}
	return y;
};

var topHeight = 0;
//显示隐藏搜索条件
function showAndHide(obj,name){
	var tagObj = $(obj).parent().parent().next("div");
	
	var div_2 = $("#"+name).parent().parent().parent();
	var flexigrid = $("#"+name).parent().parent();
	var bDiv = $("#"+name).parent();
	var hhh = bDiv.height();
	var hhh_2 = div_2.height();
	if(tagObj.is(":hidden")){
		bDiv.height(hhh-10);
		div_2.height(hhh_2-10);
		tagObj.show();
		div_2.height(div_2.height() - topHeight);
		flexigrid.height(flexigrid.height() - topHeight);
		bDiv.height(bDiv.height() - topHeight);
		$(obj).removeClass("imgSh").addClass("imgShrotate");
	}else{
		bDiv.height(hhh+10);
		div_2.height(hhh_2+10);
		topHeight = tagObj.height();
		div_2.height(div_2.height() + topHeight);
		flexigrid.height(flexigrid.height() + topHeight);
		bDiv.height(bDiv.height() + topHeight);
		tagObj.hide();
		$(obj).removeClass("imgShrotate").addClass("imgSh");
	}
}

/**
 * 获取当前浏览器页面高度与宽度;
 * return o;
 */
function getExploreWidthAndHeight(){
	var o = {};
	o.w = parseInt(document.documentElement.scrollWidth) > parseInt(document.documentElement.clientWidth) ? (parseInt(document.documentElement.scrollWidth) + "px")  : (parseInt(document.documentElement.clientWidth) + "px");
	o.h = parseInt(document.documentElement.scrollHeight) > parseInt(document.documentElement.clientHeight) ? (parseInt(document.documentElement.scrollHeight) + "px")  : (parseInt(document.documentElement.clientHeight) + "px");
	return o;
}

/**
 * 屏蔽鼠标右键
*/
function shieldRightMouse(obj){
	obj.bind("contextmenu",function(e){return false;});
}

/**
 * 
*/
function jobShowAndHide(i,obj){
	switch (i) {
		case 1:
			$(obj).parent().next(".div-main-1").slideToggle("slow",
				function(){
					if(!$(this).is(":hidden")){
						$(obj).removeClass("imgSh").addClass("imgShrotate");
					}else{
						$(obj).removeClass("imgShrotate").addClass("imgSh");
					}
				}
			);
			break;
		case 2:
			$(obj).parent().next(".div-main-1").slideToggle("slow",
					function(){
						if(!$(this).is(":hidden")){
							$(obj).removeClass("imgSh").addClass("imgShrotate");
						}else{
							$(obj).removeClass("imgShrotate").addClass("imgSh");
						}
					}
				);	
			$(obj).parent().next(".div-main-1").next("#dlg_jobs_param_detail").toggle("slow");
			$(obj).parent().next(".div-main-1").next("#dlg_param_detail").toggle("slow");
			$(obj).parent().next(".div-main-1").next("#dlg_linejobs_param_detail").toggle("slow");
			$(obj).parent().next(".div-main-1").next("#dlg_linemission_param_detail").toggle("slow");
			break;
		case 3:
			$(obj).parent().next(".div-main-1").slideToggle("slow",
					function(){
						if(!$(this).is(":hidden")){
							$(obj).removeClass("imgSh").addClass("imgShrotate");
						}else{
							$(obj).removeClass("imgShrotate").addClass("imgSh");
						}
					}
				);	
			break;
		default:
			break;
	}
}

/**
*获得当前时间 格式为:yyyy-MM-dd HH:mm:ss
*/
function getCurrentTime(){
	var currentTime = new Date();
	var seperator1 ="-";
	var seperator2 =":";
	var month = currentTime.getMonth()+1;
	var date = currentTime.getDate();
	if(month>=1 && month<=9 ){
		month = "0" + month;
	}
	if(date>=1 && date<=9 ){
		date = "0" + date;
	}
	var myTime = currentTime.getFullYear()+seperator1+month+seperator1+date+" "+
	currentTime.getHours()+seperator2+currentTime.getMinutes()+seperator2+currentTime.getSeconds();
	
	return myTime;
}

/**
*获得当前时间 格式为:yyyy-MM-dd
*/
function get_Previous_Date(num,endDate){
	var currentTime = null;
	if(endDate){
		currentTime = new Date(Date.parse(endDate.replace(/\-/g,"/")));
	}else{
		currentTime = new Date();
	}
	var seperator1 = "-";
	var previous = new Date(currentTime.getTime() - num * 24 * 60 * 60 * 1000);
	var month = previous.getMonth() + 1;
	var date = previous.getDate();
	if(month>=1 && month<=9 ){
		month = "0" + month;
	}
	if(date>=1 && date<=9 ){
		date = "0" + date;
	}
	var myTime = previous.getFullYear() + seperator1 + month + seperator1 + date;
	
	return myTime;
}


/**
*第一参数是选择中的索引
*第二参数是tabs选项卡的父元素
*第三参数是显示正文内容的父元素
*/
function foldTabs(){
	var index =  arguments[0];
	var ul_parent = $("." + arguments[1]);
	var tabs_parent = $("." + arguments[2]);
	if(ul_parent.children("li").eq(index).children("span").html() == "recharge_dig"){
		
		/*tabs_parent.children("div").removeClass("block").addClass("none");
		tabs_parent.children().eq(index).addClass("block").removeClass("none");*/
		//obj = tabs_parent.children().eq(index).children("div").eq(0).children("ul").children().eq(0);
		ul_parent.children("li").removeClass("bgwhite").addClass("bggray");
		ul_parent.children("li").eq(index).removeClass("bggray").addClass("bgwhite");
		tabs_parent.load(ul_parent.children("li").eq(index).children("input").val());
		//;
	}else if(ul_parent.children("li").eq(index).children("span").html() == "注册统计"){
		
		ul_parent.children("li").removeClass("bgwhite").addClass("bggray");
		ul_parent.children("li").eq(index).removeClass("bggray").addClass("bgwhite");
		tabs_parent.load(ul_parent.children("li").eq(index).children("input").val());
		/*tabs_parent.children("div").removeClass("block").addClass("none");
		tabs_parent.children().eq(index).addClass("block").removeClass("none");*/
		//obj = tabs_parent.children().eq(index).children("div").eq(0).children("ul").children().eq(0);
	}else{
		ul_parent.children("li").removeClass("bgwhite").addClass("bggray");
		ul_parent.children("li").eq(index).removeClass("bggray").addClass("bgwhite");
		/*tabs_parent.children("div").removeClass("block").addClass("none");
		tabs_parent.children().eq(index).addClass("block").removeClass("none");*/
		tabs_parent.load(ul_parent.children("li").eq(index).children("input").val());
		//obj = tabs_parent.children().eq(index).children("div").eq(0).children("ul").children().eq(0);
	}
}

/**
 * 1.定义生产报表的DIV变量，是全局变量：doMain
 * 2.导入生成报表的工具,同时改变趋势统计和地域统计按钮的样式
 * 3.初始化报表
 */
var arrDoMain = {};
var doMain = "";
var echart_url = "";
var my_Type = "";
function importChartUnitl(obj,url,type_Big){
	//doMain = document.getElementById($(obj).parent().parent().parent().children(".echart").attr("id"));
	var echart = $(obj).closest("div").parent().children(".echart");
	if($(obj).closest("ul").children("li").length == 2){
		$(obj).next("li").removeClass("border_li").addClass("border_none");
		$(obj).removeClass("border_none").addClass("border_li");
		$(obj).parent().parent().children("div").eq(1).removeClass("block").addClass("none");
		$(obj).parent().parent().children("div").eq(0).removeClass("none").addClass("block");
		echart.next(".acc_chart").removeClass("block").addClass("none");
		echart.removeClass("none").addClass("block");
	}else{
		var parentDiv = $(obj).closest("div"); 
		changeBtnStyle($(obj));
		changeSelectionStyle(parentDiv,$(obj).index());
		changetContextStyle(parentDiv.parent(),$(obj).index());
	}
	
}

/**
 * 切换到pie图
 */
function selectedToPie(obj_btn){
	var index= parseInt(obj_btn.index());
	var parentDiv = obj_btn.closest("div");
	//按钮改变样式
	changeBtnStyle(obj_btn);
	//条件改变样式
	//obj_btn.closest("ul").next("div").children("ul").eq(index).show().siblings().hide();
	changeSelectionStyle(parentDiv,index);
	//显示内容样式
	changetContextStyle(parentDiv.parent(),index);
}

//改变图表菜单中：趋势统计和地域统计的样式
var changeBtnStyle = function(btn){
	btn.siblings().removeClass("border_li").addClass("border_none");
	btn.removeClass("border_none").addClass("border_li");
};
//改变筛选条件的样式
var changeSelectionStyle = function(obj,index){
	obj.children("div").hide();
	obj.children("div:eq(" + index + ")").show();
};
//改变正文内容显示
var changetContextStyle = function(obj,index){
	obj.children("div:not(':first')").hide();
	obj.children("div:eq(" + (index + 1) + ")").show();
};
//pie条件选择
var getPieTime = function(obj){
	var selection = new Object();
	selection.send_time1 = obj.children("input").eq(0).val();
	selection.send_time2 = obj.children("input").eq(1).val();
	return selection;
};

//全局变量设置PIE图的时间
var r_pie_time = {
		btime : "",
		etime : ""
};
//检查PIE图的时间是否符合逻辑
function getdate(obj,i,arg1,arg2){
	var time = $(obj).val();
	switch (i) {
		case 1:
			r_pie_time.btime = time;
			break;
		case 2:
			r_pie_time.etime = time;
			break;
		default:
			break;
	}
	if(r_pie_time.btime.length == 0 || r_pie_time.etime.length == 0){
		alert("开始日期与结束日期不能为空！");
		return;
	}
	if(r_pie_time.btime > r_pie_time.etime){
		alert("开始日期不能大于结束日期！");
		return;
	}
	//recharge_count_jsp_pie.initChart();
	if(Object == arg1.constructor){
		arg1.initChart();
	}
	if(Object == arg2.constructor){
		arg2.initChart();
	}
}

/**
 * 查询趋势统计
 * @param obj
 */
function query_chart(obj){
	var grand = $(obj).parent().parent().parent();
	var typeChecked = grand.children("ul").eq(0).find("input[type='radio']:checked").val();
	typeChecked = 'undefined' == typeof(typeChecked) ? "all" : typeChecked;
	var dateChecked = getDateChecked(grand.children("ul").eq(1).find("input[type='radio']:checked").val());
	var beginDate;
	var endDate;
	if(4 != parseInt(dateChecked)){
		 beginDate = $(obj).closest("li").prev("li").children("input").eq(0).val();
		 //var endDate = grand.children("ul").eq(1).find("input[class='Wdate']").eq(1).val();
		 endDate = $(obj).closest("li").prev("li").children("input").eq(1).val();
		 if( typeof(beginDate) === "undefined" &&  typeof(endDate) === "undefined"){
			beginDate = "";
			endDate = "";
	     }
	}else{
		beginDate = $(obj).closest("li").prev("li").children("select").eq(0).val();
		endDate = $(obj).closest("li").prev("li").children("select").eq(1).val();
	}
	//调用刷新ajax报表数据；第一个参数对象;第二个是类型:all,maya,max;第三个是时间类型;
	//getOption(arrChart[id],typeChecked,dateChecked,beginDate,endDate);
	//get_Select_Date(dateChecked,beginDate,endDate)
	var selection  = {};
	selection.dataType = typeChecked;
	selection.timeType = dateChecked;
	selection.beginDate = beginDate;
	selection.endDate = endDate;
	return selection;
}



/***
 * 根据条件筛选数据
 * @param myChart
 */
/*function getOption(myChart,myOption,selection,url){
	var data_res = null;;
	if(typeof url != "undefined" && url.length > 0){
		url = HtmlEncode(url);
		//var type = typeof arguments[1] === 'undefined' ?  "all" : arguments[1];
		var type = selection.dataType;
		var param = new Object();
		var date_obj = get_Select_Date(selection.timeType,selection.beginDate,selection.endDate);
		if(date_obj){
			param.send_time1 = date_obj.beginDate;
			param.send_time2 = date_obj.endDate;
					
			$.post(encodeURI(url),param,
				function(res){
					//第一个是时间类型,第二是maya,max或是所有类型,第三个是结果,第四个是时间集合
					 set_Chart_data(selection.timeType,type,res,date_obj);
					//myChart.setOption(myOption,true);
					
					//myChart.hideLoading();
				}
			,'json');
		}
	}else{
		alert("查询出错！");
		chart_Has_Worng(myChart,myOption);
	}
}*/


/**
 * 根据条件设置表格数据
 * @param type
 * @param res
 * @param date_obj
 * 第一个是时间类型,第二是maya,max或是所有类型,第三个是结果,第四个是时间集合
 */
function set_Chart_data(cycle,type,res,date_obj,kind,myChart,myOption){
	my_Type = kind;
	if(res === null || res.length === 0){
		chart_Has_Worng(myChart,myOption);
		alert("没有数据!");
		return;
	}
	var dates = date_obj.dates;
	var chart_data = {};
	var res_chart_data= get_Cycle_Date(cycle,type,res,dates,chart_data);
	/*if(chart_data.z){
		option_Echart.xAxis[0]['data'] = chart_data.x;
		option_Echart.series[0]['data'] = chart_data.y;
		option_Echart.series[1]['data'] = chart_data.z;
	}else{
		option_Echart.xAxis[0]['data'] = chart_data.x;
		option_Echart.series[0]['data' ] = chart_data.y;
	}*/
	return res_chart_data;
}

/**
 * 获取数据
 * @param cycle
 * @param type
 * @param res
 * @param dates
 * @param chart_data
 * @returns
 */
function get_Cycle_Date(cycle,type,res,dates,chart_data){
	if(parseInt(cycle) === 1){
		chart_data.x = dates;
		chart_data.y = new Array(dates.length);
		chart_data.z = new Array(dates.length);
		for (var i = 0; i < dates.length; i++) {
			chart_data.y[i] = 0;
			chart_data.z[i] = 0;
			for (var int = 0; int < res.length; int++) {
				var time = typeof(res[int].TIME) != "undefined" ? res[int].TIME : res[int].STATISTICALTIME;
				if(time == dates[i]){
					has_Type = get_Type(type,res[int]);
					if(typeof(has_Type.y) != "undefined"){
						chart_data.y[i] = has_Type.x;
						chart_data.z[i] = has_Type.y;
					}else{
						chart_data.y[i] = has_Type;
					}
				}
			}
		}
	}else{
		chart_data.x = new Array();
		chart_data.y = new Array();
		chart_data.z = new Array();
		var unit = "";
		if(parseInt(cycle) === 2){
			unit = "";
		}else if(parseInt(cycle) === 3){
			unit = "月";
		}else if(parseInt(cycle) === 4){
			unit = "季度";
		}
		for ( var it in res) {
			chart_data.x[it] = typeof(res[it].TIME) != "undefined" ? res[it].TIME + unit : res[it].STATISTICALTIME + unit;
			has_Type = get_Type(type,res[it]);
			if(typeof(has_Type.y) != "undefined"){
				chart_data.y[it] = has_Type.x;
				chart_data.z[it] = has_Type.y;
			}else{
				chart_data.y[it] = has_Type;
			}
		}
		
	}
/*	if (!flag) {
		delete chart_data.z;
	}*/
	return chart_data;
}



function daysBetween(DateOne,DateTwo)  
{   
	
//	var startdate = new Date(Date.parse(DateOne.replace(/-/g, "/"))) ;
//	var enddate = new Date(Date.parse(DateTwo.replace(/-/g, "/"))) ;
//	var date3=enddate.getTime()-startdate.getTime(); 
//	var days=Math.floor(date3/(24*3600*1000));
//	var hours=Math.floor(date3%(24*3600*1000)/(3600*1000));
//	var minutes=Math.floor(date3%(3600*1000)/(60*1000));
//	console.log(startdate+"--"+enddate);
//	console.log("时间差为:"+days+"天-"+hours+"小时-"+minutes+"分钟");
//	return days+","+hours+","+minutes;
	//2016-06-04 13:31
//	console.log(DateOne+"--"+DateTwo);
    var OneMonth = DateOne.substring(5,DateOne.lastIndexOf ('-'));  //06
    var OneDay = DateOne.substring(DateOne.length,DateOne.lastIndexOf ('-')+1); //04 
//    var OneDay = DateOne.substring(DateOne.lastIndexOf ('-')+1,DateOne.length-5); //04 
    var OneYear = DateOne.substring(0,DateOne.indexOf ('-'));  //2016
//    //时
//    var oneHour = (DateOne.substring(DateOne.lastIndexOf (':')-3,DateOne.length-3)).trim();
//    //分
//    var oneMin = DateOne.substring(DateOne.lastIndexOf (':')+1,DateOne.length);
//    
////    console.log("OneMonth:"+OneMonth+"-OneDay:"+OneDay+"-OneYear:"+OneYear+"-oneHour:"+oneHour+"-oneMin:"+oneMin);
    var TwoMonth = DateTwo.substring(5,DateTwo.lastIndexOf ('-')); 
    var TwoDay = DateTwo.substring(DateTwo.length,DateTwo.lastIndexOf ('-')+1); //04 
//    var TwoDay = DateTwo.substring(DateTwo.lastIndexOf ('-')+1,DateTwo.length-5);
    var TwoYear = DateTwo.substring(0,DateTwo.indexOf ('-'));
//    //时
//    var twoHour = (DateOne.substring(DateOne.lastIndexOf (':')-3,DateOne.length-3)).trim();
//    //分
//    var twoMin = DateOne.substring(DateOne.lastIndexOf (':')+1,DateOne.length);
//    
//    console.log("#######"+Date.parse(OneMonth+'/'+OneDay+'/'+OneYear+' '+oneHour+':'+oneMin));
//    console.log("*******"+Date.parse(TwoMonth+'/'+TwoDay+'/'+TwoYear+' '+twoHour+':'+twoMin));
//  
    var cha=((Date.parse(OneMonth+'/'+OneDay+'/'+OneYear)- Date.parse(TwoMonth+'/'+TwoDay+'/'+TwoYear))/86400000);  
//    var cha=((Date.parse(OneMonth+'/'+OneDay+'/'+OneYear+' '+oneHour+':'+oneMin)- Date.parse(TwoMonth+'/'+TwoDay+'/'+TwoYear+' '+twoHour+':'+twoMin))/86400000);  
//    console.log("cha:"+cha);
    return Math.abs(cha);  
}



//验证为空
function checkEmpty(obj, msg) {
	if ($("#" + obj).val() == "") {
		$("#" + obj).nextAll(".LV_invalid").removeClass("none").html("*必填");
		return;
	}else{
		$("#" + obj).nextAll(".LV_invalid").addClass("none").html("");
	}
}


/**
 * 判断类型获取不同数据
 * @param type
 * @param obj
 * @returns
 */
var time_point = [];
function get_Type(type,obj){
	if(type.indexOf("all") > 0  || typeof(type) === "undefined"){
		type = "all";
	}
	var temp = null;
	if(my_Type == "SUBPERSON"){
		switch (type) {
		case "all":
			temp = obj.MANNUM_TOTAL; 
			break;
		case "max":
			temp = obj.MANNUM_MAX;
			break;
		case "maya":
			temp = obj.MANNUM_MAYA;
			break;
		default:
			break;
		}
	}else if(my_Type == "MISSIONNUMBER"){
		switch (type) {
		case "all":
			temp = obj.MISSIONNUMBER;
			break;
		case "max":
			temp = obj.MAX_MISSIONNUMBER;
			break;
		case "maya":
			temp = obj.MAYA_MISSIONNUMBER;
			break;
		default:
			break;
		}
	}else if(my_Type == "SUCCESSFRAMES"){
		switch (type) {
			case "all":
				temp = obj.SUCCESSFRAMES;
				break;
			case "max":
				temp = obj.MAX_SUCCESSFRAMES;
				break;
			case "maya":
				temp = obj.MAYA_SUCCESSFRAMES;
				break;
			default:
				break;
		}
	}else if(my_Type == "HOURHE"){
		switch (type) {
		case "all":
			temp = obj.HOURHE;
			break;
		case "max":
			temp = obj.MAX_HOURHE;
			break;
		case "maya":
			temp = obj. MAYA_HOURHE;
			break;
		default:
			break;
		}
	}else if(my_Type == "HOURHE_FRAME"){
		switch (type) {
			case "all":
				temp = obj.HOURHE_FRAME;
				break;
			case "max":
				temp = obj.MAX_HOURHE_FRAME;
				break;
			case "maya":
				temp = obj.MAYA_HOURHE_FRAME;
				break;
			default:
				break;
		}
	}else if(my_Type == "SELF_RENDER"){
		temp = new Object();
		temp.x = obj.NATUREMONEY;
		temp.y = obj.NATUREMONEY_SC;
	}else if(my_Type == "PACKTIME"){
		temp = obj.PLAYDEDUCTION;
	}else if(my_Type == "SELFPACKTIME"){
		temp = new Object();
		temp.x = obj.COST_TOTAL;
		temp.y = obj.COST_SC;
	}else if(my_Type == "FEEAVERAGE"){
		temp = new Object();
		temp.x = obj.COST_DAY;
		temp.y = obj.COST_SC_DAY;
	}else if(my_Type == "HOURSELFRENDER"){
		temp = new Object();
		switch (type) {
			case "all":
				temp.x = obj.COST_SC_HOUR;
				temp.y = obj.COST_HOUR;
				break;
			case "max":
				temp.x = obj.MAX_COST_SC_HOUR;
				temp.y = obj.MAX_COST_HOUR;
				break;
			case "maya":
				temp.x = obj.MAYA_COST_SC_HOUR;
				temp.y = obj.MAYA_COST_HOUR;
				break;
			default:
				break;
		}
	}else if(my_Type == "HOURPACKAGERENDER"){
		temp = obj.COST_HOUR;
	}else if(my_Type == "FACTTOTALRENDER"){
		//temp = new Object();
		temp = obj.COST_SC_HOUR;
		//temp.y = obj.COST_HOUR;
	}else if(my_Type == "RECHARGETOTAL"){
		switch (type) {
			case "all":
				temp = obj.MONEY;
				break;
			case "fact":
				temp = obj.SC_MONEY;
				break;
			case "give":
				temp = obj.ZS_MONEY;
				break;
			default:
				break;
		}
	}else if(my_Type == "RECHARGEPERSON"){
		temp = obj.MAN_NUM;
	}else if(my_Type == "RECHARGECOUNT"){
		switch (type) {
			case "all":
				temp = obj.NUM_TOTAL;
				break;
			case "100":
				temp = obj.NUM_0_100;
				break;
			case "500":
				temp = obj.NUM_100_500;
				break;
			case "1000":
				temp = obj.NUM_500_1000;
				break;
			case "1000up":
				temp = obj.NUM_1000;
				break;
			default:
				break;
		}
	}else if(my_Type == "PEAKCOUNT"){
		temp = obj.PEAK_NUM;
		var t_obj = hashMap.getObj(obj.TIME,obj.PEAK_NUM_TIME);
		time_point.push(t_obj);
	}else if(my_Type == "PEAKTASKCOUNT"){
		/*switch (type) {
			case "all":
				temp = parseInt(obj.PEAK_NUM) 
				break;
			case "sub":
				temp = parseInt(obj.MISSION_SUBMIT);
				break;
			case "run":
				temp = parseInt(obj.MISSION_RUN);
				break;
			default:
				break;
		}*/
		temp = parseInt(obj.PEAK_NUM);
		var t_p = hashMap.getObj(obj.TIME,obj.PEAK_NUM_TIME);
		time_point.push(t_p);
	}else if(my_Type == "USERINFOLOGIN"){
		temp = obj.NUM;
	}
	return temp;
}


/**
 * 获取查询时间的类型
 * @param dateType
 * @returns {Number}
 */
function getDateChecked(dateType){
	var temp = 0;
	switch (dateType) {
	case "day":
		temp = 1;
		break;
	case "week":
		temp = 2;
		break;
	case "month":
		temp = 3;
		break;
	case "quarter":
		temp = 4;
		break; 
	default:
		break;
	}
	return temp;
}

/**
 * 获得天数已经开始日期和结束日期
 * @param cycle 时间类型
 * @param beginDate 开始时间
 * @param endDate 结束时间
 * @returns {___anonymous39630_39631}
 */
function get_Select_Date(cycle,beginDate,endDate){
	var res = {};
	var day_start = 7;
	var day_end = 1;
	
	switch (cycle) {
	case 1:
		day_start = 7;
		day_end = 1;
		res.dates = new Array();
		if(typeof  beginDate == "undefined" || typeof  endDate == "undefined"){
			res.beginDate = get_Previous_Date(day_start);
			res.endDate = get_Previous_Date(day_end);
		}else if(beginDate.length === 0 && endDate.length === 0){
			res.beginDate = get_Previous_Date(day_start);
			res.endDate = get_Previous_Date(day_end);
		}else{
			res.beginDate = beginDate;
			res.endDate = endDate;
		}
		//res = is_logic_date(beginDate,endDate,day_start,day_end,res);
		var statTime = new Date(Date.parse(res.beginDate.replace(/\-/g,"/"))).getTime();
		var endTime = new Date(Date.parse(res.endDate.replace(/\-/g,"/"))).getTime();
		var dates = (Math.abs((statTime - endTime)) / (24 * 60 * 60 * 1000));
		for (var i = dates; i > 0; i--) {
			res.dates.push(get_Previous_Date(i,res.endDate));
		}
		res.dates.push(res.endDate);
		break;
	case 2:
		//默认前167天算第一周
		var temp = get_week_days(167);
		if(typeof  beginDate == "undefined" || typeof  endDate == "undefined"){
			res.beginDate = temp.begin;
			res.endDate = temp.end;
		}else if(beginDate.length === 0 && endDate.length === 0){
			res.beginDate = temp.begin;
			res.endDate = temp.end;
		}else{
			res.beginDate = beginDate;
			res.endDate = endDate;
		}
		if(parseInt(new Date(res.beginDate).getDay()) != 1 || parseInt(new Date(res.endDate).getDay()) != 0){
			alert("选择周跨度不正确!");
			return false;
		}
		break;
	case 3:
		//默认月份：从当前的前一个月 至前6个月
		var temp = get_month_days();
		if(typeof  beginDate == "undefined" || typeof  endDate == "undefined"){
			res.beginDate = temp.begin;
			res.endDate = temp.end;
		}else if(beginDate.length === 0 && endDate.length === 0){
			res.beginDate = temp.begin;
			res.endDate = temp.end;
		}else{
			res.beginDate = beginDate+ "-01";
			var to_date = new Date(endDate);
			var to_date_c = new Date(to_date);
			to_date_c.setMonth(to_date.getMonth() + 1);
			to_date_c.setDate(0);
			res.endDate = to_date_c.format("yyyy-MM-dd");
		}
		var a_date = new Date(res.endDate);
		var b_date = new Date(a_date);
		b_date.setMonth(a_date.getMonth() + 1);
		b_date.setDate(0);
		if(new Date(res.endDate).getMonth() === new Date().getMonth()){
			alert("结束月不能选当前月！");
			return false;
		}
		if(new Date(res.beginDate).getDate() != 1 && (a_date.getDate() != b_date.getDate() ) ){
			alert("选择月份跨度不正确!");
			return false;
		}
		break;
	case 4:
		//默认季度从当年1月份开始
		/*day_start = new Date("2015-01-01").format("yyyy-MM-dd");
		day_end = new Date("2015-12-31").format("yyyy-MM-dd");*/
		var temp = get_quarter_days();
		/*if(typeof  beginDate == "undefined" || typeof  endDate == "undefined"){
			res.beginDate = temp.b;
			res.endDate = temp.e;
		}else if(beginDate.length === 0 && endDate.length === 0){
			res.beginDate = temp.b;
			res.endDate = temp.e;
		}else{
			if(new Date(beginDate).getFullYear() == new Date().getFullYear() && 
					new Date(endDate).getFullYear() == new Date().getFullYear()){
				res.beginDate = temp.b;
				res.endDate = temp.e;
			}else{
				var d_value = parseInt(endDate) - parseInt(beginDate);
				if(new Date().getFullYear() == parseInt(endDate)){
					res.endDate = temp.e;
				}else{
					res.endDate = endDate + "-12-31";
				}
				if(d_value >= 0){
					res.beginDate = beginDate + "-01-01";
				}else{
					alert("选择年份跨度不正确!");
					return false;
				}
			}
		}*/
		res.beginDate = beginDate + "-" + formate((temp.b.getMonth()  + 1)) + "-" + formate(temp.b.getDate());
		res.endDate = endDate + "-" + formate((temp.e.getMonth() + 1)) + "-" + formate(temp.e.getDate());
		break;
	default:
		break;
	}
	
	if(res.beginDate.length === 0 && res.endDate.length > 0){
		alert("开始时间不能为空!");
		return false;
	}
	if(res.beginDate.length > 0 && res.endDate.length === 0){
		alert("结束时间不能为空!");
		return false;
	}
	if(res.beginDate > res.endDate){
		alert("开始时间不能大于结束时间");
		return false;
	}
	
	return res;
}


/**
 * 其中size是上一周的最后一天距离开始日期的天数
 * @param size
 * @returns
 */
function get_week_days(size){
	if(!size){
		size = 6;
	}
	var now = new Date();
	var now_day = now.getDay();
	var days = {};
	//var begin_day = null;
	var end = new Date(get_Previous_Date(now_day));
	//days.begin = new Date(new Date(days.end).getTime() - size * 24 * 60 * 60 * 1000);
	var begin  = new Date((end.getTime() - size * 24 * 60 * 60 * 1000));
	days.end = end.format("yyyy-MM-dd");
	days.begin = begin.format("yyyy-MM-dd");
	return days;
}

/**
 * 获取默认月的开始和结束日期
 * @returns {___anonymous45563_45564}
 */
function get_month_days(){
	var now = new Date();
	var year  = now.getFullYear();
	var month = now.getMonth() + 1;
	var now_month_first = new Date(year + "-" + month + "-01").getTime();
	var enddate = new Date(now_month_first - 1 * 24 * 60 * 60 * 1000);
	var current = null;
	for (var int = 0; int < 6; int++) {
		current = new Date(enddate.getFullYear() + "-" +  (enddate.getMonth() + 1 - int) + "-01").getTime();
	}
	var res = {};
	res.begin = new Date(current).format("yyyy-MM-dd");
	res.end = enddate.format("yyyy-MM-dd");
	return res;
}

function get_quarter_days(){
	var now = new Date();
	var month = now.getMonth();
	var res = {};
	if( month >= 0 &&  month <= 2){
		res.b = new Date(now.getFullYear() + "-01" + "-01");
		res.e = new Date(now.getFullYear() + "-01" + "-01");
	}
	if(month >= 3 && month <= 5){
		res.b = new Date(now.getFullYear() + "-01" + "-01");
		var last = new Date(now.getFullYear() + "-03" + "-01");
		var last_c = new Date(last);
		last_c.setMonth(last.getMonth() + 1);
		last_c.setDate(0);
		res.e = new Date(last_c);
	}
	if(month >= 6 && month <= 8){
		res.b = new Date(now.getFullYear() + "-01" + "-01");
		var last = new Date(now.getFullYear() + "-06" + "-01");
		var last_c = new Date(last);
		last_c.setMonth(last.getMonth() + 1);
		last_c.setDate(0);
		res.e = new Date(last_c);
	}
	if(month >= 9 && month <= 11){
		res.b = new Date(now.getFullYear() + "-01" + "-01");
		var last = new Date(now.getFullYear() + "-09" + "-01");
		var last_c = new Date(last);
		last_c.setMonth(last.getMonth() + 1);
		last_c.setDate(0);
		res.e = new Date(last_c);
	}
	res.b = res.b;
	res.e = res.e;
	return res;
}

/**
 * 错误时调用参数，生成报表
 */
function chart_Has_Worng(myChart,myOption){
	myOption.xAxis[0].data = [0,0,0,0,0,0,0];
	myOption.series[0].data = [0,0,0,0,0,0,0];
	myChart.setOption(myOption);
}

/**
 * 筛选条件
 * @param type
 * @returns {String}
 */
function get_Html_Select(type){
	var html = "";
	switch (type) {
	case 1:
		html = "开始:<input type='text'  readonly " +
				" class='Wdate' style='margin-top: 8px;' " +
				"onclick=\"WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true," +
				"maxDate:'%y-%M-{%d-1}'})\"/> " +
				"结束:<input type='text' name='' readonly " +
				" class='Wdate' style='margin-top: 8px;'" +
				"onclick=\"WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true," +
				"maxDate:'%y-%M-{%d-1}'})\"/>";
		break;
	case 2:
		html = "开始:<input type='text'  readonly " +
				" class='Wdate' style='margin-top: 8px;' " +
				"onclick=\"WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true," +
				"maxDate:'%y-%M-{%d-1}'})\"/> " +
				"结束:<input type='text' name='' readonly " +
				"' class='Wdate' style='margin-top: 8px;'" +
				"onclick=\"WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true," +
				"maxDate:'%y-%M-{%d-1}'})\"/>";
		break;
	case 3:
		html = "开始:<input type='text'  readonly " +
				" class='Wdate' style='margin-top: 8px;' " +
				"onclick=\"WdatePicker({startDate:'%y-%M',dateFmt:'yyyy-MM',alwaysUseStartDate:true," +
				"maxDate:'%y-%M'})\"/> " +
				"结束:<input type='text' name='' readonly " +
				"' class='Wdate' style='margin-top: 8px;'" +
				"onclick=\"WdatePicker({startDate:'%y-%M',dateFmt:'yyyy-MM',alwaysUseStartDate:true," +
				"maxDate:'%y-%M'})\"/>";
		break;
	case 4:
		var year = new Date().getFullYear();
		
		var temp = "";
		for (var i = 0; i < 5; i++) {
			temp += "<option value=" + year + ">" + year + "</option>";
			year--;
		}
		html = "开始:<select class='b_year'>" + temp + "</select>至" +
			   "结束:<select class='e_year'>" + temp + "</select>";
		
		break;
	default:
		break;
	}
	return html;
}


/**
 * 地域统计展示
 * @param obj
 */
var regional_url = "";
var regional_obj_tab = null;
var regional_order = "";
function regional(obj,url,order,bTime,eTime){
	var grand = $(obj).closest("div");
	var great_grand = grand.parent();
	regional_url = url;
	regional_obj_tab = great_grand;
	regional_order = order;
	if($(obj).closest("ul").children("li").length == 2){
		$(obj).prev("li").removeClass("border_li").addClass("border_none");
		$(obj).removeClass("border_none").addClass("border_li");
		great_grand.children(".echart").removeClass("block").addClass("none");
		great_grand.children(".acc_chart").removeClass("none").addClass("block");
		grand.children("div").eq(0).removeClass("block").addClass("none");
		grand.children("div").eq(1).removeClass("none").addClass("block");
	}else{
		changeBtnStyle($(obj));
		changeSelectionStyle(grand,$(obj).index());
		changetContextStyle(grand.parent(),$(obj).index());
	}
	get_Regional_Data(order,bTime,eTime);
}

/**
 * 从服务器上获取数据
 * @param url
 * @param type
 * @param order
 */
function get_Regional_Data(order,bTime,eTime){
	var current_Time = new Date();
	var end_Time = null;
	var begin_Time = null;
	if("undefined" == typeof(eTime) || eTime == ""){
		end_Time = current_Time.format("yyyy-MM-dd"); 
	}else{
		end_Time = eTime;
	}
	if("undefined" == typeof(bTime) || bTime == ""){
		 begin_Time = new Date(current_Time.getFullYear()+ "-01-01").format("yyyy-MM-dd");
	}else{
		 begin_Time = bTime;
	}
	order = typeof(order) === "undefined" ? "all" : order;
	var param = {};
	param.send_time1 = begin_Time;
	param.send_time2 = end_Time;
	param.order = "";
	$.post(encodeURI(regional_url),param,
			function(res){
		if(res.length == 0){
			regional_obj_tab.children("div").eq(2).children("div").eq(0).html("");
			regional_obj_tab.children("div").eq(2).children("div").eq(1).html("");
			alert("没有获取到数据!");
			return;
		}
		var data = set_Regional_Data(res);
		data = calcu_percent(data);
		if(data){
			if(regional_order == "nature" ||  regional_order == "pack_time" || 
					regional_order == "self_pack_time" || regional_order == "userinfo_login" || regional_order == "recharge_person"){
				//render_chart_one(order_sort(data,"all"));
				if(data){
					render_chart_one(order_sort(data,"all"));
				}
			}else if (regional_order == "recharge_count"){
				//render_chart_two(data);
				render_chart_two(order_sort_recharge_count(data,order));
			}else {
				var temp = order_sort(data,order);
				render_chart_pro(temp);
			}
			bindMessage();
		}else{
			alert("没有获取到数据!");
		}
	},'json');
}

/**
 * 把数据按照规则取出
 * @param res
 * @returns {Array}
 */
function set_Regional_Data(res){
	var data = new Array();
	for ( var it in res) {
		var regional_obj = null;
		switch (regional_order) {
		    case 'sub_person':
		    	regional_obj = new to_Object("人",res[it].PROVINCE, res[it].MAX_MAN_NUM, res[it].MAYA_MAN_NUM,"提交人数:");
		    	break;
			case 'num':
				regional_obj = new to_Object("个",res[it].PROVINCE, res[it].MAX_NUM, res[it].MAYA_NUM,"任务数:");
				break;
			case 'total_frame':
				regional_obj = new to_Object("帧",res[it].PROVINCE, res[it].MAX_TOTAL_FRAME, res[it].MAYA_TOTAL_FRAME,"帧:");
				break;
			case 'seconds':
				regional_obj = new to_Object("核小时",res[it].PROVINCE, res[it].MAX_SECONDS, res[it].MAYA_SECONDS,"核小时数:");
				break;
			case 'hourHe_frame':
				regional_obj = new to_Object("核小时",res[it].PROVINCE, res[it].MAX_HOURHE_FRAME, res[it].MAYA_HOURHE_FRAME,"核小时数:");
				break;
			case 'nature':
				regional_obj = new to_Object_one("元",res[it].PROVINCE, res[it].NATURE_SC,"实充账户扣费:");
				break;
			case 'pack_time':
				regional_obj = new to_Object_one("元",res[it].PROVINCE, res[it].PLAYDEDUCTION,"实充账户扣费:");
				break;
			case 'self_pack_time':
				regional_obj = new to_Object_one("元",res[it].PROVINCE, res[it].NATURE_SC,"实充账户扣费:");
				break;
			case 'recharge_total':
				regional_obj = new to_Object("元",res[it].PROVINCE, res[it].SC_MONEY,res[it].ZS_MONEY,"实充金额:","赠送金额:");
				break;
			case 'recharge_person':
				regional_obj = new to_Object_one("人",res[it].PROVINCE, res[it].MAN_NUM,"充值人数");
				break;
			case 'recharge_count':
				regional_obj = new to_Object_two("笔",res[it].PROVINCE, res[it].NUM_0_100, res[it].NUM_100_500, res[it].NUM_500_1000, res[it].NUM_1000,"充值笔数");
				break;
			case 'userinfo_login':
				regional_obj = new to_Object_one("人",res[it].PROVINCE,res[it].NUM,"注册数");
			default:
				break;
		}
		data.push(regional_obj);
	}
	return data;
}

/**
 * 处理数据并变成对象
 */
function to_Object(UNIT,PROVINCE,MAX_NUM,MAYA_NUM,msg1,msg2){
	this.UNIT = UNIT;
	this.PROVINCE = PROVINCE;
	this.TOTAL = (MAX_NUM + MAYA_NUM).toString().indexOf(".") != -1 ? (MAX_NUM + MAYA_NUM).toFixed(2) : (MAX_NUM + MAYA_NUM);
	this.MAX = MAX_NUM.toString().indexOf(".") != -1 ? MAX_NUM.toFixed(2) : MAX_NUM;
	//this.MAX_PERCENT = Math.ceil((this.MAX / this.TOTAL) * 100);
	this.MAYA = this.TOTAL - this.MAX;
	this.MAX_PERCENT = 0;
	this.MAYA_PERCENT = 0;
	this.MAX_LEN = 0;
	this.MAYA_LEN = 0 ;
	this.MSG_1 = msg1;
	if(typeof(msg2) != 'undefined'){
		this.MSG_2 = msg2;
	}
	//this.MAYA_PERCENT = Math.floor((this.MAYA / this.TOTAL) * 100);
	/*if(typeof(arguments[4]) != 'undefined'){
		this.TOTAL = parseInt(arguments[4]);
	}*/
}

/**
 * 
 */
function to_Object_one(UNIT,PROVINCE,arg1,msg1){
	this.UNIT = UNIT;
	this.PROVINCE = PROVINCE;
	/*if(0 < arg1 <= 100){
		this.PERCENT = arg1 * 100 / 100000 * 10;
	}else if(101 < arg1 <= 1000){
		this.PERCENT = arg1 * 1000 / 100000 * 10;
	}else if(1001 < arg1 <= 10000){
		this.PERCENT = arg1 * 10000 / 100000 * 10;
	}else if(10001 < arg1 <= 100000){
		this.PERCENT = arg1 * 100000 / 100000 * 10;
	}*/
	this.PERCENT = 0;
	this.LEN = 0;
	this.TOTAL = arg1.toString().indexOf(".") != -1 ? arg1.toFixed(2) : parseInt(arg1);
	this.MSG_1 = msg1;
}

/***
 * 
 */
function to_Object_two(UNIT,PROVINCE,arg1,arg2,arg3,arg4,msg1){
	this.UNIT = UNIT;
	this.PROVINCE = PROVINCE;
	this.NUM_0_100 = arg1;
	this.NUM_100_500 = arg2;
	this.NUM_500_1000 = arg3;
	this.NUM_1000 = arg4;
	var temp = this.NUM_0_100 + this.NUM_100_500  + this.NUM_500_1000 + this.NUM_1000;
	this.TOTAL = temp.toString().indexOf(".") != -1 ? temp.toFixed(2) : parseInt(temp);
	//this.PEN_0_100 = ( this.NUM_0_100 / this.NUM_TOTAL ) * 100 - (( this.NUM_0_100 / this.NUM_TOTAL ) * 100 / 10);
	this.PEN_0_100 = 0;
	this.PEN_100_500 = 0;
	this.PEN_500_1000 = 0;
	this.PEN_1000 = 0;
	
	this.PEN_0_100_LEN = 0;
	this.PEN_100_500_LEN = 0;
	this.PEN_500_1000_LEN = 0;
	this.PEN_1000_LEN = 0;
	this.MSG_1 = msg1;
}

/**
 * 通过传入参数判断是生成 排比
 * 计算百分比
 * @param data
 */
function calcu_percent(data){
	var count = 0;
	var total = 0;
	for ( var it in data[0]) {
		count++;
	}
	/*this.UNIT = UNIT;
	this.PROVINCE = PROVINCE;
	this.TOTAL = parseInt(MAX_NUM + MAYA_NUM);
	this.MAX = parseInt(MAX_NUM);
	//this.MAX_PERCENT = Math.ceil((this.MAX / this.TOTAL) * 100);
	this.MAYA = parseInt(this.TOTAL - this.MAX);*/
	data = order_sort(data,"all");
	total = data[0].TOTAL;
	if(count == 10 || count == 11){
		for ( var it1 in data) {
			data[it1].MAX_PERCENT = (Number((data[it1].MAX / data[it1].TOTAL)) * 100).toFixed(1);
			data[it1].MAYA_PERCENT = (Number((data[it1].MAYA / data[it1].TOTAL)) * 100).toFixed(1);
			data[it1].MAX_LEN = (Number((data[it1].MAX / total)) * 100).toFixed(1);
			data[it1].MAYA_LEN = (Number((data[it1].MAYA / total)) * 100).toFixed(1);
		}
	}
	
	if(count == 6){
		for ( var it2 in data) {
			data[it2].PERCENT = (Number((data[it2].TOTAL / data[it2].TOTAL)) * 100).toFixed(1);
			data[it2].LEN = (Number((data[it2].TOTAL / total)) * 100).toFixed(1);
		}
	}
	
	if(count == 16){
		for ( var it3 in data) {
			data[it3].PEN_0_100 = Math.floor(Number((data[it3].NUM_0_100 / data[it3].TOTAL)) * 100);
			data[it3].PEN_100_500 = (Number((data[it3].NUM_100_500 / data[it3].TOTAL)) * 100).toFixed(0);
			data[it3].PEN_500_1000 = (Number((data[it3].NUM_500_1000 / data[it3].TOTAL)) * 100).toFixed(0);
			data[it3].PEN_1000 = (Number((data[it3].NUM_1000 / data[it3].TOTAL)) * 100).toFixed(0);
			data[it3].PEN_0_100_LEN = Math.floor(Number((data[it3].NUM_0_100 / total)) * 100);;
			data[it3].PEN_100_500_LEN = (Number((data[it3].NUM_100_500 / total)) * 100).toFixed(0);
			data[it3].PEN_500_1000_LEN = (Number((data[it3].NUM_500_1000 / total)) * 100).toFixed(0);
			data[it3].PEN_1000_LEN = (Number((data[it3].NUM_1000 / total)) * 100).toFixed(0);
		}
	}
	
	return data;
}


/**
 * 按照字段排序
 */
function order_sort(data,type){
	if(type == "all"){
		data.sort(function(a,b){
			return Number(b.TOTAL) - Number(a.TOTAL);
		});
	}else if(type == "max"){
		data.sort(function(a,b){
			return  Number(b.MAX) - Number(a.MAX);
		});
	}else if(type == "maya"){
		data.sort(function(a,b){
			return  Number(b.MAYA) - Number(a.MAYA);
		});
	}
	return data;
}
/**
 * 排序
 */
function order_sort_recharge_count(data,type){
	if(type == "all"){
		data.sort(function(a,b){
			return b.TOTAL - a.TOTAL;
		});
	}else if(type == "0-100"){
		data.sort(function(a,b){
			return  b.NUM_0_100 - a.NUM_0_100;
		});
	}else if(type == "100-500"){
		data.sort(function(a,b){
			return  b.NUM_100_500 - a.NUM_100_500;
		});
	}else if(type == "500-100"){
		data.sort(function(a,b){
			return  b.NUM_500_1000 - a.NUM_500_1000;
		});
	}else if(type == "1000up"){
		data.sort(function(a,b){
			return  b.NUM_1000 - a.NUM_1000;
		});
	}
	return data;
}


/**
 *绘制占比界面图片 
 */
function render_chart_pro(data){
	var tab = regional_obj_tab;
	var other_p = "其他";
	var other_m_c = "";
	var other_a_c = "";
	var other_m = "";
	var other_a = "";
	var other_t = "";
	var other_u = "";
	var other_xlen = "";
	var other_alen = "";
	var other_msg1 = "";
	var other_msg2 = "";
	var html_1 = "<table style='width:100%;border-spacing:20px;'>";
	var html_2 = "<table style='width:100%;border-spacing:20px;'>";
	var html_3 = "";
	if(data.length <= 0 ){
		return;
	}
	var i = 0;
	data = render_del_out_data(data);
	while(i < data.length) {
		if(data[i].TOTAL == 0 ){
			i++;
			continue;
		}
		if(data[i].PROVINCE == other_p && data[i].TOTAL > 0){
			other_m_c = data[i].MAX;
			other_a_c = data[i].MAYA;
			other_m = data[i].MAX_PERCENT;
			other_a = data[i].MAYA_PERCENT;
			other_t = data[i].TOTAL;
			other_u = data[i].UNIT;
			other_xlen = data[i].MAX_LEN;
			other_alen = data[i].MAYA_LEN;
			other_msg1 = data[i].MSG_1;
			other_msg2 = data[i].MSG_2;
			data.splice(i,1);
			continue;
		}
		if(i < 8){
			html_1 += "<tr style='width: 100%;height: 35px;'>" +
			"<td style='width: 15%;font-size:60%' class='text_elipsis'>" +  (i + 1)  + "." + data[i].PROVINCE + "</td>" +
			"<td style='width: 70%'>" +
				"<ul class='showTotalMsg' style='width: 100%;height: 35px;text-align: center;line-height: 35px;;font-size:60%'>";
			
			if(isMaxWidth(data[i].MAX_LEN)){
				html_1 += "<li class='lileft font_whilte bg_type1 item-info' style='width: " + data[i].MAX_LEN  +"%;height: 35px;'>" +
					(data[i].MAX_PERCENT == 0 ? "" : data[i].MAX_PERCENT + "%") + 
					"<input type='hidden' value=\'" + data[i].MSG_1 + (Number(data[i].MAX).toFixed(2)) + ",占比:" + data[i].MAX_PERCENT +"%\'></li>";
			}else{
				html_1 += "<li class='lileft font_whilte bg_type1 item-info' style='width: " + data[i].MAX_LEN  +"%;height: 35px;'>" +
					"<input type='hidden' value=\'" + data[i].MSG_1 + (Number(data[i].MAX).toFixed(2)) + ",占比:" + data[i].MAX_PERCENT +"%\'></li>";
			}
			if(isMaxWidth(data[i].MAYA_LEN)){
				html_1 += "<li class='lileft font_whilte bg_type2 item-info' style='width: " + data[i].MAYA_LEN  +"%;height: 35px;'>" +
					(data[i].MAYA_PERCENT == 0 ? "" : data[i].MAYA_PERCENT + "%") + 
					"<input type='hidden' value=\'" + (typeof(data[i].MSG_2) != 'undefined' ? data[i].MSG_2 : data[i].MSG_1) + (Number(data[i].MAYA).toFixed(2)) + 
					",占比:" + data[i].MAYA_PERCENT +"%\'></li>";
			}else{
				html_1 += "<li class='lileft font_whilte bg_type2 item-info' style='width: " + data[i].MAYA_LEN  +"%;height: 35px;'>" +
					"<input type='hidden' value=\'" + (typeof(data[i].MSG_2) != 'undefined' ? data[i].MSG_2 : data[i].MSG_1) + (Number(data[i].MAYA).toFixed(2)) + 
					",占比:" + data[i].MAYA_PERCENT +"%\'></li>";
			}
			html_1 += "</ul></td>" +
				"<td style='width: 15%;text-align:right;font-size:60%'>" + data[i].TOTAL + data[i].UNIT + "</td>" +
				"</tr>";
		}
		if(i >= 8){
			html_2 += "<tr style='width: 100%;height: 35px;'>" +
			"<td style='width: 15%;font-size:60%' class='text_elipsis'>" +  (i + 1)  + "." + data[i].PROVINCE + "</td>" +
			"<td style='width: 70%'>" +
				"<ul class='showTotalMsg' style='width: 100%;height: 35px;text-align: center;line-height: 35px;'>";
			
			if(isMaxWidth(data[i].MAX_LEN)){
				html_2 += "<li class='lileft font_whilte bg_type1 item-info' style='width: " + data[i].MAX_LEN +"%;height: 35px;'>" +
					(data[i].MAX_PERCENT == 0 ? "" : data[i].MAX_PERCENT + "%") + 
					"<input type='hidden' value=\'" + data[i].MSG_1 + (Number(data[i].MAX).toFixed(2)) + ",占比:" + data[i].MAX_PERCENT +"%\'></li>";
			}else{
				html_2 += "<li class='lileft font_whilte bg_type1 item-info' style='width: " + data[i].MAX_LEN  +"%;height: 35px;'>" +
					"<input type='hidden' value=\'" + data[i].MSG_1 + (Number(data[i].MAX).toFixed(2)) + ",占比:" + data[i].MAX_PERCENT +"%\'></li>";
			}
			if(isMaxWidth(data[i].MAYA_LEN)){
				html_2 += "<li class='lileft font_whilte bg_type2 item-info' style='width: " + data[i].MAYA_LEN  +"%;height: 35px;'>" +
					(data[i].MAYA_PERCENT == 0 ? "" : data[i].MAYA_PERCENT + "%") + 
					"<input type='hidden' value=\'" + (typeof(data[i].MSG_2) != 'undefined' ? data[i].MSG_2 : data[i].MSG_1) + (Number(data[i].MAYA).toFixed(2)) + 
					",占比:" + data[i].MAYA_PERCENT +"%\'></li>";
			}else{
				html_2 += "<li class='lileft font_whilte bg_type2 item-info' style='width: " + data[i].MAYA_LEN  +"%;height: 35px;'>" +
					"<input type='hidden' value=\'" + (typeof(data[i].MSG_2) != 'undefined' ? data[i].MSG_2 : data[i].MSG_1) + (Number(data[i].MAYA).toFixed(2)) + 
					",占比:" + data[i].MAYA_PERCENT +"%\'></li>";
			}
			html_2 += "</ul></td>" +
				"<td style='width: 15%;text-align:right;font-size:60%'>" + data[i].TOTAL + data[i].UNIT + "</td>" +
				"</tr>";
		}
		i++;
	}
	
	
	if(other_t != "" && other_t > 0){
		html_3 += "<tr style='width: 100%;height: 35px;'>" +
		"<td style='width: 15%;font-size:60%' class='text_elipsis'>" + (data.length + 1) + "." + other_p + "</td>" +
		"<td style='width: 70%'>" +
			"<ul class='showTotalMsg' style='width: 100%;height: 35px;text-align: center;line-height: 35px;'>";
		if(isMaxWidth(other_xlen)){
			html_3 += "<li class='lileft font_whilte bg_type1 item-info' style='width: " + other_xlen  +"%;height: 35px;'>" +
					  (other_m == 0 ? "" : other_m + "%") + 
					  "<input type='hidden' value=\'" + other_msg1 + Number(other_m_c).toFixed(2) + ",占比:" + other_m +"%\'></li>";
		}else{
			html_3 += "<li class='lileft font_whilte bg_type1 item-info' style='width: " + other_xlen  +"%;height: 35px;'>" +
			  "<input type='hidden' value=\'" + other_msg1 + Number(other_m_c).toFixed(2) + ",占比:" + other_m +"%\'></li>";
		}
		
		if(isMaxWidth(other_alen)){
			html_3 += "<li class='lileft font_whilte bg_type2 text_elipsis' style='width: " + other_alen  +"%;height: 35px;'>" +
				(other_a == 0 ? "" : other_a + "%") + 
				"<input type='hidden' value=\'" + (typeof(other_msg2) != 'undefined' ? other_msg2 : other_msg1) + Number(other_a_c).toFixed(2) + 
				",占比:" + other_a +"%\'></li>";
		}else{
			html_3 += "<li class='lileft font_whilte bg_type2 text_elipsis' style='width: " + other_alen  +"%;height: 35px;'>" +
				"<input type='hidden' value=\'" + (typeof(other_msg2) != 'undefined' ? other_msg2 : other_msg1) + Number(other_a_c).toFixed(2) + 
				",占比:" + other_a +"%\'></li>";
		}
		html_3 += 	"</ul></td>" +
			"<td style='width: 15%;text-align:right;font-size:60%'>" + other_t + other_u + "</td>" +
			"</tr></table>"; 
	}
	if(data.length <= 7){
		html_1 += html_3;
	}
	if(data.length >= 8){
		html_2 += html_3;
	}
	tab.children("div").eq(2).children("div").eq(0).html(html_1);
	tab.children("div").eq(2).children("div").eq(1).html(html_2);
}

/**
 *绘制只有一条记录的界面
 */
function render_chart_one(data){
	var tab = regional_obj_tab;
	var other_p = "其他";
	var other_e = "";
	var other_t = "";
	var other_u = "";
	var other_len = "";
	var other_msg1 = "";
	var html_1 = "<table style='width:100%;border-spacing:20px;'>";
	var html_2 = "<table style='width:100%;border-spacing:20px;'>";
	var html_3 = "";
	if(data.length <= 0 ){
		return;
	}
	var i = 0;
	data = render_del_out_data(data);
	while(i < data.length) {
			if(data[i].TOTAL == 0 ){
				i++;
				continue;
			}
			if(data[i].PROVINCE == other_p && data[i].TOTAL > 0){
				other_e = data[i].PERCENT;
				other_t = data[i].TOTAL;
				other_u = data[i].UNIT;
				other_len = data[i].LEN;
				other_msg1 = data[i].MSG_1;
				data.splice(i,1);
				continue;
			}
			if(i < 8){
				html_1 += "<tr style='width: 100%;height: 35px;'>" +
				"<td style='width: 15%;font-size:60%' class='text_elipsis'>" + (i + 1) + "." + data[i].PROVINCE + "</td>" +
				"<td style='width: 70%'>" +
					"<ul class='showTotalMsg' style='width: 100%;height: 35px;text-align: center;line-height: 35px;'>";
				if(isMaxWidth(data[i].LEN)){
					html_1 += "<li class='lileft font_whilte bg_type1 item-info' style='width: " + data[i].LEN  +"%;height: 35px;'>" +
					(data[i].PERCENT == 0 ? "" : data[i].PERCENT + "%") + 
					"<input type='hidden' value=\'" + data[i].MSG_1 + Number(data[i].TOTAL).toFixed(2) + ",占比:" + data[i].PERCENT +"%\'></li>";
				}else{
					html_1 +=  "<li class='lileft font_whilte bg_type1 item-info' style='width: " + data[i].LEN  +"%;height: 35px;'>" +
					"<input type='hidden' value=\'" + data[i].MSG_1 + Number(data[i].TOTAL).toFixed(2) + ",占比:" + data[i].PERCENT +"%\'></li>";
				}
						
				html_1 +="</ul></td>" +
					"<td style='width: 15%;text-align:right;font-size:60%'>" + data[i].TOTAL + data[i].UNIT +  "</td>" +
					"</tr>";
			}
			if(i >= 8){
				html_2 += "<tr style='width: 100%;height: 35px;'>" +
				"<td style='width: 15%;font-size:60%' class='text_elipsis'>" + (i + 1) + "." + data[i].PROVINCE + "</td>" +
				"<td style='width: 70%'>" +
					"<ul class='showTotalMsg' style='width: 100%;height: 35px;text-align: center;line-height: 35px;'>";
				if(isMaxWidth(data[i].LEN)){
					html_2 += "<li class='lileft font_whilte bg_type1 item-info' style='width: " + data[i].LEN  +"%;height: 35px;'>" +
					(data[i].PERCENT == 0 ? "" : data[i].PERCENT + "%") + 
					"<input type='hidden' value=\'" + data[i].MSG_1 + Number(data[i].TOTAL).toFixed(2) + ",占比:" + data[i].PERCENT +"%\'></li>";
				}else{
					html_2 +=  "<li class='lileft font_whilte bg_type1 item-info' style='width: " + data[i].LEN  +"%;height: 35px;'>" +
					"<input type='hidden' value=\'" + data[i].MSG_1 + Number(data[i].TOTAL).toFixed(2) + ",占比:" + data[i].PERCENT +"%\'></li>";
				}
						
				html_2 +="</ul></td>" +
					"<td style='width: 15%;text-align:right;font-size:60%'>" + data[i].TOTAL + data[i].UNIT +  "</td>" +
					"</tr>";
			}
		i++;
	}
	
	
	
	if(other_t != "" && other_t > 0){
		html_3 += "<tr style='width: 100%;height: 35px;'>" +
		"<td style='width: 15%;font-size:60%' class='text_elipsis'>" + (data.length + 1) + "." + other_p + "</td>" +
		"<td style='width: 70%'>" +
			"<ul class='showTotalMsg' style='width: 100%;height: 35px;text-align: center;line-height: 35px;'>";
		if(isMaxWidth(other_len)){
			html_3 += "<li class='lileft font_whilte bg_type1 item-info' style='width: " + other_len  +"%;height: 35px;'>" +
				(other_e == 0 ? "" : other_e + "%") + 
				"<input type='hidden' value=\'" + other_msg1 + Number(other_t).toFixed(2) + ",占比:" + other_e +"%\'></li>";
		}else{
			html_3 += "<li class='lileft font_whilte bg_type1 item-info' style='width: " + other_len  +"%;height: 35px;'>" +
			"<input type='hidden' value=\'" + other_msg1 + Number(other_t).toFixed(2) + ",占比:" + other_e +"%\'></li>";
		}
		html_3 += "</ul></td>" +
		"<td style='width: 15%;text-align:right;font-size:60%'>" + other_t + other_u + "</td>" +
		"</tr></table>";
	}
	if(data.length <= 7){
	html_1 += html_3;
	}
	if(data.length >= 8){
	html_2 += html_3;
	}
	tab.children("div").eq(2).children("div").eq(0).html(html_1);
	tab.children("div").eq(2).children("div").eq(1).html(html_2);
	
}


/**
 *绘制多条记录界面
 */
function render_chart_two(data){
	var tab = regional_obj_tab;
	var other_p = "其他";
	var other_num_100 = "";
	var other_num_500 = "";
	var other_num_1000 = "";
	var other_num_out_1000 = "";
	var other_100 = "";
	var other_500 = "";
	var other_1000 = "";
	var other_out_1000 = "";
	var other_t = "";
	var other_u = "";
	var other_0_100_LEN = 0;
	var other_100_500_LEN = 0;
	var other_500_1000_LEN = 0;
	var other_1000_LEN = 0;
	
	var other_msg1 = "";
	var html_1 = "<table style='width:100%;border-spacing:20px;'>";
	var html_2 = "<table style='width:100%;border-spacing:20px;'>";
	var html_3 = "";
	if(data.length <= 0 ){
		return;
	}
	var i = 0;
	data = render_del_out_data(data);
	while(i < data.length) {
		if(data[i].TOTAL == 0 ){
			i++;
			continue;
		}
		if(data[i].PROVINCE == other_p && data[i].TOTAL > 0){
			other_num_100 = data[i].NUM_0_100;
			other_num_500 = data[i].NUM_100_500;
			other_num_1000 = data[i].NUM_500_1000;
			other_num_out_1000 = data[i].NUM_1000;
			other_100 = data[i].PEN_0_100;
			other_500 = data[i].PEN_100_500;
			other_1000 = data[i].PEN_500_1000;
			other_out_1000 = data[i].PEN_1000;
			other_0_100_LEN = data[i].PEN_0_100_LEN;
			other_100_500_LEN = data[i].PEN_100_500_LEN;
			other_500_1000_LEN = data[i].PEN_500_1000_LEN;
			other_1000_LEN = data[i].PEN_1000_LEN;
			other_t = data[i].TOTAL;
			other_u = data[i].UNIT;
			other_msg1 =data[i].MSG_1;
			data.splice(i,1);
			continue;
		}
		if(i < 8){
			html_1 += "<tr style='width: 100%;height: 35px;'>" +
			"<td style='width: 15%;font-size:60%' class='text_elipsis'>" + (i + 1) + "." + data[i].PROVINCE + "</td>" +
			"<td style='width: 70%'>" +
				"<ul class='showTotalMsg' style='width: 100%;height: 35px;text-align: center;line-height: 35px;'>";
					if(isMaxWidth(data[i].PEN_0_100_LEN)){
						html_1	+= "<li class='lileft font_whilte bg_type1 item-info' style='width: " + data[i].PEN_0_100_LEN  +"%;height: 35px;'>" +
							(data[i].PEN_0_100 == 0 ? "" : data[i].PEN_0_100 + "%") + 
							"<input type='hidden' value=\'" + data[i].MSG_1 + Number(data[i].NUM_0_100).toFixed(2) + ",占比:" + data[i].PEN_0_100 +"%\'></li>";
					}else{
						html_1	+= "<li class='lileft font_whilte bg_type1 item-info' style='width: " + data[i].PEN_0_100_LEN  +"%;height: 35px;'>" +
							"<input type='hidden' value=\'" + data[i].MSG_1 + Number(data[i].NUM_0_100).toFixed(2) + ",占比:" + data[i].PEN_0_100 +"%\'></li>";
					}
					if(isMaxWidth(data[i].PEN_100_500_LEN)){
						html_1	+= "<li class='lileft font_whilte bg_type2 item-info' style='width: " + data[i].PEN_100_500_LEN  +"%;height: 35px;'>" +
							(data[i].PEN_100_500 == 0 ? "" : data[i].PEN_100_500 + "%") + 
							"<input type='hidden' value=\'" + data[i].MSG_1 + Number(data[i].NUM_100_500).toFixed(2) + ",占比:" + data[i].PEN_100_500 +"%\'></li>";
					}else{
						html_1	+= "<li class='lileft font_whilte bg_type2 item-info' style='width: " + data[i].PEN_100_500_LEN  +"%;height: 35px;'>" +
							"<input type='hidden' value=\'" + data[i].MSG_1 + Number(data[i].NUM_100_500).toFixed(2) + ",占比:" + data[i].PEN_100_500 +"%\'></li>";
					}
					if(isMaxWidth(data[i].PEN_500_1000_LEN)){
						html_1	+= "<li class='lileft font_whilte bg_type3 item-info' style='width: " + data[i].PEN_500_1000_LEN  +"%;height: 35px;'>" +
							(data[i].PEN_500_1000 == 0 ? "" : data[i].PEN_500_1000 + "%") + 
							"<input type='hidden' value=\'" + data[i].MSG_1 + Number(data[i].NUM_500_1000).toFixed(2) + ",占比:" + data[i].PEN_500_1000 +"%\'></li>";
					}else{
						html_1	+= "<li class='lileft font_whilte bg_type3 item-info' style='width: " + data[i].PEN_500_1000_LEN  +"%;height: 35px;'>" +
							"<input type='hidden' value=\'" + data[i].MSG_1 + Number(data[i].NUM_500_1000).toFixed(2) + ",占比:" + data[i].PEN_500_1000 +"%\'></li>";
					}
					if(isMaxWidth(data[i].PEN_1000_LEN)){
						html_1	+= "<li class='lileft font_whilte bg_type4 item-info' style='width: " + data[i].PEN_1000_LEN  +"%;height: 35px;'>" +
							(data[i].PEN_1000 == 0 ? "" : data[i].PEN_1000 + "%") + 
							"<input type='hidden' value=\'" + data[i].MSG_1 + Number(data[i].NUM_1000).toFixed(2) + ",占比:" + data[i].PEN_1000 +"%\'></li>";
					}else{
						html_1	+= "<li class='lileft font_whilte bg_type4 item-info' style='width: " + data[i].PEN_1000_LEN  +"%;height: 35px;'>" +
							"<input type='hidden' value=\'" + data[i].MSG_1 + Number(data[i].NUM_1000).toFixed(2) + ",占比:" + data[i].PEN_1000 +"%\'></li>";
					}
			html_1	+= "</ul></td>" +
				"<td style='width: 15%;text-align:right;font-size:60%'>" + data[i].TOTAL + data[i].UNIT + "</td>" +
				"</tr>";
		}
		if(i >= 8){
			html_2 += "<tr style='width: 100%;height: 35px;'>" +
			"<td style='width: 15%;font-size:60%' class='text_elipsis'>" + (i + 1) + "." + data[i].PROVINCE + "</td>" +
			"<td style='width: 70%'>" +
				"<ul class='showTotalMsg' style='width: 100%;height: 35px;text-align: center;line-height: 35px;'>";
			
				if(isMaxWidth(data[i].PEN_0_100_LEN)){
					html_2 += "<li class='lileft font_whilte bg_type1 item-info' style='width: " + data[i].PEN_0_100_LEN  +"%;height: 35px;'>" +
						(data[i].PEN_0_100 == 0 ? "" : data[i].PEN_0_100 + "%") + 
						"<input type='hidden' value=\'" + data[i].MSG_1 + Number(data[i].NUM_0_100).toFixed(2) + ",占比:" + data[i].PEN_0_100 +"%\'></li>";
				}else{
					html_2 += "<li class='lileft font_whilte bg_type1 item-info' style='width: " + data[i].PEN_0_100_LEN  +"%;height: 35px;'>" +
						"<input type='hidden' value=\'" + data[i].MSG_1 + Number(data[i].NUM_0_100).toFixed(2) + ",占比:" + data[i].PEN_0_100 +"%\'></li>";
				}
				if(isMaxWidth(data[i].PEN_100_500_LEN)){
					html_2 += "<li class='lileft font_whilte bg_type2 item-info' style='width: " + data[i].PEN_100_500_LEN  +"%;height: 35px;'>" +
						(data[i].PEN_100_500 == 0 ? "" : data[i].PEN_100_500 + "%") + 
						"<input type='hidden' value=\'" + data[i].MSG_1 + Number(data[i].NUM_100_500).toFixed(2) + ",占比:" + data[i].PEN_100_500 +"%\'></li>";
				}else{
					html_2 += "<li class='lileft font_whilte bg_type2 item-info' style='width: " + data[i].PEN_100_500_LEN  +"%;height: 35px;'>" +
						"<input type='hidden' value=\'" + data[i].MSG_1 + Number(data[i].NUM_100_500).toFixed(2) + ",占比:" + data[i].PEN_100_500 +"%\'></li>";
				}
				if(isMaxWidth(data[i].PEN_500_1000_LEN)){
					html_2 +=  "<li class='lileft font_whilte bg_type3 item-info' style='width: " + data[i].PEN_500_1000_LEN  +"%;height: 35px;'>" +
						(data[i].PEN_500_1000 == 0 ? "" : data[i].PEN_500_1000 + "%") + 
						"<input type='hidden' value=\'" + data[i].MSG_1 + Number(data[i].NUM_500_1000).toFixed(2) + ",占比:" + data[i].PEN_500_1000 +"%\'></li>";
				}else{
					html_2 += "<li class='lileft font_whilte bg_type3 item-info' style='width: " + data[i].PEN_500_1000_LEN  +"%;height: 35px;'>" +
						"<input type='hidden' value=\'" + data[i].MSG_1 + Number(data[i].NUM_500_1000).toFixed(2) + ",占比:" + data[i].PEN_500_1000 +"%\'></li>";
				}
				if(isMaxWidth(data[i].PEN_1000_LEN)){
					html_2 += "<li class='lileft font_whilte bg_type4 item-info' style='width: " + data[i].PEN_1000_LEN  +"%;height: 35px;'>" +
						(data[i].PEN_1000 == 0 ? "" : data[i].PEN_1000 + "%") + 
						"<input type='hidden' value=\'" + data[i].MSG_1 + Number(data[i].NUM_1000).toFixed(2) + ",占比:" + data[i].PEN_1000 +"%\'></li>";
				}else{
					html_2 += "<li class='lileft font_whilte bg_type4 item-info' style='width: " + data[i].PEN_1000_LEN  +"%;height: 35px;'>" +
						"<input type='hidden' value=\'" + data[i].MSG_1 + Number(data[i].NUM_1000).toFixed(2) + ",占比:" + data[i].PEN_1000 +"%\'></li>";
				}
			html_2 += 	"</ul></td>" +
				"<td style='width: 15%;text-align:right;font-size:60%'>" + data[i].TOTAL + data[i].UNIT + "</td>" +
				"</tr>";
		}
		i++;
	}
	/*if(data.length < 8){
		if(other_t != "" && other_t > 0){
			html_1 += "<tr style='width: 100%;height: 35px;'>" +
			"<td style='width: 15%'>" + (data.length + 1)  + "." + other_p + "</td>" +
			"<td style='width: 70%'>" +
				"<ul class='showTotalMsg' style='width: 100%;height: 35px;text-align: center;line-height: 35px;'>";
			
			if(isMaxWidth(other_100)){
				html_1 += "<li class='lileft font_whilte bg_type1 item-info' style='width: " + other_100  +"%;height: 35px;'>" +
					(other_100 == 0 ? "" : other_100 + "%") + 
				"<input type='hidden' value=\'" + other_msg1 + other_num_100 + ",占比:" + other_100 +"%\'></li>";
			}else{
				html_1 += "<li class='lileft font_whilte bg_type1 item-info' style='width: " + other_100  +"%;height: 35px;'>" +
				"<input type='hidden' value=\'" + other_msg1 + other_num_100 + ",占比:" + other_100 +"%\'></li>";
			}
			
			if(isMaxWidth(other_500)){
				html_1 += "<li class='lileft font_whilte bg_type2 item-info' style='width: " + other_500  +"%;height: 35px;'>" +
				(other_500 == 0 ? "" : other_500 + "%") + 
				"<input type='hidden' value=\'" + other_msg1 + other_num_500 + ",占比:" + other_500 +"%\'></li>";
			}else{
				html_1 += "<li class='lileft font_whilte bg_type2 item-info' style='width: " + other_500  +"%;height: 35px;'>" +
				"<input type='hidden' value=\'" + other_msg1 + other_num_500 + ",占比:" + other_500 +"%\'></li>";
			}
			
			if(isMaxWidth(other_1000)){
				html_1 += "<li class='lileft font_whilte bg_type2 item-info' style='width: " + other_1000  +"%;height: 35px;'>" +
				(other_1000 == 0 ? "" : other_1000 + "%") + 
				"<input type='hidden' value=\'" + other_msg1 + other_num_1000 + ",占比:" + other_1000 +"%\'></li>";
			}else{
				html_1 += "<li class='lileft font_whilte bg_type2 item-info' style='width: " + other_1000  +"%;height: 35px;'>" +
				(other_1000 == 0 ? "" : other_1000 + "%") + 
				"<input type='hidden' value=\'" + other_msg1 + other_num_1000 + ",占比:" + other_1000 +"%\'></li>";
			}
			
			if(isMaxWidth(other_out_1000)){
				html_1 += "<li class='lileft font_whilte bg_type2 item-info' style='width: " + other_out_1000  +"%;height: 35px;'>" +
					(other_out_1000 == 0 ? "" : other_out_1000 + "%") + 
					"<input type='hidden' value=\'" + other_msg1 + other_num_out_1000 + ",占比:" + other_out_1000 +"%\'></li>";
			}else{
				html_1 += "<li class='lileft font_whilte bg_type2 item-info' style='width: " + other_out_1000  +"%;height: 35px;'>" +
					"<input type='hidden' value=\'" + other_msg1 + other_num_out_1000 + ",占比:" + other_out_1000 +"%\'></li>";
			}
			
			html_1 +=	"</ul></td>" +
				"<td style='width: 15%;text-align:right;font-size:60%'>" + other_t + other_u + "</td>" +
				"</tr></table>";
		}
		tab.children("div").eq(2).children("div").eq(0).html(html_1);
	}*/
		if(other_t != "" && other_t > 0){
			html_3 += "<tr style='width: 100%;height: 35px;'>" +
			"<td style='width: 15%;font-size:60%'>" + (data.length + 1)  + "." + other_p + "</td>" +
			"<td style='width: 70%'>" +
				"<ul class='showTotalMsg' style='width: 100%;height: 35px;text-align: center;line-height: 35px;'>";
			
			if(isMaxWidth(other_0_100_LEN)){
				html_3 += "<li class='lileft font_whilte bg_type1 item-info' style='width: " + other_0_100_LEN  +"%;height: 35px;'>" +
					(other_100 == 0 ? "" : other_100 + "%") + 
					"<input type='hidden' value=\'" + other_msg1 + Number(other_num_100).toFixed(2) + ",占比:" + other_100 +"%\'></li>";
			}else{
				html_3 += "<li class='lileft font_whilte bg_type1 item-info' style='width: " + other_0_100_LEN  +"%;height: 35px;'>" +
					"<input type='hidden' value=\'" + other_msg1 + Number(other_num_100).toFixed(2) + ",占比:" + other_100 +"%\'></li>";
			}
			
			if(isMaxWidth(other_100_500_LEN)){
				html_3 += "<li class='lileft font_whilte bg_type2 item-info' style='width: " + other_100_500_LEN  +"%;height: 35px;'>" +
					(other_500 == 0 ? "" : other_500 + "%") + 
					"<input type='hidden' value=\'" + other_msg1 + Number(other_num_500).toFixed(2) + ",占比:" + other_500 +"%\'></li>";
			}else{
				html_3 += "<li class='lileft font_whilte bg_type2 item-info' style='width: " + other_100_500_LEN  +"%;height: 35px;'>" +
					"<input type='hidden' value=\'" + other_msg1 + Number(other_num_500).toFixed(2) + ",占比:" + other_500 +"%\'></li>";
			}
			
			if(isMaxWidth(other_500_1000_LEN)){
				html_3 +=  "<li class='lileft font_whilte bg_type3 item-info' style='width: " + other_500_1000_LEN  +"%;height: 35px;'>" +
					(other_1000 == 0 ? "" : other_1000 + "%") + 
					"<input type='hidden' value=\'" + other_msg1 + Number(other_num_1000).toFixed(2) + ",占比:" + other_1000 +"%\'></li>";
			}else{
				html_3 += "<li class='lileft font_whilte bg_type3 item-info' style='width: " + other_500_1000_LEN  +"%;height: 35px;'>" +
					"<input type='hidden' value=\'" + other_msg1 + Number(other_num_1000).toFixed(2) + ",占比:" + other_1000 +"%\'></li>";
			}
			
			if(isMaxWidth(other_1000_LEN)){
				html_3 += "<li class='lileft font_whilte bg_type4 item-info' style='width: " + other_1000_LEN  +"%;height: 35px;'>" +
					"<input type='hidden' value=\'" + other_msg1 + Number(other_num_out_1000).toFixed(2) + ",占比:" + other_out_1000 +"%\'></li>";
			}else{
				html_3 += "<li class='lileft font_whilte bg_type4 item-info' style='width: " + other_1000_LEN  +"%;height: 35px;'>" +
					"<input type='hidden' value=\'" + other_msg1 + Number(other_num_out_1000).toFixed(2) + ",占比:" + other_out_1000 +"%\'></li>";
			}
			
			html_3 +=	"</ul></td>" +
				"<td style='width: 15%;text-align:right;font-size:60%'>" + other_t + other_u + "</td>" +
				"</tr></table>";
	}
	if(data.length <= 7){
		html_1 += html_3;
	}
	if(data.length >= 8){
		html_2 += html_3;
	}
		
	tab.children("div").eq(2).children("div").eq(0).html(html_1);
	tab.children("div").eq(2).children("div").eq(1).html(html_2);
}



function render_del_out_data(data){
	var temp = data.length;
	if(temp > 16){
		data = data.splice(0,16);
	}
	return data;
}

/**
 * 判断值是否需要显示
 */
function isMaxWidth(arg1){
	if(Number(arg1) >= 12){
		return true;
	}
	if(Number(arg1) < 12){
		return false;
	}
}


function bindMessage(){
	var msg_report = $("#msg_report");
	var msg_report_pos = null;
	$(".item-info").on({
		mouseenter:function(e){
			e.stopPropagation();
			msg_report_pos = msg_report.position();
			var position = $(this).position();
			msg_report.css({left:position.left + 210 ,top:position.top + 108});
			var html = $(this).children("input:hidden").val();
			msg_report.html(html);
			msg_report.removeClass("none").addClass("block");
		},
		mouseleave:function(e){
			e.stopPropagation();
			msg_report.css({left:msg_report_pos.left,top:msg_report_pos.top});
			msg_report.html('');
			msg_report.removeClass("block").addClass("none");
		}
	});
}

/**
 * 加载tab标签
 */
var getFram_Tab = {
		_init:function(obj_tab,obj_render,url,obj_hide){
			getFram_Tab._obj = obj_tab;
			getFram_Tab._obj_render = obj_render;
			//getFram_Tab._url = url;
			getFram_Tab._obj_hide = obj_hide;
			$.ajax({
				type:"GET",
				url: "admin!getFram_Tab.action",
				cache: false,
				success: function(data){
					var res = (new Function("","return " + data))();
					getFram_Tab._renderTab(res,obj_tab,url);
				}
			});
		},
		_renderTab:function(res,obj,url){
			var html = "";
			var html_render = '<iframe  class="div-main-4 '+
				  'block " width="100%" height="100%" '+ 
				  'src="" scrolling="auto"   marginwidth="0" marginheight="0"> ' +
				  '</iframe>';
			
			for ( var it in res) {
				html += '<li  class="lileft  h30 lh30 fontStyle '+ 
						'font120 pd_rAndl_30 margin_right '+ 
						'border_lrt mouser_cursor" '+ 
						'"><span>' + res[it].NAME +'</span> ' +
						'<input type="hidden" '+ 
						'value='+ res[it].ID +' /> ' +
						'<input type="hidden" '+
						'value='+ url + '  />' +
						'</li>';
			}
			obj.html(html);
			
			for (var i = 0; i < res.length - 1; i++) {
				//html_render += '<div class="div-main-1 none pd_bottom50"></div>';
				html_render +='<iframe  class="div-main-4 '+
				  'block " width="100%" height="100%" '+ 
				  'src="" scrolling="auto"   marginwidth="0" marginheight="0"> ' +
				  '</iframe>';
			}
			getFram_Tab._obj_render.html(html_render);
			getFram_Tab._event();
		},
		_event:function(){
			var obj = getFram_Tab._obj;
			var obj_render = getFram_Tab._obj_render;
			obj.children("li").click(function(){
				$(this).addClass("bgwhite").removeClass("bggray");
				$(this).siblings().addClass("bggray").removeClass("bgwhite");
				var val = $(this).children("input").eq(0).val();	//获取农场的ID
				//getFram_Tab._obj_hide.val(val); //设置选中农场的 ID
				$(this).parent().next("input[type='hidden']").val(val);
				var child_num_obj = obj_render.children("iframe").eq($(this).index());
				if($.trim(child_num_obj.contents().find("body").html()).length == 0){
					child_num_obj.addClass("block").removeClass("none");
					child_num_obj.siblings().addClass("none").removeClass("block");
					var url = $(this).children("input").eq(1).val();
					child_num_obj.attr("src",url + val);
				}else{
					child_num_obj.addClass("block").removeClass("none");
					child_num_obj.siblings().addClass("none").removeClass("block");
				}
				
			});
			obj.children("li").eq(0).trigger("click");
		}
};

/**
 * hashmap
 */
var hashMap = {
	/*Set:function(key,value){
		this[key] = value;
	},
	Get:function(key){
		return this[key];
	},
	Contains:function(key){
		return this.Get(key) == null ? false:true;
	},
	Remove:function(key){
		delete this[key];
	},
	RemoveAll:function(){
		for ( var i in this) {
			if(i != "Set" && i != "Get" && i != "Contains" &&
					i != "Remove" && i != "RemoveAll"){
				console.log(this[i]);
			}
		}
	}*/
	getObj:function(key,value){
		var temp = new Object();
		temp.DATE = key;
		temp.TIME = value;
		return temp;
	}
};


String.prototype.toBitSymbol = function(){
	var arr = this.toString().split(".");
	if(arr.length <= 2){
		var integer = "";
		var decimal = 'undefined' == typeof arr[1] ? "" : "." + arr[1] ;
		var temp = arr[0].split("").reverse();
		for(var i = 0 ; i < temp.length ; i++ ){
			integer += temp[i] + ((i + 1) % 3 == 0 && (i + 1) != temp.length ? "，":"");
		}
		return integer.split("").reverse().join("") + decimal;
	}
};

(function(jQuery){
	$.tipMsg = function(h,w,title,info){
		var tip = $(document.getElementById("my_tip"));
		if(h == 0 || 'undefined' == typeof(h) || isNaN(h)){
			h = 150;
			hpx = 150 + "px";
		}else{
			h = h;
			hpx = h + "px";
		}
		if(w == 0 || 'undefined' == typeof(w) || isNaN(w)){
			w = 250;
		}
		if('undefined' == typeof(title)){
			title = "系统提示信息:";
		}
		if('undefined' == typeof(info)){
			info = "提示信息";
		}
		tip.width(w);
		$(".tip-title",tip).html(title);
		$(".tip-body",tip).html(info);
		
		function tip_show(){
			if(tip.height() == 0){
				tip.animate({height:hpx},"normal");
			}else{
				tip.stop();
				tip.height(Number(h));
			}
		}
		tip_show();
		$(".tip-close",tip).off("click").on("click",function(){
			if(tip.height() == Number(h)){
				tip.animate({height:"0px"},"normal");
			}else{
				tip.stop();
				tip.height(0);
			}
		});
	}
})(jQuery);

//关闭顶部tab页
function closeIframe(){
	
	var xx = $(window.parent.document).find(".ui-tabs-nav > li.ui-state-active > div > span.tabs-close").length;
	if(xx == 1)
	{
	      $(window.parent.document).find(".ui-tabs-nav > li.ui-state-active > div > span.tabs-close").click();
	
	}
	else
	{
		 $(window.parent.parent.document).find(".ui-tabs-nav > li.ui-state-active > div > span.tabs-close").click();
	}
	
		//$(window.top.document).find(".ui-tabs-nav > li.ui-state-active > div > span.tabs-close").click();
}


//每次清空输入框
var clean_input_for_dailog = function(obj){
	var input = $(obj).find("input[type='text']");
	var select = $(obj).find("select");
	var textarea = $(obj).find("textarea");
	input.each(function(i,o){
		$(o).val("");
	});
	select.each(function(i,o){
		$(o).get(0).selectedIndex = 1;
	});
	textarea.each(function(i,o){
		$(o).val("");
	});
};

//显示流程图
function showProcess_sig(div_id,title,processId,orderId,orderIdStatus,width,height){
	  var dlgParent = $("#"+div_id+"").parent();
      var dlgOwn = $("#"+div_id+"").clone();
	 $.ajax({
         type:"get",
         data:{processId:processId,orderId:orderId,orderIdStatus:orderIdStatus,back:0},
         async:false,
         url:"process!designer.action",
         success:function(result) {
        	 $("#"+div_id+"").html(result);
         }
     });
	
	
	 $("#"+div_id+"").dialog({
		position: ['center','top'],
		draggable :"false",     
		autoOpen: "true",
		height:height,
		width:width,
		show: "slide",
		modal:"true",
		cache:"false",
		/* hide: "explode", */
		title:title,
        close : function() 
        {
        	dlgOwn.appendTo(dlgParent);
        	$(this).remove();
        }
	 });
}


function bakIframe(label,bakurl)
{
  if(label!='' && label.length>0)
  {
     window.location.href = bakurl;
  }
  else
  {
     closeIframe();
  }
}

//流程详情
function createHtml(result) {
	var data = eval("(" + result + ")");
	var result = "";
	if (data != "") {
		result += '<table cellpadding="0" cellspacing="0" class="table-y">'
				+ '<tr>'
				+ '<td colspan="6" style="font-size:12px;text-align:center;" bgcolor="#dddddd">流程详情</td>'
				+ '</tr>';
		result += '<tr>'
				+ '<td style="width:150px;font-size:12px;font-weight: bold;text-align:center">审批环节</td>'
				+ '<td style="width:100px;font-size:12px;font-weight: bold;text-align:center">处理人</td>'
				+ '<td style="width:200px;font-size:12px;font-weight: bold;text-align:center">职务</td>'
				+ '<td style="width:100px;font-size:12px;font-weight: bold;text-align:center">处理意见</td>'
				+ '<td style="width:150px;font-size:12px;font-weight: bold;text-align:center">登录ip</td>'
				+ '<td style="width:100px;font-size:12px;font-weight: bold;text-align:center">审批时间</td>'
				+ '</tr>';
		for ( var i in data) {
			if (data[i].status != 'end') {
				result += '<tr>'
						+ '<td style="width:150px;font-size:12px;text-align:center">'
						+ data[i].DISPLAY_NAME
						+ '</td>'
						+ '<td style="width:150px;font-size:12px;text-align:center">'
						+ data[i].TRUENAME
						+ '</td>'
						+ '<td style="width:150px;font-size:12px;text-align:center">'
						+ data[i].ORG_INFOR
						+ '</td>';
						if (data[i].MARK == undefined) 
						{
							result += '<td style="width:100px;font-size:12px;text-align:center;color:blue;">无</td>';
						} 								
						else if(data[i].MARK == '-1')
						{
						   result += '<td style="width:100px;font-size:12px;text-align:center;color:red;">不同意</td>';
						}
						else
						{
						   result += '<td style="width:100px;font-size:12px;text-align:center;color:blue;">同意</td>';
						}
						if (data[i].IP == undefined) 
						{
							result += '<td style="width:100px;font-size:12px;text-align:center;color:red;">无</td>';
						} 
						else 
						{
                            result += '<td style="width:150px;font-size:12px;text-align:center">'+data[i].IP+'</td>';
						}
						if (data[i].FINISH_TIME == undefined) 
						{
							result += '<td style="width:200px;font-size:12px;text-align:center;color:red;">未处理</td>'
						} else 
						{
							result += '<td style="width:200px;font-size:12px;text-align:center;">'
									+ data[i].FINISH_TIME + '</td>'
						}
						result += '</tr>';
					} 
					else 
					{
						result += '<tr>'
								+ '<td colspan="6" style="font-size:12px;text-align:center;" bgcolor="#dddddd">流程结束</td>'
								+ '</tr>';
					}
				}
				result += '</table>';
	} else {
		result = '<span style="font-size:14px;color:red">数据出错，未查到相应流程！</span>';
	}
	return result;
};
