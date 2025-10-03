package com.blog.application.contract;

import com.blog.application.dto.req.UserLoginDto;
import com.blog.application.dto.req.UserRegisterDto;

public interface AuthService {

    String login(UserLoginDto dto);

    void register(UserRegisterDto dto);

}
