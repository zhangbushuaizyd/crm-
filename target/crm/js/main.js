function openTab(text, url, iconCls){
    if($("#tabs").tabs("exists",text)){
        $("#tabs").tabs("select",text);
    }else{
        var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='" + url + "'></iframe>";
        $("#tabs").tabs("add",{
            title:text,
            iconCls:iconCls,
            closable:true,
            content:content
        });
    }
}

// 退出操作
function logout() {
    /**
     * 1.清除cookie
     * 2.跳转登录页
     */
    $.messager.confirm('来自crm','确定退出?',function (r) {
        if (r){
            $.removeCookie("userIdStr");
            $.removeCookie("userName");
            $.removeCookie("realName");
            window.location.href=ctx+'/index';
        }
    });
}

// 修改密码
//打开窗口
function openPasswordModifyDialog() {
    $('#dlg').dialog('open');
}
function modifyPassword() {
    $('#fm').form('submit',{
        url: ctx+'/user/updateUserPwd',
        onSubmit:function () {
            return $(this).form('validate');
        },
        success: function (data) {
            //console.log(data);
            // 手动解析json 框架是json字符串 手动转换成json对象
            data = JSON.parse(data);
            if(data.code==200){
                $.messager.alert('来自Crm',data.msg,'info',function () {
                    //登录成功,将原有的cookie信息删除 并跳转回登录页面
                    $.removeCookie("userIdStr");
                    $.removeCookie("userName");
                    $.removeCookie("realName");

                    window.location.href = ctx + '/index';
                });
            }else{
                $.messager.alert('来自Crm',data.msg,'error');
            }
        }
    });
}

//关闭按钮
function closePasswordModifyDialog() {
    $('#dlg').dialog('close');
}