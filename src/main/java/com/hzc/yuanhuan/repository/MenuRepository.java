package com.hzc.yuanhuan.repository;

import com.hzc.yuanhuan.entity.Menu;
import com.hzc.yuanhuan.myEnum.MenuTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> findBySuperiorMenuAndMenuTypeNot(Integer superiorMenu, String menuType);

}
