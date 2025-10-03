package com.blog.application.service;

import com.blog.application.contract.AuthService;
import com.blog.application.contract.JwtService;
import com.blog.application.contract.UserService;
import com.blog.application.dto.req.UserLoginDto;
import com.blog.application.dto.req.UserRegisterDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public String login(UserLoginDto dto) {
        UUID userId = userService.getUserIdFindByEmail(dto.getEmail());
        userService.checkUserAndPassword(dto.getEmail(), dto.getPassword());
        return jwtService.generateToken(userId);
    }

    @Override
    public void register(UserRegisterDto dto) {
        userService.isExistsUserByEmailAndUsername(dto.getEmail(), dto.getUsername());
        userService.save(dto);
    }
}
