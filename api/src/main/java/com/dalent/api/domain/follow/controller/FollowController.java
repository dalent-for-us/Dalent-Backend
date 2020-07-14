package com.dalent.api.domain.follow.controller;

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
    public void followUser(@RequestParam String target) {
        followService.follow(target);
    }

    @GetMapping("/followings")
    public Long getFollowings(@RequestParam String nickname) {
        return followService.getFollowings(nickname);
    }

    @GetMapping("/followers")
    public Long getFollowers(@RequestParam String nickname) {
        return followService.getFollowers(nickname);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteFollow(@RequestParam String target) {
        followService.deleteFollow(target);
    }

    @GetMapping("/{nickname}")
    public boolean checkFollow(@PathVariable String nickname) {
        return followService.checkFollow(nickname);
    }
}
