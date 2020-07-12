package com.dalent.api.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponseDto {

    private String id;
    private String nickname;
    private String profile_image;
    private String introduce;
    private Long gallery;
}
