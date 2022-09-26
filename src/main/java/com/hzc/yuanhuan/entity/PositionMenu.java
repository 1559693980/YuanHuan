package com.hzc.yuanhuan.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Entity(name = "position_menu")
public class PositionMenu {

    @Id
    private Integer id;
    private Integer positionId;
    private Integer menuId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    private Integer status;

}
