package com.blog.application.controller;

import com.blog.application.contract.UserService;
import com.blog.application.dto.resp.UserShortDto;
import com.blog.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/info")
    public UserShortDto info(@AuthenticationPrincipal User user) {
        return userService.getCurrentUserInfo(user);
    }
}
