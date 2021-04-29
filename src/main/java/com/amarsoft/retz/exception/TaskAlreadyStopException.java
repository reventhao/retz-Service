package com.amarsoft.retz.exception;

public class TaskAlreadyStopException extends RuntimeException {
    public TaskAlreadyStopException() {
        super("任务已停止");
    }
}
