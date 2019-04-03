package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.query.OrderDetailsQuery;
import com.shsxt.crm.service.OrderDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("orderDetails")
public class OrderDetailsController extends BaseController {
    @Resource
    private OrderDetailsService orderDetailsService;
    @RequestMapping("queryOrderDetailsByParams")
    @ResponseBody
    public Map<String,Object> queryOrderDetailsByParams(@RequestParam(defaultValue = "1") Integer page,
                                                        @RequestParam(defaultValue = "10") Integer rows,
                                                        OrderDetailsQuery query){
        query.setPageNum(page);
        query.setPageSize(rows);
        return orderDetailsService.queryForPage(query);
    }

}
