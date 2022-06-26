package com.triple.milegeservice.domain.common;

import lombok.Getter;

@Getter
public enum HttpStatusCustom {
    USER_PARAM_ERROR(400, "사용자 파라미터 오류"),
    DUPLACATE_DATA(400, "중복 데이터 오류");

    private int code;
    private String message;

    HttpStatusCustom(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
