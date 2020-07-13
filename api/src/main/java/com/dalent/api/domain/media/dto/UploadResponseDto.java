package com.dalent.api.domain.media.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UploadResponseDto {

    private String file_link;
    private String media_type;
}
