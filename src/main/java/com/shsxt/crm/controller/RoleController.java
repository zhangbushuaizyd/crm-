package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.contains.CrmConstant;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.po.Role;
import com.shsxt.crm.po.SaleChance;
import com.shsxt.crm.po.User;
import com.shsxt.crm.query.RoleQuery;
import com.shsxt.crm.query.UserQuery;
import com.shsxt.crm.service.RoleService;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController{
    @Resource
    private RoleService roleService;

    //权限树的操作
    @RequestMapping("doGrant")
    @ResponseBody
    public ResultInfo doGrant(Integer roleId,Integer[] moduleIds){
        roleService.doGrant( roleId,moduleIds);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }

    //删除角色
    //批量的删除
    @RequestMapping("deleteRoleBatch")
    @ResponseBody
    public ResultInfo deleteRoleBatch(Integer[] ids){
        roleService.deleteRoleBatch(ids);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Map> queryAllRoles(){
        return roleService.queryAllRoles();
    }

    @RequestMapping("index")
    public String index(HttpServletRequest request){
        return "role";
    }

    //根据前台table请求查询参数
    @RequestMapping("queryRolesByParams")
    @ResponseBody
    public Map<String,Object> queryRolesByParams(RoleQuery roleQuery, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows){
        roleQuery.setPageNum(page);
        roleQuery.setPageSize(rows);
        return  roleService.queryForPage(roleQuery);
    }

    @RequestMapping("saveOrUpdateRole")
    @ResponseBody
    public ResultInfo saveOrUpdateRole(Role role){
        roleService.saveOrUpdateRole(role);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }

}
