var GetParameter = function() {
    var map = new Array();
    var tgs = document.getElementsByTagName('script');
    if( tgs.length <= 0 ) { return null; }
    var src = tgs.item(tgs.length - 1).src;
    var pos = src.indexOf('?');
    if( -1 == pos ) { return null; }
    var paras = src.substring( pos + 1 );    
     paras = paras.split('&');
    for( var n = 0; n < paras.length; n++ ) 
    {
        _ParseParameter(map, paras[n],n);
    }
    return map;
};
 
var _ParseParameter = function(map, para,n) {
    var pos = para.indexOf('=');
    var key = para.substring( 0, pos );
    var value = para.substring( pos + 1 );
    map[key] = value;
};

//框架控制
var paras_ = GetParameter();
var paraValue = paras_['path'];
//<!--
function cwin()
{if(parent.testframeset.cols!='180,*')
{
	event.srcElement.parentElement.firstChild.src = paraValue+"images/1.jpg";
	event.srcElement.parentElement.firstChild.alt = "关闭左栏";
	parent.testframeset.cols='180,*';
}
else{
	event.srcElement.parentElement.firstChild.src = paraValue+"images/2.jpg";
	event.srcElement.parentElement.firstChild.alt = "打开左栏";
	parent.testframeset.cols='-10,*';
}}
function closenavi() {
w-=10;
if (w>-10) {window.parent.testframeset.cols = ''+w+',*';repeat=setTimeout("closenavi()",100);}
else {clearTimeout(repeat);w=180;}
}
//-->
//显示隐藏层
//more javascript from http://www.smallrain.net
var disappeardelay=250  //menu disappear speed onMouseout (in miliseconds)
var enableanchorlink=0 //Enable or disable the anchor link when clicked on? (1=e, 0=d)
var hidemenu_onclick=1 //hide menu when user clicks within menu? (1=yes, 0=no)

/////No further editting needed

var ie5=document.all
var ns6=document.getElementById&&!document.all

function getposOffset(what, offsettype){
var totaloffset=(offsettype=="left")? what.offsetLeft : what.offsetTop;
var parentEl=what.offsetParent;
while (parentEl!=null){
totaloffset=(offsettype=="left")? totaloffset+parentEl.offsetLeft : totaloffset+parentEl.offsetTop;
parentEl=parentEl.offsetParent;
}
return totaloffset;
}

function showhide(obj, e, visible, hidden){
if (ie5||ns6)
dropmenuobj.style.left=dropmenuobj.style.top=-500
if (e.type=="click" && obj.visibility==hidden || e.type=="mouseover")
obj.visibility=visible
else if (e.type=="click")
obj.visibility=hidden
}

function iecompattest(){
return (document.compatMode && document.compatMode!="BackCompat")? document.documentElement : document.body
}

function clearbrowseredge(obj, whichedge){
var edgeoffset=0
if (whichedge=="rightedge"){
var windowedge=ie5 && !window.opera? iecompattest().scrollLeft+iecompattest().clientWidth-15 : window.pageXOffset+window.innerWidth-15
dropmenuobj.contentmeasure=dropmenuobj.offsetWidth
if (windowedge-dropmenuobj.x < dropmenuobj.contentmeasure)
edgeoffset=dropmenuobj.contentmeasure-obj.offsetWidth
}
else{
var windowedge=ie5 && !window.opera? iecompattest().scrollTop+iecompattest().clientHeight-15 : window.pageYOffset+window.innerHeight-18
dropmenuobj.contentmeasure=dropmenuobj.offsetHeight
if (windowedge-dropmenuobj.y < dropmenuobj.contentmeasure)
edgeoffset=dropmenuobj.contentmeasure+obj.offsetHeight
}
return edgeoffset
}

function dropdownmenu(obj, e, dropmenuID){
if (window.event) event.cancelBubble=true
else if (e.stopPropagation) e.stopPropagation()
if (typeof dropmenuobj!="undefined") //hide previous menu
dropmenuobj.style.visibility="hidden"
clearhidemenu()
if (ie5||ns6){
obj.onmouseout=delayhidemenu
dropmenuobj=document.getElementById(dropmenuID)
if (hidemenu_onclick) dropmenuobj.onclick=function(){dropmenuobj.style.visibility='hidden'}
dropmenuobj.onmouseover=clearhidemenu
dropmenuobj.onmouseout=ie5? function(){ dynamichide(event)} : function(event){ dynamichide(event)}
showhide(dropmenuobj.style, e, "visible", "hidden")
dropmenuobj.x=getposOffset(obj, "left")
dropmenuobj.y=getposOffset(obj, "top")
dropmenuobj.style.left=dropmenuobj.x-clearbrowseredge(obj, "rightedge")+"px"
dropmenuobj.style.top=dropmenuobj.y-clearbrowseredge(obj, "bottomedge")+obj.offsetHeight+"px"
}
return clickreturnvalue()
}

function clickreturnvalue(){
if ((ie5||ns6) && !enableanchorlink) return false
else return true
}

function contains_ns6(a, b) {
while (b.parentNode)
if ((b = b.parentNode) == a)
return true;
return false;
}

function dynamichide(e){
if (ie5&&!dropmenuobj.contains(e.toElement))
delayhidemenu()
else if (ns6&&e.currentTarget!= e.relatedTarget&& !contains_ns6(e.currentTarget, e.relatedTarget))
delayhidemenu()
}

function delayhidemenu(){
delayhide=setTimeout("dropmenuobj.style.visibility='hidden'",disappeardelay)
}

function clearhidemenu(){
if (typeof delayhide!="undefined")
clearTimeout(delayhide)
}

function loadThreadFollow(t_id){
	var targetImg =$("followImg" + t_id);
	var targetDiv =$("follow" + t_id);
	if (targetImg.src.indexOf("nofollow")!=-1){return false;}
	if ("object"==typeof(targetImg)){
		if(arguments[1]){
			if(arguments[1]==1){
				targetDiv.style.display="block";
				targetImg.src="Images/minus1.gif";
				targetImg.title="收缩列表";
			}else{
				targetDiv.style.display="none";
				targetImg.src="Images/plus1.gif";
				targetImg.title="展开列表";
			}
		}else{
			if (targetDiv.style.display!='none'){
				targetDiv.style.display="none";
				targetImg.src="Images/plus1.gif";
				targetImg.title="展开列表";
			}else{
				targetDiv.style.display="block";
				targetImg.src="Images/minus1.gif";
				targetImg.title="收缩列表";
				goFunction(t_id);
			}
		}
	}
}

function loadThreadFollowM(t_id){
	var targetImg =$("followImgM" + t_id);
	var targetDiv =$("followM" + t_id);
	if (targetImg.src.indexOf("nofollow")!=-1){return false;}
	if ("object"==typeof(targetImg)){
		if(arguments[1]){
			if(arguments[1]==1){
				targetDiv.style.display="block";
				targetImg.src="Images/minus1.gif";
				targetImg.title="收缩列表";
			}else{
				targetDiv.style.display="none";
				targetImg.src="Images/plus1.gif";
				targetImg.title="展开列表";
			}
		}else{
			if (targetDiv.style.display!='none'){
				targetDiv.style.display="none";
				targetImg.src="Images/plus1.gif";
				targetImg.title="展开列表";
			}else{
				targetDiv.style.display="block";
				targetImg.src="Images/minus1.gif";
				targetImg.title="收缩列表";
				goFunctionM(t_id);
			}
		}
	}
}

function hideLeftCol(id){
	if(this.document.getElementById( id).style.display=='none'){
		this.document.getElementById( id).style.display='inline';
		document['HideHandle'].src = 'theme/default/hidden.gif';		
	}else{
		this.document.getElementById(  id).style.display='none';
		document['HideHandle'].src = 'theme/default/show.gif';	
	}
}