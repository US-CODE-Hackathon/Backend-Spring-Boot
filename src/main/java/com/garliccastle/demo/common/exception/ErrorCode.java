package com.garliccastle.demo.common.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    HttpStatus getHttpStatus();

    int getCode();

    String getMessage();
}
