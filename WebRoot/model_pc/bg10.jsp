<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/compart/common.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/views/common/js/jquery.min.js"></script>
	</head>
	<body>
<div  class="title_div ">
	<p class="btt">德的专项测评表</p>
<!-- <table class="table01 table_h" cellpadding="0" cellspacing="0">
<tr>
    <td colspan="16">姓名：<input type="text" id="userName" readonly="readonly" style="border:none;border-bottom:#000 1px solid" />职务：<input type="text" id="work" readonly="readonly" style="border:none;border-bottom:#000 1px solid" /></td>

  </tr>
  <tr>
    <td rowspan="2">类别</td>
    <td rowspan="2">评价内容</td>
    <td colspan="4">评价类别</td>
  </tr>
  <tr>
    <td>没问题</td>
    <td>有些问题</td>
    <td>问题较严重</td>
    <td>不了解</td>
  </tr>
  <tr>
    <td rowspan="2">理想信念</td>
    <td>政治不够坚定，信念动摇</td>
    <td><input type="radio" id="" class="td_num_0" name="td_1" value="1"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_1" value="2"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_1" value="3"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_1" value="4"></td>
  </tr>
  <tr>
    <td>在公开场合发表不当言论</td>
    <td><input type="radio" id="" class="td_num_0" name="td_2" value="1"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_2" value="2"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_2" value="3"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_2" value="4"></td>
  </tr>
  <tr>
    <td rowspan="2">政治纪律</td>
    <td>对上级决策阳奉阴违</td>
    <td><input type="radio" id="" class="td_num_0" name="td_3" value="1"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_3" value="2"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_3" value="3"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_3" value="4"></td>
  </tr>
  <tr>
    <td>在干部选拔任用中跑官要官、拉票贿选</td>
    <td><input type="radio" id="" class="td_num_0" name="td_4" value="1"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_4" value="2"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_4" value="3"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_4" value="4"></td>
  </tr>
  <tr>
    <td rowspan="2">大局意识</td>
    <td>在班子中闹不团结，搞小团体、小圈子，拉帮结派</td>
    <td><input type="radio" id="" class="td_num_0" name="td_5" value="1"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_5" value="2"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_5" value="3"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_5" value="4"></td>
  </tr>
  <tr>
    <td>考虑个人利益较多，不服从组织决定</td>
    <td><input type="radio" id="" class="td_num_0" name="td_6" value="1"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_6" value="2"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_6" value="3"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_6" value="4"></td>
  </tr>
  <tr>
    <td rowspan="2">坚持原则</td>
    <td>回避矛盾，和稀泥，当老好人</td>
    <td><input type="radio" id="" class="td_num_0" name="td_7" value="1"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_7" value="2"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_7" value="3"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_7" value="4"></td>
  </tr>
  <tr>
    <td>凭个人好恶推荐使用干部，任人唯亲</td>
    <td><input type="radio" id="" class="td_num_0" name="td_8" value="1"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_8" value="2"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_8" value="3"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_8" value="4"></td>
  </tr>
  <tr>
    <td rowspan="2">履职尽责</td>
    <td>工作的主动性不够，积极性不高，执行力不强</td>
    <td><input type="radio" id="" class="td_num_0" name="td_9" value="1"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_9" value="2"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_9" value="3"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_9" value="4"></td>
  </tr>
  <tr>
    <td>在重大任务中退缩不前、推诿塞责，不敢担当</td>
    <td><input type="radio" id="" class="td_num_0" name="td_10" value="1"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_10" value="2"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_10" value="3"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_10" value="4"></td>
  </tr>
  <tr>
    <td rowspan="2">工作作风</td>
    <td>工作浮躁，搞形式主义</td>
    <td><input type="radio" id="" class="td_num_0" name="td_11" value="1"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_11" value="2"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_11" value="3"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_11" value="4"></td>
  </tr>
  <tr>
    <td>作风不实，弄虚作假</td>
    <td><input type="radio" id="" class="td_num_0" name="td_12" value="1"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_12" value="2"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_12" value="3"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_12" value="4"></td>
  </tr>
  <tr>
    <td rowspan="3">个人品行</td>
    <td>以权谋私、吃拿卡要</td>
    <td><input type="radio" id="" class="td_num_0" name="td_13" value="1"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_13" value="2"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_13" value="3"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_13" value="4"></td>
  </tr>
  <tr>
    <td>推卸赡养抚养义务</td>
    <td><input type="radio" id="" class="td_num_0" name="td_14" value="1"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_14" value="2"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_14" value="3"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_14" value="4"></td>
  </tr>
  <tr>
    <td>家庭不和谐，生活作风有问题</td>
    <td><input type="radio" id="" class="td_num_0" name="td_15" value="1"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_15" value="2"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_15" value="3"></td>
    <td><input type="radio" id="" class="td_num_0" name="td_15" value="4"></td>
  </tr>
    <tr>
    <td colspan="2" rowspan="2">德的总体评价</td>
    <td>好</td>
    <td>较好</td>
    <td>一般</td>
    <td>差</td>
  </tr>
    <tr>
      <td><input type="radio" id="" class="td_num_0" name="td_16" value="1"></td>
      <td><input type="radio" id="" class="td_num_0" name="td_16" value="2"></td>
      <td><input type="radio" id="" class="td_num_0" name="td_16" value="3"></td>
      <td><input type="radio" id="" class="td_num_0" name="td_16" value="4"></td>
    </tr>
</table> -->
</div>
<div id="df_table" style="overflow-y: scroll;
    height: 90%;">
	
</div>
<script type="text/javascript">
$(function(){
	 $("input[type='radio']").attr("disabled",true);
	 var args = window.location.search.substr(1);
	 console.log(args);
		args = args.replace(/&/g,",");
		var d_arr = args.split(",");
		var obj = new Object();
		for ( i = 0; i < d_arr.length ; i++)
		{
			var d_arr_str = d_arr[i].split("=");
			obj[d_arr_str[0]] = d_arr_str[1];
		}
		
		//获取德的评测人
		$.ajax({
			type: "POST",
			url: "mzpf!getType4User.action",
			cache: false,
			data: {mainId:obj.mainid},
			success: function(data){
				var res = (new Function("","return " + data))();
				var userName = res.PARAVALUE.split(",");
				var work = res.POST.split(",");
				var array = [['理想信念',2,'政治不够坚定，信念动摇'],['在公开场合发表不当言论'],['政治纪律',2,'对上级决策阳奉阴违'],['在干部选拔任用中跑官要官、拉票贿选'],['大局意识',2,'在班子中闹不团结搞小团体、小圈子，拉帮结派']
				,['考虑个人利益较多，不服从组织决定'],['坚持原则',2,'回避矛盾，和稀泥，当老好人'],['凭个人好恶推荐使用干部，任人唯亲'],['履职尽责',2,'工作的主动性不够积极性不高，执行力不强'],['在重大任务中退缩不前、推诿塞责，不敢担当'],['工作作风',2,'工作浮躁，搞形式主义'],
				['作风不实，弄虚作假'],['个人品行',3,'以权谋私，吃拿卡要'],['推卸赡养抚养义务'],['家庭不和谐，生活作风有问题']];
				var html= "";
				for(var i = 0; i < userName.length; i ++){
					html+="<table class='table01 table_h' cellpadding='0' cellspacing='0'>";
					html+="<tr><td colspan='16'>姓名：<input type='text' id='userName_"+i+"' value='"+userName[i]+"' readonly='readonly' style='border:none;border-bottom:#000 1px solid' />职务：<input type='text' id='work_"+i+"' value='"+work[i]+"' readonly='readonly' style='border:none;border-bottom:#000 1px solid' /></td></tr>";
					html+="<tr><td rowspan='2'>类别</td><td rowspan='2'>评价内容</td><td colspan='4'>评价类别</td></tr><tr><td>没问题</td><td>有些问题</td><td>问题较严重</td><td>不了解</td></tr>";
					var k = 0;
					for(var j = 0 ;j < array.length; j ++){
						var arr = array[j];
						if(arr.length == 3){
							html+="<tr><td rowspan='"+arr[1]+"'>"+arr[0]+"</td><td>"+arr[2]+"</td>";
						    html+="<td><input type='radio' id='' class='td_num_0' name='td_"+i+"_"+j+"' value='1'></td>";
						    html+="<td><input type='radio' id='' class='td_num_0' name='td_"+i+"_"+j+"' value='2'></td>";
						    html+="<td><input type='radio' id='' class='td_num_0' name='td_"+i+"_"+j+"' value='3'></td>";
						    html+="<td><input type='radio' id='' class='td_num_0' name='td_"+i+"_"+j+"' value='4'></td></tr>";
						}else{
							 html+="<tr><td>"+arr[0]+"</td>";
							    html+="<td><input type='radio' id='' class='td_num_0' name='td_"+i+"_"+j+"' value='1'></td>";
								html+="<td><input type='radio' id='' class='td_num_0'  name='td_"+i+"_"+j+"' value='2'></td>";			    
							    html+="<td><input type='radio' id='' class='td_num_0'  name='td_"+i+"_"+j+"' value='3'></td>";
							    html+="<td><input type='radio' id='' class='td_num_0'  name='td_"+i+"_"+j+"' value='4'></td></tr>";
						}
						k++;
					}
				    html+="</tr><tr><td colspan='2' rowspan='2'>德的总体评价</td><td>好</td><td>较好</td><td>一般</td><td>差</td></tr>";
				    html+="<tr>"
					html+="<td><input type='radio' id='' class='td_num_0' name='td_"+i+"_"+k+"' value='1'></td>";
					html+="<td><input type='radio' id='' class='td_num_0'  name='td_"+i+"_"+k+"' value='2'></td>";
					html+="<td><input type='radio' id='' class='td_num_0'  name='td_"+i+"_"+k+"' value='3'</td>";
				    html+="<td><input type='radio' id='' class='td_num_0'  name='td_"+i+"_"+k+"' value='4'></td></tr>" 
				    html+="<tr><td colspan='16'>&nbsp;</td></tr>"
				    html+="</table>";
				    $("#df_table").html(html);
				    $("input[type='radio']").attr("disabled",true);
				}
			}
		})
		if(obj.view_type == 1){
			$.ajax({
				type: "get",
				url: "mzpc!getKHPF.action",
				cache: false,
				data: {mainid:obj.mainid,radom:obj.radom},
				success: function(data){
					 var res = (new Function("","return " + data))();
					var res1 = JSON.parse(res.PARA_VALUE);
					console.log(res1);
					for(var i = 0; i < res1.length; i ++){
						var res2 = res1[i].list; 
						for(var j = 0; j < res2.length; j++){
							$("input[name='td_"+i+"_"+j+"'][value='"+res2[j]+"']").attr("checked","checked");
						} 
						
					}
				}
			});
		}
});
</script>
	</body>
</html>
