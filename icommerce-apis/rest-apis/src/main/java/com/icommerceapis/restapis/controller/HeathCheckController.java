package com.icommerceapis.restapis.controller;

import com.icommerceapis.common.Routes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeathCheckController {

    @GetMapping(Routes.HEATH_CHECK)
    public ResponseEntity<String> heathCheck() {
        return ResponseEntity.ok("OK!");
    }
}
