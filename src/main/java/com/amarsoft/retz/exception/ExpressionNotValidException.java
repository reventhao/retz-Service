package com.amarsoft.retz.exception;

public class ExpressionNotValidException extends RuntimeException{
    public ExpressionNotValidException() {
        super("表达式错误");
    }
}
