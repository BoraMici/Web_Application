package com.example.webapplication.user.repository;

import com.example.webapplication.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "SELECT * FROM users  WHERE DATEDIFF(CURDATE(), created_at) > 2;", nativeQuery = true)
    List<User> findInactiveUsers();
}
