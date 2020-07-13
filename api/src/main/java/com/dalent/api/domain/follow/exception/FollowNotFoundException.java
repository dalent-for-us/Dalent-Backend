package com.dalent.api.domain.follow.exception;

import com.dalent.api.global.error.exception.BusinessException;
import com.dalent.api.global.error.exception.ErrorCode;

public class FollowNotFoundException extends BusinessException {

    public FollowNotFoundException() {super(ErrorCode.FOLLOW_NOT_FOUND);}
}
