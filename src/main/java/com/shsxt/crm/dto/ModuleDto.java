package com.shsxt.crm.dto;

public class ModuleDto  {
    private Integer id;
    private String name;
    private Integer pId;
    private boolean checked=false;  //通过mybatis ,非0非null的值自动转换为true

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
