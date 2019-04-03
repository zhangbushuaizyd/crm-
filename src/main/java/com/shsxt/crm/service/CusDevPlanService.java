package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.contains.CrmConstant;
import com.shsxt.crm.dao.CusDevPlanMapper;
import com.shsxt.crm.dao.SaleChanceMapper;
import com.shsxt.crm.po.CusDevPlan;
import com.shsxt.crm.po.SaleChance;
import com.shsxt.crm.utils.AssertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CusDevPlanService extends BaseService <CusDevPlan> {

    @Resource
    private CusDevPlanMapper cusDevPlanMapper;

    @Resource
    private SaleChanceMapper saleChanceMapper;
    /**
     * 参数校验
     * 补全参数
     * 通过id区分是更新还是添加
     * 执行操作
     * @param cusDevPlan
     * @param id
     */
    public void saveOrUpdateCusDevPlan(CusDevPlan cusDevPlan,Integer sid){
        checkCUsDevPlanParams(cusDevPlan);

        cusDevPlan.setUpdateDate(new Date());
        Integer id = cusDevPlan.getId();

        if (null == id){
            cusDevPlan.setCreateDate(new Date());
            cusDevPlan.setIsValid(1);   //有效
            cusDevPlan.setSaleChanceId(sid); //营销机会id

            AssertUtil.isTrue(cusDevPlanMapper.save(cusDevPlan)<1, CrmConstant.OPS_FAILED_MSG);
            /**
             * 判断开发状态
             * 如果状态未开发,变为开发中
             */
            SaleChance saleChance = saleChanceMapper.queryById(sid);
            if (saleChance.getDevResult() == 0){
                saleChance.setDevResult(1);
                AssertUtil.isTrue(saleChanceMapper.update(saleChance)<1,CrmConstant.OPS_FAILED_MSG);
            }
        }else {
            AssertUtil.isTrue(cusDevPlanMapper.update(cusDevPlan)<1, CrmConstant.OPS_FAILED_MSG);
        }
    }
    private void checkCUsDevPlanParams(CusDevPlan cusDevPlan) {
        AssertUtil.isTrue(null == cusDevPlan.getPlanDate(),"计划日期为空");
        AssertUtil.isTrue(null == cusDevPlan.getPlanItem(),"计划内容为空");
        AssertUtil.isTrue(null == cusDevPlan.getExeAffect(),"计划结果为空");

    }

}
