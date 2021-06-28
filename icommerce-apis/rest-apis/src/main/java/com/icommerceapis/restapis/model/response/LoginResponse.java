package com.icommerceapis.restapis.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.icommerceapis.common.ResultInformation;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LoginResponse {

    @JsonProperty("result")
    ResultInformation resultInformation;

    @JsonProperty("token")
    String token;
}
