package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.contains.CrmConstant;
import com.shsxt.crm.dto.ModuleDto;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.po.Module;
import com.shsxt.crm.po.SaleChance;
import com.shsxt.crm.query.ModuleQuery;
import com.shsxt.crm.service.ModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("module")
public class ModuleController extends BaseController{
    @Resource
    private ModuleService moduleService;
    @RequestMapping("queryAllModuleByRoleId")
    @ResponseBody
    public List<ModuleDto> queryAllModuleByRoleId(Integer roleId){
        return moduleService.queryAllModuleByRoleId(roleId);
    }

    @RequestMapping("index")
    public String index(HttpServletRequest request){
        return "module";
    }

    //根据前台table请求查询参数
    @RequestMapping("queryModulesByParams")
    @ResponseBody
    public Map<String,Object> queryModulesByParams(ModuleQuery moduleQuery, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows){
        moduleQuery.setPageNum(page);
        moduleQuery.setPageSize(rows);
        return  moduleService.queryForPage(moduleQuery);
    }
    //下拉回显
    @RequestMapping("queryModulesByGrade")
    @ResponseBody
    public List<Map> queryModulesByGrade(Integer grade){
        return moduleService.queryModulesByGrade(grade);
    }

    //模块的添加操作
    @RequestMapping("saveOrUpdateModule")
    @ResponseBody
    public ResultInfo saveOrUpdateModule(Module module){
        moduleService.saveOrUpdataMoudle(module);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }

    //模块的删除操作
    @RequestMapping("deleteModule")
    @ResponseBody
    public ResultInfo deleteModule(Integer[] ids){
        moduleService.deleteModule(ids);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }
}
