package com.icommerceapis.restapis.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.icommerceapis.common.ResultInformation;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ShoppingCartSaveResponse {

    @JsonProperty("result")
    ResultInformation resultInformation;
}
