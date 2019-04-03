function login() {
    // 1.获取参数
    // 2.校验参数
    // 3.发送请求
    // 4.成功回调处理
    var userName = $('#username').val();
    var userPwd = $('#password').val();
    // if (isEmpty(userName)){
    //     alert("用户名为空");
    //     return;
    // }
    // if (isEmpty(userPwd)){
    //     alert("用户密码为空");
    //     return;
    // }

    if(isEmpty(userName)){
        alert("用户名为空");
        return;
    }
    if(isEmpty(userPwd)){
        alert("密码为空");
        return;
    }

    $.ajax({
        url:ctx+"/user/login",
        type:'post',
        data:{
            userName:userName,
            userPwd:userPwd,
        },
        success:function (data) {
            if (data.code==200){
                alert(data.msg);    //信息为登录成功
                //把用户信息存入cookie
                $.cookie('userIdStr',data.result.userIdStr);
                $.cookie('userName',data.result.userName);
                $.cookie('realName',data.result.realName);
                //跳转到主页
                window.location.href=ctx+`/main`;
            }else {
                alert(data.msg);
            }
        }
    });
}