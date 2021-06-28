package com.icommerceapis.restapis.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import com.icommerceapis.common.ResultInformation;
import com.icommerceapis.restapis.model.response.dto.ProductDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductListResponse {
    @JsonProperty("result")
    ResultInformation resultInformation;

    @JsonProperty("products")
    ImmutableList<ProductDto> productList;
}
