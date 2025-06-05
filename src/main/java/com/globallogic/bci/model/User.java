package com.globallogic.bci.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import java.io.Serializable;
import java.sql.Timestamp;
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

    @NotNull
    @Column(name = "created", columnDefinition = "TIMESTAMP")
    private Timestamp created;

    @NotNull
    @Column(name = "lastLogin", columnDefinition = "TIMESTAMP")
    private Timestamp lastLogin;

    @NotNull
    @Column(name = "token", length = 250)
    private String token;

    @NotNull
    @Column(name = "isActive", length = 1)
    @JsonProperty("isActive")
    private boolean active;

    @Column(name = "name", length = 50)
    private String name;

    @NotNull
    @Column(name = "email", length = 30)
    private String email;

    @NotNull
    @Column(name = "password", length = 345)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Phone> phones;

}

