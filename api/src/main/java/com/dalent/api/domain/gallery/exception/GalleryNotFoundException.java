package com.dalent.api.domain.gallery.exception;

import com.dalent.api.global.error.exception.BusinessException;
import com.dalent.api.global.error.exception.ErrorCode;

public class GalleryNotFoundException extends BusinessException {

    public GalleryNotFoundException() { super(ErrorCode.GALLERY_NOT_FOUND);}
}
