package com.dalent.api.domain.gallery.exception;

import com.dalent.api.global.error.exception.BusinessException;
import com.dalent.api.global.error.exception.ErrorCode;

public class NotMyGalleryException extends BusinessException {

    public NotMyGalleryException() { super(ErrorCode.NOT_MY_GALLERY); }
}
