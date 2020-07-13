package com.dalent.api.domain.star.exception;

import com.dalent.api.global.error.exception.BusinessException;
import com.dalent.api.global.error.exception.ErrorCode;

public class AlreadyGaveStarException extends BusinessException {

    public AlreadyGaveStarException() { super(ErrorCode.Already_Gave_Star); }
}
