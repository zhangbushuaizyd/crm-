package com.shsxt.crm.dao;

import com.shsxt.crm.base.BaseDao;
import com.shsxt.crm.po.Customer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CustomerMapper extends BaseDao <Customer>{
    public List<Map> queryDataDicsByDicName(String dicName);
    //查询所有的流失客户
    public List<Customer> queryLossCustomers();
    //查询到所有的流失客户进入客户流失表后更新状态值,防止再次查询出错
    public Integer updateCustomerState(List<Customer> customers);

    //查询客户总数量
    public List<Map> queryLevelNums();
}