package com.dalent.api.domain.user.service;

import com.dalent.api.domain.auth.exception.UserNotFoundException;
import com.dalent.api.domain.user.dao.UserRepository;
import com.dalent.api.domain.user.domain.User;
import com.dalent.api.domain.user.dto.UserInfoResponseDto;
import com.dalent.api.domain.user.dto.UserJoinRequestDto;
import com.dalent.api.domain.user.exception.UserDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public UserInfoResponseDto getMyInfo() {
        String nickname = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByNickname(nickname)
                .map(user -> {
                    Long gallery = (user.getGallery() != null) ? user.getGallery().getGalleryId() : null;

                    return UserInfoResponseDto.builder()
                            .id(user.getId())
                            .nickname(user.getNickname())
                            .introduce(user.getIntroduce())
                            .profile_image(user.getProfile_image())
                            .gallery(gallery)
                            .build();
                })
                .orElseThrow(UserNotFoundException::new);
    }
}
