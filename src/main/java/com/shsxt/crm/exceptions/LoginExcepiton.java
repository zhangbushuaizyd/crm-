package com.shsxt.crm.exceptions;

import com.shsxt.crm.contains.CrmConstant;

public class LoginExcepiton extends RuntimeException {
    private Integer code = CrmConstant.USER_NOT_LOGIN_CODE;
    private String msg = CrmConstant.USER_NOT_LOGIN_MSG;

    public LoginExcepiton(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public LoginExcepiton(Integer code) {
        this.code = code;
    }

    public LoginExcepiton(String msg) {
        this.msg = msg;
    }

    public LoginExcepiton() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

