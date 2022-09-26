package com.hzc.yuanhuan.service;

import com.hzc.yuanhuan.entity.User;
import com.hzc.yuanhuan.entity.UserView;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface UserViewService {

    Page<UserView> findAllPage(Integer page, Integer size, UserView userView, Date start, Date end);
}
