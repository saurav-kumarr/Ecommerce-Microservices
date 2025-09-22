package com.ecommerce.controller;

import com.ecommerce.dto.CartItemRequest;
import com.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart(
            @RequestHeader("X-User-ID") String userId,
            @RequestBody CartItemRequest request){
       if( !cartService.addToCart(userId, request)){
           return ResponseEntity.badRequest()
                   .body("Product out of Stock or User not found or Product not found");
       }
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");

    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void>  removeFromCart(
            @RequestHeader("X-User-ID") String userId,
           @PathVariable Long productId){

       boolean deleted =  cartService.deleteItemFromCart(userId,productId);

       return deleted ? ResponseEntity.noContent().build()
               : ResponseEntity.notFound().build();
    }

}
