package com.icommerceapis.restapis.businesslogic.impl;

import com.icommerceapis.restapis.businesslogic.ProductListBusinessLogic;
import com.icommerceapis.restapis.model.request.ProductListRequest;
import com.icommerceapis.restapis.repository.ProductListRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductListBusinessLogicImpl implements ProductListBusinessLogic {

    @Override
    public ProductListRepository.ProductListInformationSearchCondition covertRequestToCondition(ProductListRequest request) {
        return ProductListRepository.ProductListInformationSearchCondition.builder()
                .brandId(request.getBrandId().orElse(null))
                .categoryCode(request.getCategoryCode().orElse(null))
                .colourCode(request.getColourCode().orElse(null))
                .maxPrice(request.getMaxPrice().orElse(null))
                .minPrice(request.getMinPrice().orElse(null))
                .pageIndex(request.getPageIndex())
                .pageSize(request.getPageSize())
                .build();
    }
}
