package com.dalent.api.domain.follow.service;

import com.dalent.api.domain.auth.exception.UserNotFoundException;
import com.dalent.api.domain.follow.dao.FollowRepository;
import com.dalent.api.domain.follow.domain.Follow;
import com.dalent.api.domain.follow.exception.FollowNotFoundException;
import com.dalent.api.domain.user.dao.UserRepository;
import com.dalent.api.domain.user.domain.User;
import com.dalent.api.domain.user.exception.UserDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final FollowRepository followRepository;

    private final UserRepository userRepository;

    public void follow(String targetNickname) {
        String nickname = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByNickname(nickname).orElseThrow(UserNotFoundException::new);
        User target = userRepository.findByNickname(targetNickname).orElseThrow(UserNotFoundException::new);

        followRepository.save(Follow.builder()
                .user(user)
                .target(target)
                .build());
    }

    public long getFollowings(String nickname) {
        User user = userRepository.findByNickname(nickname).orElseThrow(UserNotFoundException::new);
        List<Follow> followings = followRepository.findAllByUser(user);

        return followings.size();
    }

    public long getFollowers(String nickname) {
        User user = userRepository.findByNickname(nickname).orElseThrow(UserNotFoundException::new);
        List<Follow> followers = followRepository.findAllByTarget(user);

        return followers.size();
    }

    public void deleteFollow(String targetNick) {
        String nickname = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByNickname(nickname).orElseThrow(UserNotFoundException::new);
        User target = userRepository.findByNickname(targetNick).orElseThrow(UserNotFoundException::new);

        Follow follow = followRepository.findByUserAndTarget(user, target).orElseThrow(FollowNotFoundException::new);
        followRepository.delete(follow);
    }
}
