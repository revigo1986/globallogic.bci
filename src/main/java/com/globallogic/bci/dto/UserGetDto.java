package com.globallogic.bci.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserGetDto implements Serializable {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("created")
    private Timestamp created;

    @JsonProperty("lastLogin")
    private Timestamp lastLogin;

    @JsonProperty("token")
    private String token;

    @JsonProperty("isActive")
    private boolean active;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("phones")
    private List<PhoneDto> phones;

}
