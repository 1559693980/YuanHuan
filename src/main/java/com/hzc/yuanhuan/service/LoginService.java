package com.hzc.yuanhuan.service;


import com.hzc.yuanhuan.entity.User;
import com.hzc.yuanhuan.result.ComResult;

import java.util.Map;

public interface LoginService {

    ComResult<Map<String , String>> login(User user);

    ComResult<String> logout();
}
