package com.globallogic.bci.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Phone {

    @Column(name = "number", length = 10)
    private long number;

    @Column(name = "cityCode", length = 2)
    private int cityCode;

    @Column(name = "countryCode", length = 3)
    private String countryCode;
}
