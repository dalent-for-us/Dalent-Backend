package com.dalent.api.domain.user.service;

import com.dalent.api.domain.user.dao.UserRepository;
import com.dalent.api.domain.user.domain.User;
import com.dalent.api.domain.user.dto.UserJoinRequestDto;
import com.dalent.api.domain.user.exception.UserDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User join(UserJoinRequestDto requestDto) {
        String password = passwordEncoder.encode(requestDto.getPassword());
        if(userRepository.findByNickname(requestDto.getNickname()).isPresent()) throw new UserDuplicateException();

        return userRepository.save(
                User.builder()
                        .id(requestDto.getId())
                        .nickname(requestDto.getNickname())
                        .password(password)
                        .build()
        );
    }
}
