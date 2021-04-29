package com.amarsoft.retz.advice;

import com.amarsoft.retz.model.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public CommonResult<Void> exceptionHandler(Exception e) {
        log.error("error at {}", e.getClass(), e);
        return CommonResult.fail(e);
    }
}
