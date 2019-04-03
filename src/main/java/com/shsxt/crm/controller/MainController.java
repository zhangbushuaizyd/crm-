package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.contains.CrmConstant;
import com.shsxt.crm.po.User;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController extends BaseController {
    @Resource
    private UserService userService;
    @RequestMapping("main")
    public String index(HttpServletRequest request){
        /**
         * 查询用户,存放进入request作用域中
         */
        //int i = 1/0;
        //封装了LoginUserUtil 直接从作用域中取得useerId
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        User user = userService.queryById(userId);
        request.setAttribute("user",user);


        //查询权限列表
        List<String> permissions = userService.queryAllAclValueByUserId(userId);

        System.out.println("===================================");
        System.out.println(permissions.toString());
        System.out.println("===================================");

        //放入session中
        request.getSession().setAttribute(CrmConstant.USER_PERMISSIONS,permissions);
        return "main";
    }


}
