package com.globallogic.bci.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "User")
public class User {

    @Id
    private String id;

    @Column(name = "created", columnDefinition = "TIMESTAMP")
    private Timestamp created;

    @Column(name = "lastLogin", columnDefinition = "TIMESTAMP")
    private Timestamp lastLogin;

    @Column(name = "token", length = 50)
    private String token;

    @Column(name = "isActive", length = 1)
    private boolean isActive;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "email", length = 15)
    private String email;

    @Column(name = "password", length = 15)
    private String password;

    @EmbeddedId
    private Phone phone;

}
