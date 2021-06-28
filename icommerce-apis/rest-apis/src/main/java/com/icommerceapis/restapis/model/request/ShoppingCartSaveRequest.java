package com.icommerceapis.restapis.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonFormat
public class ShoppingCartSaveRequest {

    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("sku")
    private String sku;

    @NotNull
    @JsonProperty("brand_id")
    private Integer brandId;

    @NotNull
    @JsonProperty("price")
    private Long price;

    @NotNull
    @JsonProperty("quantity")
    private Long quantity;
}
