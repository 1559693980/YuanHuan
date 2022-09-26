package com.hzc.yuanhuan.repository;

import com.hzc.yuanhuan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUsername(String username);

    List<User> findAllByUserId(Integer userId);

    @Query("update user_tb u set u.status = :state where u.userId = :userId")
    void updateStatusByUserId(Integer state, Integer userId);

    @Query("select max(userId) from user_tb ")
    Integer findAllByMaxUserId();

    List<User> findAllByStatusNot(Integer status);


}
