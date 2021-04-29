package com.amarsoft.retz.exception;

public class ClassNotExistsException extends RuntimeException {

    public ClassNotExistsException() {
        super("执行器不存在");
    }
}
