package com.hzc.yuanhuan.repository;

import com.hzc.yuanhuan.entity.PermsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermsViewRepository extends JpaRepository<PermsView, Integer> {

    List<PermsView> findAllByUserId(Integer userId);

    @Query("select distinct p.permissions from perms_view p where p.userId = :userId")
    List<String> findPermsByUserId(Integer userId);
}
