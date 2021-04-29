package com.amarsoft.retz.task.impl;

import com.amarsoft.retz.task.TaskExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class EarlyWarningTask implements TaskExecutor {

    @Resource
    private JavaMailSender mailSender;

    @Override
    public void run() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1228546077@qq.com");
        message.setTo("1987838125@qq.com");
        message.setSubject("下班通知");
        message.setText("我下班了!!!!岑玟琦");
        mailSender.send(message);
        log.info("发送预警邮件成功");
    }
}
