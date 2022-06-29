package com.triple.milegeservice.domain.common;

import lombok.Getter;

@Getter
public enum HttpStatusCustom {
    USER_PARAM_ERROR(400, "사용자 파라미터 오류"),
    DUPLACATE_DATA(400, "중복 데이터 오류"),

    USER_NOT_ALLOWED(401, "허용하지 않은 사용자"),

    USER_NOT_FOUND(400, "사용자 데이터 없음");

    private int code;
    private String message;

    HttpStatusCustom(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
