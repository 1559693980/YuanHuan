package com.hzc.yuanhuan.controller;

import com.hzc.yuanhuan.entity.User;
import com.hzc.yuanhuan.result.ComResult;
import com.hzc.yuanhuan.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public ComResult<Map<String, String>> login(@RequestBody User user){
        try {
            return loginService.login(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new ComResult<>(500,e.getMessage(),null);
        }
    }

    @PostMapping("/logout")
    public ComResult<String> logout(){
        try {
            return loginService.logout();
        } catch (Exception e) {
            e.printStackTrace();
            return new ComResult<>(500,"error",null);
        }
    }

    @PostMapping("/test")
    public ComResult<String> test(){
        return new ComResult<>(200, "success", null);
    }
}
