package com.blog.application.contract;

import com.blog.application.dto.req.UserRegisterDto;
import com.blog.application.dto.req.UserUpdateRequest;
import com.blog.application.dto.resp.UserResponseDto;
import com.blog.application.dto.resp.UserShortDto;
import com.blog.domain.User;
import java.util.UUID;

public interface UserService {

    void save(UserRegisterDto dto);

    UserResponseDto update(UserUpdateRequest dto);

    void delete(UUID id);

    User findById(UUID id);

    UserResponseDto findByUsername(String username);

     UUID getUserIdFindByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    void isExistsUserByEmailAndUsername(String email, String username);

    void checkUserAndPassword(String email, String password);

    UserShortDto getCurrentUserInfo();
}
