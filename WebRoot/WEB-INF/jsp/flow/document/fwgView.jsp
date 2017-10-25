<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" style="padding-right:10px">
<head>
<title>发文稿</title>
<%@ include file="/common/meta.jsp"%>
</head> 
<body style="position:relative;">
	<div class="sl-content" style="height:200px;">
		<!-- <h1 class="sl-title" id="show_document_type" ></h1> -->
		<div  class="sl-table-border-top sl-table-border-botm sl-div-table" >
			<table cellpadding="0" cellspacing="0" class="sl-table" align="center">			
				<tr>
					<td class="sl-width-one sl-hegiht-one sl-table-td-botm">
						<div class="sl-div-title">签发:</div>
						<div class="sl-div-body">
							<textarea id="lingdao_view" readonly="readonly"  class="sl-div-textarea">
								${map.rect9method == '0' ? '同意' : ''}${map.rect9method == '-1' ? '不同意' : ''}<c:if test="${not empty map.rect9suggest}">(${map.rect9suggest})</c:if>
							</textarea>
						</div>
					</td>
					<td rowspan="2"  class="sl-width-two sl-hegiht-one sl-table-td-botm sl-table-td-left sl-table-td-right" >
						<div class="sl-div-title">会签:</div>
						<div class="sl-div-body p5">
							<textarea id="huiqian_view" style="align:center" readonly="readonly"   class="sl-div-textarea" style="height:100%"></textarea>
						</div>
					</td>
					<td colspan="2" class="sl-width-two sl-hegiht-one sl-table-td-botm">
						<div class="sl-div-title">主办处室:</div>
						<div class="sl-div-body">
							<textarea id="zbcs_view"  readonly="readonly" class="sl-div-textarea">${admin.org_name}</textarea>
						</div>
					</td>
				</tr>
				<tr >
					<td class="sl-hegiht-two sl-table-td-botm">
						<div class="sl-div-title">办公室核稿:</div>
						<div class="sl-div-body">
							<textarea id="four_bgshg_view" readonly="readonly"   class="sl-div-textarea">
								${map.rect4method == '0' ? '同意' : ''}${map.rect4method == '-1' ? '不同意' : ''}<c:if test="${not empty map.rect4suggest}">(${map.rect4suggest})</c:if>
							</textarea>
						</div>
					</td>
					<td class="sl-hegiht-two sl-table-td-botm sl-width-three">
						<div class="sl-div-title">
							核稿:
						</div>
						<div class="sl-div-body">
							<textarea id="second_hg_view" readonly="readonly"  class="sl-div-textarea" style="margin-left: 5px;">
							${map.rect3method == '0' ? '同意' : ''}${map.rect3method == '-1' ? '不同意' : ''}<c:if test="${not empty map.rect3suggest}">(${map.rect3suggest})</c:if>
							</textarea>
						</div>
					</td>
					<td class="sl-hegiht-two sl-table-td-botm sl-table-td-left">
						<div class="sl-div-title" style="height:30px;">
							拟稿:
						</div>
						<div class="sl-div-body" style="height:50px;border-bottom: solid 1px #DC143C;">
							<textarea id="first_ng_view"  readonly="readonly"  class="sl-div-textarea">${admin.truename}</textarea>
						</div>
							<div class="sl-div-title"  style="height:30px">
							是否公开:
						</div>
						<div class="sl-div-body" style="height:80px;text-align:center;font-size: 12px;overflow-y: auto;color: #000;" id="sfgk_view">

						</div>
					</td>
				</tr>
				<tr>
					<td  colspan="4" class="sl-hegiht-tree sl-table-td-botm">
						<div class="sl-div-title">标题:</div>
						<div class="sl-div-body">
							<textarea id="title_view" readonly="readonly"  class="sl-div-textarea">${map.title}</textarea>
						</div>
					</td>
				</tr>
				<tr>
					<td  colspan="4" class="sl-hegiht-tree sl-table-td-botm">
						<div class="sl-div-title">主送机关:（抄送机关及附件列于文末）</div>
						<div class="sl-div-body">
							<textarea id="first_zsjg_view" readonly="readonly"   class="sl-div-textarea">${map.first_zsjg}</textarea>
						</div>
					</td>
				</tr>
				<tr>
					<td class="sl-hegiht-two" colspan="2">
						<div class=" float-left sl-width-one-px sl-hegiht-four sl-line-h-one " id="document_wh_view">文号</div>
						<div class="float-left sl-hegiht-four sl-line-h-one" >
						[<input id ="wh_year" readonly="readonly" value="${map.wh_year}" type="text"/>]
						<input id ="wh_num" readonly="readonly" value="${map.wh_num}" type="text"/>号
						</div>
					</td>	
					<td class="sl-hegiht-two" colspan="2"> 打印份数:<input type="text" readonly="readonly"  id ="num_view"  class="sl-width-one-px sl-height-five"/></td>
				</tr>
				<tr>
			 	 <td>
					 <label style="color:red;">*</label>
				  	 <label for="input_1">附件：</label>
			     </td>
				 <td colspan="4"  id="upload_add">
				    <div id="showupload">		
					<input type="hidden" name="path_plann_file" id="path_plann_file" />
					<input type="file" id="plann_file" name="plann_file" value="" style="display: none;" />
					<div id="promotionShow"></div>		
					</div>
				 </td>
				</tr> 
			</table>
		</div> 
	  		</div>
<script type="text/javascript">
    function closeIframe(){
    	$(window.top.document).find(".ui-tabs-nav > li.ui-state-active > div > span.tabs-close").click();
    }
    
    $(function()
    {    
    	
    	  if(typeof JSON == 'undefined'){
			$('head').append($("<script type='text/javascript' src='${ctx}/views/common/js/json2.js'>"));
		  }
		  $("#showupload").css("display", "none");
	      var  str_path = "${map.path_plann_file}".replace(/\@/g,"\\");
	      var  str_file_name = "${map.promotionShow}".replace(/\@/g,"\\");
	      $("#upload_add").append("<span style='color: red;'>"+str_file_name+"</span>");
	      $("#upload_add").append("<a href='javascript:void(0);' onclick=mydownload('"+escape(str_path)+"');>点击下载</a>"); 	
    	
// 		    		$("#document_type").css("display", "none");
		    	    $("#document_wh_view").html("${map.document_wh}");
		    		/* $("#show_document_type").html("${map.document_type}"); */		    		
		    		$("#first_ng_view").html(HtmlDecode("${map.first_ng}"));
		    		$("#sfgk_view").html("${map.sfgk}");
		    		if("${map.sfgk}" == '否'){
		    			$("#sfgk_view").append("<br/>理由：${map.ly}");	
		    		}
		    		$("#title_view").html(HtmlDecode("${map.title}"));
		    		$("#first_zsjg_view").html(HtmlDecode("${map.first_zsjg}"));
		    		//$("#second_hg").html(HtmlDecode("${map.rect3suggest}"));
		    		$("#zbcs_view").html(HtmlDecode("${map.zbcs}"));
		    		$("#huiqian_view").html(HtmlDecode("${map.rect8suggest}"));
		    		//$("#four_bgshg").html(HtmlDecode("${map.rect4suggest}"));
		    		//$("#lingdao_view").html(HtmlDecode("${map.rect9suggest}")); 
		    		$("#wh_year").val(HtmlDecode("${map.wh_year}"));
		    		$("#wh_num").val(HtmlDecode("${map.wh_num}"));
		    		$("#num_view").val(HtmlDecode("${map.num}"));
		    		$("#k_editor_view").html(unescape("${map.file_content}"));
		    		if('${map.rect8}' != ''){
		    			var result = "";
		    			var arr = JSON2.parse('${map.rect8}');
		    			for(var i in arr){
		    				var method = arr[i].method == '0' ? '同意' : '不同意'; 
		    				result += ("姓名：" + arr[i].truename +"--结果："+ method + "--意见：" + arr[i].suggest) + "\n";
		    			}
		    			$("#huiqian_view").html(result);
		    		}		    		
    });
 
    function changeCss(id){
    	$("#" + id).each(function(){
    		$(this).removeAttr("readonly","readonly");
    	});
    }

    
    function mydownload(path)
    {
    	location.href='upload!download.action?&path='+ encodeURI(encodeURI(unescape(path))); 
    }
</script>
</body>
</html>
