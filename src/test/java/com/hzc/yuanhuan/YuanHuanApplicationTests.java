package com.hzc.yuanhuan;

import com.alibaba.fastjson2.JSON;
import com.hzc.yuanhuan.entity.Menu;
import com.hzc.yuanhuan.entity.UserView;
import com.hzc.yuanhuan.expression.SecurityExpression;
import com.hzc.yuanhuan.repository.MenuRepository;
import com.hzc.yuanhuan.repository.PermsViewRepository;
import com.hzc.yuanhuan.repository.UserViewRepository;
import com.hzc.yuanhuan.service.MenuService;
import com.hzc.yuanhuan.service.UserViewService;
import org.json.JSONString;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class YuanHuanApplicationTests {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private MenuService menuService;

    @Resource
    private MenuRepository menuRepository;

    @Resource
    private UserViewService userViewService;

    @Resource
    private PermsViewRepository permsViewRepository;

    @Test
    void contextLoads() {
        String encode = passwordEncoder.encode("123456");
        System.out.println("encode:" +encode);
    }

    @Test
    void test1() {
        List<Menu> menu = menuService.getMenu();
        System.out.println(menu);
    }

    @Test
    void test2() {
        List<Menu> all = menuRepository.findAll();
        System.out.println(all);
    }

    @Test
    void test3() {
        UserView userView = new UserView();
        Page<UserView> allPage = userViewService.findAllPage(1, 20, userView, null, null);
        System.out.println(JSON.toJSONString(allPage));
    }
}
