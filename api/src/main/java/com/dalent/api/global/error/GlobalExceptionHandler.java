package com.dalent.api.global.error;

import com.dalent.api.global.error.exception.BusinessException;
import com.dalent.api.global.error.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e) {
        final ErrorCode errorCode = e.getErrorCode();
        final int status = errorCode.getStatus();
        return new ResponseEntity<>(new ErrorResponse(status, e.getMessage(),
                errorCode.getCode()), HttpStatus.valueOf(status));
    }
}
