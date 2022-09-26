package com.hzc.yuanhuan.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Entity(name = "dep_tb")
public class Dep {

    @Id
    private Integer depId;
    private String depName;
    private Integer superiorDep;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    private Integer status;
    private Integer displayOrder;
    private String leading;
    private String email;

}
