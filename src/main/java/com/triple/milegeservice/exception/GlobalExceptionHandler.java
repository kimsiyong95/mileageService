package com.triple.milegeservice.exception;

import com.triple.milegeservice.domain.common.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ResponseDTO> IllegalArgumentExceptionHandler(IllegalArgumentException e) {
        log.info(HttpStatus.BAD_REQUEST.value() + " : " + e.getMessage());

        final ResponseDTO errorResponse = ResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage()).build();

        return ResponseEntity.badRequest().body(errorResponse);
    }


    @ExceptionHandler(IllegalStateException.class)
    protected ResponseEntity<ResponseDTO> IllegalStateExceptionHandler(IllegalStateException e) {
        log.info(HttpStatus.BAD_REQUEST.value() + " : " + e.getMessage());

        final ResponseDTO errorResponse = ResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage()).build();

        return ResponseEntity.badRequest().body(errorResponse);
    }

}