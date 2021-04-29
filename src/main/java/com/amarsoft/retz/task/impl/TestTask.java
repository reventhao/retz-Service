package com.amarsoft.retz.task.impl;

import com.amarsoft.retz.annotation.TaskHandler;
import com.amarsoft.retz.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
@TaskHandler(name = "testTaskExecutor")
public class TestTask implements TaskExecutor {

    public void run() {
        System.out.println("测试定时任务");
    }
}
