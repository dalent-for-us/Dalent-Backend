package com.dalent.api.domain.follow.service;

import com.dalent.api.domain.auth.exception.UserNotFoundException;
import com.dalent.api.domain.follow.dao.FollowRepository;
import com.dalent.api.domain.follow.domain.Follow;
import com.dalent.api.domain.follow.dto.FollowRequestDto;
import com.dalent.api.domain.user.dao.UserRepository;
import com.dalent.api.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final FollowRepository followRepository;

    private final UserRepository userRepository;

    public void follow(FollowRequestDto request) {
        User user = userRepository.findByNickname(request.getUser_nickname()).orElseThrow(UserNotFoundException::new);
        User target = userRepository.findByNickname(request.getTarget_nickname())
                .orElseThrow(UserNotFoundException::new);

        followRepository.save(Follow.builder()
                .user(user)
                .target(target)
                .build());
    }

    public long getFollowings(String userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return user.getFollowings().stream()
                .count();
    }
}
