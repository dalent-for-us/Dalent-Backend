package com.dalent.api.domain.comment.exception;

import com.dalent.api.global.error.exception.BusinessException;
import com.dalent.api.global.error.exception.ErrorCode;

public class NotMyCommentException extends BusinessException {

    public NotMyCommentException() { super(ErrorCode.NOT_MY_COMMENT); }
}
