package com.globallogic.bci.exception;

import com.globallogic.bci.dto.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorDto> handleCustomException(CustomException ex) {
        ErrorDto error = new ErrorDto();
        error.setTimestamp(new Timestamp(System.currentTimeMillis()));
        error.setCode(ex.getCode());
        error.setDetail(ex.getMessage());

        return ResponseEntity.status(ex.getCode()).body(error);
    }
}
