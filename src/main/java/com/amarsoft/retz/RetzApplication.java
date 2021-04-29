package com.amarsoft.retz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.amarsoft.retz.mapper")
@SpringBootApplication
public class RetzApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetzApplication.class, args);
    }

}
