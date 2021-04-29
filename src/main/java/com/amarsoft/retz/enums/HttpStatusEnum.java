package com.amarsoft.retz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpStatusEnum {

    SUCCESS(200, "success"),
    FAIL(500, "fail");

    private final Integer code;
    private final String msg;
}
