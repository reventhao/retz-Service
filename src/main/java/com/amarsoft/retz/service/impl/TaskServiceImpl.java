package com.amarsoft.retz.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.cron.CronException;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.pattern.CronPattern;
import cn.hutool.cron.pattern.CronPatternUtil;
import cn.hutool.json.JSONObject;
import com.amarsoft.retz.context.TaskContextHolder;
import com.amarsoft.retz.dto.MatchDateRespDTO;
import com.amarsoft.retz.enums.TaskStatusEnum;
import com.amarsoft.retz.exception.*;
import com.amarsoft.retz.mapper.TaskMapper;
import com.amarsoft.retz.po.Task;
import com.amarsoft.retz.service.TaskService;
import com.amarsoft.retz.task.TaskExecutor;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Resource
    private TaskMapper taskMapper;

    @Override
    public Page<Task> queryPage(Integer pageNum, Integer pageSize) {
        Page<Task> page = new Page<>(pageNum, pageSize);
        return taskMapper.selectPage(page, new LambdaQueryWrapper<>());
    }

    @Override
    public Task query(Integer id) {
        return taskMapper.selectById(id);
    }

    @Override
    public boolean add(Task task) {
        preValidate(task);
        return taskMapper.insert(task) > 0;
    }

    @Override
    public boolean update(Task task) {
        preValidate(task);
        if (TaskStatusEnum.RUN.getCode().equals(task.getStatus())) {
            CronUtil.updatePattern(task.getTaskId().toString(), new CronPattern(task.getCron()));
        }
        return taskMapper.updateById(task) > 0;
    }

    @Override
    public boolean remove(Integer id) {
        boolean flag = taskMapper.deleteById(id) > 0;
        if (flag) {
            TaskContextHolder.remove(id);
        }
        return flag;
    }

    @Override
    public boolean stop(Integer id) {
        Task task = taskMapper.selectById(id);

        if (TaskStatusEnum.STOP.getCode().equals(task.getStatus())) {
            throw new TaskAlreadyStopException();
        }

        TaskContextHolder.remove(id);
        log.info("*****task[{}] stop at {}*****", task.getTaskName(), DateUtil.now());
        task.setStatus(TaskStatusEnum.STOP.getCode());
        taskMapper.updateById(task);

        return true;
    }

    @Override
    public boolean start(Integer id) {
        Task task = taskMapper.selectById(id);

        if (TaskStatusEnum.RUN.getCode().equals(task.getStatus())) {
            throw new TaskAlreadyStartException();
        }

        TaskContextHolder.schedule(task);
        log.info("*****task[{}] start at {}*****", task.getTaskName(), DateUtil.now());
        task.setStatus(TaskStatusEnum.RUN.getCode());
        taskMapper.updateById(task);

        return true;
    }

    @Override
    public List<MatchDateRespDTO> matchDate(String cron, int num) {
        cronValidate(cron);
        return CronPatternUtil.matchedDates(cron, DateUtil.date(), num, true)
                .stream().map(it -> {
                    MatchDateRespDTO resp = new MatchDateRespDTO();
                    resp.setMatchDate(it);
                    return resp;
                })
                .collect(Collectors.toList());
    }

    private void cronValidate(String cron) {
        try {
            new CronPattern(cron);
        } catch (CronException e) {
            throw new ExpressionNotValidException();
        }
    }

    private void preValidate(Task task) {
        cronValidate(task.getCron());
        try {
            Class<?> aClass = Class.forName(task.getTaskClass());
            if (!TaskExecutor.class.isAssignableFrom(aClass) || !ClassUtil.isNormalClass(aClass)) {
                throw new ClassNotSupportException();
            }
        } catch (ClassNotFoundException e) {
            throw new ClassNotExistsException();
        }
    }
}
