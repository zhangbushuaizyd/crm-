package com.shsxt.crm.query;

import com.shsxt.crm.base.BaseQuery;

public class CustomerServeQuery extends BaseQuery {
    private String customer;

    private String state;

    private String createDate;

    private String myd;

    public String getMyd() {
        return myd;
    }

    public void setMyd(String myd) {
        this.myd = myd;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
