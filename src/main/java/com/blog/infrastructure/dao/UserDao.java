package com.blog.infrastructure.dao;

import com.blog.domain.User;
import com.blog.infrastructure.view.UserResponseView;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, UUID> {

    Optional<UserResponseView> findUserByUsername(String username);

    Optional<UserResponseView> findUserByEmail(String email);

    Optional<UserResponseView> findUserById(UUID id);

    Optional<User> findByEmail(String email);

    boolean existsByEmailAndUsername(String email, String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
