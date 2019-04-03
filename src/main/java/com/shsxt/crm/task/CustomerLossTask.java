package com.shsxt.crm.task;

import com.shsxt.crm.service.CustomerService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomerLossTask {
    @Resource
    private CustomerService customerService;
    //@Scheduled(cron = "0/8 * * * * ? ")
    //public void job01(){
    //    System.out.println("=============开始筛选==========");
    //    customerService.addLoseCustomers();
    //    System.out.println("=============结束筛选===============");
    //}

    //Map<String ,String> test1 = new HashMap<>();


}
