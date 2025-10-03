package com.blog.application.controller;

import com.blog.application.contract.UserService;
import com.blog.application.dto.resp.UserShortDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserContoller {

    private final UserService userService;

    @GetMapping("/info")
    public UserShortDto info() {
        return userService.getCurrentUserInfo();
    }
}
