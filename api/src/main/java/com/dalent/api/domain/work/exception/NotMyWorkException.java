package com.dalent.api.domain.work.exception;

import com.dalent.api.global.error.exception.BusinessException;
import com.dalent.api.global.error.exception.ErrorCode;

public class NotMyWorkException extends BusinessException {

    public NotMyWorkException() { super(ErrorCode.NOT_MY_WORK); }
}
