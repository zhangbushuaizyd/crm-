package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.contains.CrmConstant;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.po.CustomerServe;
import com.shsxt.crm.query.CustomerQuery;
import com.shsxt.crm.query.CustomerServeQuery;
import com.shsxt.crm.service.CustomerServeService;
import com.shsxt.crm.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("customerServe")
public class CustomerServeController extends BaseController {

    @Resource
    private CustomerServeService customerServeService;


    @RequestMapping("index/{type}")
    public String index(@PathVariable  Integer type) {
        switch (type){
            case 1:
                return "customer_serve_create";
            case 2:
                return "customer_serve_assign";
            case 3:
                return "customer_serve_proce";
            case 4:
                return "customer_serve_feed_back";
            case 5:
                return "customer_serve_archive";
            default:
                return "error";
        }

    }

    @RequestMapping("saveOrUpdateCustomerServe")
    @ResponseBody
    public ResultInfo saveOrUpdateCustomerServe(CustomerServe customerServe, HttpServletRequest request){
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        customerServeService.saveOrUpdateCustomerServe(customerServe,userId);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }
    @RequestMapping("queryCustomerServesByParams")
    @ResponseBody
    public Map<String,Object> queryCustomerServesByParams(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows, CustomerServeQuery query){
        query.setPageNum(page);
        query.setPageSize(rows);
        return customerServeService.queryForPage(query);
    }
}
