package com.globallogic.bci.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

  INVALID_EMAIL(1000, "Not a valid email", HttpStatus.BAD_REQUEST),
  INVALID_PASSWORD(1001, "Not a valid password", HttpStatus.BAD_REQUEST),
  FIELD_REQUIRED(1002, "Field required is missing: ", HttpStatus.BAD_REQUEST),

  USER_DOES_NOT_EXIST(HttpStatus.NOT_FOUND.value(), "The user does not exist", HttpStatus.NOT_FOUND),
  USER_ALREADY_EXISTS(HttpStatus.CONFLICT.value(), "The user already exists", HttpStatus.CONFLICT),

  INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "There is an internal error from the server", HttpStatus.INTERNAL_SERVER_ERROR);

  private final int code;
  private final String message;
  private final HttpStatus httpStatus;

  ErrorCode(int code, String message, HttpStatus httpStatus) {
    this.code = code;
    this.message = message;
    this.httpStatus = httpStatus;
  }

  public int getCode() { return code; }
  public String getMessage() { return message; }
  public HttpStatus getHttpStatus() { return httpStatus; }
}
