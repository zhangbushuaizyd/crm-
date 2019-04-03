package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.contains.CrmConstant;
import com.shsxt.crm.dto.UserDto;
import com.shsxt.crm.exceptions.ParamsException;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.model.UserInfo;
import com.shsxt.crm.po.User;
import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.query.UserQuery;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController{
    @Resource
    private UserService userService;

    //删除用户
    //批量的删除
    @RequestMapping("deleteUsers")
    @ResponseBody
    public ResultInfo deleteSaleChanceBatch(Integer[] ids){
        userService.deleteUsers(ids);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }

    //添加或者更新用户
    @RequestMapping("saveOrUpdateUser")
    @ResponseBody
    public ResultInfo saveOrUpdateUser(UserDto user, Integer[] roleIds){
        userService.saveOrUpdateUser(user,roleIds);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping("login")
    @ResponseBody
    public ResultInfo login(String userName,String userPwd){
        //try {
        //int i = 1/0;
           UserInfo userInfo= userService.login(userName,userPwd);
           return success("登录成功",userInfo);
        //} catch (ParamsException e) {
        //    e.printStackTrace();
        //   return success(300,e.getMsg());
        //}
        //catch (Exception e) {
        //    e.printStackTrace();
        //    return success(300,e.getMessage());
        //}
    }

    @RequestMapping("updateUserPwd")
    @ResponseBody
    public ResultInfo updateUserPwd(String oldPassword,
                                    String newPassword,
                                    String confirmPassword, HttpServletRequest request){
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request); //获取用户id
        //try {
            //将信息传递给service层
            userService.updateUserPwd(oldPassword,newPassword,confirmPassword,userId);
            return success("修改成功!");
        //}  catch (ParamsException e) {
        //    e.printStackTrace();
        //    return success(300,e.getMsg());
        //}
        //catch (Exception e) {
        //    e.printStackTrace();
        //    return success(300,e.getMessage());
        //}
    }
    //进入用户管理模块

    @RequestMapping("index")
    public String index(){
        return "user";
    }
    //用户管理查询
    @RequestMapping("queryUsersByParams")
    @ResponseBody
    public Map<String,Object> queryUsersByParams(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows, UserQuery query){
        query.setPageNum(page);
        query.setPageSize(rows);
        return userService.queryForPage(query);
    }
}
