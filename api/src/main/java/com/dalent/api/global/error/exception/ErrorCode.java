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
    AUTHENTICATION(401, "A001", "Authentication Error"),
    INVALID_TOKEN(401, "A002", "Invalid Token"),

    //Follow
    FOLLOW_NOT_FOUND(400, "F001", "Follow Not Found"),
    Already_Follow(409, "F002", "Already Follow User"),

    //Gallery
    GALLERY_NOT_FOUND(400, "G001", "Gallery Not Found"),
    NOT_MY_GALLERY(403, "G002", "Not My Gallery"),

    //Work
    WORK_NOT_FOUND(400, "W001", "Work Not Found"),
    NOT_MY_WORK(403, "W002", "Not My Work"),

    //Comment
    COMMENT_NOT_FOUND(400, "C001", "Comment Not Found"),
    NOT_MY_COMMENT(403, "C002", "Not My Comment"),

    //Star
    STAR_NOT_FOUND(400, "S001", "Star Not Found"),
    Already_Gave_Star(409, "S002", "Already Gave Star");

    private final int status;
    private final String code;
    private final String message;
}
