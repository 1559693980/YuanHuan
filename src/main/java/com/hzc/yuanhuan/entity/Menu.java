package com.hzc.yuanhuan.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "menu_tb")
public class Menu {

    @Id
    private Integer menuId;
    private String menuName;
    private Integer superiorMenu;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    private Integer status;
    private Integer displayOrder;
    private String icon;
    private String permissions;
    private String path;
    private String menuType;

    @Transient
    private List<Menu> menuList;

}
