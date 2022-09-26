package com.hzc.yuanhuan.repository;

import com.hzc.yuanhuan.entity.UserView;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserViewRepository extends JpaRepository<UserView, Integer> {


}
