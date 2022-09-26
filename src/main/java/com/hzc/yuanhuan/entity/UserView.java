package com.hzc.yuanhuan.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "user_view")
@Data
public class UserView {

  @Id
  private Integer userId;
  private String username;
  private String name;
  private String tel;
  private Integer depId;
  private String depName;
  @Temporal(TemporalType.TIMESTAMP)
  private Date createTime;
  private String positionName;


}
