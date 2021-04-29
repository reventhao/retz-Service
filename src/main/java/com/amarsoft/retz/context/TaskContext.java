package com.amarsoft.retz.context;

import com.amarsoft.retz.enums.TaskStatusEnum;
import com.amarsoft.retz.mapper.TaskMapper;
import com.amarsoft.retz.po.Task;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Component
public class TaskContext {

    @Resource
    private TaskMapper taskMapper;

    private final List<Task> taskList = new CopyOnWriteArrayList<>();

    @PostConstruct
    public void load() {
        List<Task> taskList = taskMapper.selectList(new LambdaQueryWrapper<>());

        Task task = new Task();
        task.setStatus(TaskStatusEnum.RUN.getCode());
        taskMapper.update(task, new LambdaUpdateWrapper<>());
        log.info("*****加载定时任务数据[{}]*****", taskList.size());
        this.taskList.addAll(taskList);
    }

    public List<Task> getTaskList() {
        return taskList;
    }
}
