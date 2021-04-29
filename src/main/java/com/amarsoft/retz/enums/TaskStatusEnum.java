package com.amarsoft.retz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskStatusEnum {

    STOP(0),
    RUN(1);

    private final Integer code;
}
