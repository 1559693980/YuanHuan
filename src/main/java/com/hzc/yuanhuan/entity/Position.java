package com.hzc.yuanhuan.entity;

import io.swagger.models.auth.In;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity(name = "position_tb")
public class Position {

    @Id
    private Integer positionId;
    private String positionName;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    private Integer status;
    private Integer displayOrder;
    private String identification;
    private String remarks;

}
