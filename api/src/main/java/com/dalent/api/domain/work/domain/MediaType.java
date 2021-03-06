package com.dalent.api.domain.work.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MediaType {

    IMAGE("image", "이미지"),
    AUDIO("audio", "오디오");

    private final String key;
    private final String value;

}
