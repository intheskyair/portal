<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
	<head>
		<link href="${path}css/leftmune.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${path}uploadify/jquery-1.4.2.min.js"></script>
	</head>
	<body>
		<table width="100%" height="100%" border="0" cellSpacing=0 cellPadding=0 class="dtree">
			<tr>
				<td id=outLookBarShow name="outLookBarShow" style="HEIGHT: 100%">
					<div id="o1" style="height: 100%;"></div>
				</td>
			</tr>
		</table>
     <SCRIPT LANGUAGE="JavaScript">
  var path = '${path}';             
 function CLASS_OUTLOOKBAR(id,name)
{
    var THIS1 = this;

    this.id    = getUnique(id);
    this.name = getUnique(name);
    this.width = "140";
    this.height = "100%";
    this.titles = new Array();
    this.t = new Array();

    function getUnique(p){
        if(p!=null){
            return p;
        } else {
            return "outlook_" + new Date().getTime() + "_";
        }
    }

    this.addTitle = function(name,parentid){    

        function title(name,parentid){
            var THIS2    = this;
            this.name    = name;
            this.items    = new Array();
            this.parentid = parentid;
        
            this.addItem = function(name,target,url,image,align){

                function item(name,target,url,image,align){
                    this.name = name;
                    this.image = image;
                    this.target = target;
                    this.url = url;
                    this.align = align;
                }

                var        _item = new item(name,target,url,image,align);
                THIS2.items[THIS2.items.length] = _item;
                return _item;
            }
        }

        var _title = new title(name,parentid);
        this.titles[this.titles.length] = _title;
        this.t[parentid] = this.titles.length - 1;
        
        return _title;
    }

    this.step    = 8;
    this.speed    = 10;
    this.selectedIndex = 0;
    this.timer  = 0;
    this.rate    = 100;
    this.run    = false;
    this.wait = new Array();

    this.select = function(index){
        if(this.selectedIndex!=index){
            if(this.run==false){
                this.rate = 100;
                this.run = true;
                this.timer = window.setInterval(function(){
                
                    THIS1.rate-= THIS1.step;                    
                    var oldI = document.getElementById(THIS1.id + THIS1.selectedIndex).nextSibling;
                    var newI = document.getElementById(THIS1.id + index).nextSibling;

                    var ooI = oldI.childNodes[0].childNodes[0];
                    var nnI = newI.childNodes[0].childNodes[0];

                    oldI.style.display = "";
                    newI.style.display = "";

                    if(THIS1.rate<0){
                        window.clearInterval(THIS1.timer);
                        THIS1.timer = 0;
                        ooI.style.overflow="auto";
                        nnI.style.overflow = "auto";
                        oldI.style.display = "none";
                        THIS1.run = false;
                        THIS1.selectedIndex = index;
                        if(THIS1.wait.length>0){
                            THIS1.select(THIS1.wait[0]);
                            THIS1.wait = THIS1.wait.slice(1,-1);
                        }
                    } else {                        
                        ooI.style.overflow = "hidden";
                        nnI.style.overflow = "hidden";
                        oldI.style.height = THIS1.rate +"%";
                        newI.style.height = (100 - THIS1.rate) + "%";
                    }

                },this.speed); 
            }else{
                this.wait[this.wait.length] = index;
            }
        }
    }

    this.setup = function(contain){
        if(contain!=null){        
            var table = document.createElement("table");
                table.style.width    = "100%";
                table.style.height    = "100%";
                table.cellPadding = "0px";
                table.cellSpacing = "0px";
                table.style.border = "none";
                table.bgColor = "#C3DCDF";

                function getItem(width,height,display,title){
                    var tris = document.createElement("tr");
                    var tdis = document.createElement("td");
                        tdis.style.width = width;
                        tris.style.height = height;
                        tris.style.display = display;
                        tdis.className    = "grade2";
                        tdis.style.verticalAlign ="middle";    

                        if(title!=null&&typeof(title.items)!="undefined"){
                            
                            var vv = document.createElement("div");
                                vv.style.overflow = "auto";
                                vv.style.height = "100%";
                                vv.style.width = "100%";

                            for(var i=0;i<title.items.length;i++){
                                var div = document.createElement("div");
                                    div.style.textAlign = "";
                                    div.style.height = "20px";                                    
                                    div.style.paddingTop = "8px";
									
                                if(title.items[i].image!=null){
                                    var img = document.createElement("img");
                                        img.src = title.items[i].image;
                                        div.appendChild(img);
                                    if(typeof(title.items[i].align)!="undefined"&&title.items[i].align==1){
                                        var lbl = document.createElement("label");
                                            lbl.innerHTML = "<br>";
                                            div.appendChild(lbl);
                                    }
                                }

                                var a    = document.createElement("a");
                                    a.target    = title.items[i].target;
                                    a.innerHTML = title.items[i].name;
                                    a.href        = path+title.items[i].url;
                                     //a.href        = "javascript:void(0);";
                                    //add by gaoefeng hidden url
                                    //a.onclick = "window.location.href='"+title.items[i].url+"';return false;'";
                                    div.appendChild(a);
                                    vv.appendChild(div);                                                                                
                            }                    

                            tdis.appendChild(vv);
                        }

                        tris.appendChild(tdis);
                        return tris;
                }
        
                for(var i=0;i<this.titles.length;i++){        
                    var tr = document.createElement("tr");
                    var td = document.createElement("td");

                        tr.style.height = "24px";
                        td.style.width    = "100%";                        
                        td.className    = "grade1";    

                        td.innerHTML    = this.titles[i].name;
                        tr.id            = this.id + i;
                                                                        
                        tr.appendChild(td);
                        table.appendChild(tr);
                        
                        if (i==this.selectedIndex){
                            var tris = getItem("100%","100%","",this.titles[i]);
                                table.appendChild(tris);                                
                        } else {
                            var tri     = getItem("100%","0%","none",this.titles[i]);
                                table.appendChild(tri);
                        }    
                                            
                }

                if(typeof(contain.outerHTML)=="undefined"){
                    contain.appendChild(table);
                } else {        
                    contain.innerHTML = table.outerHTML;
                }    
                
                //bind event
                for(var i=0;i<this.titles.length;i++){
                    var title = document.getElementById(this.id + i);
                    if(title){
                        function mapping(index){
                            try    {
                                title.onclick = function(){THIS1.select(index);}
                                title.oncontextmenu = function(){ return false;}
                                title.nextSibling.oncontextmenu = function(){return false;}
                            } catch (e){
                            }
                        }
                        mapping(i);
                    }
                }
        }
    }

    this.addItem = function(parentid,name,target,url,image,align){
    		index = this.t[parentid];
        if(index>=0&&index<this.titles.length){
            return this.titles[index].addItem(name,target,url,image,align);
        }
    }

    this.setupById = function(id){
        var d = document.getElementById(id);
        if(d){
            this.setup(d);
        }
    }
}
      var o = new CLASS_OUTLOOKBAR();
	 <c:forEach var="menumap" items="${menulist1}" > 
	    o.addTitle('&nbsp;&nbsp;&nbsp;${menumap.MENUNAME}','${menumap.ID}');
	  </c:forEach> 
	  <c:forEach var="menumap1" items="${menulist2}" > 
	    o.addItem('${menumap1.PARENTID}','${menumap1.MENUNAME}',"main",'${menumap1.MENUURL}');
	  </c:forEach> 
      o.setupById("o1");


       var temp_url;                     
       function hideinfo()
       {
	       var obj=event.srcElement;
	       //捕获触发事件的对象
	        if(obj.tagName=="A")
	        {                          
		       //如果对象是链接/*如果触发函数的事件是 onmouseover ，将链接地址赋予全局变量 temp_url 后设置链接的地址为链接的文字，这样在鼠标移上时状态栏的提示信息将是 "当前页地址(不包含文件名)+触发事件的链接对象的文本"，达到了隐藏真实地址的目的*/
		       if(event.type=="mouseover")
		       {
		        temp_url=obj.href;
		        obj.href=obj.innerText;
		       }     
		       //在单击链接时用window.open 方法打开窗口，窗口目标根据链接的 target 属性值而定
		       if(event.type=="click")
		       {
			       window.open(temp_url,obj.target==""?"_self":obj.target);
			       return false;
		       } //鼠标移开后复原链接
		       if(event.type=="mouseout")
		       {
		          obj.href=temp_url;
		          temp_url=null;
		       }
	       }
       }
       //document.onmouseover=hideinfo;           //鼠标移上时调用 hideinfo 函数
       //document.onmouseout=hideinfo;          //鼠标移开时调用 hideinfo 函数
       //document.onclick=hideinfo;


       
	</SCRIPT>
	</body>
</html>
