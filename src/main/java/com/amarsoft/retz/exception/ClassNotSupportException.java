package com.amarsoft.retz.exception;

public class ClassNotSupportException extends RuntimeException {
    public ClassNotSupportException() {
        super("不支持的执行器类型");
    }
}
