//edit by liudc 2010-3-24 增加TypeEx = CMCC，PHONE，MSISDN的判断


document.write("<script src='js/Calendar30.js'><\/script>");
document.write("<script type='text/javascript' src='js/calendar/calendar.js'></script>");
document.write("<script type='text/javascript' src='js/calendar/lang/calendar-cn.js'></script>");
document.write("<script type='text/javascript' src='js/calendar/calendar-setup.js'></script>");

var complete=true;

//prototype.js 1.4
function $() {
	var elements = new Array();

	for (var i = 0; i < arguments.length; i++) {
		var element = arguments[i];
		if (typeof element == 'string')
			element = document.getElementById(element);

		if (arguments.length == 1)
			return element;

		elements.push(element);
	}

	return elements;
}

//把通过dwr接收到的数据，填充到相应表单字段域内。
function fillFormField(res,fieldArray) {
	if (res != undefined && fieldArray != undefined && res != null && fieldArray != null) {
		var content;
		var field;
		
		for (var i = 0; i < fieldArray.length; i++) {
			field = $(fieldArray[i]);
			
			if (field != undefined) {
				content = eval("res." + fieldArray[i]);	
				
				if (field.type == 'text' || field.type == 'hidden' || field.type == 'textarea' || field.type == 'password') {								
					field.value = (content == null?"":content);
				}
				else if (field.type == 'radio') {
					//需要重置field
					field = document.getElementsByName(fieldArray[i]);
					
					for (var i = 0; i < field.length; i++) {
						if (field[i].value == content) {
							field[i].checked = true;
						}
					}
				}
			}
		}
	}
}


//function $(objid){return document.getElementById(objid);}


String.prototype.trim = function(){return this.replace(/(^\s*)|(\s*$)/g, "");}
String.prototype.replaceAll = function(search, replace){var regex = new RegExp(search, "g");return this.replace(regex, replace);} 
Array.prototype.clone = function (){
	var tempArray = new Array(this.length);
	for(var i=0;i<this.length;i++){
		if(this[i]==null){
			tempArray[i] = null;
			continue;
		}
		if(typeof(this[i])=="object"){ //contains Array;
			tempArray[i] = this[i].clone();
			continue;
		}
		tempArray[i] = this[i];
	}
	return tempArray;
}
Object.prototype.clone = function (){
	function clonePrototype(){}
	clonePrototype.prototype = this;
	var obj = null;
	try{
		obj = new clonePrototype();
	}catch(e){
		//遇到双相引用的对象,可能会堆栈溢出 in IE; Mozilla正常!
		throw e;
	}
	for(var ele in obj){
		if(obj[ele] == null){
			obj[ele] = null;
			continue;
		}
		var objType = typeof(obj[ele]);
		if(objType == "number"||objType == "string"||objType == "boolean"||objType == "function"){
			obj[ele] = obj[ele];
			continue;
		}
		if(obj[ele] instanceof Array){
			obj[ele] = obj[ele].clone();
			continue;
		}
		if(objType == "object"){
			if(typeof(obj[ele].clone) != "function") throw "对象不支持克隆,此操作不能完成!";
			obj[ele] = obj[ele].clone();
		}
	}
	return obj;
}

Object.prototype.equals = function(obj){
	if(this == obj)return true;
	if(typeof(obj) == "undefined" || obj == null || typeof(obj) != "object")return false;
	var length = 0; var length1=0;
	for(var ele in this) length++;for(var ele in obj) length1++;
	if(length != length1) return false;
	if(obj.constructor == this.constructor){
		for(var ele in this){
			if(this[ele] == null){
				if(obj[ele] != null) return false;
			}
			if(obj[ele] == null){
				if(this[ele] != null) return false;
			}
			if(this[ele] == null || obj[ele] == null){
				continue;
			}
			if(typeof(this[ele]) == "object"){
				if(!this[ele].equals(obj[ele]))
					return false;
			}else if(typeof(this[ele]) == "function"){
				if(!this[ele].toString().equals(obj[ele].toString())) return false;
		
			}else if(typeof(obj[ele])=="number" && typeof(this[ele]) == "number"){
				if(this[ele] != obj[ele]) return false;
			}else if(typeof(this[ele]) == "number"){
				if(typeof(obj[ele]) != "number") return false;
			}else if(typeof(obj[ele]) == "number"){
				if(typeof(this[ele]) != "number")	return false;
			}else if(typeof(this[ele]) == "boolean" && typeof(obj[ele]) == "boolean"){
				if(this[ele] != obj[ele]) return false;
			}else if(typeof(this[ele]) == "boolean"){
				if(typeof(obj[ele]) != "boolean")	return false;
			}else if(typeof(obj[ele]) == "boolean"){
				if(typeof(this[ele]) != "boolean") return false;
			}else if(this[ele]!=obj[ele]){
				return false;
			}
		}
		return true;
	}
	return false;
}

function TextBoxVerify(){
	this.AutoScan=function(){
		var tbc=document.getElementsByTagName("INPUT");
		var count=tbc.length;
		var te="";
		for(var i=0;i<count;i++){
			if(tbc[i].type.toUpperCase()=="TEXT"&&tbc[i].TypeEx!=null){
				te=tbc[i].TypeEx.toUpperCase();
				window.status="正在处理TextBox("+tbc[i].id+")...";
				this.Process(tbc[i]);//tbc[i].value=tbc[i].TypeEx;
				//if(te=="DATE"||te=="DATETIME"){i++;count++};
			}
		}
		window.status=""; 
	}
	
	this.Process=function(textobj){
		switch(textobj.TypeEx.toUpperCase()){
			case "EMAIL":
				eval("textobj.onblur=function(){tbe_Email('"+textobj.id+"');};");
				break;
			case "CERT":
				eval("textobj.onblur=function(){tbe_Cert('"+textobj.id+"');};");
				break;
			case "MSISDN":
				eval("textobj.onblur=function(){tbe_Msisdn('"+textobj.id+"');};");
				break;
			case "CMCC":
				eval("textobj.onblur=function(){tbe_Cmcc_Msisdn('"+textobj.id+"');};");
				break;
			case "PHONE":
				eval("textobj.onblur=function(){tbe_Phone('"+textobj.id+"');};");
				break;
			case "DATE":
				textobj.readOnly=true;
				var pdate = textobj.value.trim();
				if(pdate!=null){
					pdate = pdate.split(" ")[0];
				}
				textobj.value = pdate;
				var dateimg=document.createElement("IMG");
				dateimg.type="image";
				dateimg.border=0;
				dateimg.src="js/Calendar.gif";
				dateimg.style.cursor="hand";
				dateimg.title="点击此处选择日期";
				eval("dateimg.onclick=function(){calendar($('"+textobj.id+"'));return false;};");
				//eval("dateimg.onclick=function(){setday(this,$('"+textobj.id+"'));return false;};");
				textobj.insertAdjacentElement("afterEnd",dateimg);
				eval("textobj.onclick=function(){calendar($('"+textobj.id+"'));return false;};");
				break;
			default:
				this.ProcessOther(textobj);				
				break;
		}
	}
	
	this.ProcessOther=function(textobj){//处理数字及字符型
		var kind=textobj.TypeEx.toString();
		if(kind.length==1) textobj.maxLength=10;
		else textobj.maxLength=parseInt(kind.substring(1,kind.length),10);
		switch(kind.substring(0,1).toUpperCase()){
			case "N":
				eval("textobj.className = 'ime';");
				eval("textobj.onkeypress=function(){event.returnValue=IsDigit(0);};");
				eval("textobj.onblur=function(){if(isNaN($('"+textobj.id+"').value)){alert('请输入正确的数字!');textobj.focus();};};");
				break;
			case "C":
				break;
			case "I":
				eval("textobj.className = 'ime';");
				eval("textobj.onkeypress=function(){event.returnValue=IsDigit(1);};");
				eval("textobj.onblur=function(){if(isNaN($('"+textobj.id+"').value)){alert('请输入正确的数字!');textobj.focus();};};");
				break;
		}
	}
}

function tbe_Email(objid){
		var obj=$(objid);
		obj.value=obj.value.trim();
		if(obj.value==""){
			return;
		}
		if(obj.AllowEmpty!=null&&obj.value.length<=0)return;
		if(obj.value.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) == -1){
			alert("警告:\n\n您的Email格式输入不正确!请重新输入!");
			obj.focus();//obj.value="";	
		}
}

function tbe_Msisdn(objid){
		var obj=$(objid);
		obj.value=obj.value.trim();
		if(obj.value==""){
			return;
		}
		if(obj.AllowEmpty!=null&&obj.value.length<=0)return;
		if(obj.value.search(/^1[3,5]\d{9}$/) == -1){
			alert("警告:\n\n您的手机号码格式不正确!");
			obj.focus();
		}
}

function tbe_Phone(objid){
		var obj=$(objid);
		obj.value=obj.value.trim();
		if(obj.value==""){
			return;
		}
		if(obj.AllowEmpty!=null&&obj.value.length<=0)return;
		if(obj.value.search(/^0(([1-9]\d)|([3-9]\d{2}))\d{8}$/) == -1){
			alert("警告:\n\n您的电话号码格式不正确! 请使用区号+电话号码格式，例如01012345678。");
			obj.focus();
		}
}

function tbe_Cmcc_Msisdn(objid){
		var obj=$(objid);
		obj.value=obj.value.trim();
		if(obj.value==""){
			return;
		}
		if(obj.AllowEmpty!=null&&obj.value.length<=0)return;
		if(obj.value.search(/^((((13[5-9]{1})|(15[0,1,2,7,8,9]{1})|(188)){1}\d{1})|((134[0-8]{1}){1})){1}\d{7}$/) == -1){
			alert("警告:\n\n您的手机号码格式不正确或不是中国移动号码!");
			obj.focus();
		}
}

function tbe_Cert(objid){
	var obj=$(objid); 
	obj.value=obj.value.trim();
	if(obj.AllowEmpty!=null&&obj.value.length<=0)return;
	var num=$(objid).value;
	if(obj.AllowEmpty!=null&&obj.value.length<=0)return;
  var len = num.length, re; 
  if (len ==  15){ 
    re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{3})$/); 
  }else if (len == 18){ 
    re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\d)$/); 
  }else {
  	alert("警告:\n\n身份证号码输入的位数不对!请重新输入!");
  	obj.focus();
  	return;
  } 
  var a = num.match(re); 
  if (a != null){ 
    if (len==15){ 
      var D = new Date("19"+a[3]+"/"+a[4]+"/"+a[5]); 
      var B = D.getYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5]; 
    }else{ 
      var D = new Date(a[3]+"/"+a[4]+"/"+a[5]); 
      var B = D.getFullYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5]; 
    } 
    if (!B){
    	alert("警告:\n\n身份证号码输入的出生日期不对!请重新输入!");
    	obj.focus();
    	return;
    } 
  } 
} 

/////////创建数据载入窗口
function $ING(h,str){
	doingmsg=str==null?ddoingmsg:str;
	if(h)StartWait();
	else EndWait();
}

function StartWait()
{
	document.body.style.cursor="Wait.ani";
	if(complete){
		complete=false;
		window.showModelessDialog("Wait.htm",window,'unadorned:no;edge:raised;dialogWidth:'+300+'px;dialogHeight:'+80+'px;center:yes;scroll:no;help:no;resizable:no;status:no');
	}
}
function EndWait(){document.body.style.cursor="";complete=true;}

function RGB2HEX(rgbcolor){
  var color = rgbcolor.split(","); 
  var scolor = "#";
  for(var i=0;i<3;i++)
		scolor += parseInt(color[i]).toString(16);
	return scolor;
}

//取得QuerySring值
function getQuery(name){
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r!=null) return unescape(r[2]); return null;
}
String.prototype.getQuery = function(name){
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = this.substr(this.indexOf("\?")+1).match(reg);
	if (r!=null) return unescape(r[2]); return null;
}

// 只允许输入数字
function IsDigit(code){
	if(code==1){
  	return ((event.keyCode >= 48) && (event.keyCode <= 57));
	}else{
		return (((event.keyCode >= 48) && (event.keyCode <= 57))||(event.keyCode == 46));
	}
}
