package com.dalent.api.domain.user.controller;

import com.dalent.api.domain.user.dto.UserJoinRequestDto;
import com.dalent.api.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void join(@RequestBody UserJoinRequestDto requestDto) { userService.join(requestDto); }
}