package com.icommerceapis.restapis.service;

import com.icommerceapis.restapis.model.request.ProductListRequest;
import com.icommerceapis.restapis.model.response.ProductListResponse;

public interface ProductService {

    ProductListResponse getProductList(ProductListRequest request);
}
