package com.dalent.api.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    // User
    USER_DUPLICATE(409, "U001", "User Duplicated");

    private final int status;
    private final String code;
    private final String message;
}