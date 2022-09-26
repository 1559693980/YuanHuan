package com.hzc.yuanhuan.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity(name = "user_tb")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer userId;
    private String username;
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    private Integer status;
    private String identification;
    private String remarks;
    private String name;
    private String tel;
    private Integer depId;

    private String positionName;
}
