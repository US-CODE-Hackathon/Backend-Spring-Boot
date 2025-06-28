package com.garliccastle.demo.domain.user.controller;

import com.garliccastle.demo.common.response.ApiResponse;
import com.garliccastle.demo.domain.user.entity.Users;
import com.garliccastle.demo.domain.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ApiResponse<List<Users>> getUser(){
        return ApiResponse.success(userService.getUsers());
    }

}
