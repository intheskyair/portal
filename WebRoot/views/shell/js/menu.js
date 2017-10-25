//菜单插件
(function($){
	$.serialze_tree = function(data){
		var top_area = $("#top_menus");
		var mytree = $("#mytree");
		var top_trees = [];
		var other_trees = [];
		var html = "";
		//获取顶级菜单，和其他菜单
		$(data).each(function(i,it){
			if( 'undefined' == typeof(it.pId) ){
				top_trees.push(it);
			}else{
				other_trees.push(it);
			}
		});
		for (var i = 0; i < top_trees.length; i++) {
			if(i == 0){
				html += "<li tip='" + top_trees[i].id + "' class='fn-left top_li active_top_li'>" +
						 	top_trees[i].name +
						"</li>"
			}
			if(i > 0){
				html += "<li tip='" + top_trees[i].id + "' class='fn-left top_li'>" +
							top_trees[i].name +
						"</li>"
			}
		}
		top_area.html(html);
		//展示二级菜单
		function show_other_menus(top_id){
			var html = "";
			for (var i = 0; i < other_trees.length; i++) {
				if(Number(other_trees[i].pId) == Number(top_id) ){
					if('undefined' == typeof(other_trees[i].href)){
						html += "<li tip=" + other_trees[i].id +" class='mytreeLi mytreeLi" + other_trees[i].id + "' > " +
									"<span class='treeTip w_thrity has_icon'>" +
										"<div class='plus_icon'></div>" +
									"</span>" + 
						  			"<span class='treeTip w_seventy wi-align-l'>" + other_trees[i].name +
						  			"</span>" +
						  		"</li>";
					}else{
						html += "<li tip=" + other_trees[i].id +" class='mytreeLi mytreeLi" + other_trees[i].id + "' > " +
									"<span class='treeTip w_thrity has_icon'>" + 
										"<div class=''></div>" +
									"</span>" +
						  			"<span class='treeTip w_seventy wi-align-l'>" + other_trees[i].name +
						  			"</span>" +
			  					"</li>";
					}	
				}
			}
			mytree.html(html);
		}
		
		
		//一级菜单添加点击事件
		$(document).off("click","#top_menus li")
			.on("click","#top_menus li",function(){
				var top_id = $(this).attr("tip");
				$(this).addClass("active_top_li");
				$(this).siblings().removeClass("active_top_li");
				show_other_menus(top_id);
			});
		//二级菜单添加点击事件
		$(document).off("click","#mytree li")
			.on("click","#mytree li",function(){
				var id = $(this).attr("tip");
				$(this).addClass("active_menu");
				$(this).siblings().removeClass("active_menu");
				getOthers(id,$(this));
			});
		
		//递归三级和三级以下菜单
		function getOthers(id,serf){
			for (var i = 0; i < other_trees.length; i++) {
				if(Number(id) == Number(other_trees[i].id) ){
					var select_objs = $("." + id + "level0");
					if(other_trees[i].crosswise == "1"){
						/*var option = {};
						option.key = other_trees[i].id;
						option.title = other_trees[i].name;
						option.url = "../views/shell/crosswise_tab.jsp";
						option.isIframe = other_trees[i].isIframe?true:false;
						//option.style = treeNode.isIframe?"width:100%;height:100%":"";
						addTab(option);*/
						openUrl(other_trees[i]);
						break;
					}else{
						if('undefined' == typeof(other_trees[i].href)){
							if(select_objs.length == 0){
								var html = "";
								for (var j = 0; j < other_trees.length; j++) {
									
									if( Number(id) ==  Number(other_trees[j].pId)){
										var className = id + "level0";
										if('undefined' == typeof(other_trees[j].href)){
											html += "<li tip=" + other_trees[j].id +" class='" + 
														className +" none mytreeLi othertreeLi' >" +
														"<span class='treeTip w_thrity has_icon'>" +
															"<div class='plus_icon'></div>" +
														"</span>" + 
											  			"<span class='treeTip w_seventy wi-align-l'>" + other_trees[j].name +
											  			"</span>" +
											  		"</li>";
										}else{
											html += "<li tip=" + other_trees[j].id +" class='" + 
														className +" none mytreeLi othertreeLi' >" +
														"<span class='treeTip w_thrity has_icon'>" +
															"<div ></div>" +
														"</span>" + 
											  			"<span class='treeTip w_seventy wi-align-l'>" + other_trees[j].name +
											  			"</span>" +
											  		"</li>";
										}
									}
								}
								
								serf.after(html);
								$("." + id + "level0").slideToggle(500);
								if($(".plus_icon",serf).length){
									$(".plus_icon",serf).removeClass("plus_icon").addClass("minus_icon");
								}
								hiddenOtherMenu(id);
								break;
							}else{
								select_objs.slideToggle(500);
								if($(".plus_icon",serf).length){
									$(".plus_icon",serf).removeClass("plus_icon").addClass("minus_icon");
								}else{
									$(".minus_icon",serf).removeClass("minus_icon").addClass("plus_icon");
								}
								hiddenOtherMenu(id);
								break;
							}
						}else{
							openUrl(other_trees[i]);
							break;
						}
					}
				}
			}
		}
		
		//打开URL
		function openUrl(treeNode){
			if(treeNode.crosswise == "1"){
				var option = {};
				option.key = treeNode.id;
				option.title = treeNode.name;
				option.url = "../cross!toCrosswiseTab.action?key="+treeNode.id;//views/shell/crosswise_tab.jsp
				option.isIframe = true;
				//option.style = treeNode.isIframe?"width:100%;height:100%":"";
				addTab(option);
			}else{
				if( $.trim(treeNode.href) !="" )
				{
					var option1 = {};
					option1.key = treeNode.id;
					option1.title = treeNode.name;
					option1.url = "../" + treeNode.href;
					option1.isIframe = treeNode.isIframe?true:false;
					//option.style = treeNode.isIframe?"width:100%;height:100%":"";
					addTab(option1);
				}
			}
		}
		//收起当前点击的二级菜单意外的二级菜单
		function hiddenOtherMenu(id){
			for(var i = 0;i < other_trees.length; i++){
				if('undefined' == typeof(other_trees[i].href) && other_trees[i].id != id && $("." + other_trees[i].id + "level0").length != 0){
					//alert(other_trees[i].name);
					//alert($("." + other_trees[i].id + "level0").length);
					//alert($("li[tip=" + other_trees[i].id + "]").html());
					if($(".minus_icon",$("li[tip=" + other_trees[i].id + "]")).length != 0){
						$("." + other_trees[i].id + "level0").slideToggle(500);
						$(".minus_icon",$("li[tip=" + other_trees[i].id + "]")).removeClass("minus_icon").addClass("plus_icon");
					}
					//alert($(".plus_icon",$("." + other_trees[i].id + "level0")).length);
					/*if($(".plus_icon",$("." + other_trees[i].id + "level0")).length){
						$(".plus_icon",$("." + other_trees[i].id + "level0")).removeClass("plus_icon").addClass("minus_icon");
					}else{
						$(".minus_icon",$("." + other_trees[i].id + "level0")).removeClass("minus_icon").addClass("plus_icon");
					}*/
				}
			}
		}
		
		//开始触发第一菜单
		$("#top_menus li").eq(0).trigger("click");
	}
})(jQuery);


var menuSetting = {
	view: {
		dblClickExpand: true,
		showLine: false,
		/*showIcon: false,*/
		selectedMulti: false,
		expandSpeed: ($.browser.msie && parseInt($.browser.version)<=6)?"":"fast"
	},
	check: {
		enable: false
	},
	data: {
		simpleData: {
			enable: true,
			idKey: "id",
			pIdKey: "pId",
		}
	},
	callback:{
		onClick: function(event, treeId, treeNode, clickFlag){
			if( $.trim(treeNode.href) !="" )
			{
				var option = {};
				option.key = treeNode.id;
				option.title = treeNode.name;
				option.url = "../" + treeNode.href;
				option.isIframe = treeNode.isIframe?true:false;
				//option.style = treeNode.isIframe?"width:100%;height:100%":"";
				addTab(option);
			}
		},
	}
};
var mytree = null;
/**
 * author skylen
 */	
$(document).ready(function(){
	$.ajax({
		   type: "GET",
		   url: "getMenu!getMenu.action?mk="+$("#mk_menu").val(),
		   cache: false,
		   success: function(data){
			    data = (new Function("","return " + data))();
			    
			    //console.log(data);
			    $.serialze_tree(data);
			    
				/*$(data).each(function(i,item){
					$('<li><a href="#" hidefocus="true" title="'+item.name+'" id="menu_'+item.id+'">'+item.name+'</a></li>').appendTo("#head_menu");
					$("#container").append('<ul id="tree_'+item.id+'" class="ztree mytree"></ul>');
					$.fn.zTree.init($("#tree_"+item.id), menuSetting, item.child);
				});*/
				/*$("#head_menu li").on("click",function(){
					$("#head_menu li").removeClass("on");
					$("#container .mytree").removeClass("on");
					$(this).addClass("on");	
					var treeId = $(this).find("a").attr("id").substring(5);
					$('#tree_'+treeId).addClass("on");
				});*/
		   }
	});
	/*$.ajax({
		   type: "GET",
		   url: "getMenu!getMenu.action",
		   dataType:'json',
		   cache: false,
		   success: function(data){
				 var html = "";
				 mytree = data;
				 for (var i = 0; i < data.length; i++) {
					 if( 'undefined' === typeof data[i].pId){
						 html += createTopmenu(data[i]);
					 }
				 }
				 $("#mytree").html(html);
		   }
	});*/
	
	/**
	 * 
	 */
	/*function createTopmenu(obj){
		var mune = new menuOpt(obj.href,obj.id,obj.isIframe,obj.isleaf,obj.name,obj.pId);
		html = "<div style='overflow:hidden'>" +
				 "<div class='level0' onclick='showChild("+ JSON.stringify(mune) +" , this)'>";
		
				switch (obj.name) {
					case "日常办公":
						html += "<img src='../images/menu_image/4.png' class='ml30 mr10 menu_img l_fl' >";
						break;
					case "民主评测管理":
						html += "<img src='../images/menu_image/6.png' class='ml30 mr10 menu_img l_fl' >";
						break;
					case "水政法监督管理":
						html += "<img src='../images/menu_image/5.png' class='ml30 mr10 menu_img l_fl' >";
						break;
					case "工作流":
						html += "<img src='../images/menu_image/3.png' class='ml30 mr10 menu_img l_fl' >";
						break;
					case "日志管理":
						html += "<img src='../images/menu_image/1.png' class='ml30 mr10 menu_img l_fl' >";
						break;
					case "公文管理":
						html += "<img src='../images/menu_image/2.png' class='ml30 mr10 menu_img l_fl' >";
						break;
					case "系统管理":
						html += "<img src='../images/menu_image/7.png' class='ml30 mr10 menu_img l_fl' >";
						break;
					default:
						break;
				}
				 	
		html +=	"<div class='l_fl' >" + obj.name + "</div>" + 
				"</div>" +
			    "</div>";
		return html;
	}*/
});

/**
 * 判断是否有子集菜单，有创建
 * @param pid
 */
/*function showChild(m,obj){
	var cmunu = new Array();
	for (var i = 0; i < mytree.length; i++) {
		if(parseInt(m.id) === parseInt(mytree[i].pId)){
			cmunu.push(mytree[i]);
		}
	}
	if($(obj).siblings().length === 0){
		if('undefined' != typeof m.href){
			$(".otherleve").removeClass("selectOn");
			$(obj).addClass("selectOn");
			var option = {};
			option.key = m.id;
			option.title = m.name;
			option.url = "../" + m.href;
			option.isIframe = m.isIframe?true:false;
			addTab(option);
		}else if(cmunu.length > 0){
			console.log(m.href);
			console.log(m.isleaf);
			if('undefined' == typeof(m.href) && 'N' == m.isleaf){
				var html = createOtherMenu(cmunu,3);
			}else{
				var html = createOtherMenu(cmunu);
				//$(obj).after(html);
				//reset_BackColor($(obj));
				//console.log(cmunu);
				//$(obj).next("ul").slideToggle(500);
				//$(obj).children("img").attr("src","../images/foldopen.jpg");
				//$(obj).parent().siblings().children("div").children("img").attr("src","../images/foldclose.jpg");
			}
			$(obj).after(html);
			$(obj).next("ul").slideToggle(500);
		}
	}else{
		if($(obj).siblings().is(":hidden")){
			$(obj).siblings().slideToggle(500);
			//reset_BackColor($(obj));
			//$(obj).children("img").attr("src","../images/foldopen.jpg");
			//$(obj).parent().siblings().children("div").children("img").attr("src","../images/foldclose.jpg");
		}else{
			$(obj).siblings().slideUp(500);
			//$(obj).children("img").attr("src","../images/foldclose.jpg");
		}
	}
	$(obj).parent().siblings().children("ul").slideUp(500);
}*/


/**
 * 创建其他子集菜单
 * @param arr
 * @returns {String}
 */
/*function createOtherMenu(arr,type){
	var html = "<ul style='overflow:hidden;display:none'>";
	if(3 == Number(type)){
		for (var i = 0; i < arr.length; i++) {
			html += "<li ><div class='otherleve bgred'  onclick='showChild("+ JSON.stringify(arr[i]) +" , this)'>" + 
						arr[i].name + 
					"</div></li>";
		}
	}else{
		for (var i = 0; i < arr.length; i++) {
			html += "<li style='overflow:hidden;'><div class='otherleve'  onclick='showChild("+ JSON.stringify(arr[i]) +" , this)'>" + 
						arr[i].name + 
					"</div></li>";
		}
	}
	html += "</ul>";
	return html;
}*/

/*function menuOpt(href,id,isIframe,isleaf,name,pId){
	this.href = href;
	this.id = id;
	this.isIframe = isIframe;
	this.isleaf = isleaf;
	this.name = name;
	this.pId = pId;
	
}*/


