package com.shsxt.crm.dao;

import com.shsxt.crm.base.BaseDao;
import com.shsxt.crm.po.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleMapper extends BaseDao<UserRole> {
    public Integer queryUserRolesByUserId(Integer userId);
    public Integer deleteUserRolesByUserId(Integer userId);

    public Integer deleteUserRolesByRoleId(Integer roleId);
    public Integer queryUserRolesByRoleId(Integer roleId);

}