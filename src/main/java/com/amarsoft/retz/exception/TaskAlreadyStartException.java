package com.amarsoft.retz.exception;

public class TaskAlreadyStartException extends RuntimeException {

    public TaskAlreadyStartException() {
        super("任务已启动");
    }
}
