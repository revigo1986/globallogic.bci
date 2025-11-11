package com.globallogic.bci.exception;

import com.globallogic.bci.dto.MultiErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class  GlobalExceptionHandler {

    @ExceptionHandler(MultipleCustomException.class)
    public ResponseEntity<MultiErrorDto> handleMultipleCustomException(MultipleCustomException ex) {
      MultiErrorDto response = new MultiErrorDto(ex.getErrors());

      return ResponseEntity
          .status(ex.getHttpStatus())
          .body(response);
    }
}
