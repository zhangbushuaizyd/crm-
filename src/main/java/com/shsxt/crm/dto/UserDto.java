package com.shsxt.crm.dto;

import com.shsxt.crm.po.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDto  extends User implements Serializable{

    private static final long serialVersionUID = 6054138338550580247L;
    private String roleName;
    private String roleIdsStr; //接受字符串1,2,3
    private List<Integer> roleIds = new ArrayList<>();  //存id[1,2,3]

    public String getRoleName() {
        return roleName;
    }

    public String getRoleIdsStr() {
        return roleIdsStr;
    }

    public void setRoleIdsStr(String roleIdsStr) {
        this.roleIdsStr = roleIdsStr;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
