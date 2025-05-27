package com.globallogic.bci.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto implements Serializable {

    @JsonProperty("timestamp")
    private Timestamp timestamp;

    @JsonProperty("code")
    private int code;

    @JsonProperty("detail")
    private String detail;
}
