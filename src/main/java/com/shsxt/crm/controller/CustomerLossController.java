package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.contains.CrmConstant;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.po.CustomerLoss;
import com.shsxt.crm.query.CustomerLossQuery;
import com.shsxt.crm.service.CustomerLossService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("customerLoss")
public class CustomerLossController extends BaseController {
    @Resource
    private CustomerLossService customerLossService;


    @RequestMapping("index")
    private String index() {
        return "customer_loss";
    }


    @RequestMapping("queryCustomerLossByParams")
    @ResponseBody
    public Map<String, Object> queryCustomerLossByParams(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows, CustomerLossQuery query) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return customerLossService.queryForPage(query);
    }

    //更改客户流失状态
    @RequestMapping("updateCustomerLoss")
    @ResponseBody
    public ResultInfo updateCustomerLoss(CustomerLoss customerLoss) {
        customerLossService.update(customerLoss);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }

}
