package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.contains.CrmConstant;
import com.shsxt.crm.dao.CustomerReprieveMapper;
import com.shsxt.crm.po.CustomerReprieve;
import com.shsxt.crm.utils.AssertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CustomerReprieveService extends BaseService<CustomerReprieve> {
    @Resource
    private CustomerReprieveMapper customerReprieveMapper;

    //添加或者删除
    public void saveOrUpdateCustomerReprieve(CustomerReprieve customerReprieve) {
        customerReprieve.setUpdateDate(new Date());
        Integer id = customerReprieve.getId();
        if (null == id) {
            customerReprieve.setIsValid(1);
            customerReprieve.setCreateDate(new Date());
            AssertUtil.isTrue(customerReprieveMapper.save(customerReprieve) < 1, CrmConstant.OPS_FAILED_MSG);
        } else {
            AssertUtil.isTrue(customerReprieveMapper.update(customerReprieve) < 1, CrmConstant.OPS_FAILED_MSG);

        }
    }
}
