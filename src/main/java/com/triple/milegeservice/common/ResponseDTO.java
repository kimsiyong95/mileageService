package com.triple.milegeservice.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {

    int code;
    String message;

    @JsonInclude(NON_NULL)
    T data;

    public ResponseDTO setStatus(HttpStatus httpStatus, T data){
        code = httpStatus.value();
        message = httpStatus.name();
        this.data = data;

        return this;
    }

}
