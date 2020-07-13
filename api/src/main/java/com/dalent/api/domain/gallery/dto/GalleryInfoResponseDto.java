package com.dalent.api.domain.gallery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GalleryInfoResponseDto {

    private Long gallery_id;
    private String banner_image;
}
