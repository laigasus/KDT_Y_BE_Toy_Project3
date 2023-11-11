package com.example.kdt_y_be_toy_project2.domain.user.repository;

import com.example.kdt_y_be_toy_project2.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByUserId(Long userid);
}
