/*
Copyright (c) 2009, http://seyfertdesign.com/jquery/ui-tabs-paging.html

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.



* <p>Copyright: ©2011 tohot Co., Ltd. All rights reserved</p>
* <p>Title: jQuery 标签页分页插件</p> 
* <p>name: jQuery page</p> 
* <p>Description: 基于jQuery UI的标签页开发的插件，实现在不同浏览器下分页的功能</p>
*/ 

$.extend($.ui.tabs.prototype, {
	page: function(options) {
		var opts = {
			nextButton: '&#187;',
			prevButton: '&#171;',
			selectOnAdd: true
		};
		opts = $.extend(opts, options);

		var self = this, buttonWidth, containerWidth=0, allTabsWidth, tabWidths, resizeTimer = null, 
			windowHeight = $(window).height(), windowWidth = $(window).width();
		// reconfigure "ui.tabs" add/remove events to reinit paging
		var tabsAdd = self.add;
		self.add = function(url, label, index) {
			if($(".ui-tabs-paging-next",self.element).length>0){
				self.remove("remove",self.lis.length);
			}
			tabsAdd.apply(this, [url, label, index]);
		    startPage("add");
		};
		var tabsRemove = self.remove;
		self.remove = function(index) {
			tabsRemove.apply(this, [index]);
			startPage("remove");
		};
		var tabsSelect = self.select;
		self.select = function(index,actFlag) {
			tabsSelect.apply(this, [index]);
			if(actFlag){
				startPage("act");
			}
		};
		//add "prev" button ,but it's hidden
		function init(){
			if($(".ui-tabs-paging-prev").length==0){
				self.add("#tab_prevbtn",opts.prevButton);
				self.lis.eq(0).addClass("ui-state-default ui-tabs-paging-prev")
					.find("a").unbind("click")
					.bind("click",function(){
						tabGo('prev'); return false; 
					}).css("cursor","pointer").end().hide();
				buttonWidth = $(".ui-tabs-paging-prev").outerWidth({ margin: true });
			}
			self.element.bind("tabsselect", function(event, ui) {
				if(ui.panel.id=="tab_prevbtn"||ui.panel.id=="tab_nextbtn")
					return false;
			});
		}
		//when tabs's width is larger than current container,it will be executed
		function startPage(operation){
			setTabsWidth();
			if (allTabsWidth > containerWidth) {
				self.lis.eq(0).show();
				var start = 0;
				var end = tabWidths.length-1;	
				if(operation=="add"||operation=="remove"||operation=="resize"){
					if(operation!="add"){
						end = $('li.ui-state-default',self.element).index(self.lis.filter("li:not(.ui-tabs-paging-next,:hidden):last"))+1;	
						if(operation=="resize")
							end--;
						else
							end++;
						if($(".ui-tabs-paging-next",self.element).length>0){
							var nextBtnIndex = $('li.ui-state-default',self.element).index($(".ui-tabs-paging-next"));
							if(end+1>=nextBtnIndex){
								$(".ui-tabs-paging-next",self.element).hide();
							}
						}
					}
					start = getStartIndex(end)-1;
					if(operation=="add"&&$(".ui-tabs-nav").height()<50){
						start--;
					}
					if(end>self.lis.length)
						end = self.lis.length;
					if(start<1)
						start = 1;
					self.lis.filter("li:not(.ui-tabs-paging-prev,.ui-tabs-paging-next)").hide().slice(start,end).show();
					hideSuperfluousTab(start,"prev");
				}else if(operation=="act"){
					if(self.lis.eq(self.options.selected).css("display")=="none"){
						var startIndex = $('li.ui-state-default',self.element).index(self.lis.filter("li:not(.ui-tabs-paging-prev,:hidden):first"));	
						var lastIndex = $('li.ui-state-default',self.element).index(self.lis.filter("li:not(.ui-tabs-paging-next,:hidden):last"))+1;	
						var type = "prev";
						if(self.options.selected<startIndex){
							type = "next";
							start = self.options.selected-1;
							end = getEndIndex(start)+1;
							if(end>self.lis.length)
								end = self.lis.length-1;
						}else{
							type = "prev";
							end = self.options.selected;
							start = getStartIndex(end)-1;
							if(start<1)
								start = 1;
						}
						self.lis.filter("li:not(.ui-tabs-paging-prev,.ui-tabs-paging-next)").hide().slice(start,end).show();	
						hideSuperfluousTab(type=="next"?end:start,type);
						startIndex = $('li.ui-state-default',self.element).index(self.lis.filter("li:not(.ui-tabs-paging-prev,:hidden):first"))-1;
						if(startIndex < 1){
							self.lis.eq(0).hide();
						}else
							self.lis.eq(0).show();
						lastIndex = $('li.ui-state-default',self.element).index(self.lis.filter("li:not(.ui-tabs-paging-next,:hidden):last"))+1;
						//alert(lastIndex+","+($(".ui-tabs-paging-next",self.element).length>0?self.lis.length-1:self.lis.length));
						if(lastIndex<($(".ui-tabs-paging-next",self.element).length>0?self.lis.length-1:self.lis.length)){
							if($(".ui-tabs-paging-next",self.element).length==0){
								self.options.tabTemplate = '<li style="float:right"><a href="#{href}" hidefocus="true" >#{label}</a></li>';
								self.add("#tab_nextbtn",opts.nextButton);
								self.lis.filter("li:last").addClass("ui-state-default ui-tabs-paging-next").find("a").unbind("click").bind("click",function(){
									tabGo('next'); return false; 
								}).css("cursor","pointer");
							}else{
								$(".ui-tabs-paging-next").show();
							}
						}else
							$(".ui-tabs-paging-next").hide();
						self.lis.filter("li:not(.ui-tabs-paging-prev,.ui-tabs-paging-next)").hide().slice(start,end).show();	
						startIndex = $('li.ui-state-default',self.element).index(self.lis.filter("li:not(.ui-tabs-paging-prev,:hidden):first"))-1;
						if(startIndex < 1){
							self.lis.eq(0).hide();
						}else
							self.lis.eq(0).show();
						hideSuperfluousTab(type=="next"?end:start,type);
					}else{
						var startIndex = $('li.ui-state-default',self.element).index(self.lis.filter("li:not(.ui-tabs-paging-prev,:hidden):first"))-1;
						if(startIndex < 1){
							self.lis.eq(0).hide();
						}else
							self.lis.eq(0).show();
					}
				}
			}else{
				$(".ui-tabs-paging-next").hide();
				self.lis.filter("li:first").hide().end().filter("li:not(.ui-tabs-paging-prev,.ui-tabs-paging-next)").show();		
			}
			if(operation=="add"||self.options.selected==-1){
				if($(".ui-tabs-paging-next:hidden",self.element).length>0&&self.options.selected==-1)
					self.select(self.lis.length-2);
				else{
					if(operation=="add"&&opts.selectOnAdd)
						self.select(self.lis.length-1);
					else if(operation=="add"){
						self.select(self.lis.length>1?1:-1);
					}
				}
			}
			$(window).unbind("resize",handleResize).bind('resize', handleResize);
		}
		//get start index
		function getStartIndex(end){
			if(end> tabWidths.length-1)
				end = tabWidths.length-1;	
			var start = 0;
			var tempWidth = 0;
			for (var i = end; i >=0; i--) {
				tempWidth += tabWidths[i];
				if(tempWidth>containerWidth ){
					start =  i + 1;
					break;
				}
			}
			return start;
		}
		
		//get end index
		function getEndIndex(start){
			var end = tabWidths.length - 1;
			var tempWidth = 0;
			for (var i = start; i < tabWidths.length; i++) {
				tempWidth += tabWidths[i];
				if(tempWidth>containerWidth){
					end =  i - 1;
					break;
				}
			}
			return end;
		}

		//hide superfluous tab
		function hideSuperfluousTab(index,type){
			if($(".ui-tabs-nav").height()>50){
				self.lis.eq(index).hide();
				hideSuperfluousTab(type=="prev"?index+1:index-1);
			}else
				return;
		}
		//calculate each tab's width
		function setTabsWidth(){
			allTabsWidth = 0,tabWidths = new Array(), selectedTabWidths = new Array();
			containerWidth = self.element.width()==0?(containerWidth?containerWidth:0):self.element.width();
			var maxDiff = 0;
			self.lis.each(function(i) {			
				if (i == self.options.selected) {
					selectedTabWidths[i] = ($(this).outerWidth({ margin: false })+$(this).width())/2;
					tabWidths[i] = ($(this).removeClass('ui-tabs-selected').outerWidth({ margin: false })+$(this).width())/2;
					$(this).addClass('ui-tabs-selected');
					maxDiff = Math.min(maxDiff, Math.abs(selectedTabWidths[i] - tabWidths[i]));
					allTabsWidth += tabWidths[i];
				} else {
					tabWidths[i] = ($(this).outerWidth({ margin: false })+$(this).width())/2;
					selectedTabWidths[i] = ($(this).addClass('ui-tabs-selected').outerWidth({ margin: false })+$(this).width())/2;
					$(this).removeClass('ui-tabs-selected');
					maxDiff = Math.max(maxDiff, Math.abs(selectedTabWidths[i] - tabWidths[i]));
					allTabsWidth += tabWidths[i];
				}
			});
			allTabsWidth += maxDiff + ($.browser.msie?4:0) + 9;  
		}
		//when click "prev" or "next" button ,it will be executed
		function tabGo(type){
			if(type=='prev'){
				var startIndex = $('li.ui-state-default',self.element).index(self.lis.filter("li:not(.ui-tabs-paging-prev,:hidden):first"))-1;
				if(startIndex < 2)
					self.lis.eq(0).hide();
				if(startIndex > 0){
					$(".ui-tabs-paging-next",self.element).hide();
					var lastIndex = $('li.ui-state-default',self.element).index(self.lis.filter("li:not(.ui-tabs-paging-next,:hidden):last"));	
					if(lastIndex<tabWidths.length){
						if($(".ui-tabs-paging-next",self.element).length==0){
							self.options.tabTemplate = '<li style="float:right"><a href="#{href}" hidefocus="true" >#{label}</a></li>';
							self.add("#tab_nextbtn",opts.nextButton);
							self.lis.filter("li:last").addClass("ui-state-default ui-tabs-paging-next").find("a").unbind("click").bind("click",function(){
								tabGo('next'); return false; 
							}).css("cursor","pointer");
						}else{
							$(".ui-tabs-paging-next").show();
						}
					}
					setTabsWidth();
					var start = startIndex - 1;
					var end = lastIndex;
					end = getEndIndex(start)+1;
					if(end>self.lis.length)
						end = self.lis.length;
					self.lis.filter("li:not(.ui-tabs-paging-prev,.ui-tabs-paging-next)").hide().slice(start,end).show();
					hideSuperfluousTab(end,"next");
				}
			}else{
				var lastIndex = $('li.ui-state-default',self.element).index(self.lis.filter("li:not(.ui-tabs-paging-next,:hidden):last"));
				if(lastIndex+2>=self.lis.length-1){
					$(".ui-tabs-paging-next",self.element).hide();
				}
				var end = lastIndex+1;
				var startIndex = $('li.ui-state-default',self.element).index(self.lis.filter("li:not(.ui-tabs-paging-prev,:hidden):first"));
				if(startIndex>0)
					self.lis.eq(0).show();
				setTabsWidth();
				var start = startIndex;
				start = getStartIndex(end)-1;
				if(start<1)
					start=1;
				self.lis.filter("li:not(.ui-tabs-paging-prev,.ui-tabs-paging-next)").hide().slice(start,end).show();
				hideSuperfluousTab(start,"prev");
			}
		}
		//tabs resize event
		function handleResize() {
			if (resizeTimer) clearTimeout(resizeTimer);
			if (windowHeight != $(window).height() || windowWidth != $(window).width()) 
				resizeTimer = setTimeout(reinit, 100);
		}
		//when tabs have resized,it will be executed
		function reinit() {	
			windowHeight = $(window).height();
			windowWidth = $(window).width();
			startPage("resize");
		}
		//first init
		init();
	}
});
