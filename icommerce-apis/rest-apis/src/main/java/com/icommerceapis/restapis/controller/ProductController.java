package com.icommerceapis.restapis.controller;

import com.icommerceapis.common.Routes;
import com.icommerceapis.restapis.model.request.ProductListRequest;
import com.icommerceapis.restapis.model.response.ProductListResponse;
import com.icommerceapis.restapis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(Routes.Product.BASE_URL)
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(Routes.Product.LIST)
    public ResponseEntity<ProductListResponse> getProductList(@RequestBody @Valid ProductListRequest request) {
        return ResponseEntity.ok(productService.getProductList(request));
    }
}
