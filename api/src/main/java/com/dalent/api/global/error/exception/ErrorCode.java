package com.dalent.api.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    // User
    USER_NOT_FOUND(400, "U001", "User Not Found"),
    USER_DUPLICATE(409, "U002", "User Duplicated"),

    //Auth
    INVALID_TOKEN(401, "A001", "Invalid Token");

    private final int status;
    private final String code;
    private final String message;
}
