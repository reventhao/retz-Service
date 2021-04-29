package com.amarsoft.retz.listener;

import cn.hutool.cron.CronUtil;
import com.amarsoft.retz.context.TaskContextHolder;
import com.amarsoft.retz.context.TaskContext;
import com.amarsoft.retz.po.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Configuration
public class TaskStartEventListener implements ApplicationListener<ApplicationStartedEvent> {

    @Resource
    private TaskContext taskContext;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        List<Task> taskList = taskContext.getTaskList();
        if (CollectionUtils.isEmpty(taskList)) {
            log.info("*****查询定时任务为空*****");
            return;
        }
        taskList.forEach(TaskContextHolder::schedule);
        // 设置精确到秒
        CronUtil.setMatchSecond(true);
        // 启动定时任务
        log.info("*****定时任务启动*****");
        CronUtil.start();
    }
}
