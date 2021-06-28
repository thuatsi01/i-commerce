package com.icommerceapis.restapis.controller;

import com.icommerceapis.common.Routes;
import com.icommerceapis.restapis.model.request.LoginRequest;
import com.icommerceapis.restapis.model.response.LoginResponse;
import com.icommerceapis.restapis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(Routes.User.BASE_URL)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(Routes.User.LOGIN)
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }
}
