package com.dalent.api.domain.follow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FollowRequestDto {

    private String user_nickname;
    private String target_nickname;
}
