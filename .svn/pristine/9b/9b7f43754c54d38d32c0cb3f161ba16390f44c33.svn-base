(function($){
var arr_selector = new Array();
$.ajax({
	type:'get',
	dataType : "json",
	cache : false,
	url : "admin!getAllRoles.action",
	async : true,
	success : function(data) {
		/*AUTHNAME: "系统管理员"
			ID: 1
			REMARK: "超级管理员"*/
		var obj = null;
		obj = new Object();
		obj.name = "请选择";
		obj.value = "";
		arr_selector.push(obj);
		for (var i = 0; i < data.length; i++) {
			obj = new Object();
			obj.name = data[i].AUTHNAME;
			obj.value = "" + data[i].ID;
			arr_selector.push(obj);
		}
	}
});
	
var snakerflow = $.snakerflow;

$.extend(true,snakerflow.config.rect,{
	attr : {
	r : 8,
	fill : '#F6F7FF',
	stroke : '#03689A',
	"stroke-width" : 2
}
});

$.extend(true,snakerflow.config.props.props,{
	name : {name:'name', label:'名称', value:'', editor:function(){return new snakerflow.editors.inputEditor();}},
	displayName : {name:'displayName', label:'显示名称', value:'', editor:function(){return new snakerflow.editors.inputEditor();}},
	expireTime : {name:'expireTime', label:'期望完成时间', value:'', editor:function(){return new snakerflow.editors.inputEditor();}},
	//instanceUrl : {name:'instanceUrl', label:'实例启动Url', value:'', editor:function(){return new snakerflow.editors.selectEditor([{name:'--请选择--',value:''},{name:'实例启动1',value:'/flow!all.action'},{name:'实例启动2',value:'/flow!all.action'},{name:'请假申请实例启动',value:'/plan!allLeave.action'},{name:'工作计划实例启动',value:'/plan!allPlan.action'},{name:'水政执法实例启动',value:'/szzf!allSzzf.action'}]);}},
	instanceUrl : {name:'instanceUrl', label:'实例启动Url', value:'', editor:function(){return new snakerflow.editors.inputEditor();}},
	instanceNoClass : {name:'instanceNoClass', label:'实例编号生成类', value:'', editor:function(){return new snakerflow.editors.inputEditor();}}
});


$.extend(true,snakerflow.config.tools.states,{
			start : {
				showType: 'image',
				type : 'start',
				name : {text:'<<start>>'},
				text : {text:'start'},
				img : {src : 'images/48/start_event_empty.png',width : 48, height:48},
				attr : {width:50 ,heigth:50 },
				props : {
					name: {name:'name',label: '名称', value:'start', editor: function(){return new snakerflow.editors.inputEditor();}},
				    preInterceptors: {name:'preInterceptors', label : '前置拦截器', value:'', editor: function(){return new snakerflow.editors.inputEditor();}},
					postInterceptors: {name:'postInterceptors', label : '后置拦截器', value:'', editor: function(){return new snakerflow.editors.inputEditor();}}
				}},
			end : {
				showType: 'image',
				type : 'end',
				name : {text:'<<end>>'},
				text : {text:'end'},
				img : {src : 'images/48/end_event_terminate.png',width : 48, height:48},
				attr : {width:50 ,heigth:50 },
				props : {
					name: {name:'name',label: '名称', value:'end', editor: function(){return new snakerflow.editors.inputEditor();}},
				    preInterceptors: {name:'preInterceptors', label : '前置拦截器', value:'', editor: function(){return new snakerflow.editors.inputEditor();}},
					postInterceptors: {name:'postInterceptors', label : '后置拦截器', value:'', editor: function(){return new snakerflow.editors.inputEditor();}}
				}},
			task : {
				showType: 'text',
				type : 'task',
				name : {text:'<<task>>'},
				text : {text:'task'},
				img : {src : 'images/48/task_empty.png',width :48, height:48},
				props : {
					name: {name:'name',label: '名称', value:'', editor: function(){return new snakerflow.editors.inputEditor();}},
					displayName: {name:'displayName',label: '显示名称', value:'', editor: function(){return new snakerflow.editors.textEditor();}},
					//form: {name:'form', label : '表单', value:'', editor: function(){return new snakerflow.editors.selectEditor([{name:'借款申请表单',value:'/borrow!apply.action'},{name:'请假申请表单',value:'/leave!apply.action'},{name:'审批表单',value:'/flow!appoval.action'}]);}},
					form: {name:'form', label : '表单', value:'', editor: function(){return new snakerflow.editors.inputEditor();}},
					//assignee: {name:'assignee', label: '参与者', value:'', editor: function(){return new snakerflow.editors.selectEditor([{name:'申请人',value:'apply.operator'},{name:'部门经理审批',value:'approveDept.operator'},{name:'总经理审批',value:'approveBoss.operator'}]);}},
					assignee: {name:'assignee', label: '参与者', value:'', editor: function(){return new snakerflow.editors.selectEditor(arr_selector);}},
					autoOrg: {name:'autoOrg', label : '是否根据组织', value:'', editor: function(){return new snakerflow.editors.selectEditor([{name:'否',value:'N'},{name:'是',value:'Y'}]);}},
					ccperson: {name:'ccperson', label: '抄送人', value:'', editor: function(){return new snakerflow.editors.inputEditor();}},
					assignmentHandler: {name:'assignmentHandler', label: '参与者处理类', value:'', editor: function(){return new snakerflow.editors.inputEditor();}},
					taskType: {name:'taskType', label : '任务类型', value:'', editor: function(){return new snakerflow.editors.selectEditor([{name:'主办任务',value:'Major'},{name:'协办任务',value:'Aidant'}]);}},
					performType: {name:'performType', label : '参与类型', value:'', editor: function(){return new snakerflow.editors.selectEditor([{name:'普通参与',value:'ANY'},{name:'会签参与',value:'ALL'}]);}},
				    preInterceptors: {name:'preInterceptors', label : '前置拦截器', value:'', editor: function(){return new snakerflow.editors.inputEditor();}},
					postInterceptors: {name:'postInterceptors', label : '后置拦截器', value:'', editor: function(){return new snakerflow.editors.inputEditor();}}, 
				    reminderTime: {name:'reminderTime', label : '提醒时间', value:'', editor: function(){return new snakerflow.editors.inputEditor();}},
				    reminderRepeat: {name:'reminderRepeat', label : '重复提醒间隔', value:'', editor: function(){return new snakerflow.editors.inputEditor();}},
					expireTime: {name:'expireTime', label: '期望完成时间', value:'', editor: function(){return new snakerflow.editors.inputEditor();}},
					autoExecute: {name:'autoExecute', label : '是否自动执行', value:'', editor: function(){return new snakerflow.editors.selectEditor([{name:'否',value:'N'},{name:'是',value:'Y'}]);}},
					callback: {name:'callback', label : '回调处理', value:'', editor: function(){return new snakerflow.editors.inputEditor();}}
				}},
			custom : {
				showType: 'text',
				type : 'custom',
				name : {text:'<<custom>>'},
				text : {text:'custom'},
				img : {src : 'images/48/task_empty.png',width :48, height:48},
				props : {
					name: {name:'name',label: '名称', value:'', editor: function(){return new snakerflow.editors.inputEditor();}},
					displayName: {name:'displayName',label: '显示名称', value:'', editor: function(){return new snakerflow.editors.textEditor();}},
					clazz: {name:'clazz', label: '类路径', value:'', editor: function(){return new snakerflow.editors.inputEditor();}},
					methodName: {name:'methodName', label : '方法名称', value:'', editor: function(){return new snakerflow.editors.inputEditor();}},
					args: {name:'args', label : '参数变量', value:'', editor: function(){return new snakerflow.editors.inputEditor();}},
				    preInterceptors: {name:'preInterceptors', label : '前置拦截器', value:'', editor: function(){return new snakerflow.editors.inputEditor();}},
					postInterceptors: {name:'postInterceptors', label : '后置拦截器', value:'', editor: function(){return new snakerflow.editors.inputEditor();}}
				}},
			subprocess : {
				showType: 'text',
				type : 'subprocess',
				name : {text:'<<subprocess>>'},
				text : {text:'subprocess'},
				img : {src : 'images/48/task_empty.png',width :48, height:48},
				props : {
					name: {name:'name',label: '名称', value:'', editor: function(){return new snakerflow.editors.inputEditor();}},
					displayName: {name:'displayName',label: '显示名称', value:'', editor: function(){return new snakerflow.editors.textEditor();}},
					processName: {name:'processName', label: '子流程名称', value:'', editor: function(){return new snakerflow.editors.inputEditor();}},
				    preInterceptors: {name:'preInterceptors', label : '前置拦截器', value:'', editor: function(){return new snakerflow.editors.inputEditor();}},
					postInterceptors: {name:'postInterceptors', label : '后置拦截器', value:'', editor: function(){return new snakerflow.editors.inputEditor();}}
				}},
			decision : {
				showType: 'image',
				type : 'decision',
				name : {text:'<<decision>>'},
				text : {text:'decision'},
				img : {src : 'images/48/gateway_exclusive.png',width :48, height:48},
				props : {
					name: {name:'name',label: '名称', value:'', editor: function(){return new snakerflow.editors.inputEditor();}},
					expr: {name:'expr',label: '决策表达式', value:'', editor: function(){return new snakerflow.editors.inputEditor();}},
					handleClass: {name:'handleClass', label: '处理类名称', value:'', editor: function(){return new snakerflow.editors.inputEditor();}},
				    preInterceptors: {name:'preInterceptors', label : '前置拦截器', value:'', editor: function(){return new snakerflow.editors.inputEditor();}},
					postInterceptors: {name:'postInterceptors', label : '后置拦截器', value:'', editor: function(){return new snakerflow.editors.inputEditor();}}
				}},
			fork : {
				showType: 'image',
				type : 'fork',
				name : {text:'<<fork>>'},
				text : {text:'fork'},
				img : {src : 'images/48/gateway_parallel.png',width :48, height:48},
				props : {
					name: {name:'name',label: '名称', value:'', editor: function(){return new snakerflow.editors.inputEditor();}},
				    preInterceptors: {name:'preInterceptors', label : '前置拦截器', value:'', editor: function(){return new snakerflow.editors.inputEditor();}},
					postInterceptors: {name:'postInterceptors', label : '后置拦截器', value:'', editor: function(){return new snakerflow.editors.inputEditor();}}
				}},
			join : {
				showType: 'image',
				type : 'join',
				name : {text:'<<join>>'},
				text : {text:'join'},
				img : {src : 'images/48/gateway_parallel.png',width :48, height:48},
				props : {
					name: {name:'name',label: '名称', value:'', editor: function(){return new snakerflow.editors.inputEditor();}},
				    preInterceptors: {name:'preInterceptors', label : '前置拦截器', value:'', editor: function(){return new snakerflow.editors.inputEditor();}},
					postInterceptors: {name:'postInterceptors', label : '后置拦截器', value:'', editor: function(){return new snakerflow.editors.inputEditor();}}
				}}
});
})(jQuery);