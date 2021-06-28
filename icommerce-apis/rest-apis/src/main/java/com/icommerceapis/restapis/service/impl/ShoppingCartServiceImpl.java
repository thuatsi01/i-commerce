package com.icommerceapis.restapis.service.impl;

import com.icommerceapis.common.ResultInformation;
import com.icommerceapis.common.exception.BusinessException;
import com.icommerceapis.common.exception.ErrorCode;
import com.icommerceapis.restapis.businesslogic.ShoppingCartServiceBusinessLogic;
import com.icommerceapis.restapis.entity.embeddedkey.ProductId;
import com.icommerceapis.restapis.model.request.ShoppingCartSaveRequest;
import com.icommerceapis.restapis.model.response.ShoppingCartSaveResponse;
import com.icommerceapis.restapis.repository.OrderShoppingCartRepository;
import com.icommerceapis.restapis.repository.ProductRepository;
import com.icommerceapis.restapis.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderShoppingCartRepository orderShoppingCartRepository;

    @Autowired
    private ShoppingCartServiceBusinessLogic shoppingCartServiceBusinessLogic;

    @Override
    @Transactional
    public ShoppingCartSaveResponse saveShoppingCart(Integer userId, ShoppingCartSaveRequest request) {
        shoppingCartServiceBusinessLogic.validateSaveRequest(request);
        var productBooking = productRepository
                .findByProductIdAndIsDelete(new ProductId(request.getSku(), request.getBrandId()), Boolean.FALSE)
                .orElseThrow(() ->
                        BusinessException.of(ErrorCode.PRODUCT_NOT_FOUND)
                );

        if (!productBooking.getPrice().equals(request.getPrice())) {
            throw BusinessException.of(ErrorCode.PRODUCT_PRICE_NOT_MATCH);
        }

        var currentShoppingCart = orderShoppingCartRepository
                .findAllByBrandIdAndSkuAndUserId(request.getBrandId(), request.getSku(), userId)
                .orElse(null);

        var saveDto = ShoppingCartServiceBusinessLogic.ShoppingSaveDto.of(
                userId,
                request,
                currentShoppingCart,
                productBooking
        );
        var shoppingCartDto = shoppingCartServiceBusinessLogic.convertSaveEntity(saveDto);

        if (shoppingCartDto.getQuantity() < 0) {
            throw BusinessException.of(ErrorCode.PRODUCT_QUANTITY_LESS_THAN_ZERO);
        }

        var result = orderShoppingCartRepository.save(shoppingCartDto);

        if (result.getId() < 1) {
            throw BusinessException.of(ErrorCode.SHOPPING_CART_NOT_SAVED);
        }
        return ShoppingCartSaveResponse.builder()
                .resultInformation(ResultInformation.success())
                .build();
    }
}
