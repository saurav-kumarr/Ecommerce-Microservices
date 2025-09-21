package com.ecommerce.controller;

import com.ecommerce.dto.ProductRequest;
import com.ecommerce.dto.ProductResponse;
import com.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {


    private final ProductService productService;

    @PostMapping
     public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest){

         return new ResponseEntity<>(productService.createProduct(productRequest),
                 HttpStatus.CREATED);

     }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequest productRequest){

        return productService.updateProduct(id, productRequest)
                .map(ResponseEntity :: ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

}
