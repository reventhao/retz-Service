package com.amarsoft.retz.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@TableName("sys_task")
public class Task {

    @TableId(value = "task_id", type = IdType.AUTO)
    private Integer taskId;

    @TableField("task_name")
    @NotBlank(message = "任务名称不能为空")
    private String taskName;

    @TableField("task_class")
    @NotBlank(message = "类名不能为空")
    private String taskClass;

    @TableField("cron")
    @NotBlank(message = "表达式不能为空")
    private String cron;

    @TableField("status")
    private Integer status;

    @TableField("remark")
    private String remark;
}
