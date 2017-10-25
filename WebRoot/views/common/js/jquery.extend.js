/**
 * 扩展jquery
 * 
 */

(function($){
	//指定本框架中validate插件提示信息的方式
	$.extend($.fn.validate.defaults,{
		showMsg : function(msg){
			showValidateErrorMsg(msg);
		}
	});
	
	/*调用方法 
	var text = "a{0}b{0}c{1}d\nqq{0}"; 
	var text2 = $.format(text, 1, 2); 
	alert(text2); 
	 */
	$.extend({format : function(source, params){
		if (arguments.length == 1) 
			return function () { 
			var args = $.makeArray(arguments); 
			args.unshift(source); 
			return $.format.apply(this, args); 
		}; 
		if (arguments.length > 2 && params.constructor != Array) { 
			params = $.makeArray(arguments).slice(1); 
		} 
		if (params.constructor != Array) { 
			params = [params]; 
		} 
		$.each(params, function (i, n) { 
			source = source.replace(new RegExp("\\{" + i + "\\}", "g"), n); 
		}); 
		return source; 
	}});
	
	
	/**
	 * 将表单或者任务父容器以及父容器集合的form元素值转换为JSON对象
	 */
	$.fn.extend({serializeObject : function(){
		var o = {};
		var that = $(this);
		var a = that.serializeArray();
		$.each(a, function() {
			if (o[this.name] !== undefined) {
				if (!o[this.name].push) {
					o[this.name] = [ o[this.name] ];
				}
				o[this.name].push(this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		});
		return o;
	}});
	
})(jQuery);
