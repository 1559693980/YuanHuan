package com.hzc.yuanhuan.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Entity(name = "user_position")
public class UserPosition {

    @Id
    private Integer id;
    private Integer userId;
    private Integer positionId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    private Integer status;

}
