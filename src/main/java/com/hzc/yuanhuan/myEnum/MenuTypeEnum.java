package com.hzc.yuanhuan.myEnum;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MenuTypeEnum {

    CATALOGUE(1, "目录"),
    MENU(2, "菜单"),

    BUTTON(3, "按钮");

    private final Integer code;
    private final String sName;

    MenuTypeEnum(Integer code,String sName) {
        this.code = code;
        this.sName = sName;
    }

    public Integer getCode() {
        return code;
    }
    /**
     * 将数据库查询到编码自动转为文字返回给前端
     */
    @JsonValue
    public String getsName() {
        return sName;
    }
}
