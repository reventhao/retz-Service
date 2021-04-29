package com.amarsoft.retz.context;

import cn.hutool.core.date.DateUtil;
import cn.hutool.cron.CronUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.amarsoft.retz.po.Task;
import com.amarsoft.retz.task.TaskExecutor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskContextHolder {

    public static void schedule(Task task) {
        cn.hutool.cron.task.Task task1 = () -> {
            TaskExecutor executor;
            try {
                executor = (TaskExecutor) SpringUtil.getBean(Class.forName(task.getTaskClass()));
                executor.run();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        };
        log.info("*****task[{}] load at {}*****", task.getTaskName(), DateUtil.now());
        CronUtil.schedule(task.getTaskId().toString(), task.getCron(), task1);
    }

    public static void remove(Integer id) {
        CronUtil.remove(id.toString());
    }
}
