package com.icommerceapis.restapis.model.response.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    @JsonProperty("sku")
    String sku;

    @JsonProperty("display_name")
    String displayName;

    @JsonProperty("brand_id")
    Integer brandId;

    @JsonProperty("brand_name")
    String brandName;

    @JsonProperty("category_code")
    String categoryCode;

    @JsonProperty("category_name")
    String categoryName;

    @JsonProperty("colour_code")
    String colourCode;

    @JsonProperty("colour_name")
    String colourName;

    @JsonProperty("colour_hex")
    String colourHex;

    @JsonProperty("price")
    Long price;
}
