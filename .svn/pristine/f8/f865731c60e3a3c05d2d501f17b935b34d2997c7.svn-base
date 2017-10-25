/**
 * Validation for jQuery
 * Copyright (c) 2012  tohot Co., Ltd. All rights reserved
 * 
 * 代码依赖：
 * $.extend({format : function(source, params){
 *		if (arguments.length == 1) 
 *			return function () { 
 *			var args = $.makeArray(arguments); 
 *			args.unshift(source); 
 *			return $.format.apply(this, args); 
 *		}; 
 *		if (arguments.length > 2 && params.constructor != Array) { 
 *			params = $.makeArray(arguments).slice(1); 
 *		} 
 *		if (params.constructor != Array) { 
 *			params = [params]; 
 *		} 
 *		$.each(params, function (i, n) { 
 *			source = source.replace(new RegExp("\\{" + i + "\\}", "g"), n); 
 *		}); 
 *		return source; 
 *	}});
 *
 *
 * @author zhushukai,city  2012-07-14
 * @version v1.0
 */
;(function($){
	$.fn.extend({
		"validate" : function(action,headInfo){
			action = action || "default";
			var options = $.extend({},$.fn.validate.defaults,action);
			var errorClass = 'jquery-validate-error'; 
			if (typeof action == "string") {
				switch (action) {
				case "default":
					this.each(function(){
						//若选择元素为:input集合
						if($(this).is(':input[valid]')){					
							validate($(this));
							return options.isValid;
						}
						//若选择元素为父容器集合
						$(':input[valid]',$(this)).each(function(){
							validate($(this));
							return options.isValid;
						});
						return options.isValid;
					});
					return options.isValid;
				case "gaofeng":
					this.each(function(){
						//若选择元素为:input集合
						if($(this).is(':input[valid]')){
							validate1($(this),headInfo);
							return options.isValid;
						}
						//若选择元素为父容器集合
						$(':input[valid]',$(this)).each(function(){
							validate1($(this),"");
							return options.isValid;
						});
						return options.isValid;
					});
					return options.isValid;
				}
			}
			
			/**
			 * 验证核心函数
			 * @param $input
			 * @returns {Boolean}
			 */
			function validate($input){
				var ruleArr = $input.attr('valid').split(',');
				var rule = ruleArr.shift();
				var engine = options.engines[rule];	
				if(!engine.validator($input.val(),ruleArr)){
					options.isValid = false;
					$input.addClass(errorClass);
					$input.unbind('click.validate');
					$input.one('click.validate',function(){
						$input.removeClass(errorClass);
					});
					options.showMsg($.format(engine.message,ruleArr));
				} else {
					$input.removeClass(errorClass);
				}
			}
			
			function validate1($input,headInfo){
				var ruleArr = $input.attr('valid').split(',');
				var rule = ruleArr.shift();
				var engine = options.engines[rule];	
				if(!engine.validator($input.val(),ruleArr)){
					options.isValid = false;
					$input.addClass(errorClass);
					$input.unbind('click.validate');
					$input.one('click.validate',function(){
						$input.removeClass(errorClass);
					});
					options.showMsg1($.format(engine.message,ruleArr),headInfo);
				} else {
					$input.removeClass(errorClass);
				}
			}
			
			
			return this;
		}
		
	});

	/**
	 * 初始参数,可以后续扩展
	 */
	$.fn.validate.defaults = {
			isValid : true,
			//默认为alert，可以复写$('#div').validate({showMsg : function(){...}}).validate();
			showMsg : function(msg){
				alert(msg);
			},
			showMsg1 : function(msg,html){
				alert(msg+"("+html+")");
			},
			engines : {
				required : {
					validator : function(value) {
						return value != "";
					},
					message : "字段不能为空！"
				},
				email : {
					validator : function(value) {
						return /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i
								.test(value);
					},
					message : "请输入一个合法的email字符串！"
				},
				url : {
					validator : function(value) {
						return /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i
						                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         .test(value);
					},
					message : "请输入一个合法的URL字符串！"
				},
				length : {
					validator : function(value, lengthArr) {
						var len = $.trim(value).length;
						return len >= lengthArr[0] && len <= lengthArr[1];
					},
					message : "请输入一个长度在4到12之间字符串！"
				},
				username:{
					validator : function(value) {
						return /^[a-zA-Z0-9]{6,20}$/.test(value);
					},
					message : "请输入6~20英文字母或者数字"
				},
				uname:{
					validator : function(value){
						return /^[a-zA-z0-9][a-zA-Z0-9_]{3,11}$/.test(value);
					},
					message : "账号以字母开头，允许4-12位字符，允许字母数字下划线"
				},
				tel:{
					validator : function(value) {
						return /^((d{3,4})|d{3,4}-)?d{7,8}$/.test(value);
					},
					message : "请输入正确的电话号码！"
				},
				postcode:{
					validator : function(value) {
						return /^[0-9]{6}$/.test(value);
					},
					message : "请输入正确的邮编号码！"
				},
				colName:{
					validator : function(value) {
						return /^\w{1,30}$/.test(value);
					},
					message : "请输入合法的字段或表名称！"
				},
				colType:{
					validator : function(value) {
						return /^\w{1,30}$/.test(value);
					},
					message : "请输入合法的字段名称！"
				},
				number:{//非负浮点数
					validator : function(value) {
						return /^\d*\.{0,1}\d+$/.test(value);
					},
					message : "请输入正确的邮编号码！"
				},
				ip:{
					validator:function(value){
						return /^\d+.\d+.\d+.\d+$/.test(value);
						//if(reg.test(value)){
						//	return RegExp.$1*Math.pow(255,3)+RegExp.$2*Math.pow(255,2)+RegExp.$3*255+RegExp.$4*1;
						//}else{
						//	return false;
						//}

					},
					message:"请输入正确的ip地址！"
				},
				integer:{
					validator:function(value){
						return /^\d{1,10}$/.test(value);
					},
					message:"请输入一个整数"
				},
				rangeInt:{
					validator:function(value){
						return /^([1-9]{1,2}|100)$/.test(value);
					},
					message:"请输入1-100之间的整数"
				},
				integer1:{
					validator:function(value){
						return /^[1-9]\d*$/.test(value);
					},
					message:"请输入一个大于0的整数"
				},
				ips : {
					validator : function(value) {
						var reg = /^([0,1]?\d{0,2}|2[0-4]\d|25[0-5])\.([0,1]?\d{0,2}|2[0-4]\d|25[0-5])\.([0,1]?\d{0,2}|2[0-4]\d|25[0-5])\.([0,1]?\d{0,2}|2[0-4]\d|25[0-5])$/;
						if (value.indexOf(",") > 0) {
							var ips = value.split(",");
							for (var i in ips) {
								if(!reg.test(ips[i])) {
									return false;
								}
							}
						} else {
							return reg.test(value);
						}
						return true;
					},
					message : "请输入一组正确的ip地址"
				},
				decimal:{
					validator:function(value){
						return /^\d{1,10}(\.\d{1,2})?$/.test(value);
					},
					message:"请输入正数(小数最多包含2位)"
				},
				password : {
			        validator: function (value) {
			            return value.length>=8 && /[0-9]+/.test(value)
			            && /[a-zA-Z]+/.test(value) && /[^0-9a-zA-Z]/.test(value);
			        },
			        message: '密码必须8位以上且包含数字，字母和特殊字符串！'
			    },
				costNo:{
					validator:function(value,param){
						var flag = false;
						var strs= new Array();
						strs=value.split("-"); 
						if(strs.length==4){//根据- 可以分成5段
							if("KK"==strs[0]){//ZQ开头且第二段符合条件
								if(strs[1].length==4&&isInt(strs[1],4)
										&&strs[2].length==2&&strs[2]<13 &&strs[2]>0&&isInt(strs[2], 2)
										&&strs[3].length==3&&isInt(strs[3],3)){
									flag = true;
								}
							}
						}
						return flag;
					},
					message:'请输入正确格式，例如：KK-2014-11-001'
				},
				decimal1:{
					validator:function(value){
						return value.trim() == "" ? true : /^\-?\d{1,10}(\.\d+)?$/.test(value);
					},
					message:"请输入数字"
				},
				phone:{
					validator:function(value){
						return /^1[3|4|5|6|7|8]{1}[0-9]{9}$/.test(value);
					},
					message:"手机号码格式不正确"
				},
				carno:{
					validator:function(value){
						return /^[A-Z0-9]{5}$/.test(value);
					},
					message:"车牌号码格式不符合(必须为数字大写字母组成的5位字符串)"
				},
				carno_condition:{
					validator:function(value){
						if(value.trim() == ""){
							return true;
						}else{
							return /^[A-Z0-9]{5}$/.test(value);
						}
					},
					message:"车牌号码格式不符合(必须为数字大写字母组成的5位字符串)"
				}
			}
	};
	//判断是否为非负整数
	function isInt(str,length){
		var flag = false;
		if(str.length == length){
			var r = /^\d+$/;
			flag = r.test(str);
		}
		return flag;
	}
})(jQuery);