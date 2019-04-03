package com.shsxt.crm.interceptors;

import com.shsxt.crm.contains.CrmConstant;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.LoginUserUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       //从cookie里面获取用户id ,1.cookie存在,并且cookie里面的信息是正确的
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        AssertUtil.isNotLogin(null==userService.queryById(userId) || (null == userId),CrmConstant.USER_NOT_LOGIN_MSG);
        return true;
    }
}
