package com.shsxt.crm.dao;

import com.shsxt.crm.base.BaseDao;
import com.shsxt.crm.dto.UserDto;
import com.shsxt.crm.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseDao<UserDto> {

    //登录查询用户名
    public User queryUserByName(String userName);

    //修改密码
    public Integer updateUserPwd(@Param("userPwd") String userPwd,@Param("id") Integer id);

    //查询所有的权限码
    public List<String> queryAllAclValueByUserId(Integer userId);

}