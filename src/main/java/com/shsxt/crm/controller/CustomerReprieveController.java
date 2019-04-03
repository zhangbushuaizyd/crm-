package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.contains.CrmConstant;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.po.CustomerLoss;
import com.shsxt.crm.po.CustomerReprieve;
import com.shsxt.crm.query.CustomerReprieveQuery;
import com.shsxt.crm.service.CustomerLossService;
import com.shsxt.crm.service.CustomerReprieveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("customerReprieve")
public class CustomerReprieveController extends BaseController {
    @Resource
    private CustomerLossService customerLossService;

    @Resource
    private CustomerReprieveService customerReprieveService;

        @RequestMapping("index")
        public String index(Integer lossId, Model model){
            CustomerLoss customerLoss = customerLossService.queryById(lossId);
            model.addAttribute(customerLoss);
            return "customer_loss_reprieve";
        }
        //查询
        @RequestMapping("queryCustomerReprievesByParams")
        @ResponseBody
        public Map<String,Object> queryCustomerReprieveByParams(
                CustomerReprieveQuery query,
                @RequestParam(defaultValue = "1")Integer page,
                @RequestParam(defaultValue = "10")Integer size
                ){
            query.setPageNum(page);
            query.setPageSize(size);
            return customerReprieveService.queryForPage(query);
        }
        //删除
        @RequestMapping("delReprieve")
        @ResponseBody
        public ResultInfo delReprieve(Integer id){
            customerReprieveService.delete(id);
            return success(CrmConstant.OPS_SUCCESS_MSG);
        }

        //添加或者更新
        @RequestMapping("saveOrUpdateCustomerReprieve")
        @ResponseBody
        public  ResultInfo saveOrUpdateCustomerReprieve(CustomerReprieve customerReprieve){
            customerReprieveService.saveOrUpdateCustomerReprieve(customerReprieve);
            return success(CrmConstant.OPS_SUCCESS_MSG);
        }
}
