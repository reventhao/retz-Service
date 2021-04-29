package com.amarsoft.retz.service;

import com.amarsoft.retz.dto.MatchDateRespDTO;
import com.amarsoft.retz.po.Task;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public interface TaskService {

    Page<Task> queryPage(Integer pageNum, Integer pageSize);

    Task query(Integer id);

    boolean add(Task task);

    boolean update(Task task);

    boolean remove(Integer id);

    boolean stop(Integer id);

    boolean start(Integer id);

    List<MatchDateRespDTO> matchDate(String cron, int num);
}
