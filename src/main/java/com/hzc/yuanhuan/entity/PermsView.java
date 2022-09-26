package com.hzc.yuanhuan.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "perms_view")
public class PermsView {
    @Id
    private Integer userId;
    private String permissions;

}
