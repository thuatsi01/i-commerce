package com.icommerceapis.restapis.controller;

import com.icommerceapis.common.Routes;
import com.icommerceapis.restapis.configuration.jwt.Auth;
import com.icommerceapis.restapis.model.request.ShoppingCartSaveRequest;
import com.icommerceapis.restapis.model.response.ShoppingCartSaveResponse;
import com.icommerceapis.restapis.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(Routes.ShoppingCart.BASE_URL)
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping(Routes.ShoppingCart.SAVE)
    public ResponseEntity<ShoppingCartSaveResponse> saveShoppingCart(Authentication authentication, @Valid @RequestBody ShoppingCartSaveRequest request) {
        var userId = ((Auth) authentication.getPrincipal()).getUserId();
        return ResponseEntity.ok(shoppingCartService.saveShoppingCart(userId, request));
    }
}
