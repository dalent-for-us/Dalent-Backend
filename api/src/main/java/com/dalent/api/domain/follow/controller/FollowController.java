package com.dalent.api.domain.follow.controller;

import com.dalent.api.domain.follow.dto.FollowRequestDto;
import com.dalent.api.domain.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/follow")
@RestController
public class FollowController {

    private final FollowService followService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void followUser(@RequestBody FollowRequestDto requestDto) {
        followService.follow(requestDto);
    }

    @GetMapping("/followings")
    public Long getFollowings(@RequestParam String userId) {
        return followService.getFollowings(userId);
    }
}
