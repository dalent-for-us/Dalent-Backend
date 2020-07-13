package com.dalent.api.domain.comment.exception;

import com.dalent.api.global.error.exception.BusinessException;
import com.dalent.api.global.error.exception.ErrorCode;

public class CommentNotFoundException extends BusinessException {

    public CommentNotFoundException() { super(ErrorCode.COMMENT_NOT_FOUND); }
}
