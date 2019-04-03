package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.contains.CrmConstant;
import com.shsxt.crm.dao.ModuleMapper;
import com.shsxt.crm.dao.PermissionMapper;
import com.shsxt.crm.dto.ModuleDto;
import com.shsxt.crm.po.Module;
import com.shsxt.crm.utils.AssertUtil;
import com.sun.org.apache.bcel.internal.generic.MONITORENTER;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ModuleService extends BaseService<Module> {

    @Resource
    private ModuleMapper moduleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    //刪除操作
    public void deleteModule(Integer[] ids){
        AssertUtil.isTrue(null==ids || ids.length<1, "请选择删除的模块");
        for(Integer moduleId:ids){
            Module module = moduleMapper.queryById(moduleId);
            //当前模块表的删除
            String optValue = module.getOptValue();
            //自己写的
            Integer total = moduleMapper.queryTotalByOptValue(optValue);
            if (total>0){
                AssertUtil.isTrue(moduleMapper.deleteBatchByOptValue(optValue)<total,CrmConstant.OPS_FAILED_MSG);
            }
            //权限表的级联删除
            Integer total2 = permissionMapper.queryModulesByAclValue(optValue);
            if (total2>0){
                AssertUtil.isTrue(permissionMapper.deleteModulesByAclValue(optValue)<total2,CrmConstant.OPS_FAILED_MSG);
            }
        }
    }
    public void saveOrUpdataMoudle(Module module){
        /**
         * 1.校验参数
         * 2.补全参数
         * 3.判断添加或者更新
         * 4.执行操作
         *///补全参数

        checkMudleParams(module);
        module.setUpdateDate(new Date());
        Integer id = module.getId();
        if (null ==module.getId() ){
            module.setIsValid((byte)1);
            module.setCreateDate(new Date());
            AssertUtil.isTrue(moduleMapper.save(module)<1, CrmConstant.OPS_FAILED_MSG);
        }
    }

    private void checkMudleParams(Module module) {
        //1.非空检验
        String moduleName = module.getModuleName();
        AssertUtil.isTrue(StringUtils.isBlank(moduleName),"模块名称为空!");
        String optValue = module.getOptValue();
        AssertUtil.isTrue(StringUtils.isBlank(optValue),"权限码为空!");
        //2.唯一校验
        AssertUtil.isTrue(null != moduleMapper.queryModuleByName(moduleName),"模块名称已存在!");
        AssertUtil.isTrue(null!=moduleMapper.queryModuleByOptValue(optValue),"权限码已存在!");
        //确定层级
        Integer grade = module.getGrade();
        AssertUtil.isTrue( null == grade,"菜单层级为空");

        Integer len = (grade +1)*2;
        AssertUtil.isTrue(len !=optValue.length(),"权限码不正确,应为:"+len+"位");

        if (grade > 0){
            Module parentModule = moduleMapper.queryById(module.getParentId()); //父模块
            Integer parentGrade = parentModule.getGrade();
            AssertUtil.isTrue(grade-parentGrade!=1,"菜单层级不正确!");

            //权限码格式
            String parentOptValue = parentModule.getOptValue();
            AssertUtil.isTrue(optValue.indexOf(parentOptValue)!=0,"权限格式不正确,格式应为"+parentOptValue+"XX");


        }else{
            module.setParentId(null);
        }
    }


    public List<ModuleDto> queryAllModuleByRoleId(Integer roleId){
        return moduleMapper.queryAllModuleByRoleId(roleId);
    }

    //下拉的回显
    public List<Map> queryModulesByGrade(Integer grade){
        return moduleMapper.queryModulesByGrade(grade);
    }
}

