package com.icommerceapis.restapis.businesslogic;

import com.icommerceapis.restapis.entity.OrderShoppingCartEntity;
import com.icommerceapis.restapis.model.request.ShoppingCartSaveRequest;
import com.icommerceapis.restapis.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Value;

public interface ShoppingCartServiceBusinessLogic {

    void validateSaveRequest(ShoppingCartSaveRequest saveRequest);

    OrderShoppingCartEntity convertSaveEntity(ShoppingSaveDto shoppingSaveDto);

    @Value
    @AllArgsConstructor(staticName = "of")
    class ShoppingSaveDto {
        Integer userId;
        ShoppingCartSaveRequest saveRequest;
        OrderShoppingCartEntity currentShoppingCart;
        ProductRepository.ProductBookingInformation productBookingInformation;
    }
}
