package com.icommerceapis.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    // -1xxx -> HTTP 401
    HTTP_METHOD_AUTH_NOT_SUPPORT(-1000, "Failed to parse jwt token"),
    JWT_PARSE_ERROR(-1001, "Failed to parse jwt token"),
    JWT_MISSING_ERROR(-1002, "Missed jwt token"),
    WRONG_USER_NAME(-1003, "Wrong username"),
    WRONG_PASSWORD(-1004, "Wrong password"),
    USER_INVALID_ROLE(-1005, "Invalid role"),
    USER_NOT_FOUND(-1006, "User is not found"),
    USER_ACCESS_DENIED(-1007, "User is denied"),
    ROLE_SETTING_MISMATCH(-1008, "Role setting is missed match"),
    LOGIN_FAILED_MANY_TIMES(-1009, "You logged with wrong account many time"),
    USERNAME_PASSWORD_NOT_BLANK(-1010, "Username or password can not be blank"),
    USERNAME_PASSWORD_LENGTH(-1011, "The length of username or password is not less than 6"),

    // -2xxx
    PRODUCT_NOT_FOUND(-2000, "Your product not found"),
    PRODUCT_SKU_BRAND_ID_NULL(-2001, "Your requested product is null"),
    PRODUCT_QUANTITY_NULL(-2002, "Your quantity is null"),
    PRODUCT_PRICE_LESS_THAN_ZERO(-2003, "Your price is less than 0"),
    PRODUCT_PRICE_NOT_MATCH(-2004, "Your price is not match"),
    SHOPPING_CART_NOT_SAVED(-2005, "Your shopping cart is not saved"),
    PRODUCT_QUANTITY_LESS_THAN_ZERO(-2006, "Your quantity less than 0"),

    // -3xxx
    REQUEST_NULL(-3001, "Request is empty"),

    //-9999
    OTHER(-9999, "Unexpected error");


    Integer code;
    String message;
}
