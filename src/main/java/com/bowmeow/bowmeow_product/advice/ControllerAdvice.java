package com.bowmeow.bowmeow_product.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * controller advice 클래스
 * - controller 에서 발생하는 오류에 대한 처리를 하는 class
 */
@Slf4j
@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(RuntimeException.class)
    public ProblemDetail handleRuntimeException(Exception e) {
        log.error("RuntimeException occurred: {}", e);
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
