package com.icommerceapis.restapis.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Data
@JsonFormat
public class ProductListRequest {
    @JsonProperty("category_code")
    private String categoryCode;

    @JsonProperty("brand_id")
    private Integer brandId;

    @JsonProperty("colour_code")
    private String colourCode;

    @JsonProperty("min_price")
    private Long minPrice;

    @JsonProperty("max_price")
    private Long maxPrice;

    @NotNull
    @JsonProperty("page_size")
    private Integer pageSize;

    @NotNull
    @JsonProperty("page_index")
    private Integer pageIndex;

    public Optional<String> getCategoryCode() {
        return Optional.ofNullable(categoryCode);
    }

    public Optional<Integer> getBrandId() {
        return Optional.ofNullable(brandId);
    }

    public Optional<String> getColourCode() {
        return Optional.ofNullable(colourCode);
    }

    public Optional<Long> getMinPrice() {
        return Optional.ofNullable(minPrice);
    }

    public Optional<Long> getMaxPrice() {
        return Optional.ofNullable(maxPrice);
    }
}
