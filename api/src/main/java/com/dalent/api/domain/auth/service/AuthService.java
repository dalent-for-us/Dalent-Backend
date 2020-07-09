package com.dalent.api.domain.auth.service;

import com.dalent.api.domain.auth.dto.LoginRequestDto;
import com.dalent.api.domain.auth.dto.TokenResponseDto;
import com.dalent.api.domain.auth.exception.UserNotFoundException;
import com.dalent.api.domain.user.dao.UserRepository;
import com.dalent.api.domain.user.domain.User;
import com.dalent.api.global.config.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    public TokenResponseDto login(LoginRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getId())
                .filter(u -> passwordEncoder.matches(requestDto.getPassword(), u.getPassword()))
                .orElseThrow(UserNotFoundException::new);

        String nickname = user.getNickname();

        return TokenResponseDto.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(nickname))
                .refreshToken(jwtTokenProvider.generateRefreshToken(nickname))
                .build();
    }
}
