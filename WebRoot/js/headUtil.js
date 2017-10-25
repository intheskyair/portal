(function(){
	$.fn.table_head_tj = function(data){
		var tj = $(this);
		var html = "";
		html +="<table id='tab' cellpadding='0' cellspacing='0' style='width:700px;'>"
			html +="<tr><td>序号</td><td>姓名</td><td>性别</td><td>出生年月</td><td>原任职务</td><td>现任职务</td><td>任职时间</td><td colspan='3'>操作</td></tr>"
		if(data){
			console.log(data);
			var name = JSON.parse(data.PARAVALUE);
			var sex = JSON.parse(data.SEX);
			var birthday = JSON.parse(data.BIRTHDAY);
			var oldjob = JSON.parse(data.OLDJOB);
			var newjob = JSON.parse(data.NEWJOB);
			var jobtime = JSON.parse(data.JOBTIME);
			for(var i = 0 ; i < name.length ; i ++){
				html +="<tr id='tab"+(i+1)+"' align='center'>";
				html +="<td>"+(i+1)+"</td>";
				html +="<td><input type='text' value='"+name[i]+"'/></td>";
				html +="<td><input type='text' value='"+sex[i]+"'/></td>";
				html +="<td><input type='text' value='"+birthday[i]+"'/></td>";
				html +="<td><input type='text' value='"+oldjob[i]+"'/></td>";
				html +="<td><input type='text' value='"+newjob[i]+"'/></td>";
				html +="<td><input type='text' value='"+jobtime[i]+"'/></td>";
				html +="<td><a href='#' id='close' onclick='deltr("+(i+1)+",\"tab\")'>X</a></td>"
				html +="<td><a href='#' id='up' onclick='move("+(i+1)+",\"tab\",0)'>↑</a></td>"
				html +="<td><a href='#' id='down' onclick='move("+(i+1)+",\"tab\",1)'>↓</a></td></tr>"
			}
		}else{
			html +="<tr id='tab1' align='center' class='kd_input'>";
			html +="<td>1</td>";
			html +="<td><input type='text'/></td>";
			html +="<td><input type='text'/></td>";
			html +="<td><input type='text'/></td>";
			html +="<td><input type='text'/></td>";
			html +="<td><input type='text'/></td>";
			html +="<td><input type='text'/></td>";
			html +="<td><a href='#' id='close' onclick='deltr(1,\"tab\")'>X</a></td>"
			html +="<td><a href='#' id='up' onclick='move(1,\"tab\",0)'>↑</a></td>"
			html +="<td><a href='#' id='down' onclick='move(1,\"tab\",1)'>↓</a></td></tr>"
		}
		html +="</table>";
		html += "<div style='margin-top: 5px;'><input type='button' id='add_btn' value='添加' onclick='addtr(\"tab\")'/></div>";
		tj.html(html);
	}
	
	
	$.fn.table_head = function(content_container,mydata,type,mdata,options){
		var self = $(this);
		var opt = $.extend({},options);
		var html = "";
		var keys = [];
		var values = [];
		var type1 = [];
		var btype = [];
		for(var i = 0 ;i  < mydata.length; i++)
		{
			keys.push(mydata[i].ID);
			values.push(mydata[i].DIC_PAHT);
			type1.push(mydata[i].TYPE1);
			btype.push(mydata[i].TYPE);
			
			if(type == mydata[i].ID )
			{	
			   html += "<option value="+ mydata[i].ID +" selected='selected'>" + mydata[i].DIC_NAME + "</option>";
			}
			else
			{
			   html += "<option value="+ mydata[i].ID +">" + mydata[i].DIC_NAME + "</option>";
			}
			
			
		}
		self.html(html);
		selectedByValue(self.val(),mdata);
		//选中表头
		function selectedByValue(kname,mdata){
			for(var i = 0 ; i < keys.length; i++){
				if(kname == keys[i]){
					$("#type").val(btype[i]);
					if(btype[i] == 3){
						$("#ds_pjdj").hide();
						$("#ds_pwgr").hide();
						$("#ds_post").hide();
						$("#ds_person").hide();
						$("#ds_pjxr").hide();
					}else if(btype[i] == 4){
						$("#ds_pjdj").hide();
						$("#ds_pwgr").show();
						$("#ds_post").show();
						$("#ds_person").hide();
						$("#ds_pjxr").hide();
					}else if(btype[i] == 5){
						$("#ds_pjxr").show();
						$("#ds_pjdj").hide();
						$("#ds_pwgr").hide();
						$("#ds_post").hide();
						$("#ds_person").hide();
						$("#pjxr").table_head_tj(mdata);
					}else{
						$("#ds_pjdj").show();
						$("#ds_pwgr").show();
						$("#ds_post").show();
						$("#ds_person").show();
						$("#ds_pjxr").hide();
						$("#dlg_pjdj").val(type1[i]);
					}
					content_container.load("../" + values[i]);
					break;
				}
			}
			
		}
		
		self.change(function(){
			selectedByValue($(this).val());
		});
	}
	
	
	
	
	
	
	
})($);


//增加<tr/>add_btn
var addtr = function(table){
    var _len = $("#"+table+" tr").length - 1;        
    $("#"+table).append("<tr id="+table+Number(_len + 1)+" align='center'>"
    				+"<td>"+Number(_len + 1)+"</td>"
                    +"<td><input type='text'/></td>"
                    +"<td><input type='text'/></td>"
                    +"<td><input type='text'/></td>"
                    +"<td><input type='text'/></td>"
                    +"<td><input type='text'/></td>"
                    +"<td><input type='text'/></td>"
                    +"<td><a href=\'#\' id='close' onclick=\'deltr("+Number(_len + 1)+",&quot;"+table+"&quot;)\'>X</a></td>"
                    +"<td><a href=\'#\' id='up' onclick=\'move("+Number(_len + 1)+",&quot;"+table+"&quot;,0)\'>↑</a></td>"
                    +"<td><a href=\'#\' id='down' onclick=\'move("+Number(_len + 1)+",&quot;"+table+"&quot;,1)\'>↓</a></td>"
                    +"</tr>");           
   }


//表格删除与增加
var deltr =function(index,table){
	var tr = $("#"+table+" tr");
	if(tr.length == 2){
		return;
	}
		 $("tr[id='"+table+index+"']").remove();//删除当前行
		   tr = $("#"+table+" tr");
		    var len = tr.length - index;
		    var nextIndex = index + 1;
		    for(var i = 0;i<len;i++){
		       $("#"+table+" tr[id='"+table+Number(nextIndex+i)+"'] td").eq(0).text(Number(nextIndex+i-1));
		       $("#"+table+" tr[id='"+table+Number(nextIndex+i)+"'] td").eq(7).html("<a href='#' id='close' onclick='deltr("+Number(nextIndex+i-1)+",&quot;"+table+"&quot;)'>X</a>");
		       $("#"+table+" tr[id='"+table+Number(nextIndex+i)+"'] td").eq(8).html("<a href='#' id='up' onclick='move("+Number(nextIndex+i-1)+",&quot;"+table+"&quot;,0)'>↑</a>");
		       $("#"+table+" tr[id='"+table+Number(nextIndex+i)+"'] td").eq(9).html("<a href='#' id='down' onclick='move("+Number(nextIndex+i-1)+",&quot;"+table+"&quot;,1)'>↓</a>");
		       $("#"+table+" tr[id='"+table+Number(nextIndex+i)+"']").attr("id",table+(nextIndex+i-1));
		    } 
};


//上移
var move =function(index,table,status){
	if(status == 0 && index == 1){
		return;
	}
	if(status == 1 && index == $("#"+table+" tr").length - 1){
		return;
	}
	var array = new Array();
	$("#"+table+index).children().each(function(i, v){
		if($(this).children("input[type='text']").length){
			array.push($(this).children("input[type='text']").val());
		}else{
			if(i > 1){
				return;
			}
			array.push($(this).text());
		}
	});
	var array2 = new Array();
	var bb;
	if(status == 0){
		bb = index - 1;
	}else{
		bb = index + 1;
	}
	$("#"+table+bb).children().each(function(i, v){
		if($(this).children("input[type='text']").length){
			array2.push($(this).children("input[type='text']").val());
		}else{
			if(i > 1){
				return;
			}
			array2.push($(this).text());
		}
	});
		$("#"+table+array[0]).children().each(function(i, v){
			if($(this).children("input[type='text']").length){
				$(this).children("input[type='text']").val(array2[i]);
			}
		});
	
		$("#"+table+array2[0]).children().each(function(i, v){
			if($(this).children("input[type='text']").length){
				$(this).children("input[type='text']").val(array[i]);
			}
		});
	
	
};


var tjxr = function(){
	var obj = new Object();
	var name= new Array();
	var sex= new Array();
	var birthday = new Array();
	var oldjob = new Array();
	var newjob = new Array();
	var jobtime = new Array();
	$("#tab tr").each(function(index){
		var arr = ["",name,sex,birthday,oldjob,newjob,jobtime];
		if(index > 0){
			$(this).children("td").each(function(index){
				if($(this).children("input[type='text']").length){
					if(index == 1){
						if($.trim($(this).children("input[type='text']").val()) == ""){
							obj = "error";
							return false;
						}
					}
					arr[index].push($(this).children("input[type='text']").val());
				}
			});
			if(obj == "error"){
				return false;
			}
		}
	});
	if(obj != 'error'){
		obj.name = JSON.stringify(name);
		obj.sex = JSON.stringify(sex);
		obj.birthday = JSON.stringify(birthday);
		obj.oldjob = JSON.stringify(oldjob);
		obj.newjob = JSON.stringify(newjob);
		obj.jobtime = JSON.stringify(jobtime);
	}
	console.log(obj);
	return obj;
}

