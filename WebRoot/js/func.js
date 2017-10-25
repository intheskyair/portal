




String.prototype.trim = function() 
{ 
return this.replace(/(^\s*)|(\s*$)/g, ""); 
} 

String.prototype.ltrim = function() 
{ 
return this.replace(/(^\s*)/g, ""); 
} 

String.prototype.rtrim = function() 
{ 
return this.replace(/(\s*$)/g, ""); 
} 

function isEmpty(inValue)
{
    if(inValue=="")
    { 
       return true;
     }
	var result=inValue.replace(/^\s+|\s+$/g,'');
	if(result.length==0)
		return true;
	return false;
}
function isNum(str){
	var text = str;
	
	if(text.length==0){
		return false;
	}
	if(text.indexOf("-")>0){
		return false;
	}
	if(text.indexOf("-")==0){
		if(text.indexOf("-")!=text.lastIndexOf("-")){
			return false;
		}
		text = text.substring(1,text.length);
	}
	if(text.indexOf(".")>=0){
		if(text.indexOf(".")!=text.lastIndexOf(".")){
			return false;
		}
		var code = 0;
		for(var i=0;i<text.length;i++){
			code = text.charCodeAt(i);
			if(code>=48&&code<=57||code==46){
				//do nothing
			}else{
				return false;
			}
		}
	}else{
		for(var i=0;i<text.length;i++){
			if(text.charCodeAt(i)<48||text.charCodeAt(i)>57){
				return false;
			}
		}
	}

	return true;
}
function isCN(chkString){
	var text=chkString;
    var num=text.length;
    var t=0;                 
    for(var i=1;i<=num;i=i+1){
        var size=text.charCodeAt(i-1);
        if (size<=32||size>=128){
            t=t+1;
            if (t==num){
                 return true;
            }
        }else{
            return false;
            break;
        }
    }
}
function isDate(datestr){
	var lthdatestr
	if (datestr != "")
		lthdatestr= datestr.length ;
	else
		lthdatestr=0;
	var tmpy="";
	var tmpm="";
	var tmpd="";
	var status;
	status=0;
	if ( lthdatestr== 0)
	return 0
	for (i=0;i<lthdatestr;i++){ 
		if (datestr.charAt(i)== '-'){
			status++;
		}
		if (status>2){
			return 0;
		}
		
		if ((status==0) && (datestr.charAt(i)!='-')){
			tmpy=tmpy+datestr.charAt(i)
		}
		
		if ((status==1) && (datestr.charAt(i)!='-')){
			tmpm=tmpm+datestr.charAt(i)
		}
		
		if ((status==2) && (datestr.charAt(i)!='-')){
			tmpd=tmpd+datestr.charAt(i)
		}

	}
	year=new String (tmpy);
	month=new String (tmpm);
	day=new String (tmpd)
	if ((tmpy.length!=4) || (tmpm.length>2) || (tmpd.length>2)){
		return 0;
	}
	if (!((1<=month) && (12>=month) && (31>=day) && (1<=day)) ){
		return 0;	}
	if (!((year % 4)==0) && (month==2) && (day==29)){
		return 0;
	}
	if ((month<=7) && ((month % 2)==0) && (day>=31)){
		return 0;
	}
	if ((month>=8) && ((month % 2)==1) && (day>=31)){
		return 0;
	}
	if ((month==2) && (day>=30)){
		return 0;
	}
	return 1;
}
function isAaZz(inValue)
{
    if(isEmpty(inValue))
        return false;
	var result=false;
	result=/[^a-zA-Z]/g.test(inValue);
	return !result;
	
}

function isAaZz09(inValue)
{
    if(isEmpty(inValue))
        return false;
	var result=false;
	result=/[^a-zA-Z0-9]/g.test(inValue);
	return !result;
}

function isCharacter(inValue)
{
    if(isEmpty(inValue))
        return false;
	var result=false;
	result= /^([a-zA-z_]{1})([\w]*)$/g.test(inValue); 
	return !result;
}

function openWindow(src,winName,top,left,height,width,scrolling)
{
	if(winName=="0")
	winName="";
	
	window.open(src,winName,"top="+top+" , left="+left+" ,height="+height+" , width="+width+" , toolbar=no, menubar=no, scrollbars="+scrolling+", resizable=no,location=no, status=no");
}

function openModalDialog(src,winName,top,left,height,width,scrolling)
{
	showModalDialog(src,winName,"dialogHeight: "+height+"px; dialogWidth: "+width+"px; dialogTop: "+top+"px; dialogLeft: "+left+"px; edge: Raised; center: Yes; help: Yes; resizable: no; status: no;scrolling:"+scrolling+";");
}

function windowAllScreen()
{
  	self.moveTo(0,0);
	self.resizeTo(screen.availWidth,screen.availHeight);
}

function isMsisdn(textName,str)
{
	var msisdn=textName.value;
	var reg=/(^(15[0-9]{1}|13[4-9]{1})[0-9]{8}$)/;	
	if(isEmpty(msisdn))
	{
		return true;
	}
	if(!reg.test(msisdn))
	{
		if(isEmpty(str))
		{
			str="??????????????";
		}
		alert(str);
		textName.focus();
		return false;
	}
	return true;
}

function OpenCenterWindow(url,name,height,width)
{
	var str=" height="+height+",innerHeight="+height;
	str+=",width="+width+",innerWidth="+width;
	if(window.screen)
	{
		var ah=screen.availHeight-30;
		var aw=screen.availWidth-10;
		var xc=(aw-width)/2;
		var yc=(ah-height)/2;
		str+=",left="+xc+",screenX="+xc;
		str+=",top="+yc+",screenY="+yc;
		
	}
	return window.open(url,name,str);
}
function OpenCenterWindow(url,name,height,width,scoll)
{
	var str=" height="+height+",innerHeight="+height;
	str+=",width="+width+",innerWidth="+width;
	var scolling;
	if(window.screen)
	{
		var ah=screen.availHeight-30;
		var aw=screen.availWidth-10;
		var xc=(aw-width)/2;
		var yc=(ah-height)/2;
		if(scoll!='no'&&scoll!=''){
		 scolling='scrollbars=yes';
		}
		else
		scolling='scrollbars=no';
		str+=",left="+xc+",screenX="+xc;
		str+=",top="+yc+",screenY="+yc;
		str+=","+scolling;
	}
	return window.open(url,name,str);
}
/*
*displaytag onmouseover change color
*/
function changeColor(){
	var table = document.getElementById("row");
	var rows = table.getElementsByTagName("tr");
	for (i=0; i < rows.length; i++) {
	    var oldClass = null;
	    rows[i].onmouseover = function() { 
	        oldClass = this.className;
	        this.className='over' 
	    };
	    rows[i].onmouseout = function() { 
	        this.className=oldClass 
	    };
	} 
}
function clickRow(src)
{	
var row=src;
while(row!=null && row.tagName!='TR')
   row=row.parentElement;
var table = document.getElementById("row");
	var rows = table.getElementsByTagName("tr");

// alert(row.rowIndex);
	   rows[row.rowIndex].className='clickRow'; 
 
 
}
function disableImage(src)
	{
		src.onclick=function ()
		{
			;
		}
		src.style.filter="gray";
		src.style.cursor="";
	}
function enableImage(src,fun1)
	{
	src.onclick=function ()
		{
			fun1(src);
		}
		src.style.filter="black";
		src.style.cursor="hand";
	}
	
	
	
	function resetElement(id) {
    var container = document.getElementById(id);
   
    var ipts = container.getElementsByTagName("INPUT");
    for (var i = 0; i < ipts.length; i++) {
        if (ipts[i].type == "text" || ipts[i].type == "hidden" || ipts[i].type == "password") {
            ipts[i].value = "";
        }
        if (ipts[i].type == "checkbox" || ipts[i].type == "radio") {
            ipts[i].checked = false;
        }
        if (ipts[i].type == "file") {
            ipts[i].outerHTML = ipts[i].outerHTML;
        }
    }
    var tas = container.getElementsByTagName("TEXTAREA");
    for (var i = 0; i < tas.length; i++) {
        tas[i].value = "";
    }
    var sts = container.getElementsByTagName("SELECT");
    for (var i = 0; i < sts.length; i++) {
        sts[i].selectedIndex = 0;
    }
}

	function resetElement2(id) {
    var container = document.getElementById(id);
   
    var ipts = container.getElementsByTagName("INPUT");
    for (var i = 0; i < ipts.length; i++) {
        if (ipts[i].type == "text" || ipts[i].type == "hidden" || ipts[i].type == "password") {
            ipts[i].value = "";
        }
        if (ipts[i].type == "checkbox" || ipts[i].type == "radio") {
            ipts[i].checked = false;
        }
        if (ipts[i].type == "file") {
            ipts[i].outerHTML = ipts[i].outerHTML;
        }
    }
    var tas = container.getElementsByTagName("TEXTAREA");
    for (var i = 0; i < tas.length; i++) {
        tas[i].value = "";
    }
    var sts = container.getElementsByTagName("SELECT");
    for (var i = 0; i < sts.length; i++) {
        sts[i].selectedIndex = 0;
    }
}

function hideElement(id) {
    ele = document.getElementById(id);
    ele.style.display = "none";
}
function showElement(id) {
    ele = document.getElementById(id);
    ele.style.display = "";
}

//check the first raido button
function checkFirstRadio(name) {
    ipts = document.getElementsByTagName("INPUT");
    for (i = 0; i < ipts.length; i++) {
        if (ipts[i].type == "radio" && ipts[i].name == name) {
            ipts[i].checked = true;
            return;
        }
    }
}


function OpenCenterWindow(url,name,height,width,scoll)
{
	var str=" height="+height+",innerHeight="+height;
	str+=",width="+width+",innerWidth="+width;
	var scolling;
	if(window.screen)
	{
		var ah=screen.availHeight-30;
		var aw=screen.availWidth-10;
		var xc=(aw-width)/2;
		var yc=(ah-height)/2;
		if(scoll!='no'&&scoll!=''){
		 scolling='scrollbars=yes';
		}
		else
		scolling='scrollbars=no';
		str+=",left="+xc+",screenX="+xc;
		str+=",top="+yc+",screenY="+yc;
		str+=","+scolling;
	}
	return window.open(url,name,str);
}



function OpenCenterModalWindow(url,name,height,width)
{
	var str=" dialogHeight:"+height+"px;";
	str+=",dialogWidth:"+width+"px; "
	var scolling;
	if(window.screen)
	{
		var ah=screen.availHeight-30;
		var aw=screen.availWidth-10;
		var xc=(aw-width)/2;
		var yc=(ah-height)/2;
		
		str+=",dialogLeft:"+xc+";screenX:"+xc;
		str+=",dialogTop:"+yc+";screenY:"+yc;
		
	}
	return window.showModalDialog(url,name,str);
}

