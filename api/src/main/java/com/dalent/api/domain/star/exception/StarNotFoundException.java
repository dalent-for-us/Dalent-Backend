package com.dalent.api.domain.star.exception;

import com.dalent.api.global.error.exception.BusinessException;
import com.dalent.api.global.error.exception.ErrorCode;

public class StarNotFoundException extends BusinessException {

    public StarNotFoundException() { super(ErrorCode.STAR_NOT_FOUND); }
}
