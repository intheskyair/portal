/* Licence:
*   Use this however/wherever you like, just don't blame me if it breaks anything.
*
* Credit:
*   If you're nice, you'll leave this bit:
*
*   Class by Pierre-Alexandre Losson -- http://www.telio.be/blog
*   email : plosson@users.sourceforge.net
*/
var flag = false;
var type = 1;//0.全部文件,1.图片

doload();

function doload()
{
	var oMyObject = window.dialogArguments;
	$("path").value = oMyObject.path;
	alert($("path").value);
	if(!oMyObject.type){
		type = oMyObject.type;
	}
	if(type==1){
		if(oMyObject.value!=""){
			$("uploadimage").style.display = "";
			$("uploadimage").src = oMyObject.value;
			DrawImage($("uploadimage"));
		}
	}
}

function refreshProgress()
{ 
	alert(2222);
    UploadMonitor.getUploadInfo(updateProgress);
}

function updateProgress(uploadInfo)
{
	alert(3333);
    if (uploadInfo.inProgress)
    {
        $('uploadbutton').disabled = true;
        $('file1').disabled = true;
 
        var fileIndex = uploadInfo.fileIndex;

        var progressPercent = Math.ceil((uploadInfo.bytesRead / uploadInfo.totalSize) * 100);

        $('progressBarText').innerHTML = 'upload in progress: ' + progressPercent + '%';

        $('progressBarBoxContent').style.width = parseInt(progressPercent * 3.5) + 'px';
        
        window.setTimeout('refreshProgress()', 1000);
        
        
    }
    else
    {
        $('uploadbutton').disabled = false;
        $('file1').disabled = false;
    
    }

    return true;
}

function startProgress()
{
	if($("file1").value==""){
		alert("请选择要上传的文件!");
		return false;
	}
	$("type").value = $("file1").value.substring($("file1").value.lastIndexOf("."));
//	if($('type').value!='.xls'&&$('type').value!='.doc'){
//		alert('只允许上传Word或Excel文件,请重新选择!');
//		return false;
//	}
	if(type==1){
		if(!flag){
			$("uploadimage").style.display="none";
			alert("文件类型不正确或文件不存在,请重新选择!");
			return false;
		}
	}
    $('progressBar').style.display = 'block';
    $('progressBarText').innerHTML = 'upload in progress: 0%';
    $('uploadbutton').disabled = true;

    // wait a little while to make sure the upload has started ..
    window.setTimeout("refreshProgress()",1500);
    return true;
 
}


function DrawImage(ImgD){
	 
	 var image=new Image();
   image.src=ImgD.src;
   if(image.width>0 && image.height>0){
    flag=true;
    if(image.width/image.height>= 450/300){
     if(image.width>450){  
     ImgD.width=450;
     ImgD.height=(image.Height*450)/image.width;
     }else{
     ImgD.width=image.width;  
     ImgD.height=image.height;
     }
     ImgD.alt=image.width+"×"+image.height;
     }
    else{
     if(image.height>300){
     ImgD.height=300;
     ImgD.width=(image.width*300)/image.height;     
     }else{
     ImgD.width=image.width;  
     ImgD.height=image.height;
     }
     ImgD.alt=image.width+"×"+image.height;
     }
    }
} 

function FileChange(Value){
	var x = $("file1");
	var y = $("uploadimage");
                
	if(!x || !x.value || !y) return;        
	var patn = /\.jpg$|\.jpeg$|\.bmp$|\.gif$/i;        
	if(patn.test(x.value)){                
  		flag=false;
		y.style.display = "";
		//y.width=100;
		//y.height=100;
		y.alt="";
		y.src=Value;
	}
	else{
  	y.style.display="none";
  	if(type==1){
  		alert("您选择的不是图像文件,请选择bmp,jpg,gif文件!");  
  	}
  }


}
