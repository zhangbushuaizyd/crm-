package com.shsxt.crm.query;

import com.shsxt.crm.base.BaseDao;
import com.shsxt.crm.base.BaseQuery;

public class OrderDetailsQuery extends BaseQuery {
    private Integer orderId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
