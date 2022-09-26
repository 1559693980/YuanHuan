package com.hzc.yuanhuan.service.Impl;

import com.hzc.yuanhuan.domain.UserLogin;
import com.hzc.yuanhuan.entity.User;
import com.hzc.yuanhuan.repository.PermsViewRepository;
import com.hzc.yuanhuan.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private PermsViewRepository permsViewRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = userRepository.findByUsername(username);
        if (!users.isEmpty()){
            //获取权限信息
            User user = users.get(0);
            List<String> permissions = permsViewRepository.findPermsByUserId(user.getUserId());
            return new UserLogin(user,permissions);
        }else {
            throw new RuntimeException("用户名不存在");
        }
    }
}
