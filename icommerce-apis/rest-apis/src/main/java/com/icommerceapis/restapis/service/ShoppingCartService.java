package com.icommerceapis.restapis.service;

import com.icommerceapis.restapis.model.request.ShoppingCartSaveRequest;
import com.icommerceapis.restapis.model.response.ShoppingCartSaveResponse;

public interface ShoppingCartService {

    ShoppingCartSaveResponse saveShoppingCart(Integer userId, ShoppingCartSaveRequest request);
}
