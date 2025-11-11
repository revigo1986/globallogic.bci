package com.globallogic.bci.dto;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {

  private Timestamp timestamp;
  private int code;
  private String detail;

  public ErrorDto(int code, String detail) {
    this.timestamp = new Timestamp(System.currentTimeMillis());
    this.code = code;
    this.detail = detail;
  }
}
