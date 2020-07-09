package com.dalent.api.domain.auth.exception;

import com.dalent.api.global.error.exception.BusinessException;
import com.dalent.api.global.error.exception.ErrorCode;

public class InvalidTokenException extends BusinessException {

    public InvalidTokenException() { super(ErrorCode.INVALID_TOKEN); }
}
