package com.dalent.api.domain.user.exception;

import com.dalent.api.global.error.exception.BusinessException;
import com.dalent.api.global.error.exception.ErrorCode;

public class UserDuplicateException extends BusinessException {

    public UserDuplicateException() { super(ErrorCode.USER_DUPLICATE); }
}
