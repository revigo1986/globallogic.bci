package com.globallogic.bci.exception;

import com.globallogic.bci.dto.ErrorDto;
import java.util.List;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MultipleCustomException extends RuntimeException {

  private final List<ErrorDto> errors;
  private final HttpStatus httpStatus;

  public MultipleCustomException(List<ErrorDto> errors, HttpStatus httpStatus, String message) {
    super(message);
    this.httpStatus = httpStatus;
    this.errors = errors;
  }
}
