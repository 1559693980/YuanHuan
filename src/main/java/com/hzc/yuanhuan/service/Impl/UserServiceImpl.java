package com.hzc.yuanhuan.service.Impl;

import com.hzc.yuanhuan.entity.User;
import com.hzc.yuanhuan.repository.UserRepository;
import com.hzc.yuanhuan.service.UserService;
import com.hzc.yuanhuan.utils.ComUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserRepository userRepository;

    @Override
    public void saveAll(User user) {
        user.setCreateTime(new Date());
        if (ComUtil.isNotNull(user.getUsername())) {
            throw new RuntimeException("用户名不能为空");
        }
        if (ComUtil.isNotNull(user.getPassword())) {
            throw new RuntimeException("密码不能为空");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findAllByUserId(Integer uerId) {
        List<User> allByUserId = userRepository.findAllByUserId(uerId);
        if (allByUserId.isEmpty()) {
            throw new RuntimeException("找不到用户Id：" + uerId);
        } else {
            return allByUserId.get(0);
        }
    }

    @Override
    public User findAllByUsername(String username) {
        List<User> allByUserId = userRepository.findByUsername(username);
        if (allByUserId.isEmpty()) {
            throw new RuntimeException("找不到用户名：" + username);
        } else {
            return allByUserId.get(0);
        }
    }

    @Override
    public void updateByUserId(User user) {
        if (ComUtil.isNotNull(user.getStatus())){
            throw new RuntimeException("状态不能为空");
        }
        userRepository.updateStatusByUserId(user.getStatus(), user.getUserId());
    }

    @Override
    public User findAllByMaxUserId() {
        Integer maxUserId = userRepository.findAllByMaxUserId();
        return findAllByUserId(maxUserId);
    }


}
