package com.globallogic.bci.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "User")
public class User implements Serializable {

    @Id
    private UUID id;

    @Column(name = "created", columnDefinition = "TIMESTAMP")
    private Timestamp created;

    @Column(name = "lastLogin", columnDefinition = "TIMESTAMP")
    private Timestamp lastLogin;

    @Column(name = "token", length = 210)
    private String token;

    @Column(name = "isActive", length = 1)
    @JsonProperty("isActive")
    private boolean active;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "email", length = 20)
    private String email;

    @Column(name = "password", length = 15)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Phone> phones;

}

