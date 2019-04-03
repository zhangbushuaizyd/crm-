package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.contains.CrmConstant;
import com.shsxt.crm.dao.ModuleMapper;
import com.shsxt.crm.dao.PermissionMapper;
import com.shsxt.crm.dao.RoleMapper;
import com.shsxt.crm.dao.UserRoleMapper;
import com.shsxt.crm.po.Permission;
import com.shsxt.crm.po.Role;
import com.shsxt.crm.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class RoleService extends BaseService<Role> {
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private ModuleMapper moduleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    //角色模块的删除操作
    public void deleteRoleBatch(Integer[] ids){
        if (null!=ids && ids.length>0){
            //删除用户角色 即假删除将is_valid=0 级联t_user_role表以及permission表的真删除
            for (Integer roleId:ids){
                //删除用户角色
                AssertUtil.isTrue(roleMapper.delete(roleId)<0,CrmConstant.OPS_FAILED_MSG);
                //删除权限 permission表 一个用户有多个权限 删除的要等于那个权限才行
                Integer num1 = permissionMapper.queryModulesByRoleId(roleId);
                if (num1>0){
                    AssertUtil.isTrue(permissionMapper.deleteModulesByRoleId(roleId)<num1,CrmConstant.OPS_FAILED_MSG);
                }
                //查询该角色下的用户数
                Integer num2 = userRoleMapper.queryUserRolesByRoleId(roleId);
                if (num2>0){
                    AssertUtil.isTrue(userRoleMapper.deleteUserRolesByRoleId(roleId)<num2,CrmConstant.OPS_FAILED_MSG);
                }

            }

        }
    }




    //角色管理的授权操作
    public void doGrant(Integer roleId, Integer[] moduleIds){
            AssertUtil.isTrue(null == roleId,"角色Id为空");
            AssertUtil.isTrue(null == roleMapper.queryById(roleId),"角色不存在");
            if (null != moduleIds && moduleIds.length>0){
                //角色授权,先查询权限,有删除,再添加,先查询权限 无则直接添加
                Integer num = permissionMapper.queryModulesByRoleId(roleId);

                if (num>0){
                    AssertUtil.isTrue(permissionMapper.deleteModulesByRoleId(roleId)<num,CrmConstant.OPS_FAILED_MSG);
                }

                List<Permission> permissions = new ArrayList<>();
                for (Integer moduleId:moduleIds){
                    Permission permission = new Permission();
                    permission.setRoleId(roleId);
                    permission.setCreateDate(new Date());
                    permission.setUpdateDate(new Date());
                    permission.setModuleId(moduleId);
                    //设置权限码
                    permission.setAclValue(moduleMapper.queryById(moduleId).getOptValue());

                    permissions.add(permission);
                }
                AssertUtil.isTrue(permissionMapper.saveBatch(permissions)<permissions.size(),CrmConstant.OPS_FAILED_MSG);
            }
    }



    //角色管理的添加和删除
    public void saveOrUpdateRole(Role role){
        /**
         * 参数非空校验
         * 角色名称非空,不能重复
         * 根据id判断添加或者是更新
         */
        String roleName = role.getRoleName();
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()),"角色信息不能为空!");
        //Role temp = roleMapper.queryRoleByName(role.getRoleName());

        role.setUpdateDate(new Date());
        Integer id = role.getId();

        if (null==id){
            //添加
            AssertUtil.isTrue(null!=roleMapper.queryRoleByName(roleName),"角色信息已存在!");
            role.setCreateDate(new Date());
            role.setIsValid(1);
            AssertUtil.isTrue(roleMapper.save(role)<1,CrmConstant.OPS_FAILED_MSG);
        }else{
            //更新操作
            Role role2 = roleMapper.queryById(id);
           if (!roleName.equals(role2.getRoleName())){
               AssertUtil.isTrue(null!=roleMapper.queryRoleByName(roleName),"角色已存在");
           }
           AssertUtil.isTrue(roleMapper.update(role)<1,CrmConstant.OPS_FAILED_MSG);
        }

    }

    //用户管理模块
    public List<Map> queryAllRoles(){
        return roleMapper.queryAllRoles();
    }



}
