package com.icommerceapis.restapis.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@JsonFormat
@ToString(exclude = "password", includeFieldNames = false)
public class LoginRequest {

    @NotBlank
    @JsonProperty("username")
    private String username;

    @NotBlank
    @Size(min = 5)
    @JsonProperty("password")
    private String password;
}
