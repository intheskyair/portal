// 权限编辑对话框
var dlg_head_pw;
var head = {
	// 弹出修改密码的对话框
	changePw : function() {
		var dlgParent = $("#dlg_head_pw").parent();
		var dlgOwn = $("#dlg_head_pw").clone();
		dlg_head_pw = $("#dlg_head_pw").dialog({
			autoOpen: false,
			height:200,
			width:450,
			show: "slide",
			modal:true,
			cache:false,
			hide: "explode",
			title:'修改密码',
			buttons:{
				"保存":function(){
					head.saveData();
				}
			},
            close : function() {
            	dlgOwn.appendTo(dlgParent);
            	$(this).dialog("destroy").remove();
            }
		});
		
		dlg_head_pw.dialog("option", "title", '修改密码').dialog("open");
	},
	// 保存修改的密码
	saveData : function() {
		var oldPw = $("#old_pw_edit").val();
		var newPw = $("#new_pw_edit").val();
		var confirmPw = $("#confirm_pw_edit").val();

		var queryP = {};
		queryP.oldPw = $.trim(oldPw);
		queryP.newPw = $.trim(newPw);
		queryP.confirmPw = $.trim(confirmPw);
		
		
			// 输入密码验证
		if (head.validatePw(queryP)) {
			this.checkPwIsValid(queryP);
		}
	},
	// 验证密码
	validatePw : function(param) {
		//var flg1 = value.length>=8&&/[0-9]+/.test(value)&&/[a-zA-Z]+/.test(value)&&/[^0-9a-zA-Z]/.test(value);
		var flg1 = param.newPw.length >= 6 && param.newPw.length <=20;
		var flg2 = param.newPw != param.oldPw;
		var flg3 = param.newPw == param.confirmPw;
		var flg4 = param.confirmPw.length >= 6 && param.confirmPw.length <= 20;
		var reg = /^((?=.*[aA-zZ])(?=.*\d)(?=.*[\x21-\x7e]+)).{6,20}$/;
		//var reg = /^([a-zA-Z0-9!@#$%^&*()_+|{}><\-\]\\[\/]).{6,20}$/;
		/*if (flg1 && flg2 && flg3 && flg4) {
			return true;
		}*/
		
		if( param.oldPw.length <= 0){
			showMsg('原密码不能为空！');
			return false;
		}else if(param.newPw.length <= 0){
			showMsg('新密码不能为空！');
			return false;
		}else if(param.confirmPw.length <= 0){
			showMsg('确认密码不能为空！');
			return false;
		}
		
		if (!flg2) {
			showMsg('新密码与旧密码不能一样！');
			return false;
		}
		if (!(flg1 && flg4)){
			showMsg('新密码必须6-20位！');
			return false;
		}
		if (!reg.test(param.newPw)){
			showMsg('密码必须数字,字母,特殊字符组合的6-20位');
			return false;
		}
		if (!flg3) {
			showMsg('新密码与确认密码不一致！');
			return false;
		} 
		return true;
	},
	getVersion : function() 
	{
		var queryP = {};

		$.ajax({
				type: "POST",
				url: "../queryUser!getversion.action",
				cache: false,
				data: queryP,
				success: function(msg)
				{
				    //var str ="渲云监控中心_"+msg;
					var str ="常州市水利局办公系统登录";
					$('#version').html(str);
				},
				error:function()
				{
					var str ="常州市水利局办公系统登录";
					$('#version').html(str);
					
				}
			});
	},
	checkPwIsValid : function(queryP){
		$.ajax({
			type: "POST",
			url: "../queryUser!checkPwIsValid.action",
			cache: false,
			data: queryP,
			success: function(msg)
			{
				if(msg == "no"){
					showMsg("旧密码错误，请重新确认");
					head.isPwRight = false;
				}else if(msg == "yes"){
					head.isPwRight = true;
					if(head.isPwRight){
						$.ajax({
							type: "POST",
							url: "../queryUser!updatePw.action",
							cache: false,
							data: queryP,
							success: function(msg){
								if(msg=="yes"){
									showMsg("密码修改成功");
								} else {
									showMsg("密码修改失败");
								}
								$('#dlg_head_pw').dialog('close');
							}
						});
					} else {
						showMsg("修改失败，联系管理员");
					}
				}
			}
		});
		
	},
	isPwRight : false
};


$(document).ready(function()
{
    head.getVersion();
	$("#changePw").click(function()
	{
				head.changePw();
	});
	
});


