package com.dalent.api.domain.work.exception;

import com.dalent.api.global.error.exception.BusinessException;
import com.dalent.api.global.error.exception.ErrorCode;

public class WorkNotFoundException extends BusinessException {

    public WorkNotFoundException() { super(ErrorCode.WORK_NOT_FOUND); }
}
