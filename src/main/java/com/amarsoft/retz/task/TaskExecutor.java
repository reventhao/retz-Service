package com.amarsoft.retz.task;

/**
 * 定时任务统一接口
 */
@FunctionalInterface
public interface TaskExecutor {

    /**
     * 定时任务执行方法
     */
    void run();
}
