package com.blog.application.user.repos;

import com.blog.application.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByEmail(String email);
    Long deleteByEmail(String email);
    List<UserEntity> findByRole(String role);
}
