package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.contains.CrmConstant;
import com.shsxt.crm.dao.CustomerLossMapper;
import com.shsxt.crm.dao.CustomerMapper;
import com.shsxt.crm.po.Customer;
import com.shsxt.crm.po.CustomerLoss;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.MathUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService extends BaseService<Customer> {

    @Resource
    private CustomerMapper customerMapper;
    @Resource
    private CustomerLossMapper customerLossMapper;

public void addLoseCustomers(){
    /**
     * 查询所有的流失客户
     * 批量插入客户流失表
     */
    List<Customer> customerList = customerMapper.queryLossCustomers();
    System.out.println("流失客户预警人户:"+customerList.size());
    if (!CollectionUtils.isEmpty(customerList)){
        //存客户流失列表
        List<CustomerLoss> customerLossList = new ArrayList<>();
        //遍历循环加入customerList
        for(Customer customer : customerList){
            CustomerLoss customerLoss = new CustomerLoss();
            customerLoss.setCusNo(customer.getKhno());
            customerLoss.setCusName(customer.getName());
            customerLoss.setCusManager(customer.getCusManager());
            customerLoss.setState(0); //预流失
            customerLoss.setIsValid(1); //有效
            customerLoss.setUpdateDate(new Date());
            customerLoss.setCreateDate(new Date());
            customerLossList.add(customerLoss);
        }
        AssertUtil.isTrue(customerLossMapper.saveBatch(customerLossList)<customerLossList.size(),CrmConstant.OPS_FAILED_MSG);
        AssertUtil.isTrue(customerMapper.updateCustomerState(customerList)<customerList.size(),CrmConstant.OPS_FAILED_MSG);
    }
}




    public List<Map> queryDataDicsByDicName(String dicName){
        return customerMapper.queryDataDicsByDicName(dicName);
    }

    public void saveOrUpdateCustomer(Customer customer){
        /**
         * 参数校验
         * name 非空 且唯一
         * cusManger 非空
         * phone  非空
         * fr 非空
         * 2.执行添加或者更新
         * id是否为空
         */
        AssertUtil.isTrue(StringUtils.isBlank(customer.getName()),"客户名称为空");
        AssertUtil.isTrue(StringUtils.isBlank(customer.getCusManager()),"客户经理为空");
        AssertUtil.isTrue(StringUtils.isBlank(customer.getPhone()),"联系方式为空");
        AssertUtil.isTrue(StringUtils.isBlank(customer.getFr()),"法人代表为空");

        //Customer temp = customerMapper.queryCustomerByCusName(customer.getName());
        customer.setUpdateDate(new Date());
        Integer id = customer.getId();
        if (null==id){
            /**
             * 补全参数
             * 1.客户编号
             * 2.是否流失
             * 3.是否有效
             * 4时间
             */
           customer.setKhno(MathUtil.genereateKhCode());
           customer.setState(0);
           customer.setIsValid(1);
           customer.setCreateDate(new Date());
            AssertUtil.isTrue(customerMapper.save(customer)<1,CrmConstant.OPS_FAILED_MSG);

        }else{

            AssertUtil.isTrue(customerMapper.update(customer)<1,CrmConstant.OPS_FAILED_MSG);
        }
    }

   public List<Map> queryLevelNums(){
    return customerMapper.queryLevelNums();
   }
}
