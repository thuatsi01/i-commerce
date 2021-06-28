package com.icommerceapis.restapis.service.impl;

import com.google.common.collect.ImmutableList;
import com.icommerceapis.common.ResultInformation;
import com.icommerceapis.restapis.businesslogic.ProductListBusinessLogic;
import com.icommerceapis.restapis.model.request.ProductListRequest;
import com.icommerceapis.restapis.model.response.ProductListResponse;
import com.icommerceapis.restapis.model.response.dto.ProductDto;
import com.icommerceapis.restapis.repository.ProductListRepository;
import com.icommerceapis.restapis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductListRepository productListRepository;

    @Autowired
    private ProductListBusinessLogic productListBusinessLogic;

    @Override
    public ProductListResponse getProductList(ProductListRequest request) {
        var searchCondition = productListBusinessLogic.covertRequestToCondition(request);
        var productInformationList = productListRepository.getProductListInformation(searchCondition)
                .stream()
                .map(productEntity -> ProductDto.builder()
                        .sku(productEntity.getSku())
                        .displayName(productEntity.getDisplayName())
                        .brandId(productEntity.getBrandId())
                        .brandName(productEntity.getBrandName())
                        .colourCode(productEntity.getColourCode())
                        .categoryName(productEntity.getColour())
                        .colourHex(productEntity.getColourHexCode())
                        .categoryName(productEntity.getCategoryName())
                        .categoryCode(productEntity.getCategoryCode())
                        .price(productEntity.getPrice())
                        .build()
                ).collect(ImmutableList.toImmutableList());
        return ProductListResponse.builder()
                .resultInformation(ResultInformation.success())
                .productList(productInformationList)
                .build();
    }
}
