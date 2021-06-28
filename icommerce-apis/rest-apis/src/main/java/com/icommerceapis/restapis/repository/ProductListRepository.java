package com.icommerceapis.restapis.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

public interface ProductListRepository {

    List<ProductionInformation> getProductListInformation(ProductListInformationSearchCondition searchCondition);

    @Value
    @Builder
    class ProductListInformationSearchCondition {
        String categoryCode;
        Integer brandId;
        String colourCode;
        Long minPrice;
        Long maxPrice;
        Integer pageSize;
        Integer pageIndex;

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

    @Value
    @AllArgsConstructor
    class ProductionInformation {
        @NotBlank
        String sku;
        String displayName;
        @NotBlank
        Integer brandId;
        @NotBlank
        String brandName;
        String colourCode;
        String colour;
        String colourHexCode;
        @NotBlank
        String categoryCode;
        @NotBlank
        String categoryName;
        @NotBlank
        Long price;
    }
}
