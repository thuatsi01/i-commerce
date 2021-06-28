package com.icommerceapis.restapis.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.icommerceapis.common.ResultInformation;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "of")
public class DefaultResponse {
    @JsonProperty("result")
    ResultInformation resultInformation;
}
