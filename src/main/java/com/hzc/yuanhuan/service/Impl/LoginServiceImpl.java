package com.hzc.yuanhuan.service.Impl;

import com.hzc.yuanhuan.domain.UserLogin;
import com.hzc.yuanhuan.entity.User;
import com.hzc.yuanhuan.result.ComResult;
import com.hzc.yuanhuan.service.LoginService;
import com.hzc.yuanhuan.utils.JwtUtils;
import com.hzc.yuanhuan.utils.RedisTemplateUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisTemplateUtil redisTemplateUtil;

    @Override
    public ComResult<Map<String, String>> login(User user) {
        //authenticationToken 用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过，给出提示
        if (Objects.isNull(authentication)){
            throw new RuntimeException("登陆失败");
        }
        //如果认证通过了，使用userid生成一个jwt，jwt存入返回
        UserLogin userLogin = (UserLogin) authentication.getPrincipal();
        System.out.println(userLogin.getUser());
        String userId = Long.toString(userLogin.getUser().getUserId());
        String token = JwtUtils.createToken(userId);
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        redisTemplateUtil.set("login:"+userId, userLogin);
        return new ComResult<>(200,"登陆成功",map);
    }

    @Override
    public ComResult<String> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserLogin userLogin = (UserLogin) authentication.getPrincipal();
        long id = userLogin.getUser().getUserId();
        redisTemplateUtil.del("login:" + id);
        return new ComResult<>(200,"注销成功", null);
    }
}
