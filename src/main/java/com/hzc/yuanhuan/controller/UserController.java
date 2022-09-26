package com.hzc.yuanhuan.controller;

import com.hzc.yuanhuan.entity.User;
import com.hzc.yuanhuan.entity.UserView;
import com.hzc.yuanhuan.result.ComResult;
import com.hzc.yuanhuan.service.UserService;
import com.hzc.yuanhuan.service.UserViewService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserViewService userViewService;

    @PostMapping("/saveAll")
    @PreAuthorize("@security.hasAuthority('system:user:create')")
    public ComResult<Boolean> saveAll(@RequestBody User user) {
        try {
            userService.saveAll(user);
            return new ComResult<>(200, "保存成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ComResult<>(500, e.getMessage(), null);
        }
    }

    @PostMapping("/findAllByUserId")
    @PreAuthorize("@security.hasAuthority('system:user:queryById')")
    public ComResult<User> findAllByUserId(@RequestBody User user) {
        try {
            User allByUserId = userService.findAllByUserId(user.getUserId());
            return new ComResult<>(200, "success", allByUserId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ComResult<>(500, e.getMessage(), null);
        }
    }

    @PostMapping("/findAllByUsername")
    @PreAuthorize("@security.hasAuthority('system:user:queryByUsername')")
    public ComResult<User> findAllByUsername(@RequestBody User user) {
        try {
            User allByUsername = userService.findAllByUsername(user.getUsername());
            return new ComResult<>(200, "success", allByUsername);
        } catch (Exception e) {
            e.printStackTrace();
            return new ComResult<>(500, e.getMessage(), null);
        }
    }

    @PostMapping("/updateByUserId")
    @PreAuthorize("@security.hasAuthority('system:user:updateStatus')")
    public ComResult<User> updateByUserId(@RequestBody User user) {
        try {
            userService.updateByUserId(user);
            return new ComResult<>(200, "success", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ComResult<>(500, e.getMessage(), null);
        }
    }

    @PostMapping("/findAllByMaxUserId")
    @PreAuthorize("@security.hasAuthority('system:user:queryMaxId')")
    public ComResult<User> findAllByMaxUserId() {
        try {
            User allByMaxUserId = userService.findAllByMaxUserId();
            return new ComResult<>(200, "success", allByMaxUserId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ComResult<>(500, e.getMessage(), null);
        }
    }

    @PostMapping("/findAll/{page}/{size}")
    @PreAuthorize("@security.hasAuthority('system:user:query')")
    public ComResult<Page<UserView>> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody UserView userView,
                                             String startTime, String endTime) {
        try {
            Date start = null;
            if (StringUtils.isNotBlank(startTime)) {
                start = new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
            }
            Date end = null;
            if (StringUtils.isNotBlank(endTime)) {
                end = new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
            }
            Page<UserView> allPage = userViewService.findAllPage(page, size, userView, start, end);
            return new ComResult<>(200, "success", allPage);
        } catch (Exception e) {
            e.printStackTrace();
            return new ComResult<>(500, e.getMessage(), null);
        }
    }
}
