package com.dalent.api.domain.auth.exception;

import com.dalent.api.global.error.exception.BusinessException;
import com.dalent.api.global.error.exception.ErrorCode;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException() { super(ErrorCode.USER_NOT_FOUND); }
}
