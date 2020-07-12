package com.dalent.api.domain.user.controller;

import com.dalent.api.domain.user.dto.UserInfoResponseDto;
import com.dalent.api.domain.user.dto.UserInfoUpdateRequestDto;
import com.dalent.api.domain.user.dto.UserJoinRequestDto;
import com.dalent.api.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void join(@RequestBody UserJoinRequestDto requestDto) { userService.join(requestDto); }

    @GetMapping("/me")
    public UserInfoResponseDto getMyInfo() { return userService.getMyInfo(); }

    @PatchMapping("/me")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Transactional
    public void updateMyInfo(@RequestBody UserInfoUpdateRequestDto requestDto) {
        userService.updateMyInfo(requestDto);
    }
}
