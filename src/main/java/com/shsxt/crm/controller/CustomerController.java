package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.contains.CrmConstant;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.po.Customer;
import com.shsxt.crm.query.CustomerQuery;
import com.shsxt.crm.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {
   @Resource
    private CustomerService customerService;

    @RequestMapping("index")
    public String index(){
        return "customer";
    }

    //分页操作
    @RequestMapping("queryCustomersByParams")
    @ResponseBody
    public Map<String,Object> queryCustomersByParams(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows, CustomerQuery query){
        query.setPageNum(page);
        query.setPageSize(rows);
        return customerService.queryForPage(query);
    }

    @RequestMapping("queryDataDicsByDicName")
    @ResponseBody
    public List<Map> queryDataDicsByDicName(String dicName){
        return customerService.queryDataDicsByDicName(dicName);
    }
    @RequestMapping("saveOrUpdateCustomer")
    @ResponseBody
    public ResultInfo saveOrUpdateCustomer(Customer customer){
        customerService.saveOrUpdateCustomer(customer);
        return success("操作成功");
    }

    @RequestMapping("deleteCustomer")
    @ResponseBody
    public ResultInfo deleteCustomer(Integer[] ids){
        customerService.deleteBatch(ids);
        return success("操作成功");
    }

    @RequestMapping("addLossCustomers")
    @ResponseBody
    public ResultInfo addLossCustomers(){
        customerService.addLoseCustomers();
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }
}
