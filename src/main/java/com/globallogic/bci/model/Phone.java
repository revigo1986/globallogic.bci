package com.globallogic.bci.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Phone implements Serializable {

    @Id
    private UUID id;

    @Column(name = "number", length = 10)
    private long number;

    @Column(name = "cityCode", length = 2)
    private int cityCode;

    @Column(name = "countryCode", length = 3)
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonBackReference
    private User user;
}
