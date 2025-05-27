package com.globallogic.bci.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDto implements Serializable {

    @JsonProperty("number")
    private long number;

    @JsonProperty("cityCode")
    private int cityCode;

    @JsonProperty("countryCode")
    private String countryCode;
}
