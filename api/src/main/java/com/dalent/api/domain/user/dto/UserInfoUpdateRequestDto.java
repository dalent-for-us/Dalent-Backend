package com.dalent.api.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoUpdateRequestDto {

    private String profile_image;
    private String introduce;

}
