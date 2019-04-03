package com.shsxt.crm.dao;

import com.shsxt.crm.base.BaseDao;
import com.shsxt.crm.dto.ModuleDto;
import com.shsxt.crm.po.Module;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ModuleMapper extends BaseDao<Module> {

    public List<ModuleDto> queryAllModuleByRoleId(Integer roleId);

    public List<Map> queryModulesByGrade(Integer grade);
    //添加模块
    public Module queryModuleByName(String moduleName);

    public Module queryModuleByOptValue(String optValue);

    //删除模块
    public Integer queryTotalByOptValue(String optValue);

    public Integer deleteBatchByOptValue(String optValue);

}