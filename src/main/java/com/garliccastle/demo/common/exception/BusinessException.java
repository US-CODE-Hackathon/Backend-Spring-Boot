package com.garliccastle.demo.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends BaseException {

    public BusinessException(ErrorCode errorCode) {
        super(errorCode);
    }
}
