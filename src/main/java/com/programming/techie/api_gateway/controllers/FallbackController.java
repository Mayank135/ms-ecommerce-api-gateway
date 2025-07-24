package com.programming.techie.api_gateway.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {


    @GetMapping("/product-service")
    public ResponseEntity<String> productServiceFallback() {
        return new ResponseEntity<>("Product service is failing. Please try after some time.", HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("/order-service")
    public ResponseEntity<String> orderServiceFallback() {
        return new ResponseEntity<>("Order service is failing. Please try after some time.", HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("/inventory-service")
    public ResponseEntity<String> inventoryServiceFallback() {
        return new ResponseEntity<>("Inventory service is failing. Please try after some time.", HttpStatus.SERVICE_UNAVAILABLE);
    }
}
