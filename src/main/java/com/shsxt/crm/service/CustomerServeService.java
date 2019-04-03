package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.contains.CrmConstant;
import com.shsxt.crm.dao.CustomerServeMapper;
import com.shsxt.crm.dao.UserMapper;
import com.shsxt.crm.dto.UserDto;
import com.shsxt.crm.po.CustomerServe;
import com.shsxt.crm.utils.AssertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CustomerServeService extends BaseService<CustomerServe> {

    @Resource
    private CustomerServeMapper customerServeMapper;
    @Resource
    private UserMapper userMapper;

    //添加或者更新
    public void saveOrUpdateCustomerServe(CustomerServe customerServe,Integer userId){
        customerServe.setUpdateDate(new Date());
        Integer id = customerServe.getId();
        UserDto userDto = userMapper.queryById(userId);
        if (null == id){
            customerServe.setCreatePeople(userDto.getUserName()); //创建人
            customerServe.setState("1");  //初始状态
            customerServe.setIsValid(1); //有效状态
            customerServe.setCreateDate(new Date());
            AssertUtil.isTrue(customerServeMapper.save(customerServe)<1, CrmConstant.OPS_FAILED_MSG);
        }else {
            /**
             * state的变化1->2     2->3
             */
            String state = customerServe.getState();

            System.out.println("=========================state================"+state);
            if (state.equals("1")){
                customerServe.setState("2");
                customerServe.setAssignTime(new Date());    //分配时
            }else if (state.equals("2")){
                customerServe.setState("3");
                customerServe.setServiceProceTime(new Date()); //处理时间
                customerServe.setServiceProcePeople(userDto.getUserName()); //处理人
            }else if (state.equals("3")){
                customerServe.setState("4");
            }
AssertUtil.isTrue(customerServeMapper.update(customerServe)<1,CrmConstant.OPS_FAILED_MSG);
        }
    }
}
