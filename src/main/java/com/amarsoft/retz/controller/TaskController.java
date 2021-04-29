package com.amarsoft.retz.controller;

import com.amarsoft.retz.dto.MatchDateRespDTO;
import com.amarsoft.retz.model.CommonResult;
import com.amarsoft.retz.po.Task;
import com.amarsoft.retz.service.TaskService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {

    @Resource
    private TaskService taskService;

    @PostMapping
    public CommonResult<Boolean> add(@Validated @RequestBody Task task) {
        return CommonResult.success(taskService.add(task));
    }

    @PutMapping
    public CommonResult<Boolean> update(@Validated @RequestBody Task task) {
        return CommonResult.success(taskService.update(task));
    }

    @GetMapping("{id}")
    public CommonResult<Task> query(@PathVariable("id") Integer id) {
        return CommonResult.success(taskService.query(id));
    }

    @GetMapping
    public CommonResult<Page<Task>> queryPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "1") Integer pageSize) {
        return CommonResult.success(taskService.queryPage(pageNum, pageSize));
    }

    @DeleteMapping("{id}")
    public CommonResult<Boolean> remove(@PathVariable("id") Integer id) {
        return CommonResult.success(taskService.remove(id));
    }

    @PatchMapping("start/{id}")
    public CommonResult<Boolean> start(@PathVariable("id") Integer id) {
        return CommonResult.success(taskService.start(id));
    }

    @PatchMapping("stop/{id}")
    public CommonResult<Boolean> stop(@PathVariable("id") Integer id) {
        return CommonResult.success(taskService.stop(id));
    }

    @GetMapping("match")
    public CommonResult<List<MatchDateRespDTO>> match(@RequestParam("cron") String cron,
                                                      @RequestParam(value = "num", defaultValue = "5") Integer num) {
        return CommonResult.success(taskService.matchDate(cron, num));
    }
}
