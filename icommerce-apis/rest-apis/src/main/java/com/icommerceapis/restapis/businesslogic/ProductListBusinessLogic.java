package com.icommerceapis.restapis.businesslogic;

import com.icommerceapis.restapis.model.request.ProductListRequest;
import com.icommerceapis.restapis.repository.ProductListRepository;

public interface ProductListBusinessLogic {

    ProductListRepository.ProductListInformationSearchCondition covertRequestToCondition(ProductListRequest request);
}
