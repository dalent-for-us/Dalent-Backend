package com.dalent.api.domain.work.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WorkDetailResponseDto {

    private String category;
    private String title;
    private String content;
    private String media_type;
    private String media_link;
    private String thumbnail_image;
    private String nickname;
}
