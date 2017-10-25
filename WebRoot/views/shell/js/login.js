// 该js处理系统登入的业务
// 主要是页面的登入，注销，session失效的处理
// author 0188

// 全局变量
var LOGIN = {};

window.onload = function() {
    // 初始化将焦点设置在用户名输入的地方
    $("#txt_userid").focus();

    // 绑定enter键切换输入框
    LOGIN.bindKeyPress("txt_userid", "txt_pwd");
    LOGIN.bindKeyPress("txt_pwd", "txt_validate");
    LOGIN.bindKeyLogin("txt_validate");

    // 绑定登入事件
    LOGIN.bindLogin();
};

/**
 * 绑定enter键切换输入框
 * 
 * @param from 切换输入框的起始控件id
 * @param to 切换输入框的目的控件id
 */
LOGIN.bindKeyPress = function(from, to) {
    $("#" + from).on("keypress",function(evt) {
        evt = (evt) ? evt : ((window.event) ? window.event : "");
        var key = evt.keyCode?evt.keyCode:evt.which;
        if (key==13){
            $("#" + to).focus();
        };
    });
};

/**
 * 绑定enter键登入系统
 * 
 * @param id 控件id
 */
LOGIN.bindKeyLogin = function(id) {
    $("#" + id).on("keypress",function(evt) {
        evt = (evt) ? evt : ((window.event) ? window.event : "");
        var key = evt.keyCode?evt.keyCode:evt.which;
        if (key==13){
            $( "#btn_login" ).click();
        };
    });
};

/**
 * 绑定登入事件
 * 
 */
LOGIN.bindLogin = function() {
    $( "#btn_login" )
        .button({ icons: {primary:'ui-icon-carat-1-e'} })
        .on("click",function(){
            // 检查表单是否为空
            __isBlank("txt_userid", "用户名不能为空！");
            __isBlank("txt_pwd","密码不能为空！");
            //__isBlank();

            // 实现登入异步请求
            $.ajax({
                url:"../shell/login.action",
                data:
                    {
                        action   : "login",
                        u_id     : $.trim($("#txt_userid").val()),
                        password : $.trim($("#txt_pwd").val()),
                        validate : $.trim($("#txt_validate").val())
                    },
                type:"POST",
                success:function(data){
                    // 数据格式化成json对象
                    data = JSON.parse(data);
                    // 登入成功的情况
                    if(data.result=="Y"){
                        // 跳转到操作界面
                        window.location.href = "../shell/deskTop.action";
                    }else{
                        // 清空表单
                        $("#txt_validate").val("");
                        // 显示错误信息
                        if(data.hint!=""){
                            $("#span_hint").html(data.hint);
                            $("#span_copyright").hide();
                            $("#span_wran").show();
                        }else{
                            $("#span_copyright").show();
                            $("#span_wran").hide();
                        }
                        // 刷新随机验证码
                        $("#changeValidate").attr("src", '../common/validate!validateImage.action?rand='+Math.round(Math.random()*10000));
                    }
                },
                error:function(){
                    showMsg("登录出错！");
                }
            });
            return false;
        });
};

// check空域
function __isBlank(id, message) {
    if($.trim($("#" + id).val())==""){
        showMsg(message);
        return;
    }
}
