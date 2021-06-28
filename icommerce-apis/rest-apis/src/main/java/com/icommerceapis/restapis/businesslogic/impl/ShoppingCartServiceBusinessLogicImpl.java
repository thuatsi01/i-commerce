package com.icommerceapis.restapis.businesslogic.impl;

import com.icommerceapis.common.exception.BusinessException;
import com.icommerceapis.common.exception.ErrorCode;
import com.icommerceapis.common.utils.StringUtils;
import com.icommerceapis.common.utils.TimestampUtils;
import com.icommerceapis.restapis.businesslogic.ShoppingCartServiceBusinessLogic;
import com.icommerceapis.restapis.entity.OrderShoppingCartEntity;
import com.icommerceapis.restapis.model.request.ShoppingCartSaveRequest;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartServiceBusinessLogicImpl implements ShoppingCartServiceBusinessLogic {

    @Override
    public void validateSaveRequest(ShoppingCartSaveRequest saveRequest) {
        if (saveRequest == null || saveRequest.getBrandId() == null || StringUtils.isBlank(saveRequest.getSku()))
            throw BusinessException.of(ErrorCode.PRODUCT_NOT_FOUND);

        if (saveRequest.getQuantity() == null)
            throw BusinessException.of(ErrorCode.PRODUCT_QUANTITY_NULL);

        if (saveRequest.getPrice() == null || saveRequest.getPrice() < 1)
            throw BusinessException.of(ErrorCode.PRODUCT_PRICE_LESS_THAN_ZERO);
    }

    @Override
    public OrderShoppingCartEntity convertSaveEntity(ShoppingSaveDto shoppingSaveDto) {
        var productId = shoppingSaveDto.getProductBookingInformation().getProductId();
        var product = shoppingSaveDto.getProductBookingInformation();
        var productBrand = shoppingSaveDto.getProductBookingInformation().getProductBrand();
        var productColour = shoppingSaveDto.getProductBookingInformation().getProductColour();

        var entity = shoppingSaveDto.getCurrentShoppingCart();
        if (entity == null) {
            entity = new OrderShoppingCartEntity();
            entity.setUserId(shoppingSaveDto.getUserId());
            entity.setSku(productId.getSku());
            entity.setBrandId(productId.getBrandId());
            entity.setBrandName(productBrand.getBrand());
            entity.setColourEnglish(productColour.getColourEnglish());
            entity.setColourHex(productColour.getHexCode());
            entity.setDisplayName(product.getDisplayName());
            entity.setQuantity(0L);
            entity.setPrice(product.getPrice());
            entity.setCreateDate(TimestampUtils.getCurrentLocalDateTime());
        }
        var currentQuantity = entity.getQuantity();
        entity.setQuantity(currentQuantity + shoppingSaveDto.getSaveRequest().getQuantity());

        return entity;
    }
}
