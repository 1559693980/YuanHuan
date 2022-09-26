package com.hzc.yuanhuan.controller;

import com.hzc.yuanhuan.entity.Menu;
import com.hzc.yuanhuan.result.ComResult;
import com.hzc.yuanhuan.service.MenuService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class MenuController {

    @Resource
    private MenuService menuService;

    @PostMapping("/getMenu")
    @PreAuthorize("@security.hasAuthority('system:menu:query')")
    public ComResult<List<Menu>> getMenu(){
        List<Menu> menu = menuService.getMenu();
        return new ComResult<>(200,"success",menu);
    }
}
