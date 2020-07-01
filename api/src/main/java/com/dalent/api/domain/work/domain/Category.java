package com.dalent.api.domain.work.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {

    ART("ART", "미술"),
    MUSIC("MUSIC", "음악"),
    PROGRAMMING("PROGRAMMING", "프로그래밍"),
    FASHION("FASHION", "패션");

    private final String key;
    private final String value;

}
