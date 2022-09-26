package com.hzc.yuanhuan.service.Impl;

import com.hzc.yuanhuan.entity.Menu;
import com.hzc.yuanhuan.myEnum.MenuTypeEnum;
import com.hzc.yuanhuan.repository.MenuRepository;
import com.hzc.yuanhuan.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuRepository menuRepository;

    @Override
    public List<Menu> getMenu() {
        List<Menu> menuBySuperiorMenu = menuRepository.findBySuperiorMenuAndMenuTypeNot(0, MenuTypeEnum.BUTTON.getsName());
        for (Menu bySuperiorMenu : menuBySuperiorMenu) {
            getMenu(bySuperiorMenu);
        }
        return menuBySuperiorMenu;
    }

    private void getMenu(Menu menu){
        List<Menu> menuBySuperiorMenu = menuRepository.findBySuperiorMenuAndMenuTypeNot(menu.getMenuId(), "按钮");
        if (menuBySuperiorMenu.isEmpty()){
            return;
        }
        menu.setMenuList(menuBySuperiorMenu);
        for (Menu bySuperiorMenu : menuBySuperiorMenu) {
            getMenu(bySuperiorMenu);
        }
    }
}
