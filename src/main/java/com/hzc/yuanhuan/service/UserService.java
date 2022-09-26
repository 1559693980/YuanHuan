package com.hzc.yuanhuan.service;

import com.hzc.yuanhuan.entity.User;

import java.util.List;

public interface UserService {

    void saveAll(User user);

    User findAllByUserId(Integer uerId);

    User findAllByUsername(String username);

    void updateByUserId(User user);

    User findAllByMaxUserId();

}
