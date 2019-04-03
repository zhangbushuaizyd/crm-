package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.contains.CrmConstant;
import com.shsxt.crm.dao.SaleChanceMapper;
import com.shsxt.crm.dao.UserMapper;
import com.shsxt.crm.po.SaleChance;
import com.shsxt.crm.po.User;
import com.shsxt.crm.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SaleChanceService extends BaseService<SaleChance> {

    @Resource
    private SaleChanceMapper saleChanceMapper;
    @Resource
    private UserMapper userMapper;

    /**
     * 添加或者更新操作
     * @param saleChance
     * @param userId
     */
    public void saveOrUpdateSaleChance(SaleChance saleChance,Integer userId){
        /***
         * 步骤
         * 1. 校验参数
         * 2. 补全参数
         * 3. 通过id区分是添加或者更新
         * 4. 执行最终操作
         * */
        checkSaleChanceParams(saleChance.getCustomerName(),saleChance.getLinkMan(),saleChance.getLinkPhone());

        saleChance.setUpdateDate(new Date());
        Integer id = saleChance.getId();
        if (null == id){

            /***
             * 如果选择分配人, state 为 1 已分配, 设置分配时间
             * 如果未选择分配人, state 为 0 未分配
             * */
            if (StringUtils.isBlank(saleChance.getAssignMan())){
                saleChance.setState(0); //没选择就是未分配
            }else {
                saleChance.setState(1); //选择就是已分配
                saleChance.setAssignTime(new Date());  //分配时间
            }
            //添加操作
            //saleChance.setState(0);//未分配
            saleChance.setDevResult(0); //未开发
            saleChance.setIsValid(1);   //有效数据
            saleChance.setCreateDate(new Date());   //创建时间
            //通过id查询到用户,设置创建人
            User user = userMapper.queryById(userId);
            saleChance.setCreateMan(user.getUserName());
            AssertUtil.isTrue(saleChanceMapper.save(saleChance)<1, CrmConstant.OPS_FAILED_MSG);

        }else {
            //更新操作
            AssertUtil.isTrue(saleChanceMapper.update(saleChance)<1, CrmConstant.OPS_FAILED_MSG);
        }
    }
    //校验参数
    private void checkSaleChanceParams(String customerName, String linkMan, String linkPhone) {
        AssertUtil.isTrue(StringUtils.isBlank(customerName),"客户名称为空!");
        AssertUtil.isTrue(StringUtils.isBlank(linkMan),"联系人为空!");
        AssertUtil.isTrue(StringUtils.isBlank(linkPhone),"联系电话为空!");
    }
    //下拉回显查询所有的客户经理
    public List<Map> queryAllCustomerManager(){
        return saleChanceMapper.queryAllCustomerManager();
    }

    //更改开发状态
    public Integer updateSaleChanceDevResult(SaleChance saleChance){
        return saleChanceMapper.updateSaleChanceDevResult(saleChance);
    }

}
