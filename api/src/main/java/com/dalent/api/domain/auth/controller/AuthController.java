package com.dalent.api.domain.auth.controller;

import com.dalent.api.domain.auth.dto.LoginRequestDto;
import com.dalent.api.domain.auth.dto.TokenResponseDto;
import com.dalent.api.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody LoginRequestDto requestDto) {
        return authService.login(requestDto);
    }
}
