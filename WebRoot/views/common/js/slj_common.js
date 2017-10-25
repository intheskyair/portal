/*;(function(){
	
	var isIE = '\v'=='v';
	if(isIE){
		if(!$(".title_div").hasClass("title_fiex")){
			$(".title_div").addClass("title_fiex");
		}
		
	}else{
		$(document).scroll(function(){
			if($(document).scrollTop() >= 150){
				if(!$(".title_div").hasClass("title_fiex")){
					$(".title_div").addClass("title_fiex");
				}
			}else{
				if($(".title_div").hasClass("title_fiex")){
					$(".title_div").removeClass("title_fiex");
				}
			}
		});
	}
})();
*/
;(function($){
	//预览插件,1:表格头部，2表格标题,3是否编辑
	$.fn.getData_show = function(title_table,title,type,btn){
		var max = 0;
		var self = $(this);
		var iframe = $(this).closest("iframe");
		//获得URL参数
		var args = window.location.search.substr(1);
		args = args.replace(/&/g,",");
		var d_arr = args.split(",");
		var obj = new Object();
		for ( i = 0; i < d_arr.length ; i++)
		{
			var d_arr_str = d_arr[i].split("=");
			obj[d_arr_str[0]] = d_arr_str[1];
		}				
		var cols_num = $(".title_t > td",title_table).length + 1;
		var data_rows = 0;
		var data_rows2 = 0;
		var view_type = 0;
		if(obj.view_type!=undefined)
		{
			view_type = obj.view_type;
		}
		if(view_type == 0)
		{
				//访问后台获得数据
				$.ajax({
					type: "get",
					url: "mzpc!cretateMzpc.action",
					cache: false, 
					data: {mainid:obj.mainid},
					success: function(data){
						var res = (new Function("","return " + data))();
						title.html(res.TITLE);
						max = res.max;
						switch (Number(res.type)) {
							//无职务评测
							case 0:
								if(type){
									render_table1_edit(res);
								}else{
									render_tabel1(res.list); //第一种类型
								}
								break;
							//有职务评测
							case 1:
								if(type){
									render_table2_edit(res);
								}else{
									render_tabel2(res.list);	//第二种类型
								}
								break;
							//打分类评测
							case 2:
								if(type){
									render_table3_edit(res);
								}else{
									render_tabel3(res.list);	//第三种类型
								}
								break;
							default:
								if(type){
									render_table1_edit(res);
								}else{
									render_tabel1(res.list); //第一种类型
								}
						}
					}
				});
		}
		else
        { 
			//访问后台获得数据
			$.ajax({
				type: "get",
				url: "mzpc!viewMzpc.action",
				cache: false,
				data: {mainid:obj.mainid,radom:obj.radom},
				success: function(data){
					var res = (new Function("","return " + data))();
					title.html(res.TITLE);
					switch (Number(res.type)) 
					{
						case 0:
								//console.log("type"+Number(res.type));
								render_tabel1_df(res.list); //第一种类型
							break;
						case 1:
								//console.log("type"+Number(res.type));
								render_tabel2_df(res.list);	//第二种类型
							break;
						case 2:
							//console.log("type"+Number(res.type));
							render_tabel3_df(res.list);	//第二种类型
						break;
						default:
								//console.log("type"+Number(res.type));
								render_tabel1_df(res.list); //第一种类型
					}
				}
			});
		}
		
	
		
		
		//绘制表格
		function render_tabel1(res){
			//console.log(res);
			var html = '<table  cellpadding="0" cellspacing="0" class="table01" >';
			for (var i = 0; i < res.length; i++) {
				var dlist = res[i].DATALIST;
				html += '<tr>' +
								'<td rowspan=' + (res[i].COLS + 1) + ' class="name_td" >' + res[i].NAME + '</td>' +
						'</tr>';
				for (var j = 0; j < dlist.length; j++) {
					var eval_val = dlist[j].ZB;
					html += '<tr class=row_' + j + '>';
						if(j == 0){
							for (var l = 0; l < cols_num + 1; l++) {
								if(l == 0){
									html += '<td  class="eval_td" >' + eval_val + '</td>';
								}else if(l == cols_num){
									html += '<td rowspan=' + (res[i].COLS + 1) + ' class="last_td" >&nbsp;</td>';
								}else{
									html += '<td>&nbsp;</td>';
								}
							}
						}else{
							for (var k = 0; k < cols_num; k++) {
								if(k == 0){
									html += '<td  class="eval_td" >' + eval_val + '</td>';
								}else{
									html += '<td>&nbsp;</td>';
								}
							}
						}
					html += '</tr>';
				}
			}
			
			html += '</table>';
			self.html(html);
		}
		
		//绘制第二种表格
		function render_tabel2(res){
			var html = '<table  cellpadding="0" cellspacing="0" class="table01" >';
			for (var i = 0; i < res.length; i++) {
				html += '<tr>' +
							'<td class="name_td">' + res[i].NAME + '</td>' +
							'<td class="eval_td_type1">' + res[i].POST + '</td>';
							for (var j = 0; j < (cols_num - 1); j++) {
								if(j == 1){
									html += '<td class="mzbg4_w2">&nbsp;</td>';
								}else{
									html += '<td class="mzbg4_w1">&nbsp;</td>';
								}
							}
				html += '</tr>';
			}
			html += '</table>';
			self.html(html);
		}
		
		
		
		//绘制表格
		function render_tabel3(res){
			//console.log(res);
			var html = '<table  cellpadding="0" cellspacing="0" class="table01" >';
			for (var i = 0; i < res.length; i++) {
				var dlist = res[i].DATALIST;
				html += '<tr>' +
								'<td rowspan=' + (res[i].COLS + 1) + ' class="big_td1" >' + res[i].NAME + '</td>' +
						'</tr>';
				for (var j = 0; j < dlist.length; j++) {
					var eval_val = dlist[j].ZB;
					html += '<tr class=row_' + j + '>';
						for (var k = 0; k < cols_num; k++) {
							if(k == 0){
								html += '<td  class="big_td8" >' + eval_val + '</td>';
							}else{
								html += '<td style="width:55px;">&nbsp;</td>';
							}
						}
					html += '</tr>';
				}
			}
			
			html += '</table>';
			self.html(html);
		}
		
		//绘制编辑表格第一类型
		function render_table1_edit(data){
			//拼接选择条件
			var selector = data.pj_list;
			var html_select = "<select class='kd'>";
			html_select += "<option value=''>请选择</option>";
			for (var q = 0; q < selector.length; q++) {
				var temp = selector[q];
				for (var x in temp) {
					html_select += "<option value="+ x +">" + temp[x] + "</option>";
				}
			}
			//拼接表格
			html_select += "</select>";
			var res = data.list;
			data_rows = res.length;
			var html = '<table  cellpadding="0" cellspacing="0" class="table01" >';
			for (var i = 0; i < res.length; i++) {
				var dlist = res[i].DATALIST;
				html += '<tr class="row_' + i + '" >' +
								'<td rowspan=' + (res[i].COLS + 1) + ' class="name_td" >' + res[i].NAME + '</td>' +
						'</tr>';
				for (var j = 0; j < dlist.length; j++) {
					var eval_val = dlist[j].ZB;
					html += '<tr class="row_' + i + '" >';
						if(j == 0){
							for (var l = 0; l < cols_num + 1; l++) {
								if(l == 0){
									html += '<td  class="eval_td" >' + eval_val + '</td>';
								}else if(l == cols_num){
									html += '<td rowspan=' + (res[i].COLS + 1) + ' class="last_td" >' + 
												html_select
										  + '</td>';
								}else{
									html += '<td><input type="checkbox" class="td_num_' + i + '_' + l +'"/></td>';
								}
							}
						}else{
							for (var k = 0; k < cols_num; k++) {
								if(k == 0){
									html += '<td  class="eval_td" >' + eval_val + '</td>';
								}else{
									html += '<td ><input type="checkbox"  class="td_num_' + i + '_'+ k +'" /></td>';
								}
							}
						}
					html += '</tr>';
				}
			}
			
			html += '</table>';
			self.html(html);
			
			//checkbox事件
			$("input[type='checkbox']").click(function(){
				var box_class = $("." + $(this).attr("class"));
				if($(this).attr('checked')){
					box_class.removeAttr('checked');
					$(this).attr('checked','checked');
				}else{
					$(this).attr('checked','checked');
				}
			});
			
			//按钮添加事件;
			if(btn){
				btn.click(function(){
					//选中的数量--by dsc
						var check_num = 0;
						var data = [];
						var ztFlagAA = 0; //当前A+的个数
						var ztFlagA = 0; //当前A的个数
						//循环第一组数据
						for (var i = 0; i < data_rows; i++) {
//							console.log("data_rows"+data_rows);
							var flag = 0; //记录一个用户打钩个数
							var  temp = new Object();
							$(".row_" + i).each(function(index){
								if(index == 0){
									//取出每一组数据的NAME
									temp.NAME = $(this).children("td").html();
									temp.ROW = new Array();
								}else{
									//循环第一行数据
									var n = "";
									var row = null;
									$(this).children("td").each(function(h){
										if(h == 0){
											n = $(this).html();
											row = new Object();
											row[n] = new Array();
										}else{
											
											if($(this).children("input[type='checkbox']").length){
												if($(this).children("input[type='checkbox']").prop("checked")){
													//选中的数量--by dsc
													check_num +=1;
													row[n].push(1);
													flag ++;
												}else{
													row[n].push(0);
												}
											}else{
												temp.PJ = $(this).children("select").val();
											}
										}
									});
									temp.ROW.push(row);
								}
							});
							if(flag != cols_num - 1 || temp.PJ.length == 0){
								$("#check_num_info").html("["+temp.NAME+"]有未选择的选项");
								break;
							}
							data.push(temp);
						}
						
						
						
						if(data.length != data_rows){
							return false;
						}else{
							for(var k = 0;k < data.length ; k ++){
								if(data[k].PJ == 'A+'){
									ztFlagAA ++;
								}
								if(data[k].PJ == 'A'){
									ztFlagA ++;
								}
							}
							if(ztFlagAA > Number(max) || ztFlagA > Number(max)){
								$("#check_num_info").html("嘉奖或优秀不能超过"+max+"个");
								return false;
							}else{
								$("#check_num_info").html("");
							}
						}
						//console.log("check_num:"+check_num+"总选项条目"+11*data_rows);
//						<!-- 未选中完整提示信息 by dsc -->
						/*if(check_num <(cols_num-1)*data_rows){
							$("#check_num_info").html("*你有未选择的选项");
							return false;
						}*/
						
						/*var flag = 0;
						$(".last_td").each(function(index){
							if(index != 0){
								if($(this).children("select").val().length == 0){
									$("#check_num_info").html("*你有未选择的总体评价");
									flag = 1;
								}
							}
						});
						if(flag == 1){
							return false;
						}*/
						//数据交互
						$.ajax({
							type: "POST",
							url: "mzpc!savepdf.action",
							cache: false,
							data: {"json":JSON.stringify(data),"mainid":obj.mainid,"radom":obj.radom},
							success: function(data){
								var res = (new Function("","return " + data))();
								if(res.msg == "ok"){
									window.parent.showMsg("提交成功！");
									window.parent.clearNewTab();
									//$(window.parent.document).find("#span_close_tab_" + obj.mainid).click();
								}else{
									window.parent.showMsg(res.msg);
								}
							}
						});
				});
			}
		}
		
		//绘制编辑表格第二类型
		function render_table2_edit(data){
			var res = data.list;
			data_rows2 = res.length;
			var html = '<table  cellpadding="0" cellspacing="0" class="table01" >';
			for (var i = 0; i < res.length; i++) {
				html += '<tr class="row_' + i + '" >' +
							'<td class="name_td">' + res[i].NAME + '</td>' +
							'<td class="eval_td_type1">' + res[i].POST + '</td>';
							for (var j = 0; j < (cols_num - 1); j++) 
							{
								if(j == 1){
									//html += '<td class="mzbg4_w2"><input type="checkbox"  class="td_num_'+ i +'_' + j +' " /></td>';
									html += '<td class="mzbg4_w2"><input type="checkbox"  class="td_num_'+ i +' " /></td>';
								}else{
									if(j > 2){
										html += '<td class="mzbg4_w1"><input type="checkbox"  class="td_num_'+ i +'_advise " /></td>';
									}else{
										html += '<td class="mzbg4_w1"><input type="checkbox"  class="td_num_'+ i +' " /></td>';
									}
								}
							}
				html += '</tr>';
			}
			html += '</table>';
			self.html(html);
			//checkbox事件
			$("input[type='checkbox']").click(function(){
				var box_class = $("." + $(this).attr("class"));
				if($(this).attr('checked')){
					box_class.removeAttr('checked');
					$(this).attr('checked','checked');
				}else{
					$(this).attr('checked','checked');
				}
			});
			//保存
			if(btn){
				btn.click(function(){
					var data = [];
					//循环第一组数据
					for (var i = 0; i < data_rows2; i++) {
						var status = 0;
						var temp = new Object();
						$(".row_"+ i).each(function(){
							var rows = new Array();
							$(this).children("td").each(function(index){
								if(index == 0){
									temp.NAME = $(this).html();
									return true;
								}
								if(index == 1){
									temp.POST = $(this).html();
									return true;
								}
								if(index > 1){
									if($(this).children("input[type='checkbox']").length){
										if($(this).children("input[type='checkbox']").prop("checked")){
											if(index == 5){
												temp.PJ = 'A';
											}
											rows.push(1);
										}else{
											if(index == 5){
												temp.PJ = 'B';
											}
											rows.push(0);
										}
									}
								}
							});
							temp.ROW = rows;
						}); 
						for(var j = 0;j <temp.ROW.length; j++ ){
							if(temp.ROW[j] == 1){
								status++;
							}
						}
						if(status != 2){
							$("#check_num_info").html("["+temp.NAME+"]有未选择的选项");
							break;
						}
						data.push(temp);
					}

					if(data_rows2 == data.length){
						$.ajax({
							type: "POST",
							url: "mzpc!savepdf.action",
							cache: false,
							data: {"json":JSON.stringify(data),"mainid":obj.mainid,"radom":obj.radom},
							success: function(data){
								var res = (new Function("","return " + data))();
								if(res.msg == "ok"){
									window.parent.showMsg("提交成功！");
									window.parent.clearNewTab();
									//$(window.parent.document).find("#span_close_tab_" + obj.mainid).click();
								}else{
									window.parent.showMsg(res.msg);
								}
								/*window.parent.closeMZtabs();*/
							}
						});
					}
					/*
					 
					var st = 0;
					var flag = true;
					for(var i = 0; i < data.length ; i++){
						for(var j = 0; j < data[i].ROW.length ; j++){
							if((data[i].ROW)[j] == 1){
								st ++;
							}
						}
						if(st != 2){
							flag = false;
							break;
						}
						st = 0;
					}
					if(!flag){
						$("#check_num_info").html("*你有未选择的选项");
					}else{
						$.ajax({
							type: "POST",
							url: "mzpc!savepdf.action",
							cache: false,
							data: {"json":JSON.stringify(data),"mainid":obj.mainid,"radom":obj.radom},
							success: function(data){
								var res = (new Function("","return " + data))();
								if(res.msg == "ok"){
									window.parent.showMsg("提交成功！");
									window.parent.clearNewTab();
									//$(window.parent.document).find("#span_close_tab_" + obj.mainid).click();
								}else{
									window.parent.showMsg(res.msg);
								}
								window.parent.closeMZtabs();
							}
						});
					}*/
					
					
				});
			}
		}
		
		
		
		
		//绘制编辑表格第三类型
		function render_table3_edit(data){
			var rowLength;
			var res = data.list;
			data_rows = res.length;
			var html = '<table  cellpadding="0" cellspacing="0" class="table01" >';
			for (var i = 0; i < res.length; i++) {
				var dlist = res[i].DATALIST;
				rowLength = dlist.length;
				html += '<tr class="row_' + i + '" >' +
								'<td rowspan=' + (res[i].COLS + 1) + ' class="big_td1" >' + res[i].NAME + '</td>' +
						'</tr>';
				for (var j = 0; j < dlist.length; j++) {
					var eval_val = dlist[j].ZB;
					html += '<tr class="row_' + i + '" >';
							for (var k = 0; k < cols_num; k++) {
								if(k == 0){
									html += '<td  class="big_td8" >' + eval_val + '</td>';
								}else{
									html += '<td ><input type="checkbox"  class="td_num_' + i + '_'+ j +'" /></td>';
								} 
							}
					html += '</tr>';
				}
			}
			
			html += '</table>';
			self.html(html);
			
			//checkbox事件
			$("input[type='checkbox']").click(function(){
				var box_class = $("." + $(this).attr("class"));
				if($(this).attr('checked')){
					box_class.removeAttr('checked');
					$(this).attr('checked','checked');
				}else{
					$(this).attr('checked','checked');
				}
			});
			
			//按钮添加事件;
			if(btn){
				btn.click(function(){
					//选中的数量--by dsc
						var check_num = 0;
						var data = [];
						var ztFlagAA = 0; //当前好的个数
						var ztFlagA = 0; //当前较好的个数
						//循环第一组数据
						for (var i = 0; i < data_rows; i++) {
//							console.log("data_rows"+data_rows);
							var flag = 0; //记录一个用户打钩个数
							var  temp = new Object();
							$(".row_" + i).each(function(index){
								//当前为第一个tr
								if(index == 0){
									//取出每一组数据的NAME
									temp.NAME = $(this).children("td").html();
									temp.ROW = new Array();
								//当前为第2~$(".row_" + i).length 个tr	
								}else{
									//循环第一行数据
									var n = "";
									var row = null;
									$(this).children("td").each(function(h){
										//获取第2~$(".row_" + i).length 个tr下面的第一个td的text
										if(h == 0){
											n = $(this).html();
											row = new Object();
											row[n] = new Array();
										//获取第2~$(".row_" + i).length 个tr下面的第2~n个td的text
										}else{
											//if($(this).children("input[type='checkbox']").length){
												if($(this).children("input[type='checkbox']").prop("checked")){
													//选中的数量--by dsc
													check_num +=1;
													row[n].push(1);	
													flag ++;
													
													//获取最后一行总体评价的值
													if(index == $(".row_" + i).length - 1){
														temp.PJ = 11 - h;
													}
												}else{
													row[n].push(0);
												}
											/*}else{
												temp.PJ = $(this).children("select").val();
											}*/
												
												
										}
									});
									temp.ROW.push(row);
								}
							});
							if(flag != rowLength){
								$("#check_num_info").html("["+temp.NAME+"]有未选择的选项");
								break;
							}
							data.push(temp);
						}
						//console.log(data);
						
						
						if(data.length != data_rows){
							return false;
						}else{
							for(var k = 0;k < data.length ; k ++){
								if(data[k].PJ == '10' || data[k].PJ == '9'){
									ztFlagAA ++;
								}
							}
							if(ztFlagAA > Number(max)){
								$("#check_num_info").html("好或较好不能超过"+max+"个");
								return false;
							}else{
								$("#check_num_info").html("");
							}
						}
						//console.log("check_num:"+check_num+"总选项条目"+11*data_rows);
//						<!-- 未选中完整提示信息 by dsc -->
						/*if(check_num <(cols_num-1)*data_rows){
							$("#check_num_info").html("*你有未选择的选项");
							return false;
						}*/
						
						/*var flag = 0;
						$(".last_td").each(function(index){
							if(index != 0){
								if($(this).children("select").val().length == 0){
									$("#check_num_info").html("*你有未选择的总体评价");
									flag = 1;
								}
							}
						});
						if(flag == 1){
							return false;
						}*/
						//数据交互
						$.ajax({
							type: "POST",
							url: "mzpc!savepdf.action",
							cache: false,
							data: {"json":JSON.stringify(data),"mainid":obj.mainid,"radom":obj.radom},
							success: function(data){
								var res = (new Function("","return " + data))();
								if(res.msg == "ok"){
									window.parent.showMsg("提交成功！");
									window.parent.clearNewTab();
									//$(window.parent.document).find("#span_close_tab_" + obj.mainid).click();
								}else{
									window.parent.showMsg(res.msg);
								}
							}
						});
				});
			}
		}
		
		
		function render_tabel1_df(res){
			var html = '<table  cellpadding="0" cellspacing="0" class="table01" >';
			for (var i = 0; i < res.length; i++) 
			{
				var dlist = res[i].DATALIST;
				html += '<tr>' +
								'<td rowspan=' + (res[i].COLS + 1) + ' class="name_td" >' + res[i].NAME + '</td>' +
						'</tr>';
				for (var j = 0; j < dlist.length; j++) 
				{
					var eval_val = dlist[j].ZB;
					var eval_val_value = dlist[j].ZB_VALUE;
					
					html += '<tr class=row_' + j + '>';
						if(j == 0)
						{
							for (var l = 0; l < cols_num + 1; l++) 
							{
								if(l == 0){
									html += '<td  class="eval_td" >' + eval_val + '</td>';
								}else if(l == cols_num)
								{
									html += '<td rowspan=' + (res[i].COLS + 1) + ' class="last_td" >'+ res[i].TOTAL+'</td>';
								}
								else
								{
									    var _val = eval_val_value[j];
									  
									    for ( t in _val){
									    	var tp = _val[t];
									    	if(tp[l-1] == 0){
									    		 html += '<td></td>';
									    	}else{
									    		 html += '<td>√</td>';
									    	}
									    }
									
								}
							
							}
						}else
						{
							for (var k = 0; k < cols_num; k++) 
							{
								if(k == 0)
								{
									html += '<td  class="eval_td" >' + eval_val + '</td>';
								}
								else
								{
									    var _val = eval_val_value[j];
									    for ( t in _val){
									    	var tp = _val[t];
									    	if(tp[k-1] == 0){
									    		html += '<td></td>';
									    	}else{
									    		html += '<td>√</td>';
									    	}
									    }
									
								}
							}
						}
					html += '</tr>';
				}
			}
			
			html += '</table>';
			self.html(html);
		}
		
		
	    //预览第二种类型数据
		function render_tabel2_df(res){
			var html = '<table  cellpadding="0" cellspacing="0" class="table01" >';
			for (var i = 0; i < res.length; i++) {
				html += '<tr>' +
							'<td class="name_td">' + res[i].NAME + '</td>' +
							'<td class="eval_td_type1">' + res[i].POST + '</td>';
						var temp = res[i].DATALIST[0].ZB_VALUE;
							for (var j = 0; j < temp.length; j++) {
								if(j == 1){
									if(Number(temp[j]) == 0){
										html += '<td class="mzbg4_w2"></td>';
									}else{
										html += '<td class="mzbg4_w2">√</td>';
									}
								}else{
									if(Number(temp[j]) == 0){
										html += '<td class="mzbg4_w1"></td>';
									}else{
										html += '<td class="mzbg4_w1">√</td>';
									}
								}
							}
				html += '</tr>';
			}
			html += '</table>';
			self.html(html);
		}
		
		
		
		function render_tabel3_df(res){
			var html = '<table  cellpadding="0" cellspacing="0" class="table01" >';
			for (var i = 0; i < res.length; i++) 
			{
				var dlist = res[i].DATALIST;
				html += '<tr>' +
								'<td rowspan=' + (res[i].COLS + 1) + ' class="big_td1" >' + res[i].NAME + '</td>' +
						'</tr>';
				for (var j = 0; j < dlist.length; j++) 
				{
					var eval_val = dlist[j].ZB;
					var eval_val_value = dlist[j].ZB_VALUE;
					
					html += '<tr class=row_' + j + '>';
							for (var k = 0; k < cols_num; k++) 
							{
								if(k == 0)
								{
									html += '<td  class="big_td8" >' + eval_val + '</td>';
								}
								else
								{
									    var _val = eval_val_value[j];
									    for ( t in _val){
									    	var tp = _val[t];
									    	if(tp[k-1] == 0){
									    		html += '<td style="width:55px;">&nbsp;</td>';
									    	}else{
									    		html += '<td style="width:55px;">√</td>';
									    	}
									    }
									
								}
							}
					html += '</tr>';
				}
			}
			
			html += '</table>';
			self.html(html);
		}
		
//[{name:'gf',ROW :{A:[],B:[],C:[],D:[] },pj:'A'},{name:'gf',A:[],B:[],C:[],D:[],pj:'A'}]
//[{name:'gf',post:'xx',ROW :[],pj:'A'},{name:'gf',A:[],B:[],C:[],D:[],pj:'A'}]
}

})(jQuery);