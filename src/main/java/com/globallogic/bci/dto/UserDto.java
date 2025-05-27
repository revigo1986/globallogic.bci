package com.globallogic.bci.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("created")
    private Timestamp created;

    @JsonProperty("lastLogin")
    private Timestamp lastLogin;

    @JsonProperty("token")
    private String token;

    @JsonProperty("isActive")
    private boolean isActive;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("phones")
    private List<PhoneDto> phones;

}
