package com.dalent.api.domain.follow.exception;

import com.dalent.api.global.error.exception.BusinessException;
import com.dalent.api.global.error.exception.ErrorCode;

public class AlreadyFollowException extends BusinessException {

    public AlreadyFollowException() { super(ErrorCode.Already_Follow); }
}
