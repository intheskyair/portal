<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script type="text/javascript">
var orgTree, rMenu, addCount;
// 定义节点的一些操作
var treeNodeObj = {
	// 显示菜单
	showRMenu : function(type, x, y) {
		$("#rMenu ul").show();
		if (type == "root") {
			$("#m_del").hide();
		} else {
			$("#m_del").show();
		}
		rMenu.css({
			"top" : y - 100 + "px",
			"left" : x - 200 + "px",
			"display" : "block"
		});
		$("#rMenu li").css("padding","4px");
		$("#m_add").hover(function(){
			$("#m_add").css("background-color","yellow");
			},function(){
			$("#m_add").css("background-color","gray");
		});
		$("#m_rename").hover(function(){
			$("#m_rename").css("background-color","yellow");
			},function(){
			$("#m_rename").css("background-color","gray");
		});
		$("#m_del").hover(function(){
			$("#m_del").css("background-color","yellow");
			},function(){
			$("#m_del").css("background-color","gray");
		});
		$("body").bind("mousedown", treeNodeObj.onBodyMouseDown);
	},
	// 隐藏菜单
	hideRMenu : function() {
		if (rMenu) rMenu.css({"display": "none"});
		$("body").unbind("mousedown", treeNodeObj.onBodyMouseDown);
	},
	// 鼠标眼下事件
	onBodyMouseDown : function(event) {
		if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
			rMenu.css({"display" : "none"});
		}
	},
	// 获取节点并且编辑该节点
	getAndEditNode : function(zTree, fNode) {
		var nNode = zTree.getNodeByParam("temp", addCount, null);
		nNode.temp = 0;
		nNode.Ev = 0;
		if (fNode) {
			nNode.SUPER_ORG_ID = fNode.ORG_ID;
			nNode.SUPER_ORG_NAME = fNode.ORG_NAME;
			nNode.ORG_TYPE = parseInt(fNode.ORG_TYPE) + 1;
		} else {
			nNode.SUPER_ORG_ID = 1;
			nNode.SUPER_ORG_NAME = "全部组织";
			nNode.ORG_TYPE = 1;
		}
		treeNodeObj.editTreeNode(nNode);
		addCount++;
	},
	// 新增节点
	addTreeNode : function() {
		treeNodeObj.hideRMenu();
		var fNode = orgTree.getSelectedNodes()[0];
		var newNode = {
			ORG_NAME : "",
			temp : addCount
		};
		if (fNode) {
			orgTree.addNodes(fNode, newNode);
			treeNodeObj.getAndEditNode(orgTree, fNode);
		} else {
			orgTree.addNodes(null, newNode);
			treeNodeObj.getAndEditNode(orgTree, fNode);
		}
	},
	// 编辑节点
	editTreeNode : function(nNode) {
		treeNodeObj.hideRMenu();
		if (nNode) {
			orgTree.editName(nNode);
		}
	},
	// rename之前
	beforeRename : function(treeId, treeNode, newName, isCancel) {
		if (newName.length == 0) {
			showMsg("节点名称不能为空!");
			return false;
		}
		return true;
	},
	// 删除节点
	removeNode : function(nNode) {
		treeNodeObj.hideRMenu();
		orgTree.removeNode(nNode);

		$.ajax({
			type : "post",
			url : "org!deleteOrg.action",
			data : nNode,
			async : false,
			success : function(msg) {
				if (msg == "ok") {
					showMsg("节点删除成功!");
				}
			}
		});
	},
	// 传递数据
	postData : function(jsonData, url) {
		var str;
		$.ajax({
			type : "post",
			//contentType : "application/json; charset=utf-8",
			//dataType : "json",
			url : url,
			data : jsonData,
			async : false,
			success : function(msg) {
				var data=eval("("+msg+")");

				if (data.orgid) str = data.orgid;
				showMsg(data.msg);
			}
		});
		return str;
	},
	// 重命名
	onRename : function(e, treeId, treeNode, isCancel) {

		if (treeNode.Ev == 0) {
			var id = treeNodeObj.postData(treeNode, "org!addOrg.action");
			treeNode.ORG_ID = id;
		} else {
			treeNodeObj.postData(treeNode, "org!updateOrg.action");
			orgTree.updateNode(treeNode);
		}
	},
	// 右击事件
	onRightClick : function(event, treeId, treeNode) {
		if (!treeNode && event.target.tagName.toLowerCase() != "bttton"
				&& $(event.target).parents("a").length == 0) {
			orgTree.cancelSelectedNode();
			treeNodeObj.showRMenu("root", event.clientX, event.clientY);
		} else if (treeNode && !treeNode.noR) {
			orgTree.selectNode();
			treeNodeObj.showRMenu("node", event.clientX, event.clientY);
		}
	}
};
var setting = {
		view: {
				dblClickExpand: false, //关闭双击节点展开  
	            showLine: true,  	//显示线
	            selectedMulti: false   //不允许同时选中多个节点
		},
		edit : {
			enable :true,	//可编辑
			drag: {
				isCopy: false, //不允许复制
				isMove: true,	//允许拖动
				inner: false,
				next: true,
				prev: true
			},
			editNameSelectAll: true, //编辑名称时，默认全选
			showRemoveBtn : false,  //显示删除按钮
			showRenameBtn : false	//显示重命名 按钮
		},
		data: {
			
			simpleData: {
				enable: true, //使用简单数据格式
				idKey : "ORG_ID", //节点ID
				pIdKey : "SUPER_ORG_ID", //节点父ID
				rootPID : 1 //根节点ID
			},
			key : {
				name: "ORG_NAME", //节点显示名称
			}
		},
		callback:{
			onRightClick : treeNodeObj.onRightClick, //鼠标右击时间
			//beforeRemove : treeNodeObj.beforeRename,
			beforeRename : treeNodeObj.beforeRename,	//重命名之前调用事件
			onRename : treeNodeObj.onRename,	 //重命名调用事件
			beforeDrag: beforeDrag,	//拖拽之前调用事件
			beforeDrop: beforeDrop 	//拖拽中调用事件
		}
	};
	var org = {
		loadTree : function() {
			orgTree = null;
			$("#org_tree").html("");
			var queryP = {};
			queryP.pagenum = 1;
			queryP.pagesize = 10000;
			$.ajax({
				type: "POST",
				url: "org!toConfigOrg.action?operation=init",
				cache: false,
				data: queryP,
				success: function(msg){
					var data=jQuery.parseJSON(msg);
					orgTree = $.fn.zTree.init($("#org_tree"), setting, data);
					/* orgTree.setting.edit.drag.isMove = true; */
					//orgTree = $.fn.zTree.getZTreeObj("org_tree");
					orgTree.expandAll(true);
					rMenu = $("#rMenu");
					addCount = 1;
				}
			});
		}
	};
	$(function(){
		org.loadTree();

		$("#m_add").click(function(){
			treeNodeObj.addTreeNode();
		});
		$("#m_rename").click(function(){
			var nNode = orgTree.getSelectedNodes()[0];
			if (nNode) {
				nNode.Ev = 1;
				treeNodeObj.editTreeNode(nNode);
			} else {
				showMsg("请选定一个节点!");
			}
		});
		$("#m_del").click(function(){
			var nNode = orgTree.getSelectedNodes()[0];
			if (nNode) {
				treeNodeObj.removeNode(nNode);
			} else {
				showMsg("请选定一个节点!");
			}
		});
	});
	
	//拖拽前执行
	  var dragId;
	  function beforeDrag(treeId, treeNodes) {
	      for (var i=0,l=treeNodes.length; i<l; i++) {
	          dragId = treeNodes[i].SUPER_ORG_ID;
	           if (Number(treeNodes[i].level) == 0) {
	             return false;
	          } 
	 	  }
	      return true;
	  }
	   //拖拽释放之后执行
	  function beforeDrop(treeId, treeNodes, targetNode, moveType) {
	       if(targetNode.SUPER_ORG_ID == dragId){
	         var data = {id:treeNodes[0].ORG_ID,targetId:targetNode.ORG_ID,moveType:moveType,pid:dragId};
	         var confirmVal = false;
	         $.ajax({
	           async: false,
	           type: "post",
	           data:data,
	           url:"org!sortOrg.action",
	           success: function(json){
	              if(json=="success" ){
	                confirmVal = true;
	               // showMsg('操作成功!');
	              } else{
	            	  showMsg('操作失败');
	              }
	           },
	           error: function(){
	        	   showMsg('亲，网络有点不给力呀！');
	           }
	         });
	        return confirmVal;
	       } else{
	    	   showMsg('只支持同级排序');
	         return false;
	     }
	  }
</script>
<div >
	<ul id="org_tree" class="ztree" style="font-size: 20px;">
	</ul>
</div>

<div id="rMenu" style="position: absolute;display: none; background-color: gray; width: 75px; height: 80px;padding: 4px;font-size: 20px;">
	<ul>
		<li id="m_add">新增</li>
		<li id="m_rename">重命名</li>
		<li id="m_del">删除</li>
	</ul>
</div>