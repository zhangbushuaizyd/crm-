package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("report")
public class ReportController extends BaseController {
    @Resource
    private CustomerService customerService;

    @RequestMapping("/{index}")
    public String index(@PathVariable Integer index) {
        if (index == 1) {
            return "customer_report";
        }
        return "error";
    }
    @RequestMapping("queryLevelNums")
    @ResponseBody
    public List<Map> queryLevelNums(){
       return customerService.queryLevelNums();
    }
}
